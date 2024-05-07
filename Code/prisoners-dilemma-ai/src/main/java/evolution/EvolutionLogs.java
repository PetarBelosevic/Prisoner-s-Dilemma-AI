package evolution;

import evolution.specimen.ISpecimen;

/**
 * <p>
 *     Simple evolution decorator that prints logs during it's process and saves certain specimens.
 * </p>
 * Logs contain fitness values of the best, median and the worst specimen of every step generation.
 * Every specimen whose fitness is printed is saved in text file.
 *
 * @param <T> type of specimen
 */
public class EvolutionLogs<T extends ISpecimen<T>> extends Evolution<T> {
    private static final String FORMAT = "Generation %d:\n\tBest score: %d\n\tMedian score: %d\n\tWorst score: %d\n------------------------------\n";
    private final int step;

    public EvolutionLogs(IEvolution<T> evolution, int step) {
        super(evolution);
        this.step = step;
    }

    public EvolutionLogs(IEvolution<T> evolution) {
        this(evolution, 10);
    }

    @Override
    public void generateNextGeneration() {
        evolution.generateNextGeneration();
        if ((getCurrentGenerationIndex()) % step == 0) {
            System.out.printf(FORMAT, getCurrentGenerationIndex(), getBestSpecimen().getFitness(), getMedianSpecimen().getFitness(), getWorstSpecimen().getFitness());
            getBestSpecimen().saveInFile("bestInGen" + getCurrentGenerationIndex() + ".txt");
            getMedianSpecimen().saveInFile("medianInGen" + getCurrentGenerationIndex() + ".txt");
            getWorstSpecimen().saveInFile("worstInGen" + getCurrentGenerationIndex() + ".txt");
        }
    }
}
