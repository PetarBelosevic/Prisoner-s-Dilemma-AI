package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.ISpecimenFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Implements basic functionalities of IEvolution interface.
 * </p>
 * @param <T> extends ISpecimen
 */
public abstract class AbstractEvolution<T extends ISpecimen> implements IEvolution<T> {
    protected double smallMutationChance;
    protected int smallMutationMagnitude;
    protected double bigMutationChance;
    protected int bigMutationMagnitude;
    protected boolean oneParent = false;
    protected int currentGenerationIndex = 0;
    protected int generationSize;
    protected List<T> currentGeneration;
    protected List<T> nextGeneration;

    protected AbstractEvolution(double smallMutationChance, int smallMutationAmplitude, double bigMutationChance, int bigMutationAmplitude, int generationSize, ISpecimenFactory<T> factory) {
        this.smallMutationChance = smallMutationChance;
        this.smallMutationMagnitude = smallMutationAmplitude;
        this.bigMutationChance = bigMutationChance;
        this.bigMutationMagnitude = bigMutationAmplitude;
        this.generationSize = generationSize;
        initialize(factory);
    }

    /**
     * <p>
     *     Initializes lists for storing specimens and creates initial specimens.
     * </p>
     * @param factory for creating specimens.
     */
    private void initialize(ISpecimenFactory<T> factory) {
        currentGeneration = new ArrayList<>(generationSize);
        nextGeneration = new ArrayList<>(generationSize);

        for (int i = 0; i < generationSize; i++) {
            currentGeneration.add(factory.create());
            nextGeneration.add(currentGeneration.get(i));
        }
        // TODO evaluate?
    }

    /**
     * <p>
     *     Evaluates fitness for every specimen of next generation and transfers those specimens in current generation.
     * </p>
     */
    protected abstract void evaluateNextGeneration();

    @Override
    public T getBestSpecimen() {
        return currentGeneration.get(0);
    }

    @Override
    public T getWorstSpecimen() {
        return currentGeneration.get(generationSize - 1);
    }

    @Override
    public T getMedianSpecimen() {
        return currentGeneration.get(generationSize / 2);
    }

    @Override
    public int getCurrentGenerationIndex() {
        return currentGenerationIndex;
    }

    @Override
    public void setOneParent(boolean oneParent) {
        this.oneParent = oneParent;
    }

    @Override
    public boolean isOneParent() {
        return oneParent;
    }

    @Override
    public double getSmallMutationChance() {
        return smallMutationChance;
    }

    @Override
    public void setSmallMutationChance(double smallMutationChance) {
        this.smallMutationChance = smallMutationChance;
    }

    @Override
    public int getSmallMutationMagnitude() {
        return smallMutationMagnitude;
    }

    @Override
    public void setSmallMutationMagnitude(int smallMutationMagnitude) {
        this.smallMutationMagnitude = smallMutationMagnitude;
    }

    @Override
    public double getBigMutationChance() {
        return bigMutationChance;
    }

    @Override
    public void setBigMutationChance(double bigMutationChance) {
        this.bigMutationChance = bigMutationChance;
    }

    @Override
    public int getBigMutationMagnitude() {
        return bigMutationMagnitude;
    }

    @Override
    public void setBigMutationMagnitude(int bigMutationMagnitude) {
        this.bigMutationMagnitude = bigMutationMagnitude;
    }

    @Override
    public int getGenerationSize() {
        return generationSize;
    }
}
