package org.example.model;

import org.example.model.Enums.TipoPedido;
import org.example.model.Enums.Prioridad;
import org.example.model.Enums.EstadoPedido;
import org.example.implementations.DynamicLinkedListADT;
import org.example.tda.LinkedListADT;

/**
 * Clase que representa un pedido en el sistema
 */
public class Pedido {
    private static int contadorId = 1;
    
    private final int id;
    private Cliente cliente;
    private LinkedListADT platos; // Almacena índices de platos del menú
    private TipoPedido tipoPedido;
    private Prioridad prioridad;
    private EstadoPedido estado;
    private long timestamp; // Momento en que se realizó el pedido
    
    /**
     * Constructor completo
     */
    public Pedido(Cliente cliente, TipoPedido tipoPedido, Prioridad prioridad) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.platos = new DynamicLinkedListADT();
        this.tipoPedido = tipoPedido;
        this.prioridad = prioridad;
        this.estado = EstadoPedido.PENDIENTE;
        this.timestamp = System.currentTimeMillis();
    }
    
    /**
     * Constructor simplificado (prioridad normal por defecto)
     */
    public Pedido(Cliente cliente, TipoPedido tipoPedido) {
        this(cliente, tipoPedido, Prioridad.NORMAL);
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public LinkedListADT getPlatos() {
        return platos;
    }
    
    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }
    
    public Prioridad getPrioridad() {
        return prioridad;
    }
    
    public EstadoPedido getEstado() {
        return estado;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    // Setters
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }
    
    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
    
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
    
    // Métodos de utilidad
    
    /**
     * Agrega un plato al pedido (se almacena el ID del plato)
     */
    public void agregarPlato(int idPlato) {
        platos.add(idPlato);
    }
    
    /**
     * Elimina un plato del pedido por índice
     */
    public void eliminarPlato(int indice) {
        if (indice >= 0 && indice < platos.size()) {
            platos.remove(indice);
        }
    }
    
    /**
     * Obtiene el plato en una posición específica
     */
    public int getPlato(int indice) {
        if (indice >= 0 && indice < platos.size()) {
            return platos.get(indice);
        }
        throw new IllegalArgumentException("Índice fuera de rango");
    }
    
    /**
     * Obtiene la cantidad de platos en el pedido
     */
    public int getCantidadPlatos() {
        return platos.size();
    }
    
    /**
     * Verifica si el pedido tiene platos
     */
    public boolean tienePlatos() {
        return !platos.isEmpty();
    }
    
    /**
     * Calcula el tiempo total de preparación del pedido
     * (requiere acceso a los objetos Plato completos)
     */
    public int calcularTiempoPreparacionTotal(Plato[] menuPlatos) {
        int tiempoTotal = 0;
        for (int i = 0; i < platos.size(); i++) {
            int idPlato = platos.get(i);
            if (idPlato >= 0 && idPlato < menuPlatos.length && menuPlatos[idPlato] != null) {
                tiempoTotal += menuPlatos[idPlato].getTiempoPreparacion();
            }
        }
        return tiempoTotal;
    }
    
    /**
     * Obtiene la prioridad numérica para ordenamiento en cola
     */
    public int getPrioridadNumerica() {
        return prioridad.getNivel();
    }
    
    @Override
    public String toString() {
        return String.format("Pedido #%d - Cliente: %s, Tipo: %s, Prioridad: %s, Estado: %s, Platos: %d",
                id, 
                cliente != null ? cliente.getNombre() : "Sin cliente",
                tipoPedido,
                prioridad,
                estado,
                platos.size());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pedido pedido = (Pedido) obj;
        return id == pedido.id;
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

