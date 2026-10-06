import sys
sys.path.append("C:/Users/DAM/Desktop/Github/APUNTES-2-DAM/Bibliotecas")

import utiles

# MENSAJES
MENSAJE_PETICION_CLIENTE = "Ingrese el nombre del cliente: "
MENSAJE_ERROR_CAMPO_VACIO = "¡ERROR! Este campo no debe estar vacío"
MENSAJE_PETICION_PRODUCTO = "Ingrese el nombre del producto "
MENSAJE_PETICION_PRECIO_PRODUCTO = "Ingrese el precio del producto "
MENSAJE_ERROR_NUMERO = "¡ERROR! Debes ingresar un número mayor a 0"
MENSAJE_CANTIDAD_PRODUCTO = "Ingrese la cantidad del producto "

# PETICIONES
cliente = utiles.pedir_cadena_no_vacia(MENSAJE_PETICION_CLIENTE, MENSAJE_ERROR_CAMPO_VACIO)
nombre_primer_producto = utiles.pedir_cadena_no_vacia(MENSAJE_PETICION_PRODUCTO + " 1: ", MENSAJE_ERROR_CAMPO_VACIO)
precio_primer_producto = utiles.pedir_numero_no_negativo(MENSAJE_PETICION_PRECIO_PRODUCTO + nombre_primer_producto + ": ", MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
cantidad_primer_producto = utiles.pedir_numero_no_negativo(MENSAJE_CANTIDAD_PRODUCTO + nombre_primer_producto + ": ", MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
nombre_segundo_producto = utiles.pedir_cadena_no_vacia(MENSAJE_PETICION_PRODUCTO + " 2: ", MENSAJE_ERROR_CAMPO_VACIO)
precio_segundo_producto = utiles.pedir_numero_no_negativo(MENSAJE_PETICION_PRECIO_PRODUCTO + nombre_segundo_producto + ": ", MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
cantidad_segundo_producto = utiles.pedir_numero_no_negativo(MENSAJE_CANTIDAD_PRODUCTO + nombre_segundo_producto + ": ", MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)

# CALCULOS
subtotal_primer_producto = precio_primer_producto * cantidad_primer_producto
subtotal_segundo_producto = precio_segundo_producto * cantidad_segundo_producto
total = subtotal_primer_producto + subtotal_segundo_producto

# MOSTRAR POR PANTALLA
print(f"Productos: {nombre_primer_producto} y {nombre_segundo_producto} | Cantidades: {cantidad_primer_producto:.0f} y {cantidad_segundo_producto:.0f}\nCostes: {subtotal_primer_producto:.2f}€ y {subtotal_segundo_producto:.2f}€\nTotal: {total:.2f}€")