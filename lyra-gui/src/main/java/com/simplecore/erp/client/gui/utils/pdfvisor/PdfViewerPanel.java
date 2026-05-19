package com.simplecore.erp.client.gui.utils.pdfvisor;

import com.simplecore.erp.client.controllers.workspace.PanelManager;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import java.awt.Graphics;
import javax.swing.JPanel;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public final class PdfViewerPanel extends JPanel{

    private SystemMessages notificator = new SystemMessages();
    private static final Logger LOGGER = Logger.getLogger(PdfViewerPanel.class.getName());
    private static final float FIXED_DPI = 150f;
    private static final float MIN_DPI = 50f;
    private static final float MAX_DPI = 600f;
    private float currentDPI = FIXED_DPI; // Usamos una variable para mantener el DPI actual

    private PDDocument document;
    private PDFRenderer pdfRenderer;
    private int currentPageIndex = 0;
    private BufferedImage currentPageImage;
    private Dimension preferredSize;

    public PdfViewerPanel(byte[] pdfBytes) {
        if (pdfBytes == null || pdfBytes.length == 0) {
            notificator.showInfoMsg(AppMessages.msg(AppMessages.Key.ERROR_LOADING_FILES));
            return;
        }
        try {
            this.document = Loader.loadPDF(pdfBytes); // o PDDocument.load(inputStream) si usás PDFBox directamente
            this.pdfRenderer = new PDFRenderer(document);
            setBackground(Color.WHITE);
            renderPage(currentPageIndex);
        } catch (IOException ex) {
            notificator.showInfoMsg(AppMessages.msg(AppMessages.Key.ERROR_LOADING_FILES));
            PanelManager.goBack();
        }
    }

    public void renderPage(int pageIndex) {
        try {
            if (pageIndex < 0 || pageIndex >= document.getNumberOfPages()) {
                notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.PAGE_NOT_FOUND));
                return;
            }
            currentPageImage = pdfRenderer.renderImageWithDPI(pageIndex, currentDPI);
            currentPageIndex = pageIndex;
            // Establecer tamaño preferido basado en la imagen
            if (currentPageImage != null) {
                preferredSize = new Dimension(
                        currentPageImage.getWidth(),
                        currentPageImage.getHeight()
                );
            }
            revalidate();
            repaint();
        } catch (IOException e) {
            currentPageImage = null;
            notificator.showInfoMsg(AppMessages.msg(AppMessages.Key.ERROR_LOADING_FILES));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Enable high-quality rendering
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill the background with light gray
        g2d.setColor(new Color(220, 220, 220));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (currentPageImage != null) {
            int imgWidth = currentPageImage.getWidth();
            int imgHeight = currentPageImage.getHeight();
            int x = (getWidth() - imgWidth) / 2;
            int y = (getHeight() - imgHeight) / 2;

            // Draw white margin around the page
            int margin = 8;
            g2d.setColor(Color.WHITE);
            g2d.fillRect(x - margin, y - margin, imgWidth + 2 * margin, imgHeight + 2 * margin);

            // Draw the rendered PDF page
            g2d.drawImage(currentPageImage, x, y, this);
        } else {
            g2d.setColor(Color.RED);
            g2d.drawString("No page to display", 20, 20);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return preferredSize != null ? preferredSize : new Dimension(600, 800);
    }

    public void nextPage() {
        if (hasNextPage()) {
            renderPage(currentPageIndex + 1);
        }
    }

    public void previousPage() {
        if (hasPreviousPage()) {
            renderPage(currentPageIndex - 1);
        }
    }

    public boolean hasNextPage() {
        return document != null && currentPageIndex < document.getNumberOfPages() - 1;
    }

    public boolean hasPreviousPage() {
        return document != null && currentPageIndex > 0;
    }

    public int getCurrentPageNumber() {
        return currentPageIndex + 1; // Mostrar como 1-based para el usuario
    }

    public int getTotalPages() {
        return document != null ? document.getNumberOfPages() : 0;
    }

    public void setRenderDPI(float dpi) {
        if (dpi != currentDPI) {
            currentDPI = dpi;
            renderPage(currentPageIndex);
        }
    }

    public void closeDocument() {
        try {
            if (document != null) {
                document.close();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error closing PDF document", e);
        }
    }

    public void dispose() {
        closeDocument();
    }

    public void zoomIn() {
        float newDPI = currentDPI * 1.2f;
        if (newDPI > MAX_DPI) {
            newDPI = MAX_DPI;
        }
        setRenderDPI(newDPI);
    }

    public void zoomOut() {
        float newDPI = currentDPI / 1.2f;
        if (newDPI < MIN_DPI) {
            newDPI = MIN_DPI;
        }
        setRenderDPI(newDPI);
    }

    // Método para imprimir todo el documento PDF
    public void printDocument() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex >= document.getNumberOfPages()) {
                    return NO_SUCH_PAGE; // No hay más páginas para imprimir
                }

                // Renderizar la página correspondiente con el DPI fijado
                try {
                    BufferedImage pageImage = pdfRenderer.renderImageWithDPI(pageIndex, FIXED_DPI);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                    // Ajustar el tamaño de la imagen para que encaje en la página imprimible
                    double pageWidth = pageFormat.getImageableWidth();
                    double pageHeight = pageFormat.getImageableHeight();
                    double imgWidth = pageImage.getWidth();
                    double imgHeight = pageImage.getHeight();

                    // Escalar la imagen para que encaje en el área imprimible
                    double scaleX = pageWidth / imgWidth;
                    double scaleY = pageHeight / imgHeight;
                    double scale = Math.min(scaleX, scaleY);

                    g2d.scale(scale, scale);

                    // Dibujar la página
                    g2d.drawImage(pageImage, 0, 0, null);

                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error printing page", e);
                    return NO_SUCH_PAGE;
                }

                return PAGE_EXISTS;
            }
        });

        // Mostrar cuadro de diálogo de impresión
        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException e) {
                LOGGER.log(Level.SEVERE, "Error printing document", e);
            }
        }
    }

}
