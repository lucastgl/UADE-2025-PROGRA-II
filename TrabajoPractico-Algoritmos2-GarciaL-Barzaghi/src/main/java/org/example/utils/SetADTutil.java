package org.example.utils;

import org.example.tda.SetADT;
import org.example.implementations.StaticSetADT;

public class SetADTutil {
    public static void pasar(SetADT original, SetADT copia) {
 // mientras original no este vacia, inicializo una variable y eso lo añado a copia.
        while(!original.isEmpty()) {
            int numero = original.choose();
            copia.add(numero);
            original.remove(numero);
        }
    }

    public static void copy(SetADT original, SetADT copia) {
        SetADT aux = new StaticSetADT();
/* con una pila aux, paso original a la aux, y mientras aux no este vacia, añado todos los
elementos a la copia y a la original. */
        pasar(original,aux);
        while(!aux.isEmpty()) {
            int numero = aux.choose();

            copia.add(numero);
            original.add(numero);
            aux.remove(numero);
        }
    }
    public static void print(SetADT conjunto) {
        SetADT aux = new StaticSetADT();
        copy(conjunto,aux);
        while(!aux.isEmpty()) {
            int numero = aux.choose();
            System.out.print(numero + " ");
            aux.remove(numero);
        }
    }
}

