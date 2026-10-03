# Función bucle para la validación de datos
def validarCadenaVacia(mensajeInput, avisoError):
  while True:
    cadenaAComprobar = input(mensajeInput)
    if len(cadenaAComprobar.strip()) == 0:
      print(avisoError)
    else:
      break
  return cadenaAComprobar 

def validarTelefono(mensajeInput, avisoError, avisoErrorLongitudNoNumero):
  LONGITUD_NUMERO_TELEFONO = 9
  while True:
    telefonoAComprobar = validarCadenaVacia(mensajeInput, avisoError)
    if not (len(telefonoAComprobar) >= LONGITUD_NUMERO_TELEFONO and telefonoAComprobar.isdigit()):
      print(avisoErrorLongitudNoNumero)
    else: 
      break
  return telefonoAComprobar

# ============== MAIN ===============
# VARIABLES
mensajeError = "¡ERROR! No debes dejar este campo vacío"
mensajeErrorLongitudONoNumero = "¡ERROR! El teléfono introducido no sigue el formato válido."
mensajePeticionCliente = "Ingrese su nombre: "
mensajePeticionEmpresa = "Nombre de la empresa: "
mensajePeticionCorreoElectronico = "Ingrese su correo electrónico: "
mensajePeticionTelefono = "Ingrese su número de teléfono: "
# Petición de datos
nombreCliente = validarCadenaVacia(mensajePeticionCliente, mensajeError)
nombreEmpresa = validarCadenaVacia(mensajePeticionEmpresa, mensajeError)
correoElectronico = validarCadenaVacia(mensajePeticionCorreoElectronico, mensajeError)
telefono = validarTelefono(mensajePeticionTelefono, mensajeError, mensajeErrorLongitudONoNumero)

# MUESTRA LOS DATOS
print(f"===== DATOS DEL CLIENTE =====\nNOMBRE: {nombreCliente} | Empresa: {nombreEmpresa}\nCorreo Electrónico: {correoElectronico} | Telefono: {telefono}")
