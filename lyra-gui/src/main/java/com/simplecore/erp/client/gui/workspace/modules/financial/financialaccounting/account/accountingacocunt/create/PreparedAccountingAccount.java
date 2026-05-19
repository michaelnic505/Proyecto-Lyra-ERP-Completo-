package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.create;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models.AcModComboItem;
import com.simplecore.erp.shared.models.dto.AccountSubclassDTO;
import com.simplecore.erp.shared.models.dto.AccountingAccountDTO;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class PreparedAccountingAccount {

    JTextField accountNameTf;
    JTextArea descriptionTextArea;
    ActiveSession activeSession;

    public PreparedAccountingAccount(JTextField accountNameTf, JTextArea descriptionTextArea, ActiveSession activeSession) {
        this.accountNameTf = accountNameTf;
        this.descriptionTextArea = descriptionTextArea;
        this.activeSession = activeSession;
    }
    
    private JComboBox subclassCombo;
    private JComboBox parentCombo;

    public void initializeCombos(JComboBox subclassCombo, JComboBox parentCombo) {
        this.subclassCombo = subclassCombo;
        this.parentCombo = parentCombo;
    }

    public AccountingAccountDTO prepareAccountingAccounting(String accountCode) {
        String nextAccountCode = accountCode;
        String accountName = accountNameTf.getText().trim();
        String description = descriptionTextArea.getText().trim();
        String createdBy = activeSession.getUsername();
        Integer parentAccountId = getParentAccountId();
        Integer subclassId = getSubclassId();

        return new AccountingAccountDTO.Builder()
                .accountCode(nextAccountCode)
                .accountName(accountName)
                .accountDescription(description)
                .createdBy(createdBy)
                .parentAccountId(parentAccountId)
                .subclassId(subclassId)
                .build();

    }

    private Integer getParentAccountId() {
        AcModComboItem item = (AcModComboItem) parentCombo.getSelectedItem();
        if (item != null && !item.toString().trim().isEmpty()) {
            return ((AccountingAccountDTO) ((AcModComboItem) parentCombo.getSelectedItem()).getObjectClass()).getAccountId();
        }
        return null;
    }

    private Integer getSubclassId() {
        AcModComboItem item = (AcModComboItem) subclassCombo.getSelectedItem();
        if (item != null && !item.toString().trim().isEmpty()) {
            return ((AccountSubclassDTO) ((AcModComboItem) subclassCombo.getSelectedItem()).getObjectClass()).getSubclassId();
        }
        return null;
    }
}
