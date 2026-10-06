import sys
sys.path.append("C:/Users/DAM/Desktop/Github/APUNTES-2-DAM/Bibliotecas")

import MisUtiles

# Mensajes
MENSAJE_PETICION_PRODUCTO = "Ingrese el producto: "
MENSAJE_ERROR_CAMPO_VACIO = "¡ERROR! Este campo no debe estar vacío"
MENSAJE_CANTIDAD_PRODUCTO = "Ingrese la cantidad del producto: "
MENSAJE_ERROR_NUMERO = "Debes ingresar un número mayor o igual a 0"
MENSAJE_PRECIO_PRODUCTO = "Ingrese el precio del producto: "
MENSAJE_PETICION_PORCENTAJE = "Ingrese el porcentaje de descuento: "
MENSAJE_ERROR_PORCENTAJE = "¡ERROR! Debes ingresar un número entre 0 y 100"

# Peticiones
producto = MisUtiles.pedir_cadena_no_vacia(MENSAJE_PETICION_PRODUCTO, MENSAJE_ERROR_CAMPO_VACIO)
precio = MisUtiles.pedir_numero_no_negativo(MENSAJE_CANTIDAD_PRODUCTO, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
cantidad = MisUtiles.pedir_numero_no_negativo(MENSAJE_PRECIO_PRODUCTO, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
porcentaje_descuento = MisUtiles.pedir_numero_en_rango(0, 100, MENSAJE_PETICION_PORCENTAJE, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_PORCENTAJE)

# Calculos
porcentaje_descuento_multiplicador = porcentaje_descuento / 100
descuento = precio * cantidad * porcentaje_descuento_multiplicador
precio_final =  precio * cantidad - descuento

# Salida por pantall
print(f"Producto: {producto} | Cantidad: {cantidad}\nDescuento del: {porcentaje_descuento:.2f}% | Precio final: {precio_final:.2f}€")
