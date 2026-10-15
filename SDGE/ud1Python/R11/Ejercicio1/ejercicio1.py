import utiles
import ficheros
import csv
import sys
from pathlib import Path

# RUTAS
CARPETA_SCRIPT = Path(__file__).resolve().parent
NOMBRE_ARCHIVO = "productos.csv"
RUTA_ARCHIVO = CARPETA_SCRIPT / NOMBRE_ARCHIVO

sys.path.append(str(CARPETA_SCRIPT.parents[3] / "Bibliotecas"))


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
INTERFAZ_MENU = "===== MENU =====\n1) Listar productos\n2) Añadir producto\n3) Modificar stock\n4) Buscar por nombre\n0) Salir\nIngrese una opción: "
ERROR_INTERFAZ = "¡ERROR! Debes ingresar una opción válida."

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
            visualizacion_productos += f"\n{'NOMBRE':<24}{'PRECIO':<10}{'STOCK':<10}"
            visualizacion_productos += "\n" + "-" * 44

            # Muestra cada fila alineada con la cabecera
            for fila in lector_csv:
                nombre = fila['nombre']
                # Si el nombre mide más de 24 caracteres, se recorta y se le añaden ...
                if len(nombre) > 24:
                    nombre = name[:20] + "..."

                visualizacion_productos += f"\n{nombre:<24}{f'{fila['precio']}€':<10}{
                    fila['stock']:<10}"

            return visualizacion_productos
    except PermissionError:
        consola(f"{MENSAJE_ERROR_PERMISOS} para mostrar el csv")

# METODO PARA AÑADIR PRODUCTOS


def aniadir_producto(producto_a_aniadir):
    try:
        with open(RUTA_ARCHIVO, "a", encoding="utf-8", newline="") as archivo:
            escritor_csv = csv.DictWriter(
                archivo, fieldnames=COLUMNAS, delimiter=";")
            if no_tiene_contenido():
                escritor_csv.writeheader()
            escritor_csv.writerow(producto_a_aniadir)
            consola(f"{producto_a_aniadir}  guardado en: {RUTA_ARCHIVO}")
    except PermissionError as error_escritura:
        consola(f"{MENSAJE_ERROR_PERMISOS} {error_escritura}")
        sys.exit()

# COMPROBAR EL NOMBRE DEL PRODUCTO


def contolar_nombre_producto():
    nombre_producto = utiles.pedir_cadena_no_vacia(
        PETICION_NOMBRE, MENSAJE_ERROR).strip()
    if not no_tiene_contenido():
        if buscar_producto(nombre_producto) is not None:
            consola(f"¡VAYA! Parece que ya existe el producto")
            return None  # MODIFICADO: Retorna None en vez de forzar sys.exit()
        else:
            return nombre_producto
    else:
        return nombre_producto

# MÉTODO PARA CREAR UN PRODUCTO


def crear_producto():
    nombre = contolar_nombre_producto()
    if nombre is None:
        return None  # MODIFICADO: Cancela el diccionario si el producto está repetido

    producto_a_crear = {
        "nombre": nombre,
        "precio": utiles.pedir_numero_no_negativo(PETICION_PRECIO, MENSAJE_ERROR, MENSAJE_ERROR_NUMERO_DOUBLE),
        "stock": utiles.pedir_numero_entero_no_negativo(PETICION_STOCK, MENSAJE_ERROR, MENSAJE_ERROR_NUMERO_ENTERO)
    }
    return producto_a_crear

# BUSCAR PRODUCTO POR NOMBRE - DEVUELVE UN DICCIONARIO CON EL PRODUCTO


def buscar_producto(nombre_producto):
    producto_encontrado = None
    try:
        with open(RUTA_ARCHIVO, mode='r', encoding='utf-8') as archivo:
            lector_csv = csv.DictReader(archivo, delimiter=";")
            for fila in lector_csv:
                if nombre_producto.upper() == fila['nombre'].upper():
                    diccionario_producto = {
                        "nombre": fila['nombre'],
                        "precio": fila['precio'],
                        "stock": fila['stock']
                    }
                    producto_encontrado = diccionario_producto
        return producto_encontrado
    except PermissionError as error_lectura:
        consola(f"{MENSAJE_ERROR_PERMISOS} {error_lectura}")
        sys.exit()

# COMPROBAR SI EL NOMBRE DEL PRODUCTO EXISTE - DEVUELVE TRUE SI SE PUDO - FALSE SI NO


def modificar_stock(nombre_producto, nuevo_stock):
    se_pudo_modificar = False
    producto_a_cambiar = buscar_producto(nombre_producto)
    if producto_a_cambiar != None:
        productos_modificados = []
        producto_a_cambiar['stock'] = nuevo_stock
        try:
            with open(RUTA_ARCHIVO, mode='r', encoding='utf-8') as archivo:
                lector_csv = csv.DictReader(archivo, delimiter=";")
                for fila in lector_csv:
                    if nombre_producto.upper() == fila['nombre'].upper():
                        productos_modificados.append(producto_a_cambiar)
                    else:
                        producto_que_existia = {
                            "nombre": fila['nombre'],
                            "precio": fila['precio'],
                            "stock": fila['stock']
                        }
                        productos_modificados.append(producto_que_existia)
        except PermissionError as error_lectura:
            consola(f"{MENSAJE_ERROR_PERMISOS} {error_lectura}")
            sys.exit()
        try:
            with open(RUTA_ARCHIVO, "w", encoding="utf-8", newline="") as archivo:
                escritor_csv = csv.DictWriter(
                    archivo, fieldnames=COLUMNAS, delimiter=";")
                escritor_csv.writeheader()
                escritor_csv.writerows(productos_modificados)
        except PermissionError as error_escritura:
            consola(f"{MENSAJE_ERROR_PERMISOS} {error_escritura}")
            sys.exit()
        se_pudo_modificar = True
    return se_pudo_modificar

# Devuelve una cadena con los datos del producto


def mostrar_producto(producto):
    return f"{producto['nombre']} | {producto['precio']} € | {producto['stock']} unidades"

# ================ MAIN ===============


def main():
    comprobar_existencia_archivo()
    opcion_elegida = ""
    while opcion_elegida != 0:

        opcion_elegida = utiles.pedir_numero_en_rango(
            0, 4, INTERFAZ_MENU, MENSAJE_ERROR, ERROR_INTERFAZ)

        # Lista los productos
        if opcion_elegida == 1:
            if not no_tiene_contenido():
                consola(listar_productos())
            else:
                consola(f"¡VAYA! Aun no hay productos en el archivo")
        # Añade un nuevo producto
        elif opcion_elegida == 2:
            producto_nuevo = crear_producto()
            if producto_nuevo is not None:
                aniadir_producto(producto_nuevo)
            else:
                consola("Regresando al menú principal...")
        # Modifica el stock del producto
        elif opcion_elegida == 3:
            if modificar_stock(utiles.pedir_cadena_no_vacia(PETICION_NOMBRE, MENSAJE_ERROR).strip(), utiles.pedir_numero_entero_no_negativo(PETICION_STOCK, MENSAJE_ERROR, MENSAJE_ERROR_NUMERO_ENTERO)):
                consola(f"Producto modificado.")
            else:
                consola("El producto no existe. No se pudo modificar")
        # Muestra un producto
        elif opcion_elegida == 4:
            if not no_tiene_contenido():
                producto_buscado = buscar_producto(
                    utiles.pedir_cadena_no_vacia(PETICION_NOMBRE, MENSAJE_ERROR).strip())
                if producto_buscado is not None:
                    consola(mostrar_producto(producto_buscado))
                else:
                    consola("El producto no existe.")
            else:
                consola(f"¡VAYA! Aun no hay productos en el archivo")
        else:
            consola("ADIOS")


if __name__ == "__main__":
    main()
