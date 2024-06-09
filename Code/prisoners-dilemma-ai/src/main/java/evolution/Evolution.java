package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.evaulator.IEvaluator;

/**
 * <p>
 *     Basic decorator for IEvolution.
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
    public double getMutationChance() {
        return evolution.getMutationChance();
    }

    @Override
    public void setMutationChance(double mutationChance) {
        evolution.setMutationChance(mutationChance);
    }

    @Override
    public double getMutationMagnitude() {
        return evolution.getMutationMagnitude();
    }

    @Override
    public void setMutationMagnitude(double mutationMagnitude) {
        evolution.setMutationMagnitude(mutationMagnitude);
    }

    @Override
    public int getGenerationSize() {
        return evolution.getGenerationSize();
    }

    @Override
    public IEvaluator<T>[] getEvaluators() {
        return evolution.getEvaluators();
    }

    @SafeVarargs
    @Override
    public final void setEvaluators(IEvaluator<T>... evaluators) {
        evolution.setEvaluators(evaluators);
    }
}
