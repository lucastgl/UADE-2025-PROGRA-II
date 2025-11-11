package org.example.implementations;

import org.example.utils.exceptions.EmptyStructureException;
import org.example.utils.exceptions.FullStructureException;
import org.example.utils.exceptions.InvalidIndexException;
import org.example.tda.MultipleDictionaryADT;
import org.example.tda.SetADT;

public class StaticMultipleDictionaryADT implements MultipleDictionaryADT {

    private static final int MAX = 10000;
    private static final int MAX_VALUES = 100;

    private final int[][] matrix; // [key, count, value1, value2, ...]
    private int count;

    public StaticMultipleDictionaryADT() {
        this.matrix = new int[MAX][MAX_VALUES + 2];
        this.count = 0;
    }

    @Override
    public void add(int key, int value) {
        for (int i = 0; i < count; i++) {
            if (matrix[i][0] == key) {
                matrix[i][matrix[i][1] + 2] = value;
                matrix[i][1]++;
                return;
            }
        }

        if (count >= MAX) {
            throw new FullStructureException("Diccionario lleno");
        }

        matrix[count][0] = key;
        matrix[count][1] = 1;
        matrix[count][2] = value;
        count++;
    }

    @Override
    public void remove(int key) {
        if (isEmpty()) {
            throw new EmptyStructureException("La estructura está vacía");
        }

        for (int i = 0; i < count; i++) {
            if (matrix[i][0] == key) {
                matrix[i] = matrix[count - 1]; // reemplazo por último
                matrix[count - 1] = new int[MAX_VALUES + 2]; // limpiar
                count--;
                return;
            }
        }
        // No hace nada si no se encuentra la clave
    }

    @Override
    public void remove(int key, int value) {
        if (isEmpty()) {
            throw new EmptyStructureException("La estructura está vacía");
        }

        for (int i = 0; i < count; i++) {
            if (matrix[i][0] == key) {
                int valueCount = matrix[i][1];
                for (int j = 0; j < valueCount; j++) {
                    if (matrix[i][j + 2] == value) {
                        // desplazamiento
                        for (int k = j + 2; k < valueCount + 1; k++) {
                            matrix[i][k] = matrix[i][k + 1];
                        }
                        matrix[i][1]--;
                        if (matrix[i][1] == 0) {
                            remove(key);
                        }
                        return;
                    }
                }
                throw new InvalidIndexException("El valor no existe para la clave");
            }
        }
        throw new InvalidIndexException("La clave no existe");
    }

    @Override
    public int[] get(int key) {
        if (isEmpty()) {
            throw new EmptyStructureException("La estructura está vacía");
        }

        for (int i = 0; i < count; i++) {
            if (matrix[i][0] == key) {
                int size = matrix[i][1];
                int[] result = new int[size];
                for (int j = 0; j < size; j++) {
                    result[j] = matrix[i][j + 2];
                }
                return result;
            }
        }
        throw new InvalidIndexException("La clave no existe");
    }

    @Override
    public SetADT getKeys() {
        SetADT keys = new StaticSetADT();
        for (int i = 0; i < count; i++) {
            keys.add(matrix[i][0]);
        }
        return keys;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}

