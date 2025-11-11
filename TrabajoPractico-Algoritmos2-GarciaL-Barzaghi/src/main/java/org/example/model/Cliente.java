package org.example.model;

import org.example.implementations.DynamicLinkedListADT;
import org.example.tda.LinkedListADT;

/**
 * Clase que representa un cliente del restaurante
 */
public class Cliente {
    private static int contadorId = 1;
    
    private final int id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String email;
    private LinkedListADT historialPedidos; // Almacena IDs de pedidos
    private boolean clienteVIP;
    
    /**
     * Constructor completo
     */
    public Cliente(String nombre, String telefono, String direccion, String email) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.historialPedidos = new DynamicLinkedListADT();
        this.clienteVIP = false;
    }
    
    /**
     * Constructor simplificado
     */
    public Cliente(String nombre, String telefono) {
        this(nombre, telefono, "", "");
    }
    
    /**
     * Constructor mínimo
     */
    public Cliente(String nombre) {
        this(nombre, "", "", "");
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
    
    public String getDireccion() {
        return direccion;
    }
    
    public String getEmail() {
        return email;
    }
    
    public LinkedListADT getHistorialPedidos() {
        return historialPedidos;
    }
    
    public boolean isClienteVIP() {
        return clienteVIP;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setClienteVIP(boolean clienteVIP) {
        this.clienteVIP = clienteVIP;
    }
    
    // Métodos de utilidad
    
    /**
     * Agrega un pedido al historial del cliente
     */
    public void agregarPedidoAlHistorial(int idPedido) {
        historialPedidos.add(idPedido);
    }
    
    /**
     * Obtiene la cantidad de pedidos realizados
     */
    public int getCantidadPedidos() {
        return historialPedidos.size();
    }
    
    /**
     * Obtiene un pedido del historial por índice
     */
    public int getPedidoDelHistorial(int indice) {
        if (indice >= 0 && indice < historialPedidos.size()) {
            return historialPedidos.get(indice);
        }
        throw new IllegalArgumentException("Índice fuera de rango");
    }
    
    /**
     * Verifica si el cliente tiene pedidos en su historial
     */
    public boolean tienePedidos() {
        return !historialPedidos.isEmpty();
    }
    
    /**
     * Verifica si el cliente califica para ser VIP
     * (por ejemplo, si tiene más de 10 pedidos)
     */
    public boolean calificaParaVIP() {
        return historialPedidos.size() >= 10;
    }
    
    /**
     * Actualiza automáticamente el estado VIP según el historial
     */
    public void actualizarEstadoVIP() {
        if (calificaParaVIP()) {
            this.clienteVIP = true;
        }
    }
    
    /**
     * Obtiene información completa del cliente
     */
    public String getInformacionCompleta() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Información del Cliente ===\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Teléfono: ").append(telefono.isEmpty() ? "No especificado" : telefono).append("\n");
        sb.append("Dirección: ").append(direccion.isEmpty() ? "No especificada" : direccion).append("\n");
        sb.append("Email: ").append(email.isEmpty() ? "No especificado" : email).append("\n");
        sb.append("Estado: ").append(clienteVIP ? "VIP" : "Regular").append("\n");
        sb.append("Pedidos realizados: ").append(historialPedidos.size()).append("\n");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("Cliente #%d: %s - Tel: %s - %s - Pedidos: %d",
                id,
                nombre,
                telefono.isEmpty() ? "N/A" : telefono,
                clienteVIP ? "VIP" : "Regular",
                historialPedidos.size());
    }
    
    /**
     * Versión resumida para listas
     */
    public String toStringResumido() {
        return String.format("%d. %s (%s)", id, nombre, clienteVIP ? "VIP" : "Regular");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return id == cliente.id;
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

