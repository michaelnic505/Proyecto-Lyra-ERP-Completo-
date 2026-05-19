package com.simplecore.erp.shared.responses.base;

import java.io.Serializable;

/**
 *
 * @author user
 */
public abstract class BaseResponse implements Response, Serializable {

    private static final long serialVersionUID = 1L;

    private final String sessionId;
    private ResultType resultType;

    public BaseResponse(String sessionId, ResultType resultType) {
        this.sessionId = sessionId;
        this.resultType = resultType;
    }

    public ResultType getResultType() {
        return resultType;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    // --- Operaciones CRUD positivas ---
    public boolean wasFound() {
        return resultType == ResultType.FOUND;
    }

    public boolean wasCreated() {
        return resultType == ResultType.CREATED;
    }

    public boolean wasUpdated() {
        return resultType == ResultType.UPDATED;
    }

    public boolean wasDeleted() {
        return resultType == ResultType.DELETED;
    }

    // --- Estado de ejecución sin resultado específico ---
    public boolean wasExecuted() {
        return resultType == ResultType.EXECUTED;
    }

    // --- Errores ---
    public boolean isSqlError() {
        return resultType == ResultType.SQL_ERROR;
    }

    public boolean isValidationError() {
        return resultType == ResultType.VALIDATION_ERROR;
    }

    public boolean isUnauthorized() {
        return resultType == ResultType.UNAUTHORIZED;
    }

    public boolean isUnknownError() {
        return resultType == ResultType.UNKNOWN_ERROR;
    }

    // --- Evaluadores generales ---
    public boolean isSuccess() {
        return switch (resultType) {
            case FOUND, CREATED, UPDATED, DELETED, EXECUTED ->
                true;
            default ->
                false;
        };
    }

    public boolean isError() {
        return switch (resultType) {
            case SQL_ERROR, VALIDATION_ERROR, UNAUTHORIZED, UNKNOWN_ERROR ->
                true;
            default ->
                false;
        };
    }
}
