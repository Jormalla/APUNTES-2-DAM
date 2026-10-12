import sys
from pathlib import Path

# Método para saber si el contenido de un archivo esta vacio
def is_vacio(ruta):
    return ruta.stat().st_size == 0 # Con esto se comprueba si el archivo esta o no vacio