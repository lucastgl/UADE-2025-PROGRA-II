package org.example.implementations;

import org.example.tda.GraphADT;
import org.example.tda.SetADT;
import org.example.tda.SimpleDictionaryADT;
import org.example.utils.exceptions.EmptyStructureException;
import org.example.utils.exceptions.FullStructureException;
import org.example.utils.exceptions.ElementNotFoundException;
import org.example.utils.exceptions.DuplicateElementException;

public class StaticGraphADT implements GraphADT {

    private static final int MAX_VERTICES = 50;

    private final int[][] adjacencyMatrix;
    private final SimpleDictionaryADT dictionary;
    private int totalVertices;

    public StaticGraphADT(SimpleDictionaryADT dictionaryImpl) {
        this.adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        this.dictionary = dictionaryImpl;
        this.totalVertices = 0;
    }

    @Override
    public SetADT getVertxs() {
        return this.dictionary.getKeys();
    }

    @Override
    public void addVertx(int vertex) {
        if (this.totalVertices >= MAX_VERTICES) {
            throw new FullStructureException("No se pueden agregar más vértices (límite " + MAX_VERTICES + ")");
        }

        SetADT vertices = this.dictionary.getKeys();
        while (!vertices.isEmpty()) {
            int current = vertices.choose();
            if (current == vertex) {
                throw new DuplicateElementException("El vértice ya existe: " + vertex);
            }
            vertices.remove(current);
        }

        this.dictionary.add(vertex, this.totalVertices);
        this.totalVertices++;
    }

    @Override
    public void removeVertx(int vertex) {
        if (this.totalVertices == 0) {
            throw new EmptyStructureException("El grafo está vacío");
        }

        SetADT vertices = this.dictionary.getKeys();
        boolean exists = false;
        int index = -1;
        int lastVertex = -1;

        while (!vertices.isEmpty()) {
            int current = vertices.choose();
            if (this.dictionary.get(current) == this.totalVertices - 1) {
                lastVertex = current;
            }
            if (current == vertex) {
                exists = true;
                index = this.dictionary.get(vertex);
            }
            vertices.remove(current);
        }

        if (!exists) {
            throw new ElementNotFoundException("El vértice no existe: " + vertex);
        }

        int lastIndex = this.totalVertices - 1;

        if (lastVertex == vertex) {
            this.dictionary.remove(vertex);
            this.totalVertices--;
            return;
        }

        for (int i = 0; i < this.totalVertices; i++) {
            this.adjacencyMatrix[i][index] = this.adjacencyMatrix[i][lastIndex];
            this.adjacencyMatrix[index][i] = this.adjacencyMatrix[lastIndex][i];
            this.adjacencyMatrix[i][lastIndex] = 0;
            this.adjacencyMatrix[lastIndex][i] = 0;
        }

        this.dictionary.remove(vertex);
        this.dictionary.remove(lastVertex);
        this.dictionary.add(lastVertex, index);
        this.totalVertices--;
    }

    @Override
    public void addEdge(int vertxOne, int vertxTwo, int weight) {
        boolean existsOne = false;
        boolean existsTwo = false;

        SetADT vertices = this.dictionary.getKeys();
        while (!vertices.isEmpty()) {
            int current = vertices.choose();
            if (current == vertxOne) existsOne = true;
            if (current == vertxTwo) existsTwo = true;
            vertices.remove(current);
        }

        if (!existsOne || !existsTwo) {
            throw new ElementNotFoundException("Alguno de los vértices no existe");
        }

        int indexOne = this.dictionary.get(vertxOne);
        int indexTwo = this.dictionary.get(vertxTwo);

        if (this.adjacencyMatrix[indexOne][indexTwo] != 0) {
            throw new DuplicateElementException("La arista ya existe entre " + vertxOne + " y " + vertxTwo);
        }

        this.adjacencyMatrix[indexOne][indexTwo] = weight;
    }

    @Override
    public void removeEdge(int vertxOne, int vertxTwo) {
        if (!existsEdge(vertxOne, vertxTwo)) {
            throw new ElementNotFoundException("La arista no existe entre " + vertxOne + " y " + vertxTwo);
        }

        int indexOne = this.dictionary.get(vertxOne);
        int indexTwo = this.dictionary.get(vertxTwo);
        this.adjacencyMatrix[indexOne][indexTwo] = 0;
    }

    @Override
    public boolean existsEdge(int vertxOne, int vertxTwo) {
        boolean existsOne = false;
        boolean existsTwo = false;

        SetADT vertices = this.dictionary.getKeys();
        while (!vertices.isEmpty()) {
            int current = vertices.choose();
            if (current == vertxOne) existsOne = true;
            if (current == vertxTwo) existsTwo = true;
            vertices.remove(current);
        }

        if (!existsOne || !existsTwo) {
            return false;
        }

        int indexOne = this.dictionary.get(vertxOne);
        int indexTwo = this.dictionary.get(vertxTwo);

        return this.adjacencyMatrix[indexOne][indexTwo] != 0;
    }

    @Override
    public int edgeWeight(int vertxOne, int vertxTwo) {
        if (!existsEdge(vertxOne, vertxTwo)) {
            throw new ElementNotFoundException("La arista no existe entre " + vertxOne + " y " + vertxTwo);
        }
        int indexOne = this.dictionary.get(vertxOne);
        int indexTwo = this.dictionary.get(vertxTwo);

        return this.adjacencyMatrix[indexOne][indexTwo];
    }

    @Override
    public boolean isEmpty() {
        return this.totalVertices == 0;
    }
}

