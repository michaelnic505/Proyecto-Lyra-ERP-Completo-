package com.simplecore.erp.shared.models.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class FICompanyDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    private final long documentID;
    private final long fiCompanyID;
    private final String documentType;
    private final byte[] fileContent;
    private final String fileName;
    private final String fileExtension;
    private final Timestamp createdAt;
    private final Timestamp udpatedAt;

    private FICompanyDocument(Builder builder) {
        this.documentID = builder.documentID;
        this.fiCompanyID = builder.fiCompanyID;
        this.documentType = builder.documentType;
        this.fileContent = builder.fileContent;
        this.fileName = builder.fileName;
        this.fileExtension = builder.fileExtension;
        this.createdAt = builder.createdAt;
        this.udpatedAt = builder.udpatedAt;
    }

    public long getDocumentID() {
        return documentID;
    }

    public long getFICompanyID() {
        return fiCompanyID;
    }

    public String getDocumentType() {
        return documentType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUdpatedAt() {
        return udpatedAt;
    }

    public static class Builder {

        private long documentID;
        private long fiCompanyID;
        private String documentType;
        private byte[] fileContent;
        private String fileName;
        private String fileExtension;
        private Timestamp createdAt;
        private Timestamp udpatedAt;

        public Builder setFIDocumentID(long documentID) {
            this.documentID = documentID;
            return this;
        }

        public Builder setFICompanyID(long fiCompanyID) {
            this.fiCompanyID = fiCompanyID;
            return this;
        }

        public Builder setDocumentType(String documentType) {
            this.documentType = documentType;
            return this;
        }

        public Builder setFileContent(byte[] fileContent) {
            this.fileContent = fileContent;
            return this;
        }

        public Builder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder setFileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
            return this;
        }

        public Builder setCreatedAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUdpatedAt(Timestamp udpatedAt) {
            this.udpatedAt = udpatedAt;
            return this;
        }

        public FICompanyDocument build() {
            return new FICompanyDocument(this);
        }
    }
}
