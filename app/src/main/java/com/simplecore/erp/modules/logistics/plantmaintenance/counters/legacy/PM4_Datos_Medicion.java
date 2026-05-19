package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.UnitsOfMeasurement;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;


public class PM4_Datos_Medicion extends javax.swing.JPanel {

    
    private SimpleDateFormat date = new SimpleDateFormat("MM.dd.yyyy");
    private SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
    private SimpleDateFormat dateTime = new SimpleDateFormat("MM.dd.yyyy hh:mm a");
    private final double hour = 0.0417;
    private boolean firstRecord = false;
    
    private boolean readyToCreate = false;
    private boolean timeOk = false;
    
    private String measPoint;
    private String measPosition;
    private String measPosDesc;
    private String measType;
    private String equipment;
    private String equipDescription;
    private String characteristic;
    private String charactDescription;
    private int numCharacter;
    private int decPlaces;
    private String charUnit;
    private Date readingDate;
    
    
    public PM4_Datos_Medicion() {
        initComponents();    
        addEvents();     
    }

    private void addEvents(){
        
        setDateFormat();
        measPointType();
        measPointUnit();
        setReadingTime();
        buttonSetTime();
        dateChangeEvent();
        saveButton();
        exitButton();

    }

    private void setDateFormat() {

        readingDateDCh.setDateFormatString("MM.dd.yyyy");
        ((JTextFieldDateEditor) readingDateDCh.getDateEditor()).setFont(new Font("Roboto Light", Font.PLAIN, 12));

    }
    private void setReadingTime() {

        String times = time.format(Calendar.getInstance().getTime());
        timeTB.setText(times);

    }
    private void buttonSetTime() {
        buttonTime.addActionListener((e) -> {

            TimePicker time = new TimePicker(getSuperFrame(), buttonTime);
            time.setJTextField(timeTB);
            time.setVisible(true);

        });
    }

    private boolean isAllFieldsCompleted() {

        boolean completed = false;

        if (!(measPointTB.getText().isEmpty()
                | measPointPosTB.getText().isEmpty()
                | equipmentLB.getText().isEmpty()
                | equipDescLB.getText().isEmpty()
                | characteristicTB.getText().isEmpty()
                | readingDateDCh.getDate() == null
                | timeTB.getText().isEmpty()
                | measUnitTB.getText().isEmpty()
                | irdTB.getText().isEmpty()
                | measuredValueTB.getText().isEmpty()
                | theoricalValueTB.getText().isEmpty()
                | textDocumentTB.getText().isEmpty()
                | createdByTB.getText().isEmpty()
                | createdOnTB.getText().isEmpty()
                | irdPreviousTB.getText().isEmpty()
                | daysSinceLastRegTB.getText().isEmpty()
                | previousValueTB.getText().isEmpty())) {

            completed = true;
        }
        return completed;
    }
    
    private void executeCreate() {

        if (!isAllFieldsCompleted()) {
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            return;
        }

        if (!readyToCreate) {
            new SystemMessages(NOT.msg(NOT.CHECK_DATA_PROPERLY), TypeMessage.WARNING);
            return;
        }

        if (!timeOk) {
            new SystemMessages(NOT.msg(NOT.SHORT_TIME_REGISTRATIONS), TypeMessage.WARNING);
            return;
        }

        createMeasurementDocument();

    }
    private void createMeasurementDocument() {
        
        Create_Measurement_Document cmd = new Create_Measurement_Document();

        cmd.setMeasPoint(Integer.parseInt(getMeasPoint()));
        cmd.setMeasPosition(getMeasPosition());
        cmd.setMeasPosDesc(getMeasPosDesc());
        cmd.setMeasTypeCode(getMeasType());
        cmd.setEquipment(getEquipment());
        cmd.setEquipDesc(getEquipDescription());
        cmd.setCharacteristic(getCharacteristic());
        cmd.setReadingDate(date.format(getReadingDate()));
        cmd.setmUnit(measUnitTB.getText());
        cmd.setIrd(Double.parseDouble(irdTB.getText()));
        cmd.setMeasuredValue(formatDoubleNumbers(measuredValueTB.getText()));
        cmd.setTime(timeTB.getText());
        cmd.setTheoricalValue(formatDoubleNumbers(theoricalValueTB.getText()));
        cmd.setText(textDocumentTB.getText());
        cmd.setCreatedBy(createdByTB.getText());
        cmd.setCreatedOn(createdOnTB.getText());
        cmd.setIrdPrev(Double.parseDouble(irdPreviousTB.getText()));
        cmd.setDaysLastReg(Double.parseDouble(daysSinceLastRegTB.getText()));
        cmd.setPreviousValue(formatDoubleNumbers(previousValueTB.getText()));
        cmd.setStatus(true);

        cmd.createMeasDoc();

        docNumTB.setText(String.valueOf(cmd.getMeasNumDoc()));
        disableComponents();
        new SystemMessages(NOT.msg(NOT.DOCUMENT_CREATED) + ":" + cmd.getMeasNumDoc(), TypeMessage.SUCCESS);
    }

    
    private void disableComponents() {

        ((JTextFieldDateEditor) readingDateDCh.getDateEditor()).setEditable(false);
        readingDateDCh.getCalendarButton().setEnabled(false);

        buttonTime.setEnabled(false);
        measuredValueTB.setEditable(false);
        textDocumentTB.setEditable(false);
        measuredValueTB.setEditable(false);
        saveButton.setEnabled(false);

    }
    public void setValues() {

        measPointTB.setText(getMeasPoint());
        measPointPosTB.setText(getMeasPosition());
        measPosDescription.setText(getMeasPosDesc());
        measTypeTB.setText(getMeasType());
        equipmentLB.setText(getEquipment());
        equipDescLB.setText(getEquipDescription());
        characteristicTB.setText(getCharacteristic());
        charactDescLB.setText(getCharactDescription());
        readingDateDCh.setDate(getReadingDate());
        measUnitTB.setText(getCharUnit());

        irdPreviousTB.setText(decFormat().format(IRD.getLastIRD(getMeasPoint())));
        daysSinceLastRegTB.setText(decFormat().format(getDaysCountSinceLastReg()));
        theoricalValueTB.setText(decFormat().format(getTheoreticalValue()));

//        createdByTB.setText(LyraWorkspace.getUserName());
        createdOnTB.setText(date.format(Calendar.getInstance().getTime()));
        previousValueTB.setText(decFormat().format(IRD.getPreviousValue(getMeasPoint())));

        measuredValueNum();

    }

    
    private boolean nums = true;
    private boolean dec = false;
    private int cant = 0;

    
    //Metodo de procesamiento numerico del valor a ingresar
    private void measuredValueNum() {

        measuredValueTB.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();

                if (getDecPlaces() > 0) {

                    if (c == '.' && measuredValueTB.getText().contains(".")) {
                        e.consume();
                    } else if (c == '.' && !measuredValueTB.getText().contains(".")) {
                        nums = false;
                        dec = true;
                        cant = 0;
                    }

                    if (!Character.isDigit(c) && c != '.') {
                        e.consume();
                    }

                    if (nums) {
                        if (cant == getNumCharacter()) {
                            e.consume();
                        } else if (Character.isDigit(c)) {
                            cant++;
                        }
                    }

                    if (dec) {
                        if (cant == getDecPlaces()) {
                            e.consume();
                        } else if (Character.isDigit(c)) {
                            cant++;
                        }
                    }
                } else {
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                    if (cant == getNumCharacter()) {
                        e.consume();
                    } else if (Character.isDigit(c)) {
                        cant++;
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

                    measuredValueTB.setText(null);
                    irdTB.setText(null);
                    nums = true;
                    dec = false;
                    cant = 0;
                    readyToCreate = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(!measuredValueTB.getText().isEmpty()){
                        updateCalculations();
                    }
                }
            }
        });

        measuredValueTB.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!measuredValueTB.getText().isEmpty()){
                     updateCalculations();
                }
            }

        });

    }
    
    private void dateChangeEvent(){
        
      readingDateDCh.addPropertyChangeListener((PropertyChangeEvent evt) -> {
          if(evt.getPropertyName().equals("date")){
              
              updateCalculations();
          }
      });
      
      timeTB.getDocument().addDocumentListener(new DocumentListener(){
          @Override
          public void insertUpdate(DocumentEvent e) {
              updateCalculations();
          }

          @Override
          public void removeUpdate(DocumentEvent e) {
          
          }

          @Override
          public void changedUpdate(DocumentEvent e) {
              updateCalculations();
          }
          
      });
        
        
    }

    private void updateCalculations() {

        daysSinceLastRegTB.setText(decFormat().format(getDaysCountSinceLastReg()));
        theoricalValueTB.setText(decFormat().format(getTheoreticalValue()));
        irdTB.setText(decFormat().format(getDailyPerformanceIndexValue()));

        if (!measuredValueTB.getText().isEmpty()) {
            double num = Double.parseDouble(measuredValueTB.getText().replace(",", ""));
            measuredValueTB.setText(decFormat().format(num));
        }

        if (!irdTB.getText().isEmpty() && !measuredValueTB.getText().isEmpty()) {

            double numIRD = Double.parseDouble(irdTB.getText().replace(",", ""));

            if (numIRD >= 0) {
                readyToCreate = true;
            } else {
                readyToCreate = false;
            }
        }

        if (getDaysCountSinceLastReg() >= hour | firstRecord) {
            timeOk = true;
        } else {
            timeOk = false;
        }

    }

    
    private double getDailyPerformanceIndexValue() {

        double ird = 0;

        if (!previousValueTB.getText().isEmpty() && !measuredValueTB.getText().isEmpty()) {

            double lValue = Double.parseDouble(previousValueTB.getText());
            double mValue = Double.parseDouble(measuredValueTB.getText());

            ird = (mValue - lValue) / getDaysCountSinceLastReg();

            if (ird == Double.POSITIVE_INFINITY) {
                ird = 0;
            }else if (ird == Double.NEGATIVE_INFINITY) {
                ird = 0;
            }
        }

        return ird;
    }
    private double getDaysCountSinceLastReg() {

        double dayCount = 0;

        String lastDate = IRD.getMaxLastReading(getMeasPoint());

        if (lastDate != null) {

            String currTime = timeTB.getText();
            String currDate = date.format(readingDateDCh.getDate());

            String currentDate = currDate + " " + currTime;

            if (lastDate != null && currDate != null) {

                try {
                    Date lDate = dateTime.parse(lastDate);
                    Date cDate = dateTime.parse(currentDate);

                    long timeDif = cDate.getTime() - lDate.getTime();

                    TimeUnit und = TimeUnit.MINUTES;

                    dayCount = ((und.convert(timeDif, TimeUnit.MILLISECONDS) / 60.00) / 24.00);

                } catch (ParseException ex) {
                    Logger.getLogger(PM4_Datos_Medicion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }else{
            firstRecord = true;
        }

        return dayCount;
    }
    private double getTheoreticalValue() {

        double tValue = 0;

        if (IRD.getLastIRD(getMeasPoint()) > 0) {

            double lValue = IRD.getPreviousValue(getMeasPoint());
            double lIRD = IRD.getLastIRD(getMeasPoint());
            double dayCount = getDaysCountSinceLastReg();

            double plusValue = (lIRD * dayCount) + lValue;

            tValue = plusValue;
        }

        return tValue;
    }

  

    private void measPointType(){
        measTypeTB.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                
                String measTypeCode = measTypeTB.getText();
                descripcionCatalogoLB.setText(MeasurementPointTypes.getDescriptionCodeByCode(measTypeCode));

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                
                descripcionCatalogoLB.setText(null);
            
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            
                String measTypeCode = measTypeTB.getText();
                descripcionCatalogoLB.setText(MeasurementPointTypes.getDescriptionCodeByCode(measTypeCode));
            
            }
            
        });
    }
    private void measPointUnit() {
        
        measUnitTB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String code = measUnitTB.getText();
                charUnitDescLB.setText(UnitsOfMeasurement.getDescriptionByCode(code));
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                charUnitDescLB.setText(null);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                String code = measUnitTB.getText();
                charUnitDescLB.setText(UnitsOfMeasurement.getDescriptionByCode(code));
            }
            
        });
        
    }
    
    private double formatDoubleNumbers(String number) {
        return Double.parseDouble(number.replace(",", ""));
    }
    
    
    //Boton para crear el documento
    private void saveButton() {
        saveButton.addActionListener((e) -> {
            
            executeCreate();

        });
    }
    
    
    
    public String getMeasPoint() {
        return measPoint;
    }
    public String getMeasPosition() {
        return measPosition;
    }
    public String getMeasPosDesc() {
        return measPosDesc;
    }
    public String getMeasType() {
        return measType;
    }
    public String getEquipment() {
        return equipment;
    }
    public String getEquipDescription() {
        return equipDescription;
    }
    public String getCharacteristic() {
        return characteristic;
    }
    public int getDecPlaces() {
        return decPlaces;
    }
    public String getCharUnit() {
        return charUnit;
    }
    
    
    public void setMeasPoint(String measPoint) {
        this.measPoint = measPoint;
    }
    public void setMeasPosition(String measPosition) {
        this.measPosition = measPosition;
    }
    public void setMeasPosDesc(String measPosDesc) {
        this.measPosDesc = measPosDesc;
    }
    public void setMeasType(String measType) {
        this.measType = measType;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    public void setEquipDescription(String equipDescription) {
        this.equipDescription = equipDescription;
    }
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
    public void setDecPlaces(int decPlaces) {
        this.decPlaces = decPlaces;
    }
    public void setCharUnit(String charUnit) {
        this.charUnit = charUnit;
    }
    
    
    public Date getReadingDate() {
        return readingDate;
    }
    public void setReadingDate(Date readingDate) {
        this.readingDate = readingDate;
    }
    public int getNumCharacter() {
        return numCharacter;
    }
    public void setNumCharacter(int numCharacter) {
        this.numCharacter = numCharacter;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_PM1 = new javax.swing.JMenuBar();
        MENU_PUNTO_MEDIDA = new javax.swing.JMenu();
        MENU_ITEM_GUARDAR = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_NUEVO = new javax.swing.JMenuItem();
        MENU_ITEM_MODIFICAR = new javax.swing.JMenuItem();
        MENU_ITEM_VISUALIZAR = new javax.swing.JMenuItem();
        separador1 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_EQUIPOS = new javax.swing.JMenuItem();
        separador3 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_CARACTERISTICAS = new javax.swing.JMenu();
        MENU_ITEM_CARACTERISTICAS = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        MENU_ITEM_MANUAL = new javax.swing.JMenuItem();
        MENU_ITEM_ACERCA_DE = new javax.swing.JMenuItem();
        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        exitButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        saveButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        lastDocsButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        generalData3 = new javax.swing.JPanel();
        labelTaxonomia3 = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        irdTB = new javax.swing.JTextField();
        measuredValueTB = new javax.swing.JTextField();
        theoricalValueTB = new javax.swing.JTextField();
        textDocumentTB = new javax.swing.JTextField();
        createdByTB = new javax.swing.JTextField();
        createdOnTB = new javax.swing.JTextField();
        irdPreviousTB = new javax.swing.JTextField();
        daysSinceLastRegTB = new javax.swing.JTextField();
        previousValueTB = new javax.swing.JTextField();
        textoLB15 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB16 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB17 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB18 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB19 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB20 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB21 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB22 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB23 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        generalData2 = new javax.swing.JPanel();
        labelTaxonomia2 = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        valorTeoricoLB1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB2 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        characteristicTB = new javax.swing.JTextField();
        readingDateDCh = new com.toedter.calendar.JDateChooser();
        textoLB3 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        measUnitTB = new javax.swing.JTextField();
        textoLB4 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        equipDescLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        charUnitDescLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        timeTB = new javax.swing.JTextField();
        buttonTime = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        charactDescLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        equipmentLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        valorTeoricoLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        docNumTB = new javax.swing.JTextField();
        measPointTB = new javax.swing.JTextField();
        textoLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        catalogoLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        measTypeTB = new javax.swing.JTextField();
        descripcionCatalogoLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        textoLB5 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        measPointPosTB = new javax.swing.JTextField();
        measPosDescription = new com.simplecore.erp.gui.components.labels.JLabelHQ();

        MENU_PUNTO_MEDIDA.setText("Punto de Medida");

        MENU_ITEM_GUARDAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_GUARDAR.setText("Guardar Punto Medida");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_GUARDAR);
        MENU_PUNTO_MEDIDA.add(separador);

        MENU_ITEM_NUEVO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_NUEVO.setText("Nuevo punto medida");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_NUEVO);

        MENU_ITEM_MODIFICAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_MODIFICAR.setText("Modificar punto medida");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_MODIFICAR);

        MENU_ITEM_VISUALIZAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_VISUALIZAR.setText("Visualizar punto medida");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_VISUALIZAR);
        MENU_PUNTO_MEDIDA.add(separador1);

        MENU_ITEM_EQUIPOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_EQUIPOS.setText("Equipos");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_EQUIPOS);
        MENU_PUNTO_MEDIDA.add(separador3);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_SALIR);

        menuBar_PM1.add(MENU_PUNTO_MEDIDA);

        MENU_CARACTERISTICAS.setText("Caracteristicas");

        MENU_ITEM_CARACTERISTICAS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_CARACTERISTICAS.setText("Ver caracteristicas");
        MENU_CARACTERISTICAS.add(MENU_ITEM_CARACTERISTICAS);

        menuBar_PM1.add(MENU_CARACTERISTICAS);

        MENU_AYUDA.setText("Ayuda");

        MENU_ITEM_MANUAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MENU_ITEM_MANUAL.setText("Manual Instrucciones");
        MENU_AYUDA.add(MENU_ITEM_MANUAL);

        MENU_ITEM_ACERCA_DE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        MENU_ITEM_ACERCA_DE.setText("Acercar de Puntos Medida");
        MENU_AYUDA.add(MENU_ITEM_ACERCA_DE);

        menuBar_PM1.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        exitButton.setBackground(new java.awt.Color(226, 210, 144));
        exitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        saveButton.setBackground(new java.awt.Color(226, 210, 144));
        saveButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        lastDocsButton.setBackground(new java.awt.Color(226, 210, 144));
        lastDocsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lastDocsButton.setText("Last Measuring Documentations");
        lastDocsButton.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        lastDocsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(lastDocsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lastDocsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(242, 242, 242));
        panelFondo.setColor2(new java.awt.Color(242, 242, 242));

        generalData3.setBackground(new java.awt.Color(202, 219, 236));
        generalData3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelTaxonomia3.setText("Reading and details");
        labelTaxonomia3.setColorBordes(new java.awt.Color(117, 141, 163));
        labelTaxonomia3.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelTaxonomia3.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        irdTB.setEditable(false);
        irdTB.setBackground(new java.awt.Color(202, 219, 236));
        irdTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measuredValueTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        theoricalValueTB.setEditable(false);
        theoricalValueTB.setBackground(new java.awt.Color(202, 219, 236));
        theoricalValueTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textDocumentTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        createdByTB.setEditable(false);
        createdByTB.setBackground(new java.awt.Color(202, 219, 236));
        createdByTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        createdOnTB.setEditable(false);
        createdOnTB.setBackground(new java.awt.Color(202, 219, 236));
        createdOnTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        irdPreviousTB.setEditable(false);
        irdPreviousTB.setBackground(new java.awt.Color(202, 219, 236));
        irdPreviousTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        daysSinceLastRegTB.setEditable(false);
        daysSinceLastRegTB.setBackground(new java.awt.Color(202, 219, 236));
        daysSinceLastRegTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        previousValueTB.setEditable(false);
        previousValueTB.setBackground(new java.awt.Color(202, 219, 236));
        previousValueTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB15.setText("Daily Perf.Index");
        textoLB15.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB16.setText("Measured value");
        textoLB16.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB17.setText("Theoretical value");
        textoLB17.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB18.setText("Text");
        textoLB18.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB19.setText("Created By");
        textoLB19.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB20.setText("Created On");
        textoLB20.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB21.setText("PrevDailyPerf.Index");
        textoLB21.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB22.setText("DaysSinceLastReg");
        textoLB22.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB23.setText("Previous value");
        textoLB23.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalData3Layout = new javax.swing.GroupLayout(generalData3);
        generalData3.setLayout(generalData3Layout);
        generalData3Layout.setHorizontalGroup(
            generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTaxonomia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(generalData3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoLB15, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLB16, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLB17, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLB18, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLB19, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLB20, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(createdOnTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addComponent(createdByTB, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(generalData3Layout.createSequentialGroup()
                        .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textDocumentTB, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, generalData3Layout.createSequentialGroup()
                                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(measuredValueTB)
                                        .addComponent(theoricalValueTB, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(irdTB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textoLB22, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(textoLB21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textoLB23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, 0)
                        .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(irdPreviousTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(daysSinceLastRegTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(previousValueTB, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        generalData3Layout.setVerticalGroup(
            generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalData3Layout.createSequentialGroup()
                .addComponent(labelTaxonomia3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(irdTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLB21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(irdPreviousTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(measuredValueTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLB22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(daysSinceLastRegTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(theoricalValueTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLB23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previousValueTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textDocumentTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdByTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(generalData3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(createdOnTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoLB20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        generalData2.setBackground(new java.awt.Color(202, 219, 236));
        generalData2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelTaxonomia2.setText("General data");
        labelTaxonomia2.setColorBordes(new java.awt.Color(117, 141, 163));
        labelTaxonomia2.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelTaxonomia2.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        valorTeoricoLB1.setText("Equipment");
        valorTeoricoLB1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB1.setText("Description");
        textoLB1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB2.setText("Characteristic");
        textoLB2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        characteristicTB.setEditable(false);
        characteristicTB.setBackground(new java.awt.Color(202, 219, 236));
        characteristicTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB3.setText("Reading date");
        textoLB3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measUnitTB.setEditable(false);
        measUnitTB.setBackground(new java.awt.Color(202, 219, 236));
        measUnitTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB4.setText("UM");
        textoLB4.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        equipDescLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        equipDescLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        charUnitDescLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        charUnitDescLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        timeTB.setEditable(false);
        timeTB.setBackground(new java.awt.Color(255, 255, 255));
        timeTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        buttonTime.setBackground(new java.awt.Color(226, 210, 144));
        buttonTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/time.png"))); // NOI18N

        charactDescLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        charactDescLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        equipmentLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        equipmentLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout generalData2Layout = new javax.swing.GroupLayout(generalData2);
        generalData2.setLayout(generalData2Layout);
        generalData2Layout.setHorizontalGroup(
            generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTaxonomia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(generalData2Layout.createSequentialGroup()
                .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalData2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalData2Layout.createSequentialGroup()
                                .addComponent(textoLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equipDescLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(generalData2Layout.createSequentialGroup()
                                .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoLB4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoLB2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoLB3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(generalData2Layout.createSequentialGroup()
                                        .addComponent(valorTeoricoLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(equipmentLB, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(generalData2Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalData2Layout.createSequentialGroup()
                                .addComponent(measUnitTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(charUnitDescLB, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(generalData2Layout.createSequentialGroup()
                                .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, generalData2Layout.createSequentialGroup()
                                        .addComponent(readingDateDCh, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeTB))
                                    .addComponent(characteristicTB, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(generalData2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(buttonTime, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(generalData2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(charactDescLB, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        generalData2Layout.setVerticalGroup(
            generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalData2Layout.createSequentialGroup()
                .addComponent(labelTaxonomia2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(valorTeoricoLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equipmentLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalData2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(textoLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(generalData2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(equipDescLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(characteristicTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(charactDescLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readingDateDCh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonTime, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(generalData2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(measUnitTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textoLB4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(charUnitDescLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        valorTeoricoLB.setText("Document");
        valorTeoricoLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        docNumTB.setEditable(false);
        docNumTB.setBackground(new java.awt.Color(202, 219, 236));
        docNumTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measPointTB.setEditable(false);
        measPointTB.setBackground(new java.awt.Color(202, 219, 236));
        measPointTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB.setText("MeasPoint");
        textoLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        catalogoLB.setText("Cat.");
        catalogoLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measTypeTB.setEditable(false);
        measTypeTB.setBackground(new java.awt.Color(202, 219, 236));
        measTypeTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        descripcionCatalogoLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionCatalogoLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB5.setText("MeasPosition");
        textoLB5.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measPointPosTB.setEditable(false);
        measPointPosTB.setBackground(new java.awt.Color(202, 219, 236));
        measPointPosTB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measPosDescription.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        measPosDescription.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(generalData3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(measPosDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelFondoLayout.createSequentialGroup()
                                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(valorTeoricoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textoLB5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, 0)
                                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(measPointTB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelFondoLayout.createSequentialGroup()
                                            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(measPointPosTB)
                                                .addComponent(docNumTB, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                                            .addGap(94, 94, 94)
                                            .addComponent(catalogoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(measTypeTB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(descripcionCatalogoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(generalData2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(352, Short.MAX_VALUE))))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(valorTeoricoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(docNumTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catalogoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionCatalogoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(measTypeTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(measPointTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(measPointPosTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(measPosDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(generalData2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generalData3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
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
                .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_CARACTERISTICAS;
    public static javax.swing.JMenuItem MENU_ITEM_ACERCA_DE;
    public static javax.swing.JMenuItem MENU_ITEM_CARACTERISTICAS;
    public static javax.swing.JMenuItem MENU_ITEM_EQUIPOS;
    public static javax.swing.JMenuItem MENU_ITEM_GUARDAR;
    public static javax.swing.JMenuItem MENU_ITEM_MANUAL;
    public static javax.swing.JMenuItem MENU_ITEM_MODIFICAR;
    public static javax.swing.JMenuItem MENU_ITEM_NUEVO;
    public static javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem MENU_ITEM_VISUALIZAR;
    public static javax.swing.JMenu MENU_PUNTO_MEDIDA;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ buttonTime;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined catalogoLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ charUnitDescLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ charactDescLB;
    protected static javax.swing.JTextField characteristicTB;
    protected static javax.swing.JTextField createdByTB;
    protected static javax.swing.JTextField createdOnTB;
    protected static javax.swing.JTextField daysSinceLastRegTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ descripcionCatalogoLB;
    protected static javax.swing.JTextField docNumTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ equipDescLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ equipmentLB;
    private com.simplecore.erp.gui.components.labels.JButtonHQ exitButton;
    private javax.swing.JPanel generalData2;
    private javax.swing.JPanel generalData3;
    protected static javax.swing.JTextField irdPreviousTB;
    protected static javax.swing.JTextField irdTB;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelTaxonomia2;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelTaxonomia3;
    private com.simplecore.erp.gui.components.labels.JButtonHQ lastDocsButton;
    protected static javax.swing.JTextField measPointPosTB;
    protected static javax.swing.JTextField measPointTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ measPosDescription;
    private javax.swing.JTextField measTypeTB;
    protected static javax.swing.JTextField measUnitTB;
    protected static javax.swing.JTextField measuredValueTB;
    public static javax.swing.JMenuBar menuBar_PM1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    protected static javax.swing.JTextField previousValueTB;
    protected static com.toedter.calendar.JDateChooser readingDateDCh;
    private com.simplecore.erp.gui.components.labels.JButtonHQ saveButton;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JPopupMenu.Separator separador1;
    private javax.swing.JPopupMenu.Separator separador3;
    protected static javax.swing.JTextField textDocumentTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB1;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB15;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB16;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB17;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB18;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB19;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB2;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB20;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB21;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB22;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB23;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB3;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB4;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB5;
    protected static javax.swing.JTextField theoricalValueTB;
    private javax.swing.JTextField timeTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined valorTeoricoLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined valorTeoricoLB1;
    // End of variables declaration//GEN-END:variables
 
    private JPanel panelAnterior;
    public void setPanelAnterior(JPanel panel){
        panelAnterior = panel;
    }
    
    private void exitButton() {
        exitButton.addActionListener((e) -> {

            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

        });
    }    
    
    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }

    public String getCharactDescription() {
        return charactDescription;
    }

    public void setCharactDescription(String charactDescription) {
        this.charactDescription = charactDescription;
    }
      
    private DecimalFormat decFormat() {

        DecimalFormat df;

        if (getDecPlaces() > 0) {

            String ceros = "";

            if (getDecPlaces() > 0) {
                for (int i = 0; i < getDecPlaces(); i++) {
                    ceros = ceros + "0";
                }
                ceros = "." + ceros;
            }

            df = new DecimalFormat("#,##0" + ceros);
        } else {
            df = new DecimalFormat("#,##0");
        }

        return df;
    }
    
}
