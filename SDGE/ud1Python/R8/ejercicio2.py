def clasificar_stock(stock):
  texto_devolver = ""
  if stock <= 0:
    texto_devolver = "Agotado"
  elif stock <= 5:
    texto_devolver = "Stock bajo"
  else:
    texto_devolver = "Stock suficiente"
  return texto_devolver

print(f"{clasificar_stock(0)}")
print(f"{clasificar_stock(3)}")
print(f"{clasificar_stock(10)}")
