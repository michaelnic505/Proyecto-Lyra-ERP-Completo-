package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.simplecore.erp.gui.notifications.SystemMessages;
import com.simplecore.erp.gui.notifications.TypeMessage;
import com.simplecore.erp.gui.workspace.legacy.FullUserName;
import com.simplecore.erp.gui.workspace.LyraFrame;
import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.controllers.gui.PanelLoader;
import com.simplecore.erp.utils.notifications.NOT;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.Check_Modification_Permission;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.StatusOrder;
import com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas.aprobacion_ordenes.O05_Lista_Ordenes_Aprobacion;
import static com.simplecore.erp.gui.workspace.LyraWorkspace.mainContainerPanel;

public class O04_Detalle_Orden extends javax.swing.JPanel {

    JFrame frame;
    String numeroOrden;
    JPanel thisPanel = this;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy.MM.dd");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
    List<String> listaOrdenes;
    String titulos; 
    private String username;
    private String fullname;
    
    
    public O04_Detalle_Orden(String username) {
        this.username=username;
//        this.fullname=FullUserName.getName(username);
        initComponents();
        addEvents();
        titulos = LyraWorkspace.TitleLabel.getText();
    }

    private void removeJMenuBar() {
        frame = (JFrame) SwingUtilities.getWindowAncestor(mainContainerPanel);
        frame.remove(frame.getJMenuBar());
    }

    private void addEvents() {

        removeJMenuBar();
        btnAprobar.addActionListener(aprobacionIndividual);
        btnRechazar.addActionListener(rechazoIndividual);        
        btnSalir();

    }

    
    
    private JPanel panelAnterior;
    public void setPanelAnterior(JPanel data) {
        panelAnterior = data;
    }

    
    
    
    public void numeroOrdenTB(String data) {
        numeroOrdenTB.setText(data);
        numeroOrden = data;

        
        LyraWorkspace.TitleLabel.setText(titulos + ": " + numeroOrden);

    }
    public void codigoEstatusTB(String data) {
        codigoEstatusTB.setText(data);

    }
    public void descripcionEstatusTB(String data) {
        descripcionEstatusTB.setText(data);

    }
    public void tituloOrdenTB(String data) {
        tituloOrdenTB.setText(data);

    }
    public void descripcionExtendidaOrdenTB(String data) {
        descripcionExtendidaOrdenTB.setText(data);

    }
    public void codigoEquipoTB(String data) {
        codigoEquipoTB.setText(data);

    }
    public void descripcionEquipoLB(String data) {
        descripcionEquipoLB.setText(data);

    }
    public void codigoUbicacionTB(String data) {
        codigoUbicacionTB.setText(data);

    }
    public void descripcionUbicacionLB(String data) {
        descripcionUbicacionLB.setText(data);

    }
    public void centroCostosTB(String data) {
        centroCostosTB.setText(data);
    }
    public void descripcionCentroCosto(String data) {
        descripcionCentroCosto.setText(data);

    }
    public void labelCostoEstimado(String data) {
        labelCostoEstimado.setText(data);

    }
    public void labelHorasEstimadas(String data) {
        labelHorasEstimadas.setText(data);

    }
    public void descripcionTipoMttoTB(String data) {
        descripcionTipoMttoTB.setText(data);

    }
    public void descripcionClaseOrdenTB(String data) {
        descripcionClaseOrdenTB.setText(data);

    }
    public void descripcionCriticidadTB(String data) {
        descripcionCriticidadTB.setText(data);

    }
    public void descripcionSistemasTB(String data) {
        descripcionSistemasTB.setText(data);

    }
    public void descripcionComponenteTB(String data) {
        descripcionComponenteTB.setText(data);

    }
    public void descripcionSintomaTB(String data) {
        descripcionSintomaTB.setText(data);

    }
    public void labelUsuarioPlaneador1(String data) {
        labelUsuarioPlaneador1.setText(data);

    }
    public void codigoSolicitanteTB(String data) {
        codigoSolicitanteTB.setText(data);

    }
    public void descripcionSolicitanteLB(String data) {
        descripcionSolicitanteLB.setText(data);

    }
    public void codigoResponsableTB(String data) {
        codigoResponsableTB.setText(data);
    }
    public void descripcionResponsableLB(String data) {
        descripcionResponsableLB.setText(data);

    }

    
    public void revisionPorLista(List<String> lista) {
        listaOrdenes = lista;
    }

    ActionListener aprobacionIndividual = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST4_ORDER_APPROVED.getStatusCode())) {

                Object[] options = {"Proceed to approval", "Cancel"};

                int r = JOptionPane.showOptionDialog(null, NOT.msg(NOT.PROCEED_WITH_APPROVAL),
                        NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (r == JOptionPane.YES_OPTION) {

                    aprobarOrder(numeroOrden);
                    notifications(NOT.msg(NOT.ORDER_APPROVED) + ": " + numeroOrden, TypeMessage.SUCCESS);
                    
                    
                    if (isMassive) {
                        
                        indicadorOrden = indicadorOrden + 1;
                        
                        if (indicadorOrden < listaOrdenes.size()) {
                          
                            cargarDatosOrden(listaOrdenes.get(indicadorOrden));
                            numeroOrdenTB(listaOrdenes.get(indicadorOrden));
                    
                        }else{
                            
                            ((O05_Lista_Ordenes_Aprobacion)panelAnterior).recargarTabla();
                            
                            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
                        }
                        
                    } else {
                        PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
                    }

                } else {
                    notifications(NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.WARNING);
                }
            } else {
                notifications(NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.WARNING);
            }
            
            
        }

    };
    ActionListener rechazoIndividual = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Check_Modification_Permission.isPermitted(username, "O02", StatusOrder.ST9_ORDER_REJECTED.getStatusCode())) {

                Object[] options = {"Proceed to decline", "Cancel"};

                int r = JOptionPane.showOptionDialog(null, NOT.msg(NOT.PROCEED_WITH_REJECTION),
                        NOT.msg(NOT.TITLE), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (r == JOptionPane.YES_OPTION) {

                    rechazarOrder(numeroOrden);
                    notifications(NOT.msg(NOT.ORDER_REJECTED) + ": " + numeroOrden, TypeMessage.ERROR);
                   
                    if (isMassive) {

                        indicadorOrden = indicadorOrden + 1;

                        if (indicadorOrden < listaOrdenes.size()) {
                            
                            cargarDatosOrden(listaOrdenes.get(indicadorOrden));
                            numeroOrdenTB(listaOrdenes.get(indicadorOrden));
                        
                        } else {
                            
                            ((O05_Lista_Ordenes_Aprobacion)panelAnterior).recargarTabla();
                            PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
                        
                        }

                    } else {
                        PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
                    }

                } else {
                    notifications(NOT.msg(NOT.NO_ACTION_EXECUTED), TypeMessage.WARNING);

                }
            } else {
                notifications(NOT.msg(NOT.NO_PERMISSIONS), TypeMessage.WARNING);
            }

        }

    };

    
    
    private String codigoEstatus;
    private String descripcionEstatus;
    private String fechaAprobacion;
    private String horaAprobacion;
    private String aprobadaPor;

    private void setValues() {

        codigoEstatusTB.setText(codigoEstatus);
        descripcionEstatusTB.setText(descripcionEstatus);

        fechaAprobacion = formatoFecha.format(Calendar.getInstance().getTime());
        horaAprobacion = formatoHora.format(Calendar.getInstance().getTimeInMillis());

        aprobadaPor = fullname;
    }
    
    private void rechazarOrder(String order) {

        codigoEstatus = StatusOrder.ST9_ORDER_REJECTED.getStatusCode();
        descripcionEstatus = StatusOrder.ST9_ORDER_REJECTED.getDescription();

        setValues();

        Approve_Order_SQL reject = new Approve_Order_SQL();
        reject.setCodigoEstatus(codigoEstatus);
        reject.setDescripcionEstatus(descripcionEstatus);
        reject.setFechaAprobacion(fechaAprobacion);
        reject.setHoraAprobacion(horaAprobacion);
        reject.setAprobadaPor(aprobadaPor);
        reject.processOrder(order);

    }
    private void aprobarOrder(String order) {
        
        codigoEstatus = StatusOrder.ST4_ORDER_APPROVED.getStatusCode();
        descripcionEstatus = StatusOrder.ST4_ORDER_APPROVED.getDescription();

        setValues();

        Approve_Order_SQL approve = new Approve_Order_SQL();
        approve.setCodigoEstatus(codigoEstatus);
        approve.setDescripcionEstatus(descripcionEstatus);
        approve.setFechaAprobacion(fechaAprobacion);
        approve.setHoraAprobacion(horaAprobacion);
        approve.setAprobadaPor(aprobadaPor);
        approve.processOrder(order);

    }

    private boolean isMassive = false;
    public void isMassiveProccess(boolean isMassive){
        this.isMassive = isMassive;
    }
    
    int indicadorOrden = 0;

    
    public void cargarDatosOrden(String orden){
            
                   
            Load_Order_Fields_SQL o = new Load_Order_Fields_SQL();
            o.loadOrder(orden);
            
            numeroOrdenTB(orden);
            codigoEstatusTB(o.getCodigoEstatus());
            descripcionEstatusTB(o.getDescripcionEstatus());

            tituloOrdenTB(o.getTitulo());
            descripcionExtendidaOrdenTB(o.getTextoExtendido());
            codigoEquipoTB(o.getEquipo());
            descripcionEquipoLB(o.getDenominaconEquipo());
            codigoUbicacionTB(o.getUbicación());
            descripcionUbicacionLB(o.getDenominaconUbicación());
            centroCostosTB(o.getCentroCostos());
            descripcionCentroCosto(o.getDenominacionCC());
            labelCostoEstimado(o.getCostoEstimado());
            labelHorasEstimadas(o.getTiempoEstimado());
            descripcionTipoMttoTB(o.getTipo());
            descripcionClaseOrdenTB(o.getClase());
            descripcionCriticidadTB(o.getPrioridad());
            descripcionSistemasTB(o.getSistema());
            descripcionComponenteTB(o.getComponente());
            descripcionSintomaTB(o.getSintoma());
            labelUsuarioPlaneador1(o.getPlaneadoPor());

            codigoSolicitanteTB(o.getCodSolicitante());
            descripcionSolicitanteLB(o.getNombreSolicitante());
            codigoResponsableTB(o.getNombreResponsable());
            descripcionResponsableLB(o.getNombreResponsable());
    }
    
    
    
    
    private void btnSalir() {

        btnSalir_CrearOrden.addActionListener((e) -> {

            if (isMassive) {

                indicadorOrden = indicadorOrden + 1;

                if (indicadorOrden < listaOrdenes.size()) {
                    
                    cargarDatosOrden(listaOrdenes.get(indicadorOrden));
                    numeroOrdenTB(listaOrdenes.get(indicadorOrden));
                    
                } else {
                    PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
                }

            } else {
                PanelLoader.loadPanel(panelAnterior, mainContainerPanel);
            }

        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelScroll = new javax.swing.JScrollPane();
        bodyPanel = new javax.swing.JPanel();
        numeroOrdenLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        descripcionOrdenLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        numeroOrdenTB = new javax.swing.JTextField();
        tituloOrdenTB = new javax.swing.JTextField();
        estatusLB = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoEstatusTB = new javax.swing.JTextField();
        descripcionEstatusTB = new javax.swing.JTextField();
        scroll = new javax.swing.JScrollPane();
        descripcionExtendidaOrdenTB = new javax.swing.JTextArea();
        labelTipoMtto = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelClaseMtto = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCriticidad = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        descripcionTipoMttoTB = new javax.swing.JTextField();
        descripcionClaseOrdenTB = new javax.swing.JTextField();
        descripcionCriticidadTB = new javax.swing.JTextField();
        labelSistema = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelComponente = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelSintoma = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        descripcionSistemasTB = new javax.swing.JTextField();
        descripcionComponenteTB = new javax.swing.JTextField();
        descripcionSintomaTB = new javax.swing.JTextField();
        labelEquipo = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelUbicacion = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoUbicacionTB = new javax.swing.JTextField();
        codigoEquipoTB = new javax.swing.JTextField();
        descripcionEquipoLB = new javax.swing.JLabel();
        descripcionUbicacionLB = new javax.swing.JLabel();
        labelSolicitante = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelResponsable = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        codigoSolicitanteTB = new javax.swing.JTextField();
        codigoResponsableTB = new javax.swing.JTextField();
        descripcionSolicitanteLB = new javax.swing.JLabel();
        descripcionResponsableLB = new javax.swing.JLabel();
        labelTiempoEstimado = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelHorasEstimadas = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCostosEstimados = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCostoEstimado = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelCentroCostos = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        centroCostosTB = new javax.swing.JTextField();
        descripcionCentroCosto = new javax.swing.JLabel();
        labelUsuarioPlaneador = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        labelPlaneadaPor = new com.simplecore.erp.gui.components.labels.JLabelHQUnderlined();
        labelUsuarioPlaneador1 = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        jPanel_Rounded_Corners_Degradado6 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        btnSalir_CrearOrden = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnAprobar = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        btnRechazar = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        setPreferredSize(new java.awt.Dimension(1042, 550));
        setRequestFocusEnabled(false);

        bodyPanel.setBackground(new java.awt.Color(246, 250, 253));

        numeroOrdenLB.setText("Orden");

        descripcionOrdenLB.setText("Descripcion");

        numeroOrdenTB.setEditable(false);
        numeroOrdenTB.setBackground(new java.awt.Color(255, 204, 204));
        numeroOrdenTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        numeroOrdenTB.setBorder(null);

        tituloOrdenTB.setEditable(false);
        tituloOrdenTB.setBackground(new java.awt.Color(255, 204, 204));
        tituloOrdenTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        tituloOrdenTB.setBorder(null);

        estatusLB.setText("Estatus");

        codigoEstatusTB.setEditable(false);
        codigoEstatusTB.setBackground(new java.awt.Color(255, 204, 204));
        codigoEstatusTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoEstatusTB.setBorder(null);

        descripcionEstatusTB.setEditable(false);
        descripcionEstatusTB.setBackground(new java.awt.Color(255, 204, 204));
        descripcionEstatusTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionEstatusTB.setBorder(null);

        descripcionExtendidaOrdenTB.setEditable(false);
        descripcionExtendidaOrdenTB.setColumns(20);
        descripcionExtendidaOrdenTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionExtendidaOrdenTB.setLineWrap(true);
        descripcionExtendidaOrdenTB.setRows(5);
        descripcionExtendidaOrdenTB.setWrapStyleWord(true);
        descripcionExtendidaOrdenTB.setBorder(null);
        scroll.setViewportView(descripcionExtendidaOrdenTB);

        labelTipoMtto.setText("Tipo Mtto");

        labelClaseMtto.setText("Clase Orden");

        labelCriticidad.setText("Criticidad");

        descripcionTipoMttoTB.setEditable(false);
        descripcionTipoMttoTB.setBackground(new java.awt.Color(204, 204, 255));
        descripcionTipoMttoTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionTipoMttoTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        descripcionTipoMttoTB.setBorder(null);

        descripcionClaseOrdenTB.setEditable(false);
        descripcionClaseOrdenTB.setBackground(new java.awt.Color(204, 204, 255));
        descripcionClaseOrdenTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionClaseOrdenTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        descripcionClaseOrdenTB.setBorder(null);

        descripcionCriticidadTB.setEditable(false);
        descripcionCriticidadTB.setBackground(new java.awt.Color(204, 204, 255));
        descripcionCriticidadTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionCriticidadTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        descripcionCriticidadTB.setBorder(null);

        labelSistema.setText("Sistema");

        labelComponente.setText("Componente");

        labelSintoma.setText("Síntoma");

        descripcionSistemasTB.setEditable(false);
        descripcionSistemasTB.setBackground(new java.awt.Color(204, 204, 255));
        descripcionSistemasTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionSistemasTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        descripcionSistemasTB.setBorder(null);

        descripcionComponenteTB.setEditable(false);
        descripcionComponenteTB.setBackground(new java.awt.Color(204, 204, 255));
        descripcionComponenteTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionComponenteTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        descripcionComponenteTB.setBorder(null);

        descripcionSintomaTB.setEditable(false);
        descripcionSintomaTB.setBackground(new java.awt.Color(204, 204, 255));
        descripcionSintomaTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionSintomaTB.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        descripcionSintomaTB.setBorder(null);

        labelEquipo.setText("Equipo");

        labelUbicacion.setText("Location");

        codigoUbicacionTB.setEditable(false);
        codigoUbicacionTB.setBackground(new java.awt.Color(255, 255, 102));
        codigoUbicacionTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoUbicacionTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codigoUbicacionTB.setBorder(null);

        codigoEquipoTB.setEditable(false);
        codigoEquipoTB.setBackground(new java.awt.Color(255, 255, 102));
        codigoEquipoTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoEquipoTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codigoEquipoTB.setBorder(null);

        descripcionEquipoLB.setBackground(new java.awt.Color(255, 255, 102));
        descripcionEquipoLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionEquipoLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionEquipoLB.setText(" ");
        descripcionEquipoLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        descripcionEquipoLB.setOpaque(true);

        descripcionUbicacionLB.setBackground(new java.awt.Color(255, 255, 102));
        descripcionUbicacionLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionUbicacionLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionUbicacionLB.setText(" ");
        descripcionUbicacionLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        descripcionUbicacionLB.setOpaque(true);

        labelSolicitante.setText("Solicitante");

        labelResponsable.setText("Responsable");

        codigoSolicitanteTB.setEditable(false);
        codigoSolicitanteTB.setBackground(new java.awt.Color(238, 244, 254));
        codigoSolicitanteTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoSolicitanteTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codigoSolicitanteTB.setBorder(null);

        codigoResponsableTB.setEditable(false);
        codigoResponsableTB.setBackground(new java.awt.Color(238, 244, 254));
        codigoResponsableTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        codigoResponsableTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codigoResponsableTB.setBorder(null);

        descripcionSolicitanteLB.setBackground(new java.awt.Color(238, 244, 254));
        descripcionSolicitanteLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionSolicitanteLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionSolicitanteLB.setText(" ");
        descripcionSolicitanteLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        descripcionSolicitanteLB.setOpaque(true);

        descripcionResponsableLB.setBackground(new java.awt.Color(238, 244, 254));
        descripcionResponsableLB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionResponsableLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionResponsableLB.setText(" ");
        descripcionResponsableLB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        descripcionResponsableLB.setOpaque(true);

        labelTiempoEstimado.setBackground(new java.awt.Color(255, 255, 102));
        labelTiempoEstimado.setText("Tiempo Estimado (Hrs)");
        labelTiempoEstimado.setOpaque(true);

        labelHorasEstimadas.setBackground(new java.awt.Color(255, 255, 102));
        labelHorasEstimadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHorasEstimadas.setFont(new java.awt.Font("Roboto Light", 1, 13)); // NOI18N
        labelHorasEstimadas.setOpaque(true);

        labelCostosEstimados.setBackground(new java.awt.Color(102, 204, 255));
        labelCostosEstimados.setText("Costo Estimado ($)");
        labelCostosEstimados.setOpaque(true);

        labelCostoEstimado.setBackground(new java.awt.Color(102, 204, 255));
        labelCostoEstimado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCostoEstimado.setFont(new java.awt.Font("Roboto Light", 1, 13)); // NOI18N
        labelCostoEstimado.setOpaque(true);

        labelCentroCostos.setText("Cost Center");

        centroCostosTB.setEditable(false);
        centroCostosTB.setBackground(new java.awt.Color(204, 255, 255));
        centroCostosTB.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        centroCostosTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        centroCostosTB.setBorder(null);

        descripcionCentroCosto.setBackground(new java.awt.Color(153, 255, 255));
        descripcionCentroCosto.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        descripcionCentroCosto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        descripcionCentroCosto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        descripcionCentroCosto.setOpaque(true);

        labelUsuarioPlaneador.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        labelPlaneadaPor.setText("Planeada por");

        labelUsuarioPlaneador1.setBackground(new java.awt.Color(204, 204, 204));
        labelUsuarioPlaneador1.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        labelUsuarioPlaneador1.setOpaque(true);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addComponent(labelTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelHorasEstimadas, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(labelCostosEstimados, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCostoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelSolicitante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addComponent(codigoResponsableTB, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(descripcionResponsableLB, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addComponent(codigoSolicitanteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(descripcionSolicitanteLB, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(codigoEquipoTB, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(codigoUbicacionTB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descripcionEquipoLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descripcionUbicacionLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCriticidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelClaseMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelTipoMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(descripcionTipoMttoTB)
                            .addComponent(descripcionClaseOrdenTB, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descripcionCriticidadTB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descripcionSintomaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionComponenteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionSistemasTB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(scroll)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcionOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addComponent(numeroOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(estatusLB, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(codigoEstatusTB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descripcionEstatusTB))
                            .addComponent(tituloOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelPlaneadaPor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCentroCostos, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addComponent(centroCostosTB, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(descripcionCentroCosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(labelUsuarioPlaneador1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(252, Short.MAX_VALUE))
            .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bodyPanelLayout.createSequentialGroup()
                    .addGap(447, 447, 447)
                    .addComponent(labelUsuarioPlaneador, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(319, Short.MAX_VALUE)))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHorasEstimadas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCostosEstimados, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCostoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(centroCostosTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionCentroCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPlaneadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuarioPlaneador1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estatusLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoEstatusTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEstatusTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionOrdenLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelTipoMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionTipoMttoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionSistemasTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelClaseMtto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionClaseOrdenTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionComponenteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCriticidad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSintoma, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionCriticidadTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionSintomaTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoEquipoTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionEquipoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoUbicacionTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionUbicacionLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoSolicitanteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionSolicitanteLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoResponsableTB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionResponsableLB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bodyPanelLayout.createSequentialGroup()
                    .addGap(240, 240, 240)
                    .addComponent(labelUsuarioPlaneador, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(267, Short.MAX_VALUE)))
        );

        panelScroll.setViewportView(bodyPanel);

        jPanel_Rounded_Corners_Degradado6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado6.setColor1(new java.awt.Color(202, 216, 237));
        jPanel_Rounded_Corners_Degradado6.setColor2(new java.awt.Color(202, 216, 237));

        btnSalir_CrearOrden.setBackground(new java.awt.Color(226, 210, 144));
        btnSalir_CrearOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSalir_CrearOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/windows/close.png"))); // NOI18N

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
                .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnRechazar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado6Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Rounded_Corners_Degradado6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSalir_CrearOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRechazar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnAprobar;
    public static com.simplecore.erp.gui.components.labels.JButtonHQ btnRechazar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ btnSalir_CrearOrden;
    protected static javax.swing.JTextField centroCostosTB;
    protected static javax.swing.JTextField codigoEquipoTB;
    private javax.swing.JTextField codigoEstatusTB;
    protected static javax.swing.JTextField codigoResponsableTB;
    private javax.swing.JTextField codigoSolicitanteTB;
    protected static javax.swing.JTextField codigoUbicacionTB;
    public static javax.swing.JLabel descripcionCentroCosto;
    protected static javax.swing.JTextField descripcionClaseOrdenTB;
    protected static javax.swing.JTextField descripcionComponenteTB;
    protected static javax.swing.JTextField descripcionCriticidadTB;
    public static javax.swing.JLabel descripcionEquipoLB;
    private javax.swing.JTextField descripcionEstatusTB;
    private javax.swing.JTextArea descripcionExtendidaOrdenTB;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined descripcionOrdenLB;
    public static javax.swing.JLabel descripcionResponsableLB;
    protected static javax.swing.JTextField descripcionSintomaTB;
    protected static javax.swing.JTextField descripcionSistemasTB;
    private javax.swing.JLabel descripcionSolicitanteLB;
    protected static javax.swing.JTextField descripcionTipoMttoTB;
    public static javax.swing.JLabel descripcionUbicacionLB;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined estatusLB;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado6;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCentroCostos;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelClaseMtto;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelComponente;
    protected static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCostoEstimado;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCostosEstimados;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelCriticidad;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelEquipo;
    protected static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelHorasEstimadas;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelPlaneadaPor;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelResponsable;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSintoma;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSistema;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelSolicitante;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTiempoEstimado;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelTipoMtto;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined labelUbicacion;
    public static com.simplecore.erp.gui.components.labels.JLabelHQ labelUsuarioPlaneador;
    public static com.simplecore.erp.gui.components.labels.JLabelHQ labelUsuarioPlaneador1;
    public static com.simplecore.erp.gui.components.labels.JLabelHQUnderlined numeroOrdenLB;
    private javax.swing.JTextField numeroOrdenTB;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField tituloOrdenTB;
    // End of variables declaration//GEN-END:variables


    private void notifications(String msg, TypeMessage type) {

            new SystemMessages(LyraWorkspace.NotificationLabel, msg, type);

    }

}
