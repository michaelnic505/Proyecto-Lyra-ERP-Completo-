
package com.simplecore.erp.config.database.utils;

import java.util.List;

public class SQL_Modify_Statement {
    
    public static String setModifyFields(List<String> field){
        
        String statement = "";
        
        for(int i = 0; i < field.size();i++){

            statement = statement + field.get(i)
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.QUESTION_MARK.toSQL()+", ";
            
        }

        String finalString = statement.substring(0, statement.length()-2);
        
        return finalString;
    };
    
}
