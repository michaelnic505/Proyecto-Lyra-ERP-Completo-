
package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICOAssociationsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<FICOCompanyRelation> companyAssociationToCreate;
    private List<FICOCompanyRelation> companyAssociationToModify;
    private List<FICOCompanyRelation> companyAssociationToRetrieve;

    public FICOAssociationsDTO(
            List<FICOCompanyRelation> companyAssociationToCreate,
            List<FICOCompanyRelation> companyAssociationToModify,
            List<FICOCompanyRelation> companyAssociationToRetrieve) {
        
        this.companyAssociationToCreate = Optional.ofNullable(companyAssociationToCreate).orElse(Collections.emptyList());
        this.companyAssociationToModify = Optional.ofNullable(companyAssociationToModify).orElse(Collections.emptyList());
        this.companyAssociationToRetrieve = Optional.ofNullable(companyAssociationToRetrieve).orElse(Collections.emptyList());
    }

    public List<FICOCompanyRelation> getAssociationToCreate() {
        return companyAssociationToCreate;
    }

    public List<FICOCompanyRelation> getAssociationToModify() {
        return companyAssociationToModify;
    }
    
    public List<FICOCompanyRelation> getAssociationToRetrieve() {
        return companyAssociationToRetrieve;
    }
}
