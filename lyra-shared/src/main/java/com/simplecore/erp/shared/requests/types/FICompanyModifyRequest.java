

package com.simplecore.erp.shared.requests.types;

import com.simplecore.erp.shared.models.dto.FICOAssociationsDTO;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import com.simplecore.erp.shared.models.dto.FIDocumentsDTO;
import com.simplecore.erp.shared.requests.base.BaseRequest;
import com.simplecore.erp.shared.requests.base.RequestType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyModifyRequest extends BaseRequest {

    private final FICompanyDTO fiCompanyDTO;
    private final FIDocumentsDTO documentsDTO;
    private final FICOAssociationsDTO ficoAssociationDTO;

    public FICompanyModifyRequest(
            String sessionId,
            int userId,
            FICompanyDTO fiCompanyDTO,
            FIDocumentsDTO documentsDTO,
            FICOAssociationsDTO ficoAssociationDTO) {
        super(sessionId, userId);
        this.fiCompanyDTO = fiCompanyDTO;
        this.documentsDTO = documentsDTO;
        this.ficoAssociationDTO = ficoAssociationDTO;
    }

    public FICompanyDTO getFICompanyDTO() {
        return fiCompanyDTO;
    }

    public FIDocumentsDTO getDocumentsDTO() {
        return documentsDTO;
    }

    public FICOAssociationsDTO getFICOAssociationDTO() {
        return ficoAssociationDTO;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FI_COMPANY_MODIFY;
    }
}
