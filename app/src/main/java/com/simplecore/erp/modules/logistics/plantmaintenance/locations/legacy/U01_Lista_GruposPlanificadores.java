package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import com.simplecore.erp.gui.components.tables.lastversion.CellEditorLyraTable;
import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import com.simplecore.erp.config.database.utils.Tabla_Formato;

public class U01_Lista_GruposPlanificadores extends javax.swing.JDialog {



    public U01_Lista_GruposPlanificadores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
        cargarDatos();
        addEvents();
        
        funcionesBotones();
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lyra/access/main_class/icons/pir20.png")));
    }

    private void funcionesBotones() {
        
        TableEventSimpleButton e = new TableEventSimpleButton(){
            @Override
            public void selectionRow(int row) {

            }
            
        };
                
        tablaGruposPlanif.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(e));
    }

    private void cargarDatos() {

        Modelo_Grupos_Planificadores.setLanguage(tablaGruposPlanif);
        
        U01_Cargar_Lista_GruposPlanif gp = new U01_Cargar_Lista_GruposPlanif();
        gp.cargar_Lista_Areas(tablaGruposPlanif);
        
        Tabla_Formato.tablaNoEditable(tablaGruposPlanif, 10);        
        Tabla_Formato.editableColumn(tablaGruposPlanif, 0, 0);
        Tabla_Formato.resizeTable(tablaGruposPlanif, 15);
    }
    
    private void addEvents(){
        botonSeleccionar();
        botonSalir();   
        
    }

    private void botonSeleccionar() {
        btnSeleccionar.addActionListener((ActionEvent e)->{
            seleccionarCriticidad();
        });
    }
    
    private void botonSalir(){
        btnSalir.addActionListener((ActionEvent e)->{
            this.dispose();
        });
    }
    
    
    JTextField tf1;
    JTextField tf2;
    
    public void setJTextFields(JTextField code,JTextField desc){
        tf1 = code;
        tf2 = desc;       
        
    }
    
    private void seleccionarCriticidad() {

        int fila = tablaGruposPlanif.getSelectedRow();
        if (fila > -1) {

            String ID = tablaGruposPlanif.getValueAt(fila, 1).toString();
            String DESCRIPCION = tablaGruposPlanif.getValueAt(fila, 2).toString();

            tf1.setText(ID);
            tf2.setText(DESCRIPCION);
            
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnSalir = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaGruposPlanif = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();

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

        tablaGruposPlanif.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaGruposPlanif);

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
        Contenedor.setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
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
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tablaGruposPlanif;
    // End of variables declaration//GEN-END:variables
}
