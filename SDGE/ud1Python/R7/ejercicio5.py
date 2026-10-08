# Lista inicial con valores repetidos
lista_categorias = ["Teclado", "Ratón", "Monitor", "Monitor", "Televisor", "Ratón"]

# Obtener un set con las categorías únicas y mostrarlas
categorias_unicas = set(lista_categorias)
print("=== Categorías únicas ===")
for categoria in categorias_unicas:
  print(categoria)

# Pedir un texto de búsqueda al usuario
busqueda = input("Introduce el texto a buscar: ")

# Mostrar los nombres que contengan el texto ignorando mayúsculas y minúsculas
print(f"\n=== Resultados de búsqueda para '{busqueda}' ===")

# Convertir la búsqueda a minúsculas
busqueda_lower = busqueda.lower()

encontrado = False
for categoria in categorias_unicas:
    # Convertimos también el producto a minúsculas para comparar de forma justa
    if busqueda_lower == categoria.lower():
        print(f"- {categoria}")
        encontrado = True

if not encontrado:
    print(f"No se encontraron coincidencias para {busqueda}")
