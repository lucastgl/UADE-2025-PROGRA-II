package org.example.service;

import org.example.model.*;
import org.example.model.Enums.*;
import org.example.implementations.DynamicQueueADT;
import org.example.tda.QueueADT;

/**
 * Gestor de cocina del restaurante
 * Maneja la preparación de pedidos utilizando una cola FIFO
 */
public class GestorCocina {
    
    private QueueADT colaPreparacion;        // Cola de IDs de pedidos en preparación
    private int pedidoEnPreparacionId;       // ID del pedido actualmente siendo preparado
    private int pedidosPreparadosTotal;      // Contador de pedidos completados
    private String nombreCocina;
    private boolean activa;
    
    /**
     * Constructor
     */
    public GestorCocina(String nombreCocina) {
        this.nombreCocina = nombreCocina;
        this.colaPreparacion = new DynamicQueueADT();
        this.pedidoEnPreparacionId = -1;
        this.pedidosPreparadosTotal = 0;
        this.activa = true;
    }
    
    // ==================== GESTIÓN DE COLA DE PREPARACIÓN ====================
    
    /**
     * Agrega un pedido a la cola de preparación
     */
    public boolean agregarPedidoAPreparacion(int idPedido, Pedido pedido) {
        if (!activa) {
            System.out.println("✗ La cocina está inactiva");
            return false;
        }
        
        if (pedido == null) {
            System.out.println("✗ Error: Pedido no válido");
            return false;
        }
        
        // Verificar que el pedido tenga platos
        if (pedido.getCantidadPlatos() == 0) {
            System.out.println("✗ Error: El pedido no tiene platos");
            return false;
        }
        
        // Agregar a la cola
        colaPreparacion.add(idPedido);
        pedido.setEstado(EstadoPedido.EN_PREPARACION);
        
        System.out.println("✓ Pedido #" + idPedido + " agregado a cola de preparación");
        return true;
    }
    
    /**
     * Extrae el siguiente pedido de la cola según orden FIFO
     */
    public int extraerSiguientePedido() {
        if (colaPreparacion.isEmpty()) {
            System.out.println("✗ No hay pedidos en cola de preparación");
            return -1;
        }
        
        int idPedido = colaPreparacion.getElement();
        colaPreparacion.remove();
        
        return idPedido;
    }
    
    /**
     * Obtiene el siguiente pedido sin extraerlo
     */
    public int verSiguientePedido() {
        if (colaPreparacion.isEmpty()) {
            return -1;
        }
        return colaPreparacion.getElement();
    }
    
    // ==================== SIMULACIÓN DE PREPARACIÓN ====================
    
    /**
     * Inicia la preparación de un pedido
     */
    public boolean iniciarPreparacion(int idPedido, Pedido pedido) {
        if (pedidoEnPreparacionId != -1) {
            System.out.println("✗ Ya hay un pedido en preparación (Pedido #" + pedidoEnPreparacionId + ")");
            return false;
        }
        
        if (pedido == null) {
            System.out.println("✗ Error: Pedido no válido");
            return false;
        }
        
        pedidoEnPreparacionId = idPedido;
        pedido.setEstado(EstadoPedido.EN_PREPARACION);
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           INICIANDO PREPARACIÓN                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("  Pedido #" + idPedido);
        System.out.println("  Cliente: " + pedido.getCliente().getNombre());
        System.out.println("  Cantidad de platos: " + pedido.getCantidadPlatos());
        System.out.println("════════════════════════════════════════════════════════════");
        
        return true;
    }
    
    /**
     * Simula la preparación de todos los platos del pedido
     */
    public boolean prepararPedido(Pedido pedido, GestorPedidos gestorPedidos) {
        if (pedido == null || gestorPedidos == null) {
            System.out.println("✗ Error: Datos inválidos para preparación");
            return false;
        }
        
        System.out.println("\n→ Preparando platos...\n");
        
        int tiempoTotalSegundos = 0;
        
        // Simular preparación de cada plato
        for (int i = 0; i < pedido.getCantidadPlatos(); i++) {
            int idPlato = pedido.getPlato(i);
            Plato plato = gestorPedidos.buscarPlatoPorId(idPlato);
            
            if (plato != null) {
                System.out.print("  🍳 Preparando: " + plato.getNombre() + " (" + plato.getTiempoPreparacion() + " min)");
                
                // Simular tiempo de preparación (en realidad solo espera simbólica)
                simularTiempoPreparacion(plato.getTiempoPreparacion());
                
                System.out.println(" ✓");
                tiempoTotalSegundos += plato.getTiempoPreparacion() * 60; // Convertir a segundos
            }
        }
        
        System.out.println("\n  ✓ Todos los platos preparados");
        System.out.println("  ⏱️  Tiempo total de preparación: " + (tiempoTotalSegundos / 60) + " minutos");
        
        return true;
    }
    
    /**
     * Simula el tiempo de preparación (simbólico, no bloquea el programa)
     */
    private void simularTiempoPreparacion(int minutos) {
        // En un sistema real, aquí podríamos usar Thread.sleep() o un temporizador
        // Por ahora solo es simbólico para la demostración
        try {
            // Pausa muy breve solo para efecto visual (0.5 segundos)
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // Ignorar
        }
    }
    
    /**
     * Finaliza la preparación del pedido actual
     */
    public boolean finalizarPreparacion(Pedido pedido) {
        if (pedidoEnPreparacionId == -1) {
            System.out.println("✗ No hay ningún pedido en preparación");
            return false;
        }
        
        if (pedido == null || pedido.getId() != pedidoEnPreparacionId) {
            System.out.println("✗ Error: El pedido no coincide con el que está en preparación");
            return false;
        }
        
        // Marcar como listo
        pedido.setEstado(EstadoPedido.LISTO);
        pedidosPreparadosTotal++;
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              PEDIDO LISTO                                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("  Pedido #" + pedido.getId());
        System.out.println("  Tipo: " + pedido.getTipoPedido());
        System.out.println("  Estado: " + pedido.getEstado());
        System.out.println("════════════════════════════════════════════════════════════\n");
        
        // Limpiar pedido actual
        pedidoEnPreparacionId = -1;
        
        return true;
    }
    
    /**
     * Procesa completamente un pedido (inicio, preparación y finalización)
     */
    public boolean procesarPedidoCompleto(int idPedido, Pedido pedido, GestorPedidos gestorPedidos) {
        if (!iniciarPreparacion(idPedido, pedido)) {
            return false;
        }
        
        if (!prepararPedido(pedido, gestorPedidos)) {
            pedidoEnPreparacionId = -1;
            return false;
        }
        
        if (!finalizarPreparacion(pedido)) {
            return false;
        }
        
        return true;
    }
    
    // ==================== DECISIÓN DE DESTINO ====================
    
    /**
     * Determina si el pedido debe ir a reparto o está listo para retiro
     */
    public String determinarDestino(Pedido pedido) {
        if (pedido == null) {
            return "DESCONOCIDO";
        }
        
        if (pedido.getTipoPedido() == TipoPedido.DOMICILIO) {
            System.out.println("  → Pedido será enviado a REPARTO");
            return "REPARTO";
        } else {
            System.out.println("  → Pedido listo para RETIRO EN LOCAL");
            return "RETIRO";
        }
    }
    
    /**
     * Verifica si el pedido debe ir a reparto
     */
    public boolean requiereReparto(Pedido pedido) {
        return pedido != null && pedido.getTipoPedido() == TipoPedido.DOMICILIO;
    }
    
    // ==================== CONSULTAS Y ESTADÍSTICAS ====================
    
    /**
     * Obtiene la cantidad de pedidos en cola de preparación
     */
    public int getCantidadEnCola() {
        int contador = 0;
        QueueADT temp = new DynamicQueueADT();
        
        while (!colaPreparacion.isEmpty()) {
            int id = colaPreparacion.getElement();
            temp.add(id);
            colaPreparacion.remove();
            contador++;
        }
        
        // Restaurar la cola
        while (!temp.isEmpty()) {
            colaPreparacion.add(temp.getElement());
            temp.remove();
        }
        
        return contador;
    }
    
    /**
     * Verifica si la cola de preparación está vacía
     */
    public boolean colaVacia() {
        return colaPreparacion.isEmpty();
    }
    
    /**
     * Verifica si hay un pedido actualmente en preparación
     */
    public boolean hayPedidoEnPreparacion() {
        return pedidoEnPreparacionId != -1;
    }
    
    /**
     * Obtiene el ID del pedido en preparación actual
     */
    public int getPedidoEnPreparacionId() {
        return pedidoEnPreparacionId;
    }
    
    /**
     * Obtiene el total de pedidos preparados
     */
    public int getPedidosPreparadosTotal() {
        return pedidosPreparadosTotal;
    }
    
    /**
     * Muestra el estado actual de la cocina
     */
    public void mostrarEstado() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              ESTADO DE LA COCINA                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("  Nombre: " + nombreCocina);
        System.out.println("  Estado: " + (activa ? "✓ Activa" : "✗ Inactiva"));
        System.out.println("  Pedidos en cola: " + getCantidadEnCola());
        
        if (hayPedidoEnPreparacion()) {
            System.out.println("  Pedido en preparación: #" + pedidoEnPreparacionId);
        } else {
            System.out.println("  Pedido en preparación: Ninguno");
        }
        
        System.out.println("  Total preparados: " + pedidosPreparadosTotal);
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    /**
     * Muestra estadísticas de la cocina
     */
    public void mostrarEstadisticas() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           ESTADÍSTICAS DE COCINA                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("  Pedidos preparados totales: " + pedidosPreparadosTotal);
        System.out.println("  Pedidos en cola actual: " + getCantidadEnCola());
        System.out.println("  Estado de la cocina: " + (activa ? "Operativa" : "Cerrada"));
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    // ==================== GETTERS Y SETTERS ====================
    
    public String getNombreCocina() {
        return nombreCocina;
    }
    
    public void setNombreCocina(String nombreCocina) {
        this.nombreCocina = nombreCocina;
    }
    
    public boolean isActiva() {
        return activa;
    }
    
    public void setActiva(boolean activa) {
        this.activa = activa;
        System.out.println("✓ Cocina " + (activa ? "activada" : "desactivada"));
    }
    
    /**
     * Reinicia los contadores (útil para pruebas)
     */
    public void reiniciar() {
        colaPreparacion = new DynamicQueueADT();
        pedidoEnPreparacionId = -1;
        pedidosPreparadosTotal = 0;
        System.out.println("✓ Cocina reiniciada");
    }
}

