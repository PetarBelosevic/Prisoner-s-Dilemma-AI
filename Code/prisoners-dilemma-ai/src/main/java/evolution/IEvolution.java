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
     *     Generates next generation of organisms.
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

    boolean isOneParent();

    double getSmallMutationChance();

    void setSmallMutationChance(double smallMutationChance);

    int getSmallMutationAmplitude();

    void setSmallMutationAmplitude(int smallMutationAmplitude);

    double getBigMutationChance();

    void setBigMutationChance(double bigMutationChance);

    int getBigMutationAmplitude();

    void setBigMutationAmplitude(int bigMutationAmplitude);
}
