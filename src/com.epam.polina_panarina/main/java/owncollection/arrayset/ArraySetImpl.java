package owncollection.arrayset;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;


public class ArraySetImpl<T> implements ArraySet<T> {

    private static final int INIT_SIZE = 16;
    public T[] values;
    private int pointer = 0;

    public ArraySetImpl() {
        values = (T[]) new Object[0];
    }

    public boolean add(T t) {
        boolean indicator = false;
        if (pointer == 0 || pointer == values.length) {
            resize(pointer + 1);
        }
        if (values[0] != null && pointer > 0) {
            for (int i = 0; i < pointer; i++) {
                if (values[i].equals(t)) {
                    indicator = true;
                    values[i] = t;
                    resize(pointer);
                    return false;
                }
            }
        }
        if (!indicator) {
            values[pointer] = t;
            pointer++;
            return true;
        }
        return false;
    }

    private void resize(int newLength) {
        try {
            T[] newArray = (T[]) new Object[newLength];
            System.arraycopy(values, 0, newArray, 0, pointer);
            values = newArray;
        } catch (ClassCastException ex) {
            ex.getMessage();
        }
    }


    public T delete(int index) {
        if (index >= pointer) {
            throw new ConcurrentModificationException("Index: " + index + ", Size " + values.length);
        }
        T oldValue = values[index];
        int value = pointer - index - 1;
        if (value > 0) {
            System.arraycopy(values, index + 1, values, index, value);
        }
        values[--pointer] = null;
        return oldValue;
    }

    public T delete(T obj) {
        int index = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(obj)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        if (index >= pointer) {
            throw new ConcurrentModificationException("Index: " + index + ", Size " + values.length);
        }
        T oldValue = values[index];
        int value = pointer - index - 1;
        if (value > 0) {
            System.arraycopy(values, index + 1, values, index, value);
        }
        values[--pointer] = null;
        return oldValue;
    }

    public T get(int index) {
        return values[index];
    }

    public int size() {
        return pointer;
    }

    public void update(int index, T t) {
        values[index] = t;
    }

    public Iterator<T> iterator() {
        return new ArrayIterator<T>(values);
    }

    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        final T[] elementData = (T[]) values;
        final int size = values.length;
        for (int i = 0; i < size; i++) {
            action.accept(elementData[i]);
        }
    }


    private class ArrayIterator<T> implements Iterator<T> {
        private int index = 0;
        private int modCount = 0;
        private T[] values;

        ArrayIterator(T[] values) {
            this.values = values;
        }

        public boolean hasNext() {
            return index != values.length;
        }

        public T next() {
            int i = index;
            if (i >= values.length) {
                throw new NoSuchElementException();
            }
            T[] elements = values;
            if (i > elements.length) {
                throw new ConcurrentModificationException();
            }
            index = i + 1;
            return (T) elements[i];
        }


        public void remove() {
            if (index < 0)
                throw new IllegalStateException();
            try {
                ArraySetImpl.this.delete(0);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
