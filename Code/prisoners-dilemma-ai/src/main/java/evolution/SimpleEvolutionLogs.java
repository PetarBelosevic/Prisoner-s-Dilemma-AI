package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.ISpecimenFactory;
import evolution.specimen.evaulator.IEvaluator;

/**
 * <p>
 *     Simple evolution that prints logs during it's process and saves certain specimens.
 * </p>
 * Logs contain fitness values of the best, median and the worst specimen of every {@value STEP} generation.
 * Every specimen whose fitness is printed is saved in text file.
 *
 * @param <T> type of specimen
 */
public class SimpleEvolutionLogs<T extends ISpecimen<T>> extends SimpleEvolution<T> {
    private static final String FORMAT = "Generation %d:\n\tBest score: %d\n\tMedian score: %d\n\tWorst score: %d\n------------------------------\n";
    private static final int STEP = 10;

    public SimpleEvolutionLogs(double smallMutationChance, int smallMutationMagnitude, double bigMutationChance, int bigMutationMagnitude, int generationSize, ISpecimenFactory<T> factory, IEvaluator<T> evaluator) {
        super(smallMutationChance, smallMutationMagnitude, bigMutationChance, bigMutationMagnitude, generationSize, factory, evaluator);
    }

    @Override
    public void generateNextGeneration() {
        super.generateNextGeneration();
        if ((currentGenerationIndex) % STEP == 0) {
            System.out.printf(FORMAT, currentGenerationIndex, getBestSpecimen().getFitness(), getMedianSpecimen().getFitness(), getWorstSpecimen().getFitness());
            getBestSpecimen().saveInFile("bestInGen" + currentGenerationIndex + ".txt");
            getMedianSpecimen().saveInFile("medianInGen" + currentGenerationIndex + ".txt");
            getWorstSpecimen().saveInFile("worstInGen" + currentGenerationIndex + ".txt");
        }
    }
}
