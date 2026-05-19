package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableDesign;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;
import com.simplecore.erp.controllers.gui.PanelLoader;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class O04_Aprobacion_Ordenes_Trabajo extends javax.swing.JPanel {

    private String username;
    
    public O04_Aprobacion_Ordenes_Trabajo(String username) {
        this.username=username;
        initComponents();
        addEvents();
        cargarDatos();
        filterByKeywords();
    }

    private void addEvents() {
        salirListaOrdenes();
        selectOrder();
    }

    private void cargarDatos() {

        Order_List_Model.set(tablaOrdenesAprobacion);

        Load_List_Orders_to_Approve_SQL list = new Load_List_Orders_to_Approve_SQL();
        list.loadData(tablaOrdenesAprobacion);

        //Ordem
        LyraTableDesign.alignCenter(tablaOrdenesAprobacion, 1);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 1, 100);

        //Código de status
        LyraTableDesign.alignCenter(tablaOrdenesAprobacion, 2);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 2, 100);

        //Descrição do status
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 3);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 3, 150);

        //Parar
        LyraTableDesign.alignCenter(tablaOrdenesAprobacion, 4);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 4, 40);

        //Título do pedido
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 5);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 5, 250);

        //Equipamento
        LyraTableDesign.alignCenter(tablaOrdenesAprobacion, 6);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 6, 80);

        //Nome do equipamento
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 7);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 7, 200);

        //Localização
        LyraTableDesign.alignCenter(tablaOrdenesAprobacion, 8);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 8, 80);

        //Designação de localização
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 9);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 9, 200);

        //Centro de custo
        LyraTableDesign.alignCenter(tablaOrdenesAprobacion, 10);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 10, 80);

        //Nome do centro de custo
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 11);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 11, 200);

        //Custo estimado $
        LyraTableDesign.alignRight(tablaOrdenesAprobacion, 12);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 12, 100);

        //Tempo estimado Horas
        LyraTableDesign.alignCenter(tablaOrdenesAprobacion, 13);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 13, 110);

        //Tipo
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 14);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 14, 100);

        //Clase
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 15);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 15, 100);

        //Prioridade
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 16);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 16, 100);

        //Sistema
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 17);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 17, 100);

        //Componente
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 18);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 18, 100);

        //Sintoma
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 19);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 19, 100);

        //Planejado por
        LyraTableDesign.alignLeft(tablaOrdenesAprobacion, 20);
        LyraTableDesign.setWidthResizable(tablaOrdenesAprobacion, 20, 200);

        setKeywordsFilter();
        addItemtoIndexCombo();
        setIndexToSort();
    }

    private JPanel panelAnterior;

    public void setPanelAnterior(JPanel panelAnterior){
        this.panelAnterior = panelAnterior;
    }
    
    private JTextField order;
    public void setOrderField(JTextField order){
        this.order = order;
    }
    
    
    private TableRowSorter<DefaultTableModel> filtro;
    
    private void setKeywordsFilter(){
        tablaOrdenesAprobacion.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((LyraTableModel) tablaOrdenesAprobacion.getModel());
        tablaOrdenesAprobacion.setRowSorter(filtro);
    }
    
    int index;
    KeyAdapter combo = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            filtro.setRowFilter(RowFilter.regexFilter(keywordsTB.getText(), index));
        }

    };
    KeyAdapter comboAll = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            filtro.setRowFilter(RowFilter.regexFilter(keywordsTB.getText()));
        }

    };
    private void filterByKeywords(){        
        keywordsTB.addKeyListener(comboAll);
    }
    private void addItemtoIndexCombo() {

        indexCombo.addItem("All");
        
        int col = tablaOrdenesAprobacion.getColumnCount();
        for (int i = 1; i < col; i++) {
            indexCombo.addItem(tablaOrdenesAprobacion.getColumnName(i));
        }

    }
    private void setIndexToSort() {
        indexCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
              
                index = indexCombo.getSelectedIndex();
                if (index != 0) {
                    
                    keywordsTB.addKeyListener(comboAll);
                    keywordsTB.addKeyListener(combo);

                } else{
                    keywordsTB.addKeyListener(combo);
                    keywordsTB.addKeyListener(comboAll);
                }

            }

        });
    }
    private void selectOrder() {
        btnAbrirOrden.addActionListener((e) -> {

            int fila = tablaOrdenesAprobacion.getSelectedRow();

            if (fila > -1) {

                order.setText(tablaOrdenesAprobacion.getValueAt(fila, 1).toString());
                PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
            }

        });
    }
    
    
    
    
    private void salirListaOrdenes() {

        btnSalir_CrearOrden.addActionListener((e) -> {
            EventQueue.invokeLater(() -> {
                ((O04_Aprobar_Ordenes)panelAnterior).setTitle();
                PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
            });
        });

    }


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_O04 = new javax.swing.JMenuBar();
        MENU_APROBACION_ORDEN = new javax.swing.JMenu();
        MENU_APROBAR = new javax.swing.JMenu();
        MENU_ITEM_EJECUTAR_APROBACION = new javax.swing.JMenuItem();
        MENU_RECHAZAR = new javax.swing.JMenu();
        MENU_ITEM_EJECUTAR_RECHAZO = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        MENU_SELECCION_TODO_APROBAR = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_ORDEN = new javax.swing.JMenu();
        MENU_ITEM_VER_ORDEN = new javax.swing.JMenuItem();
        MENU_DETALLES = new javax.swing.JMenu();
        MENU_ITEM_RESUMEN_COSTOS = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        MENU_ITEM_INSTRUCCIONES = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir_CrearOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnAbrirOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        keywordsTB = new javax.swing.JTextField();
        keywordsLabel = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        indexCombo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaOrdenesAprobacion = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

        MENU_APROBACION_ORDEN.setText("Aprobacion ordenes");

        MENU_APROBAR.setText("Aprobar");

        MENU_ITEM_EJECUTAR_APROBACION.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        MENU_ITEM_EJECUTAR_APROBACION.setText("Ejecutar Aprobacion");
        MENU_APROBAR.add(MENU_ITEM_EJECUTAR_APROBACION);

        MENU_APROBACION_ORDEN.add(MENU_APROBAR);

        MENU_RECHAZAR.setText("Rechazar");

        MENU_ITEM_EJECUTAR_RECHAZO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        MENU_ITEM_EJECUTAR_RECHAZO.setText("Ejecutar Rechazo");
        MENU_RECHAZAR.add(MENU_ITEM_EJECUTAR_RECHAZO);

        MENU_APROBACION_ORDEN.add(MENU_RECHAZAR);
        MENU_APROBACION_ORDEN.add(jSeparator3);

        MENU_SELECCION_TODO_APROBAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_SELECCION_TODO_APROBAR.setText("Seleccionar todo para Aprobar");
        MENU_APROBACION_ORDEN.add(MENU_SELECCION_TODO_APROBAR);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Seleccionar todo para Rechazar");
        MENU_APROBACION_ORDEN.add(jMenuItem3);
        MENU_APROBACION_ORDEN.add(jSeparator4);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_APROBACION_ORDEN.add(MENU_ITEM_SALIR);

        menuBar_O04.add(MENU_APROBACION_ORDEN);

        MENU_ORDEN.setText("Orden");

        MENU_ITEM_VER_ORDEN.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_VER_ORDEN.setText("Ver Orden de Trabajo");
        MENU_ORDEN.add(MENU_ITEM_VER_ORDEN);

        menuBar_O04.add(MENU_ORDEN);

        MENU_DETALLES.setText("Detalles");

        MENU_ITEM_RESUMEN_COSTOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MENU_ITEM_RESUMEN_COSTOS.setText("Resumen de costos");
        MENU_DETALLES.add(MENU_ITEM_RESUMEN_COSTOS);

        menuBar_O04.add(MENU_DETALLES);

        MENU_AYUDA.setText("Ayuda");

        MENU_ITEM_INSTRUCCIONES.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MENU_ITEM_INSTRUCCIONES.setText("Manual Instrucciones");
        MENU_AYUDA.add(MENU_ITEM_INSTRUCCIONES);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem6.setText("Acerca de Aprobaciones");
        MENU_AYUDA.add(jMenuItem6);

        menuBar_O04.add(MENU_AYUDA);

        setBackground(new java.awt.Color(246, 250, 253));
        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir_CrearOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir_CrearOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir_CrearOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        btnAbrirOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnAbrirOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAbrirOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N

        keywordsTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        keywordsLabel.setText("Entry keywords >>");
        keywordsLabel.setFont(new java.awt.Font("Roboto Light", 1, 13)); // NOI18N

        indexCombo.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnAbrirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(keywordsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(keywordsTB, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indexCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 301, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(keywordsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(keywordsTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indexCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbrirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        tablaOrdenesAprobacion.setAutoCreateRowSorter(true);
        tablaOrdenesAprobacion.setBackground(new java.awt.Color(242, 242, 242));
        tablaOrdenesAprobacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaOrdenesAprobacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaOrdenesAprobacion.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jScrollPane2.setViewportView(tablaOrdenesAprobacion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenu MENU_APROBACION_ORDEN;
    public static javax.swing.JMenu MENU_APROBAR;
    public static javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_DETALLES;
    public static javax.swing.JMenuItem MENU_ITEM_EJECUTAR_APROBACION;
    public static javax.swing.JMenuItem MENU_ITEM_EJECUTAR_RECHAZO;
    public static javax.swing.JMenuItem MENU_ITEM_INSTRUCCIONES;
    public static javax.swing.JMenuItem MENU_ITEM_RESUMEN_COSTOS;
    public static javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem MENU_ITEM_VER_ORDEN;
    public static javax.swing.JMenu MENU_ORDEN;
    public static javax.swing.JMenu MENU_RECHAZAR;
    public static javax.swing.JMenuItem MENU_SELECCION_TODO_APROBAR;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnAbrirOrden;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir_CrearOrden;
    private javax.swing.JComboBox<String> indexCombo;
    public static javax.swing.JMenuItem jMenuItem3;
    public static javax.swing.JMenuItem jMenuItem6;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined keywordsLabel;
    private javax.swing.JTextField keywordsTB;
    public static javax.swing.JMenuBar menuBar_O04;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaOrdenesAprobacion;
    // End of variables declaration//GEN-END:variables
}
