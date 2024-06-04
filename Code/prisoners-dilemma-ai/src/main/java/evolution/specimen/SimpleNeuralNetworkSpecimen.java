package evolution.specimen;

import neuralNetwork.IllegalArchitectureException;
import neuralNetwork.NeuralNetwork;
import neuralNetwork.layer.ILayer;
import org.nd4j.linalg.api.ndarray.INDArray;
import utils.Constants;
import utils.UtilityFunctions;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

/**
 * <p>
 *     Neural network fitted for evolution (genetic algorithm).
 * </p>
 */
public class SimpleNeuralNetworkSpecimen extends NeuralNetwork implements ISpecimen<SimpleNeuralNetworkSpecimen> {
    private double fitness = 0;

    public SimpleNeuralNetworkSpecimen(ILayer... layers) {
        super(layers);
    }

    public SimpleNeuralNetworkSpecimen(int... layerSizes) {
        super(layerSizes);
    }

    public SimpleNeuralNetworkSpecimen(Path fileName) {
        super();
        loadFromFile(fileName);
    }

    @Override
    public void mutate(double smallMutationChance, double smallMutationMagnitude, double bigMutationChance, double bigMutationMagnitude) {
        double change;
        for (ILayer layer: getLayers()) {
            for (INDArray parameter: layer.getParameters()) {
                for (int i = 0; i < parameter.length(); i++) {
                    change = UtilityFunctions.generateMutation(smallMutationChance, smallMutationMagnitude, bigMutationChance, bigMutationMagnitude);
                    if (change != 0.0) {
                        parameter.putScalar(i, parameter.getDouble(i) + change);
                    }
                }
            }
        }
    }

    @Override
    public void createOffspring(SimpleNeuralNetworkSpecimen parent1, SimpleNeuralNetworkSpecimen parent2) {
        double totalFitness = parent1.getFitness() + parent2.getFitness();
        for (int i = 0; i < getDepth()-1; i++) {
            for (int j = 0; j < getLayer(i).getParameters().length; j++) {
                for (int k = 0; k < getLayer(i).getParameters()[j].length(); k++) {
                    if (Math.random() * totalFitness < parent1.getFitness()) {
                        getLayer(i).getParameters()[j].putScalar(k, parent1.getLayer(i).getParameters()[j].getDouble(k));
                    }
                    else {
                        getLayer(i).getParameters()[j].putScalar(k, parent2.getLayer(i).getParameters()[j].getDouble(k));
                    }
//                    getLayer(i).getParameters()[j].putScalar(
//                            k,
//                            (parent1.getLayer(i).getParameters()[j].getDouble(k) + parent2.getLayer(i).getParameters()[j].getDouble(k)) / 2
//                    );
                }
            }
        }
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void addToFitness(double fitness) {
        this.fitness += fitness;
    }

    @Override
    public void resetFitness() {
        fitness = 0;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArchitectureException if other neural network doesn't have same architecture
     * @param other specimen which is copied into this one
     */
    @Override
    public void copyFrom(SimpleNeuralNetworkSpecimen other) {
        if (getDepth() != other.getDepth()) {
            throw new IllegalArchitectureException();
        }

        for (int i = 0; i < getDepth()-1; i++) {
            for (int j = 0; j < getLayer(i).getParameters().length; j++) {
                for (int k = 0; k < getLayer(i).getParameters()[j].length(); k++) {
                    getLayer(i).getParameters()[j].putScalar(k, other.getLayer(i).getParameters()[j].getDouble(k));
                }
            }
        }
    }

    @Override
    public void saveInFile(Path filePath) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath.toString(), false))) {
            os.writeInt(getDepth()-1);

            for (ILayer layer: getLayers()) {
                os.writeObject(layer.getClass().getCanonicalName());
                os.writeObject(layer.getActivationFunctionName());

                os.writeInt(layer.getParameters().length);
                for (INDArray parameter: layer.getParameters()) {
                    os.writeObject(parameter);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadFromFile(Path filePath) {
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(filePath.toString()))) {
            int depth = is.readInt();
            ILayer[] layers = new ILayer[depth];

            for (int i = 0; i < depth; i++) {
                String className = (String) is.readObject();
                String activationFunctionName = (String) is.readObject();

                int parametersNumber = is.readInt();
                INDArray[] parameters = new INDArray[parametersNumber];
                for (int j = 0; j < parametersNumber; j++) {
                    parameters[j] = (INDArray) is.readObject();
                }

                layers[i] = (ILayer) Class.forName(className).getConstructor(String.class).newInstance(activationFunctionName);
                layers[i].setParameters(parameters);
            }
            setAllLayers(layers);
        }
        catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
               IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
