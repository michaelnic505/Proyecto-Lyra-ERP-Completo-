package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.create;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.windows.auxiliar.AuxiliarWindow;
import com.simplecore.erp.client.gui.windows.auxiliar.RowSelectionListener;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.client.utils.sound.Sound;
import com.simplecore.erp.client.utils.sound.SoundManager;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.SocietyClassListRequest;
import com.simplecore.erp.shared.requests.types.SocietyClassRequest;
import com.simplecore.erp.shared.responses.types.SocietyClassListResponse;
import com.simplecore.erp.shared.responses.types.SocietyClassResponse;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class InitCreationCompany extends JPanel implements TransactionPanel, RowSelectionListener{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final TranslationHelper windowTranslator;
    private final SystemMessages notificator;
    
    public InitCreationCompany() {
        initComponents();
        initEvents();
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.windowTranslator = Workspace.translators(TranslatorType.MESSAGES);
        this.notificator = new SystemMessages();
    }
    
    private String transactionCode;
    @Override
    public void initialize(String transactionCode,ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.transactionCode = transactionCode;
        this.activeSession = session;
        this.output = output;
        this.input = input;
    }

    private void initEvents() {
        matchCodeSocietyEvents();
        setButtonCommandConfigure();
    }

    private void matchCodeSocietyEvents(){
        societyClassMatchCode.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    societyClassMatchCode.getTextField().setText(null);
                    societyClassDescriptionLb.setText(null);
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(societyClassMatchCode.getTextField().getText()==null||societyClassMatchCode.getTextField().getText().isEmpty()){
                        return;
                    }
                    String clasCode = societyClassMatchCode.getTextField().getText().toUpperCase();
                    SocietyClassResponse societyClass = getSocietyClassByCode(clasCode);
                    if (societyClass == null) {
                        societyClassMatchCode.getTextField().setText(null);
                        societyClassDescriptionLb.setText(null);
                        return;
                    }
                    societyClassMatchCode.getTextField().setText(societyClass.getSocietyClassCode());
                    societyClassDescriptionLb.setText(descriptionClass(societyClass.getSocietyClassKey()));
                }
            }
        });
        societyClassMatchCode.getButton().addActionListener(e->{
            openSocietyClassList();
        });
    }

    private SocietyClassResponse getSocietyClassByCode(String societyClassCode) {
        try {
            String sessionId = activeSession.getSessionId();
            int userId = activeSession.getUserId();
            output.writeObject(new SocietyClassRequest(societyClassCode, sessionId, userId));
            output.flush();
            Object response = input.readObject();

            if (response instanceof SocietyClassResponse societyResponse) {

                String classCode = societyResponse.getSocietyClassCode();
                String classKey = societyResponse.getSocietyClassKey();

                if (classCode == null || classCode.isEmpty() || classKey == null || classKey.isEmpty()) {
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.CODE_DOES_NOT_EXIST));
                    return null;
                }
                return societyResponse;
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InitCreationCompany.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void onRowSelected(Object[] selectedData) {
        societyClassMatchCode.getTextField().setText((String) selectedData[0]);
        societyClassDescriptionLb.setText((String) selectedData[1]);
    }

    List<Integer> columnsToReturn = List.of(0, 1);
    private void openSocietyClassList() {
        AuxiliarWindow auxWindow = new AuxiliarWindow(Workspace.getFrame(), societyClassMatchCode.getButton(),
                getTableColumnsName(), translateSocietyData(), this, columnsToReturn);
        
        auxWindow.setWindowTitle(windowTranslator.getTranslation("creation.society.classWindowTableList.Title"));
        auxWindow.setTitlePane(0,windowTranslator.getTranslation("creation.society.classWindowTableList.Pane.Title"));
        auxWindow.setVisible(true);
    }

    private String[] getTableColumnsName() {
        String societyClass = tableTranslator.getTranslation("society.colum.societyClassCode");
        String societyClassDescription = tableTranslator.getTranslation("society.colum.societyClassDescription");
        return new String[]{
            societyClass,
            societyClassDescription
        };

    }

    private String descriptionClass(String key) {
        return tableTranslator.getTranslation("society." + key + ".text");
    }

    private String[][] translateSocietyData() {
        String[][] matrix = getSocietyDataList();
        if (matrix == null) {
            return new String[0][0]; // Evita errores si getSocietyDataList() devuelve null
        }
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) { // Usa matrix[row].length
                if (column == 1) {
                    matrix[row][column] = descriptionClass(matrix[row][column]); // ✅ Corrección aquí
                }
            }
        }
        return matrix;
    }

    private String[][] getSocietyDataList() {
        try {
            output.writeObject(new SocietyClassListRequest(activeSession.getSessionId(), activeSession.getUserId()));
            output.flush();
            Object response = input.readObject();
            if (response instanceof SocietyClassListResponse societyClassesList) {
                return societyClassesList.getSocieties();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AuxiliarWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void setButtonCommandConfigure(){
        proceedButton.setIcon(new CustomSVGIcon("/icons/svg/ok_button_icon.svg",new Dimension(24,24)));
        proceedButton.addActionListener(e->goToSocietyFieldsPanel());
    }
    
    private void goToSocietyFieldsPanel(){
        String societyCode = societyClassMatchCode.getTextField().getText().trim();
        if(societyCode!=null && !societyCode.isEmpty()){
        
            if (societyCode.equals(CompanyClass.CONTROLLING.getCompanyClass())) {
              
                ControllingCompany controllingCompany = new ControllingCompany(societyCode);
                controllingCompany.initialize(transactionCode,activeSession, output, input);
                PanelManager.goToPanel(controllingCompany);
           
            }else if(societyCode.equals(CompanyClass.FINANCIAL_ACCOUNTING.getCompanyClass())) {
                
                FinancialAccountingCompany financialAccountingCompany = new FinancialAccountingCompany(societyCode);
                financialAccountingCompany.initialize(transactionCode,activeSession, output, input);
                PanelManager.goToPanel(financialAccountingCompany);
            
            }
            SoundManager.playSound(Sound.OPEN.dir());
        
        }else{
            societyClassMatchCode.getTextField().requestFocus();
            SoundManager.playSound(Sound.EMPTY_FIELD.dir());
            new SystemMessages().showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
        }
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
        bodyPanel = new corex.suite.JPanelRoundedGradient();
        societyClassLabel = new corex.suite.JLabelHQUnderlined();
        societyClassMatchCode = new com.simplecore.erp.client.gui.components.matchcode.MatchCode();
        societyClassDescriptionLb = new javax.swing.JLabel();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moduleTitleLabel.setText("Company Creation");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 951, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
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

        bodyPanel.setColor1(new java.awt.Color(247, 247, 255));
        bodyPanel.setColor2(new java.awt.Color(239, 243, 247));

        societyClassLabel.setText("Company class");
        societyClassLabel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        societyClassMatchCode.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        societyClassDescriptionLb.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(societyClassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(societyClassMatchCode, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(societyClassDescriptionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(489, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(societyClassLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(societyClassMatchCode, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(societyClassDescriptionLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(343, Short.MAX_VALUE))
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JLabel societyClassDescriptionLb;
    private corex.suite.JLabelHQUnderlined societyClassLabel;
    private com.simplecore.erp.client.gui.components.matchcode.MatchCode societyClassMatchCode;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables



}
