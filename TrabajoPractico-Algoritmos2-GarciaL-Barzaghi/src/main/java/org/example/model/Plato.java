package org.example.model;

/**
 * Clase que representa un plato del menú del restaurante
 */
public class Plato {
    private static int contadorId = 1;
    
    private final int id;
    private String nombre;
    private int tiempoPreparacion; // En minutos
    private int cantidadPedidos; // Contador de veces que se ha pedido este plato
    private double precio;
    private boolean disponible;
    
    /**
     * Constructor completo
     */
    public Plato(String nombre, int tiempoPreparacion, double precio) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.precio = precio;
        this.cantidadPedidos = 0;
        this.disponible = true;
    }
    
    /**
     * Constructor simplificado
     */
    public Plato(String nombre, int tiempoPreparacion) {
        this(nombre, tiempoPreparacion, 0.0);
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }
    
    public int getCantidadPedidos() {
        return cantidadPedidos;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setTiempoPreparacion(int tiempoPreparacion) {
        if (tiempoPreparacion > 0) {
            this.tiempoPreparacion = tiempoPreparacion;
        }
    }
    
    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        }
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    // Métodos de utilidad
    
    /**
     * Incrementa el contador de pedidos de este plato
     */
    public void incrementarPedidos() {
        this.cantidadPedidos++;
    }
    
    /**
     * Decrementa el contador de pedidos (por ejemplo, si se cancela un pedido)
     */
    public void decrementarPedidos() {
        if (this.cantidadPedidos > 0) {
            this.cantidadPedidos--;
        }
    }
    
    /**
     * Reinicia el contador de pedidos
     */
    public void reiniciarContadorPedidos() {
        this.cantidadPedidos = 0;
    }
    
    /**
     * Calcula la popularidad del plato como porcentaje
     */
    public double calcularPopularidad(int totalPedidos) {
        if (totalPedidos == 0) return 0.0;
        return (cantidadPedidos * 100.0) / totalPedidos;
    }
    
    @Override
    public String toString() {
        return String.format("Plato #%d: %s - Tiempo: %d min - Precio: $%.2f - Pedidos: %d - %s",
                id,
                nombre,
                tiempoPreparacion,
                precio,
                cantidadPedidos,
                disponible ? "Disponible" : "No disponible");
    }
    
    /**
     * Versión resumida para menús
     */
    public String toStringResumido() {
        return String.format("%d. %s ($%.2f - %d min)",
                id,
                nombre,
                precio,
                tiempoPreparacion);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Plato plato = (Plato) obj;
        return id == plato.id;
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

