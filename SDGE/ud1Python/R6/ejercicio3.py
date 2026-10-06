import sys
sys.path.append("C:/Users/DAM/Desktop/Github/APUNTES-2-DAM/Bibliotecas")

import utiles

# Pide importes de operaciones mientras el valor introducido sea distinto de 0.
# Acumula el total y cuenta cuántos importes distintos de cero se han registrado.
# Construye el while con una condición explícita.

# MENSAJES
MENSAJE_ERROR_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío."
MENSAJE_PETICION_IMPORTE = "Ingrese el importe (0 para terminar): "
MENSAJE_ERROR_NUMERO = "¡ERROR! Debes ingresar un número mayor o igual a 0."

# VARIABLES
importe = 1
contador_importes = 0
total = 0

# PETICIONES
while importe > 0:
    importe = utiles.pedir_numero_no_negativo(MENSAJE_PETICION_IMPORTE, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
    total = total + importe
    contador_importes += 1

# RESULTADO
print(f"Cantidad de importes: {contador_importes - 1} | Total: {total:.2f}€")