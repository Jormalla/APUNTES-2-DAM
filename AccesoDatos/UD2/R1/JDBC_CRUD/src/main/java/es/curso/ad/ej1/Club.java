package es.curso.ad.ej1;

import java.math.BigDecimal;

public record Club(int id, String nombre, String ciudad,
                   BigDecimal presupuesto) { }
