package org.example.implementations;

import org.example.tda.BinaryTreeADT;
import org.example.utils.exceptions.EmptyStructureException;
import org.example.utils.exceptions.FullStructureException;

public class DynamicBinaryTreeADT implements BinaryTreeADT {

    private Integer root;                    // null ⇒ árbol vacío
    private DynamicBinaryTreeADT left;
    private DynamicBinaryTreeADT right;

    /** Crea un árbol vacío. */
    public DynamicBinaryTreeADT() { }

    /** Crea un árbol con un único nodo raíz. */
    public DynamicBinaryTreeADT(int value) {
        this.root = value;
    }

    @Override
    public int getRoot() {
        if (isEmpty()) {
            throw new EmptyStructureException("El árbol está vacío: no hay raíz.");
        }
        return root;
    }

    @Override
    public BinaryTreeADT getLeft() {
        if (left == null) {
            return new DynamicBinaryTreeADT();  // Retorna un árbol vacío si no hay hijo izquierdo
        }
        return left;
    }

    @Override
    public BinaryTreeADT getRight() {
        if (right == null) {
            return new DynamicBinaryTreeADT();  // Retorna un árbol vacío si no hay hijo derecho
        }
        return right;
    }

    @Override
    public void add(int value) {
        if (isEmpty()) {             // Inserta en la raíz
            root = value;
            return;
        }

        if (value == root) {
            throw new FullStructureException("Valor duplicado: " + value);
        }

        if (value < root) {
            if (left == null) left = new DynamicBinaryTreeADT();
            left.add(value);
        } else {                     // value > root
            if (right == null) right = new DynamicBinaryTreeADT();
            right.add(value);
        }
    }

    @Override
    public void remove(int value) {
        if (isEmpty()) {
            throw new EmptyStructureException("El árbol está vacío.");
        }

        /* ---- Caso: buscar el valor en subárbol izquierdo o derecho ---- */
        if (value < root) {
            if (left == null) throw new EmptyStructureException("El valor no existe.");
            left.remove(value);
            if (left.isEmpty()) left = null;         // Podamos rama vacía
            return;
        }
        if (value > root) {
            if (right == null) throw new EmptyStructureException("El valor no existe.");
            right.remove(value);
            if (right.isEmpty()) right = null;       // Podamos rama vacía
            return;
        }

        /* ---- Caso: this.root es el valor a eliminar ---- */
        if (left != null && right != null) {          // Dos hijos
            // Buscar sucesor in-order = mínimo del subárbol derecho
            DynamicBinaryTreeADT succ = right;
            while (succ.left != null) succ = succ.left;
            int succVal = succ.root;
            this.root = succVal;                      // Reemplaza
            right.remove(succVal);                    // Elimina duplicado
            if (right.isEmpty()) right = null;
        } else {                                      // Uno o ningún hijo
            DynamicBinaryTreeADT child = (left != null) ? left : right;
            if (child != null) {
                this.root  = child.root;
                this.left  = child.left;
                this.right = child.right;
            } else {                                  // Hoja
                this.root = null;
                this.left = null;
                this.right = null;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}

