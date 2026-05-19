

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util;

import com.simplecore.erp.client.abstractions.AbstractDTOFormInjector;
import com.simplecore.erp.client.abstractions.DTOInjector;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COAssociationPanel;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCard;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.shared.models.dto.FICOAssociationsDTO;
import com.simplecore.erp.shared.models.dto.FICOCompanyRelation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICORelationDTOInjector extends AbstractDTOFormInjector<FICompanyFormPanel> 
        implements DTOInjector<FICOAssociationsDTO>{

    private final COAssociationPanel associationPanel;
    private final List<FICOCompanyRelation> relationState;

    public FICORelationDTOInjector(FICompanyFormPanel panel) {
        super(panel);
        this.associationPanel = panel.getCOAssociationPanel();
        this.relationState = new ArrayList<>();
    }

    @Override
    public void inject(FICOAssociationsDTO dto) {
        if (dto == null || dto.getAssociationToRetrieve().isEmpty()) {
            return;
        }
        List<FICOCompanyRelation> relations = dto.getAssociationToRetrieve();
        insertCards(relations.size());

        List<COCompanyCard> cards = associationPanel.getAssociationCards();
        for (int i = 0; i < relations.size(); i++) {
            injectCardData(cards.get(i), relations.get(i));
        }
    }
    
    private void insertCards(int cardCount){
        for(int i = 0; i < cardCount; i++){
            associationPanel.addNewCard();
        }
    }
    
    private void injectCardData(COCompanyCard card,FICOCompanyRelation dto) {
        card.injectCardData(dto);
        relationState.add(dto);
    }
    
    public List<FICOCompanyRelation> getDTORelationList() {
        return relationState;
    }
}
