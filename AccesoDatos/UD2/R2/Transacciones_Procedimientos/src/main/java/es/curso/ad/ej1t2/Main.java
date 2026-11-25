package es.curso.ad.ej1t2;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        OperacionesClubes operaciones = new OperacionesClubes();
        try {
            operaciones.transferir(1, 2, new BigDecimal(10.25));
            System.out.println(operaciones.deshacerMovimiento(1L));

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
}
