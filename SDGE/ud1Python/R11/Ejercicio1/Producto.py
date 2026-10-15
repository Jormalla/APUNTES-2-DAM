class Producto:
    __numero_productos = 0

    def __init__(self, nombre, precio, stock = 0):
        self.__asignar_id()
        self.__nombre = nombre
        self.__asignar_precio(precio)
        self.__asignar_stock(stock)
    
    # Asigna una id a cada producto
    def __asignar_id(self):
        self.__id = Producto.__numero_productos
        Producto.__numero_productos += 1

    # Asigna precio
    def __asignar_precio(self, precio):
        if precio < 0:
            self.__precio = 0
        else:
            self.__precio = precio

    # Asigna stock
    def __asignar_stock(self, stock):
            if stock < 0:
                self.__stock = 0
            else:
                self.__stock = stock

    # GETTERS & SETTERS
    def get_id(self):
        return self.__id

    def get_nombre(self):
        return self.__nombre

    def get_precio(self):
        return self.__precio
    
    def get_stock(self):
        return self.__stock

    def set_id(self, id):
         self.__id = id

    def set_nombre(self, nombre):
        self.__nombre = nombre

    def set_precio(self, precio):
        self.__precio = precio

    def set_stock(self, stock):
        self.__stock = stock

    # TOSTRING
    def __str__(self):
        return f"[{self.get_id()}] --> {self.get_nombre()} | {self.get_precio():.2f} € | {self.get_stock()} unidades"
