package com.simplecore.erp.client.gui.utils.pdfvisor;

import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterNumeric;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.text.AbstractDocument;

public class PDFVisorPanel extends JPanel  implements PDFVisorListener {

    private SystemMessages notificator = new SystemMessages();
    private PdfViewerPanel viewerPanel;
    private boolean documentLoaded = false;
    private String documentName;

    //Constructor used for new charts
    public PDFVisorPanel(String documentName) {
        initComponents();
        setDocsFilters();
        initActions();
        disableNavigationButtons();
        this.documentName = documentName;
    }

    private void setDocsFilters() {
        ((AbstractDocument) currentPageTF.getDocument()).setDocumentFilter(new DocumentFilterNumeric(3));
        ((AbstractDocument) totalPagesTF.getDocument()).setDocumentFilter(new DocumentFilterNumeric(3));
        visorScroll.getViewport().setBackground(new Color(220, 220, 220));
        visorScroll.setBorder(null); // Opcional, si quieres sin bordes feos por defecto
    }
    
    public void showPDF(byte[] pdfBytes) {
        // Limpiar visor anterior si existe
        if (viewerPanel != null) {
            viewerPanel.closeDocument();
            visorScroll.remove(viewerPanel);
        }
        try {
            viewerPanel = new PdfViewerPanel(pdfBytes);
            visorScroll.setViewportView(viewerPanel);
            documentLoaded = true;
            updateNavigationButtons();

        } catch (Exception ex) {
            viewerPanel = null; // Evitar referencias obsoletas
            notificator.showInfoMsg(AppMessages.msg(AppMessages.Key.ERROR_LOADING_FILES));
            disableNavigationButtons();
        } finally {
            visorScroll.revalidate();
            visorScroll.repaint();
        }
        setDocumentIntormation();
    }
    
    private void setDocumentIntormation() {
        moduleTitleLabel.setText(moduleTitleLabel.getText() + " : " + documentName);
        currentPageTF.setText(String.valueOf(viewerPanel.getCurrentPageNumber()));
        totalPagesTF.setText(String.valueOf(viewerPanel.getTotalPages()));
    }

    public void goToPage(int pageNumber) {
        if (!documentLoaded) {
            return;
        }
        int total = viewerPanel.getTotalPages();
        int current = viewerPanel.getCurrentPageNumber();

        if (pageNumber >= 1 && pageNumber <= total) {
            if (pageNumber != current) {
                viewerPanel.renderPage(pageNumber - 1);
            }
        } else {
            Toolkit.getDefaultToolkit().beep(); // fuera de rango
        }
        currentPageTF.setText(String.valueOf(viewerPanel.getCurrentPageNumber()));
        updateNavigationButtons();
    }

    private void initActions() {
        backButton.addActionListener(e -> navigatePrevious());
        nextButton.addActionListener(e -> navigateNext());
        zoomInButton.addActionListener(e -> {
            if (documentLoaded) {
                viewerPanel.zoomIn();
            }
        });
        zoomOutButton.addActionListener(e -> {
            if (documentLoaded) {
                viewerPanel.zoomOut();
            }
        });
        currentPageTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = currentPageTF.getText().trim();
                    if (text.isEmpty()) {
                        return;
                    }

                    try {
                        int page = Integer.parseInt(text);
                        goToPage(page);
                    } catch (NumberFormatException ex) {
                        Toolkit.getDefaultToolkit().beep();
                        currentPageTF.setText(String.valueOf(viewerPanel.getCurrentPageNumber()));
                    }
                }
            }
        });
        printButton.addActionListener(e -> {
            if (documentLoaded) { // Verifica si el documento está cargado
                viewerPanel.printDocument(); // Llama al método printDocument de PdfViewerPanel
            } else {
                notificator.showInfoMsg(AppMessages.msg(AppMessages.Key.NO_DOCUMENTS_TO_PRINT)); // Muestra un mensaje si no hay documento cargado
            }
        });
        
        zoomOnScroll();
    }

    private void navigatePrevious() {
        if (documentLoaded) {
            viewerPanel.previousPage();
            updateNavigationButtons();
            currentPageTF.setText(String.valueOf(viewerPanel.getCurrentPageNumber()));
        }
    }

    private void navigateNext() {
        if (documentLoaded) {
            viewerPanel.nextPage();
            updateNavigationButtons();
            currentPageTF.setText(String.valueOf(viewerPanel.getCurrentPageNumber()));
        }
    }

    private void closeViewer() {
        if (viewerPanel != null) {
            viewerPanel.closeDocument();
        }
    }

    private void updateNavigationButtons() {
        if (documentLoaded) {
            backButton.setEnabled(viewerPanel.hasPreviousPage());
            nextButton.setEnabled(viewerPanel.hasNextPage());
        }
    }

    private void disableNavigationButtons() {
        backButton.setEnabled(false);
        nextButton.setEnabled(false);
        documentLoaded = false;
    }

    private void zoomOnScroll() {
        visorScroll.addMouseWheelListener(e -> {
            if (e.isControlDown()) {
                if (e.getWheelRotation() < 0) {
                    viewerPanel.zoomIn();
                } else {
                    viewerPanel.zoomOut();
                }
            }
        });
    }

    
    
    @Override
    public void onPanelClose() {
        viewerPanel.closeDocument();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        panelTitle = new com.simplecore.erp.client.gui.components.panels.PanelGradient3();
        moduleTitleLabel = new com.simplecore.erp.client.gui.components.labels.JLabelHQ();
        buttonsPanel = new com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient();
        backButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        zoomOutButton = new javax.swing.JButton();
        zoomInButton = new javax.swing.JButton();
        currentPageTF = new javax.swing.JTextField();
        slash = new javax.swing.JLabel();
        totalPagesTF = new javax.swing.JTextField();
        printButton = new javax.swing.JButton();
        bodyPanel = new corex.suite.JPanelRoundedGradient();
        visorScroll = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(233, 241, 250));
        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        panelTitle.setColor1(new java.awt.Color(206, 223, 239));
        panelTitle.setColor2(new java.awt.Color(173, 199, 222));
        panelTitle.setColor3(new java.awt.Color(173, 199, 222));

        moduleTitleLabel.setForeground(new java.awt.Color(51, 51, 51));
        moduleTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        moduleTitleLabel.setText("PDF Lyra Viewer");
        moduleTitleLabel.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 16)); // NOI18N

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1013, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
            .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(moduleTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        topPanel.add(panelTitle);

        buttonsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonsPanel.setColor1(new java.awt.Color(206, 223, 239));
        buttonsPanel.setColor2(new java.awt.Color(206, 223, 239));
        buttonsPanel.setMaximumSize(null);
        buttonsPanel.setMinimumSize(new java.awt.Dimension(149, 35));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(149, 35));
        buttonsPanel.setVerifyInputWhenFocusTarget(false);

        backButton.setIcon(new CustomSVGIcon("/icons/svg/navigate_previous.svg", new Dimension(20,20))
        );

        nextButton.setIcon(new CustomSVGIcon("/icons/svg/navigate_next.svg", new Dimension(20,20))
        );

        zoomOutButton.setIcon(new CustomSVGIcon("/icons/svg/zoom_out.svg", new Dimension(20,20))
        );

        zoomInButton.setIcon(new CustomSVGIcon("/icons/svg/zoom_in.svg", new Dimension(20,20))
        );

        currentPageTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 12)); // NOI18N

        slash.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        slash.setText("/");

        totalPagesTF.setEditable(false);
        totalPagesTF.setFont(new java.awt.Font("IBM Plex Sans Medium", 0, 12)); // NOI18N

        printButton.setIcon(new CustomSVGIcon("/icons/svg/printer.svg", new Dimension(20,20)));

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zoomInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(zoomOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                .addComponent(currentPageTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalPagesTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zoomOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zoomInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentPageTF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slash)
                    .addComponent(totalPagesTF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        topPanel.add(buttonsPanel);

        add(topPanel, java.awt.BorderLayout.NORTH);

        bodyPanel.setColor1(new java.awt.Color(247, 247, 255));
        bodyPanel.setColor2(new java.awt.Color(239, 243, 247));

        visorScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        visorScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(visorScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(visorScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );

        add(bodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private corex.suite.JPanelRoundedGradient bodyPanel;
    private com.simplecore.erp.client.gui.components.panels.JPanelRoundedGradient buttonsPanel;
    private javax.swing.JTextField currentPageTF;
    private com.simplecore.erp.client.gui.components.labels.JLabelHQ moduleTitleLabel;
    private javax.swing.JButton nextButton;
    private com.simplecore.erp.client.gui.components.panels.PanelGradient3 panelTitle;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel slash;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField totalPagesTF;
    private javax.swing.JScrollPane visorScroll;
    private javax.swing.JButton zoomInButton;
    private javax.swing.JButton zoomOutButton;
    // End of variables declaration//GEN-END:variables
}
