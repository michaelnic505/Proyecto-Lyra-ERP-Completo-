package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.service.CharacteristicService;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.gui.CharacteristicList;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.CharacteristicsOptionsData;
import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils.UnitsOfMeasurement;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;


public class PM1_Creacion_Punto_Medidas extends javax.swing.JPanel {
    
    private CharacteristicService characteristicService;
    
    public PM1_Creacion_Punto_Medidas() {

        initComponents();
        addEvents();
        startServices();
    }

    private void startServices() {
        characteristicService = new CharacteristicService();
    }

    private void addEvents() {

        button_CharactsList();
        button_CreateMeasPoint();
        charactListEvents();
        setNumJTextField();
        btnSalir();
    
    }

    private String objetoPuntoMedida;
    private String codigoEquipo;
    private String descripcionEquipo;
    private String codigoCatalogoPuntoMedida;
    private String descripcionTipoContador;
    private boolean esContador;
    
  
    public void setValoresEnCampos(){
 
        codigoEquipoLB2.setText(codigoEquipo);
        descripcionEquipoLB2.setText(descripcionEquipo);
        measPointType.setText(codigoCatalogoPuntoMedida);
        measPointTypeDescLB.setText(descripcionTipoContador);
        cbEsContador.setSelected(esContador);
        
    }

    private void button_CharactsList() {

        sbCaracteristica.getButton().addActionListener((e) -> {

            CharacteristicList list = new CharacteristicList(getSuperFrame(),characteristicService);
            list.setCampos(sbCaracteristica.getTextBox(), null);
            list.setVisible(true);

        });

    }
    private void charactListEvents(){
        
        sbCaracteristica.getTextBox().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(!sbCaracteristica.getTextBox().getText().isEmpty()){
                        
                        getCharacteristicData();
                        sbCaracteristica.getTextBox().setEditable(false);
                        sbCaracteristica.getButton().setEnabled(false);
                        
                    }
                }
            }
            
        });

        
    }
    
    private boolean character = false;
    private void getCharacteristicData() {

        String charact = sbCaracteristica.getTextBox().getText();

        if (characteristicService.doesCharacteristicExist(charact)) {

            character = true;

            Characteristic_data chData = new Characteristic_data();
            chData.getCharacteriticData(charact);

            String uMeas = chData.getUnitMeas();
            String decimalPl = chData.getDecimalPlaces();
            String codeGrp = chData.getCodeGroup();

            descripcionCaracteristicaLB.setText(chData.getDescription());
            unidadCaracteristicaTB.setText(uMeas);
            decimalPlacesTB.setText(decimalPl);
            codeGroupTB.setText(codeGrp);

            descripcionUnidadCaractLB.setText(UnitsOfMeasurement.getDescriptionByCode(uMeas));
            descripcionCodigoGrupoLB.setText(CharacteristicsOptionsData.getCharGroupDescriptionByCode(codeGrp));
            
            unidadMedidaTB.setText(uMeas);

        } else {

            sbCaracteristica.getTextBox().requestFocus();
            
            descripcionCaracteristicaLB.setText(null);

            unidadCaracteristicaTB.setText(null);
            decimalPlacesTB.setText(null);
            codeGroupTB.setText(null);

            descripcionUnidadCaractLB.setText(null);
            descripcionCodigoGrupoLB.setText(null);
            
            unidadMedidaTB.setText(null);

            new SystemMessages(NOT.msg(NOT.CHARACTERISTIC_DOES_NOT_EXIST), TypeMessage.ERROR);
            character = false;
        }
    }


    private int measurePoint;
    private String objMeasPoint;
    private String measPosition;
    private String measType;
    private String description;
    private int equipment;
    private String equipmentDescription;
    private String characteristic;
    private String charactUnit;
    private int decimalPlaces;
    private boolean isCounter;
    private String codeGroup;
    private String assembly;
    private String authorizGroup;
    private String targetValue;
    private String text;
    private boolean status;
    
    
    private boolean isDataCompleted(){
        
        boolean completed = false;
        
        if(!(posicionPuntoMedidaTB.getText().isEmpty()
                
                |measPointType.getText().isEmpty()
                |descripcionPuntoMedidaTB.getText().isEmpty()
                |codigoEquipoLB2.getText().isEmpty()
                |descripcionEquipoLB2.getText().isEmpty()
                |sbCaracteristica.getTextBox().getText().isEmpty()
                |unidadCaracteristicaTB.getText().isEmpty()
                |decimalPlacesTB.getText().isEmpty()
                |codeGroupTB.getText().isEmpty())){
            
            if(!posicionPuntoMedidaTB.getText().trim().contains(" ")){
           
                completed = true;
            
            }else{
                
                completed = false;
                posicionPuntoMedidaTB.requestFocus();
                new SystemMessages(NOT.msg(NOT.CONTAINS_SPACES), TypeMessage.WARNING);
                
            }
            
        }else{
           completed = false; 
           new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
        }
        
        
        return completed;
    }
    private void setValues() {
        
            objMeasPoint = objetoPuntoMedida;
            measPosition = posicionPuntoMedidaTB.getText();
            measType = measPointType.getText();
            description = descripcionPuntoMedidaTB.getText();
            equipment = Integer.parseInt(codigoEquipoLB2.getText());
            equipmentDescription = descripcionEquipoLB2.getText();
            characteristic = sbCaracteristica.getTextBox().getText();
            charactUnit = unidadCaracteristicaTB.getText();
            decimalPlaces = Integer.parseInt(decimalPlacesTB.getText());
            isCounter = cbEsContador.isSelected();
            codeGroup = codeGroupTB.getText();
            assembly = assemblyTB.getText();
            authorizGroup = sbGrupoAutorizacion.getTextBox().getText();
            targetValue = valorTeoricoTB.getText();
            text = textTB.getText();
            status = true;

    }
    private void button_CreateMeasPoint() {

        saveButton.addActionListener((e) -> {
            if (isDataCompleted()) {

                setValues();

                Create_Measure_Point nMeas = new Create_Measure_Point();

                nMeas.setMeasPosition(measPosition);
                nMeas.setObjMeasPoint(objMeasPoint);
                nMeas.setMeasType(measType);
                nMeas.setDescription(description);
                nMeas.setEquipment(equipment);
                nMeas.setEquipmentDescription(equipmentDescription);
                nMeas.setCharacteristic(characteristic);
                nMeas.setCharactUnit(charactUnit);
                nMeas.setDecimalPlaces(decimalPlaces);
                nMeas.setIsCounter(isCounter);
                nMeas.setCodeGroup(codeGroup);
                nMeas.setAssembly(assembly);
                nMeas.setAuthorizGroup(authorizGroup);
                nMeas.setTargetValue(targetValue);
                nMeas.setText(text);
                nMeas.setStatus(status);

                nMeas.createMeasPoint();
                
                measurePoint = nMeas.getMeasurePoint();
                puntoMedidaTB.setText(String.valueOf(measurePoint));
                
                new SystemMessages(NOT.msg(NOT.MEASUREMENT_POINT_CREATED) + " " + measurePoint, TypeMessage.SUCCESS);

                disableComponent();

                PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
            }

        });

    }
    private void setNumJTextField(){
        valorTeoricoTB.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                
                char chr = e.getKeyChar();
                
                if(!Character.isDigit(chr)){
                    e.consume();
                }
                
            }
            
        });
    }

    private void disableComponent(){
        
        saveButton.setEnabled(false);
        posicionPuntoMedidaTB.setEditable(false);
        descripcionPuntoMedidaTB.setEditable(false);
        assemblyTB.setEditable(false);
        sbGrupoAutorizacion.getTextBox().setEditable(false);
        valorTeoricoTB.setEditable(false);
        textTB.setEditable(false);
        cbEsContador.setEnabled(false);
        
        
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
        jLabel_HQ_Subrayado1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        puntoMedidaTB = new javax.swing.JTextField();
        jLabel_HQ_Subrayado2 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        posicionPuntoMedidaTB = new javax.swing.JTextField();
        jLabel_HQ_Subrayado3 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        descripcionPuntoMedidaTB = new javax.swing.JTextField();
        equipoLB1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoEquipoLB2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        descripcionEquipoLB1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        descripcionEquipoLB2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        catalogoLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        measPointType = new javax.swing.JTextField();
        measPointTypeDescLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        generalData = new javax.swing.JPanel();
        labelTaxonomia = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        caracteristicaLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sbCaracteristica = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        caracteristicaUnidadLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        unidadCaracteristicaTB = new javax.swing.JTextField();
        caracteristicaUnidadLB1 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        decimalPlacesTB = new javax.swing.JTextField();
        caracteristicaUnidadLB2 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codeGroupTB = new javax.swing.JTextField();
        caracteristicaUnidadLB3 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        assemblyTB = new javax.swing.JTextField();
        caracteristicaUnidadLB4 = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sbGrupoAutorizacion = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        cbEsContador = new javax.swing.JCheckBox();
        descripcionCaracteristicaLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        descripcionUnidadCaractLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        descripcionCodigoGrupoLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        descripcionGrupoAutorizacionLB = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        generalData1 = new javax.swing.JPanel();
        labelTaxonomia1 = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        valorTeoricoLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        textoLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        valorTeoricoTB = new javax.swing.JTextField();
        textTB = new javax.swing.JTextField();
        unidadMedidaTB = new javax.swing.JTextField();

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
                .addGap(30, 30, 30)
                .addComponent(lastDocsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastDocsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        jLabel_HQ_Subrayado1.setText("Measuring point");
        jLabel_HQ_Subrayado1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        puntoMedidaTB.setEditable(false);
        puntoMedidaTB.setBackground(new java.awt.Color(202, 219, 236));
        puntoMedidaTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        jLabel_HQ_Subrayado2.setText("MeasPosition");
        jLabel_HQ_Subrayado2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        posicionPuntoMedidaTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        jLabel_HQ_Subrayado3.setText("Description");
        jLabel_HQ_Subrayado3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        descripcionPuntoMedidaTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        equipoLB1.setText("Equipment");
        equipoLB1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codigoEquipoLB2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        codigoEquipoLB2.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        descripcionEquipoLB1.setText("Description");
        descripcionEquipoLB1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        descripcionEquipoLB2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionEquipoLB2.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        catalogoLB.setText("Cat.");
        catalogoLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        measPointType.setEditable(false);
        measPointType.setBackground(new java.awt.Color(202, 219, 236));

        measPointTypeDescLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        measPointTypeDescLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        generalData.setBackground(new java.awt.Color(202, 219, 236));
        generalData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelTaxonomia.setText("General data");
        labelTaxonomia.setColorBordes(new java.awt.Color(117, 141, 163));
        labelTaxonomia.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelTaxonomia.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        caracteristicaLB.setText("Characteristic");
        caracteristicaLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        caracteristicaUnidadLB.setText("CharactUnit");
        caracteristicaUnidadLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        unidadCaracteristicaTB.setEditable(false);
        unidadCaracteristicaTB.setBackground(new java.awt.Color(202, 219, 236));
        unidadCaracteristicaTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        caracteristicaUnidadLB1.setText("Decimal places");
        caracteristicaUnidadLB1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        decimalPlacesTB.setEditable(false);
        decimalPlacesTB.setBackground(new java.awt.Color(202, 219, 236));
        decimalPlacesTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        caracteristicaUnidadLB2.setText("Code group");
        caracteristicaUnidadLB2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        codeGroupTB.setEditable(false);
        codeGroupTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        caracteristicaUnidadLB3.setText("Assembly");
        caracteristicaUnidadLB3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        assemblyTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        caracteristicaUnidadLB4.setText("AuthorizGroup");
        caracteristicaUnidadLB4.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        cbEsContador.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        cbEsContador.setText("MeasPoint is counter");
        cbEsContador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        descripcionCaracteristicaLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionCaracteristicaLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        descripcionUnidadCaractLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionUnidadCaractLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        descripcionCodigoGrupoLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionCodigoGrupoLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        descripcionGrupoAutorizacionLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionGrupoAutorizacionLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout generalDataLayout = new javax.swing.GroupLayout(generalData);
        generalData.setLayout(generalDataLayout);
        generalDataLayout.setHorizontalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTaxonomia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(caracteristicaUnidadLB4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caracteristicaUnidadLB3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caracteristicaLB, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caracteristicaUnidadLB2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caracteristicaUnidadLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caracteristicaUnidadLB, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalDataLayout.createSequentialGroup()
                                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(assemblyTB, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(decimalPlacesTB, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 83, Short.MAX_VALUE))
                            .addGroup(generalDataLayout.createSequentialGroup()
                                .addComponent(sbGrupoAutorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descripcionGrupoAutorizacionLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(109, 109, 109)
                        .addComponent(cbEsContador))
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addComponent(sbCaracteristica, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descripcionCaracteristicaLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codeGroupTB, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unidadCaracteristicaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalDataLayout.createSequentialGroup()
                                .addComponent(descripcionUnidadCaractLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(250, 250, 250))
                            .addComponent(descripcionCodigoGrupoLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        generalDataLayout.setVerticalGroup(
            generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDataLayout.createSequentialGroup()
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addComponent(labelTaxonomia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(caracteristicaLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sbCaracteristica, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(descripcionCaracteristicaLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(caracteristicaUnidadLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(unidadCaracteristicaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(descripcionUnidadCaractLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cbEsContador)))
                .addGap(2, 2, 2)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(caracteristicaUnidadLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decimalPlacesTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(generalDataLayout.createSequentialGroup()
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(caracteristicaUnidadLB2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codeGroupTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionCodigoGrupoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(caracteristicaUnidadLB3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(assemblyTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(generalDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(caracteristicaUnidadLB4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sbGrupoAutorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(descripcionGrupoAutorizacionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        generalData1.setBackground(new java.awt.Color(202, 219, 236));
        generalData1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 168, 192), 2));

        labelTaxonomia1.setText("Target value");
        labelTaxonomia1.setColorBordes(new java.awt.Color(117, 141, 163));
        labelTaxonomia1.setColorRelleno(new java.awt.Color(136, 175, 198));
        labelTaxonomia1.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        valorTeoricoLB.setText("Target value");
        valorTeoricoLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        textoLB.setText("Text");
        textoLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        valorTeoricoTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        textTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        unidadMedidaTB.setEditable(false);
        unidadMedidaTB.setBackground(new java.awt.Color(202, 219, 236));
        unidadMedidaTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout generalData1Layout = new javax.swing.GroupLayout(generalData1);
        generalData1.setLayout(generalData1Layout);
        generalData1Layout.setHorizontalGroup(
            generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTaxonomia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(generalData1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTeoricoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textTB, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(generalData1Layout.createSequentialGroup()
                        .addComponent(valorTeoricoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(unidadMedidaTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        generalData1Layout.setVerticalGroup(
            generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalData1Layout.createSequentialGroup()
                .addComponent(labelTaxonomia1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(valorTeoricoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorTeoricoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unidadMedidaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(generalData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(equipoLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_HQ_Subrayado3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_HQ_Subrayado2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_HQ_Subrayado1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionEquipoLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(codigoEquipoLB2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionPuntoMedidaTB, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addGroup(panelFondoLayout.createSequentialGroup()
                                .addComponent(puntoMedidaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(162, 162, 162)
                                .addComponent(catalogoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(measPointType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(descripcionEquipoLB2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(posicionPuntoMedidaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(measPointTypeDescLB, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(generalData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(generalData1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(393, 393, 393))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel_HQ_Subrayado1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(puntoMedidaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catalogoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(measPointType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(measPointTypeDescLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel_HQ_Subrayado2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posicionPuntoMedidaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel_HQ_Subrayado3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionPuntoMedidaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(equipoLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoEquipoLB2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(descripcionEquipoLB1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEquipoLB2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(generalData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(generalData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
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
    private javax.swing.JTextField assemblyTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined caracteristicaLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined caracteristicaUnidadLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined caracteristicaUnidadLB1;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined caracteristicaUnidadLB2;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined caracteristicaUnidadLB3;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined caracteristicaUnidadLB4;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined catalogoLB;
    private javax.swing.JCheckBox cbEsContador;
    private javax.swing.JTextField codeGroupTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ codigoEquipoLB2;
    private javax.swing.JTextField decimalPlacesTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ descripcionCaracteristicaLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ descripcionCodigoGrupoLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined descripcionEquipoLB1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ descripcionEquipoLB2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ descripcionGrupoAutorizacionLB;
    private javax.swing.JTextField descripcionPuntoMedidaTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQ descripcionUnidadCaractLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined equipoLB1;
    private com.simplecore.erp.gui.components.labels.JButtonHQ exitButton;
    private javax.swing.JPanel generalData;
    private javax.swing.JPanel generalData1;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined jLabel_HQ_Subrayado1;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined jLabel_HQ_Subrayado2;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined jLabel_HQ_Subrayado3;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelTaxonomia;
    public static com.simplecore.erp.gui.components.labels.JLabelHQFraming labelTaxonomia1;
    private com.simplecore.erp.gui.components.labels.JButtonHQ lastDocsButton;
    private javax.swing.JTextField measPointType;
    private com.simplecore.erp.gui.components.labels.JLabelHQ measPointTypeDescLB;
    public static javax.swing.JMenuBar menuBar_PM1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private javax.swing.JTextField posicionPuntoMedidaTB;
    private javax.swing.JTextField puntoMedidaTB;
    private com.simplecore.erp.gui.components.labels.JButtonHQ saveButton;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbCaracteristica;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox sbGrupoAutorizacion;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JPopupMenu.Separator separador1;
    private javax.swing.JPopupMenu.Separator separador3;
    private javax.swing.JTextField textTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined textoLB;
    private javax.swing.JTextField unidadCaracteristicaTB;
    private javax.swing.JTextField unidadMedidaTB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined valorTeoricoLB;
    private javax.swing.JTextField valorTeoricoTB;
    // End of variables declaration//GEN-END:variables
 
    private JPanel panelAnterior;
    public void setPanelAnterior(JPanel panel){
        panelAnterior = panel;
    }

    public String getObjetoPuntoMedida() {
        return objetoPuntoMedida;
    }
    public String getCodigoEquipo() {
        return codigoEquipo;
    }
    public String getDescripcionEquipo() {
        return descripcionEquipo;
    }
    public String getCodigoCatalogoPuntoMedida() {
        return codigoCatalogoPuntoMedida;
    }
    public boolean isEsContador() {
        return esContador;
    }
    
    public void setObjetoPuntoMedida(String objetoPuntoMedida) {
        this.objetoPuntoMedida = objetoPuntoMedida;
    }
    public void setCodigoEquipo(String codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }
    public void setDescripcionEquipo(String descripcionEquipo) {
        this.descripcionEquipo = descripcionEquipo;
    }
    public void setCodigoCatalogoPuntoMedida(String codigoCatalogoPuntoMedida) {
        this.codigoCatalogoPuntoMedida = codigoCatalogoPuntoMedida;
    }
    public void setEsContador(boolean esContador) {
        this.esContador = esContador;
    }
    
        private void btnSalir() {
        exitButton.addActionListener((e) -> {
            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

        });
    }

    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }

    public String getDescripcionCategoria() {
        return descripcionTipoContador;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionTipoContador = descripcionCategoria;
    }

}
