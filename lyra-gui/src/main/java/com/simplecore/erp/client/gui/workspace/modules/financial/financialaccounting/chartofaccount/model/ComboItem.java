

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.model;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ComboItem {
    private final Integer id;
    private final String description;
    private final Object objectClass;

    public ComboItem(Integer id, String description, Object objectClass) {
        this.id = id;
        this.description = description;
        this.objectClass = objectClass;
    }

    public int getId() {
        return id;
    }

    public Object getObjectClass() {
        return objectClass;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return (id == null ? "" :  id + " - "+ description);
    }

}
