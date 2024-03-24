package utils;

/**
 * <p>
 *     Model for n-tuple.
 * </p>
 * @param <T>
 */
public interface INTuple<T> {
    /**
     * @return number of elements that this n-tuple stores
     */
    int getN();

    /**
     * @param index position of requested element
     * @return element at position index
     */
    T getElementAt(int index);

    /**
     * <p>
     *     Sets element at given position.
     * </p>
     * @param index where new value is being set.
     * @param value new value
     */
    void setElementAt(int index, T value);
}
