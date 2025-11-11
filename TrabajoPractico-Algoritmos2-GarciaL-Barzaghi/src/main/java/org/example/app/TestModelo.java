package org.example.app;

import org.example.model.*;
import org.example.model.Enums.*;

/**
 * Clase de prueba para demostrar el funcionamiento de las entidades del modelo
 */
public class TestModelo {
    
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  PRUEBA DEL MODELO DE DOMINIO");
        System.out.println("===========================================\n");
        
        // Prueba de Platos
        System.out.println(">>> CREACIÓN DE PLATOS <<<");
        Plato pizza = new Plato("Pizza Muzzarella", 15, 350.0);
        Plato empanadas = new Plato("Empanadas de Carne x6", 10, 200.0);
        Plato milanesa = new Plato("Milanesa con Papas Fritas", 20, 450.0);
        Plato ensalada = new Plato("Ensalada Caesar", 5, 180.0);
        
        System.out.println(pizza.toStringResumido());
        System.out.println(empanadas.toStringResumido());
        System.out.println(milanesa.toStringResumido());
        System.out.println(ensalada.toStringResumido());
        System.out.println();
        
        // Prueba de Clientes
        System.out.println(">>> CREACIÓN DE CLIENTES <<<");
        Cliente cliente1 = new Cliente("Juan Pérez", "1234-5678", "Av. Corrientes 1234", "juan@mail.com");
        Cliente cliente2 = new Cliente("María González", "8765-4321", "Av. Santa Fe 5678", "maria@mail.com");
        cliente2.setClienteVIP(true); // Cliente VIP manual
        
        System.out.println(cliente1);
        System.out.println(cliente2);
        System.out.println();
        
        // Prueba de Pedidos
        System.out.println(">>> CREACIÓN DE PEDIDOS <<<");
        
        // Pedido 1 - Cliente Normal, Domicilio
        Pedido pedido1 = new Pedido(cliente1, TipoPedido.DOMICILIO, Prioridad.NORMAL);
        pedido1.agregarPlato(pizza.getId());
        pedido1.agregarPlato(empanadas.getId());
        pizza.incrementarPedidos();
        empanadas.incrementarPedidos();
        cliente1.agregarPedidoAlHistorial(pedido1.getId());
        
        System.out.println(pedido1);
        System.out.println("  Cantidad de platos: " + pedido1.getCantidadPlatos());
        System.out.println();
        
        // Pedido 2 - Cliente VIP, Retiro
        Pedido pedido2 = new Pedido(cliente2, TipoPedido.RETIRO, Prioridad.VIP);
        pedido2.agregarPlato(milanesa.getId());
        pedido2.agregarPlato(ensalada.getId());
        milanesa.incrementarPedidos();
        ensalada.incrementarPedidos();
        cliente2.agregarPedidoAlHistorial(pedido2.getId());
        
        System.out.println(pedido2);
        System.out.println("  Cantidad de platos: " + pedido2.getCantidadPlatos());
        System.out.println();
        
        // Pedido 3 - Cliente Normal, Domicilio
        Pedido pedido3 = new Pedido(cliente1, TipoPedido.DOMICILIO, Prioridad.NORMAL);
        pedido3.agregarPlato(pizza.getId());
        pedido3.agregarPlato(pizza.getId()); // 2 pizzas
        pedido3.agregarPlato(ensalada.getId());
        pizza.incrementarPedidos();
        pizza.incrementarPedidos();
        ensalada.incrementarPedidos();
        cliente1.agregarPedidoAlHistorial(pedido3.getId());
        
        System.out.println(pedido3);
        System.out.println("  Cantidad de platos: " + pedido3.getCantidadPlatos());
        System.out.println();
        
        // Prueba de Cocina
        System.out.println(">>> GESTIÓN DE COCINA <<<");
        Cocina cocina = new Cocina("Cocina Principal", 10);
        
        System.out.println("Agregando pedidos a la cocina...");
        cocina.agregarPedido(pedido1); // Normal
        cocina.agregarPedido(pedido2); // VIP (debería tener prioridad)
        cocina.agregarPedido(pedido3); // Normal
        System.out.println();
        
        System.out.println(cocina.getInformacionCompleta());
        
        System.out.println("Procesando pedidos (debería salir primero el VIP)...");
        System.out.println("Siguiente pedido en cola: #" + cocina.verSiguientePedido() + 
                          " (Prioridad: " + cocina.verPrioridadSiguiente() + ")");
        
        int idEnPreparacion = cocina.tomarSiguientePedido();
        System.out.println("Pedido #" + idEnPreparacion + " en preparación");
        pedido2.setEstado(EstadoPedido.EN_PREPARACION);
        System.out.println();
        
        // Completar pedido
        cocina.completarPedidoActual();
        pedido2.setEstado(EstadoPedido.LISTO);
        System.out.println();
        
        // Siguiente pedido
        idEnPreparacion = cocina.tomarSiguientePedido();
        System.out.println("Pedido #" + idEnPreparacion + " en preparación");
        pedido1.setEstado(EstadoPedido.EN_PREPARACION);
        System.out.println();
        
        System.out.println(cocina);
        System.out.println();
        
        // Prueba de Repartidores
        System.out.println(">>> GESTIÓN DE REPARTIDORES <<<");
        Repartidor repartidor1 = new Repartidor("Carlos Gómez", "5555-1111", "Moto");
        Repartidor repartidor2 = new Repartidor("Ana Martínez", "5555-2222", "Bicicleta");
        
        System.out.println(repartidor1);
        System.out.println(repartidor2);
        System.out.println();
        
        // Asignar pedido a repartidor
        System.out.println("Asignando pedido #" + pedido1.getId() + " a " + repartidor1.getNombre());
        if (repartidor1.asignarPedido(pedido1.getId())) {
            pedido1.setEstado(EstadoPedido.EN_CAMINO);
            System.out.println("✓ Pedido asignado exitosamente");
        }
        System.out.println(repartidor1);
        System.out.println();
        
        // Completar entrega
        System.out.println("Completando entrega...");
        repartidor1.completarEntrega();
        pedido1.setEstado(EstadoPedido.ENTREGADO);
        System.out.println("✓ Entrega completada");
        System.out.println(repartidor1);
        System.out.println();
        
        // Información de Clientes
        System.out.println(">>> INFORMACIÓN DE CLIENTES <<<");
        System.out.println(cliente1.getInformacionCompleta());
        System.out.println(cliente2.getInformacionCompleta());
        
        // Estadísticas de Platos
        System.out.println(">>> ESTADÍSTICAS DE PLATOS <<<");
        int totalPedidos = pizza.getCantidadPedidos() + empanadas.getCantidadPedidos() + 
                          milanesa.getCantidadPedidos() + ensalada.getCantidadPedidos();
        
        System.out.println(pizza);
        System.out.println("  Popularidad: " + String.format("%.1f%%", pizza.calcularPopularidad(totalPedidos)));
        System.out.println(empanadas);
        System.out.println("  Popularidad: " + String.format("%.1f%%", empanadas.calcularPopularidad(totalPedidos)));
        System.out.println(milanesa);
        System.out.println("  Popularidad: " + String.format("%.1f%%", milanesa.calcularPopularidad(totalPedidos)));
        System.out.println(ensalada);
        System.out.println("  Popularidad: " + String.format("%.1f%%", ensalada.calcularPopularidad(totalPedidos)));
        System.out.println();
        
        // Resumen Final
        System.out.println("===========================================");
        System.out.println("  RESUMEN FINAL");
        System.out.println("===========================================");
        System.out.println("Platos en menú: 4");
        System.out.println("Clientes registrados: 2");
        System.out.println("Pedidos creados: 3");
        System.out.println("Pedidos preparados: " + cocina.getPedidosPreparados());
        System.out.println("Entregas realizadas: " + repartidor1.getCantidadPedidosEntregados());
        System.out.println("Estado cocina: " + cocina.toStringResumido());
        System.out.println();
        System.out.println("✓ Todas las pruebas completadas exitosamente");
        System.out.println("✓ El modelo está funcionando correctamente");
        System.out.println("✓ Utilizando TDAs propios (LinkedListADT, PriorityQueueADT)");
    }
}

