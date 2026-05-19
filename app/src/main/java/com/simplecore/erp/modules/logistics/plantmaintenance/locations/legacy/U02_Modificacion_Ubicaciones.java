
package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.utils.notifications.NOT;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class U02_Modificacion_Ubicaciones extends javax.swing.JPanel {
    
    boolean verificacionRealizada;
    
    
    public U02_Modificacion_Ubicaciones() {
        initComponents(); 
        componentesInicio();
        addEvents();
        
    }

    
    private void addEvents(){

        botonSalir();
        botonCrear();
        btnNuevo();
        botonGuardar();
        btnRehacer();       
        btnBuscar();
        MENU_ITEM_SALIR();
        CODIGO_UBICACION();
        DENOMINACION_UBICACION();
    }

    private void componentesInicio() {
        CODIGO_UBICACION.setEditable(true);
        CODIGO_UBICACION.setText(null);
        DENOMINACION_UBICACION.setEditable(true);
        DENOMINACION_UBICACION.setText(null);    
        btnRehacer.setEnabled(true);
        btnGuardar1.setEnabled(true);
        btnGuardar2.setEnabled(true);
        labelNivelUbicacion.setText(null);
        labelCodigoUbicacionSup.setText(null);
        labelDenominacionUbicSup.setText(null);
        
        verificacionRealizada = false;

    }

    private void componentesGuardar() {
        CODIGO_UBICACION.setEditable(false);
        DENOMINACION_UBICACION.setEditable(false);        
        btnRehacer.setEnabled(false);
        btnGuardar1.setEnabled(false);
        btnGuardar2.setEnabled(false);
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            salir();
        });
    }
    
    private void MENU_ITEM_SALIR(){
        MENU_ITEM_SALIR.addActionListener((ActionEvent e) -> {
            salir();
        });
    }

    private void salir() {
        if (treeMenus != null) {
            EventQueue.invokeLater(() -> {
                
                cargarMenuBarPrincipal();
                PanelLoader.loadPanel(treeMenus, mainContainerPanel);
            });
        }
    }

    private void cargarMenuBarPrincipal() {

        JFrame ventanaPrincipal = (JFrame) SwingUtilities.getRoot(this);
        Invoke_JMenuBars.setMenuBar(ventanaPrincipal, ventanaPrincipal.getJMenuBar(),
                 LyraWorkspace.barMenu);

    }
    
    private void botonCrear() {
        btnGuardar1.addActionListener((ActionEvent e) -> {
            if(!CODIGO_UBICACION.getText().isEmpty()){
                crearUbicacion();
            }else{
                JOptionPane.showMessageDialog(null, NOT.msg(NOT.EMPTY_FIELDS),NOT.msg(NOT.TITLE),JOptionPane.INFORMATION_MESSAGE);
                CODIGO_UBICACION.requestFocus();
            }
        });
    }
    
    private void botonGuardar(){
        btnGuardar2.addActionListener((ActionEvent e) -> {
            if(!CODIGO_UBICACION.getText().isEmpty()){
                crearUbicacion();
            }else{
                JOptionPane.showMessageDialog(null, NOT.msg(NOT.EMPTY_FIELDS),NOT.msg(NOT.TITLE),JOptionPane.INFORMATION_MESSAGE);
                CODIGO_UBICACION.requestFocus();
            }
        });
    }

    private void btnNuevo(){
        btnNuevo.addActionListener((ActionEvent e) -> {
            componentesInicio();
            
        });
    }

    private void btnBuscar() {
        btnBucar.addActionListener((ActionEvent e) -> {
            PanelLoader.loadPanel(new U02_Lista_Ubicaciones(), mainContainerPanel);
            
        });
    }

    private void btnRehacer() {
        btnRehacer.addActionListener((ActionEvent e) -> {
            CODIGO_UBICACION.setText(null);
            CODIGO_UBICACION.setEditable(true);
            DENOMINACION_UBICACION.setText(null);
            DENOMINACION_UBICACION.setEditable(true);
            CODIGO_UBICACION.requestFocus();
        });
    }

    private void CODIGO_UBICACION() {

        
        CODIGO_UBICACION.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (CODIGO_UBICACION.isEditable()) {
                    String ubicacion = CODIGO_UBICACION.getText();
                    int longText = CODIGO_UBICACION.getText().length() + 1;

                    switch (longText) {
                        case 4 ->
                            CODIGO_UBICACION.setText(ubicacion + "-");
                        case 8 ->
                            CODIGO_UBICACION.setText(ubicacion + "-");
                        case 11 ->
                            CODIGO_UBICACION.setText(ubicacion + "-");
                        case 15 ->
                            CODIGO_UBICACION.setText(ubicacion + "-");
                        case 19 ->
                            CODIGO_UBICACION.setText(ubicacion + "-");

                        default -> {
                        }
                    }

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                 
                if (CODIGO_UBICACION.isEditable()) {
                     
                String texto = CODIGO_UBICACION.getText().toUpperCase();
                int longtext = CODIGO_UBICACION.getText().length();

                switch (longtext) {
                    case 3 -> CODIGO_UBICACION.setText(texto);
                    case 7 -> CODIGO_UBICACION.setText(texto);
                    case 10 -> CODIGO_UBICACION.setText(texto);
                    case 14 -> CODIGO_UBICACION.setText(texto);
                    case 18 -> CODIGO_UBICACION.setText(texto);
                    case 23 -> CODIGO_UBICACION.setText(texto);
                    default -> {
                        }
                    }

                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        CODIGO_UBICACION.setText(texto);
                    }
                }

            }

        });

        CODIGO_UBICACION.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

                if (CODIGO_UBICACION.isEditable()) {
                    if (!CODIGO_UBICACION.getText().isEmpty()) {
                        int lon = CODIGO_UBICACION.getText().length();

                        if (lon < 3
                                | lon > 3 && lon < 7
                                | lon > 7 && lon < 10
                                | lon > 10 && lon < 14
                                | lon > 14 && lon < 18
                                | lon > 18 && lon < 23) {
                            JOptionPane.showMessageDialog(null, NOT.msg(NOT.DATA_INCONSISTENCY), NOT.msg(NOT.TITLE), JOptionPane.ERROR_MESSAGE);
                            CODIGO_UBICACION.requestFocus();
                        } else {
                            CODIGO_UBICACION.setText(CODIGO_UBICACION.getText().toUpperCase());
                        }
                    }
                }

            }

        });

        CODIGO_UBICACION.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (CODIGO_UBICACION.isEditable()) {
                    verificarNivelUbicacion();
                    verificacionRealizada = false;
                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (CODIGO_UBICACION.isEditable()) {
                    verificarNivelUbicacion();
                    verificacionRealizada = false;
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (CODIGO_UBICACION.isEditable()) {
                    verificarNivelUbicacion();
                    verificacionRealizada = false;
                }

            }

        });

    }

    private void DENOMINACION_UBICACION() {
        DENOMINACION_UBICACION.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (DENOMINACION_UBICACION.isEditable()) {
                    if (!DENOMINACION_UBICACION.getText().isEmpty()) {
                        DENOMINACION_UBICACION.setText(DENOMINACION_UBICACION.getText().toUpperCase());
                    }
                }

            }

        });

        DENOMINACION_UBICACION.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (DENOMINACION_UBICACION.isEditable()) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        DENOMINACION_UBICACION.setText(DENOMINACION_UBICACION.getText().toUpperCase());
                    }
                }

            }

        });
    }

    private void verificarNivelUbicacion() {

        labelCodigoUbicacionSup.setText("");
        labelDenominacionUbicSup.setText("");

        int longitud = CODIGO_UBICACION.getText().length();

        if (!CODIGO_UBICACION.getText().isEmpty()) {

            if (longitud < 3) {
                labelNivelUbicacion.setText(null);
            } else if (longitud == 3) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_1.getNivel());

            } else if (longitud == 7) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_2.getNivel());


            } else if (longitud == 10) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_3.getNivel());

            } else if (longitud == 14) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_4.getNivel());

            } else if (longitud == 18) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_5.getNivel());

            } else if (longitud == 23) {
                labelNivelUbicacion.setText(U01_Ubicaciones_Enum.NIVEL_6.getNivel());

            }
        } else {
            labelNivelUbicacion.setText(null);

        }
    }

    private void verificarUbicacionBD() {

        String codigoUbicacion = CODIGO_UBICACION.getText();

        U01_Verificar_Existencia_Ubicacion nuevaRevision = new U01_Verificar_Existencia_Ubicacion();
        nuevaRevision.setCodigoUbicacion(codigoUbicacion);
        nuevaRevision.verificar();

        boolean existe = nuevaRevision.isExiste();

        if (existe) {
            JOptionPane.showMessageDialog(null, NOT.msg(NOT.RECORD_ALREADY_EXISTS), NOT.msg(NOT.TITLE), JOptionPane.WARNING_MESSAGE);
            CODIGO_UBICACION.requestFocus();
        } else {

            
            if(codigoUbicacion.length()==3){
                U01_Verificar_Existencia_Ubicacion nuevaRevision2 = new U01_Verificar_Existencia_Ubicacion();
                nuevaRevision2.setCodigoUbicacion(CODIGO_UBICACION.getText());
                nuevaRevision2.verificar();
                
                if(nuevaRevision2.isExiste()){
                    JOptionPane.showConfirmDialog(null, NOT.msg(NOT.RECORD_ALREADY_EXISTS),NOT.msg(NOT.TITLE),JOptionPane.ERROR_MESSAGE);
                    CODIGO_UBICACION.requestFocus();
                }else{
                    JOptionPane.showMessageDialog(null, NOT.msg(NOT.LOCATION_LEVEL_1),NOT.msg(NOT.TITLE),JOptionPane.INFORMATION_MESSAGE);
                    verificacionRealizada = true;
                }
                
            }else if (codigoUbicacion.length() > 3) {
                
                U01_Verificar_Existencia_Ubicacion nuevaRevision3 = new U01_Verificar_Existencia_Ubicacion();                
                String codigoUbicacionSup = "";
                
                if (codigoUbicacion.length() == 7) {
                    codigoUbicacionSup = codigoUbicacion.substring(0, 3);
                } else if (codigoUbicacion.length() == 10) {
                    codigoUbicacionSup = codigoUbicacion.substring(0, 7);
                } else if (codigoUbicacion.length() == 14) {
                    codigoUbicacionSup = codigoUbicacion.substring(0, 10);
                } else if (codigoUbicacion.length() == 18) {
                    codigoUbicacionSup = codigoUbicacion.substring(0, 14);
                } else if (codigoUbicacion.length() == 23) {
                    codigoUbicacionSup = codigoUbicacion.substring(0, 18);
                }

                nuevaRevision3.setCodigoUbicacion(codigoUbicacionSup);
                nuevaRevision3.verificar();

                if (nuevaRevision3.isExiste()) {
                    labelCodigoUbicacionSup.setText(nuevaRevision3.getCodigoUbicacion());
                    labelDenominacionUbicSup.setText(nuevaRevision3.getDescripcionUbicacion());
                    verificacionRealizada = true;
                } else {
                    JOptionPane.showMessageDialog(null, NOT.msg(NOT.SUPERIOR_LOCATION_DOES_NOT_EXIST), NOT.msg(NOT.TITLE), JOptionPane.WARNING_MESSAGE);
                    CODIGO_UBICACION.requestFocus();
                    verificacionRealizada = false;
                }
            }
            
            verificacionRealizada = true;            

        }

    }

    private void crearUbicacion() {

        if (verificacionRealizada) {

            boolean montaje = montajePermitido.isSelected();


            
            
            JOptionPane.showMessageDialog(null, NOT.msg(NOT.OPERATION_COMPLETED), NOT.msg(NOT.TITLE), JOptionPane.INFORMATION_MESSAGE);
           
        }else{
            JOptionPane.showMessageDialog(null, NOT.msg(NOT.VERIFY_DATA_FIRST),NOT.msg(NOT.TITLE),JOptionPane.WARNING_MESSAGE);
          
        }

    }
    
    


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_U02 = new javax.swing.JMenuBar();
        MENU_CREACION = new javax.swing.JMenu();
        SUB_MENU_E01_CREAR_EQUIPO = new javax.swing.JMenuItem();
        SUB_MENU_E02_MODIFICAR_EQUIPO = new javax.swing.JMenuItem();
        SUB_MENU_E03_VISUALIZAR_EQUIPO = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_ACCIONES = new javax.swing.JMenu();
        SUB_MENU_GUARDAR = new javax.swing.JMenuItem();
        SUB_MENU_ESTATUS = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        SUB_MENU_INSTRUCCIONES = new javax.swing.JMenuItem();
        SUB_MENU_ACERCA_DE_EQUIPOS = new javax.swing.JMenuItem();
        cintaControles = new javax.swing.JToolBar();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnGuardar1 = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnNuevo = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnBucar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        label4 = new javax.swing.JLabel();
        LABELNIVEL = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        CODIGO_UBICACION = new javax.swing.JTextField();
        montajePermitido = new javax.swing.JCheckBox();
        DENOMINACION_UBICACION = new javax.swing.JTextField();
        LABELCODIGOUBICACION = new javax.swing.JLabel();
        LABELDENOMINACION = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LABELUBICACION = new javax.swing.JLabel();
        labelNivelUbicacion = new javax.swing.JLabel();
        LABELDENOMINACIONSUP = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelCodigoUbicacionSup = new javax.swing.JLabel();
        labelDenominacionUbicSup = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        LABELMODELO = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        btnEstructura = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnRehacer = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnGuardar2 = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        MENU_CREACION.setText("Creacion de equipo");

        SUB_MENU_E01_CREAR_EQUIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_E01_CREAR_EQUIPO.setText("E01 - Nuevo Crear equipo");
        MENU_CREACION.add(SUB_MENU_E01_CREAR_EQUIPO);

        SUB_MENU_E02_MODIFICAR_EQUIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_E02_MODIFICAR_EQUIPO.setText("E02 - Nuevo Modificar equipo");
        MENU_CREACION.add(SUB_MENU_E02_MODIFICAR_EQUIPO);

        SUB_MENU_E03_VISUALIZAR_EQUIPO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_E03_VISUALIZAR_EQUIPO.setText("E03 - Nuevo Visualizar equipo");
        MENU_CREACION.add(SUB_MENU_E03_VISUALIZAR_EQUIPO);
        MENU_CREACION.add(separador);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_CREACION.add(MENU_ITEM_SALIR);

        menuBar_U02.add(MENU_CREACION);

        MENU_ACCIONES.setText("Acciones");

        SUB_MENU_GUARDAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        SUB_MENU_GUARDAR.setText("Crear");
        MENU_ACCIONES.add(SUB_MENU_GUARDAR);

        SUB_MENU_ESTATUS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SUB_MENU_ESTATUS.setText("Estatus");
        MENU_ACCIONES.add(SUB_MENU_ESTATUS);

        menuBar_U02.add(MENU_ACCIONES);

        MENU_AYUDA.setText("Ayuda");

        SUB_MENU_INSTRUCCIONES.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        SUB_MENU_INSTRUCCIONES.setText("Manual de instruccion");
        MENU_AYUDA.add(SUB_MENU_INSTRUCCIONES);

        SUB_MENU_ACERCA_DE_EQUIPOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        SUB_MENU_ACERCA_DE_EQUIPOS.setText("Acerca de Equipos");
        MENU_AYUDA.add(SUB_MENU_ACERCA_DE_EQUIPOS);

        menuBar_U02.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        cintaControles.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cintaControles.setRollover(true);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSalir);

        btnGuardar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/save.png"))); // NOI18N
        btnGuardar1.setText("Guardar");
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cintaControles.add(btnGuardar1);
        cintaControles.add(jSeparator1);

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnNuevo);

        btnBucar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBucar.setText("Buscar");
        btnBucar.setFocusable(false);
        btnBucar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnBucar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnBucar);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label4.setBackground(new java.awt.Color(204, 204, 204));
        label4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setText("4");
        label4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LABELNIVEL.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        LABELNIVEL.setText("Nivel ->");

        label5.setBackground(new java.awt.Color(204, 204, 204));
        label5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label5.setText("5");
        label5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        label1.setBackground(new java.awt.Color(204, 204, 204));
        label1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("1");
        label1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        CODIGO_UBICACION.setEditable(false);
        CODIGO_UBICACION.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CODIGO_UBICACION.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CODIGO_UBICACION.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        montajePermitido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        montajePermitido.setText("Montaje permitido");
        montajePermitido.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        DENOMINACION_UBICACION.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DENOMINACION_UBICACION.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        DENOMINACION_UBICACION.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LABELCODIGOUBICACION.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCODIGOUBICACION.setText("Codigo");
        LABELCODIGOUBICACION.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LABELDENOMINACION.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELDENOMINACION.setText("Denominación");
        LABELDENOMINACION.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LABELUBICACION.setText("Ubicacion");
        LABELUBICACION.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelNivelUbicacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNivelUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNivelUbicacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LABELDENOMINACIONSUP.setText("Codigo Ubic. Superior");
        LABELDENOMINACIONSUP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Denominacion Ubic. Sup.");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelCodigoUbicacionSup.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCodigoUbicacionSup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCodigoUbicacionSup.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        labelCodigoUbicacionSup.setOpaque(true);

        labelDenominacionUbicSup.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDenominacionUbicSup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDenominacionUbicSup.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(LABELDENOMINACIONSUP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LABELUBICACION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDenominacionUbicSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNivelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigoUbicacionSup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELUBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNivelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELDENOMINACIONSUP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigoUbicacionSup, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDenominacionUbicSup, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("XXX-XXX-XX-XXX-XXX-XXXX");

        label2.setBackground(new java.awt.Color(204, 204, 204));
        label2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setText("2");
        label2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        label3.setBackground(new java.awt.Color(204, 204, 204));
        label3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText("3");
        label3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LABELMODELO.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        LABELMODELO.setText("Modelo ->");

        label6.setBackground(new java.awt.Color(204, 204, 204));
        label6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        label6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label6.setText("6");
        label6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnEstructura.setText("Estructura");
        btnEstructura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnRehacer.setText("Rehacer");

        btnGuardar2.setText("Guardar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEstructura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRehacer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LABELMODELO)
                                    .addComponent(LABELNIVEL))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addGap(337, 337, 337))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(LABELCODIGOUBICACION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(LABELDENOMINACION, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(CODIGO_UBICACION, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(DENOMINACION_UBICACION, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(montajePermitido)))
                        .addGap(54, 54, 54))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCODIGOUBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CODIGO_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(montajePermitido))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELDENOMINACION, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DENOMINACION_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELMODELO)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LABELNIVEL)
                    .addComponent(label1)
                    .addComponent(label2)
                    .addComponent(label3)
                    .addComponent(label4)
                    .addComponent(label5)
                    .addComponent(label6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRehacer, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstructura, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        panelScroll.setViewportView(bodyPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cintaControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cintaControles, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JTextField CODIGO_UBICACION;
    protected static javax.swing.JTextField DENOMINACION_UBICACION;
    public static javax.swing.JLabel LABELCODIGOUBICACION;
    public static javax.swing.JLabel LABELDENOMINACION;
    public static javax.swing.JLabel LABELDENOMINACIONSUP;
    public static javax.swing.JLabel LABELMODELO;
    public static javax.swing.JLabel LABELNIVEL;
    public static javax.swing.JLabel LABELUBICACION;
    public static javax.swing.JMenu MENU_ACCIONES;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_CREACION;
    private javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem SUB_MENU_ACERCA_DE_EQUIPOS;
    public static javax.swing.JMenuItem SUB_MENU_E01_CREAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_E02_MODIFICAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_E03_VISUALIZAR_EQUIPO;
    public static javax.swing.JMenuItem SUB_MENU_ESTATUS;
    public static javax.swing.JMenuItem SUB_MENU_GUARDAR;
    public static javax.swing.JMenuItem SUB_MENU_INSTRUCCIONES;
    private javax.swing.JPanel bodyPanel;
    public static javax.swing.JButton btnBucar;
    public static javax.swing.JButton btnEstructura;
    public static javax.swing.JButton btnGuardar1;
    public static javax.swing.JButton btnGuardar2;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnRehacer;
    public static javax.swing.JButton btnSalir;
    private javax.swing.JToolBar cintaControles;
    public static javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar.Separator jSeparator1;
    protected static javax.swing.JLabel label1;
    protected static javax.swing.JLabel label2;
    protected static javax.swing.JLabel label3;
    protected static javax.swing.JLabel label4;
    protected static javax.swing.JLabel label5;
    protected static javax.swing.JLabel label6;
    protected static javax.swing.JLabel labelCodigoUbicacionSup;
    protected static javax.swing.JLabel labelDenominacionUbicSup;
    protected static javax.swing.JLabel labelNivelUbicacion;
    public static javax.swing.JMenuBar menuBar_U02;
    public static javax.swing.JCheckBox montajePermitido;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPopupMenu.Separator separador;
    // End of variables declaration//GEN-END:variables
}
