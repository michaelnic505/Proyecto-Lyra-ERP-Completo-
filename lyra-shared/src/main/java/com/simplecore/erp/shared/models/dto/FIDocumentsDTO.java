
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
public class FIDocumentsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<FICompanyDocument> documentsRetrieves;
    private List<FICompanyDocument> documentsToCreate;
    private List<FICompanyDocument> documentsToModify;
    private List<FICompanyDocument> documentsToDelete;

    public FIDocumentsDTO(
            List<FICompanyDocument> documentsToRetrieve,
            List<FICompanyDocument> documentsToCreate,
            List<FICompanyDocument> documentsToModify,
            List<FICompanyDocument> documentsToDelete) {
        this.documentsRetrieves = Optional.ofNullable(documentsToRetrieve).orElse(Collections.emptyList());
        this.documentsToCreate = Optional.ofNullable(documentsToCreate).orElse(Collections.emptyList());
        this.documentsToModify = Optional.ofNullable(documentsToModify).orElse(Collections.emptyList());
        this.documentsToDelete = Optional.ofNullable(documentsToDelete).orElse(Collections.emptyList());
    }

    public List<FICompanyDocument> getDocumentsRetrieves() {
        return documentsRetrieves;
    }
    
    public List<FICompanyDocument> getDocumentsToCreate() {
        return documentsToCreate;
    }

    public List<FICompanyDocument> getDocumentsToModify() {
        return documentsToModify;
    }

    public List<FICompanyDocument> getDocumentsToDelete() {
        return documentsToDelete;
    }
}
