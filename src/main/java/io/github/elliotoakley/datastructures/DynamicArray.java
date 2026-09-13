package io.github.elliotoakley.datastructures;

import java.util.Objects;

public class DynamicArray<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private Object[] array;
    private int size;

    public DynamicArray() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        array = new Object[initialCapacity];
        size = 0;
    }

    public void add(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    public T set(int index, T value) {
        checkIndex(index);
        Object temp = array[index];
        array[index] = value;
        return (T) temp;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    public T remove(int index) {
        checkIndex(index);
        Object value = array[index];
        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        array[size - 1] = null;
        size--;
        return (T) value;
    }

    public boolean remove(T value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    private void ensureCapacity() {
        if (array.length == size) {
            int newCapacity = Math.max(array.length + 1, (int) (array.length * GROWTH_FACTOR));
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
