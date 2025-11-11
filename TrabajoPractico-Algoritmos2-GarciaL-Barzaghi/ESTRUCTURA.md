# Estructura del Proyecto - Sistema de Gestión de Pedidos y Entregas

## 📁 Organización de Paquetes

```
org.example/
├── app/                    # Clase principal con menú interactivo
│   └── Main.java
│
├── model/                  # Clases del dominio del negocio
│   ├── Cliente.java        # Información de clientes
│   ├── Pedido.java         # Gestión de pedidos
│   ├── Repartidor.java     # Información de repartidores
│   └── Enums.java          # Enumeraciones del sistema
│
├── tda/                    # Interfaces de Tipos de Datos Abstractos propios
│   ├── QueueADT.java       # Interfaz Cola
│   ├── StackADT.java       # Interfaz Pila
│   ├── SetADT.java         # Interfaz Conjunto
│   ├── LinkedListADT.java  # Interfaz Lista Enlazada
│   ├── SimpleDictionaryADT.java      # Interfaz Diccionario Simple
│   ├── MultipleDictionaryADT.java    # Interfaz Diccionario Múltiple
│   ├── PriorityQueueADT.java         # Interfaz Cola con Prioridad
│   ├── BinaryTreeADT.java            # Interfaz Árbol Binario
│   └── GraphADT.java                 # Interfaz Grafo
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
│   │   ├── DynamicLinkedListADT.java
│   │   ├── DynamicSimpleDictionaryADT.java
│   │   ├── DynamicPriorityQueueADT.java
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
│   ├── GestorPedidos      # Gestión de pedidos del restaurante
│   ├── GestorCocina       # Gestión de la cocina
│   └── GestorReparto      # Gestión de entregas
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

## 🔧 Configuración del Proyecto

- **Build Tool:** Maven
- **Java Version:** 24
- **Encoding:** UTF-8

## ✅ Estado Actual

### Completado:
- ✅ Reorganización completa de la estructura de paquetes
- ✅ 9 interfaces TDA definidas
- ✅ 17 implementaciones de TDAs (dinámicas y estáticas)
- ✅ 5 nodos para estructuras dinámicas
- ✅ 8 utilidades para manejo de TDAs
- ✅ 5 excepciones personalizadas
- ✅ Compilación exitosa del proyecto
- ✅ Clase Main actualizada con información del proyecto

### Por Implementar:
- 🔲 Definir completamente las clases del modelo (Cliente, Pedido, Repartidor, Plato)
- 🔲 Implementar servicios de gestión (GestorPedidos, GestorCocina, GestorReparto)
- 🔲 Crear menú interactivo por consola en Main

## 🚀 Cómo Ejecutar

### Compilar el proyecto:
```bash
mvn clean compile
```

### Ejecutar la aplicación:
```bash
mvn exec:java -Dexec.mainClass="org.example.app.Main"
```

## 📝 Notas Importantes

1. **TDAs Propios:** El proyecto utiliza estructuras de datos propias, NO las nativas de Java (no se usa ArrayList, HashMap, etc.)

2. **Implementaciones Duales:** Cada TDA tiene dos implementaciones:
   - **Dinámica:** Usa nodos enlazados (memoria dinámica)
   - **Estática:** Usa arrays (memoria estática con límite MAX)

3. **Excepciones:** Se han creado excepciones personalizadas para manejo de errores específicos de las estructuras de datos

4. **Utilidades:** Cada TDA tiene una clase de utilidad con métodos helper como `copy()` y `print()`

## 🎯 Próximos Pasos

1. **Modelar el Dominio:**
   - Completar la clase `Plato` con atributos (nombre, precio, tiempo de preparación)
   - Completar la clase `Pedido` con relación a Cliente y Platos
   - Completar la clase `Cliente` con datos de contacto
   - Completar la clase `Repartidor` con estado y pedidos asignados

2. **Implementar Servicios:**
   - `GestorPedidos`: Cola de pedidos, asignación a cocina
   - `GestorCocina`: Preparación de pedidos, prioridades
   - `GestorReparto`: Asignación de repartidores, rutas

3. **Crear Interfaz:**
   - Menú interactivo por consola
   - Opciones para gestionar pedidos, cocina y reparto
   - Visualización de estados y reportes

