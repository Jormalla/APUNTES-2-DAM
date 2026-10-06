import sys
sys.path.append("C:/Users/DAM/Desktop/Github/APUNTES-2-DAM/Bibliotecas")

import utiles

# Pide cuántos pedidos se van a etiquetar. Usa for y range() para mostrar PED-001,
# PED-002... hasta la cantidad indicada. Si la cantidad es menor que 1, muestra un
# mensaje y no generes códigos.

# MENSAJES
MENSAJE_PETICION_CANTIDAD_PEDIDOS = "Ingrese la cantidad de pedidos que se van a realizar: "
MENSAJE_ERROR_NUMERO = "¡ERROR! Debes ingresar un número mayor o igual a 0."
MENSAJE_ERROR_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío."
IDENTIFICADOR_PEDIDO = "PED-"

# PETICIONES
cantidad_pedidos = utiles.pedir_numero_entero_no_negativo(MENSAJE_PETICION_CANTIDAD_PEDIDOS, MENSAJE_ERROR_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)

# MUESTRA LOS PEDIDOS
for i in range(0, cantidad_pedidos):
    if len(str(i+1)) < 3:
        if len(str(i+1)) < 2:
            print(f"{IDENTIFICADOR_PEDIDO}00{i+1}")
        else:
            print(f"{IDENTIFICADOR_PEDIDO}0{i+1}")
    else:
        print(f"{IDENTIFICADOR_PEDIDO}{i+1}")