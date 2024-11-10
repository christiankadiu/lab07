package it.unibo.inner;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    T t[];
    List<T> lista = new LinkedList<T>();

    IterableWithPolicyImpl(T t[]) {
        this.t = t;
        lista = List.of(t);
    }

    private class InnerClass implements Iterator<T> {

        int current = 0;

        @Override
        public boolean hasNext() {
            if (current < lista.size()) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return lista.get(current++);
            }
            throw new NoSuchElementException();
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new InnerClass();
    }

    @Override
    public void setIterationPolicy(Predicate filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

}
