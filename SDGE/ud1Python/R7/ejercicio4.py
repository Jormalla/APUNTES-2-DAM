def mostrarClavesDiccionario(diccionario):
    for clave, contenido in diccionario.items():
        print(f"{clave.capitalize()}: {contenido}")

manzana = {
  "nombre": "manzana",
  "categoria": "fruta",
  "precio": 1.2,
  "stock": 50
}

mopa = {
  "nombre": "mopa",
  "categoria": "limpieza",
  "precio": 0.50,
  "stock": 400
}

xbox = {
  "nombre": "xbox",
  "categoria": "consola",
  "precio": 499.99,
  "stock": 100
}

tv = {
  "nombre": "televisión",
  "categoria": "electrónica",
  "precio": 600,
  "stock": 0
}

productos = [manzana, mopa, xbox, tv]

for producto in productos:
  if producto["stock"] > 0:
    mostrarClavesDiccionario(producto)
    print("\n")
    
