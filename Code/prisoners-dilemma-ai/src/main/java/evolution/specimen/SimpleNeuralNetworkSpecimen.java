package evolution.specimen;

import neuralNetwork.NeuralNetwork;
import neuralNetwork.layer.ILayer;


public class SimpleNeuralNetworkSpecimen extends NeuralNetwork implements ISpecimen<SimpleNeuralNetworkSpecimen> {
    private int fitness;
    private boolean modified;

    @Override
    public void mutate(double smallMutationChance, int smallMutationMagnitude, double bigMutationChance, int bigMutationMagnitude) {
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
                    layer.getBiases().putScalar(i, layer.getWeights().getDouble(i) + change);
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
    double generateMutation(double smallMutationChance, int smallMutationMagnitude, double bigMutationChance, int bigMutationMagnitude) {
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
        for (int i = 0; i < getLayers().length; i++) {
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

    @Override
    public boolean isModified() {
        return modified;
    }
}
