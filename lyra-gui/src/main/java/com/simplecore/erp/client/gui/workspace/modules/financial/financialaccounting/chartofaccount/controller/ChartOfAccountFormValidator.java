
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.utils.notifications.AppMessages;
import java.awt.Component;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountFormValidator {

    public static class ValidationResult {
        public final boolean valid;
        public final String errorMessage;
        public final Component focusComponent;

        public ValidationResult(boolean valid, String errorMessage, Component focusComponent) {
            this.valid = valid;
            this.errorMessage = errorMessage;
            this.focusComponent = focusComponent;
        }

        public static ValidationResult ok() {
            return new ValidationResult(true, null, null);
        }
    }

    public ValidationResult validate(String modelName,
                                     String modelDescription,
                                     String accountCode,
                                     String accountName,
                                     Component modelField,
                                     Component descriptionLabel,
                                     Component codeField,
                                     Component nameField) {

        if (modelName == null || modelName.isBlank()) {
            return new ValidationResult(false, AppMessages.msg(AppMessages.Key.EMPTY_FIELDS), modelField);
        }
        if (modelDescription == null || modelDescription.isBlank()) {
            return new ValidationResult(false, AppMessages.msg(AppMessages.Key.EMPTY_FIELDS), descriptionLabel);
        }
        if (accountCode == null || accountCode.isBlank()) {
            return new ValidationResult(false, AppMessages.msg(AppMessages.Key.EMPTY_FIELDS), codeField);
        }
        if (accountName == null || accountName.isBlank()) {
            return new ValidationResult(false, AppMessages.msg(AppMessages.Key.EMPTY_FIELDS), nameField);
        }

        return ValidationResult.ok();
    }
}
