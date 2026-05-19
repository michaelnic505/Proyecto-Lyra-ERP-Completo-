package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas.aprobacion_ordenes;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import com.simplecore.erp.gui.components.tables.lastversion.CellEditorLyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableDesign;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel2;
import com.simplecore.erp.gui.components.tables.interfaces.TableEventSimpleButton;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.utils.notifications.NOT;

public class AddList extends javax.swing.JDialog {

    public AddList(java.awt.Frame parent, String titl) {
       
        super(parent, true);
        initComponents();
        title.setText(titl);
        addEvents();
        setMovebleWindow();
        setResizebleWindow();
        
    }

    private void funcionesBotones() {

        TableEventSimpleButton event = new TableEventSimpleButton() {
            @Override
            public void selectionRow(int row) {
            }
        };
        tableList.getColumnModel().getColumn(0).setCellEditor(new CellEditorLyraTable(event));
    
    }

    
    private void disenoTabla() {

        
        LyraTableDesign.alignLeft(tableList, 1);
        LyraTableDesign.setWidthResizable(tableList, 1, 100);

        tableList.setSurrendersFocusOnKeystroke(true);
        
        
    }
    private void addEvents() {

        botonOk();
        botonPegarLista();
        borrarDatos();
        botonSalir();
        pegarConTeclas();
        
    }
    
    
    public void setModel(String nombreCampo) {

        setTitle(nombreCampo);
        Object[] nombreColumna = {nombreCampo};

        LyraTableModel2 model = new LyraTableModel2();
        model.setColumnIdentifiers(nombreColumna);

        for (int i = 0; i < 50; i++) {
            model.addRow(new String[]{null});
        }

        tableList.setModel(model);

        funcionesBotones();
        disenoTabla();
        
    }
    
    
    private void botonPegarLista() {
        btnPegarLista.addActionListener((e) -> {

            pasteClipboard();

        });

    }
    private void pegarConTeclas() {

        //aqui creamos un objeto tipo KeyStroke para indicar cuales teclas son las que se debe presionar
        KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK, false);

        //aqui le indicamos que cuando la tabla se ponga en el foco
        InputMap imap = tableList.getInputMap(JComponent.WHEN_FOCUSED);
        //le indique que puede recibir la combinacion de teclas anteriormente creado y le asigna un nombre "paste"
        imap.put(paste, "paste");

        //aqui le asignamos a la la accion que debe tomar cuando reciba el input, en este caso la accion es "pegarDatosEnColumnas"
        ActionMap amap = tableList.getActionMap();
        amap.put("paste", pegarDatosEnColumnas);

    }

    
    //Esta es la accion que se prepara para ejecutarse cuando la tabla reciba la combinacion de teclas asignada en el metodo "pegarConTeclas"
    AbstractAction pegarDatosEnColumnas = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pasteClipboard();
        }

    };
   
    
    
    //este el metodo encargado de realizar el pegado
    private void pasteClipboard() {

        limpiarDatosTabla();

        try {

            //aqui creamos un objeto del tipo Clipboard para tomar el portapapeles de nuestro sistema operativo
            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();

            //Aqui creamos un objeto de tipo Transferable el cual sirve para tomar la informacion que esta guardada 
            //en el portapapeles del sistema y toma la informacion para si
            Transferable tran = c.getContents(null);

            //Aqui le transferimos la informacion de tipo texto  tomada del portapapeles a un objeto del tipo Object "data"
            Object data = tran.getTransferData(DataFlavor.stringFlavor);

            //si la informacion no es nula entonces inicia el proceso de pegado a continuacion
            if (data != null) {

                //Hace un cast de la variable data a un tipo String
                String dataString = (String) data;

                //Ya que los datos se transfieren como uno solo, es necesario fragmentarlos usando el salto de linea
                String[] listaPegar = dataString.split("\n");

                //una vez listo el arrelgo de datos procedemos a pasar los datos a la tabla usando un ciclo for    
                for (int i = 0; i < listaPegar.length; i++) {

                    //si la cantidad de datos en el arreglo excede la cantidad de filas de la tabla entonces agrega las que faltan
                    if (i > tableList.getRowCount() - 1) {

                        ((LyraTableModel2) tableList.getModel()).addRow(new String[]{listaPegar[i]});

                        //si la el arreglo no excede las filas solamente pega los datos y fin
                    } else {
                        tableList.setValueAt(listaPegar[i], i, 1);
                    }

                }
            } else {
                notifactionMessages(NOT.msg(NOT.CLIPBOARD_NO_DATA), TypeMessage.WARNING);
            }

        } catch (UnsupportedFlavorException | IOException ex) {
            Logger.getLogger(AddList.class.getName()).log(Level.SEVERE, null, ex);
        }
        contarRegistros();
    }
    private void limpiarDatosTabla() {

        int filas = tableList.getRowCount();

        if (filas > 0) {
            for (int i = 0; i < filas; i++) {
                tableList.setValueAt(null, i, 1);
            }
        }
    }
    
    
    
    private List<String> lista;
    private JTextField textfield;
    
    public void setList(List<String> lista, JTextField textfield){
        this.lista = lista;
        this.textfield = textfield;
        
        fillTableIfThereIsData();
    }
    private void fillTableIfThereIsData() {

        limpiarDatosTabla();

            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {

                    if (i > tableList.getRowCount() - 1) {
                        ((LyraTableModel2) tableList.getModel()).addRow(new String[]{lista.get(i)});
                    } else {
                        tableList.setValueAt(lista.get(i), i, 1);
                    }
                }
        }

        contarRegistros();

    }
    private void contarRegistros(){

        int cant = 0;
        int datos = tableList.getRowCount();

        for (int i = 0; i < datos; i++) {

            if (tableList.getValueAt(i, 1) != null) {
                cant+=1;
            }

        }
        labelConteoRegistro.setText(cant+" "+NOT.msg(NOT.ENTRIES_FOUND));
    
    }
    private void transferDataListaOrdenes() {
        lista.clear();
        int datos = tableList.getRowCount();

        for (int i = 0; i < datos; i++) {

            if (tableList.getValueAt(i, 1) != null) {
                if (i == 0) {
                    textfield.setText(tableList.getValueAt(i, 1).toString());
                }
                lista.add(tableList.getValueAt(i, 1).toString());
            }

        }

    }
    private void borrarDatos(){
        
        btnBorrar.addActionListener((e)->{
            
            int filas = tableList.getRowCount();
            
            if(filas>0){
                for(int i =0; i< filas;i++){
                    tableList.setValueAt(null, i, 1);
                }
                
            }
            
            if(lista!=null){
                if(!lista.isEmpty()){
                    lista.clear();
                }
                
            }
            textfield.setText(null);
            contarRegistros();
        });
        
        
    }
    
    
    private void botonOk() {
        btnSeleccionar.addActionListener((ActionEvent e) -> {
            transferDataListaOrdenes();
            dispose();
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

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_CornerPainted1 = new com.simplecore.erp.gui.components.panels.JPanelCornerPainted();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new com.simplecore.erp.gui.components.tables.lastversion.LyraTable();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnSeleccionar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnPegarLista = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnBorrar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelMenu = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnExit = new com.simplecore.erp.gui.components.labels.JButtonCustom();
        title = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        labelConteoRegistro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 146, 229));
        setModal(true);
        setUndecorated(true);

        jPanel_CornerPainted1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_CornerPainted1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_CornerPainted1.setAlto(15);
        jPanel_CornerPainted1.setAncho(15);

        tableList.setBackground(new java.awt.Color(249, 249, 249));
        tableList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableList.setColorOnEdit(new java.awt.Color(255, 255, 255));
        tableList.setColorTextOnEdit(new java.awt.Color(0, 0, 153));
        tableList.setFontStyle(com.simplecore.erp.gui.components.tables.lastversion.FontStyle.Bold);
        jScrollPane1.setViewportView(tableList);

        jPanel_Rounded_Corners_Degradado1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado1.setColor1(new java.awt.Color(229, 235, 244));
        jPanel_Rounded_Corners_Degradado1.setColor2(new java.awt.Color(229, 235, 244));

        btnSalir.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close.png"))); // NOI18N

        btnSeleccionar.setBackground(new java.awt.Color(226, 210, 144));
        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/ok_icon.png"))); // NOI18N

        btnPegarLista.setBackground(new java.awt.Color(226, 210, 144));
        btnPegarLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/paste.png"))); // NOI18N

        btnBorrar.setBackground(new java.awt.Color(226, 210, 144));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/erase.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado1Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado1);
        jPanel_Rounded_Corners_Degradado1.setLayout(jPanel_Rounded_Corners_Degradado1Layout);
        jPanel_Rounded_Corners_Degradado1Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnPegarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado1Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnPegarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelMenu.setColor1(new java.awt.Color(0, 146, 229));
        panelMenu.setColor2(new java.awt.Color(0, 146, 229));

        btnExit.setBackground(new java.awt.Color(0, 146, 229));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close_window.png"))); // NOI18N
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
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel_CornerPainted1Layout = new javax.swing.GroupLayout(jPanel_CornerPainted1);
        jPanel_CornerPainted1.setLayout(jPanel_CornerPainted1Layout);
        jPanel_CornerPainted1Layout.setHorizontalGroup(
            jPanel_CornerPainted1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_CornerPainted1Layout.createSequentialGroup()
                .addComponent(labelConteoRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(153, 153, 153))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel_CornerPainted1Layout.setVerticalGroup(
            jPanel_CornerPainted1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CornerPainted1Layout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(labelConteoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel_CornerPainted1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel_CornerPainted1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnBorrar;
    private com.simplecore.erp.gui.components.labels.JButtonCustom btnExit;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnPegarLista;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSeleccionar;
    private com.simplecore.erp.gui.components.panels.JPanelCornerPainted jPanel_CornerPainted1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelConteoRegistro;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelMenu;
    private com.simplecore.erp.gui.components.tables.lastversion.LyraTable tableList;
    private com.simplecore.erp.gui.components.labels.JLabelHQ title;
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
        
        btnExit.addActionListener((e)->{
            dispose();
        });

    }
    private void setResizebleWindow(){
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
    
    private void countEntriesStopEditing(){
        tableList.getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener(){
            @Override
            public void editingStopped(ChangeEvent e) {
                contarRegistros();
            }

            @Override
            public void editingCanceled(ChangeEvent e) {
                contarRegistros();
            }
            
        });
    }
    
    
    private void setDateAtTableChooser(){
        ListDateEvent e = new ListDateEvent(){
            @Override
            public void setDate(int row, String date) {
                tableList.setValueAt(date, row, 1);
            }

            
        };
        tableList.getColumnModel().getColumn(1).setCellEditor(new CeldasTipoFecha(e));
    }
    public void setDataTypeForTable(DataTypeForTable type){

//        switch (type) {
//
//            case DataTypeForTable.StringType ->  {
//                
//                tableList.getColumnModel().getColumn(1).setCellEditor(new CeldasTipoTexto());
//                tableList.addKeyListener(new KeyAdapter(){
//                @Override
//                public void keyTyped(KeyEvent e) {
//                    char c = e.getKeyChar();
//                    if (!Character.isLetter(c)) {
//                        e.consume();
//                        new Sounds().PlaySound(SoundsList.INTRODUCIR_CONTRASENA.getAudio());
//                    }
//                }
//
//                });
//            }
//            case DataTypeForTable.NumberType -> {
//                tableList.getColumnModel().getColumn(1).setCellEditor(new CeldasTipoNumero());
//                tableList.addKeyListener(new KeyAdapter() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        char c = e.getKeyChar();
//                        if (Character.isLetter(c)) {
//                            e.consume();
//                            new Sounds().PlaySound(SoundsList.INTRODUCIR_CONTRASENA.getAudio());
//                        }
//                    }
//
//                });
//            }
//            case DataTypeForTable.DateType ->  {
//                setDateAtTableChooser();                
//            }
//
//        }
//        countEntriesStopEditing();
    }

    
    public enum DataTypeForTable{    
        StringType,
        NumberType,
        DateType        
    }   
    
    
    private class CeldasTipoNumero extends DefaultCellEditor {

        public CeldasTipoNumero() {
            super(new JTextField());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            table.setSurrendersFocusOnKeystroke(true);
            Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);
            comp.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (Character.isLetter(c)) {
                        e.consume();
                    }
                }

            });

            return comp;
        }
    }
    private class CeldasTipoFecha extends DefaultCellEditor {

        private ListDateEvent e;
        public CeldasTipoFecha(ListDateEvent e) {
            
            super(new JTextField());
            this.e = e;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            JDateChooserCustom dcc = new JDateChooserCustom();
            dcc.setDateAtTable(e, row);
            
            return dcc;
        }
    }
    private class CeldasTipoTexto extends DefaultCellEditor {

        public CeldasTipoTexto() {
            super(new JTextField());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            table.setSurrendersFocusOnKeystroke(true);
            Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);
            comp.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isLetter(c)) {
                        e.consume();
                    }
                }

            });

            return comp;
        }
    }

}

