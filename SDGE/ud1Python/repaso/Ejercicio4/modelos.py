"""Modelos del servicio técnico: herencia Trabajo -> Reparacion / AsistenciaRemota."""

ESTADOS = ("pendiente", "en curso", "finalizado")
TARIFA_REPARACION = 25.0
TARIFA_REMOTA = 20.0


class Trabajo:
    """Clase base: datos y estados comunes a todos los trabajos."""

    tipo = "trabajo"

    def __init__(self, codigo, cliente, horas, estado="pendiente"):
        codigo = str(codigo).strip()
        cliente = str(cliente).strip()
        if not codigo:
            raise ValueError("El código no puede estar vacío.")
        if not cliente:
            raise ValueError("El cliente no puede estar vacío.")
        horas = float(horas)
        if horas <= 0:
            raise ValueError("Las horas deben ser mayores que cero.")
        if estado not in ESTADOS:
            raise ValueError(f"Estado desconocido: {estado!r}.")
        self.codigo = codigo
        self.cliente = cliente
        self.horas = horas
        self.estado = estado

    def calcular_importe(self):
        raise NotImplementedError("Cada subtipo define su cálculo.")

    def cambiar_estado(self, nuevo):
        """Solo se permite avanzar un paso: pendiente -> en curso -> finalizado."""
        if nuevo not in ESTADOS:
            raise ValueError(f"Estado desconocido: {nuevo!r}.")
        if ESTADOS.index(nuevo) != ESTADOS.index(self.estado) + 1:
            raise ValueError(f"Transición no permitida: {self.estado} -> {nuevo}.")
        self.estado = nuevo

    def cambiar_horas(self, horas):
        """Ampliación: solo si el trabajo no está finalizado."""
        if self.estado == "finalizado":
            raise ValueError("No se puede editar un trabajo finalizado.")
        horas = float(horas)
        if horas <= 0:
            raise ValueError("Las horas deben ser mayores que cero.")
        self.horas = horas

    @property
    def precio_pieza(self):
        return 0.0

    def __str__(self):
        return (f"{self.codigo:<8} {self.tipo:<10} {self.cliente:<18} "
                f"{self.horas:>5.1f} h  {self.estado:<10} {self.calcular_importe():>8.2f} €")


class Reparacion(Trabajo):
    tipo = "reparacion"

    def __init__(self, codigo, cliente, horas, precio_pieza=0.0, estado="pendiente"):
        super().__init__(codigo, cliente, horas, estado)
        precio_pieza = float(precio_pieza)
        if precio_pieza < 0:
            raise ValueError("El precio de la pieza no puede ser negativo.")
        self._precio_pieza = precio_pieza

    @property
    def precio_pieza(self):
        return self._precio_pieza

    def calcular_importe(self):
        return self.horas * TARIFA_REPARACION + self._precio_pieza


class AsistenciaRemota(Trabajo):
    tipo = "remota"

    def calcular_importe(self):
        return self.horas * TARIFA_REMOTA


def crear_trabajo(tipo, codigo, cliente, horas, precio_pieza=0.0, estado="pendiente"):
    """Fábrica: reconstruye el subtipo correcto a partir del campo tipo."""
    tipo = str(tipo).strip().lower()
    if tipo == Reparacion.tipo:
        return Reparacion(codigo, cliente, horas, precio_pieza, estado)
    if tipo == AsistenciaRemota.tipo:
        return AsistenciaRemota(codigo, cliente, horas, estado)
    raise ValueError(f"Tipo de trabajo desconocido: {tipo!r}.")


class ServicioTecnico:
    """Colección de trabajos (diccionario indexado por código único)."""

    def __init__(self):
        self.trabajos = {}

    def alta(self, trabajo):
        if trabajo.codigo in self.trabajos:
            raise ValueError(f"Ya existe un trabajo con el código {trabajo.codigo}.")
        self.trabajos[trabajo.codigo] = trabajo

    def obtener(self, codigo):
        try:
            return self.trabajos[codigo.strip()]
        except KeyError:
            raise ValueError(f"No existe el trabajo {codigo!r}.") from None

    def buscar(self, cliente="", tipo=None, estado=None):
        cliente = cliente.strip().lower()
        return [t for t in self.trabajos.values()
                if cliente in t.cliente.lower()
                and (not tipo or t.tipo == tipo)
                and (not estado or t.estado == estado)]

    def resumen(self):
        por_estado = {e: 0 for e in ESTADOS}
        for t in self.trabajos.values():
            por_estado[t.estado] += 1
        total = sum(t.calcular_importe() for t in self.trabajos.values()
                    if t.estado == "finalizado")
        return por_estado, total