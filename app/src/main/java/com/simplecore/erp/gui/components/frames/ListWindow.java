package com.simplecore.erp.gui.components.frames;

import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

public class ListWindow extends javax.swing.JDialog {
    
    
    public ListWindow(Frame parent, JButton button) {
        
        super(parent, true);
        initComponents();
        addEvents();
        setMoveableWindow();
        setResizeableWindow();
        setBorders();
        setLocations(button);
        applyTranslations("en");
        setTableModel("en");
        SwingUtilities.invokeLater(() -> 
                autoFitWindowWidth(this, tableList, tableList.getWidth(), tableList.getHeight()));
        
    }
    
    private void setBorders(){
                MatteBorder matteBorder = new MatteBorder(1, 1, 1, 1, new Color(55,103,191)); // Borde gris
        getRootPane().setBorder(matteBorder);
    }
    private void setLocations(JButton button) {
        Point buttonLocation = button.getLocationOnScreen();
        setLocation(buttonLocation.x + 40, buttonLocation.y - 220);
    }

    private void addEvents() {
        extiButton();
        selectButton();
    }

    private void extiButton() {
        exitButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
        disposeButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
    }
    private void selectButton() {
        selectButton.addActionListener(e -> {

            int row = tableList.getSelectedRow();

            if (row != -1) {



                dispose();
            }

        });
    }
    
    private void applyTranslations(String language) {

    }

    private void setTableModel(String language) {


        String[] columns = switch (language) {
            case "es" ->
                new String[]{"", ""};
            case "en" ->
                new String[]{"", ""};
            case "fr" ->
                new String[]{"", ""};
            case "pt" ->
                new String[]{"", ""};
            default ->
                new String[]{"", ""};
        };
        
        int rows = 0;
        
        DynamicTableModel model = new DynamicTableModel(rows, columns);

        for (int i = 0; i < rows; i++) {
            model.setValueAt(null, i, 0);
            model.setCellEditable(i, 0, false);

            model.setValueAt(null, i, 1);
            model.setCellEditable(i, 1, false);
        }

        tableList.setModel(model);
        tableList.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableList.getColumnModel().getColumn(1).setPreferredWidth(150);

    }

    private void autoFitWindowWidth(JDialog frame, JTable table, int defaultWidth, int defaultHeight) {

        int columnCount = table.getColumnModel().getColumnCount();
        int totalColumnWidth = table.getColumnModel().getTotalColumnWidth();

        if (totalColumnWidth > defaultWidth) {
            int extraWidthPerColumn = (int) (defaultWidth * 0.1); // 10% del ancho predeterminado
            int columnsExceeding = columnCount - (defaultWidth / table.getColumnModel().getColumn(0).getWidth());
            int newWidth = defaultWidth + (columnsExceeding * extraWidthPerColumn);
            frame.setSize(newWidth, frame.getHeight());
        }

        // Ajustar la altura de la ventana según las filas
        int rowCount = table.getRowCount();
        int rowHeight = table.getRowHeight();
        int totalRowHeight = rowCount * rowHeight;

        if (totalRowHeight > defaultHeight) {
            int extraHeightPerRow = (int) (defaultHeight * 0.05); // 5% de la altura predeterminada
            int rowsExceeding = rowCount - (defaultHeight / rowHeight);
            int newHeight = defaultHeight + (rowsExceeding * extraHeightPerRow);
            frame.setSize(frame.getWidth(), newHeight);
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_CornerPainted1 = new com.simplecore.erp.gui.components.panels.JPanelCornerPainted();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        exitButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        selectButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        menuPanel = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        windowTitleLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        disposeButton = new com.simplecore.erp.gui.components.labels.JButtonCustom();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new com.simplecore.erp.gui.components.tables.newversions.DynamicTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 146, 229));
        setModal(true);
        setUndecorated(true);

        jPanel_CornerPainted1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_CornerPainted1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_CornerPainted1.setAlto(15);
        jPanel_CornerPainted1.setAncho(15);

        jPanel_Rounded_Corners_Degradado1.setBackground(new java.awt.Color(217, 215, 200));
        jPanel_Rounded_Corners_Degradado1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado1.setColor1(new java.awt.Color(217, 215, 200));
        jPanel_Rounded_Corners_Degradado1.setColor2(new java.awt.Color(217, 215, 200));

        exitButton.setBackground(new java.awt.Color(226, 210, 144));
        exitButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/error.png"))); // NOI18N

        selectButton.setBackground(new java.awt.Color(226, 210, 144));
        selectButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        selectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/chekOk.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado1Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado1);
        jPanel_Rounded_Corners_Degradado1.setLayout(jPanel_Rounded_Corners_Degradado1Layout);
        jPanel_Rounded_Corners_Degradado1Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado1Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        menuPanel.setColor1(new java.awt.Color(0, 146, 229));
        menuPanel.setColor2(new java.awt.Color(0, 146, 229));

        windowTitleLabel.setForeground(new java.awt.Color(248, 248, 248));
        windowTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        windowTitleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/acceso2.png"))); // NOI18N
        windowTitleLabel.setText("Title");
        windowTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        disposeButton.setBackground(new java.awt.Color(0, 146, 229));
        disposeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/components/buttonicons/cerrar.png"))); // NOI18N
        disposeButton.setBorderColor(new java.awt.Color(0, 146, 229));
        disposeButton.setColor(new java.awt.Color(0, 146, 229));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(windowTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(76, 76, 76)
                .addComponent(disposeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(disposeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tableList.setBackground(new java.awt.Color(146, 178, 193));
        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableList.setNotEditableCell(new java.awt.Color(221, 221, 221));
        tableList.setNotEditableCellSelection(new java.awt.Color(221, 174, 37));
        tableList.setNotEditableUniqueCellSelection(new java.awt.Color(221, 174, 37));
        tableList.setOpaque(false);
        jScrollPane1.setViewportView(tableList);

        javax.swing.GroupLayout jPanel_CornerPainted1Layout = new javax.swing.GroupLayout(jPanel_CornerPainted1);
        jPanel_CornerPainted1.setLayout(jPanel_CornerPainted1Layout);
        jPanel_CornerPainted1Layout.setHorizontalGroup(
            jPanel_CornerPainted1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel_CornerPainted1Layout.setVerticalGroup(
            jPanel_CornerPainted1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CornerPainted1Layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1)
                .addGap(19, 19, 19))
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
    private com.simplecore.erp.gui.components.labels.JButtonCustom disposeButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ exitButton;
    private com.simplecore.erp.gui.components.panels.JPanelCornerPainted jPanel_CornerPainted1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient menuPanel;
    private com.simplecore.erp.gui.components.labels.JButtonHQ selectButton;
    private com.simplecore.erp.gui.components.tables.newversions.DynamicTable tableList;
    private com.simplecore.erp.gui.components.labels.JLabelHQ windowTitleLabel;
    // End of variables declaration//GEN-END:variables

    private int pX;
    private int pY;

    private void setMoveableWindow() {
        // Manejador de MouseListener para capturar la posición al presionar el ratón
        menuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Guardamos la posición inicial del ratón
                pX = e.getX();
                pY = e.getY();
            }
        });

        // Manejador de MouseMotionListener para mover la ventana
        menuPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Calculamos el desplazamiento y actualizamos la ubicación
                int deltaX = e.getX() - pX;
                int deltaY = e.getY() - pY;
                setLocation(getLocation().x + deltaX, getLocation().y + deltaY);
            }
        });
    }

    private void setResizeableWindow() {
        addMouseMotionListener(resizableWindowListener);
    }

    private final MouseMotionListener resizableWindowListener = new MouseMotionListener() {

        @Override
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            int margin = 10; // Margen de 10 píxeles para redimensionar

            // Determina el cursor según la posición del ratón
            if (isOnBottomRightCorner(p, margin)) {
                setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR)); // Cursor diagonal
            } else if (isOnRightEdge(p, margin)) {
                setCursor(new Cursor(Cursor.E_RESIZE_CURSOR)); // Cursor horizontal
            } else if (isOnBottomEdge(p, margin)) {
                setCursor(new Cursor(Cursor.S_RESIZE_CURSOR)); // Cursor vertical
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Cursor por defecto
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point p = e.getPoint();

            // Redimensionar según el tipo de cursor activo
            switch (getCursor().getType()) {
                case Cursor.E_RESIZE_CURSOR:
                    resizeWindow(p, "horizontal");
                    break;
                case Cursor.S_RESIZE_CURSOR:
                    resizeWindow(p, "vertical");
                    break;
                case Cursor.SE_RESIZE_CURSOR:
                    resizeWindow(p, "both");
                    break;
                default:
                    break;
            }
        }

        // Métodos auxiliares para simplificar las condiciones
        private boolean isOnRightEdge(Point p, int margin) {
            return p.getX() >= getWidth() - margin && p.getX() <= getWidth();
        }

        private boolean isOnBottomEdge(Point p, int margin) {
            return p.getY() >= getHeight() - margin && p.getY() <= getHeight();
        }

        private boolean isOnBottomRightCorner(Point p, int margin) {
            return p.getX() >= getWidth() - margin && p.getY() >= getHeight() - margin;
        }

        private void resizeWindow(Point p, String direction) {
            int width = getWidth();
            int height = getHeight();
            int marginMajor, marginMinor;

            switch (direction) {
                case "horizontal":
                    marginMajor = (int) (p.getX() - width);
                    marginMinor = (int) p.getX();
                    if (marginMajor > 0) {
                        setBounds(getX(), getY(), width + marginMajor, height);
                    } else if (marginMinor >= 200) {
                        setBounds(getX(), getY(), marginMinor, height);
                    }
                    break;

                case "vertical":
                    marginMajor = (int) (p.getY() - height);
                    marginMinor = (int) p.getY();
                    if (marginMajor > 0) {
                        setBounds(getX(), getY(), width, height + marginMajor);
                    } else if (marginMinor >= 200) {
                        setBounds(getX(), getY(), width, marginMinor);
                    }
                    break;

                case "both":
                    int marginMajorX = (int) (p.getX() - width);
                    int marginMajorY = (int) (p.getY() - height);
                    int marginMinorX = (int) p.getX();
                    int marginMinorY = (int) p.getY();

                    if (marginMajorX > 0 && marginMajorY > 0) {
                        setBounds(getX(), getY(), width + marginMajorX, height + marginMajorY);
                    } else if (marginMinorX >= 200 && marginMinorY >= 200) {
                        setBounds(getX(), getY(), marginMinorX, marginMinorY);
                    }
                    break;
            }
        }
    };

}
