import sys
from pathlib import Path

# RUTAS
CARPETA_SCRIPT = Path(__file__).resolve().parent
RAIZ_REPOSITORIO = CARPETA_SCRIPT.parents[2]  # ud1Python | SDGE | APUNTES-2-DAM
sys.path.append(str(RAIZ_REPOSITORIO / "Bibliotecas"))

import utiles
import ficheros
import csv

# MENSAJES
PETICION_NUMERO_PRODUCTOS = "Ingrese el número de productos que desea escribir: "
PETICION_NOMBRE = "Ingrese el nombre del producto: "
PETICION_PRECIO = "Ingrese el precio del producto: "
PETICION_STOCK = "Ingrese el stock del producto: "
PETICION_CREAR_CARPETA = "La carpeta no existe. ¿Quieres crearla? (s/n): "
PETICION_CREAR_ARCHIVO = "El archivo no existe. ¿Quieres crearlo? (s/n): "
MENSAJE_ERROR = "¡ERROR! No debes dejar este campo vacío"
MENSAJE_ERROR_NUMERO_ENTERO = "¡ERROR! Debes ingresar un número entero mayor o igual a 0."
MENSAJE_ERROR_NUMERO_DOUBLE = "¡ERROR! Debes ingresar un número mayor o igual a 0."
MENSAJE_ERROR_ARCHIVO = "ERROR: No se pudo acceder al archivo: "
MENSAJE_ERROR_NO_ES_ARCHIVO = "ERROR: La ruta existe pero no es un archivo: "
MENSAJE_SALIDA = "Programa finalizado, no se ha creado nada."
MENSAJE_ARCHIVO_NUEVO = "Archivo creado correctamente."
MENSAJE_ARCHIVO_EXISTENTE = "El archivo ya existe, se añadirán los productos al final."

# CONSTANTES
NOMBRE_ARCHIVO = "productos.csv"
CAMPOS = ["nombre", "precio", "stock"]  # títulos de la cabecera
RUTA = CARPETA_SCRIPT / NOMBRE_ARCHIVO
CARPETA_ARCHIVO = RUTA.parent


# MÉTODO PARA PREGUNTAR SÍ O NO
def confirmar(mensaje):
    while True:
        respuesta = input(mensaje).strip().lower()
        if respuesta in ("s", "si", "sí"):
            return True
        if respuesta in ("n", "no"):
            return False
        print(MENSAJE_ERROR_SI_NO)


# COMPROBACIONES
# 1. La carpeta
if not ficheros.existe_ruta(CARPETA_ARCHIVO):
    if not confirmar(PETICION_CREAR_CARPETA):
        print(MENSAJE_SALIDA)
        sys.exit(0)
    try:
        CARPETA_ARCHIVO.mkdir(parents=True)
    except OSError as e:
        print(f"{MENSAJE_ERROR_ARCHIVO}{e}")
        sys.exit(1)

# 2. El archivo
if not ficheros.existe_ruta(RUTA):
    if not confirmar(PETICION_CREAR_ARCHIVO):
        print(MENSAJE_SALIDA)
        sys.exit(0)
    try:
        RUTA.touch()
    except OSError as e:
        print(f"{MENSAJE_ERROR_ARCHIVO}{e}")
        sys.exit(1)
    print(MENSAJE_ARCHIVO_NUEVO)
elif not ficheros.es_archivo(RUTA):
    print(f"{MENSAJE_ERROR_NO_ES_ARCHIVO}{RUTA}")
    sys.exit(1)
else:
    print(MENSAJE_ARCHIVO_EXISTENTE)

escribir_cabecera = ficheros.is_vacio(RUTA)

# PETICIONES
productos = []

numero_productos = utiles.pedir_numero_entero_no_negativo(PETICION_NUMERO_PRODUCTOS, MENSAJE_ERROR, MENSAJE_ERROR_NUMERO_ENTERO)

for i in range(1, numero_productos + 1):
    print(f"\n======= PRODUCTO {i} =======")
    producto = {
        "nombre": utiles.pedir_cadena_no_vacia(PETICION_NOMBRE, MENSAJE_ERROR),
        "precio": utiles.pedir_numero_no_negativo(PETICION_PRECIO, MENSAJE_ERROR, MENSAJE_ERROR_NUMERO_DOUBLE),
        "stock": utiles.pedir_numero_entero_no_negativo(PETICION_STOCK, MENSAJE_ERROR, MENSAJE_ERROR_NUMERO_ENTERO)
    }
    productos.append(producto)

# ESCRITURA
try:
    with open(RUTA, "a", encoding="utf-8", newline="") as archivo:
        escritor = csv.DictWriter(archivo, fieldnames=CAMPOS, delimiter=";")
        if escribir_cabecera:
            escritor.writeheader()
        escritor.writerows(productos)
except OSError as e:
    print(f"ERROR: No se pudo escribir en el archivo: {e}")
    sys.exit(1)
else:
    print(f"{len(productos)} producto(s) guardado(s) en: {RUTA}")