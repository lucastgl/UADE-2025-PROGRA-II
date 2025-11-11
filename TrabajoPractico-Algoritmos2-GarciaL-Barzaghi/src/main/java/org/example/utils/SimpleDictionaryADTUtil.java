package org.example.utils;

import org.example.tda.SetADT;
import org.example.tda.SimpleDictionaryADT;
import org.example.implementations.StaticSimpleDictionaryADT;
import org.example.implementations.StaticSetADT;

public class SimpleDictionaryADTUtil {

    public static SimpleDictionaryADT copy(SimpleDictionaryADT dictionary) {
        SimpleDictionaryADT aux = new StaticSimpleDictionaryADT();

        SetADT keys = dictionary.getKeys();
        SetADT tempKeys = new StaticSetADT(); // crear un set vacío donde se copiarán las claves
        SetADTutil.copy(keys, tempKeys);      // ahora sí, pasamos los 2 argumentos

        while (!tempKeys.isEmpty()) {
            int key = tempKeys.choose();
            tempKeys.remove(key);
            int value = dictionary.get(key);
            aux.add(key, value);
        }

        return aux;
    }

    public static void print(SimpleDictionaryADT dictionary) {
        SetADT keys = dictionary.getKeys();
        while (!keys.isEmpty()) {
            int key = keys.choose();
            System.out.println(key + "\t" + dictionary.get(key));
            keys.remove(key);
        }
    }

}

