
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.listbuttons;

import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.PasteListButtonController;
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
public class ListButtonsControllerManager extends PasteListButtonController{

    private final JButton button;
    private final String titleWindow;
    private final List<Object>originalList;
    private DocumentFilter docFilter;
    private final int limit;
    
    public ListButtonsControllerManager(JButton button,String titleWindow,List<Object>originalList,DocumentFilter docFilter,int limit) {
        this.button = button;
        this.titleWindow = titleWindow;
        this.originalList = originalList;
        this.limit = limit;
        if(docFilter!=null)this.docFilter = docFilter;
        setButtonEvents();
    }
    
    private void setButtonEvents(){
        button.addActionListener(e->{
            openListWindow();
        });
    }
    private void openListWindow(){
        WindowPasteListData pasteData = new WindowPasteListData(frame, columnNames, pastedListener, columnsToCopy,originalList);
        if(docFilter!=null)pasteData.setDocumentFilter(docFilter,limit);
        pasteData.setInputSanitizer(sanitizerType);
        pasteData.setWindowTitle(titleWindow);
        pasteData.setVisible(true);
    }
    
}
