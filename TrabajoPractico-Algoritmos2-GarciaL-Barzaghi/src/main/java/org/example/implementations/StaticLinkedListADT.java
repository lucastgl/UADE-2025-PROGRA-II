package org.example.implementations;

import org.example.utils.exceptions.FullStructureException;
import org.example.utils.exceptions.InvalidIndexException;
import org.example.tda.LinkedListADT;

public class StaticLinkedListADT implements LinkedListADT {
    private static final int MAX_SIZE = 100;
    private int[] data;
    private int size;

    public StaticLinkedListADT() {
        data = new int[MAX_SIZE];
        size = 0;
    }

    @Override
    public void add(int value) {
        if (size >= MAX_SIZE) {
            throw new FullStructureException("Lista llena");
        }
        data[size] = value;
        size++;
    }

    @Override
    public void insert(int index, int value) {
        if (index < 0 || index > size || size >= MAX_SIZE) {
            throw new InvalidIndexException("Índice fuera de rango o lista llena");
        }
        // Desplazar elementos hacia la derecha
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Índice fuera de rango");
        }
        // Desplazar elementos hacia la izquierda
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Índice fuera de rango");
        }
        return data[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

