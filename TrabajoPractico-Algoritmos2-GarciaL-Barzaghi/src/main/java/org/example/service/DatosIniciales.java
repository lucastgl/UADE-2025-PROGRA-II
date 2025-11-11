package org.example.service;

import org.example.model.*;
import org.example.model.Enums.*;

/**
 * Clase para cargar datos iniciales del sistema
 */
public class DatosIniciales {
    
    private static final int MAX_CLIENTES = 50;
    private static final int MAX_REPARTIDORES = 20;
    private static final int MAX_PEDIDOS = 100;
    
    private Cliente[] clientes;
    private Repartidor[] repartidores;
    private Pedido[] pedidos;
    
    private int cantidadClientes;
    private int cantidadRepartidores;
    private int cantidadPedidos;
    
    public DatosIniciales() {
        this.clientes = new Cliente[MAX_CLIENTES];
        this.repartidores = new Repartidor[MAX_REPARTIDORES];
        this.pedidos = new Pedido[MAX_PEDIDOS];
        this.cantidadClientes = 0;
        this.cantidadRepartidores = 0;
        this.cantidadPedidos = 0;
    }
    
    /**
     * Carga todos los datos iniciales
     */
    public void cargarTodo(GestorPedidos gestorPedidos) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            CARGANDO DATOS INICIALES...                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        cargarMenu(gestorPedidos);
        cargarClientes(gestorPedidos);
        cargarRepartidores();
        cargarPedidos(gestorPedidos);
        
        System.out.println("\n✓ Datos iniciales cargados exitosamente\n");
    }
    
    /**
     * Carga el menú de platos
     */
    private void cargarMenu(GestorPedidos gestorPedidos) {
        System.out.println("→ Cargando menú de platos...");
        
        gestorPedidos.agregarPlatoAlMenu(new Plato("Pizza Muzzarella", 15, 450.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Pizza Napolitana", 15, 500.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Empanadas de Carne x6", 10, 280.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Empanadas de Pollo x6", 10, 280.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Milanesa con Papas Fritas", 20, 650.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Milanesa Napolitana", 25, 750.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Hamburguesa Completa", 12, 550.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Hamburguesa con Cheddar", 12, 600.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Ensalada Caesar", 8, 380.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Papas Fritas", 8, 250.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Nuggets x10", 12, 420.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Coca-Cola 1.5L", 2, 180.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Agua Mineral 500ml", 1, 120.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Flan con Dulce de Leche", 5, 280.0));
        gestorPedidos.agregarPlatoAlMenu(new Plato("Helado 1kg", 3, 520.0));
        
        System.out.println("  ✓ Menú cargado (15 platos)\n");
    }
    
    /**
     * Carga clientes de prueba
     */
    private void cargarClientes(GestorPedidos gestorPedidos) {
        System.out.println("→ Cargando clientes...");
        
        // Clientes VIP
        Cliente c1 = new Cliente("Juan Pérez", "1234-5678", "Av. Corrientes 1234", "juan@mail.com");
        c1.setClienteVIP(true);
        agregarCliente(c1, gestorPedidos);
        
        Cliente c2 = new Cliente("María González", "8765-4321", "Av. Santa Fe 5678", "maria@mail.com");
        c2.setClienteVIP(true);
        agregarCliente(c2, gestorPedidos);
        
        // Clientes regulares
        agregarCliente(new Cliente("Carlos López", "5555-1111", "Calle Falsa 123", "carlos@mail.com"), gestorPedidos);
        agregarCliente(new Cliente("Ana Martínez", "5555-2222", "Av. Libertador 999", "ana@mail.com"), gestorPedidos);
        agregarCliente(new Cliente("Pedro Ramírez", "5555-3333", "Calle 25 de Mayo 456", "pedro@mail.com"), gestorPedidos);
        agregarCliente(new Cliente("Laura Fernández", "5555-4444", "Av. Belgrano 777", "laura@mail.com"), gestorPedidos);
        agregarCliente(new Cliente("Diego Torres", "5555-5555", "Calle San Martín 321", "diego@mail.com"), gestorPedidos);
        agregarCliente(new Cliente("Sofía Ruiz", "5555-6666", "Av. 9 de Julio 888", "sofia@mail.com"), gestorPedidos);
        
        System.out.println("  ✓ Clientes cargados (8 clientes, 2 VIP)\n");
    }
    
    /**
     * Carga repartidores
     */
    private void cargarRepartidores() {
        System.out.println("→ Cargando repartidores...");
        
        agregarRepartidor(new Repartidor("Roberto García", "1111-1111", "Moto"));
        agregarRepartidor(new Repartidor("Lucía Sánchez", "2222-2222", "Moto"));
        agregarRepartidor(new Repartidor("Fernando Díaz", "3333-3333", "Bicicleta"));
        agregarRepartidor(new Repartidor("Gabriela Moreno", "4444-4444", "Moto"));
        agregarRepartidor(new Repartidor("Martín Castro", "5555-5555", "Auto"));
        agregarRepartidor(new Repartidor("Valentina Rojas", "6666-6666", "Bicicleta"));
        agregarRepartidor(new Repartidor("Nicolás Vega", "7777-7777", "Moto"));
        agregarRepartidor(new Repartidor("Carolina Méndez", "8888-8888", "Moto"));
        agregarRepartidor(new Repartidor("Sebastián Ortiz", "9999-9999", "Bicicleta"));
        agregarRepartidor(new Repartidor("Florencia Silva", "0000-0000", "Auto"));
        
        System.out.println("  ✓ Repartidores cargados (10 repartidores)\n");
    }
    
    /**
     * Carga pedidos de ejemplo
     */
    private void cargarPedidos(GestorPedidos gestorPedidos) {
        System.out.println("→ Cargando pedidos de ejemplo...");
        
        // Pedido 1 - VIP
        Cliente cliente1 = clientes[0]; // Juan Pérez (VIP)
        Pedido pedido1 = new Pedido(cliente1, TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido1.agregarPlato(1); // Pizza Muzzarella
        pedido1.agregarPlato(10); // Papas Fritas
        pedido1.agregarPlato(12); // Coca-Cola
        agregarPedido(pedido1);
        gestorPedidos.registrarPedido(pedido1);
        
        // Pedido 2 - VIP
        Cliente cliente2 = clientes[1]; // María González (VIP)
        Pedido pedido2 = new Pedido(cliente2, TipoPedido.RETIRO, Prioridad.VIP);
        pedido2.agregarPlato(5); // Milanesa con Papas
        pedido2.agregarPlato(9); // Ensalada Caesar
        agregarPedido(pedido2);
        gestorPedidos.registrarPedido(pedido2);
        
        // Pedido 3 - Normal
        Cliente cliente3 = clientes[2]; // Carlos López
        Pedido pedido3 = new Pedido(cliente3, TipoPedido.DOMICILIO, Prioridad.NORMAL);
        pedido3.agregarPlato(7); // Hamburguesa Completa
        pedido3.agregarPlato(10); // Papas Fritas
        pedido3.agregarPlato(12); // Coca-Cola
        agregarPedido(pedido3);
        gestorPedidos.registrarPedido(pedido3);
        
        // Pedido 4 - Normal
        Cliente cliente4 = clientes[3]; // Ana Martínez
        Pedido pedido4 = new Pedido(cliente4, TipoPedido.DOMICILIO, Prioridad.NORMAL);
        pedido4.agregarPlato(3); // Empanadas de Carne
        pedido4.agregarPlato(4); // Empanadas de Pollo
        pedido4.agregarPlato(13); // Agua Mineral
        agregarPedido(pedido4);
        gestorPedidos.registrarPedido(pedido4);
        
        // Pedido 5 - Normal
        Cliente cliente5 = clientes[4]; // Pedro Ramírez
        Pedido pedido5 = new Pedido(cliente5, TipoPedido.RETIRO, Prioridad.NORMAL);
        pedido5.agregarPlato(2); // Pizza Napolitana
        pedido5.agregarPlato(14); // Flan
        agregarPedido(pedido5);
        gestorPedidos.registrarPedido(pedido5);
        
        System.out.println("  ✓ Pedidos cargados (5 pedidos, 2 VIP)\n");
    }
    
    // Métodos auxiliares para agregar a los arrays
    
    private void agregarCliente(Cliente cliente, GestorPedidos gestor) {
        if (cantidadClientes < MAX_CLIENTES) {
            clientes[cantidadClientes] = cliente;
            cantidadClientes++;
            gestor.registrarCliente(cliente);
        }
    }
    
    private void agregarRepartidor(Repartidor repartidor) {
        if (cantidadRepartidores < MAX_REPARTIDORES) {
            repartidores[cantidadRepartidores] = repartidor;
            cantidadRepartidores++;
        }
    }
    
    private void agregarPedido(Pedido pedido) {
        if (cantidadPedidos < MAX_PEDIDOS) {
            pedidos[cantidadPedidos] = pedido;
            cantidadPedidos++;
        }
    }
    
    // Getters
    
    public Cliente[] getClientes() {
        return clientes;
    }
    
    public Repartidor[] getRepartidores() {
        return repartidores;
    }
    
    public Pedido[] getPedidos() {
        return pedidos;
    }
    
    public int getCantidadClientes() {
        return cantidadClientes;
    }
    
    public int getCantidadRepartidores() {
        return cantidadRepartidores;
    }
    
    public int getCantidadPedidos() {
        return cantidadPedidos;
    }
}

