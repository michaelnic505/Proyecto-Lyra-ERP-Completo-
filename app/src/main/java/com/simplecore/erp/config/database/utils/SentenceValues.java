package com.simplecore.erp.config.database.utils;

/**
 * This class provides utilities to build the VALUES text string 
 * included in SQL statements for data insertion.
 * 
 * Esta clase proporciona utilidades para construir la cadena de texto VALUES
 * que se incluye en sentencias SQL para la inserción de datos.
 */
public class SentenceValues {

    // Constant for the question mark used in SQL statements.
    // Constante para el signo de interrogación utilizado en las sentencias SQL.
    private static final String QUESTION_MARK = " ? ";
    private static final String COMMA_SEPARATOR = ", ";
    private static final String VALUES_PREFIX = " VALUES (";

    /**
     * Builds the VALUES text string for an SQL statement.
     * Construye la cadena de texto VALUES para una sentencia SQL.
     *
     * @param cantidad The number of values to insert in the SQL statement.
     *                 El número de valores a insertar en la sentencia SQL.
     * @return A text string in the format "VALUES (?, ?, ...)".
     *         Una cadena de texto en el formato "VALUES (?, ?, ...)".
     * @throws IllegalArgumentException If the quantity is less than 1.
     *                                  Si la cantidad es menor que 1.
     */
    public static String setValues(int cantidad) {
        if (cantidad < 1) {
            throw new IllegalArgumentException("The quantity must be greater than or equal to 1. / La cantidad debe ser mayor o igual a 1.");
        }

        StringBuilder simbolsCount = new StringBuilder();

        for (int i = 0; i < cantidad; i++) {
            simbolsCount.append(QUESTION_MARK).append(COMMA_SEPARATOR);
        }

        // Remove the last comma
        // Eliminar la última coma
        String simbolos = simbolsCount.substring(0, simbolsCount.length() - 1);

        // Build the final string
        // Construir la cadena final
        return VALUES_PREFIX + simbolos + ")";
    }
}