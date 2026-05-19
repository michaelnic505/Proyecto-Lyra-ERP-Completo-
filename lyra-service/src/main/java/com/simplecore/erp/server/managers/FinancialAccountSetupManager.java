

package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.AccountClasses;
import com.simplecore.erp.server.config.database.tablecolumns.AccountSubclasses;
import com.simplecore.erp.server.config.database.tablecolumns.AccountingAccounts;
import com.simplecore.erp.server.config.database.tablecolumns.ChartOfAccounts;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.AccountCardData;
import com.simplecore.erp.shared.requests.types.AccountsByChartOfAccountCodeRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.AccountsByChartOfAccountCodeRetrieveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FinancialAccountSetupManager {
    
    private static final Logger logger = LoggerFactory.getLogger(FinancialAccountSetupManager.class);

    public static AccountsByChartOfAccountCodeRetrieveResponse getAccountsByChartOfAccountCode(AccountsByChartOfAccountCodeRetrieveRequest request) {
       
        List<AccountCardData> accountDataList = new ArrayList<>();
        
        String chartTable = DatabaseTables.FI_CHART_OF_ACCOUNTS.tableName();
        String subclassTable = DatabaseTables.FI_ACCOUNT_SUBCLASSES.tableName();
        String classTable = DatabaseTables.FI_ACCOUNT_CLASSES.tableName();
        String accountTable = DatabaseTables.FI_ACCOUNTING_ACCOUNTS.tableName();

        String query = Q.selectF(
                Q.fieldAliasWithAS("chart", ChartOfAccounts.CODE.getColumnName(), "CHART_CODE"),
                Q.fieldAliasWithAS("chart", ChartOfAccounts.NAME.getColumnName(), "CHART_NAME"),
                Q.fieldAliasWithAS("class", AccountClasses.CLASS_NAME.getColumnName(), "CLASS_NAME"),
                Q.fieldAliasWithAS("subclass", AccountSubclasses.SUBCLASS_CODE.getColumnName(), "SUBCLASS_CODE"),
                Q.fieldAliasWithAS("subclass", AccountSubclasses.SUBCLASS_NAME.getColumnName(), "SUBCLASS_NAME"),
                Q.fieldAliasWithAS("account", AccountingAccounts.ACCOUNT_ID.getColumnName(), "ACCOUNT_ID"),
                Q.fieldAliasWithAS("account", AccountingAccounts.ACCOUNT_NAME.getColumnName(), "ACCOUNT_NAME"),
                Q.fieldAliasWithAS("account", AccountingAccounts.ACCOUNT_DESCRIPTION.getColumnName(), "ACCOUNT_DESCRIPTION"),
                Q.fieldAliasWithAS("account", AccountingAccounts.ACCOUNT_STATUS.getColumnName(), "ACCOUNT_STATUS"),
                Q.fieldAliasWithAS("account", AccountingAccounts.ACCOUNT_CODE.getColumnName(), "ACCOUNT_CODE")
        )
                .concat(Q.fromAlias("chart", chartTable))
                .concat(Q.join(Q.fromAlias("subclass", subclassTable)))
                .concat(Q.onEqual(
                        "chart", ChartOfAccounts.ACCOUNT_MODEL_ID.getColumnName(),
                        "subclass", AccountSubclasses.MODEL_ID.getColumnName()))
                .concat(Q.join(Q.fromAlias("account", accountTable)))
                .concat(Q.onEqual(
                        "subclass", AccountSubclasses.SUBCLASS_ID.getColumnName(),
                        "account", AccountingAccounts.SUBCLASS_ID.getColumnName()))
                // JOIN account_classes ON subclass.class_id = class.class_id
                .concat(Q.join(Q.fromAlias("class", classTable)))
                .concat(Q.onEqual(
                        "subclass", AccountSubclasses.CLASS_ID.getColumnName(),
                        "class", AccountClasses.ID.getColumnName()))
                .concat(Q.where("chart."+ChartOfAccounts.CODE.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, request.getChartOfAccountCode());
            
            try(ResultSet rs = st.executeQuery()){
                while(rs.next()){
                    accountDataList.add(new AccountCardData(
                            rs.getLong("ACCOUNT_ID"),
                            rs.getString("CHART_CODE"),
                            rs.getString("CHART_NAME"),
                            rs.getString("CLASS_NAME"),
                            rs.getString("SUBCLASS_CODE"),
                            rs.getString("SUBCLASS_NAME"),
                            rs.getString("ACCOUNT_NAME"),
                            rs.getString("ACCOUNT_DESCRIPTION"),
                            rs.getString("ACCOUNT_STATUS"),
                            rs.getString("ACCOUNT_CODE")
                    ));
                }
                if(!accountDataList.isEmpty()){
                    return new AccountsByChartOfAccountCodeRetrieveResponse(request.getSessionId(), ResultType.FOUND, accountDataList);
                }
                return new AccountsByChartOfAccountCodeRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, accountDataList);
            }
            
        } catch (SQLException ex) {
            logger.error("SQL Exception encountered while retrieving accounts by chart code '{}'. Operation aborted. Keep calm and debug on!", 
                 request.getChartOfAccountCode(), ex);
            return new AccountsByChartOfAccountCodeRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, accountDataList);
        }
    }

}
