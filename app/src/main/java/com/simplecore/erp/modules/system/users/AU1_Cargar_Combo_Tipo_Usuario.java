package com.simplecore.erp.modules.system.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;


public class AU1_Cargar_Combo_Tipo_Usuario {

    public JComboBox getComboTipoUsuarios() {
        return comboTipoUsuarios;
    }

    public void setComboTipoUsuarios(JComboBox comboTipoUsuarios) {
        this.comboTipoUsuarios = comboTipoUsuarios;
    }

    private JComboBox comboTipoUsuarios;

    public void cargarCombo() {

        try {
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            String query = SQLKeywords.SELECT_ALL.toSQL()
                    + DatabaseTables.Tipos_de_usuarios.tableName();
            
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet Datos = pSt.getResultSet();
            while(Datos.next()){
              getComboTipoUsuarios().addItem(Datos.getString(1));
            }
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(AU1_Cargar_Combo_Tipo_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
