# 🚀 Cómo Ejecutar el Sistema

## ⚡ Inicio Rápido

### 1️⃣ Compilar
```bash
mvn clean compile
```

### 2️⃣ Ejecutar
```bash
mvn exec:java -Dexec.mainClass="org.example.app.Main"
```

---

## 🎮 Navegación del Sistema

### Menú Principal
```
╔════════════════════════════════════════════════════════════╗
║                    MENÚ PRINCIPAL                          ║
╠════════════════════════════════════════════════════════════╣
║  1. 📋 Gestión de Pedidos                                  ║
║  2. 🔍 Consultas                                           ║
║  3. 📊 Estadísticas                                        ║
║  4. ⚙️  Configuración                                      ║
║  0. 🚪 Salir                                               ║
╚════════════════════════════════════════════════════════════╝
```

### Controles
- **1-4:** Seleccionar opción
- **0:** Volver/Salir
- **Enter:** Continuar

---

## 📋 Funcionalidades Disponibles

### 1. Gestión de Pedidos
- ✅ **Registrar Nuevo Pedido**
  - Seleccionar cliente
  - Elegir tipo (Domicilio/Retiro)
  - Agregar platos del menú
  - Validación automática
  
- ✅ **Ver Cola de Pedidos**
  - Cantidad en espera
  - Prioridad VIP destacada
  
- ✅ **Procesar Siguiente Pedido**
  - VIP procesados primero
  - Actualización de estado

- ✅ **Buscar Pedido por ID**

### 2. Consultas
- 📋 Ver Menú de Platos (15 items)
- 👥 Ver Clientes (8 registrados)
- 🏍️ Ver Repartidores (10 disponibles)
- 📦 Ver Todos los Pedidos

### 3. Estadísticas
- 📊 Estadísticas Generales
- 🍕 Platos Más Populares
- 👑 Clientes VIP

---

## 📦 Datos Precargados

Al iniciar el sistema, se cargan automáticamente:

✅ **15 platos** en el menú  
✅ **8 clientes** (2 VIP, 6 regulares)  
✅ **10 repartidores** disponibles  
✅ **5 pedidos** de ejemplo  

---

## 🎯 Ejemplo de Uso

### Registrar un Pedido VIP

1. Ejecutar el sistema
2. Seleccionar opción **1** (Gestión de Pedidos)
3. Seleccionar opción **1** (Registrar Nuevo Pedido)
4. Ingresar ID de cliente VIP (ej: **1** para Juan Pérez)
5. Seleccionar tipo **1** (Domicilio) o **2** (Retiro)
6. Ver el menú y agregar platos:
   - Ingresar **1** para Pizza Muzzarella
   - Ingresar **10** para Papas Fritas
   - Ingresar **12** para Coca-Cola
   - Ingresar **0** para terminar
7. ✅ Pedido registrado con prioridad VIP

### Ver Estadísticas

1. Desde el menú principal, seleccionar **3** (Estadísticas)
2. Seleccionar **1** (Estadísticas Generales) para ver resumen
3. Seleccionar **2** (Platos Más Populares) para ver top 5
4. Seleccionar **0** para volver

---

## 🔧 Características Especiales

### Cola de Prioridad Automática
- Los pedidos de clientes **VIP** se procesan primero
- Clasificación automática al registrar
- No requiere intervención manual

### Validaciones
- ✅ Cliente debe existir
- ✅ Platos deben existir en el menú
- ✅ Platos deben estar disponibles
- ✅ Pedido debe tener al menos un plato

### Interfaz Visual
- Diseño con caracteres Unicode
- Emojis para mejor navegación
- Mensajes claros de éxito/error

---

## 📚 Documentación Adicional

- **[RESUMEN-FASE2.md](RESUMEN-FASE2.md)** - Resumen visual de la Fase 2
- **[FASE2-GESTION-PEDIDOS.md](FASE2-GESTION-PEDIDOS.md)** - Documentación técnica completa
- **[COMANDOS-FASE2.md](COMANDOS-FASE2.md)** - Comandos útiles detallados
- **[ESTRUCTURA.md](ESTRUCTURA.md)** - Estructura del proyecto
- **[MODELO.md](MODELO.md)** - Documentación del modelo

---

## 🐛 Solución de Problemas

### El sistema no compila
```bash
mvn clean
mvn compile
```

### Error al ejecutar
```bash
# Verificar que estás en el directorio correcto
cd TrabajoPractico-Algoritmos2-GarciaL-Barzaghi

# Recompilar y ejecutar
mvn clean compile && mvn exec:java -Dexec.mainClass="org.example.app.Main"
```

### Maven no se encuentra
```bash
# Verificar instalación
mvn --version

# Si no está instalado, descargar desde: https://maven.apache.org/
```

---

## ✅ Requisitos

- ☑️ Java JDK 8 o superior
- ☑️ Maven 3.6 o superior
- ☑️ Terminal/Consola

---

## 🎉 ¡Listo para Usar!

El sistema está completamente funcional y listo para demostrar:

✅ Gestión de pedidos con validaciones  
✅ Clasificación por prioridad (VIP/NORMAL)  
✅ Cola de prioridad funcional  
✅ Menú interactivo escalable  
✅ Estadísticas y consultas  
✅ Uso exclusivo de TDAs propios  

---

**¡Disfruta del sistema!** 🍕🍔🚀

