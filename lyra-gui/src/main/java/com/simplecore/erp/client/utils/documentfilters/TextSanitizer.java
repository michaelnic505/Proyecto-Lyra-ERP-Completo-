
package com.simplecore.erp.client.utils.documentfilters;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TextSanitizer {

    public static String sanitizeVarcharWithoutSpaces(String input, int limit, boolean uppercase) {
        if (input == null) return "";

        // Eliminar espacios
        input = input.replace(" ", "");

        // Aplicar mayúsculas si se requiere
        if (uppercase) {
            input = input.toUpperCase();
        }

        // Cortar si excede el límite
        if (input.length() > limit) {
            input = input.substring(0, limit);
        }

        return input;
    }

    public static String sanitizeVarcharWithSpaces(String input, int limit, boolean uppercase) {
        if (input == null) {
            return "";
        }
        if (uppercase) {
            input = input.toUpperCase();
        }
        if (input.length() > limit) {
            input = input.substring(0, limit);
        }

        return input;

    }

    public static String sanitizeNumeric(String input, int limit) {
        if (input == null) {
            return "";
        }

        if (!input.matches("\\d*")) {
            return "";
        }

        // Limitar la longitud
        if (input.length() > limit) {
            input = input.substring(0, limit);
        }

        return input;
    }
    
    // Método para sanitizar números con límite de dígitos enteros y decimales
    public static String sanitizeDoubleWithLimit(String input, int maxIntegerDigits, int maxDecimalDigits) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Validar si es un número válido según el filtro de límite
        if (isValidDouble(input, maxIntegerDigits, maxDecimalDigits)) {
            return input; // Si es válido, retornamos el input tal cual
        } else {
            return ""; // Si no es válido, retornamos una cadena vacía
        }
    }

    // Método privado que valida si un número cumple con el límite de dígitos enteros y decimales
    private static boolean isValidDouble(String text, int maxIntegerDigits, int maxDecimalDigits) {
        // Expresión regular para limitar tanto los dígitos enteros antes del punto
        // como los decimales después del punto
        String regex = "^\\d{0," + maxIntegerDigits + "}(\\.\\d{0," + maxDecimalDigits + "})?$";
        return text.matches(regex);
    }

    public enum Types {
        VARCHAR_SPACED_UPPERCASE,
        VARCHAR_SPACED,
        VARCHAR_NO_SPACED_UPPERCASE,
        VARCHAR_NO_SPACED,
        NUMERIC,
        DECIMAL
    }
}
