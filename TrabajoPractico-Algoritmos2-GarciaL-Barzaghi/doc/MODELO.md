# 📚 Documentación del Modelo de Dominio

## Descripción General

El modelo de dominio está compuesto por 6 clases principales y 3 enumeraciones, todas implementadas utilizando **TDAs propios** (sin estructuras nativas de Java).

---

## 🎯 Enumeraciones

### `TipoPedido`
Define el tipo de entrega del pedido.

**Valores:**
- `DOMICILIO` - Pedido para envío a domicilio
- `RETIRO` - Pedido para retiro en el local

**Atributos:**
- `descripcion` (String)

**Métodos:**
- `getDescripcion()` - Obtiene la descripción legible
- `toString()` - Retorna la descripción

---

### `Prioridad`
Define la prioridad del pedido en la cola de cocina.

**Valores:**
- `VIP` (nivel 1) - Clientes VIP con atención prioritaria
- `NORMAL` (nivel 2) - Clientes regulares

**Atributos:**
- `nivel` (int) - Valor numérico para ordenamiento (menor = mayor prioridad)
- `descripcion` (String)

**Métodos:**
- `getNivel()` - Obtiene el nivel numérico
- `getDescripcion()` - Obtiene la descripción
- `toString()` - Retorna la descripción

---

### `EstadoPedido`
Define los posibles estados en el ciclo de vida de un pedido.

**Valores:**
- `PENDIENTE` - Pedido recibido, esperando procesamiento
- `EN_PREPARACION` - Pedido siendo preparado en cocina
- `LISTO` - Pedido listo para entrega o retiro
- `EN_CAMINO` - Pedido en ruta de entrega
- `ENTREGADO` - Pedido entregado al cliente
- `CANCELADO` - Pedido cancelado

**Atributos:**
- `descripcion` (String)

**Métodos:**
- `getDescripcion()` - Obtiene la descripción
- `toString()` - Retorna la descripción

---

## 👤 Cliente

Representa un cliente del restaurante.

### Atributos
- `id` (int, final) - Identificador único autogenerado
- `nombre` (String) - Nombre del cliente
- `telefono` (String) - Teléfono de contacto
- `direccion` (String) - Dirección de entrega
- `email` (String) - Correo electrónico
- `historialPedidos` (LinkedListADT) - Lista de IDs de pedidos realizados
- `clienteVIP` (boolean) - Estado VIP del cliente

### Constructores
```java
Cliente(String nombre, String telefono, String direccion, String email)
Cliente(String nombre, String telefono)
Cliente(String nombre)
```

### Métodos Principales
- `agregarPedidoAlHistorial(int idPedido)` - Registra un pedido en el historial
- `getCantidadPedidos()` - Retorna cantidad de pedidos realizados
- `calificaParaVIP()` - Verifica si tiene ≥10 pedidos
- `actualizarEstadoVIP()` - Actualiza automáticamente el estado VIP
- `getInformacionCompleta()` - Retorna información detallada formateada

### TDA Utilizado
- **LinkedListADT** (DynamicLinkedListADT) para `historialPedidos`

---

## 🍽️ Plato

Representa un plato del menú del restaurante.

### Atributos
- `id` (int, final) - Identificador único autogenerado
- `nombre` (String) - Nombre del plato
- `tiempoPreparacion` (int) - Tiempo de preparación en minutos
- `cantidadPedidos` (int) - Contador de veces que se ha pedido
- `precio` (double) - Precio del plato
- `disponible` (boolean) - Disponibilidad del plato

### Constructores
```java
Plato(String nombre, int tiempoPreparacion, double precio)
Plato(String nombre, int tiempoPreparacion)
```

### Métodos Principales
- `incrementarPedidos()` - Aumenta el contador cuando se pide
- `decrementarPedidos()` - Decrementa el contador (ej: cancelación)
- `calcularPopularidad(int totalPedidos)` - Calcula porcentaje de popularidad
- `toStringResumido()` - Formato compacto para menús

### Funcionalidades
- Control de disponibilidad
- Contador de popularidad
- Validaciones de tiempo y precio

---

## 📦 Pedido

Representa un pedido realizado por un cliente.

### Atributos
- `id` (int, final) - Identificador único autogenerado
- `cliente` (Cliente) - Cliente que realiza el pedido
- `platos` (LinkedListADT) - Lista de IDs de platos del pedido
- `tipoPedido` (TipoPedido) - DOMICILIO o RETIRO
- `prioridad` (Prioridad) - VIP o NORMAL
- `estado` (EstadoPedido) - Estado actual del pedido
- `timestamp` (long) - Marca temporal de creación

### Constructores
```java
Pedido(Cliente cliente, TipoPedido tipoPedido, Prioridad prioridad)
Pedido(Cliente cliente, TipoPedido tipoPedido) // Prioridad NORMAL por defecto
```

### Métodos Principales
- `agregarPlato(int idPlato)` - Agrega un plato al pedido
- `eliminarPlato(int indice)` - Elimina un plato por índice
- `getCantidadPlatos()` - Retorna cantidad de platos
- `calcularTiempoPreparacionTotal(Plato[] menuPlatos)` - Calcula tiempo total
- `getPrioridadNumerica()` - Obtiene valor numérico para ordenamiento

### TDA Utilizado
- **LinkedListADT** (DynamicLinkedListADT) para `platos`

### Funcionalidades
- Gestión dinámica de platos
- Cálculo automático de tiempos
- Sistema de prioridades
- Trazabilidad completa

---

## 🏍️ Repartidor

Representa un repartidor del restaurante.

### Atributos
- `id` (int, final) - Identificador único autogenerado
- `nombre` (String) - Nombre del repartidor
- `telefono` (String) - Teléfono de contacto
- `vehiculo` (String) - Tipo de vehículo (Moto, Bicicleta, Auto)
- `disponible` (boolean) - Estado de disponibilidad
- `pedidosEntregados` (LinkedListADT) - Historial de entregas (IDs)
- `pedidoActualId` (int) - ID del pedido en curso (-1 si no tiene)

### Constructores
```java
Repartidor(String nombre, String telefono, String vehiculo)
Repartidor(String nombre, String telefono)
Repartidor(String nombre)
```

### Métodos Principales
- `asignarPedido(int idPedido)` - Asigna un pedido (retorna boolean)
- `completarEntrega()` - Marca entrega como completada
- `cancelarPedidoActual()` - Cancela y libera el pedido actual
- `getCantidadPedidosEntregados()` - Retorna total de entregas
- `calcularRendimiento()` - Evalúa desempeño del repartidor
- `getInformacionCompleta()` - Información detallada formateada

### TDA Utilizado
- **LinkedListADT** (DynamicLinkedListADT) para `pedidosEntregados`

### Funcionalidades
- Asignación automática de pedidos
- Control de disponibilidad
- Historial de entregas
- Cálculo de rendimiento (Principiante/Intermedio/Experimentado)

---

## 🍳 Cocina

Representa la cocina del restaurante con su sistema de gestión de preparación.

### Atributos
- `nombre` (String) - Nombre de la cocina
- `colaPedidos` (PriorityQueueADT) - Cola con prioridad de pedidos
- `capacidadMaxima` (int) - Capacidad máxima de pedidos en cola
- `pedidosPreparados` (int) - Contador de pedidos completados
- `pedidoActualId` (int) - ID del pedido en preparación (-1 si no hay)
- `activa` (boolean) - Estado de la cocina

### Constructores
```java
Cocina(String nombre, int capacidadMaxima)
Cocina(String nombre) // Capacidad ilimitada
Cocina() // "Cocina Principal" con capacidad ilimitada
```

### Métodos Principales
- `agregarPedido(Pedido pedido)` - Agrega pedido a la cola (respeta prioridad)
- `tomarSiguientePedido()` - Toma el pedido con mayor prioridad (retorna ID)
- `completarPedidoActual()` - Marca como completado el pedido actual
- `cancelarPedidoActual()` - Cancela el pedido en preparación
- `getCantidadPedidosEnCola()` - Retorna cantidad de pedidos esperando
- `verSiguientePedido()` - Consulta próximo pedido sin removerlo
- `calcularUtilizacion()` - Calcula porcentaje de uso de la cocina
- `getInformacionCompleta()` - Información detallada formateada

### TDA Utilizado
- **PriorityQueueADT** (DynamicPriorityQueueADT) para `colaPedidos`
  - Pedidos VIP (prioridad 1) procesados antes que NORMAL (prioridad 2)
  - Menor valor numérico = mayor prioridad

### Funcionalidades
- Cola con prioridad automática (VIP primero)
- Control de capacidad máxima
- Gestión de pedido en preparación
- Estadísticas de utilización
- Validación de estado activa/inactiva

---

## 🔗 Relaciones Entre Entidades

```
Cliente (1) ─────→ (N) Pedido
                      │
                      │ (N)
                      ↓
                   Plato (M)
                      
Pedido → Cocina (cola con prioridad)
       ↓
    Repartidor
```

### Flujo Típico

1. **Cliente** realiza un **Pedido** seleccionando **Platos**
2. **Pedido** se agrega a la cola de **Cocina** (con prioridad según cliente VIP/NORMAL)
3. **Cocina** procesa pedidos según prioridad (VIP primero)
4. Al completar preparación, si es DOMICILIO, se asigna a **Repartidor**
5. **Repartidor** entrega y registra en su historial
6. **Cliente** recibe confirmación y se actualiza su historial

---

## 📊 Características del Diseño

### ✅ Encapsulamiento
- Todos los atributos son `private`
- Acceso controlado mediante getters/setters
- Validaciones en métodos de modificación

### ✅ Identificadores Únicos
- Cada clase tiene ID autogenerado mediante contador estático
- Métodos `reiniciarContador()` para testing
- IDs finales (no modificables después de creación)

### ✅ Múltiples Representaciones
- `toString()` - Información completa de la entidad
- `toStringResumido()` - Versión compacta para listas
- `getInformacionCompleta()` - Formato detallado multilínea

### ✅ Uso Exclusivo de TDAs Propios
- **LinkedListADT** - Usado en Cliente, Pedido, Repartidor
- **PriorityQueueADT** - Usado en Cocina
- ❌ **NO se usa:** ArrayList, HashMap, LinkedList, PriorityQueue de Java

### ✅ Validaciones Robustas
- Control de rangos en índices
- Validación de estados y transiciones
- Manejo de casos límite (null, vacío, completo)
- Excepciones personalizadas

### ✅ Sistema de Prioridades
- Pedidos VIP (prioridad 1) procesados antes que NORMAL (prioridad 2)
- PriorityQueueADT ordena automáticamente
- Menor valor = mayor prioridad

---

## 🧪 Ejemplo de Uso

```java
// Crear cliente
Cliente cliente = new Cliente("Juan Pérez", "1234-5678", "Av. Corrientes 1234", "juan@mail.com");

// Crear platos
Plato pizza = new Plato("Pizza Muzzarella", 15, 350.0);
Plato empanadas = new Plato("Empanadas x6", 10, 200.0);

// Crear pedido VIP
Pedido pedido = new Pedido(cliente, TipoPedido.DOMICILIO, Prioridad.VIP);
pedido.agregarPlato(pizza.getId());
pedido.agregarPlato(empanadas.getId());
pizza.incrementarPedidos();
empanadas.incrementarPedidos();

// Agregar a cocina
Cocina cocina = new Cocina("Cocina Principal", 10);
cocina.agregarPedido(pedido);

// Procesar en cocina
int idEnPreparacion = cocina.tomarSiguientePedido();
pedido.setEstado(EstadoPedido.EN_PREPARACION);
// ... preparar ...
cocina.completarPedidoActual();
pedido.setEstado(EstadoPedido.LISTO);

// Asignar repartidor (si es domicilio)
if (pedido.getTipoPedido() == TipoPedido.DOMICILIO) {
    Repartidor repartidor = new Repartidor("Carlos", "9876-5432", "Moto");
    repartidor.asignarPedido(pedido.getId());
    pedido.setEstado(EstadoPedido.EN_CAMINO);
    // ... entregar ...
    repartidor.completarEntrega();
    pedido.setEstado(EstadoPedido.ENTREGADO);
}

// Actualizar historial del cliente
cliente.agregarPedidoAlHistorial(pedido.getId());
```

---

## 📈 Métricas de las Clases

| Clase | Líneas de Código | Atributos | Métodos Públicos | TDA Usado |
|-------|------------------|-----------|------------------|-----------|
| Enums | 86 | - | - | - |
| Cliente | 201 | 7 | ~15 | LinkedListADT |
| Plato | 144 | 6 | ~12 | - |
| Pedido | 184 | 7 | ~15 | LinkedListADT |
| Repartidor | 233 | 7 | ~18 | LinkedListADT |
| Cocina | 341 | 6 | ~20 | PriorityQueueADT |
| **Total** | **~1,190** | **33** | **~80** | **2 TDAs** |

---

*Ver estructura completa del proyecto en [ESTRUCTURA.md](ESTRUCTURA.md)*  
*Ver comandos y guías en [README.md](README.md)*
