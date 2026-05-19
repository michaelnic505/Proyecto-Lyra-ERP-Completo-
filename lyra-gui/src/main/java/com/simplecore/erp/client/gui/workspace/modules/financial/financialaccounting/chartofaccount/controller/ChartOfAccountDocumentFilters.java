
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.controller;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.ui.form.ChartOfAccountFormPanel;
import com.simplecore.erp.client.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterNumeric;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterVarchar;
import javax.swing.text.AbstractDocument;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccountDocumentFilters {
    private final ChartOfAccountFormPanel panel;

    public ChartOfAccountDocumentFilters(ChartOfAccountFormPanel panel) {
        this.panel = panel;
        initDocumentFilters();
    }
    
    private void initDocumentFilters(){
        setFilterOnNameOfChartAccountNameTF();
        setFilterOnCurrencyCodeMatchCode();
        setFilterOnCountryCodeMatchCode();
        setFilterOnBusinessTypeTxtF();
        setFilterOnIndustryClassificationTxtF();
        setFilterOnFiscalYearTxtF();
        setFilterOnTaxSchema();
        setFilterOnVersionTagTxtF();
        setFilterOnChartOfAccountNotesTxtF();
    }
    
    private void setFilterOnNameOfChartAccountNameTF(){
        AbstractDocument document = (AbstractDocument) panel.getChartOfAccountNameTF().getDocument();
        document.setDocumentFilter(new DocumentFilterVarchar(100));
    }
    
    private void setFilterOnCurrencyCodeMatchCode(){
        AbstractDocument document = (AbstractDocument) panel.getCurrencyCodeMatchCode().getTextField().getDocument();
        document.setDocumentFilter(new DocFilterVarcharWithoutSpace(3));
    }
    
    private void setFilterOnCountryCodeMatchCode(){
        AbstractDocument document = (AbstractDocument) panel.getCountryCodeMatchCode().getTextField().getDocument();
        document.setDocumentFilter(new DocFilterVarcharWithoutSpace(2));
    }
    
    private void setFilterOnBusinessTypeTxtF(){
        AbstractDocument document = (AbstractDocument) panel.getBusinessTypeTF().getDocument();
        document.setDocumentFilter(new DocumentFilterVarchar(100));
    }
    
    private void setFilterOnIndustryClassificationTxtF(){
        AbstractDocument document = (AbstractDocument) panel.getBusinessClassificationTF().getDocument();
        document.setDocumentFilter(new DocumentFilterVarchar(100));
    }
    
    private void setFilterOnFiscalYearTxtF(){
        AbstractDocument document = (AbstractDocument) panel.getFiscalYearTF().getDocument();
        document.setDocumentFilter(new DocumentFilterNumeric(4));
    }
    
    private void setFilterOnTaxSchema(){
        AbstractDocument document = (AbstractDocument) panel.getTaxSchemaMatchCode().getTextField().getDocument();
        document.setDocumentFilter(new DocumentFilterNumeric(5));
    }
    
    private void setFilterOnVersionTagTxtF(){
        AbstractDocument document = (AbstractDocument) panel.getVersionTagTF().getDocument();
        document.setDocumentFilter(new DocumentFilterVarchar(20));
    }
    
    private void setFilterOnChartOfAccountNotesTxtF(){
        AbstractDocument document = (AbstractDocument) panel.getChartOfAccountNotes().getDocument();
        document.setDocumentFilter(new DocumentFilterVarchar(200));
    }
}
