package org.example.service;

import org.example.model.*;
import org.example.model.Enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para GestorPedidos
 * Valida alta y clasificación de pedidos
 */
public class GestorPedidosTest {
    
    private GestorPedidos gestorPedidos;
    private DatosIniciales datosIniciales;
    private Cliente clienteVIP;
    private Cliente clienteNormal;
    
    @BeforeEach
    void setUp() {
        gestorPedidos = new GestorPedidos();
        datosIniciales = new DatosIniciales();
        
        // Reiniciar contadores
        Cliente.reiniciarContador();
        Pedido.reiniciarContador();
        Plato.reiniciarContador();
        
        // Cargar datos básicos
        datosIniciales.cargarTodo(gestorPedidos);
        
        // Obtener clientes de prueba
        Cliente[] clientes = datosIniciales.getClientes();
        clienteVIP = clientes[0]; // Juan Pérez (VIP)
        clienteNormal = clientes[2]; // Carlos López (Normal)
    }
    
    @Test
    void testRegistrarPedidoVIP() {
        // Crear pedido VIP
        Pedido pedido = new Pedido(clienteVIP, TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido.agregarPlato(1); // Pizza Muzzarella
        
        // Registrar pedido
        boolean resultado = gestorPedidos.registrarPedido(pedido);
        
        assertTrue(resultado, "El pedido VIP debe registrarse correctamente");
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
        assertEquals(Prioridad.VIP, pedido.getPrioridad());
        assertFalse(gestorPedidos.colaVacia(), "La cola no debe estar vacía después de registrar");
    }
    
    @Test
    void testRegistrarPedidoNormal() {
        // Crear pedido normal
        Pedido pedido = new Pedido(clienteNormal, TipoPedido.RETIRO, Prioridad.NORMAL);
        pedido.agregarPlato(1);
        
        boolean resultado = gestorPedidos.registrarPedido(pedido);
        
        assertTrue(resultado, "El pedido normal debe registrarse correctamente");
        assertEquals(Prioridad.NORMAL, pedido.getPrioridad());
    }
    
    @Test
    void testClasificacionPorPrioridad() {
        // Limpiar cola primero
        while (!gestorPedidos.colaVacia()) {
            gestorPedidos.obtenerSiguientePedido();
        }
        
        // Crear pedido normal primero
        Pedido pedidoNormal = new Pedido(clienteNormal, TipoPedido.DOMICILIO, Prioridad.NORMAL);
        pedidoNormal.agregarPlato(1);
        gestorPedidos.registrarPedido(pedidoNormal);
        
        // Crear pedido VIP después
        Pedido pedidoVIP = new Pedido(clienteVIP, TipoPedido.DOMICILIO, Prioridad.VIP);
        pedidoVIP.agregarPlato(1);
        gestorPedidos.registrarPedido(pedidoVIP);
        
        // El VIP debe procesarse primero (mayor prioridad = menor número)
        int siguiente = gestorPedidos.obtenerSiguientePedido();
        assertEquals(pedidoVIP.getId(), siguiente, "El pedido VIP debe procesarse primero");
    }
    
    @Test
    void testValidacionPlatoInexistente() {
        Pedido pedido = new Pedido(clienteVIP, TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido.agregarPlato(999); // ID de plato que no existe
        
        boolean resultado = gestorPedidos.registrarPedido(pedido);
        
        assertFalse(resultado, "No debe registrar pedido con plato inexistente");
    }
    
    @Test
    void testValidacionPedidoSinPlatos() {
        Pedido pedido = new Pedido(clienteVIP, TipoPedido.DOMICILIO, Prioridad.VIP);
        // No agregar platos
        
        boolean resultado = gestorPedidos.registrarPedido(pedido);
        
        assertFalse(resultado, "No debe registrar pedido sin platos");
    }
    
    @Test
    void testObtenerSiguientePedido() {
        // Limpiar cola primero
        while (!gestorPedidos.colaVacia()) {
            gestorPedidos.obtenerSiguientePedido();
        }
        
        // Registrar dos pedidos
        Pedido pedido1 = new Pedido(clienteVIP, TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido1.agregarPlato(1);
        gestorPedidos.registrarPedido(pedido1);
        
        Pedido pedido2 = new Pedido(clienteNormal, TipoPedido.DOMICILIO, Prioridad.NORMAL);
        pedido2.agregarPlato(1);
        gestorPedidos.registrarPedido(pedido2);
        
        // Obtener siguiente (debe ser VIP)
        int siguiente = gestorPedidos.obtenerSiguientePedido();
        assertEquals(pedido1.getId(), siguiente);
        
        // Obtener siguiente (debe ser NORMAL)
        siguiente = gestorPedidos.obtenerSiguientePedido();
        assertEquals(pedido2.getId(), siguiente);
    }
}

