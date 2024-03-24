package utils;

public class NTuple<T> implements INTuple<T> {
    private final T[] values;
    StringBuilder builder = new StringBuilder();

    @SafeVarargs
    public NTuple(T... values) {
        this.values = values;
    }

    @Override
    public int getN() {
        return values.length;
    }

    @Override
    public T getElementAt(int index) {
        return values[index];
    }

    @Override
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
