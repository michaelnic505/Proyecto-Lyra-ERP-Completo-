
package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.config.database.tables.equipments;
import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils.StatusEquipment;


public class E01_Cargar_Lista_Equipos {

    
    public void cargarListaEquiposEnTabla(JTable table){
     
        LyraTableModel model = (LyraTableModel) table.getModel();
        
        Connection conexion = PooledConnectionService.getConnection();
        
        PreparedStatement pSt = null;
       
        String query = SQLKeywords.SELECT.toSQL()
                     + equipments.EQUIPMENT_ID.toString()
                     + equipments.NAME.toString()
                     + equipments.STATUS_ID.toString()
                     + equipments.STATUS_NAME.toString()
                     + equipments.TYPE_EQUIPMENT_ID.toString()
                     + equipments.TYPE_NAME.toString()
                     + equipments.CRITICALITY_ID.toString()
                     + equipments.CRITICALITY_NAME.toString()
                     + equipments.BRAND.toString()
                     + equipments.MODEL.toString()
                     + equipments.SERIAL.toString()
                
                     + equipments.SOCIETY.toString()
                     + equipments.SOCIETY_NAME.toString()
                
                     + equipments.FIXED_ASSETS.toString()
                     + equipments.FIXED_ASSETS_NAME.toString()
                
                
                     + equipments.COST_CENTER.toString()
                     + equipments.COST_CENTER_NAME.toString()
                     + equipments.EMPLAZEMENT_CENTER.toString()
                     + equipments.EMPLAZEMENT_CENTER_NAME.toString()
                
                
                
                     + equipments.AREA_ID.toString()
                     + equipments.AREA_NAME.toString()
                
                     + equipments.TOP_EQUIPMENT.toString()
                     + equipments.TOP_EQUIPMENT_NAME.toString()
                
                     + equipments.LOCATION.toString()
                     + equipments.LOCATION_NAME.toString()

                     + equipments.CREATED_BY.toString()
                     + equipments.CREATION_DATE.toString()
                     + equipments.MODIFIED_BY.toString()
                     + equipments.MODIFICATION_DATE.toString()  
                     + equipments.G_PLANIFICACION.toString()  
                     + equipments.DESCRIPCION_G_PLANIF.toString()  
                     + DatabaseTables.EQUIPMENTS.tableName()
                     + SQLKeywords.WHERE.toSQL()
                     + equipments.STATUS_ID.toString()
                     + SQLKeywords.EQUALS.toSQL()
                     + SQLKeywords.SINGLE_QUOTE.toSQL()  
                     + StatusEquipment.A.toString()
                     + SQLKeywords.SINGLE_QUOTE.toSQL()
                      ;
        
        try {
           
            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();
            
            ResultSet Datos = pSt.getResultSet();
            ResultSetMetaData MetaDatos = Datos.getMetaData();
            
            
            int CantCol = MetaDatos.getColumnCount();

                while (Datos.next()) {
                    
                    Object[] filas = new Object[CantCol];
                   
                    for (int i = 0; i < CantCol; i++) {
                        filas[i] = Datos.getObject(i + 1);
                    }
                   
                    model.addRow(filas);
                }
        
                
                
                table.setModel(model);
                pSt.close();
            
       
        
        
        } catch (SQLException ex) {
            Logger.getLogger(E01_Cargar_Lista_Equipos.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }      
    
    
}
