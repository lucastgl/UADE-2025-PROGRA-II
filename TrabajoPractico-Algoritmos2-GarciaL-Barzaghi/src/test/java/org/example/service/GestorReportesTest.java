package org.example.service;

import org.example.model.*;
import org.example.model.Enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para GestorReportes
 * Valida generación de reportes
 */
public class GestorReportesTest {
    
    private GestorReportes gestorReportes;
    private GestorPedidos gestorPedidos;
    private GestorCocina gestorCocina;
    private GestorReparto gestorReparto;
    private DatosIniciales datosIniciales;
    
    @BeforeEach
    void setUp() {
        gestorPedidos = new GestorPedidos();
        gestorCocina = new GestorCocina("Cocina Test");
        gestorReparto = new GestorReparto("Reparto Test");
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
        
        gestorReportes = new GestorReportes(gestorPedidos, gestorCocina, gestorReparto, datosIniciales);
    }
    
    @Test
    void testReportePedidosPendientes() {
        // Crear pedido pendiente
        Cliente[] clientes = datosIniciales.getClientes();
        Pedido pedido = new Pedido(clientes[0], TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido.agregarPlato(1);
        pedido.setEstado(EstadoPedido.PENDIENTE);
        
        // El reporte no debe lanzar excepciones
        assertDoesNotThrow(() -> {
            gestorReportes.reportePedidosPendientes();
        }, "El reporte de pedidos pendientes debe ejecutarse sin errores");
    }
    
    @Test
    void testReportePedidosFinalizados() {
        // Crear pedido finalizado
        Cliente[] clientes = datosIniciales.getClientes();
        Pedido pedido = new Pedido(clientes[0], TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido.agregarPlato(1);
        pedido.setEstado(EstadoPedido.ENTREGADO);
        
        assertDoesNotThrow(() -> {
            gestorReportes.reportePedidosFinalizados();
        }, "El reporte de pedidos finalizados debe ejecutarse sin errores");
    }
    
    @Test
    void testReportePedidosPorRepartidor() {
        assertDoesNotThrow(() -> {
            gestorReportes.reportePedidosPorRepartidor();
        }, "El reporte de pedidos por repartidor debe ejecutarse sin errores");
    }
    
    @Test
    void testReporteClienteConMasPedidos() {
        // Agregar pedidos a un cliente
        Cliente[] clientes = datosIniciales.getClientes();
        Cliente cliente = clientes[0];
        
        // Agregar múltiples pedidos al historial
        cliente.agregarPedidoAlHistorial(1);
        cliente.agregarPedidoAlHistorial(2);
        cliente.agregarPedidoAlHistorial(3);
        
        assertDoesNotThrow(() -> {
            gestorReportes.reporteClienteConMasPedidos();
        }, "El reporte de cliente con más pedidos debe ejecutarse sin errores");
    }
    
    @Test
    void testReportePlatosMasPedidos() {
        // Crear pedidos con diferentes platos
        Cliente[] clientes = datosIniciales.getClientes();
        Pedido pedido1 = new Pedido(clientes[0], TipoPedido.DOMICILIO, Prioridad.VIP);
        pedido1.agregarPlato(1); // Pizza Muzzarella
        pedido1.agregarPlato(1); // Duplicado
        pedido1.agregarPlato(2); // Pizza Napolitana
        
        assertDoesNotThrow(() -> {
            gestorReportes.reportePlatosMasPedidos();
        }, "El reporte de platos más pedidos debe ejecutarse sin errores");
    }
    
    @Test
    void testReporteGeneral() {
        assertDoesNotThrow(() -> {
            gestorReportes.reporteGeneral();
        }, "El reporte general debe ejecutarse sin errores");
    }
    
    @Test
    void testReporteConDatosVacios() {
        // Crear gestor con datos vacíos
        DatosIniciales datosVacios = new DatosIniciales();
        GestorPedidos pedidosVacio = new GestorPedidos();
        GestorCocina cocinaVacia = new GestorCocina("Cocina Vacia");
        GestorReparto repartoVacio = new GestorReparto("Reparto Vacio");
        GestorReportes reportesVacio = new GestorReportes(
            pedidosVacio, cocinaVacia, repartoVacio, datosVacios
        );
        
        // Los reportes deben manejarse correctamente con datos vacíos
        assertDoesNotThrow(() -> {
            reportesVacio.reportePedidosPendientes();
            reportesVacio.reportePedidosFinalizados();
            reportesVacio.reportePedidosPorRepartidor();
            reportesVacio.reporteClienteConMasPedidos();
            reportesVacio.reportePlatosMasPedidos();
            reportesVacio.reporteGeneral();
        }, "Los reportes deben manejarse correctamente con datos vacíos");
    }
}

