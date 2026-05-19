package com.simplecore.erp.modules.logistics.materialmanagement.inventory.warehouses.legacy.lists;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;

public class M08_Lista_Materiales extends javax.swing.JPanel {
    
    String VistaSQL;
    private TableRowSorter<DefaultTableModel> filtro;
    
    public M08_Lista_Materiales() {
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
        
        MENU_ITEM_SALIR();
    }

    private void cargarTabla() {

        CODIGO_MATERIAL.setText(null);
        DESCRIPCION_MATERIAL.setText(null);
        MARCA_MATERIAL.setText(null);

        M08_Cargar_Lista_Alta_Materiales nuevaLista = new M08_Cargar_Lista_Alta_Materiales();
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

    
    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
        
        });
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            salir();
        });
    }

    private void MENU_ITEM_SALIR() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_M08 = new javax.swing.JMenuBar();
        MENU_LISTA_MATERIALES = new javax.swing.JMenu();
        MENU_ITEM_VER_MATERIAL = new javax.swing.JMenuItem();
        MENU_ITEM_NUEVA_ALTA = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_DATOS = new javax.swing.JMenu();
        MENU_EXPORTAR = new javax.swing.JMenu();
        MENU_ITEM_HOJA_CALCULO = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
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

        MENU_LISTA_MATERIALES.setText("Lista de Materiales");

        MENU_ITEM_VER_MATERIAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_VER_MATERIAL.setText("Ver Material");
        MENU_LISTA_MATERIALES.add(MENU_ITEM_VER_MATERIAL);

        MENU_ITEM_NUEVA_ALTA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_NUEVA_ALTA.setText("Nueva Alta de Material");
        MENU_LISTA_MATERIALES.add(MENU_ITEM_NUEVA_ALTA);
        MENU_LISTA_MATERIALES.add(separador);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_LISTA_MATERIALES.add(MENU_ITEM_SALIR);

        menuBar_M08.add(MENU_LISTA_MATERIALES);

        MENU_DATOS.setText("Datos");

        MENU_EXPORTAR.setText("Exportar");

        MENU_ITEM_HOJA_CALCULO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_HOJA_CALCULO.setText("Hoja de Calculo");
        MENU_EXPORTAR.add(MENU_ITEM_HOJA_CALCULO);

        MENU_DATOS.add(MENU_EXPORTAR);

        menuBar_M08.add(MENU_DATOS);

        MENU_AYUDA.setText("Ayuda");
        menuBar_M08.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cintaControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelScroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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
    private javax.swing.JTextField MARCA_MATERIAL;
    private javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_DATOS;
    public static javax.swing.JMenu MENU_EXPORTAR;
    public static javax.swing.JMenuItem MENU_ITEM_HOJA_CALCULO;
    public static javax.swing.JMenuItem MENU_ITEM_NUEVA_ALTA;
    public static javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem MENU_ITEM_VER_MATERIAL;
    public static javax.swing.JMenu MENU_LISTA_MATERIALES;
    private javax.swing.JPanel bodyPanel;
    public static javax.swing.JButton btnSalir;
    public static javax.swing.JButton btnSeleccionar;
    public static javax.swing.JButton btnTodo;
    private javax.swing.JToolBar cintaControles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator3;
    public static javax.swing.JMenuBar menuBar_M08;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPopupMenu.Separator separador;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tabla_Materiales;
    // End of variables declaration//GEN-END:variables
}
