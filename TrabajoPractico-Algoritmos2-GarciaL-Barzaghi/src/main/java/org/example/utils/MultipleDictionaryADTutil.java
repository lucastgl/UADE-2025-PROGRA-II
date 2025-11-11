package org.example.utils;

import org.example.tda.SetADT;
import org.example.tda.MultipleDictionaryADT;
import org.example.implementations.StaticMultipleDictionaryADT;

import java.util.Arrays;

public class MultipleDictionaryADTutil {
    public static void print(MultipleDictionaryADT dictionary) {
        MultipleDictionaryADT copy = copy(dictionary);

        if (copy.isEmpty()) {
            System.out.println("El diccionario está vacío.");
            return;
        }

        SetADT keys = copy.getKeys();
        System.out.println("Contenido del diccionario:");

        while (!keys.isEmpty()) {
            int key = keys.choose();
            int[] values = dictionary.get(key);
            System.out.print("Clave " + key + ": ");

            if (values.length == 0) {
                System.out.println("Sin valores.");
            } else {
                System.out.println(Arrays.toString(values));
            }

            keys.remove(key);
        }
    }
    public static MultipleDictionaryADT copy(MultipleDictionaryADT dictionary) {
        if (dictionary.isEmpty()) {
            return new StaticMultipleDictionaryADT();
        }

        MultipleDictionaryADT copy = new StaticMultipleDictionaryADT();
        SetADT keys = dictionary.getKeys();

        while (!keys.isEmpty()) {
            int key = keys.choose();
            int[] values = dictionary.get(key);

            for (int value : values) {
                copy.add(key, value);
            }

            keys.remove(key);
        }

        return copy;
    }
}

