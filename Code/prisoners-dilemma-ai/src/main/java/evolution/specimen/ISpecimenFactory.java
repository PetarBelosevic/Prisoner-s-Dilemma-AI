package evolution.specimen;

/**
 * <p>
 *     Interface for ISpecimen objects factory.
 * </p>
 * @param <T> extends ISpecimen
 */
public interface ISpecimenFactory<T extends ISpecimen<T>> {
    /**
     * @return object that is or extends type ISpecimen
     */
    T create();
}
