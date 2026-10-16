import utiles
import ficheros
import csv
import sys
from pathlib import Path

# RUTAS
CARPETA_SCRIPT = Path(__file__).resolve().parent
NOMBRE_ARCHIVO = "clientes.csv"
RUTA_ARCHIVO = CARPETA_SCRIPT / NOMBRE_ARCHIVO

sys.path.append(str(CARPETA_SCRIPT.parents[3] / "Bibliotecas"))

# MENSAJES
INTERFAZ_MENU = "===== GESTIÓN DE CLIENTES =====\n1) Dar de alta un nuevo cliente\n2) Listar clientes\n3) Buscar cliente\n4) Cambiar estado del cliente\n5) Guardar en CSV\n6) Cargar CSV\nIngrese una opción: "
MENSAJE_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío."
ERROR_INTERFAZ = "¡ERROR! Debes elegir una opción válida."

# ============= MAIN ============== #
def main():
     opcion_elegida = 1
     while opcion_elegida != 0:
          opcion_elegida = utiles.pedir_numero_en_rango(0, 6, INTERFAZ_MENU, MENSAJE_CAMPO_VACIO, ERROR_INTERFAZ)
if __name__ == "__main__":
    main()
