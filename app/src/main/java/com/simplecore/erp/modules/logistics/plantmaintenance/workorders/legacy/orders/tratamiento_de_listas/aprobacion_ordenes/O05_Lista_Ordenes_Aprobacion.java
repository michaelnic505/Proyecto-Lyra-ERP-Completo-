package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas.aprobacion_ordenes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableDesign;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel2;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.legacy.FullUserName;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.config.database.utils.ClearTable;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.Check_Modification_Permission;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.StatusOrder;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders.Approve_Order_SQL;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders.O04_Detalle_Orden;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas.CheckBoxTableHeaderRender;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class O05_Lista_Ordenes_Aprobacion extends javax.swing.JPanel {

    private final JPanel thisPanel = this;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy.MM.dd");
    private SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
    private String username;
    private String fullname;

    public O05_Lista_Ordenes_Aprobacion(String username) {
        this.username=username;
//        this.fullname=FullUserName.getName(username);
        initComponents();
        setModel();
        addEvents();
    }

    private void removeJMenuBar() {
        getSuperFrame().remove(getSuperFrame().getJMenuBar());
    }
    public void setTitle(){
        LyraWorkspace.TitleLabel.setText("Work Orders List");
    }
    private void addEvents() {
        removeJMenuBar();

        aprobarOrden();
        rechazarOrden();
        verOrden();

        btnSalir();
    }

    private void setModel() {
        Order_List_Model_List.set(tablaListaOrdenes);

        tablaListaOrdenes.getColumnModel().getColumn(1).setHeaderRenderer(new CheckBoxTableHeaderRender(tablaListaOrdenes, 1));

        //check
        LyraTableDesign.alignCenter(tablaListaOrdenes, 1);
        LyraTableDesign.setWidthNoResizable(tablaListaOrdenes, 1, 40);

        //Ordem
        LyraTableDesign.alignCenter(tablaListaOrdenes, 2);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 2, 100);

        //Código de status
        LyraTableDesign.alignCenter(tablaListaOrdenes, 3);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 3, 100);

        //Descrição do status
        LyraTableDesign.alignLeft(tablaListaOrdenes, 4);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 4, 150);

        //Título do pedido
        LyraTableDesign.alignLeft(tablaListaOrdenes, 5);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 5, 300);

        //fecha inicio
        LyraTableDesign.alignCenter(tablaListaOrdenes, 6);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 6, 80);

        //fecha ifn
        LyraTableDesign.alignCenter(tablaListaOrdenes, 7);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 7, 80);

        //Equipamento
        LyraTableDesign.alignCenter(tablaListaOrdenes, 8);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 8, 80);

        //Nome do equipamento
        LyraTableDesign.alignLeft(tablaListaOrdenes, 9);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 9, 200);

        //Localização
        LyraTableDesign.alignCenter(tablaListaOrdenes, 10);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 10, 140);

        //Designação de localização
        LyraTableDesign.alignLeft(tablaListaOrdenes, 11);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 11, 200);

        //Centro de custo
        LyraTableDesign.alignCenter(tablaListaOrdenes, 12);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 12, 80);

        //Nome do centro de custo
        LyraTableDesign.alignLeft(tablaListaOrdenes, 13);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 13, 200);

        //Custo estimado $
        LyraTableDesign.alignRight(tablaListaOrdenes, 14);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 14, 100);

        //Tempo estimado Horas
        LyraTableDesign.alignCenter(tablaListaOrdenes, 15);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 15, 110);

        //Tipo
        LyraTableDesign.alignLeft(tablaListaOrdenes, 16);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 16, 100);

        //Clase
        LyraTableDesign.alignLeft(tablaListaOrdenes, 17);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 17, 100);

        //Prioridade
        LyraTableDesign.alignLeft(tablaListaOrdenes, 18);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 18, 100);

        //Sistema
        LyraTableDesign.alignLeft(tablaListaOrdenes, 19);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 19, 100);

        //Componente
        LyraTableDesign.alignLeft(tablaListaOrdenes, 20);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 20, 100);

        //Sintoma
        LyraTableDesign.alignLeft(tablaListaOrdenes, 21);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 21, 100);

        //Planejado por
        LyraTableDesign.alignLeft(tablaListaOrdenes, 22);
        LyraTableDesign.setWidthResizable(tablaListaOrdenes, 22, 200);

    }

    public void setCheckBoxs() {

        DefaultTableCellRenderer re = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row % 2 == 0) {
                    setBackground(((LyraTable) table).getColorRow1());
                } else {
                    setBackground(((LyraTable) table).getColorRow2());
                }

                return comp;
            }

        };

        tablaListaOrdenes.getColumnModel().getColumn(1).setCellRenderer(tablaListaOrdenes.getDefaultRenderer(Boolean.class));
        tablaListaOrdenes.getColumnModel().getColumn(1).setCellEditor(tablaListaOrdenes.getDefaultEditor(Boolean.class));

    }

    private void aprobarOrden() {

        btnAprobar.addActionListener(aprobar);
    }
    ActionListener aprobar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int filas = tablaListaOrdenes.getRowCount();
            int apro = 0;
            if (filas > 0) {
                for (int i = 0; i < filas; i++) {

                    if ((boolean) tablaListaOrdenes.getValueAt(i, 1)) {
                        apro = apro + 1;
                    }
                }

                if (apro > 0) {

                    if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST4_ORDER_APPROVED.getStatusCode())) {

                        Object[] options = {"Proceed to approve (" + apro + ") orders", "Cancel"};
                        int r = JOptionPane.showOptionDialog(null, NOT.msg(NOT.PROCEED_WITH_APPROVAL),
                                NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                        if (r == JOptionPane.YES_OPTION) {

                            for (int i = 0; i < filas; i++) {

                                if ((boolean) tablaListaOrdenes.getValueAt(i, 1)) {

                                    Approve_Order_SQL approve = new Approve_Order_SQL();
                                    approve.setCodigoEstatus(StatusOrder.ST4_ORDER_APPROVED.getStatusCode());
                                    approve.setDescripcionEstatus(StatusOrder.ST4_ORDER_APPROVED.getDescription());
                                    approve.setFechaAprobacion(formatoFecha.format(Calendar.getInstance().getTime()));
                                    approve.setHoraAprobacion(formatoHora.format(Calendar.getInstance().getTimeInMillis()));
                                    approve.setAprobadaPor(fullname);
                                    approve.processOrder(tablaListaOrdenes.getValueAt(i, 2).toString());
                                }

                            }

                            recargarTabla();

                        } else {
                            notifications(NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.WARNING);
                        }

                    } else {
                        notifications(NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);
                    }

                } else {
                    notifications(NOT.msg(NOT.SELECT_ONE_OR_MORE), TypeMessage.INFORMATION);
                }
            }
        }

    };

    private void rechazarOrden() {
        btnRechazar.addActionListener(rechazar);
    }
    ActionListener rechazar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int filas = tablaListaOrdenes.getRowCount();
            int apro = 0;
            if (filas > 0) {
                for (int i = 0; i < filas; i++) {

                    if ((boolean) tablaListaOrdenes.getValueAt(i, 1)) {
                        apro = apro + 1;
                    }
                }

                if (apro > 0) {

                    if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST9_ORDER_REJECTED.getStatusCode())) {

                        Object[] options = {"Proceed to decline (" + apro + ") orders", "Cancel"};
                        int r = JOptionPane.showOptionDialog(null, NOT.msg(NOT.PROCEED_WITH_REJECTION),
                                NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                        if (r == JOptionPane.YES_OPTION) {
                            for (int i = 0; i < filas; i++) {

                                if ((boolean) tablaListaOrdenes.getValueAt(i, 1)) {

                                    Approve_Order_SQL reject = new Approve_Order_SQL();
                                    reject.setCodigoEstatus(StatusOrder.ST9_ORDER_REJECTED.getStatusCode());
                                    reject.setDescripcionEstatus(StatusOrder.ST9_ORDER_REJECTED.getDescription());
                                    reject.setFechaAprobacion(formatoFecha.format(Calendar.getInstance().getTime()));
                                    reject.setHoraAprobacion(formatoHora.format(Calendar.getInstance().getTimeInMillis()));
                                    reject.setAprobadaPor(fullname);
                                    reject.processOrder(tablaListaOrdenes.getValueAt(i, 2).toString());
                                }

                            }

                            recargarTabla();

                        } else {
                            notifications(NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.WARNING);
                        }
                    } else {
                        notifications(NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.ERROR);
                    }

                } else {
                    notifications(NOT.msg(NOT.SELECT_ONE_OR_MORE), TypeMessage.INFORMATION);
                }
            }
        }

    };

    private void verOrden() {
        btnAbrirOrden.addActionListener(abrirOrdenes);
    }
    ActionListener abrirOrdenes = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            List<String> ordenes = new ArrayList();
            int filas = tablaListaOrdenes.getRowCount();

            if (filas > 0) {

                for (int i = 0; i < filas; i++) {

                    if ((boolean) tablaListaOrdenes.getValueAt(i, 1)) {
                        ordenes.add(tablaListaOrdenes.getValueAt(i, 2).toString());
                    }

                }

            }
            if (!ordenes.isEmpty()) {

                O04_Detalle_Orden deo = new O04_Detalle_Orden(username);

                deo.setPanelAnterior(thisPanel);
                deo.isMassiveProccess(true);
                deo.revisionPorLista(ordenes);
                deo.cargarDatosOrden(ordenes.get(0));

                PanelLoader.loadPanel(deo, mainContainerPanel);

            }
        }

    };

    private String query;

    public void setQuery(String query) {
        this.query = query;
    }

    public void recargarTabla() {

        try {
            ClearTable.clear(tablaListaOrdenes);

            LyraTableModel2 model = (LyraTableModel2) tablaListaOrdenes.getModel();

            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;

            pSt = conexion.prepareStatement(query);
            pSt.executeQuery();

            ResultSet datos = pSt.getResultSet();
            ResultSetMetaData meta = datos.getMetaData();

            int CantCol = meta.getColumnCount() + 1;

            while (datos.next()) {

                Object[] filas = new Object[CantCol];

                for (int i = 0; i < CantCol; i++) {

                    if (i == 0) {
                        filas[i] = false;
                    } else {
                        filas[i] = datos.getObject(i);
                    }

                }

                model.addRow(filas);
            }

            pSt.close();

        } catch (SQLException ex) {
            Logger.getLogger(O05_Lista_Ordenes_Aprobacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListaOrdenes = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnAbrirOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnAprobar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnRechazar = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        bodyPanel.setBackground(new java.awt.Color(246, 250, 253));

        jPanel_Rounded_Corners_Degradado1.setColor1(new java.awt.Color(246, 250, 253));
        jPanel_Rounded_Corners_Degradado1.setColor2(new java.awt.Color(202, 216, 237));

        tablaListaOrdenes.setBackground(new java.awt.Color(242, 242, 242));
        tablaListaOrdenes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tablaListaOrdenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaListaOrdenes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaListaOrdenes.setColorOnEdit(new java.awt.Color(255, 255, 255));
        tablaListaOrdenes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaListaOrdenes);

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado1Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado1);
        jPanel_Rounded_Corners_Degradado1.setLayout(jPanel_Rounded_Corners_Degradado1Layout);
        jPanel_Rounded_Corners_Degradado1Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado1Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        btnAbrirOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnAbrirOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAbrirOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/view_document.png"))); // NOI18N

        btnAprobar.setBackground(new java.awt.Color(226, 210, 144));
        btnAprobar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAprobar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N
        btnAprobar.setText("Approve");

        btnRechazar.setBackground(new java.awt.Color(226, 210, 144));
        btnRechazar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRechazar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/rejects.png"))); // NOI18N
        btnRechazar.setText("Decline");

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRechazar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRechazar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAbrirOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnAbrirOrden;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnAprobar;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnRechazar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane panelScroll;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaListaOrdenes;
    // End of variables declaration//GEN-END:variables

    private JPanel panelAnterior;

    public void panelAnterior(JPanel panelAnterior) {
        this.panelAnterior = panelAnterior;
    }

    public LyraTable getTable() {
        return tablaListaOrdenes;
    }

    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(mainContainerPanel);
    }

    private void notifications(String msg, TypeMessage type) {
            new SystemMessages(LyraWorkspace.NotificationLabel, msg, type);
    }


    private void btnSalir() {
        btnSalir.addActionListener((e) -> {

            ((O05_Ambiente_Filtrado_Datos) panelAnterior).clearAll();
            ((O05_Ambiente_Filtrado_Datos) panelAnterior).setTitle();

            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

        });
    }

}
