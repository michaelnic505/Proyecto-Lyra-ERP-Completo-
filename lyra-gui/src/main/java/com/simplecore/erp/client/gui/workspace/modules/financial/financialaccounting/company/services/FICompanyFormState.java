

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.services;

import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.dependencies.IgnoreFromCount;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.ui.form.COCompanyCard;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICompanyFormState extends FormState {

    private Long companyID;
    private String companyCode;
    private String companyName;
    private String companyLegalName;
    private String companyBussinesType;
    private String companyBussinesClassification;
    private String companyCountryCode;
    private String companyLegalAddress;
    private String companyPhone;
    private String companyEmail;
    private String companyOfficialLanguage;
    private String companyTimeZone;
    private String companyChartOfAccount;
    private List<COCompanyCard> companyCOCards;//tabla aparte
    private String companyCreatedBy;
    private Timestamp companyCreatedAt;
    private String companyUpdatedBy; 
    private Timestamp companyUpdatedAt;
    private String companyStatus;//enum
    private String sysTransaction;
   
    @IgnoreFromCount
    private Long docConstitutionActID;
    
    private byte[] docConstitutionAct;
    private String docConstitutionActFileName;
    private String docConstitutionActFileExtension;

    @IgnoreFromCount
    private Long docRucCertificateID;
   
    private byte[] docRucCertificate;
    private String docRucCertificateFileName;
    private String docRucCertificateFileExtension;

    @IgnoreFromCount
    private Long docLegalPowerID;
    
    private byte[] docLegalPower;
    private String docLegalPowerFileName;
    private String docLegalPowerFileExtension;

    @IgnoreFromCount
    private Long docMunicipalLicenseID;
    
    private byte[] docMunicipalLicense;
    private String docMunicipalLicenseFileName;
    private String docMunicipalLicenseFileExtension;

    @IgnoreFromCount
    private Long docRepresentativeIdID;
    
    private byte[] docRepresentativeID;
    private String docRepresentativeIDFileName;
    private String docRepresentativeIDFileExtension;

    @IgnoreFromCount
    private Long docEntityRegistrationID;
    
    private byte[] docEntityRegistration;
    private String docEntityRegistrationFileName;
    private String docEntityRegistrationFileExtension;

    @IgnoreFromCount
    private Long docOtherID;
    
    private byte[] docOther;
    private String docOtherFileName;
    private String docOtherFileExtension;

    public FICompanyFormState(int fieldsCount) {
        super(fieldsCount);
    }
    
    public Long getCompanyID(){
        return companyID;
    }
    
    public void setCompanyID(Long id){
        this.companyID = id;
    }
    
    public String getCompanyCode(){
        return companyCode;
    }
    
    public void setCompanyCode(String companyCode){
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
        updateFilledFields(this);
    }

    public String getCompanyLegalName() {
        return companyLegalName;
    }

    public void setCompanyLegalName(String companyLegalName) {
        this.companyLegalName = companyLegalName;
        updateFilledFields(this);
    }

    public String getCompanyBussinesType() {
        return companyBussinesType;
    }

    public void setCompanyBussinesType(String companyBussinesType) {
        this.companyBussinesType = companyBussinesType;
        updateFilledFields(this);
    }

    public String getCompanyBussinesClassification() {
        return companyBussinesClassification;
    }

    public void setCompanyBussinesClassification(String companyBussinesClassification) {
        this.companyBussinesClassification = companyBussinesClassification;
        updateFilledFields(this);
    }

    public String getCompanyCountryCode() {
        return companyCountryCode;
    }

    public void setCompanyCountryCode(String companyCountryCode) {
        this.companyCountryCode = companyCountryCode;
        updateFilledFields(this);
    }

    public String getCompanyLegalAddress() {
        return companyLegalAddress;
    }

    public void setCompanyLegalAddress(String companyLegalAddress) {
        this.companyLegalAddress = companyLegalAddress;
        updateFilledFields(this);
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
        updateFilledFields(this);
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
        updateFilledFields(this);
    }

    public String getCompanyOfficialLanguage() {
        return companyOfficialLanguage;
    }

    public void setCompanyOfficialLanguage(String companyOfficialLanguage) {
        this.companyOfficialLanguage = companyOfficialLanguage;
        updateFilledFields(this);
    }

    public String getCompanyTimeZone() {
        return companyTimeZone;
    }

    public void setCompanyTimeZone(String companyTimeZone) {
        this.companyTimeZone = companyTimeZone;
        updateFilledFields(this);
    }

    public String getCompanyChartOfAccount() {
        return companyChartOfAccount;
    }

    public void setCompanyChartOfAccount(String companyChartOfAccount) {
        this.companyChartOfAccount = companyChartOfAccount;
        updateFilledFields(this);
    }
    
    public void setSysTransaction(String sysTransaction){
        this.sysTransaction = sysTransaction;
    }

    public String getSysTransaction(){
        return sysTransaction;
    }
    
    public List<COCompanyCard> getCompanyCOCards() {
        if(companyCOCards==null)new SystemMessages().showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
        return companyCOCards;
    }

    public void setCompanyCOCards(List<COCompanyCard> companyCOAssociatoins) {
        this.companyCOCards = companyCOAssociatoins;
        updateFilledFields(this);
    }

    public String getCompanyCreatedBy() {
        return companyCreatedBy;
    }

    public void setCompanyCreatedBy(String companyCreatedBy) {
        this.companyCreatedBy = companyCreatedBy;
        updateFilledFields(this);
    }

    public String getCompanyUpdatedBy() {
        return companyUpdatedBy; // Getter añadido aquí
    }

    public void setCompanyUpdatedBy(String companyUpdatedBy) { // Setter añadido aquí
        this.companyUpdatedBy = companyUpdatedBy;
        updateFilledFields(this);
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
        updateFilledFields(this);
    }

    // Setters y getters para documentos
    public byte[] getDocConstitutionAct() {
        return docConstitutionAct;
    }

    public String getDocConstitutionActFileName() {
        return docConstitutionActFileName;
    }

    public String getDocConstitutionActFileExtension() {
        return docConstitutionActFileExtension;
    }

    public void setDocConstitutionAct(byte[] docConstitutionAct) {
        this.docConstitutionAct = docConstitutionAct;
        updateFilledFields(this);
    }

    public void setDocConstitutionActFileName(String fileName) {
        this.docConstitutionActFileName = fileName;
        updateFilledFields(this);
    }

    public void setDocConstitutionActFileExtension(String fileExtension) {
        this.docConstitutionActFileExtension = fileExtension;
        updateFilledFields(this);
    }

    public byte[] getDocRucCertificate() {
        return docRucCertificate;
    }

    public String getDocRucCertificateFileName() {
        return docRucCertificateFileName;
    }

    public String getDocRucCertificateFileExtension() {
        return docRucCertificateFileExtension;
    }

    public void setDocRucCertificate(byte[] docRucCertificate) {
        this.docRucCertificate = docRucCertificate;
        updateFilledFields(this);
    }

    public void setDocRucCertificateFileName(String fileName) {
        this.docRucCertificateFileName = fileName;
        updateFilledFields(this);
    }

    public void setDocRucCertificateFileExtension(String fileExtension) {
        this.docRucCertificateFileExtension = fileExtension;
        updateFilledFields(this);
    }

    public byte[] getDocLegalPower() {
        return docLegalPower;
    }

    public String getDocLegalPowerFileName() {
        return docLegalPowerFileName;
    }

    public String getDocLegalPowerFileExtension() {
        return docLegalPowerFileExtension;
    }

    public void setDocLegalPower(byte[] docLegalPower) {
        this.docLegalPower = docLegalPower;
        updateFilledFields(this);
    }

    public void setDocLegalPowerFileName(String fileName) {
        this.docLegalPowerFileName = fileName;
        updateFilledFields(this);
    }

    public void setDocLegalPowerFileExtension(String fileExtension) {
        this.docLegalPowerFileExtension = fileExtension;
        updateFilledFields(this);
    }

    public byte[] getDocMunicipalLicense() {
        return docMunicipalLicense;
    }

    public String getDocMunicipalLicenseFileName() {
        return docMunicipalLicenseFileName;
    }

    public String getDocMunicipalLicenseFileExtension() {
        return docMunicipalLicenseFileExtension;
    }

    public void setDocMunicipalLicense(byte[] docMunicipalLicense) {
        this.docMunicipalLicense = docMunicipalLicense;
        updateFilledFields(this);
    }

    public void setDocMunicipalLicenseFileName(String fileName) {
        this.docMunicipalLicenseFileName = fileName;
        updateFilledFields(this);
    }

    public void setDocMunicipalLicenseFileExtension(String fileExtension) {
        this.docMunicipalLicenseFileExtension = fileExtension;
        updateFilledFields(this);
    }

    public byte[] getDocRepresentativeID() {
        return docRepresentativeID;
    }

    public String getDocRepresentativeIDFileName() {
        return docRepresentativeIDFileName;
    }

    public String getDocRepresentativeIDFileExtension() {
        return docRepresentativeIDFileExtension;
    }

    public void setDocRepresentativeID(byte[] docRepresentativeID) {
        this.docRepresentativeID = docRepresentativeID;
        updateFilledFields(this);
    }

    public void setDocRepresentativeIDFileName(String fileName) {
        this.docRepresentativeIDFileName = fileName;
        updateFilledFields(this);
    }

    public void setDocRepresentativeIDFileExtension(String fileExtension) {
        this.docRepresentativeIDFileExtension = fileExtension;
        updateFilledFields(this);
    }

    public byte[] getDocEntityRegistration() {
        return docEntityRegistration;
    }

    public String getDocEntityRegistrationFileName() {
        return docEntityRegistrationFileName;
    }

    public String getDocEntityRegistrationFileExtension() {
        return docEntityRegistrationFileExtension;
    }

    public void setDocEntityRegistration(byte[] docEntityRegistration) {
        this.docEntityRegistration = docEntityRegistration;
        updateFilledFields(this);
    }

    public void setDocEntityRegistrationFileName(String fileName) {
        this.docEntityRegistrationFileName = fileName;
        updateFilledFields(this);
    }

    public void setDocEntityRegistrationFileExtension(String fileExtension) {
        this.docEntityRegistrationFileExtension = fileExtension;
        updateFilledFields(this);
    }

    public byte[] getDocOther() {
        return docOther;
    }

    public String getDocOtherFileName() {
        return docOtherFileName;
    }

    public String getDocOtherFileExtension() {
        return docOtherFileExtension;
    }

    public void setDocOther(byte[] docOther) {
        this.docOther = docOther;
        updateFilledFields(this);
    }

    public void setDocOtherFileName(String fileName) {
        this.docOtherFileName = fileName;
        updateFilledFields(this);
    }

    public void setDocOtherFileExtension(String fileExtension) {
        this.docOtherFileExtension = fileExtension;
        updateFilledFields(this);
    }

    public Timestamp getCompanyUpdatedAt() {
        return companyUpdatedAt;
    }

    public void setCompanyUpdatedAt(Timestamp companyUpdatedAt) {
        this.companyUpdatedAt = companyUpdatedAt;
        updateFilledFields(this);
    }

    public Timestamp getCompanyCreatedAt() {
        return companyCreatedAt;
    }

    public void setCompanyCreatedAt(Timestamp companyCreatedAt) {
        this.companyCreatedAt = companyCreatedAt;
        updateFilledFields(this);
    }

    /**
     * @return the docConstitutionActID
     */
    public Long getDocConstitutionActID() {
        return docConstitutionActID;
    }

    /**
     * @param docConstitutionActID the docConstitutionActID to set
     */
    public void setDocConstitutionActID(long docConstitutionActID) {
        this.docConstitutionActID = docConstitutionActID;
    }

    /**
     * @return the docRucCertificateID
     */
    public Long getDocRucCertificateID() {
        return docRucCertificateID;
    }

    /**
     * @param docRucCertificateID the docRucCertificateID to set
     */
    public void setDocRucCertificateID(long docRucCertificateID) {
        this.docRucCertificateID = docRucCertificateID;
    }

    /**
     * @return the docLegalPowerID
     */
    public Long getDocLegalPowerID() {
        return docLegalPowerID;
    }

    /**
     * @param docLegalPowerID the docLegalPowerID to set
     */
    public void setDocLegalPowerID(long docLegalPowerID) {
        this.docLegalPowerID = docLegalPowerID;
    }

    /**
     * @return the docMunicipalLicenseID
     */
    public Long getDocMunicipalLicenseID() {
        return docMunicipalLicenseID;
    }

    /**
     * @param docMunicipalLicenseID the docMunicipalLicenseID to set
     */
    public void setDocMunicipalLicenseID(long docMunicipalLicenseID) {
        this.docMunicipalLicenseID = docMunicipalLicenseID;
    }

    /**
     * @return the docRepresentativeIdID
     */
    public Long getDocRepresentativeIdID() {
        return docRepresentativeIdID;
    }

    /**
     * @param docRepresentativeIdID the docRepresentativeIdID to set
     */
    public void setDocRepresentativeIdID(long docRepresentativeIdID) {
        this.docRepresentativeIdID = docRepresentativeIdID;
    }

    /**
     * @return the docEntityRegistrationID
     */
    public Long getDocEntityRegistrationID() {
        return docEntityRegistrationID;
    }

    /**
     * @param docEntityRegistrationID the docEntityRegistrationID to set
     */
    public void setDocEntityRegistrationID(long docEntityRegistrationID) {
        this.docEntityRegistrationID = docEntityRegistrationID;
    }

    /**
     * @return the docOtherID
     */
    public Long getDocOtherID() {
        return docOtherID;
    }

    /**
     * @param docOtherID the docOtherID to set
     */
    public void setDocOtherID(long docOtherID) {
        this.docOtherID = docOtherID;
    }
}
