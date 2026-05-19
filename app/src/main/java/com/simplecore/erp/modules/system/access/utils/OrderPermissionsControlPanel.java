
package com.simplecore.erp.modules.system.access.utils;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.FullUserName;
import com.simplecore.erp.utils.notifications.NOT;


public class OrderPermissionsControlPanel extends javax.swing.JPanel {

    boolean accion = false;
    

    public OrderPermissionsControlPanel() {
        
        initComponents();
        botonAtras();
        botonGuardar();
        

    }

    
    JPanel panelAtras;
    public void setPanelAtras(JPanel panel){
        this.panelAtras = panel;
    }
    
        
    JPanel panelPrincipal;
    public void setPanelPrincipal(JPanel panel){
        this.panelPrincipal = panel;
    }
    
    private void botonAtras() {
         
         btnSalir.addActionListener((e)->{        
             
             if(!accion){
                    
               new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.INFORMATION);

             }
              PanelLoader.loadPanel(panelAtras, panelPrincipal);
             
         });
         
     }

    public void cargarO01(String user) {

        Extraer_Permisos ep = new Extraer_Permisos();
        ep.permisos(user, "O01");

        btnOCRTD_O01.setSelected(ep.isOCRTD());
        btnOIPNN_O01.setSelected(ep.isOIPNN());
        btnOUAPP_O01.setSelected(ep.isOUAPP());
        btnOAPPV_O01.setSelected(ep.isOAPPV());
        btnOSCHD_O01.setSelected(ep.isOSCHD());
        btnOIEXN_O01.setSelected(ep.isOIEXN());
        btnOEXTD_O01.setSelected(ep.isOEXTD());
        btnOCLSD_O01.setSelected(ep.isOCLSD());
        btnORJTD_O01.setSelected(ep.isORJTD());
        btnOCCLD_O01.setSelected(ep.isOCCLD());

    }

    public void cargarO02(String user) {

        Extraer_Permisos ep = new Extraer_Permisos();
        ep.permisos(user, "O02");

        btnOCRTD_O02.setSelected(ep.isOCRTD());
        btnOIPNN_O02.setSelected(ep.isOIPNN());
        btnOUAPP_O02.setSelected(ep.isOUAPP());
        btnOAPPV_O02.setSelected(ep.isOAPPV());
        btnOSCHD_O02.setSelected(ep.isOSCHD());
        btnOIEXN_O02.setSelected(ep.isOIEXN());
        btnOEXTD_O02.setSelected(ep.isOEXTD());
        btnOCLSD_O02.setSelected(ep.isOCLSD());
        btnORJTD_O02.setSelected(ep.isORJTD());
        btnOCCLD_O02.setSelected(ep.isOCCLD());

    }

    public void cargarO03(String user) {

        Extraer_Permisos ep = new Extraer_Permisos();
        ep.permisos(user, "O03");

        btnOCRTD_O03.setSelected(ep.isOCRTD());
        btnOIPNN_O03.setSelected(ep.isOIPNN());
        btnOUAPP_O03.setSelected(ep.isOUAPP());
        btnOAPPV_O03.setSelected(ep.isOAPPV());
        btnOSCHD_O03.setSelected(ep.isOSCHD());
        btnOIEXN_O03.setSelected(ep.isOIEXN());
        btnOEXTD_O03.setSelected(ep.isOEXTD());
        btnOCLSD_O03.setSelected(ep.isOCLSD());
        btnORJTD_O03.setSelected(ep.isORJTD());
        btnOCCLD_O03.setSelected(ep.isOCCLD());

    }
    
    
    private String user;
    
    public void setUser(String user) {
        this.user = user;

        labelUser.setText(user);
//        labelNombre.setText(FullUserName.getName(user));
    }
    
    private void botonGuardar() {
        btnGuardar.addActionListener((e) -> {

            int r = JOptionPane.showConfirmDialog(null, NOT.msg(NOT.ARE_YOU_SURE_TO_SAVE), NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                guardarCambios();
                accion = true;
      

                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.DATA_SAVED), TypeMessage.SUCCESS);


            } else {


                new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.DATA_NOT_SAVED), TypeMessage.WARNING);

            }

        });
    }
    
    private void guardarCambios(){
        
        //guardar ajustes de O01        
        Modify_Permission mp1 = new Modify_Permission();
        mp1.setOCRTD(btnOCRTD_O01.isSelected());
        mp1.setOIPNN(btnOIPNN_O01.isSelected());
        mp1.setOUAPP(btnOUAPP_O01.isSelected());
        mp1.setOAPPV(btnOAPPV_O01.isSelected());
        mp1.setOSCHD(btnOSCHD_O01.isSelected());
        mp1.setOIEXN(btnOIEXN_O01.isSelected());
        mp1.setOEXTD(btnOEXTD_O01.isSelected());
        mp1.setOCLSD(btnOCLSD_O01.isSelected());
        mp1.setORJTD(btnORJTD_O01.isSelected());
        mp1.setOCCLD(btnOCCLD_O01.isSelected());
        
        mp1.modify(user,"O01");

        //guardar ajustes de O02
        Modify_Permission mp2 = new Modify_Permission();
        mp2.setOCRTD(btnOCRTD_O02.isSelected());
        mp2.setOIPNN(btnOIPNN_O02.isSelected());
        mp2.setOUAPP(btnOUAPP_O02.isSelected());
        mp2.setOAPPV(btnOAPPV_O02.isSelected());
        mp2.setOSCHD(btnOSCHD_O02.isSelected());
        mp2.setOIEXN(btnOIEXN_O02.isSelected());
        mp2.setOEXTD(btnOEXTD_O02.isSelected());
        mp2.setOCLSD(btnOCLSD_O02.isSelected());
        mp2.setORJTD(btnORJTD_O02.isSelected());
        mp2.setOCCLD(btnOCCLD_O02.isSelected());

        mp2.modify(user, "O02");

        
        //guardar ajustes de O03
        Modify_Permission mp3 = new Modify_Permission();
        mp3.setOCRTD(btnOCRTD_O03.isSelected());
        mp3.setOIPNN(btnOIPNN_O03.isSelected());
        mp3.setOUAPP(btnOUAPP_O03.isSelected());
        mp3.setOAPPV(btnOAPPV_O03.isSelected());
        mp3.setOSCHD(btnOSCHD_O03.isSelected());
        mp3.setOIEXN(btnOIEXN_O03.isSelected());
        mp3.setOEXTD(btnOEXTD_O03.isSelected());
        mp3.setOCLSD(btnOCLSD_O03.isSelected());
        mp3.setORJTD(btnORJTD_O03.isSelected());
        mp3.setOCCLD(btnOCCLD_O03.isSelected());

        mp3.modify(user, "O03");

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelOIPNN9 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnGuardar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        title = new com.simplecore.erp.gui.components.labels.JLabelHQFraming();
        titlePrincipal = new com.simplecore.erp.gui.components.labels.JLabelHQLongFraming();
        creationPanel = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnOSCHD_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton10 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOIEXN = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOIEXN_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton12 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOEXTD = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOEXTD_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton14 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOCLSD = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOCLSD_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton16 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelORJTD = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnORJTD_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton18 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOIPNN8 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOCCLD_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton20 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOCRTD = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOCRTD_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton2 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOIPNN = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOIPNN_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton4 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOUAPP = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOUAPP_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton6 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOAPPV = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOAPPV_O01 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton8 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOSCHD = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        titlePrincipal1 = new com.simplecore.erp.gui.components.labels.JLabelHQLongFraming();
        changePanel = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnOSCHD_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton22 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOIEXN1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOIEXN_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton24 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOEXTD1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOEXTD_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton26 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOCLSD1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOCLSD_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton28 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelORJTD1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnORJTD_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton30 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOIPNN10 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOCCLD_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton32 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOCRTD1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOCRTD_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton34 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOIPNN1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOIPNN_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton36 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOUAPP1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOUAPP_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton38 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOAPPV1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOAPPV_O02 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton40 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOSCHD1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        titlePrincipal2 = new com.simplecore.erp.gui.components.labels.JLabelHQLongFraming();
        visualizationPanel = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnOSCHD_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton42 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOIEXN2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOIEXN_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton44 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOEXTD2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOEXTD_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton46 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOCLSD2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOCLSD_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton48 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelORJTD2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnORJTD_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton50 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOIPNN11 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOCCLD_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton52 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOCRTD2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOCRTD_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton54 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOIPNN2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOIPNN_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton56 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOUAPP2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOUAPP_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton58 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOAPPV2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnOAPPV_O03 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        toggleButton60 = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        labelOSCHD2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        labelUser = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        labelNombre = new com.simplecore.erp.gui.components.labels.JLabelHQ();

        labelOIPNN9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIPNN9.setText("OCCLD");
        labelOIPNN9.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        setBackground(new java.awt.Color(238, 244, 254));

        btnGuardar.setBackground(new java.awt.Color(226, 210, 144));
        btnGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/general_windows/guardar15.png"))); // NOI18N

        btnSalir.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/volver.png"))); // NOI18N

        title.setForeground(new java.awt.Color(245, 245, 245));
        title.setText("Access control");
        title.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        titlePrincipal.setForeground(new java.awt.Color(81, 81, 81));
        titlePrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlePrincipal.setText("Creation O01");
        titlePrincipal.setColor1(new java.awt.Color(202, 216, 237));
        titlePrincipal.setColor2(new java.awt.Color(123, 160, 193));
        titlePrincipal.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N

        creationPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        creationPanel.setColor1(new java.awt.Color(202, 216, 237));
        creationPanel.setColor2(new java.awt.Color(123, 160, 193));

        btnOSCHD_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnOSCHD_O01.add(toggleButton10);
        toggleButton10.setBounds(0, 0, 60, 35);

        labelOIEXN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIEXN.setText("OIEXN");
        labelOIEXN.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOIEXN_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnOIEXN_O01.add(toggleButton12);
        toggleButton12.setBounds(0, 0, 60, 35);

        labelOEXTD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOEXTD.setText("OEXTD");
        labelOEXTD.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOEXTD_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnOEXTD_O01.add(toggleButton14);
        toggleButton14.setBounds(0, 0, 60, 35);

        labelOCLSD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOCLSD.setText("OCLSD");
        labelOCLSD.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOCLSD_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnOCLSD_O01.add(toggleButton16);
        toggleButton16.setBounds(0, 0, 60, 35);

        labelORJTD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelORJTD.setText("ORJTD");
        labelORJTD.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnORJTD_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnORJTD_O01.add(toggleButton18);
        toggleButton18.setBounds(0, 0, 60, 35);

        labelOIPNN8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIPNN8.setText("OCCLD");
        labelOIPNN8.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOCCLD_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnOCCLD_O01.add(toggleButton20);
        toggleButton20.setBounds(0, 0, 60, 35);

        labelOCRTD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOCRTD.setText("OCRTD");
        labelOCRTD.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOCRTD_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnOCRTD_O01.add(toggleButton2);
        toggleButton2.setBounds(0, 0, 60, 35);

        labelOIPNN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIPNN.setText("OIPNN");
        labelOIPNN.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOIPNN_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnOIPNN_O01.add(toggleButton4);
        toggleButton4.setBounds(0, 0, 60, 35);

        labelOUAPP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOUAPP.setText("OUAPP");
        labelOUAPP.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOUAPP_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnOUAPP_O01.add(toggleButton6);
        toggleButton6.setBounds(0, 0, 60, 35);

        labelOAPPV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOAPPV.setText("OAPPV");
        labelOAPPV.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOAPPV_O01.setForeground(new java.awt.Color(37, 150, 190));
        btnOAPPV_O01.add(toggleButton8);
        toggleButton8.setBounds(0, 0, 60, 35);

        labelOSCHD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOSCHD.setText("OSCHD");
        labelOSCHD.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        javax.swing.GroupLayout creationPanelLayout = new javax.swing.GroupLayout(creationPanel);
        creationPanel.setLayout(creationPanelLayout);
        creationPanelLayout.setHorizontalGroup(
            creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creationPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelOCRTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOIEXN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOIPNN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOEXTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOUAPP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOCLSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOAPPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelORJTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOSCHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOIPNN8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnOCCLD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSCHD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIEXN_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCRTD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOEXTD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIPNN_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCLSD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOUAPP_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnORJTD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOAPPV_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );
        creationPanelLayout.setVerticalGroup(
            creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creationPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOCRTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCRTD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOIPNN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIPNN_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOUAPP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOUAPP_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOAPPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOAPPV_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOSCHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSCHD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOIEXN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIEXN_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOEXTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOEXTD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOCLSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCLSD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelORJTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnORJTD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOIPNN8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCCLD_O01, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        titlePrincipal1.setForeground(new java.awt.Color(81, 81, 81));
        titlePrincipal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlePrincipal1.setText("Modification O02");
        titlePrincipal1.setColor1(new java.awt.Color(202, 216, 237));
        titlePrincipal1.setColor2(new java.awt.Color(123, 160, 193));
        titlePrincipal1.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N

        changePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        changePanel.setColor1(new java.awt.Color(202, 216, 237));
        changePanel.setColor2(new java.awt.Color(123, 160, 193));

        btnOSCHD_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnOSCHD_O02.add(toggleButton22);
        toggleButton22.setBounds(0, 0, 60, 35);

        labelOIEXN1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIEXN1.setText("OIEXN");
        labelOIEXN1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOIEXN_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnOIEXN_O02.add(toggleButton24);
        toggleButton24.setBounds(0, 0, 60, 35);

        labelOEXTD1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOEXTD1.setText("OEXTD");
        labelOEXTD1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOEXTD_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnOEXTD_O02.add(toggleButton26);
        toggleButton26.setBounds(0, 0, 60, 35);

        labelOCLSD1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOCLSD1.setText("OCLSD");
        labelOCLSD1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOCLSD_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnOCLSD_O02.add(toggleButton28);
        toggleButton28.setBounds(0, 0, 60, 35);

        labelORJTD1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelORJTD1.setText("ORJTD");
        labelORJTD1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnORJTD_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnORJTD_O02.add(toggleButton30);
        toggleButton30.setBounds(0, 0, 60, 35);

        labelOIPNN10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIPNN10.setText("OCCLD");
        labelOIPNN10.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOCCLD_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnOCCLD_O02.add(toggleButton32);
        toggleButton32.setBounds(0, 0, 60, 35);

        labelOCRTD1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOCRTD1.setText("OCRTD");
        labelOCRTD1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOCRTD_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnOCRTD_O02.add(toggleButton34);
        toggleButton34.setBounds(0, 0, 60, 35);

        labelOIPNN1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIPNN1.setText("OIPNN");
        labelOIPNN1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOIPNN_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnOIPNN_O02.add(toggleButton36);
        toggleButton36.setBounds(0, 0, 60, 35);

        labelOUAPP1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOUAPP1.setText("OUAPP");
        labelOUAPP1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOUAPP_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnOUAPP_O02.add(toggleButton38);
        toggleButton38.setBounds(0, 0, 60, 35);

        labelOAPPV1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOAPPV1.setText("OAPPV");
        labelOAPPV1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOAPPV_O02.setForeground(new java.awt.Color(0, 153, 255));
        btnOAPPV_O02.add(toggleButton40);
        toggleButton40.setBounds(0, 0, 60, 35);

        labelOSCHD1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOSCHD1.setText("OSCHD");
        labelOSCHD1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        javax.swing.GroupLayout changePanelLayout = new javax.swing.GroupLayout(changePanel);
        changePanel.setLayout(changePanelLayout);
        changePanelLayout.setHorizontalGroup(
            changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelOCRTD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOIEXN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOIPNN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOEXTD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOUAPP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOCLSD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOAPPV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelORJTD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOSCHD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOIPNN10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnOCCLD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSCHD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIEXN_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCRTD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOEXTD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIPNN_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCLSD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOUAPP_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnORJTD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOAPPV_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );
        changePanelLayout.setVerticalGroup(
            changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOCRTD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCRTD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOIPNN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIPNN_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOUAPP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOUAPP_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOAPPV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOAPPV_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOSCHD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSCHD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOIEXN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIEXN_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOEXTD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOEXTD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOCLSD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCLSD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelORJTD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnORJTD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOIPNN10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCCLD_O02, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        titlePrincipal2.setForeground(new java.awt.Color(81, 81, 81));
        titlePrincipal2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlePrincipal2.setText("Visualization O03");
        titlePrincipal2.setColor1(new java.awt.Color(202, 216, 237));
        titlePrincipal2.setColor2(new java.awt.Color(123, 160, 193));
        titlePrincipal2.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N

        visualizationPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        visualizationPanel.setColor1(new java.awt.Color(202, 216, 237));
        visualizationPanel.setColor2(new java.awt.Color(123, 160, 193));

        btnOSCHD_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnOSCHD_O03.add(toggleButton42);
        toggleButton42.setBounds(0, 0, 60, 35);

        labelOIEXN2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIEXN2.setText("OIEXN");
        labelOIEXN2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOIEXN_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnOIEXN_O03.add(toggleButton44);
        toggleButton44.setBounds(0, 0, 60, 35);

        labelOEXTD2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOEXTD2.setText("OEXTD");
        labelOEXTD2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOEXTD_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnOEXTD_O03.add(toggleButton46);
        toggleButton46.setBounds(0, 0, 60, 35);

        labelOCLSD2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOCLSD2.setText("OCLSD");
        labelOCLSD2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOCLSD_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnOCLSD_O03.add(toggleButton48);
        toggleButton48.setBounds(0, 0, 60, 35);

        labelORJTD2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelORJTD2.setText("ORJTD");
        labelORJTD2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnORJTD_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnORJTD_O03.add(toggleButton50);
        toggleButton50.setBounds(0, 0, 60, 35);

        labelOIPNN11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIPNN11.setText("OCCLD");
        labelOIPNN11.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOCCLD_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnOCCLD_O03.setToolTipText("");
        btnOCCLD_O03.add(toggleButton52);
        toggleButton52.setBounds(0, 0, 60, 35);

        labelOCRTD2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOCRTD2.setText("OCRTD");
        labelOCRTD2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOCRTD_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnOCRTD_O03.add(toggleButton54);
        toggleButton54.setBounds(0, 0, 60, 35);

        labelOIPNN2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOIPNN2.setText("OIPNN");
        labelOIPNN2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOIPNN_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnOIPNN_O03.add(toggleButton56);
        toggleButton56.setBounds(0, 0, 60, 35);

        labelOUAPP2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOUAPP2.setText("OUAPP");
        labelOUAPP2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOUAPP_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnOUAPP_O03.add(toggleButton58);
        toggleButton58.setBounds(0, 0, 60, 35);

        labelOAPPV2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOAPPV2.setText("OAPPV");
        labelOAPPV2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        btnOAPPV_O03.setForeground(new java.awt.Color(0, 153, 255));
        btnOAPPV_O03.add(toggleButton60);
        toggleButton60.setBounds(0, 0, 60, 35);

        labelOSCHD2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelOSCHD2.setText("OSCHD");
        labelOSCHD2.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        javax.swing.GroupLayout visualizationPanelLayout = new javax.swing.GroupLayout(visualizationPanel);
        visualizationPanel.setLayout(visualizationPanelLayout);
        visualizationPanelLayout.setHorizontalGroup(
            visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizationPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelOCRTD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOIEXN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOIPNN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOEXTD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOUAPP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOCLSD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOAPPV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelORJTD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOSCHD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelOIPNN11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnOCCLD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSCHD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIEXN_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCRTD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOEXTD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIPNN_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCLSD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOUAPP_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnORJTD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOAPPV_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );
        visualizationPanelLayout.setVerticalGroup(
            visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizationPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOCRTD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCRTD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOIPNN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIPNN_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOUAPP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOUAPP_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOAPPV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOAPPV_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOSCHD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSCHD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOIEXN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOIEXN_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOEXTD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOEXTD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOCLSD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCLSD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelORJTD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnORJTD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelOIPNN11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOCCLD_O03, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        labelUser.setBackground(new java.awt.Color(0, 51, 153));
        labelUser.setForeground(new java.awt.Color(243, 243, 243));
        labelUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUser.setText("root");
        labelUser.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        labelUser.setOpaque(true);

        labelNombre.setBackground(new java.awt.Color(255, 255, 102));
        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNombre.setText("root");
        labelNombre.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        labelNombre.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(creationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(titlePrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(changePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(titlePrincipal1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(visualizationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(titlePrincipal2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(119, 119, 119))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titlePrincipal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(visualizationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(titlePrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(creationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(titlePrincipal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(changePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnGuardar;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOAPPV_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOAPPV_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOAPPV_O03;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOCCLD_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOCCLD_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOCCLD_O03;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOCLSD_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOCLSD_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOCLSD_O03;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOCRTD_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOCRTD_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOCRTD_O03;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOEXTD_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOEXTD_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOEXTD_O03;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOIEXN_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOIEXN_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOIEXN_O03;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOIPNN_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOIPNN_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOIPNN_O03;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnORJTD_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnORJTD_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnORJTD_O03;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOSCHD_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOSCHD_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOSCHD_O03;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOUAPP_O01;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOUAPP_O02;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton btnOUAPP_O03;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient changePanel;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient creationPanel;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelNombre;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOAPPV;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOAPPV1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOAPPV2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOCLSD;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOCLSD1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOCLSD2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOCRTD;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOCRTD1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOCRTD2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOEXTD;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOEXTD1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOEXTD2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIEXN;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIEXN1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIEXN2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIPNN;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIPNN1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIPNN10;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIPNN11;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIPNN2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIPNN8;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOIPNN9;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelORJTD;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelORJTD1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelORJTD2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOSCHD;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOSCHD1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOSCHD2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOUAPP;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOUAPP1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelOUAPP2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ labelUser;
    private com.simplecore.erp.gui.components.labels.JLabelHQFraming title;
    private com.simplecore.erp.gui.components.labels.JLabelHQLongFraming titlePrincipal;
    private com.simplecore.erp.gui.components.labels.JLabelHQLongFraming titlePrincipal1;
    private com.simplecore.erp.gui.components.labels.JLabelHQLongFraming titlePrincipal2;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton10;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton12;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton14;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton16;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton18;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton2;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton20;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton22;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton24;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton26;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton28;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton30;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton32;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton34;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton36;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton38;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton4;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton40;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton42;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton44;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton46;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton48;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton50;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton52;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton54;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton56;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton58;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton6;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton60;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton toggleButton8;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient visualizationPanel;
    // End of variables declaration//GEN-END:variables
}
