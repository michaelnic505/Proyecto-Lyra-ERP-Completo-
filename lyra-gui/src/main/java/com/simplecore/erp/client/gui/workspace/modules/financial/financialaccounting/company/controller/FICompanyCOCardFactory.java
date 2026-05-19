

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.utils.currencies.CurrencyController;
import com.simplecore.erp.client.gui.utils.currencies.CurrencyService;
import com.simplecore.erp.client.gui.workspace.modules.financial.controlling.company.auxiliar.COCompanyController;
import com.simplecore.erp.client.gui.workspace.modules.financial.controlling.company.auxiliar.COCompanyService;
import com.simplecore.erp.client.gui.workspace.modules.financial.controlling.costvariant.auxiliar.CostVariantController;
import com.simplecore.erp.client.gui.workspace.modules.financial.controlling.costvariant.auxiliar.CostVariantRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.controlling.services.costcenterplan.CostCenterPlanController;
import com.simplecore.erp.client.gui.workspace.modules.financial.controlling.services.costcenterplan.CostCenterPlanService;
import com.simplecore.erp.client.gui.workspace.modules.financial.fico.controller.FICORelationStatusController;
import com.simplecore.erp.client.gui.workspace.modules.financial.fico.controller.FICORelationTypeController;
import com.simplecore.erp.client.gui.workspace.modules.financial.fico.services.FICORelationStatusService;
import com.simplecore.erp.client.gui.workspace.modules.financial.fico.services.FICORelationTypeService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCard;
import com.simplecore.erp.client.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.text.AbstractDocument;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyCOCardFactory {

    private final ActiveSession session;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    
    private COCompanyService companyService;
    private CostVariantRetrieveService costVariantService;
    private CurrencyService currencyService;
    private CostCenterPlanService costCenterPlanservice;

    // Constructor privado que solo usa el Builder
    public FICompanyCOCardFactory(ActiveSession session,
            ObjectOutputStream output,
            ObjectInputStream input) {
        this.session = session;
        this.output = output;
        this.input = input;
    }
 
    public COCompanyCard buildCard() {
        COCompanyCard card = new COCompanyCard();
        
        var coCompany = buildCOCompanyController(
                card.getCOCompanyMatchCode(),
                card.companyListener(),
                List.of(0, 1));
        
        var costVariant = buildCostVariantController(
                card.getCostVariantMatchCode(), 
                card.costVariantListener(), 
                List.of(0,1));
        
        var relationTypes = buildRelationTypesController(
                card.getRelationTypeCombo());
        
        var costCenterPlan = buildCostCenterPlanController(
                card.getCostCenterPlanMatchCode(), 
                card.costCenterPlanListener(), 
                List.of(0,1));
        
        var currency = buildCurrencyController(
                card.getCurrencyMatchCode(), 
                card.currencyListener(), 
                List.of(0, 1));
        
        var relationStatus = buildRelationStatusController(
                card.getStatusCombo());
        
        card.setCOCompanyService(companyService);
        card.setCostVariantService(costVariantService);
        card.setCurrencyService(currencyService);
        card.setCostCenterPlanService(costCenterPlanservice);
        card.setCreatedBy(session.getUsername());
        
        return card;
    }
    
    private COCompanyController buildCOCompanyController(MatchCode matchCode,
            TableSelectionListener listener,
            List<Integer> visibleColumns){
        
        companyService = new COCompanyService(session, output, input);
       
        ((AbstractDocument)matchCode.getTextField().getDocument()).setDocumentFilter(new DocFilterVarcharWithoutSpace(20));
        return new COCompanyController(matchCode, companyService, listener, visibleColumns);
    }

    private CostVariantController buildCostVariantController(MatchCode matchCode,
            TableSelectionListener listener,
            List<Integer> visibleColumns) {
        
        costVariantService = new CostVariantRetrieveService(session, output, input);
        
        ((AbstractDocument)matchCode.getTextField().getDocument()).setDocumentFilter(new DocFilterVarcharWithoutSpace(20));
        return new CostVariantController(matchCode, costVariantService, listener, visibleColumns);
    }

    private CostCenterPlanController buildCostCenterPlanController(MatchCode matchCode,
            TableSelectionListener listener,
            List<Integer> visibleColumns) {
   
        costCenterPlanservice = new CostCenterPlanService(session, output, input);
        
        ((AbstractDocument)matchCode.getTextField().getDocument()).setDocumentFilter(new DocFilterVarcharWithoutSpace(20));
        return new CostCenterPlanController(matchCode, costCenterPlanservice, listener, visibleColumns);
    }

    private FICORelationTypeController buildRelationTypesController(JComboBox combo) {
        var service = new FICORelationTypeService(session, output, input);
        return new FICORelationTypeController(combo, service);
    }

    private FICORelationStatusController buildRelationStatusController(JComboBox combo){
        var status = new FICORelationStatusService(session, output, input);
        return new FICORelationStatusController(combo, status);
    }
    
    private CurrencyController buildCurrencyController(MatchCode matchCode,
            TableSelectionListener listener,
            List<Integer> visibleColumns){
        
        currencyService = new CurrencyService(session, output, input);
        
        ((AbstractDocument)matchCode.getTextField().getDocument()).setDocumentFilter(new DocFilterVarcharWithoutSpace(3));
        return new CurrencyController(matchCode, currencyService, listener, visibleColumns);
    }
}
