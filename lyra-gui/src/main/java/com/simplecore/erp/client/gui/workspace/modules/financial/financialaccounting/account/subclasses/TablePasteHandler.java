

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
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TablePasteHandler {
    private final CorpTable table;
    private final int maxCharacter;
    private final int minValue;
    private final int maxValue;
    

    public TablePasteHandler(CorpTable table, int maxCharacter, int minValue, int maxValue) {
        this.table = table;
        this.maxCharacter = maxCharacter;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    public void pasteFromClipboard(int fromRow) throws InvalidPasteDataException {
        pasteClipboardData(fromRow);
    }
    private void pasteClipboardData(int fromRow) throws InvalidPasteDataException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            String clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
            processClipboardData(clipboardText,fromRow);
        } catch (UnsupportedFlavorException | IOException ex) {
        }
    }

    private void processClipboardData(String clipboardText,int fromRow) throws InvalidPasteDataException{
        if (clipboardText == null || clipboardText.trim().isEmpty()) {
            return;
        }
        // 🔹 Procesar las filas del portapapeles
        String[] rows = clipboardText.split("\n");
        Set<Integer> existingCodes = new HashSet<>();
        List<String[]> validData = new ArrayList<>();

        for (String row : rows) {
            String[] columns = row.split("\t"); // Usa tabulación como separador

            if (columns.length != 2) {
                throw new InvalidPasteDataException(AppMessages.msg(AppMessages.Key.ERROR_INVALID_COLUMNS));
            }

            String codeString = columns[0].trim();
            String description = columns[1].trim();

            // 🔹 Validar si `codeStr` es un entero
            Integer code = parseInteger(codeString);
            if (code == null || code < minValue || code > maxValue) {
                throw new InvalidPasteDataException(AppMessages.msg(AppMessages.Key.ERROR_CODE_OUT_OF_RANGE));
            }
            // 🔹 Validar duplicados
            if (!existingCodes.add(code)) {
               throw new InvalidPasteDataException(AppMessages.msg(AppMessages.Key.ERROR_DUPLICATE_CODE));
            }

            // 🔹 Validar la longitud de la descripción
            if (description.length() > maxCharacter) {
                throw new InvalidPasteDataException(AppMessages.msg(AppMessages.Key.ERROR_DESCRIPTION_EXCEEDS_LIMIT));
            }

            validData.add(new String[]{codeString, columns[1].trim()});
        }
        // Ordenar la lista por `code`
        validData.sort(Comparator.comparingInt(entry -> Integer.parseInt(entry[0])));
        if(!canPasteData(table,fromRow,validData.size())){
            new SystemMessages().showErrorMsg(AppMessages.msg(AppMessages.Key.INSUFFICIENT_SPACE_TO_PASTE));
            return;
        }
        setValuesOnModel(validData,fromRow);
    }

    private boolean canPasteData(CorpTable table, int rowFrom, int dataLength) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();
        int totalRows = model.getRowCount();

        // Validar que la fila de inicio esté dentro del rango válido
        if (rowFrom < 0 || rowFrom >= totalRows) {
            return false;
        }

        // Calcular si hay espacio suficiente para pegar los datos
        return (rowFrom + dataLength) <= totalRows;
    }

    private void setValuesOnModel(List<String[]> validData,int fromRow) {
        // 🔹 Insertar datos en la tabla solo si todos pasaron la validación
        LCTableModel model = (LCTableModel) table.TableData().getModel();
        
        for(int row = fromRow; row < model.getRowCount();row++){
            for(int col = 1; col < model.getColumnCount();col++){
                model.setValueAt(null, row, col);
            }
        }

        for (int i = 0; i < validData.size(); i++) {
            String[] data = validData.get(i);
            model.setValueAt(data[0], i + fromRow, 1);
            model.setValueAt(data[1], i + fromRow, 2);
            model.setCellEditable(i + fromRow, 1, false);
            model.setCellEditable(i + fromRow, 2, false);
        }

        if (model.getRowCount() > validData.size()) {
            int nextRowIndex = validData.size() + fromRow;
            model.setCellEditable(nextRowIndex, 1, true);
            model.setCellEditable(nextRowIndex, 2, true);
        }
    }

    
    private Integer parseInteger(String str) {
        try {
            return Integer.parseInt(str); // Solo permite enteros dentro del rango de int
        } catch (NumberFormatException e) {
            return null; // Devuelve null si no es un entero válido
        }
    }

    class InvalidPasteDataException extends Exception {
        public InvalidPasteDataException(String message) {
            super(message);
        }
    }
}
