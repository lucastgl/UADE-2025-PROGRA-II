package org.example.implementations;

import org.example.utils.exceptions.EmptyStructureException;
import org.example.implementations.nodes.PriorityNode;
import org.example.tda.PriorityQueueADT;

public class DynamicPriorityQueueADT implements PriorityQueueADT {

    private PriorityNode first;

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("No se puede obtener el valor de una cola vacía"); // C
        } else {
            return this.first.getValue();
        }
    }

    @Override
    public int getPriority() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("No se puede obtener la prioridad de una cola vacía");
        }
        return this.first.getPriority();
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public void add(int a, int priority) {
        if (this.isEmpty()) {
            this.first = new PriorityNode(a, priority, null);
            return;
        }

        if (this.first.getNext() == null) {
            if (priority < this.first.getPriority()) {
                this.first = new PriorityNode(a, priority, this.first);
                return;
            }
            this.first.setNext(new PriorityNode(a, priority, null));
            return;
        }

        if (priority < this.first.getPriority()) {
            this.first = new PriorityNode(a, priority, this.first);
            return;
        }

        int minPriority = getMinPriority();
        if (priority >= minPriority) {
            PriorityNode candidate = this.first;
            while (candidate.getNext() != null) {
                candidate = candidate.getNext();
            }
            candidate.setNext(new PriorityNode(a, priority, null));
            return;
        }

        PriorityNode backup = this.first;
        PriorityNode current = this.first.getNext();

        while (current.getNext() != null) {
            if (current.getPriority() > priority) {
                break;
            }
            backup = current;
            current = current.getNext();
        }

        backup.setNext(new PriorityNode(a, priority, current));
    }

    private int getMinPriority() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("No se puede obtener la prioridad de una cola vacía");
        }
        int candidate = Integer.MIN_VALUE;
        PriorityNode aux = this.first;

        while (aux != null) {
            if (aux.getPriority() > candidate) {
                candidate = aux.getPriority();
            }
            aux = aux.getNext();
        }
        return candidate;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new EmptyStructureException("No se puede desacolar una cola vacía");
        }
        this.first = this.first.getNext();
    }
}

