
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.materiales_orden_lista_materiales;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class CellEditorSearchButton extends DefaultCellEditor {
    
    SearchButtonInterface btI;
    
    public CellEditorSearchButton(SearchButtonInterface btI) {
        super(new JTextField());
        
        this.btI = btI;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        SearchMaterialButton btn = new SearchMaterialButton();
        btn.funcionBotton(btI, row, column);
        
        return btn;
    }
    
}
