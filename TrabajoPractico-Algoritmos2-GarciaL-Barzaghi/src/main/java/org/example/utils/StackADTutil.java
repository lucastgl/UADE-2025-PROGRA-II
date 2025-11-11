package org.example.utils;

import org.example.tda.StackADT;
import org.example.implementations.StaticStackADT;
import org.example.implementations.DynamicStackADT;

public class StackADTutil {

    public static StackADT copy(StackADT stack) {
        StackADT aux = getNewStack(stack);
        StackADT aux2 = getNewStack(stack);

        while (!stack.isEmpty()) {
            aux.add(stack.getElement());
            aux2.add(stack.getElement());
            stack.remove();
        }
        while (!aux.isEmpty()) {
            stack.add(aux.getElement());
            aux.remove();
        }
        while (!aux2.isEmpty()) {
            aux.add(aux2.getElement());
            aux2.remove();
        }
        return aux;

    }
    public static void print(StackADT stack) {
        StackADT copy = copy(stack);
        while (!copy.isEmpty()) {
            System.out.println(copy.getElement());
            copy.remove();
        }
    }

    private static StackADT getNewStack(StackADT stack) {
        if (stack instanceof StaticStackADT) {
            return new StaticStackADT();
        } else {
            return new DynamicStackADT();
        }
    }
}

