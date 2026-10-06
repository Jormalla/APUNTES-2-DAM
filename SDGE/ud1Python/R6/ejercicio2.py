import sys
sys.path.append("C:/Users/DAM/Desktop/Github/APUNTES-2-DAM/Bibliotecas")

import utiles

# Pide las ventas de 7 días mediante un bucle for. Acumula el total y calcula la
# media. Muestra ambos valores con dos decimales. No utilices todavía una lista:
# procesa cada dato a medida que se introduce.

# MENSAJES
MENSAJE_PETICION_VENTA = "Ingrese las ventas del día "
MENSAJE_ERROR_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío."
MENSAJE_ERROR_NUMERO = "¡ERROR! Debes ingresar un número mayor o igual a 0."

# VARIABLES
CANTIDAD_DIAS = 7
total = 0
# PETICIONES
for i in range(0, CANTIDAD_DIAS):
    ventas_dia = utiles.pedir_numero_no_negativo(f"{MENSAJE_PETICION_VENTA}{i+1}: ", MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
    total = total + ventas_dia

# CALCULO
media = total / CANTIDAD_DIAS

# RESULTADO
print(f"Total: {total:.2f}€ | Media: {media:.2f}€")