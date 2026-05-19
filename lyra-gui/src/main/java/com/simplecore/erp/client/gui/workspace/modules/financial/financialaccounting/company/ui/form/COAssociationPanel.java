

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

import com.simplecore.erp.client.dependencies.OperationType;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class COAssociationPanel extends JPanel {

    private final JPanel cardsContainer;
    private final List<COCompanyCard> associationCards;
    private COCompanyCardFactory cardFactory;
    private COCompanyCardListener ficoListener;
    private JButton addButton;
    private OperationType operationType;

    public COAssociationPanel() {
        setLayout(new BorderLayout());

        associationCards = new ArrayList<>();

        addButton = new JButton("Add CO Association");
        addButton.setIcon(new CustomSVGIcon("/icons/svg/add_circle.svg",new Dimension(20,20)));
        addButton.addActionListener(e -> addNewCard());

        cardsContainer = new JPanel();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        cardsContainer.setBackground(getBackground());

        JScrollPane scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(getBackground());

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(addButton);
        topBar.setBackground(getBackground());

        add(topBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public void setCardFactory(COCompanyCardFactory cardFactory,
            COCompanyCardListener ficoListener){
        this.cardFactory = cardFactory;
        this.ficoListener = ficoListener;
    }
    public void setOperationType(OperationType opType){
        this.operationType = opType;
    }
    
    
    public void addNewCard() {
        COCompanyCard card = cardFactory.createCard();
        card.setRemoveAction(this::removeCard);
        componentEnabler(card);
        associationCards.add(card);
        cardsContainer.add(card);
        cardsContainer.revalidate();
        cardsContainer.repaint();
        updateCreatedCards();
    }

    public void removeCard(COCompanyCard card) {
        Long relationID = card.getFICOAssociationData().getFICORelationID();
        if (relationID != null) {
            JOptionPane.showMessageDialog(
                    null,
                    AppMessages.msg(AppMessages.Key.FICO_RELATIONS_CANNOT_BE_DELETED),
                    AppMessages.msg(AppMessages.Key.ACTION_NOT_ALLOWED),
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        associationCards.remove(card);
        cardsContainer.remove(card);
        cardsContainer.revalidate();
        cardsContainer.repaint();
        updateCreatedCards();
    }

    public List<COCompanyCard> getAssociationCards() {
        return associationCards;
    }
    
    private void updateCreatedCards(){
        ficoListener.sendCard(associationCards);
    }
    
    public JButton getAddButton(){return addButton;};
    
    private void componentEnabler(COCompanyCard card) {
        if (operationType == OperationType.VIEW) {
            card.getCOCompanyMatchCode().getTextField().setEditable(false);
            card.getCOCompanyMatchCode().getButton().setEnabled(false);
            card.getCostVariantMatchCode().getTextField().setEditable(false);
            card.getCostVariantMatchCode().getButton().setEnabled(false);
            card.getCurrencyMatchCode().getTextField().setEditable(false);
            card.getCurrencyMatchCode().getButton().setEnabled(false);
            card.getInternalOrderCBx().setEnabled(false);
            card.getCostCenterMandatoryCBx().setEnabled(false);
            card.getStatusCombo().setEnabled(false);
            card.getRelationTypeCombo().setEnabled(false);
            card.getCostCenterPlanMatchCode().getTextField().setEditable(false);
            card.getCostCenterPlanMatchCode().getButton().setEnabled(false);
            card.getValidFromChooser().getDateEditor().setEnabled(false);
            card.getValidFromChooser().getCalendarButton().setEnabled(false);
            card.getValidToChooser().getDateEditor().setEnabled(false);
            card.getValidToChooser().getCalendarButton().setEnabled(false);
            card.getRemoveButton().setEnabled(false);
        }
    }
}
