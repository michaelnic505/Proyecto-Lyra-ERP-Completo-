package com.simplecore.erp.modules.system.access.legacy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel2;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.DatabaseTables.PermissionsModificationOrder;

public class Cargar_Permisos_Ordenes2 {

    public String getUsuario() {
        return usuario;
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

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    private String usuario;
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

    public void cargarPermisosOrdenes(JTable table) {

        LyraTableModel2 model = (LyraTableModel2) table.getModel();
        
        
        try {

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;

            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.PERMISSIONS_MODIFICATION_ORDER.tableName()
                    + SQLKeywords.ORDER_BY.toSQL()
                    + PermissionsModificationOrder.USER.toString()
                    + SQLKeywords.ASC.toSQL();



            st = conexion.prepareStatement(query);
            st.executeQuery();

            ResultSet rs = st.getResultSet();
            ResultSetMetaData MetaData = rs.getMetaData();

            int canCol = MetaData.getColumnCount();

            while (rs.next ()) {

                Object[] filas = new Object[canCol];
                
                for(int i = 0; i < 2 ;i++){
                    
                    filas[i] = rs.getObject(i+1);
                   
                }

                model.addRow(filas);
            }

            table.setModel(model);            
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Cargar_Permisos_Ordenes2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
