package com.simplecore.erp.modules.system.access.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.PermissionsModificationOrder;

public class Extraer_Permisos {
    
    
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
    

    public void permisos(String user, String transaction) {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String consulta = SQLKeywords.SELECT.toSQL()
                    + PermissionsModificationOrder.OCRTD.toString()
                    + PermissionsModificationOrder.OIPNN.toString()
                    + PermissionsModificationOrder.OUAPP.toString()
                    + PermissionsModificationOrder.OAPPV.toString()
                    + PermissionsModificationOrder.OSCHD.toString()
                    + PermissionsModificationOrder.OIEXN.toString()
                    + PermissionsModificationOrder.OEXTD.toString()
                    + PermissionsModificationOrder.OCLSD.toString()
                    + PermissionsModificationOrder.ORJTD.toString()
                    + PermissionsModificationOrder.OCCLD.toString()
                    + SQLKeywords.FROM.toSQL()
                    + DatabaseTables.PERMISSIONS_MODIFICATION_ORDER.tableName()
                    + SQLKeywords.WHERE.toSQL()
                    + PermissionsModificationOrder.USER.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + user
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + SQLKeywords.AND.toSQL()
                    + PermissionsModificationOrder.TRANSACTION.toString()
                    + SQLKeywords.EQUALS.toSQL()
                    + SQLKeywords.SINGLE_QUOTE.toSQL()
                    + transaction
                    + SQLKeywords.SINGLE_QUOTE.toSQL();
            
            
            
            st = conexion.prepareStatement(consulta);
            st.executeQuery();
            
            ResultSet rs = st.getResultSet();
            
            if(rs.next()){
                
                setOCRTD(rs.getBoolean(PermissionsModificationOrder.OCRTD.toString()));
                setOIPNN(rs.getBoolean(PermissionsModificationOrder.OIPNN.toString()));
                setOUAPP(rs.getBoolean(PermissionsModificationOrder.OUAPP.toString()));
                setOAPPV(rs.getBoolean(PermissionsModificationOrder.OAPPV.toString()));
                setOSCHD(rs.getBoolean(PermissionsModificationOrder.OSCHD.toString()));
                setOIEXN(rs.getBoolean(PermissionsModificationOrder.OIEXN.toString()));
                setOEXTD(rs.getBoolean(PermissionsModificationOrder.OEXTD.toString()));
                setOCLSD(rs.getBoolean(PermissionsModificationOrder.OCLSD.toString()));
                setORJTD(rs.getBoolean(PermissionsModificationOrder.ORJTD.toString()));
                setOCCLD(rs.getBoolean(PermissionsModificationOrder.OCCLD.toString()));
                
            }else{
                
                Create_Permission cp = new Create_Permission();
                cp.create(user, transaction);
                
                
                
                //Llamada concurrente para extraer valores
                permisos(user,transaction);
                
            }
            
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Extraer_Permisos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isOCRTD() {
        return OCRTD;
    }

    public boolean isOIPNN() {
        return OIPNN;
    }

    public boolean isOUAPP() {
        return OUAPP;
    }

    public boolean isOAPPV() {
        return OAPPV;
    }

    public boolean isOSCHD() {
        return OSCHD;
    }

    public boolean isOIEXN() {
        return OIEXN;
    }

    public boolean isOEXTD() {
        return OEXTD;
    }

    public boolean isOCLSD() {
        return OCLSD;
    }

    public boolean isORJTD() {
        return ORJTD;
    }

    public boolean isOCCLD() {
        return OCCLD;
    }

    public void setOCRTD(boolean OCRTD) {
        this.OCRTD = OCRTD;
    }

    public void setOIPNN(boolean OIPNN) {
        this.OIPNN = OIPNN;
    }

    public void setOUAPP(boolean OUAPP) {
        this.OUAPP = OUAPP;
    }

    public void setOAPPV(boolean OAPPV) {
        this.OAPPV = OAPPV;
    }

    public void setOSCHD(boolean OSCHD) {
        this.OSCHD = OSCHD;
    }

    public void setOIEXN(boolean OIEXN) {
        this.OIEXN = OIEXN;
    }

    public void setOEXTD(boolean OEXTD) {
        this.OEXTD = OEXTD;
    }

    public void setOCLSD(boolean OCLSD) {
        this.OCLSD = OCLSD;
    }

    public void setORJTD(boolean ORJTD) {
        this.ORJTD = ORJTD;
    }

    public void setOCCLD(boolean OCCLD) {
        this.OCCLD = OCCLD;
    }

}
