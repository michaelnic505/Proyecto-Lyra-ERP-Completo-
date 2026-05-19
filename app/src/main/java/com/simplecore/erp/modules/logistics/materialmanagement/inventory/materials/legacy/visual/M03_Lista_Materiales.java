package com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.visual;

import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.modules.logistics.materialmanagement.inventory.materials.legacy.modification.M02_Cargar_Lista_Materiales;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.TreeMenu.transactionPanel;

public class M03_Lista_Materiales extends javax.swing.JPanel {
    
    String VistaSQL;
    private TableRowSorter<DefaultTableModel> filtro;
    
    public M03_Lista_Materiales() {
        initComponents(); 
        addEvents();
        cargarTabla();
    }
    

    
    private void addEvents(){
        botonSeleccionar();
        botonSalir();
        filtrarCODIGO_MATERIAL();
        filtrarDESCRIPCION_MATERIAL();
        filtrarMARCA_MATERIAL();
    }

    
    private void cargarTabla() {

        CODIGO_MATERIAL.setText(null);
        DESCRIPCION_MATERIAL.setText(null);
        MARCA_MATERIAL.setText(null);

        M02_Cargar_Lista_Materiales nuevaLista = new M02_Cargar_Lista_Materiales();
        nuevaLista.setTABLA_SQL(VistaSQL);
        nuevaLista.setJTABLA(tabla_Materiales);
        nuevaLista.cargar_Lista_Material();
        Tabla_Formato.tablaNoEditable(tabla_Materiales, 10);
        ConfigurarFiltroEnTabla();

    }

    private void ConfigurarFiltroEnTabla() {

        tabla_Materiales.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tabla_Materiales.getModel());
        tabla_Materiales.setRowSorter(filtro);
    }

    private void filtrarCODIGO_MATERIAL() {
        CODIGO_MATERIAL.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(CODIGO_MATERIAL.getText(), 0));
            }
            
        });
        
    }

    private void filtrarDESCRIPCION_MATERIAL() {
        DESCRIPCION_MATERIAL.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(DESCRIPCION_MATERIAL.getText(), 1));
            }            
        });        
        
    }

    private void filtrarMARCA_MATERIAL() {
        MARCA_MATERIAL.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(MARCA_MATERIAL.getText(), 14));
            }            
        });
        
    }
 
    private void seleccionMaterial() {

        if (tabla_Materiales.getSelectedRow() > -1) {

            String CODIGO_MAT = String.valueOf(tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 0));
            String DESCRIPCION_MAT = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 1).toString();
            String ID_TIPO_MATERIAL = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 2).toString();
            String DESCRIPCION_TIPO_MATERIAL = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 3).toString();
            String ID_CLASE_MATERIAL = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 4).toString();
            String DESCRIPCION_CLASE_MATERIAL = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 5).toString();
            String MARCA = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 6).toString();
            String MODELO = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 7).toString();
            String SERIE = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 8).toString();
            String ID_ESTATUS_MATERIAL = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 9).toString();
            String DESCRIPCION_ESTATUS = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 10).toString();
            String ID_AREA_MATERIAL = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 11).toString();
            String DESCRIPCION_AREA_MATERIAL = tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 12).toString();
            String FECHA_FABRICACION = String.valueOf(tabla_Materiales.getValueAt(tabla_Materiales.getSelectedRow(), 13));

            M03_Visualizacion_Materiales.CODIGO_MATERIAL.setText(CODIGO_MAT);
            M03_Visualizacion_Materiales.DESCRIPCION_MATERIAL.setText(DESCRIPCION_MAT);
            M03_Visualizacion_Materiales.ID_TIPO_MATERIAL.setText(ID_TIPO_MATERIAL);
            M03_Visualizacion_Materiales.DESCRIPCION_TIPO_MATERIAL.setText(DESCRIPCION_TIPO_MATERIAL);
            M03_Visualizacion_Materiales.ID_CLASE_MATERIAL.setText(ID_CLASE_MATERIAL);
            M03_Visualizacion_Materiales.DESCRIPCION_CLASE_MATERIAL.setText(DESCRIPCION_CLASE_MATERIAL);
            M03_Visualizacion_Materiales.MARCA.setText(MARCA);
            M03_Visualizacion_Materiales.MODELO.setText(MODELO);
            M03_Visualizacion_Materiales.SERIE.setText(SERIE);
            M03_Visualizacion_Materiales.ID_ESTATUS_MATERIAL.setText(ID_ESTATUS_MATERIAL);
            M03_Visualizacion_Materiales.DESCRIPCION_ESTATUS.setText(DESCRIPCION_ESTATUS);
            M03_Visualizacion_Materiales.ID_AREA_MATERIAL.setText(ID_AREA_MATERIAL);
            M03_Visualizacion_Materiales.DESCRIPCION_AREA_MATERIAL.setText(DESCRIPCION_AREA_MATERIAL);
            M03_Visualizacion_Materiales.FECHA_FABRICACION.setText(FECHA_FABRICACION);

            PanelLoader.loadPanel(transactionPanel, mainContainerPanel);
        }

    }
    
    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
           seleccionMaterial();
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
        cintaControles = new javax.swing.JToolBar();
        btnSalir = new JButtonHQ();
        btnSeleccionar = new JButtonHQ();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnTodo = new JButtonHQ();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jPanel1 = new javax.swing.JPanel();
        CODIGO_MATERIAL = new javax.swing.JTextField();
        DESCRIPCION_MATERIAL = new javax.swing.JTextField();
        MARCA_MATERIAL = new javax.swing.JTextField();
        LABELCODIGOMATERIAL = new javax.swing.JLabel();
        LABELDESCRIPCIONMATERIAL = new javax.swing.JLabel();
        LABELMARCA = new javax.swing.JLabel();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Materiales = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        titlePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LABELTITULOMODULO.setFont(new java.awt.Font("Barlow Condensed", 3, 18)); // NOI18N
        LABELTITULOMODULO.setForeground(new java.awt.Color(0, 153, 153));
        LABELTITULOMODULO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LABELTITULOMODULO.setText("M03 - Lista de Materiales");
        LABELTITULOMODULO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LABELTITULOMODULO, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, titlePanelLayout.createSequentialGroup()
                .addComponent(LABELTITULOMODULO)
                .addGap(9, 9, 9))
        );

        cintaControles.setBackground(new java.awt.Color(121, 163, 215));
        cintaControles.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cintaControles.setRollover(true);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cintaControles.add(btnSalir);

        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        LABELCODIGOMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        LABELCODIGOMATERIAL.setText("Cod. Material:");

        LABELDESCRIPCIONMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        LABELDESCRIPCIONMATERIAL.setText("Descripción Material : ");

        LABELMARCA.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        LABELMARCA.setText("Marca : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LABELCODIGOMATERIAL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CODIGO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LABELDESCRIPCIONMATERIAL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DESCRIPCION_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LABELMARCA)
                .addGap(3, 3, 3)
                .addComponent(MARCA_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CODIGO_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MARCA_MATERIAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELCODIGOMATERIAL)
                    .addComponent(LABELDESCRIPCIONMATERIAL)
                    .addComponent(LABELMARCA))
                .addContainerGap())
        );

        cintaControles.add(jPanel1);

        tabla_Materiales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla_Materiales);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cintaControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JTextField CODIGO_MATERIAL;
    private javax.swing.JTextField DESCRIPCION_MATERIAL;
    public static javax.swing.JLabel LABELCODIGOMATERIAL;
    public static javax.swing.JLabel LABELDESCRIPCIONMATERIAL;
    public static javax.swing.JLabel LABELMARCA;
    public static javax.swing.JLabel LABELTITULOMODULO;
    private javax.swing.JTextField MARCA_MATERIAL;
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
    private javax.swing.JScrollPane panelScroll;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tabla_Materiales;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
