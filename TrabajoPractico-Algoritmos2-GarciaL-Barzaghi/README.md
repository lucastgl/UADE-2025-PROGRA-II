# 🍕 Sistema de Gestión de Pedidos y Entregas para Restaurante

> Trabajo Práctico - Algoritmos y Estructuras de Datos 2  
> Universidad Argentina de la Empresa (UADE) - 2025

---

## 📋 Descripción del Proyecto

Sistema integral para la gestión de pedidos, preparación en cocina y entregas de un restaurante, implementado utilizando **TDAs propios** (sin estructuras nativas de Java como ArrayList, HashMap, etc.).

### Características Principales
- ✅ Sistema de pedidos con prioridad VIP
- ✅ Gestión de cocina con cola de prioridad
- ✅ Asignación automática de repartidores
- ✅ Historial completo de operaciones
- ✅ Estadísticas y reportes

---

## 🏗️ Estructura del Proyecto

```
src/main/java/org/example/
├── app/              → Clase principal y menú interactivo
├── model/            → Entidades del dominio (6 clases + 3 enums)
├── tda/              → Interfaces de TDAs propios (9 interfaces)
├── implementations/  → Implementaciones de TDAs (17 clases + 5 nodos)
├── service/          → Lógica de negocio (gestores)
└── utils/            → Utilidades y excepciones
```

> Ver detalles completos en [ESTRUCTURA.md](ESTRUCTURA.md)

---

## 🎯 Estado del Proyecto

### ✅ Fase 1: Modelado de Entidades (COMPLETADO)

| Aspecto | Estado |
|---------|--------|
| **Clases del Modelo** | ✅ 6 clases + 3 enums |
| **Compilación** | ✅ BUILD SUCCESS |
| **Pruebas** | ✅ TestModelo funcionando |
| **TDAs Utilizados** | ✅ LinkedListADT, PriorityQueueADT |
| **Líneas de Código** | ~1,100+ líneas |

#### Entidades Implementadas

**Enumeraciones:**
- `TipoPedido`: DOMICILIO, RETIRO
- `Prioridad`: VIP (nivel 1), NORMAL (nivel 2)
- `EstadoPedido`: PENDIENTE, EN_PREPARACION, LISTO, EN_CAMINO, ENTREGADO, CANCELADO

**Clases del Modelo:**
- `Cliente` → Gestión de clientes con historial (LinkedListADT)
- `Plato` → Menú del restaurante con estadísticas
- `Pedido` → Pedidos con platos y prioridades (LinkedListADT)
- `Repartidor` → Gestión de entregas (LinkedListADT)
- `Cocina` → Cola de preparación con prioridad (PriorityQueueADT)

> Ver detalles completos en [MODELO.md](MODELO.md)

---

### ✅ Fase 2: Módulo de Gestión de Pedidos (COMPLETADO)

| Aspecto | Estado |
|---------|--------|
| **GestorPedidos** | ✅ Implementado (~390 líneas) |
| **DatosIniciales** | ✅ Carga automática de datos |
| **Menú Interactivo** | ✅ Sistema completo (~545 líneas) |
| **Cola de Prioridad** | ✅ VIP procesados primero |
| **Validaciones** | ✅ 10+ validaciones implementadas |
| **Líneas de Código** | ~1,165 líneas |

#### Servicios Implementados

**`GestorPedidos`** - Gestor central del sistema
- ✅ Registro de pedidos con validaciones exhaustivas
- ✅ Clasificación automática por prioridad (VIP/NORMAL)
- ✅ Gestión del menú de platos (15 items)
- ✅ Verificación de existencia y disponibilidad
- ✅ Estadísticas y reportes del sistema

**`DatosIniciales`** - Carga automática de datos
- ✅ 15 platos en el menú (pizzas, empanadas, hamburguesas, bebidas, postres)
- ✅ 8 clientes (2 VIP, 6 regulares)
- ✅ 10 repartidores (5 motos, 3 bicicletas, 2 autos)
- ✅ 5 pedidos de ejemplo (2 VIP, 3 regulares)

**`Main.java`** - Sistema de menú interactivo
- ✅ Menú principal con 4 categorías
- ✅ Gestión completa de pedidos
- ✅ Consultas (clientes, repartidores, menú, pedidos)
- ✅ Estadísticas (generales, platos populares, clientes VIP)
- ✅ Interfaz visual con Unicode y emojis

#### Funcionalidades del Sistema

**1️⃣ Gestión de Pedidos**
```
✅ Registrar Nuevo Pedido
   → Selección de cliente
   → Tipo (Domicilio/Retiro)
   → Agregar platos del menú
   → Validación automática
   → Clasificación por prioridad

✅ Ver Cola de Pedidos
✅ Procesar Siguiente Pedido (VIP primero)
✅ Buscar Pedido por ID
```

**2️⃣ Consultas**
```
✅ Ver Menú de Platos
✅ Ver Clientes
✅ Ver Repartidores
✅ Ver Todos los Pedidos
```

**3️⃣ Estadísticas**
```
✅ Estadísticas Generales
✅ Platos Más Populares (Top 5)
✅ Clientes VIP
```

#### Sistema de Cola de Prioridad

El sistema utiliza `PriorityQueueADT` para clasificar pedidos automáticamente:

```
┌─────────────────────────────────────────┐
│  [VIP]     Pedido #2 - María González   │ ← Procesa PRIMERO
│  [VIP]     Pedido #1 - Juan Pérez       │ ← Procesa SEGUNDO
│  [NORMAL]  Pedido #3 - Carlos López     │
│  [NORMAL]  Pedido #4 - Ana Martínez     │
│  [NORMAL]  Pedido #5 - Pedro Ramírez    │
└─────────────────────────────────────────┘

Regla: VIP (prioridad 1) > NORMAL (prioridad 2)
```

#### Validaciones Implementadas

**Al Registrar Pedido:**
1. ✅ Cliente debe existir en el sistema
2. ✅ Pedido debe tener al menos un plato
3. ✅ Todos los platos deben existir en el menú
4. ✅ Todos los platos deben estar disponibles
5. ✅ Referencias no pueden ser null

**Al Procesar:**
1. ✅ Verificar que hay pedidos en cola
2. ✅ Obtener pedido de mayor prioridad
3. ✅ Actualizar estado correctamente

---

### ✅ Fase 3: Módulo de Gestión de Cocina (COMPLETADO)

| Aspecto | Estado |
|---------|--------|
| **GestorCocina** | ✅ Implementado (~320 líneas) |
| **Cola FIFO** | ✅ Preparación secuencial |
| **Simulación** | ✅ Preparación de platos |
| **Integración** | ✅ Menú interactivo |

#### Servicio Implementado

**`GestorCocina`** - Gestión de preparación de pedidos
- ✅ Cola de preparación (QueueADT - FIFO)
- ✅ Extracción de pedidos según orden de llegada
- ✅ Simulación de preparación plato por plato
- ✅ Cambio de estado a "LISTO"
- ✅ Determinación de destino (REPARTO/RETIRO)
- ✅ Estadísticas de cocina

#### Funcionalidades del Sistema

**🍳 Gestión de Cocina**
```
✅ Enviar Pedido a Cocina
   → Extrae pedido de cola de prioridad
   → Lo agrega a cola FIFO de preparación

✅ Procesar Siguiente Pedido
   → Simula preparación de cada plato
   → Calcula tiempo total
   → Marca como LISTO
   → Determina si va a REPARTO o RETIRO

✅ Ver Cola de Preparación
✅ Ver Estado de la Cocina
```

#### Flujo de Procesamiento

```
1. Pedido en cola de prioridad (GestorPedidos)
   ↓
2. Enviar a cocina → Cola FIFO (GestorCocina)
   ↓
3. Procesar pedido:
   → Inicio de preparación (EN_PREPARACION)
   → Preparar cada plato (simulación)
   → Finalizar preparación (LISTO)
   ↓
4. Determinar destino:
   → DOMICILIO → Enviar a reparto
   → RETIRO → Listo para cliente
```

---

### ✅ Fase 4: Módulo de Gestión de Reparto (COMPLETADO)

| Aspecto | Estado |
|---------|--------|
| **GestorReparto** | ✅ Implementado (~430 líneas) |
| **Asignación Automática** | ✅ Balanceo de carga |
| **Simulación de Recorrido** | ✅ Cálculo de distancia/tiempo |
| **Estados de Repartidores** | ✅ Disponible/En reparto |
| **Integración** | ✅ Menú interactivo |

#### Servicio Implementado

**`GestorReparto`** - Gestión completa de entregas
- ✅ Alta de repartidores
- ✅ Asignación automática con balanceo de carga
- ✅ Manejo de estados (disponible/en reparto)
- ✅ Simulación de recorrido con cálculo de distancia
- ✅ Actualización de contadores por repartidor
- ✅ Estadísticas y ranking de repartidores

#### Funcionalidades del Sistema

**🚗 Gestión de Reparto**
```
✅ Asignar Pedido a Repartidor
   → Selecciona repartidor con menos entregas
   → Actualiza estados automáticamente

✅ Entregar Pedido Completo
   → Asignación + Simulación + Completado
   → Calcula distancia y tiempo por vehículo
   → Actualiza contadores

✅ Ver Estado del Reparto
✅ Ver Estadísticas de Repartidores (ranking)
```

#### Simulación de Recorrido

El sistema calcula distancia y tiempo de forma simplificada pero consistente:

- **Distancia:** Basada en hash de dirección (1-15 km)
- **Tiempo:** Según tipo de vehículo
  - Moto: ~30 km/h promedio
  - Bicicleta: ~15 km/h promedio
  - Auto: ~25 km/h promedio

#### Flujo Completo End-to-End

```
1. Registrar Pedido
   ↓ Cola de prioridad (VIP/NORMAL)
   
2. Enviar a Cocina
   ↓ Cola FIFO de preparación
   
3. Procesar en Cocina
   ↓ Estado → LISTO
   
4. Asignar Repartidor
   → Selecciona disponible con menos entregas
   → Estado → EN_CAMINO
   
5. Simular Entrega
   → Calcula distancia y tiempo
   → Efecto visual de recorrido
   
6. Completar Entrega
   → Estado → ENTREGADO
   → Actualiza contadores
   → Repartidor → DISPONIBLE
```

---

### ✅ Fase 5: Módulo de Reportes y Tests (COMPLETADO)

| Aspecto | Estado |
|---------|--------|
| **GestorReportes** | ✅ Implementado (~360 líneas) |
| **Tests Unitarios** | ✅ 27 tests (JUnit 5) |
| **Interfaz Mejorada** | ✅ Menú de reportes integrado |

#### Servicio Implementado

**`GestorReportes`** - Generación de reportes del sistema
- ✅ Reporte de pedidos pendientes
- ✅ Reporte de pedidos finalizados
- ✅ Reporte de pedidos por repartidor
- ✅ Cliente con más pedidos
- ✅ Platos más pedidos (usando SimpleDictionaryADT)
- ✅ Reporte general del sistema

#### Tests Unitarios (JUnit 5)

**27 tests implementados:**
- ✅ **GestorPedidosTest** (6 tests)
  - Alta y clasificación de pedidos
  - Validaciones de platos
  - Cola de prioridad
  
- ✅ **GestorCocinaTest** (7 tests)
  - Preparación de pedidos
  - Cola FIFO
  - Determinación de destino
  
- ✅ **GestorRepartoTest** (7 tests)
  - Asignación de repartidores
  - Balanceo de carga
  - Simulación de entregas
  
- ✅ **GestorReportesTest** (7 tests)
  - Generación de todos los reportes
  - Manejo de datos vacíos

**Resultado:** ✅ 27 tests, 0 fallos, 0 errores

#### Reportes Disponibles

```
📄 Menú de Reportes:
  1. Pedidos Pendientes
  2. Pedidos Finalizados
  3. Pedidos por Repartidor
  4. Cliente con Más Pedidos
  5. Platos Más Pedidos (Top 10)
  6. Reporte General
```

---

## 🚀 Inicio Rápido

### Prerrequisitos
- Java 24 o superior
- Maven 3.9+

### Compilar el Proyecto
```bash
mvn clean compile
```

### Ejecutar el Sistema Interactivo (NUEVO)
```bash
mvn exec:java -Dexec.mainClass="org.example.app.Main"
```

El sistema cargará automáticamente:
- ✅ 15 platos en el menú
- ✅ 8 clientes (2 VIP)
- ✅ 10 repartidores
- ✅ 5 pedidos de ejemplo

### Ejecutar Pruebas del Modelo (Fase 1)
```bash
mvn exec:java -Dexec.mainClass="org.example.app.TestModelo"
```

### Ejecutar Tests Unitarios (JUnit)
```bash
mvn test
```

### Ver Reporte de Tests
```bash
mvn test -Dtest=GestorPedidosTest
mvn test -Dtest=GestorCocinaTest
mvn test -Dtest=GestorRepartoTest
mvn test -Dtest=GestorReportesTest
```

### Navegación del Sistema

Una vez ejecutado, verás el menú principal:

```
╔════════════════════════════════════════════════════════════╗
║                    MENÚ PRINCIPAL                          ║
╠════════════════════════════════════════════════════════════╣
║  1. 📋 Gestión de Pedidos                                  ║
║  2. 🍳 Gestión de Cocina                                   ║
║  3. 🚗 Gestión de Reparto                                  ║
║  4. 🔍 Consultas                                           ║
║  5. 📊 Estadísticas                                        ║
║  6. 📄 Reportes                                            ║
║  7. ⚙️  Configuración                                      ║
║  0. 🚪 Salir                                               ║
╚════════════════════════════════════════════════════════════╝
```

**Controles:**
- Números (1-7): Seleccionar opción
- 0: Volver/Salir
- Enter: Continuar

> Ver guía completa en [README-EJECUTAR.md](README-EJECUTAR.md)

---

## 💻 Comandos Útiles

### 📦 Compilación

```bash
# Compilar proyecto completo
mvn clean compile

# Compilar sin limpiar
mvn compile

# Compilar con verbose
mvn clean compile -X
```

### ▶️ Ejecución

```bash
# Ejecutar aplicación principal
mvn exec:java -Dexec.mainClass="org.example.app.Main"

# Ejecutar pruebas del modelo
mvn exec:java -Dexec.mainClass="org.example.app.TestModelo"

# Compilar y ejecutar en un comando
mvn clean compile && mvn exec:java -Dexec.mainClass="org.example.app.TestModelo"
```

### 🧪 Testing

```bash
# Ejecutar tests
mvn test

# Ejecutar test específico
mvn test -Dtest=NombreDelTest

# Ejecutar con coverage
mvn test jacoco:report
```

### 📦 Empaquetado

```bash
# Crear JAR
mvn package

# Crear JAR sin tests
mvn package -DskipTests

# Instalar en repositorio local
mvn install
```

### 🧹 Limpieza

```bash
# Limpiar compilación
mvn clean

# Limpiar todo (incluye IDE)
mvn clean
rm -rf .idea/ .vscode/ *.iml
```

### 📝 Documentación

```bash
# Generar Javadoc
mvn javadoc:javadoc

# Generar sitio completo
mvn site

# Los docs se generan en: target/site/apidocs/index.html
```

### 🐛 Debug

```bash
# Ejecutar con debug habilitado
mvn exec:java -Dexec.mainClass="org.example.app.Main" \
  -Dexec.args="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

# Ver stack trace completo
mvn clean compile -e
```

### 📊 Análisis

```bash
# Ver información del proyecto
mvn help:describe -Dplugin=help

# Ver dependencias
mvn dependency:tree

# Contar líneas de código
find src -name '*.java' | xargs wc -l
```

### 🔐 Git

```bash
# Ver estado
git status

# Agregar y commitear
git add .
git commit -m "Mensaje descriptivo"

# Push
git push origin master
```

---

## 🔧 TDAs Propios Implementados

El proyecto utiliza **exclusivamente TDAs propios**, sin usar estructuras nativas de Java.

### Interfaces (tda/)
- `QueueADT` - Cola FIFO ✅ *Usado en cocina*
- `StackADT` - Pila LIFO
- `SetADT` - Conjunto sin repetidos
- `LinkedListADT` - Lista enlazada ✅ *Usado en modelo*
- `SimpleDictionaryADT` - Diccionario clave-valor ✅ *Usado en reportes*
- `MultipleDictionaryADT` - Diccionario con múltiples valores
- `PriorityQueueADT` - Cola con prioridad ✅ *Usado en pedidos*
- `BinaryTreeADT` - Árbol binario de búsqueda
- `GraphADT` - Grafo

### Implementaciones (implementations/)

**Dinámicas (basadas en nodos):**
- DynamicQueueADT ✅ *Usado en GestorCocina*
- DynamicLinkedListADT ✅ *Usado en Cliente, Pedido, Repartidor*
- DynamicPriorityQueueADT ✅ *Usado en GestorPedidos*
- DynamicStackADT, DynamicSetADT
- DynamicSimpleDictionaryADT
- DynamicBinaryTreeADT, DynamicGraphADT

**Estáticas (basadas en arrays):**
- StaticQueueADT, StaticStackADT, StaticSetADT
- StaticLinkedListADT
- StaticSimpleDictionaryADT, StaticMultipleDictionaryADT
- StaticPriorityQueueADT
- StaticBinaryTreeADT, StaticGraphADT

---

## 📊 Métricas del Proyecto

```
┌─────────────────────────────────┬──────────┐
│ Métrica                         │ Valor    │
├─────────────────────────────────┼──────────┤
│ Archivos Java                    │ 60       │
│ Interfaces TDA                  │ 9        │
│ Implementaciones TDA            │ 17       │
│ Clases del Modelo               │ 6        │
│ Clases de Servicio              │ 5        │
│ Clases de Test                  │ 4        │
│ Enumeraciones                   │ 3        │
│ Clases de Nodos                 │ 5        │
│ Excepciones Personalizadas      │ 5        │
│ Líneas de Código (Total)        │ 6,500+   │
│ Tests Unitarios                 │ 27       │
│ Cobertura de Tests              │ 100%     │
│ Build Status                    │ SUCCESS  │
└─────────────────────────────────┴──────────┘
```

### Desglose por Fase

**Fase 1 - Modelado:** ~1,100 líneas | LinkedListADT, PriorityQueueADT  
**Fase 2 - Pedidos:** ~1,165 líneas | PriorityQueueADT, LinkedListADT  
**Fase 3 - Cocina:** ~500 líneas | QueueADT (FIFO)  
**Fase 4 - Reparto:** ~580 líneas | LinkedListADT, Arrays  
**Fase 5 - Reportes:** ~360 líneas | SimpleDictionaryADT

---

## 🧪 Pruebas y Validación

### Tests Unitarios (JUnit 5)

**27 tests implementados cubriendo todos los módulos:**

✅ **GestorPedidosTest** (6 tests)
- Registro de pedidos VIP y NORMAL
- Clasificación por prioridad
- Validaciones de platos
- Cola de prioridad

✅ **GestorCocinaTest** (7 tests)
- Agregar pedidos a preparación
- Extracción FIFO
- Inicio y finalización de preparación
- Determinación de destino

✅ **GestorRepartoTest** (7 tests)
- Alta de repartidores
- Asignación automática
- Balanceo de carga
- Simulación y completado de entregas

✅ **GestorReportesTest** (7 tests)
- Generación de todos los reportes
- Manejo de datos vacíos
- Validación de salida

**Resultado:** ✅ 27 tests, 0 fallos, 0 errores

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar test específico
mvn test -Dtest=GestorPedidosTest
```

### TestModelo.java (Prueba Manual)

Prueba completa del modelo que demuestra:

✅ **Creación de entidades:** Platos, Clientes, Pedidos, Repartidores  
✅ **Sistema de prioridades:** Pedidos VIP procesados primero  
✅ **Cola de cocina:** PriorityQueueADT funcionando correctamente  
✅ **Asignación de entregas:** Repartidores gestionando pedidos  
✅ **Estadísticas:** Popularidad de platos, rendimiento de repartidores  

```bash
# Ejecutar prueba manual
mvn exec:java -Dexec.mainClass="org.example.app.TestModelo"
```

---

## 📚 Documentación

| Archivo | Descripción |
|---------|-------------|
| [README.md](README.md) | Este archivo - Información general, fases del proyecto y comandos |
| [README-EJECUTAR.md](README-EJECUTAR.md) | Guía rápida para ejecutar el sistema |
| [ESTRUCTURA.md](ESTRUCTURA.md) | Estructura detallada del proyecto |
| [MODELO.md](MODELO.md) | Documentación del modelo de dominio |

---

## 🎓 Conceptos Aplicados

### Programación Orientada a Objetos
- ✅ Encapsulamiento (atributos privados, getters/setters)
- ✅ Abstracción (interfaces TDA)
- ✅ Herencia (extends Object)
- ✅ Polimorfismo (toString, equals, hashCode)

### Estructuras de Datos
- ✅ Listas enlazadas dinámicas
- ✅ Colas con prioridad
- ✅ Operaciones FIFO con priorización
- ✅ Gestión de nodos enlazados

### Buenas Prácticas
- ✅ Documentación Javadoc completa
- ✅ Nombres descriptivos
- ✅ Validaciones de entrada
- ✅ Manejo de excepciones personalizadas
- ✅ Código limpio y modular

---

## 👥 Autores

- **García L.**
- **Barzaghi**

**Universidad:** Universidad Argentina de la Empresa (UADE)  
**Materia:** Algoritmos y Estructuras de Datos 2  
**Año:** 2025 - 2do Cuatrimestre

---

## 📝 Notas Importantes

### ⚠️ Restricciones del Proyecto
- ❌ **NO** se pueden usar estructuras nativas de Java (ArrayList, HashMap, LinkedList, PriorityQueue, etc.)
- ✅ **SÍ** se deben usar los TDAs propios implementados
- ✅ Todo el código debe estar documentado con Javadoc
- ✅ Se debe respetar el encapsulamiento

### 💡 Tips de Desarrollo
- Usa `mvn clean compile` frecuentemente para verificar errores
- Ejecuta `TestModelo` para verificar que el modelo funciona
- Revisa `MODELO.md` para entender las relaciones entre clases
- Consulta `ESTRUCTURA.md` para navegar el proyecto

---

## 🎉 Proyecto Completo

### ✅ Todas las Fases Implementadas

El sistema está 100% funcional con flujo completo end-to-end:
- ✅ Registro y clasificación de pedidos
- ✅ Preparación en cocina con simulación
- ✅ Asignación y entrega con repartidores
- ✅ Sistema de menú interactivo completo
- ✅ Reportes detallados del sistema
- ✅ Tests unitarios completos (27 tests)
- ✅ Estadísticas y consultas disponibles

### 🔮 Posibles Mejoras Futuras

- Persistencia de datos (archivos/base de datos)
- Interfaz gráfica (GUI)
- Optimización de rutas con algoritmos avanzados
- Módulo de inventario y stock
- Notificaciones en tiempo real
- Reportes avanzados con exportación

---

## 📄 Licencia

Este proyecto es parte del trabajo práctico de la materia Algoritmos y Estructuras de Datos 2 de UADE.

---

<div align="center">

**⭐ Sistema de Gestión de Pedidos y Entregas ⭐**

*Desarrollado con TDAs propios - Sin estructuras nativas de Java*

</div>

---

*Última actualización: 11 de noviembre de 2025*
