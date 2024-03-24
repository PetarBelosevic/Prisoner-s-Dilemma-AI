package evolution.specimen;

/**
 * <p>
 *     Model for specimen in evolution.
 * </p>
 */
public interface ISpecimen<T extends ISpecimen<T>> extends Comparable<ISpecimen<T>> {
    /**
     * <p>
     *     Mutates organism.
     * </p>
     */
    void mutate(double smallMutationChance, double smallMutationMagnitude, double bigMutationChance, double bigMutationMagnitude);

    /**
     * <p>
     *     Modifies this organism so that it becomes child of two given parents.
     * </p>
     * Order of parents shouldn't make a difference in final result.
     *
     * @param parent1 first parent of this organism
     * @param parent2 second parent of this organism
     */
    void createOffspring(T parent1, T parent2);

    /**
     * @return fitness of this specimen
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

    /**
     * <p>
     *     Copies relevant values from other specimen so this one acts like the other.
     * </p>
     * Other specimen remains unmodified.
     *
     * @param other specimen which is copied into this one
     */
    void copyFrom(T other);

    /**
     * <p>
     *     Saves all relevant information in the given file.
     * </p>
     * File will be stored in resources directory.
     *
     * @param fileName name of the file where information will be saved
     */
    void saveInFile(String fileName);

    /**
     * <p>
     *     Loads all relevant information form given file.
     * </p>
     * File is expected to be stored in resources directory.
     *
     * @param fileName name of the file where relevant information is stored
     */
    void loadFromFile(String fileName);

    @Override
    default int compareTo(ISpecimen o) {
        return o.getFitness() - getFitness();
    }
}
