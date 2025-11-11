package org.example.service;

import org.example.model.*;
import org.example.model.Enums.*;
import org.example.implementations.DynamicLinkedListADT;
import org.example.tda.LinkedListADT;

/**
 * Gestor de reparto del restaurante
 * Maneja la asignación de repartidores y seguimiento de entregas
 */
public class GestorReparto {
    
    private Repartidor[] repartidores;           // Array de repartidores
    private int cantidadRepartidores;
    private static final int MAX_REPARTIDORES = 50;
    
    private LinkedListADT pedidosEnReparto;      // IDs de pedidos actualmente en reparto
    private int totalEntregasRealizadas;
    private String nombreSistema;
    
    /**
     * Constructor
     */
    public GestorReparto(String nombreSistema) {
        this.nombreSistema = nombreSistema;
        this.repartidores = new Repartidor[MAX_REPARTIDORES];
        this.cantidadRepartidores = 0;
        this.pedidosEnReparto = new DynamicLinkedListADT();
        this.totalEntregasRealizadas = 0;
    }
    
    // ==================== GESTIÓN DE REPARTIDORES ====================
    
    /**
     * Da de alta un nuevo repartidor
     */
    public boolean altaRepartidor(Repartidor repartidor) {
        if (repartidor == null) {
            System.out.println("✗ Error: Repartidor no válido");
            return false;
        }
        
        if (cantidadRepartidores >= MAX_REPARTIDORES) {
            System.out.println("✗ Error: Se alcanzó el máximo de repartidores");
            return false;
        }
        
        // Verificar si ya existe
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i] != null && repartidores[i].getId() == repartidor.getId()) {
                System.out.println("✗ Error: El repartidor ya está registrado");
                return false;
            }
        }
        
        repartidores[cantidadRepartidores] = repartidor;
        cantidadRepartidores++;
        
        System.out.println("✓ Repartidor dado de alta: " + repartidor.getNombre());
        return true;
    }
    
    /**
     * Carga múltiples repartidores desde un array
     */
    public void cargarRepartidores(Repartidor[] repartidoresIniciales, int cantidad) {
        System.out.println("\n→ Cargando repartidores en sistema de reparto...");
        
        for (int i = 0; i < cantidad && i < repartidoresIniciales.length; i++) {
            if (repartidoresIniciales[i] != null) {
                altaRepartidor(repartidoresIniciales[i]);
            }
        }
        
        System.out.println("  ✓ " + cantidadRepartidores + " repartidores cargados\n");
    }
    
    /**
     * Busca un repartidor disponible
     */
    public Repartidor buscarRepartidorDisponible() {
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i] != null && repartidores[i].isDisponible()) {
                return repartidores[i];
            }
        }
        return null;
    }
    
    /**
     * Busca el repartidor con menos entregas (para balancear carga)
     */
    public Repartidor buscarRepartidorMenosEntregas() {
        Repartidor seleccionado = null;
        int menosEntregas = Integer.MAX_VALUE;
        
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i] != null && repartidores[i].isDisponible()) {
                int entregas = repartidores[i].getCantidadPedidosEntregados();
                if (entregas < menosEntregas) {
                    menosEntregas = entregas;
                    seleccionado = repartidores[i];
                }
            }
        }
        
        return seleccionado;
    }
    
    /**
     * Obtiene la cantidad de repartidores disponibles
     */
    public int getCantidadDisponibles() {
        int contador = 0;
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i] != null && repartidores[i].isDisponible()) {
                contador++;
            }
        }
        return contador;
    }
    
    /**
     * Obtiene la cantidad de repartidores en reparto
     */
    public int getCantidadEnReparto() {
        return cantidadRepartidores - getCantidadDisponibles();
    }
    
    // ==================== ASIGNACIÓN DE PEDIDOS ====================
    
    /**
     * Asigna automáticamente un pedido al siguiente repartidor disponible
     */
    public boolean asignarPedidoAutomatico(int idPedido, Pedido pedido) {
        if (pedido == null) {
            System.out.println("✗ Error: Pedido no válido");
            return false;
        }
        
        // Verificar que el pedido esté listo
        if (pedido.getEstado() != EstadoPedido.LISTO) {
            System.out.println("✗ Error: El pedido no está listo para reparto");
            return false;
        }
        
        // Verificar que sea de tipo DOMICILIO
        if (pedido.getTipoPedido() != TipoPedido.DOMICILIO) {
            System.out.println("✗ Error: El pedido no es de tipo DOMICILIO");
            return false;
        }
        
        // Buscar repartidor disponible (balanceando carga)
        Repartidor repartidor = buscarRepartidorMenosEntregas();
        
        if (repartidor == null) {
            System.out.println("✗ No hay repartidores disponibles");
            return false;
        }
        
        // Asignar el pedido al repartidor
        repartidor.asignarPedido(idPedido);
        pedido.setEstado(EstadoPedido.EN_CAMINO);
        pedidosEnReparto.add(idPedido);
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           PEDIDO ASIGNADO A REPARTIDOR                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("  Pedido #" + idPedido);
        System.out.println("  Repartidor: " + repartidor.getNombre());
        System.out.println("  Vehículo: " + repartidor.getVehiculo());
        System.out.println("  Cliente: " + pedido.getCliente().getNombre());
        System.out.println("  Dirección: " + pedido.getCliente().getDireccion());
        System.out.println("════════════════════════════════════════════════════════════");
        
        return true;
    }
    
    // ==================== SIMULACIÓN DE ENTREGA ====================
    
    /**
     * Simula la entrega de un pedido (recorrido simplificado)
     */
    public boolean simularEntrega(int idPedido, Pedido pedido, Repartidor repartidor) {
        if (pedido == null || repartidor == null) {
            System.out.println("✗ Error: Datos inválidos");
            return false;
        }
        
        if (repartidor.getPedidoActualId() != idPedido) {
            System.out.println("✗ Error: El repartidor no tiene asignado este pedido");
            return false;
        }
        
        System.out.println("\n→ Simulando entrega...\n");
        
        // Calcular distancia simplificada (basada en el hash del cliente)
        int distancia = calcularDistanciaSimplificada(pedido.getCliente());
        int tiempoEstimado = calcularTiempoEntrega(distancia, repartidor.getVehiculo());
        
        System.out.println("  📍 Punto de partida: Restaurante");
        System.out.println("  📍 Destino: " + pedido.getCliente().getDireccion());
        System.out.println("  📏 Distancia estimada: " + distancia + " km");
        System.out.println("  🚗 Vehículo: " + repartidor.getVehiculo());
        System.out.println("  ⏱️  Tiempo estimado: " + tiempoEstimado + " minutos");
        
        // Simular recorrido (pausa breve para efecto visual)
        System.out.print("\n  🛣️  Realizando entrega");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(300);
                System.out.print(".");
            } catch (InterruptedException e) {
                // Ignorar
            }
        }
        System.out.println(" ✓\n");
        
        return true;
    }
    
    /**
     * Calcula distancia simplificada basada en dirección del cliente
     */
    private int calcularDistanciaSimplificada(Cliente cliente) {
        // Usar hash de la dirección para generar distancia "aleatoria" pero consistente
        int hash = Math.abs(cliente.getDireccion().hashCode());
        // Distancia entre 1 y 15 km
        return (hash % 15) + 1;
    }
    
    /**
     * Calcula tiempo de entrega según distancia y vehículo
     */
    private int calcularTiempoEntrega(int distancia, String vehiculo) {
        int velocidadPromedio;
        
        // Velocidad promedio según tipo de vehículo (km por minuto)
        if (vehiculo.toLowerCase().contains("moto")) {
            velocidadPromedio = 30; // 30 km/h en ciudad
        } else if (vehiculo.toLowerCase().contains("bicicleta")) {
            velocidadPromedio = 15; // 15 km/h
        } else if (vehiculo.toLowerCase().contains("auto")) {
            velocidadPromedio = 25; // 25 km/h en ciudad
        } else {
            velocidadPromedio = 20; // Velocidad por defecto
        }
        
        // Tiempo en minutos (distancia / velocidad * 60)
        return (distancia * 60) / velocidadPromedio;
    }
    
    /**
     * Completa la entrega de un pedido
     */
    public boolean completarEntrega(int idPedido, Pedido pedido, Repartidor repartidor) {
        if (pedido == null || repartidor == null) {
            System.out.println("✗ Error: Datos inválidos");
            return false;
        }
        
        // Completar en el repartidor
        repartidor.completarEntrega();
        
        // Actualizar estado del pedido
        pedido.setEstado(EstadoPedido.ENTREGADO);
        
        // Remover de pedidos en reparto
        removerDePedidosEnReparto(idPedido);
        
        // Incrementar contador global
        totalEntregasRealizadas++;
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              ENTREGA COMPLETADA                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("  Pedido #" + idPedido + " ENTREGADO");
        System.out.println("  Cliente: " + pedido.getCliente().getNombre());
        System.out.println("  Repartidor: " + repartidor.getNombre());
        System.out.println("  Total entregas del repartidor: " + repartidor.getCantidadPedidosEntregados());
        System.out.println("════════════════════════════════════════════════════════════\n");
        
        return true;
    }
    
    /**
     * Procesa una entrega completa (asignación + simulación + completado)
     */
    public boolean procesarEntregaCompleta(int idPedido, Pedido pedido) {
        // Asignar repartidor
        if (!asignarPedidoAutomatico(idPedido, pedido)) {
            return false;
        }
        
        // Buscar el repartidor asignado
        Repartidor repartidor = buscarRepartidorConPedido(idPedido);
        if (repartidor == null) {
            System.out.println("✗ Error: No se encontró el repartidor asignado");
            return false;
        }
        
        // Simular entrega
        if (!simularEntrega(idPedido, pedido, repartidor)) {
            return false;
        }
        
        // Completar entrega
        return completarEntrega(idPedido, pedido, repartidor);
    }
    
    /**
     * Busca el repartidor que tiene asignado un pedido específico
     */
    public Repartidor buscarRepartidorConPedido(int idPedido) {
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i] != null && repartidores[i].getPedidoActualId() == idPedido) {
                return repartidores[i];
            }
        }
        return null;
    }
    
    /**
     * Remueve un pedido de la lista de pedidos en reparto
     */
    private void removerDePedidosEnReparto(int idPedido) {
        LinkedListADT temp = new DynamicLinkedListADT();
        
        for (int i = 0; i < pedidosEnReparto.size(); i++) {
            int id = pedidosEnReparto.get(i);
            if (id != idPedido) {
                temp.add(id);
            }
        }
        
        pedidosEnReparto = temp;
    }
    
    // ==================== CONSULTAS Y ESTADÍSTICAS ====================
    
    /**
     * Muestra el estado del sistema de reparto
     */
    public void mostrarEstado() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           ESTADO DEL SISTEMA DE REPARTO                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("  Sistema: " + nombreSistema);
        System.out.println("  Repartidores totales: " + cantidadRepartidores);
        System.out.println("  Repartidores disponibles: " + getCantidadDisponibles());
        System.out.println("  Repartidores en reparto: " + getCantidadEnReparto());
        System.out.println("  Pedidos en reparto: " + pedidosEnReparto.size());
        System.out.println("  Total entregas realizadas: " + totalEntregasRealizadas);
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    /**
     * Muestra estadísticas de repartidores
     */
    public void mostrarEstadisticasRepartidores() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         ESTADÍSTICAS DE REPARTIDORES                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        if (cantidadRepartidores == 0) {
            System.out.println("  No hay repartidores registrados\n");
            return;
        }
        
        // Ordenar por cantidad de entregas (bubble sort)
        Repartidor[] ordenados = new Repartidor[cantidadRepartidores];
        for (int i = 0; i < cantidadRepartidores; i++) {
            ordenados[i] = repartidores[i];
        }
        
        for (int i = 0; i < cantidadRepartidores - 1; i++) {
            for (int j = 0; j < cantidadRepartidores - i - 1; j++) {
                if (ordenados[j] != null && ordenados[j + 1] != null) {
                    if (ordenados[j].getCantidadPedidosEntregados() < ordenados[j + 1].getCantidadPedidosEntregados()) {
                        Repartidor temp = ordenados[j];
                        ordenados[j] = ordenados[j + 1];
                        ordenados[j + 1] = temp;
                    }
                }
            }
        }
        
        // Mostrar top repartidores
        for (int i = 0; i < cantidadRepartidores && i < 10; i++) {
            if (ordenados[i] != null) {
                String estado = ordenados[i].isDisponible() ? "✓ Disponible" : "🚗 En reparto";
                String rendimiento = ordenados[i].calcularRendimiento();
                
                System.out.printf("  %d. %s (%s)%n", (i + 1), ordenados[i].getNombre(), ordenados[i].getVehiculo());
                System.out.printf("     Entregas: %d | Rendimiento: %s | Estado: %s%n",
                    ordenados[i].getCantidadPedidosEntregados(),
                    rendimiento,
                    estado);
                
                if (i < cantidadRepartidores - 1) {
                    System.out.println();
                }
            }
        }
        
        System.out.println("\n════════════════════════════════════════════════════════════\n");
    }
    
    /**
     * Muestra todos los repartidores
     */
    public void mostrarTodosRepartidores() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                LISTA DE REPARTIDORES                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        if (cantidadRepartidores == 0) {
            System.out.println("  No hay repartidores registrados\n");
            return;
        }
        
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i] != null) {
                System.out.println("  " + repartidores[i]);
            }
        }
        
        System.out.println();
    }
    
    // ==================== GETTERS ====================
    
    public int getCantidadRepartidores() {
        return cantidadRepartidores;
    }
    
    public int getTotalEntregasRealizadas() {
        return totalEntregasRealizadas;
    }
    
    public Repartidor[] getRepartidores() {
        return repartidores;
    }
    
    public String getNombreSistema() {
        return nombreSistema;
    }
}

