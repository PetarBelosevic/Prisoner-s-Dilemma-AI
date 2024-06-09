package evolution;

import evolution.specimen.ISpecimen;
import evolution.specimen.evaulator.IEvaluator;

/**
 * <p>
 *     Model for an evolution in generational genetic algorithm.
 * </p>
 * Every implementations of this interface needs to initialize its first generation of specimens after its construction.
 *
 * @param <T> type of specimen
 */
public interface IEvolution<T extends ISpecimen<T>> {
    /**
     * <p>
     *     Get the best organism in current generation.
     * </p>
     */
    T getBestSpecimen();

    /**
     * <p>
     *     Get the worst organism in current generation.
     * </p>
     */
    T getWorstSpecimen();

    /**
     * <p>
     *     Get the median organism in current generation.
     * </p>
     */
    T getMedianSpecimen();

    /**
     * <p>
     *     Generates next generation of specimens.
     * </p>
     */
    void generateNextGeneration();

    /**
     * <p>
     *     Get index of current generation.
     * </p>
     * @return current generation's index
     */
    int getCurrentGenerationIndex();

    /**
     * @return number between 0.0 and 1.0, represents a chance of a small mutation to occur
     */
    double getMutationChance();

    /**
     *<p>
     *     If given number is smaller than 0.0, method behaves like 0.0 was given.
     *     If given number is grater than 1.0, method behaves like 1.0 was given.
     *</p>
     * @param mutationChance number between 0.0 and 1.0, represents a chance of small mutation to occur
     */
    void setMutationChance(double mutationChance);

    /**
     * @return magnitude of small mutation when it occurs
     */
    double getMutationMagnitude();

    /**
     * @param mutationMagnitude magnitude of a small mutation when it occurs
     */
    void setMutationMagnitude(double mutationMagnitude);

    /**
     * @return size of one generation
     */
    int getGenerationSize();

    /**
     * @return all evaluators used by this evolution
     */
    IEvaluator<T>[] getEvaluators();

    /**
     * <p>
     *     Sets new evaluators to use by this evolution.
     * </p>
     * @param evaluators new evaluators
     */
    void setEvaluators(IEvaluator<T>... evaluators);
}
