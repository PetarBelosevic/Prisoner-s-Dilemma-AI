package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.ISpecimenFactory;
import evolution.specimen.evaulator.IEvaluator;

/**
 * <p>
 *     Simple implementation of IEvolution interface.
 * </p>
 * This implementation always preserves best specimen for the next generation.
 * Parents are selected based on their fitness values - higher fitness means higher chance of being selected.
 *
 * @param <T> extends ISpecimen
 */
public class SimpleEvolution<T extends ISpecimen<T>> extends AbstractEvolution<T> {
    private int totalFitness = 0;
    private final IEvaluator<T> evaluator;

    public SimpleEvolution(double smallMutationChance, int smallMutationMagnitude, double bigMutationChance, int bigMutationMagnitude, int generationSize, ISpecimenFactory<T> factory, IEvaluator<T> evaluator) {
        super(smallMutationChance, smallMutationMagnitude, bigMutationChance, bigMutationMagnitude, generationSize, factory);
        this.evaluator = evaluator;
    }

    @Override
    protected void evaluateNextGeneration() {
        totalFitness = 0;
        for (int i = 0; i < getGenerationSize(); i++) {
            getNextGeneration().get(i).resetFitness();
        }

        for (int i = 0; i < getGenerationSize(); i++) {
            for (int j = i; j < getGenerationSize(); j++) {
                totalFitness += evaluator.evaluate(getNextGeneration().get(i), getNextGeneration().get(j));
            }
        }
        getNextGeneration().sort(null);
        totalFitness -= getNextGeneration().get(getGenerationSize() - 1).getFitness() * getGenerationSize();

        swapGenerations();
    }

    @Override
    public void generateNextGeneration() {
        getNextGeneration().set(0, getCurrentGeneration().get(0));
        for (int i = 1; i < getGenerationSize(); i++) {
            if (!isOneParent()) {
                getNextGeneration().get(i).createOffspring(selectParent(), selectParent());
            }
            else {
                getNextGeneration().get(i).copyFrom(selectParent());
            }
            getNextGeneration().get(i).mutate(getSmallMutationChance(), getSmallMutationMagnitude(), getBigMutationChance(), getBigMutationMagnitude());
        }
        evaluateNextGeneration();
        incrementCurrentGenerationIndex();
    }

    /**
     * <p>
     *     Parent is selected based on its fitness.
     * </p>
     * Greater fitness means greater chance of being selected.
     *
     * @return specimen selected for reproduction
     */
    private T selectParent() {
        double random = Math.random() * totalFitness;
        int currentFitnessSum = 0;
        for (T specimen: getCurrentGeneration()) {
            if (currentFitnessSum < random) {
                currentFitnessSum += specimen.getFitness() - getWorstSpecimen().getFitness();
            }
            if (currentFitnessSum >= random) {
                return specimen;
            }
        }
        return getBestSpecimen();
    }
}
