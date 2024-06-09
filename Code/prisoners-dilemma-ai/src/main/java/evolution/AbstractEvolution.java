package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.evaulator.IEvaluator;
import evolution.specimen.factory.ISpecimenFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     Implements basic functionalities of IEvolution interface.
 * </p>
 * @param <T> extends ISpecimen
 */
public abstract class AbstractEvolution<T extends ISpecimen<T>> implements IEvolution<T> {
    private double mutationChance;
    private double mutationMagnitude;
    private int currentGenerationIndex = -1;
    private final int generationSize;
    private List<T> currentGeneration;
    private List<T> nextGeneration;
    protected final Map<T, Boolean> visited = new HashMap<>();
    private IEvaluator<T>[] evaluators;
    protected double totalFitness = 0;

    @SafeVarargs
    protected AbstractEvolution(double mutationChance, double mutationMagnitude, int generationSize, ISpecimenFactory<T> factory, IEvaluator<T>... evaluators) {
        this.mutationChance = mutationChance;
        this.mutationMagnitude = mutationMagnitude;
        this.generationSize = generationSize;
        this.evaluators = evaluators;
        initialize(factory);
    }

    /**
     * <p>
     *     Initializes lists for storing specimens and creates initial specimens.
     * </p>
     * Also sets visited value to false for all given specimens.
     *
     * @param factory for creating specimens.
     */
    private void initialize(ISpecimenFactory<T> factory) {
        currentGeneration = new ArrayList<>(generationSize);
        nextGeneration = new ArrayList<>(generationSize);

        for (int i = 0; i < generationSize; i++) {
            currentGeneration.add(factory.create());
            nextGeneration.add(factory.create());
            visited.put(currentGeneration.get(i), false);
            visited.put(nextGeneration.get(i), false);
        }
    }

    /**
     * <p>
     *     Evaluates fitness for every specimen of next generation and transfers those specimens in current generation.
     * </p>
     */
    protected void evaluateNextGeneration() {
        totalFitness = 0;
        for (int i = 0; i < getGenerationSize(); i++) {
            getNextGeneration().get(i).resetFitness();
        }
        for (var evaluator: getEvaluators()) {
            totalFitness += evaluator.evaluate(getNextGeneration());
        }
        getNextGeneration().sort(null);
        totalFitness -= getNextGeneration().get(getGenerationSize() - 1).getFitness() * getGenerationSize();
        swapGenerations();
    }

    /**
     * <p>
     *     Parent is selected based on its fitness.
     * </p>
     * Greater fitness means greater chance of being selected.
     *
     * @return specimen selected for reproduction
     */
    protected abstract T selectParent();

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
    public double getMutationChance() {
        return mutationChance;
    }

    @Override
    public void setMutationChance(double mutationChance) {
        this.mutationChance = mutationChance;
    }

    @Override
    public double getMutationMagnitude() {
        return mutationMagnitude;
    }

    @Override
    public void setMutationMagnitude(double mutationMagnitude) {
        this.mutationMagnitude = mutationMagnitude;
    }

    @Override
    public int getGenerationSize() {
        return generationSize;
    }

    @Override
    public IEvaluator<T>[] getEvaluators() {
        return evaluators;
    }

    @SafeVarargs
    @Override
    public final void setEvaluators(IEvaluator<T>... evaluators) {
        this.evaluators = evaluators;
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
