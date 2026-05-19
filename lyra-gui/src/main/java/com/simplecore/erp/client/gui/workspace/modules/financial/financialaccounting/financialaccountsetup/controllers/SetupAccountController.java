

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.controllers;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form.FinancialAccountSetup;
import corex.suite.CorpTable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class SetupAccountController {
    
    private FinancialAccountSetup panel;

    public SetupAccountController(FinancialAccountSetup panel) {
        this.panel = panel;
        setupAccount();
    }
    
    private void setupAccount(){
        JButton button = panel.getSetupButton();
        button.addActionListener(e->{
            
        });
    }
   
    private void getSelectedRows() {
        CorpTable table = panel.getAccountsTable();
        int[] rows = table.TableData().getSelectedRows();

        List<Integer> rowList = Arrays.stream(rows) // convierte a IntStream
                .boxed() // convierte de int a Integer (auto-boxing)
                .collect(Collectors.toList()); // convierte a lista

        // Ahora puedes iterar con un Iterator si quieres
        Iterator<Integer> iterator = rowList.iterator();
    }
}
