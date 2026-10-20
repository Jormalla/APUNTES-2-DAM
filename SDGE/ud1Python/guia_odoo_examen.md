# Guía examen Odoo 18 — dónde hacer clic

Prefijo en todo registro nuevo: `[REP26] ` (con corchetes, igual en todas las apps).
Prohibido pulsar: **Confirmar**, **Enviar por correo**, **Ganado**, **Perdido**, **Marcar como hecho** (salvo que lo pidan).
Tras cada cambio: guardar (nube), salir y reabrir la ficha para comprobar.

## Índice

0. [Interfaz general](#0-interfaz-general)
1. [Contactos](#1-contactos)
2. [CSV: importar y exportar](#2-csv-importar-y-exportar)
3. [CRM](#3-crm)
4. [Ventas](#4-ventas)
5. [Proyecto](#5-proyecto)
6. [Calendario](#6-calendario)
7. [Caso integrador: enlaces reales vs nombre](#7-caso-integrador-enlaces-reales-vs-nombre)
8. [Auditoría](#8-auditoría)
9. [Errores típicos y cálculos](#9-errores-típicos-y-cálculos)

---

## 0. Interfaz general

| Acción | Dónde |
| --- | --- |
| Cambiar de app | Cuadrícula arriba a la izquierda |
| Crear | Botón **Nuevo** |
| Guardar / descartar | Icono **nube** / **✕** junto a la ruta superior |
| Volver | Clic en la ruta superior (ej. "Flujo", "Proyectos") |
| Cambiar vista | Iconos arriba a la derecha: kanban, lista, calendario… |
| Buscar | Escribir en la barra → Intro |
| Filtrar / agrupar | Flecha **▾** a la derecha de la barra → Filtros / Agrupar por |
| Quitar filtro | **x** de la faceta en la barra |
| Acciones sobre varios | Vista **Lista** → marcar casillas → **⚙ Acciones** |
| Importar | **⚙** junto al título de la vista → **Importar registros** |
| Nota interna | Chatter → **Registrar una nota** → **Registrar** |
| Actividad | Chatter → **Actividades** |

---

## 1. Contactos

### Crear empresa

Contactos → **Nuevo** → selector **Empresa** → Nombre `[REP26] Centro Cauce` → Correo → guardar.
Cliente/proveedor: pestaña **Notas internas** o **Registrar una nota**.

### Crear persona vinculada

- Vía A: **Nuevo** → **Persona** → nombre → campo empresa bajo el nombre → **elegir la existente de la lista** → Puesto de trabajo → Correo → guardar.
- Vía B: abrir empresa → pestaña **Contactos y direcciones** → **Añadir** → Contacto → **Guardar y cerrar**.

Trampa: pulsar "Crear…" en el campo empresa genera una empresa duplicada.

### Buscar

Escribir parte del nombre → Intro. Salen la empresa y sus personas (el nombre completo de la persona incluye la empresa).

![Buscar "cauce": empresa + persona vinculada](img/01_contactos_buscar.png)

### Agrupar por empresa (comprobar vínculos)

**▾** → Agrupar por → **Compañía relacionada**. Cada empresa con su persona; las empresas van al grupo **Ninguno**.

![Agrupado por compañía relacionada](img/02_contactos_agrupar.png)

![Ninguno (7) = 7 empresas, sin duplicados](img/03_contactos_7_empresas.png)

En la imagen anterior: `Oficina RÃo` = tilde rota por codificación mal elegida al importar; `academiamiradoe` = errata. Corregir editando la ficha, no reimportando.

### Búsqueda sin resultados

Las tarjetas grises "Columna 1, 2…" = **no hay resultados**. Aquí se buscó `[REP]`, que no coincide con `[REP26]`. Buscar `REP26`.

![Búsqueda incorrecta: sin resultados](img/04_busqueda_sin_resultados.png)

Agrupar por **Compañía** ≠ **Compañía relacionada**:

| Opción | Qué es |
| --- | --- |
| Compañía | Empresa propietaria del registro en Odoo (YourCompany) |
| Compañía relacionada | Empresa a la que pertenece la persona |

---

## 2. CSV: importar y exportar

### Exportar

Vista **Lista** (no agrupada; agrupada no deja exportar) → marcar casillas → **⚙ Acciones** → **Exportar**.

Estado inicial (mal: XLSX y campos sobrantes):

![Ventana exportar por defecto](img/05_exportar_inicial.png)

Configurado: **CSV**, campos a la derecha con **+** desde la izquierda, sobrantes con la papelera.

![Exportar en CSV con 3 campos](img/06_exportar_csv.png)

- **+** gris = campo ya añadido.
- Casilla **compatible con importación** marcada → cabeceras técnicas + columna `id`. Desmarcada → etiquetas en español, sin `id` (más limpio para la entrega).

![CSV exportado con compatible marcado](img/07_csv_resultado.png)

`complete_name` = Nombre completo (incluye la empresa). Para exportar solo el nombre, elegir **Nombre**. `id` `__export__.res_partner_72_…` = ID externo: reimportar con él actualiza en vez de duplicar.

Exportación final limpia (3 empresas):

```csv
"Nombre","Correo electrónico"
"[REP26] Editorial Alba","editorial.alba@example.test"
"[REP26] Oficina Río","oficina.rio@example.test"
"[REP26] TecnoLuz","tecnoluz@example.test"
```

### Preparar CSV de importación (VS Code, UTF-8 abajo a la derecha)

```csv
name,is_company,email
[REP26] TecnoLuz,True,tecnoluz@example.test
[REP26] Editorial Alba,True,editorial.alba@example.test
[REP26] Oficina Río,True,oficina.rio@example.test
```

Personas vinculadas: `is_company` = `False` + columna `parent_id` con el nombre exacto de la empresa (debe existir antes).

### Importar

1. Contactos → **⚙** junto al título → **Importar registros** → **Subir archivo**.
2. Panel izquierdo: Codificación **utf-8**, Separador **coma**, primera fila como encabezado.
3. Mapeo: `name` → Nombre · `is_company` → Es una compañía · `email` → Correo electrónico.
4. **Probar** → verde. Si la vista previa muestra `Ã`, cambiar codificación y volver a Probar.
5. **Importar** una sola vez.
6. Buscar `REP26` → filtro Empresas → cada una 1 vez.

Archivo defectuoso (fila sin nombre): **Probar** → error en rojo → no importar.

---

## 3. CRM

### Crear oportunidad

CRM → quitar faceta **Mi flujo** si oculta registros → **Nuevo** → tarjeta rápida: Contacto (elegir de la lista), Oportunidad, Ingresos esperados → **Editar** para abrir ficha.

![Creación rápida en columna Nuevo](img/08_crm_creacion_rapida.png)

![Ficha: Contacto, 490 €, etapa Nuevo, Presupuestos 0](img/09_crm_oportunidad.png)

El campo cliente se llama **Contacto**.

### Actividad

Botón **Actividades** → rellenar → **Programar**.

![Ventana Programar actividad](img/10_crm_actividad.png)

| Botón | Efecto |
| --- | --- |
| **Programar** | Queda pendiente (lo normal) |
| Programar y marcar como hecho | Se cierra al momento |
| Hecho y programar siguiente | Cierra y abre otra |

Tipos disponibles: "Por hacer" = **Actividades pendientes**. Llamada = **Llamada**.

![Tipos de actividad](img/11_crm_actividad_tipos.png)

![Actividad pendiente en el chatter](img/12_crm_actividad_programada.png)

Para marcarla hecha después: en el chatter, **✓ Marcar como hecho**. Si piden nota antes, primero **Registrar una nota**.

### Avanzar etapa

Clic en **Calificado** en la barra de etapas (o arrastrar la tarjeta en el kanban). El chatter registra "Nuevo → Calificado". La probabilidad cambia sola.

![Etapa Calificado](img/13_crm_calificado.png)

Falta en esta captura: la **nota interna** (Registrar una nota). La descripción de la actividad no cuenta como nota.

### Filtrar y agrupar

Quitar **Mi flujo** → buscar `REP26` → **▾** → Filtros: abiertas → Agrupar por: **Etapa** → vista Lista.

---

## 4. Ventas

### Crear servicio

Ventas → **Productos** → **Productos** → **Nuevo**.

| Campo | Valor |
| --- | --- |
| Casilla Ventas | ✓ |
| Tipo de producto | **Servicio** |
| Crear en el pedido | **Nada** |
| Precio de venta | 70 |
| Impuesto | 21 % (dejarlo) |

![Producto servicio, sin guardar aún](img/14_producto_servicio.png)

Guardar con la nube.
Unidad **Horas**: Ventas → Configuración → Ajustes → **Unidades de medida** ✓ → Guardar → aparece el campo en el producto.

### Presupuesto desde la oportunidad

Oportunidad → **Nuevo presupuesto** → cliente ya puesto → **Añadir un producto** → producto + cantidad.

![Presupuesto editando la segunda línea](img/15_presupuesto_editando.png)

![Presupuesto: base 490 €, estado Presupuesto](img/16_presupuesto_490.png)

- Comprobar el **Importe base** (sin IVA). Total con IVA no importa.
- Producto correcto: **Preparación de puesto informático** (en la captura se eligió otro con el mismo precio: corregir).
- Título "Nuevo" = sin guardar → nube → pasa a `S000XX`.
- Guardar mantiene **Presupuesto** (borrador). Confirmar → Pedido de venta (error).
- Enlace: pestaña **Otra Información** → Oportunidad; en la oportunidad, botón **Presupuestos: 1**.
- Ingresos esperados de la oportunidad no cambia con el presupuesto.

---

## 5. Proyecto

### Etapas

Proyecto nuevo sin etapas: columnas grises de muestra (no reales). Panel izquierdo **Etapa…** → nombre → **Añadir**. Repetir.

![Proyecto sin etapas](img/17_proyecto_sin_etapas.png)

![Etapas Pendiente y Finalizado; añadir En curso](img/18_proyecto_etapas.png)

### Tareas

**+** en la cabecera de la columna → título → **Añadir** (sigue abierto para la siguiente). **Editar** abre la ficha.

![Creación rápida de tareas](img/19_proyecto_crear_tarea.png)

Fecha límite: abrir tarea → **Fecha límite** → guardar. La tarjeta muestra "En X días".

![3 tareas con fecha límite](img/20_proyecto_fechas.png)

Cambiar etapa: arrastrar la tarjeta. Resultado final con 2 etapas:

![Pendiente 3, En curso 2](img/21_proyecto_final.png)

| Concepto | Dónde |
| --- | --- |
| **Etapa** | Columna del tablero / barra en la ficha |
| **Estado** | Círculo de la tarjeta (En curso, Cambios solicitados, Aprobado, Cancelado, Hecho) |

- Faceta **Tareas abiertas** oculta las tareas Hecho/Cancelado. Quitarla para contar todas.
- Renombrar proyecto / poner **Cliente**: ⚙ junto al nombre (o Proyectos → tarjeta → ⋮) → **Configuración**.

---

## 6. Calendario

Error típico: la vista calendario **dentro de Proyecto** crea **tareas**, no eventos.

![Error: Nueva tarea desde el calendario de Proyecto](img/22_error_tarea_desde_calendario.png)

Comprobar que la barra morada dice **Calendario**. Si no: **Descartar** → cuadrícula → app **Calendario**.

### Crear evento

Calendario → vista Semana → arrastrar 30 min en una franja (o **Nuevo**) → nombre → **Editar**.

![Ficha del evento: 30 min, 1 asistente](img/23_evento_ficha.png)

- Duración `00:30`, Todo el día desactivado.
- Asistentes: solo tu usuario.
- No pulsar **CORREO ELECTRÓNICO**, **SMS** ni **+ Reunión de Odoo**.
- Guardar con la nube (arriba sigue poniendo "Nuevo" hasta guardar).

![Evento en la vista semana](img/24_calendario_semana.png)

---

## 7. Caso integrador: enlaces reales vs nombre

Orden: Contactos → CRM → Ventas (desde la oportunidad) → Proyecto → Calendario.

| Relación | Tipo | Dónde se ve |
| --- | --- | --- |
| Persona → empresa | Real | Empresa bajo el nombre de la persona |
| Oportunidad → cliente | Real | Campo **Contacto** |
| Presupuesto → oportunidad | Real (creado con Nuevo presupuesto) | Botón **Presupuestos** en la oportunidad |
| Presupuesto → cliente | Real | Campo **Cliente** |
| Proyecto → cliente | Real solo si se rellena Cliente en Configuración | Configuración del proyecto |
| Proyecto → presupuesto | Coincidencia de nombre | Ningún botón los une |
| Evento → oportunidad | Coincidencia de nombre (creado desde Calendario) | Sin campo de enlace |

Recuento buscando `Cauce`: Contactos 2 · CRM 1 · Presupuestos 1 · Proyecto 1 con 5 tareas · Calendario 1.

---

## 8. Auditoría

- [ ] Contactos `REP26`, filtro Empresas: sin nombres repetidos.
- [ ] Cada persona con su empresa (agrupar por Compañía relacionada).
- [ ] Tildes correctas y correos sin erratas.
- [ ] Oportunidades: cliente e ingresos correctos, abiertas, no ganadas.
- [ ] Ventas → Pedidos → Presupuestos → quitar **Mis presupuestos** → todos en estado **Presupuesto**.
- [ ] Importe base de cada presupuesto (tabla de cálculos).
- [ ] Proyecto: nº de tareas y reparto por etapas (quitar Tareas abiertas).
- [ ] Evento: 30 min, sin externos.

Corregir siempre en la ficha existente. Duplicado → abrir el sobrante → ⚙ Acciones → **Archivar**. Presupuesto sobrante → **Cancelar**.

---

## 9. Errores típicos y cálculos

| Error | Evitarlo |
| --- | --- |
| "Crear…" en un campo de empresa/cliente | Elegir de la lista |
| Importar dos veces | Probar, importar una vez |
| Tildes rotas (`Ã`) | Codificación utf-8 al importar |
| Exportar agrupado | Quitar agrupación, vista Lista |
| No ver registros | Quitar facetas por defecto (Mi flujo, Mis presupuestos, Tareas abiertas) |
| Confirmar presupuesto | Solo nube |
| Tarea en lugar de evento | Usar la app Calendario |
| Mezclar `REP26` y `[REP26]` | Un formato en todo |

| Presupuesto | Líneas | Importe base |
| --- | --- | --- |
| Centro Horizonte mantenimiento (inicial) | 4 × 45 + 2 × 30 | 240 € |
| Centro Horizonte mantenimiento (editado) | 6 × 45 + 2 × 30 | 330 € |
| Academia Mirador | 3 × 30 + 2 × 35 | 160 € |
| Centro Cauce | 6 × 70 + 2 × 35 | 490 € |
