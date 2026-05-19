

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services.FICompanyFormState;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class OriginalFIDocuments {

    private FICompanyFormState formState;
    private Long docConstitutionActID;
    private byte[] docConstitutionAct;
    private String docConstitutionActFileName;
    private String docConstitutionActFileExtension;

    private Long docRucCertificateID;
    private byte[] docRucCertificate;
    private String docRucCertificateFileName;
    private String docRucCertificateFileExtension;

    private Long docLegalPowerID;
    private byte[] docLegalPower;
    private String docLegalPowerFileName;
    private String docLegalPowerFileExtension;

    private Long docMunicipalLicenseID;
    private byte[] docMunicipalLicense;
    private String docMunicipalLicenseFileName;
    private String docMunicipalLicenseFileExtension;

    private Long docRepresentativeIdID;
    private byte[] docRepresentativeID;
    private String docRepresentativeIDFileName;
    private String docRepresentativeIDFileExtension;

    private Long docEntityRegistrationID;
    private byte[] docEntityRegistration;
    private String docEntityRegistrationFileName;
    private String docEntityRegistrationFileExtension;

    private Long docOtherID;
    private byte[] docOther;
    private String docOtherFileName;
    private String docOtherFileExtension;

    public OriginalFIDocuments(FICompanyFormState formState) {
        this.formState = formState;
        setOriginalValues();
    }

    private void setOriginalValues() {
        this.docConstitutionActID = formState.getDocConstitutionActID();
        this.docConstitutionAct = formState.getDocConstitutionAct();
        this.docConstitutionActFileName = formState.getDocConstitutionActFileName();
        this.docConstitutionActFileExtension = formState.getDocConstitutionActFileExtension();

        this.docRucCertificateID = formState.getDocRucCertificateID();
        this.docRucCertificate = formState.getDocRucCertificate();
        this.docRucCertificateFileName = formState.getDocRucCertificateFileName();
        this.docRucCertificateFileExtension = formState.getDocRucCertificateFileExtension();

        this.docLegalPowerID = formState.getDocLegalPowerID();
        this.docLegalPower = formState.getDocLegalPower();
        this.docLegalPowerFileName = formState.getDocLegalPowerFileName();
        this.docLegalPowerFileExtension = formState.getDocLegalPowerFileExtension();

        this.docMunicipalLicenseID = formState.getDocMunicipalLicenseID();
        this.docMunicipalLicense = formState.getDocMunicipalLicense();
        this.docMunicipalLicenseFileName = formState.getDocMunicipalLicenseFileName();
        this.docMunicipalLicenseFileExtension = formState.getDocMunicipalLicenseFileExtension();

        this.docRepresentativeIdID = formState.getDocRepresentativeIdID();
        this.docRepresentativeID = formState.getDocRepresentativeID();
        this.docRepresentativeIDFileName = formState.getDocRepresentativeIDFileName();
        this.docRepresentativeIDFileExtension = formState.getDocRepresentativeIDFileExtension();

        this.docEntityRegistrationID = formState.getDocEntityRegistrationID();
        this.docEntityRegistration = formState.getDocEntityRegistration();
        this.docEntityRegistrationFileName = formState.getDocEntityRegistrationFileName();
        this.docEntityRegistrationFileExtension = formState.getDocEntityRegistrationFileExtension();

        this.docOtherID = formState.getDocOtherID();
        this.docOther = formState.getDocOther();
        this.docOtherFileName = formState.getDocOtherFileName();
        this.docOtherFileExtension = formState.getDocOtherFileExtension();
    }

    public Long getOriginalDocConstitutionActID() { return docConstitutionActID; }
    public byte[] getOriginalDocConstitutionAct() { return docConstitutionAct; }
    public String getOriginalDocConstitutionActFileName() { return docConstitutionActFileName; }
    public String getOriginalDocConstitutionActFileExtension() { return docConstitutionActFileExtension; }

    public Long getOriginalDocRucCertificateID() { return docRucCertificateID; }
    public byte[] getOriginalDocRucCertificate() { return docRucCertificate; }
    public String getOriginalDocRucCertificateFileName() { return docRucCertificateFileName; }
    public String getOriginalDocRucCertificateFileExtension() { return docRucCertificateFileExtension; }

    public Long getOriginalDocLegalPowerID() { return docLegalPowerID; }
    public byte[] getOriginalDocLegalPower() { return docLegalPower; }
    public String getOriginalDocLegalPowerFileName() { return docLegalPowerFileName; }
    public String getOriginalDocLegalPowerFileExtension() { return docLegalPowerFileExtension; }

    public Long getOriginalDocMunicipalLicenseID() { return docMunicipalLicenseID; }
    public byte[] getOriginalDocMunicipalLicense() { return docMunicipalLicense; }
    public String getOriginalDocMunicipalLicenseFileName() { return docMunicipalLicenseFileName; }
    public String getOriginalDocMunicipalLicenseFileExtension() { return docMunicipalLicenseFileExtension; }

    public Long getOriginalDocRepresentativeIdID() { return docRepresentativeIdID; }
    public byte[] getOriginalDocRepresentativeID() { return docRepresentativeID; }
    public String getOriginalDocRepresentativeIDFileName() { return docRepresentativeIDFileName; }
    public String getOriginalDocRepresentativeIDFileExtension() { return docRepresentativeIDFileExtension; }

    public Long getOriginalDocEntityRegistrationID() { return docEntityRegistrationID; }
    public byte[] getOriginalDocEntityRegistration() { return docEntityRegistration; }
    public String getOriginalDocEntityRegistrationFileName() { return docEntityRegistrationFileName; }
    public String getOriginalDocEntityRegistrationFileExtension() { return docEntityRegistrationFileExtension; }

    public Long getOriginalDocOtherID() { return docOtherID; }
    public byte[] getOriginalDocOther() { return docOther; }
    public String getOriginalDocOtherFileName() { return docOtherFileName; }
    public String getOriginalDocOtherFileExtension() { return docOtherFileExtension; }
}
