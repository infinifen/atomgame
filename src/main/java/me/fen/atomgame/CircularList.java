package me.fen.atomgame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CircularList<E> extends ArrayList<E> implements List<E> {

    public CircularList(int initialCapacity) {
        super(initialCapacity);
    }

    public CircularList() {
        super();
    }

    public CircularList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public E get(int index) {
        return super.get(getActualIndex(index));
    }

    @Override
    public E set(int index, E element) {
        return super.set(getActualIndex(index), element);
    }

    public int getActualIndex(int index) {
        return this.size() != 0 ? (index % this.size() + this.size()) % this.size() : 0; // works with negative numbers
    }

    @Override
    public void add(int index, E element) {
        super.add(getActualIndex(index), element);
    }

    @Override
    public E remove(int index) {
        return super.remove(getActualIndex(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return super.subList(getActualIndex(fromIndex), getActualIndex(toIndex));
    }
}
