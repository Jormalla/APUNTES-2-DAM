import sys
sys.path.append("C:/Users/DAM/Desktop/Github/APUNTES-2-DAM/Bibliotecas")

import utiles

# Mensajes
MENSAJE_PETICION_PRODUCTO = "Ingrese el producto: "
MENSAJE_ERROR_CAMPO_VACIO = "¡ERROR! Este campo no debe estar vacío"
MENSAJE_CANTIDAD_INICIAL = "Ingrese la cantidad inicial del producto: "
MENSAJE_ERROR_NUMERO = "Debes ingresar un número mayor o igual a 0"
MENSAJE_PETICION_RECIBIDAS = "Ingrese la cantidad del producto recibido: "
MENSAJE_PETICION_VENDIDAS = "Ingrese las unidades vendidas: "
MENSAJE_ERROR_UNIDADES_VENDIDAS = "¡ERROR! Las unidades vendidas deben ser mayor o iguales a 0 y menores o iguales que las existencias totales"

# Peticiones
producto = utiles.pedir_cadena_no_vacia(MENSAJE_PETICION_PRODUCTO, MENSAJE_ERROR_CAMPO_VACIO)
existencias_iniciales = utiles.pedir_numero_entero_no_negativo(MENSAJE_CANTIDAD_INICIAL, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
unidades_recibidas = utiles.pedir_numero_entero_no_negativo(MENSAJE_PETICION_RECIBIDAS, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
unidades_vendidas = utiles.pedir_numero_entero_en_rango(0, existencias_iniciales + unidades_recibidas, MENSAJE_PETICION_VENDIDAS, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_UNIDADES_VENDIDAS)

# Muestra datos
print(f"Producto: {producto} | Stock Inicial: {existencias_iniciales}\nUnidades Recibidas: {unidades_recibidas} | Unidades vendidas: {unidades_vendidas}\nQuedan: {existencias_iniciales + unidades_recibidas - unidades_vendidas}")
