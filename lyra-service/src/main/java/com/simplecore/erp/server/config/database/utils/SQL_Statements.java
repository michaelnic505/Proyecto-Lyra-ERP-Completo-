
package com.simplecore.erp.server.config.database.utils;

import java.util.List;

public class SQL_Statements {
    
    public static String Select(List<String> field){
        
        String statement = "";
        
        for(int i = 0; i < field.size();i++){

            statement = statement + field.get(i)+", ";
                    
            
        }

        String finalString = statement.substring(0, statement.length()-2);
        
        return finalString;
    };

    
    
    
}
