package evolution.specimen;

import java.nio.file.Path;

/**
 * <p>
 *     Model for specimen in evolution.
 * </p>
 * @param <T> concrete type that implements this interface
 */
public interface ISpecimen<T extends ISpecimen<T>> extends Comparable<ISpecimen<T>> {
    /**
     *  <p>
     *     Mutates properties of this object.
     * </p>
     * @param mutationChance chance of mutation to happen
     * @param mutationMagnitude magnitude of mutation
     */
    void mutate(double mutationChance, double mutationMagnitude);

    /**
     * <p>
     *     Modifies this object so that it inherits properties from two given parents.
     * </p>
     * Order of parents shouldn't make a difference in final result.
     *
     * @param parent1 first parent
     * @param parent2 second parent
     */
    void createOffspring(T parent1, T parent2);

    /**
     * <p>
     *     Copies relevant properties from other object so this one acts like the other.
     * </p>
     * Other object remains unmodified.
     *
     * @param other object which is copied into this one
     */
    void copyFrom(T other);

    /**
     * <p>
     *     Saves all relevant properties of this object in the given file.
     * </p>
     *
     * @param filePath full path of the file where information will be saved
     */
    void saveInFile(Path filePath);

    /**
     * <p>
     *     Loads all relevant properties for this object form given file.
     * </p>
     *
     * @param filePath full path of the file where relevant information is stored
     */
    void loadFromFile(Path filePath);

    /**
     * @return fitness of this specimen
     */
    double getFitness();

    /**
     * @param fitness value that is to be added to total fitness of this specimen
     */
    void addToFitness(double fitness);

    /**
     * <p>
     *     Resets fitness to initial value (typically 0).
     * </p>
     */
    void resetFitness();

    @Override
    default int compareTo(ISpecimen o) {
        double d = o.getFitness() - getFitness();
        if (d < 0) {
            return -1;
        }
        else if (d > 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
