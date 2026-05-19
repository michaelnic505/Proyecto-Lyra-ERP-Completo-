package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.procedures;

import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;


public class ProcedureText extends javax.swing.JPanel {
    
    private LanguageManager languageManager;
    private boolean isSaved = false;
    
    public ProcedureText(LanguageManager languageManager) {
        this.languageManager=languageManager;
        initComponents();    
        init();     
    }

    private void init(){
        exitButton();
        saveButton();
        textAreaProperties();
    }

    private void textAreaProperties() {
        // Enable word wrapping for the text area
        procedureTextArea.setWrapStyleWord(true);

        // Enable line wrapping for the text area
        procedureTextArea.setLineWrap(true);

        // Set the font to Consolas, plain style, size 14
        procedureTextArea.setFont(new Font("Consolas", Font.PLAIN, 14));

        // Set the margins for the text area (top, left, bottom, right)
        procedureTextArea.setMargin(new Insets(20, 50, 100, 550));

        // Set a character limit based on MySQL TEXT type (maximum 16777215 characters)
        int limiteCaracteres = 16777215;  // Limit according to MySQL TEXT type

        // Apply a document filter to restrict the number of characters
        ((AbstractDocument) procedureTextArea.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String str, AttributeSet a) throws BadLocationException {
                // Only allow insertion if the total length does not exceed the character limit
                if ((fb.getDocument().getLength() + str.length()) <= limiteCaracteres) {
                    super.insertString(fb, offset, str, a);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet a) throws BadLocationException {
                // Only allow replacement if the total length does not exceed the character limit
                if ((fb.getDocument().getLength() + str.length() - length) <= limiteCaracteres) {
                    super.replace(fb, offset, length, str, a);
                }
            }
        });
     

        
    }

    private String procedureKey;
    private String extendedText;
    private String shortDescription;
    

    private void saveButton() {
        saveButton.addActionListener(e -> saveData());
    }

    private void saveData() {

        switch (typeTask) {
            case CREATE -> {
                createProcedure();
            }
            case CHANGE -> {
                changeProcedure();
            }
            case DELETE -> {
                deleteProcedure();
            }
            case VIEW ->{
                new SystemMessages(NOT.msg(NOT.ACCESS_DENIED), TypeMessage.ERROR);
            }


        }

    }
    private void createProcedure() {
        
        if (shorDescriptionTf.getText().isEmpty()) {
            // Show an error notification if the text area is empty
            shorDescriptionTf.requestFocus();
            new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.ERROR);
            return;
        }

        setShortDescription(shorDescriptionTf.getText().trim());
        
        // Check if the procedure text area is empty
        if (procedureTextArea.getText().isEmpty()) {
            // Show an error notification if the text area is empty
            new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.ERROR);
            return;
        }
        
        setExtendedText(procedureTextArea.getText());

        // Mark the procedure as saved
        isSaved = true;

        // Create a new MaintProcedures object and save the procedure
        MaintProcedures nMp = new MaintProcedures();
        nMp.createProcedureText(getProcedureKey(), getShortDescription(), getExtendedText());

        // Show a success notification with the procedure key
        new SystemMessages(NOT.msg(NOT.DATA_SAVED) + " " + getProcedureKey(), TypeMessage.SUCCESS);

        // Execute the panel transition after saving
        ((MaintenanceProcedures) lastPanel).getKeyTextField().setEditable(false);
        ((MaintenanceProcedures) lastPanel).getShortTextField().setText(shorDescriptionTf.getText());
        ((MaintenanceProcedures) lastPanel).getShortTextField().setEditable(false);
        PanelLoader.loadPanel(lastPanel, mainContainerPanel);
    }

    private void changeProcedure() {
        if (shorDescriptionTf.getText().isEmpty()) {
            // Show an error notification if the text area is empty
            shorDescriptionTf.requestFocus();
            new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.ERROR);
            return;
        }
        setShortDescription(shorDescriptionTf.getText().trim());
        
        // Check if the procedure text area is empty
        if (procedureTextArea.getText().isEmpty()) {
            // Show an error notification if the text area is empty
            new SystemMessages(NOT.msg(NOT.EMPTY_FIELDS), TypeMessage.ERROR);
            return;
        }
        setExtendedText(procedureTextArea.getText());

        MaintProcedures nMp = new MaintProcedures();
        nMp.updateProcedureText(getProcedureKey(), getShortDescription(), getExtendedText());
        // Show a success notification with the procedure key
        new SystemMessages(NOT.msg(NOT.DATA_SAVED) + " " + getProcedureKey(), TypeMessage.SUCCESS);

        // Execute the panel transition after saving
        ((MaintenanceProcedures) lastPanel).getKeyTextField().setEditable(false);
        ((MaintenanceProcedures) lastPanel).getShortTextField().setText(shorDescriptionTf.getText());
        ((MaintenanceProcedures) lastPanel).getShortTextField().setEditable(false);
        PanelLoader.loadPanel(lastPanel, mainContainerPanel);
    }
    private void deleteProcedure() {
        
        int reply = JOptionPane.showConfirmDialog(null,
                NOT.msg(NOT.ARE_YOU_SURE_DELETE_PROCEDURE),
                NOT.msg(NOT.TITLE),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        
        if(reply == JOptionPane.NO_OPTION){
            return;
        }
        
        MaintProcedures nMp = new MaintProcedures();
        nMp.deleteProcedure(getProcedureKey());
        
        new SystemMessages(NOT.msg(NOT.OPERATION_COMPLETED), TypeMessage.SUCCESS);
        
        PanelLoader.loadPanel(lastPanel, mainContainerPanel);
    }

    public String getProcedureKey() {
        return procedureKey;
    }
    public String getExtendedText() {
        return extendedText;
    }
    public void setProcedureKey(String procedureKey) {
        this.procedureKey = procedureKey;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public void setExtendedText(String extendedText) {
        this.extendedText = extendedText;
    }
    
    public JTextField getShorDescriptionTextField(){
        return shorDescriptionTf;
    }
    public JTextArea getTextArea(){
        return procedureTextArea;
    }
  
    
    public enum TypeTask{
        CREATE,
        CHANGE,
        VIEW,
        DELETE
    }
    private TypeTask typeTask;
    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;

        if (this.typeTask == TypeTask.VIEW) {
            shorDescriptionTf.setEditable(false);
            procedureTextArea.setEditable(false);
            saveButton.setEnabled(false);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        buttonExit = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        saveButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        jScrollPane1 = new javax.swing.JScrollPane();
        procedureTextArea = new javax.swing.JTextArea();
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

        saveButton.setBackground(new java.awt.Color(226, 210, 144));
        saveButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        procedureTextArea.setColumns(20);
        procedureTextArea.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        procedureTextArea.setLineWrap(true);
        procedureTextArea.setRows(5);
        procedureTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(procedureTextArea);

        procedureDescriptionLB.setText("Short description");
        procedureDescriptionLB.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(procedureDescriptionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(shorDescriptionTf, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(545, Short.MAX_VALUE))
            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(procedureDescriptionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shorDescriptionTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(481, Short.MAX_VALUE))
            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)))
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
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined procedureDescriptionLB;
    public static javax.swing.JTextArea procedureTextArea;
    private com.simplecore.erp.gui.components.labels.JButtonHQ saveButton;
    private javax.swing.JTextField shorDescriptionTf;
    // End of variables declaration//GEN-END:variables
 
    
    private JPanel lastPanel;
    public void setLastPanel(JPanel panel) {
        lastPanel = panel;
    }

    private void exitButton() {
        buttonExit.addActionListener((e) -> {

            // Check if there is unsaved data
            if (!procedureTextArea.getText().isEmpty() && !isSaved) {
                int response = JOptionPane.showConfirmDialog(
                        null,
                        NOT.msg(NOT.UNSAVED_DATA),
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (response == JOptionPane.NO_OPTION) {
                    return; // If the user selects "No", do nothing
                }
                // If the user selects "Yes", or if there is no unsaved data, continue to the next line
            }

            // Execute the transition and play the sound after the validation
            
            ((MaintenanceProcedures)lastPanel).getKeyTextField().setEditable(false);
            ((MaintenanceProcedures)lastPanel).getShortTextField().setText(shorDescriptionTf.getText());
            ((MaintenanceProcedures)lastPanel).getShortTextField().setEditable(false);
            
            PanelLoader.loadPanel(lastPanel, mainContainerPanel);
        });
    }


    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }

}
