package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.header;

import com.toedter.calendar.JTextFieldDateEditor;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import java.awt.Font;
import java.time.Instant;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import com.simplecore.erp.config.date.FormatDates;
import com.simplecore.erp.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;


public class MaintenanceRoutineSheetForm extends javax.swing.JPanel {
    
    
    public MaintenanceRoutineSheetForm() {
        initComponents();    
        addEvents();     
        
    }

    private void addEvents(){
        exitButton();
        buttonProceed();
        textFieldLimits();
        dateFormatSetting();
    }

    private void textFieldLimits(){
        
        ((AbstractDocument)maintRoutineCodeField.getTextBox().getDocument())
                .setDocumentFilter(new DocFilterVarcharWithoutSpace(8).setUpperCase(true));
        
    }
    private void dateFormatSetting(){
        
        ((JTextFieldDateEditor)scheduledDayPicker.getDateEditor()).setFont(new Font("Roboto Light", 0, 12));
        
        scheduledDayPicker.setDateFormatString(FormatDates.DATE_AMPM);
        scheduledDayPicker.setDate(Calendar.getInstance().getTime());
    }
    
    private void buttonProceed(){
        buttonProceed.addActionListener(e->{
            proceedToHeader();
        });
    }
    private void proceedToHeader() {

        if (maintRoutineCodeField.getTextBox().getText().isEmpty()) {
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            maintRoutineCodeField.getTextBox().requestFocus();
            return;
        }
        
        Instant instant = FormatDates.getInstantCurrentTimeFromNTPServers();
        String toDay = FormatDates.getFormattedInstant(instant, FormatDates.DATE_AMPM, LyraWorkspace.getTimeZoned());
        String datePicker = FormatDates.dateFormat(FormatDates.DATE_AMPM).format(scheduledDayPicker.getDate());
        
        if (!datePicker.equals(toDay)) {
            new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            ((JTextFieldDateEditor) scheduledDayPicker.getDateEditor()).requestFocus();
            return;
        }
        
        String routineSheet = maintRoutineCodeField.getTextBox().getText().trim();
        
        int counter = RoutineSheetHeaderDAO.getRoutineCounter(routineSheet, true);
        if (counter > 99) {
            new SystemMessages(NOT.msg(NOT.ROUTINE_SHEET_NOT_ALLOW_MORE_RECORDS), TypeMessage.WARNING);
            maintRoutineCodeField.getTextBox().requestFocus();
            return;
        }

        java.util.Date utilDate = scheduledDayPicker.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        RoutineSheetHeaderForm rsh = new RoutineSheetHeaderForm();
        rsh.setMaintRoutineSheet(routineSheet);
        rsh.setRoutineSheetCounter(counter);
        rsh.setScheduledDay(sqlDate);
        rsh.setMaintRoutingeSheetCodes();
        rsh.setLastPanel(this);

        PanelLoader.loadPanel(rsh, mainContainerPanel);

    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        buttonExit = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        buttonProceed = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        maintRoutineCodeField = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        roadmapCodeLabel = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        predefinedValuesPanel = new com.simplecore.erp.gui.components.panels.JPanelCornerPainted();
        scheduledDayLabel = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        scheduledDayPicker = new com.toedter.calendar.JDateChooser();
        predefinedValuesLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        buttonExit.setBackground(new java.awt.Color(226, 210, 144));
        buttonExit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        buttonProceed.setBackground(new java.awt.Color(226, 210, 144));
        buttonProceed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonProceed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(buttonProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonProceed, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        roadmapCodeLabel.setText("Maint.Routine sheet");
        roadmapCodeLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        predefinedValuesPanel.setBackground(new java.awt.Color(234, 242, 255));
        predefinedValuesPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        scheduledDayLabel.setText("Scheduled day");
        scheduledDayLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        predefinedValuesLabel.setBackground(new java.awt.Color(202, 216, 237));
        predefinedValuesLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        predefinedValuesLabel.setText("Predefined values");
        predefinedValuesLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        predefinedValuesLabel.setOpaque(true);

        javax.swing.GroupLayout predefinedValuesPanelLayout = new javax.swing.GroupLayout(predefinedValuesPanel);
        predefinedValuesPanel.setLayout(predefinedValuesPanelLayout);
        predefinedValuesPanelLayout.setHorizontalGroup(
            predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(predefinedValuesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scheduledDayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scheduledDayPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
            .addComponent(predefinedValuesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        predefinedValuesPanelLayout.setVerticalGroup(
            predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(predefinedValuesPanelLayout.createSequentialGroup()
                .addComponent(predefinedValuesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scheduledDayPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scheduledDayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(predefinedValuesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoLayout.createSequentialGroup()
                        .addComponent(roadmapCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(maintRoutineCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(582, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(roadmapCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maintRoutineCodeField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(predefinedValuesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private com.simplecore.erp.gui.components.searchbox.JSearchBox maintRoutineCodeField;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.labels.JLabelHQ predefinedValuesLabel;
    private com.simplecore.erp.gui.components.panels.JPanelCornerPainted predefinedValuesPanel;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined roadmapCodeLabel;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined scheduledDayLabel;
    private com.toedter.calendar.JDateChooser scheduledDayPicker;
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
