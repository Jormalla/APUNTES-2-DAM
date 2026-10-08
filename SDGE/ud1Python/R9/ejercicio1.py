def pide_numero_no_negativo(MENSAJE_INPUT, MENSAJE_ERROR):
    es_numero_no_negativo = False
    while not es_numero_no_negativo:
        try:
            numero = float(input(MENSAJE_INPUT))
            if numero >= 0:
                es_numero_no_negativo = True
            else:
                print(MENSAJE_ERROR)
        except ValueError:
            print(MENSAJE_ERROR)
    return numero


peticion = pide_numero_no_negativo("Ingresa un número mayor o igual a 0: ", "¡ERROR! No ingresastes un número mayor o igual a 0.")
            
print(f"El número ingresado es {peticion}")
