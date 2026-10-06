# Función bucle para la validación de datos
def pedirCadenaNoVacia(mensajeInput, avisoError):
  while True:
    cadenaAComprobar = input(mensajeInput)
    if len(cadenaAComprobar.strip()) == 0:
      print(avisoError)
    else:
      break
  return cadenaAComprobar

# Valida si el input es un número y lo transforma a float
def pedirNumeroNoNegativo(mensajeInput, avisoError, mensajeFalloEnNumero):
  while True:
    # Valida que no esté vacio
    numeroAComprobar = pedirCadenaNoVacia(mensajeInput, avisoError)
    
    # Intenta convertir a float y verifica si es positivo
    try:
      valorNumerico = float(numeroAComprobar)
      if valorNumerico >= 0:
        return valorNumerico  # Devuelve el número en float
      else:
        print(mensajeFalloEnNumero)
    except ValueError:
      # Si float() falla
      print(mensajeFalloEnNumero)

# Valida que sea un número positivo mayor o igual a 0 y que no sea mayor a 100
def pedirNumeroEnRango(numeroMinimo, numeroMaximo, mensajeInput, avisoError, mensajeFalloEnNumero):
 while True:
    # Valida que no esté vacio
    numeroAComprobar = pedirCadenaNoVacia(mensajeInput, avisoError)

    # Intenta convertir a float y verifica si es positivo
    try:
      valorNumerico = float(numeroAComprobar)
      if (valorNumerico >= numeroMinimo and valorNumerico <= numeroMaximo):
        return valorNumerico  # Devuelve el número en float
      else:
        print(mensajeFalloEnNumero)
    except ValueError:
      # Si float() falla
      print(mensajeFalloEnNumero)

# Valida que el telefono ingresado siga el patrón de 6 dígitos xxx-xxx-xxx
def pedirTelefono(mensajeInput, avisoError, avisoErrorLongitudNoNumero):
  LONGITUD_NUMERO_TELEFONO = 9
  while True:
    telefonoAComprobar = pedirCadenaNoVacia(mensajeInput, avisoError)
    if not (len(telefonoAComprobar) >= LONGITUD_NUMERO_TELEFONO and telefonoAComprobar.isdigit()):
      print(avisoErrorLongitudNoNumero)
    else: 
      break
  return telefonoAComprobar
