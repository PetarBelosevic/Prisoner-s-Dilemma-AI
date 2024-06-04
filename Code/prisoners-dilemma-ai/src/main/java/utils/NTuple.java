package utils;

/**
 * <p>
 *     Basic tuple implementation.
 * </p>
 * @param <T> type stored in n-tuple
 */
public class NTuple<T> {
    private final T[] values;
    StringBuilder builder = new StringBuilder();

    @SafeVarargs
    public NTuple(T... values) {
        this.values = values;
    }

    /**
     * @return number of elements stored in this tuple
     */
    public int getN() {
        return values.length;
    }

    /**
     * @param index index of requested element in tuple
     * @return element of this tuple at given index
     */
    public T getElementAt(int index) {
        return values[index];
    }

    /**
     * <p>
     *     Sets value of element at given index.
     * </p>
     * @param index index of element whose value will be changed
     * @param value new value of element
     */
    public void setElementAt(int index, T value) {
        values[index] = value;
    }

    @Override
    public String toString() {
        builder.setLength(0);
        builder.append("( ");
        for (T element: values) {
            builder.append(element).append(" ");
        }
        builder.append(")");
        return builder.toString();
    }
}
