"""Servicio técnico: órdenes y herencia. Menú por consola."""
from modelos import ESTADOS, AsistenciaRemota, Reparacion, ServicioTecnico
from persistencia import cargar_csv, guardar_csv

RUTA = "trabajos.csv"
MENU = """
1) Listar · 2) Alta reparación · 3) Alta remota · 4) Cambiar estado
5) Buscar/filtrar · 6) Resumen · 7) Guardar · 8) Cambiar horas · 0) Salir"""


def numero(texto):
    return float(input(texto).strip().replace(",", "."))


def mostrar(trabajos):
    if not trabajos:
        print("No hay trabajos.")
    for t in trabajos:
        print(t)


def alta(servicio, clase):
    codigo = input("Código: ")
    cliente = input("Cliente: ")
    horas = numero("Horas: ")
    if clase is Reparacion:
        servicio.alta(Reparacion(codigo, cliente, horas, numero("Precio pieza (€): ")))
    else:
        servicio.alta(AsistenciaRemota(codigo, cliente, horas))
    print("Trabajo creado.")


def cambiar_estado(servicio):
    t = servicio.obtener(input("Código: "))
    print(f"Estado actual: {t.estado}")
    t.cambiar_estado(input(f"Nuevo estado {ESTADOS}: ").strip().lower())
    print(f"Estado: {t.estado}")


def buscar(servicio):
    cliente = input("Cliente (vacío = todos): ")
    tipo = input("Tipo [reparacion/remota] (vacío = todos): ").strip().lower() or None
    estado = input("Estado (vacío = todos): ").strip().lower() or None
    mostrar(servicio.buscar(cliente, tipo, estado))


def resumen(servicio):
    por_estado, total = servicio.resumen()
    for e, n in por_estado.items():
        print(f"{e:<11}: {n}")
    print(f"Total finalizados: {total:.2f} €")


def cambiar_horas(servicio):
    t = servicio.obtener(input("Código: "))
    t.cambiar_horas(numero("Nuevas horas: "))
    print(f"Nuevo importe: {t.calcular_importe():.2f} €")


def main():
    servicio = cargar_csv(RUTA)
    acciones = {
        "1": lambda: mostrar(servicio.trabajos.values()),
        "2": lambda: alta(servicio, Reparacion),
        "3": lambda: alta(servicio, AsistenciaRemota),
        "4": lambda: cambiar_estado(servicio),
        "5": lambda: buscar(servicio),
        "6": lambda: resumen(servicio),
        "7": lambda: (guardar_csv(servicio, RUTA), print("Guardado.")),
        "8": lambda: cambiar_horas(servicio),
    }
    while True:
        print(MENU)
        op = input("Opción: ").strip()
        if op == "0":
            print("Hasta luego.")
            break
        if op not in acciones:
            print("Opción no válida.")
            continue
        try:
            acciones[op]()
        except ValueError as e:
            print(f"Error: {e}")


if __name__ == "__main__":
    main()