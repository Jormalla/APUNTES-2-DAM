# Función bucle para la validación de datos
def validar_cadena_vacia(mensaje_input, aviso_error):
  while True:
    cadena_a_comprobar = input(mensaje_input)
    if len(cadena_a_comprobar.strip()) == 0:
      print(aviso_error)
    else:
      break
  return cadena_a_comprobar 

def validar_telefono(mensaje_input, aviso_error, aviso_error_longitud_no_numero):
  LONGITUD_NUMERO_TELEFONO = 9
  while True:
    telefono_a_comprobar = validar_cadena_vacia(mensaje_input, aviso_error)
    if not (len(telefono_a_comprobar) >= LONGITUD_NUMERO_TELEFONO and telefono_a_comprobar.isdigit()):
      print(aviso_error_longitud_no_numero)
    else: 
      break
  return telefono_a_comprobar

# ============== MAIN ===============
# VARIABLES
mensaje_error = "¡ERROR! No debes dejar este campo vacío"
mensaje_error_longitud_o_no_numero = "¡ERROR! El teléfono introducido no sigue el formato válido."
mensaje_peticion_cliente = "Ingrese su nombre: "
mensaje_peticion_empresa = "Nombre de la empresa: "
mensaje_peticion_correo_electronico = "Ingrese su correo electrónico: "
mensaje_peticion_telefono = "Ingrese su número de teléfono: "
# Petición de datos
nombre_cliente = validar_cadena_vacia(mensaje_peticion_cliente, mensaje_error)
nombre_empresa = validar_cadena_vacia(mensaje_peticion_empresa, mensaje_error)
correo_electronico = validar_cadena_vacia(mensaje_peticion_correo_electronico, mensaje_error)
telefono = validar_telefono(mensaje_peticion_telefono, mensaje_error, mensaje_error_longitud_o_no_numero)

# MUESTRA LOS DATOS
print(f"===== DATOS DEL CLIENTE =====\nNOMBRE: {nombre_cliente} | Empresa: {nombre_empresa}\nCorreo Electrónico: {correo_electronico} | Telefono: {telefono}")

