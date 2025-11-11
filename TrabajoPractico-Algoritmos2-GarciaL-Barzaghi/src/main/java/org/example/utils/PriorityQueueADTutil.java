package org.example.utils;

import org.example.tda.PriorityQueueADT;
import org.example.implementations.StaticPriorityQueueADT;

public class PriorityQueueADTutil {

    public static void print(PriorityQueueADT queue) {
        PriorityQueueADT copy = copy(queue);
        while (!copy.isEmpty()) {
            System.out.println("Valor: " + copy.getElement() + " - Prioridad: " + copy.getPriority());
            copy.remove();
        }
    }

    public static PriorityQueueADT copy(PriorityQueueADT queue) {
        PriorityQueueADT aux = new StaticPriorityQueueADT();
        PriorityQueueADT copy = new StaticPriorityQueueADT();
        while (!queue.isEmpty()) {
            aux.add(queue.getElement(), queue.getPriority());
            queue.remove();
        }

        while (!aux.isEmpty()) {
            copy.add(aux.getElement(), aux.getPriority());
            queue.add(aux.getElement(), aux.getPriority());
            aux.remove();
        }

        return copy;
    }
}

