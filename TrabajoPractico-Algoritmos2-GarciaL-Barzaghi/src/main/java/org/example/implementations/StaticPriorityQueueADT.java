package org.example.implementations;

import org.example.tda.PriorityQueueADT;

public class StaticPriorityQueueADT implements PriorityQueueADT {

    private static final int MAX = 10000;

    private final int[] values;
    private final int[] priorities;
    private int count;

    public StaticPriorityQueueADT() {
        this.values = new int[MAX];
        this.priorities = new int[MAX];
        this.count = 0;
    }

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener el valor de una cola vacía");
        }
        return this.values[0];
    }

    @Override
    public int getPriority() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede obtener la prioridad de una cola vacía");
        }
        return this.priorities[0];
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public void add(int a, int priority) {
        if (this.count == MAX) { // TODO Esto debería estar en todas las estructuras
            throw new RuntimeException("No se tiene capacidad para almacenar un nuevo elemento");
        }
        if (this.isEmpty()) {
            this.values[0] = a;
            this.priorities[0] = priority;
            this.count = 1;
            return;
        }

        if (this.priorities[this.count - 1] <= priority) {
            this.values[count] = a;
            this.priorities[count] = priority;
            this.count++;
            return;
        }

        if (this.priorities[0] > priority) {
            for (int i = this.count - 1; i > 0; i--) {
                this.values[i + 1] = this.values[i];
                this.priorities[i + 1] = this.priorities[i];
            }
            this.values[0] = a;
            this.priorities[0] = priority;
            this.count++;
            return;
        }

        int index = findIndex(priority);

        if (index != -1) {
            int candidate = index;
            while (this.priorities[candidate] == priority) {
                candidate++;
            }
            for (int i = this.count - 1; i >= candidate; i--) {
                this.values[i + 1] = this.values[i];
                this.priorities[i + 1] = this.priorities[i];
            }
            this.values[candidate] = a;
            this.priorities[candidate] = priority;
            this.count++;
            return;
        }

        int candidate = 0;
        while (this.priorities[candidate] <= priority) {
            candidate++;
        }

        for (int i = this.count - 1; i >= candidate; i--) {
            this.values[i + 1] = this.values[i];
            this.priorities[i + 1] = this.priorities[i];
        }
        this.values[candidate] = a;
        this.priorities[candidate] = priority;
    }

    private int findIndex(int priority) {
        for (int i = 0; i < this.count; i++) {
            if (this.priorities[i] == priority) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede desacolar una cola vacía");
        }

        for (int i = 0; i < this.count - 1; i++) {
            this.values[i] = this.values[i + 1];
            this.priorities[i] = this.priorities[i + 1];
        }

        this.count--;
    }
}

