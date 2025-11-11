package org.example.service;

import org.example.model.*;
import org.example.model.Enums.*;
import org.example.implementations.DynamicLinkedListADT;
import org.example.implementations.DynamicPriorityQueueADT;
import org.example.tda.LinkedListADT;
import org.example.tda.PriorityQueueADT;

/**
 * Gestor de pedidos del restaurante
 * Maneja el registro, clasificación y validación de pedidos
 */
public class GestorPedidos {
    
    private LinkedListADT pedidos;              // Todos los pedidos del sistema
    private PriorityQueueADT colaPrioridad;     // Cola de pedidos pendientes con prioridad
    private LinkedListADT clientes;             // Clientes registrados
    private Plato[] menuPlatos;                 // Menú de platos disponibles
    private int cantidadPlatos;                 // Cantidad actual de platos en el menú
    private static final int MAX_PLATOS = 100;
    
    /**
     * Constructor
     */
    public GestorPedidos() {
        this.pedidos = new DynamicLinkedListADT();
        this.colaPrioridad = new DynamicPriorityQueueADT();
        this.clientes = new DynamicLinkedListADT();
        this.menuPlatos = new Plato[MAX_PLATOS];
        this.cantidadPlatos = 0;
    }
    
    // ==================== GESTIÓN DE CLIENTES ====================
    
    /**
     * Registra un nuevo cliente
     */
    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser null");
        }
        clientes.add(cliente.getId());
        System.out.println("✓ Cliente registrado: " + cliente.getNombre());
    }
    
    /**
     * Busca un cliente por ID
     */
    public Cliente buscarClientePorId(int idCliente, Cliente[] todosLosClientes) {
        for (int i = 0; i < todosLosClientes.length; i++) {
            if (todosLosClientes[i] != null && todosLosClientes[i].getId() == idCliente) {
                return todosLosClientes[i];
            }
        }
        return null;
    }
    
    /**
     * Obtiene la cantidad de clientes registrados
     */
    public int getCantidadClientes() {
        return clientes.size();
    }
    
    // ==================== GESTIÓN DE MENÚ ====================
    
    /**
     * Agrega un plato al menú
     */
    public boolean agregarPlatoAlMenu(Plato plato) {
        if (plato == null) {
            throw new IllegalArgumentException("El plato no puede ser null");
        }
        
        if (cantidadPlatos >= MAX_PLATOS) {
            System.out.println("✗ Error: El menú está lleno");
            return false;
        }
        
        // Verificar si ya existe un plato con ese ID
        if (buscarPlatoPorId(plato.getId()) != null) {
            System.out.println("✗ Error: Ya existe un plato con ese ID");
            return false;
        }
        
        menuPlatos[cantidadPlatos] = plato;
        cantidadPlatos++;
        System.out.println("✓ Plato agregado al menú: " + plato.getNombre());
        return true;
    }
    
    /**
     * Busca un plato por ID
     */
    public Plato buscarPlatoPorId(int idPlato) {
        for (int i = 0; i < cantidadPlatos; i++) {
            if (menuPlatos[i] != null && menuPlatos[i].getId() == idPlato) {
                return menuPlatos[i];
            }
        }
        return null;
    }
    
    /**
     * Verifica si un plato existe y está disponible
     */
    public boolean verificarPlatoDisponible(int idPlato) {
        Plato plato = buscarPlatoPorId(idPlato);
        return plato != null && plato.isDisponible();
    }
    
    /**
     * Obtiene el menú completo
     */
    public Plato[] getMenuCompleto() {
        Plato[] menu = new Plato[cantidadPlatos];
        for (int i = 0; i < cantidadPlatos; i++) {
            menu[i] = menuPlatos[i];
        }
        return menu;
    }
    
    /**
     * Muestra el menú de platos disponibles
     */
    public void mostrarMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    MENÚ DE PLATOS                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        if (cantidadPlatos == 0) {
            System.out.println("  No hay platos en el menú");
            return;
        }
        
        for (int i = 0; i < cantidadPlatos; i++) {
            if (menuPlatos[i] != null) {
                String disponible = menuPlatos[i].isDisponible() ? "✓" : "✗";
                System.out.printf("  [%d] %s %s - $%.2f - %d min%n",
                    menuPlatos[i].getId(),
                    disponible,
                    menuPlatos[i].getNombre(),
                    menuPlatos[i].getPrecio(),
                    menuPlatos[i].getTiempoPreparacion());
            }
        }
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    // ==================== GESTIÓN DE PEDIDOS ====================
    
    /**
     * Registra un nuevo pedido con validaciones
     */
    public boolean registrarPedido(Pedido pedido) {
        if (pedido == null) {
            System.out.println("✗ Error: El pedido no puede ser null");
            return false;
        }
        
        // Validar que el cliente existe
        if (pedido.getCliente() == null) {
            System.out.println("✗ Error: El pedido debe tener un cliente asociado");
            return false;
        }
        
        // Validar que el pedido tenga al menos un plato
        if (pedido.getCantidadPlatos() == 0) {
            System.out.println("✗ Error: El pedido debe tener al menos un plato");
            return false;
        }
        
        // Validar que todos los platos existen y están disponibles
        for (int i = 0; i < pedido.getCantidadPlatos(); i++) {
            int idPlato = pedido.getPlato(i);
            if (!verificarPlatoDisponible(idPlato)) {
                Plato plato = buscarPlatoPorId(idPlato);
                if (plato == null) {
                    System.out.println("✗ Error: El plato con ID " + idPlato + " no existe");
                } else {
                    System.out.println("✗ Error: El plato '" + plato.getNombre() + "' no está disponible");
                }
                return false;
            }
        }
        
        // Registrar el pedido
        pedidos.add(pedido.getId());
        
        // Agregar a la cola de prioridad (si está pendiente)
        if (pedido.getEstado() == EstadoPedido.PENDIENTE) {
            clasificarPedidoPorPrioridad(pedido);
        }
        
        // Actualizar historial del cliente
        pedido.getCliente().agregarPedidoAlHistorial(pedido.getId());
        
        // Incrementar contador de pedidos en los platos
        for (int i = 0; i < pedido.getCantidadPlatos(); i++) {
            int idPlato = pedido.getPlato(i);
            Plato plato = buscarPlatoPorId(idPlato);
            if (plato != null) {
                plato.incrementarPedidos();
            }
        }
        
        System.out.println("✓ Pedido #" + pedido.getId() + " registrado exitosamente");
        System.out.println("  Cliente: " + pedido.getCliente().getNombre());
        System.out.println("  Tipo: " + pedido.getTipoPedido());
        System.out.println("  Prioridad: " + pedido.getPrioridad());
        System.out.println("  Platos: " + pedido.getCantidadPlatos());
        
        return true;
    }
    
    /**
     * Clasifica un pedido en la cola de prioridad
     */
    private void clasificarPedidoPorPrioridad(Pedido pedido) {
        // Agregar a la cola con prioridad
        // VIP = 1 (mayor prioridad), NORMAL = 2 (menor prioridad)
        int prioridad = pedido.getPrioridadNumerica();
        colaPrioridad.add(pedido.getId(), prioridad);
        
        System.out.println("  → Agregado a cola de prioridad (nivel " + prioridad + ")");
    }
    
    /**
     * Obtiene el siguiente pedido de la cola de prioridad
     */
    public int obtenerSiguientePedido() {
        if (colaPrioridad.isEmpty()) {
            System.out.println("✗ No hay pedidos en la cola");
            return -1;
        }
        
        int idPedido = colaPrioridad.getElement();
        int prioridad = colaPrioridad.getPriority();
        colaPrioridad.remove();
        
        System.out.println("✓ Siguiente pedido: #" + idPedido + " (Prioridad: " + prioridad + ")");
        return idPedido;
    }
    
    /**
     * Busca un pedido por ID
     */
    public Pedido buscarPedidoPorId(int idPedido, Pedido[] todosPedidos) {
        for (int i = 0; i < todosPedidos.length; i++) {
            if (todosPedidos[i] != null && todosPedidos[i].getId() == idPedido) {
                return todosPedidos[i];
            }
        }
        return null;
    }
    
    /**
     * Obtiene la cantidad de pedidos registrados
     */
    public int getCantidadPedidos() {
        return pedidos.size();
    }
    
    /**
     * Obtiene la cantidad de pedidos en cola de prioridad
     */
    public int getCantidadPedidosEnCola() {
        int contador = 0;
        PriorityQueueADT temp = new DynamicPriorityQueueADT();
        
        while (!colaPrioridad.isEmpty()) {
            int id = colaPrioridad.getElement();
            int prioridad = colaPrioridad.getPriority();
            temp.add(id, prioridad);
            colaPrioridad.remove();
            contador++;
        }
        
        // Restaurar la cola
        while (!temp.isEmpty()) {
            int id = temp.getElement();
            int prioridad = temp.getPriority();
            colaPrioridad.add(id, prioridad);
            temp.remove();
        }
        
        return contador;
    }
    
    /**
     * Verifica si la cola de prioridad está vacía
     */
    public boolean colaVacia() {
        return colaPrioridad.isEmpty();
    }
    
    // ==================== ESTADÍSTICAS ====================
    
    /**
     * Muestra estadísticas del gestor de pedidos
     */
    public void mostrarEstadisticas() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              ESTADÍSTICAS DEL SISTEMA                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("  Clientes registrados: " + getCantidadClientes());
        System.out.println("  Platos en menú: " + cantidadPlatos);
        System.out.println("  Pedidos totales: " + getCantidadPedidos());
        System.out.println("  Pedidos en cola: " + getCantidadPedidosEnCola());
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
    
    /**
     * Muestra platos más populares
     */
    public void mostrarPlatosPopulares() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              PLATOS MÁS POPULARES                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        if (cantidadPlatos == 0) {
            System.out.println("  No hay platos en el menú");
            return;
        }
        
        // Calcular total de pedidos
        int totalPedidos = 0;
        for (int i = 0; i < cantidadPlatos; i++) {
            if (menuPlatos[i] != null) {
                totalPedidos += menuPlatos[i].getCantidadPedidos();
            }
        }
        
        // Ordenar por popularidad (bubble sort simple)
        Plato[] ordenados = new Plato[cantidadPlatos];
        for (int i = 0; i < cantidadPlatos; i++) {
            ordenados[i] = menuPlatos[i];
        }
        
        for (int i = 0; i < cantidadPlatos - 1; i++) {
            for (int j = 0; j < cantidadPlatos - i - 1; j++) {
                if (ordenados[j] != null && ordenados[j + 1] != null) {
                    if (ordenados[j].getCantidadPedidos() < ordenados[j + 1].getCantidadPedidos()) {
                        Plato temp = ordenados[j];
                        ordenados[j] = ordenados[j + 1];
                        ordenados[j + 1] = temp;
                    }
                }
            }
        }
        
        // Mostrar top 5
        int limite = Math.min(5, cantidadPlatos);
        for (int i = 0; i < limite; i++) {
            if (ordenados[i] != null && ordenados[i].getCantidadPedidos() > 0) {
                double popularidad = ordenados[i].calcularPopularidad(totalPedidos);
                System.out.printf("  %d. %s - %d pedidos (%.1f%%)%n",
                    (i + 1),
                    ordenados[i].getNombre(),
                    ordenados[i].getCantidadPedidos(),
                    popularidad);
            }
        }
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
}

