def pide_numero_en_rango(mensaje_input, minimo, maximo):
    es_numero_no_negativo = False
    MENSAJE_ERROR = f"¡ERROR! No ingresastes un número entero entre {minimo} y {maximo}"
    while not es_numero_no_negativo:
        try:
            numero = int(input(mensaje_input))
            if numero >= minimo and numero <= maximo:
                es_numero_no_negativo = True
            else:
                print(MENSAJE_ERROR)
        except ValueError:
            print(MENSAJE_ERROR)
    return numero


peticion = pide_numero_en_rango("Ingresa un número entero entre 1 y 100: ", 1, 100)
            
print(f"El número ingresado es {peticion}")
