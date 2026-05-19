package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util;

import com.simplecore.erp.shared.models.dto.FICompanyDocument;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class FIDocumentMapper {

    public static void processDocument(
            DocumentComparison doc,
            List<FICompanyDocument> toCreate,
            List<FICompanyDocument> toModify,
            List<FICompanyDocument> toDelete
    ) {
        if (isValidToCreate(doc.currentId, doc.currentName, doc.currentExtension, doc.currentContent)) {
            toCreate.add(new FICompanyDocument.Builder()
                    .setDocumentType(doc.documentType)
                    .setFileName(doc.currentName)
                    .setFileExtension(doc.currentExtension)
                    .setFileContent(doc.currentContent)
                    .build());
        } else if (isValidToModify(doc.originalId, doc.originalName, doc.currentName, doc.currentExtension, doc.currentContent, doc.originalContent)) {
            toModify.add(new FICompanyDocument.Builder()
                    .setFIDocumentID(doc.originalId)
                    .setDocumentType(doc.documentType)
                    .setFileName(doc.currentName)
                    .setFileExtension(doc.currentExtension)
                    .setFileContent(doc.currentContent)
                    .build());
        } else if (isValidToDelete(doc.originalId, doc.currentName, doc.currentExtension, doc.currentContent)) {
            toDelete.add(new FICompanyDocument.Builder()
                    .setFIDocumentID(doc.originalId)
                    .build());
        }
    }

    private static boolean isValidToCreate(Long ID, String fileName, String fileExtension, byte[] content) {
        return ID == null
                && content != null
                && (fileName != null && !fileName.isBlank())
                && (fileExtension != null && !fileExtension.isBlank());
    }

    private static boolean isValidToModify(Long ID, String originalFileName, String fileName, String fileExtension, byte[] content, byte[] originalContent) {
        return ID != null
                && fileName != null && !fileName.isBlank()
                && fileExtension != null && !fileExtension.isBlank()
                && (!originalFileName.equals(fileName)
                || !Arrays.equals(originalContent, content));

    }

    private static boolean isValidToDelete(Long ID, String fileName, String fileExtension,
            byte[] content) {
        return ID != null
                && (fileName == null || fileName.isBlank())
                && (fileExtension == null || fileExtension.isBlank())
                && content == null;

    }

}
