package com.simplecore.erp.modules.controlling.costmanagement.costcenters.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;

public class F02_Create_Cost_Center {

    public int getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(int codigoError) {
        this.codigoError = codigoError;
    }

    public String getIdCostCenter() {
        return idCostCenter;
    }

    public String getDescriptionCC() {
        return descriptionCC;
    }

    public String getIdEmplazament() {
        return idEmplazament;
    }

    public String getDescriptionEmp() {
        return descriptionEmp;
    }

    public String getIdArea() {
        return idArea;
    }

    public String getDescriptionArea() {
        return descriptionArea;
    }

    public String getIdSociety() {
        return idSociety;
    }

    public String getDescriptionSociety() {
        return descriptionSociety;
    }

    public void setIdCostCenter(String idCostCenter) {
        this.idCostCenter = idCostCenter;
    }

    public void setDescriptionCC(String descriptionCC) {
        this.descriptionCC = descriptionCC;
    }

    public void setIdEmplazament(String idEmplazament) {
        this.idEmplazament = idEmplazament;
    }

    public void setDescriptionEmp(String descriptionEmp) {
        this.descriptionEmp = descriptionEmp;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public void setDescriptionArea(String descriptionArea) {
        this.descriptionArea = descriptionArea;
    }

    public void setIdSociety(String idSociety) {
        this.idSociety = idSociety;
    }

    public void setDescriptionSociety(String descriptionSociety) {
        this.descriptionSociety = descriptionSociety;
    }

    private String idCostCenter;
    private String descriptionCC;
    private String idEmplazament;
    private String descriptionEmp;
    private String idArea;
    private String descriptionArea;
    private String idSociety;
    private String descriptionSociety;
    
    private int codigoError;

    public void createCostCenter() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.cost_centers.tableName()
                    + SentenceValues.setValues(8);
            
            st = conexion.prepareStatement(query);
            st.setString(1, getIdCostCenter());
            st.setString(2, getDescriptionCC());
            st.setString(3, getIdEmplazament());
            st.setString(4, getDescriptionEmp());
            st.setString(5, getIdArea());
            st.setString(6, getDescriptionArea());
            st.setString(7, getIdSociety());
            st.setString(8, getDescriptionSociety());
            
            st.executeUpdate();            
            st.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(F02_Create_Cost_Center.class.getName()).log(Level.SEVERE, null, ex);
            
            setCodigoError(ex.getErrorCode());
                
            
        }
        
    }

}
