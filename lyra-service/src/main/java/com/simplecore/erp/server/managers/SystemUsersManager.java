
package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.Users;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.requests.types.SystemUsersListRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.SystemUsersListRetrieveResponse;
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
public class SystemUsersManager {
    private static final Logger logger = LoggerFactory.getLogger(AccountingAccountManager.class);

    public static SystemUsersListRetrieveResponse getSystemUsersList(SystemUsersListRetrieveRequest request) {
        List<Object[]> dataSource = new ArrayList<>();
        String query = Q.select(DatabaseTables.USERS.tableName(),
                Users.USER_ID.columnName(),
                Users.USERNAME.columnName(),
                Users.FIRST_NAME.columnName(),
                Users.LAST_NAME.columnName(),
                Users.EMAIL.columnName(),
                Users.POSITION.columnName(),
                Users.DEPARTMENT.columnName(),
                Users.ROLE.columnName());

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    dataSource.add(new Object[]{
                        rs.getString(Users.USER_ID.columnName()),
                        rs.getString(Users.USERNAME.columnName()),
                        rs.getString(Users.FIRST_NAME.columnName()),
                        rs.getString(Users.LAST_NAME.columnName()),
                        rs.getString(Users.EMAIL.columnName()),
                        rs.getString(Users.POSITION.columnName()),
                        rs.getString(Users.DEPARTMENT.columnName()),
                        rs.getString(Users.ROLE.columnName())
                    });
                }
                if(!dataSource.isEmpty()){
                    return new SystemUsersListRetrieveResponse(request.getSessionId(),ResultType.FOUND ,dataSource);
                }else{
                    return new SystemUsersListRetrieveResponse(request.getSessionId(),ResultType.NOT_FOUND ,dataSource);
                }
            }

        } catch (SQLException ex) {
            logger.error("Error in getting systems users list", ex);
        }
        return new SystemUsersListRetrieveResponse(request.getSessionId(),ResultType.SQL_ERROR, dataSource);
    }
}
