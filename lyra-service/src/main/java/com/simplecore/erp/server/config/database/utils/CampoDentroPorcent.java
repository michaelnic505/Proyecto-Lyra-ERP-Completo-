
package com.simplecore.erp.server.config.database.utils;


public class CampoDentroPorcent {
    
    public static String meterTexto(String texto){
        
        String Texto = " '%"+texto+"%' ";
        
        return Texto;
    }
    
}
