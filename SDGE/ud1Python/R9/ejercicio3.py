import sys
from pathlib import Path

# RUTAS
CARPETA_SCRIPT = Path(__file__).resolve().parent
RAIZ_REPOSITORIO = CARPETA_SCRIPT.parents[2]  # ud1Python -> SDGE -> APUNTES-2-DAM
sys.path.append(str(RAIZ_REPOSITORIO / "Bibliotecas"))

import utiles

# MENSAJES
PETICION_NOMBRE = "Ingrese el nombre del cliente "
PETICION_CIUDAD = "Ingrese el nombre de la ciudad del cliente "
MENSAJE_ERROR = "¡ERROR! No debes dejar este campo vacío"
MENSAJE_ERROR_ARCHIVO = "ERROR: No se pudo escribir el archivo: "

# CLIENTES
NUMERO_CLIENTES = 3
clientes = []

# CREA UN DICCIONARIO POR CADA CLIENTE Y LO AÑADE A LA LISTA DE CLIENTES
for i in range(1, NUMERO_CLIENTES + 1):
    cliente = {
        "nombre": utiles.pedir_cadena_no_vacia(f"{PETICION_NOMBRE}{i}: ", MENSAJE_ERROR),
        "ciudad": utiles.pedir_cadena_no_vacia(f"{PETICION_CIUDAD}{i}: ", MENSAJE_ERROR)
    }
    clientes.append(cliente)


# ESCRITURA
ruta_archivo = CARPETA_SCRIPT / "clientes.txt"

try:
    with open(ruta_archivo, "w", encoding="utf-8") as archivo:
        for cliente in clientes:
            archivo.write(f"{cliente['nombre']};{cliente['ciudad']}\n")
except PermissionError:
    print(f"{MENSAJE_ERROR_ARCHIVO}no tienes permisos en {ruta_archivo}")
except FileNotFoundError:
    print(f"{MENSAJE_ERROR_ARCHIVO}la carpeta {CARPETA_SCRIPT} no existe")
except OSError as e:
    print(f"{MENSAJE_ERROR_ARCHIVO}{e}")
