class Cliente:
    def __init__(self, nombre, email, ciudad):
        self.nombre = nombre
        self.email = email
        self.ciudad = ciudad
    def cambiar_ciudad(self, ciudad):
        self.ciudad = ciudad
    def __str__(self):
        return f"===== CLIENTE {self.nombre.upper()} =====\nMAIL: {self.email} | CIUDAD: {self.ciudad}"

juan = Cliente("Juan", "correo@gmail.com", "Granada")
print(juan)

juan.cambiar_ciudad("Madrid")
print(juan)