package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.header;

import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.creation.activity.RoutineSheetActivity;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.UsagesWindowList;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.OperationTypesMaintenance;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.OperationTypesListWindow;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.PlanningGroups;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.PlanningGroupsListWindow;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.MaintenanceStrategies;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.MaintenanceStrategiesWindow;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.components.togglebuttons.ToggleListener;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import com.simplecore.erp.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.utils.documentfilters.DocumentFilterNumeric;
import com.simplecore.erp.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.OperationContextWindow;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.OperationContexts;
import com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar.RoutineSheetsUsage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;


public class RoutineSheetHeaderForm extends javax.swing.JPanel {

    private LanguageManager languageManager;
    private boolean isHeaderSaved = false;

    public RoutineSheetHeaderForm() {

        this.languageManager = LyraWorkspace.getLanguageManager();
        initComponents();
        addComponentsEvents();
        SwingUtilities.invokeLater(() -> {
            setStatusButton();

        });

    }

    private void addComponentsEvents(){
        setFielsdLimit();
        exitButton();
        saveButton();
        activityButton();
        openOperationContextList();
        openPlanningGroupsList();
        openOperationTypeList();
        openMaintenanceStrategiesList();
        openUsagesList();
        setOperationContextBySearch(languageManager.getLocale().getLanguage());
        setPlannigGroupBySearh();
        setOperationTypeBySearch();
        setMaintenanceStrategyBySearch();
        setUsageBySearch(languageManager.getLocale().getLanguage());
    }

    
    private String maintRoutineSheet;
    private Date scheduledDay;
    private int routineSheetCounter;
    private String positionName;
    private String planningGroup;
    private String jobPosition;
    private String routineSheetStatus;
    private int operatingContext;
    private String maintenanceStrategy;
    private int usage;
    private boolean status;
    private String idRoutineSheet;
    
    public void setMaintRoutingeSheetCodes(){
   
        routineCodeTitleLb.setText(getMaintRoutineSheet());
        routineKeyTf.setText(getMaintRoutineSheet());
        counterRoutineTf.setText(String.valueOf(getRoutineSheetCounter()));
    
    }
    
    private void setFielsdLimit() {
        ((AbstractDocument) counterRoutineTf.getDocument()).setDocumentFilter(new DocumentFilterNumeric(2));
        ((AbstractDocument) positionNameTf.getDocument()).setDocumentFilter(new DocumentFilterVarchar(45).setUpperCase(true));
        ((AbstractDocument) planningGroupCodeTf.getTextBox().getDocument()).setDocumentFilter(new DocFilterVarcharWithoutSpace(15));
        ((AbstractDocument) operationTypeTf.getTextBox().getDocument()).setDocumentFilter(new DocFilterVarcharWithoutSpace(15));
        ((AbstractDocument) operatingContextTf.getTextBox().getDocument()).setDocumentFilter(new DocumentFilterNumeric(1));
        ((AbstractDocument) maintStrategyKeyTf.getTextBox().getDocument()).setDocumentFilter(new DocFilterVarcharWithoutSpace(8));
        ((AbstractDocument) usageRoutineTf.getTextBox().getDocument()).setDocumentFilter(new DocumentFilterNumeric(1));
    }
    
    public String getMaintRoutineSheet() {
        return maintRoutineSheet;
    }
    public Date getScheduledDay() {
        return scheduledDay;
    }
    public int getRoutineSheetCounter() {
        return routineSheetCounter;
    }
    public String getPositionName() {
        return positionName;
    }
    public String getPlanningGroup() {
        return planningGroup;
    }
    public String getOperationType() {
        return jobPosition;
    }
    public String getRoutineSheetStatus() {
        return routineSheetStatus;
    }
    public int getOperatingContext() {
        return operatingContext;
    }
    public String getMaintenanceStrategy() {
        return maintenanceStrategy;
    }
    public int getUsage() {
        return usage;
    }
    public boolean isStatus() {
        return status;
    }

    public void setMaintRoutineSheet(String maintRoutineSheet) {
        this.maintRoutineSheet = maintRoutineSheet;
    }
    public void setScheduledDay(Date scheduledDay) {
        this.scheduledDay = scheduledDay;
    }
    public void setRoutineSheetCounter(int routineSheetCounter) {
        this.routineSheetCounter = routineSheetCounter;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public void setPlanningGroup(String planningGroup) {
        this.planningGroup = planningGroup;
    }
    public void setOperationType(String jobPosition) {
        this.jobPosition = jobPosition;
    }
    public void setRoutineSheetStatus(String routineSheetStatus) {
        this.routineSheetStatus = routineSheetStatus;
    }
    public void setOperatingContext(int operatingContext) {
        this.operatingContext = operatingContext;
    }
    public void setMaintenanceStrategy(String maintenanceStrategy) {
        this.maintenanceStrategy = maintenanceStrategy;
    }
    public void setUsage(int usage) {
        this.usage = usage;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
    
    //Metodos ventanas de listas de campos
    private void openOperationContextList(){
        operatingContextTf.getButton().addActionListener(e->{
            
          OperationContextWindow ocw = new OperationContextWindow(getSuperFrame()
                  ,operatingContextTf.getButton(),languageManager);
          
          ocw.setJTextFieldCode(operatingContextTf.getTextBox());
          ocw.setJLabel(operatingContextDescriptionLabel);
          ocw.setVisible(true);
            
            
        });
    }
    private void setOperationContextBySearch(String language){
        operatingContextTf.getTextBox().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (operatingContextTf.getTextBox().getText().isEmpty()) {
           
                        operatingContextTf.getTextBox().setText(null);
                        operatingContextDescriptionLabel.setText(null);
                        return;
                    }
                    int value = Integer.parseInt(operatingContextTf.getTextBox().getText());
                    String description = OperationContexts.getDescriptionByValue(value, language);

                    if (description == null) {
                        operatingContextTf.getTextBox().setText(null);
                        operatingContextDescriptionLabel.setText(null);
                        new SystemMessages(NOT.msg(NOT.CODE_DOES_NOT_EXIST), TypeMessage.ERROR);
                        return;
                    }
                    operatingContextTf.getTextBox().setEditable(false);
                    operatingContextTf.getButton().setEnabled(false);
                    operatingContextDescriptionLabel.setText(description);
                }
                if (operatingContextTf.getTextBox().isEditable()&&e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    operatingContextTf.getTextBox().setText(null);
                    operatingContextDescriptionLabel.setText(null);
                }
            }

        });
    }
    
    private void openPlanningGroupsList(){
        
        planningGroupCodeTf.getButton().addActionListener(e->{
            
            Frame parent = getSuperFrame();
            JButton button = planningGroupCodeTf.getButton();
            
            PlanningGroupsListWindow pG = 
                    new PlanningGroupsListWindow(parent,button,languageManager);
            pG.setJTextFieldCode(planningGroupCodeTf.getTextBox());
            pG.setJLabel(planningGroupNameLb);
            pG.setVisible(true);
            
        });
        
        
    }
    private void setPlannigGroupBySearh(){
        planningGroupCodeTf.getTextBox().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!planningGroupCodeTf.getTextBox().getText().isEmpty()) {

                        String code = planningGroupCodeTf.getTextBox().getText();
                        String description = PlanningGroups.getDescriptionByCode(code);

                        if (description == null) {

                            planningGroupCodeTf.getTextBox().setText(null);
                            planningGroupNameLb.setText(null);
                            new SystemMessages(NOT.msg(NOT.CODE_DOES_NOT_EXIST), TypeMessage.ERROR);
                            return;
                        }
                        planningGroupCodeTf.getTextBox().setEditable(false);
                        planningGroupCodeTf.getButton().setEnabled(false);
                        planningGroupCodeTf.getTextBox().setText(code.toUpperCase());
                        planningGroupNameLb.setText(description);
                        

                    }
                }
                if (planningGroupCodeTf.getTextBox().isEditable() && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    planningGroupCodeTf.getTextBox().setText(null);
                    planningGroupNameLb.setText(null);
                }
            }
            
        });
    }
    
    private void openOperationTypeList() {
        operationTypeTf.getButton().addActionListener(e -> {

            Frame parent = getSuperFrame();
            JButton button = operationTypeTf.getButton();

            OperationTypesListWindow oPt = new OperationTypesListWindow(parent, button, languageManager);
            oPt.setJTextFieldCode(operationTypeTf.getTextBox());
            oPt.setJLabel(operationTypeDescriptionLb);
            oPt.setVisible(true);

        });
    }
    private void setOperationTypeBySearch() {
        operationTypeTf.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!operationTypeTf.getTextBox().getText().isEmpty()) {

                        String code = operationTypeTf.getTextBox().getText();
                        String description = OperationTypesMaintenance.getDescriptionByCode(code);

                        if (description == null) {
                            operationTypeTf.getTextBox().setText(null);
                            operationTypeDescriptionLb.setText(null);
                            new SystemMessages(NOT.msg(NOT.CODE_DOES_NOT_EXIST), TypeMessage.ERROR);
                            return;
                        }

                        operationTypeTf.getTextBox().setText(code.toUpperCase());
                        operationTypeTf.getButton().setEnabled(false);
                        operationTypeTf.getTextBox().setEditable(false);
                        operationTypeDescriptionLb.setText(description);

                    }
                }
                if (operationTypeTf.getTextBox().isEditable()&&e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    operationTypeTf.getTextBox().setText(null);
                    operationTypeDescriptionLb.setText(null);
                }
            }
        });
    }
    
    private void openMaintenanceStrategiesList() {
        maintStrategyKeyTf.getButton().addActionListener(e -> {

            Frame parent = getSuperFrame();
            JButton button = maintStrategyKeyTf.getButton();

            MaintenanceStrategiesWindow msl = new MaintenanceStrategiesWindow(parent, button, languageManager);
            msl.setJTextFieldCode(maintStrategyKeyTf.getTextBox());
            msl.setJLabel(strategyDescriptionLabel);
            msl.setVisible(true);

        });
    }
    private void setMaintenanceStrategyBySearch(){
        maintStrategyKeyTf.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!maintStrategyKeyTf.getTextBox().getText().isEmpty()) {
                        
                        String code = maintStrategyKeyTf.getTextBox().getText();
                        String description = MaintenanceStrategies.getDescriptionByCode(code);
                        
                        if (description == null) {
                            maintStrategyKeyTf.getTextBox().setText(null);
                            strategyDescriptionLabel.setText(null);
                            new SystemMessages(NOT.msg(NOT.CODE_DOES_NOT_EXIST), TypeMessage.ERROR);
                            return;
                        }
                        maintStrategyKeyTf.getTextBox().setEditable(false);
                        maintStrategyKeyTf.getButton().setEnabled(false);
                        maintStrategyKeyTf.getTextBox().setText(code.toUpperCase());
                        strategyDescriptionLabel.setText(description);
                    }
                }
                if (maintStrategyKeyTf.getTextBox().isEditable()&&e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    maintStrategyKeyTf.getTextBox().setText(null);
                    strategyDescriptionLabel.setText(null);
                }
            }
        });
    }
    
    private void setStatusButton() {

        String status1 = LyraWorkspace.getTranslator().getTranslation("RoutineSheetActiveStatus.text");
        String status0 = LyraWorkspace.getTranslator().getTranslation("RoutineSheetInactiveStatus.text");

        String state = statuRoutineButton.isSelected() ? status1 : status0;
        statusDescriptionLabel.setText(state);
        
        statuRoutineButton.addEventToggleSelected(new ToggleListener(){
            @Override
            public void onSelected(boolean selected) {
                if(selected){
                    statusDescriptionLabel.setText(status1);
                }else{
                    statusDescriptionLabel.setText(status0);
                }
            }

            @Override
            public void onAnimated(float animated) {
                
            }
            
        });
    }
    private void openUsagesList() {
        usageRoutineTf.getButton().addActionListener(e -> {

            Frame parent = getSuperFrame();
            JButton button = usageRoutineTf.getButton();
            
            UsagesWindowList ul = new UsagesWindowList(parent,button,languageManager);
            ul.setJTextFieldCode(usageRoutineTf.getTextBox());
            ul.setJLabel(usageDescriptionLabel);
            ul.setVisible(true);

        });
    }
    private void setUsageBySearch(String language) {
        usageRoutineTf.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (usageRoutineTf.getTextBox().getText().isEmpty()) {
                        usageRoutineTf.getTextBox().setText(null);
                        usageDescriptionLabel.setText(null);
                        return;
                    }

                    int value = Integer.parseInt(usageRoutineTf.getTextBox().getText());
                    String description = RoutineSheetsUsage.getDescriptionById(value);

                    if (description == null) {
                        usageRoutineTf.getTextBox().setText(null);
                        usageDescriptionLabel.setText(null);
                        new SystemMessages(NOT.msg(NOT.CODE_DOES_NOT_EXIST), TypeMessage.ERROR);
                        return;
                    }

                    usageRoutineTf.getTextBox().setEditable(false);
                    usageRoutineTf.getButton().setEnabled(false);
                    usageDescriptionLabel.setText(description);

                }
                if (usageRoutineTf.getTextBox().isEditable() && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    usageRoutineTf.getTextBox().setText(null);
                    usageDescriptionLabel.setText(null);
                }
            }

        });
    }

    private boolean hasAllFieldsFullyFilled() {
        JTextComponent[] fields = {
            positionNameTf,
            planningGroupCodeTf.getTextBox(),
            operationTypeTf.getTextBox(),
            operatingContextTf.getTextBox(),
            maintStrategyKeyTf.getTextBox(),
            usageRoutineTf.getTextBox()
        };

        for (JTextComponent field : fields) {
            if (field.getText().trim().isEmpty()) {
                field.requestFocus(); // Set focus on the empty field
                new SystemMessages(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR); // Show notification
                return false;
            }
        }

        return true; // All fields are filled
    }

    private void getValuesFromFields(){
       
        setMaintRoutineSheet(routineKeyTf.getText().trim());
        setRoutineSheetCounter(Integer.parseInt(counterRoutineTf.getText()));
        setPositionName(positionNameTf.getText().trim());
        setPlanningGroup(planningGroupCodeTf.getTextBox().getText().trim());
        setOperationType(operationTypeTf.getTextBox().getText().trim());
        setStatus(statuRoutineButton.isSelected());
        setOperatingContext(Integer.parseInt(operatingContextTf.getTextBox().getText().trim()));
        setMaintenanceStrategy(maintStrategyKeyTf.getTextBox().getText().trim());
        setUsage(Integer.parseInt(usageRoutineTf.getTextBox().getText()));
        
    }

    private void saveRoutineSheetHeader() {

        if (hasAllFieldsFullyFilled()) {

            getValuesFromFields();
            
            RoutineSheetHeader header = new RoutineSheetHeader.Builder()
                    .setRoutineSheet(getMaintRoutineSheet())
                    .setCounter(getRoutineSheetCounter())
                    .setPositionName(getPositionName())
                    .setPlanningGroup(getPlanningGroup())
                    .setOperationType(getOperationType())
                    .setStatus(isStatus())
                    .setOperatingContext(getOperatingContext())
                    .setMaintenanceStrategy(getMaintenanceStrategy())
                    .setUsage(getUsage())
                    .setScheduledDay(getScheduledDay())
                    .build();
            
            RoutineSheetHeaderDAO routineHeader = new RoutineSheetHeaderDAO();
            routineHeader.insert(header);
            
            isHeaderSaved = true;
            new SystemMessages(NOT.msg(NOT.ROUTINE_SHEET_CREATED)+" "+getMaintRoutineSheet(), TypeMessage.SUCCESS);
        }

    }
    private void saveButton(){
        saveButton.addActionListener(e->{
            saveRoutineSheetHeader();
        });
    }
    
    private RoutineSheetActivity rsa;
    private void openActivitySection() {
        getValuesFromFields();

        if (rsa == null) {
            
            rsa = new RoutineSheetActivity(languageManager);
            rsa.setLastPanel(this);
            rsa.setRoutineSheetCode(getMaintRoutineSheet());
            rsa.setRoutinePositionName(getPositionName());
            rsa.setRoutineCounter(String.valueOf(getRoutineSheetCounter()));
            rsa.setHeaderSaved(isHeaderSaved);
            rsa.setMaintenanceStrategy(getMaintenanceStrategy());
            
            /*variable to save the routine sheet*/
            rsa.setPlanningGroup(getPlanningGroup());
            rsa.setOperationType(getOperationType());
            rsa.setStatus(isStatus());
            rsa.setOperatingContext(getOperatingContext());
            rsa.setUsage(getUsage());
            rsa.setScheduledDate(getScheduledDay());
            
        }

        PanelLoader.loadPanel(rsa, mainContainerPanel);

    }
    
    private void activityButton(){
        activityButton.addActionListener(e->{
            if(hasAllFieldsFullyFilled()){
                openActivitySection();
            }
            
        });
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        exitButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        saveButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        maintRoutineLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        routineKeyTf = new javax.swing.JTextField();
        routineCounterLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        counterRoutineTf = new javax.swing.JTextField();
        positionNameTf = new javax.swing.JTextField();
        planningGroupLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        planningGroupCodeTf = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        predefinedValuesPanel = new com.simplecore.erp.gui.components.panels.JPanelCornerPainted();
        predefinedValuesLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        operationTypeLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        operationTypeTf = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        roadmapStatusLabel = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        statuRoutineButton = new com.simplecore.erp.gui.components.togglebuttons.ToggleButton();
        operationContextLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        statusDescriptionLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        operatingContextTf = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        operatingContextDescriptionLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        maintenanceStrategyLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        maintStrategyKeyTf = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        strategyDescriptionLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        operationTypeDescriptionLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        usageLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        usageRoutineTf = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        usageDescriptionLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        activityButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        summaryButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        routineTitleLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineCodeTitleLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        routineNameLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        positionNameLb = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        planningGroupNameLb = new com.simplecore.erp.gui.components.labels.JLabelHQ();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        exitButton.setBackground(new java.awt.Color(226, 210, 144));
        exitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        saveButton.setBackground(new java.awt.Color(226, 210, 144));
        saveButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        maintRoutineLb.setText("Routine sheet");
        maintRoutineLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        routineKeyTf.setEditable(false);
        routineKeyTf.setBackground(new java.awt.Color(202, 216, 237));
        routineKeyTf.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        routineKeyTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        routineCounterLb.setText("Rout.Sheet.Cnt.");
        routineCounterLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        counterRoutineTf.setEditable(false);
        counterRoutineTf.setBackground(new java.awt.Color(202, 216, 237));
        counterRoutineTf.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        counterRoutineTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        positionNameTf.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        positionNameTf.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        planningGroupLb.setText("Planning group");
        planningGroupLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        predefinedValuesPanel.setBackground(new java.awt.Color(234, 242, 255));
        predefinedValuesPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        predefinedValuesLabel.setBackground(new java.awt.Color(202, 216, 237));
        predefinedValuesLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        predefinedValuesLabel.setText("Assignments to routine header");
        predefinedValuesLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        predefinedValuesLabel.setOpaque(true);

        operationTypeLb.setText("Operation type");
        operationTypeLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        roadmapStatusLabel.setText("Rout.Sheet status");
        roadmapStatusLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        statuRoutineButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        operationContextLb.setText("Op.Context");
        operationContextLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        statusDescriptionLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        statusDescriptionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        operatingContextDescriptionLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        operatingContextDescriptionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        maintenanceStrategyLb.setText("Maint.Strategy");
        maintenanceStrategyLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        strategyDescriptionLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        strategyDescriptionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        operationTypeDescriptionLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        operationTypeDescriptionLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        usageLb.setText("Usage");
        usageLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        usageDescriptionLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        usageDescriptionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout predefinedValuesPanelLayout = new javax.swing.GroupLayout(predefinedValuesPanel);
        predefinedValuesPanel.setLayout(predefinedValuesPanelLayout);
        predefinedValuesPanelLayout.setHorizontalGroup(
            predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(predefinedValuesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(predefinedValuesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(predefinedValuesPanelLayout.createSequentialGroup()
                        .addComponent(maintenanceStrategyLb, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(maintStrategyKeyTf, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(strategyDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(predefinedValuesPanelLayout.createSequentialGroup()
                        .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(operationTypeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roadmapStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(operationContextLb, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(predefinedValuesPanelLayout.createSequentialGroup()
                                .addComponent(operationTypeTf, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addComponent(operationTypeDescriptionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(predefinedValuesPanelLayout.createSequentialGroup()
                                .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(operatingContextTf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(statuRoutineButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                                .addGap(71, 71, 71)
                                .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(operatingContextDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(predefinedValuesPanelLayout.createSequentialGroup()
                        .addComponent(usageLb, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(usageRoutineTf, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usageDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        predefinedValuesPanelLayout.setVerticalGroup(
            predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(predefinedValuesPanelLayout.createSequentialGroup()
                .addComponent(predefinedValuesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(operationTypeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operationTypeTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operationTypeDescriptionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(roadmapStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statuRoutineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(operationContextLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operatingContextTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operatingContextDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(maintenanceStrategyLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maintStrategyKeyTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strategyDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(predefinedValuesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(usageLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usageRoutineTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usageDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        activityButton.setBackground(new java.awt.Color(226, 210, 144));
        activityButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        activityButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/launch.png"))); // NOI18N
        activityButton.setText("Activity");
        activityButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        summaryButton.setBackground(new java.awt.Color(226, 210, 144));
        summaryButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        summaryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/launch.png"))); // NOI18N
        summaryButton.setText("Summary");
        summaryButton.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N

        routineTitleLb.setText("M.Rout.Sheet");
        routineTitleLb.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        routineTitleLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        routineCodeTitleLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        routineCodeTitleLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        routineNameLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        routineNameLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        positionNameLb.setText("Position name");
        positionNameLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        planningGroupNameLb.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        planningGroupNameLb.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maintRoutineLb, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(planningGroupLb, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positionNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFondoLayout.createSequentialGroup()
                                .addComponent(planningGroupCodeTf, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(planningGroupNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(positionNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelFondoLayout.createSequentialGroup()
                                .addComponent(routineKeyTf, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(routineCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(counterRoutineTf, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(predefinedValuesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(routineTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(routineCodeTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(routineNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(activityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(summaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(427, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(routineTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(routineCodeTitleLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(routineNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(maintRoutineLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(routineKeyTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(routineCounterLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(counterRoutineTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(positionNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(positionNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(planningGroupLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(planningGroupCodeTf, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(planningGroupNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(predefinedValuesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(summaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
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
    private com.simplecore.erp.gui.components.labels.JButtonHQ activityButton;
    private javax.swing.JTextField counterRoutineTf;
    private com.simplecore.erp.gui.components.labels.JButtonHQ exitButton;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined maintRoutineLb;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox maintStrategyKeyTf;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined maintenanceStrategyLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ operatingContextDescriptionLabel;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox operatingContextTf;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined operationContextLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ operationTypeDescriptionLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined operationTypeLb;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox operationTypeTf;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox planningGroupCodeTf;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined planningGroupLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ planningGroupNameLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined positionNameLb;
    private javax.swing.JTextField positionNameTf;
    private com.simplecore.erp.gui.components.labels.JLabelHQ predefinedValuesLabel;
    private com.simplecore.erp.gui.components.panels.JPanelCornerPainted predefinedValuesPanel;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined roadmapStatusLabel;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineCodeTitleLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined routineCounterLb;
    private javax.swing.JTextField routineKeyTf;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineNameLb;
    private com.simplecore.erp.gui.components.labels.JLabelHQ routineTitleLb;
    private com.simplecore.erp.gui.components.labels.JButtonHQ saveButton;
    private com.simplecore.erp.gui.components.togglebuttons.ToggleButton statuRoutineButton;
    private com.simplecore.erp.gui.components.labels.JLabelHQ statusDescriptionLabel;
    private com.simplecore.erp.gui.components.labels.JLabelHQ strategyDescriptionLabel;
    private com.simplecore.erp.gui.components.labels.JButtonHQ summaryButton;
    private com.simplecore.erp.gui.components.labels.JLabelHQ usageDescriptionLabel;
    private com.simplecore.erp.gui.components.labels.JLabelHQUnderlined usageLb;
    private com.simplecore.erp.gui.components.searchbox.JSearchBox usageRoutineTf;
    // End of variables declaration//GEN-END:variables
 
    private JPanel lastPanel;

    public void setLastPanel(JPanel panel) {
        lastPanel = panel;
    }

    private void exitButton() {
        exitButton.addActionListener((e) -> {

            PanelLoader.loadPanel(lastPanel, mainContainerPanel);

        });
    }

    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }

}
