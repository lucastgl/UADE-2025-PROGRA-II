package org.example.model;

import org.example.implementations.DynamicLinkedListADT;
import org.example.tda.LinkedListADT;

/**
 * Clase que representa un repartidor del restaurante
 */
public class Repartidor {
    private static int contadorId = 1;
    
    private final int id;
    private String nombre;
    private String telefono;
    private String vehiculo;
    private boolean disponible;
    private LinkedListADT pedidosEntregados; // Almacena IDs de pedidos entregados
    private int pedidoActualId; // ID del pedido que está entregando actualmente (-1 si no tiene)
    
    /**
     * Constructor completo
     */
    public Repartidor(String nombre, String telefono, String vehiculo) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.telefono = telefono;
        this.vehiculo = vehiculo;
        this.disponible = true;
        this.pedidosEntregados = new DynamicLinkedListADT();
        this.pedidoActualId = -1;
    }
    
    /**
     * Constructor simplificado
     */
    public Repartidor(String nombre, String telefono) {
        this(nombre, telefono, "No especificado");
    }
    
    /**
     * Constructor mínimo
     */
    public Repartidor(String nombre) {
        this(nombre, "", "No especificado");
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public String getVehiculo() {
        return vehiculo;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    public LinkedListADT getPedidosEntregados() {
        return pedidosEntregados;
    }
    
    public int getPedidoActualId() {
        return pedidoActualId;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    // Métodos de utilidad
    
    /**
     * Asigna un pedido al repartidor
     */
    public boolean asignarPedido(int idPedido) {
        if (!disponible) {
            return false;
        }
        this.pedidoActualId = idPedido;
        this.disponible = false;
        return true;
    }
    
    /**
     * Completa la entrega del pedido actual
     */
    public void completarEntrega() {
        if (pedidoActualId != -1) {
            pedidosEntregados.add(pedidoActualId);
            pedidoActualId = -1;
            disponible = true;
        }
    }
    
    /**
     * Cancela el pedido actual y libera al repartidor
     */
    public void cancelarPedidoActual() {
        pedidoActualId = -1;
        disponible = true;
    }
    
    /**
     * Obtiene la cantidad de pedidos entregados
     */
    public int getCantidadPedidosEntregados() {
        return pedidosEntregados.size();
    }
    
    /**
     * Obtiene un pedido entregado del historial por índice
     */
    public int getPedidoEntregado(int indice) {
        if (indice >= 0 && indice < pedidosEntregados.size()) {
            return pedidosEntregados.get(indice);
        }
        throw new IllegalArgumentException("Índice fuera de rango");
    }
    
    /**
     * Verifica si el repartidor tiene pedidos entregados
     */
    public boolean tienePedidosEntregados() {
        return !pedidosEntregados.isEmpty();
    }
    
    /**
     * Verifica si el repartidor tiene un pedido asignado actualmente
     */
    public boolean tienePedidoAsignado() {
        return pedidoActualId != -1;
    }
    
    /**
     * Calcula el rendimiento del repartidor (pedidos entregados)
     */
    public String calcularRendimiento() {
        int totalEntregados = pedidosEntregados.size();
        if (totalEntregados == 0) {
            return "Sin entregas realizadas";
        } else if (totalEntregados < 5) {
            return "Principiante (" + totalEntregados + " entregas)";
        } else if (totalEntregados < 20) {
            return "Intermedio (" + totalEntregados + " entregas)";
        } else {
            return "Experimentado (" + totalEntregados + " entregas)";
        }
    }
    
    /**
     * Obtiene información completa del repartidor
     */
    public String getInformacionCompleta() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Información del Repartidor ===\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Teléfono: ").append(telefono.isEmpty() ? "No especificado" : telefono).append("\n");
        sb.append("Vehículo: ").append(vehiculo).append("\n");
        sb.append("Estado: ").append(disponible ? "Disponible" : "Ocupado").append("\n");
        if (pedidoActualId != -1) {
            sb.append("Pedido actual: #").append(pedidoActualId).append("\n");
        }
        sb.append("Entregas realizadas: ").append(pedidosEntregados.size()).append("\n");
        sb.append("Rendimiento: ").append(calcularRendimiento()).append("\n");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("Repartidor #%d: %s - %s - %s - Entregas: %d",
                id,
                nombre,
                disponible ? "Disponible" : "Ocupado",
                vehiculo,
                pedidosEntregados.size());
    }
    
    /**
     * Versión resumida para listas
     */
    public String toStringResumido() {
        return String.format("%d. %s (%s)", 
                id, 
                nombre, 
                disponible ? "Disponible" : "Ocupado");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Repartidor that = (Repartidor) obj;
        return id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
    
    /**
     * Reinicia el contador de IDs (útil para testing)
     */
    public static void reiniciarContador() {
        contadorId = 1;
    }
}

