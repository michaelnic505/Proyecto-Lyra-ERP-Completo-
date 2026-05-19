

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form;

import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import corex.suite.CorpTable;
import corex.utils.LCTableModel;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountTableUtil {
    
    private CorpTable table;
    private LCTableModel tableModel;
    private TranslationHelper tableTransalator = Workspace.translators(TranslatorType.TABLES);
    
    public AccountTableUtil(FinancialAccountSetup panel){
        this.table = panel.getAccountsTable();
    }

    public void injectTDataSourceInTable(Object[][] dataSource) {
        if (dataSource == null) {
            System.out.println("datasource is empty");
            return;
        }
        tableModel = new LCTableModel(dataSource, getColumns());
        table.setModel(tableModel);
        setPreferredSizes();
        initButtons();
    }
    
    private void initButtons(){
        table.RowsButton().addRowActionListener(table::selectOrDeselectOnCase);
    }
    private void setPreferredSizes(){
        table.TableData().getColumnModel().getColumn(0).setPreferredWidth(70);
        table.TableData().getColumnModel().getColumn(1).setPreferredWidth(100);
        table.TableData().getColumnModel().getColumn(2).setPreferredWidth(180);
        table.TableData().getColumnModel().getColumn(3).setPreferredWidth(80);
        table.TableData().getColumnModel().getColumn(4).setPreferredWidth(100);
        table.TableData().getColumnModel().getColumn(5).setPreferredWidth(200);
        table.TableData().getColumnModel().getColumn(6).setPreferredWidth(200);
        table.TableData().getColumnModel().getColumn(7).setPreferredWidth(200);
        table.TableData().getColumnModel().getColumn(8).setPreferredWidth(80);
        table.TableData().getColumnModel().getColumn(9).setPreferredWidth(100);
    }

    private String[] getColumns() {
        return new String[]{
            tableTransalator.getTranslation(AccountSetupColumnsTable.ACCOUNT_ID.getKey()),
            tableTransalator.getTranslation(AccountSetupColumnsTable.CHART_OF_ACCOUNT_CODE.getKey()),
            tableTransalator.getTranslation(AccountSetupColumnsTable.CHART_OF_ACCOUNT_NAME.getKey()),
            tableTransalator.getTranslation(AccountSetupColumnsTable.CLASS_NAME.getKey()),
            tableTransalator.getTranslation(AccountSetupColumnsTable.SUBCLASS_CODE.getKey()),
            tableTransalator.getTranslation(AccountSetupColumnsTable.SUBCLASS_NAME.getKey()),
            tableTransalator.getTranslation(AccountSetupColumnsTable.ACCOUNT_NAME.getKey()),
            tableTransalator.getTranslation(AccountSetupColumnsTable.ACCOUNT_DESCRIPTION.getKey()),
            tableTransalator.getTranslation(AccountSetupColumnsTable.ACCOUNT_STATUS.getKey()),
            tableTransalator.getTranslation(AccountSetupColumnsTable.ACCOUNT_CODE.getKey())
        };
    }

}
