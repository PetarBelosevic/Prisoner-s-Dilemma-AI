package evolution.manager;

import evolution.IEvolution;
import evolution.specimen.ISpecimen;
import utils.INTuple;
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
    private final int acceptableFitness;
    private final List<INTuple<Integer>> generationsHistory = new LinkedList<>();
    private volatile boolean stop = false;

    public SimpleEvolutionManager(IEvolution<T> evolution, int maxGenerationLimit, int acceptableFitness) {
        this.evolution = evolution;
        this.maxGenerationLimit = maxGenerationLimit;
        this.acceptableFitness = acceptableFitness;
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
    public List<INTuple<Integer>> getGenerationsHistory() {
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
}
