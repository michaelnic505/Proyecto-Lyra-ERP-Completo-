package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.model.ComboItem;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.form.ChartOfAccountFormPanel;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.auxiliar.TaxSchemaListener;
import com.simplecore.erp.client.gui.utils.countries.CountryInfomartionController;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.TaxSchemasService;
import com.simplecore.erp.client.utils.validators.FormValidator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import com.simplecore.erp.client.abstractions.TableSelectionListener;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class ChartOfAccountDataHandler {

    private final ChartOfAccountFormPanel panel;
    private final ChartOfAccountFormState state;
    private CountryInfomartionController countryService;
    private CountryInfomartionController currencyService;
    private TaxSchemasService taxSchemaService;
    private TableSelectionListener countryListener;
    private TableSelectionListener currencyListener;

    private int accountModelId;
    private String transactionCode;
    private TaxSchemaListener taxSchemaListener;


    public ChartOfAccountDataHandler(Builder builder) {
        this.panel = builder.panel;
        this.state = builder.state;
        this.accountModelId = builder.accountModelID;
        this.transactionCode = builder.transactionCode;
        initListeners();
    }
    
    private void initListeners() {
        initChatOfAccountCodeListener();
        initChatOfAccountNameListener();
        initCountryListener();
        initCurrencyListener();
        initTaxSchemaListener();
        initBusinessTypeListener();
        initBusinessClassificationListener();
        initFiscalYearListener();
        initFiscalStartDateListener();
        initFiscalEndDateListener();
        initAccountStandardListener();
        initChartOfAccountStatusListener();
        initVersionTagListener();
        initMultiCurrencyCheckboxListener();
        initChartOfAccountNotesListener();
        initChartOfAccountDescriptionListener();
    }

    public void setCountryServices(CountryInfomartionController countryService){
        this.countryService = countryService;
    }
    
    public void setCurrencyServices(CountryInfomartionController currencyService){
        this.currencyService = currencyService;
    }
    
    public void setTaxSchemasService(TaxSchemasService taxSchemaService){
        this.taxSchemaService = taxSchemaService;
    }

    private void initChatOfAccountCodeListener() {
        panel.getCharOfAccountCodeTF().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String code = panel.getCharOfAccountCodeTF().getText().trim();
                state.setChartOfAccountCode(code);
            }
        });
    }

    private void initChatOfAccountNameListener(){
        panel.getChartOfAccountNameTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String name = panel.getChartOfAccountNameTF().getText().trim();
                state.setChartOfAccountName(name);
            }
        });
    }
    
    private void initBusinessTypeListener(){
        panel.getBusinessTypeTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String text  = panel.getBusinessTypeTF().getText().trim();
                state.setBusinessType(text);
            }
        });
    }
    
    private void initBusinessClassificationListener(){
        panel.getBusinessClassificationTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String text  = panel.getBusinessClassificationTF().getText().trim();
                state.setBusinessClass(text);
            }
        });
    }
    
    private void initFiscalYearListener(){
        panel.getFiscalYearTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String text = panel.getFiscalYearTF().getText().trim();
                state.setFiscalYear(Integer.parseInt(text));
            }
        });
    }
    
    private void initFiscalStartDateListener(){
        JTextField textFiedl = (JTextField) panel.getFiscalStartDateChooser().getDateEditor();
        textFiedl.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                java.util.Date selectedDate = panel.getFiscalStartDateChooser().getDate();
                state.setFiscalStartDate(
                        selectedDate != null ? new java.sql.Timestamp(selectedDate.getTime()) : null
                );
            }
        });
    }
    
    private void initFiscalEndDateListener(){
        JTextField textFiedl = (JTextField)panel.getFiscalEndDateChooser().getDateEditor();
        textFiedl.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                java.util.Date selectedDate = panel.getFiscalEndDateChooser().getDate();
                state.setFiscalEndDate(selectedDate!= null ? new java.sql.Timestamp(selectedDate.getTime()):null);
            }
        });
    }
    
    private void initAccountStandardListener() {
        panel.getAccountingStandardCombo().addItemListener((ItemEvent e) -> {
            ComboItem selectedItem = (ComboItem) panel.getAccountingStandardCombo().getSelectedItem();
            if (selectedItem != null) {
                state.setAccountingStandard(selectedItem.getDescription());            }
        });
    }

    private void initChartOfAccountStatusListener(){
        panel.getChartOfAccountStatusCombo().addItemListener((ItemEvent e) -> {
            state.setChartOfAccountStatus(e.getItem().toString());
        });
    }
    
    private void initVersionTagListener(){
        panel.getVersionTagTF().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                state.setChartVersionTag(panel.getVersionTagTF().getText().trim());
            }
        });
    }
    
    private void initMultiCurrencyCheckboxListener(){
        panel.getChartMultiCurrencyCheckbox().addActionListener(e->{
            state.setMultiCurrencyAllowed(panel.getChartMultiCurrencyCheckbox().isSelected());
        });
    }
    
    private void initChartOfAccountNotesListener(){
        panel.getChartOfAccountNotes().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                state.setChartOfAccountNotes(panel.getChartOfAccountNotes().getText().trim());
            }
        });
    }
    
    private void initChartOfAccountDescriptionListener(){
        panel.getChartOfAccountDescription().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                state.setChartOfAccountDescription(panel.getChartOfAccountDescription().getText().trim());
            }
        });
    }
    
    private void initCountryListener() {
        countryListener = (Object[] data) -> {
            String countryCode = (data[1] == null||data[1].toString().isEmpty()) ? "" : data[1].toString();
            String countryName = (data[3] == null||data[3].toString().isEmpty()) ? "" : data[3].toString();

            panel.getCountryCodeMatchCode().getTextField().setText(countryCode);
            panel.getCountryNameLb().setText(countryName);
            
            state.setCountryCode(countryCode);
        };
        panel.getCountryCodeMatchCode().getTextField().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String countryCode = panel.getCountryCodeMatchCode().getTextField().getText().trim();
                state.setCountryCode(countryCode);
            }
        });
    }

    private void initCurrencyListener() {
        currencyListener = (Object[] data) -> {
            String currencyCode = (data[0] == null||data[0].toString().isEmpty()) ? "" : data[0].toString();
            String currencyName = (data[1] == null||data[1].toString().isEmpty()) ? "" : data[1].toString();

            panel.getCurrencyCodeMatchCode().getTextField().setText(currencyCode);
            panel.getCurrencyNameLb().setText(currencyName);
            
            state.setCurrencyCode(currencyCode);
        };
        panel.getCurrencyCodeMatchCode().getTextField().addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                String currencyCode = panel.getCurrencyCodeMatchCode().getTextField().getText().trim();
                state.setCurrencyCode(currencyCode);
            }
        });
    }

    private void initTaxSchemaListener() {
        taxSchemaListener = (Object[] data) -> {
            // Verificamos que data[0] esté presente y sea un número antes de castearlo.
            int taxSchemaID = (data[0] == null || data[0].toString().isEmpty()) ? 0 : Integer.parseInt(data[0].toString());

            // Verificamos si data[1] es null o está vacío antes de asignar el valor.
            String taxSchemaName = (data[1] == null || data[1].toString().isEmpty()) ? "" : data[1].toString();

            // Actualizamos los componentes en el panel
            panel.getTaxSchemaMatchCode().getTextField().setText(String.valueOf(taxSchemaID));
            panel.getTaxSchemaNamelb().setText(taxSchemaName);
          
            state.setTaxSchemaID(taxSchemaID);
        };

        panel.getTaxSchemaMatchCode().getTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String input = panel.getTaxSchemaMatchCode().getTextField().getText().trim();
                try {
                    int taxID = Integer.parseInt(input);
                    state.setTaxSchemaID(taxID);
                } catch (NumberFormatException ex) {
                    // Si lo querés ignorar por completo, podés dejarlo vacío, pero mejor hacer algo:
                }
            }
        });
    }


    public TableSelectionListener countryListener(){return countryListener;};
    public TableSelectionListener currencyListener(){return currencyListener;};
    public TaxSchemaListener taxSchemaListener(){return taxSchemaListener;};
        

    public void updateStateBeforeSave() {
        state.setChartOfAccountCode(panel.getCharOfAccountCodeTF().getText().trim());
        state.setChartOfAccountName(panel.getChartOfAccountNameTF().getText().trim());
        state.setBusinessType(panel.getBusinessTypeTF().getText().trim());
        state.setBusinessClass(panel.getBusinessClassificationTF().getText().trim());
        state.setFiscalYear(Integer.parseInt(panel.getFiscalYearTF().getText().trim()));
        state.setChartVersionTag(panel.getVersionTagTF().getText().trim());
        state.setChartOfAccountNotes(panel.getChartOfAccountNotes().getText().trim());
        state.setChartOfAccountDescription(panel.getChartOfAccountDescription().getText().trim());

        // Datos seleccionados
        state.setAccountingStandard(((ComboItem) panel.getAccountingStandardCombo().getSelectedItem()).getDescription());
        state.setChartOfAccountStatus(((ComboItem) panel.getChartOfAccountStatusCombo().getSelectedItem()).getDescription());
        state.setMultiCurrencyAllowed(panel.getChartMultiCurrencyCheckbox().isSelected());

        // Fechas
        state.setFiscalStartDate(new java.sql.Timestamp(panel.getFiscalStartDateChooser().getDate().getTime()));
        state.setFiscalEndDate(new java.sql.Timestamp(panel.getFiscalEndDateChooser().getDate().getTime()));

        // Country y currency los obtenés por listener, pero los puedes refrescar también por si acaso:
        state.setCountryCode(panel.getCountryCodeMatchCode().getTextField().getText().trim());
        state.setCurrencyCode(panel.getCurrencyCodeMatchCode().getTextField().getText().trim());

        // Tax Schema ID: también lo podrías extraer directo del panel si querés asegurarte
        try {
            String idStr = panel.getTaxSchemaMatchCode().getTextField().getText().trim();
            int id = idStr.isEmpty() ? 0 : Integer.parseInt(idStr);
            state.setTaxSchemaID(id);
        } catch (NumberFormatException e) {
            state.setTaxSchemaID(0); // fallback en caso de error
        }
    }
   
    public boolean checkDataForSave() {
        if (!areAllComponentsFullyFilled()) {
            return false;
        }
        if (!currencyService.searchCurrencyByCode()) {
            return false;
        }

        if (!countryService.searchCountryByCode()) {
            return false;
        }

        if (!taxSchemaService.searchSchemaById()) {
            return false;
        }
        
        updateStateBeforeSave();
        
        return true;
    }

    // Método para validar que todos los campos están llenos
    public boolean areAllComponentsFullyFilled() {

        FormValidator validator = new FormValidator()
                .add(panel.getChartOfAccountNameTF())
                .add(panel.getCurrencyCodeMatchCode().getTextField())
                .add(panel.getCountryCodeMatchCode().getTextField())
                .add(panel.getFiscalYearTF())
                .add(panel.getFiscalStartDateChooser())
                .add(panel.getFiscalEndDateChooser())
                .add(panel.getAccountingStandardCombo())
                .add(panel.getChartOfAccountStatusCombo())
                .add(panel.getTaxSchemaMatchCode().getTextField());

        if (!validator.validate(panel.notificator())) {
            Workspace.getSaveButton().setPlayable(false);
            return false;
        }
        Workspace.getSaveButton().setPlayable(true);
        return true;
    }

    public static class Builder {

        private ChartOfAccountFormPanel panel;
        private ChartOfAccountFormState state;
        private int accountModelID;
        private String transactionCode;

        public Builder withPanel(ChartOfAccountFormPanel panel) {
            this.panel = panel;
            return this;
        }

        public Builder withFormState(ChartOfAccountFormState state) {
            this.state = state;
            return this;
        }

        public Builder withAccountModelId(int accountModelId) {
            this.accountModelID = accountModelId;
            return this;
        }
        
        public Builder withTransactionCode(String transactionCode) {
            this.transactionCode = transactionCode;
            return this;
        }

        public ChartOfAccountDataHandler build() {
            return new ChartOfAccountDataHandler(this);
        }
    }

}
