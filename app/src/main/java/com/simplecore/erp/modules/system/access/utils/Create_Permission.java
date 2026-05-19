package com.simplecore.erp.modules.system.access.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;

public class Create_Permission {
    
    
    private String user;
    private String transaction;
    private boolean OCRTD = false;
    private boolean OIPNN = false;
    private boolean OUAPP = false;
    private boolean OAPPV = false;
    private boolean OSCHD = false;
    private boolean OIEXN = false;
    private boolean OEXTD = false;
    private boolean OCLSD = false;
    private boolean ORJTD = false;
    private boolean OCCLD = false;
    

    public void create(String user, String transaction) {
        
        this.user = user;
        this.transaction = transaction;

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String insert = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.PERMISSIONS_MODIFICATION_ORDER.tableName()
                    + SentenceValues.setValues(13);
            
            
            st = conexion.prepareStatement(insert);
            st.setString(1, "0");
            st.setString(2, user);
            st.setString(3, transaction);
            st.setBoolean(4, OCRTD);
            st.setBoolean(5, OIPNN);
            st.setBoolean(6, OUAPP);
            st.setBoolean(7, OAPPV);
            st.setBoolean(8, OSCHD);
            st.setBoolean(9, OIEXN);
            st.setBoolean(10, OEXTD);
            st.setBoolean(11, OCLSD);
            st.setBoolean(12, ORJTD);
            st.setBoolean(13, OCCLD);
            
            st.executeUpdate();
            
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Create_Permission.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
