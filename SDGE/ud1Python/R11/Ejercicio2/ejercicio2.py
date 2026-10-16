import csv
import sys
from pathlib import Path

# RUTAS
CARPETA_SCRIPT = Path(__file__).resolve().parent
NOMBRE_ARCHIVO = "clientes.csv"
RUTA_ARCHIVO = CARPETA_SCRIPT / NOMBRE_ARCHIVO

sys.path.append(str(CARPETA_SCRIPT.parents[3] / "Bibliotecas"))
from Cliente import Cliente
import utiles
import ficheros

# MENSAJES
INTERFAZ_MENU = "===== GESTIÓN DE CLIENTES =====\n1) Dar de alta un nuevo cliente\n2) Listar clientes\n3) Buscar cliente\n4) Cambiar estado del cliente\n5) Guardar en CSV\nIngrese una opción: "
MENSAJE_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío."
ERROR_INTERFAZ = "¡ERROR! Debes elegir una opción válida."
PETICION_CREAR_ARCHIVO = "No existe el archvio clientes.csv, ¿Te gustaría crearlo? Sí (s) o No (n): "
MENSAJE_SALIDA = "¡HASTA PRONTO!"
ERROR_PERMISOS = "¡ERROR! No dispones de los permisos necesarios."
CREANDO_ARCHIVO = f"Creando {NOMBRE_ARCHIVO} ..."
CARGANDO_ARCHIVO = f"Cargando {NOMBRE_ARCHIVO} ..."
PETICION_CLIENTE = "Ingrese el nombre del cliete: "
PETICION_MAIL = "Ingrese el correo electrónico: "
PETICION_CIUDAD = "Ingrese la ciudad: "

# COLUMNAS
CAMPOS = ["nombre", "email", "ciudad", "activo"]

# COMPRUEBA SI EL ARCHIVO EXISTE - SI NO. PREGUNTA PARA CREARLO
def comprobar_existencia_archivo():
    if not RUTA_ARCHIVO.is_file():
        if not utiles.confirmar_accion(PETICION_CREAR_ARCHIVO):
            utiles.consola(MENSAJE_SALIDA)
            sys.exit()
        try:
            RUTA_ARCHIVO.touch()
        except PermissionError as error_permisos:
            utiles.consola(f"{ERROR_PERMISOS}{error_permisos}")
            sys.exit()
        utiles.consola(CREANDO_ARCHIVO)
    else:
        utiles.consola(CARGANDO_ARCHIVO)

# DEVUELVE LOS CLIENTES EN LISTA
def cargar_clientes():
    clientes_cargados = []
    try:
        with open(RUTA_ARCHIVO, mode='r', encoding='utf-8') as archivo:
            lector_csv = csv.DictReader(archivo, delimiter=";")
            for fila in lector_csv:
                cliente_cargado = Cliente(fila['nombre'], fila['email'], fila['ciudad'], fila['activo'])
                clientes_cargados.append(cliente_cargado)
    except PermissionError as error_lectura:
        utiles.consola(f"No se pudo cargar los clientes. {ERROR_PERMISOS} {error_lectura}")
        sys.exit()
    return clientes_cargados

# CREAR NUEVO CLIENTE
def nuevo_cliente(clientes_lista: list):
    nombre = utiles.pedir_cadena_no_vacia(PETICION_CLIENTE, MENSAJE_CAMPO_VACIO)
    email = utiles.pedir_cadena_no_vacia(PETICION_MAIL, MENSAJE_CAMPO_VACIO)
    ciudad = utiles.pedir_cadena_no_vacia(PETICION_CIUDAD, MENSAJE_CAMPO_VACIO)
    cliente_nuevo = Cliente(nombre, email, ciudad)
    clientes_lista.append(cliente_nuevo)
    utiles.consola(f"{cliente_nuevo.get_nombre()} creado con éxito.")

# METODO PARA LISTAR CLIENTES
def listar_clientes(clientes_lista: list):
    if len(clientes_lista) == 0:
        utiles.consola("¡VAYA! Parece que aún no hay clientes.")
    else:
        for cliente in clientes_lista:
            utiles.consola(f"-> {cliente}")

# MÉTODO PARA BUSCAR UN CLIENTE
def buscar_cliente(clientes_lista: list):
    if len(clientes_lista) == 0:
        utiles.consola("¡VAYA! Parece que aún no hay clientes.")
    else:
        encontrado = False
        nombre_cliente = utiles.pedir_cadena_no_vacia(PETICION_CLIENTE, MENSAJE_CAMPO_VACIO)
        for cliente in clientes_lista:
            if nombre_cliente == cliente.get_nombre():
                utiles.consola(f"-> {cliente}")
                encontrado = True
                break
        if not encontrado:
            utiles.consola(f"¡VAYA! El cliente {nombre_cliente} no existe")


# ============= MAIN ============== #

def main():
    comprobar_existencia_archivo()
    opcion_elegida = 1
    clientes = cargar_clientes()
    while opcion_elegida != 0:
        opcion_elegida = utiles.pedir_numero_en_rango(0, 5, INTERFAZ_MENU, MENSAJE_CAMPO_VACIO, ERROR_INTERFAZ)

         # OPCION 1 - DAR DE ALTA
        if opcion_elegida == 1:
            nuevo_cliente(clientes)
        # OPCIÓN 2 - LISTAR CLIENTES
        elif opcion_elegida == 2:
            listar_clientes(clientes)
        # OPCIÓN 3 - BUSCAR CLIENTE
        elif opcion_elegida == 3:
            buscar_cliente(clientes)
        elif opcion_elegida == 4:
            utiles.consola("Opción 4")
        elif opcion_elegida == 5:
            utiles.consola("Opción 5")
        else:
            utiles.consola(MENSAJE_SALIDA)

if __name__ == "__main__":
    main()
