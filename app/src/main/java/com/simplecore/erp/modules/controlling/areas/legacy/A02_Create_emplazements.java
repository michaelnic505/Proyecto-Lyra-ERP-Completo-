
package com.simplecore.erp.modules.controlling.areas.legacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;




public class A02_Create_emplazements {

    public String getIdEmplazement() {
        return idEmplazement;
    }

    public String getDescriptionEmplazement() {
        return descriptionEmplazement;
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

    public String getDescripcionSociety() {
        return descripcionSociety;
    }

    public void setIdEmplazement(String idEmplazement) {
        this.idEmplazement = idEmplazement;
    }

    public void setDescriptionEmplazement(String descriptionEmplazement) {
        this.descriptionEmplazement = descriptionEmplazement;
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

    public void setDescripcionSociety(String descripcionSociety) {
        this.descripcionSociety = descripcionSociety;
    }

    private String idEmplazement;
    private String descriptionEmplazement;
    private String idArea;
    private String descriptionArea;
    private String idSociety;
    private String descripcionSociety;

    public void createEmplazement() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.emplazement.tableName()
                    + SentenceValues.setValues(7);
            
            st = conexion.prepareStatement(query);
            st.setString(1, getIdEmplazement());
            st.setString(2, getDescriptionEmplazement());
            st.setString(3, getIdArea());
            st.setString(4, getDescriptionArea());
            st.setString(5, getIdSociety());
            st.setString(6, getDescripcionSociety());
            st.setBoolean(7, true);
            st.executeUpdate();
            
            st.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(A02_Create_emplazements.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

}
