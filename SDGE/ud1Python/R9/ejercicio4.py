import csv
import sys
from pathlib import Path

# RUTAS
CARPETA_SCRIPT = Path(__file__).resolve().parent
# Creamos una subcarpeta dedicada para comprobar su existencia
RUTA_ARCHIVO = CARPETA_SCRIPT / "Datos"
NOMBRE_ARCHIVO = "productos.csv"
ARCHIVO = RUTA_ARCHIVO / NOMBRE_ARCHIVO

sys.path.append(str(CARPETA_SCRIPT.parents[2] / "Bibliotecas"))

import ficheros
import utiles

# TITULOS DE LAS COLUMNAS DEL CSV
CAMPOS = ["nombre", "precio", "stock"]  # títulos de la cabecera


# MENSAJES
PETICION_NUMERO_PRODUCTOS = "Ingrese el número de productos que desea escribir: "
PETICION_NOMBRE = "Ingrese el nombre del producto: "
PETICION_PRECIO = "Ingrese el precio del producto: "
PETICION_STOCK = "Ingrese el stock del producto: "
PETICION_CREAR_CARPETA = f"La carpeta {RUTA_ARCHIVO} no existe. ¿Quieres crearla? (s/n): "
PETICION_CREAR_ARCHIVO = f"El archivo {ARCHIVO} no existe. ¿Quieres crearlo? (s/n): "
MENSAJE_ERROR = "¡ERROR! No debes dejar este campo vacío"
MENSAJE_ERROR_NUMERO_ENTERO = "¡ERROR! Debes ingresar un número entero mayor o igual a 0."
MENSAJE_ERROR_NUMERO_DOUBLE = "¡ERROR! Debes ingresar un número mayor o igual a 0."
MENSAJE_ERROR_ARCHIVO = "ERROR: No se pudo acceder al archivo: "
MENSAJE_ERROR_NO_ES_ARCHIVO = "ERROR: La ruta existe pero no es un archivo: "
MENSAJE_SALIDA = "Programa finalizado, no se ha creado nada."
MENSAJE_ARCHIVO_NUEVO = "Archivo creado correctamente."
MENSAJE_ARCHIVO_EXISTENTE = "El archivo ya existe, se añadirán los productos al final."
MENSAJE_ERROR_PERMISOS = "¡ERROR! No tienes los permisos suficientes."

# COMPROBACIONES
# COMPRUEBA RUTA - LA CREA
if not RUTA_ARCHIVO.exists():
    if not utiles.confirmar_accion(PETICION_CREAR_CARPETA):
        print(MENSAJE_SALIDA)
        sys.exit()
    try:
        # parents=True permite crear todas las carpetas que falten de la ruta
        RUTA_ARCHIVO.mkdir(parents=True)
    except PermissionError as error_permisos:
        print(f"{MENSAJE_ERROR_PERMISOS}{error_permisos}")
        sys.exit()

# COMPRUEBA ARCHIVO - LO CREA
if not ARCHIVO.is_file():
    if not utiles.confirmar_accion(PETICION_CREAR_ARCHIVO):
        print(MENSAJE_SALIDA)
        sys.exit()
    try:
        ARCHIVO.touch()
    except PermissionError as error_permisos:
        print(f"{MENSAJE_ERROR_PERMISOS}{error_permisos}")
        sys.exit()
    print(MENSAJE_ARCHIVO_NUEVO)
else:
    print(MENSAJE_ARCHIVO_EXISTENTE)

escribir_cabecera = ficheros.is_vacio(ARCHIVO)

# PETICIONES
productos = []

numero_productos = utiles.pedir_numero_entero_no_negativo(
    PETICION_NUMERO_PRODUCTOS, MENSAJE_ERROR, MENSAJE_ERROR_NUMERO_ENTERO
)

for i in range(1, numero_productos + 1):
    print(f"\n======= PRODUCTO {i} =======")
    producto = {
        "nombre": utiles.pedir_cadena_no_vacia(PETICION_NOMBRE, MENSAJE_ERROR),
        "precio": utiles.pedir_numero_no_negativo(
            PETICION_PRECIO, MENSAJE_ERROR, MENSAJE_ERROR_NUMERO_DOUBLE
        ),
        "stock": utiles.pedir_numero_entero_no_negativo(
            PETICION_STOCK, MENSAJE_ERROR, MENSAJE_ERROR_NUMERO_ENTERO
        ),
    }
    productos.append(producto)

# ESCRITURA
try:
    with open(ARCHIVO, "a", encoding="utf-8", newline="") as archivo:
        escritor_csv = csv.DictWriter(archivo, fieldnames=CAMPOS, delimiter=";")
        if escribir_cabecera:
            escritor_csv.writeheader()
        escritor_csv.writerows(productos)
        print(f"{len(productos)} producto(s) guardado(s) en: {ARCHIVO}")
except PermissionError as error_escritura:
    print(f"{MENSAJE_ERROR_PERMISOS} {error_escritura}")
    sys.exit()
    
    
# LECTURA Y MUESTRA POR PANTALLA DEL CSV
print("\n======= CONTENIDO DEL ARCHIVO CSV =======")
try:
    with open(ARCHIVO, "r", encoding="utf-8") as archivo_lectura:
        lector_csv = csv.DictReader(archivo_lectura, delimiter=";")
        
        # Muestra la cabecera formateada
        print(f"{'NOMBRE':<24}{'PRECIO':<10}{'STOCK':<10}")
        print("-" * 44)
    
        # Muestra cada fila alineada con la cabecera
        for fila in lector_csv:
            nombre = fila['nombre']
            # Si el nombre mide más de 24 caracteres, se recorta y se le añaden ... 
            if len(nombre) > 24:
                nombre = nombre[:21] + "..."
                
            print(f"{nombre:<24}{fila['precio']:<10}{fila['stock']:<10}")
except PermissionError:
    print(f"{MENSAJE_ERROR_PERMISOS} para mostrar el csv")


