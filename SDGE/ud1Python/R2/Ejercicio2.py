# Función bucle para la validación de datos
def validarCadenaVacia(mensajeInput, avisoError):
  while True:
    cadenaAComprobar = input(mensajeInput)
    if len(cadenaAComprobar.strip()) == 0:
      print(avisoError)
    else:
      break
  return cadenaAComprobar 

# Valida si el input es un número y lo transforma a float
def validarNumeroNoNegativo(mensajeInput, avisoError, mensajeFalloEnNumero):
  while True:
    # Valida que no esté vacio
    numeroAComprobar = validarCadenaVacia(mensajeInput, avisoError)
    
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

# ============== MAIN ===============
# VARIABLES
mensajeError = "¡ERROR! No debes dejar este campo vacío"
errorEnNumero = "¡ERROR! Debes introducir un número mayor o igual a 0"
mensajePeticionCliente = "Ingrese su nombre: "
mensajePeticionServicio = "Ingrese el servicio que desea recibir: "
mensajePeticiónPrecio = "Ingrese el precio unitario: "
mensajePeticiónCantidad = "Ingrese la cantidad: "

# PETICIÓNES DE CADENAS
nombreCliente = validarCadenaVacia(mensajePeticionCliente, mensajeError)
nombreServicio = validarCadenaVacia(mensajePeticionServicio, mensajeError)

# GUARDADO DE PRECIO Y CANTIDAD COMO VARIABLES FLOAT
precio = validarNumeroNoNegativo(mensajePeticiónPrecio, mensajeError, errorEnNumero)
cantidad = validarNumeroNoNegativo(mensajePeticiónCantidad, mensajeError, errorEnNumero)

# CALCULOS
costeTotal = precio * cantidad

# MUESTRA LOS DATOS
print(f"\n===== DATOS DEL CLIENTE =====")
print(f"NOMBRE: {nombreCliente} | Servicio: {nombreServicio}")
print(f"Precio: {precio:.2f} | Cantidad: {cantidad:.0f}")
print(f"Coste total: {costeTotal:.2f}")

