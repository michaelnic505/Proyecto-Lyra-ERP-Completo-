package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.Modelo_Lista_Ubicaciones;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.TreeMenu.transactionPanel;

public class U02_Lista_Ubicaciones extends javax.swing.JPanel {
    
    private TableRowSorter<DefaultTableModel> filtro;
        
    public U02_Lista_Ubicaciones() {
        initComponents(); 
        addEvents();
        setModeloTabla();
        cargarTabla();
        cargarCampos();
    }

    
    private void addEvents(){
        
        botonSeleccionar();
        botonSalir();
        filtroCodigoUbicacion();
        filtroDescripcionUbicacion();
    
    }
    
    private void setModeloTabla(){
        Modelo_Lista_Ubicaciones.set(tabla_Ubicaciones);
    }
    

    
    private void cargarTabla(){        
        COD_UBICACION.setText(null);
        DESCRIPCION_UBICACION.setText(null);
        
        U02_Cargar_Lista_Ubicaciones nuevaCarga = new U02_Cargar_Lista_Ubicaciones();
        nuevaCarga.cargarListaUbicaciones(tabla_Ubicaciones);        
        Tabla_Formato.tablaNoEditable(tabla_Ubicaciones,10);
        ConfigurarFiltroEnTabla();

    }
    
    private void cargarCampos(){
        U02_Agregar_CheckBox.addCheckBox(6, tabla_Ubicaciones);
        U02_Agregar_CheckBox.addCheckBox(7, tabla_Ubicaciones);
        cargarValoresCampos();
        Tabla_Formato.resizeTable(tabla_Ubicaciones, 10);
    }
    
    private void cargarValoresCampos(){
        if (tabla_Ubicaciones.getRowCount() > 0) {
             for (int i = 0; i < tabla_Ubicaciones.getRowCount(); i++) {
                 U02_Cargar_Campos_Ubicaciones carga = new U02_Cargar_Campos_Ubicaciones ();
                 carga.setIdUbicacion(tabla_Ubicaciones.getValueAt(i, 1).toString());
                 carga.cargar();
                 
                 tabla_Ubicaciones.setValueAt(carga.getNivel(), i, 5);
                 tabla_Ubicaciones.setValueAt(carga.isMontaje(), i, 6);
                 tabla_Ubicaciones.setValueAt(carga.isEstatus(), i, 7);
             }
        }
    }

    private void ConfigurarFiltroEnTabla() {

        tabla_Ubicaciones.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tabla_Ubicaciones.getModel());
        tabla_Ubicaciones.setRowSorter(filtro);
    }

    private void filtroCodigoUbicacion() {
        
        COD_UBICACION.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(COD_UBICACION.getText(), 1));
            }
            
        });
        
    }

    private void filtroDescripcionUbicacion() {
        
        DESCRIPCION_UBICACION.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(DESCRIPCION_UBICACION.getText(), 2));
            }            
        });        
        
    }

    private void seleccionarUbicacion() {

        if (tabla_Ubicaciones.getSelectedRow() > -1) {
            
            String codigoUbicacion = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 1).toString();
            String descripcionUbicacion = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 2).toString();
            
            String codigoUbicacionSup = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 3).toString();
            String descripcionUbicacionSup = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 4).toString();
            String nivel = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 5).toString();
            
            boolean montaje = (boolean) tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 6);

            U02_Modificacion_Ubicaciones.CODIGO_UBICACION.setText(codigoUbicacion);
            U02_Modificacion_Ubicaciones.DENOMINACION_UBICACION.setText(descripcionUbicacion);
            
            U02_Modificacion_Ubicaciones.montajePermitido.setSelected(montaje);
            
            U02_Modificacion_Ubicaciones.labelNivelUbicacion.setText(nivel);
            U02_Modificacion_Ubicaciones.labelCodigoUbicacionSup.setText(codigoUbicacionSup);
            U02_Modificacion_Ubicaciones.labelDenominacionUbicSup.setText(descripcionUbicacionSup);

            PanelLoader.loadPanel(transactionPanel, mainContainerPanel);
        }
         
    }
    
    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
            seleccionarUbicacion();
        });
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            PanelLoader.loadPanel(transactionPanel, mainContainerPanel);
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        titlePanel = new javax.swing.JPanel();
        LABELTITULOMODULO = new javax.swing.JLabel();
        logoSuperior = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        cintaControles = new javax.swing.JToolBar();
        btnSalir = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnTodo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jPanel1 = new javax.swing.JPanel();
        LABELCODIGO = new javax.swing.JLabel();
        COD_UBICACION = new javax.swing.JTextField();
        LABELDESCRIPCIONUBICACION = new javax.swing.JLabel();
        DESCRIPCION_UBICACION = new javax.swing.JTextField();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Ubicaciones = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        titlePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LABELTITULOMODULO.setFont(new java.awt.Font("Barlow Condensed", 3, 18)); // NOI18N
        LABELTITULOMODULO.setForeground(new java.awt.Color(0, 153, 153));
        LABELTITULOMODULO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LABELTITULOMODULO.setText("U02 - Lista de Ubicaciones");
        LABELTITULOMODULO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        logoSuperior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoSuperior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/puzzle.png"))); // NOI18N

        lbUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbUser.setToolTipText("");
        lbUser.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(logoSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LABELTITULOMODULO, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(256, 256, 256)
                .addComponent(lbUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(87, 87, 87))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, titlePanelLayout.createSequentialGroup()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LABELTITULOMODULO, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(logoSuperior, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );

        cintaControles.setBackground(new java.awt.Color(121, 163, 215));
        cintaControles.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cintaControles.setRollover(true);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSalir);

        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSeleccionar);
        cintaControles.add(jSeparator3);

        btnTodo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTodo.setText("Todo");
        btnTodo.setFocusable(false);
        btnTodo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnTodo);
        cintaControles.add(jSeparator1);

        jPanel1.setBackground(new java.awt.Color(121, 163, 215));

        LABELCODIGO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELCODIGO.setText("Codigo");

        LABELDESCRIPCIONUBICACION.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LABELDESCRIPCIONUBICACION.setText("Descripción Ubiación");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LABELCODIGO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(COD_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LABELDESCRIPCIONUBICACION)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DESCRIPCION_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(297, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(LABELCODIGO)
                    .addComponent(COD_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DESCRIPCION_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LABELDESCRIPCIONUBICACION)))
                .addContainerGap())
        );

        cintaControles.add(jPanel1);

        tabla_Ubicaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla_Ubicaciones);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        panelScroll.setViewportView(bodyPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cintaControles, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(panelScroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cintaControles, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField COD_UBICACION;
    private javax.swing.JTextField DESCRIPCION_UBICACION;
    public static javax.swing.JLabel LABELCODIGO;
    public static javax.swing.JLabel LABELDESCRIPCIONUBICACION;
    public static javax.swing.JLabel LABELTITULOMODULO;
    private javax.swing.JPanel bodyPanel;
    public static javax.swing.JButton btnSalir;
    public static javax.swing.JButton btnSeleccionar;
    public static javax.swing.JButton btnTodo;
    private javax.swing.JToolBar cintaControles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    protected static javax.swing.JLabel lbUser;
    private javax.swing.JLabel logoSuperior;
    private javax.swing.JScrollPane panelScroll;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tabla_Ubicaciones;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
