

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util;

import com.simplecore.erp.client.abstractions.AbstractDTOFormInjector;
import com.simplecore.erp.client.abstractions.DTOInjector;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.FICompanyFormPanel;
import com.simplecore.erp.shared.models.dto.FICompanyDocument;
import com.simplecore.erp.shared.models.dto.FIDocumentsDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.JTextField;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyDocumentsInjector extends AbstractDTOFormInjector<FICompanyFormPanel>
        implements DTOInjector<FIDocumentsDTO> {

    private FICompanyFormState formState;
    private OriginalFIDocuments originalDocumentsData;
    private final Map<String, Consumer<FICompanyDocument>> injectors = new HashMap<>();

    public FICompanyDocumentsInjector(
            FICompanyFormPanel panel, 
            FICompanyFormState formState) {
        super(panel);
        this.formState = formState;
        initializeInjectors();
    }
    
    public OriginalFIDocuments getOriginalDocumentsData(){
        return originalDocumentsData;
    }

    private void initializeInjectors() {
        injectors.put(FIDocumentType.CONSTITUTION_ACT.name(),
                doc -> injectDocument(doc, panel.getConstitutionActTF(),
                        formState::setDocConstitutionActID,
                        formState::setDocConstitutionActFileName,
                        formState::setDocConstitutionActFileExtension,
                        formState::setDocConstitutionAct));

        injectors.put(FIDocumentType.RUC_CERTIFICATE.name(),
                doc -> injectDocument(doc, panel.getRucCertificateTF(),
                        formState::setDocRucCertificateID,
                        formState::setDocRucCertificateFileName,
                        formState::setDocRucCertificateFileExtension,
                        formState::setDocRucCertificate));

        injectors.put(FIDocumentType.LEGAL_POWER.name(),
                doc -> injectDocument(doc, panel.getLegalPowerTF(),
                        formState::setDocLegalPowerID,
                        formState::setDocLegalPowerFileName,
                        formState::setDocLegalPowerFileExtension,
                        formState::setDocLegalPower));

        injectors.put(FIDocumentType.MUNICIPAL_LICENSE.name(),
                doc -> injectDocument(doc, panel.getMunicipalLicenseTF(),
                        formState::setDocMunicipalLicenseID,
                        formState::setDocMunicipalLicenseFileName,
                        formState::setDocMunicipalLicenseFileExtension,
                        formState::setDocMunicipalLicense));

        injectors.put(FIDocumentType.REPRESENTATIVE_ID.name(),
                doc -> injectDocument(doc, panel.getRepresentativeIDTF(),
                        formState::setDocRepresentativeIdID,
                        formState::setDocRepresentativeIDFileName,
                        formState::setDocRepresentativeIDFileExtension,
                        formState::setDocRepresentativeID));

        injectors.put(FIDocumentType.ENTITY_REGISTRATION.name(),
                doc -> injectDocument(doc, panel.getEntityRegistrationTF(),
                        formState::setDocEntityRegistrationID,
                        formState::setDocEntityRegistrationFileName,
                        formState::setDocEntityRegistrationFileExtension,
                        formState::setDocEntityRegistration));

        injectors.put(FIDocumentType.OTHER.name(),
                doc -> injectDocument(doc, panel.getOtherDocument(),
                        formState::setDocOtherID,
                        formState::setDocOtherFileName,
                        formState::setDocOtherFileExtension,
                        formState::setDocOther));

    }

    private void injectDocument(FICompanyDocument document,
            JTextField targetField,
            Consumer<Long> documentID,
            Consumer<String> fileNameSetter,
            Consumer<String> extensionSetter,
            Consumer<byte[]> contentSetter) {
        documentID.accept(document.getDocumentID());
        targetField.setText(writeDocName(document));
        fileNameSetter.accept(document.getFileName());
        extensionSetter.accept(document.getFileExtension());
        contentSetter.accept(document.getFileContent());
    }

    @Override
    public void inject(FIDocumentsDTO dto) {
        if (dto == null || dto.getDocumentsRetrieves().isEmpty()) {
            return;
        }
        dto.getDocumentsRetrieves().forEach(document -> {
            Consumer<FICompanyDocument> injector = injectors.get(document.getDocumentType());
            if (injector != null) {
                injector.accept(document);
            }
        });
        this.originalDocumentsData = new OriginalFIDocuments(formState);
    }

    private String writeDocName(FICompanyDocument doc) {
        return doc.getFileName() + "." + doc.getFileExtension();
    }
}
