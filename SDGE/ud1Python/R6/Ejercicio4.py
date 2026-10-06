import sys
sys.path.append("C:/Users/DAM/Desktop/Github/APUNTES-2-DAM/Bibliotecas")

import utiles

# Crea un menú que muestre: 1) Alta ficticia, 2) Consulta ficticia, 3) Informe ficticio,
# 0) Salir. Mientras la opción sea distinta de 0, muestra un mensaje distinto para
# cada opción. Si no es 0, 1, 2 o 3, indica "Opción no válida". No uses while True ni
# break.

# MENSAJES
INTERFAZ = "===== MENU =====\n1) Alta ficticia\n2) Consulta ficticia\n3) Informe ficticio\n0) Salir\nIngrese una opción: "
MENSAJE_ERROR_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío."
OPCION_NO_VALIDA = "OPCIÓN NO VÁLIDA"

# PETICIONES
en_menu = True

while en_menu:
    seleccion_menu = utiles.pedir_numero_entero_en_rango(0, 3, INTERFAZ, MENSAJE_ERROR_CAMPO_VACIO, OPCION_NO_VALIDA)
    if seleccion_menu == 0:
        en_menu = False
    elif seleccion_menu == 1:
        print("Esta es la opción 1")
    elif seleccion_menu == 2:
        print("Osea, que soy tu segunda opción")
    else:
        print("Multitud")
