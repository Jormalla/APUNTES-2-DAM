# Lista de objetos
lista_objetos = ["Teclado", "Ratón", "Monitor"]

# Agrega nuevo elemento
lista_objetos.append("Webcam")

# Elimina Ratón
lista_objetos.remove("Ratón")

# Ordena alfabéticamente
lista_objetos.sort()

# Muestra cada objeto enumerado con enumerate
for i, objeto in enumerate(lista_objetos, start=1):
    print(f"{i}- {objeto}")
