# Función bucle para la validación de datos
def pedir_cadena_no_vacia(mensaje_input, aviso_error):
  while True:
    cadena_a_comprobar = input(mensaje_input)
    if len(cadena_a_comprobar.strip()) == 0:
      print(aviso_error)
    else:
      break
  return cadena_a_comprobar

# Valida si el input es un número y lo transforma a float
def pedir_numero_no_negativo(mensaje_input, aviso_error, mensaje_fallo_en_numero):
  while True:
    # Valida que no esté vacio
    numero_a_comprobar = pedir_cadena_no_vacia(mensaje_input, aviso_error)
    
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

# Valida si el input es un número y lo transforma a int
def pedir_numero_entero_no_negativo(mensaje_input, aviso_error, mensaje_fallo_en_numero):
  while True:
    # Valida que no esté vacio
    numero_a_comprobar = pedir_cadena_no_vacia(mensaje_input, aviso_error)
    
    # Intenta convertir a int y verifica si es positivo
    try:
      valor_numerico = int(numero_a_comprobar)
      if valor_numerico >= 0:
        return valor_numerico  # Devuelve el número en int
      else:
        print(mensaje_fallo_en_numero)
    except ValueError:
      # Si int() falla
      print(mensaje_fallo_en_numero)


# Valida que sea un número entero en el rango
def pedir_numero_entero_en_rango(numero_minimo, numero_maximo, mensaje_input, aviso_error, mensaje_fallo_en_numero):
 while True:
    # Valida que no esté vacio
    numero_a_comprobar = pedir_cadena_no_vacia(mensaje_input, aviso_error)

    # Intenta convertir a int y verifica si es positivo
    try:
      valor_numerico = int(numero_a_comprobar)
      if (valor_numerico >= numero_minimo and valor_numerico <= numero_maximo):
        return valor_numerico  # Devuelve el número en float
      else:
        print(mensaje_fallo_en_numero)
    except ValueError:
      # Si int() falla
      print(mensaje_fallo_en_numero)

# Valida que sea un número en el rango
def pedir_numero_en_rango(numero_minimo, numero_maximo, mensaje_input, aviso_error, mensaje_fallo_en_numero):
 while True:
    # Valida que no esté vacio
    numero_a_comprobar = pedir_cadena_no_vacia(mensaje_input, aviso_error)

    # Intenta convertir a float y verifica si es positivo
    try:
      valor_numerico = float(numero_a_comprobar)
      if (valor_numerico >= numero_minimo and valor_numerico <= numero_maximo):
        return valor_numerico  # Devuelve el número en float
      else:
        print(mensaje_fallo_en_numero)
    except ValueError:
      # Si float() falla
      print(mensaje_fallo_en_numero)

# Valida que el telefono ingresado siga el patrón de 6 dígitos xxx-xxx-xxx
def pedir_telefono(mensaje_input, aviso_error, aviso_error_longitud_no_numero):
  LONGITUD_NUMERO_TELEFONO = 9
  while True:
    telefono_a_comprobar = pedir_cadena_no_vacia(mensaje_input, aviso_error)
    if not (len(telefono_a_comprobar) >= LONGITUD_NUMERO_TELEFONO and telefono_a_comprobar.isdigit()):
      print(aviso_error_longitud_no_numero)
    else: 
      break
  return telefono_a_comprobar

# Método para mostrar por pantalla el contenido de un diccionario
def mostrarClavesDiccionario(diccionario):
    for clave, contenido in diccionario.items():
        print(f"{clave.capitalize()}: {contenido}")
