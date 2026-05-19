

package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.company.util;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class DocumentComparison {
    public final String documentType;
    public final Long originalId;
    public final String originalName;
    public final byte[] originalContent;
    
    public final Long currentId;
    public final String currentName;
    public final String currentExtension;
    public final byte[] currentContent;

    public DocumentComparison(
        String documentType,
        Long originalId,
        String originalName,
        byte[] originalContent,
        Long currentId,
        String currentName,
        String currentExtension,
        byte[] currentContent
    ) {
        this.documentType = documentType;
        this.originalId = originalId;
        this.originalName = originalName;
        this.originalContent = originalContent;
        this.currentId = currentId;
        this.currentName = currentName;
        this.currentExtension = currentExtension;
        this.currentContent = currentContent;
    }
}
