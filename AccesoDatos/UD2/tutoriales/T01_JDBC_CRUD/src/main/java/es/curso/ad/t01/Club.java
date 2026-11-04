package AccesoDatos.UD2.tutoriales.T01_JDBC_CRUD.src.main.java.es.curso.ad.t01;

import java.math.BigDecimal;

public record Club(int id, String nombre, String ciudad,
                   BigDecimal presupuesto) { }
