
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterVarchar;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class TextFieldFilterService {

    private final MatchCode accountModelMatchCode;
    private final JTextField chartAccountCodeTextField;
    private final JTextField chartAccountNameTextField;

    public TextFieldFilterService(Builder builder) {
        this.accountModelMatchCode = builder.accountModelMatchCode;
        this.chartAccountCodeTextField = builder.chartAccountCodeTextField;
        this.chartAccountNameTextField = builder.chartAccountNameTextField;
        setAccountModelMatchCodeDocumentFilter();
        setChartAccountCodeTextFieldDocumentFilter();
        setChartAccountNameTextFieldDocumentFilter();
    }
    
    // Sets the document filter for account model match code field
    private void setAccountModelMatchCodeDocumentFilter() {
        AbstractDocument document = (AbstractDocument) accountModelMatchCode.getTextField().getDocument();
        document.setDocumentFilter(new DocFilterVarcharWithoutSpace(100));
    }

    // Sets the document filter for chart account code field
    private void setChartAccountCodeTextFieldDocumentFilter() {
        AbstractDocument document = (AbstractDocument) chartAccountCodeTextField.getDocument();
        document.setDocumentFilter(new DocFilterVarcharWithoutSpace(20));
    }

    // Sets the document filter for chart account name field
    private void setChartAccountNameTextFieldDocumentFilter() {
        AbstractDocument document = (AbstractDocument) chartAccountNameTextField.getDocument();
        document.setDocumentFilter(new DocumentFilterVarchar(100));
    }

    // Builder class for constructing TextFieldFilterService instance
    public static class Builder {

        private MatchCode accountModelMatchCode;
        private JTextField chartAccountCodeTextField;
        private JTextField chartAccountNameTextField;

        public Builder accountModelMatchCode(MatchCode accountModelMatchCode) {
            this.accountModelMatchCode = accountModelMatchCode;
            return this;
        }

        public Builder chartAccountCodeTextField(JTextField chartAccountCodeTextField) {
            this.chartAccountCodeTextField = chartAccountCodeTextField;
            return this;
        }

        public Builder chartAccountNameTextField(JTextField chartAccountNameTextField) {
            this.chartAccountNameTextField = chartAccountNameTextField;
            return this;
        }

        public TextFieldFilterService build() {
            return new TextFieldFilterService(this);
        }
    }
}

