
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


public class A01_Create_Area {

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


    private String idArea;
    private String descriptionArea;
    private String idSociety;
    private String descriptionSociety;
    
    
    public void createArea(){
        
        try {
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement st = null;
            
            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.Areas.tableName()
                    + SentenceValues.setValues(5);
            
            
            st = conexion.prepareStatement(query);
            st.setString(1, getIdArea());
            st.setString(2, getDescriptionArea());
            st.setString(3, getIdSociety());
            st.setString(4, getDescriptionSociety());
            st.setBoolean(5, true);
            
            st.executeUpdate();            
            st.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(A01_Create_Area.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 

    
}
