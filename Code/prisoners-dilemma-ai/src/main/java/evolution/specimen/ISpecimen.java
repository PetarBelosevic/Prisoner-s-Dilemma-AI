package evolution.specimen;

/**
 * <p>
 *     Model for organism in evolutionary computing.
 * </p>
 */
public interface ISpecimen extends Comparable<ISpecimen> {
    /**
     * <p>
     *     Mutates organism.
     * </p>
     */
    void mutate(double smallMutationChance, int smallMutationMagnitude, double bigMutationChance, int bigMutationMagnitude);

    /**
     * <p>
     *     Modifies this organism so that it is child of two given parents
     * </p>
     * @param parent1 first parent of this organism
     * @param parent2 second parent of this organism
     */
    void createOffspring(ISpecimen parent1, ISpecimen parent2);

    /**
     * <p>
     *     Gets value of the fitness function for this organism.
     * </p>
     * @return value of the fitness function
     */
    int getFitness();

    /**
     * <p>
     *     Returns if modified since last call of getFitness method.
     * </p>
     * @return true if object was modified since last call of getFitness method, false otherwise
     */
    boolean isModified();

    @Override
    default int compareTo(ISpecimen o) {
        return getFitness() - o.getFitness();
    }
}
