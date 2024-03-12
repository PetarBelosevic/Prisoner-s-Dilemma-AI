package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NTuple<T> implements INTuple<T> {
    private final List<T> elements;

    public NTuple(T... values) {
        this.elements = new ArrayList<>();
        elements.addAll(Arrays.stream(values).toList());
    }

    @Override
    public int getN() {
        return elements.size();
    }

    @Override
    public T getElementAt(int index) {
        return elements.get(index);
    }

    @Override
    public void setElementAt(int index, T value) {
        elements.set(index, value);
    }
}
