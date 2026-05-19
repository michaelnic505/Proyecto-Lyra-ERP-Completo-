package com.simplecore.erp.client.gui.connections;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
//import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.client.utils.splash.LyraCoreSplash;
import com.simplecore.erp.client.controllers.gui.WindowCustomizer;
import com.simplecore.erp.client.gui.login.Login;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.models.connections.ServerConnectionFileReader;
import com.simplecore.erp.client.services.connections.ConnectionService;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class ServerConnectionsForm extends javax.swing.JFrame {

    public static LyraCoreSplash splashPanel;

    public ServerConnectionsForm() {
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

        if (serversList.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "Select One Connection to Proceed.", "Select Connection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        File databasePath = onExtractPathButtonClicked();
        FlatMacDarkLaf.setup();
        new LyraCoreSplash().setVisible(true);
        Login.setServerConnectionFile(databasePath);
        dispose(); // Closes the current window (Cierra la ventana actual)
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
        ServerConnectionEditor newConnectionWindow = new ServerConnectionEditor(this, true,connectionFileUrl);
        newConnectionWindow.setVisible(true);
    }

    // Refreshes the list of connection files in the table (Actualiza la lista de archivos de conexión en la tabla)
    private final Map<String, String> filePathMap = new HashMap<>(); // Mapea nombres con rutas
    private void refreshConnectionFilesTable() {
        DefaultListModel<String> model = new DefaultListModel<>();
        filePathMap.clear(); // Limpiar el mapa antes de llenarlo

        File directory = ConnectionService.getConnectionDirectoy();
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    model.addElement(file.getName()); // Solo mostrar el nombre
                    filePathMap.put(file.getName(), file.getAbsolutePath()); // Guardar la ruta
                }
                serversList.setModel(model);
            }else{
                serversList.setModel(model);
            }
        }
    }

    private File onExtractPathButtonClicked() {
        String selectedFileName = serversList.getSelectedValue();
        if (selectedFileName != null) {
            String filePath = filePathMap.get(selectedFileName); // Obtener la ruta del mapa
            if (filePath != null) {
                return new File(filePath); // Retorna el archivo
            }
        }
        return null; // Si no hay selección, retorna null
    }

    // Initializes the table model (Inicializa el modelo de la tabla)
    private void initializeTableModel() {
        connectionDirectoryList.setCellRenderer(new FolderListCellRender(new ImageIcon(getClass().getResource("/icons/connectionsdb/carpeta.png"))));
        serversList.setCellRenderer(new FolderListCellRender(new CustomSVGIcon("/icons/svg/server.svg", new Dimension(20, 20))));
    }

    // Modifies the selected connection file (Modifica el archivo de conexión seleccionado)
    private void modifySelectedConnectionFile() {
        //   File connectionFile = new File(connectionsTable.getValueAt(selectedRow, 1).toString()); // Get file path (Obtiene la ruta del archivo)
        if (serversList.isSelectionEmpty()) {
            return;
        }
        File fileToModify = onExtractPathButtonClicked();

        // Read the connection file (Lee el archivo de conexión)
        ServerConnectionFileReader readingFile = ConnectionService.readConnectionFile(fileToModify);
        if (readingFile == null) {
                return; // Exit if the file cannot be read (Salir si no se puede leer el archivo)
            }

            // Get the directory where connection files are stored (Obtiene el directorio donde se guardan los archivos de conexión)
            File connectionFilesUrl = ConnectionService.getConnectionDirectoy();

            // Open the connection modification dialog (Abre el diálogo de modificación de conexión)
            openModifyConnectionDialog(connectionFilesUrl, readingFile);
        
    }

    // Opens the connection modification dialog (Abre el cuadro de diálogo de modificación de conexión)
    private void openModifyConnectionDialog(File connectionFileUrl, ServerConnectionFileReader readingFile) {
        // Create a new instance of ConnectionEditor (Crea una nueva instancia de ConnectionEditor)
        ServerConnectionEditor newConnectionWindow = new ServerConnectionEditor(this, true, connectionFileUrl);

        // Set the connection details (Establece los detalles de la conexión)
        newConnectionWindow.setPort(String.valueOf(readingFile.getPort()));
        newConnectionWindow.setHostName(readingFile.getHostname());
        newConnectionWindow.setFileName(readingFile.getFilename());

        // Display the modification window (Muestra la ventana de modificación)
        newConnectionWindow.setVisible(true);
    }

    // Deletes the selected connection file (Elimina el archivo de conexión seleccionado)
    private void deleteSelectedConnectionFile() {
        
        if (!serversList.isSelectionEmpty()) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this file?", "Connections", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (reply == JOptionPane.YES_OPTION) {

                File fileToModify = onExtractPathButtonClicked();
                File connectionFile = new File(fileToModify.getAbsolutePath());
                connectionFile.delete();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanelWindow = new javax.swing.JPanel();
        container = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedCorners();
        panelMenus = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        toolbar = new javax.swing.JToolBar();
        createNewButton = new com.simplecore.erp.client.gui.components.labels.JButtonHQ();
        modifyButton = new com.simplecore.erp.client.gui.components.labels.JButtonHQ();
        deleteButton = new com.simplecore.erp.client.gui.components.labels.JButtonHQ();
        sep1 = new javax.swing.JToolBar.Separator();
        btnBuscar = new com.simplecore.erp.client.gui.components.labels.JButtonHQ();
        filler = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        sep2 = new javax.swing.JToolBar.Separator();
        runButton = new corex.suite.JButtonGradient();
        split = new javax.swing.JSplitPane();
        scrollFiles = new javax.swing.JScrollPane();
        connectionDirectoryList = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        serversList = new javax.swing.JList<>();
        titlePanel = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        windowTitleLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        btnExit = new com.simplecore.erp.client.gui.components.labels.JButtonGradient();
        btnMax = new com.simplecore.erp.client.gui.components.labels.JButtonGradient();
        btnMin = new com.simplecore.erp.client.gui.components.labels.JButtonGradient();

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

        runButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/connectionsdb/run_play.png"))); // NOI18N
        runButton.setText("Connect to Server");
        runButton.setBorderColor(new java.awt.Color(170, 186, 211));
        runButton.setColor1(new java.awt.Color(241, 246, 252));
        runButton.setColor2(new java.awt.Color(170, 186, 211));
        runButton.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelMenusLayout = new javax.swing.GroupLayout(panelMenus);
        panelMenus.setLayout(panelMenusLayout);
        panelMenusLayout.setHorizontalGroup(
            panelMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenusLayout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(runButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMenusLayout.setVerticalGroup(
            panelMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenusLayout.createSequentialGroup()
                .addGroup(panelMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        split.setDividerLocation(200);

        connectionDirectoryList.setBorder(null);
        connectionDirectoryList.setFont(new java.awt.Font("Open Sans Light", 0, 14)); // NOI18N
        connectionDirectoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollFiles.setViewportView(connectionDirectoryList);

        split.setLeftComponent(scrollFiles);

        serversList.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        serversList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(serversList);

        split.setRightComponent(jScrollPane1);

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
    private com.simplecore.erp.client.gui.components.labels.JButtonHQ btnBuscar;
    private com.simplecore.erp.client.gui.components.labels.JButtonGradient btnExit;
    private com.simplecore.erp.client.gui.components.labels.JButtonGradient btnMax;
    private com.simplecore.erp.client.gui.components.labels.JButtonGradient btnMin;
    private javax.swing.JList<String> connectionDirectoryList;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedCorners container;
    private com.simplecore.erp.client.gui.components.labels.JButtonHQ createNewButton;
    private com.simplecore.erp.client.gui.components.labels.JButtonHQ deleteButton;
    private javax.swing.Box.Filler filler;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanelWindow;
    private com.simplecore.erp.client.gui.components.labels.JButtonHQ modifyButton;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient panelMenus;
    private corex.suite.JButtonGradient runButton;
    private javax.swing.JScrollPane scrollFiles;
    private javax.swing.JToolBar.Separator sep1;
    private javax.swing.JToolBar.Separator sep2;
    private javax.swing.JList<String> serversList;
    private javax.swing.JSplitPane split;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient titlePanel;
    private javax.swing.JToolBar toolbar;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ windowTitleLabel;
    // End of variables declaration//GEN-END:variables
}
