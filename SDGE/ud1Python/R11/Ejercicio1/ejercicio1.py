import csv
import sys
from pathlib import Path

# RUTAS
CARPETA_SCRIPT = Path(__file__).resolve().parent
NOMBRE_ARCHIVO = "productos.csv"
RUTA_ARCHIVO = CARPETA_SCRIPT / NOMBRE_ARCHIVO

sys.path.append(str(CARPETA_SCRIPT.parents[3] / "Bibliotecas"))

import ficheros
import utiles

# MENSAJES
PETICION_NUMERO_PRODUCTOS = "Ingrese el número de productos que desea escribir: "
PETICION_NOMBRE = "Ingrese el nombre del producto: "
PETICION_PRECIO = "Ingrese el precio del producto: "
PETICION_STOCK = "Ingrese el stock del producto: "
PETICION_CREAR_ARCHIVO = f"El archivo {NOMBRE_ARCHIVO} no existe. ¿Quieres crearlo? (s/n): "
MENSAJE_ERROR = "¡ERROR! No debes dejar este campo vacío"
MENSAJE_ERROR_NUMERO_ENTERO = "¡ERROR! Debes ingresar un número entero mayor o igual a 0."
MENSAJE_ERROR_NUMERO_DOUBLE = "¡ERROR! Debes ingresar un número mayor o igual a 0."
MENSAJE_ERROR_ARCHIVO = "ERROR: No se pudo acceder al archivo: "
MENSAJE_ERROR_NO_ES_ARCHIVO = "ERROR: La ruta existe pero no es un archivo: "
MENSAJE_SALIDA = "Programa finalizado, no se ha creado nada."
MENSAJE_ARCHIVO_NUEVO = "Archivo creado correctamente."
MENSAJE_ARCHIVO_EXISTENTE = "El archivo ya existe, se añadirán los productos al final."
MENSAJE_ERROR_PERMISOS = "¡ERROR! No tienes los permisos suficientes."

# TITULOS DE LAS COLUMNAS DEL CSV
COLUMNAS = ["nombre", "precio", "stock"]  # títulos de la cabecera

# MOSTRAR POR PANTALLA
def consola(mensaje):
    print(mensaje)

# COMPRUEBA ARCHIVO - LO CREA
def comprobar_existencia_archivo():
    if not RUTA_ARCHIVO.is_file():
        if not utiles.confirmar_accion(PETICION_CREAR_ARCHIVO):
            consola(MENSAJE_SALIDA)
            sys.exit()
        try:
            RUTA_ARCHIVO.touch()
        except PermissionError as error_permisos:
            consola(f"{MENSAJE_ERROR_PERMISOS}{error_permisos}")
            sys.exit()
        consola(MENSAJE_ARCHIVO_NUEVO)
    else:
        consola(MENSAJE_ARCHIVO_EXISTENTE)

# COMPRUEBA SI EL ARCHIVO TIENE CONTENIDO MIRANDO EL TAMAÑO DE SUS BYTES
def no_tiene_contenido():
    return ficheros.esta_vacio(RUTA_ARCHIVO)

# MÉTODO PARA LISTAR LOS PRODUCTOS
def listar_productos():
    visualizacion_productos = "\n======= CONTENIDO DEL ARCHIVO CSV ======="
    try:
        with open(RUTA_ARCHIVO, "r", encoding="utf-8") as archivo_lectura:
            lector_csv = csv.DictReader(archivo_lectura, delimiter=";")
        
            # Muestra la cabecera formateada
            visualizacion_productos += f"{'NOMBRE':<24}{'PRECIO':<10}{'STOCK':<10}"
            visualizacion_productos += "-" * 44
    
            # Muestra cada fila alineada con la cabecera
            for fila in lector_csv:
                nombre = fila['nombre']
                # Si el nombre mide más de 24 caracteres, se recorta y se le añaden ... 
                if len(nombre) > 24:
                    nombre = nombre[:20] + "..."
                
                visualizacion_productos += f"{nombre:<24}{f'{fila['precio']}€':<10}{fila['stock']:<10}"
                return visualizacion_productos
    except PermissionError:
        consola(f"{MENSAJE_ERROR_PERMISOS} para mostrar el csv")

def aniadir_producto(producto_a_aniadir):
    try:
        with open(RUTA_ARCHIVO, "a", encoding="utf-8", newline="") as archivo:
            escritor_csv = csv.DictWriter(archivo, fieldnames=COLUMNAS, delimiter=";")
            if no_tiene_contenido():
                escritor_csv.writeheader()
            escritor_csv.writerows(producto_a_aniadir)
            consola(f"{producto_a_aniadir}  guardado en: {RUTA_ARCHIVO}")
    except PermissionError as error_escritura:
        consola(f"{MENSAJE_ERROR_PERMISOS} {error_escritura}")
        sys.exit()