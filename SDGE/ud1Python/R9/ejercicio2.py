def pide_numero_no_negativo(MENSAJE_INPUT, minimo, maximo):
    es_numero_no_negativo = False
    MENSAJE_ERROR = "¡ERROR! No ingresastes un número entero entre 1 y 100"
    while not es_numero_no_negativo:
        try:
            numero = int(input(MENSAJE_INPUT))
            if numero >= minimo and numero <= maximo:
                es_numero_no_negativo = True
            else:
                print(MENSAJE_ERROR)
        except ValueError:
            print(MENSAJE_ERROR)
    return numero


peticion = pide_numero_no_negativo("Ingresa un número entero entre 1 y 100: ", 1, 100)
            
print(f"El número ingresado es {peticion}")
