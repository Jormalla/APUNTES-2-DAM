def mostrarClavesDiccionario(diccionario):
    for clave, contenido in diccionario.items():
        print(f"{clave.capitalize()}: {contenido}")

# Crear el diccionario
cliente = {
  "nombre": "Jorge",
  "ciudad": "Granada",
  "email": "emailejemplo@gmail.com",
  "activo": True
}

# Mostrar todos los campos (Clave y Valor)
mostrarClavesDiccionario(cliente)

#Cambiar la ciudad
cliente["ciudad"] = "Sevilla"

# Añadir una nueva clave llamada teléfono
cliente["teléfono"] = "123456789"

# Mostrar de nuevo el diccionario
print("\n")
mostrarClavesDiccionario(cliente)

