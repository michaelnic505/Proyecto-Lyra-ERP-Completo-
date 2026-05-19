
package com.simplecore.erp.config.database.tables;


public class Entre_Comillas {
    
    public static String entreComillas(String campo){
        String comilla = "'";
                         
        String Final = comilla+campo+comilla;
        
        return Final;
    }
    
    
    
}
