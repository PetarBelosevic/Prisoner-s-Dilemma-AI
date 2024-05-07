package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.factory.ISpecimenFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Implements basic functionalities of IEvolution interface.
 * </p>
 * @param <T> extends ISpecimen
 */
public abstract class AbstractEvolution<T extends ISpecimen<T>> implements IEvolution<T> {
    private double smallMutationChance;
    private double smallMutationMagnitude;
    private double bigMutationChance;
    private double bigMutationMagnitude;
    private boolean oneParent = false;
    private int currentGenerationIndex = 0;
    private final int generationSize;
    private List<T> currentGeneration;
    private List<T> nextGeneration;

    protected AbstractEvolution(double smallMutationChance, int smallMutationMagnitude, double bigMutationChance, int bigMutationMagnitude, int generationSize, ISpecimenFactory<T> factory) {
        this.smallMutationChance = smallMutationChance;
        this.smallMutationMagnitude = smallMutationMagnitude;
        this.bigMutationChance = bigMutationChance;
        this.bigMutationMagnitude = bigMutationMagnitude;
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
            nextGeneration.add(factory.create());
        }
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
    public double getSmallMutationMagnitude() {
        return smallMutationMagnitude;
    }

    @Override
    public void setSmallMutationMagnitude(double smallMutationMagnitude) {
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
    public double getBigMutationMagnitude() {
        return bigMutationMagnitude;
    }

    @Override
    public void setBigMutationMagnitude(double bigMutationMagnitude) {
        this.bigMutationMagnitude = bigMutationMagnitude;
    }

    @Override
    public int getGenerationSize() {
        return generationSize;
    }

    /**
     * @return current generation of specimens
     */
    protected List<T> getCurrentGeneration() {
        return currentGeneration;
    }

    /**
     * @return next generation of specimens (this is temporary list of specimens)
     */
    protected List<T> getNextGeneration() {
        return nextGeneration;
    }

    /**
     * <p>
     *     Swaps current and next generation.
     * </p>
     */
    protected void swapGenerations() {
        List<T> temp = getCurrentGeneration();
        currentGeneration = nextGeneration;
        nextGeneration = temp;
    }

    /**
     * <p>
     *     Increments current generation index by 1.
     * </p>
     */
    protected void incrementCurrentGenerationIndex() {
        currentGenerationIndex++;
    }
}
