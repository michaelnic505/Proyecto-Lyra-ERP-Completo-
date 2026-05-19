package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar;

import com.simplecore.erp.gui.components.tables.newversions.DynamicTableModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableRowSorter;
import com.simplecore.erp.i18n.LanguageManager;
import com.simplecore.erp.gui.workspace.LyraWorkspace;

public class OperationContextWindow extends javax.swing.JDialog {
    

    private LanguageManager languageManager;    
    private JButton button;
    private TableRowSorter<DynamicTableModel> filter;
    
    
    public OperationContextWindow(Frame parent, JButton button,LanguageManager languageManager) {
       
        super(parent, true);
        this.languageManager=languageManager;
        this.button = button;
        initComponents();
        addEvents();
        setMoveableWindow();
        setResizeableWindow();
        MatteBorder matteBorder = new MatteBorder(1, 1, 1, 1, new Color(55,103,191)); // Borde gris
        getRootPane().setBorder(matteBorder);
        
        applyTranslations();
        setTableModel(languageManager.getLocale().getLanguage());

        Point buttonLocation = button.getLocationOnScreen();
        setLocation(buttonLocation.x + 40, buttonLocation.y - 220);
        searchingToolbar.setVisible(false);

    }

    private void addEvents() {
        extiButton();
        selectButton();
        selectRowByDoubleClick();
        searchButton();
    }
    private void extiButton() {
        exitButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
        disposeButton.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
    }

    private void applyTranslations() {
        tabbedPane.setTitleAt(0, "Search among results");
        String title = LyraWorkspace.getTranslator().getTranslation("OperationContextWindow.title");
        windowTitleLabel.setText(title);
    }

    private String[] getTableColumnsName() {
        String key = LyraWorkspace.getTableTranslator().getTranslation("column.key");
        String context = LyraWorkspace.getTableTranslator().getTranslation("column.context");

        return new String[]{
            key,
            context
        };
    }


    private void setTableModel(String language) {

        OperationContexts[] rows = OperationContexts.values();
        String[] columns = getTableColumnsName();

        DynamicTableModel model = new DynamicTableModel(rows.length, columns);

        for (int i = 0; i < rows.length; i++) {
            model.setValueAt(rows[i].getValue(), i, 0);
            model.setCellEditable(i, 0, false);

            model.setValueAt(rows[i].getDescription(language), i, 1);
            model.setCellEditable(i, 1, false);
        }

        tableList.setModel(model);
        tableList.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableList.getColumnModel().getColumn(1).setPreferredWidth(150);

        setTableFilter();
    }
    
    // Original method modified to receive two JTextFields as parameters
    private void getSelectedRowData(JTextField tf, JTextField tf2) {

        int selectedRow = tableList.getSelectedRow();

        if (selectedRow != -1) {
            int selectedModelRow = tableList.convertRowIndexToModel(selectedRow);

            Object codeG = tableList.getModel().getValueAt(selectedModelRow, 0);
            Object descG = tableList.getModel().getValueAt(selectedModelRow, 1);

            if (tf != null) {
                tf.setText(codeG.toString());
            }
            if (tf2 != null) {
                tf2.setText(descG.toString());
            }
            button.setEnabled(false);
            dispose();
        }
    }

// Overloaded method for a JTextField and a JLabel
    private void getSelectedRowData(JTextField tf, JLabel label) {

        int selectedRow = tableList.getSelectedRow();

        if (selectedRow != -1) {
            int selectedModelRow = tableList.convertRowIndexToModel(selectedRow);

            Object codeG = tableList.getModel().getValueAt(selectedModelRow, 0);
            Object descG = tableList.getModel().getValueAt(selectedModelRow, 1);

            if (tf != null) {
                tf.setText(codeG.toString());
            }
            if (label != null) {
                label.setText(descG.toString());
            }
            button.setEnabled(false);
            dispose();
        }
    }

    private void selectButton() {
        selectButton.addActionListener(e -> {
            getSelectedRowData(textField1, label);
        });
    }

    private void selectRowByDoubleClick() {
        tableList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableList.getSelectedRow() != -1) {
                    getSelectedRowData(textField1,label);
                }
            }

        });

    }

    private JTextField textField1;
    public void setJTextFieldCode(JTextField tf) {
        this.textField1 = tf;
    }

    private JTextField textField2;
    public void setJTextField2(JTextField tf2) {
        this.textField2 = tf2;
    }

    // Method to set a JLabel
    private JLabel label;
    public void setJLabel(JLabel label) {
        this.label = label;
    }

    private void setTableFilter() {

        tableList.setAutoCreateRowSorter(true);
        filter = new TableRowSorter<>((DynamicTableModel) tableList.getModel());
        tableList.setRowSorter(filter);

        searchBoxTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = searchBoxTf.getText();
                if (text.trim().isEmpty()) {
                    filter.setRowFilter(null); // Quitar el filtro si el campo está vacío
                } else {
                    filter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
                }
            }

        });
    }
    private void searchButton() {
        searchButton.addActionListener((e) -> {
            if (searchingToolbar.isVisible()) {

                searchButton.setIcon(new ImageIcon(getClass().getResource("/icons/auxiliarwindows/deployed.png")));
                searchingToolbar.setVisible(false);

            } else {

                searchButton.setIcon(new ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png")));
                searchingToolbar.setVisible(true);

            }
        });
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerPanel = new com.simplecore.erp.gui.components.panels.JPanelCornerPainted();
        menuPanel = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        windowTitleLabel = new com.simplecore.erp.gui.components.labels.JLabelHQ();
        disposeButton = new com.simplecore.erp.gui.components.labels.JButtonCustom();
        tabbedPane = new com.simplecore.erp.gui.components.tabbedpanes.TabbedPane();
        panelTabbed = new javax.swing.JPanel();
        searchingToolbar = new javax.swing.JToolBar();
        searchBoxTf = new javax.swing.JTextField();
        searchButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new com.simplecore.erp.gui.components.tables.newversions.DynamicTable();
        jPanel_Rounded_Corners_Degradado1 = new com.simplecore.erp.gui.components.panels.JPanelRoundedGradient();
        exitButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();
        selectButton = new com.simplecore.erp.gui.components.labels.JButtonHQ();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 146, 229));
        setModal(true);
        setUndecorated(true);

        containerPanel.setBackground(new java.awt.Color(255, 255, 255));
        containerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        containerPanel.setAlto(15);
        containerPanel.setAncho(15);

        menuPanel.setColor1(new java.awt.Color(0, 146, 229));
        menuPanel.setColor2(new java.awt.Color(0, 146, 229));

        windowTitleLabel.setForeground(new java.awt.Color(248, 248, 248));
        windowTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        windowTitleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lyra/access/modules/iconography/toolbar/acceso2.png"))); // NOI18N
        windowTitleLabel.setText("Title");
        windowTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        disposeButton.setBackground(new java.awt.Color(0, 146, 229));
        disposeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close_window.png"))); // NOI18N
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

        tabbedPane.setForeground(new java.awt.Color(246, 246, 246));
        tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        tabbedPane.setSelectedTabColor(new java.awt.Color(0, 131, 206));
        tabbedPane.setTabBackgroundColor(new java.awt.Color(0, 102, 160));

        panelTabbed.setBackground(new java.awt.Color(202, 216, 237));

        searchingToolbar.setRollover(true);
        searchingToolbar.add(searchBoxTf);

        searchButton.setBackground(new java.awt.Color(226, 210, 144));
        searchButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/deploy.png"))); // NOI18N

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

        jPanel_Rounded_Corners_Degradado1.setBackground(new java.awt.Color(217, 215, 200));
        jPanel_Rounded_Corners_Degradado1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel_Rounded_Corners_Degradado1.setColor1(new java.awt.Color(217, 215, 200));
        jPanel_Rounded_Corners_Degradado1.setColor2(new java.awt.Color(217, 215, 200));

        exitButton.setBackground(new java.awt.Color(226, 210, 144));
        exitButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/close.png"))); // NOI18N

        selectButton.setBackground(new java.awt.Color(226, 210, 144));
        selectButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        selectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auxiliarwindows/ok_icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_Rounded_Corners_Degradado1Layout = new javax.swing.GroupLayout(jPanel_Rounded_Corners_Degradado1);
        jPanel_Rounded_Corners_Degradado1.setLayout(jPanel_Rounded_Corners_Degradado1Layout);
        jPanel_Rounded_Corners_Degradado1Layout.setHorizontalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Rounded_Corners_Degradado1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Rounded_Corners_Degradado1Layout.setVerticalGroup(
            jPanel_Rounded_Corners_Degradado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelTabbedLayout = new javax.swing.GroupLayout(panelTabbed);
        panelTabbed.setLayout(panelTabbedLayout);
        panelTabbedLayout.setHorizontalGroup(
            panelTabbedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchingToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
        );
        panelTabbedLayout.setVerticalGroup(
            panelTabbedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabbedLayout.createSequentialGroup()
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(searchingToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel_Rounded_Corners_Degradado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Restricciones", panelTabbed);

        javax.swing.GroupLayout containerPanelLayout = new javax.swing.GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.simplecore.erp.gui.components.panels.JPanelCornerPainted containerPanel;
    private com.simplecore.erp.gui.components.labels.JButtonCustom disposeButton;
    private com.simplecore.erp.gui.components.labels.JButtonHQ exitButton;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient jPanel_Rounded_Corners_Degradado1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.simplecore.erp.gui.components.panels.JPanelRoundedGradient menuPanel;
    private javax.swing.JPanel panelTabbed;
    private javax.swing.JTextField searchBoxTf;
    private com.simplecore.erp.gui.components.labels.JButtonHQ searchButton;
    private javax.swing.JToolBar searchingToolbar;
    private com.simplecore.erp.gui.components.labels.JButtonHQ selectButton;
    private com.simplecore.erp.gui.components.tabbedpanes.TabbedPane tabbedPane;
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
            int margin = 4; // Margen de 10 píxeles para redimensionar

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
