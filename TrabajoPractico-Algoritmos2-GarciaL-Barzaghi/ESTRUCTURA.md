# 📁 Estructura del Proyecto

## Organización de Paquetes

```
org.example/
├── app/                    # Clase principal con menú interactivo
│   ├── Main.java          # Punto de entrada del sistema
│   └── TestModelo.java    # Pruebas del modelo de dominio
│
├── model/                  # Clases del dominio del negocio
│   ├── Enums.java         # Enumeraciones (TipoPedido, Prioridad, EstadoPedido)
│   ├── Cliente.java       # Gestión de clientes
│   ├── Plato.java         # Platos del menú
│   ├── Pedido.java        # Pedidos del restaurante
│   ├── Repartidor.java    # Repartidores
│   └── Cocina.java        # Cola de preparación
│
├── tda/                    # Interfaces de Tipos de Datos Abstractos propios
│   ├── QueueADT.java      # Interfaz Cola
│   ├── StackADT.java      # Interfaz Pila
│   ├── SetADT.java        # Interfaz Conjunto
│   ├── LinkedListADT.java # Interfaz Lista Enlazada
│   ├── SimpleDictionaryADT.java       # Interfaz Diccionario Simple
│   ├── MultipleDictionaryADT.java     # Interfaz Diccionario Múltiple
│   ├── PriorityQueueADT.java          # Interfaz Cola con Prioridad
│   ├── BinaryTreeADT.java             # Interfaz Árbol Binario
│   └── GraphADT.java                  # Interfaz Grafo
│
├── implementations/        # Implementaciones concretas de los TDAs
│   ├── nodes/             # Nodos para estructuras dinámicas
│   │   ├── Node.java
│   │   ├── PriorityNode.java
│   │   ├── DictionaryNode.java
│   │   ├── EdgeNode.java
│   │   └── VertexNode.java
│   │
│   ├── Implementaciones Dinámicas (basadas en nodos):
│   │   ├── DynamicQueueADT.java
│   │   ├── DynamicStackADT.java
│   │   ├── DynamicSetADT.java
│   │   ├── DynamicLinkedListADT.java      ✅ Usado en el modelo
│   │   ├── DynamicSimpleDictionaryADT.java
│   │   ├── DynamicPriorityQueueADT.java   ✅ Usado en el modelo
│   │   ├── DynamicBinaryTreeADT.java
│   │   └── DynamicGraphADT.java
│   │
│   └── Implementaciones Estáticas (basadas en arrays):
│       ├── StaticQueueADT.java
│       ├── StaticStackADT.java
│       ├── StaticSetADT.java
│       ├── StaticLinkedListADT.java
│       ├── StaticSimpleDictionaryADT.java
│       ├── StaticMultipleDictionaryADT.java
│       ├── StaticPriorityQueueADT.java
│       ├── StaticBinaryTreeADT.java
│       └── StaticGraphADT.java
│
├── service/               # Lógica de negocio (a implementar)
│   ├── GestorPedidos     # Gestión de pedidos del restaurante
│   ├── GestorCocina      # Gestión de la cocina
│   └── GestorReparto     # Gestión de entregas
│
└── utils/                 # Clases auxiliares y utilidades
    ├── exceptions/        # Excepciones personalizadas
    │   ├── EmptyStructureException.java
    │   ├── FullStructureException.java
    │   ├── InvalidIndexException.java
    │   ├── ElementNotFoundException.java
    │   └── DuplicateElementException.java
    │
    └── Utilidades para TDAs:
        ├── QueueADTutil.java
        ├── StackADTutil.java
        ├── SetADTutil.java
        ├── SimpleDictionaryADTUtil.java
        ├── PriorityQueueADTutil.java
        ├── BinaryTreeADTutil.java
        ├── GraphADTutil.java
        └── MultipleDictionaryADTutil.java
```

---

## Árbol de Directorios Completo

```
TrabajoPractico-Algoritmos2-GarciaL-Barzaghi/
│
├── src/
│   └── main/
│       ├── java/org/example/
│       │   ├── app/
│       │   │   ├── Main.java
│       │   │   └── TestModelo.java
│       │   ├── model/
│       │   │   ├── Enums.java
│       │   │   ├── Cliente.java
│       │   │   ├── Plato.java
│       │   │   ├── Pedido.java
│       │   │   ├── Repartidor.java
│       │   │   └── Cocina.java
│       │   ├── tda/
│       │   │   └── [9 interfaces]
│       │   ├── implementations/
│       │   │   ├── nodes/
│       │   │   │   └── [5 nodos]
│       │   │   └── [17 implementaciones]
│       │   ├── service/
│       │   │   └── [pendiente]
│       │   └── utils/
│       │       ├── exceptions/
│       │       │   └── [5 excepciones]
│       │       └── [8 utilidades]
│       └── resources/
│
├── target/                    # Archivos compilados (generado por Maven)
│   └── classes/
│
├── pom.xml                    # Configuración de Maven
├── README.md                  # Documentación principal
├── ESTRUCTURA.md              # Este archivo
└── MODELO.md                  # Documentación del modelo de dominio
```

---

## Archivos por Tipo

### Clases de Aplicación (2)
- `Main.java` - Punto de entrada del sistema
- `TestModelo.java` - Pruebas del modelo

### Modelo de Dominio (6 + 3 enums)
- `Enums.java` - 3 enumeraciones
- `Cliente.java` - 201 líneas
- `Plato.java` - 144 líneas
- `Pedido.java` - 184 líneas
- `Repartidor.java` - 233 líneas
- `Cocina.java` - 341 líneas

### Interfaces TDA (9)
- Estructuras básicas: Queue, Stack, Set, LinkedList
- Diccionarios: Simple, Multiple
- Avanzadas: PriorityQueue, BinaryTree, Graph

### Implementaciones TDA (17 + 5 nodos)
- 8 implementaciones dinámicas
- 9 implementaciones estáticas
- 5 clases de nodos

### Utilidades (8 + 5 excepciones)
- 8 clases de utilidades para TDAs
- 5 excepciones personalizadas

---

## Uso de TDAs en el Modelo

| Clase | TDA Utilizado | Propósito |
|-------|---------------|-----------|
| **Cliente** | `LinkedListADT` | Almacenar historial de pedidos |
| **Pedido** | `LinkedListADT` | Almacenar lista de platos |
| **Repartidor** | `LinkedListADT` | Almacenar pedidos entregados |
| **Cocina** | `PriorityQueueADT` | Cola de pedidos con prioridad VIP |

---

## Configuración del Proyecto

- **Build Tool:** Maven
- **Java Version:** 24
- **Encoding:** UTF-8
- **Archivos Compilados:** 52
- **Total de Líneas:** ~3,500+

---

## Convenciones de Nomenclatura

### Paquetes
- `app` - Minúscula, aplicación
- `model` - Minúscula, modelo de dominio
- `tda` - Minúscula, interfaces TDA
- `implementations` - Minúscula, implementaciones
- `service` - Minúscula, servicios
- `utils` - Minúscula, utilidades

### Clases
- `NombreClase` - PascalCase
- Interfaces TDA terminan en `ADT`
- Implementaciones dinámicas prefijo `Dynamic`
- Implementaciones estáticas prefijo `Static`
- Nodos terminan en `Node`
- Excepciones terminan en `Exception`
- Utilidades terminan en `util` o `Util`

### Métodos
- `getNombre()` - camelCase para getters
- `setNombre()` - camelCase para setters
- `calcularTotal()` - camelCase para métodos
- `isEmpty()` - camelCase con prefijo `is` para boolean

### Variables
- `nombreVariable` - camelCase
- `MAX_SIZE` - UPPER_SNAKE_CASE para constantes
- `this.variable` - uso explícito de this

---

## Dependencias entre Paquetes

```
app → model, service
model → tda, implementations
implementations → tda, implementations.nodes
service → model, tda, implementations
utils → tda, implementations
```

---

## 🎯 Estado del Proyecto

### ✅ FASE 1: ESTRUCTURA Y MODELO DE DOMINIO
**Estado:** ✅ COMPLETADO
- ✅ Reestructuración de paquetes
- ✅ Clases del modelo de dominio
- ✅ Pruebas unitarias (TestModelo.java)
- ✅ Documentación completa

### ✅ FASE 2: MÓDULO DE GESTIÓN DE PEDIDOS
**Estado:** ✅ COMPLETADO
- ✅ GestorPedidos con validaciones
- ✅ Cola de prioridad para clasificación
- ✅ DatosIniciales (10 repartidores, 5 pedidos, menú)
- ✅ Menú interactivo escalable
- ✅ Estadísticas y consultas

### ✅ FASE 3: MÓDULO DE GESTIÓN DE COCINA
**Estado:** ✅ COMPLETADO
- ✅ GestorCocina con cola FIFO
- ✅ Simulación de preparación de platos
- ✅ Determinación de destino (REPARTO/RETIRO)
- ✅ Integración con menú interactivo

### 🔄 FASE 4: GESTIÓN DE REPARTO
**Estado:** ⏳ PENDIENTE
- [ ] GestorReparto
- [ ] Asignación de repartidores
- [ ] Seguimiento de entregas

---

*Ver documentación completa en [README.md](README.md) y [README-EJECUTAR.md](README-EJECUTAR.md)*
