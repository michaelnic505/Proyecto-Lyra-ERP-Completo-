package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.awt.Toolkit;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.gui.components.tables.lastversion.CellEditorLyraTable;
import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import javax.swing.ImageIcon;


public class U01_Lista_Centro_Costos extends javax.swing.JDialog {

    TableRowSorter<DefaultTableModel> filtro;
    
    public U01_Lista_Centro_Costos(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
        initComponents();
        cargarDatos();
        addEvents();
        
        toolbarBusqueda.setVisible(false);
       // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));
        funcionesBotones();
    }

    private void funcionesBotones() {

        TableEventSimpleButton event = (int row) -> {

        };
        tablaCentroCostos.getColumnModel().getColumn(1).setCellEditor(new CellEditorLyraTable(event));
    }


    private void cargarDatos() {        
        
        Modelo_Lista_CentroCostos.setLanguage(tablaCentroCostos);
        
        U01_Cargar_Lista_CentroCostos list = new U01_Cargar_Lista_CentroCostos();
        list.setTable(tablaCentroCostos);
        list.cargarDatos();
        
        
        Tabla_Formato.tablaNoEditable(tablaCentroCostos, 15);
        ConfigurarFiltroEnTabla();
    }

    private void btnMostrarBusqueda() {
        btnMostrarBusqueda.addActionListener((e) -> {
            if (toolbarBusqueda.isVisible()) {

                btnMostrarBusqueda.setIcon(new ImageIcon(getClass().getResource("/icons/auxiliarwindows/deployed.png")));
                toolbarBusqueda.setVisible(false);

            } else {

                btnMostrarBusqueda.setIcon(new ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png")));
                toolbarBusqueda.setVisible(true);

            }
        });
    }

    private void ConfigurarFiltroEnTabla() {

        tablaCentroCostos.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tablaCentroCostos.getModel());
        tablaCentroCostos.setRowSorter(filtro);
    }

    private void busquedaTextBox() {
        busquedaTextBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtro.setRowFilter(RowFilter.regexFilter(busquedaTextBox.getText()));
            }
        });
    }

    
    private void addEvents(){
        
        botonSeleccionar();
        botonSalir();
        btnMostrarBusqueda();
        busquedaTextBox();
        
    }

    private void botonSeleccionar(){
        
        btnSeleccionar.addActionListener((ActionEvent e)->{
            seleccionarAreaSuperior();
            
        });
    }
    
    private void botonSalir(){
        
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }

    public void setIdCentroCostos(JTextField idCentroCostos) {
        this.idCentroCostos = idCentroCostos;
    }

    public void setDescripcionCentroCosto(JTextField descripcionCentroCosto) {
        this.descripcionCentroCosto = descripcionCentroCosto;
    }

    public void setIdEmplazamiento(JTextField idEmplazamiento) {
        this.idEmplazamiento = idEmplazamiento;
    }

    public void setDescripcionEmplazamiento(JTextField descripcionEmplazamiento) {
        this.descripcionEmplazamiento = descripcionEmplazamiento;
    }

    public void setIdArea(JTextField idArea) {
        this.idArea = idArea;
    }

    public void setDescripcionArea(JTextField descripcionArea) {
        this.descripcionArea = descripcionArea;
    }

    public void setIdSociedad(JTextField idSociedad) {
        this.idSociedad = idSociedad;
    }

    public void setDescripcionSociedad(JTextField descripcionSociedad) {
        this.descripcionSociedad = descripcionSociedad;
    }
    
    private JTextField idCentroCostos;
    private JTextField descripcionCentroCosto;
    private JTextField idEmplazamiento;
    private JTextField descripcionEmplazamiento;
    private JTextField idArea;
    private JTextField descripcionArea;
    private JTextField idSociedad;
    private JTextField descripcionSociedad;

    
    private void seleccionarAreaSuperior(){
        
         if (tablaCentroCostos.getSelectedRow() > -1) {

            
            String centroCosto = tablaCentroCostos.getValueAt(tablaCentroCostos.getSelectedRow(), 0).toString();
            String descripcionCC = tablaCentroCostos.getValueAt(tablaCentroCostos.getSelectedRow(), 1).toString();
            String emplazamiento = tablaCentroCostos.getValueAt(tablaCentroCostos.getSelectedRow(), 2).toString();
            String descripcionEmp = tablaCentroCostos.getValueAt(tablaCentroCostos.getSelectedRow(), 3).toString();
            String area = tablaCentroCostos.getValueAt(tablaCentroCostos.getSelectedRow(), 4).toString();
            String descripcionArea1 = tablaCentroCostos.getValueAt(tablaCentroCostos.getSelectedRow(), 5).toString();
            String sociedad = tablaCentroCostos.getValueAt(tablaCentroCostos.getSelectedRow(), 6).toString();
            String descripcionSociedad1 = tablaCentroCostos.getValueAt(tablaCentroCostos.getSelectedRow(), 7).toString();
            
             if (idCentroCostos != null) {
                 idCentroCostos.setText(centroCosto);
             }
             if (descripcionCentroCosto != null) {
                 descripcionCentroCosto.setText(descripcionCC);
             }
             if (idEmplazamiento != null) {
                 idEmplazamiento.setText(emplazamiento);
             }
             if (descripcionEmplazamiento != null) {
                 descripcionEmplazamiento.setText(descripcionEmp);
             }
             if (idArea != null) {
                 idArea.setText(area);
             }
             if (descripcionArea != null) {
                 descripcionArea.setText(descripcionArea1);
             }
             if (idSociedad != null) {
                 idSociedad.setText(sociedad);
             }
             if (descripcionSociedad != null) {
                 descripcionSociedad.setText(descripcionSociedad1);
             }


            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnSalir = new JButtonHQ();
        btnSeleccionar = new JButtonHQ();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCentroCostos = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();
        toolbarBusqueda = new javax.swing.JToolBar();
        busquedaTextBox = new javax.swing.JTextField();
        btnMostrarBusqueda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(172, 200, 225));
        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close.png"))); // NOI18N
        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSalir);

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/ok_icon.png"))); // NOI18N
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSeleccionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSeleccionar);

        tablaCentroCostos.setAutoCreateRowSorter(true);
        tablaCentroCostos.setBackground(new java.awt.Color(202, 219, 236));
        tablaCentroCostos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaCentroCostos);

        toolbarBusqueda.setRollover(true);
        toolbarBusqueda.add(busquedaTextBox);

        btnMostrarBusqueda.setBackground(new java.awt.Color(226, 210, 144));
        btnMostrarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png"))); // NOI18N

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolbarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(toolbarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Contenedor;
    private javax.swing.JButton btnMostrarBusqueda;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JTextField busquedaTextBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable tablaCentroCostos;
    private javax.swing.JToolBar toolbarBusqueda;
    // End of variables declaration//GEN-END:variables
}
