package org.example.model;

/**
 * Enumeraciones utilizadas en el sistema de gestión de pedidos
 */
public class Enums {
    
    /**
     * Tipo de pedido del cliente
     */
    public enum TipoPedido {
        DOMICILIO("Domicilio"),
        RETIRO("Retiro en local");
        
        private final String descripcion;
        
        TipoPedido(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
        
        @Override
        public String toString() {
            return descripcion;
        }
    }
    
    /**
     * Prioridad del pedido
     */
    public enum Prioridad {
        VIP(1, "VIP"),
        NORMAL(2, "Normal");
        
        private final int nivel;
        private final String descripcion;
        
        Prioridad(int nivel, String descripcion) {
            this.nivel = nivel;
            this.descripcion = descripcion;
        }
        
        public int getNivel() {
            return nivel;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
        
        @Override
        public String toString() {
            return descripcion;
        }
    }
    
    /**
     * Estado del pedido en el sistema
     */
    public enum EstadoPedido {
        PENDIENTE("Pendiente"),
        EN_PREPARACION("En Preparación"),
        LISTO("Listo para Entrega/Retiro"),
        EN_CAMINO("En Camino"),
        ENTREGADO("Entregado"),
        CANCELADO("Cancelado");
        
        private final String descripcion;
        
        EstadoPedido(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
        
        @Override
        public String toString() {
            return descripcion;
        }
    }
}
