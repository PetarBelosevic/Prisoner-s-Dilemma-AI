package utils;

/**
 * <p>
 *     Model for n-tuple.
 * </p>
 * @param <T>
 */
public interface INTuple<T> {
    /**
     * <p>
     *     Get number of elements that this n-tuple stores.
     * </p>
     * @return n
     */
    int getN();

    /**
     * <p>
     *      Get element at position index.
     * </p>
     * @param index
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
