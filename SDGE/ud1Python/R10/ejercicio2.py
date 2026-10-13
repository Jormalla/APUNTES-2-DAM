# R10E2 | Métodos de inventario
# vender() y reponer() con actualización de stock.
# Consulta el PDF de la Relación 10 para el enunciado completo y la comprobación.

# TODO: escribe aquí tu solución.
class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def mostrar(self):
        mensaje_mostrar = f"======= {self.nombre.upper()} =======\nPRECIO: {self.precio}€ | STOCK: {self.stock}"
        return mensaje_mostrar

    def vender(self, cantidad):
        se_pudo = False
        if self.stock - cantidad >= 0:
            se_pudo = True
            self.stock -= cantidad
        return se_pudo
    def reponer(self, cantidad):
        self.stock += cantidad

pc_gaming = Producto("Pc MSI", 1320.40, 34)
teclado = Producto("Teclado Logitech", 30, 120)

pc_gaming.vender(30)
teclado.vender(121)

print(pc_gaming.mostrar())
print(teclado.mostrar())

pc_gaming.reponer(60)
teclado.reponer(200)

print(pc_gaming.mostrar())
print(teclado.mostrar())