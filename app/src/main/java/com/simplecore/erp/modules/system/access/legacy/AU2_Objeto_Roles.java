
package com.simplecore.erp.modules.system.access.legacy;


public class AU2_Objeto_Roles {

    /**
     * @return the module
     */
    public String getModule() {
        return module;
    }

    /**
     * @param module the module to set
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * @return the nombreRol
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * @param nombreRol the nombreRol to set
     */
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    /**
     * @return the transaction
     */
    public String getTransaction() {
        return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    /**
     * @return the transactionName
     */
    public String getTransactionName() {
        return transactionName;
    }

    /**
     * @param transactionName the transactionName to set
     */
    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }




    private String nombreRol;
    private String transaction;
    private String transactionName;
    private String module;
    
    
}
