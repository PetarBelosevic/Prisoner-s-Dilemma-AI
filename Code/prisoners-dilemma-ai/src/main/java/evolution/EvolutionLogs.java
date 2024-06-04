package evolution;

import evolution.specimen.ISpecimen;
import utils.Constants;

import java.nio.file.Path;

/**
 * <p>
 *     Simple evolution decorator that prints logs during it's process and saves certain specimens.
 * </p>
 * Logs contain fitness values of the best, median and the worst specimen of every n-th generation.
 * Every specimen whose fitness is printed is saved in text file.
 *
 * @param <T> type of specimen
 */
public class EvolutionLogs<T extends ISpecimen<T>> extends Evolution<T> {
    private static final String FORMAT = "Generation %d:\n\tBest score: %f\n\tMedian score: %f\n\tWorst score: %f\n------------------------------\n";
    private final int step;
    private final Path storageDirectory;

    public EvolutionLogs(IEvolution<T> evolution, int step, Path storageDirectory) {
        super(evolution);
        this.step = step;
        this.storageDirectory = storageDirectory;
    }

    public EvolutionLogs(IEvolution<T> evolution) {
        this(evolution, 10, Constants.DEFAULT_STORAGE);
    }

    @Override
    public void generateNextGeneration() {
        evolution.generateNextGeneration();
        if ((getCurrentGenerationIndex()) % step == 0) {
            System.out.printf(FORMAT, getCurrentGenerationIndex(), getBestSpecimen().getFitness(), getMedianSpecimen().getFitness(), getWorstSpecimen().getFitness());
            getBestSpecimen().saveInFile(storageDirectory.resolve("bestInGen" + getCurrentGenerationIndex() + ".txt"));
            getMedianSpecimen().saveInFile(storageDirectory.resolve("medianInGen" + getCurrentGenerationIndex() + ".txt"));
            getWorstSpecimen().saveInFile(storageDirectory.resolve("worstInGen" + getCurrentGenerationIndex() + ".txt"));
        }
    }
}
