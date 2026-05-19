
package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;



public class CombosItems {

 
    public JComboBox getCOMBO_BOX() {
        return COMBO_BOX;
    }

   
    public void setCOMBO_BOX(JComboBox COMBO_BOX) {
        this.COMBO_BOX = COMBO_BOX;
    }

 
    public String getTABLA_SQL() {
        return TABLA_SQL;
    }


    public void setTABLA_SQL(String TABLA_SQL) {
        this.TABLA_SQL = TABLA_SQL;
    }
    
    private JComboBox COMBO_BOX;
    private String TABLA_SQL;
    
    public void actualizarCombos(JComboBox combo, String tablasql) {

        Connection conexion = PooledConnectionService.getConnection();

        PreparedStatement preparedStatement = null;
        String query = SQLKeywords.SELECT_ALL
                     + getTABLA_SQL();

        
            try {
                preparedStatement = conexion.prepareStatement(query);
                preparedStatement.executeQuery();
                ResultSet consulta = preparedStatement.getResultSet();

                while (consulta.next()) {

                    String item = consulta.getString(1) + " - " + consulta.getString(2);
                    getCOMBO_BOX().addItem(item);
                }

            } catch (Exception ex) {

            }
        }
    }


