package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.classes;

import com.simplecore.erp.client.controllers.transaction.TransactionPanel;
import com.simplecore.erp.client.gui.components.tables.newversions.DynamicTableModel;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TableKeys;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.AccountClassesRetrieveRequest;
import com.simplecore.erp.shared.responses.types.AccountClassesRetrieveResponse;
import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class ViewAccountClasses extends JPanel implements TransactionPanel{

    private ActiveSession activeSession;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final TranslationHelper tableTranslator;
    private final SystemMessages notificator;

    public ViewAccountClasses() {
        this.tableTranslator = Workspace.translators(TranslatorType.TABLES);
        this.notificator = new SystemMessages();
        initComponents();
        initEvents();
    }

    private String transactionCode;
    @Override
    public void initialize(String transactionCode,ActiveSession session, ObjectOutputStream output, ObjectInputStream input) {
        this.transactionCode = transactionCode;
        this.activeSession = session;
        this.output = output;
        this.input = input;
        initAccountClassesTable();
    }

    private void initEvents() {
        setButtonCommandConfigure();
    }
    private String[] getTableColumnNames(){
        String id = tableTranslator.getTranslation(TableKeys.ACCOUNT_CLASSES_TC_ID.getKey());
        String classCode = tableTranslator.getTranslation(TableKeys.ACCOUNT_CLASSES_TC_CLASS_CODE.getKey());
        String className = tableTranslator.getTranslation(TableKeys.ACCOUNT_CLASSES_TC_CLASS_NAME.getKey());
        String classKey = tableTranslator.getTranslation(TableKeys.ACCOUNT_CLASSES_TC_CLASS_KEY.getKey());
        
        return new String[]{id,classCode,className,classKey};
    }
    
    private String[][] requestAccountClassesList(){
        try {
            output.writeObject(new AccountClassesRetrieveRequest(activeSession.getSessionId(),activeSession.getUserId()));
            output.flush();
            Object response = input.readObject();
            if(response instanceof AccountClassesRetrieveResponse accountClassesResponse){
                return accountClassesResponse.getAccountClasses();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ViewAccountClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void initAccountClassesTable() {
        String[] columnNames = getTableColumnNames();
        String[][] dataMatrix = requestAccountClassesList();

        if (dataMatrix == null) {
            dataMatrix = new String[0][columnNames.length]; // Evita NullPointerException
        }
        DynamicTableModel model = new DynamicTableModel(dataMatrix.length, columnNames);
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int column = 0; column < columnNames.length; column++) {
                if (column == 3) {
                    model.setValueAt(descriptionClass(dataMatrix[row][column]), row, 2);
                }
                model.setValueAt(dataMatrix[row][column], row, column);
                model.setCellEditable(row, column, false);
            }
        }
        accountClassesTable.setModel(model);
        adjustColumnWidths();
        AlignedColumnRenderer.setAlignedColumnRenderer(accountClassesTable, 0, SwingUtilities.CENTER);
        AlignedColumnRenderer.setAlignedColumnRenderer(accountClassesTable, 1, SwingUtilities.LEFT);
        
        AlignedHeaderRenderer.applyToHeader(accountClassesTable, 0, SwingUtilities.LEFT);
        accountClassesTable.getColumnModel().getColumn(0).setPreferredWidth(50);

    }

    private void adjustColumnWidths() {
        JTableHeader header = accountClassesTable.getTableHeader();
        TableColumnModel columnModel = accountClassesTable.getColumnModel();

        int rowCount = accountClassesTable.getRowCount();
        int columnCount = accountClassesTable.getColumnCount();

        for (int col = 0; col < columnCount; col++) {
            int totalLength = 0;
            int maxLength = header.getColumnModel().getColumn(col).getHeaderValue().toString().length(); // Considerar el encabezado

            for (int row = 0; row < rowCount; row++) {
                Object value = accountClassesTable.getValueAt(row, col);
                if (value != null) {
                    int length = value.toString().length();
                    totalLength += length;
                    maxLength = Math.max(maxLength, length);
                }
            }

            int avgLength = rowCount > 0 ? totalLength / rowCount : maxLength;
            int columnWidth = Math.max(avgLength * 7, maxLength * 7); // Factor 7 px por caracter aproximado
            columnModel.getColumn(col).setPreferredWidth(columnWidth);
        }
    }

    private String descriptionClass(String key) {
        return tableTranslator.getTranslation(key);
    }

    private void setButtonCommandConfigure(){
        proceedButton.setIcon(new CustomSVGIcon("/icons/svg/green_flag.svg",new Dimension(24,24)));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        accountClassesTable = new corex.suite.LCTable();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moduleTitleLabel.setText("Financial Accounting - Account Classes");
        moduleTitleLabel.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 923, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
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
                .addContainerGap(766, Short.MAX_VALUE))
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

        accountClassesTable.setBackground(new java.awt.Color(146, 178, 193));
        accountClassesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(accountClassesTable);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private corex.suite.LCTable accountClassesTable;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private com.simplecore.erp.client.controllers.servicebuttons.ButtonServices proceedButton;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

}
