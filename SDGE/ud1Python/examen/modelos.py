"""UD1 · PT1 · Clases del sistema BiciAlerta.

No escribas print() ni input() en las clases ni en sus métodos.
"""


class Bicicleta:
    def __init__(self, codigo, propietario, modelo, valor, estado="ROBADA"):
        self.codigo = codigo
        self.propietario = propietario
        self.modelo = modelo
        self.valor = valor
        self.estado = estado


class RegistroRobos:
    def __init__(self):
        # El código de la bicicleta será la clave del diccionario.
        self.bicicletas = {}

    def registrar(self, bicicleta):
        """Devuelve True si se añade; False si el código está repetido."""
        if bicicleta.codigo in self.bicicletas:
            return False            
        self.bicicletas[bicicleta.codigo]= bicicleta
        return True

    def listar(self):
        """Devuelve la lista completa de objetos Bicicleta."""
        return list(self.bicicletas.values())

    def recuperar(self, codigo):
        """Cambia ROBADA a RECUPERADA. Devuelve True si cambia algo."""
        if self.bicicletas[codigo].estado == "RECUPERADA":
            return False
        self.bicicletas[codigo].estado = "RECUPERADA"
        return True

    def buscar(self, texto):
        """Busca por fragmento de propietario o modelo, sin importar mayúsculas."""
        lista_bicis = []
        for bici in self.listar():
            if bici.modelo.lower() == texto.lower() or bici.propietario.lower() == texto.lower():
                lista_bicis.append(bici)
        return lista_bicis

    def resumen(self):
        """Devuelve un diccionario con total, robadas, recuperadas y valor_pendiente."""
        robadas = 0
        recuperadas = 0
        valor_pendiente = 0
        for bici in self.listar():
            if bici.estado == "ROBADA":
                robadas += 1
                valor_pendiente += bici.valor
            else:
                recuperadas += 1
        return {"total": len(self.listar()), "robadas": robadas, "recuperadas": recuperadas, "valor_pendiente": valor_pendiente}
