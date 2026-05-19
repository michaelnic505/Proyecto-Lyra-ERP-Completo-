
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service;

import com.simplecore.erp.client.gui.windows.auxiliar.DataListPastedListener;
import com.simplecore.erp.client.utils.documentfilters.TextSanitizer;
import java.awt.Frame;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public abstract class PasteListButtonController {

    protected Frame frame;
    protected DataListPastedListener pastedListener;
    protected List columnsToCopy;
    protected String[]columnNames;
    protected TextSanitizer.Types sanitizerType;
    
    public void initialize(String[]columnNames,Frame frame, DataListPastedListener pastedListener, 
            List columnsToCopy,TextSanitizer.Types sanitizerType) {
        this.frame = frame;
        this.pastedListener = pastedListener;
        this.columnsToCopy = columnsToCopy;
        this.columnNames = columnNames;
        this.sanitizerType = sanitizerType;
    }
}
