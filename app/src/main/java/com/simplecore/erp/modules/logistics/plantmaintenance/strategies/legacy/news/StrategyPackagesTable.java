package com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.text.AbstractDocument;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.components.tables.interfaces.TableButtonListener;
import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.documentfilters.DocumentFilterNumeric;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.UnitsOfMeasurement;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class StrategyPackagesTable extends javax.swing.JPanel {

    public StrategyPackagesTable() {

        initComponents();
        addEvents();

    }

    private void addEvents() {
        exitButton();
        saveButton();
        setTableModel();
    }


    public JTextField getStrategyTF(){
        return estrategyTB;
    }
    public JTextField getStrategyDescriptionTB(){
        return strategyDescriptionTB;
    }
    
    
    private void setTableModel() {

        String[] identifiers_ES = {null,
            "N° Paquete",
            "Duración ciclo",
            "Unidad",
            "Descripción und.",
            "Texto ciclo mantenimiento",
            "Texto breve ciclo",
            "Jerarquía",
            "Texto breve jerarquía",
            "Offset"
        };
        String[] identifiers_EN = {null,
            "Package N°",
            "Cycle duration",
            "Unit",
            "Unit description",
            "Maintenance cycle text",
            "Short cycle text",
            "Hierarchy",
            "Short hierarchy text",
            "Offset"};
        
            String[] columns = null;
            
                columns = identifiers_EN;
            
            DynamicTableModel model = new DynamicTableModel(100, columns);
            
            packagesTable.setModel(model);
            packagesTable.setSelectedRowsList(selectedRows);
            
            inputTypesTable();
            valueInputControl();
            shortTextCycleControl();
            shortTextHierarchyControl();
            clearValueOnCancelEdit();
            
            resetSelection();
            avoidSelectColumn0();
            setTableButtonListener();
            setColumnsWidths();
    
    }
    private void resetSelection() {
        packagesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (packagesTable.columnAtPoint(e.getPoint()) != 0) {
                    selectedRows.clear();
                }
            }
        });
    }
    private void avoidSelectColumn0() {
        packagesTable.getColumnModel().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedColumn = packagesTable.getSelectedColumn();
                if (selectedColumn == 0) {
                    // Si se selecciona la columna 0, cambiamos la selección a la columna 1
                    packagesTable.changeSelection(packagesTable.getSelectedRow(), 1, false, false);
                }
            }
        });
    }
    ArrayList<Integer> selectedRows = new ArrayList<>();

    private void setTableButtonListener() {
        packagesTable.addTableButtonListener(new TableButtonListener() {
            @Override
            public void actionPerformed(int row) {
                if (selectedRows.contains(row)) {
                    selectedRows.remove(Integer.valueOf(row));
                } else {
                    selectedRows.add(row);
                }
                packagesTable.clearSelection();
                for (int selectedRow : selectedRows) {
                    packagesTable.addRowSelectionInterval(selectedRow, selectedRow);
                    packagesTable.addColumnSelectionInterval(1, packagesTable.getColumnCount() - 1);
                }
                packagesTable.getDefaultEditor(Object.class).stopCellEditing();
            }
        });
    }

    private void setColumnsWidths() {
        packagesTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        packagesTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        packagesTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        packagesTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        packagesTable.getColumnModel().getColumn(5).setPreferredWidth(200);
        packagesTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        packagesTable.getColumnModel().getColumn(7).setPreferredWidth(50);
        packagesTable.getColumnModel().getColumn(8).setPreferredWidth(100);
        packagesTable.getColumnModel().getColumn(9).setPreferredWidth(200);
    }
    private void clearValueOnCancelEdit() {
        packagesTable.getDefaultEditor(Object.class).addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {

            }
            @Override
            public void editingCanceled(ChangeEvent e) {
                int row = packagesTable.getEditingRow();
                int col = packagesTable.getEditingColumn();

                if (row != -1 && col != -1) {
                    packagesTable.getModel().setValueAt(null, row, col);
                }
            }
        });
    }
    
    private void inputTypesTable() {

        packagesTable.getColumnModel().getColumn(5).setCellEditor(new LimitedAlphanumericCellEditor(45));
        packagesTable.getColumnModel().getColumn(6).setCellEditor(new LimitedAlphanumericCellEditor(2));
        packagesTable.getColumnModel().getColumn(8).setCellEditor(new LimitedAlphanumericCellEditor(2));
        packagesTable.getColumnModel().getColumn(9).setCellEditor(new LimitedNumericCellEditor(3));
        
    }
    private boolean isUpdating = false;
    private void valueInputControl() {

        JTextField jt = new JTextField();
        ((AbstractDocument)jt.getDocument()).setDocumentFilter(new DocumentFilterNumeric(6));
        packagesTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jt));

        packagesTable.getModel().addTableModelListener((TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.UPDATE && !isUpdating) {

                int row = e.getFirstRow();
                int column = e.getColumn();

                if (column == 2) {

                    Object valueStr = packagesTable.getModel().getValueAt(row, column);

                    if(valueStr!=null && !valueStr.toString().trim().isEmpty()){
                        int value = Integer.parseInt(valueStr.toString().trim());
                        if (!isValueValid((DynamicTableModel)packagesTable.getModel(), value, row)) {
                            if (packagesTable.getModel().getValueAt(row, 1) == null) {
                                new SystemMessages(NOT.msg(NOT.INVALID_VALUE), TypeMessage.ERROR);
                                isUpdating = true;
                                packagesTable.setValueAt(null, row, column);
                                removeErasedRow( (DynamicTableModel)packagesTable.getModel(), row);
                                reassignItemNumber((DynamicTableModel) packagesTable.getModel());
                                isUpdating = false;
                                return;
                            }
                            return;
                        }
                        isUpdating = true;
                        moveToFirstEmptyRow((DynamicTableModel) packagesTable.getModel(), value, row);
                        reassignItemNumber((DynamicTableModel) packagesTable.getModel());
                        isUpdating = false;
                    }else if(valueStr.toString().trim().isEmpty()){
                        removeErasedRow((DynamicTableModel) packagesTable.getModel(),row);
                        isUpdating = true;
                        reassignItemNumber((DynamicTableModel) packagesTable.getModel());
                        isUpdating = false;
                    }
                }

            }
        });

    }
    
    
    private static boolean isValueValid(DynamicTableModel model, int value, int currentRow) {
        Integer maxValue = null;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i == currentRow) {
                continue;
            }
       
            Object valueStr = model.getValueAt(i, 2);

            if (valueStr != null) {
                
                String valueStr1 = String.valueOf(valueStr).trim();
                
                if (!valueStr1.isEmpty()) {

                    int currentValue = Integer.parseInt(valueStr1);
                    if (maxValue == null || currentValue > maxValue) {
                        maxValue = currentValue;
                    }
                }
            }

        }
        return maxValue == null || value > maxValue;
    }
    private static void reassignItemNumber(DynamicTableModel model) {

        int index = 1;
        for (int i = 0; i < model.getRowCount(); i++) {
            Object valueStr = model.getValueAt(i, 2);

            if (valueStr != null) {
                String valueStr1 = String.valueOf(model.getValueAt(i, 2));
                if (valueStr1 != null && !valueStr1.trim().isEmpty()) {
                    model.setValueAt(index, i, 1);
                    model.setValueAt(1, i, 7);
                    index++;
                }
            }else{
                model.setValueAt(null, i, 1);
                model.setValueAt(null, i, 7);
            }
        }
    }
    private static void moveToFirstEmptyRow(DynamicTableModel model, int value, int currentRow) {
        
        int firstEmptyRow = -1;

        for (int i = 0; i < model.getRowCount(); i++) {

            Object valueStr = model.getValueAt(i, 2);

            if (valueStr == null) {
                firstEmptyRow = i;
                break;
            } else {
                
                if (valueStr.toString().isEmpty()) {
                    firstEmptyRow = i;
                    break;
                }
            }
        }

        if (firstEmptyRow != -1 && firstEmptyRow < currentRow) {
            model.setValueAt(value, firstEmptyRow, 2);
            model.setValueAt(null, currentRow, 2);
        }
    }
    private static void removeErasedRow(DynamicTableModel model, int row) {
        
        model.removeRow(row);
        
        String sUnit = null;
        String sUDesc = null;
        if(row>0){
            sUnit = (String) model.getValueAt(row-1, 3);
            sUDesc = (String) model.getValueAt(row-1, 4);
        }else{
            sUnit = (String) model.getValueAt(row+1, 3);
            sUDesc = (String) model.getValueAt(row+1, 4);
        }
        
        model.addRow(new Object[]{null,null, null, sUnit, sUDesc,
             null, null, null, null,null});
        
    }

    
    private boolean isUpdatingSTC = false;
    private void shortTextCycleControl() {
        packagesTable.getModel().addTableModelListener((TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.UPDATE && !isUpdatingSTC) {
               
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (column == 6) {
                    Object valueStr = packagesTable.getModel().getValueAt(row, column);
                    if (valueStr != null) {
                        String val = valueStr.toString().trim();
                        if (!val.isEmpty() && isTextCycleDuplicated((DynamicTableModel) packagesTable.getModel(), val, row)) {
                            isUpdatingSTC = true;
                            try {
                                new SystemMessages(NOT.msg(NOT.DUPLICATE_VALUE), TypeMessage.ERROR);
                                packagesTable.setValueAt(null, row, column);
                            } finally {
                                isUpdatingSTC = false;
                            }
                        }
                    }
                }
            }
        });
    }
    private static boolean isTextCycleDuplicated(DynamicTableModel model, String value, int currentRow) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i == currentRow) {
                continue;
            }
            Object cellValue = model.getValueAt(i, 6);
            if (cellValue != null && !cellValue.toString().trim().isEmpty()) {
                if (value.equals(cellValue.toString().trim())) {
                    return true; 
                }
            }
        }
        return false;
    }

    
    private boolean isUpdatingSTH = false;
    private void shortTextHierarchyControl() {
        packagesTable.getModel().addTableModelListener((TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.UPDATE && !isUpdatingSTH) {

                int row = e.getFirstRow();
                int column = e.getColumn();

                if (column == 8) {
                    Object valueStr = packagesTable.getModel().getValueAt(row, column);
                    if (valueStr != null) {
                        String val = valueStr.toString().trim();
                        if (!val.isEmpty() && isTextHierarchyDuplicated((DynamicTableModel) packagesTable.getModel(), val, row)) {
                            isUpdatingSTH = true;
                            try {
                                new SystemMessages(NOT.msg(NOT.DUPLICATE_VALUE), TypeMessage.ERROR);
                                packagesTable.setValueAt(null, row, column);
                            } finally {
                                isUpdatingSTH = false;
                            }
                        }
                    }
                }
            }
        });
    }
    private static boolean isTextHierarchyDuplicated(DynamicTableModel model, String value, int currentRow) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i == currentRow) {
                continue;
            }
            Object cellValue = model.getValueAt(i, 8);
            if (cellValue != null && !cellValue.toString().trim().isEmpty()) {
                if (value.equals(cellValue.toString().trim())) {
                    return true;
                }
            }
        }
        return false;
    }


    public void setStrategyUnit(String strategyUnit){
    
        this.strategyUnit = strategyUnit;
        String description = UnitsOfMeasurement.getDescriptionByCode(strategyUnit);

        for(int i = 0; i < packagesTable.getRowCount();i++){
            
            packagesTable.setValueAt(strategyUnit, i, 3);
            packagesTable.setValueAt(description, i, 4);

        }

    }

    public void disableEditingForReservedFields() {
        for (int i = 0; i < packagesTable.getRowCount(); i++) {
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 1, false);
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 3, false);
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 4, false);
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 7, false);
        }
    }


    public String getStrategyCode() {
        return strategyCode;
    }
    public String getStrategyDescription() {
        return strategyDescription;
    }
    public String getStrategyUnit() {
        return strategyUnit;
    }
    public int getApertureHorizon() {
        return apertureHorizon;
    }
    public int getDelayFactorDelayedConclusion() {
        return delayFactorDelayedConclusion;
    }
    public int getToleranceOnLateCompletion() {
        return toleranceOnLateCompletion;
    }
    public int getDelayFactorEarlyConclusion() {
        return delayFactorEarlyConclusion;
    }
    public int getToleranceOnEarlyCompletion() {
        return toleranceOnEarlyCompletion;
    }
    
    public void setStrategyCode(String strategyCode) {
        this.strategyCode = strategyCode;
    }
    public void setStrategyDescription(String strategyDescription) {
        this.strategyDescription = strategyDescription;
    }
    public void setApertureHorizon(int apertureHorizon) {
        this.apertureHorizon = apertureHorizon;
    }
    public void setDelayFactorDelayedConclusion(int delayFactorDelayedConclusion) {
        this.delayFactorDelayedConclusion = delayFactorDelayedConclusion;
    }
    public void setToleranceOnLateCompletion(int toleranceOnLateCompletion) {
        this.toleranceOnLateCompletion = toleranceOnLateCompletion;
    }
    public void setDelayFactorEarlyConclusion(int delayFactorEarlyConclusion) {
        this.delayFactorEarlyConclusion = delayFactorEarlyConclusion;
    }
    public void setToleranceOnEarlyCompletion(int toleranceOnEarlyCompletion) {
        this.toleranceOnEarlyCompletion = toleranceOnEarlyCompletion;
    }


    
    //cabecera de la estrategia
    private String strategyCode;
    private String strategyDescription;
    private String strategyUnit;
    private int apertureHorizon;
    private int delayFactorDelayedConclusion;
    private int toleranceOnLateCompletion;
    private int delayFactorEarlyConclusion;
    private int toleranceOnEarlyCompletion;
    
    enum Task {
        CREATE,
        CHANGE,
        VIEW
    }
    private Task typeTask;

    public void setTaskType(Task type) {
        this.typeTask = type;
    }
 
    private void saveButton() {
        saveButton.addActionListener(e -> {

            saveData((DynamicTableModel) packagesTable.getModel());

        });
    }
    private void saveData(DynamicTableModel model){

        for (int i = 0; i < model.getRowCount(); i++) {


            if (model.getValueAt(i, 1)!=null && model.getValueAt(i, 2)!=null) {

                Object col5 = model.getValueAt(i, 5);
                if (col5 == null || col5.toString().isEmpty()) {
                    new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                    packagesTable.editCellAt(i, 5);
                    
                    return;
                }
                Object col6 = model.getValueAt(i, 6);
                if (col6 == null || col6.toString().isEmpty()) {
                    new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                    packagesTable.editCellAt(i, 6);
                    return;
                }
                Object col7 = model.getValueAt(i, 8);
                if (col7 == null || col7.toString().isEmpty()) {
                    new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                    packagesTable.editCellAt(i, 8);
                    return;
                }
            }
        }

        switch (typeTask) {
            case CREATE -> {
                
                createHeaderStrategySQL();
                createPackagesStrategySQL(model);

                new SystemMessages(NOT.msg(NOT.STRATEGY_CREATED) + " " + getStrategyCode(), TypeMessage.SUCCESS);
                ((StrategyHeader) panelAnterior).exit();
                ((MaintenanceStrategies) ((StrategyHeader) panelAnterior).getPanelAnterior()).loadStrategiesList();
            
            }
            case CHANGE -> {
               
                addNewPackages(model);
                new SystemMessages(NOT.msg(NOT.DATA_SAVED), TypeMessage.SUCCESS);
            
            }
            case VIEW -> {

            }

        }


        
    }

    //These methods creates the header ands the packages list into SQL
    private void createHeaderStrategySQL(){
        
        MaintenanceStrategyHeader header = new MaintenanceStrategyHeader();
        
        header.setStrategyCode(getStrategyCode());
        header.setStrategyDescription(getStrategyDescription());
        header.setStrategyUnit(getStrategyUnit());
        header.setApertureHorizon(getApertureHorizon());
        header.setDelayFactorDelayedConclusion(getDelayFactorDelayedConclusion());
        header.setToleranceOnLateCompletion(getToleranceOnLateCompletion());
        header.setDelayFactorEarlyConclusion(getDelayFactorEarlyConclusion());
        header.setToleranceOnEarlyCompletion(getToleranceOnEarlyCompletion());
        
        header.executeHeaderCreationSQL();
        
        
    }
    private void createPackagesStrategySQL(DynamicTableModel model) {

        for (int i = 0; i < model.getRowCount(); i++) {

            if (isRowFullyFilled(model, i)) {

                Object pn1 = model.getValueAt(i, 1);
                int packageNumber = Integer.parseInt(pn1.toString());

                Object cd = model.getValueAt(i, 2);
                int cycleDuration = Integer.parseInt(cd.toString());

                Object mu = model.getValueAt(i, 3);
                String measUnit = mu.toString();

                Object mc = model.getValueAt(i, 5);
                String maintenanceCycleText = mc.toString();

                Object sc = model.getValueAt(i, 6);
                String shortCycleText = sc.toString();

                Object h = model.getValueAt(i, 7);
                int hierarchy = (int) h;

                Object sh = model.getValueAt(i, 8);
                String shortHierarchyText = sh.toString();

                Object off = model.getValueAt(i, 9);
                int offset = 0;
                if (off != null) {
                    offset = (int) off;
                }

                String id = getStrategyCode() + "-" + packageNumber;

                //crear
                MaintenanceStrategyPackages cms = new MaintenanceStrategyPackages();

                cms.setPackageNumber(packageNumber);
                cms.setCycleDuration(cycleDuration);
                cms.setMeasUnit(measUnit);
                cms.setMaintenanceCycleText(maintenanceCycleText);
                cms.setShortCycleText(shortCycleText);
                cms.setHierarchy(hierarchy);
                cms.setShortHierarchyText(shortHierarchyText);
                cms.setOffset(offset);

                cms.executePackageCreationSQL(getStrategyCode());
                
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 2, false);
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 5, false);
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 6, false);
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 8, false);
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 9, false);
            }

        }

    }
  
    //This method add new packages to the maintenance strategy into SQL
    private void addNewPackages(DynamicTableModel model) {

        for (int i = 0; i < model.getRowCount(); i++) {
            
            if (isRowFullyFilled(model, i)) {

                Object pn = model.getValueAt(i, 1);

                if (!StrategySQLDataRetriever.packageExists(pn.toString(), getStrategyCode())) {

                    Object pn1 = model.getValueAt(i, 1);
                    int packageNumber = Integer.parseInt(pn1.toString());

                    Object cd = model.getValueAt(i, 2);
                    int cycleDuration = Integer.parseInt(cd.toString());

                    Object mu = model.getValueAt(i, 3);
                    String measUnit = mu.toString();

                    Object mc = model.getValueAt(i, 5);
                    String maintenanceCycleText = mc.toString();

                    Object sc = model.getValueAt(i, 6);
                    String shortCycleText = sc.toString();

                    Object h = model.getValueAt(i, 7);
                    int hierarchy = (int) h;

                    Object sh = model.getValueAt(i, 8);
                    String shortHierarchyText = sh.toString();

                    Object off = model.getValueAt(i, 9);
                    int offset = 0;
                    if (off != null) {
                        offset = (int) off;
                    }

                    //crear
                    MaintenanceStrategyPackages cms = new MaintenanceStrategyPackages();
                    cms.setPackageNumber(packageNumber);
                    cms.setCycleDuration(cycleDuration);
                    cms.setMeasUnit(measUnit);
                    cms.setMaintenanceCycleText(maintenanceCycleText);
                    cms.setShortCycleText(shortCycleText);
                    cms.setHierarchy(hierarchy);
                    cms.setShortHierarchyText(shortHierarchyText);
                    cms.setOffset(offset);
                    cms.executePackageCreationSQL(getStrategyCode());

                    ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 2, false);
                    ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 6, false);
                    ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 8, false);
                    ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 9, false);

                } else {

                    //This code changes packages to the maintenance strategy into SQL
                    Object pn1 = model.getValueAt(i, 1);
                    int packageNumber = Integer.parseInt(pn1.toString());

                    Object mc = model.getValueAt(i, 5);
                    String maintenanceCycleText = mc.toString();

                    MaintenanceStrategyPackages cms = new MaintenanceStrategyPackages();
                    cms.setPackageNumber(packageNumber);
                    cms.setMaintenanceCycleText(maintenanceCycleText);
                    cms.executePackadeUpdateSQL(getStrategyCode());



                }
            }
        }
        
        new SystemMessages(NOT.msg(NOT.DATA_SAVED), TypeMessage.SUCCESS);
    }

    private static boolean isRowFullyFilled(DynamicTableModel model, int row) {
        boolean r = true;

        for (int i = 1; i < model.getColumnCount() - 1; i++) {
            Object val = model.getValueAt(row, i);

            if (val == null || val.toString().isEmpty()) {
                r = false;
                break;
            }
        }
        return r;
    }
    
    
    //cargar los paquetes si es una consulta
    public void loadPackagesList() {

        isUpdating = true;

        StrategySQLDataRetriever sr = new StrategySQLDataRetriever();
        sr.loadPackagesData((DynamicTableModel) packagesTable.getModel(), getStrategyCode());
        
        for(int i =0; i<packagesTable.getModel().getRowCount();i++){
            if(packagesTable.getModel().getValueAt(i, 2)!=null){
                
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 2, false);
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 6, false);
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 7, false);
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 8, false);
                ((DynamicTableModel)packagesTable.getModel()).setCellEditable(i, 9, false);
            
            }
        }
        

        isUpdating = false;
    }
    public void disableCellEditing(){
        
        isUpdating = true;
        
        for (int i = 0; i < packagesTable.getModel().getRowCount(); i++) {
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 2, false);
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 5, false);
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 6, false);
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 7, false);
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 8, false);
            ((DynamicTableModel) packagesTable.getModel()).setCellEditable(i, 9, false);
        }
        isUpdating = false;
    }
    
    
    public JButton getSaveButton(){
        return saveButton;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        buttonExit = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        saveButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        measDocNum = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        measDocNum1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        strategyDescriptionTB = new javax.swing.JTextField();
        estrategyTB = new javax.swing.JTextField();
        buttonPosition = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        labelConteoRegistro = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        packagesTable = new com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons();
        buttonPkgSeq = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        buttonExit.setBackground(new java.awt.Color(226, 210, 144));
        buttonExit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        saveButton.setBackground(new java.awt.Color(226, 210, 144));
        saveButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        measDocNum.setText("Strategy");
        measDocNum.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measDocNum1.setText("Description");
        measDocNum1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        strategyDescriptionTB.setEditable(false);
        strategyDescriptionTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        estrategyTB.setEditable(false);
        estrategyTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        buttonPosition.setBackground(new java.awt.Color(226, 210, 144));
        buttonPosition.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonPosition.setText("Position");
        buttonPosition.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        buttonPosition.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelConteoRegistro.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelConteoRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        packagesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        packagesTable.setColorColumnas(new java.awt.Color(255, 255, 255));
        packagesTable.setGridColor(new java.awt.Color(145, 145, 145));
        packagesTable.setShowGrid(true);
        packagesTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(packagesTable);

        buttonPkgSeq.setBackground(new java.awt.Color(226, 210, 144));
        buttonPkgSeq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonPkgSeq.setText("PkgSeq");
        buttonPkgSeq.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        buttonPkgSeq.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(measDocNum, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(measDocNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(estrategyTB, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelFondoLayout.createSequentialGroup()
                                .addComponent(strategyDescriptionTB, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(buttonPkgSeq, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(buttonPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(labelConteoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(measDocNum, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estrategyTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(measDocNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strategyDescriptionTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonPkgSeq, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(buttonPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelConteoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotonera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonExit;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonPkgSeq;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonPosition;
    private javax.swing.JTextField estrategyTB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelConteoRegistro;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measDocNum1;
    private com.simplecore.erp.gui.components.tables.newversions.DynamicTableButtons packagesTable;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.labels.JButtonHQ saveButton;
    private javax.swing.JTextField strategyDescriptionTB;
    // End of variables declaration//GEN-END:variables

    private JPanel panelAnterior;
    public void setPanelAnterior(JPanel panel) {
        panelAnterior = panel;
    }
    
    private void exitButton() {
        buttonExit.addActionListener((e) -> {
            exit();
        });
    }
    private void exit() {
        PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
    }

    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }

}
