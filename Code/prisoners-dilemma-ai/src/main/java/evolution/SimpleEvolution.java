package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.ISpecimenFactory;
import evolution.specimen.evaulator.IEvaluator;

import java.util.List;

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
        for (int i = 0; i < generationSize; i++) {
            nextGeneration.get(i).resetFitness();
        }
        for (int i = 0; i < generationSize; i++) {
            for (int j = i; j < generationSize; j++) {
                totalFitness += evaluator.evaluate(nextGeneration.get(i), nextGeneration.get(j));
            }
        }
        nextGeneration.sort(null);
        totalFitness -= nextGeneration.get(generationSize - 1).getFitness() * generationSize;

        List<T> temp = currentGeneration;
        currentGeneration = nextGeneration;
        nextGeneration = temp;
    }

    @Override
    public void generateNextGeneration() {
        nextGeneration.set(0, currentGeneration.get(0));
        for (int i = 1; i < generationSize; i++) {
            if (!oneParent) {
                nextGeneration.get(i).createOffspring(selectParent(), selectParent());
            }
            else {
                nextGeneration.get(i).copyFrom(selectParent());
            }
            nextGeneration.get(i).mutate(smallMutationChance, smallMutationMagnitude, bigMutationChance, bigMutationMagnitude);
        }
        evaluateNextGeneration();
        currentGenerationIndex++;
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
        for (T specimen: currentGeneration) {
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
