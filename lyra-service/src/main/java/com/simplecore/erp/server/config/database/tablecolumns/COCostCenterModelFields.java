

package com.simplecore.erp.server.config.database.tablecolumns;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum COCostCenterModelFields {
    ID("ID"),
    NAME("NAME"),
    DESCRIPTION("DESCRIPTION"),
    CREATED_BY("CREATED_BY"),
    CREATED_AT("CREATED_AT"),
    UPDATED_AT("UPDATED_AT"),
    STATUS("STATUS");

    private final String colunmName;

    private COCostCenterModelFields(String colunmName) {
        this.colunmName = colunmName;
    }

    public String getColunmName() {
        return colunmName;
    }

}
