package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.ISpecimenFactory;
import game.IGame;
import game.PrisonersDilemmaGame;

import java.util.List;

public class SimpleEvolution extends AbstractEvolution {
    private int totalFitness = 0;
    private IGame game;

    public SimpleEvolution(double smallMutationChance, int smallMutationAmplitude, double bigMutationChance, int bigMutationAmplitude, boolean oneParent, int generationSize, ISpecimenFactory factory) {
        super(smallMutationChance, smallMutationAmplitude, bigMutationChance, bigMutationAmplitude, oneParent, generationSize, factory);
        this.game = new PrisonersDilemmaGame(50); // TODO
    }

    /**
     * <p>
     *     Evaluates fitness for every specimen of next generation.
     * </p>
     */
    private void evaluateNextGeneration() {

    }

    @Override
    public void generateNextGeneration() {
        nextGeneration.set(0, currentGeneration.get(0));
        for (int i = 1; i < generationSize; i++) {
            if (!oneParent) {
                nextGeneration.get(i).createOffspring(selectParent(), selectParent());
            }
            nextGeneration.get(i).mutate(smallMutationChance, smallMutationMagnitude, bigMutationChance, bigMutationMagnitude);
        }
        evaluateNextGeneration();

        nextGeneration.sort(null);
        totalFitness -= nextGeneration.get(generationSize - 1).getFitness();

        List<ISpecimen> temp = currentGeneration;
        currentGeneration = nextGeneration;
        nextGeneration = temp;
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
    private ISpecimen selectParent() {
        double random = Math.random() * totalFitness;
        int currentFitnessSum = 0;
        for (ISpecimen specimen: currentGeneration) {
            if (currentFitnessSum < random) {
                currentFitnessSum += specimen.getFitness();
            }
            if (currentFitnessSum >= random) {
                return specimen;
            }
        }
        return currentGeneration.get(0);
    }
}
