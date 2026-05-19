
package com.simplecore.erp.shared.responses.types;

import com.simplecore.erp.shared.responses.base.BaseResponse;
import com.simplecore.erp.shared.responses.base.ResponseType;
import com.simplecore.erp.shared.responses.base.ResultType;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TransactionResponse extends BaseResponse {

    private final boolean hasAccess; // Si tiene acceso o no
    private final String message;    // Mensaje adicional en caso de que no tenga acceso

    public TransactionResponse(String sessionId,ResultType resultType ,boolean hasAccess,String message) {
        super(sessionId, resultType);  // Se pasa el sessionId al constructor de la clase base
        this.hasAccess = hasAccess;
        this.message = message;
    }

    public boolean hasAccess() {
        return hasAccess;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.TRANSACTION_PERMISSIONS;
    }


}
