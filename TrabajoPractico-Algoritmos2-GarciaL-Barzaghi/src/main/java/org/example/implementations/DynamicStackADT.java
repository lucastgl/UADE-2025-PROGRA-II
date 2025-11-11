package org.example.implementations;

import org.example.implementations.nodes.Node;
import org.example.tda.StackADT;

public class DynamicStackADT implements StackADT {

    private Node top;

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener el tope de una pila vacia");
        }
        return this.top.getValue();
    }

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    @Override
    public void add(int a) {
        this.top = new Node(a, this.top);
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede desapilar de una pila vacia");
        }
        this.top = this.top.getNext();
    }
}

