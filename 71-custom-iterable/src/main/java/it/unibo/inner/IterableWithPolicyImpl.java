package it.unibo.inner;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    T t[];
    Predicate<T> F;
    List<T> lista = new LinkedList<T>();

    IterableWithPolicyImpl(T t[], Predicate<T> f) {
        F = f;
        lista = List.of(t);
    }

    IterableWithPolicyImpl(T t[]) {
        this(t, new Predicate<>() {
            @Override
            public boolean test(T t) {
                return true;
            }
        });
    }

    private class InnerClass implements Iterator<T> {

        int current = 0;

        @Override
        public boolean hasNext() {
            while (current < lista.size()) {
                T t = lista.get(current);
                if (F.test(t)) {
                    return true;
                }
                current++;
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
    public void setIterationPolicy(Predicate<T> filter) {
        F = filter;
    }

}
