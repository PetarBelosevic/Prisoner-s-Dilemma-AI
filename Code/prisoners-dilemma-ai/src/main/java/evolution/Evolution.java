package evolution;

import evolution.specimen.ISpecimen;

/**
 * <p>
 *     Basic decorator for IEvolution
 * </p>
 * @param <T> type of specimen
 */
public class Evolution<T extends ISpecimen<T>> implements IEvolution<T> {
    protected IEvolution<T> evolution;

    public Evolution(IEvolution<T> evolution) {
        this.evolution = evolution;
    }

    @Override
    public T getBestSpecimen() {
        return evolution.getBestSpecimen();
    }

    @Override
    public T getWorstSpecimen() {
        return evolution.getWorstSpecimen();
    }

    @Override
    public T getMedianSpecimen() {
        return evolution.getMedianSpecimen();
    }

    @Override
    public void generateNextGeneration() {
        evolution.generateNextGeneration();
    }

    @Override
    public int getCurrentGenerationIndex() {
        return evolution.getCurrentGenerationIndex();
    }

    @Override
    public double getSmallMutationChance() {
        return evolution.getSmallMutationChance();
    }

    @Override
    public void setSmallMutationChance(double smallMutationChance) {
        evolution.setSmallMutationChance(smallMutationChance);
    }

    @Override
    public double getSmallMutationMagnitude() {
        return evolution.getSmallMutationMagnitude();
    }

    @Override
    public void setSmallMutationMagnitude(double smallMutationMagnitude) {
        evolution.setSmallMutationMagnitude(smallMutationMagnitude);
    }

    @Override
    public double getBigMutationChance() {
        return evolution.getBigMutationChance();
    }

    @Override
    public void setBigMutationChance(double bigMutationChance) {
        evolution.setBigMutationChance(bigMutationChance);
    }

    @Override
    public double getBigMutationMagnitude() {
        return evolution.getBigMutationMagnitude();
    }

    @Override
    public void setBigMutationMagnitude(double bigMutationMagnitude) {
        evolution.setBigMutationMagnitude(bigMutationMagnitude);
    }

    @Override
    public int getGenerationSize() {
        return evolution.getGenerationSize();
    }
}
