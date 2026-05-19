

package com.simplecore.erp.client.gui.components.tables;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.TableCellRenderer;

public class BooleanCellRenderer extends JCheckBox implements TableCellRenderer {

    public BooleanCellRenderer() {
        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if (value instanceof Boolean) {
            setSelected((Boolean) value);
        }

        // Pedimos el render por defecto de la celda y le copiamos el fondo
        TableCellRenderer defaultRenderer = table.getDefaultRenderer(Object.class);
        Component defaultComp = defaultRenderer.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);

        setBackground(defaultComp.getBackground());
        setForeground(defaultComp.getForeground());
        setOpaque(true); // Aseguramos que se pinte el fondo

        return this;
    }
}
