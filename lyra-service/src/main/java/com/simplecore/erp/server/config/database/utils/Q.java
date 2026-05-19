package com.simplecore.erp.server.config.database.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public enum Q {

    // Enum defining SQL keywords (Enumeración que define palabras clave SQL)
    INSERT("INSERT INTO"),
    SELECT_ALL("SELECT * FROM"),
    SELECT("SELECT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    WHERE("WHERE"),
    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    RETURNING("RETURNING"),
    EXISTS("EXISTS"),
    NOT_EXISTS("NOT EXISTS"),
    OPEN_PARENTHESIS("("),
    CLOSE_PARENTHESIS(")"),
    EQUALS("="),
    NOT_EQUALS("!="),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    LESS_THAN_OR_EQUAL("<="),
    GREATER_THAN_OR_EQUAL(">="),
    LIKE("LIKE"),
    SET("SET"),
    QUESTION_MARK("?"), // Placeholder for prepared statements (Marcador para sentencias preparadas)
    FROM("FROM"),
    ORDER_BY("ORDER BY"),
    ASC("ASC"),
    DESC("DESC"),
    SINGLE_QUOTE("'"),
    GROUP_BY("GROUP BY"),
    AS("AS"),
    TRIM("TRIM"),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    BETWEEN("BETWEEN"),
    IN("IN"),
    JOIN("JOIN"),
    INNER_JOIN("INNER JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    RIGHT_JOIN("RIGHT JOIN"),
    ON("ON"),
    LIMIT("LIMIT"),
    OFFSET("OFFSET"),
    DISTINCT("DISTINCT"),
    UNION("UNION"),
    HAVING("HAVING"),
    VALUES("VALUES");

    private final String keyword;
    public static final String COMMA = ", ";
    public static final String SPACE = " ";

    private Q(String statement) {
        this.keyword = statement;
    }

    public String toSQL() {
        return keyword;
    }

    // Utility methods for SQL query construction (Métodos utilitarios para construcción de consultas SQL)

    /** 
     * Generates a SELECT query with specific columns from a table 
     * (Genera una consulta SELECT con columnas específicas de una tabla)
     * @param table
     * @param columns
     * @return 
     */
    public static String select(String table, String... columns) {
        return SELECT.toSQL() + SPACE + String.join(COMMA, columns) + SPACE + FROM.toSQL() + SPACE + table +SPACE;
    }
    public static String selectF(String... columns) {
        return SELECT.toSQL() + SPACE + String.join(COMMA, columns) + SPACE + FROM.toSQL() + SPACE ;
    }

    public static String fieldAlias(String alias, String field) {
        return alias + "." + field;
    }

    public static String fieldAliasWithAS(String alias, String field, String AS) {
        return alias + "." + field + " as " + AS;
    }

    public static String fromAlias(String alias, String table) {
        return table + SPACE + alias + SPACE;
    }
    
    public static String returning(String... columns) {
        return RETURNING.toSQL() + SPACE + String.join(COMMA, columns) + SPACE;
    }
    /**
     * Generates a SELECT * FROM table query
     * (Genera una consulta SELECT * FROM tabla)
     * @param table
     * @return 
     */
    public static String selectAll(String table) {
        return SELECT_ALL.toSQL() + SPACE + table + SPACE;
    }

    public static String selectWithJoin(String queryTable, String joinTable,
            String foreignKey, String retrievedField,
            String primaryKey) {

        String queryAlias = "t1";  // Generic alias for the query table
        String joinAlias = "t2";   // Generic alias for the table to join

        return SELECT.toSQL()+ SPACE + DISTINCT.toSQL() + SPACE
                + queryAlias + "." + foreignKey + COMMA + SPACE
                + joinAlias + "." + retrievedField + SPACE
                + FROM.toSQL() + SPACE
                + queryTable + SPACE + queryAlias + SPACE
                + INNER_JOIN.toSQL() + SPACE
                + joinTable + SPACE + joinAlias + SPACE
                + ON.toSQL() + SPACE
                + queryAlias + "." + foreignKey + EQUALS.toSQL() + joinAlias + "." + primaryKey + SPACE;
    }

    public static String selectWithMultipleJoins(String baseTable,
            String[] secondaryTables,
            String[] foreignKeys,
            String[] primaryKeys,
            String[] columnsToRetrieve) {
        // Start the query with SELECT DISTINCT
        StringBuilder query = new StringBuilder(SELECT.toSQL() + DISTINCT.toSQL() + SPACE);

        // Add columns to retrieve
        query.append(String.join(COMMA, columnsToRetrieve));

        // Base table and alias
        query.append(SPACE).append(FROM.toSQL()).append(SPACE).append(baseTable).append(" baseTable ");

        // Loop through secondary tables and build JOINs dynamically
        for (int i = 0; i < secondaryTables.length; i++) {
            query.append(SPACE).append(JOIN.toSQL()).append(SPACE).append(secondaryTables[i]).append(" secTable").append(i).append(1).append(" ");
            query.append(ON.toSQL()).append(SPACE + "baseTable.").append(foreignKeys[i]).append(EQUALS.toSQL()).append("secTable").append(i).append(1).append(".").append(primaryKeys[i]);
        }

        return query.toString();
    }

    /**
     * Generates an INSERT INTO statement with placeholders for values
     * (Genera una sentencia INSERT INTO con marcadores de posición para valores)
     * @param table
     * @param columns
     * @return 
     */
    public static String insertInto(String table, String... columns) {
        String placeholders = String.join(COMMA, Collections.nCopies(columns.length, QUESTION_MARK.toSQL()));
        return INSERT.toSQL() + SPACE + table + SPACE
                + OPEN_PARENTHESIS.toSQL() + String.join(COMMA, columns) + CLOSE_PARENTHESIS.toSQL()
                + SPACE + VALUES.toSQL() + SPACE
                + OPEN_PARENTHESIS.toSQL() + placeholders + CLOSE_PARENTHESIS.toSQL()+SPACE;
    }

    /**
     * Generates an UPDATE statement with SET clause
     * (Genera una sentencia UPDATE con cláusula SET)
     * @param table
     * @param columns
     * @return 
     */
    public static String update(String table, String... columns) {
        if (columns.length == 0) {
            throw new IllegalArgumentException("UPDATE must have at least one column to update.");
        }

        String setClause = String.join(COMMA, Arrays.stream(columns)
                .map(col -> col + SPACE +EQUALS.toSQL() + SPACE +QUESTION_MARK.toSQL())
                .toArray(String[]::new));

        return UPDATE.toSQL() + SPACE + table + SPACE + SET.toSQL() + SPACE + setClause + SPACE;
    }

    /**
     * Generates a DELETE FROM statement with optional WHERE clause
     * (Genera una sentencia DELETE FROM con cláusula WHERE opcional)
     * @param table
     * @param conditions
     * @return 
     */
    public static String deleteFrom(String table, String... conditions) {
        String whereClause = conditions.length > 0
                ? SPACE + WHERE.toSQL() + SPACE + String.join(SPACE + AND.toSQL() + SPACE,
                Arrays.stream(conditions)
                        .map(col -> col+ SPACE + EQUALS.toSQL()+ SPACE + QUESTION_MARK.toSQL())
                        .toArray(String[]::new))
                : "";

        return DELETE.toSQL() + SPACE + FROM.toSQL() + SPACE + table + whereClause + SPACE;
    }

    /**
     * Generates a WHERE clause with multiple conditions
     * (Genera una cláusula WHERE con múltiples condiciones)
     * @param conditions
     * @return 
     */
    public static String where(String... conditions) {
        if (conditions.length == 0) {
            throw new IllegalArgumentException("WHERE clause requires at least one condition.");
        }

        return WHERE.toSQL() + SPACE
                + String.join(SPACE + AND.toSQL() + SPACE,
                        Arrays.stream(conditions)
                                .map(condition -> condition + SPACE +EQUALS.toSQL() + SPACE +QUESTION_MARK.toSQL())
                                .toArray(String[]::new)) + SPACE;
    }

    /**
     * Generates an ORDER BY clause
     * (Genera una cláusula ORDER BY)
     * @param columns
     * @return 
     */
/**
 * Constructs an ORDER BY clause with optional sorting direction for each column.
 * (Construye una cláusula ORDER BY con dirección opcional para cada columna).
 *
 * @param columns List of columns with optional ASC/DESC sorting.
 *                Example: orderBy("name ASC", "age DESC")
 *                (Lista de columnas con ordenación
     * opcional ASC/DESC).
     * @return The ORDER BY SQL clause.
     */
    public static String orderBy(String... columns) {
        if (columns.length == 0) {
            throw new IllegalArgumentException("ORDER BY requires at least one column.");
        }

        // Ensuring valid ORDER BY syntax (Asegura la sintaxis correcta)
        String formattedColumns = Arrays.stream(columns)
                .map(column -> {
                    String trimmed = column.trim();
                    if (!trimmed.matches("(?i)^\\w+(\\s+(ASC|DESC))?$")) {
                        throw new IllegalArgumentException("Invalid ORDER BY format: " + column);
                    }
                    return trimmed;
                })
                .collect(Collectors.joining(COMMA));

        return ORDER_BY.toSQL() + SPACE + formattedColumns + SPACE;
    }

    /**
     * Constructs a GROUP BY clause ensuring valid column names. (Construye una
     * cláusula GROUP BY asegurando nombres de columna válidos).
     *
     * @param columns List of columns to group by. Example: groupBy("category",
     * "year") (Lista de columnas para agrupar).
     * @return The GROUP BY SQL clause.
     */
    public static String groupBy(String... columns) {
        if (columns.length == 0) {
            throw new IllegalArgumentException("GROUP BY requires at least one column.");
        }

        // Ensure valid column names (Asegurar nombres de columna válidos)
        String formattedColumns = Arrays.stream(columns)
                .map(column -> {
                    String trimmed = column.trim();
                    if (!trimmed.matches("^\\w+$")) {
                        throw new IllegalArgumentException("Invalid column name in GROUP BY: " + column);
                    }
                    return trimmed;
                })
                .collect(Collectors.joining(COMMA));

        return GROUP_BY.toSQL() + SPACE + formattedColumns + SPACE;
    }

    /**
     * Generates a HAVING clause
     * (Genera una cláusula HAVING)
     * @param condition
     * @return 
     */
    public static String having(String condition) {
        return HAVING.toSQL() + SPACE + condition + SPACE;
    }

    /**
     * Generates a JOIN statement
     * (Genera una sentencia JOIN)
     * @param table
     * @return 
     */
    public static String join(String table) {
        return JOIN.toSQL() + SPACE + table + SPACE;
    }
    
    public static String onEqual(String alias, String field,
            String alias2, String field2) {
        return ON.toSQL() + SPACE + alias + "." + field + EQUALS.toSQL() + SPACE + alias2 + "." + field2 + SPACE;
    }

    /**
     * Generates a LEFT JOIN statement
     * (Genera una sentencia LEFT JOIN)
     * @param table
     * @return 
     */
    public static String leftJoin(String table) {
        return LEFT_JOIN.toSQL() + SPACE + table + SPACE;
    }

    /**
     * Generates a RIGHT JOIN statement
     * (Genera una sentencia RIGHT JOIN)
     * @param table
     * @return 
     */
    public static String rightJoin(String table) {
        return RIGHT_JOIN.toSQL() + SPACE + table + SPACE;
    }

    /**
     * Generates an ON clause
     * (Genera una cláusula ON)
     * @param condition
     * @return 
     */
    public static String on(String condition) {
        return ON.toSQL() + SPACE + condition + SPACE;
    }

    /**
     * Generates a LIMIT clause
     * (Genera una cláusula LIMIT)
     * @param count
     * @return 
     */
    public static String limit(int count) {
        return LIMIT.toSQL() + SPACE + count + SPACE;
    }

    /**
     * Generates an OFFSET clause
     * (Genera una cláusula OFFSET)
     * @param count
     * @return 
     */
    public static String offset(int count) {
        return OFFSET.toSQL() + SPACE + count + SPACE;
    }
}
