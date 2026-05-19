package com.simplecore.erp.modules.system.access.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQL_Modify_Statement;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.PermissionsModificationOrder;

public class Modify_Permission {

    private String user;
    private String transaction;
    private boolean OCRTD;
    private boolean OIPNN;
    private boolean OUAPP;
    private boolean OAPPV;
    private boolean OSCHD;
    private boolean OIEXN;
    private boolean OEXTD;
    private boolean OCLSD;
    private boolean ORJTD;
    private boolean OCCLD;

    public void modify(String user, String transaction) {

        try {
            this.setUser(user);
            this.setTransaction(transaction);
            
            List<String> fieldList = new ArrayList();
            fieldList.add(PermissionsModificationOrder.OCRTD.toString());
            fieldList.add(PermissionsModificationOrder.OIPNN.toString());
            fieldList.add(PermissionsModificationOrder.OUAPP.toString());
            fieldList.add(PermissionsModificationOrder.OAPPV.toString());
            fieldList.add(PermissionsModificationOrder.OSCHD.toString());
            fieldList.add(PermissionsModificationOrder.OIEXN.toString());
            fieldList.add(PermissionsModificationOrder.OEXTD.toString());
            fieldList.add(PermissionsModificationOrder.OCLSD.toString());
            fieldList.add(PermissionsModificationOrder.ORJTD.toString());
            fieldList.add(PermissionsModificationOrder.OCCLD.toString());
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String modify = SQLKeywords.UPDATE.toSQL()
                    + DatabaseTables.PERMISSIONS_MODIFICATION_ORDER.tableName()
                    + SQLKeywords.SET.toSQL()
                    + SQL_Modify_Statement.setModifyFields(fieldList)
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
            
            st = conexion.prepareStatement(modify);
            st.setBoolean(1, isOCRTD());
            st.setBoolean(2, isOIPNN());
            st.setBoolean(3, isOUAPP());
            st.setBoolean(4, isOAPPV());
            st.setBoolean(5, isOSCHD());
            st.setBoolean(6, isOIEXN());
            st.setBoolean(7, isOEXTD());
            st.setBoolean(8, isOCLSD());
            st.setBoolean(9, isORJTD());
            st.setBoolean(10, isOCCLD());
            
            st.executeUpdate();
            
            st.close();
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Modify_Permission.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                      

    }

    public String getUser() {
        return user;
    }

    public String getTransaction() {
        return transaction;
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

    public void setUser(String user) {
        this.user = user;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
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
