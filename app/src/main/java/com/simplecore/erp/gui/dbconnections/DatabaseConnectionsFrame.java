package com.simplecore.erp.gui.dbconnections;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.utils.splash.LyraCoreSplash;
import com.simplecore.erp.config.database.utils.ClearTable;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.gui.login.Login;
import com.simplecore.erp.models.dbconnections.DatabaseConnectionFileReader;
import com.simplecore.erp.services.dbconnections.ConnectionService;
import com.simplecore.erp.controllers.gui.WindowCustomizer;

public class DatabaseConnectionsFrame extends javax.swing.JFrame {

    public static LyraCoreSplash splashPanel;

    public DatabaseConnectionsFrame() {

        initComponents();
        initializeTableModel();
        createDirectoriesIfNeeded();
        loadExistingFilesList();
        configureWindowProperties();
        
    }

    private void setBorders() {
        setTitle("Lyra Core+ >> Connections");
        MatteBorder matteBorder = new MatteBorder(3, 3, 3, 3, new Color(0, 99, 155)); // Borde gris
        getRootPane().setBorder(matteBorder);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/connectionsdb/pir20.png")));
    }
    // Sets up the events for the window (Configura los eventos de la ventana)

    private void setupWindowEvents() {
        setupExitButtonEvent();
        setupMaximizeButtonEvent();
        setupMinimizeButtonEvent();
        setupFileActions();
        refreshFilesInTable();
    }
    // Configures the exit button event (Configura el evento del botón de salida)

    private void setupExitButtonEvent() {
        btnExit.addActionListener(e -> System.exit(0));
    }
    // Configures the maximize button event (Configura el evento del botón de maximizar)

    private void setupMaximizeButtonEvent() {
        btnMax.addActionListener(e -> toggleWindowMaximize());
    }
    // Toggles between maximized and normal window state (Cambia entre el estado maximizado y normal de la ventana)

    private void toggleWindowMaximize() {
        if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            setExtendedState(JFrame.NORMAL);
        }
    }
    // Configures the minimize button event (Configura el evento del botón de minimizar)

    private void setupMinimizeButtonEvent() {
        btnMin.addActionListener(e -> setExtendedState(JFrame.ICONIFIED));
    }
    // Configures the file search button action (Configura el evento del botón de búsqueda de archivos)

    private void refreshFilesInTable() {
        btnBuscar.addActionListener(e -> refreshConnectionFilesTable());
    }
    // Sets up the file-related button actions (Establece las acciones para los botones relacionados con los archivos)

    private void setupFileActions() {
        createNewButton.addActionListener(e -> createNewConnectionFile());
        modifyButton.addActionListener(e -> modifySelectedConnectionFile());
        deleteButton.addActionListener(e -> deleteSelectedConnectionFile());
        runButton.addActionListener(e -> launchLyraApp());
    }

    // Configures the window properties and events (Configura las propiedades y eventos de la ventana)
    private void configureWindowProperties() {
        setupWindowEvents();
        setBorders();
        new WindowCustomizer(this, titlePanel);
    }

    // Checks if the connection files directory exists and loads the files (Verifica si el directorio de archivos de conexión existe y carga los archivos)
    private void loadExistingFilesList() {
        DefaultListModel<String> fileListModel = (DefaultListModel<String>) connectionDirectoryList.getModel();
        if (fileListModel.getSize() > 0) {
            connectionDirectoryList.setSelectedIndex(0);
            refreshConnectionFilesTable();
        }
    }

    // Loads the selected connection file and runs the Lyra application (Carga el archivo de conexión seleccionado y ejecuta la aplicación Lyra)
    private void launchLyraApp() {
        int selectedRow = connectionsTable.getSelectedRow();

        if (selectedRow > -1) {
            String databaseUrl = connectionsTable.getValueAt(selectedRow, 1).toString();
            File databasePath = new File(databaseUrl);

            FlatMacDarkLaf.setup();
            new LyraCoreSplash().setVisible(true);

            Login.setDatabaseConfigFile(databasePath);
            dispose(); // Closes the current window (Cierra la ventana actual)
        }
    }

    
    private void checkConnectionFilesDirectory() {

        // Get the connection directory using the getConnectionDirectory method (Obtiene el directorio de conexión usando el método getConnectionDirectory)
        File directory = ConnectionService.getConnectionDirectoy();

        // Log the directory path being checked (Registrar la ruta del directorio que se está verificando)
        System.out.println("Checking connection directory: " + directory.getAbsolutePath());

        if (directory.exists()) {
            DefaultListModel<String> model = new DefaultListModel<>();
            model.addElement(directory.getParent());
            
            connectionDirectoryList.setModel(model);
            System.out.println("Files loaded successfully from directory.");
        } else {
            // Log if the directory doesn't exist (Registrar si el directorio no existe)
            System.out.println("Directory does not exist: " + directory.getAbsolutePath());
        }
    }

    
    private void createDirectoriesIfNeeded() {
        ConnectionService.createDirectoryIfNeeded();
        // Llama a checkFileDir después de crear los directorios
        checkConnectionFilesDirectory();
    }

    // Creates a new connection file (Crea un nuevo archivo de conexión)
    private void createNewConnectionFile() {
        int reply = JOptionPane.showConfirmDialog(this, "Do you want to add a new connection?", "Connections", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            File connectionFilesUrl = ConnectionService.getConnectionDirectoy();
            openNewConnectionDialog(connectionFilesUrl);
        }
    }

    // Método separado para abrir el diálogo
    private void openNewConnectionDialog(File connectionFileUrl) {
        ConnectionEditor newConnectionWindow = new ConnectionEditor(this, true,connectionFileUrl);
        newConnectionWindow.setVisible(true);
    }

    // Refreshes the list of connection files in the table (Actualiza la lista de archivos de conexión en la tabla)
    private void refreshConnectionFilesTable() {
        ClearTable.clear(connectionsTable);
        DefaultTableModel tableModel = (DefaultTableModel) connectionsTable.getModel();

        File directory = ConnectionService.getConnectionDirectoy();

        if (directory.isDirectory()) {
            String[] files = directory.list();
            if (files != null && files.length > 0) {
                for (String file : files) {
                    File filePath = new File(directory, file);
                    tableModel.addRow(new Object[]{filePath.getName(), filePath.getPath()});
                }
                connectionsTable.setModel(tableModel);
                Tabla_Formato.tablaNoEditable(connectionsTable, 20);
            }
        }
    }

    // Initializes the table model (Inicializa el modelo de la tabla)
    private void initializeTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnIdentifiers = {"File", "Description"};
        model.setColumnIdentifiers(columnIdentifiers);
        connectionsTable.setModel(model);
        connectionDirectoryList.setCellRenderer(new ListCellRendererLyra());
        connectionsTable.setDragEnabled(false);
    }

    // Modifies the selected connection file (Modifica el archivo de conexión seleccionado)
    private void modifySelectedConnectionFile() {
        int selectedRow = connectionsTable.getSelectedRow(); // Get the selected row (Obtiene la fila seleccionada)

        if (selectedRow > -1) { // Check if a row is selected (Verifica si una fila está seleccionada)
            File connectionFile = new File(connectionsTable.getValueAt(selectedRow, 1).toString()); // Get file path (Obtiene la ruta del archivo)

            // Read the connection file (Lee el archivo de conexión)
            DatabaseConnectionFileReader readingFile = ConnectionService.readConnectionFile(connectionFile);
            if (readingFile == null) {
                return; // Exit if the file cannot be read (Salir si no se puede leer el archivo)
            }

            // Get the directory where connection files are stored (Obtiene el directorio donde se guardan los archivos de conexión)
            File connectionFilesUrl = ConnectionService.getConnectionDirectoy();

            // Open the connection modification dialog (Abre el diálogo de modificación de conexión)
            openModifyConnectionDialog(connectionFilesUrl, readingFile);
        }
    }

    // Opens the connection modification dialog (Abre el cuadro de diálogo de modificación de conexión)
    private void openModifyConnectionDialog(File connectionFileUrl, DatabaseConnectionFileReader readingFile) {
        // Create a new instance of ConnectionEditor (Crea una nueva instancia de ConnectionEditor)
        ConnectionEditor newConnectionWindow = new ConnectionEditor(this, true, connectionFileUrl);

        // Set the connection details (Establece los detalles de la conexión)
        newConnectionWindow.setConnector(readingFile.getConnector());
        newConnectionWindow.setPort(readingFile.getPort());
        newConnectionWindow.setHostName(readingFile.getHostname());
        newConnectionWindow.setDataBase(readingFile.getDatabase());
        newConnectionWindow.setFileName(readingFile.getFilename());

        // Display the modification window (Muestra la ventana de modificación)
        newConnectionWindow.setVisible(true);
    }

    // Deletes the selected connection file (Elimina el archivo de conexión seleccionado)
    private void deleteSelectedConnectionFile() {
        int selectedRow = connectionsTable.getSelectedRow();

        if (selectedRow > -1) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this file?", "Connections", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (reply == JOptionPane.YES_OPTION) {
                File connectionFile = new File(connectionsTable.getValueAt(selectedRow, 1).toString());
                connectionFile.delete();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanelWindow = new javax.swing.JPanel();
        container = new com.simplecore.erp.gui.components.panels.JPanelRoundedCorners();
        panelMenus = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        toolbar = new javax.swing.JToolBar();
        createNewButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        modifyButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        deleteButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        sep1 = new javax.swing.JToolBar.Separator();
        btnBuscar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        filler = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        sep2 = new javax.swing.JToolBar.Separator();
        runButton = new com.simplecore.erp.gui.components.labels.JButtonCustom();
        split = new javax.swing.JSplitPane();
        scrollFiles = new javax.swing.JScrollPane();
        connectionDirectoryList = new javax.swing.JList<>();
        scrollTable = new javax.swing.JScrollPane();
        connectionsTable = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();
        titlePanel = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        windowTitleLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        btnExit = new com.simplecore.erp.gui.components.labels.JButtonGradient();
        btnMax = new com.simplecore.erp.gui.components.labels.JButtonGradient();
        btnMin = new com.simplecore.erp.gui.components.labels.JButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanelWindow.setBackground(new java.awt.Color(238, 245, 250));

        container.setBackground(new java.awt.Color(247, 251, 255));
        container.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panelMenus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelMenus.setColor1(new java.awt.Color(241, 246, 252));
        panelMenus.setColor2(new java.awt.Color(202, 216, 237));

        toolbar.setRollover(true);
        toolbar.setOpaque(false);

        createNewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/new_document.png"))); // NOI18N
        toolbar.add(createNewButton);

        modifyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/modify_pencil .png"))); // NOI18N
        toolbar.add(modifyButton);

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/delete_trash.png"))); // NOI18N
        toolbar.add(deleteButton);
        toolbar.add(sep1);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/refresh.png"))); // NOI18N
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnBuscar);
        toolbar.add(filler);
        toolbar.add(sep2);

        runButton.setForeground(new java.awt.Color(255, 255, 255));
        runButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/run_play.png"))); // NOI18N
        runButton.setText("Run");
        runButton.setColorOver(new java.awt.Color(0, 153, 51));
        runButton.setFocusable(false);
        runButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        runButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        runButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        runButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(runButton);

        javax.swing.GroupLayout panelMenusLayout = new javax.swing.GroupLayout(panelMenus);
        panelMenus.setLayout(panelMenusLayout);
        panelMenusLayout.setHorizontalGroup(
            panelMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenusLayout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelMenusLayout.setVerticalGroup(
            panelMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        split.setDividerLocation(200);

        connectionDirectoryList.setBorder(null);
        connectionDirectoryList.setFont(new java.awt.Font("Open Sans Light", 0, 14)); // NOI18N
        connectionDirectoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollFiles.setViewportView(connectionDirectoryList);

        split.setLeftComponent(scrollFiles);

        connectionsTable.setBackground(new java.awt.Color(146, 178, 193));
        connectionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        connectionsTable.setColorRow1(new java.awt.Color(255, 255, 255));
        connectionsTable.setColorRow2(new java.awt.Color(255, 255, 255));
        connectionsTable.setColorSelectFont(new java.awt.Color(255, 255, 255));
        connectionsTable.setColorSelection(new java.awt.Color(0, 0, 153));
        connectionsTable.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        scrollTable.setViewportView(connectionsTable);

        split.setRightComponent(scrollTable);

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(split, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addComponent(panelMenus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(split, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );

        titlePanel.setColor1(new java.awt.Color(0, 146, 229));
        titlePanel.setColor2(new java.awt.Color(0, 99, 155));

        windowTitleLabel.setForeground(new java.awt.Color(248, 248, 248));
        windowTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        windowTitleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/access_title_icon.png"))); // NOI18N
        windowTitleLabel.setText("Lyra Access Design ");
        windowTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close_window.png"))); // NOI18N
        btnExit.setShowBorder(false);

        btnMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/maximize.png"))); // NOI18N
        btnMax.setShowBorder(false);

        btnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/minimize.png"))); // NOI18N
        btnMin.setShowBorder(false);

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(windowTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMax, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnMax, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnMin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout mainPanelWindowLayout = new javax.swing.GroupLayout(mainPanelWindow);
        mainPanelWindow.setLayout(mainPanelWindowLayout);
        mainPanelWindowLayout.setHorizontalGroup(
            mainPanelWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelWindowLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addComponent(titlePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelWindowLayout.setVerticalGroup(
            mainPanelWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelWindowLayout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainPanelWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanelWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnBuscar;
    private com.simplecore.erp.gui.components.labels.JButtonGradient btnExit;
    private com.simplecore.erp.gui.components.labels.JButtonGradient btnMax;
    private com.simplecore.erp.gui.components.labels.JButtonGradient btnMin;
    private javax.swing.JList<String> connectionDirectoryList;
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable connectionsTable;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedCorners container;
    private com.simplecore.erp.gui.components.labels.JButtonHQ createNewButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ deleteButton;
    private javax.swing.Box.Filler filler;
    private javax.swing.JPanel mainPanelWindow;
    private com.simplecore.erp.gui.components.labels.JButtonHQ modifyButton;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelMenus;
    private com.simplecore.erp.gui.components.labels.JButtonCustom runButton;
    private javax.swing.JScrollPane scrollFiles;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JToolBar.Separator sep1;
    private javax.swing.JToolBar.Separator sep2;
    private javax.swing.JSplitPane split;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient titlePanel;
    private javax.swing.JToolBar toolbar;
    private com.simplecore.erp.gui.components.labels.JLabelHQ windowTitleLabel;
    // End of variables declaration//GEN-END:variables
}
