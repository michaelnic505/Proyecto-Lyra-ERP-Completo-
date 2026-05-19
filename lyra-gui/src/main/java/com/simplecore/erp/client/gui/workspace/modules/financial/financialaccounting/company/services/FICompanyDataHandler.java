

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services;

import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.utils.countries.controller.CountryService;
import com.simplecore.erp.client.gui.utils.timezones.TimezonesService;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.model.ComboItem;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.ChartOfAccountsService;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCard;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.client.utils.validators.FormValidator;
import com.simplecore.erp.shared.models.dto.ChartOfAccountDTO;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Optional;
import javax.swing.JComboBox;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCardListener;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCardValidator;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyDataHandler {

    private FICompanyFormPanel panel;
    private FICompanyFormState formState;
    private COCompanyCardListener companyCOCard;
    private TimezonesService timezoneService;
    private TableSelectionListener chartsListener;
    private CountryService countryServices;
    private ChartOfAccountsService chartService;
    private TableSelectionListener countryListener;
    private TableSelectionListener timezoneListener;
    

    public FICompanyDataHandler(FICompanyFormPanel panel,
            FICompanyFormState formState) {
        this.panel = panel;
        this.formState = formState;
        initListeners();
    }
    
    private void initListeners() {
        initCompanyCodeListener();
        initCompanyNameListener();
        initLegalNameListener();
        initBussinessTypeListener();
        initBussinessClassListener();
        initCountryListener();
        initLegalAddressListener();
        initEmailListener();
        initPhoneListener();
        initOfficialLanguageListener();
        initTimezoneListener();
        initChartOfAccountListener();
        createdByListener();
        updatedByListener();
        ficoCardListener();
        initCompanyStatusCombo();
    }
    
    public void setChartOfAccountService(ChartOfAccountsService service){
        this.chartService = service;
    }
    public void setCountryService(CountryService countryServices){
        this.countryServices = countryServices;
    }
    public void setTimezoneServoice(TimezonesService timezoneService) {
        this.timezoneService = timezoneService;
    }

    private void initCompanyCodeListener() {
        panel.getCompanyCodeTF().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getCompanyCodeTF().getText().trim();
                formState.setCompanyCode(text);
            }
        });
    }

    private void initCompanyNameListener() {
        panel.getCompanyNameTF().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getCompanyNameTF().getText().trim();
                formState.setCompanyName(text);
            }
        });
    }
    
    private void initLegalNameListener(){
        panel.getLegalNameTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getLegalNameTF().getText().trim();
                formState.setCompanyLegalName(text);
            }
        });
    }
    
    private void initBussinessTypeListener(){
        panel.getBusinessTypeTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getBusinessTypeTF().getText().trim();
                formState.setCompanyBussinesType(text);
            }
        });
    }
    
    private void initBussinessClassListener(){
        panel.getIndustryClassificationTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getIndustryClassificationTF().getText().trim();
                formState.setCompanyBussinesClassification(text);
            }
        });
    }
    
    public void findCountryByCode(){
        String countryCode = panel.getCountryCodeMatchCode().getTextField().getText().trim();
        countryServices.findCountryByCode(countryCode, countryListener);
    }
    
    private void initCountryListener() {
        countryListener = (Object[] data) -> {
            
            String countryCode = (data[1] == null||data[1].toString().isEmpty()) ? "" : data[1].toString();
            String countryName = (data[3] == null||data[3].toString().isEmpty()) ? "" : data[3].toString();

            panel.getCountryCodeMatchCode().getTextField().setText(countryCode);
            panel.getCountryNameLb().setText(countryName);
            
            formState.setCompanyCountryCode(countryCode);
        };
        panel.getCountryCodeMatchCode().getTextField().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String countryCode = panel.getCountryCodeMatchCode().getTextField().getText().trim();
                formState.setCompanyCountryCode(countryCode);
            }
        });
    }
    
    public TableSelectionListener countryListener(){return countryListener;};
    
    private void initLegalAddressListener(){
        panel.getLegalAddressTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getLegalAddressTF().getText().trim();
                formState.setCompanyLegalAddress(text);
            }
        });
    }

    private void initPhoneListener() {
        panel.getPhoneTF().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getPhoneTF().getText().trim();
                formState.setCompanyPhone(text);
            }
        });
    }

    private void initEmailListener() {
        panel.getEmailTF().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getEmailTF().getText().trim();
                formState.setCompanyEmail(text);
            }
        });
    }

    private void initOfficialLanguageListener(){
        panel.getOfficialLanguageTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getOfficialLanguageTF().getText().trim();
                formState.setCompanyOfficialLanguage(text);
            }
        });
    }
    
    private void initTimezoneListener() {
        timezoneListener = (Object[] data) -> {
            String text = (data[0] == null) ? "" : data[0].toString();
            formState.setCompanyTimeZone(text);
            panel.getTimezoneMatchCode().getTextField().setText(text);
        };
    }
    
    public TableSelectionListener timezoneListener(){return timezoneListener;};

    
    public void findChartByCode(){
        String code = panel.getChartOfAccountMatchCode().getTextField().getText().trim();
        chartService.searchChartByCode(code, chartsListener);
    }
    
    private void initChartOfAccountListener() {
        chartsListener = (Object[] data) -> {
            String code = (data[0] == null) ? "" : data[0].toString();
            String name = (data[1] == null) ? "" : data[1].toString();
            ChartOfAccountDTO chartDTO = (data.length > 2 && data[2] instanceof ChartOfAccountDTO)
                    ? (ChartOfAccountDTO) data[2]
                    : null;

            panel.getChartOfAccountMatchCode().getTextField().setText(code);
            panel.getChartOfAccountNameLb().setText(name);
            
            if (chartDTO == null) {
                chartService.searchChartByCode(code, chartsListener);
                return;
            }

            panel.getCurrencyCodeMatchCode().getTextField().setText(chartDTO.getCurrencyCode());
            panel.getCurrencyNameLb().setText(chartDTO.getCurrencyDescription());
            panel.getFiscalYearTF().setText(String.valueOf(chartDTO.getFiscalYear()));
            panel.getFiscalStartDateChooser().getDateEditor().setDate(chartDTO.getFiscalStartDate());
            panel.getFiscalEndDateChooser().getDateEditor().setDate(chartDTO.getFiscalEndDate());
            setStandardComboItem(panel.getAccountingStandardCombo(), chartDTO.getAccountingStandard());
            panel.getChartMultiCurrencyCheckbox().setSelected(chartDTO.isMultiCurrencyAllowed());
            panel.getTaxSchemaMatchCode().getTextField().setText(String.valueOf(chartDTO.getTaxSchemaID()));
            panel.getTaxSchemaNamelb().setText(chartDTO.getTaxSchemaDescription());

            formState.setCompanyChartOfAccount(code);
        };
        
        panel.getChartOfAccountMatchCode().getTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String code = panel.getChartOfAccountMatchCode().getTextField().getText().trim();
                panel.getChartOfAccountMatchCode().getTextField().setText(code);
            }
        });

    }
    
    private void setStandardComboItem(JComboBox combo, String chartStandard) {
        if (chartStandard == null || chartStandard.isEmpty()) {
            System.err.println("chart null or empty");
            return;
        }
        int itemCount = combo.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            Object item = combo.getItemAt(i);
            if (item instanceof ComboItem citem && citem.getDescription() != null) {
                if (citem.getDescription().equals(chartStandard)) {
                    combo.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
  
    private void initCompanyStatusCombo() {
        panel.getCompanyStatusCombo().addItemListener(e
                -> Optional.of(e.getItem())
                        .filter(ComboItem.class::isInstance)
                        .map(ComboItem.class::cast)
                        .map(comboItem -> Optional.ofNullable(comboItem.getDescription()).orElse(""))
                        .ifPresent(formState::setCompanyStatus)
        );
    }


    public TableSelectionListener chartsListener(){return chartsListener;}
    
    private void createdByListener(){
        panel.getCompanyCreatedByTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getCompanyCreatedByTF().getText().trim();
                formState.setCompanyCreatedBy(text);
            }
        });
    }

    private void updatedByListener() {
        panel.getCompanyUpdatedByTF().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getCompanyUpdatedByTF().getText().trim();
                formState.setCompanyUpdatedBy(text);
            }
        });
    }
    
    private void ficoCardListener(){
        companyCOCard = (List<COCompanyCard> companyCOCard1) -> {
            formState.setCompanyCOCards(companyCOCard1);
        };
    }

    public COCompanyCardListener getCOCompanyCardsListener(){return companyCOCard;};
    
    //Pendiente
    public void updateStateBeforeSave() {
        formState.setCompanyCode(panel.getCompanyCodeTF().getText().trim());
        formState.setCompanyName(panel.getCompanyNameTF().getText().trim());
        formState.setCompanyLegalName(panel.getLegalNameTF().getText().trim());
        formState.setCompanyBussinesType(panel.getBusinessTypeTF().getText().trim());
        formState.setCompanyBussinesClassification(panel.getIndustryClassificationTF().getText().trim());
        formState.setCompanyCountryCode(panel.getCountryCodeMatchCode().getTextField().getText().trim());
        formState.setCompanyLegalAddress(panel.getLegalAddressTF().getText().trim());
        formState.setCompanyPhone(panel.getPhoneTF().getText().trim());
        formState.setCompanyEmail(panel.getEmailTF().getText().trim());
        formState.setCompanyOfficialLanguage(panel.getOfficialLanguageTF().getText().trim());
        formState.setCompanyTimeZone(panel.getTimezoneMatchCode().getTextField().getText().trim());
        formState.setCompanyChartOfAccount(panel.getChartOfAccountMatchCode().getTextField().getText().trim());
        
        ComboItem item = (ComboItem) panel.getCompanyStatusCombo().getSelectedItem();
        formState.setCompanyStatus(item.getDescription());
    }
    
    public boolean checkDataForSave() {
        if (!areAllComponentsFullyFilled()) {
            return false;
        }
        if(formState.getCompanyCOCards()==null||formState.getCompanyCOCards().isEmpty()){
            return false;
        }else{
            if(!areCardsCompleted(formState.getCompanyCOCards())){
                return false;
            }
        }
        String countryCode = panel.getCountryCodeMatchCode().getTextField().getText().trim();
        if(!countryServices.findCountryByCode(countryCode, countryListener)){
            return false;
        }
        String timezone = panel.getTimezoneMatchCode().getTextField().getText().trim();
        if(!timezoneService.searchTimezoneByName(timezone, timezoneListener)){
            return false;
        }
        String chartOfAccount = panel.getChartOfAccountMatchCode().getTextField().getText().trim();
        if(!chartService.searchChartByCode(chartOfAccount, chartsListener)){
            return false;
        }
        updateStateBeforeSave();
        return true;
    }

    public boolean areAllComponentsFullyFilled() {
        FormValidator validator = new FormValidator()
                .add(panel.getCompanyCodeTF())
                .add(panel.getCompanyNameTF())
                .add(panel.getLegalNameTF())
                .add(panel.getBusinessTypeTF())
                .add(panel.getIndustryClassificationTF())
                .add(panel.getCountryCodeMatchCode().getTextField())
                .add(panel.getLegalAddressTF())
                .add(panel.getPhoneTF())
                .add(panel.getEmailTF())
                .add(panel.getOfficialLanguageTF())
                .add(panel.getTimezoneMatchCode().getTextField())
                .add(panel.getChartOfAccountMatchCode().getTextField())
                .add(panel.getCompanyStatusCombo());

        if (!validator.validate(panel.notificator())) {
            Workspace.getSaveButton().setPlayable(false);
            return false;
        }
        Workspace.getSaveButton().setPlayable(true);
        return true;
    }

    private boolean areCardsCompleted(List<COCompanyCard> cards) {
        return cards.stream().allMatch(card -> new COCompanyCardValidator(card).isCompleted());
    }
}
