class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def mostrar(self):
        mensaje_mostrar = f"======= {self.nombre.upper()} =======\nPRECIO: {self.precio}€ | STOCK: {self.stock}"
        return mensaje_mostrar

pc_gaming = Producto("Pc MSI", 1320.40, 34)
teclado = Producto("Teclado Logitech", 30, 120)

print(pc_gaming.mostrar())
print(teclado.mostrar())