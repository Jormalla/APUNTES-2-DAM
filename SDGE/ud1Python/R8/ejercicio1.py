import sys
sys.path.append("C:/Users/DAM/Desktop/Github/APUNTES-2-DAM/Bibliotecas")

import utiles

def calcular_subtotal(precio, cantidad):
  return precio * cantidad

# ======= MAIN =========
# ======MENSAJES ======
MENSAJE_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío"
MENSAJE_ERROR_NUMERO = "¡ERROR! Debes introducir un número"
MENSAJE_ERROR_NUMERO_ENTERO = "¡ERROR! Debes introducir un número entero no negativo"
PETICION_PRECIO = "Ingrese el precio del producto: "
PETICION_CANTIDAD = "Ingrese la cantidad del producto: "

# Peticiones
precio_producto = utiles.pedir_numero(PETICION_PRECIO, MENSAJE_CAMPO_VACIO, MENSAJE_ERROR_NUMERO)
cantidad_producto = utiles.pedir_numero_entero_no_negativo(PETICION_CANTIDAD, MENSAJE_CAMPO_VACIO, MENSAJE_ERROR_NUMERO_ENTERO)

# Resultado
print(f"Tiene que pagar: {calcular_subtotal(precio_producto, cantidad_producto):.2f}€")
