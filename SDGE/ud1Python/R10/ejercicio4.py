class Empleado:
    def __init__(self, nombre, salario):
        self.nombre = nombre
        self.salario = salario   
    def __str__(self):
        return f"======= {self.nombre.upper()} =======\nSALARIO: {self.salario:.2f}€"
    
class Comercial(Empleado):
    def __init__(self, nombre, salario, ventas):
        super().__init__(nombre, salario)
        self.ventas = ventas
    def calcular_bonus(self):
        return 0.05 * self.ventas
    def __str__(self):
        return super().__str__() + f"| BONUS: {self.calcular_bonus():.2f}€"

comercial = Comercial("Juan", 2000.00, 350.20)

print(comercial)