
package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.materiales_orden_lista_materiales;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author user
 */
public class CellRenderSearchButton extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        SearchMaterialButton btn = new SearchMaterialButton();

        return btn;
    }

}
