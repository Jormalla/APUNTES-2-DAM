from pathlib import Path
import sys

from clases import Producto, Pedido
CARPETA_SCRIPT = Path(__file__).resolve().parent
sys.path.append(str(CARPETA_SCRIPT.parents[3] / "Bibliotecas"))
import utiles

# MENSAJES
INTERFAZ_MENU = "1) Ver productos y stock\n2) Añadir línea al pedido\n3) Listar detalle del pedido\n4) Calcular Total\n0) Salir\nElige una opción: "
ERROR_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío."
ERROR_OPCION_MENU = "¡ERROR! Debes ingresar una opción válida (0-4)."
PETICION_CLIENTE = "Ingrese el nombre del cliente: "
PETICIO_PRODUCTO = "Ingrese el índice del producto: "
ERROR_INDICE_NO_VALIDO = "¡ERROR! Debes elegir un índice válido."
PETICION_CANTIDAD = "Ingrese la cantidad que desea comprar: "
ERROR_CANTIDAD = "La cantidad debe ser mayor a 0."

# FUNCIÓN PARA MOSTRAR TODOS LOS PRODUCTOS
def mostrar_productos(lista_productos):
    lista_en_cadena = "=== PRODUCTOS ===\n"
    for numero, producto in enumerate(lista_productos, start=1):
        lista_en_cadena += f"{numero}- {producto}\n"
    return lista_en_cadena

# MÉTODO PARA TRANSFORMAR EL ÍNDICE A UN NÚMERO INTERNO
def transformar_indice(indice):
    return indice - 1

# MÉTODO PARA AÑADIR UNA LINEA DE PEDIDO AL PEDIDO
def aniadir_linea(productos, indice, pedido: Pedido, cantidad):
    mensaje_a_devolver = ""
    if pedido.agregar_linea(productos[indice], cantidad):
        mensaje_a_devolver = f"Se ha agregado al pedido {cantidad} unidades del producto {productos[indice].get_nombre()}"
    else:
        mensaje_a_devolver = f"¡UPS! Parece que no hay stock suficiente del producto {productos[indice].get_nombre()}. Solo nos quedan {productos[indice].get_stock()} unidades."
    return mensaje_a_devolver
        
# ========== MAIN =============
def main():
    # CREACIÓN DE LOS PRODUCTOS
    productos = [
        Producto("Teclado", 25.50, 8),
        Producto("Ratón", 12.75, 5),
        Producto("Monitor", 149.99, 2)
    ]
    
    # PETICIÓN DEL NOMBRE DEL CLIENTE - CREACIÓN DEL PEDIDO
    cliente = utiles.pedir_cadena_no_vacia(PETICION_CLIENTE, ERROR_CAMPO_VACIO)
    pedido = Pedido(cliente)
    
    # === MENÚ ===
    opcion = 1
    while opcion != 0:
        opcion = utiles.pedir_numero_entero_en_rango(0, 4, INTERFAZ_MENU, ERROR_CAMPO_VACIO, ERROR_OPCION_MENU)
        # OPCION 1- VER PRODUCTOS
        if opcion == 1:
            utiles.consola(mostrar_productos(productos))
        # OPCIÓN 2 - AÑADIR LÍNEA AL PEDIDO
        elif opcion == 2:
            indice = utiles.pedir_numero_entero_en_rango(1, len(productos), f"{mostrar_productos(productos)}\n{PETICIO_PRODUCTO}", ERROR_CAMPO_VACIO, ERROR_INDICE_NO_VALIDO)
            indice = transformar_indice(indice)
            cantidad = utiles.pedir_numero_entero_positivo(PETICION_CANTIDAD, ERROR_CAMPO_VACIO, ERROR_CANTIDAD)
            utiles.consola(aniadir_linea(productos, indice, pedido, cantidad))
        # OPCION 3 - LISTAR DETALLE DEL PEDIDO
        elif opcion == 3:
            utiles.consola(pedido)
        elif opcion == 4:
            utiles.consola(f"--- Total del Pedido: {pedido.total():.2f} €")
        else:
            utiles.consola("¡ADIÓS!")

if __name__ == "__main__":
    main()