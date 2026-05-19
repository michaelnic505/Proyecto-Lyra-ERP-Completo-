
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.model.ComboItem;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services.AccountingStandardsService;
import java.util.stream.IntStream;
import javax.swing.JComboBox;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyAccountingStandardController {

    private JComboBox<ComboItem> combo;
    private AccountingStandardsService statusService;

    public FICompanyAccountingStandardController(JComboBox<ComboItem> combo, 
            AccountingStandardsService statusService) {
        this.combo = combo;
        this.statusService = statusService;
        initializeCombo();
    }

    private void initializeCombo() {
        String[] data = statusService.getListItems();

        combo.addItem(new ComboItem(null, null, null));
        IntStream.range(0, data.length)
                .forEach(i -> {
                    String item = data[i];
                    // Puedes usar 'i' para el índice
                    combo.addItem(new ComboItem(i, item, null));
                });
        combo.setSelectedItem(0);
    }
}
