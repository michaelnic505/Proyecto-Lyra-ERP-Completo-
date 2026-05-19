package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.E01_Cargar_Lista_Equipos;
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
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.config.database.utils.Tabla_Formato;
import com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils.Modelo_Lista_Equipos;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders.O01_Crear_Orden;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class Lista_Equipos extends javax.swing.JPanel {

    public boolean isEquipoSelect() {
        return equipoSelect;
    }

    public void setEquipoSelect(boolean equipoSelect) {
        this.equipoSelect = equipoSelect;
    }
    
    private TableRowSorter<DefaultTableModel> filtro;
        
    public Lista_Equipos() {
        
        initComponents();   
        addEvents();
        cargarTabla();
        
        funcionesBotones();
    }

    private void funcionesBotones() {

        TableEventSimpleButton e = new TableEventSimpleButton() {
            @Override
            public void selectionRow(int row) {

            }

        };

        tabla_Equipos.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(e));
    }


    private void addEvents(){
        
        botonSeleccionar();
        botonSalir();
        filtrarID_EQUIPO();
        filtrarDESCRIPCION_EQUIPO();
        filtrarSERIE();
  
    }
    
    private void cargarTabla(){ 
        
        
        idEquipoTB.setText(null);
        descripcionEquipoTB.setText(null);
        serieTB.setText(null);
        
        Modelo_Lista_Equipos.setLanguage(tabla_Equipos);
        
        
        E01_Cargar_Lista_Equipos nuevaLista = new E01_Cargar_Lista_Equipos();
        nuevaLista.cargarListaEquiposEnTabla(tabla_Equipos);
        
        Tabla_Formato.tablaNoEditable(tabla_Equipos,10);                
        Tabla_Formato.editableColumn(tabla_Equipos, 0, 0);        
        Tabla_Formato.resizeTable(tabla_Equipos, 10);
        
        ConfigurarFiltroEnTabla();

    }

    private void ConfigurarFiltroEnTabla() {

        tabla_Equipos.setAutoCreateRowSorter(true);
        filtro = new TableRowSorter<>((DefaultTableModel) tabla_Equipos.getModel());
        tabla_Equipos.setRowSorter(filtro);
    }

    private void filtrarID_EQUIPO() {
        
        idEquipoTB.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(idEquipoTB.getText(), 1));
            }
            
        });
        
    }
    private void filtrarDESCRIPCION_EQUIPO() {
        descripcionEquipoTB.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(descripcionEquipoTB.getText(), 2));
            }            
        });        
        
    }
    private void filtrarSERIE() {
        serieTB.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
               filtro.setRowFilter(RowFilter.regexFilter(serieTB.getText(), 15));
            }            
        });
        
    }

    private JTextField codigoEquipoTB;
    private JLabel descripcionEquipoLB;
    private JTextField codigoUbicacionTB;
    private JLabel descripcionUbicacionLB;
    private JTextField sociedadTB;
    private JLabel descripcionSociedad;
    private JTextField areaTB;
    private JLabel descripcionArea;
    private JTextField emplazamientoTB;
    private JLabel descripcionEmplazamientro;
    private JTextField centroCostosTB;
    private JLabel descripcionCentroCosto;
    private JTextField codigoGrupoPlanTB;
    private JLabel descripcionGrupoPlanif;
    
    public void setCampos(JTextField codigoEquipoTB, JLabel descripcionEquipoLB,
                        JTextField codigoUbicacionTB, JLabel descripcionUbicacionLB,
                        JTextField sociedadTB, JLabel descripcionSociedad,
                        JTextField areaTB, JLabel descripcionArea,
                        JTextField emplazamientoTB, JLabel descripcionEmplazamientro,
                        JTextField centroCostosTB, JLabel descripcionCentroCosto,
                        JTextField codigoGrupoPlanTB, JLabel descripcionGrupoPlanif) {

        this.codigoEquipoTB = codigoEquipoTB;
        this.descripcionEquipoLB = descripcionEquipoLB;
        
        this.codigoUbicacionTB = codigoUbicacionTB;
        this.descripcionUbicacionLB = descripcionUbicacionLB;
        
        this.sociedadTB = sociedadTB;
        this.descripcionSociedad = descripcionSociedad;
        
        this.areaTB = areaTB;
        this.descripcionArea = descripcionArea;
        
        this.emplazamientoTB = emplazamientoTB;
        this.descripcionEmplazamientro = descripcionEmplazamientro;
        
        this.centroCostosTB = centroCostosTB;
        this.descripcionCentroCosto = descripcionCentroCosto;
        
        this.codigoGrupoPlanTB = codigoGrupoPlanTB;
        this.descripcionGrupoPlanif = descripcionGrupoPlanif;

    }

    public void setCampos(JTextField codigoEquipoTB, JLabel descripcionEquipoLB,
            JTextField codigoUbicacionTB, JLabel descripcionUbicacionLB,
            JTextField codigoGrupoPlanTB) {

        this.codigoEquipoTB = codigoEquipoTB;
        this.descripcionEquipoLB = descripcionEquipoLB;
        this.codigoUbicacionTB = codigoUbicacionTB;
        this.descripcionUbicacionLB = descripcionUbicacionLB;
        this.codigoGrupoPlanTB = codigoGrupoPlanTB;

    }

    
    JPanel panelAnterior;
    
    public void setPanelAnterior(JPanel panel){
        this.panelAnterior = panel;
    }
 
    
    private String equipo;
    private String descripcionEquipo;
    private String codigoUbicacionSup;
    private String descripcionUbicacionSup;
    private String sociedad;
    private String denominacionSociedad;
    private String centroCostos;
    private String denominacionCentroCostos;
    private String emplazamiento;
    private String denominacionEmplazamiento;
    private String area;
    private String denominacionArea;
    private String grupoPlanif;
    private String descripcionGrupoPlani;
    private boolean equipoSelect = false;
    
    private void seleccionarEquipo() {

        if (tabla_Equipos.getSelectedRow() > -1) {
            
            int fila = tabla_Equipos.getSelectedRow();
            
            setEquipo(tabla_Equipos.getValueAt(fila , 1).toString());
            setDescripcionEquipo(tabla_Equipos.getValueAt(fila , 2).toString());
            
            setCodigoUbicacionSup(tabla_Equipos.getValueAt(fila , 24).toString());
            setDescripcionUbicacionSup(tabla_Equipos.getValueAt(fila , 25).toString());
            
            setSociedad(tabla_Equipos.getValueAt(fila , 12).toString());
            setDenominacionSociedad(tabla_Equipos.getValueAt(fila , 13).toString());
            
            setCentroCostos(tabla_Equipos.getValueAt(fila , 16).toString());
            setDenominacionCentroCostos(tabla_Equipos.getValueAt(fila, 17).toString());

            setEmplazamiento(tabla_Equipos.getValueAt(fila, 18).toString());
            setDenominacionEmplazamiento(tabla_Equipos.getValueAt(fila, 19).toString());

            setArea(tabla_Equipos.getValueAt(fila, 20).toString());
            setDenominacionArea(tabla_Equipos.getValueAt(fila, 21).toString());

            setGrupoPlanif(tabla_Equipos.getValueAt(fila, 30).toString());
            setDescripcionGrupoPlani(tabla_Equipos.getValueAt(fila, 31).toString());
            
            
            if(codigoEquipoTB!=null){
                codigoEquipoTB.setText(getEquipo());
            }
            if(descripcionEquipoLB!=null){
                descripcionEquipoLB.setText(getDescripcionEquipo());
            }
            if(codigoUbicacionTB!=null){
                codigoUbicacionTB.setText(getCodigoUbicacionSup());
            }
            if(descripcionUbicacionLB!=null){
                descripcionUbicacionLB.setText(getDescripcionUbicacionSup());
            }
            if(centroCostosTB!=null){
                centroCostosTB.setText(getCentroCostos());
            }
            if(descripcionCentroCosto!=null){
                descripcionCentroCosto.setText(getDenominacionCentroCostos());
            }
            if(sociedadTB!=null){
                sociedadTB.setText(getSociedad());
            }
            if(descripcionSociedad!=null){
                descripcionSociedad.setText(getDenominacionSociedad());
            }
            if(emplazamientoTB!=null){
                emplazamientoTB.setText(getEmplazamiento());
            }
            if(descripcionEmplazamientro!=null){
                descripcionEmplazamientro.setText(getDenominacionEmplazamiento());
            }
            if(areaTB!=null){
                areaTB.setText(getArea());
            }
            if(descripcionArea!=null){
                descripcionArea.setText(getDenominacionArea());
            }
            if(codigoGrupoPlanTB!=null){
                codigoGrupoPlanTB.setText(getGrupoPlanif());
            }
            if(descripcionGrupoPlanif!=null){
                descripcionGrupoPlanif.setText(getDescripcionGrupoPlani());
            }
            
            if(search){
                O01_Crear_Orden.pasarDatosEquipo();
            }
            
            
            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
        }
        
        

    }
    private void botonSeleccionar(){
        btnSeleccionar.addActionListener((ActionEvent e)->{
            seleccionarEquipo();
        });
    }
    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);

        });
    }

    private boolean search = false;
    public void setSearch(boolean search){
        this.search = search;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Equipos = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        ToolBar = new javax.swing.JToolBar();
        separador1 = new javax.swing.JToolBar.Separator();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0));
        btnSalir = new JButtonHQ();
        btnSeleccionar = new JButtonHQ();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnTodo = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        panelBusqueda = new javax.swing.JPanel();
        labelEquipo = new javax.swing.JLabel();
        idEquipoTB = new javax.swing.JTextField();
        labelDescripcionEquipo = new javax.swing.JLabel();
        descripcionEquipoTB = new javax.swing.JTextField();
        labelSerie = new javax.swing.JLabel();
        serieTB = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        tabla_Equipos.setBackground(new java.awt.Color(246, 250, 253));
        tabla_Equipos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabla_Equipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla_Equipos);

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
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ToolBar.add(btnSalir);

        btnSeleccionar.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ToolBar.add(btnSeleccionar);
        ToolBar.add(jSeparator4);

        btnTodo.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        btnTodo.setText("Todo");
        btnTodo.setFocusable(false);
        btnTodo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnTodo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ToolBar.add(btnTodo);
        ToolBar.add(jSeparator5);

        panelBusqueda.setOpaque(false);

        labelEquipo.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelEquipo.setText("Codigo");

        labelDescripcionEquipo.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelDescripcionEquipo.setText("Descripción Ubicación");

        labelSerie.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelSerie.setText("Serie :");

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelEquipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelDescripcionEquipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descripcionEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSerie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serieTB, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelEquipo)
                    .addComponent(idEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(descripcionEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelDescripcionEquipo))
                    .addComponent(labelSerie)
                    .addComponent(serieTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    public String getEquipo() {
        return equipo;
    }

    public String getDescripcionEquipo() {
        return descripcionEquipo;
    }

    public String getCodigoUbicacionSup() {
        return codigoUbicacionSup;
    }

    public String getDescripcionUbicacionSup() {
        return descripcionUbicacionSup;
    }

    public String getSociedad() {
        return sociedad;
    }

    public String getDenominacionSociedad() {
        return denominacionSociedad;
    }

    public String getCentroCostos() {
        return centroCostos;
    }

    public String getDenominacionCentroCostos() {
        return denominacionCentroCostos;
    }

    public String getEmplazamiento() {
        return emplazamiento;
    }

    public String getDenominacionEmplazamiento() {
        return denominacionEmplazamiento;
    }

    public String getArea() {
        return area;
    }

    public String getDenominacionArea() {
        return denominacionArea;
    }

    public String getGrupoPlanif() {
        return grupoPlanif;
    }

    public String getDescripcionGrupoPlani() {
        return descripcionGrupoPlani;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setDescripcionEquipo(String descripcionEquipo) {
        this.descripcionEquipo = descripcionEquipo;
    }

    public void setCodigoUbicacionSup(String codigoUbicacionSup) {
        this.codigoUbicacionSup = codigoUbicacionSup;
    }

    public void setDescripcionUbicacionSup(String descripcionUbicacionSup) {
        this.descripcionUbicacionSup = descripcionUbicacionSup;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public void setDenominacionSociedad(String denominacionSociedad) {
        this.denominacionSociedad = denominacionSociedad;
    }

    public void setCentroCostos(String centroCostos) {
        this.centroCostos = centroCostos;
    }

    public void setDenominacionCentroCostos(String denominacionCentroCostos) {
        this.denominacionCentroCostos = denominacionCentroCostos;
    }

    public void setEmplazamiento(String emplazamiento) {
        this.emplazamiento = emplazamiento;
    }

    public void setDenominacionEmplazamiento(String denominacionEmplazamiento) {
        this.denominacionEmplazamiento = denominacionEmplazamiento;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDenominacionArea(String denominacionArea) {
        this.denominacionArea = denominacionArea;
    }

    public void setGrupoPlanif(String grupoPlanif) {
        this.grupoPlanif = grupoPlanif;
    }

    public void setDescripcionGrupoPlani(String descripcionGrupoPlani) {
        this.descripcionGrupoPlani = descripcionGrupoPlani;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JPanel bodyPanel;
    public static javax.swing.JButton btnSalir;
    public static javax.swing.JButton btnSeleccionar;
    public static javax.swing.JButton btnTodo;
    private javax.swing.JTextField descripcionEquipoTB;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JTextField idEquipoTB;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    public static javax.swing.JLabel labelDescripcionEquipo;
    public static javax.swing.JLabel labelEquipo;
    public static javax.swing.JLabel labelSerie;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JToolBar.Separator separador1;
    private javax.swing.JTextField serieTB;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tabla_Equipos;
    // End of variables declaration//GEN-END:variables
}
