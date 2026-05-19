
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.subclasses;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountSubclassEditor extends DefaultCellEditor {

    public AccountSubclassEditor(JTextField textField,DocumentFilter filter) {
        super(textField);
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
    }

}
