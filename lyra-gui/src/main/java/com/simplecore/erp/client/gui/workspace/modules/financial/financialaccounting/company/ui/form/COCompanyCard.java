
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form;

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.abstractions.TableSelectionListener;
import com.simplecore.erp.client.gui.utils.currencies.CurrencyService;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.modules.financial.controlling.company.auxiliar.COCompanyService;
import com.simplecore.erp.client.gui.workspace.modules.financial.controlling.costvariant.auxiliar.CostVariantRetrieveService;
import com.simplecore.erp.client.gui.workspace.modules.financial.controlling.services.costcenterplan.CostCenterPlanService;
import com.simplecore.erp.client.gui.workspace.modules.financial.fico.util.ComboItem;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.dto.COCompanyDTO;
import com.simplecore.erp.shared.models.dto.COCostVariantDTO;
import com.simplecore.erp.shared.models.dto.FICOCompanyRelation;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.sql.Date;
import java.util.Optional;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author user
 */
public class COCompanyCard extends javax.swing.JPanel {
    
    private TableSelectionListener companyListener;
    private TableSelectionListener variantListener;
    private TableSelectionListener currencyListener;
    private TableSelectionListener costCenterPlanListener;
    private SystemMessages notificator = new SystemMessages();
    
    private Long relationID;
    
    private int coCompanyID;
    private long costVariantID;
    private String companyCode;
    private String costingVariant;
    private String costCurrency;
    private boolean internalOrder;
    private boolean costCenterMandatory;
    private String relationType;
    private String costCenterPlan;
    private Date validFrom;
    private Date validTo;
    private String status;
    private String createdBy;
    
    private COCompanyService coCompanyService;
    private CostVariantRetrieveService costVariantService;
    private CurrencyService currencyService;
    private CostCenterPlanService costCenterPlanService;
    

    public COCompanyCard() {
        initComponents();
        this.setBorder(new RoundedBorderWithTitle("CO Associacion",13));
        initCOCompanyListener();
        initVariantListener();
        initCurrencyListener();
        initCostCenterPlanListener();
    }
    
    public void setCOCompanyService(COCompanyService service){this.coCompanyService = service;}
    public void setCostVariantService(CostVariantRetrieveService costVariantService) {this.costVariantService = costVariantService;}
    public void setCurrencyService(CurrencyService currencyService) {this.currencyService = currencyService;}
    public void setCostCenterPlanService(CostCenterPlanService costCenterPlanService) {this.costCenterPlanService = costCenterPlanService;}

    public void setCreatedBy(String createdBy){this.createdBy = createdBy;}

    public void setRemoveAction(Consumer<COCompanyCard> onRemove) {
        removeButton.addActionListener(e -> {
            if (hasDataInFields()) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        AppMessages.msg(AppMessages.Key.UNSAVED_DATA),
                        AppMessages.msg(AppMessages.Key.DATA_NOT_SAVED),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            onRemove.accept(this);
        });
    }

    private void initCOCompanyListener() {
        companyListener = (Object[] data) -> {
            String code = (data[0] == null) ? "" : data[0].toString();
            String description = (data[1] == null) ? "" : data[1].toString();

            if (data.length > 2 && data[2] instanceof COCompanyDTO dto) {
                coCompanyID = dto.getCompanyId();
                System.out.print(coCompanyID);
            }else{
                coCompanyService.findCompanyByCode(companyListener,code);
            }
            companyMatchCode.getTextField().setText(code);
            companyNameLb.setText(description);
        };
    }

    private void initVariantListener() {
        variantListener = (Object[] data) -> {
            String code = (data[0] == null) ? "" : data[0].toString();
            String description = (data[1] == null) ? "" : data[1].toString();
           
            if (data.length > 2) {
                COCostVariantDTO costVariantDTO = (data[2] == null) ? null : (COCostVariantDTO) data[2];
                costVariantID = (costVariantDTO == null) ? -1 : costVariantDTO.getVariantId();
            }

            variantMatchCode.getTextField().setText(code);
            variantNameLb.setText(description);
        };
    }

    private void initCurrencyListener() {
        currencyListener = (Object[] data) -> {
            String code = (data[0] == null) ? "" : data[0].toString();
            String description = (data[1] == null) ? "" : data[1].toString();

            currencyMatchCode.getTextField().setText(code);
            costCurrencyNameLb.setText(description);
        };
    }
    
    private void initCostCenterPlanListener() {
        costCenterPlanListener = (Object[] data) -> {
            String ID = (data[0] == null) ? "" : data[0].toString();
            String description = (data[1] == null) ? "" : data[1].toString();
            
            costCenterPlanMatchCode.getTextField().setText(ID);
        };
    }

    private void setData() {
        companyCode = currencyMatchCode.getTextField().getText();
        costingVariant = variantMatchCode.getTextField().getText();
        costCurrency = currencyMatchCode.getTextField().getText();
        internalOrder = internalOrdersCheckBox.isSelected();
        costCenterMandatory = costCenterMandatoryCheckBox.isSelected();
        relationType = getComboItemString(relationTypeCombo);
        costCenterPlan = costCenterPlanMatchCode.getTextField().getText();
        validFrom = new Date(validFromDChooser.getDateEditor().getDate().getTime());
        validTo = new Date(validToDChooser.getDateEditor().getDate().getTime());
        status = getComboItemString(statusCombo);
    }

    public FICOCompanyRelation getFICOAssociationData(){
        setData();
        return new FICOCompanyRelation.Builder()
                .withFICORelationID(relationID)
                .withCOCompanyId(coCompanyID)
                .withCostingVariant(costingVariant)
                .withCostCurrency(costCurrency)
                .withAllowInternalOrders(internalOrder)
                .withRequireCostCenter(costCenterMandatory)
                .withCostCenterPlan(costCenterPlan)
                .withRelationType(relationType)
                .withValidFrom(validFrom)
                .withValidTo(validTo)
                .withStatus(status)
                .withCreatedBy(createdBy)
                .build();
    }

    private String getComboItemString(JComboBox<ComboItem> combo) {
        Object item = combo.getSelectedItem();
        if (item instanceof ComboItem comboItem) {
            return Optional.ofNullable(comboItem.getDescription()).orElse("");
        }
        return "";
    }

    private boolean hasDataInFields() {
        return isNonEmpty(companyMatchCode)
            || isNonEmpty(variantMatchCode)
            || isNonEmpty(currencyMatchCode)
            || isNonEmptySelection(statusCombo)
            || isNonEmptySelection(relationTypeCombo)
            || internalOrdersCheckBox.isSelected()
            || costCenterMandatoryCheckBox.isSelected()
            || isNonEmpty(costCenterPlanMatchCode)
            || validFromDChooser.getDateEditor().getDate() != null
            || validToDChooser.getDateEditor().getDate() != null;
    }

    private boolean isNonEmpty(MatchCode matchCode) {
        return !matchCode.getTextField().getText().isEmpty();
    }

    private boolean isNonEmptySelection(JComboBox<?> comboBox) {
        Object selected = comboBox.getSelectedItem();
        return selected != null && !selected.toString().isEmpty();
    }


    public void injectCardData(FICOCompanyRelation dto) {
        relationID = dto.getFICORelationID();
        coCompanyService.findCompanyByCode(companyListener, dto.getCoCompanyCode());
        costVariantService.findCostVariantByCode(variantListener, dto.getCostingVariant());
        currencyService.searchCurrencyByCode(currencyListener, dto.getCostCurrency());
        costCenterPlanService.findCostCenterPlanByID(costCenterPlanListener, Long.parseLong(dto.getCostCenterPlan()));
        
        internalOrdersCheckBox.setSelected(dto.isAllowInternalOrders());
        costCenterMandatoryCheckBox.setSelected(dto.isRequireCostCenter());

        selectItemInCombo(relationTypeCombo, dto.getRelationType());
        selectItemInCombo(statusCombo, dto.getStatus());

        setDate(validFromDChooser, dto.getValidFrom());
        setDate(validToDChooser, dto.getValidTo());
    }
    private void setDate(JDateChooser chooser, Date date) {
        if (date != null) {
            chooser.getDateEditor().setDate(date);
        }
    }

    private void selectItemInCombo(JComboBox<ComboItem> combo, String value) {
        if (value == null) {
            return;
        }
        for (int i = 0; i < combo.getItemCount(); i++) {
            ComboItem item = combo.getItemAt(i);
            String description = item.getDescription();
            if (description != null && description.equals(value)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }
    

    public MatchCode getCOCompanyMatchCode(){return companyMatchCode;}
    public JLabel getCOCompanyNameLb(){return companyNameLb;};
    public MatchCode getCostVariantMatchCode(){return variantMatchCode;};
    public JLabel getVariantNameLb(){return variantNameLb;};
    public MatchCode getCurrencyMatchCode(){return currencyMatchCode;};
    public JLabel getCurrencyNameLb(){return costCurrencyNameLb;};
    public JCheckBox getInternalOrderCBx(){return internalOrdersCheckBox;};
    public JCheckBox getCostCenterMandatoryCBx(){return costCenterMandatoryCheckBox;};
    public JComboBox getStatusCombo(){return statusCombo;};
    public MatchCode getCostCenterPlanMatchCode(){return costCenterPlanMatchCode;};
    public JComboBox getRelationTypeCombo(){return relationTypeCombo;};
    public JDateChooser getValidFromChooser(){return validFromDChooser;};
    public JDateChooser getValidToChooser(){return validToDChooser;};
    public JButton getRemoveButton(){return removeButton;};

    public TableSelectionListener costVariantListener(){return variantListener;}
    public TableSelectionListener currencyListener(){return currencyListener;}
    public TableSelectionListener companyListener(){return companyListener;};
    public TableSelectionListener costCenterPlanListener(){return costCenterPlanListener;};

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        coCompanyLb = new corex.suite.JLabelHQUnderlined();
        costingVariantLb = new corex.suite.JLabelHQUnderlined();
        costCurrencyLb = new corex.suite.JLabelHQUnderlined();
        currencyMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        costCurrencyNameLb = new javax.swing.JLabel();
        internalOrdersCheckBox = new javax.swing.JCheckBox();
        costCenterMandatoryCheckBox = new javax.swing.JCheckBox();
        removeButton = new javax.swing.JButton();
        companyMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        variantMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        companyNameLb = new javax.swing.JLabel();
        variantNameLb = new javax.swing.JLabel();
        costCurrencyLb1 = new corex.suite.JLabelHQUnderlined();
        costCenterPlanMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        relationTypeCombo = new javax.swing.JComboBox<>();
        costCurrencyLb2 = new corex.suite.JLabelHQUnderlined();
        validFromDChooser = new com.toedter.calendar.JDateChooser();
        costCurrencyLb3 = new corex.suite.JLabelHQUnderlined();
        costCurrencyLb4 = new corex.suite.JLabelHQUnderlined();
        validToDChooser = new com.toedter.calendar.JDateChooser();
        costCurrencyLb5 = new corex.suite.JLabelHQUnderlined();
        statusCombo = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(239, 243, 247));
        setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 13)); // NOI18N

        coCompanyLb.setText("CO Company");
        coCompanyLb.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        costingVariantLb.setText("Costing Variant");
        costingVariantLb.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        costCurrencyLb.setText("Cost Currency");
        costCurrencyLb.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        currencyMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        costCurrencyNameLb.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N
        costCurrencyNameLb.setToolTipText("");

        internalOrdersCheckBox.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N
        internalOrdersCheckBox.setText("Internal Orders");

        costCenterMandatoryCheckBox.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N
        costCenterMandatoryCheckBox.setText("Cost Center Mandatory");

        removeButton.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N
        removeButton.setIcon(new CustomSVGIcon("/icons/svg/remove_card.svg",new Dimension(18,18)));
        removeButton.setText("Remove");

        companyMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        variantMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        companyNameLb.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N
        companyNameLb.setToolTipText("");

        variantNameLb.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N
        variantNameLb.setToolTipText("");

        costCurrencyLb1.setText("Cost Center Plan");
        costCurrencyLb1.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        costCenterPlanMatchCode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        costCurrencyLb2.setText("Relation type");
        costCurrencyLb2.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        validFromDChooser.setDateFormatString("MM.dd.yyyy");
        validFromDChooser.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N

        costCurrencyLb3.setText("Valid From");
        costCurrencyLb3.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        costCurrencyLb4.setText("Valid To");
        costCurrencyLb4.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        validToDChooser.setDateFormatString("MM.dd.yyyy");
        validToDChooser.setFont(new java.awt.Font("IBM Plex Sans", 0, 13)); // NOI18N

        costCurrencyLb5.setText("Status");
        costCurrencyLb5.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(coCompanyLb, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(costingVariantLb, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(costCurrencyLb, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(currencyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(companyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(costCurrencyNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(companyNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(variantMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(variantNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(internalOrdersCheckBox))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(costCurrencyLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(costCenterMandatoryCheckBox))
                                    .addComponent(relationTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(costCurrencyLb1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(costCurrencyLb5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(costCurrencyLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(validFromDChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(costCurrencyLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(validToDChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costCenterPlanMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(coCompanyLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(costingVariantLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(variantMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(variantNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(costCurrencyLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCurrencyNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(internalOrdersCheckBox)
                    .addComponent(costCenterMandatoryCheckBox)
                    .addComponent(statusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCurrencyLb5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(relationTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(costCurrencyLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(costCurrencyLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(costCenterPlanMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(validFromDChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(costCurrencyLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(validToDChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costCurrencyLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private corex.suite.JLabelHQUnderlined coCompanyLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode companyMatchCode;
    private javax.swing.JLabel companyNameLb;
    private javax.swing.JCheckBox costCenterMandatoryCheckBox;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode costCenterPlanMatchCode;
    private corex.suite.JLabelHQUnderlined costCurrencyLb;
    private corex.suite.JLabelHQUnderlined costCurrencyLb1;
    private corex.suite.JLabelHQUnderlined costCurrencyLb2;
    private corex.suite.JLabelHQUnderlined costCurrencyLb3;
    private corex.suite.JLabelHQUnderlined costCurrencyLb4;
    private corex.suite.JLabelHQUnderlined costCurrencyLb5;
    private javax.swing.JLabel costCurrencyNameLb;
    private corex.suite.JLabelHQUnderlined costingVariantLb;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode currencyMatchCode;
    private javax.swing.JCheckBox internalOrdersCheckBox;
    private javax.swing.JComboBox<ComboItem> relationTypeCombo;
    private javax.swing.JButton removeButton;
    private javax.swing.JComboBox<ComboItem> statusCombo;
    private com.toedter.calendar.JDateChooser validFromDChooser;
    private com.toedter.calendar.JDateChooser validToDChooser;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode variantMatchCode;
    private javax.swing.JLabel variantNameLb;
    // End of variables declaration//GEN-END:variables


   private class RoundedBorderWithTitle extends AbstractBorder {

        private final int arc;
        private final String title;
        private final Font font;

        public RoundedBorderWithTitle(String title, int arc) {
            this.arc = arc;
            this.title = title;
            this.font = new Font("SansSerif", Font.BOLD, 12);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();

            // Activar antialiasing para suavizar bordes
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Dibujar borde redondeado
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x + 1, y + 10, width - 3, height - 12, arc, arc);

            // Dibujar el título
            g2.setFont(font);
            FontMetrics fm = g2.getFontMetrics();
            int titleWidth = fm.stringWidth(title);
            int titleHeight = fm.getAscent();

            g2.setColor(c.getBackground());
            g2.fillRect(x + 20, y, titleWidth + 10, titleHeight + 4);

            g2.setColor(Color.BLACK);
            g2.drawString(title, x + 25, y + titleHeight);

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(20, 10, 10, 10);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(20, 10, 10, 10);
            return insets;
        }
    }

}
