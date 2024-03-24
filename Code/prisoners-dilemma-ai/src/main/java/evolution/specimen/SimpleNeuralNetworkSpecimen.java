package evolution.specimen;

import neuralNetwork.IllegalArchitectureException;
import neuralNetwork.NeuralNetwork;
import neuralNetwork.layer.ILayer;
import neuralNetwork.layer.SigmoidLayer;
import org.nd4j.linalg.factory.Nd4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class SimpleNeuralNetworkSpecimen extends NeuralNetwork implements ISpecimen<SimpleNeuralNetworkSpecimen> {
    private static final Path DEFAULT_STORAGE = Path.of("src/main/resources/");
    private int fitness = 0;

    public SimpleNeuralNetworkSpecimen(int... layerSizes) {
        super(layerSizes);
    }

    public SimpleNeuralNetworkSpecimen(String fileName) {
        super();
        loadFromFile(fileName);
    }

    @Override
    public void mutate(double smallMutationChance, double smallMutationMagnitude, double bigMutationChance, double bigMutationMagnitude) {
        double change;
        for (ILayer layer: getLayers()) {
            for (int i = 0; i < layer.getWeights().length(); i++) {
                change = generateMutation(smallMutationChance, smallMutationMagnitude, bigMutationChance, bigMutationMagnitude);
                if (change != 0) {
                    layer.getWeights().putScalar(i, layer.getWeights().getDouble(i) + change);
                }
            }
            for (int i = 0; i < layer.getBiases().length(); i++) {
                change = generateMutation(smallMutationChance, smallMutationMagnitude, bigMutationChance, bigMutationMagnitude);
                if (change != 0) {
                    layer.getBiases().putScalar(i, layer.getBiases().getDouble(i) + change);
                }
            }
        }
    }

    /**
     * <p>
     *     Generates random mutation.
     * </p>
     * Mutation can be big, small or can be 0.
     *
     * @param smallMutationChance chance for small mutation to occur
     * @param smallMutationMagnitude magnitude of small mutation
     * @param bigMutationChance chance for big mutation to occur if small mutation didn't occur
     * @param bigMutationMagnitude magnitude of big mutation
     * @return random mutation
     */
    double generateMutation(double smallMutationChance, double smallMutationMagnitude, double bigMutationChance, double bigMutationMagnitude) {
        if (Math.random() < smallMutationChance) {
            return Math.random() * smallMutationMagnitude;
        }
        else if (Math.random() < bigMutationChance) {
            return Math.random() * bigMutationMagnitude;
        }
        return  0.0;
    }

    @Override
    public void createOffspring(SimpleNeuralNetworkSpecimen parent1, SimpleNeuralNetworkSpecimen parent2) {
        int totalFitness = parent1.getFitness() + parent2.getFitness();
        for (int i = 0; i < getDepth()-1; i++) {
            for (int j = 0; j < getLayer(i).getWeights().length(); j++) {
                if (Math.random() * totalFitness < parent1.getFitness()) {
                    getLayer(i).getWeights().putScalar(j, parent1.getLayer(i).getWeights().getDouble(j));
                }
                else {
                    getLayer(i).getWeights().putScalar(j, parent2.getLayer(i).getWeights().getDouble(j));
                }
            }
            for (int j = 0; j < getLayer(i).getBiases().length(); j++) {
                if (Math.random() * totalFitness < parent1.getFitness()) {
                    getLayer(i).getBiases().putScalar(j, parent1.getLayer(i).getBiases().getDouble(j));
                }
                else {
                    getLayer(i).getBiases().putScalar(j, parent2.getLayer(i).getBiases().getDouble(j));
                }
            }
        }
    }

    @Override
    public int getFitness() {
        return fitness;
    }

    @Override
    public void addToFitness(int fitness) {
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
            if (getLayer(i).getRowNumber() != other.getLayer(i).getRowNumber() || getLayer(i).getColumnNumber() != other.getLayer(i).getColumnNumber()) {
                throw new IllegalArchitectureException();
            }
            for (int j = 0; j < getLayer(i).getWeights().length(); j++) {
                getLayer(i).getWeights().putScalar(j, other.getLayer(i).getWeights().getDouble(j));
            }
            for (int j = 0; j < getLayer(i).getBiases().length(); j++) {
                getLayer(i).getBiases().putScalar(j, other.getLayer(i).getBiases().getDouble(j));
            }
        }
    }

    @Override
    public void saveInFile(String fileName) {
        Path destination = DEFAULT_STORAGE.resolve(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(destination, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(getDepth()-1 + "\n");
            for (int i = 0; i < getDepth()-1; i++) {
                writer.write(getLayer(i).getRowNumber() + "," + getLayer(i).getColumnNumber() + "\n");
                writer.write(getLayer(i).getBiases().toStringFull() + "\n");
                writer.write(getLayer(i).getWeights().toStringFull() + "\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadFromFile(String fileName) {
        Path destination = DEFAULT_STORAGE.resolve(fileName);
        try (BufferedReader reader = Files.newBufferedReader(destination)) {
            int depth = Integer.parseInt(reader.readLine());
            ILayer[] layers = new ILayer[depth];

            for (int i = 0; i < depth; i++) {
                String[] dimensions = reader.readLine().split(",");
                int rows = Integer.parseInt(dimensions[0]);
                int columns = Integer.parseInt(dimensions[1]);

                double[] biases = new double[rows];
                double[][] weights = new double[rows][columns];

                String biasesString = reader.readLine();
                String[] biasesStringSplit = biasesString.substring(2, biasesString.length() - 1).split(",");
                for (int j = 0; j < rows; j++) {
                    biases[j] = Double.parseDouble(biasesStringSplit[j].trim());
                }

                for (int j = 0; j < rows; j++) {
                    String row = reader.readLine();
                    String[] rowSplit = row.substring(2, row.length() - 3).split(",");
                    for (int k = 0; k < columns; k++) {
                        weights[j][k] = Double.parseDouble(rowSplit[k].trim());
                    }
                }
                layers[i] = new SigmoidLayer(Nd4j.create(weights), Nd4j.create(biases)); // TODO prepoznati tip layera?
            }
            setAllLayers(layers);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
