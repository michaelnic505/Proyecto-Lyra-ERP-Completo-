package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.registration_of_materials_on_warehouse;


import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.panelTransaccion;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class M06_Lista_Almacenes extends javax.swing.JPanel {
    
    String VistaSQL;
    private TableRowSorter<DefaultTableModel> filtro;
    
    public M06_Lista_Almacenes() {
        initComponents(); 
        addEvents();
        cargarTabla();
    }
    

    
    private void addEvents(){
        botonSeleccionar();
        botonSalir();
        filtrarCODIGO_ALMACEN();
        filtrarDESCRIPCION_ALMACEN();
        filtrarTIPO_ALMACEN();
    }

    private void cargarTabla() {

        CODIGO_ALMACEN.setText(null);
        DESCRIPCION_ALMACEN.setText(null);
        TIPO_ALMACEN.setText(null);
        

        M06_Cargar_Lista_Almacenes nuevaLista = new M06_Cargar_Lista_Almacenes();
        nuevaLista.setTABLA_SQL(VistaSQL);
        nuevaLista.setJTABLA(tabla_Almacenes);
        nuevaLista.cargar_Lista_Almacenes();
        Tabla_Formato.tablaNoEditable(tabla_Almacenes, 15);
        ConfigurarFiltroEnTabla();
    }

    private void ConfigurarFiltroEnTabla() {

        tabla_Almacenes.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tabla_Almacenes.getModel());
        tabla_Almacenes.setRowSorter(filtro);
    }

    private void filtrarCODIGO_ALMACEN() {
        CODIGO_ALMACEN.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(CODIGO_ALMACEN.getText(), 0));
            }
            
        });
        
    }

    private void filtrarDESCRIPCION_ALMACEN() {
        DESCRIPCION_ALMACEN.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(DESCRIPCION_ALMACEN.getText(), 1));
            }            
        });        
        
    }

    private void filtrarTIPO_ALMACEN() {
        TIPO_ALMACEN.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(TIPO_ALMACEN.getText(), 2));
            }            
        });
        
    }

    
    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
           seleccionAlmacen();
        });
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            PanelLoader.loadPanel(panelTransaccion, mainContainerPanel);
        });
    }

     
    private void seleccionAlmacen() {

        if (tabla_Almacenes.getSelectedRow() > -1) {
            
            String CODIGO_ALMA = tabla_Almacenes.getValueAt(tabla_Almacenes.getSelectedRow(), 1).toString();
            String DESCRIPCION_ALMA = tabla_Almacenes.getValueAt(tabla_Almacenes.getSelectedRow(), 2).toString();

            M06_Alta_Materiales_en_Almacen.CODIGO_ALMACEN.setText(CODIGO_ALMA);
            M06_Alta_Materiales_en_Almacen.DESCRIPCION_ALMACEN.setText(DESCRIPCION_ALMA);

            
            PanelLoader.loadPanel(panelTransaccion, mainContainerPanel);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        titlePanel = new javax.swing.JPanel();
        LABELTITULO = new javax.swing.JLabel();
        cintaControles = new javax.swing.JToolBar();
        btnSalir = new JButtonHQ();
        btnSeleccionar = new JButtonHQ();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnTodo = new JButtonHQ();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jPanel1 = new javax.swing.JPanel();
        CODIGO_ALMACEN = new javax.swing.JTextField();
        DESCRIPCION_ALMACEN = new javax.swing.JTextField();
        TIPO_ALMACEN = new javax.swing.JTextField();
        LABELCODIGOMATERIAL = new javax.swing.JLabel();
        LABELDESCRIPCIONMATERIAL = new javax.swing.JLabel();
        LABELMARCA = new javax.swing.JLabel();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Almacenes = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        titlePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LABELTITULO.setFont(new java.awt.Font("Barlow Condensed", 3, 18)); // NOI18N
        LABELTITULO.setForeground(new java.awt.Color(0, 153, 153));
        LABELTITULO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LABELTITULO.setText("M06 - Lista de Almacenes");
        LABELTITULO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LABELTITULO, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, titlePanelLayout.createSequentialGroup()
                .addComponent(LABELTITULO)
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
        LABELCODIGOMATERIAL.setText("Cod. Almacen:");

        LABELDESCRIPCIONMATERIAL.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        LABELDESCRIPCIONMATERIAL.setText("Descripción Almacen:");

        LABELMARCA.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        LABELMARCA.setText("Tipo : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LABELCODIGOMATERIAL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CODIGO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LABELDESCRIPCIONMATERIAL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DESCRIPCION_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(LABELMARCA)
                .addGap(3, 3, 3)
                .addComponent(TIPO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(CODIGO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESCRIPCION_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TIPO_ALMACEN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABELCODIGOMATERIAL)
                    .addComponent(LABELDESCRIPCIONMATERIAL)
                    .addComponent(LABELMARCA))
                .addContainerGap())
        );

        cintaControles.add(jPanel1);

        tabla_Almacenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla_Almacenes);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
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
    private javax.swing.JTextField CODIGO_ALMACEN;
    private javax.swing.JTextField DESCRIPCION_ALMACEN;
    public static javax.swing.JLabel LABELCODIGOMATERIAL;
    public static javax.swing.JLabel LABELDESCRIPCIONMATERIAL;
    public static javax.swing.JLabel LABELMARCA;
    public static javax.swing.JLabel LABELTITULO;
    private javax.swing.JTextField TIPO_ALMACEN;
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
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tabla_Almacenes;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
