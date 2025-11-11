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
| **Compilación** | ✅ BUILD SUCCESS (52 archivos) |
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

#### Características del Modelo
✅ **Encapsulamiento completo** - Atributos privados con getters/setters  
✅ **Sistema de prioridades** - Pedidos VIP procesados primero  
✅ **Validaciones robustas** - Control de rangos y estados  
✅ **TDAs propios** - Sin usar estructuras nativas de Java  
✅ **Documentación Javadoc** - Todos los métodos documentados  

### 🔲 Fase 2: Servicios de Gestión (PENDIENTE)

- `GestorPedidos` → Recepción y seguimiento de pedidos
- `GestorCocina` → Administración de preparación
- `GestorReparto` → Asignación y seguimiento de entregas

### 🔲 Fase 3: Menú Interactivo (PENDIENTE)

- Sistema de menú por consola
- CRUD completo para cada entidad
- Reportes y estadísticas
- Flujo end-to-end de pedidos

---

## 🚀 Inicio Rápido

### Prerrequisitos
- Java 24 o superior
- Maven 3.9+

### Compilar el Proyecto
```bash
mvn clean compile
```

### Ejecutar Main
```bash
mvn exec:java -Dexec.mainClass="org.example.app.Main"
```

### Ejecutar Pruebas del Modelo
```bash
mvn exec:java -Dexec.mainClass="org.example.app.TestModelo"
```

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
- `QueueADT` - Cola FIFO
- `StackADT` - Pila LIFO
- `SetADT` - Conjunto sin repetidos
- `LinkedListADT` - Lista enlazada
- `SimpleDictionaryADT` - Diccionario clave-valor
- `MultipleDictionaryADT` - Diccionario con múltiples valores
- `PriorityQueueADT` - Cola con prioridad
- `BinaryTreeADT` - Árbol binario de búsqueda
- `GraphADT` - Grafo

### Implementaciones (implementations/)

**Dinámicas (basadas en nodos):**
- DynamicQueueADT, DynamicStackADT, DynamicSetADT
- DynamicLinkedListADT ✅ *Usado en el modelo*
- DynamicSimpleDictionaryADT
- DynamicPriorityQueueADT ✅ *Usado en el modelo*
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
┌─────────────────────────────┬──────────┐
│ Métrica                     │ Valor    │
├─────────────────────────────┼──────────┤
│ Archivos Java               │ 52       │
│ Interfaces TDA              │ 9        │
│ Implementaciones TDA        │ 17       │
│ Clases del Modelo           │ 6        │
│ Enumeraciones              │ 3        │
│ Clases de Nodos            │ 5        │
│ Excepciones Personalizadas │ 5        │
│ Líneas de Código           │ 3,500+   │
│ Build Status               │ SUCCESS  │
└─────────────────────────────┴──────────┘
```

---

## 🧪 Pruebas y Validación

### TestModelo.java

Prueba completa del modelo que demuestra:

✅ **Creación de entidades:** Platos, Clientes, Pedidos, Repartidores  
✅ **Sistema de prioridades:** Pedidos VIP procesados primero  
✅ **Cola de cocina:** PriorityQueueADT funcionando correctamente  
✅ **Asignación de entregas:** Repartidores gestionando pedidos  
✅ **Estadísticas:** Popularidad de platos, rendimiento de repartidores  

**Resultado:** ✅ Todas las pruebas pasando

```bash
# Ejecutar pruebas
mvn exec:java -Dexec.mainClass="org.example.app.TestModelo"
```

---

## 📚 Documentación

| Archivo | Descripción |
|---------|-------------|
| [README.md](README.md) | Este archivo - Información general y comandos |
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

## 🚀 Próximos Pasos

### En Desarrollo
- 🔲 Implementación de servicios de gestión
- 🔲 Menú interactivo por consola
- 🔲 Integración completa del sistema

### Futuras Mejoras
- Persistencia de datos
- Interfaz gráfica
- Sistema de reportes avanzados
- Módulo de estadísticas

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
