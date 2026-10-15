class Producto:
    __numero_productos = 0

    def __init__(self, nombre, precio, stock = 0):
        self.__id = self.__asignar_id()
        self.__nombre = nombre
        self.__precio = self.__asignar_precio(precio)
        self.__stock = self.__asignar_stock(stock)
    
    # Asigna una id a cada producto
    def __asignar_id(self):
        self.__id = __numero_productos
        __numero_productos += 1

    # Asigna precio
    def __asignar_precio(self, precio):
        if precio < 0:
            self.__precio = 0
        else:
            self.__precio = precio
    # Asigna sock
    def __asignar_stock(self, stock):
            if stock < 0:
                self.__stock = 0
            else:
                self.__stock = stock
