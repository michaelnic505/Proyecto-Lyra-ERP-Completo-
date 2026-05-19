package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;


import java.awt.Component;
import java.awt.Toolkit;
import com.simplecore.erp.gui.components.labels.JButtonHQ;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.gui.components.tables.lastversion.CellEditorLyraTable;
import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import javax.swing.ImageIcon;

public class Lista_Personal extends javax.swing.JDialog {

    TableRowSorter<DefaultTableModel> filtro;

    public Lista_Personal(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        cargarDatos();
        addEvents();
        funcionesBotones();
        toolbarBusqueda.setVisible(false);
     //   this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));

    }

    private void addEvents() {

        botonSeleccionar();
        botonSalir();
        btnMostrarBusqueda();
        busquedaTextBox();

    }

    private void funcionesBotones() {

        TableEventSimpleButton event = new TableEventSimpleButton() {
            @Override
            public void selectionRow(int row) {

            }

        };

        tablaResponsables.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(event));
    }

    public void setTitles(String title) {
        setTitle(title);
    }



    private void cargarDatos() {

        Lista_Responsable_Model.set(tablaResponsables);
        
        Lista_Personal_SQL pr = new Lista_Personal_SQL();
        pr.setTable(tablaResponsables);
        pr.cargarDatos();
        
        Tabla_Formato.tablaNoEditable(tablaResponsables, 10);        
        Tabla_Formato.editableColumn(tablaResponsables, 0, 0);
        Tabla_Formato.resizeTable(tablaResponsables, 5);
        
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

        tablaResponsables.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tablaResponsables.getModel());
        tablaResponsables.setRowSorter(filtro);
        
    }

    private void busquedaTextBox() {

        busquedaTextBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtro.setRowFilter(RowFilter.regexFilter(busquedaTextBox.getText()));
            }
        });

    }

    public void setTitleWindow(String title) {
        setTitle(title);
    }

    public void setUbicacionComponente(Component comp) {
        setLocationRelativeTo(comp);
    }


    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
            select();
        });
    }
    
    private void botonSalir(){
        
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
   
    }
    
    JTextField cod;
    JLabel desc;

    public void setTextFields(JTextField cod, JLabel desc) {

        this.cod = cod;
        this.desc = desc;

    }

    private void select() {

        int fila = tablaResponsables.getSelectedRow();
        if (fila > -1) {

            String codigo = tablaResponsables.getValueAt(fila, 1).toString();
            String descripcion = tablaResponsables.getValueAt(fila, 2).toString();

            if(cod!=null){
                cod.setText(codigo);
            }
            if(desc!=null){
                desc.setText(descripcion);
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
        tablaResponsables = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();
        btnMostrarBusqueda = new javax.swing.JButton();
        toolbarBusqueda = new javax.swing.JToolBar();
        busquedaTextBox = new javax.swing.JTextField();

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

        tablaResponsables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaResponsables);

        btnMostrarBusqueda.setBackground(new java.awt.Color(226, 210, 144));
        btnMostrarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png"))); // NOI18N

        toolbarBusqueda.setRollover(true);
        toolbarBusqueda.add(busquedaTextBox);

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaResponsables;
    private javax.swing.JToolBar toolbarBusqueda;
    // End of variables declaration//GEN-END:variables
}
