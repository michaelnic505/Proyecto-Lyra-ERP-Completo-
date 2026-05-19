
package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public enum SocietyClasses {

    CLASS_ID("CLASS_ID"),
    CLASS_KEY("CLASS_KEY");
    
    String columnName;

    private SocietyClasses(String columnName) {
        this.columnName = columnName;
    }

    public String columnName() {
        return columnName;
    }
    
}
