
package com.simplecore.erp.client.services.base;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.dependencies.OperationType;
import static com.simplecore.erp.client.dependencies.OperationType.CREATE;
import static com.simplecore.erp.client.dependencies.OperationType.MODIFY;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.base.BaseRequest;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Michael F. Sánchez
 * @param <T>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
/**
 * Abstract base class for handling data persistence operations based on a given {@link OperationType}.
 * This class centralizes the persistence flow (create or modify) and delegates the construction and execution
 * of requests to concrete subclasses.
 *
 * @param <T> a type extending {@link FormState}, representing the current state of the form being persisted
 */
public abstract class AbstractPersistence<T extends FormState,CR extends BaseRequest,MR extends BaseRequest> {

    protected final T formState;
    protected final ObjectOutputStream output;
    protected final ObjectInputStream input;
    protected final ActiveSession activeSession;
    protected final OperationType operationType;
    protected final String transactionCode;
    protected final ServerController serverController;
    protected final SystemMessages notificator = new SystemMessages();
    protected String sessionID;
    protected int userID;
    protected String username;

    /**
     * Constructs a new AbstractPersistence instance with the given dependencies.
     * Automatically disables editing state if the operation type is VIEW.
     *
     * @param formState       the form state object containing user input
     * @param output          the output stream to communicate with the server
     * @param input           the input stream to receive server responses
     * @param activeSession   the current active user session
     * @param operationType   the type of operation to perform (e.g., CREATE, MODIFY, VIEW)
     * @param transactionCode the transaction identifier used for tracing or auditing
     */
    public AbstractPersistence(T formState,
                               ObjectOutputStream output,
                               ObjectInputStream input,
                               ActiveSession activeSession,
                               OperationType operationType,
                               String transactionCode) {
        this.formState = formState;
        this.output = output;
        this.input = input;
        this.activeSession = activeSession;
        this.operationType = operationType;
        this.transactionCode = transactionCode;
        this.serverController = new ServerController(output, input);
        this.sessionID = activeSession.getSessionId();
        this.userID = activeSession.getUserId(); 
        this.username = activeSession.getUsername();
        turnOffState(operationType);
    }

    /**
     * Disables changes to the form if the current operation is of type VIEW.
     *
     * @param operationType the type of operation being processed
     */
    private void turnOffState(OperationType operationType) {
        if (operationType == OperationType.VIEW) {
            formState.setSaved(true);
        }
    }

    /**
     * Performs the persistence logic based on the specified {@link OperationType}.
     * If the type is CREATE, it builds and executes an insert request.
     * If the type is MODIFY, it builds and executes an update request.
     * VIEW is ignored as it requires no persistence.
     */
    private void persistRequestByOperationType() {
        if (operationType == null) {
            return;
        }
        switch (operationType) {
            case CREATE -> {
                CR createRequest = buildCreateRequest();
                if (createRequest != null) {
                    executeInsert(createRequest);
                }
            }
            case MODIFY -> {
                MR modifyRequest = buildModifyRequest();
                if (modifyRequest != null) {
                    executeChange(modifyRequest);
                }
            }
            default ->
                throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }
    }

    /**
     * Builds the request object to be used in a CREATE operation.
     *
     * @return a concrete {@link BaseRequest} for insertion
     */
    protected abstract CR buildCreateRequest();

    /**
     * Builds the request object to be used in a MODIFY operation.
     *
     * @return a concrete {@link BaseRequest} for update
     */
    protected abstract MR buildModifyRequest();

    /**
     * Executes the insert operation using the given request.
     *
     * @param request the request to be sent to the server for insertion
     */
    protected abstract void executeInsert(CR request);

    /**
     * Executes the update operation using the given request.
     *
     * @param request the request to be sent to the server for modification
     */
    protected abstract void executeChange(MR request);

    /**
     * Entry point to trigger the persistence logic based on the configured operation type.
     */
    public void persistData() {
        persistRequestByOperationType();
    }
}
