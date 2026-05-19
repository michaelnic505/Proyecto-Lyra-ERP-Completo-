

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.model;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyFormState;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCard;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util.DocumentComparison;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util.FIDocumentMapper;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util.FIDocumentType;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util.OriginalFIDocuments;
import com.simplecore.erp.shared.models.dto.FICOAssociationsDTO;
import com.simplecore.erp.shared.models.dto.FICOCompanyRelation;
import com.simplecore.erp.shared.models.dto.FICompanyDTO;
import com.simplecore.erp.shared.models.dto.FICompanyDocument;
import com.simplecore.erp.shared.models.dto.FIDocumentsDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyMapper {
    //Create FI Company
    public static FICompanyDTO mapToDTOToCreate(FICompanyFormState formState, String createdBy) {
        return new FICompanyDTO.Builder()
                .setCompanyCode(formState.getCompanyCode())
                .setCompanyName(formState.getCompanyName())
                .setCompanyLegalName(formState.getCompanyLegalName())
                .setCompanyBussinesType(formState.getCompanyBussinesType())
                .setCompanyBussinesClassification(formState.getCompanyBussinesClassification())
                .setCompanyCountryCode(formState.getCompanyCountryCode())
                .setCompanyLegalAddress(formState.getCompanyLegalAddress())
                .setCompanyPhone(formState.getCompanyPhone())
                .setCompanyEmail(formState.getCompanyEmail())
                .setCompanyOfficialLanguage(formState.getCompanyOfficialLanguage())
                .setCompanyTimeZone(formState.getCompanyTimeZone())
                .setCompanyChartOfAccount(formState.getCompanyChartOfAccount())
                .setCompanyCreatedBy(createdBy)
                .setCompanyStatus(formState.getCompanyStatus())
                .setSysTransaction(formState.getSysTransaction())
                .build();
    }
    //Modify FI Company
    public static FICompanyDTO mapToDTOToModify(FICompanyFormState formState, String updatedBy) {
        return new FICompanyDTO.Builder()
                .setCompanyID(formState.getCompanyID())
                .setCompanyName(formState.getCompanyName())
                .setCompanyLegalName(formState.getCompanyLegalName())
                .setCompanyBussinesType(formState.getCompanyBussinesType())
                .setCompanyBussinesClassification(formState.getCompanyBussinesClassification())
                .setCompanyCountryCode(formState.getCompanyCountryCode())
                .setCompanyLegalAddress(formState.getCompanyLegalAddress())
                .setCompanyPhone(formState.getCompanyPhone())
                .setCompanyEmail(formState.getCompanyEmail())
                .setCompanyOfficialLanguage(formState.getCompanyOfficialLanguage())
                .setCompanyTimeZone(formState.getCompanyTimeZone())
                .setCompanyChartOfAccount(formState.getCompanyChartOfAccount())
                .setCompanyUpdatedBy(updatedBy)
                .setCompanyStatus(formState.getCompanyStatus())
                .setSysTransaction(formState.getSysTransaction())
                .build();
    }

    
    //Create FI Company Documents
    public static FIDocumentsDTO mapToDTOToCreateDocs(FICompanyFormState formState) {
        List<FICompanyDocument> documentsToCreate = new ArrayList<>();

        addIfValid(documentsToCreate, FIDocumentType.CONSTITUTION_ACT.name(),
                formState.getDocConstitutionActFileName(),
                formState.getDocConstitutionActFileExtension(),
                formState.getDocConstitutionAct());

        addIfValid(documentsToCreate, FIDocumentType.RUC_CERTIFICATE.name(),
                formState.getDocRucCertificateFileName(),
                formState.getDocRucCertificateFileExtension(),
                formState.getDocRucCertificate());

        addIfValid(documentsToCreate, FIDocumentType.LEGAL_POWER.name(),
                formState.getDocLegalPowerFileName(),
                formState.getDocLegalPowerFileExtension(),
                formState.getDocLegalPower());

        addIfValid(documentsToCreate, FIDocumentType.MUNICIPAL_LICENSE.name(),
                formState.getDocMunicipalLicenseFileName(),
                formState.getDocMunicipalLicenseFileExtension(),
                formState.getDocMunicipalLicense());

        addIfValid(documentsToCreate, FIDocumentType.REPRESENTATIVE_ID.name(),
                formState.getDocRepresentativeIDFileName(),
                formState.getDocRepresentativeIDFileExtension(),
                formState.getDocRepresentativeID());

        addIfValid(documentsToCreate, FIDocumentType.ENTITY_REGISTRATION.name(),
                formState.getDocEntityRegistrationFileName(),
                formState.getDocEntityRegistrationFileExtension(),
                formState.getDocEntityRegistration());

        addIfValid(documentsToCreate, FIDocumentType.OTHER.name(),
                formState.getDocOtherFileName(),
                formState.getDocOtherFileExtension(),
                formState.getDocOther());

        return new FIDocumentsDTO(null, documentsToCreate, null, null);
    }
    //FI Company Documents to Modify
    public static FIDocumentsDTO mapToDTOToModifyDocs(FICompanyFormState formState, OriginalFIDocuments originalDocs) {
        List<FICompanyDocument> documentsToCreate = new ArrayList<>();
        List<FICompanyDocument> documentsToModify = new ArrayList<>();
        List<FICompanyDocument> documentsToDelete = new ArrayList<>();

        FIDocumentMapper.processDocument(new DocumentComparison(
                FIDocumentType.CONSTITUTION_ACT.name(),
                originalDocs.getOriginalDocConstitutionActID(),
                originalDocs.getOriginalDocConstitutionActFileName(),
                originalDocs.getOriginalDocConstitutionAct(),
                formState.getDocConstitutionActID(),
                formState.getDocConstitutionActFileName(),
                formState.getDocConstitutionActFileExtension(),
                formState.getDocConstitutionAct()
        ), documentsToCreate, documentsToModify, documentsToDelete);

        FIDocumentMapper.processDocument(new DocumentComparison(
                FIDocumentType.RUC_CERTIFICATE.name(),
                originalDocs.getOriginalDocRucCertificateID(),
                originalDocs.getOriginalDocRucCertificateFileName(),
                originalDocs.getOriginalDocRucCertificate(),
                formState.getDocRucCertificateID(),
                formState.getDocRucCertificateFileName(),
                formState.getDocRucCertificateFileExtension(),
                formState.getDocRucCertificate()
        ), documentsToCreate, documentsToModify, documentsToDelete);

        FIDocumentMapper.processDocument(new DocumentComparison(
                FIDocumentType.LEGAL_POWER.name(),
                originalDocs.getOriginalDocLegalPowerID(),
                originalDocs.getOriginalDocLegalPowerFileName(),
                originalDocs.getOriginalDocLegalPower(),
                formState.getDocLegalPowerID(),
                formState.getDocLegalPowerFileName(),
                formState.getDocLegalPowerFileExtension(),
                formState.getDocLegalPower()
        ), documentsToCreate, documentsToModify, documentsToDelete);

        FIDocumentMapper.processDocument(new DocumentComparison(
                FIDocumentType.MUNICIPAL_LICENSE.name(),
                originalDocs.getOriginalDocMunicipalLicenseID(),
                originalDocs.getOriginalDocMunicipalLicenseFileName(),
                originalDocs.getOriginalDocMunicipalLicense(),
                formState.getDocMunicipalLicenseID(),
                formState.getDocMunicipalLicenseFileName(),
                formState.getDocMunicipalLicenseFileExtension(),
                formState.getDocMunicipalLicense()
        ), documentsToCreate, documentsToModify, documentsToDelete);

        FIDocumentMapper.processDocument(new DocumentComparison(
                FIDocumentType.REPRESENTATIVE_ID.name(),
                originalDocs.getOriginalDocRepresentativeIdID(),
                originalDocs.getOriginalDocRepresentativeIDFileName(),
                originalDocs.getOriginalDocRepresentativeID(),
                formState.getDocRepresentativeIdID(),
                formState.getDocRepresentativeIDFileName(),
                formState.getDocRepresentativeIDFileExtension(),
                formState.getDocRepresentativeID()
        ), documentsToCreate, documentsToModify, documentsToDelete);

        FIDocumentMapper.processDocument(new DocumentComparison(
                FIDocumentType.ENTITY_REGISTRATION.name(),
                originalDocs.getOriginalDocEntityRegistrationID(),
                originalDocs.getOriginalDocEntityRegistrationFileName(),
                originalDocs.getOriginalDocEntityRegistration(),
                formState.getDocEntityRegistrationID(),
                formState.getDocEntityRegistrationFileName(),
                formState.getDocEntityRegistrationFileExtension(),
                formState.getDocEntityRegistration()
        ), documentsToCreate, documentsToModify, documentsToDelete);

        FIDocumentMapper.processDocument(new DocumentComparison(
                FIDocumentType.OTHER.name(),
                originalDocs.getOriginalDocOtherID(),
                originalDocs.getOriginalDocOtherFileName(),
                originalDocs.getOriginalDocOther(),
                formState.getDocOtherID(),
                formState.getDocOtherFileName(),
                formState.getDocOtherFileExtension(),
                formState.getDocOther()
        ), documentsToCreate, documentsToModify, documentsToDelete);

        return new FIDocumentsDTO(null, documentsToCreate, documentsToModify, documentsToDelete);
    }

    
    //Auxiliary method for safely adding documents
    private static void addIfValid(List<FICompanyDocument> list, String documentType, String fileName, String fileExtension, byte[] content) {
        if (fileName != null && !fileName.isEmpty()
                && fileExtension != null && !fileExtension.isEmpty()
                && content != null && content.length > 0) {
            list.add(new FICompanyDocument.Builder()
                    .setDocumentType(documentType)
                    .setFileName(fileName)
                    .setFileExtension(fileExtension)
                    .setFileContent(content)
                    .build()
            );
        }
    }

    
    //FICO Relations
    public static FICOAssociationsDTO mapToFICOAssociationDTOToCreate(FICompanyFormState formState) {
        List<FICOCompanyRelation> relations = Optional.ofNullable(formState.getCompanyCOCards())
                .orElseGet(List::of) // Si es null, devuelve lista vacía
                .stream()
                .map(COCompanyCard::getFICOAssociationData)
                .toList();

        return new FICOAssociationsDTO(relations,null,null);
    }
    
    public static FICOAssociationsDTO mapToFICOAssociationDTOToModify(FICompanyFormState formState){
        List<FICOCompanyRelation> relationToCreate = getToCreate(formState);
        List<FICOCompanyRelation> relationToModify = getToModify(formState);
        
        return new FICOAssociationsDTO(relationToCreate, relationToModify,null);
    }
    
    
    //FICO Relations Lists 
    private static List<FICOCompanyRelation> getToCreate(FICompanyFormState formState) {
        return Optional.ofNullable(formState.getCompanyCOCards())
                .orElse(Collections.emptyList())
                .stream()
                .map(COCompanyCard::getFICOAssociationData)
                .filter(relation -> relation.getFICORelationID() == null)
                .collect(Collectors.toList());
    }

    private static List<FICOCompanyRelation> getToModify(FICompanyFormState formState) {
        return Optional.ofNullable(formState.getCompanyCOCards())
                .orElse(Collections.emptyList())
                .stream()
                .map(COCompanyCard::getFICOAssociationData)
                .filter(relation -> relation.getFICORelationID() != null)
                .collect(Collectors.toList());
    }

}
