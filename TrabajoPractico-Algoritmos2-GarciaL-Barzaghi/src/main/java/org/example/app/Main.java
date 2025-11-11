package org.example.app;

import org.example.model.*;
import org.example.model.Enums.*;
import org.example.service.GestorPedidos;
import org.example.service.DatosIniciales;

import java.util.Scanner;

/**
 * Sistema de Gestión de Pedidos y Entregas para Restaurante
 * Menú interactivo escalable con gestión completa del sistema
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static GestorPedidos gestorPedidos;
    private static DatosIniciales datosIniciales;
    
    public static void main(String[] args) {
        mostrarBienvenida();
        inicializarSistema();
        ejecutarMenuPrincipal();
    }
    
    /**
     * Muestra mensaje de bienvenida
     */
    private static void mostrarBienvenida() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║       SISTEMA DE GESTIÓN DE PEDIDOS Y ENTREGAS             ║");
        System.out.println("║              🍕 Restaurante UADE 🍔                        ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("  Algoritmos y Estructuras de Datos 2 - UADE 2025");
        System.out.println("  Usando TDAs propios - Sin estructuras nativas de Java\n");
    }
    
    /**
     * Inicializa el sistema y carga datos
     */
    private static void inicializarSistema() {
        gestorPedidos = new GestorPedidos();
        datosIniciales = new DatosIniciales();
        
        // Cargar datos iniciales
        datosIniciales.cargarTodo(gestorPedidos);
        
        presionarEnter();
    }
    
    /**
     * Menú principal del sistema
     */
    private static void ejecutarMenuPrincipal() {
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    menuGestionPedidos();
                    break;
                case 2:
                    menuConsultas();
                    break;
                case 3:
                    menuEstadisticas();
                    break;
                case 4:
                    menuConfiguracion();
                    break;
                case 0:
                    continuar = confirmarSalida();
                    break;
                default:
                    System.out.println("\n✗ Opción inválida. Intente nuevamente.\n");
            }
        }
        
        despedida();
    }
    
    /**
     * Muestra el menú principal
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    MENÚ PRINCIPAL                          ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. 📋 Gestión de Pedidos                                  ║");
        System.out.println("║  2. 🔍 Consultas                                           ║");
        System.out.println("║  3. 📊 Estadísticas                                        ║");
        System.out.println("║  4. ⚙️  Configuración                                      ║");
        System.out.println("║  0. 🚪 Salir                                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.print("  Seleccione una opción: ");
    }
    
    /**
     * Menú de gestión de pedidos
     */
    private static void menuGestionPedidos() {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║              GESTIÓN DE PEDIDOS                            ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. ➕ Registrar Nuevo Pedido                              ║");
            System.out.println("║  2. 📋 Ver Cola de Pedidos Pendientes                      ║");
            System.out.println("║  3. ⏭️  Procesar Siguiente Pedido                          ║");
            System.out.println("║  4. 🔍 Buscar Pedido por ID                                ║");
            System.out.println("║  0. ⬅️  Volver al Menú Principal                           ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.print("  Seleccione una opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    registrarNuevoPedido();
                    break;
                case 2:
                    verColaPedidos();
                    break;
                case 3:
                    procesarSiguientePedido();
                    break;
                case 4:
                    buscarPedidoPorId();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("\n✗ Opción inválida.\n");
            }
        }
    }
    
    /**
     * Registra un nuevo pedido
     */
    private static void registrarNuevoPedido() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              REGISTRAR NUEVO PEDIDO                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        try {
            // Seleccionar cliente
            Cliente cliente = seleccionarCliente();
            if (cliente == null) return;
            
            // Seleccionar tipo de pedido
            TipoPedido tipoPedido = seleccionarTipoPedido();
            
            // Determinar prioridad según cliente
            Prioridad prioridad = cliente.isClienteVIP() ? Prioridad.VIP : Prioridad.NORMAL;
            
            // Crear pedido
            Pedido pedido = new Pedido(cliente, tipoPedido, prioridad);
            
            // Agregar platos
            System.out.println("\n→ Agregando platos al pedido...");
            boolean agregarMas = true;
            
            while (agregarMas) {
                gestorPedidos.mostrarMenu();
                System.out.print("  Ingrese ID del plato (0 para terminar): ");
                int idPlato = leerOpcion();
                
                if (idPlato == 0) {
                    agregarMas = false;
                } else {
                    if (gestorPedidos.verificarPlatoDisponible(idPlato)) {
                        pedido.agregarPlato(idPlato);
                        Plato plato = gestorPedidos.buscarPlatoPorId(idPlato);
                        System.out.println("  ✓ " + plato.getNombre() + " agregado");
                    } else {
                        System.out.println("  ✗ Plato no disponible o no existe");
                    }
                }
            }
            
            // Registrar pedido
            if (pedido.getCantidadPlatos() > 0) {
                System.out.println();
                if (gestorPedidos.registrarPedido(pedido)) {
                    // Agregar al array de pedidos
                    Pedido[] pedidos = datosIniciales.getPedidos();
                    int cant = datosIniciales.getCantidadPedidos();
                    if (cant < pedidos.length) {
                        pedidos[cant] = pedido;
                    }
                }
            } else {
                System.out.println("\n✗ No se agregaron platos. Pedido cancelado.");
            }
            
        } catch (Exception e) {
            System.out.println("\n✗ Error al registrar pedido: " + e.getMessage());
        }
        
        presionarEnter();
    }
    
    /**
     * Selecciona un cliente
     */
    private static Cliente seleccionarCliente() {
        System.out.println("→ Clientes disponibles:");
        Cliente[] clientes = datosIniciales.getClientes();
        int cant = datosIniciales.getCantidadClientes();
        
        for (int i = 0; i < cant; i++) {
            if (clientes[i] != null) {
                System.out.println("  " + clientes[i].toStringResumido());
            }
        }
        
        System.out.print("\n  Ingrese ID del cliente: ");
        int idCliente = leerOpcion();
        
        Cliente cliente = gestorPedidos.buscarClientePorId(idCliente, clientes);
        if (cliente == null) {
            System.out.println("\n✗ Cliente no encontrado");
        }
        return cliente;
    }
    
    /**
     * Selecciona tipo de pedido
     */
    private static TipoPedido seleccionarTipoPedido() {
        System.out.println("\n→ Tipo de pedido:");
        System.out.println("  1. Domicilio");
        System.out.println("  2. Retiro en local");
        System.out.print("\n  Seleccione tipo: ");
        
        int opcion = leerOpcion();
        return (opcion == 1) ? TipoPedido.DOMICILIO : TipoPedido.RETIRO;
    }
    
    /**
     * Muestra la cola de pedidos pendientes
     */
    private static void verColaPedidos() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           COLA DE PEDIDOS PENDIENTES                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        int enCola = gestorPedidos.getCantidadPedidosEnCola();
        
        if (enCola == 0) {
            System.out.println("  No hay pedidos en cola\n");
        } else {
            System.out.println("  Pedidos en cola: " + enCola);
            System.out.println("  (Los pedidos VIP se procesan primero)\n");
        }
        
        presionarEnter();
    }
    
    /**
     * Procesa el siguiente pedido de la cola
     */
    private static void procesarSiguientePedido() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           PROCESAR SIGUIENTE PEDIDO                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        int idPedido = gestorPedidos.obtenerSiguientePedido();
        
        if (idPedido != -1) {
            Pedido pedido = gestorPedidos.buscarPedidoPorId(idPedido, datosIniciales.getPedidos());
            if (pedido != null) {
                System.out.println("\n" + pedido);
                pedido.setEstado(EstadoPedido.EN_PREPARACION);
                System.out.println("  Estado actualizado: EN_PREPARACION");
            }
        }
        
        System.out.println();
        presionarEnter();
    }
    
    /**
     * Busca un pedido por ID
     */
    private static void buscarPedidoPorId() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              BUSCAR PEDIDO POR ID                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        System.out.print("  Ingrese ID del pedido: ");
        int idPedido = leerOpcion();
        
        Pedido pedido = gestorPedidos.buscarPedidoPorId(idPedido, datosIniciales.getPedidos());
        
        if (pedido != null) {
            System.out.println("\n  ✓ Pedido encontrado:");
            System.out.println("  " + pedido);
            System.out.println();
        } else {
            System.out.println("\n  ✗ Pedido no encontrado\n");
        }
        
        presionarEnter();
    }
    
    /**
     * Menú de consultas
     */
    private static void menuConsultas() {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║                    CONSULTAS                               ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. 📋 Ver Menú de Platos                                  ║");
            System.out.println("║  2. 👥 Ver Clientes                                        ║");
            System.out.println("║  3. 🏍️  Ver Repartidores                                   ║");
            System.out.println("║  4. 📦 Ver Todos los Pedidos                               ║");
            System.out.println("║  0. ⬅️  Volver                                             ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.print("  Seleccione una opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    gestorPedidos.mostrarMenu();
                    presionarEnter();
                    break;
                case 2:
                    mostrarClientes();
                    break;
                case 3:
                    mostrarRepartidores();
                    break;
                case 4:
                    mostrarTodosPedidos();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("\n✗ Opción inválida.\n");
            }
        }
    }
    
    /**
     * Muestra todos los clientes
     */
    private static void mostrarClientes() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    CLIENTES                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Cliente[] clientes = datosIniciales.getClientes();
        int cant = datosIniciales.getCantidadClientes();
        
        for (int i = 0; i < cant; i++) {
            if (clientes[i] != null) {
                System.out.println("  " + clientes[i]);
            }
        }
        
        System.out.println();
        presionarEnter();
    }
    
    /**
     * Muestra todos los repartidores
     */
    private static void mostrarRepartidores() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  REPARTIDORES                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Repartidor[] repartidores = datosIniciales.getRepartidores();
        int cant = datosIniciales.getCantidadRepartidores();
        
        for (int i = 0; i < cant; i++) {
            if (repartidores[i] != null) {
                System.out.println("  " + repartidores[i]);
            }
        }
        
        System.out.println();
        presionarEnter();
    }
    
    /**
     * Muestra todos los pedidos
     */
    private static void mostrarTodosPedidos() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  TODOS LOS PEDIDOS                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Pedido[] pedidos = datosIniciales.getPedidos();
        int cant = datosIniciales.getCantidadPedidos();
        
        for (int i = 0; i < cant; i++) {
            if (pedidos[i] != null) {
                System.out.println("  " + pedidos[i]);
            }
        }
        
        System.out.println();
        presionarEnter();
    }
    
    /**
     * Menú de estadísticas
     */
    private static void menuEstadisticas() {
        boolean volver = false;
        
        while (!volver) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║                  ESTADÍSTICAS                              ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. 📊 Estadísticas Generales                              ║");
            System.out.println("║  2. 🍕 Platos Más Populares                                ║");
            System.out.println("║  3. 👑 Clientes VIP                                        ║");
            System.out.println("║  0. ⬅️  Volver                                             ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.print("  Seleccione una opción: ");
            
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    gestorPedidos.mostrarEstadisticas();
                    presionarEnter();
                    break;
                case 2:
                    gestorPedidos.mostrarPlatosPopulares();
                    presionarEnter();
                    break;
                case 3:
                    mostrarClientesVIP();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("\n✗ Opción inválida.\n");
            }
        }
    }
    
    /**
     * Muestra clientes VIP
     */
    private static void mostrarClientesVIP() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  CLIENTES VIP                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        Cliente[] clientes = datosIniciales.getClientes();
        int cant = datosIniciales.getCantidadClientes();
        int vipCount = 0;
        
        for (int i = 0; i < cant; i++) {
            if (clientes[i] != null && clientes[i].isClienteVIP()) {
                System.out.println("  👑 " + clientes[i]);
                vipCount++;
            }
        }
        
        if (vipCount == 0) {
            System.out.println("  No hay clientes VIP registrados");
        }
        
        System.out.println();
        presionarEnter();
    }
    
    /**
     * Menú de configuración (para futuras expansiones)
     */
    private static void menuConfiguracion() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  CONFIGURACIÓN                             ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Próximamente:                                             ║");
        System.out.println("║  • Agregar/Editar Platos                                   ║");
        System.out.println("║  • Gestionar Clientes                                      ║");
        System.out.println("║  • Gestionar Repartidores                                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        presionarEnter();
    }
    
    // ==================== UTILIDADES ====================
    
    /**
     * Lee una opción del usuario
     */
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Pausa hasta que el usuario presione Enter
     */
    private static void presionarEnter() {
        System.out.print("  Presione Enter para continuar...");
        scanner.nextLine();
    }
    
    /**
     * Confirma la salida del sistema
     */
    private static boolean confirmarSalida() {
        System.out.print("\n  ¿Está seguro que desea salir? (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();
        return !respuesta.equals("S");
    }
    
    /**
     * Mensaje de despedida
     */
    private static void despedida() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║         ¡Gracias por usar nuestro sistema!                 ║");
        System.out.println("║                                                            ║");
        System.out.println("║              Restaurante UADE - 2025                       ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        scanner.close();
    }
}