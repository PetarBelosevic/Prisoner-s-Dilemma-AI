package evolution.specimen;

import neuralNetwork.NeuralNetwork;

public class NeuralNetworkSpecimen extends NeuralNetwork implements ISpecimen {
    private int fitness;
    private boolean modified;
    @Override
    public void mutate(double smallMutationChance, int smallMutationMagnitude, double bigMutationChance, int bigMutationMagnitude) {

    }

    @Override
    public void createOffspring(ISpecimen parent1, ISpecimen parent2) {

    }

    @Override
    public int getFitness() {
        return fitness;
    }

    @Override
    public boolean isModified() {
        return modified;
    }
}
