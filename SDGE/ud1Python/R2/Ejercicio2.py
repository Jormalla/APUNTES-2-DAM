# Función bucle para la validación de datos
def validar_cadena_vacia(mensaje_input, aviso_error):
  while True:
    cadena_a_comprobar = input(mensaje_input)
    if len(cadena_a_comprobar.strip()) == 0:
      print(aviso_error)
    else:
      break
  return cadena_a_comprobar 

# Valida si el input es un número y lo transforma a float
def validar_numero_no_negativo(mensaje_input, aviso_error, mensaje_fallo_en_numero):
  while True:
    # Valida que no esté vacio
    numero_a_comprobar = validar_cadena_vacia(mensaje_input, aviso_error)
    
    # Intenta convertir a float y verifica si es positivo
    try:
      valor_numerico = float(numero_a_comprobar)
      if valor_numerico >= 0:
        return valor_numerico  # Devuelve el número en float
      else:
        print(mensaje_fallo_en_numero)
    except ValueError:
      # Si float() falla
      print(mensaje_fallo_en_numero)

# ============== MAIN ===============
# VARIABLES
mensaje_error = "¡ERROR! No debes dejar este campo vacío"
error_en_numero = "¡ERROR! Debes introducir un número mayor o igual a 0"
mensaje_peticion_cliente = "Ingrese su nombre: "
mensaje_peticion_servicio = "Ingrese el servicio que desea recibir: "
mensaje_peticion_precio = "Ingrese el precio unitario: "
mensaje_peticion_cantidad = "Ingrese la cantidad: "

# PETICIÓNES DE CADENAS
nombre_cliente = validar_cadena_vacia(mensaje_peticion_cliente, mensaje_error)
nombre_servicio = validar_cadena_vacia(mensaje_peticion_servicio, mensaje_error)

# GUARDADO DE PRECIO Y CANTIDAD COMO VARIABLES FLOAT
precio = validar_numero_no_negativo(mensaje_peticion_precio, mensaje_error, error_en_numero)
cantidad = validar_numero_no_negativo(mensaje_peticion_cantidad, mensaje_error, error_en_numero)

# CALCULOS
coste_total = precio * cantidad

# MUESTRA LOS DATOS
print(f"\n===== DATOS DEL CLIENTE =====")
print(f"NOMBRE: {nombre_cliente} | Servicio: {nombre_servicio}")
print(f"Precio: {precio:.2f} | Cantidad: {cantidad:.0f}")
print(f"Coste total: {coste_total:.2f}")


