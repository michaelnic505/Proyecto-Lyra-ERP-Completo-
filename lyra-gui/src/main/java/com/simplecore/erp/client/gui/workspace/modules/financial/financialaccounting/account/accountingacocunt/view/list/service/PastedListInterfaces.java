package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service;

import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.client.gui.windows.auxiliar.DataListPastedListener;
import com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.view.list.service.TextFieldFilterService;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */

/**
 * PastedListInterfaces es una clase que gestiona las listas de datos y los botones de filtro relacionados con diferentes 
 * campos de una cuenta, permitiendo procesar y almacenar datos que se pegan en estas listas.
 * 
 * Además, maneja la interacción con los botones de filtro, actualizando su estado según los datos disponibles.
 */
public class PastedListInterfaces {

    private List<Object> accountNumbersList;//Lista especifica para numeros de cuentas
    private List<Object> accountNameList;//Lista especifica para nombres de cuentas
    private List<Object> accountParentList;//Lista especifica para parent de cuentas
    private List<Object> accountSubclassList;//Lista especifica para subclasses de cuentas
    private List<Object> accountModelNameList; //Lista especifica para modelos a que pertenece la cuentas
    private List<Object> accountModelStatesList; //Lista especifica para estados del modelo
    private List<Object> accountCreatedByList; //Lista especifica para lista de usuario que crearon la cuenta
    private List<Object> accountCreatedAtList;//Lista especifica para lista de fecha en que crearon la cuenta
    private List<Object> accountUpdatedByList;//Lista especifica para lista de usuario que modificaron la cuenta
    private List<Object> accountUpdatedAtList;//Lista especifica para lista de fecha en la que actualizaron la cuenta        

    public PastedListInterfaces(Builder builder) {
        this.accountNumbersList = new ArrayList<>();
        this.accountNameList = new ArrayList<>();
        this.accountParentList = new ArrayList<>();
        this.accountSubclassList = new ArrayList<>();
        this.accountModelNameList = new ArrayList<>();
        this.accountModelStatesList = new ArrayList<>();
        this.accountCreatedByList = new ArrayList<>();
        this.accountCreatedAtList = new ArrayList<>();
        this.accountUpdatedByList = new ArrayList<>();
        this.accountUpdatedAtList = new ArrayList<>();
        this.accountNumberFilterButton = builder.accountNumberFilterButton;
        this.accountNameFilterButton = builder.accountNameFilterButton;
        this.parentFilterButton = builder.parentFilterButton;
        this.subclassFilterButton = builder.subclassFilterButton;
        this.modelFilterButton = builder.modelFilterButton;
        this.modelStateFilterButton = builder.modelStateFilterButton;
        this.createdByFilterButton = builder.createdByFilterButton;
        this.createdAtAccountFilterButton = builder.createdAtAccountFilterButton;
        this.updatedByAccountFilterButton = builder.updatedByAccountFilterButton;
        this.updatedAtAccountFilterButton = builder.updatedAtAccountFilterButton;
        this.textFieldService = builder.textFieldService;
    }
    
    public void setTextFieldService(TextFieldFilterService textFieldService) {
        this.textFieldService = textFieldService;
    }

    //metodos de retorno de la listas
    public List<Object> accountNumbersList(){return accountNumbersList;}
    public List<Object> accountNameList(){return accountNameList;}
    public List<Object> accountParentList(){return accountParentList;}
    public List<Object> accountSubclassList(){return accountSubclassList;}
    public List<Object> accountModelNameList(){return accountModelNameList;}
    public List<Object> accountModelStatesList(){return accountModelStatesList;}
    public List<Object> accountCreatedByList(){return accountCreatedByList;}
    public List<Object> accountCreatedAtList(){return accountCreatedAtList;}
    public List<Object> accountUpdatedByList(){return accountUpdatedByList;}
    public List<Object> accountUpdatedAtList(){return accountUpdatedAtList;}
    
    //metodos de retorno de botones
    public JButton accountNumberFilterButton(){return accountNumberFilterButton;};
    public JButton accountNameFilterButton(){return accountNameFilterButton;};
    public JButton parentFilterButton(){return parentFilterButton;};
    public JButton subclassFilterButton(){return subclassFilterButton;};
    public JButton modelFilterButton(){return modelFilterButton;};
    public JButton modelStateFilterButton(){return modelStateFilterButton;};
    public JButton createdByFilterButton(){return createdByFilterButton;};
    public JButton createdAtAccountFilterButton(){return createdAtAccountFilterButton;};
    public JButton updatedByAccountFilterButton(){return updatedByAccountFilterButton;};
    public JButton updatedAtAccountFilterButton(){return updatedAtAccountFilterButton;};
    
    private JButton accountNumberFilterButton;
    private JButton accountNameFilterButton;
    private JButton parentFilterButton;                
    private JButton subclassFilterButton;
    private JButton modelFilterButton;
    private JButton modelStateFilterButton;
    private JButton createdByFilterButton;
    private JButton createdAtAccountFilterButton;
    private JButton updatedByAccountFilterButton;
    private JButton updatedAtAccountFilterButton;
    private TextFieldFilterService textFieldService;

    public static class Builder {

        private JButton accountNumberFilterButton;
        private JButton accountNameFilterButton;
        private JButton parentFilterButton;
        private JButton subclassFilterButton;
        private JButton modelFilterButton;
        private JButton modelStateFilterButton;
        private JButton createdByFilterButton;
        private JButton createdAtAccountFilterButton;
        private JButton updatedByAccountFilterButton;
        private JButton updatedAtAccountFilterButton;
        private TextFieldFilterService textFieldService;
        
        public Builder textFieldService(TextFieldFilterService textFieldService){
            this.textFieldService = textFieldService;
            return this;
        }

        public Builder accountNumberFilterButton(JButton button) {
            this.accountNumberFilterButton = button;
            return this;
        }

        public Builder accountNameFilterButton(JButton button) {
            this.accountNameFilterButton = button;
            return this;
        }

        public Builder parentFilterButton(JButton button) {
            this.parentFilterButton = button;
            return this;
        }

        public Builder subclassFilterButton(JButton button) {
            this.subclassFilterButton = button;
            return this;
        }

        public Builder modelFilterButton(JButton button) {
            this.modelFilterButton = button;
            return this;
        }

        public Builder modelStateFilterButton(JButton button) {
            this.modelStateFilterButton = button;
            return this;
        }

        public Builder createdByFilterButton(JButton button) {
            this.createdByFilterButton = button;
            return this;
        }

        public Builder createdAtAccountFilterButton(JButton button) {
            this.createdAtAccountFilterButton = button;
            return this;
        }

        public Builder updatedByAccountFilterButton(JButton button) {
            this.updatedByAccountFilterButton = button;
            return this;
        }

        public Builder updatedAtAccountFilterButton(JButton button) {
            this.updatedAtAccountFilterButton = button;
            return this;
        }

        public PastedListInterfaces build() {
            return new PastedListInterfaces(this);
        }
    }

    // Returns the listener for pasting account numbers
    public DataListPastedListener accountNumbersPasted() {return accountNumbersPasted;}
    public DataListPastedListener accountNamesPasted() {return accountNamesPasted;}
    public DataListPastedListener accountParentPasted() {return accountParentPasted;}
    public DataListPastedListener accountSubclassPasted() {return accountSubclassPasted;}
    public DataListPastedListener accountModelNamePasted() {return accountModelNamePasted;}
    public DataListPastedListener accountModelStatesPasted() {return accountModelStatesPasted;}
    public DataListPastedListener accountCreatedByPasted() {return accountCreatedByPasted;}
    public DataListPastedListener accountCreatedAtPasted() {return accountCreatedAtPasted;}
    public DataListPastedListener accountUpdatedByPasted() {return accountUpdatedByPasted;}
    public DataListPastedListener accountUpdatedAtPasted() {return accountUpdatedAtPasted;}

    // Handles the pasted account numbers and processes the data
    private DataListPastedListener accountNumbersPasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            // Processes the pasted data and updates the account number list
            processData(dataList, accountNumbersList, accountNumberFilterButton);
            textFieldService.setFirstValueOnMatchCode(accountNumbersList, textFieldService.accountNumberFromMatchCode, textFieldService.accountNumberToMatchCode);
        }
    };
    // Handles the pasted account names and processes the data
    private DataListPastedListener accountNamesPasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            processData(dataList, accountNameList, accountNameFilterButton);
           textFieldService.setFirstValueOnMatchCode(accountNameList, textFieldService.accountNameFrom, textFieldService.accountNameTo);
        }
    };
    // Handles the pasted account parent data and processes the data
    private DataListPastedListener accountParentPasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            processData(dataList, accountParentList, parentFilterButton);
           textFieldService.setFirstValueOnMatchCode(accountParentList, textFieldService.parentFromMatchCode, textFieldService.parentToMatchCode);
        }
    };
    // Handles the pasted account subclass data and processes the data
    private DataListPastedListener accountSubclassPasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            processData(dataList, accountSubclassList, subclassFilterButton);
           textFieldService.setFirstValueOnMatchCode(accountSubclassList, textFieldService.subclassFromMatchCode, textFieldService.subclassToMatchCode);
        }
    };
    // Handles the pasted account model names and processes the data
    private DataListPastedListener accountModelNamePasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            processData(dataList, accountModelNameList, modelFilterButton);
           textFieldService.setFirstValueOnMatchCode(accountModelNameList, textFieldService.modelFromMatchCode, textFieldService.modelToMatchCode);
        }
    };
    // Handles the pasted account model states and processes the data
    private DataListPastedListener accountModelStatesPasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            processData(dataList, accountModelStatesList, modelStateFilterButton);
            textFieldService.setFirstValueOnMatchCode(accountModelStatesList, textFieldService.modelStateFromMatchCode, textFieldService.modelStateToMatchCode);
        }
    };
    // Handles the pasted account creator information and processes the data
    private DataListPastedListener accountCreatedByPasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            processData(dataList, accountCreatedByList, createdByFilterButton);
            textFieldService.setFirstValueOnMatchCode(accountCreatedByList, textFieldService.createdByFromMatchCode, textFieldService.createdByToMatchCode);
        }
    };
    // Handles the pasted account creation date and processes the data
    private DataListPastedListener accountCreatedAtPasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            processData(dataList, accountCreatedAtList, createdAtAccountFilterButton);
            textFieldService.setFirstValueOnMatchCode(accountCreatedAtList, textFieldService.createdAtFromChooser, textFieldService.createdAtToChooser);
        }
    };
    // Handles the pasted account updater information and processes the data
    private DataListPastedListener accountUpdatedByPasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            processData(dataList, accountUpdatedByList, updatedByAccountFilterButton);
            textFieldService.setFirstValueOnMatchCode(accountUpdatedByList, textFieldService.updatedByFromMatchCode, textFieldService.updatedByToMatchCode);
        }
    };
    // Handles the pasted account update date and processes the data
    private DataListPastedListener accountUpdatedAtPasted = new DataListPastedListener() {
        @Override
        public void OnDataPasted(Object[][] dataList) {
            processData(dataList, accountUpdatedAtList, updatedAtAccountFilterButton);
           textFieldService.setFirstValueOnMatchCode(accountUpdatedAtList, textFieldService.updatedAtFromChooser, textFieldService.updatedAtToChooser);
        }
    };

    
    private void processData(Object[][] dataList, List<Object> targetList, JButton filterButton) {
        // Solo iteramos si hay datos en dataList
        for (int row = 0; row < dataList.length; row++) {
            if (dataList[row][0] != null) {  // Verificamos que no sea nulo antes de agregar
                targetList.add(dataList[row][0].toString());  // Convertimos a String solo si es necesario
            }
        }
        // Activar o desactivar el icono del filtro según la lista
        setAddFilterIcon(filterButton, !targetList.isEmpty());
    }

    private void setAddFilterIcon(JButton button, boolean filterAdded) {
        if (filterAdded) {
            button.setIcon(new CustomSVGIcon("/icons/svg/filter_ready.svg", new Dimension(24, 24)));
            return;
        }
        button.setIcon(new CustomSVGIcon("/icons/svg/filter_add.svg", new Dimension(24, 24)));
    }
}
