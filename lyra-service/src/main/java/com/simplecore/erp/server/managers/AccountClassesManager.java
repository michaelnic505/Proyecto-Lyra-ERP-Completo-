package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.AccountClasses;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.requests.types.AccountClassesRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.AccountClassesRetrieveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class AccountClassesManager {

    public static AccountClassesRetrieveResponse getFinancialAccountingClasses(AccountClassesRetrieveRequest request) {
        List<String[]> accountClasses = new ArrayList<>();
        String query = Q.selectAll(DatabaseTables.FI_ACCOUNT_CLASSES.tableName());

        try (Connection conn = PooledConnectionService.getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(query)) {
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        accountClasses.add(new String[]{
                            rs.getString(AccountClasses.ID.getColumnName()),
                            rs.getString(AccountClasses.CLASS_CODE.getColumnName()),
                            rs.getString(AccountClasses.CLASS_NAME.getColumnName()),
                            rs.getString(AccountClasses.CLASS_KEY.getColumnName())});
                    }
                    if(!accountClasses.isEmpty()){
                        return new AccountClassesRetrieveResponse(request.getSessionId(),ResultType.FOUND,accountClasses);
                    }
                   return new AccountClassesRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND,accountClasses);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountClassesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new AccountClassesRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR,accountClasses);
    }
}
