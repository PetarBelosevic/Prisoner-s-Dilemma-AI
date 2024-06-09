package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.evaulator.IEvaluator;
import evolution.specimen.factory.ISpecimenFactory;

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

    @SafeVarargs
    public SimpleEvolution(double mutationChance, double mutationMagnitude, int generationSize, ISpecimenFactory<T> factory, IEvaluator<T>... evaluators) {
        super(mutationChance, mutationMagnitude, generationSize, factory, evaluators);
    }

    @Override
    public void generateNextGeneration() {
        if (getCurrentGenerationIndex() == -1) {
            evaluateNextGeneration();
            incrementCurrentGenerationIndex();
            return;
        }
        getNextGeneration().get(0).copyFrom(getCurrentGeneration().get(0));
        for (int i = 1; i < getGenerationSize(); i++) {
            T p1 = selectParent();
            T p2 = selectParent();
            getNextGeneration().get(i).createOffspring(p1, p2);
            visited.put(p1, false);
            visited.put(p2, false);
            getNextGeneration().get(i).mutate(getMutationChance(), getMutationMagnitude());
        }
        evaluateNextGeneration();
        incrementCurrentGenerationIndex();
    }

    @Override
    protected T selectParent() {
        double random = Math.random() * totalFitness;
        double currentFitnessSum = 0;
        for (T specimen: getCurrentGeneration()) {
            currentFitnessSum += specimen.getFitness() - getWorstSpecimen().getFitness();
            if (visited.get(specimen)) {
                continue;
            }
            if (random <= currentFitnessSum) {
                visited.put(specimen, true);
                return specimen;
            }
        }
        visited.put(getBestSpecimen(), true);
        return getBestSpecimen();
    }
}
