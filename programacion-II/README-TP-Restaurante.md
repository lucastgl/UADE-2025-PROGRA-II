
# TP - Sistema de Gestión de Pedidos y Entregas (adaptado a tu repo)

Código agregado en `src/main/java/app/**` que usa **tus TDA** del paquete `structure.*` y utilidades en `util/*`.

## Ejecutar
- Abrí el proyecto en IntelliJ (ya está la carpeta).
- Compilar y correr `app.Main`.
- El menú permite:
  1. Alta de platos/clientes/repartidores
  2. Crear pedidos (VIP/Normal, llevar/domicilio)
  3. Enviar a cocina (QueueADT)
  4. Entregar (GraphADT + SetADT + SimpleDictionaryADT - Dijkstra)
  5. Reportes (diccionarios y multiple dictionary)
  6. Ver colas con `util.*`

## Dónde se usan los TDA
- `PriorityQueueADT`: prioridad de pedidos (VIP primero)
- `QueueADT`: cola de **platos** a preparar
- `GraphADT` + `SetADT` + `SimpleDictionaryADT`: **camino más corto** para entregas
- `SimpleDictionaryADT`: conteos por repartidor y cliente
- `MultipleDictionaryADT`: ranking de platos (tamaño de lista)
