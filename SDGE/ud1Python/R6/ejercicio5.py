import sys
sys.path.append("C:/Users/DAM/Desktop/Github/APUNTES-2-DAM/Bibliotecas")

import utiles

# Pide un número de productos y un número de meses. Usa dos bucles for anidados
# para mostrar una cuadrícula de textos del tipo "Producto 1 - Mes 1", "Producto 1 -
# Mes 2"... para todas las combinaciones.

# MENSAJES
MENSAJE_PETICION_NUMERO_PRODUCTOS = "Ingrese el número de productos: "
MENSAJE_PETICION_NUMERO_MESES = "Ingrese el número de meses: "
MENSAJE_ERROR_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío."
MENSAJE_ERROR_NUMERO = "¡ERROR! Debes ingresar un número entero mayor o igual a 0."
MENSAJE_ERROR_RANGO = "¡ERROR! Debes ingresar un número entero entre 0 y 12."

# PETICIONES
numero_productos = utiles.pedir_numero_entero_no_negativo(MENSAJE_PETICION_NUMERO_PRODUCTOS, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
numero_meses = utiles.pedir_numero_entero_en_rango(0, 12, MENSAJE_PETICION_NUMERO_MESES, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_RANGO)

# MUESTRA RESULTADO
for i in range(0, numero_productos):
    for j in range(0, numero_meses):
        print(f"Producto {i+1} - Mes {j+1}")