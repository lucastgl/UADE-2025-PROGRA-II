package org.example.implementations;

import org.example.utils.exceptions.EmptyStructureException;
import org.example.utils.exceptions.InvalidIndexException;
import org.example.implementations.nodes.DictionaryNode;
import org.example.tda.SetADT;
import org.example.tda.SimpleDictionaryADT;

public class DynamicSimpleDictionaryADT implements SimpleDictionaryADT {

    private DictionaryNode node;

    @Override
    public void add(int key, int value) {
        if (this.node == null) {
            this.node = new DictionaryNode(key, value, null);
            return;
        }

        DictionaryNode aux = this.node;
        while (aux != null) {
            if (aux.getKey() == key) {
                aux.setValue(value);  // Pisar el contenido si ya existe
                return;
            }
            if (aux.getNext() == null) break;
            aux = aux.getNext();
        }

        aux.setNext(new DictionaryNode(key, value, null));
    }

    @Override
    public void remove(int key) {
        if (this.node == null) return;

        if (this.node.getKey() == key) {
            this.node = this.node.getNext();
            return;
        }

        DictionaryNode prev = this.node;
        DictionaryNode current = this.node.getNext();

        while (current != null) {
            if (current.getKey() == key) {
                prev.setNext(current.getNext());
                return;
            }
            prev = current;
            current = current.getNext();
        }

        // No hacer nada si no se encuentra la clave
    }

    @Override
    public int get(int key) {
        if (this.node == null) {
            throw new EmptyStructureException("No se puede obtener un valor de una estructura vacía");
        }

        DictionaryNode aux = this.node;
        while (aux != null) {
            if (aux.getKey() == key) {
                return aux.getValue();
            }
            aux = aux.getNext();
        }

        throw new InvalidIndexException("La clave no existe");
    }

    @Override
    public SetADT getKeys() {
        SetADT result = new StaticSetADT();
        DictionaryNode aux = this.node;

        while (aux != null) {
            result.add(aux.getKey());
            aux = aux.getNext();
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return this.node == null;
    }
}

