

package com.simplecore.erp.server.managers;

import com.simplecore.erp.server.config.PooledConnectionService;
import com.simplecore.erp.server.config.database.DatabaseTables;
import com.simplecore.erp.server.config.database.tablecolumns.Timezones;
import com.simplecore.erp.server.config.database.utils.Q;
import com.simplecore.erp.shared.models.dto.TimezoneDTO;
import com.simplecore.erp.shared.requests.types.TimezoneByNameRetrieveRequest;
import com.simplecore.erp.shared.requests.types.TimezonesListRetrieveRequest;
import com.simplecore.erp.shared.responses.base.ResultType;
import com.simplecore.erp.shared.responses.types.TimezoneByNameRetrieveResponse;
import com.simplecore.erp.shared.responses.types.TimezonesListRetrieveResponse;
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
public class TimezonesManager {
    
    private static final Logger logger = LoggerFactory.getLogger(TimezonesManager.class);

    public static TimezonesListRetrieveResponse getTimezones(TimezonesListRetrieveRequest request) {

        List<Object[]> dataSource = new ArrayList<>();
        String query = Q.select(DatabaseTables.TIMEZONES.tableName(),
                Timezones.NAME.getColumnName());

        try (Connection conn = PooledConnectionService.getConnection(); 
                PreparedStatement st = conn.prepareStatement(query); 
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                dataSource.add(new Object[]{rs.getString(Timezones.NAME.getColumnName())});
            }
            if (!dataSource.isEmpty()) {
                return new TimezonesListRetrieveResponse(request.getSessionId(), ResultType.FOUND, dataSource);
            }
            return new TimezonesListRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, dataSource);

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred while retrieving timezones.", ex);
        }
        return new TimezonesListRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, dataSource);
    }
    
    
    public static TimezoneByNameRetrieveResponse getTimezoneByName(TimezoneByNameRetrieveRequest request){
        String query = Q.select(DatabaseTables.TIMEZONES.tableName(), 
                Timezones.ID.getColumnName(),
                Timezones.NAME.getColumnName()).concat(Q.where(Timezones.NAME.getColumnName()));
        
        try(Connection conn = PooledConnectionService.getConnection();
                PreparedStatement st = conn.prepareStatement(query)){
            st.setString(1, request.getTimezoneName());
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()){
                    TimezoneDTO timezoneDTO = new TimezoneDTO(
                            rs.getInt(Timezones.ID.getColumnName()), 
                            rs.getString(Timezones.NAME.getColumnName()));
                    return new TimezoneByNameRetrieveResponse(request.getSessionId(), ResultType.FOUND, timezoneDTO);
                }
                return new TimezoneByNameRetrieveResponse(request.getSessionId(), ResultType.NOT_FOUND, null);
            }
            
        }catch(SQLException ex){
            logger.error("An SQL exception occurred while retrieving a timezone by name.", ex);
        }
        return new TimezoneByNameRetrieveResponse(request.getSessionId(), ResultType.SQL_ERROR, null);
    }
}