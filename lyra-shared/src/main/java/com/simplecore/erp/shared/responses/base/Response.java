
package com.simplecore.erp.shared.responses.base;

import java.io.Serializable;

/**
 *
 * @author user
 */
public interface Response extends Serializable {
    ResponseType getResponseType();
    String getSessionId();
}
