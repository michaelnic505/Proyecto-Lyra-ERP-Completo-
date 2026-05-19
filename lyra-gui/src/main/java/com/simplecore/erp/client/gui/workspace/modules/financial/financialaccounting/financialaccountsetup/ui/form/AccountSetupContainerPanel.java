

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form;

import com.simplecore.erp.shared.models.dto.AccountCardData;
import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCard;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountSetupContainerPanel extends JPanel{
    
    private final JPanel cardsContainer;
    private final List<AccountSetupCard> accountCards;
    private OperationType operationType;

    public AccountSetupContainerPanel() {
        setLayout(new BorderLayout());
        
        accountCards = new ArrayList<>();
        cardsContainer = new JPanel();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        cardsContainer.setBackground(getBackground());

        JScrollPane scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(getBackground());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setOperationType(OperationType opType) {
        this.operationType = opType;
    }
    
    public void addNewCard(AccountCardData data) {
        AccountSetupCard card = new AccountSetupCard();
        injectValues(card, data);
        componentEnabler(card);
        accountCards.add(card);
        cardsContainer.add(card);
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }

    private void injectValues(AccountSetupCard card, AccountCardData data) {
        card.setAccountData(data);
        card.getAccountNameTF().setText(data.accountName);
        card.getAccountNumberTF().setText(data.accountCode);
        card.getAccountClassNameLB().setText(data.className);
        card.getSubClassCodeValueLB().setText(data.subclassCode);
        card.getSubClassNameValueLB().setText(data.subclassName); // ← cuidado, este se repite (¿error?)
        card.getChartOfAccountCodeValueLB().setText(data.chartOfAccountCode);
        card.getChartOfAccountNameValueLB().setText(data.chartOfAccountName);
    }


    public List<AccountSetupCard> getListCards() {
        return accountCards;
    }

    private void componentEnabler(AccountSetupCard card) {
        card.getAccountNameTF().setEditable(false);
        card.getAccountNumberTF().setEditable(false);
    }

}
