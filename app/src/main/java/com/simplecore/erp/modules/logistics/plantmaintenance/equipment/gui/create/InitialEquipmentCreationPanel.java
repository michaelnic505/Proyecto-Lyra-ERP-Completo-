package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.gui.create;

public class InitialEquipmentCreationPanel extends javax.swing.JPanel {

    public InitialEquipmentCreationPanel() {
        
        initComponents();
   
    }

    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_E01 = new javax.swing.JMenuBar();
        menuCreation = new javax.swing.JMenu();
        menuCreateNewEquipment = new javax.swing.JMenuItem();
        menuModifyEquipment = new javax.swing.JMenuItem();
        menuVisualizeEquipment = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();
        menuActions = new javax.swing.JMenu();
        menuCreate = new javax.swing.JMenuItem();
        menuStatus = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuInstruction = new javax.swing.JMenuItem();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        buttonsPanel = new corex.suite.JPanelRoundedGradient();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnCrear = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        menuCreation.setText("Creacion de equipo");

        menuCreateNewEquipment.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuCreateNewEquipment.setText("E01 - Nuevo Crear equipo");
        menuCreation.add(menuCreateNewEquipment);

        menuModifyEquipment.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuModifyEquipment.setText("E02 - Nuevo Modificar equipo");
        menuCreation.add(menuModifyEquipment);

        menuVisualizeEquipment.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuVisualizeEquipment.setText("E03 - Nuevo Visualizar equipo");
        menuCreation.add(menuVisualizeEquipment);
        menuCreation.add(separador);

        menuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menuExit.setText("Salir");
        menuCreation.add(menuExit);

        menuBar_E01.add(menuCreation);

        menuActions.setText("Acciones");

        menuCreate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuCreate.setText("Crear");
        menuActions.add(menuCreate);

        menuStatus.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuStatus.setText("Estatus");
        menuActions.add(menuStatus);

        menuBar_E01.add(menuActions);

        menuHelp.setText("Ayuda");

        menuInstruction.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        menuInstruction.setText("Manual de instruccion");
        menuHelp.add(menuInstruction);

        menuBar_E01.add(menuHelp);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1181, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        buttonsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonsPanel.setColor1(new java.awt.Color(202, 216, 237));
        buttonsPanel.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        btnCrear.setBackground(new java.awt.Color(226, 210, 144));
        btnCrear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
            .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnCrear;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir;
    private corex.suite.JPanelRoundedGradient buttonsPanel;
    public static javax.swing.JMenu menuActions;
    public static javax.swing.JMenuBar menuBar_E01;
    public static javax.swing.JMenuItem menuCreate;
    public static javax.swing.JMenuItem menuCreateNewEquipment;
    public static javax.swing.JMenu menuCreation;
    public static javax.swing.JMenuItem menuExit;
    public static javax.swing.JMenu menuHelp;
    public static javax.swing.JMenuItem menuInstruction;
    public static javax.swing.JMenuItem menuModifyEquipment;
    public static javax.swing.JMenuItem menuStatus;
    public static javax.swing.JMenuItem menuVisualizeEquipment;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPopupMenu.Separator separador;
    // End of variables declaration//GEN-END:variables
}
