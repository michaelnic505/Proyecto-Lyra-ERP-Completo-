
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class AlignedColumnRenderer implements TableCellRenderer {
    private final TableCellRenderer originalRenderer;
    private final int targetColumn; // Columna específica a modificar
    private final int alignment;    // Alineación deseada

    public AlignedColumnRenderer(JTable table, int targetColumn, int alignment) {
        // Obtener el renderizador original de la columna
        TableCellRenderer existingRenderer = table.getColumnModel().getColumn(targetColumn).getCellRenderer();
        // Si no tiene un renderizador asignado, usar el renderizador por defecto de la tabla
        if (existingRenderer == null) {
            existingRenderer = table.getDefaultRenderer(table.getColumnClass(targetColumn));
        }
        this.originalRenderer = existingRenderer;
        this.targetColumn = targetColumn;
        this.alignment = alignment;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Obtener el componente del renderizador original
        Component component = originalRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Si es la columna objetivo, aplicar la alineación
        if (column == targetColumn && component instanceof DefaultTableCellRenderer) {
            DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) component;
            renderer.setHorizontalAlignment(alignment); // Aplicar la alineación
        }

        return component; // Devolver el componente con el estilo original y la alineación modificada (si es la columna objetivo)
    }

    // Método estático para simplificar la asignación del renderizador
    public static void setAlignedColumnRenderer(JTable table, int targetColumn, int alignment) {
        AlignedColumnRenderer renderer = new AlignedColumnRenderer(table, targetColumn, alignment);
        table.getColumnModel().getColumn(targetColumn).setCellRenderer(renderer);
    }
}