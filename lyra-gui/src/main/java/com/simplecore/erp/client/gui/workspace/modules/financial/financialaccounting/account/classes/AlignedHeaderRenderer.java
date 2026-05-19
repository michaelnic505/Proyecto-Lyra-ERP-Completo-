
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.classes;

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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.JTableHeader;

public class AlignedHeaderRenderer implements TableCellRenderer {
    private final TableCellRenderer originalRenderer;
    private final int alignment; // SwingConstants.LEFT, CENTER, RIGHT

    public AlignedHeaderRenderer(TableCellRenderer originalRenderer, int alignment) {
        this.originalRenderer = originalRenderer;
        this.alignment = alignment;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = originalRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Si el componente es un JLabel, aplicamos la alineación deseada
        if (component instanceof javax.swing.JLabel label) {
            label.setHorizontalAlignment(alignment);
        }

        return component;
    }

    public static void applyToHeader(JTable table, int targetColumn, int alignment) {
        JTableHeader header = table.getTableHeader();
        TableCellRenderer originalRenderer = header.getDefaultRenderer();

        // Aplicamos el nuevo render para la columna específica
        table.getColumnModel().getColumn(targetColumn).setHeaderRenderer(new AlignedHeaderRenderer(originalRenderer, alignment));

        // Aseguramos que el header se actualice
        header.repaint();
    }
}
