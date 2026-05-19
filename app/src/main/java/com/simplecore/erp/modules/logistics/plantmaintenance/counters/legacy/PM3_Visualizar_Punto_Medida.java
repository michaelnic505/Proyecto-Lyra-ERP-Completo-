package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;


public class PM3_Visualizar_Punto_Medida extends javax.swing.JPanel {
    
    
    public PM3_Visualizar_Punto_Medida() {
        
        initComponents();    
        addEvents();     
    }

    private void addEvents(){
        
        button_MeasPointList();
        button_Proceed();
        btnSalir();
    
    }

    private void button_MeasPointList(){
        sbMeasurePoint.getButton().addActionListener((e)->{
            
            List_Measurement_Points list = new List_Measurement_Points(getSuperFrame());
            list.setCampos(sbMeasurePoint.getTextBox(), null);
            list.setVisible(true);
       
        });
    }

    private void button_Proceed() {
        cmdProceder.addActionListener((e) -> {

            if (!sbMeasurePoint.getTextBox().getText().isEmpty()) {

                String meas = sbMeasurePoint.getTextBox().getText().trim();

                if (IsMeasPointCreated.measPointExists(meas)) {
                  
                    Load_MeasPoint_Data lmd = new Load_MeasPoint_Data();
                    lmd.loadData(meas);                    
                    
                    PM3_Visualizacion_Punto_Medida mpm = new PM3_Visualizacion_Punto_Medida();
                    
                    mpm.setPanelAnterior(this);
                    mpm.setMeasurePoint(lmd.getMeasurePoint());
                    mpm.setObjMeasPoint(lmd.getObjMeasPoint());
                    mpm.setMeasPosition(lmd.getMeasPosition());
                    mpm.setMeasType(lmd.getMeasType());
                    mpm.setDescription(lmd.getDescription());
                    mpm.setEquipment(lmd.getEquipment());
                    mpm.setEquipmentDescription(lmd.getEquipmentDescription());
                    mpm.setCharacteristic(lmd.getCharacteristic());
                    mpm.setCharactUnit(lmd.getCharactUnit());
                    mpm.setDecimalPlaces(lmd.getDecimalPlaces());
                    mpm.setIsCounter(lmd.isIsCounter());
                    mpm.setCodeGroup(lmd.getCodeGroup());
                    mpm.setAssembly(lmd.getAssembly());
                    mpm.setAuthorizGroup(lmd.getAuthorizGroup());
                    mpm.setTargetValue(lmd.getTargetValue());
                    mpm.setText(lmd.getText());
                    mpm.setStatus(lmd.isStatus());
                    
                    mpm.setValues();
                    
                    PanelLoader.loadPanel(mpm, mainContainerPanel);
                    
                }else{
                    
                    new SystemMessages(NOT.msg(NOT.MEASUREMENT_POINT_DOES_NOT_EXIST), TypeMessage.ERROR);
                }

            }

        });
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
        cmdSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        cmdProceder = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        measPointLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sbMeasurePoint = new com.simplecore.erp.gui.components.searchbox.JSearchBox();

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

        cmdSalir.setBackground(new java.awt.Color(226, 210, 144));
        cmdSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cmdSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        cmdProceder.setBackground(new java.awt.Color(226, 210, 144));
        cmdProceder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cmdProceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(cmdProceder, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdProceder, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        measPointLb.setText("Measure point");
        measPointLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(measPointLb, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sbMeasurePoint, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(782, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(measPointLb, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbMeasurePoint, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(412, Short.MAX_VALUE))
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
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdProceder;
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdSalir;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined measPointLb;
    public static javax.swing.JMenuBar menuBar_PM1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    protected static com.simplecore.erp.gui.components.searchbox.JSearchBox sbMeasurePoint;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JPopupMenu.Separator separador1;
    private javax.swing.JPopupMenu.Separator separador3;
    // End of variables declaration//GEN-END:variables
 
    
    
    private void btnSalir() {
        cmdSalir.addActionListener((e) -> {

            Invoke_JMenuBars.setMenuBar(getSuperFrame(), getSuperFrame().getJMenuBar(),
                    LyraWorkspace.barMenu);

            PanelLoader.loadPanel(treeMenus, mainContainerPanel);

        });
    }
    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }

    
}
