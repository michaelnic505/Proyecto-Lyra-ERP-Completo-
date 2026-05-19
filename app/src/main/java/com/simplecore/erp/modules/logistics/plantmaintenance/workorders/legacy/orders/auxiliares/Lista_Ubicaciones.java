package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.Modelo_Lista_Ubicaciones;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import com.simplecore.erp.gui.components.tables.lastversion.CellEditorLyraTable;
import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy.U02_Agregar_CheckBox;
import com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy.U02_Cargar_Campos_Ubicaciones;
import com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy.U02_Cargar_Lista_Ubicaciones;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.O01_Crear_Orden;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class Lista_Ubicaciones extends javax.swing.JPanel {

    public JLabel getDescUbi() {
        return descUbi;
    }

    public JTextField getCentroC() {
        return centroC;
    }

    public JLabel getDescCC() {
        return descCC;
    }

    public JTextField getEmpl() {
        return empl;
    }

    public JLabel getDescEmpl() {
        return descEmpl;
    }

    public JTextField getArea() {
        return area;
    }

    public JLabel getDescArea() {
        return descArea;
    }

    public JTextField getSocie() {
        return socie;
    }

    public JLabel getDescSocie() {
        return descSocie;
    }

    public JTextField getGrupoPlanif() {
        return grupoPlanif;
    }

    public JLabel getDescripcionGP() {
        return descripcionGP;
    }

    public void setDescUbi(JLabel descUbi) {
        this.descUbi = descUbi;
    }

    public void setCentroC(JTextField centroC) {
        this.centroC = centroC;
    }

    public void setDescCC(JLabel descCC) {
        this.descCC = descCC;
    }

    public void setEmpl(JTextField empl) {
        this.empl = empl;
    }

    public void setDescEmpl(JLabel descEmpl) {
        this.descEmpl = descEmpl;
    }

    public void setArea(JTextField area) {
        this.area = area;
    }

    public void setDescArea(JLabel descArea) {
        this.descArea = descArea;
    }

    public void setSocie(JTextField socie) {
        this.socie = socie;
    }

    public void setDescSocie(JLabel descSocie) {
        this.descSocie = descSocie;
    }

    public void setGrupoPlanif(JTextField grupoPlanif) {
        this.grupoPlanif = grupoPlanif;
    }

    public void setDescripcionGP(JLabel descripcionGP) {
        this.descripcionGP = descripcionGP;
    }

    public JTextField getCodUbi() {
        return codUbi;
    }

    public void setCodUbi(JTextField codUbi) {
        this.codUbi = codUbi;
    }


    
    private TableRowSorter<DefaultTableModel> filtro;
        
    public Lista_Ubicaciones() {
        initComponents(); 
        addEvents();
        setModeloTabla();
        cargarTabla();
        cargarCampos();
        
        funcionesBotones();
    }

    private void funcionesBotones() {

        TableEventSimpleButton event = new TableEventSimpleButton() {
            @Override
            public void selectionRow(int row) {

            }

        };

        tabla_Ubicaciones.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(event));
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
        
        Tabla_Formato.tablaNoEditable(tabla_Ubicaciones, 7);        
        Tabla_Formato.editableColumn(tabla_Ubicaciones, 0, 0);
        Tabla_Formato.resizeTable(tabla_Ubicaciones, 7);
        
        ConfigurarFiltroEnTabla();

    }
    
    private void cargarCampos(){

        U02_Agregar_CheckBox.addCheckBox(16, tabla_Ubicaciones);
        U02_Agregar_CheckBox.addCheckBox(17, tabla_Ubicaciones);
        cargarValoresCampos();
        Tabla_Formato.resizeTable(tabla_Ubicaciones, 10);

    }
    
    private void cargarValoresCampos(){
        if (tabla_Ubicaciones.getRowCount() > 0) {
             for (int i = 0; i < tabla_Ubicaciones.getRowCount(); i++) {
                 U02_Cargar_Campos_Ubicaciones carga = new U02_Cargar_Campos_Ubicaciones ();
                 carga.setIdUbicacion(tabla_Ubicaciones.getValueAt(i, 1).toString());
                 carga.cargar();
                 
                 tabla_Ubicaciones.setValueAt(carga.getNivel(), i, 15);
                 tabla_Ubicaciones.setValueAt(carga.isMontaje(), i, 16);
                 tabla_Ubicaciones.setValueAt(carga.isEstatus(), i, 17);
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
    
    JPanel panelAnterior;

    public void setPanelAnterior(JPanel panel) {
        this.panelAnterior = panel;
    }
    
    private JTextField codUbi;
    private JLabel descUbi;
    private JTextField centroC;
    private JLabel descCC;
    private JTextField empl;
    private JLabel descEmpl;
    private JTextField area;
    private JLabel descArea;
    private JTextField socie;
    private JLabel descSocie;
    private JTextField grupoPlanif;
    private JLabel descripcionGP;
    
    private boolean search = false;

    public void setSearch(boolean search) {
        this.search = search;
    }

    private void seleccionarUbicacion() {

        int fila = tabla_Ubicaciones.getSelectedRow();

        if (fila > -1) {

            boolean mounting = (boolean) tabla_Ubicaciones.getValueAt(fila, 16);

            if (mounting) {

                String codigoUbicacion = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 1).toString();
                String descripcionUbicacion = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 2).toString();

                String centroCosto = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 5).toString();
                String descripcionCC = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 6).toString();

                String emplazamiento = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 7).toString();
                String descripcionEmp = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 8).toString();

                String area1 = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 9).toString();
                String descripcionArea = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 10).toString();

                String sociedad = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 11).toString();
                String descripcionSociedad = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 12).toString();

                String grupoP = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 13).toString();
                String descripcionGrupoP = tabla_Ubicaciones.getValueAt(tabla_Ubicaciones.getSelectedRow(), 14).toString();

                if (getCodUbi() != null) {
                    getCodUbi().setText(codigoUbicacion);
                }
                if (getDescUbi() != null) {
                    getDescUbi().setText(descripcionUbicacion);
                }
                if (getCentroC() != null) {
                    getCentroC().setText(centroCosto);
                }
                if (getDescCC() != null) {
                    getDescCC().setText(descripcionCC);
                }
                if (getEmpl() != null) {
                    getEmpl().setText(emplazamiento);
                }
                if (getDescEmpl() != null) {
                    getDescEmpl().setText(descripcionEmp);
                }
                if (getArea() != null) {
                    getArea().setText(area1);
                }
                if (getDescArea() != null) {
                    getDescArea().setText(descripcionArea);
                }
                if (getSocie() != null) {
                    getSocie().setText(sociedad);
                }
                if (getDescSocie() != null) {
                    getDescSocie().setText(descripcionSociedad);
                }
                if (getGrupoPlanif() != null) {
                    getGrupoPlanif().setText(grupoP);
                }
                if (getDescripcionGP() != null) {
                    getDescripcionGP().setText(descripcionGrupoP);
                }

                if (search) {
                    O01_Crear_Orden.pasarDatosUbicacion();
                }

                PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

            } else {

               new SystemMessages(LyraWorkspace.NotificationLabel, NOT.msg(NOT.MOUNTING_NOT_ALLOWED), TypeMessage.WARNING);

            }

        }

    }
    
    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
            seleccionarUbicacion();
        });
    }

    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Ubicaciones = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        ToolBar = new javax.swing.JToolBar();
        separador1 = new javax.swing.JToolBar.Separator();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0));
        btnSalir = new JButtonHQ();
        btnSeleccionar = new JButtonHQ();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnTodo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        panelBusqueda = new javax.swing.JPanel();
        labelCodigo = new javax.swing.JLabel();
        COD_UBICACION = new javax.swing.JTextField();
        labelDescripcion = new javax.swing.JLabel();
        DESCRIPCION_UBICACION = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        ToolBar.setBackground(new java.awt.Color(114, 162, 207));
        ToolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ToolBar.setRollover(true);
        ToolBar.setBorderPainted(false);
        ToolBar.setOpaque(false);
        ToolBar.add(separador1);
        ToolBar.add(filler1);

        btnSalir.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ToolBar.add(btnSalir);

        btnSeleccionar.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/ok_icon.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ToolBar.add(btnSeleccionar);
        ToolBar.add(jSeparator3);

        btnTodo.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        btnTodo.setText("Todo");
        btnTodo.setFocusable(false);
        btnTodo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnTodo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ToolBar.add(btnTodo);
        ToolBar.add(jSeparator1);

        panelBusqueda.setOpaque(false);

        labelCodigo.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelCodigo.setText("Codigo");

        labelDescripcion.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelDescripcion.setText("Descripción Ubicación");

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(COD_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DESCRIPCION_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCodigo)
                    .addComponent(COD_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DESCRIPCION_UBICACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelDescripcion)))
                .addContainerGap())
        );

        ToolBar.add(panelBusqueda);

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado6Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado6);
        jPanel_Rounded_Corners_Degradado6.setLayout(jPanel_Rounded_Corners_Degradado6Layout);
        jPanel_Rounded_Corners_Degradado6Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ToolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScroll)
            .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelScroll))
        );
    }// </editor-fold>//GEN-END:initComponents




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField COD_UBICACION;
    private javax.swing.JTextField DESCRIPCION_UBICACION;
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JPanel bodyPanel;
    public static javax.swing.JButton btnSalir;
    public static javax.swing.JButton btnSeleccionar;
    public static javax.swing.JButton btnTodo;
    private javax.swing.Box.Filler filler1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    public static javax.swing.JLabel labelCodigo;
    public static javax.swing.JLabel labelDescripcion;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JToolBar.Separator separador1;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tabla_Ubicaciones;
    // End of variables declaration//GEN-END:variables
}
