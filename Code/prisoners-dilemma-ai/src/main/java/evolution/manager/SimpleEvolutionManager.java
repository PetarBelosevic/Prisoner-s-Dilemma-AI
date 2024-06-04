package evolution.manager;

import evolution.IEvolution;
import evolution.specimen.ISpecimen;
import utils.NTuple;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *     Simple implementation of IEvolutionManager.
 * </p>
 */
public class SimpleEvolutionManager<T extends ISpecimen<T>> implements IEvolutionManager<T> {
    private final IEvolution<T> evolution;
    private final int maxGenerationLimit;
    private final double acceptableFitness;
    private final List<NTuple<Double>> generationsHistory = new LinkedList<>();
    private volatile boolean stop = false;

    public SimpleEvolutionManager(IEvolution<T> evolution, int maxGenerationLimit, double acceptableFitness) {
        this.evolution = evolution;
        this.maxGenerationLimit = maxGenerationLimit;
        this.acceptableFitness = acceptableFitness;
    }

    public SimpleEvolutionManager(IEvolution<T> evolution, int maxGenerationLimit) {
        this.evolution = evolution;
        this.maxGenerationLimit = maxGenerationLimit;
        double totalMaxFitness = 0;
        for (var evaluator: evolution.getEvaluators()) {
            totalMaxFitness += evaluator.maxPossibleScore(evolution.getGenerationSize());
        }
        this.acceptableFitness = totalMaxFitness;
    }

    @Override
    public void runEvolution() {
        while (evolution.getCurrentGenerationIndex() < maxGenerationLimit && acceptableFitness > evolution.getBestSpecimen().getFitness() && !stop) {
            evolution.generateNextGeneration();
            generationsHistory.add(new NTuple<>(
                    evolution.getBestSpecimen().getFitness(),
                    evolution.getMedianSpecimen().getFitness(),
                    evolution.getWorstSpecimen().getFitness())
            );
        }
    }

    @Override
    public List<NTuple<Double>> getGenerationsHistory() {
        return generationsHistory;
    }

    @Override
    public IEvolution<T> getEvolution() {
        return evolution;
    }

    @Override
    public synchronized void stopEvolution() {
        this.stop = true;
    }

    @Override
    public double getAcceptableFitness() {
        return acceptableFitness;
    }
}
