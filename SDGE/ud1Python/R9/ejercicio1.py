def pide_numero_no_negativo(mensaje_input, mensaje_error):
    es_numero_no_negativo = False
    while not es_numero_no_negativo:
        try:
            numero = float(input(mensaje_input))
            if numero >= 0:
                es_numero_no_negativo = True
            else:
                print(mensaje_error)
        except ValueError:
            print(mensaje_error)
    return numero


peticion = pide_numero_no_negativo("Ingresa un número mayor o igual a 0: ", "¡ERROR! No ingresastes un número mayor o igual a 0.")
            
print(f"El número ingresado es {peticion}")
