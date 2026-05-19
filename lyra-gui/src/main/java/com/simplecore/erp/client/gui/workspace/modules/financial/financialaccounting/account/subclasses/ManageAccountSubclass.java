package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.subclasses;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.classes.ViewAccountClasses;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountClassesRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountClassesRetrieveResponse;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import com.simplecore.erp.client.controllers.workspace.TaskPanel;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.classes.AlignedColumnRenderer;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.models.ModelStates;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterNumeric;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.exceptions.IncompleteDataException;
import com.simplecore.erp.shared.models.dto.AccountSubclassDTO;
import com.simplecore.erp.shared.requests.types.AccountModelStateChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountRangesModelRetrieveRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassChangeRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassCreateRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassDeleteRequest;
import com.simplecore.erp.shared.requests.types.AccountSubclassByModelRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountModelStateChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountRangesModelRetrieveResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassChangeResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassCreateResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassDeleteResponse;
import com.simplecore.erp.shared.responses.types.AccountSubclassesRetrieveResponse;
import corex.suite.CorpTable;
import corex.utils.LCTableModel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.apache.commons.math3.util.Pair;

public class ManageAccountSubclass extends JPanel implements TransactionPanel, TaskPanel {

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final SystemMessages notificator;
    private final TranslationHelper windowTranslator;
    private final int modelId;
    private final String modelName;
    private final String modelDescription;
    private String systemState;

    public ManageAccountSubclass(int modelId, String modelName, String modelDescription, String systemState) {

        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.notificator = new SystemMessages();
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelDescription = modelDescription;
        this.systemState = systemState;
        initComponents();
        setHeaderValues();
    }
    
    private String transactionCode;
    @Override
    public void initialize(String transactionCode,ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.transactionCode = transactionCode;
        this.activeSession = session;
        this.output = output;
        this.input = input;
        requestAccountClassesList();
        requestAccountRangesByModelId();
        initializeAccountTables();
        requestAccountSubclassesByModelId(modelId);
        initCommandButtons();
    }

    private int assetsId;
    private int liabilitiesId;
    private int revenueId;
    private int equityId;
    private int costsId;
    private int expensesId;

    private String assetsDescription;
    private String liabilitiesDescription;
    private String revenueDescription;
    private String equityDescription;
    private String costsDescription;
    private String expensesDescription;

    private void setHeaderValues() {
        modelNameTextField.setText(modelName);
        modelIdTextField.setText(String.valueOf(modelId));
        modelDescriptionTextField.setText(modelDescription);
        systemStateTextField.setText(systemState);
    }

    private void componentsByModelState(String state,CorpTable table ,JButton pasteButton, JButton clearButton,
            JButton deleteButton, JButton addRowButton, JButton padlockButton, JButton editRowButton) {

        ModelStates current = ModelStates.fromString(state);

        // Lógica común para todos los estados
        switch (current) {
            case CREATED:
                setComponentsEnabled(true, pasteButton, clearButton, deleteButton, addRowButton, padlockButton, editRowButton);
                break;
            case READY:
                // Todos habilitados
                setComponentsEnabled(true, pasteButton, clearButton, deleteButton, addRowButton, padlockButton, editRowButton);
                break;
            case IN_USE:
                // Todos habilitados, excepto algunos
                setComponentsEnabled(true, pasteButton, clearButton, deleteButton, addRowButton, padlockButton,editRowButton);
                editRowButton.setEnabled(false);  // Excepción para IN_USE
                proceedButton.setEnabled(false);
                cancelButton.setEnabled(false);
                setNotEditableTable(table);
                break;
            case CANCELLED:
                // Todos deshabilitados
                setComponentsEnabled(false, pasteButton, clearButton, deleteButton, addRowButton, padlockButton, editRowButton);
                proceedButton.setEnabled(false);
                cancelButton.setEnabled(false);
                setNotEditableTable(table);
                Workspace.getSaveButton().setEnabled(false);
                break;
        }
    }

    private void setComponentsEnabled(boolean isEnabled, JButton pasteButton, JButton clearButton,
            JButton deleteButton, JButton addRowButton, JButton padlockButton, JButton editRowButton) {

        pasteButton.setEnabled(isEnabled);
        clearButton.setEnabled(isEnabled);
        deleteButton.setEnabled(isEnabled);
        addRowButton.setEnabled(isEnabled);
        padlockButton.setEnabled(isEnabled);
        editRowButton.setEnabled(isEnabled);
    }

    private void changeStateAndApplyToTables(String newState) {
        // Aplicar el cambio de estado a los botones relacionados con las tablas
        componentsByModelState(newState,assetsTable ,assetsPasteButton, assetsClearButton, assetsDeleteRowButton, assetsAddRowButton, assetsPadlockButton, assetsEditRowButton);
        componentsByModelState(newState,liabilitiesTable, liabilitiesPasteButton, liabilitiesClearButton, liabilitiesDeleteRowButton, liabilitiesAddRowButton, liabilitiesPadlockButton, liabilitiesEditRowButton);
        componentsByModelState(newState,equityTable, equityPasteButton, equityClearButton, equityDeleteRowButton, equityAddRowButton, equityPadlockButton, equityEditRowButton);
        componentsByModelState(newState,revenueTable, revenuePasteButton, revenueClearButton, revenueDeleteRowButton, revenueAddRowButton, revenuePadlockButton, revenueEditRowButton);
        componentsByModelState(newState,costsTable, costsPasteButton, costsClearButton, costsDeleteRowButton, costsAddRowButton, costsPadlockButton, costsEditRowButton);
        componentsByModelState(newState,expensesTable, expensesPasteButton, expensesClearButton, expensesDeleteRowButton, expensesAddRowButton, expensesPadlockButton, expensesEditRowButton);
        
    }

    private void requestAccountClassesList() {
        try {
            output.writeObject(new AccountClassesRetrieveRequest(activeSession.getSessionId(), activeSession.getUserId()));
            output.flush();
            Object response = input.readObject();
            if (response instanceof AccountClassesRetrieveResponse accountClassesResponse) {
                String[][] matrix = accountClassesResponse.getAccountClasses();
                recollectDataAccountClass(matrix);
            }
            setAccountDataInTextFields();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ViewAccountClasses.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void requestAccountRangesByModelId() {
        String sessionId = activeSession.getSessionId();
        int userId = activeSession.getUserId();
        AccountRangesModelRetrieveRequest rangesRequest = new AccountRangesModelRetrieveRequest.Builder(sessionId, userId, modelId)
                .assetsId(assetsId)
                .liabilitiesId(liabilitiesId)
                .equityId(equityId)
                .revenueId(revenueId)
                .costsId(costsId)
                .expensesId(expensesId)
                .build();

        try {
            output.writeObject(rangesRequest);
            output.flush();
            Object response = input.readObject();

            if (response instanceof AccountRangesModelRetrieveResponse modelResponse) {
                setValuesFromResponse(modelResponse);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ManageAccountSubclass.class.getName())
                    .log(Level.SEVERE, "Error requesting account ranges by model ID", ex);
        }
    }
    private void requestAccountSubclassesByModelId(int modelId) {
        disableAllModelListeners();
        try {
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();

            output.writeObject(new AccountSubclassByModelRetrieveRequest(sessionId, userId, modelId));
            output.flush();

            Object response = input.readObject();
            if (response instanceof AccountSubclassesRetrieveResponse subclassesResponse) {
                if(subclassesResponse.isSqlError()){
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return;
                }
                if(subclassesResponse.wasFound()){
                    subclassesAssignment(subclassesResponse.getSubclassesList());
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ManageAccountSubclass.class.getName()).log(Level.SEVERE, null, ex);
        }
        enableAllModelListeners();
    }

    private void recollectDataAccountClass(String[][] matrix) {
        Pair<Integer, String> result;

        result = getValuesByAccountClass(matrix, "Assets");
        assetsId = result.getKey();
        assetsDescription = result.getValue();

        result = getValuesByAccountClass(matrix, "Liabilities");
        liabilitiesId = result.getKey();
        liabilitiesDescription = result.getValue();

        result = getValuesByAccountClass(matrix, "Equity");
        equityId = result.getKey();
        equityDescription = result.getValue();

        result = getValuesByAccountClass(matrix, "Revenue");
        revenueId = result.getKey();
        revenueDescription = result.getValue();

        result = getValuesByAccountClass(matrix, "Costs");
        costsId = result.getKey();
        costsDescription = result.getValue();

        result = getValuesByAccountClass(matrix, "Expenses");
        expensesId = result.getKey();
        expensesDescription = result.getValue();
    }
    private Pair<Integer, String> getValuesByAccountClass(String[][] matrix, String accountName)    {
        for (String[] row : matrix) {
            if (row.length >= 4 && row[2].trim().equals(accountName)) {
                return new Pair<>(Integer.parseInt(row[0]), descriptionClass(row[3]));
            }
        }
        return new Pair<>(-1, "N/A"); // Retorna valores por defecto si no se encuentra la cuenta
    }

    
    private void subclassesAssignment(List<AccountSubclassDTO> subclasses) {
        setSubclassesInTable(assetsTable, filterSubclasses(subclasses, assetsId));
        setSubclassesInTable(liabilitiesTable, filterSubclasses(subclasses, liabilitiesId));
        setSubclassesInTable(equityTable, filterSubclasses(subclasses, equityId));
        setSubclassesInTable(revenueTable, filterSubclasses(subclasses, revenueId));
        setSubclassesInTable(costsTable, filterSubclasses(subclasses, costsId));
        setSubclassesInTable(expensesTable, filterSubclasses(subclasses, expensesId));
    }
    private List<AccountSubclassDTO> filterSubclasses(List<AccountSubclassDTO> subclasses, int accountClassId) {
        return subclasses.stream()
                .filter(subclass -> subclass.getClassId() == accountClassId)
                .toList();
    }
    private void setSubclassesInTable(CorpTable table, List<AccountSubclassDTO> subclasses) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();
        int row = 0;

        for (AccountSubclassDTO subclass : subclasses) {
            model.setValueAt(String.valueOf(subclass.getSubclassId()), row, 0);
            model.setValueAt(String.valueOf(subclass.getSubclassCode()), row, 1);
            model.setValueAt(subclass.getSubclassName(), row, 2);
            model.setCellEditable(row, 1, false);
            model.setCellEditable(row, 2, false);
            row++;
        }
        model.setCellEditable(row, 1, true);
        model.setCellEditable(row, 2, true);
    }
    
    
    private void setValuesFromResponse(AccountRangesModelRetrieveResponse modelResponse) {
        setRangesValuesInField(assetsFromTextField, assetsToTextField, modelResponse.getAssetsFrom(), modelResponse.getAssetsTo());
        setRangesValuesInField(liabilitiesFromTextField, liabilitiesToTextField, modelResponse.getLiabilitiesFrom(), modelResponse.getLiabilitiesTo());
        setRangesValuesInField(equityFromTextField, equityToTextField, modelResponse.getEquityFrom(), modelResponse.getEquityTo());
        setRangesValuesInField(revenueFromTextField, revenueToTextField, modelResponse.getRevenueFrom(), modelResponse.getRevenueTo());
        setRangesValuesInField(costsFromTextField, costsToTextField, modelResponse.getCostsFrom(), modelResponse.getCostsTo());
        setRangesValuesInField(expensesFromTextField, expensesToTextField, modelResponse.getExpensesFrom(), modelResponse.getExpensesTo());
    }
    private void setRangesValuesInField(JTextField textFieldFrom, JTextField textFieldTo, int from, int to) {
        textFieldFrom.setText(String.valueOf(from));
        textFieldTo.setText(String.valueOf(to));
    }
    
    private void setAccountDataInTextFields() {
        assetsTag.setText("(" + assetsId + ") " + assetsDescription);
        liabilitiesTag.setText("(" + liabilitiesId + ") " + liabilitiesDescription);
        revenueTag.setText("(" + revenueId + ") " + revenueDescription);
        equityTag.setText("(" + equityId + ") " + equityDescription);
        costsTag.setText("(" + costsId + ") " + costsDescription);
        expensesTag.setText("(" + expensesId + ") " + expensesDescription);
    }
    private String descriptionClass(String key) {
        return tableTranslator.getTranslation(key);
    }

    private void initCommandButtons() {
        setSaveButtonListener();
        setButtonCommandConfigure();
        setButtonCancelConfigure();
    }

    private void setButtonCommandConfigure() {
        proceedButton.setIcon(new CustomSVGIcon("/icons/svg/start_flag.svg", new Dimension(24, 24)));
        proceedButton.addActionListener(e->{
            ModelStates currentState = ModelStates.fromString(systemState);
            sendModelStateChangeRequest(currentState.getNext());
        });
    }
    private void setButtonCancelConfigure() {
        cancelButton.setIcon(new CustomSVGIcon("/icons/svg/cancel.svg", new Dimension(24, 24)));
        cancelButton.addActionListener(e -> {
            ModelStates current = ModelStates.fromString(systemState);
            if(current!=ModelStates.IN_USE){
                sendModelStateChangeRequest(ModelStates.CANCELLED);
            }
            
        });
    }
    
    private void initializePadblockButton(CorpTable table, JButton padlockButton) {
        padlockButton.setIcon(new CustomSVGIcon("/icons/svg/padlock.svg", new Dimension(18, 18)));
        lockTable(table, padlockButton);
    }
    private void initializePasteButton(CorpTable table, JButton pasteButton, int min, int max) {
        pasteButton.setIcon(new CustomSVGIcon("/icons/svg/paste.svg", new Dimension(16, 16)));
        setPasteTableHandler(table, pasteButton, min, max);
    }
    private void initializeClearButton(CorpTable table, JButton clearButton) {
        clearButton.setIcon(new CustomSVGIcon("/icons/svg/clear.svg", new Dimension(18, 18)));
        setClearTable(table, clearButton);
    }
    private void initializeAddRowButton(CorpTable table, JButton addRowButton) {
        addRowButton.setIcon(new CustomSVGIcon("/icons/svg/add_row.svg", new Dimension(18, 18)));
        setAddRowToEdit(table, addRowButton);
    }
    private void initializeDeleteRowButton(CorpTable table, JButton deleteButton) {
        deleteButton.setIcon(new CustomSVGIcon("/icons/svg/delete_row.svg", new Dimension(18, 18)));
        setDeleteSelectedRows(table, deleteButton);
    }
    private void initializeEditRowButton(CorpTable table, JButton editButton){
        editButton.setIcon(new CustomSVGIcon("/icons/svg/edit.svg", new Dimension(18, 18)));
        setEditRow(table, editButton);
    }
    private void initializeColumnAlignment(CorpTable table) {
        AlignedColumnRenderer render0 = new AlignedColumnRenderer(table.TableData(), 0, SwingUtilities.CENTER);
        table.TableData().getColumnModel().getColumn(0).setCellRenderer(render0);
        
        AlignedColumnRenderer render1 = new AlignedColumnRenderer(table.TableData(), 1, SwingUtilities.LEFT);
        table.TableData().getColumnModel().getColumn(1).setCellRenderer(render1);
    }
    private void initializeTableEditor(CorpTable table, int accountWidth) {

        JTextField accounTextField = new JTextField();
        DocumentFilterNumeric numFilter = new DocumentFilterNumeric(accountWidth);
        AccountSubclassEditor subclassEditor = new AccountSubclassEditor(accounTextField, numFilter);
        table.TableData().getColumnModel().getColumn(1).setCellEditor(subclassEditor);

        JTextField descriptionTextField = new JTextField();
        DocumentFilterVarchar varcharFilter = new DocumentFilterVarchar(100);
        AccountSubclassEditor descriptionEditor = new AccountSubclassEditor(descriptionTextField, varcharFilter);
        table.TableData().getColumnModel().getColumn(2).setCellEditor(descriptionEditor);

    }
    private void initializeAccountTables() {
        initializeAccountTable(assetsTable, assetsFromTextField, assetsToTextField, assetsPasteButton, assetsClearButton,
                assetsDeleteRowButton, assetsAddRowButton, assetsPadlockButton,assetsEditRowButton);

        initializeAccountTable(liabilitiesTable, liabilitiesFromTextField, liabilitiesToTextField, liabilitiesPasteButton, liabilitiesClearButton,
                liabilitiesDeleteRowButton, liabilitiesAddRowButton, liabilitiesPadlockButton,liabilitiesEditRowButton);

        initializeAccountTable(equityTable, equityFromTextField, equityToTextField, equityPasteButton, equityClearButton,
                equityDeleteRowButton, equityAddRowButton, equityPadlockButton,equityEditRowButton);

        initializeAccountTable(revenueTable, revenueFromTextField, revenueToTextField, revenuePasteButton, revenueClearButton,
                revenueDeleteRowButton, revenueAddRowButton, revenuePadlockButton,revenueEditRowButton);

        initializeAccountTable(costsTable, costsFromTextField, costsToTextField, costsPasteButton, costsClearButton,
                costsDeleteRowButton, costsAddRowButton, costsPadlockButton,costsEditRowButton);

        initializeAccountTable(expensesTable, expensesFromTextField, expensesToTextField, expensesPasteButton, expensesClearButton,
                expensesDeleteRowButton, expensesAddRowButton, expensesPadlockButton,expensesEditRowButton);
    }

    private void initializeAccountTable(CorpTable table, JTextField fromTextField, JTextField toTextField, JButton pasteButton, JButton clearButton,
            JButton deleteButton, JButton addRowButton, JButton padlockButton,JButton editRowButton) {

        int accountWidth = fromTextField.getText().trim().length();
        int numberFrom = Integer.parseInt(fromTextField.getText());
        int numberTo = Integer.parseInt(toTextField.getText());

        String subClassId = tableTranslator.getTranslation(TableKeys.AC13_ACCOUNT_SUBCLASS_ID.getKey());
        String subClassNumer = tableTranslator.getTranslation(TableKeys.AC13_ACCOUNT_SUBCLASS_CODE.getKey());
        String subClassDescription = tableTranslator.getTranslation(TableKeys.AC13_ACCOUNT_SUBCLASS_DESCRIPTION.getKey());

        LCTableModel model = new LCTableModel(100, new String[]{subClassId,subClassNumer, subClassDescription});
        //Deja habilitado para edicion solamente la fila 0 en caso que no hayan datos
        model.setCellEditable(0, 0, false);//Dejamos la columna ID bloqueada puesto que el bloque empieza desde la fila 1
        for (int i = 1; i < model.getRowCount(); i++) {
            model.setCellEditable(i, 0, false);
            model.setCellEditable(i, 1, false);
            model.setCellEditable(i, 2, false);
        }
        table.setModel(model);
        table.TableData().getColumnModel().getColumn(0).setPreferredWidth(70);
        table.TableData().getColumnModel().getColumn(1).setPreferredWidth(150);
        table.TableData().getColumnModel().getColumn(2).setPreferredWidth(320);
        table.setRowHeight(22);
        table.RowsButton().addRowActionListener((int i) -> {
            table.selectOrDeselectOnCase(i);
        });

        initializeColumnAlignment(table);
        initializeTableEditor(table, accountWidth);
        initializeTableModelListener(table, numberFrom, numberTo);

        initializePadblockButton(table, padlockButton);
        initializePasteButton(table, pasteButton, numberFrom, numberTo);
        initializeClearButton(table, clearButton);
        initializeAddRowButton(table, addRowButton);
        initializeDeleteRowButton(table, deleteButton);
        initializeEditRowButton(table, editRowButton);
        
        componentsByModelState(systemState,table ,pasteButton, clearButton, deleteButton, addRowButton, padlockButton, editRowButton);        
    }

    private void setPasteTableHandler(CorpTable table, JButton button, int min, int max) {
        TablePasteHandler pasteHandler = new TablePasteHandler(table, 100, min, max);
        
        button.addActionListener(e -> {
            disableModelListener(table);
            int index = getFromRowIndex(table);
            try {
                if (hasModelTableData(table)) {
                    int response = JOptionPane.showConfirmDialog(null,
                            AppMessages.msg(AppMessages.Key.ARE_YOU_SURE_YOU_WANT_TO_PROCEED),
                            AppMessages.msg(AppMessages.Key.CONFIRMATION),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
                pasteHandler.pasteFromClipboard(index);
            } catch (TablePasteHandler.InvalidPasteDataException ex) {
                notificator.showErrorMsg(ex.getMessage());
            }
            enableModelListener(table);
        });
    }
    private void setClearTable(CorpTable table, JButton button) {
        button.addActionListener(e -> {
            LCTableModel model = (LCTableModel) table.TableData().getModel();
            if (!hasModelTableData(table)) {
                return;
            }
            int response = JOptionPane.showConfirmDialog(null,
                    AppMessages.msg(AppMessages.Key.ARE_YOU_SURE_YOU_WANT_TO_PROCEED),
                    AppMessages.msg(AppMessages.Key.CONFIRMATION),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                return;
            }

            int rowFrom = getFromRowIndex(table);
            int rowCount = model.getRowCount();

            // Deshabilitar la edición en las filas restantes
            for (int i = rowFrom; i < rowCount; i++) {
                model.setValueAt(null, i, 1);
                model.setValueAt(null, i, 2);
                model.setCellEditable(i, 1, false);
                model.setCellEditable(i, 2, false);
            }
            // Habilitar la edición solo en la primera fila
            model.setCellEditable(rowFrom, 1, true);
            model.setCellEditable(rowFrom, 2, true);
        });

    }
    private void setAddRowToEdit(CorpTable table, JButton button) {
        button.addActionListener(e -> {
            LCTableModel model = (LCTableModel) table.TableData().getModel();
            int emptyRowIndex = findFirstEmptyRow(model);
            if (emptyRowIndex != -1) {
                // Aquí puedes agregar el código para editar la fila vacía encontrada
                model.setCellEditable(emptyRowIndex, 1, true);
                model.setCellEditable(emptyRowIndex, 2, true);
                focusOnCell(table, emptyRowIndex, 1);
            }
        });

    }
    private void setDeleteSelectedRows(CorpTable table, JButton button) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();
        button.addActionListener(e -> {
            int[] selectedRows = table.TableData().getSelectedRows();
            if (selectedRows.length == 0) {
                return;
            }
            // Si no hay datos en las celdas seleccionadas, no hacer nada
            if (!hasSeletedCellsData(selectedRows, model)) {
                return;
            }
            // Si hay datos, preguntar antes de eliminar
            int response = JOptionPane.showConfirmDialog(null,
                    AppMessages.msg(AppMessages.Key.ARE_YOU_SURE_YOU_WANT_TO_PROCEED),
                    AppMessages.msg(AppMessages.Key.CONFIRMATION),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                return;
            }

            List<Integer> existingIds = getExistingId(selectedRows, model);

            if (!existingIds.isEmpty()) {
                if(!systemState.equals(ModelStates.CREATED.name())){
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.CANNOT_BE_DELETED_STATUS)+" "+systemState);
                    return;
                }
                
                int[] selectedIds = existingIds.stream().mapToInt(Integer::intValue).toArray();
                boolean successful = sendSubclassDeleteRequest(selectedIds, modelId);
                if (!successful) {
                    return;
                }
            }

            int totalRowsBefore = model.getRowCount();
            // Ordenar de menor a mayor y eliminar de mayor a menor
            Arrays.sort(selectedRows);
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                table.removeRow(selectedRows[i]);
            }

            // Agregar las filas necesarias para mantener la cantidad original
            int totalRowsAfter = model.getRowCount();
            int rowsToAdd = totalRowsBefore - totalRowsAfter;

            for (int i = 0; i < rowsToAdd; i++) {
                table.addRow();
            }
        });
    }
    private void setEditRow(CorpTable table, JButton button) {
        button.addActionListener(e -> {
            int[] selectedRows = table.TableData().getSelectedRows();

            if (selectedRows.length == 0) {
                return; // If no rows are selected, we exit. / Si no se seleccionan filas, salimos.
            }
            Arrays.sort(selectedRows);
            LCTableModel model = (LCTableModel) table.TableData().getModel();
            List<Integer> existingIds = getExistingId(selectedRows, model);
            
            if (!existingIds.isEmpty()) {
                if (!systemState.equals(ModelStates.CREATED.name())) {
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.CANNOT_BE_EDITED_STATUS) + " " + systemState);
                    return;
                }
            }

            setNotEditableTable(table); // Make the table non-editable initially. / Hacer que la tabla no sea editable inicialmente.

            disableModelListener(table); // Disable the model listener to prevent unwanted changes. / Deshabilitar el listener del modelo para evitar cambios no deseados.
            Iterator<Integer> rowIterator = Arrays.stream(selectedRows).boxed().iterator();
            AtomicBoolean isEditing = new AtomicBoolean(false); // Flag to manage the editing process. / Bandera para gestionar el proceso de edición.

            // Listen for changes in the table. / Escuchar los cambios en la tabla.
            TableModelListener modelListener = new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        if (isEditing.get()) {
                            return; // If we are already editing, do nothing. / Si ya estamos editando, no hacer nada.
                        }
                        int updatedColumn = e.getColumn();
                        // Check if we are working with column 2. / Verificar si estamos trabajando con la columna 2.
                        if (updatedColumn == 2) {
                            int updatedRow = e.getFirstRow();

                            isEditing.set(true);
                            model.setCellEditable(updatedRow, 2, false); // Disable editing on the current cell. / Deshabilitar la edición de la celda actual.

                            // If there are more rows to edit, move to the next one. / Si hay más filas para editar, movernos a la siguiente.
                            if (rowIterator.hasNext()) {
                                int row = rowIterator.next();
                                model.setCellEditable(row, 2, true);  // Enable editing on the next row. / Habilitar la edición en la siguiente fila.
                                focusOnCell(table, row, 2);  // Set focus to the new editable cell. / Establecer el foco en la nueva celda editable.
                            } else {
                                // If no more rows, remove the listener and reset selection. / Si no hay más filas, eliminar el listener y resetear la selección.
                                table.TableData().getModel().removeTableModelListener(this);
                                table.resetSelection();
                                enableModelListener(table); // Re-enable the model listener. / Volver a habilitar el listener del modelo.
                            }
                            isEditing.set(false); // Reset the editing flag. / Resetear la bandera de edición.
                        }
                    }
                }
            };

            // Add the listener to listen for changes. / Agregar el listener para escuchar los cambios.
            table.TableData().getModel().addTableModelListener(modelListener);

            // Start with the first row. / Empezar con la primera fila.
            if (rowIterator.hasNext()) {
                isEditing.set(true);
                int row = rowIterator.next();
                model.setCellEditable(row, 2, true);  // Enable editing on the first row. / Habilitar la edición en la primera fila.
                focusOnCell(table, row, 2);  // Set focus to the first editable cell. / Establecer el foco en la primera celda editable.
                isEditing.set(false); // Reset the editing flag. / Resetear la bandera de edición.
            }

        });

    }

    private int getFromRowIndex(CorpTable table) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            Object value = model.getValueAt(row, 0);
            if (value == null || value.toString().trim().isEmpty()) {
                return row;
            }
        }
        return -1;
    }

    private List<Integer> getExistingId(int[] rows, LCTableModel model) {
        List<Integer> existingId = new ArrayList<>();
        for (int row : rows) {
            Object value = model.getValueAt(row, 0);
            if (value != null && !value.toString().trim().isEmpty()) {
                existingId.add(Integer.parseInt(value.toString()));
            }
        }
        return existingId;
    }
    private boolean hasSeletedCellsData(int[] rows, LCTableModel model) {
        for (int row : rows) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                Object value = model.getValueAt(row, col);
                if (value != null && !value.toString().trim().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void focusParentTab(CorpTable table) {
        Component parent = table;
        while (parent != null && !(parent instanceof JTabbedPane)) {
            parent = parent.getParent();
        }

        if (parent instanceof JTabbedPane tabbedPane) {
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                if (SwingUtilities.isDescendingFrom(table, tabbedPane.getComponentAt(i))) {
                    tabbedPane.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
    private void focusOnCell(CorpTable table, int row, int column) {
        JTable tableData = table.TableData();
        // Asegurarse de que la tabla tiene el foco
        tableData.requestFocusInWindow();
        // Cambiar la selección a la celda deseada
        // Solo cambiamos la selección si no es la misma fila y columna
        if (tableData.getSelectedRow() != row || tableData.getSelectedColumn() != column) {
            tableData.changeSelection(row, column, false, false);
        }
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
    
    private boolean hasAtLeastOneCompleteRow(CorpTable table) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            Object subclassCode = model.getValueAt(row, 1);
            Object subclassDescription = model.getValueAt(row, 2);

            boolean isCodeFilled = subclassCode != null && !subclassCode.toString().trim().isEmpty();
            boolean isDescriptionFilled = subclassDescription != null && !subclassDescription.toString().trim().isEmpty();

            if (isCodeFilled && isDescriptionFilled) {
                return true; // Si al menos una fila tiene ambos datos, la tabla es válida.
            }
        }
        focusParentTab(table);
        notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.INCOMPLETE_FIELDS));
        return false; // Si ninguna fila contiene ambos datos, la tabla no es válida.
    }
    private boolean hasAtLeastOneSavedRow(CorpTable table) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            Object subclassId = model.getValueAt(row, 0);
            Object subclassCode = model.getValueAt(row, 1);
            Object subclassDescription = model.getValueAt(row, 2);

            boolean isIdFilled = subclassId != null && !subclassId.toString().trim().isEmpty();
            boolean isCodeFilled = subclassCode != null && !subclassCode.toString().trim().isEmpty();
            boolean isDescriptionFilled = subclassDescription != null && !subclassDescription.toString().trim().isEmpty();

            if (isIdFilled && isCodeFilled && isDescriptionFilled) {
                return true; // Si al menos una fila tiene ambos datos, la tabla es válida.
            }
        }
        focusParentTab(table);
        notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.INCOMPLETE_FIELDS));
        return false; // Si ninguna fila contiene ambos datos, la tabla no es válida.
    }
    private boolean isDataCompleteInAllTables() {
        return hasAtLeastOneCompleteRow(assetsTable)
                && hasAtLeastOneCompleteRow(liabilitiesTable)
                && hasAtLeastOneCompleteRow(equityTable)
                && hasAtLeastOneCompleteRow(revenueTable)
                && hasAtLeastOneCompleteRow(costsTable)
                && hasAtLeastOneCompleteRow(expensesTable);
    }
    private boolean isDataSavedInAllTables(){
        return hasAtLeastOneSavedRow(assetsTable)
                && hasAtLeastOneSavedRow(liabilitiesTable)
                && hasAtLeastOneSavedRow(equityTable)
                && hasAtLeastOneSavedRow(revenueTable)
                && hasAtLeastOneSavedRow(costsTable)
                && hasAtLeastOneSavedRow(expensesTable);
    }
    
    private int findFirstEmptyRow(LCTableModel model) {
        for (int row = 0; row < model.getRowCount(); row++) {
            String code = (String) model.getValueAt(row, 1);
            String description = (String) model.getValueAt(row, 2);

            boolean isEmpty = (code == null || code.trim().isEmpty())
                    && (description == null || description.trim().isEmpty());

            if (isEmpty) {
                return row; // Retorna la primera fila vacía encontrada
            }
        }
        return -1; // No se encontró ninguna fila vacía
    }
    private void lockTable(CorpTable table, JButton button) {
        button.addActionListener(e -> {
            setNotEditableTable(table);
        });

    }
    private void setNotEditableTable(CorpTable table) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();
        int rows = table.TableData().getModel().getRowCount();

        for (int i = 0; i < rows; i++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                model.setCellEditable(i, col, false);
            }
        }
        table.TableData().clearSelection();
    }
    private boolean hasModelTableData(CorpTable table) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                Object value = model.getValueAt(row, col);
                if (value != null && !value.toString().trim().isEmpty()) {
                    return true;
                }
            }

        }
        return false;
    }
    
    private final Map<CorpTable,TableModelListener> modelListeners = new HashMap<>();
    private void initializeTableModelListener(CorpTable table, int min, int max) {
        TableValidationListener modelListener = new TableValidationListener(table, 100, min, max, notificator);
        table.TableData().getModel().addTableModelListener(modelListener);
  
        modelListeners.put(table, modelListener);
    }

    private void disableAllModelListeners() {
        for (Map.Entry<CorpTable, TableModelListener> entry : modelListeners.entrySet()) {
            TableValidationListener listener = (TableValidationListener) entry.getValue();
            listener.disableValidation();
        }
    }
    private void disableModelListener(CorpTable table) {
        // Obtener el listener del Map usando la tabla como clave
        TableValidationListener listener = (TableValidationListener) modelListeners.get(table);

        if (listener != null) {
            listener.disableValidation();
        }
    }

    private void enableAllModelListeners() {
        for (Map.Entry<CorpTable, TableModelListener> entry : modelListeners.entrySet()) {
            TableValidationListener listener = (TableValidationListener) entry.getValue();
            listener.enableValidation();
        }
    }
    private void enableModelListener(CorpTable table) {
        // Obtener el listener del Map usando la tabla como clave
        TableValidationListener listener = (TableValidationListener) modelListeners.get(table);

        if (listener != null) {
            listener.enableValidation();
        }
    }

    private void processTableDataToCreate(CorpTable table, List<AccountSubclassDTO> subclassesList, int modelId, int accountId)
            throws IncompleteDataException {
        LCTableModel model = (LCTableModel) table.TableData().getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            Object subclassId = model.getValueAt(row, 0);
            Object subclassCode = model.getValueAt(row, 1);
            Object subclassDescription = model.getValueAt(row, 2);

            boolean isIdEmpty = subclassId == null || subclassId.toString().trim().isEmpty();
            boolean isCodeEmpty = subclassCode == null || subclassCode.toString().trim().isEmpty();
            boolean isDescriptionEmpty = subclassDescription == null || subclassDescription.toString().trim().isEmpty();

            // Si hay datos incompletos, lanzar la excepción
            if (isCodeEmpty ^ isDescriptionEmpty) {
                throw new IncompleteDataException("Incomplete data at row " + (row + 1)
                        + " in table: " + table.getName() + ". Both code and description must be filled.");
            }

            // Si ambos están completos, los agregamos a la lista
            if (isIdEmpty && !isCodeEmpty && !isDescriptionEmpty) {
                int subclassCodes = Integer.parseInt(subclassCode.toString());
                String subclassDescriptions = subclassDescription.toString().trim();
                subclassesList.add(new AccountSubclassDTO(modelId, accountId, -1, subclassCodes, subclassDescriptions));
            }
        }
    }

    private void processTableDataToChange(CorpTable table, List<AccountSubclassDTO> subclassesList, int modelId, int accountId)
            throws IncompleteDataException {
        LCTableModel model = (LCTableModel) table.TableData().getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            Object subclassId = model.getValueAt(row, 0);
            Object subclassCode = model.getValueAt(row, 1);
            Object subclassDescription = model.getValueAt(row, 2);

            boolean isIdEmpty = subclassId == null || subclassId.toString().trim().isEmpty();
            boolean isCodeEmpty = subclassCode == null || subclassCode.toString().trim().isEmpty();
            boolean isDescriptionEmpty = subclassDescription == null || subclassDescription.toString().trim().isEmpty();

            // Si hay datos incompletos, lanzar la excepción
            if (isCodeEmpty ^ isDescriptionEmpty) {
                throw new IncompleteDataException("Incomplete data at row " + (row + 1)
                        + " in table: " + table.getName() + ". Both code and description must be filled.");
            }

            // Si ambos están completos, los agregamos a la lista
            if (!isIdEmpty && !isCodeEmpty && !isDescriptionEmpty) {
                int subclassIds = Integer.parseInt(subclassId.toString());
                int subclassCodes = Integer.parseInt(subclassCode.toString());
                String subclassDescriptions = subclassDescription.toString().trim();
                subclassesList.add(new AccountSubclassDTO(modelId, accountId, subclassIds, subclassCodes, subclassDescriptions));
            }
        }
    }

    private List<AccountSubclassDTO> prepareAccountSubclassesBatchCreate() {
        List<AccountSubclassDTO> subclassesList = new ArrayList<>();
        try {
            processTableDataToCreate(assetsTable, subclassesList, modelId, assetsId);
            processTableDataToCreate(liabilitiesTable, subclassesList, modelId, liabilitiesId);
            processTableDataToCreate(equityTable, subclassesList, modelId, equityId);
            processTableDataToCreate(revenueTable, subclassesList, modelId, revenueId);
            processTableDataToCreate(costsTable, subclassesList, modelId, costsId);
            processTableDataToCreate(expensesTable, subclassesList, modelId, expensesId);

            return subclassesList;
        } catch (IncompleteDataException ex) {
            subclassesList.clear(); // Reiniciar la lista para que el usuario corrija
            notificator.showErrorMsg(ex.getMessage());
        }
        return subclassesList;
    }
    private List<AccountSubclassDTO> prepareAccountSubclassesBatchChange() {
        List<AccountSubclassDTO> subclassesList = new ArrayList<>();
        try {
            processTableDataToChange(assetsTable, subclassesList, modelId, assetsId);
            processTableDataToChange(liabilitiesTable, subclassesList, modelId, liabilitiesId);
            processTableDataToChange(equityTable, subclassesList, modelId, equityId);
            processTableDataToChange(revenueTable, subclassesList, modelId, revenueId);
            processTableDataToChange(costsTable, subclassesList, modelId, costsId);
            processTableDataToChange(expensesTable, subclassesList, modelId, expensesId);

            return subclassesList;
        } catch (IncompleteDataException ex) {
            subclassesList.clear(); // Reiniciar la lista para que el usuario corrija
            notificator.showErrorMsg(ex.getMessage());
        }
        return subclassesList;
    }

    private void setIdInTables(List<AccountSubclassDTO> subclasses, int classId, CorpTable table) {
        LCTableModel model = (LCTableModel) table.TableData().getModel();

        for (AccountSubclassDTO subclass : subclasses) {
            if (subclass.getClassId() == classId) {
                for (int row = 0; row < model.getRowCount(); row++) {
                    Object value = model.getValueAt(row, 1);

                    // Comprobar si el valor de la columna es no nulo y no vacío
                    if (value != null && !value.toString().trim().isEmpty()) {
                        String valueStr = value.toString().trim();

                        // Si el código de la subcuenta coincide, asignar el ID de la subcuenta en la primera columna
                        if (valueStr.equals(String.valueOf(subclass.getSubclassCode()))) {
                            model.setValueAt(subclass.getSubclassId(), row, 0);
                        }

                    }
                }
            }
        }
    }
    
    private void setNewSubclassesId(List<AccountSubclassDTO> subclasses) {
        setIdInTables(subclasses, assetsId, assetsTable);
        setIdInTables(subclasses, liabilitiesId, liabilitiesTable);
        setIdInTables(subclasses, equityId, equityTable);
        setIdInTables(subclasses, revenueId, revenueTable);
        setIdInTables(subclasses, costsId, costsTable);
        setIdInTables(subclasses, expensesId, expensesTable);
    }

    private void sendSubclassesSaveRequest() {
        if (!isDataCompleteInAllTables()) {
            return;
        }
        List<AccountSubclassDTO> newSubclasses = prepareAccountSubclassesBatchCreate();
        List<AccountSubclassDTO> modifiedSubclasses = prepareAccountSubclassesBatchChange();

        try {
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();

            // Enviar las nuevas subcuentas
            if (!newSubclasses.isEmpty()) {
                output.writeObject(new AccountSubclassCreateRequest(sessionId, userId, newSubclasses));
                output.flush();
                Object response = input.readObject();
                if (response instanceof AccountSubclassCreateResponse createResponse) {
                    if(createResponse.isSqlError()){
                        notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                        return;
                    }
                    if(createResponse.wasCreated()){
                        setNewSubclassesId(createResponse.getSubclassesList());
                        notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.CREATED));
                    }
                }
            }

            String state = systemStateTextField.getText().trim();
            if (state.equals(ModelStates.CREATED.name())) {
                // Enviar los cambios en las subcuentas existentes
                if (!modifiedSubclasses.isEmpty()) {
                    output.writeObject(new AccountSubclassChangeRequest(sessionId, userId, modifiedSubclasses));
                    output.flush();
                    Object response = input.readObject();
                    if (response instanceof AccountSubclassChangeResponse changeResponse) {
                        if(changeResponse.isSqlError()){
                            notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                            return;
                        }
                        if(changeResponse.wasUpdated()){
                            notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.UPDATED)); 
                        }else{
                            notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.NOT_UPDATED)); 
                        }
                    }
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
            Logger.getLogger(ManageAccountSubclass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendModelStateChangeRequest(ModelStates nextState){
        if (!isDataSavedInAllTables()) {
            return;
        }
        int resp = JOptionPane.showConfirmDialog(null,
                AppMessages.msg(AppMessages.Key.ARE_YOU_SURE_YOU_WANT_TO_PROCEED),
                AppMessages.msg(AppMessages.Key.CONFIRMATION),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (resp == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();

            output.writeObject(new AccountModelStateChangeRequest(sessionId, userId, modelId, nextState.name()));
            output.flush();
            Object response = input.readObject();

            if (response instanceof AccountModelStateChangeResponse stateChangeResponse) {
                String state = stateChangeResponse.getState();
                if (stateChangeResponse.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return;
                }
                if (stateChangeResponse.wasUpdated()) {
                    systemState = state;
                    systemStateTextField.setText(systemState);
                    notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.UPDATED) + " " + state);
                    changeStateAndApplyToTables(systemState);
                }else{
                    notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.NOT_UPDATED) + " " + state);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.ACTION_COULD_NOT_BE_EXECUTED));
            Logger.getLogger(ManageAccountSubclass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private boolean sendSubclassDeleteRequest(int[] subclasses, int modelId) {
        String sessionId = activeSession.getSessionId();
        int userId = activeSession.getUserId();
        
        try {
            output.writeObject(new AccountSubclassDeleteRequest(sessionId, userId, subclasses, modelId));
            output.flush();
            Object response = input.readObject();
            if(response instanceof AccountSubclassDeleteResponse deleteResponse){
                if(deleteResponse.isSqlError()){
                    notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return false;
                }
                if(deleteResponse.wasDeleted()){
                    notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.DELETED));
                    return true;
                }else{
                    notificator.showSuccessMsg(AppMessages.msg(AppMessages.Key.NOT_DELETED));
                    return false;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ACTION_COULD_NOT_BE_EXECUTED));
        }
        return false;
    }
    
    
    private boolean isTask = true;
    private void setTasking(boolean isTask){
        this.isTask = isTask;
    }
    private ActionListener setSaveButtonListener() {
        return (ActionEvent e) -> {
            sendSubclassesSaveRequest();
        };
    }
    @Override
    public boolean isTaskRunning() {
        return isTask;
    }
    @Override
    public ActionListener getOnTaskComplete() {
        return setSaveButtonListener();
    }

    @Override
    public String getTransactionCode() {
        return transactionCode;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        panelTitle = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        moduleTitleLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        buttonsPanel = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        jToolBar1 = new javax.swing.JToolBar();
        proceedButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        cancelButton = new com.simplecore.erp.client.controllers.servicebuttons.ButtonServices();
        bodyPanel = new corex.suite.JPanelRoundedGradient();
        modelNameLabel = new corex.suite.JLabelHQUnderlined();
        modelNameTextField = new javax.swing.JTextField();
        modelDescriptionLabel = new corex.suite.JLabelHQUnderlined();
        modelDescriptionTextField = new javax.swing.JTextField();
        modelIdLabel = new corex.suite.JLabelHQUnderlined();
        modelIdTextField = new javax.swing.JTextField();
        accountModelTabbedPane = new javax.swing.JTabbedPane();
        scrollAssets = new javax.swing.JScrollPane();
        assetsPanel = new corex.suite.JPanelRoundedGradient();
        fromLb = new corex.suite.JLabelHQUnderlined();
        assetsFromTextField = new javax.swing.JTextField();
        toLb = new corex.suite.JLabelHQUnderlined();
        assetsToTextField = new javax.swing.JTextField();
        assetsTag = new javax.swing.JTextField();
        assetsPasteButton = new javax.swing.JButton();
        assetsClearButton = new javax.swing.JButton();
        assetsTable = new corex.suite.CorpTable();
        assetsDeleteRowButton = new javax.swing.JButton();
        assetsAddRowButton = new javax.swing.JButton();
        assetsPadlockButton = new javax.swing.JButton();
        panelGradient31 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        assetsEditRowButton = new javax.swing.JButton();
        scrollLiabilities = new javax.swing.JScrollPane();
        assetsPanel1 = new corex.suite.JPanelRoundedGradient();
        liabilitiesfromLb = new corex.suite.JLabelHQUnderlined();
        liabilitiesFromTextField = new javax.swing.JTextField();
        toLb1 = new corex.suite.JLabelHQUnderlined();
        liabilitiesToTextField = new javax.swing.JTextField();
        liabilitiesTag = new javax.swing.JTextField();
        liabilitiesPasteButton = new javax.swing.JButton();
        liabilitiesClearButton = new javax.swing.JButton();
        liabilitiesTable = new corex.suite.CorpTable();
        liabilitiesDeleteRowButton = new javax.swing.JButton();
        liabilitiesAddRowButton = new javax.swing.JButton();
        liabilitiesPadlockButton = new javax.swing.JButton();
        panelGradient32 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        liabilitiesEditRowButton = new javax.swing.JButton();
        scrollEquity = new javax.swing.JScrollPane();
        assetsPanel2 = new corex.suite.JPanelRoundedGradient();
        liabilitiesfromLb1 = new corex.suite.JLabelHQUnderlined();
        equityFromTextField = new javax.swing.JTextField();
        toLb2 = new corex.suite.JLabelHQUnderlined();
        equityToTextField = new javax.swing.JTextField();
        equityTag = new javax.swing.JTextField();
        equityPasteButton = new javax.swing.JButton();
        equityClearButton = new javax.swing.JButton();
        equityTable = new corex.suite.CorpTable();
        equityDeleteRowButton = new javax.swing.JButton();
        equityAddRowButton = new javax.swing.JButton();
        equityPadlockButton = new javax.swing.JButton();
        panelGradient33 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        equityEditRowButton = new javax.swing.JButton();
        scrollRevenue = new javax.swing.JScrollPane();
        assetsPanel3 = new corex.suite.JPanelRoundedGradient();
        liabilitiesfromLb2 = new corex.suite.JLabelHQUnderlined();
        revenueFromTextField = new javax.swing.JTextField();
        toLb3 = new corex.suite.JLabelHQUnderlined();
        revenueToTextField = new javax.swing.JTextField();
        revenueTag = new javax.swing.JTextField();
        revenuePasteButton = new javax.swing.JButton();
        revenueClearButton = new javax.swing.JButton();
        revenueTable = new corex.suite.CorpTable();
        revenueDeleteRowButton = new javax.swing.JButton();
        revenueAddRowButton = new javax.swing.JButton();
        revenuePadlockButton = new javax.swing.JButton();
        panelGradient34 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        revenueEditRowButton = new javax.swing.JButton();
        scrollCosts = new javax.swing.JScrollPane();
        assetsPanel4 = new corex.suite.JPanelRoundedGradient();
        liabilitiesfromLb3 = new corex.suite.JLabelHQUnderlined();
        costsFromTextField = new javax.swing.JTextField();
        toLb4 = new corex.suite.JLabelHQUnderlined();
        costsToTextField = new javax.swing.JTextField();
        costsTag = new javax.swing.JTextField();
        costsPasteButton = new javax.swing.JButton();
        costsClearButton = new javax.swing.JButton();
        costsTable = new corex.suite.CorpTable();
        costsDeleteRowButton = new javax.swing.JButton();
        costsAddRowButton = new javax.swing.JButton();
        costsPadlockButton = new javax.swing.JButton();
        panelGradient35 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        costsEditRowButton = new javax.swing.JButton();
        scrollExpenses = new javax.swing.JScrollPane();
        assetsPanel5 = new corex.suite.JPanelRoundedGradient();
        liabilitiesfromLb4 = new corex.suite.JLabelHQUnderlined();
        expensesFromTextField = new javax.swing.JTextField();
        toLb5 = new corex.suite.JLabelHQUnderlined();
        expensesToTextField = new javax.swing.JTextField();
        expensesTag = new javax.swing.JTextField();
        expensesPasteButton = new javax.swing.JButton();
        expensesClearButton = new javax.swing.JButton();
        expensesTable = new corex.suite.CorpTable();
        expensesDeleteRowButton = new javax.swing.JButton();
        expensesAddRowButton = new javax.swing.JButton();
        expensesPadlockButton = new javax.swing.JButton();
        panelGradient36 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        expensesEditRowButton = new javax.swing.JButton();
        systemStateLabel = new corex.suite.JLabelHQUnderlined();
        systemStateTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("Subclasses Accounting Model");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        topPanel.add(panelTitle);

        buttonsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonsPanel.setColor1(new java.awt.Color(206, 223, 239));
        buttonsPanel.setColor2(new java.awt.Color(206, 223, 239));
        buttonsPanel.setMaximumSize(null);
        buttonsPanel.setMinimumSize(new java.awt.Dimension(149, 35));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(149, 35));
        buttonsPanel.setVerifyInputWhenFocusTarget(false);

        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        proceedButton.setFocusable(false);
        proceedButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        proceedButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(proceedButton);

        cancelButton.setFocusable(false);
        cancelButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(cancelButton);

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(903, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        topPanel.add(buttonsPanel);

        add(topPanel, java.awt.BorderLayout.NORTH);

        bodyPanel.setColor1(new java.awt.Color(247, 247, 255));
        bodyPanel.setColor2(new java.awt.Color(239, 243, 247));

        modelNameLabel.setText("Model Name");
        modelNameLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        modelNameTextField.setEditable(false);
        modelNameTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        modelNameTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        modelDescriptionLabel.setText("Description");
        modelDescriptionLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        modelDescriptionTextField.setEditable(false);
        modelDescriptionTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        modelDescriptionTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        modelIdLabel.setText("ID");
        modelIdLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        modelIdTextField.setEditable(false);
        modelIdTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        modelIdTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        accountModelTabbedPane.setBackground(new java.awt.Color(202, 216, 237));
        accountModelTabbedPane.setForeground(new java.awt.Color(102, 102, 102));
        accountModelTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        accountModelTabbedPane.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        assetsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        assetsPanel.setColor1(new java.awt.Color(247, 247, 255));
        assetsPanel.setColor2(new java.awt.Color(247, 247, 255));

        fromLb.setText("From");
        fromLb.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        assetsFromTextField.setEditable(false);

        toLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        toLb.setText("To");
        toLb.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        assetsToTextField.setEditable(false);

        assetsTag.setEditable(false);
        assetsTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        assetsTag.setText("Assets");

        assetsTable.setBackground(new java.awt.Color(146, 178, 193));
        assetsTable.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        assetsTable.setCellNonEditableOneCellSelection(new java.awt.Color(255, 242, 156));
        assetsTable.setCellOneColorOnSelection(new java.awt.Color(255, 200, 43));
        assetsTable.setGridColor(new java.awt.Color(102, 102, 102));
        assetsTable.setNonEditableCellColorOnSelection(new java.awt.Color(255, 242, 156));

        javax.swing.GroupLayout panelGradient31Layout = new javax.swing.GroupLayout(panelGradient31);
        panelGradient31.setLayout(panelGradient31Layout);
        panelGradient31Layout.setHorizontalGroup(
            panelGradient31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGradient31Layout.setVerticalGroup(
            panelGradient31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout assetsPanelLayout = new javax.swing.GroupLayout(assetsPanel);
        assetsPanel.setLayout(assetsPanelLayout);
        assetsPanelLayout.setHorizontalGroup(
            assetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(assetsTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(assetsPanelLayout.createSequentialGroup()
                        .addGroup(assetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(assetsPanelLayout.createSequentialGroup()
                                .addComponent(assetsPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(assetsPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(assetsClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(assetsDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(assetsAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(assetsEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(assetsPanelLayout.createSequentialGroup()
                                .addComponent(assetsTag, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fromLb, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(assetsFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(assetsToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addComponent(panelGradient31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        assetsPanelLayout.setVerticalGroup(
            assetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(assetsTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(assetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(assetsToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(toLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(assetsFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fromLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(assetsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(assetsPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assetsClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assetsDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assetsAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assetsPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assetsEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGradient31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(assetsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrollAssets.setViewportView(assetsPanel);

        accountModelTabbedPane.addTab("Assets", scrollAssets);

        assetsPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        assetsPanel1.setColor1(new java.awt.Color(247, 247, 255));
        assetsPanel1.setColor2(new java.awt.Color(247, 247, 255));

        liabilitiesfromLb.setText("From");
        liabilitiesfromLb.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        liabilitiesFromTextField.setEditable(false);

        toLb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        toLb1.setText("To");
        toLb1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        liabilitiesToTextField.setEditable(false);

        liabilitiesTag.setEditable(false);
        liabilitiesTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        liabilitiesTag.setText("Assets");

        liabilitiesTable.setBackground(new java.awt.Color(146, 178, 193));
        liabilitiesTable.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        liabilitiesTable.setCellNonEditableOneCellSelection(new java.awt.Color(255, 242, 156));
        liabilitiesTable.setCellOneColorOnSelection(new java.awt.Color(255, 200, 43));
        liabilitiesTable.setGridColor(new java.awt.Color(102, 102, 102));
        liabilitiesTable.setNonEditableCellColorOnSelection(new java.awt.Color(255, 242, 156));

        javax.swing.GroupLayout panelGradient32Layout = new javax.swing.GroupLayout(panelGradient32);
        panelGradient32.setLayout(panelGradient32Layout);
        panelGradient32Layout.setHorizontalGroup(
            panelGradient32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGradient32Layout.setVerticalGroup(
            panelGradient32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout assetsPanel1Layout = new javax.swing.GroupLayout(assetsPanel1);
        assetsPanel1.setLayout(assetsPanel1Layout);
        assetsPanel1Layout.setHorizontalGroup(
            assetsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(liabilitiesTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(assetsPanel1Layout.createSequentialGroup()
                        .addGroup(assetsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(assetsPanel1Layout.createSequentialGroup()
                                .addComponent(liabilitiesPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(liabilitiesPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(liabilitiesClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(liabilitiesDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(liabilitiesAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(liabilitiesEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(assetsPanel1Layout.createSequentialGroup()
                                .addComponent(liabilitiesTag, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(liabilitiesfromLb, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(liabilitiesFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(liabilitiesToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addComponent(panelGradient32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        assetsPanel1Layout.setVerticalGroup(
            assetsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(liabilitiesTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(assetsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(liabilitiesToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(toLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(liabilitiesFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(liabilitiesfromLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(assetsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(liabilitiesPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liabilitiesClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liabilitiesDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liabilitiesAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liabilitiesPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(liabilitiesEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGradient32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(liabilitiesTable, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrollLiabilities.setViewportView(assetsPanel1);

        accountModelTabbedPane.addTab("Liabilities", scrollLiabilities);

        assetsPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        assetsPanel2.setColor1(new java.awt.Color(247, 247, 255));
        assetsPanel2.setColor2(new java.awt.Color(247, 247, 255));

        liabilitiesfromLb1.setText("From");
        liabilitiesfromLb1.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        equityFromTextField.setEditable(false);

        toLb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        toLb2.setText("To");
        toLb2.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        equityToTextField.setEditable(false);

        equityTag.setEditable(false);
        equityTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        equityTag.setText("Assets");

        equityTable.setBackground(new java.awt.Color(146, 178, 193));
        equityTable.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        equityTable.setCellNonEditableOneCellSelection(new java.awt.Color(255, 242, 156));
        equityTable.setCellOneColorOnSelection(new java.awt.Color(255, 200, 43));
        equityTable.setGridColor(new java.awt.Color(102, 102, 102));
        equityTable.setNonEditableCellColorOnSelection(new java.awt.Color(255, 242, 156));

        javax.swing.GroupLayout panelGradient33Layout = new javax.swing.GroupLayout(panelGradient33);
        panelGradient33.setLayout(panelGradient33Layout);
        panelGradient33Layout.setHorizontalGroup(
            panelGradient33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGradient33Layout.setVerticalGroup(
            panelGradient33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout assetsPanel2Layout = new javax.swing.GroupLayout(assetsPanel2);
        assetsPanel2.setLayout(assetsPanel2Layout);
        assetsPanel2Layout.setHorizontalGroup(
            assetsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(equityTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(assetsPanel2Layout.createSequentialGroup()
                        .addGroup(assetsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(assetsPanel2Layout.createSequentialGroup()
                                .addComponent(equityPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(equityPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(equityClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(equityDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(equityAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(equityEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(assetsPanel2Layout.createSequentialGroup()
                                .addComponent(equityTag, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(liabilitiesfromLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(equityFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(equityToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addComponent(panelGradient33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        assetsPanel2Layout.setVerticalGroup(
            assetsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(equityTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(assetsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(equityToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(toLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(equityFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(liabilitiesfromLb1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(assetsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(equityPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equityClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equityDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equityAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equityPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equityEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGradient33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(equityTable, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrollEquity.setViewportView(assetsPanel2);

        accountModelTabbedPane.addTab("Equity", scrollEquity);

        assetsPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        assetsPanel3.setColor1(new java.awt.Color(247, 247, 255));
        assetsPanel3.setColor2(new java.awt.Color(247, 247, 255));

        liabilitiesfromLb2.setText("From");
        liabilitiesfromLb2.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        revenueFromTextField.setEditable(false);

        toLb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        toLb3.setText("To");
        toLb3.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        revenueToTextField.setEditable(false);

        revenueTag.setEditable(false);
        revenueTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        revenueTag.setText("Assets");

        revenueTable.setBackground(new java.awt.Color(146, 178, 193));
        revenueTable.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        revenueTable.setCellNonEditableOneCellSelection(new java.awt.Color(255, 242, 156));
        revenueTable.setCellOneColorOnSelection(new java.awt.Color(255, 200, 43));
        revenueTable.setGridColor(new java.awt.Color(102, 102, 102));
        revenueTable.setNonEditableCellColorOnSelection(new java.awt.Color(255, 242, 156));

        javax.swing.GroupLayout panelGradient34Layout = new javax.swing.GroupLayout(panelGradient34);
        panelGradient34.setLayout(panelGradient34Layout);
        panelGradient34Layout.setHorizontalGroup(
            panelGradient34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGradient34Layout.setVerticalGroup(
            panelGradient34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout assetsPanel3Layout = new javax.swing.GroupLayout(assetsPanel3);
        assetsPanel3.setLayout(assetsPanel3Layout);
        assetsPanel3Layout.setHorizontalGroup(
            assetsPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(revenueTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(assetsPanel3Layout.createSequentialGroup()
                        .addGroup(assetsPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(assetsPanel3Layout.createSequentialGroup()
                                .addComponent(revenuePadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(revenuePasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(revenueClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(revenueDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(revenueAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(revenueEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(assetsPanel3Layout.createSequentialGroup()
                                .addComponent(revenueTag, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(liabilitiesfromLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(revenueFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(revenueToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addComponent(panelGradient34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        assetsPanel3Layout.setVerticalGroup(
            assetsPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(revenueTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(assetsPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(revenueToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(toLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(revenueFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(liabilitiesfromLb2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(assetsPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(revenuePasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenueClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenueDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenueAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenuePadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(revenueEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGradient34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(revenueTable, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrollRevenue.setViewportView(assetsPanel3);

        accountModelTabbedPane.addTab("Revenue", scrollRevenue);

        assetsPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        assetsPanel4.setColor1(new java.awt.Color(247, 247, 255));
        assetsPanel4.setColor2(new java.awt.Color(247, 247, 255));

        liabilitiesfromLb3.setText("From");
        liabilitiesfromLb3.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costsFromTextField.setEditable(false);

        toLb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        toLb4.setText("To");
        toLb4.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        costsToTextField.setEditable(false);

        costsTag.setEditable(false);
        costsTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        costsTag.setText("Assets");

        costsTable.setBackground(new java.awt.Color(146, 178, 193));
        costsTable.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        costsTable.setCellNonEditableOneCellSelection(new java.awt.Color(255, 242, 156));
        costsTable.setCellOneColorOnSelection(new java.awt.Color(255, 200, 43));
        costsTable.setGridColor(new java.awt.Color(102, 102, 102));
        costsTable.setNonEditableCellColorOnSelection(new java.awt.Color(255, 242, 156));

        javax.swing.GroupLayout panelGradient35Layout = new javax.swing.GroupLayout(panelGradient35);
        panelGradient35.setLayout(panelGradient35Layout);
        panelGradient35Layout.setHorizontalGroup(
            panelGradient35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGradient35Layout.setVerticalGroup(
            panelGradient35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout assetsPanel4Layout = new javax.swing.GroupLayout(assetsPanel4);
        assetsPanel4.setLayout(assetsPanel4Layout);
        assetsPanel4Layout.setHorizontalGroup(
            assetsPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costsTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(assetsPanel4Layout.createSequentialGroup()
                        .addGroup(assetsPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(assetsPanel4Layout.createSequentialGroup()
                                .addComponent(costsPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(costsPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(costsClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(costsDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(costsAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(costsEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(assetsPanel4Layout.createSequentialGroup()
                                .addComponent(costsTag, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(liabilitiesfromLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(costsFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(costsToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addComponent(panelGradient35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        assetsPanel4Layout.setVerticalGroup(
            assetsPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costsTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(assetsPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(costsToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(toLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(costsFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(liabilitiesfromLb3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(assetsPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(costsPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costsClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costsDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costsAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costsPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costsEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGradient35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(costsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrollCosts.setViewportView(assetsPanel4);

        accountModelTabbedPane.addTab("Costs", scrollCosts);

        assetsPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192)));
        assetsPanel5.setColor1(new java.awt.Color(247, 247, 255));
        assetsPanel5.setColor2(new java.awt.Color(247, 247, 255));

        liabilitiesfromLb4.setText("From");
        liabilitiesfromLb4.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        expensesFromTextField.setEditable(false);

        toLb5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        toLb5.setText("To");
        toLb5.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        expensesToTextField.setEditable(false);

        expensesTag.setEditable(false);
        expensesTag.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        expensesTag.setText("Assets");

        expensesTable.setBackground(new java.awt.Color(146, 178, 193));
        expensesTable.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        expensesTable.setCellNonEditableOneCellSelection(new java.awt.Color(255, 242, 156));
        expensesTable.setCellOneColorOnSelection(new java.awt.Color(255, 200, 43));
        expensesTable.setGridColor(new java.awt.Color(102, 102, 102));
        expensesTable.setNonEditableCellColorOnSelection(new java.awt.Color(255, 242, 156));

        javax.swing.GroupLayout panelGradient36Layout = new javax.swing.GroupLayout(panelGradient36);
        panelGradient36.setLayout(panelGradient36Layout);
        panelGradient36Layout.setHorizontalGroup(
            panelGradient36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGradient36Layout.setVerticalGroup(
            panelGradient36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout assetsPanel5Layout = new javax.swing.GroupLayout(assetsPanel5);
        assetsPanel5.setLayout(assetsPanel5Layout);
        assetsPanel5Layout.setHorizontalGroup(
            assetsPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(expensesTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(assetsPanel5Layout.createSequentialGroup()
                        .addGroup(assetsPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(assetsPanel5Layout.createSequentialGroup()
                                .addComponent(expensesPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(expensesPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(expensesClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(expensesDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(expensesAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(expensesEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(assetsPanel5Layout.createSequentialGroup()
                                .addComponent(expensesTag, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(liabilitiesfromLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(expensesFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(toLb5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(expensesToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addComponent(panelGradient36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        assetsPanel5Layout.setVerticalGroup(
            assetsPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(assetsPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(expensesTag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(assetsPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(expensesToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(toLb5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(expensesFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(liabilitiesfromLb4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(assetsPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(expensesPasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensesClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensesDeleteRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensesAddRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensesPadlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expensesEditRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGradient36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(expensesTable, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        scrollExpenses.setViewportView(assetsPanel5);

        accountModelTabbedPane.addTab("Expenses", scrollExpenses);

        systemStateLabel.setText("System State");
        systemStateLabel.setFont(new java.awt.Font("JetBrains Mono Light", 0, 12)); // NOI18N

        systemStateTextField.setEditable(false);
        systemStateTextField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        systemStateTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(modelDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(modelNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modelDescriptionTextField)
                            .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addComponent(modelNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modelIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(modelIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(systemStateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(systemStateTextField))))
                    .addComponent(accountModelTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(354, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(modelNameTextField)
                        .addComponent(modelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(systemStateTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(systemStateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(modelIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(modelIdTextField)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(modelDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(accountModelTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTabbedPane accountModelTabbedPane;
    private javax.swing.JButton assetsAddRowButton;
    private javax.swing.JButton assetsClearButton;
    private javax.swing.JButton assetsDeleteRowButton;
    private javax.swing.JButton assetsEditRowButton;
    private javax.swing.JTextField assetsFromTextField;
    private javax.swing.JButton assetsPadlockButton;
    private corex.suite.JPanelRoundedGradient assetsPanel;
    private corex.suite.JPanelRoundedGradient assetsPanel1;
    private corex.suite.JPanelRoundedGradient assetsPanel2;
    private corex.suite.JPanelRoundedGradient assetsPanel3;
    private corex.suite.JPanelRoundedGradient assetsPanel4;
    private corex.suite.JPanelRoundedGradient assetsPanel5;
    private javax.swing.JButton assetsPasteButton;
    private corex.suite.CorpTable assetsTable;
    private javax.swing.JTextField assetsTag;
    private javax.swing.JTextField assetsToTextField;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices cancelButton;
    private javax.swing.JButton costsAddRowButton;
    private javax.swing.JButton costsClearButton;
    private javax.swing.JButton costsDeleteRowButton;
    private javax.swing.JButton costsEditRowButton;
    private javax.swing.JTextField costsFromTextField;
    private javax.swing.JButton costsPadlockButton;
    private javax.swing.JButton costsPasteButton;
    private corex.suite.CorpTable costsTable;
    private javax.swing.JTextField costsTag;
    private javax.swing.JTextField costsToTextField;
    private javax.swing.JButton equityAddRowButton;
    private javax.swing.JButton equityClearButton;
    private javax.swing.JButton equityDeleteRowButton;
    private javax.swing.JButton equityEditRowButton;
    private javax.swing.JTextField equityFromTextField;
    private javax.swing.JButton equityPadlockButton;
    private javax.swing.JButton equityPasteButton;
    private corex.suite.CorpTable equityTable;
    private javax.swing.JTextField equityTag;
    private javax.swing.JTextField equityToTextField;
    private javax.swing.JButton expensesAddRowButton;
    private javax.swing.JButton expensesClearButton;
    private javax.swing.JButton expensesDeleteRowButton;
    private javax.swing.JButton expensesEditRowButton;
    private javax.swing.JTextField expensesFromTextField;
    private javax.swing.JButton expensesPadlockButton;
    private javax.swing.JButton expensesPasteButton;
    private corex.suite.CorpTable expensesTable;
    private javax.swing.JTextField expensesTag;
    private javax.swing.JTextField expensesToTextField;
    private corex.suite.JLabelHQUnderlined fromLb;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton liabilitiesAddRowButton;
    private javax.swing.JButton liabilitiesClearButton;
    private javax.swing.JButton liabilitiesDeleteRowButton;
    private javax.swing.JButton liabilitiesEditRowButton;
    private javax.swing.JTextField liabilitiesFromTextField;
    private javax.swing.JButton liabilitiesPadlockButton;
    private javax.swing.JButton liabilitiesPasteButton;
    private corex.suite.CorpTable liabilitiesTable;
    private javax.swing.JTextField liabilitiesTag;
    private javax.swing.JTextField liabilitiesToTextField;
    private corex.suite.JLabelHQUnderlined liabilitiesfromLb;
    private corex.suite.JLabelHQUnderlined liabilitiesfromLb1;
    private corex.suite.JLabelHQUnderlined liabilitiesfromLb2;
    private corex.suite.JLabelHQUnderlined liabilitiesfromLb3;
    private corex.suite.JLabelHQUnderlined liabilitiesfromLb4;
    private corex.suite.JLabelHQUnderlined modelDescriptionLabel;
    private javax.swing.JTextField modelDescriptionTextField;
    private corex.suite.JLabelHQUnderlined modelIdLabel;
    private javax.swing.JTextField modelIdTextField;
    private corex.suite.JLabelHQUnderlined modelNameLabel;
    private javax.swing.JTextField modelNameTextField;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient31;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient32;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient33;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient34;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient35;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient36;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JButton revenueAddRowButton;
    private javax.swing.JButton revenueClearButton;
    private javax.swing.JButton revenueDeleteRowButton;
    private javax.swing.JButton revenueEditRowButton;
    private javax.swing.JTextField revenueFromTextField;
    private javax.swing.JButton revenuePadlockButton;
    private javax.swing.JButton revenuePasteButton;
    private corex.suite.CorpTable revenueTable;
    private javax.swing.JTextField revenueTag;
    private javax.swing.JTextField revenueToTextField;
    private javax.swing.JScrollPane scrollAssets;
    private javax.swing.JScrollPane scrollCosts;
    private javax.swing.JScrollPane scrollEquity;
    private javax.swing.JScrollPane scrollExpenses;
    private javax.swing.JScrollPane scrollLiabilities;
    private javax.swing.JScrollPane scrollRevenue;
    private corex.suite.JLabelHQUnderlined systemStateLabel;
    private javax.swing.JTextField systemStateTextField;
    private corex.suite.JLabelHQUnderlined toLb;
    private corex.suite.JLabelHQUnderlined toLb1;
    private corex.suite.JLabelHQUnderlined toLb2;
    private corex.suite.JLabelHQUnderlined toLb3;
    private corex.suite.JLabelHQUnderlined toLb4;
    private corex.suite.JLabelHQUnderlined toLb5;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public FormState getFormState() {
        return null;
    }
}
