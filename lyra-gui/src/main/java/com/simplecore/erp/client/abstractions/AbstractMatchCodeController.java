
package com.simplecore.erp.client.abstractions;

import com.simplecore.erp.client.gui.components.matchcode.MatchCode;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author Michael F. Sánchez
 * @param <Service>
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public abstract class AbstractMatchCodeController<Service> {

    protected final MatchCode matchCode;
    protected final Service service;
    protected final TableSelectionListener listener;
    protected final List<Integer> visibleColumns;
    protected final SystemMessages messages = new SystemMessages();

    public AbstractMatchCodeController(MatchCode matchCode, Service service,
            TableSelectionListener listener,
            List<Integer> visibleColumns) {
        this.matchCode = matchCode;
        this.service = service;
        this.listener = listener;
        this.visibleColumns = visibleColumns;
        initializeListeners();
    }

    private void initializeListeners() {
        matchCode.getButton().addActionListener(e -> openWindow());
        matchCode.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    onClearSelection();
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String value = matchCode.getTextField().getText().trim();
                    if (value == null || value.isEmpty()) {
                        matchCode.getTextField().requestFocus();
                        messages.showErrorMsg(AppMessages.msg(AppMessages.Key.EMPTY_FIELDS));
                        onClearSelection();
                        return;
                    }
                    onSearch(value);
                }
            }
        });
    }
    private Object[] getClearArray() {
        return new Object[visibleColumns.size()]; // Arrays se inicializan con nulls
    }

    protected abstract void openWindow();
    protected abstract void onSearch(String value);
    private void onClearSelection(){
        listener.onRowSelected(getClearArray());
    }
}
