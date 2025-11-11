package org.example.utils;

import org.example.tda.BinaryTreeADT;
import org.example.implementations.StaticBinaryTreeADT;

public class BinaryTreeADTutil {

    public static BinaryTreeADT copy(BinaryTreeADT binaryTree) {
        BinaryTreeADT aux = new StaticBinaryTreeADT();

        if (binaryTree.isEmpty()) {
            return aux;
        }

        copyRecursive(binaryTree, aux);

        return aux;
    }

    private static void copyRecursive(BinaryTreeADT current, BinaryTreeADT aux) {
        if (current == null) {
            return;
        }

        aux.add(current.getRoot());

        if (current.getLeft() != null) {
            copyRecursive(current.getLeft(), aux);
        }

        if (current.getRight() != null) {
            copyRecursive(current.getRight(), aux);
        }
    }
    private static void printPreOrder(BinaryTreeADT binaryTree) {
        if (binaryTree.isEmpty()) {
            return;
        }
        System.out.println(binaryTree.getRoot());
        printPreOrder(binaryTree.getLeft());
        printPreOrder(binaryTree.getRight());
    }

    private static void printInOrder(BinaryTreeADT binaryTree) {
        if (binaryTree.isEmpty()) {
            return;
        }
        printInOrder(binaryTree.getLeft());
        System.out.println(binaryTree.getRoot());
        printInOrder(binaryTree.getRight());
    }

    private static void printPostOrder(BinaryTreeADT binaryTree) {
        if (binaryTree.isEmpty()) {
            return;
        }
        printPostOrder(binaryTree.getLeft());
        printPostOrder(binaryTree.getRight());
        System.out.println(binaryTree.getRoot());
    }

    private static void printByLevel(BinaryTreeADT binaryTree) {
        int height = height(binaryTree);
        for (int i = 0; i < height; i++) {
            printByLevel(binaryTree, i);
        }
    }

    private static int height(BinaryTreeADT binaryTree) {
        if (binaryTree == null || binaryTree.isEmpty()) {
            return 0;
        }
        return 1 + Math.max(height(binaryTree.getLeft()), height(binaryTree.getRight()));
    }

    private static void printByLevel(BinaryTreeADT binaryTree, int level) {
        if (binaryTree == null || binaryTree.isEmpty()) {  // ✅ Se corta si está vacío
            return;
        }
        if (level == 0) {
            System.out.println(binaryTree.getRoot());
        }
        printByLevel(binaryTree.getLeft(), level - 1);
        printByLevel(binaryTree.getRight(), level - 1);
    }
    public static void printAll(BinaryTreeADT binaryTree) {
        System.out.println("Recorrido por niveles:");
        printByLevel(binaryTree);

        System.out.println("\nRecorrido In-Order:");
        printInOrder(binaryTree);

        System.out.println("\nRecorrido Pre-Order:");
        printPreOrder(binaryTree);

        System.out.println("\nRecorrido Post-Order:");
        printPostOrder(binaryTree);
    }
}

