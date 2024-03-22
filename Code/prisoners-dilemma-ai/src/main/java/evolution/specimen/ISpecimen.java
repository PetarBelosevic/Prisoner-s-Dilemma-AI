package evolution.specimen;

/**
 * <p>
 *     Model for organism in evolutionary computing.
 * </p>
 */
public interface ISpecimen<T extends ISpecimen> extends Comparable<ISpecimen> {
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
    void createOffspring(T parent1, T parent2);

    /**
     * <p>
     *     Gets value of the fitness function for this organism.
     * </p>
     * @return value of the fitness function
     */
    int getFitness();

    /**
     * @param fitness value that is to be added to total fitness of this specimen
     */
    void addToFitness(int fitness);

    /**
     * <p>
     *     Resets fitness to initial value (typically 0).
     * </p>
     */
    void resetFitness();

    // TODO remove?
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
