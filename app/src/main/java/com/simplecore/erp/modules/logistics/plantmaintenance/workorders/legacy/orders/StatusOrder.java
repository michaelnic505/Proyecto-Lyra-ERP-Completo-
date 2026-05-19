
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders;


public enum StatusOrder {
    
    
    ST1_ORDER_CREATED("OCRTD","ORDEN CREADA","ORDER CREATED","COMMANDE CRÉÉE","PEDIDO CRIADO"),
    ST2_ORDER_IN_PLANNING("OIPNN","ORDEN EN PLANEACIÓN","ORDER IN PLANNING","ORDRE DANS LA PLANIFICATION","ORDEM NO PLANEJAMENTO"),
    ST3_ORDER_UNDER_APPROVAL("OUAPP","ORDEN EN APROBACIÓN","ORDER UNDER APPROVAL","ORDRE EN APPROBATION","ORDEM EM APROVAÇÃO"),
    ST4_ORDER_APPROVED("OAPPV","ORDEN APROBADA","ORDER APPROVED","ORDRE APPROUVÉE","ORDEM APROVADO"),
    ST5_SCHEDULED_ORDER("OSCHD","ORDEN PROGRAMADA","SCHEDULED ORDER","ORDRE PRÉVUE","ORDEM AGENDADO"),
    ST6_ORDER_IN_EXECUTION("OIEXN","ORDEN EN EJECUCIÓN","ORDER IN EXECUTION","ORDRE EN EXÉCUTION","ORDEM EM EXECUÇÃO"),
    ST7_ORDER_EXECUTED("OEXTD","ORDEN EJECUTADA","ORDER EXECUTED","ORDRE EXÉCUTÉ","ORDEM EXECUTADA"),
    ST8_CLOSED_ORDER("OCLSD","ORDEN CERRADA","ORDER CLOSED","ORDRE CLOS","ORDEM FECHADA"),
    ST9_ORDER_REJECTED("ORJTD","ORDEN RECHAZADA","ORDER REJECTED","ORDRE REJETÉE","ORDEM REJEITADA"),
    ST10_ORDER_CANCELED("OCCLD","ORDEN CANCELADA","ORDER CANCELED","ORDRE ANNULÉE","ORDEM CANCELADO");
    
    
    private final String statusCode;
    private final String statusDescriptionES;
    private final String statusDescriptionEN;
    private final String statusDescriptionFR;
    private final String statusDescriptionPT;

    private StatusOrder(String statusCode, String statusDescriptionES, String statusDescriptionEN, String statusDescriptionFR, String statusDescriptionPT) {
        this.statusCode = statusCode;
        this.statusDescriptionES = statusDescriptionES;
        this.statusDescriptionEN = statusDescriptionEN;
        this.statusDescriptionFR = statusDescriptionFR;
        this.statusDescriptionPT = statusDescriptionPT;
    }

    public String getStatusCode() {
        return statusCode;
    }

    
    public String getDescription(){
        String des = "";
                des = statusDescriptionEN;
           
        
        return des;
    }
    

    
}
