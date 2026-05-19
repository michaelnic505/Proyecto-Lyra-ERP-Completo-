
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service;

import com.simplecore.erp.client.gui.windows.auxiliar.DataListPastedListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.listbuttons.FilterConfig;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.listbuttons.ListButtonsControllerManager;
import com.simplecore.erp.client.utils.documentfilters.DocFilterVarcharWithoutSpace;
import com.simplecore.erp.client.utils.documentfilters.DocumentFilterVarchar;
import com.simplecore.erp.client.utils.documentfilters.TextSanitizer;
import java.awt.Frame;
import java.util.Arrays;
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
public class ListButtonService {

    private JButton accountNumberFilterButton;
    private JButton accountNameFilterButton;
    private JButton parentFilterButton;
    private JButton subclassFilterButton;
    private JButton modelFilterButton;
    private JButton modelStateFilterButton;
    private JButton createdByFilterButton;
    private JButton createdAtFilterButton;
    private JButton updatedByFilterButton;
    private JButton updatedAtFilterButton;

    private String accountNumberLb;
    private String accountNameLb;
    private String accountParentLb;
    private String accountSubclassLb;
    private String modelNameLb;
    private String modelStateLb;
    private String createdByLb;
    private String createdAtLb;
    private String updatedByLb;
    private String updatedAtLb;
    
    private PastedListInterfaces pastedListInterfaces;

    private ListButtonService(Builder builder) {
        this.accountNumberFilterButton = builder.accountNumberFilterButton;
        this.accountNameFilterButton = builder.accountNameFilterButton;
        this.parentFilterButton = builder.parentFilterButton;
        this.subclassFilterButton = builder.subclassFilterButton;
        this.modelFilterButton = builder.modelFilterButton;
        this.modelStateFilterButton = builder.modelStateFilterButton;
        this.createdByFilterButton = builder.createdByFilterButton;
        this.createdAtFilterButton = builder.createdAtFilterButton;
        this.updatedByFilterButton = builder.updatedByFilterButton;
        this.updatedAtFilterButton = builder.updatedAtFilterButton;

        this.accountNumberLb = builder.accountNumberLb;
        this.accountNameLb = builder.accountNameLb;
        this.accountParentLb = builder.accountParentLb;
        this.accountSubclassLb = builder.accountSubclassLb;
        this.modelNameLb = builder.modelNameLb;
        this.modelStateLb = builder.modelStateLb;
        this.createdByLb = builder.createdByLb;
        this.createdAtLb = builder.createdAtLb;
        this.updatedByLb = builder.updatedByLb;
        this.updatedAtLb = builder.updatedAtLb;
        this.pastedListInterfaces = builder.pastedListInterfaces;
        
        initializeListButtonsControllers();
    }

    public static class Builder {

        private JButton accountNumberFilterButton;
        private JButton accountNameFilterButton;
        private JButton parentFilterButton;
        private JButton subclassFilterButton;
        private JButton modelFilterButton;
        private JButton modelStateFilterButton;
        private JButton createdByFilterButton;
        private JButton createdAtFilterButton;
        private JButton updatedByFilterButton;
        private JButton updatedAtFilterButton;

        private String accountNumberLb;
        private String accountNameLb;
        private String accountParentLb;
        private String accountSubclassLb;
        private String modelNameLb;
        private String modelStateLb;
        private String createdByLb;
        private String createdAtLb;
        private String updatedByLb;
        private String updatedAtLb;
        
        private PastedListInterfaces pastedListInterfaces;
        
        public Builder pastedListInterfaces(PastedListInterfaces pastedListInterfaces){
            this.pastedListInterfaces = pastedListInterfaces;
            return this;
        }

        public Builder accountNumber(JButton button, String label) {
            this.accountNumberFilterButton = button;
            this.accountNumberLb = label;
            return this;
        }

        public Builder accountName(JButton button, String label) {
            this.accountNameFilterButton = button;
            this.accountNameLb = label;
            return this;
        }

        public Builder parent(JButton button, String label) {
            this.parentFilterButton = button;
            this.accountParentLb = label;
            return this;
        }

        public Builder subclass(JButton button, String label) {
            this.subclassFilterButton = button;
            this.accountSubclassLb = label;
            return this;
        }

        public Builder model(JButton button, String label) {
            this.modelFilterButton = button;
            this.modelNameLb = label;
            return this;
        }

        public Builder modelState(JButton button, String label) {
            this.modelStateFilterButton = button;
            this.modelStateLb = label;
            return this;
        }

        public Builder createdBy(JButton button, String label) {
            this.createdByFilterButton = button;
            this.createdByLb = label;
            return this;
        }

        public Builder createdAt(JButton button, String label) {
            this.createdAtFilterButton = button;
            this.createdAtLb = label;
            return this;
        }

        public Builder updatedBy(JButton button, String label) {
            this.updatedByFilterButton = button;
            this.updatedByLb = label;
            return this;
        }

        public Builder updatedAt(JButton button, String label) {
            this.updatedAtFilterButton = button;
            this.updatedAtLb = label;
            return this;
        }

        public ListButtonService build() {
            return new ListButtonService(this);
        }
    }

    private void initializeListButtonsControllers() {
        // Tu método existente que usa los labels y botones
        var frame = Workspace.getFrame();

        List<FilterConfig> filterConfigs = Arrays.asList(
                new FilterConfig(accountNumberFilterButton, accountNumberLb, pastedListInterfaces.accountNumbersPasted(), pastedListInterfaces.accountNumbersList(), filter45, 45, TextSanitizer.Types.VARCHAR_NO_SPACED),
                new FilterConfig(accountNameFilterButton, accountNameLb, pastedListInterfaces.accountNamesPasted(), pastedListInterfaces.accountNameList(), filter100, 100, TextSanitizer.Types.VARCHAR_SPACED),
                new FilterConfig(parentFilterButton, accountParentLb, pastedListInterfaces.accountParentPasted(), pastedListInterfaces.accountParentList(), filter45, 45, TextSanitizer.Types.VARCHAR_SPACED),
                new FilterConfig(subclassFilterButton, accountSubclassLb, pastedListInterfaces.accountSubclassPasted(), pastedListInterfaces.accountSubclassList(), filter45, 45, TextSanitizer.Types.VARCHAR_NO_SPACED),
                new FilterConfig(modelFilterButton, modelNameLb, pastedListInterfaces.accountModelNamePasted(), pastedListInterfaces.accountModelNameList(), filter45, 45, TextSanitizer.Types.VARCHAR_SPACED),
                new FilterConfig(modelStateFilterButton, modelStateLb, pastedListInterfaces.accountModelStatesPasted(), pastedListInterfaces.accountModelStatesList(), filter45, 45, TextSanitizer.Types.VARCHAR_NO_SPACED_UPPERCASE),
                new FilterConfig(createdByFilterButton, createdByLb, pastedListInterfaces.accountCreatedByPasted(), pastedListInterfaces.accountCreatedByList(), filter45, 45, TextSanitizer.Types.VARCHAR_NO_SPACED),
                new FilterConfig(createdAtFilterButton, createdAtLb, pastedListInterfaces.accountCreatedAtPasted(), pastedListInterfaces.accountCreatedAtList(), filter100, 100, TextSanitizer.Types.VARCHAR_SPACED),
                new FilterConfig(updatedByFilterButton, updatedByLb, pastedListInterfaces.accountUpdatedByPasted(), pastedListInterfaces.accountUpdatedByList(), filter45, 45, TextSanitizer.Types.VARCHAR_NO_SPACED),
                new FilterConfig(updatedAtFilterButton, updatedAtLb, pastedListInterfaces.accountUpdatedAtPasted(), pastedListInterfaces.accountUpdatedAtList(), filter100, 100, TextSanitizer.Types.VARCHAR_SPACED)
        );

        for (FilterConfig config : filterConfigs) {
            setupListButtonsControllers(config.button, config.title, new String[]{config.title}, frame, config.listener, config.dataList, config.filter, config.limit, config.sanitizerType);
        }
    }

    DocumentFilter filter45 = new DocFilterVarcharWithoutSpace(45);
    DocumentFilter filter100 = new DocumentFilterVarchar(100);

    private void setupListButtonsControllers(JButton button, String title, String[] columns, Frame frame, DataListPastedListener listener, List<Object> dataList,
            DocumentFilter filter, int limit, TextSanitizer.Types sanitizerType) {

        var accountNameListButtonController = new ListButtonsControllerManager(button, title, dataList, filter, limit);
        accountNameListButtonController.initialize(columns, frame, listener, List.of(0), sanitizerType);
    }

}
