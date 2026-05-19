
package com.simplecore.erp.server.config.database.utils;

public class Function_SQL {
    
    public static String SUM(String campo) {

        String SUMA = "SUM(" + campo + ")";
        return SUMA;
    }
    
    public static String AS(String campo) {

        String AS = " AS '" + campo + "'";
        return AS;
    }

    public static String MAX(String campo) {

        String MAX = "MAX(" + campo + ")";
        return MAX;
    }

    public static String MIN(String campo) {

        String MAX = "MIN(" + campo + ")";
        return MAX;
    }
    
    public static String COUNT(String campo) {

        String MAX = "COUNT(" + campo + ")";
        return MAX;
    }


}
