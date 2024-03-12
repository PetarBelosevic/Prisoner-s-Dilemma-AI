package evolution;

import utils.INTuple;
import utils.NTuple;

import java.util.LinkedList;
import java.util.List;

public class SimpleEvolutionManager implements IEvolutionManager {
    private IEvolution evolution;
    private int maxGenerationLimit;
    private int acceptableFitness;
    private List<INTuple<Integer>> generationsHistory = new LinkedList<>();

    public SimpleEvolutionManager(IEvolution evolution, int maxGenerationLimit, int acceptableFitness) {
        this.evolution = evolution;
        this.maxGenerationLimit = maxGenerationLimit;
        this.acceptableFitness = acceptableFitness;
        generationsHistory.add(new NTuple<>(evolution.getBestSpecimen().getFitness(),
                                            evolution.getMedianSpecimen().getFitness(),
                                            evolution.getWorstSpecimen().getFitness()));
    }

    @Override
    public void runEvolution() {
        int i = 0;
        while (i++ < maxGenerationLimit && acceptableFitness > evolution.getBestSpecimen().getFitness()) {
            evolution.generateNextGeneration();
            generationsHistory.add(new NTuple<>(evolution.getBestSpecimen().getFitness(),
                    evolution.getMedianSpecimen().getFitness(),
                    evolution.getWorstSpecimen().getFitness()));
        }
    }

    @Override
    public List<INTuple<Integer>> getGenerationsHistory() {
        return generationsHistory;
    }
}
