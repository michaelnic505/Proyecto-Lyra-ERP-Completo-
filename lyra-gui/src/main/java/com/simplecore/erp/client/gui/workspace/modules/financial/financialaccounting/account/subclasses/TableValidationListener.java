

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.subclasses;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import corex.suite.CorpTable;
import corex.utils.LCTableModel;
import java.awt.Component;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class TableValidationListener implements TableModelListener {
    private final CorpTable table;
    private final int maxCharacter;
    private final int minValue;
    private final int maxValue;
    private final SystemMessages notificator;
    private boolean isUpdating = false;
    
    public TableValidationListener(CorpTable table, int maxCharacter, int minValue, int maxValue,SystemMessages notificator) {
        this.table = table;
        this.maxCharacter = maxCharacter;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.notificator = notificator;
    }

    public void disableValidation() {
        this.isUpdating = true;
    }

    public void enableValidation() {
        this.isUpdating = false;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (isUpdating) {
            return;
        }
        if (e.getType() == TableModelEvent.UPDATE) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            isUpdating = true; // Bloqueamos para evitar loops

            try {
                if (column == 1) {
                    validateCode(row);
                } else if (column == 2) {
                    validateDescription(row);
                    validateCode(row);
                }
            } finally {
                isUpdating = false; // Desbloqueamos después de validar
            }

        }
    }

    private void validateCode(int rowIndex) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();
        String codeStr = (String) model.getValueAt(rowIndex, 1);
        String description = (String) model.getValueAt(rowIndex, 2);

        Integer code = parseInteger(codeStr);
        if (code == null) {
            model.setValueAt("", rowIndex, 1);
            return;
        }

        // Validar rango de valores
        if (code < minValue || code > maxValue) {
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ERROR_CODE_OUT_OF_RANGE));
            model.setValueAt("", rowIndex, 1);
            return;
        }

        // Validar duplicados
        if (isDuplicate(code, rowIndex)) {
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ERROR_DUPLICATE_CODE));
            model.setValueAt("", rowIndex, 1);
            return;
        }

        // Validar que el código sea mayor que el de la fila anterior
        if (rowIndex > 0) { // Solo aplicar si no es la primera fila
            String prevCodeStr = (String) model.getValueAt(rowIndex - 1, 1);
            Integer prevCode = parseInteger(prevCodeStr);

            if (prevCode != null && code <= prevCode) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ERROR_CODE_MUST_BE_GREATER));
                model.setValueAt("", rowIndex, 1);
                return;
            }
        }

        // Si la descripción está vacía, mover el foco a la columna de descripción
        if (description == null || description.trim().isEmpty()) {
            focusOnCell(rowIndex, 2);
        } else {
            editNextRow(rowIndex);
        }
    }

    private void validateDescription(int rowIndex) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();
        String description = (String) model.getValueAt(rowIndex, 2);
        String codeStr = (String) model.getValueAt(rowIndex, 1);

        if (description == null || description.trim().isEmpty()) {
            model.setValueAt("", rowIndex, 2);
        } else if (description.length() > maxCharacter) {
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ERROR_DESCRIPTION_EXCEEDS_LIMIT));
            model.setValueAt("", rowIndex, 2);
        } else if (codeStr == null || codeStr.trim().isEmpty()) {
            focusOnCell(rowIndex, 1); // Si la descripción es válida pero el código está vacío, enfocar en el código
        }else{
            editNextRow(rowIndex);
        }
    }

    private void focusOnCell(int row, int column) {
        JTable tableData = table.TableData();
        // Asegurarse de que la tabla tiene el foco
        tableData.requestFocusInWindow();
        // Cambiar la selección a la celda deseada
        tableData.changeSelection(row, column, false, false);
        // Usamos SwingUtilities.invokeLater para asegurar que la celda esté lista para editar
        SwingUtilities.invokeLater(() -> {
            // Iniciar la edición si no está en modo edición
            if (!tableData.isEditing()) {
                tableData.editCellAt(row, column);
            }
            // Obtener el componente editor (si la celda está en modo de edición)
            Component editor = tableData.getEditorComponent();
            if (editor != null) {
                // Darle foco al componente editor
                editor.requestFocusInWindow();
            }
        });
    }

    private void editNextRow(int rowIndex) {
        JTable tableData = table.TableData();
        LCTableModel model = (LCTableModel) tableData.getModel();
        String codeStr = (String) model.getValueAt(rowIndex, 1);
        String description = (String) model.getValueAt(rowIndex, 2);

        if (codeStr != null && description != null) {
            // Disable editing on current row
            model.setCellEditable(rowIndex, 1, false);
            model.setCellEditable(rowIndex, 2, false);

            // Check if the next row exists
            if (rowIndex < model.getRowCount() - 1) {
                rowIndex++; // Move to next row
                // Enable editing on next row
                model.setCellEditable(rowIndex, 1, true);
                model.setCellEditable(rowIndex, 2, true);
                focusOnCell(rowIndex, 1);
            }
        }
    }

    private Integer parseInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isDuplicate(int code, int currentRow) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (i == currentRow) {
                continue; // Ignorar la fila actual
            }
            Integer existingCode = parseInteger((String) model.getValueAt(i, 1));

            if (existingCode != null && existingCode == code) {
                return true; // Encontramos un código duplicado
            }
        }
        return false;
    }

}
