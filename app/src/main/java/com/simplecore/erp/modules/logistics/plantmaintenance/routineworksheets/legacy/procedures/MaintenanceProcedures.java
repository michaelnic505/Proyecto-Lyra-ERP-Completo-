package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.procedures;

import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import com.simplecore.erp.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import com.simplecore.erp.gui.workspace.LyraFrame;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;


public class MaintenanceProcedures extends javax.swing.JPanel {
    
    private LanguageManager languageManager;
    private String username;
    
    public MaintenanceProcedures(String username) {
        this.languageManager = LyraWorkspace.getLanguageManager(); // Initialize the language manager
        this.username = username;
        initComponents(); // Initialize UI components
        addEvents(); // Add event listeners to buttons
    }

    private void addEvents(){
        exitButton(); // Set up the exit button action
        buttonProceed(); // Set up the proceed button action
        buttonEdit(); // Set up the edit button action
        buttonView(); // Set up the view button action
        buttonDelete(); // Set up the delete button action
        textFieldLimits(); // Apply input limits on text fields
        openProceduresList();
        openProcedureByKey();
    }

    private void textFieldLimits(){
        // Set document filters to limit input length and enforce uppercase
        ((AbstractDocument)procedureKeyTB.getTextBox().getDocument())
            .setDocumentFilter(new DocFilterVarcharWithoutSpace(8).setUpperCase(true));
        
        ((AbstractDocument)shorDescriptionTf.getDocument())
            .setDocumentFilter(new DocumentFilterVarchar(45));
    }

    private void buttonProceed(){
        buttonProceed.addActionListener(e->newProcedure()); // Add action listener for proceed button
    }
    private void buttonEdit(){
        editButton.addActionListener(e->editProcedure()); // Add action listener for edit button
    }
    private void buttonView(){
        viewButton.addActionListener(e->viewProcedure());
    }
    private void buttonDelete(){
        deleteButton.addActionListener(e->deleteProcedure()); // Add action listener for delete button
    }

    private void newProcedure() {
        
        // Validate that the procedure key field is not empty
        if (procedureKeyTB.getTextBox().getText().isEmpty()) {
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            procedureKeyTB.getTextBox().requestFocus();
            return;
        }
        
        // Check if the procedure key already exists
        String procedureKey = procedureKeyTB.getTextBox().getText().trim();
        
        if (MaintProcedures.keyExists(procedureKey)) {
            new SystemMessages(NOT.msg(NOT.PROCEDURE_KEY_EXISTS), TypeMessage.WARNING);
            return;
        }

        // Validate that the short description field is not empty
        if (shorDescriptionTf.getText().isEmpty()) {
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            shorDescriptionTf.requestFocus();
            return;
        }
        
        // Check if the user has permission to create a procedure
        if(!MaintProcedures.canCreate(username)){
            new SystemMessages(NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.WARNING);
            return;
        }

        String shorDescription = shorDescriptionTf.getText().trim();

        // Create a new procedure text panel
        ProcedureText newText = new ProcedureText(languageManager);
        newText.setProcedureKey(procedureKey);
        newText.getShorDescriptionTextField().setText(shorDescription);
        newText.setTypeTask(ProcedureText.TypeTask.CREATE);
        newText.setLastPanel(this);

        PanelLoader.loadPanel(newText, mainContainerPanel); // Load the new panel
    }
    private void editProcedure() {
        // Validate that the procedure key field is not empty
        if (procedureKeyTB.getTextBox().getText().isEmpty()) {
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            procedureKeyTB.getTextBox().requestFocus();
            return;
        }
        
        // Check if the procedure key exists
        String procedureKey = procedureKeyTB.getTextBox().getText();
        
        if (!MaintProcedures.keyExists(procedureKey)) {
            new SystemMessages(NOT.msg(NOT.PROCEDURE_KEY_NOT_FOUND), TypeMessage.WARNING);
            return;
        }

        shorDescriptionTf.setText(MaintProcedures.getShortDescriptionByKey(procedureKey));

        // Check if the user has permission to edit a procedure
        if (!MaintProcedures.canUpdate(username)) {
            new SystemMessages(NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.WARNING);
            return;
        }

        String shorDescription = shorDescriptionTf.getText().trim();

        // Create an edit procedure text panel
        ProcedureText editText = new ProcedureText(languageManager);
        editText.setProcedureKey(procedureKey);
        editText.getShorDescriptionTextField().setText(shorDescription);
        editText.setTypeTask(ProcedureText.TypeTask.CHANGE);
        editText.getTextArea().setText(MaintProcedures.getProcedureTextByKey(procedureKey));
        editText.setLastPanel(this);
        
        PanelLoader.loadPanel(editText, mainContainerPanel); // Load the edit panel
    }
    private void viewProcedure() {
        // Validate that the procedure key field is not empty
        if (procedureKeyTB.getTextBox().getText().isEmpty()) {
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            procedureKeyTB.getTextBox().requestFocus();
            return;
        }

        // Check if the procedure key exists
        String procedureKey = procedureKeyTB.getTextBox().getText();

        if (!MaintProcedures.keyExists(procedureKey)) {
            new SystemMessages(NOT.msg(NOT.PROCEDURE_KEY_NOT_FOUND), TypeMessage.WARNING);
            return;
        }
        shorDescriptionTf.setText(MaintProcedures.getShortDescriptionByKey(procedureKey));
        // Check if the user has permission to edit a procedure
        if (!MaintProcedures.canView(username)) {
            new SystemMessages(NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.WARNING);
            return;
        }
        
        String shorDescription = shorDescriptionTf.getText().trim();
        
        ProcedureText editText = new ProcedureText(languageManager);
        editText.setProcedureKey(procedureKey);
        editText.getShorDescriptionTextField().setText(shorDescription);
        editText.setTypeTask(ProcedureText.TypeTask.VIEW);
        editText.getTextArea().setText(MaintProcedures.getProcedureTextByKey(procedureKey));
        editText.setLastPanel(this);
        
        PanelLoader.loadPanel(editText, mainContainerPanel); // Load the edit panel

    }
    private void deleteProcedure() {
        // Validate that the procedure key field is not empty
        if (procedureKeyTB.getTextBox().getText().isEmpty()) {
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            procedureKeyTB.getTextBox().requestFocus();
            return;
        }

        // Check if the procedure key exists
        String procedureKey = procedureKeyTB.getTextBox().getText();

        if (!MaintProcedures.keyExists(procedureKey)) {
            new SystemMessages(NOT.msg(NOT.PROCEDURE_KEY_NOT_FOUND), TypeMessage.WARNING);
            return;
        }
        shorDescriptionTf.setText(MaintProcedures.getShortDescriptionByKey(procedureKey));
        
        // Check if the user has permission to delete a procedure
        if (!MaintProcedures.canDelete(username)) {
            new SystemMessages(NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.WARNING);
            return;
        }

        // Confirm deletion with the user
        int response = JOptionPane.showConfirmDialog(null,
                NOT.msg(NOT.ARE_YOU_SURE_DELETE_PROCEDURE),
                NOT.msg(NOT.TITLE),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
 
        if(response == JOptionPane.NO_OPTION){
            return;
        }

        String shorDescription = shorDescriptionTf.getText().trim();
        
        // Create a delete procedure text panel
        ProcedureText editText = new ProcedureText(languageManager);
        editText.setProcedureKey(procedureKey);
        editText.getShorDescriptionTextField().setText(shorDescription);
        editText.setTypeTask(ProcedureText.TypeTask.DELETE);
        editText.getTextArea().setText(MaintProcedures.getProcedureTextByKey(procedureKey));
        editText.setLastPanel(this);

        PanelLoader.loadPanel(editText, mainContainerPanel); // Load the delete panel
    }

    private void openProceduresList(){
        procedureKeyTB.getButton().addActionListener(e->{
            
            Frame frame = getSuperFrame();
            JButton button = procedureKeyTB.getButton();
            
            ProceduresList pl = new ProceduresList(frame,button);
            pl.setJTextFieldCode(procedureKeyTB.getTextBox());
            pl.setJTextField2(shorDescriptionTf);
            pl.setVisible(true);
            
            
        });
    }
    private void openProcedureByKey(){
        procedureKeyTB.getTextBox().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(!procedureKeyTB.getTextBox().getText().isEmpty()){
                        
                        String procedureKey = procedureKeyTB.getTextBox().getText();
                        if(MaintProcedures.keyExists(procedureKey)){

                            shorDescriptionTf.setText(MaintProcedures.getShortDescriptionByKey(procedureKey));

                            procedureKeyTB.getTextBox().setEditable(false);
                            shorDescriptionTf.setEditable(false);
                            
                        }else{
                            shorDescriptionTf.setText(null);
                        }
                        
                    }
                }
            }
            
        });
    }
    
    public JTextField getKeyTextField() {
        return procedureKeyTB.getTextBox();
    }

    public JTextField getShortTextField() {
        return shorDescriptionTf;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        buttonExit = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        buttonProceed = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        editButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        deleteButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        viewButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        procedureKeyTB = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        procedureKeyLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        procedureDescriptionLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        shorDescriptionTf = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        buttonExit.setBackground(new java.awt.Color(226, 210, 144));
        buttonExit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        buttonProceed.setBackground(new java.awt.Color(226, 210, 144));
        buttonProceed.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonProceed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N

        editButton.setBackground(new java.awt.Color(226, 210, 144));
        editButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/edit.png"))); // NOI18N

        deleteButton.setBackground(new java.awt.Color(226, 210, 144));
        deleteButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/delete_trash.png"))); // NOI18N

        viewButton.setBackground(new java.awt.Color(226, 210, 144));
        viewButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        viewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/view_lens.png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(buttonProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonProceed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        procedureKeyLB.setText("Procedure key");
        procedureKeyLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        procedureDescriptionLB.setText("Short description");
        procedureDescriptionLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(procedureKeyLB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(procedureDescriptionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shorDescriptionTf, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(procedureKeyTB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(545, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(procedureKeyLB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(procedureKeyTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(procedureDescriptionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shorDescriptionTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonExit;
    private com.simplecore.erp.gui.components.labels.JButtonHQ buttonProceed;
    private com.simplecore.erp.gui.components.labels.JButtonHQ deleteButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ editButton;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined procedureDescriptionLB;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined procedureKeyLB;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox procedureKeyTB;
    private javax.swing.JTextField shorDescriptionTf;
    private com.simplecore.erp.gui.components.labels.JButtonHQ viewButton;
    // End of variables declaration//GEN-END:variables
 
    
    private JPanel lastPanel;
    public void setLastPanel(JPanel panel){
        lastPanel = panel;
    }
    
    private void exitButton() {
        buttonExit.addActionListener((e) -> {
            PanelLoader.loadPanel(treeMenus, mainContainerPanel);
        });
    }  
    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }


}
