package org.example.implementations;

import org.example.utils.exceptions.EmptyStructureException;
import org.example.tda.StackADT;

public class StaticStackADT implements StackADT {

    private static final int MAX = 10000;

    private final int[] array;
    private int count;

    public StaticStackADT() {
        this.array = new int[MAX];
        this.count = 0;
    }

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("No se puede obtener el tope de una pila vacia");
        }
        return this.array[count - 1];
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public void add(int a) {
        this.array[count] = a;
        this.count++;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("No se puede desapilar de una pila vacia");
        }
        this.count--;
    }

}

