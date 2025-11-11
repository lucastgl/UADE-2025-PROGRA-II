package org.example.service;

import org.example.model.*;
import org.example.model.Enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para GestorCocina
 * Valida preparación de pedidos en cocina
 */
public class GestorCocinaTest {
    
    private GestorCocina gestorCocina;
    private GestorPedidos gestorPedidos;
    private DatosIniciales datosIniciales;
    private Cliente cliente;
    private Pedido pedido;
    
    @BeforeEach
    void setUp() {
        gestorCocina = new GestorCocina("Cocina Test");
        gestorPedidos = new GestorPedidos();
        datosIniciales = new DatosIniciales();
        
        // Reiniciar contadores
        Cliente.reiniciarContador();
        Pedido.reiniciarContador();
        Plato.reiniciarContador();
        
        // Cargar datos
        datosIniciales.cargarTodo(gestorPedidos);
        
        // Crear pedido de prueba
        Cliente[] clientes = datosIniciales.getClientes();
        cliente = clientes[0];
        pedido = new Pedido(cliente, TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido.agregarPlato(1); // Pizza Muzzarella
    }
    
    @Test
    void testAgregarPedidoAPreparacion() {
        boolean resultado = gestorCocina.agregarPedidoAPreparacion(pedido.getId(), pedido);
        
        assertTrue(resultado, "Debe agregar pedido a cola de preparación");
        assertEquals(EstadoPedido.EN_PREPARACION, pedido.getEstado());
        assertFalse(gestorCocina.colaVacia(), "La cola no debe estar vacía");
        assertEquals(1, gestorCocina.getCantidadEnCola());
    }
    
    @Test
    void testExtraerSiguientePedido() {
        gestorCocina.agregarPedidoAPreparacion(pedido.getId(), pedido);
        
        int idExtraido = gestorCocina.extraerSiguientePedido();
        
        assertEquals(pedido.getId(), idExtraido, "Debe extraer el pedido correcto");
        assertTrue(gestorCocina.colaVacia(), "La cola debe estar vacía después de extraer");
    }
    
    @Test
    void testIniciarPreparacion() {
        gestorCocina.agregarPedidoAPreparacion(pedido.getId(), pedido);
        int idPedido = gestorCocina.extraerSiguientePedido();
        
        boolean resultado = gestorCocina.iniciarPreparacion(idPedido, pedido);
        
        assertTrue(resultado, "Debe iniciar preparación correctamente");
        assertTrue(gestorCocina.hayPedidoEnPreparacion(), "Debe haber un pedido en preparación");
        assertEquals(idPedido, gestorCocina.getPedidoEnPreparacionId());
    }
    
    @Test
    void testFinalizarPreparacion() {
        gestorCocina.agregarPedidoAPreparacion(pedido.getId(), pedido);
        int idPedido = gestorCocina.extraerSiguientePedido();
        gestorCocina.iniciarPreparacion(idPedido, pedido);
        gestorCocina.prepararPedido(pedido, gestorPedidos);
        
        boolean resultado = gestorCocina.finalizarPreparacion(pedido);
        
        assertTrue(resultado, "Debe finalizar preparación correctamente");
        assertEquals(EstadoPedido.LISTO, pedido.getEstado());
        assertFalse(gestorCocina.hayPedidoEnPreparacion(), "No debe haber pedido en preparación");
    }
    
    @Test
    void testDeterminarDestinoDomicilio() {
        pedido.setEstado(EstadoPedido.LISTO);
        String destino = gestorCocina.determinarDestino(pedido);
        
        assertEquals("REPARTO", destino, "Pedido DOMICILIO debe ir a REPARTO");
        assertTrue(gestorCocina.requiereReparto(pedido));
    }
    
    @Test
    void testDeterminarDestinoRetiro() {
        Pedido pedidoRetiro = new Pedido(cliente, TipoPedido.RETIRO, Prioridad.NORMAL);
        pedidoRetiro.setEstado(EstadoPedido.LISTO);
        String destino = gestorCocina.determinarDestino(pedidoRetiro);
        
        assertEquals("RETIRO", destino, "Pedido RETIRO debe estar listo para retiro");
        assertFalse(gestorCocina.requiereReparto(pedidoRetiro));
    }
    
    @Test
    void testProcesarPedidoCompleto() {
        gestorCocina.agregarPedidoAPreparacion(pedido.getId(), pedido);
        int idPedido = gestorCocina.extraerSiguientePedido();
        
        boolean resultado = gestorCocina.procesarPedidoCompleto(idPedido, pedido, gestorPedidos);
        
        assertTrue(resultado, "Debe procesar pedido completo correctamente");
        assertEquals(EstadoPedido.LISTO, pedido.getEstado());
    }
}

