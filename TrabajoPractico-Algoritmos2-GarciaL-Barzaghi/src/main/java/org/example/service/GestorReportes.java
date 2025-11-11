package org.example.service;

import org.example.model.*;
import org.example.model.Enums.*;
import org.example.implementations.DynamicSimpleDictionaryADT;
import org.example.tda.SimpleDictionaryADT;

/**
 * Gestor de reportes del restaurante
 * Genera reportes estadísticos del sistema
 */
public class GestorReportes {
    
    private GestorPedidos gestorPedidos;
    private GestorCocina gestorCocina;
    private GestorReparto gestorReparto;
    private DatosIniciales datosIniciales;
    
    /**
     * Constructor
     */
    public GestorReportes(GestorPedidos gestorPedidos, GestorCocina gestorCocina, 
                          GestorReparto gestorReparto, DatosIniciales datosIniciales) {
        this.gestorPedidos = gestorPedidos;
        this.gestorCocina = gestorCocina;
        this.gestorReparto = gestorReparto;
        this.datosIniciales = datosIniciales;
    }
    
    // ==================== REPORTE DE PEDIDOS PENDIENTES ====================
    
    /**
     * Genera reporte de pedidos pendientes
     */
    public void reportePedidosPendientes() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         REPORTE DE PEDIDOS PENDIENTES                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Pedido[] todosPedidos = datosIniciales.getPedidos();
        int cantidadPedidos = datosIniciales.getCantidadPedidos();
        
        int pendientes = 0;
        int enPreparacion = 0;
        int listos = 0;
        
        System.out.println("  PEDIDOS PENDIENTES:");
        System.out.println("  ──────────────────────────────────────────────────────────");
        
        for (int i = 0; i < cantidadPedidos; i++) {
            if (todosPedidos[i] != null) {
                EstadoPedido estado = todosPedidos[i].getEstado();
                
                if (estado == EstadoPedido.PENDIENTE || 
                    estado == EstadoPedido.EN_PREPARACION || 
                    estado == EstadoPedido.LISTO) {
                    
                    Pedido pedido = todosPedidos[i];
                    System.out.println("\n  Pedido #" + pedido.getId());
                    System.out.println("    Cliente: " + pedido.getCliente().getNombre());
                    System.out.println("    Tipo: " + pedido.getTipoPedido());
                    System.out.println("    Prioridad: " + pedido.getPrioridad());
                    System.out.println("    Estado: " + pedido.getEstado());
                    System.out.println("    Platos: " + pedido.getCantidadPlatos());
                    
                    if (estado == EstadoPedido.PENDIENTE) pendientes++;
                    else if (estado == EstadoPedido.EN_PREPARACION) enPreparacion++;
                    else if (estado == EstadoPedido.LISTO) listos++;
                }
            }
        }
        
        System.out.println("\n  ──────────────────────────────────────────────────────────");
        System.out.println("  RESUMEN:");
        System.out.println("    Pendientes: " + pendientes);
        System.out.println("    En Preparación: " + enPreparacion);
        System.out.println("    Listos: " + listos);
        System.out.println("    Total Pendientes: " + (pendientes + enPreparacion + listos));
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    // ==================== REPORTE DE PEDIDOS FINALIZADOS ====================
    
    /**
     * Genera reporte de pedidos finalizados
     */
    public void reportePedidosFinalizados() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         REPORTE DE PEDIDOS FINALIZADOS                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Pedido[] todosPedidos = datosIniciales.getPedidos();
        int cantidadPedidos = datosIniciales.getCantidadPedidos();
        
        int entregados = 0;
        int cancelados = 0;
        
        System.out.println("  PEDIDOS FINALIZADOS:");
        System.out.println("  ──────────────────────────────────────────────────────────");
        
        for (int i = 0; i < cantidadPedidos; i++) {
            if (todosPedidos[i] != null) {
                EstadoPedido estado = todosPedidos[i].getEstado();
                
                if (estado == EstadoPedido.ENTREGADO || estado == EstadoPedido.CANCELADO) {
                    Pedido pedido = todosPedidos[i];
                    System.out.println("\n  Pedido #" + pedido.getId());
                    System.out.println("    Cliente: " + pedido.getCliente().getNombre());
                    System.out.println("    Tipo: " + pedido.getTipoPedido());
                    System.out.println("    Estado: " + pedido.getEstado());
                    System.out.println("    Platos: " + pedido.getCantidadPlatos());
                    
                    if (estado == EstadoPedido.ENTREGADO) entregados++;
                    else cancelados++;
                }
            }
        }
        
        System.out.println("\n  ──────────────────────────────────────────────────────────");
        System.out.println("  RESUMEN:");
        System.out.println("    Entregados: " + entregados);
        System.out.println("    Cancelados: " + cancelados);
        System.out.println("    Total Finalizados: " + (entregados + cancelados));
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    // ==================== REPORTE DE PEDIDOS POR REPARTIDOR ====================
    
    /**
     * Genera reporte de cantidad de pedidos por repartidor
     */
    public void reportePedidosPorRepartidor() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║      REPORTE DE PEDIDOS POR REPARTIDOR                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Repartidor[] repartidores = gestorReparto.getRepartidores();
        int cantidadRepartidores = gestorReparto.getCantidadRepartidores();
        
        if (cantidadRepartidores == 0) {
            System.out.println("  No hay repartidores registrados\n");
            return;
        }
        
        System.out.println("  PEDIDOS POR REPARTIDOR:");
        System.out.println("  ──────────────────────────────────────────────────────────\n");
        
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i] != null) {
                Repartidor repartidor = repartidores[i];
                int entregas = repartidor.getCantidadPedidosEntregados();
                String estado = repartidor.isDisponible() ? "Disponible" : "En reparto";
                
                System.out.println("  " + repartidor.getNombre() + " (ID: " + repartidor.getId() + ")");
                System.out.println("    Vehículo: " + repartidor.getVehiculo());
                System.out.println("    Estado: " + estado);
                System.out.println("    Pedidos entregados: " + entregas);
                System.out.println("    Rendimiento: " + repartidor.calcularRendimiento());
                System.out.println();
            }
        }
        
        System.out.println("  ──────────────────────────────────────────────────────────");
        System.out.println("  Total de entregas: " + gestorReparto.getTotalEntregasRealizadas());
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    // ==================== CLIENTE CON MÁS PEDIDOS ====================
    
    /**
     * Genera reporte del cliente con más pedidos
     */
    public void reporteClienteConMasPedidos() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        REPORTE: CLIENTE CON MÁS PEDIDOS                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Cliente[] clientes = datosIniciales.getClientes();
        int cantidadClientes = datosIniciales.getCantidadClientes();
        
        if (cantidadClientes == 0) {
            System.out.println("  No hay clientes registrados\n");
            return;
        }
        
        Cliente clienteMasPedidos = null;
        int maxPedidos = -1;
        
        // Encontrar cliente con más pedidos
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i] != null) {
                int cantidadPedidos = clientes[i].getCantidadPedidos();
                if (cantidadPedidos > maxPedidos) {
                    maxPedidos = cantidadPedidos;
                    clienteMasPedidos = clientes[i];
                }
            }
        }
        
        if (clienteMasPedidos == null || maxPedidos == 0) {
            System.out.println("  No hay clientes con pedidos registrados\n");
            return;
        }
        
        System.out.println("  CLIENTE CON MÁS PEDIDOS:");
        System.out.println("  ──────────────────────────────────────────────────────────\n");
        System.out.println("  Nombre: " + clienteMasPedidos.getNombre());
        System.out.println("  ID: " + clienteMasPedidos.getId());
        System.out.println("  Teléfono: " + clienteMasPedidos.getTelefono());
        System.out.println("  Dirección: " + clienteMasPedidos.getDireccion());
        System.out.println("  Email: " + clienteMasPedidos.getEmail());
        System.out.println("  Cliente VIP: " + (clienteMasPedidos.isClienteVIP() ? "Sí" : "No"));
        System.out.println("  Total de pedidos: " + maxPedidos);
        System.out.println("\n  ──────────────────────────────────────────────────────────");
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    // ==================== PLATOS MÁS PEDIDOS ====================
    
    /**
     * Genera reporte de platos más pedidos usando Diccionario para conteo
     */
    public void reportePlatosMasPedidos() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         REPORTE: PLATOS MÁS PEDIDOS                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Usar Diccionario para contar pedidos por plato
        SimpleDictionaryADT conteoPlatos = new DynamicSimpleDictionaryADT();
        
        Pedido[] todosPedidos = datosIniciales.getPedidos();
        int cantidadPedidos = datosIniciales.getCantidadPedidos();
        
        // Contar pedidos de cada plato
        for (int i = 0; i < cantidadPedidos; i++) {
            if (todosPedidos[i] != null) {
                Pedido pedido = todosPedidos[i];
                
                for (int j = 0; j < pedido.getCantidadPlatos(); j++) {
                    int idPlato = pedido.getPlato(j);
                    
                    // Obtener conteo actual o inicializar en 0
                    int conteoActual = 0;
                    try {
                        conteoActual = conteoPlatos.get(idPlato);
                    } catch (Exception e) {
                        // Si no existe, queda en 0
                    }
                    
                    // Incrementar conteo
                    conteoPlatos.add(idPlato, conteoActual + 1);
                }
            }
        }
        
        // Obtener todos los platos del menú
        Plato[] menu = gestorPedidos.getMenuCompleto();
        
        // Crear array para ordenar
        int totalPlatos = 0;
        for (int i = 0; i < menu.length; i++) {
            if (menu[i] != null) {
                try {
                    int conteo = conteoPlatos.get(menu[i].getId());
                    if (conteo > 0) {
                        totalPlatos++;
                    }
                } catch (Exception e) {
                    // No tiene pedidos
                }
            }
        }
        
        if (totalPlatos == 0) {
            System.out.println("  No hay platos con pedidos registrados\n");
            return;
        }
        
        // Crear array de pares (ID, conteo) para ordenar
        int[][] platosConConteo = new int[totalPlatos][2];
        int indice = 0;
        
        for (int i = 0; i < menu.length; i++) {
            if (menu[i] != null) {
                try {
                    int conteo = conteoPlatos.get(menu[i].getId());
                    if (conteo > 0) {
                        platosConConteo[indice][0] = menu[i].getId();
                        platosConConteo[indice][1] = conteo;
                        indice++;
                    }
                } catch (Exception e) {
                    // Ignorar
                }
            }
        }
        
        // Ordenar por conteo (bubble sort)
        for (int i = 0; i < totalPlatos - 1; i++) {
            for (int j = 0; j < totalPlatos - i - 1; j++) {
                if (platosConConteo[j][1] < platosConConteo[j + 1][1]) {
                    int[] temp = platosConConteo[j];
                    platosConConteo[j] = platosConConteo[j + 1];
                    platosConConteo[j + 1] = temp;
                }
            }
        }
        
        System.out.println("  PLATOS MÁS PEDIDOS (Top 10):");
        System.out.println("  ──────────────────────────────────────────────────────────\n");
        
        int limite = Math.min(10, totalPlatos);
        for (int i = 0; i < limite; i++) {
            int idPlato = platosConConteo[i][0];
            int conteo = platosConConteo[i][1];
            Plato plato = gestorPedidos.buscarPlatoPorId(idPlato);
            
            if (plato != null) {
                System.out.printf("  %d. %s%n", (i + 1), plato.getNombre());
                System.out.printf("     ID: %d | Pedidos: %d | Precio: $%.2f%n", 
                    plato.getId(), conteo, plato.getPrecio());
                System.out.println();
            }
        }
        
        System.out.println("  ──────────────────────────────────────────────────────────");
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    // ==================== REPORTE GENERAL ====================
    
    /**
     * Genera un reporte general del sistema
     */
    public void reporteGeneral() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              REPORTE GENERAL DEL SISTEMA                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Estadísticas generales
        System.out.println("  ESTADÍSTICAS GENERALES:");
        System.out.println("  ──────────────────────────────────────────────────────────");
        System.out.println("    Clientes registrados: " + datosIniciales.getCantidadClientes());
        Plato[] menu = gestorPedidos.getMenuCompleto();
        int cantidadPlatos = menu != null ? menu.length : 0;
        System.out.println("    Platos en menú: " + cantidadPlatos);
        System.out.println("    Repartidores: " + gestorReparto.getCantidadRepartidores());
        System.out.println("    Pedidos totales: " + datosIniciales.getCantidadPedidos());
        System.out.println("    Pedidos en cola de prioridad: " + gestorPedidos.getCantidadPedidosEnCola());
        System.out.println("    Pedidos en cocina: " + gestorCocina.getCantidadEnCola());
        System.out.println("    Pedidos en reparto: " + gestorReparto.getCantidadEnReparto());
        System.out.println("    Total entregas: " + gestorReparto.getTotalEntregasRealizadas());
        System.out.println("\n════════════════════════════════════════════════════════════\n");
    }
}

