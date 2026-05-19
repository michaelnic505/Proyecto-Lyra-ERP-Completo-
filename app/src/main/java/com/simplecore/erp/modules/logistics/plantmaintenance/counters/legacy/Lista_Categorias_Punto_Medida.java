package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableDesign;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.config.database.utils.Tabla_Formato;

public class Lista_Categorias_Punto_Medida extends javax.swing.JDialog {

    public Lista_Categorias_Punto_Medida(java.awt.Frame parent) {
       
        super(parent, true);
        initComponents();
        
        addEvents();
        setMovebleWindow();
        setResizebleWindow();
        setLocation(400, 80);
        toolbarBusqueda.setVisible(false);
        getRootPane().setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, new Color(0,102,160)));
        
        modeloTabla();
        cargarDatos();
    }

    private void addEvents() {

        botonOk();
        btnMostrarBusqueda();
        botonSalir();
    }
    private void contarRegistros(){

        int cant = 0;
        int datos = tableList.getRowCount();

        for (int i = 0; i < datos; i++) {

            if (tableList.getValueAt(i, 0) != null) {
                cant+=1;
            }

        }
        labelConteoRegistro.setText(cant+" "+NOT.msg(NOT.ENTRIES_FOUND));
    
    }

    private void modeloTabla() {

        String[] columnsES = {"Tp.", "Descripción"};
        String[] columnsEN = {"Cat", "Description"};
        String[] columnsPT = {"Cat", "Descrição"};

        DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnsEN);
            tabbedPanePrincipal.setTitleAt(0, "Restrictions");

        Objetos_Punto_Medida.getTitulo();
        
        
        tableList.setModel(model);
        
        JTableHeader header = tableList.getTableHeader();
        header.setBackground(tableList.getBackground());
        header.setReorderingAllowed(false);
        
        


    }
    private void cargarDatos(){

        DefaultTableModel model = (DefaultTableModel) tableList.getModel();
        model.addRow(new String[]{MeasurePointTypes.K.getCodigo(),MeasurePointTypes.K.getDescriptionCatPM()});
        model.addRow(new String[]{MeasurePointTypes.L.getCodigo(),MeasurePointTypes.L.getDescriptionCatPM()});
        model.addRow(new String[]{MeasurePointTypes.M.getCodigo(),MeasurePointTypes.M.getDescriptionCatPM()});
        model.addRow(new String[]{MeasurePointTypes.U.getCodigo(),MeasurePointTypes.U.getDescriptionCatPM()});
        
        tableList.setModel(model);
        title.setText(MeasurePointTypes.getTitulo());
        
        contarRegistros();
        edicionTablaMedidas();
        
    }
    private void edicionTablaMedidas() {
        
        Tabla_Formato.tablaCellNoEditable(tableList);
        LyraTableDesign.alignCenter(tableList, 0);
        LyraTableDesign.setWidthResizable(tableList, 0, 40);

        LyraTableDesign.alignLeft(tableList, 1);
        LyraTableDesign.setWidthResizable(tableList, 1, 200);

    }
    
    private void botonOk() {
        btnSeleccionar.addActionListener((ActionEvent e) -> {

            if (tableList.getSelectedRow() > -1) {
                
                cod.setText(tableList.getValueAt(tableList.getSelectedRow(), 0).toString());
                texto.setText(tableList.getValueAt(tableList.getSelectedRow(), 1).toString());
            
                dispose();
            }
        });
    }

    
    
    
    private JTextField cod;
    private JLabel texto;
    public void setCampos(JTextField cod, JLabel texto){
        this.cod = cod;
        this.texto = texto;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new com.simplecore.erp.gui.components.panels.JPanelCornerPainted();
        panelMenu = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnExit = new com.simplecore.erp.gui.components.labels.JButtonCustom();
        title = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        labelConteoRegistro = new javax.swing.JLabel();
        tabbedPanePrincipal = new com.simplecore.erp.gui.components.tabbedpanes.TabbedPane();
        panelTabbed = new javax.swing.JPanel();
        cintaOpciones = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnSeleccionar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnPegarLista = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnBorrar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        toolbarBusqueda = new javax.swing.JToolBar();
        cajaBusqueda = new javax.swing.JTextField();
        btnMostrarBusqueda = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableList = new com.simplecore.erp.gui.components.tables.lastversion.LyraTableAuxiliar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 146, 229));
        setModal(true);
        setUndecorated(true);

        panelContenedor.setBackground(new java.awt.Color(202, 216, 237));
        panelContenedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelContenedor.setAlto(15);
        panelContenedor.setAncho(15);

        panelMenu.setColor1(new java.awt.Color(0, 146, 229));
        panelMenu.setColor2(new java.awt.Color(0, 146, 229));

        btnExit.setBackground(new java.awt.Color(0, 146, 229));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/customized_components/button_icons/cerrar.png"))); // NOI18N
        btnExit.setBorderColor(new java.awt.Color(0, 146, 229));
        btnExit.setColor(new java.awt.Color(0, 146, 229));

        title.setForeground(new java.awt.Color(248, 248, 248));
        title.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/acceso2.png"))); // NOI18N
        title.setText("Title");
        title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(112, 112, 112)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        labelConteoRegistro.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N

        tabbedPanePrincipal.setForeground(new java.awt.Color(246, 246, 246));
        tabbedPanePrincipal.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPanePrincipal.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        tabbedPanePrincipal.setSelectedTabColor(new java.awt.Color(0, 131, 206));
        tabbedPanePrincipal.setTabBackgroundColor(new java.awt.Color(0, 102, 160));

        panelTabbed.setBackground(new java.awt.Color(202, 216, 237));

        cintaOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cintaOpciones.setColor1(new java.awt.Color(229, 235, 244));
        cintaOpciones.setColor2(new java.awt.Color(229, 235, 244));

        btnSalir.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close.png"))); // NOI18N

        btnSeleccionar.setBackground(new java.awt.Color(226, 210, 144));
        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/ok_icon.png"))); // NOI18N

        btnPegarLista.setBackground(new java.awt.Color(226, 210, 144));
        btnPegarLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/paste.png"))); // NOI18N

        btnBorrar.setBackground(new java.awt.Color(226, 210, 144));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/erase.png"))); // NOI18N

        javax.swing.GroupLayout cintaOpcionesLayout = new javax.swing.GroupLayout(cintaOpciones);
        cintaOpciones.setLayout(cintaOpcionesLayout);
        cintaOpcionesLayout.setHorizontalGroup(
            cintaOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cintaOpcionesLayout.createSequentialGroup()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnPegarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 375, Short.MAX_VALUE))
        );
        cintaOpcionesLayout.setVerticalGroup(
            cintaOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnPegarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        toolbarBusqueda.setRollover(true);
        toolbarBusqueda.add(cajaBusqueda);

        btnMostrarBusqueda.setBackground(new java.awt.Color(226, 210, 144));
        btnMostrarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png"))); // NOI18N

        tableList.setBackground(new java.awt.Color(202, 216, 237));
        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableList.setFontStyle(com.simplecore.erp.gui.components.tables.lastversion.FontStyle.Bold);
        tableList.setShowGrid(true);
        tableList.setSurrendersFocusOnKeystroke(true);
        jScrollPane2.setViewportView(tableList);

        javax.swing.GroupLayout panelTabbedLayout = new javax.swing.GroupLayout(panelTabbed);
        panelTabbed.setLayout(panelTabbedLayout);
        panelTabbedLayout.setHorizontalGroup(
            panelTabbedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cintaOpciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolbarBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelTabbedLayout.setVerticalGroup(
            panelTabbedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabbedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMostrarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(toolbarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(cintaOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        tabbedPanePrincipal.addTab("Restricciones", panelTabbed);

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addComponent(labelConteoRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(153, 153, 153))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(tabbedPanePrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedPanePrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(labelConteoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnBorrar;
    private com.simplecore.erp.gui.components.labels.JButtonCustom btnExit;
    private javax.swing.JButton btnMostrarBusqueda;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnPegarLista;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSeleccionar;
    private javax.swing.JTextField cajaBusqueda;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient cintaOpciones;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelConteoRegistro;
    private com.simplecore.erp.gui.components.panels.JPanelCornerPainted panelContenedor;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelMenu;
    private javax.swing.JPanel panelTabbed;
    private com.simplecore.erp.gui.components.tabbedpanes.TabbedPane tabbedPanePrincipal;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTableAuxiliar tableList;
    private com.simplecore.erp.gui.components.labels.JLabelHQ title;
    private javax.swing.JToolBar toolbarBusqueda;
    // End of variables declaration//GEN-END:variables
    
    
    private int pX;
    private int pY;
    
    private void setMovebleWindow() {

        panelMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pX = e.getX();
                pY = e.getY();
            }

        });

        panelMenu.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(getLocation().x + e.getX() - pX,
                        getLocation().y + e.getY() - pY);
            }
        });

        btnExit.addActionListener((e) -> {
            dispose();
        });

    }
    private void setResizebleWindow() {
        addMouseMotionListener(ResizableWindow);
    }
    private final MouseMotionListener ResizableWindow = new MouseMotionListener() {

        @Override
        public void mouseMoved(MouseEvent e) {

            //margenes - derecha
            double ladoW = getWidth() - 2;
            double ladoS = getHeight() - 2;

            Point p = e.getPoint();

            if (p.getX() >= ladoW && p.getX() <= getWidth()
                    && p.getY() > 10 && p.getY() < getHeight() - 10) {

                setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));

            } else if (p.getY() >= ladoS && p.getY() <= getHeight()
                    && p.getX() < getWidth() - 10) {

                setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));

            } else if (p.getX() > getWidth() - 10 && p.getY() > getHeight() - 10) {

                setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));

            } else {

                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        }

        @Override
        public void mouseDragged(MouseEvent e) {

            if (getCursor().getType() == Cursor.E_RESIZE_CURSOR) {

                Point c = e.getPoint();
                int margenMayor = (int) (c.getX() - getWidth());
                int margenMenor = (int) c.getX();

                if (c.getX() > getWidth()) {

                    setBounds(getX(), getY(), getWidth() + margenMayor, getHeight());

                } else if (c.getX() < getWidth()) {
                    if (margenMenor >= 200) {
                        setBounds(getX(), getY(), margenMenor, getHeight());
                    }
                }

            } else if (getCursor().getType() == Cursor.S_RESIZE_CURSOR) {

                Point c = e.getPoint();
                int margenMayor = (int) (c.getY() - getHeight());
                int margenMenor = (int) c.getY();

                if (c.getY() > getHeight()) {

                    setBounds(getX(), getY(), getWidth(), getHeight() + margenMayor);

                } else if (c.getY() < getHeight()) {
                    if (margenMenor >= 200) {
                        setBounds(getX(), getY(), getWidth(), margenMenor);
                    }
                }

            } else if (getCursor().getType() == Cursor.SE_RESIZE_CURSOR) {

                Point c = e.getPoint();

                int margenMayorX = (int) (c.getX() - getWidth());
                int margenMenorX = (int) c.getX();

                int margenMayorY = (int) (c.getY() - getHeight());
                int margenMenorY = (int) c.getY();

                if (c.getX() > getWidth() && c.getY() > getHeight()) {

                    setBounds(getX(), getY(), getWidth() + margenMayorX, getHeight() + margenMayorY);

                } else if (c.getX() < getWidth() && c.getY() < getHeight()) {

                    if (margenMenorX >= 200 && margenMenorY >= 200) {
                        setBounds(getX(), getY(), margenMenorX, margenMenorY);
                    }

                } else if (c.getX() > getWidth() && c.getY() == getHeight()) {

                    setBounds(getX(), getY(), getWidth() + margenMayorX, getHeight());

                } else if (c.getX() > getWidth() && c.getY() < getHeight()) {

                    setBounds(getX(), getY(), getWidth() + margenMayorX, getHeight() + margenMayorY);

                } else if (c.getX() == getWidth() && c.getY() > getHeight()) {

                    setBounds(getX(), getY(), getWidth(), getHeight() + margenMayorY);
                } else if (c.getX() < getWidth() && c.getY() > getHeight()) {

                    setBounds(getX(), getY(), getWidth() + margenMayorX, getHeight() + margenMayorY);
                }

            }
        }
    };

    
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
    
    private void notifactionMessages(String text, TypeMessage type) {

       new SystemMessages(LyraWorkspace.NotificationLabel, text, type);

    }
    private void botonSalir() {
        btnSalir.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
    }

}

