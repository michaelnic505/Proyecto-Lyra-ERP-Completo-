
package com.simplecore.erp.shared.requests.base;


/**
 *
 * @author user
 */
import java.io.Serializable;

public interface Request extends Serializable {
    RequestType getRequestType();
    String getSessionId();
    int getUserId();
}

