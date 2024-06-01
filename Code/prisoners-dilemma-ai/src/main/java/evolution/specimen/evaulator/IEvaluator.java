package evolution.specimen.evaulator;

import evolution.specimen.ISpecimen;

import java.util.List;

/**
 * <p>
 *     Interface for evaluation of population of specimens.
 * </p>
 *
 * @param <T> Any object that implements ISpecimen interface
 */
public interface IEvaluator<T extends ISpecimen<T>> {

    /**
     * <p>
     *     Method takes population of specimens, puts them in interaction and based on that contributes to their fitness.
     * </p>
     * @param population specimens to be evaluated
     * @return total calculated fitness
     */
    double evaluate(List<T> population);
}
