package evolution;

import evolution.specimen.ISpecimen;

/**
 * <p>
 *     Model for an evolutionary process in evolutionary computing.
 * </p>
 * Every implementations of this interface needs to initialize its first generation of specimens after its construction.
 */
public interface IEvolution {
    /**
     * <p>
     *     Get the best organism in current generation.
     * </p>
     */
    ISpecimen getBestSpecimen();

    /**
     * <p>
     *     Get the worst organism in current generation.
     * </p>
     */
    ISpecimen getWorstSpecimen();

    /**
     * <p>
     *     Get the median organism in current generation.
     * </p>
     */
    ISpecimen getMedianSpecimen();

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
     * <p>
     *     Sets if offsprings will be produced from two parents or one parent.
     * </p>
     * @param oneParent true of one-parent offspring is desired, false if two-parent offspring is desired
     */
    void setOneParent(boolean oneParent);

    /**
     * @return true if offsprings have only one parent, false otherwise
     */
    boolean isOneParent();

    /**
     * @return number between 0.0 and 1.0, represents a chance of a small mutation to occur
     */
    double getSmallMutationChance();

    /**
     *<p>
     *     If given number is smaller than 0.0, method behaves like 0.0 was given.
     *     If given number is grater than 1.0, method behaves like 1.0 was given.
     *</p>
     * @param smallMutationChance number between 0.0 and 1.0, represents a chance of small mutation to occur
     */
    void setSmallMutationChance(double smallMutationChance);

    /**
     * @return magnitude of small mutation when it occurs
     */
    int getSmallMutationMagnitude();

    /**
     * @param smallMutationMagnitude magnitude of a small mutation when it occurs
     */
    void setSmallMutationMagnitude(int smallMutationMagnitude);

    /**
     * @return number between 0.0 and 1.0, represents a chance of a big mutation to occur
     */
    double getBigMutationChance();

    /**
     * <p>
     *     If given number is smaller than 0.0, method behaves like 0.0 was given.
     *     If given number is grater than 1.0, method behaves like 1.0 was given.
     * </p>
     * @param bigMutationChance number between 0.0 and 1.0, represents a chance of big mutation to occur
     */
    void setBigMutationChance(double bigMutationChance);

    /**
     * @return magnitude of small mutation when it occurs
     */
    int getBigMutationMagnitude();

    /**
     * @param bigMutationMagnitude magnitude of a big mutation when it occurs
     */
    void setBigMutationMagnitude(int bigMutationMagnitude);

    /**
     * @return size of one generation
     */
    int getGenerationSize();
}
