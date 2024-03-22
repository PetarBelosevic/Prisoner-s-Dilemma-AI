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
public class SimpleEvolutionManager implements IEvolutionManager {
    private final IEvolution<? extends ISpecimen> evolution;
    private final int maxGenerationLimit;
    private final int acceptableFitness;
    private final List<INTuple<Integer>> generationsHistory = new LinkedList<>();

    public SimpleEvolutionManager(IEvolution<? extends ISpecimen> evolution, int maxGenerationLimit, int acceptableFitness) {
        this.evolution = evolution;
        this.maxGenerationLimit = maxGenerationLimit;
        this.acceptableFitness = acceptableFitness;
        generationsHistory.add(new NTuple<>(
                evolution.getBestSpecimen().getFitness(),
                evolution.getMedianSpecimen().getFitness(),
                evolution.getWorstSpecimen().getFitness())
        );
    }

    @Override
    public void runEvolution() {
        int i = 0;
        while (i++ < maxGenerationLimit && acceptableFitness > evolution.getBestSpecimen().getFitness()) {
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
}
