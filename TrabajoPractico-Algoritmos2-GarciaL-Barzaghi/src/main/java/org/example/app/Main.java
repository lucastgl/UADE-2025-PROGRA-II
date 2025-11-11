package org.example.app;

/**
 * Sistema de Gestión de Pedidos y Entregas para Restaurante
 * 
 * Estructura del proyecto:
 * - model:          Clases del dominio (Pedido, Plato, Repartidor, Cliente)
 * - tda:            Interfaces de TDAs propias (Cola, Pila, Lista, Diccionario, etc.)
 * - implementations: Implementaciones concretas de TDAs (dinámicas y estáticas)
 * - service:        Lógica de negocio (GestorPedidos, GestorCocina, GestorReparto)
 * - utils:          Clases auxiliares y utilidades para TDAs
 * - app:            Clase principal con menú interactivo por consola
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  Sistema de Gestión de Pedidos y Entregas");
        System.out.println("===========================================");
        System.out.println();
        System.out.println("Proyecto correctamente estructurado.");
        System.out.println("Las interfaces TDA se encuentran en: org.example.tda");
        System.out.println("Las implementaciones se encuentran en: org.example.implementations");
        System.out.println("Las utilidades se encuentran en: org.example.utils");
        System.out.println();
        System.out.println("Próximos pasos:");
        System.out.println("1. Definir clases del modelo de dominio");
        System.out.println("2. Implementar servicios de gestión");
        System.out.println("3. Crear menú interactivo");
    }
}