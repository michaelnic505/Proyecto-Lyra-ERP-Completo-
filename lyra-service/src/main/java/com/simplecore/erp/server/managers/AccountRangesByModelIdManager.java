package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.AccountRanges;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.requests.types.AccountRangesModelRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountRangesModelRetrieveResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountRangesByModelIdManager {

    public static AccountRangesModelRetrieveResponse getRangesByModelId(AccountRangesModelRetrieveRequest request) {
        AccountRangesModelRetrieveResponse.Builder modelIdResponse = new AccountRangesModelRetrieveResponse.Builder(request.getSessionId());

        String query = Q.select(DatabaseTables.FI_ACCOUNT_RANGES.tableName(),
                AccountRanges.CLASS_ID.getColumnName(),
                AccountRanges.RANGE_START.getColumnName(),
                AccountRanges.RANGE_END.getColumnName())
                .concat(Q.where(AccountRanges.MODEL_ID.getColumnName(), AccountRanges.CLASS_ID.getColumnName()));

        try (Connection conn = PooledConnectionService.getConnection()) {
            fetchAccountRange(conn, query, request.getModelId(), request.getAssetsId(), modelIdResponse::assets);
            fetchAccountRange(conn, query, request.getModelId(), request.getLiabilitiesId(), modelIdResponse::liabilities);
            fetchAccountRange(conn, query, request.getModelId(), request.getEquityId(), modelIdResponse::equity);
            fetchAccountRange(conn, query, request.getModelId(), request.getRevenueId(), modelIdResponse::revenue);
            fetchAccountRange(conn, query, request.getModelId(), request.getCostsId(), modelIdResponse::costs);
            fetchAccountRange(conn, query, request.getModelId(), request.getExpensesId(), modelIdResponse::expenses);
        } catch (SQLException ex) {
            Logger.getLogger(AccountRangesByModelIdManager.class.getName()).log(Level.SEVERE, "Database error", ex);
        }

        return modelIdResponse.build();
    }

    private static void fetchAccountRange(Connection conn, String query, int modelId, int accountId,
            TriConsumer<Integer, Integer, Integer> setter) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setInt(1, modelId);
            st.setInt(2, accountId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    setter.accept(
                            rs.getInt(AccountRanges.CLASS_ID.getColumnName()),
                            rs.getInt(AccountRanges.RANGE_START.getColumnName()),
                            rs.getInt(AccountRanges.RANGE_END.getColumnName())
                    );
                }
            }
        }
    }

    @FunctionalInterface
    private interface TriConsumer<T, U, V> {

        void accept(T t, U u, V v);
    }
}
