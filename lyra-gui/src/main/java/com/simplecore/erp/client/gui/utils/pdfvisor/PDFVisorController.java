
package com.simplecore.erp.client.gui.utils.pdfvisor;

import com.simplecore.erp.client.controllers.workspace.PanelManager;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class PDFVisorController {

    public static void openPDFVisor(byte[] pdfBytes, String fileName, String fileExtension) {
        if (pdfBytes == null || pdfBytes.length == 0) {
            return;
        }
        // Validar extensión del archivo
        if (!fileExtension.equalsIgnoreCase("pdf")) {
            return;
        }
        PDFVisorPanel visor = new PDFVisorPanel(fileName);
        visor.showPDF(pdfBytes);
        PanelManager.goToPanel(visor);
    }
}
