package evolution.specimen.evaulator;

import evolution.specimen.ISpecimen;

// TODO problem sa parametrizacijom
/**
 * <p>
 *     Interface for evaluation of two specimens.
 * </p>
 * Interface is used when specimens have to be evaluated based on their interaction with others.
 *
 * @param <T> Any object that implements ISpecimen interface
 */
public interface IEvaluator<T extends ISpecimen<T>> {
    /**
     * <p>
     *     Method takes two specimens, puts them in interaction and based on that contributes to their fitness.
     * </p>
     * Order of parameters doesn't affect behaviour of this method.
     *
     * @param specimen1 first specimen in interaction
     * @param specimen2 second specimen in interaction
     * @return combined value of fitness added to both of specimens
     */
    int evaluate(T specimen1, T specimen2);
}
