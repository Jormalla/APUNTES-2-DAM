class Producto:
    numero_productos = 0

    def __init__(self, nombre, precio, stock = 0):
        self.id = self.__asignar_id()
        self.nombre = nombre
        self.precio = self.__asignar_precio(precio)
        self.stock = self.__asignar_stock(stock)
    
    # Asigna una id a cada producto
    def __asignar_id(self):
        self.id = numero_productos
        numero_productos += numero_productos + 1

    # Asigna precio
    def __asignar_precio(self, precio):
        if precio < 0:
            self.precio = 0
        else:
            self.precio = precio
    # Asigna sock
    def __asignar_stock(self, stock):
            if stock < 0:
                self.stock = 0
            else:
                self.stock = stock