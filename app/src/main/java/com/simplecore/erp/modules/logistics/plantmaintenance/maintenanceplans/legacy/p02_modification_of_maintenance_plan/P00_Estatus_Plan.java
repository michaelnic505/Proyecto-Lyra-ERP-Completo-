
package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceplans.legacy.p02_modification_of_maintenance_plan;


public enum P00_Estatus_Plan {
    
    ACTIVO("AC","Active"),
    INACTIVO("IN","Inactive"),
    OPERANDO("OP","Operating");

    public String getID() {
        return ID;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }
    
    private String ID;
    private String DESCRIPCION;

    private P00_Estatus_Plan(String ID, String DESCRIPCION) {
        this.ID = ID;
        this.DESCRIPCION = DESCRIPCION;
    }
            
    
 
}
