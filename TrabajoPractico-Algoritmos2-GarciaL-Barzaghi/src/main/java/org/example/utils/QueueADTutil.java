package org.example.utils;

import org.example.tda.QueueADT;
import org.example.implementations.StaticQueueADT;

// import static algorithm.queue.pasarCola.pasar; // TODO: Descomentar cuando exista este paquete

public class QueueADTutil {
    public static void copy(QueueADT original, QueueADT copia) {
        QueueADT aux = new StaticQueueADT();
        // pasar(original, aux); // TODO: Descomentar cuando exista este método
        
        // Implementación temporal alternativa
        while (!original.isEmpty()) {
            aux.add(original.getElement());
            original.remove();
        }

        while (!aux.isEmpty()) {
            original.add(aux.getElement());
            copia.add(aux.getElement());
            aux.remove();
        }
    }

    public static void print(QueueADT original) {
        QueueADT aux = new StaticQueueADT();
        copy(original, aux);
        while (!aux.isEmpty()) {
            System.out.println(aux.getElement());
            aux.remove();

        }
    }
}

