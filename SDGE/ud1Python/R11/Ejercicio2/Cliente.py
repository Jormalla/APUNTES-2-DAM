class Cliente:

    def __init__(self, nombre, email, ciudad, activo = True):
        self.__nombre = nombre
        self.__email = email
        self.__ciudad = ciudad
        self.__activo = activo

    # ==== SETTERS & GETTERS
    
    def get_nombre(self):
        return self.__nombre

    def set_nombre(self, nombre):
        self.__nombre = nombre

    def get_email(self):
        return self.__email

    def set_email(self, email):
        self.__email = email

    def get_ciudad(self):
        return self.__ciudad

    def set_ciudad(self, ciudad):
        self.__ciudad = ciudad

    def esta_activo(self):
        return self.__activo

    def set_activo(self, activo):
        self.__activo = activo

    # OTROS MÉTODOS
    def visualizar_actividad(self):
        if self.__activo:
            return "Activo"
        else:
            return "Inactivo"

    # ==== TO STRING

    def __str__(self):
        return f"{self.get_nombre()} | MAIL: {self.get_email()} | CIUDAD: {self.get_ciudad()} | ESTADO: {self.visualizar_actividad()}"