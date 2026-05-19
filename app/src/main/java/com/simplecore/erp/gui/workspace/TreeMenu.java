package com.simplecore.erp.gui.workspace;

import com.simplecore.erp.config.database.PooledConnectionService;
import java.awt.Dimension;
import javax.swing.JPanel;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.controllers.maintree.TreeMenuController;
import com.simplecore.erp.services.maintree.TreeMenuService;
import com.simplecore.erp.utils.sound.SoundManager;
import com.zaxxer.hikari.pool.HikariPool;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import javax.swing.ImageIcon;

public class TreeMenu extends javax.swing.JPanel {

    public static JPanel transactionPanel;
    private TreeMenuController treeController;
    private LanguageManager languageManager;
    private int userId;
    private String username;
    private Connection connection;

    public TreeMenu(LanguageManager languageManager,int userId ,String username) {
        this.languageManager = languageManager;
        this.userId = userId;
        this.username = username;
        this.connection = PooledConnectionService.getConnection();
        this.treeController = new TreeMenuController(connection);
        initComponents();
        splitDimensions();
        init();

    }

    private void init() {
        configureTreeMenu();
        activateSound();
    }

    private void configureTreeMenu() {
        TreeMenuService.configureTree(systemMenuTree);
        treeController.setController(systemMenuTree, userId,username);

        toggleButtonNode.addActionListener(e -> {
            TreeMenuController.showNodeCodeInTree(systemMenuTree);
        });
        toggleCollapseButton.addActionListener(e -> {
            boolean expanded = toggleCollapseButton.isSelected();
            TreeMenuController.setTreeExpandedState(systemMenuTree, expanded);
        });
    }

    private void activateSound() {
        // Asegura que el botón refleja el estado actual del sonido
        boolean isSoundActive = SoundManager.isActivated();
        soundToggleButton.setSelected(isSoundActive);
        updateSoundIcon(isSoundActive);

        // Elimina cualquier ActionListener previo para evitar acumulaciones
        for (ActionListener al : soundToggleButton.getActionListeners()) {
            soundToggleButton.removeActionListener(al);
        }

        // Agrega un nuevo ActionListener de manera segura
        soundToggleButton.addActionListener(e -> {
            boolean activated = soundToggleButton.isSelected();
            SoundManager.setActivate(activated);
            updateSoundIcon(activated);
        });
    }

// Método auxiliar para cambiar el icono de forma segura
    private void updateSoundIcon(boolean activated) {
        String iconPath = activated ? "/icons/sound/sound_active.png" : "/icons/sound/sound_inactive.png";
        URL iconUrl = getClass().getResource(iconPath);
        if (iconUrl != null) {
            soundToggleButton.setIcon(new ImageIcon(iconUrl));
        }
    }

    private void splitDimensions() {
        split.getLeftComponent().setMinimumSize(new Dimension(370, 660));
        split.getRightComponent().setMinimumSize(new Dimension(840, 660));
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonsPanel = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        toggleButtonNode = new javax.swing.JToggleButton();
        toggleCollapseButton = new javax.swing.JToggleButton();
        soundToggleButton = new javax.swing.JToggleButton();
        split = new javax.swing.JSplitPane();
        rigthPanelBody = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        jLabel_HQ1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        jLabel_HQ2 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        jLabel_HQ3 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        treePanel = new javax.swing.JPanel();
        scrollTree = new com.simplecore.erp.gui.components.dropdownmenu.ScrollPaneWin11();
        systemMenuTree = new corex.suite.CoreTree();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        buttonsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonsPanel.setColor1(new java.awt.Color(202, 216, 237));
        buttonsPanel.setColor2(new java.awt.Color(202, 216, 237));

        toggleButtonNode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/treemenu/show.png"))); // NOI18N

        toggleCollapseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/treemenu/collapse_expand.png"))); // NOI18N

        soundToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sound/sound_active.png"))); // NOI18N

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(toggleCollapseButton)
                .addGap(1, 1, 1)
                .addComponent(toggleButtonNode)
                .addGap(1, 1, 1)
                .addComponent(soundToggleButton)
                .addContainerGap(817, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soundToggleButton)
                    .addComponent(toggleButtonNode)
                    .addComponent(toggleCollapseButton))
                .addContainerGap())
        );

        add(buttonsPanel, java.awt.BorderLayout.NORTH);

        split.setBackground(new java.awt.Color(227, 227, 227));
        split.setDividerLocation(550);
        split.setResizeWeight(0.5);

        rigthPanelBody.setColor1(new java.awt.Color(202, 216, 237));
        rigthPanelBody.setColor2(new java.awt.Color(123, 160, 193));

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
                .addContainerGap(96, Short.MAX_VALUE)
                .addGroup(rigthPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel_HQ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_HQ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_HQ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        rigthPanelBodyLayout.setVerticalGroup(
            rigthPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rigthPanelBodyLayout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addComponent(jLabel_HQ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_HQ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_HQ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        split.setRightComponent(rigthPanelBody);

        treePanel.setLayout(new java.awt.BorderLayout());

        systemMenuTree.setBaseColor(new java.awt.Color(202, 216, 237));
        systemMenuTree.setRowHeight(22);
        scrollTree.setViewportView(systemMenuTree);

        treePanel.add(scrollTree, java.awt.BorderLayout.CENTER);

        split.setLeftComponent(treePanel);

        add(split, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ1;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ2;
    private com.simplecore.erp.gui.components.labels.JLabelHQ jLabel_HQ3;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient rigthPanelBody;
    private com.simplecore.erp.gui.components.dropdownmenu.ScrollPaneWin11 scrollTree;
    private javax.swing.JToggleButton soundToggleButton;
    private javax.swing.JSplitPane split;
    private corex.suite.CoreTree systemMenuTree;
    private javax.swing.JToggleButton toggleButtonNode;
    private javax.swing.JToggleButton toggleCollapseButton;
    private javax.swing.JPanel treePanel;
    // End of variables declaration//GEN-END:variables
}
