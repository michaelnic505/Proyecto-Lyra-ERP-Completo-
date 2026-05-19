

package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountSubclassDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * @param subclassId the subclassId to set
     */
    public void setSubclassId(int subclassId) {
        this.subclassId = subclassId;
    }
    
    private int subclassId;
    private final int modelId;
    private final int classId;
    private final int subclassCode;
    private final String subclassName;

    public AccountSubclassDTO(int modelId, int classId,int subclassId ,int subclassCode, String subclassName) {
        this.subclassId = subclassId;
        this.modelId = modelId;
        this.classId = classId;
        this.subclassCode = subclassCode;
        this.subclassName = subclassName;
    }

    public int getSubclassId() {
        return subclassId;
    }
    
    public int getModelId() {
        return modelId;
    }

    public int getClassId() {
        return classId;
    }

    public int getSubclassCode() {
        return subclassCode;
    }

    public String getSubclassName() {
        return subclassName;
    }

}
