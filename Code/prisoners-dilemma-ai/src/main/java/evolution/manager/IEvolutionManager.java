package evolution.manager;

import utils.INTuple;

import java.util.List;

/**
 * <p>
 *     Interface for managing evolution.
 * </p>
 * Manager runs evolution and saves scores for best, median and worst specimen of every generation.
 */
public interface IEvolutionManager {
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
}
