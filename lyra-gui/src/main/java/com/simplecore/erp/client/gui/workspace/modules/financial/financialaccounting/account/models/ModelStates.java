package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public enum ModelStates {
    CREATED,
    READY,
    IN_USE,
    CANCELLED;

    public ModelStates getNext() {
        return switch (this) {
            case CREATED ->
                READY;
            case READY ->
                IN_USE;
            default ->
                this;
        };
    }

    public static ModelStates fromString(String state) {
        try {
            return ModelStates.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
