package utils;

import java.util.Objects;

/**
 * <p>
 *     Simple class for storing pair of objects.
 * </p>
 * @param <T> type of first object
 * @param <D> type of second object
 */
public class Pair<T, D> {
    private T first;
    private D second;

    public Pair(T first, D second) {
        this.first = first;
        this.second = second;
    }
    public Pair() {
    }

    /**
     * @return first element of this pair.
     */
    public T getFirst() {
        return first;
    }

    /**
     * @return second element of this pair.
     */
    public D getSecond() {
        return second;
    }

    /**
     * <p>
     *     Sets new value of the first element of this pair.
     * </p>
     * @param first new value of the first element
     */
    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * <p>
     *     Sets new value of the second element of this pair.
     * </p>
     * @param second new value of the second element
     */
    public void setSecond(D second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ')';
    }
}
