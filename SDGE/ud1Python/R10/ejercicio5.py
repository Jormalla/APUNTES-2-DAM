# =========== PEDIDO ===========
class LineaPedido:
    def __init__(self, producto, precio, cantidad):
        self.producto = producto
        self.__agregar_precio(precio)
        self.__agregar_cantidad(cantidad)

    def subtotal(self):
        return self.precio * self.cantidad

    def __agregar_precio(self, precio):
        if precio > 0:
            self.precio = precio
        else:
            self.precio = "[¡ERROR! Valor de precio no válido, debe ser mayor a 0]"

    def __agregar_cantidad(self, cantidad):
        if cantidad >= 0:
            self.cantidad = cantidad
        else:
            self.cantidad = "[¡ERROR! Valor de cantidad no válido, debe ser mayor o igual a 0]"

    def __str__(self):
        return f"===== {self.producto.upper()} =====\nPRECIO: {self.precio} | CANTIDAD: {self.cantidad}"

# =========== CLIENTE ===========
class Cliente:
    def __init__(self, nombre, email, ciudad):
        self.nombre = nombre
        self.email = email
        self.ciudad = ciudad
    def cambiar_ciudad(self, ciudad):
        self.ciudad = ciudad
    def __str__(self):
        return f"===== CLIENTE {self.nombre.upper()} =====\nMAIL: {self.email} | CIUDAD: {self.ciudad}"

# =========== PEDIDO ===========
class Pedido:
    def __init__(self, cliente: Cliente, lineas = []):
        self.cliente = cliente
        self.lineas = lineas

    def agregar_linea(self, linea: LineaPedido):
        self.lineas.append(linea)

    def total(self):
        total_final = 0
        for linea in self.lineas:
            total_final += linea.subtotal()
        return total_final
    def __str__(self):
        return f"===== PEDIDO =====\n CLIENTE: {self.cliente.nombre} | Número de líneas de pedido: {len(self.lineas)}"


cliente = Cliente("Jorge", "jormalla@gmail.com", "Granada")
linea_pedido_1 = LineaPedido("Pc", 1000.00, 1)
linea_pedido_2 = LineaPedido("Ratón", 35, 2)

pedido = Pedido(cliente)
pedido.agregar_linea(linea_pedido_1)
pedido.agregar_linea(linea_pedido_2)
print(pedido)
print(pedido.total())