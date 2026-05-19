
package com.simplecore.erp.client.gui.workspace.modules.financial.fico.controller;

import com.simplecore.erp.client.abstractions.AbstractComboController;
import com.simplecore.erp.client.gui.workspace.modules.financial.fico.services.FICORelationStatusService;
import com.simplecore.erp.client.gui.workspace.modules.financial.fico.util.ComboItem;
import java.util.stream.IntStream;
import javax.swing.JComboBox;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class FICORelationStatusController extends AbstractComboController<FICORelationStatusService, ComboItem> {

    public FICORelationStatusController(JComboBox<ComboItem> combo, FICORelationStatusService service) {
        super(combo, service);
    }

    @Override
    protected void initComboBox() {
        String[] data = service.getDataRequested();

        combo.addItem(new ComboItem(null, null, null));
        IntStream.range(0, data.length)
                .forEach(i -> {
                    String item = data[i];
                    // Puedes usar 'i' para el índice
                    combo.addItem(new ComboItem(i, item, null));
                });
        combo.setSelectedIndex(0);
    }
}
