package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.gui.components.tables.lastversion.CellEditorLyraTable;
import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import static com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy.U01_Crear_Ubicaciones.labelOrganizacion;
import javax.swing.ImageIcon;


public class U01_Lista_Areas extends javax.swing.JDialog {

    TableRowSorter<DefaultTableModel> filtro;
    
    public U01_Lista_Areas(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
        initComponents();
        cargarDatos();
        addEvents();
        
        toolbarBusqueda.setVisible(false);
        setTitle(labelOrganizacion.getText());
        funcionesBotones();
    }

    private void funcionesBotones() {

        TableEventSimpleButton event = (int row) -> {

        };
        tablaAreas.getColumnModel().getColumn(1).setCellEditor(new CellEditorLyraTable(event));
    }


    private void cargarDatos() {        
        
        Modelo_Lista_Areas.setLanguage(tablaAreas);
        
        U01_Cargar_Lista_Areas areas = new U01_Cargar_Lista_Areas();
        areas.setTable(tablaAreas);
        areas.cargarAreas();
        
        Tabla_Formato.tablaNoEditable(tablaAreas, 15);
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

        tablaAreas.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tablaAreas.getModel());
        tablaAreas.setRowSorter(filtro);
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
    
    private void seleccionarAreaSuperior(){
        
         if (tablaAreas.getSelectedRow() > -1) {

            String area = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 0).toString();
            String descripcionArea = tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 1).toString();
           

            U01_Crear_Ubicaciones.idArea.setText(area);
            U01_Crear_Ubicaciones.descripcionArea.setText(descripcionArea);
            

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
        tablaAreas = new com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable();
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

        tablaAreas.setAutoCreateRowSorter(true);
        tablaAreas.setBackground(new java.awt.Color(202, 219, 236));
        tablaAreas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaAreas);

        toolbarBusqueda.setRollover(true);
        toolbarBusqueda.add(busquedaTextBox);

        btnMostrarBusqueda.setBackground(new java.awt.Color(226, 210, 144));
        btnMostrarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png"))); // NOI18N

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
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
    private com.simplecore.erp.gui.components.tables.lastversion.SimpleLyraTable tablaAreas;
    private javax.swing.JToolBar toolbarBusqueda;
    // End of variables declaration//GEN-END:variables
}
