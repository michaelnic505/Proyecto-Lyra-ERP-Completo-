package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui;


import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.service.CharacteristicService;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.model.Characteristic;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values.CharacteristicsValuesView;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values.CreateCharacteristicValues;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.Languages;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import com.simplecore.erp.gui.components.tables.interfaces.TableButtonListener;
import com.simplecore.erp.gui.components.tables.interfaces.TableForParametersModel;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import java.util.List;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.ClearTables;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.SearchBoxCellEditor;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.dao.CharacteristicDescriptionDAO;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.dao.CharacteristicRestrictionDAO;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values.DeleteCharacteristicValues;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values.DeleteCharacteristicValuesById;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values.CharacteristicValuesID;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.values.ValueCreated;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.Date_ValuesTable_CellEditor;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.RestrictionsTable_CellEditor;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.ValuesTable_CellRender;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.CharacteristicsOptionsData;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.Class_Type_List;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class CharacteristicsForm extends javax.swing.JPanel {

    private final SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
    private CharacteristicService characteristicService;
    
    
    public CharacteristicsForm() {
        initComponents();
        initEvents();
        startServices();
    }

    private void startServices() {
        characteristicService = new CharacteristicService();
    }

    private void componentsVisibility() {
        basicDataPanel.setVisible(false);
        formatsPanel.setVisible(false);
        valuesAssigmentPanel.setVisible(false);
    }

    private void initEvents() {
        componentsVisibility();
        comboCharGroups();
        comboCharStatus();
        comboCharFormats();
        
        charGroupComboChange();
        charStatusComboChange();
        charFormatsComboChange();
        
        addNewButton();
        textBox_addNewCharacteristic();
        
        updateButton();
        viewButton();
        saveButton();
        deleteButton();
        listCharacteristicsButton();
        
        buttonGroup();
        setDescription();
        tableEvents();
        
        buttonExit();
    }
    private void buttonGroup() {
        ButtonGroup g = new ButtonGroup();
        g.add(singleValueRO);
        g.add(mutiplesValueRO);
    }
    
    private void comboCharGroups() {
        String[]charGroupsList = CharacteristicsOptionsData.getCharGroupDescriptions();
        for(String option:charGroupsList){
            grupoCarCB.addItem(option);
        }
    }
    private void comboCharStatus() {
        String[]charStatusList = CharacteristicsOptionsData.getStatusDescriptions();
        for(String status:charStatusList){
            estatusCarCB.addItem(status);
        }
    }
    private void comboCharFormats() {
        String[] charFormatsList = CharacteristicsOptionsData.getFormatDescriptions();
        for(String format:charFormatsList){
            formatsCarCB.addItem(format);
        }
    }
    
    private String charGroupCode = null;
    private String charStatusCode;
    private String charFormatsCode;

    private void charGroupComboChange() {
        grupoCarCB.addItemListener(e -> {
            charGroupCode = (grupoCarCB.getSelectedItem() != null)
                    ? CharacteristicsOptionsData.getCharGroupCode(grupoCarCB.getSelectedIndex())
                    : null;
        });
    }

    private void charStatusComboChange() {
        estatusCarCB.addItemListener((ItemEvent e) -> {
            charStatusCode = CharacteristicsOptionsData.getCharStatusCode(estatusCarCB.getSelectedIndex());
        });
    }
  
    private void charFormatsComboChange() {
        formatsCarCB.addItemListener(e -> {
            resetFields();

            String selectedFormatCode = formatsCarCB.getSelectedItem() != null ? formatsCarCB.getSelectedItem().toString() : "";
            charFormatsCode = CharacteristicsOptionsData.getCharFormatsCode(formatsCarCB.getSelectedIndex());

            handleCharFormatChange(selectedFormatCode);
        });
    }

    private void resetFields() {
        numberCharacters = 0;
        numberDecimals = 0;
        unitMeasure = null;
        template = null;
        currencySimbolCC = null;

        // Clear the data in tables
        ClearTables.clearData(tablaDescripciones);
        ClearTables.clearData(tablaValores);
        ClearTables.clearData(tablaRestricciones);
    }

    private void handleCharFormatChange(String formatCode) {
        // Disable/Enable based on format
        boolean isNumberFormat = formatCode.equals("NUM");

        intervalsValsAllowedCB.setEnabled(isNumberFormat);
        negativeValsAllowedCB.setEnabled(isNumberFormat);

        switch (formatCode) {
            case "CHAR":
                cf = new CharactersFormat();
                changePanel(cf);
                break;
            case "CURR":
                cff = new CurrencyFormat();
                changePanel(cff);
                break;
            case "DATE":
                df = new DateFormat();
                changePanel(df);
                break;
            case "NUM":
                nf = new NumberFormat();
                changePanel(nf);
                break;
            case "":
                changePanel(emptyPanel);
                break;
        }
    }

    private void setDescription() {
        descripcionChar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String descripcion = descripcionChar.getText().trim(); // Eliminar espacios al inicio y al final

                updateDescriptionInTable(descripcion);
            }
        });
    }

    private void updateDescriptionInTable(String descripcion) {
        // Si la descripción no está vacía, asigna los valores correspondientes
        if (!descripcion.isEmpty()) {
            setTableValues("EN", descripcion);
        } else {
            clearTableValues();
        }
    }

    private void setTableValues(String language, String description) {
        tablaDescripciones.setValueAt(language, 0, 1);
        tablaDescripciones.setValueAt(description, 0, 2);
    }

    private void clearTableValues() {
        tablaDescripciones.setValueAt(null, 0, 1);
        tablaDescripciones.setValueAt(null, 0, 2);
    }


    //tipos de datos
    private CharactersFormat cf;
    private CurrencyFormat cff;
    private DateFormat df;
    private NumberFormat nf;
    private String currencySimbolCC;

    //campos generales de caracteristica
    private String characteristic;
    private String validFrom;
    private String descriptionCharact;
    private boolean singleValue;
    private boolean multiplesValues;
    private boolean intervalsValsAllowed;
    private boolean negativeValsAllowed;
    private boolean restrictable;
    private boolean entryRequired;
    
    private int numberCharacters;
    private int numberDecimals;
    private String unitMeasure;
    private String template;

    //Inicio de edicion de una nueva caracteristica, se activa cuando confirmamos que queremos crear o ver una caracteristica
    private void startEditCharacteristic() {
        String characteristicText = sbCharacteristic.getTextBox().getText().trim();

        // Verifica si el campo no está vacío
        if (characteristicText.isEmpty()) {
            showErrorMessage(NOT.INCOMPLETE_FIELDS);
            sbCharacteristic.getTextBox().requestFocus();
            return;
        }

        // Elimina los espacios y convierte a mayúsculas
        characteristic = characteristicText.replace(" ", "").toUpperCase();

        // Verifica si la característica existe
        

        
        if (characteristicService.doesCharacteristicExist(characteristic)) {
            handleExistingCharacteristic();
        } else {
            handleNewCharacteristic();
        }
    }

    private void handleExistingCharacteristic() {
        new SystemMessages(NOT.msg(NOT.CHARACTERISTIC_EXISTS), TypeMessage.WARNING);

        int reply = showConfirmationDialog(NOT.WANT_TO_VIEW);
        if (reply == JOptionPane.YES_OPTION) {
            viewCharacteristic();
        }
    }

    private void handleNewCharacteristic() {
        int reply = showConfirmationDialog(NOT.RECORD_DOES_NOT_EXIST_CREATE);

        if (reply == JOptionPane.YES_OPTION) {
            enableEditingForNewCharacteristic();
        } else {
            new SystemMessages(NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);
        }
    }

    private int showConfirmationDialog(String messageKey) {
        return JOptionPane.showConfirmDialog(
                null,
                NOT.msg(messageKey),
                NOT.msg(NOT.TITLE),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    private void enableEditingForNewCharacteristic() {
        sbCharacteristic.getTextBox().setText(characteristic);
        sbCharacteristic.getTextBox().setEditable(false);

        validFrom = date.format(Calendar.getInstance().getTime());
        validFromTF.setText(validFrom);

        basicDataPanel.setVisible(true);
        formatsPanel.setVisible(true);
        valuesAssigmentPanel.setVisible(true);

        cmdGuardar.setEnabled(true);
        singleValueRO.setSelected(true);
        cmdNuevo.setEnabled(true);

        // Activa el método para crear
        newChar = true;
    }

    private void showErrorMessage(String messageKey) {
        new SystemMessages(NOT.msg(messageKey), TypeMessage.ERROR);
    }

    private void addNewButton() {
        cmdNuevo.addActionListener((ActionEvent e) -> {
            if (newChar) {
                startEditCharacteristic();
            } else if (clear) {
                int reply = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.CREATE_NEW_CHARACTERISTIC), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (reply == JOptionPane.YES_OPTION) {
                    resetComponents();
                    newChar = true;
                    clear = false;
                    changeChar = false;
                    cmdGuardar.setEnabled(true);
                }
            }

        });

    }
    private void textBox_addNewCharacteristic() {
        sbCharacteristic.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!sbCharacteristic.getTextBox().getText().isEmpty()) {
                        startEditCharacteristic();
                    }
                }
            }
        });
    }
    
    private void diabledComponents() {
     
        cmdGuardar.setEnabled(false);
        sbCharacteristic.getTextBox().setEditable(false);
        sbCharacteristic.getButton().setEnabled(false);
        descripcionChar.setEditable(false);
        grupoCarCB.setEnabled(false);
        estatusCarCB.setEnabled(false);
        formatsCarCB.setEnabled(false);
        singleValueRO.setEnabled(false);
        mutiplesValueRO.setEnabled(false);
        intervalsValsAllowedCB.setEnabled(false);
        negativeValsAllowedCB.setEnabled(false);
        restrictableCB.setEnabled(false);
        entryRequiredCB.setEnabled(false);
        tablaDescripciones.setEnabled(false);
        tablaValores.setEnabled(false);
        tablaRestricciones.setEnabled(false);
        cmdGuardar.setEnabled(false);
        
        switch (charFormatsCode) {
            case "CHAR" -> {
                CharactersFormat.numCharsTB.setEditable(false);
                CharactersFormat.templateTB.setEditable(false);
            }
            case "CURR" -> {
                CurrencyFormat.sbCurrencySimbol.getTextBox().setEditable(false);
                CurrencyFormat.sbCurrencySimbol.getButton().setEnabled(false);
                CurrencyFormat.templateTB.setEditable(false);
            }
            case "DATE" -> {
                DateFormat.templateTB.setEnabled(false);
            }
            case "NUM" -> {
                NumberFormat.numCharsTB.setEditable(false);
                NumberFormat.decimalPlacesTB.setEditable(false);
                NumberFormat.sbUnitMeasuring.getTextBox().setEditable(false);
                NumberFormat.sbUnitMeasuring.getButton().setEnabled(false);
            }
            
        }
    }
    
    
    private boolean isDataCompleted() {
        boolean filled = false;

        if (!descripcionChar.getText().isEmpty()
                && grupoCarCB.getSelectedItem() != null
                && estatusCarCB.getSelectedItem() != null
                && formatsCarCB.getSelectedItem() != null) {
            if (!(grupoCarCB.getSelectedItem().toString().equals("")
                    && estatusCarCB.getSelectedItem().toString().equals("")
                    && formatsCarCB.getSelectedItem().toString().equals(""))) {
                
                switch (CharacteristicsOptionsData.getCharFormatsCode(formatsCarCB.getSelectedIndex())) {

                    case "CHAR" -> {
                        if (!cf.numCharsTB.getText().isEmpty()) {
                            filled = true;
                        } else {
                            filled = false;
                            cf.numCharsTB.requestFocus();
                            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                        }
                        break;
                    }
                    case "CURR" -> {
                        if (!cff.sbCurrencySimbol.getTextBox().getText().isEmpty()) {
                            filled = true;
                        } else {
                            filled = false;
                            cff.sbCurrencySimbol.requestFocus();
                            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                        }
                        break;
                    }
                    case "DATE" -> {
                        if (df.templateTB.getSelectedItem() != null
                                && !df.templateTB.getSelectedItem().toString().equals("")) {
                            filled = true;
                        } else {
                            filled = false;
                            df.templateTB.requestFocus();
                            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                        }
                        break;
                    }
                    case "NUM" -> {
                        if (!nf.numCharsTB.getText().isEmpty()
                                && !nf.decimalPlacesTB.getText().isEmpty()
                                && !nf.sbUnitMeasuring.getTextBox().getText().isEmpty()) {

                            filled = true;
                                    
                        } else {
                            
                            filled = false;
                            nf.sbUnitMeasuring.requestFocus();
                            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                        }
                        break;
                    }



                }
            } else {
                filled = false;
                new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            }

        } else {
            filled = false;
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
        }

        return filled;
    }
    
    
    //BOTON MODIFICAR
    private boolean change = false;
    private void modifyCharacteristic() {
        
        if(change){
            
            newChar = false;
            clear = true;
            delete = true;
            
            cmdGuardar.setEnabled(true);
            
            sbCharacteristic.getTextBox().setEditable(false);
            descripcionChar.setEditable(false);
            grupoCarCB.setEnabled(false);
            estatusCarCB.setEnabled(true);
            formatsCarCB.setEnabled(false);
            singleValueRO.setEnabled(true);
            mutiplesValueRO.setEnabled(true);
  
            if (charFormatsCode.equals("NUM")) {

                intervalsValsAllowedCB.setEnabled(true);
                negativeValsAllowedCB.setEnabled(true);
                restrictableCB.setEnabled(true);
                
            }else{
                
                intervalsValsAllowedCB.setEnabled(false);
                negativeValsAllowedCB.setEnabled(false);
                restrictableCB.setEnabled(false);
                
            }

            entryRequiredCB.setEnabled(true);
            
            
            /*Bloquear edicion de celdas que ya contienen datos*/
            
            tablaDescripciones.setEnabled(true);
            
            ArrayList<Integer> rowsTD = new ArrayList();
            ArrayList<Integer> colsTD = new ArrayList();
            
            for (int i = 0; i < tablaDescripciones.getRowCount(); i++) {

                if (tablaDescripciones.getValueAt(i, 2) != null) {
                    rowsTD.add(i);
                    colsTD.add(2);
                }

            }

            ((TableForParametersModel)tablaDescripciones.getModel()).setCellNoEditable(rowsTD,colsTD);
            
            /*Fin del bloqueo de celdas*/
            
            
            /*Bloquear edicion de celdas que ya contienen datos*/
            tablaValores.setEnabled(true);
            
            ArrayList<Integer> rowsTV = new ArrayList();
            ArrayList<Integer> colsTV = new ArrayList();
            
            for (int i = 0; i < tablaValores.getRowCount(); i++) {
                if (tablaValores.getValueAt(i, 1) != null) {
                    rowsTV.add(i);
                    colsTV.add(1);
                }
            }

            ((TableForParametersModel)tablaValores.getModel()).setCellNoEditable(rowsTV,colsTV);
            
            /*Fin del bloqueo de celdas*/
            
            /*Bloquear edicion de celdas que ya contienen datos*/
            
            tablaRestricciones.setEnabled(true);
            
            ArrayList<Integer> rowsTR = new ArrayList();
            ArrayList<Integer> colsTR = new ArrayList();

            for (int i = 0; i < tablaRestricciones.getRowCount(); i++) {
                if (tablaRestricciones.getValueAt(i, 2) != null) {
                    rowsTR.add(i);
                    colsTR.add(2);
                }
            }
            ((TableForParametersModel)tablaRestricciones.getModel()).setCellNoEditable(rowsTR,colsTR);
        }
        
    }
    private void updateButton() {
        cmdModificar.addActionListener((ActionEvent e) -> {
            modifyCharacteristic();
        });
    }

    private void setComponentVisibles() {
        basicDataPanel.setVisible(true);
        formatsPanel.setVisible(true);
        valuesAssigmentPanel.setVisible(true);
        cmdGuardar.setEnabled(true);
        singleValueRO.setSelected(true);
    }

    private void setComponentDisables() {
        sbCharacteristic.getTextBox().setEditable(false);
        descripcionChar.setEditable(false);
        grupoCarCB.setEnabled(false);
        estatusCarCB.setEnabled(false);
        formatsCarCB.setEnabled(false);
        singleValueRO.setEnabled(false);
        mutiplesValueRO.setEnabled(false);
        intervalsValsAllowedCB.setEnabled(false);
        negativeValsAllowedCB.setEnabled(false);
        restrictableCB.setEnabled(false);
        entryRequiredCB.setEnabled(false);
        tablaDescripciones.setEnabled(false);
        tablaValores.setEnabled(false);
        tablaRestricciones.setEnabled(false);
        cmdGuardar.setEnabled(false);
    }
    
    private void viewCharacteristic() {
        String caract = sbCharacteristic.getTextBox().getText().trim();
        
        if (!caract.isEmpty()) {
            if (characteristicService.doesCharacteristicExist(caract)) {
                setComponentVisibles();
                
                Characteristic characteristicBuilder = characteristicService.getCharacteristicData(caract);
                
                if (characteristicBuilder != null) {
                    populateGeneralFields(characteristicBuilder);
                    populateFormatSpecificFields(characteristicBuilder);
                    loadCharacteristicData(caract);
                    
                    setComponentDisables();
                    setFlagsValues();
                }
                
            } else {
                new SystemMessages(NOT.msg(NOT.CHARACTERISTIC_DOES_NOT_EXIST), TypeMessage.WARNING);
            }
        }
    }
    
    private void populateGeneralFields(Characteristic characteristicBuilder) {
        validFromTF.setText(characteristicBuilder.getValidFrom());
        descripcionChar.setText(characteristicBuilder.getDescription());
        grupoCarCB.setSelectedItem(characteristicBuilder.getCharGroup());
        estatusCarCB.setSelectedIndex(CharacteristicsOptionsData.getCharStatusIndex(characteristicBuilder.getStatus()));
        singleValueRO.setSelected(characteristicBuilder.isSingleValue());
        mutiplesValueRO.setSelected(characteristicBuilder.isMultiplesValues());
        intervalsValsAllowedCB.setSelected(characteristicBuilder.isIntervalsAllowed());
        negativeValsAllowedCB.setSelected(characteristicBuilder.isNegativeValsAllowed());
        restrictableCB.setSelected(characteristicBuilder.isRestrictable());
        entryRequiredCB.setSelected(characteristicBuilder.isEntryRequired());
        
        formatsCarCB.setSelectedIndex(CharacteristicsOptionsData.getCharFormatIndex(characteristicBuilder.getDataType()));
    }
    
    private void populateFormatSpecificFields(Characteristic characteristicBuilder) {
        switch (characteristicBuilder.getDataType()) {
            case "CHAR":
                cf.numCharsTB.setText(String.valueOf(characteristicBuilder.getNumberCharacters()));
                cf.numCharsTB.setEditable(false);
                cf.templateTB.setText(characteristicBuilder.getTemplate());
                break;
            
            case "CURR":
                cff.sbCurrencySimbol.getTextBox().setText(characteristicBuilder.getCurrencySimbols());
                cff.sbCurrencySimbol.getTextBox().setEditable(false);
                cff.templateTB.setText(characteristicBuilder.getTemplate());
                break;
            
            case "DATE":
                df.templateTB.setSelectedItem(characteristicBuilder.getTemplate());
                df.templateTB.setEnabled(false);
                break;
            
            case "NUM":
                nf.numCharsTB.setText(String.valueOf(characteristicBuilder.getNumberCharacters()));
                nf.numCharsTB.setEditable(false);
                nf.decimalPlacesTB.setText(String.valueOf(characteristicBuilder.getNumberDecimals()));
                nf.decimalPlacesTB.setEditable(false);
                nf.sbUnitMeasuring.getTextBox().setText(characteristicBuilder.getUnitOfMeasure());
                nf.sbUnitMeasuring.getTextBox().setEditable(false);
                nf.templateTB.setText(characteristicBuilder.getTemplate());
                nf.templateTB.setEditable(false);
                break;
        }
    }
    
    private void loadCharacteristicData(String caract) {
        new CharacteristicDescriptionDAO().getDescriptions(tablaDescripciones, caract);
        new CharacteristicsValuesView().getValues(tablaValores, caract);
        new CharacteristicRestrictionDAO().getRestrictions(tablaRestricciones, caract);
    }
    
    private void setFlagsValues() {
        change = true;
        newChar = false;
        changeChar = true;
        clear = true;
        delete = true;
    }

    private void viewButton() {
        cmdVer.addActionListener((ActionEvent e) -> {
            
           viewCharacteristic();
            
        });
    }
    private void listCharacteristicsButton(){
        sbCharacteristic.getButton().addActionListener((e)->{
            
            CharacteristicList list = new CharacteristicList(getSuperFrame(),characteristicService);
            list.setCampos(sbCharacteristic.getTextBox(), null);
            list.setVisible(true);
            
        });
    }
   
    private boolean clear = false;
    private void resetComponents() {

            sbCharacteristic.getTextBox().setText(null);
            sbCharacteristic.getTextBox().setEditable(true);
            sbCharacteristic.getButton().setEnabled(true);

            descripcionChar.setText(null);
            descripcionChar.setEditable(true);
            
            grupoCarCB.setSelectedItem(null);
            grupoCarCB.setEnabled(true);

            estatusCarCB.setSelectedIndex(0);
            estatusCarCB.setEnabled(true);

            formatsCarCB.setSelectedIndex(4);
            formatsCarCB.setEnabled(true);

            singleValueRO.setSelected(false);
            singleValueRO.setEnabled(true);

            mutiplesValueRO.setSelected(false);
            mutiplesValueRO.setEnabled(true);

            intervalsValsAllowedCB.setSelected(false);
            intervalsValsAllowedCB.setEnabled(true);

            negativeValsAllowedCB.setSelected(false);
            negativeValsAllowedCB.setEnabled(true);

            restrictableCB.setSelected(false);
            restrictableCB.setEnabled(true);

            entryRequiredCB.setSelected(false);
            entryRequiredCB.setEnabled(true);

            switch (charFormatsCode) {
                case "CHAR" -> {

                    cf.numCharsTB.setText(null);
                    cf.templateTB.setText(null);
                }
                case "CURR" -> {
                    cff.sbCurrencySimbol.getTextBox().setText(null);
                    cff.templateTB.setText(null);
                }
                case "DATE" -> {
                    df.templateTB.setSelectedItem(null);
                }
                case "NUM" -> {

                    nf.numCharsTB.setText(null);
                    nf.decimalPlacesTB.setText(null);
                    nf.sbUnitMeasuring.getTextBox().setText(null);

                }
            }

            ClearTables.clearData(tablaDescripciones);
            ClearTables.clearData(tablaValores);
            ClearTables.clearData(tablaRestricciones);

            ((TableForParametersModel) tablaDescripciones.getModel()).setCellNoEditable(null,null);
            ((TableForParametersModel) tablaValores.getModel()).setCellNoEditable(null,null);
            ((TableForParametersModel) tablaRestricciones.getModel()).setCellNoEditable(null,null);
            
        
    }

    //BOTON ELIMINAR
    private boolean delete = false;
    private void deleteCharacteristic() {
   
        if (delete) {

            String characteristics = sbCharacteristic.getTextBox().getText();
            characteristicService.deleteCharacteristic(characteristics);

            List<Integer> listaIdDesc = CharacteristicDescriptionDAO.getIdDescriptions(characteristics);

            if (listaIdDesc != null) {
                for (int i = 0; i < listaIdDesc.size(); i++) {
                    CharacteristicDescriptionDAO.delete(listaIdDesc.get(i));
                }
            }

            ArrayList<Integer> listaIdValues = CharacteristicValuesID.getIdValues(characteristics);

            if (listaIdValues != null) {
                for (int i = 0; i < listaIdValues.size(); i++) {
                    DeleteCharacteristicValuesById.deleteValue(listaIdValues.get(i));
                }
            }

            List<Integer> listaIdRestriction = CharacteristicRestrictionDAO.getIdRestrictions(characteristics);

            if (listaIdRestriction != null) {
                for (int i = 0; i < listaIdRestriction.size(); i++) {
                    CharacteristicRestrictionDAO.deleteById(listaIdRestriction.get(i));
                }
            }

           resetComponents(); 
           new SystemMessages(NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.WARNING); 
        }

    }
    private void deleteButton() {
        cmdEliminar.addActionListener((e) -> {
            
            int reply = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.ARE_YOU_SURE_DELETE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (reply == JOptionPane.YES_OPTION) {
                deleteCharacteristic();
            }

        });
    }
    
    //Metodos del boton de guardado superior
    private void saveCharacteristic() {
        if (isDataCompleted()) {

            if (newChar) {

                if (!characteristicService.doesCharacteristicExist(characteristic)) {
                    valueAssignment();
                    createCharacteristic();
                }else{
                    new SystemMessages(NOT.msg(NOT.CHARACTERISTIC_EXISTS), TypeMessage.WARNING);
                }

            } else if (changeChar) {
                valueAssignment();
                changeCharacteristic();

            }
        }
    }
    private void saveButton() {
        cmdGuardar.addActionListener((ActionEvent e) -> {
            saveCharacteristic();
        });
    }

    private void valueAssignment() {
        
        //Asigna valores a variables de la caracteristica 
        
        sbCharacteristic.getTextBox().setText(sbCharacteristic.getTextBox().getText().toUpperCase());
        
        characteristic = sbCharacteristic.getTextBox().getText().trim();
        validFrom = validFromTF.getText();
        
        descriptionCharact = descripcionChar.getText();

        charStatusCode = CharacteristicsOptionsData.getCharStatusCode(estatusCarCB.getSelectedIndex());
        charGroupCode = CharacteristicsOptionsData.getCharGroupCode(grupoCarCB.getSelectedIndex());

        singleValue = singleValueRO.isSelected();
        multiplesValues = mutiplesValueRO.isSelected();

        intervalsValsAllowed = intervalsValsAllowedCB.isSelected();
        negativeValsAllowed = negativeValsAllowedCB.isSelected();
        restrictable = restrictableCB.isSelected();
        entryRequired = entryRequiredCB.isSelected();

        String charFormat = CharacteristicsOptionsData.getCharFormatsCode(formatsCarCB.getSelectedIndex());

        switch (charFormat) {

            case "CHAR" -> {

                numberCharacters = Integer.parseInt(cf.numCharsTB.getText());
                template = cf.getTemplate();

            }
            case "CURR" -> {

                currencySimbolCC = cff.sbCurrencySimbol.getTextBox().getText();
                template = cff.getTemplate();

            }
            case "DATE" -> {

                template = df.templateTB.getSelectedItem().toString();
            }
            case "NUM" -> {

                numberCharacters = Integer.parseInt(nf.numCharsTB.getText());
                numberDecimals = Integer.parseInt(nf.decimalPlacesTB.getText());
                unitMeasure = nf.sbUnitMeasuring.getTextBox().getText();
                template = nf.templateTB.getText();

            }

        }
    }


    //Creacion de la caracteristica, se activa al proceder a la creacion
    private boolean newChar = true;
    private void createCharacteristic() {

        valueAssignment();
        
        Characteristic characteristic1 = new Characteristic.Builder()
                .characteristic(characteristic)
                .validFrom(validFrom)
                .charStatus(charStatusCode)
                .description(descriptionCharact)
                .charGroup(charGroupCode)
                .singleValue(singleValue)
                .multiplesValues(multiplesValues)
                .intervalsAllowed(intervalsValsAllowed)
                .negativeValsAllowed(negativeValsAllowed)
                .restrictable(restrictable)
                .entryRequired(entryRequired)
                .dataType(charFormatsCode)
                .numberCharacters(numberCharacters)
                .numberDecimals(numberDecimals)
                .unitOfMeasure(unitMeasure)
                .currencySimbols(currencySimbolCC)
                .template(template)
                .build();

        //Asigna valores a clase de creacion
        characteristicService.insertCharacteristic(characteristic1);

        createDescriptions();
        createValues();
        createRestrictions();

        new SystemMessages(NOT.msg(NOT.CHARACTERISTIC_CREATED), TypeMessage.SUCCESS);

        changeChar = true;
        newChar = false;
        delete = true;
        change = true;
        clear = true;
    }
    
    
    //Cambios de la caracteristica, se activa al proceder al crear la caracteristica en SQL
    private boolean changeChar = false;
    private void changeCharacteristic() {
        
        valueAssignment();
        
        Characteristic changeCharacteristic = new Characteristic.Builder()
                .description(descriptionCharact)
                .charGroup(charGroupCode)
                .charStatus(charStatusCode)
                .singleValue(singleValue)
                .multiplesValues(multiplesValues)
                .intervalsAllowed(intervalsValsAllowed)
                .negativeValsAllowed(negativeValsAllowed)
                .restrictable(restrictable)
                .entryRequired(entryRequired)
                .build();
        
        characteristicService.updateCharacteristic(characteristic,changeCharacteristic);
        
        diabledComponents();

        changeChar = true;
        newChar = false;
        delete = true;
        change = true;
        
        changeDescriptions();
        createValues();
        createRestrictions();

        new SystemMessages(NOT.msg(NOT.CHARACTERISTIC_SAVED), TypeMessage.SUCCESS);
    }

    
    //Metodos de creacion, cambio o eliminacion de descripciones
    private void createDescriptions() {
        
        int filas = tablaDescripciones.getRowCount();

        for (int i = 0; i < filas; i++) {
           
            if (tablaDescripciones.getValueAt(i, 1) != null && tablaDescripciones.getValueAt(i, 2) != null) {

                String idioma1 = tablaDescripciones.getValueAt(i, 1).toString();
                String description = tablaDescripciones.getValueAt(i, 2).toString();

                new CharacteristicDescriptionDAO().insert(characteristic, idioma1, description);
                
            }
        
        }
        
    }
    private void changeDescriptions() {

        int filas = tablaDescripciones.getRowCount();

        for (int i = 0; i < filas; i++) {

            if (tablaDescripciones.getValueAt(i, 1) != null && tablaDescripciones.getValueAt(i, 2) != null) {

                String idioma1 = tablaDescripciones.getValueAt(i, 1).toString();
                String description = tablaDescripciones.getValueAt(i, 2).toString();
                String charact = sbCharacteristic.getTextBox().getText();

                if (CharacteristicDescriptionDAO.exists(idioma1, charact)) {
                    new CharacteristicDescriptionDAO().update(characteristic, idioma1, description);
                } else {
                    new CharacteristicDescriptionDAO().insert(characteristic, idioma1, description);
                }

            }

        }

    }
    private void deleteDescriptions(String caracteristica, String idioma) {
        new CharacteristicDescriptionDAO().delete(caracteristica, idioma);
    }

    
    //Metodos de creacion, cambio o eliminacion de lista de valores
    private void createValues() {

        int filas = tablaValores.getRowCount();

        for (int i = 0; i < filas; i++) {

            if (tablaValores.getValueAt(i, 1) != null && tablaValores.getValueAt(i, 2) == null) {

                if (!tablaValores.getValueAt(i, 1).toString().isEmpty()) {
                    
                    String charact = sbCharacteristic.getTextBox().getText();
                    String charValue = tablaValores.getValueAt(i, 1).toString();

                    if (!ValueCreated.valueExists(charact, charValue)) {

                        CreateCharacteristicValues cv = new CreateCharacteristicValues();
                        cv.insertValues(charact, charValue, "");

                    }
                }

            } else if (tablaValores.getValueAt(i, 1) != null && tablaValores.getValueAt(i, 2) != null) {

                if (!tablaValores.getValueAt(i, 1).toString().isEmpty()|
                        !tablaValores.getValueAt(i, 2).toString().isEmpty()) {
                    
                    String charact = sbCharacteristic.getTextBox().getText();
                    String charValue = tablaValores.getValueAt(i, 1).toString();
                    String description = tablaValores.getValueAt(i, 2).toString();

                    if (!ValueCreated.valueExists(charact, charValue)) {

                        CreateCharacteristicValues cv = new CreateCharacteristicValues();
                        cv.insertValues(charact, charValue, description);

                    }
                }

            }
        }


    }
    private void deleteValues(String caract, String value){
        DeleteCharacteristicValues dChV = new DeleteCharacteristicValues ();
        dChV.deleteValue(caract, value);
    }
    
    
    //Metodos de creacion, cambio o eliminacion de lista de restricciones
    private void createRestrictions(){
        int filas = tablaRestricciones.getRowCount();
        for(int i = 0; i < filas;i++){
            if (tablaRestricciones.getValueAt(i, 1) != null && tablaRestricciones.getValueAt(i, 2) != null) {
                String charact = sbCharacteristic.getTextBox().getText();
                String type = tablaRestricciones.getValueAt(i, 1).toString();
                if (!CharacteristicRestrictionDAO.exists(charact, type)) {                    
                    new CharacteristicRestrictionDAO().insertRestriction(type, charact);
                }
            }
        }

        
    }
    private void deleteRestrictions(String characteristic, String type) {
        new CharacteristicRestrictionDAO().deleteRestriction(characteristic, type);
    }
    
    
    //Eventos de la tabla
    private void tableEvents() {

        tableValues();
        tableDescriptions();
        tableRestrictions();
        tabbedChange();

    }

    //Configuraciones tabla descripciones
    private void tableDescriptions() {
        
        tablaDescripciones.setVisible(true);

        String[] identifiers = new String[]{"Language", "Description"};

        tablaDescripciones.setModel(new TableForParametersModel(identifiers));
        ((TableForParametersModel)tablaDescripciones.getModel()).addRow(new Object[]{null,null});
        ((TableForParametersModel)tablaDescripciones.getModel()).addRow(new Object[]{null,null});
        
        tablaDescripciones.getColumnModel().getColumn(1).setCellEditor(new SearchBoxCellEditor());
        
        tablaDescripciones.getColumnModel().getColumn(2).setMinWidth(30);
        tablaDescripciones.getColumnModel().getColumn(2).setPreferredWidth(300);
        
        tablaDescripciones.addTableButtonListener(new TableButtonListener(){
            @Override
            public void actionPerformed(int row) {
                
                if(tablaDescripciones.getValueAt(row, 1)!=null){
                    
                    String caract = sbCharacteristic.getTextBox().getText();
                    String idioma = tablaDescripciones.getValueAt(row, 1).toString();
                    String descripcion = tablaDescripciones.getValueAt(row, 1).toString();
                    
                    deleteDescriptions(caract,idioma);
                    
                    tablaDescripciones.setValueAt(null, row, 1);
                    tablaDescripciones.setValueAt(null, row, 2);
                    
                    new SystemMessages(NOT.msg(NOT.INFORMATION_REMOVED)+": "+descripcion, TypeMessage.INFORMATION);

                    ((TableForParametersModel)tablaDescripciones.getModel()).getArrayNoEditable().remove(row);
                }
                
            }
            
        });
    }
    
    //Configuraciones tabla descripciones
    private void tableValues() {

        tablaValores.setVisible(true);
        
        tablaValores.setSurrendersFocusOnKeystroke(true);
        
        String[] identifiers = new String[]{"Char.Value", "Description"};

        tablaValores.setModel(new TableForParametersModel(identifiers));
        for (int i = 0; i < 20; i++) {
            ((TableForParametersModel) tablaValores.getModel()).addRow(new Object[]{null, null});
        }

        tablaValores.getColumnModel().getColumn(1).setPreferredWidth(120);
        tablaValores.getColumnModel().getColumn(1).setMinWidth(10);

        tablaValores.getColumnModel().getColumn(2).setPreferredWidth(300);
        tablaValores.getColumnModel().getColumn(2).setMinWidth(50);

        tablaValores.addTableButtonListener(new TableButtonListener() {
            @Override
            public void actionPerformed(int row) {
                
                if (tablaValores.getValueAt(row, 1) != null) {

                    String caract = sbCharacteristic.getTextBox().getText();
                    String value = tablaValores.getValueAt(row, 1).toString();

                    deleteValues(caract, value);
                    
                    tablaValores.setValueAt(null, row, 1);
                    tablaValores.setValueAt(null, row, 2);

                    new SystemMessages(NOT.msg(NOT.INFORMATION_REMOVED) + ": " + value, TypeMessage.INFORMATION);
                
                    ((TableForParametersModel)tablaValores.getModel()).getArrayNoEditable().remove(row);
                }

            }

        });

        
    }
    
    //Configuracinoes tabla restricciones
    private void tableRestrictions() {
        
        tablaRestricciones.setVisible(true);
        
        restrictableCB.addActionListener((e)->{
            if(restrictableCB.isSelected()){
                tablaRestricciones.setEnabled(true);
            }else{
                tablaRestricciones.setEnabled(false);
            }
        });

        String[] identifiers = new String[]{"Class type", "Description"};

        tablaRestricciones.setModel(new TableForParametersModel(identifiers));
        for(int i = 0; i<20;i++){
            ((TableForParametersModel) tablaRestricciones.getModel()).addRow(new Object[]{null, null});
        }
        
        tablaRestricciones.getColumnModel().getColumn(1).setMinWidth(20);
        tablaRestricciones.getColumnModel().getColumn(1).setPreferredWidth(120);
        
        RestrictionsTable_CellEditor restricRender = new RestrictionsTable_CellEditor(getSuperFrame());
        tablaRestricciones.getColumnModel().getColumn(1).setCellEditor(restricRender);

        
        tablaRestricciones.getColumnModel().getColumn(2).setMinWidth(100);
        tablaRestricciones.getColumnModel().getColumn(2).setPreferredWidth(300);
        
        tablaRestricciones.setEnabled(false);        
        tablaRestricciones.getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {

                int row = tablaRestricciones.getSelectedRow();

                if (restricRender.getValue() != null) {
                    
                    int index = Class_Type_List.getClassTypeIndex(restricRender.getValue());

                    if (index > -1) {
                        
                        String desc = Class_Type_List.getClassTypeDescriptionByIndex(index);
                        tablaRestricciones.setValueAt(desc, row, 2);
                        restricRender.setValue(null);
                    
                    }
                }else{
                    tablaRestricciones.setValueAt(null, row, 2);
                }

            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }

        });

        tablaRestricciones.getModel().addTableModelListener(new TableModelListener(){
            
            int rows = tablaRestricciones.getRowCount();
            
            @Override
            public void tableChanged(TableModelEvent e) {

                if (e.getColumn() == 1) {

                    for (int i = 0; i < rows; i++) {

                        if (tablaRestricciones.getValueAt(i, 1) != null) {

                            if (!tablaRestricciones.getValueAt(i, 1).toString().isEmpty()) {
                                String code = tablaRestricciones.getValueAt(i, 1).toString();
                                String desc = Class_Type_List.getClassTypeDescriptionByCode(code);

                                tablaRestricciones.setValueAt(desc, i, 2);
                            }

                        }
                    }

                }
            }
            
        });
        
        tablaRestricciones.addTableButtonListener(new TableButtonListener() {
            @Override
            public void actionPerformed(int row) {

                if (tablaRestricciones.getValueAt(row, 1) != null) {

                    String caract = sbCharacteristic.getTextBox().getText();
                    String type = tablaRestricciones.getValueAt(row, 1).toString();
                    String typeDesc = tablaRestricciones.getValueAt(row, 2).toString();

                    deleteRestrictions(caract, type);

                    tablaRestricciones.setValueAt(null, row, 1);
                    tablaRestricciones.setValueAt(null, row, 2);

                    new SystemMessages(NOT.msg(NOT.INFORMATION_REMOVED) + ": " + type + " " + typeDesc, TypeMessage.INFORMATION);

                    if(((TableForParametersModel) tablaRestricciones.getModel()).getArrayNoEditable()!=null){
                        ((TableForParametersModel) tablaRestricciones.getModel()).getArrayNoEditable().remove(row);
                    }
                    
                }

            }

        });

    }

    private void changeFormatTableValues(String tf, int nc,int decimal,String um,String cur,String temp,boolean interV,boolean neg){
        
        tablaValores.getColumnModel().getColumn(1).setCellEditor(new ValuesTable_CellRender(tf,nc,decimal,um,cur,temp,interV,neg));
        
    }
    private void tabbedChange(){
        tabPaned.addChangeListener((ChangeEvent e) -> {
            
            if(descripcionChar.getText().isEmpty()
                    |grupoCarCB.getSelectedItem()==null
                    |estatusCarCB.getSelectedItem()==null
                    |formatsCarCB.getSelectedItem()==null){
                
                tabPaned.setSelectedIndex(0);
                descripcionChar.requestFocus();
                new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS),TypeMessage.ERROR);
                
            }else{

                charFormatsCode = CharacteristicsOptionsData.getCharFormatsCode(formatsCarCB.getSelectedIndex());
                tablaDescripciones.setValueAt("EN", 0, 1);
                tablaDescripciones.setValueAt(descripcionChar.getText(), 0, 2);
                
                switch(charFormatsCode){
                    
                    case "CHAR"->{
                        
                        if(cf.numCharsTB.getText().isEmpty()){
                            
                            
                            tabPaned.setSelectedIndex(0);
                            cf.numCharsTB.requestFocus();
                            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS),TypeMessage.ERROR);
                            
                        }else{
                            
                            int numChar = Integer.parseInt(cf.numCharsTB.getText());
                            changeFormatTableValues("CHAR",numChar,0,null,null,null,false,false);
                        }
                        
                    }
                    case "CURR"->{
                        
                        if(cff.sbCurrencySimbol.getTextBox().getText().isEmpty()){
                            
                            
                            tabPaned.setSelectedIndex(0);
                            cff.sbCurrencySimbol.getTextBox().requestFocus();
                            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS),TypeMessage.ERROR);
                        }else{
                            String curr = cff.sbCurrencySimbol.getTextBox().getText();
                            changeFormatTableValues("CURR",0,0,null,curr,cff.getTemplate(),false,false);
                        }

                    }
                    case "DATE"->{
                        
                        if(df.templateTB.getSelectedItem()==null){
                            
                            
                            tabPaned.setSelectedIndex(0);
                            df.templateTB.requestFocus();
                            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS),TypeMessage.ERROR);
                            
                        }else{
                            String temp = df.templateTB.getSelectedItem().toString();
                            tablaValores.getColumnModel().getColumn(1).setCellEditor(new Date_ValuesTable_CellEditor(temp));
                        }
                        
                    }
                    case "NUM" -> {
                        
                        if (nf.numCharsTB.getText().isEmpty()
                                | nf.decimalPlacesTB.getText().isEmpty()
                                | nf.sbUnitMeasuring.getTextBox().getText().isEmpty()) {
                            
                            
                            tabPaned.setSelectedIndex(0);
                            nf.numCharsTB.requestFocus();
                            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
                            
                        }else{
                            
                            int numChar = Integer.parseInt(nf.numCharsTB.getText());
                            int numDec = Integer.parseInt(nf.decimalPlacesTB.getText());
                            String unitMea = nf.sbUnitMeasuring.getTextBox().getText();
                            boolean interV = intervalsValsAllowedCB.isSelected();
                            boolean neg = negativeValsAllowedCB.isSelected();
                            
                            changeFormatTableValues("NUM",numChar,numDec,unitMea,null,null,interV,neg);
                        }
                        
                    }



                }

            }
        });
    }

    
    
    private void changePanel(JPanel panel) {

        formatOptionsPanel.removeAll();
        formatOptionsPanel.add(panel);
        formatOptionsPanel.revalidate();
        formatOptionsPanel.repaint();
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        cmdSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        cmdGuardar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        characteristicLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sbCharacteristic = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        cmdVer = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        cmdModificar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        cmdNuevo = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        cmdEliminar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        validFromLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        validFromTF = new javax.swing.JTextField();
        tabPaned = new com.simplecore.erp.gui.components.tabbedpanes.TabbedPane();
        mainPanel = new javax.swing.JPanel();
        basicDataPanel = new javax.swing.JPanel();
        basicDataLB = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        descriptionLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        descripcionChar = new javax.swing.JTextField();
        charGroupLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        grupoCarCB = new javax.swing.JComboBox<>();
        statusLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        estatusCarCB = new javax.swing.JComboBox<>();
        formatsPanel = new javax.swing.JPanel();
        formatsLB = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        dataTypeLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        formatsCarCB = new javax.swing.JComboBox<>();
        formatOptionsPanel = new javax.swing.JPanel();
        emptyPanel = new javax.swing.JPanel();
        valuesAssigmentPanel = new javax.swing.JPanel();
        valueAssigmentLB = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        singleValueRO = new javax.swing.JRadioButton();
        mutiplesValueRO = new javax.swing.JRadioButton();
        intervalsValsAllowedCB = new javax.swing.JCheckBox();
        negativeValsAllowedCB = new javax.swing.JCheckBox();
        restrictableCB = new javax.swing.JCheckBox();
        entryRequiredCB = new javax.swing.JCheckBox();
        descriptionsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDescripciones = new com.simplecore.erp.gui.components.tables.newversions.TableForParameters();
        descriptionHeadingsLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        valuesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaValores = new com.simplecore.erp.gui.components.tables.newversions.TableForParameters();
        restrictionPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaRestricciones = new com.simplecore.erp.gui.components.tables.newversions.TableForParameters();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        cmdSalir.setBackground(new java.awt.Color(226, 210, 144));
        cmdSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cmdSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        cmdGuardar.setBackground(new java.awt.Color(226, 210, 144));
        cmdGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cmdGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N
        cmdGuardar.setEnabled(false);

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(cmdGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 965, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setAutoscrolls(true);
        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        characteristicLB.setText("Characteristic");
        characteristicLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        cmdVer.setBackground(new java.awt.Color(226, 210, 144));
        cmdVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/view_lens.png"))); // NOI18N

        cmdModificar.setBackground(new java.awt.Color(226, 210, 144));
        cmdModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/modify_pencil .png"))); // NOI18N

        cmdNuevo.setBackground(new java.awt.Color(226, 210, 144));
        cmdNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/new_empty.png"))); // NOI18N

        cmdEliminar.setBackground(new java.awt.Color(226, 210, 144));
        cmdEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/delete_trash.png"))); // NOI18N

        validFromLB.setText("Valid from");
        validFromLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        validFromTF.setEditable(false);
        validFromTF.setBackground(new java.awt.Color(202, 216, 237));

        tabPaned.setForeground(new java.awt.Color(248, 248, 248));
        tabPaned.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        tabPaned.setSelectedTabColor(new java.awt.Color(0, 131, 206));
        tabPaned.setTabBackgroundColor(new java.awt.Color(0, 102, 160));

        basicDataPanel.setBackground(new java.awt.Color(202, 219, 236));
        basicDataPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        basicDataLB.setText("Basic data");
        basicDataLB.setColorBordes(new java.awt.Color(117, 141, 163));
        basicDataLB.setColorRelleno(new java.awt.Color(136, 175, 198));
        basicDataLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        descriptionLB.setText("Description");
        descriptionLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        descripcionChar.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        charGroupLB.setText("Char. Group");
        charGroupLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        grupoCarCB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        statusLB.setText("Status");
        statusLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        estatusCarCB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout basicDataPanelLayout = new javax.swing.GroupLayout(basicDataPanel);
        basicDataPanel.setLayout(basicDataPanelLayout);
        basicDataPanelLayout.setHorizontalGroup(
            basicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(basicDataLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(basicDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(charGroupLB, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusLB, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(basicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcionChar, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grupoCarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estatusCarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        basicDataPanelLayout.setVerticalGroup(
            basicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicDataPanelLayout.createSequentialGroup()
                .addComponent(basicDataLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(basicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionChar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(basicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(charGroupLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grupoCarCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(basicDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estatusCarCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        formatsPanel.setBackground(new java.awt.Color(202, 219, 236));
        formatsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        formatsLB.setText("Format");
        formatsLB.setColorBordes(new java.awt.Color(117, 141, 163));
        formatsLB.setColorRelleno(new java.awt.Color(136, 175, 198));
        formatsLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        dataTypeLB.setText("Data type");
        dataTypeLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        formatsCarCB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        formatOptionsPanel.setBackground(new java.awt.Color(202, 219, 236));
        formatOptionsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        formatOptionsPanel.setLayout(new java.awt.BorderLayout());

        emptyPanel.setBackground(new java.awt.Color(202, 219, 236));

        javax.swing.GroupLayout emptyPanelLayout = new javax.swing.GroupLayout(emptyPanel);
        emptyPanel.setLayout(emptyPanelLayout);
        emptyPanelLayout.setHorizontalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );
        emptyPanelLayout.setVerticalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        formatOptionsPanel.add(emptyPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout formatsPanelLayout = new javax.swing.GroupLayout(formatsPanel);
        formatsPanel.setLayout(formatsPanelLayout);
        formatsPanelLayout.setHorizontalGroup(
            formatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(formatsLB, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(formatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formatOptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formatsPanelLayout.createSequentialGroup()
                        .addComponent(dataTypeLB, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(formatsCarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        formatsPanelLayout.setVerticalGroup(
            formatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatsPanelLayout.createSequentialGroup()
                .addComponent(formatsLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataTypeLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatsCarCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formatOptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        valuesAssigmentPanel.setBackground(new java.awt.Color(202, 219, 236));
        valuesAssigmentPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        valueAssigmentLB.setText("Value assigment");
        valueAssigmentLB.setColorBordes(new java.awt.Color(117, 141, 163));
        valueAssigmentLB.setColorRelleno(new java.awt.Color(136, 175, 198));
        valueAssigmentLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        singleValueRO.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        singleValueRO.setText("Single value");

        mutiplesValueRO.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        mutiplesValueRO.setText("Multiple values");

        intervalsValsAllowedCB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        intervalsValsAllowedCB.setText("Interval vals allowed");

        negativeValsAllowedCB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        negativeValsAllowedCB.setText("Negatives vals allowed");

        restrictableCB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        restrictableCB.setText("Restrictable");

        entryRequiredCB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        entryRequiredCB.setText("Entry Required");

        javax.swing.GroupLayout valuesAssigmentPanelLayout = new javax.swing.GroupLayout(valuesAssigmentPanel);
        valuesAssigmentPanel.setLayout(valuesAssigmentPanelLayout);
        valuesAssigmentPanelLayout.setHorizontalGroup(
            valuesAssigmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(valueAssigmentLB, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(valuesAssigmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(valuesAssigmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(singleValueRO)
                    .addComponent(mutiplesValueRO)
                    .addComponent(intervalsValsAllowedCB)
                    .addComponent(negativeValsAllowedCB)
                    .addComponent(restrictableCB)
                    .addComponent(entryRequiredCB)))
        );
        valuesAssigmentPanelLayout.setVerticalGroup(
            valuesAssigmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valuesAssigmentPanelLayout.createSequentialGroup()
                .addComponent(valueAssigmentLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(singleValueRO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mutiplesValueRO)
                .addGap(70, 70, 70)
                .addComponent(intervalsValsAllowedCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(negativeValsAllowedCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restrictableCB)
                .addGap(42, 42, 42)
                .addComponent(entryRequiredCB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(basicDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(formatsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valuesAssigmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(basicDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(formatsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(valuesAssigmentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPaned.addTab("Basic data", mainPanel);

        tablaDescripciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaDescripciones.setColumn_0_Width(15);
        tablaDescripciones.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        tablaDescripciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaDescripciones);

        descriptionHeadingsLB.setText("Descriptions and headings");
        descriptionHeadingsLB.setFont(new java.awt.Font("Raleway Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout descriptionsPanelLayout = new javax.swing.GroupLayout(descriptionsPanel);
        descriptionsPanel.setLayout(descriptionsPanelLayout);
        descriptionsPanelLayout.setHorizontalGroup(
            descriptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(descriptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionHeadingsLB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        descriptionsPanelLayout.setVerticalGroup(
            descriptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptionsPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(descriptionHeadingsLB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        tabPaned.addTab("Descriptions", descriptionsPanel);

        tablaValores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaValores.setColumn_0_Width(15);
        tablaValores.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        tablaValores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tablaValores);

        javax.swing.GroupLayout valuesPanelLayout = new javax.swing.GroupLayout(valuesPanel);
        valuesPanel.setLayout(valuesPanelLayout);
        valuesPanelLayout.setHorizontalGroup(
            valuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valuesPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );
        valuesPanelLayout.setVerticalGroup(
            valuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valuesPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPaned.addTab("Values", valuesPanel);

        tablaRestricciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaRestricciones.setColumn_0_Width(15);
        tablaRestricciones.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        tablaRestricciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tablaRestricciones);

        javax.swing.GroupLayout restrictionPanelLayout = new javax.swing.GroupLayout(restrictionPanel);
        restrictionPanel.setLayout(restrictionPanelLayout);
        restrictionPanelLayout.setHorizontalGroup(
            restrictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restrictionPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        restrictionPanelLayout.setVerticalGroup(
            restrictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restrictionPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPaned.addTab("Restrictions", restrictionPanel);

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPaned, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(characteristicLB, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(validFromLB, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoLayout.createSequentialGroup()
                                .addComponent(sbCharacteristic, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdVer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(cmdModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(cmdNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(cmdEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(validFromTF, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(characteristicLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbCharacteristic, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdVer, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validFromLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validFromTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabPaned, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotonera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming basicDataLB;
    private javax.swing.JPanel basicDataPanel;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined charGroupLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined characteristicLB;
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdEliminar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdGuardar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdModificar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdNuevo;
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdSalir;
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdVer;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined dataTypeLB;
    private javax.swing.JTextField descripcionChar;
    private com.simplecore.erp.gui.components.labels.JLabelHQ descriptionHeadingsLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined descriptionLB;
    private javax.swing.JPanel descriptionsPanel;
    private javax.swing.JPanel emptyPanel;
    private javax.swing.JCheckBox entryRequiredCB;
    private javax.swing.JComboBox<String> estatusCarCB;
    private javax.swing.JPanel formatOptionsPanel;
    private javax.swing.JComboBox<String> formatsCarCB;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming formatsLB;
    private javax.swing.JPanel formatsPanel;
    private javax.swing.JComboBox<String> grupoCarCB;
    private javax.swing.JCheckBox intervalsValsAllowedCB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButton mutiplesValueRO;
    private javax.swing.JCheckBox negativeValsAllowedCB;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private javax.swing.JCheckBox restrictableCB;
    private javax.swing.JPanel restrictionPanel;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbCharacteristic;
    private javax.swing.JRadioButton singleValueRO;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined statusLB;
    private com.simplecore.erp.gui.components.tabbedpanes.TabbedPane tabPaned;
    private com.simplecore.erp.gui.components.tables.newversions.TableForParameters tablaDescripciones;
    public static com.simplecore.erp.gui.components.tables.newversions.TableForParameters tablaRestricciones;
    public static com.simplecore.erp.gui.components.tables.newversions.TableForParameters tablaValores;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined validFromLB;
    private javax.swing.JTextField validFromTF;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming valueAssigmentLB;
    private javax.swing.JPanel valuesAssigmentPanel;
    private javax.swing.JPanel valuesPanel;
    // End of variables declaration//GEN-END:variables

    private void buttonExit() {
        cmdSalir.addActionListener((e) -> {
            characteristicService.closeService();
            
            Invoke_JMenuBars.setMenuBar(getSuperFrame(), getSuperFrame().getJMenuBar(),
                    LyraWorkspace.barMenu);

            PanelLoader.loadPanel(treeMenus, mainContainerPanel);

        });
    }
    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }

    
}
