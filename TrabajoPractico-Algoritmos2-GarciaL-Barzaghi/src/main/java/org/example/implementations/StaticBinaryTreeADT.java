package org.example.implementations;

import org.example.tda.BinaryTreeADT;
import org.example.utils.exceptions.EmptyStructureException;
import org.example.utils.exceptions.FullStructureException;

public class StaticBinaryTreeADT implements BinaryTreeADT {

    private static final int MAX_HEIGHT = 10;
    private static final int CAPACITY = (1 << MAX_HEIGHT) - 1;

    private final Integer[] array;
    private final int indexRoot;

    public StaticBinaryTreeADT() {
        this.array = new Integer[CAPACITY];
        this.indexRoot = 0;
    }

    private StaticBinaryTreeADT(Integer[] array, int indexRoot) {
        this.array = array;
        this.indexRoot = indexRoot;
    }

    public StaticBinaryTreeADT(int root) {
        this();
        this.array[indexRoot] = root;
    }

    @Override
    public int getRoot() {
        if (isEmpty()) {
            throw new EmptyStructureException("El árbol está vacío.");
        }
        return array[indexRoot];
    }

    @Override
    public BinaryTreeADT getLeft() {
        int leftIndex = 2 * indexRoot + 1;
        if (leftIndex >= CAPACITY || array[leftIndex] == null) {
            return new StaticBinaryTreeADT();  // Devuelve un árbol vacío si no hay hijo izquierdo
        }
        return new StaticBinaryTreeADT(array, leftIndex);
    }

    @Override
    public BinaryTreeADT getRight() {
        int rightIndex = 2 * indexRoot + 2;
        if (rightIndex >= CAPACITY || array[rightIndex] == null) {
            return new StaticBinaryTreeADT();  // Devuelve un árbol vacío si no hay hijo derecho
        }
        return new StaticBinaryTreeADT(array, rightIndex);
    }

    @Override
    public void add(int value) {
        int index = indexRoot;
        while (index < CAPACITY) {
            if (array[index] == null) {
                array[index] = value;
                return;
            }
            if (value == array[index]) {
                throw new FullStructureException("Valor duplicado: " + value);
            }
            if (value < array[index]) {
                index = 2 * index + 1;
            } else {
                index = 2 * index + 2;
            }
        }
        throw new FullStructureException("Capacidad máxima alcanzada.");
    }

    @Override
    public void remove(int value) {
        int index = indexRoot;
        int parent = -1;
        boolean isLeftChild = false;

        while (index < CAPACITY && array[index] != null) {
            if (array[index] == value) {
                break;
            }
            parent = index;
            if (value < array[index]) {
                index = 2 * index + 1;
                isLeftChild = true;
            } else {
                index = 2 * index + 2;
                isLeftChild = false;
            }
        }

        if (index >= CAPACITY || array[index] == null) {
            throw new EmptyStructureException("Valor no encontrado: " + value);
        }

        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if ((left >= CAPACITY || array[left] == null) && (right >= CAPACITY || array[right] == null)) {
            array[index] = null;
        } else if (left >= CAPACITY || array[left] == null) {
            array[index] = array[right];
            array[right] = null;
        } else if (right >= CAPACITY || array[right] == null) {
            array[index] = array[left];
            array[left] = null;
        } else {
            int successorIndex = right;
            while (2 * successorIndex + 1 < CAPACITY && array[2 * successorIndex + 1] != null) {
                successorIndex = 2 * successorIndex + 1;
            }
            array[index] = array[successorIndex];
            array[successorIndex] = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return array[indexRoot] == null;
    }
}

