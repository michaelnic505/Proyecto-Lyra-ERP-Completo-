package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyDataHandler;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.utils.pdfvisor.PDFVisorController;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class FICompanyDocumentsController {

    private final FICompanyFormState formState;
    private final FICompanyDataHandler dataHandler;
    private final FICompanyFormPanel panel;
    private SystemMessages notificator = new SystemMessages();

    public FICompanyDocumentsController(FICompanyFormState formState,
            FICompanyDataHandler dataHandler,
            FICompanyFormPanel panel) {
        this.formState = formState;
        this.dataHandler = dataHandler;
        this.panel = panel;
        initializeListeners();
    }

    private void initializeListeners() {
        setConstitutionActListener();
        setRucCertificateListener();
        setLegalPowerListener();
        setMunicipalLicenseListener();
        setRepresentativeIDListener();
        setEntityRegistrationListener();
        setOtherListener();
    }

    private void setConstitutionActListener() {
        panel.getConstitutionActButton().addActionListener(e -> {
            handleFileSelection(
                    panel.getConstitutionActTF(),
                    formState::setDocConstitutionAct,
                    formState::setDocConstitutionActFileName,
                    formState::setDocConstitutionActFileExtension);
        });
        panel.getRemoveConstitutionActButton().addActionListener(e -> {
            panel.getConstitutionActTF().setText("");
            removeAndSetNulls(formState::setDocConstitutionAct,
                    formState::setDocConstitutionActFileName,
                    formState::setDocConstitutionActFileExtension);
        });
        panel.getViewConstitutionActButton().addActionListener(e -> {
            viewPDFDocument(formState::getDocConstitutionAct,
                    formState::getDocConstitutionActFileName,
                    formState::getDocConstitutionActFileExtension);
        });
    }

    private void setRucCertificateListener() {
        panel.getRucCertificateButton().addActionListener(e -> {
            handleFileSelection(
                    panel.getRucCertificateTF(),
                    formState::setDocRucCertificate,
                    formState::setDocRucCertificateFileName,
                    formState::setDocRucCertificateFileExtension);
        });
        panel.getRemoveRucCertificateButton().addActionListener(e -> {
            panel.getRucCertificateTF().setText("");
            removeAndSetNulls(formState::setDocRucCertificate,
                    formState::setDocRucCertificateFileName,
                    formState::setDocRucCertificateFileExtension);
        });
        panel.getViewRucCertificateButton().addActionListener(e -> {
            viewPDFDocument(formState::getDocRucCertificate,
                    formState::getDocRucCertificateFileName,
                    formState::getDocRucCertificateFileExtension);
        });
    }
    
    private void setLegalPowerListener() {
        panel.getLegalPowerButton().addActionListener(e -> {
            handleFileSelection(
                    panel.getLegalPowerTF(),
                    formState::setDocLegalPower,
                    formState::setDocLegalPowerFileName,
                    formState::setDocLegalPowerFileExtension);
        });
        panel.getRemoveLegalPowerButton().addActionListener(e -> {
            panel.getLegalPowerTF().setText("");
            removeAndSetNulls(formState::setDocLegalPower,
                    formState::setDocLegalPowerFileName,
                    formState::setDocLegalPowerFileExtension);
        });
        panel.getViewLegalPowerButton().addActionListener(e -> {
            viewPDFDocument(formState::getDocLegalPower,
                    formState::getDocLegalPowerFileName,
                    formState::getDocLegalPowerFileExtension);
        });
    }

    private void setMunicipalLicenseListener() {
        panel.getMunicipalLicenseButton().addActionListener(e -> {
            handleFileSelection(
                    panel.getMunicipalLicenseTF(),
                    formState::setDocMunicipalLicense,
                    formState::setDocMunicipalLicenseFileName,
                    formState::setDocMunicipalLicenseFileExtension);
        });
        panel.getRemoveMunicipalLicenseButton().addActionListener(e -> {
            panel.getMunicipalLicenseTF().setText("");
            removeAndSetNulls(formState::setDocMunicipalLicense,
                    formState::setDocMunicipalLicenseFileName,
                    formState::setDocMunicipalLicenseFileExtension);
        });
        panel.getViewMunicipalLicenseButton().addActionListener(e -> {
            viewPDFDocument(formState::getDocMunicipalLicense,
                    formState::getDocMunicipalLicenseFileName,
                    formState::getDocMunicipalLicenseFileExtension);
        });
    }

    private void setRepresentativeIDListener() {
        panel.getRepresentativeIDButton().addActionListener(e -> {
            handleFileSelection(
                    panel.getRepresentativeIDTF(),
                    formState::setDocRepresentativeID,
                    formState::setDocRepresentativeIDFileName,
                    formState::setDocRepresentativeIDFileExtension);
        });
        panel.getRemoveRepresentativeIDButton().addActionListener(e -> {
            panel.getRepresentativeIDTF().setText("");
            removeAndSetNulls(formState::setDocRepresentativeID,
                    formState::setDocRepresentativeIDFileName,
                    formState::setDocRepresentativeIDFileExtension);
        });
        panel.getViewRepresentativeIDButton().addActionListener(e -> {
            viewPDFDocument(formState::getDocRepresentativeID,
                    formState::getDocRepresentativeIDFileName,
                    formState::getDocRepresentativeIDFileExtension);
        });
    }

    private void setEntityRegistrationListener() {
        panel.getEntityRegistrationButton().addActionListener(e -> {
            handleFileSelection(
                    panel.getEntityRegistrationTF(),
                    formState::setDocEntityRegistration,
                    formState::setDocEntityRegistrationFileName,
                    formState::setDocEntityRegistrationFileExtension);
        });
        panel.getRemoveEntityRegistrationButton().addActionListener(e -> {
            panel.getEntityRegistrationTF().setText("");
            removeAndSetNulls(formState::setDocEntityRegistration,
                    formState::setDocEntityRegistrationFileName,
                    formState::setDocEntityRegistrationFileExtension);
        });
        panel.getViewEntityRegistrationButton().addActionListener(e -> {
            viewPDFDocument(formState::getDocEntityRegistration,
                    formState::getDocEntityRegistrationFileName,
                    formState::getDocEntityRegistrationFileExtension);
        });
    }

    private void setOtherListener() {
        panel.getOtherButton().addActionListener(e -> {
            handleFileSelection(
                    panel.getOtherDocument(),
                    formState::setDocOther,
                    formState::setDocOtherFileName,
                    formState::setDocOtherFileExtension);
        });
        panel.getRemoveOtherButton().addActionListener(e -> {
            panel.getOtherDocument().setText("");
            removeAndSetNulls(formState::setDocOther, 
                    formState::setDocOtherFileName, 
                    formState::setDocOtherFileExtension);
        });
        panel.getViewOtherButton().addActionListener(e -> {
            viewPDFDocument(formState::getDocOther,
                    formState::getDocOtherFileName,
                    formState::getDocOtherFileExtension);
        });
    }


    private File openFileChooser(String title) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(title);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PDF Files", "pdf"
        );
        chooser.setFileFilter(filter);

        int result = chooser.showOpenDialog(panel); // O null si preferís
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    private void handleFileSelection(JTextField textField, 
            Consumer<byte[]> setFileData,
            Consumer<String> setFileName,
            Consumer<String> setFileExtension) {
        try {
            File file = openFileChooser("Select File");

            if (file == null) {
                setFileData.accept(null);  // Enviar null si no se seleccionó archivo
                textField.setText("");     // Limpiar el campo de texto
                return;
            }

            if (!file.exists() || !file.isFile()) {
                notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ERROR_LOADING_FILES));
                setFileData.accept(null);
                textField.setText("");
                return;
            }
            // Validación: tamaño máximo permitido (ej: 5MB)
            long maxSize = 5L * 1024 * 1024; // 5 MB
            if (file.length() > maxSize) {
                notificator.showErrorMsg("El archivo excede el tamaño máximo de 5 MB");
                setFileData.accept(null);
                textField.setText("");
                return;
            }
            // Verificar si el archivo existe y es válido

            setFileData.accept(Files.readAllBytes(file.toPath()));
            setFileName.accept(getFileName(file));
            setFileExtension.accept(getFileExtension(file));
            textField.setText(file.getAbsolutePath());

        } catch (IOException ex) {
            notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.ERROR_LOADING_FILES));
            setFileData.accept(null);
            textField.setText(""); // Limpiar el campo de texto en caso de error
        }
    }

    private String getFileName(File file) {
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex != name.length() - 1) {
            return name.substring(0, lastDotIndex); // Base name
        } else {
            return name; // Sin extensión, retorna todo el nombre
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex != name.length() - 1) {
            return name.substring(lastDotIndex + 1); // Extensión
        } else {
            return ""; // Sin extensión
        }
    }
    
    private void removeAndSetNulls(Consumer<byte[]> fileData,
            Consumer<String> fileName,
            Consumer<String> fileExtension) {
        fileData.accept(null);
        fileName.accept("");
        fileExtension.accept("");
    }

    private void viewPDFDocument(Supplier<byte[]> pdfBytes,
            Supplier<String> fileNames,
            Supplier<String> fileExtensions) {
        PDFVisorController.openPDFVisor(pdfBytes.get(), fileNames.get(), fileExtensions.get());
    }
}
