
package com.simplecore.erp.modules.logistics.plantmaintenance.strategies.legacy.news;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import com.simplecore.erp.utils.documentfilters.DocumentFilterVarchar;

public class LimitedAlphanumericCellEditor extends DefaultCellEditor {
    
    public LimitedAlphanumericCellEditor(int maxLength) {
        super(new JTextField());
        
        JTextField textField = (JTextField) getComponent();
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilterVarchar(maxLength).setUpperCase(true));
    }
    
}
