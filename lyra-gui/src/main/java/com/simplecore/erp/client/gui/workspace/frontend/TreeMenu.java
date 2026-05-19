package com.simplecore.erp.client.gui.workspace.frontend;

import java.awt.Dimension;
import javax.swing.JPanel;
import com.simplecore.erp.client.controllers.maintree.TreeMenuController;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.services.maintree.TreeMenuService;
import com.simplecore.erp.client.utils.sound.SoundManager;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

public class TreeMenu extends javax.swing.JPanel {

    public static JPanel transactionPanel;
    private final TreeMenuController treeController;
    private final ActiveSession activeSession;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;

    public TreeMenu(ActiveSession activeSession,ObjectOutputStream output, ObjectInputStream input) {
        this.activeSession = activeSession;
        this.output = output;
        this.input = input;
        this.treeController = new TreeMenuController(output,input,activeSession);
        initComponents();
        init();
    }

    private void init() {
        configureTreeMenu();
        activateSound();
        PanelManager.setWorkspace(this);
    }

    private void configureTreeMenu() {
        TreeMenuService.build(systemMenuTree);
        treeController.setController(this,systemMenuTree);

        toggleButtonNode.setIcon(new CustomSVGIcon("/icons/svg/show_codes.svg",new Dimension(24,24)));
        toggleButtonNode.addActionListener(e -> {
            TreeMenuController.showNodeCodeInTree(systemMenuTree);
        });
        
        updateCollapseIcon(toggleCollapseButton.isSelected());
        toggleCollapseButton.addActionListener(e -> {
            boolean expanded = toggleCollapseButton.isSelected();
            TreeMenuController.setTreeExpandedState(systemMenuTree, expanded);
            updateCollapseIcon(expanded);
        });
    }

    private void updateCollapseIcon(boolean activated) {
        String iconPath = activated ? "/icons/svg/open_folder.svg" : "/icons/svg/close_folder.svg";
        toggleCollapseButton.setIcon(new CustomSVGIcon(iconPath,new Dimension(24,24)));
    }

    private void activateSound() {
        // Asegura que el botón refleja el estado actual del sonido
        boolean isSoundActive = SoundManager.isActivated();
        soundToggleButton.setSelected(isSoundActive);
        updateSoundIcon(isSoundActive);

        // Agrega un nuevo ActionListener de manera segura
        soundToggleButton.addActionListener(e -> {
            boolean activated = soundToggleButton.isSelected();
            SoundManager.setActivate(activated);
            updateSoundIcon(activated);
        });
    }

// Método auxiliar para cambiar el icono de forma segura
    private void updateSoundIcon(boolean activated) {
        String iconPath = activated ? "/icons/svg/sound_enabled.svg" : "/icons/svg/sound_disabled.svg";
        URL iconUrl = getClass().getResource(iconPath);
        if (iconUrl != null) {
            soundToggleButton.setIcon(new CustomSVGIcon(iconPath, new Dimension(24,24)));
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        rigthPanelBody = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        jLabel_HQ1 = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        jLabel_HQ2 = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        jLabel_HQ3 = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        treePanel = new javax.swing.JPanel();
        scrollTree = new com.simplecore.erp.client.gui.components.dropdownmenu.ScrollPaneWin11();
        systemMenuTree = new corex.suite.CoreTree();
        topPanel = new javax.swing.JPanel();
        panelGradient31 = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        timezoneLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        buttonsPanel = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        jToolBar1 = new javax.swing.JToolBar();
        toggleCollapseButton = new javax.swing.JToggleButton();
        toggleButtonNode = new javax.swing.JToggleButton();
        soundToggleButton = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        splitPane.setBackground(new java.awt.Color(227, 227, 227));
        splitPane.setDividerLocation(350);
        splitPane.setResizeWeight(0.5);

        rigthPanelBody.setColor1(new java.awt.Color(247, 247, 255));
        rigthPanelBody.setColor2(new java.awt.Color(239, 243, 247));

        jLabel_HQ1.setForeground(new java.awt.Color(81, 81, 81));
        jLabel_HQ1.setText("Stardust Analytics");
        jLabel_HQ1.setFont(new java.awt.Font("Roboto Condensed", 0, 32)); // NOI18N

        jLabel_HQ2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_HQ2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/treemenu/main_logo.png"))); // NOI18N
        jLabel_HQ2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel_HQ3.setForeground(new java.awt.Color(58, 58, 58));
        jLabel_HQ3.setText("Lyra Software - Release 2024.0817.035");
        jLabel_HQ3.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N

        javax.swing.GroupLayout rigthPanelBodyLayout = new javax.swing.GroupLayout(rigthPanelBody);
        rigthPanelBody.setLayout(rigthPanelBodyLayout);
        rigthPanelBodyLayout.setHorizontalGroup(
            rigthPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rigthPanelBodyLayout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addGroup(rigthPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel_HQ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_HQ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_HQ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        rigthPanelBodyLayout.setVerticalGroup(
            rigthPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rigthPanelBodyLayout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addComponent(jLabel_HQ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_HQ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_HQ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        splitPane.setRightComponent(rigthPanelBody);

        treePanel.setLayout(new java.awt.BorderLayout());

        systemMenuTree.setBaseColor(new java.awt.Color(231, 239, 255));
        systemMenuTree.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        systemMenuTree.setRowHeight(22);
        scrollTree.setViewportView(systemMenuTree);

        treePanel.add(scrollTree, java.awt.BorderLayout.CENTER);

        splitPane.setLeftComponent(treePanel);

        add(splitPane, java.awt.BorderLayout.CENTER);

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelGradient31.setColor1(new java.awt.Color(206, 223, 239));
        panelGradient31.setColor2(new java.awt.Color(173, 199, 222));
        panelGradient31.setColor3(new java.awt.Color(173, 199, 222));

        timezoneLabel.setForeground(new java.awt.Color(51, 51, 51));
        timezoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        timezoneLabel.setText("LyraCore Workspace");
        timezoneLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelGradient31Layout = new javax.swing.GroupLayout(panelGradient31);
        panelGradient31.setLayout(panelGradient31Layout);
        panelGradient31Layout.setHorizontalGroup(
            panelGradient31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 951, Short.MAX_VALUE)
            .addGroup(panelGradient31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradient31Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(timezoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelGradient31Layout.setVerticalGroup(
            panelGradient31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
            .addGroup(panelGradient31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelGradient31Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(timezoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        topPanel.add(panelGradient31);

        buttonsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonsPanel.setColor1(new java.awt.Color(206, 223, 239));
        buttonsPanel.setColor2(new java.awt.Color(206, 223, 239));
        buttonsPanel.setMaximumSize(null);
        buttonsPanel.setMinimumSize(new java.awt.Dimension(149, 35));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(149, 35));
        buttonsPanel.setVerifyInputWhenFocusTarget(false);

        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        toggleCollapseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/treemenu/collapse_expand.png"))); // NOI18N
        jToolBar1.add(toggleCollapseButton);

        toggleButtonNode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/treemenu/show.png"))); // NOI18N
        jToolBar1.add(toggleButtonNode);

        soundToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sound/sound_active.png"))); // NOI18N
        jToolBar1.add(soundToggleButton);

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(794, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ jLabel_HQ1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ jLabel_HQ2;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ jLabel_HQ3;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelGradient31;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient rigthPanelBody;
    private com.simplecore.erp.client.gui.components.dropdownmenu.ScrollPaneWin11 scrollTree;
    private javax.swing.JToggleButton soundToggleButton;
    private javax.swing.JSplitPane splitPane;
    private corex.suite.CoreTree systemMenuTree;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ timezoneLabel;
    private javax.swing.JToggleButton toggleButtonNode;
    private javax.swing.JToggleButton toggleCollapseButton;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel treePanel;
    // End of variables declaration//GEN-END:variables
}
