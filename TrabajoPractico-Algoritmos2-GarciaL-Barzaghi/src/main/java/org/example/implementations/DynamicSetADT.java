package org.example.implementations;

import org.example.utils.exceptions.EmptyStructureException;
import org.example.utils.exceptions.InvalidIndexException;
import org.example.tda.SetADT;
import org.example.implementations.nodes.Node;

import java.util.Random;

public class DynamicSetADT implements SetADT {

    private final Random random;
    private Node node;
    private int count;

    public DynamicSetADT() {
        this.random = new Random();
    }

    @Override
    public boolean exist(int value) {
        Node aux = this.node;
        while (aux != null) {
            if (aux.getValue() == value) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }
    @Override
    public void add(int a) {
        if (this.isEmpty()) {
            this.node = new Node(a, null);
            this.count++;
            return;
        }

        Node aux = this.node;
        while (aux != null) {
            if (aux.getValue() == a) {
                return;
            }
            aux = aux.getNext();
        }

        this.node = new Node(a, this.node);
        this.count++;
    }

    @Override
    public void remove(int a) {
        if (this.isEmpty()) {
            return;
        }

        if (this.node.getNext() == null) {
            if (this.node.getValue() == a) {
                this.node = null;
                this.count--;
            }
            return;
        }

        if (this.node.getValue() == a) {
            this.node = this.node.getNext();
            this.count--;
            return;
        }

        Node backup = this.node;
        Node aux = this.node.getNext();
        while (aux != null) {
            if (aux.getValue() == a) {
                backup.setNext(aux.getNext());
                this.count--;
                return;
            }
            backup = aux;
            aux = aux.getNext();
        }
    }

    @Override
    public boolean isEmpty() {
        return this.node == null;
    }

    @Override
    public int choose() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("No se puede elegir un elemento de un conjunto vacío");
        }
        if (this.node.getNext() == null) {
            return this.node.getValue();
        }
        int randomIndex = random.nextInt(count);

        int i = 0;
        Node aux = this.node;
        while (i < count) {
            if (i == randomIndex) {
                return aux.getValue();
            }
            i++;
            aux = aux.getNext();
        }

        throw new InvalidIndexException("No se pudo encontrar el nodo correspondiente al índice aleatorio.");
    }
}

