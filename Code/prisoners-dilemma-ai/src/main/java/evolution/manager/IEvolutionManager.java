package evolution.manager;

import evolution.IEvolution;
import evolution.specimen.ISpecimen;
import utils.INTuple;

import java.util.List;

/**
 * <p>
 *     Interface for managing evolution.
 * </p>
 * Manager runs evolution and saves scores for best, median and worst specimen of every generation.
 */
public interface IEvolutionManager<T extends ISpecimen<T>> {
    /**
     * <p>
     *     Starts evolutionary process for creating best organism.
     * </p>
     */
    void runEvolution();

    /**
     * <p>
     *      Returns data about fitness of the best, median and the worst performing specimens of each generation.
     * </p>
     * @return fitness history of the best, median and the worst specimens
     */
    List<INTuple<Integer>> getGenerationsHistory();

    /**
     * @return evolution used in this manager
     */
    IEvolution<T> getEvolution();

    /**
     * <p>
     *     Sends signal to stop evolution.
     * </p>
     */
    void stopEvolution();
}
