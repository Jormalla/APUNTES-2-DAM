class Cliente:
    __numero_clientes = 0
    def __init__(self, nombre, email, ciudad, activo):
        self.nombre = nombre
        self.email = email
        self.ciudad = ciudad
        self.activo = activo