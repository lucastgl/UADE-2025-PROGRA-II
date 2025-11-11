package org.example.implementations;

import org.example.utils.exceptions.EmptyStructureException;
import org.example.utils.exceptions.FullStructureException;
import org.example.tda.SetADT;
import java.util.Random;

public class StaticSetADT implements SetADT {

    private static final int MAX = 10000;
    private final int[] array;
    private int count;
    private final Random random;

    public StaticSetADT() {
        array = new int[MAX];
        count = 0;
        random = new Random();
    }

    @Override
    public void add(int value) {
        if (count >= MAX) {
            throw new FullStructureException("No se puede agregar más elementos: la estructura está llena.");
        }
        if (exist(value)) return;
        array[count] = value;
        count++;
    }

    @Override
    public void remove(int element) {
        for (int i = 0; i < count; i++) {
            if (array[i] == element) {
                array[i] = array[count - 1];
                count--;
                return;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int choose() {
        if (isEmpty()) {
            throw new EmptyStructureException("El conjunto está vacío");
        }
        int index = random.nextInt(count);
        return array[index];
    }

    @Override
    public boolean exist(int value) {
        for (int i = 0; i < count; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }
}

