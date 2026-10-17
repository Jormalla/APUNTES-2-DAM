# ================== PRODUCTO ===================
class Producto:
    def __init__(self, nombre, precio, stock):
        self.__nombre = nombre
        self.__precio = precio
        self.__stock = stock
    
    # GETTERS & SETTERS
    def get_nombre(self):
        return self.__nombre
    
    def set_nombre(self, nombre):
        self.__nombre = nombre
        
    def get_precio(self):
        return self.__precio
    
    def set_precio(self, precio):
        se_pudo = False
        if precio >= 0:
            self.__precio = precio
            se_pudo = True
        return se_pudo
    
    def get_stock(self):
        return self.__stock
    
    def set_stock(self, stock):
        se_pudo = False
        if stock >= 0:
            self.__stock = stock
            se_pudo = True
        return se_pudo
    
    # MÉTODOS DE LA CLASE
    def vender(self, cantidad):
        se_pudo = False
        if self.__stock >= cantidad and cantidad != 0:
            se_pudo = True
            self.__stock -= cantidad
        return se_pudo
    
    # TO STRING
    def __str__(self):
        return f"{self.get_nombre()} | {self.get_precio()} € | {self.get_stock()} unidades"
    
# ================= LINEA PEDIDO ===================
class LineaPedido():
    def __init__(self, producto: Producto, cantidad):
        self.__producto = producto
        self.__cantidad = cantidad
    
    # GETTERS & SETTERS
    
    def get_producto(self):
        return self.__producto
    
    def set_producto(self, producto: Producto):
        self.__producto = producto
    
    def get_cantidad(self):
        return self.__cantidad
    
    def set_cantidad(self, cantidad):
        self.__cantidad = cantidad
    
    # MÉTODOS DE LA CLASE
    
    def subtotal(self):
        return self.get_producto().get_precio() * self.get_cantidad()
    
    # TO STRING
    def __str__(self):
        return f"{self.get_producto().get_nombre()} | Unidades: {self.get_cantidad()} | Coste: {self.subtotal()} €"

# =============== CLASE PEDIDO ===============
class Pedido:
    def __init__(self, cliente, lineas_pedido: list = []):
        self.__cliente = cliente
        self.__lineas_pedido = lineas_pedido
    
    # GETTERS & SETTERS
    
    def get_cliente(self):
        return self.__cliente
    
    def set_cliente(self, cliente):
        self.__cliente = cliente
    
    def get_lineas_pedido(self):
        return self.__lineas_pedido
    
    def set_lineas_pedido(self, lineas_pedido):
        self.__lineas_pedido = lineas_pedido
    
    # MÉTODOS DE LA CLASE
    def agregar_linea(self, producto: Producto, cantidad):
        se_pudo = False
        if producto.vender(cantidad):
            self.get_lineas_pedido().append(LineaPedido(producto, cantidad))
            se_pudo = True
        return se_pudo
    
    def total(self):
        total = 0
        for linea in self.get_lineas_pedido():
            total += linea.subtotal()
        
        return total
    
    # TO STRING
    def __str__(self):
        cadena_a_mostrar = "==== RESUMEN DEL PEDIDO ====\n"
        for linea in self.get_lineas_pedido():
            cadena_a_mostrar += f"-> {linea}\n"
        
        cadena_a_mostrar += f"Cantidad de lineas: {len(self.get_lineas_pedido())}"
        return cadena_a_mostrar
            