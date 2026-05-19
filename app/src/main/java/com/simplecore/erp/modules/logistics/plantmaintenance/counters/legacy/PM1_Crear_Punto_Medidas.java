package com.simplecore.erp.modules.logistics.plantmaintenance.counters.legacy;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.legacy.Invoke_JMenuBars;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares.Lista_Equipos;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.treeMenus;


public class PM1_Crear_Punto_Medidas extends javax.swing.JPanel {
    
    
    public PM1_Crear_Punto_Medidas() {
        initComponents();    
        addEvents();     
    }

    private void addEvents(){
        
        objetosPuntoMedida();
        abrirListaCategorias();
        abrirListaEquipos();
        
        objetoPuntoMedidaEscrito();
        buscarEquipoEscrito();
        categoriaPuntoMedidaEscrito();
        
        botonProceder();
        btnSalir();
    
    }
    
    
    private void objetosPuntoMedida(){
        sbObjetosPuntoMedida.getButton().addActionListener((e)->{
          
            Lista_Objetos_Punto_Medida listaObjetosPM = new Lista_Objetos_Punto_Medida(getSuperFrame());
            listaObjetosPM.setCampos(sbObjetosPuntoMedida.getTextBox(), descripcionObjetosPM);
            listaObjetosPM.setVisible(true);
            
        });
    }
    private void abrirListaEquipos(){
        
        sbEquipos.getButton().addActionListener((e)->{
          
            Lista_Equipos le = new Lista_Equipos();
            le.setPanelAnterior(this);
            le.setCampos(sbEquipos.getTextBox(), descripcionEquipoLB, null, null, null);
            
            
            PanelLoader.loadPanel(le, mainContainerPanel);
            
        });
        
    }
    private void abrirListaCategorias(){
        
        sbTipoPuntoMedida.getButton().addActionListener((e)->{
        
            Lista_Categorias_Punto_Medida  lc = new Lista_Categorias_Punto_Medida(getSuperFrame());
            lc.setCampos(sbTipoPuntoMedida.getTextBox(), descripcionCatLabel);
            lc.setVisible(true);
            
        });
        
    }
    
    
    private void objetoPuntoMedidaEscrito() {

        sbObjetosPuntoMedida.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    objetosPM();
                }
            }

        });

    }
    private void objetosPM() {

        if (!sbObjetosPuntoMedida.getTextBox().getText().isEmpty()) {
            
            String toUpperCase = sbObjetosPuntoMedida.getTextBox().getText().toUpperCase();
            sbObjetosPuntoMedida.getTextBox().setText(toUpperCase);
            
            String code = sbObjetosPuntoMedida.getTextBox().getText();
            
            if (code.equals(Objetos_Punto_Medida.IBD.getCode())) {
             
                descripcionObjetosPM.setText(Objetos_Punto_Medida.IBD.getDescOPM());
                objetoPuntoMedida = Objetos_Punto_Medida.IBD.getCode();
            
            } else if (code.equals(Objetos_Punto_Medida.IEQ.getCode())) {
            
                descripcionObjetosPM.setText(Objetos_Punto_Medida.IEQ.getDescOPM());
                objetoPuntoMedida = Objetos_Punto_Medida.IEQ.getCode();
                
            } else if (code.equals(Objetos_Punto_Medida.IFL.getCode())) {
               
                descripcionObjetosPM.setText(Objetos_Punto_Medida.IFL.getDescOPM());
                objetoPuntoMedida = Objetos_Punto_Medida.IFL.getCode();
                
            } else if (code.equals(Objetos_Punto_Medida.IME.getCode())) {
            
                descripcionObjetosPM.setText(Objetos_Punto_Medida.IME.getDescOPM());
                objetoPuntoMedida = Objetos_Punto_Medida.IME.getCode();
            
            } else {

                notificacion(NOT.msg(NOT.CODE_DOES_NOT_EXIST), TypeMessage.ERROR);
                descripcionObjetosPM.setText(null);
                objetoPuntoMedida = null;

            }
        } else {
            descripcionObjetosPM.setText(null);
            objetoPuntoMedida = null;
        }

    }

    
    private void buscarEquipoEscrito(){
        
        sbEquipos.getTextBox().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    equipo();
                }
            }
            
        });
        
    }
    private void equipo() {

        if (!sbEquipos.getTextBox().getText().isEmpty()) {
            
            String code = sbEquipos.getTextBox().getText();
            
            if (Extraer_Equipo.equipmentExists(code)) {

                Extraer_Equipo ee = new Extraer_Equipo();
                ee.getData(code);

                codigoEquipo = ee.getEquipo();
                descripcionEquipo = ee.getDenominacion();
                
                sbEquipos.getTextBox().setText(codigoEquipo);
                descripcionEquipoLB.setText(descripcionEquipo);
                
                
            } else {

                notificacion(NOT.msg(NOT.EQUIPMENT_DOES_NOT_EXIST), TypeMessage.ERROR);
                descripcionEquipoLB.setText(null);

                codigoEquipo = null;
                descripcionEquipo = null;
            }
        } else {
            codigoEquipo = null;
            descripcionEquipo = null;
        }

    }
    
    
    private void categoriaPuntoMedidaEscrito() {
        sbTipoPuntoMedida.getTextBox().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tipoPuntoMedida();
                }
            }
        });
    }
    private void tipoPuntoMedida() {

        if (!sbTipoPuntoMedida.getTextBox().getText().isEmpty()) {
            
            String toUpperCase = sbTipoPuntoMedida.getTextBox().getText().toUpperCase();
            sbTipoPuntoMedida.getTextBox().setText(toUpperCase);
            
            String code = sbTipoPuntoMedida.getTextBox().getText();

            if (code.equals(MeasurePointTypes.K.getCodigo())) {
        
                descripcionCatLabel.setText(MeasurePointTypes.K.getDescriptionCatPM());
                tipoPuntoMedida = MeasurePointTypes.K.getCodigo();
                
            } else if (code.equals(MeasurePointTypes.L.getCodigo())) {
            
                descripcionCatLabel.setText(MeasurePointTypes.L.getDescriptionCatPM());
                tipoPuntoMedida = MeasurePointTypes.L.getCodigo();
            
            } else if (code.equals(MeasurePointTypes.M.getCodigo())) {
            
                descripcionCatLabel.setText(MeasurePointTypes.M.getDescriptionCatPM());
                tipoPuntoMedida = MeasurePointTypes.M.getCodigo();
            
            } else if (code.equals(MeasurePointTypes.U.getCodigo())) {
            
                descripcionCatLabel.setText(MeasurePointTypes.U.getDescriptionCatPM());
                tipoPuntoMedida = MeasurePointTypes.U.getCodigo();
            
            } else {
                notificacion(NOT.msg(NOT.CODE_DOES_NOT_EXIST), TypeMessage.ERROR);
                descripcionCatLabel.setText(null);
                tipoPuntoMedida = null;
            }
        } else {
            descripcionCatLabel.setText(null);
            tipoPuntoMedida = null;
        }

    }
    
    private void esContador(){
        esContador = cbEsContador.isSelected();
    }
    
    
    private String objetoPuntoMedida;
    private String codigoEquipo;
    private String descripcionEquipo;
    private String tipoPuntoMedida;
    private boolean esContador;
    
    private boolean camposLlenos() {

        boolean estanLlenos = false;

        if (!(sbObjetosPuntoMedida.getTextBox().getText().isEmpty()
                | descripcionObjetosPM.getText()==null
                | sbEquipos.getTextBox().getText().isEmpty()
                | descripcionEquipoLB.getText()==null
                | sbTipoPuntoMedida.getTextBox().getText().isEmpty()
                | descripcionCatLabel.getText()==null)) {

            estanLlenos = true;
        } else {
            estanLlenos = false;
        }
        return estanLlenos;
    }
    
    
    private void botonProceder() {
        cmdProceder.addActionListener((e) -> {

            objetosPM();
            equipo();
            tipoPuntoMedida();
            esContador();

            if (camposLlenos()) {

                PM1_Creacion_Punto_Medidas pm1 = new PM1_Creacion_Punto_Medidas();
                pm1.setPanelAnterior(this);

                pm1.setObjetoPuntoMedida(objetoPuntoMedida);
                pm1.setCodigoCatalogoPuntoMedida(tipoPuntoMedida);
                pm1.setDescripcionCategoria(descripcionCatLabel.getText());
                pm1.setCodigoEquipo(codigoEquipo);
                pm1.setDescripcionEquipo(descripcionEquipo);
                pm1.setEsContador(esContador);
                
                pm1.setValoresEnCampos();

                disableComponent();
                
                PanelLoader.loadPanel(pm1, mainContainerPanel);
                
            }else{
                notificacion(NOT.msg(NOT.INCOMPLETE_FIELDS), TypeMessage.ERROR);
            }

        });
    }
    
    private void disableComponent(){

        cmdProceder.setEnabled(false);
        sbObjetosPuntoMedida.getTextBox().setEditable(false);
        sbObjetosPuntoMedida.getButton().setEnabled(false);
        sbEquipos.getTextBox().setEditable(false);
        sbEquipos.getButton().setEnabled(false);
        sbTipoPuntoMedida.getTextBox().setEditable(false);
        sbTipoPuntoMedida.getButton().setEnabled(false);
        cbEsContador.setEnabled(false);
        
        
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar_PM1 = new javax.swing.JMenuBar();
        MENU_PUNTO_MEDIDA = new javax.swing.JMenu();
        MENU_ITEM_GUARDAR = new javax.swing.JMenuItem();
        separador = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_NUEVO = new javax.swing.JMenuItem();
        MENU_ITEM_MODIFICAR = new javax.swing.JMenuItem();
        MENU_ITEM_VISUALIZAR = new javax.swing.JMenuItem();
        separador1 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_EQUIPOS = new javax.swing.JMenuItem();
        separador3 = new javax.swing.JPopupMenu.Separator();
        MENU_ITEM_SALIR = new javax.swing.JMenuItem();
        MENU_CARACTERISTICAS = new javax.swing.JMenu();
        MENU_ITEM_CARACTERISTICAS = new javax.swing.JMenuItem();
        MENU_AYUDA = new javax.swing.JMenu();
        MENU_ITEM_MANUAL = new javax.swing.JMenuItem();
        MENU_ITEM_ACERCA_DE = new javax.swing.JMenuItem();
        panelBotonera = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        cmdSalir = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        cmdProceder = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        panelFondo = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        equipoLabel = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sbEquipos = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        descripcionEquipoLB = new javax.swing.JLabel();
        denominationLabel = new javax.swing.JLabel();
        objetosPuntoMedidaLabel = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sbObjetosPuntoMedida = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        tipoPuntoMedidaLabel = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        sbTipoPuntoMedida = new com.simplecore.erp.gui.components.searchbox.JSearchBox();
        descripcionObjetosPM = new javax.swing.JLabel();
        cbEsContador = new javax.swing.JCheckBox();
        descripcionCatLabel = new javax.swing.JLabel();

        MENU_PUNTO_MEDIDA.setText("Punto de Medida");

        MENU_ITEM_GUARDAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_GUARDAR.setText("Guardar Punto Medida");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_GUARDAR);
        MENU_PUNTO_MEDIDA.add(separador);

        MENU_ITEM_NUEVO.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_NUEVO.setText("Nuevo punto medida");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_NUEVO);

        MENU_ITEM_MODIFICAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_MODIFICAR.setText("Modificar punto medida");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_MODIFICAR);

        MENU_ITEM_VISUALIZAR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_VISUALIZAR.setText("Visualizar punto medida");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_VISUALIZAR);
        MENU_PUNTO_MEDIDA.add(separador1);

        MENU_ITEM_EQUIPOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_EQUIPOS.setText("Equipos");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_EQUIPOS);
        MENU_PUNTO_MEDIDA.add(separador3);

        MENU_ITEM_SALIR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        MENU_ITEM_SALIR.setText("Salir");
        MENU_PUNTO_MEDIDA.add(MENU_ITEM_SALIR);

        menuBar_PM1.add(MENU_PUNTO_MEDIDA);

        MENU_CARACTERISTICAS.setText("Caracteristicas");

        MENU_ITEM_CARACTERISTICAS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        MENU_ITEM_CARACTERISTICAS.setText("Ver caracteristicas");
        MENU_CARACTERISTICAS.add(MENU_ITEM_CARACTERISTICAS);

        menuBar_PM1.add(MENU_CARACTERISTICAS);

        MENU_AYUDA.setText("Ayuda");

        MENU_ITEM_MANUAL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MENU_ITEM_MANUAL.setText("Manual Instrucciones");
        MENU_AYUDA.add(MENU_ITEM_MANUAL);

        MENU_ITEM_ACERCA_DE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        MENU_ITEM_ACERCA_DE.setText("Acercar de Puntos Medida");
        MENU_AYUDA.add(MENU_ITEM_ACERCA_DE);

        menuBar_PM1.add(MENU_AYUDA);

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        panelBotonera.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBotonera.setColor1(new java.awt.Color(202, 216, 237));
        panelBotonera.setColor2(new java.awt.Color(202, 216, 237));

        cmdSalir.setBackground(new java.awt.Color(226, 210, 144));
        cmdSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cmdSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

        cmdProceder.setBackground(new java.awt.Color(226, 210, 144));
        cmdProceder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cmdProceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/ok_icon.png"))); // NOI18N

        javax.swing.GroupLayout panelBotoneraLayout = new javax.swing.GroupLayout(panelBotonera);
        panelBotonera.setLayout(panelBotoneraLayout);
        panelBotoneraLayout.setHorizontalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(cmdProceder, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBotoneraLayout.setVerticalGroup(
            panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotoneraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdProceder, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFondo.setColor1(new java.awt.Color(246, 250, 253));
        panelFondo.setColor2(new java.awt.Color(202, 216, 237));

        equipoLabel.setText("Equipment");
        equipoLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        descripcionEquipoLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionEquipoLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionEquipoLB.setText(" ");
        descripcionEquipoLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        denominationLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        denominationLabel.setText("Description");

        objetosPuntoMedidaLabel.setText("Obj.measu.point");
        objetosPuntoMedidaLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        tipoPuntoMedidaLabel.setText("Meas. pt cat.");
        tipoPuntoMedidaLabel.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        descripcionObjetosPM.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionObjetosPM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionObjetosPM.setText(" ");
        descripcionObjetosPM.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        cbEsContador.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        cbEsContador.setText("MeasPoint is counter");
        cbEsContador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        descripcionCatLabel.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionCatLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionCatLabel.setText(" ");
        descripcionCatLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(objetosPuntoMedidaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equipoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(denominationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tipoPuntoMedidaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbEsContador)
                    .addComponent(descripcionEquipoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(sbObjetosPuntoMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descripcionObjetosPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(sbTipoPuntoMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descripcionCatLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(473, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(objetosPuntoMedidaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbObjetosPuntoMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionObjetosPM, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(equipoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sbEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(descripcionEquipoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(denominationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(sbTipoPuntoMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoPuntoMedidaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionCatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cbEsContador)
                .addContainerGap(329, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotonera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MENU_AYUDA;
    public static javax.swing.JMenu MENU_CARACTERISTICAS;
    public static javax.swing.JMenuItem MENU_ITEM_ACERCA_DE;
    public static javax.swing.JMenuItem MENU_ITEM_CARACTERISTICAS;
    public static javax.swing.JMenuItem MENU_ITEM_EQUIPOS;
    public static javax.swing.JMenuItem MENU_ITEM_GUARDAR;
    public static javax.swing.JMenuItem MENU_ITEM_MANUAL;
    public static javax.swing.JMenuItem MENU_ITEM_MODIFICAR;
    public static javax.swing.JMenuItem MENU_ITEM_NUEVO;
    public static javax.swing.JMenuItem MENU_ITEM_SALIR;
    public static javax.swing.JMenuItem MENU_ITEM_VISUALIZAR;
    public static javax.swing.JMenu MENU_PUNTO_MEDIDA;
    private javax.swing.JCheckBox cbEsContador;
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdProceder;
    private com.simplecore.erp.gui.components.labels.JButtonHQ cmdSalir;
    private javax.swing.JLabel denominationLabel;
    public static javax.swing.JLabel descripcionCatLabel;
    public static javax.swing.JLabel descripcionEquipoLB;
    public static javax.swing.JLabel descripcionObjetosPM;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined equipoLabel;
    public static javax.swing.JMenuBar menuBar_PM1;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined objetosPuntoMedidaLabel;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelBotonera;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient panelFondo;
    protected static com.simplecore.erp.gui.components.searchbox.JSearchBox sbEquipos;
    protected static com.simplecore.erp.gui.components.searchbox.JSearchBox sbObjetosPuntoMedida;
    protected static com.simplecore.erp.gui.components.searchbox.JSearchBox sbTipoPuntoMedida;
    private javax.swing.JPopupMenu.Separator separador;
    private javax.swing.JPopupMenu.Separator separador1;
    private javax.swing.JPopupMenu.Separator separador3;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined tipoPuntoMedidaLabel;
    // End of variables declaration//GEN-END:variables
 
    
    
    private void btnSalir() {
        cmdSalir.addActionListener((e) -> {

            Invoke_JMenuBars.setMenuBar(getSuperFrame(), getSuperFrame().getJMenuBar(),
                    LyraWorkspace.barMenu);

            PanelLoader.loadPanel(treeMenus, mainContainerPanel);

        });
    }
    private JFrame getSuperFrame() {
        return (JFrame) SwingUtilities.getRoot(mainContainerPanel);
    }

    private void notificacion(String msg, TypeMessage type) {

        new SystemMessages(LyraWorkspace.NotificationLabel, msg, type);
     

    }

    
}
