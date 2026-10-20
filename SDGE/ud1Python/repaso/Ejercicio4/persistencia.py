"""Guardado y carga de trabajos en CSV, conservando el subtipo."""
import csv

from modelos import ServicioTecnico, crear_trabajo

CAMPOS = ["tipo", "codigo", "cliente", "horas", "estado", "precio_pieza"]


def guardar_csv(servicio, ruta="trabajos.csv"):
    with open(ruta, "w", newline="", encoding="utf-8") as f:
        w = csv.DictWriter(f, fieldnames=CAMPOS)
        w.writeheader()
        for t in servicio.trabajos.values():
            w.writerow({"tipo": t.tipo, "codigo": t.codigo, "cliente": t.cliente,
                        "horas": t.horas, "estado": t.estado,
                        "precio_pieza": t.precio_pieza})


def cargar_csv(ruta="trabajos.csv"):
    servicio = ServicioTecnico()
    try:
        with open(ruta, newline="", encoding="utf-8") as f:
            for n, fila in enumerate(csv.DictReader(f), start=2):
                try:
                    servicio.alta(crear_trabajo(
                        fila["tipo"], fila["codigo"], fila["cliente"], fila["horas"],
                        fila.get("precio_pieza") or 0, fila["estado"]))
                except (ValueError, KeyError) as e:
                    print(f"Línea {n} ignorada: {e}")
    except FileNotFoundError:
        pass  # primera ejecución: sin datos todavía
    return servicio