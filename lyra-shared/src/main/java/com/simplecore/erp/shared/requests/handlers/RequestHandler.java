
package com.simplecore.erp.shared.requests.handlers;

import com.simplecore.erp.shared.requests.base.Request;

/**
 *
 * @author user
 * @param <T>
 */
public interface RequestHandler<T extends Request> {
    Object handle(T request);
}
