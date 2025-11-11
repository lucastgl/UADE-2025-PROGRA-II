package org.example.model;

import org.example.implementations.DynamicPriorityQueueADT;
import org.example.tda.PriorityQueueADT;
import org.example.model.Enums.EstadoPedido;

/**
 * Clase que representa la cocina del restaurante con su cola de pedidos en preparación
 */
public class Cocina {
    private String nombre;
    private PriorityQueueADT colaPedidos; // Cola con prioridad (VIP primero)
    private int capacidadMaxima;
    private int pedidosPreparados;
    private int pedidoActualId; // ID del pedido en preparación actual (-1 si no hay)
    private boolean activa;
    
    /**
     * Constructor completo
     */
    public Cocina(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.colaPedidos = new DynamicPriorityQueueADT();
        this.pedidosPreparados = 0;
        this.pedidoActualId = -1;
        this.activa = true;
    }
    
    /**
     * Constructor simplificado (capacidad ilimitada)
     */
    public Cocina(String nombre) {
        this(nombre, Integer.MAX_VALUE);
    }
    
    /**
     * Constructor por defecto
     */
    public Cocina() {
        this("Cocina Principal", Integer.MAX_VALUE);
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public PriorityQueueADT getColaPedidos() {
        return colaPedidos;
    }
    
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
    public int getPedidosPreparados() {
        return pedidosPreparados;
    }
    
    public int getPedidoActualId() {
        return pedidoActualId;
    }
    
    public boolean isActiva() {
        return activa;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setCapacidadMaxima(int capacidadMaxima) {
        if (capacidadMaxima > 0) {
            this.capacidadMaxima = capacidadMaxima;
        }
    }
    
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    // Métodos de utilidad
    
    /**
     * Agrega un pedido a la cola de la cocina
     * @param pedido El pedido a agregar
     * @return true si se agregó exitosamente, false si la cocina está llena o inactiva
     */
    public boolean agregarPedido(Pedido pedido) {
        if (!activa) {
            System.out.println("La cocina está inactiva. No se pueden recibir pedidos.");
            return false;
        }
        
        if (getCantidadPedidosEnCola() >= capacidadMaxima) {
            System.out.println("La cocina está al máximo de su capacidad.");
            return false;
        }
        
        // Agregar a la cola con prioridad (VIP=1, NORMAL=2)
        // Números menores tienen mayor prioridad
        int prioridad = pedido.getPrioridadNumerica();
        colaPedidos.add(pedido.getId(), prioridad);
        
        System.out.println("Pedido #" + pedido.getId() + " agregado a la cola de cocina.");
        return true;
    }
    
    /**
     * Toma el siguiente pedido de la cola para preparar
     * @return El ID del pedido a preparar, o -1 si no hay pedidos
     */
    public int tomarSiguientePedido() {
        if (colaPedidos.isEmpty()) {
            System.out.println("No hay pedidos en cola.");
            return -1;
        }
        
        if (pedidoActualId != -1) {
            System.out.println("Ya hay un pedido en preparación (#" + pedidoActualId + ")");
            return -1;
        }
        
        int idPedido = colaPedidos.getElement();
        colaPedidos.remove();
        pedidoActualId = idPedido;
        
        System.out.println("Comenzando preparación del pedido #" + idPedido);
        return idPedido;
    }
    
    /**
     * Marca el pedido actual como completado
     */
    public void completarPedidoActual() {
        if (pedidoActualId == -1) {
            System.out.println("No hay pedido en preparación.");
            return;
        }
        
        System.out.println("Pedido #" + pedidoActualId + " completado.");
        pedidosPreparados++;
        pedidoActualId = -1;
    }
    
    /**
     * Cancela el pedido actual y lo libera
     */
    public void cancelarPedidoActual() {
        if (pedidoActualId == -1) {
            System.out.println("No hay pedido en preparación para cancelar.");
            return;
        }
        
        System.out.println("Pedido #" + pedidoActualId + " cancelado.");
        pedidoActualId = -1;
    }
    
    /**
     * Obtiene la cantidad de pedidos en la cola
     */
    public int getCantidadPedidosEnCola() {
        int contador = 0;
        // Como PriorityQueueADT no tiene un método size(), debemos contar
        // Esto es una limitación - en una implementación real se podría agregar
        PriorityQueueADT temp = new DynamicPriorityQueueADT();
        while (!colaPedidos.isEmpty()) {
            int id = colaPedidos.getElement();
            int prioridad = colaPedidos.getPriority();
            temp.add(id, prioridad);
            colaPedidos.remove();
            contador++;
        }
        
        // Restaurar la cola
        while (!temp.isEmpty()) {
            int id = temp.getElement();
            int prioridad = temp.getPriority();
            colaPedidos.add(id, prioridad);
            temp.remove();
        }
        
        return contador;
    }
    
    /**
     * Verifica si la cola está vacía
     */
    public boolean colaVacia() {
        return colaPedidos.isEmpty();
    }
    
    /**
     * Verifica si hay un pedido en preparación
     */
    public boolean hayPedidoEnPreparacion() {
        return pedidoActualId != -1;
    }
    
    /**
     * Verifica si la cocina está disponible para tomar otro pedido
     */
    public boolean estaDisponible() {
        return activa && pedidoActualId == -1;
    }
    
    /**
     * Obtiene el siguiente pedido sin removerlo de la cola
     */
    public int verSiguientePedido() {
        if (colaPedidos.isEmpty()) {
            return -1;
        }
        return colaPedidos.getElement();
    }
    
    /**
     * Obtiene la prioridad del siguiente pedido
     */
    public int verPrioridadSiguiente() {
        if (colaPedidos.isEmpty()) {
            return -1;
        }
        return colaPedidos.getPriority();
    }
    
    /**
     * Calcula el porcentaje de utilización de la cocina
     */
    public double calcularUtilizacion() {
        if (capacidadMaxima == Integer.MAX_VALUE) {
            return 0.0; // Capacidad ilimitada
        }
        return (getCantidadPedidosEnCola() * 100.0) / capacidadMaxima;
    }
    
    /**
     * Reinicia los contadores (útil para testing o fin de jornada)
     */
    public void reiniciarContadores() {
        pedidosPreparados = 0;
    }
    
    /**
     * Obtiene información completa de la cocina
     */
    public String getInformacionCompleta() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Información de la Cocina ===\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Estado: ").append(activa ? "Activa" : "Inactiva").append("\n");
        sb.append("Pedidos en cola: ").append(getCantidadPedidosEnCola()).append("\n");
        sb.append("Capacidad máxima: ");
        if (capacidadMaxima == Integer.MAX_VALUE) {
            sb.append("Ilimitada\n");
        } else {
            sb.append(capacidadMaxima).append("\n");
            sb.append("Utilización: ").append(String.format("%.1f%%", calcularUtilizacion())).append("\n");
        }
        if (pedidoActualId != -1) {
            sb.append("Pedido en preparación: #").append(pedidoActualId).append("\n");
        } else {
            sb.append("Sin pedido en preparación\n");
        }
        sb.append("Pedidos preparados: ").append(pedidosPreparados).append("\n");
        
        if (!colaPedidos.isEmpty()) {
            sb.append("Siguiente pedido: #").append(verSiguientePedido());
            sb.append(" (Prioridad: ").append(verPrioridadSiguiente()).append(")\n");
        }
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s - Cola: %d - Preparados: %d%s",
                nombre,
                activa ? "Activa" : "Inactiva",
                getCantidadPedidosEnCola(),
                pedidosPreparados,
                pedidoActualId != -1 ? " - En preparación: #" + pedidoActualId : "");
    }
    
    /**
     * Versión resumida para estado rápido
     */
    public String toStringResumido() {
        return String.format("%s: %d en cola%s",
                nombre,
                getCantidadPedidosEnCola(),
                pedidoActualId != -1 ? " + 1 en preparación" : "");
    }
}

