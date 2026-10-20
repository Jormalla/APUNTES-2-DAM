"""UD1 · PT1 · Persistencia CSV. Sin print() ni input()."""
import csv
from modelos import Bicicleta
CAMPOS = ["codigo", "propietario", "modelo", "valor", "estado"]


def cargar_bicicletas(ruta):
    """Lee el CSV y devuelve una lista de objetos Bicicleta.

    Convierte el valor a número y respeta el estado del archivo.
    La primera línea contiene las cabeceras indicadas en CAMPOS.
    """
    bicicletas_cargadas = []
    with open(ruta, mode='r', encoding='utf-8') as archivo:
        lector_csv = csv.DictReader(archivo, delimiter=",")
        for fila in lector_csv:
            bicileta_cargada = Bicicleta(
                fila['codigo'], fila['propietario'], fila['modelo'], float(fila['valor']), fila['estado'])
            bicicletas_cargadas.append(bicileta_cargada)
    return bicicletas_cargadas


def guardar_bicicletas(ruta, bicicletas):
    """Escribe el CSV con cabecera y todas las bicicletas; devuelve True al guardar."""
    try:
        with open(ruta, "w", encoding="utf-8", newline="") as archivo:
            escritor_csv = csv.DictWriter(
                archivo, fieldnames=CAMPOS, delimiter=",")
            escritor_csv.writeheader()
            bicicletas_nuevas = []
            for bici in bicicletas:
                fila = {
                    "codigo": bici.codigo,
                    "propietario": bici.propietario,
                    "modelo": bici.modelo,
                    "valor": bici.valor,
                    "estado": bici.estado
                    }
                bicicletas_nuevas.append(fila)
            escritor_csv.writerows(bicicletas_nuevas)
            return True
    except PermissionError as error_escritura:
        return False
