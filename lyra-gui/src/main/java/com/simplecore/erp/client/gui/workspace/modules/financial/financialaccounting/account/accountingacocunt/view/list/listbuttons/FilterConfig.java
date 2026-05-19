
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.listbuttons;

import com.simplecore.erp.client.gui.windows.auxiliar.DataListPastedListener;
import com.simplecore.erp.client.utils.documentfilters.TextSanitizer;
import java.util.List;
import javax.swing.JButton;
import javax.swing.text.DocumentFilter;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FilterConfig {

    public JButton button;
    public String title;
    public DataListPastedListener listener;
    public List<Object> dataList;
    public DocumentFilter filter;
    public int limit;
    public TextSanitizer.Types sanitizerType;

    public FilterConfig(JButton button, String title, DataListPastedListener listener, List<Object> dataList, DocumentFilter filter, int limit, TextSanitizer.Types sanitizerType) {
        this.button = button;
        this.title = title;
        this.listener = listener;
        this.dataList = dataList;
        this.filter = filter;
        this.limit = limit;
        this.sanitizerType = sanitizerType;
    }
}
