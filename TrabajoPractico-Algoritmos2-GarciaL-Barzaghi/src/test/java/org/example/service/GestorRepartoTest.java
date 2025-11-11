package org.example.service;

import org.example.model.*;
import org.example.model.Enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para GestorReparto
 * Valida asignación de repartidores y entregas
 */
public class GestorRepartoTest {
    
    private GestorReparto gestorReparto;
    private GestorPedidos gestorPedidos;
    private DatosIniciales datosIniciales;
    private Cliente cliente;
    private Pedido pedido;
    private Repartidor repartidor;
    
    @BeforeEach
    void setUp() {
        gestorReparto = new GestorReparto("Sistema Test");
        gestorPedidos = new GestorPedidos();
        datosIniciales = new DatosIniciales();
        
        // Reiniciar contadores
        Cliente.reiniciarContador();
        Pedido.reiniciarContador();
        Repartidor.reiniciarContador();
        Plato.reiniciarContador();
        
        // Cargar datos
        datosIniciales.cargarTodo(gestorPedidos);
        gestorReparto.cargarRepartidores(
            datosIniciales.getRepartidores(),
            datosIniciales.getCantidadRepartidores()
        );
        
        // Crear pedido de prueba
        Cliente[] clientes = datosIniciales.getClientes();
        cliente = clientes[0];
        pedido = new Pedido(cliente, TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido.agregarPlato(1);
        pedido.setEstado(EstadoPedido.LISTO);
        
        // Obtener repartidor de prueba
        Repartidor[] repartidores = gestorReparto.getRepartidores();
        repartidor = repartidores[0];
    }
    
    @Test
    void testAltaRepartidor() {
        Repartidor nuevoRepartidor = new Repartidor("Test Repartidor", "1234-5678", "Bicicleta");
        
        boolean resultado = gestorReparto.altaRepartidor(nuevoRepartidor);
        
        assertTrue(resultado, "Debe dar de alta repartidor correctamente");
        assertTrue(gestorReparto.getCantidadRepartidores() > 0);
    }
    
    @Test
    void testBuscarRepartidorDisponible() {
        Repartidor disponible = gestorReparto.buscarRepartidorDisponible();
        
        assertNotNull(disponible, "Debe encontrar repartidor disponible");
        assertTrue(disponible.isDisponible(), "El repartidor debe estar disponible");
    }
    
    @Test
    void testAsignarPedidoAutomatico() {
        boolean resultado = gestorReparto.asignarPedidoAutomatico(pedido.getId(), pedido);
        
        assertTrue(resultado, "Debe asignar pedido automáticamente");
        assertEquals(EstadoPedido.EN_CAMINO, pedido.getEstado());
        assertFalse(repartidor.isDisponible(), "El repartidor asignado no debe estar disponible");
    }
    
    @Test
    void testAsignacionBalanceoCarga() {
        // Asignar primer pedido
        Pedido pedido1 = new Pedido(cliente, TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido1.agregarPlato(1);
        pedido1.setEstado(EstadoPedido.LISTO);
        gestorReparto.asignarPedidoAutomatico(pedido1.getId(), pedido1);
        
        // Asignar segundo pedido (debe ir a otro repartidor con menos entregas)
        Pedido pedido2 = new Pedido(cliente, TipoPedido.DOMICILIO, Prioridad.NORMAL);
        pedido2.agregarPlato(1);
        pedido2.setEstado(EstadoPedido.LISTO);
        
        Repartidor repartidorAntes = gestorReparto.buscarRepartidorMenosEntregas();
        boolean resultado = gestorReparto.asignarPedidoAutomatico(pedido2.getId(), pedido2);
        
        assertTrue(resultado, "Debe asignar segundo pedido");
        // El repartidor asignado debe ser diferente o tener menos entregas
        assertNotNull(repartidorAntes);
    }
    
    @Test
    void testCompletarEntrega() {
        gestorReparto.asignarPedidoAutomatico(pedido.getId(), pedido);
        Repartidor repartidorAsignado = gestorReparto.buscarRepartidorConPedido(pedido.getId());
        
        assertNotNull(repartidorAsignado, "Debe encontrar repartidor asignado");
        
        int entregasAntes = repartidorAsignado.getCantidadPedidosEntregados();
        boolean resultado = gestorReparto.completarEntrega(pedido.getId(), pedido, repartidorAsignado);
        
        assertTrue(resultado, "Debe completar entrega correctamente");
        assertEquals(EstadoPedido.ENTREGADO, pedido.getEstado());
        assertEquals(entregasAntes + 1, repartidorAsignado.getCantidadPedidosEntregados());
        assertTrue(repartidorAsignado.isDisponible(), "Repartidor debe estar disponible después");
    }
    
    @Test
    void testProcesarEntregaCompleta() {
        boolean resultado = gestorReparto.procesarEntregaCompleta(pedido.getId(), pedido);
        
        assertTrue(resultado, "Debe procesar entrega completa");
        assertEquals(EstadoPedido.ENTREGADO, pedido.getEstado());
    }
    
    @Test
    void testSimulacionEntrega() {
        gestorReparto.asignarPedidoAutomatico(pedido.getId(), pedido);
        Repartidor repartidorAsignado = gestorReparto.buscarRepartidorConPedido(pedido.getId());
        
        boolean resultado = gestorReparto.simularEntrega(pedido.getId(), pedido, repartidorAsignado);
        
        assertTrue(resultado, "Debe simular entrega correctamente");
    }
}

