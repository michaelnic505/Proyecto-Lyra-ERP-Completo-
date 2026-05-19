

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.List;


public class CoAssociationCard extends JPanel {

    private final JComboBox<String> coCompanyCombo;
    private final JComboBox<String> costingVariantCombo;
    private final MatchCode currencyMatchCode;
    private final JCheckBox internalOrdersCheck;
    private final JCheckBox costCenterMandatoryCheck;
    private final JButton removeButton;

    public CoAssociationCard(
            List<String> coCompanies,
            List<String> costingVariants,
            List<String> currencies,
            Consumer<CoAssociationCard> onRemove) {
        
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("CO Association"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Row 1
        add(new JLabel("CO Company:"), gbc);
        gbc.gridx = 1;
        coCompanyCombo = new JComboBox<>(coCompanies.toArray(String[]::new));
        coCompanyCombo.setPreferredSize(new Dimension(180, 25));
        add(coCompanyCombo, gbc);
        gbc.gridx = 2;
        add(new JLabel("Controlling Company #1"), gbc);


        // Row 2
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Costing Variant:"), gbc);
        gbc.gridx = 1;
        costingVariantCombo = new JComboBox<>(costingVariants.toArray(String[]::new));
        costingVariantCombo.setPreferredSize(new Dimension(180, 25));
        add(costingVariantCombo, gbc);
        gbc.gridx = 2;
        add(new JLabel("Controlling variant #1"), gbc);
        
        // Row 3
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Cost Currency:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        currencyMatchCode = new MatchCode();
        currencyMatchCode.setPreferredSize(new Dimension(180, 25));
        add(currencyMatchCode, gbc);
        gbc.gridx = 2;
        add(new JLabel("United State Dollar"), gbc);

        // Row 4
        gbc.gridx = 0; gbc.gridy++;
        internalOrdersCheck = new JCheckBox("Internal Orders");
        add(internalOrdersCheck, gbc);
        gbc.gridx = 1;
        costCenterMandatoryCheck = new JCheckBox("Cost Center Mandatory");
        add(costCenterMandatoryCheck, gbc);

        // Row 5 - Remove Button
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> onRemove.accept(this));
        add(removeButton, gbc);
        
                // Invisible filler to keep all content left-aligned
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = gbc.gridy + 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(Box.createHorizontalGlue(), gbc);
    }

    // Métodos para recuperar los datos si necesitás
    public String getCoCompany() { return (String) coCompanyCombo.getSelectedItem(); }
    public String getCostingVariant() { return (String) costingVariantCombo.getSelectedItem(); }
    public String getCurrency() { return (String) currencyMatchCode.getTextField().getText(); }
    public boolean isInternalOrdersEnabled() { return internalOrdersCheck.isSelected(); }
    public boolean isCostCenterMandatory() { return costCenterMandatoryCheck.isSelected(); }
}
