package com.simplecore.erp.modules.system.access.legacy;

import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class AU2_Modelo_Lista_Permisos {    
    
    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Transacción",	"Nombre transacción",	"Modulo transacción",	"L1",	"L2",	"L3",	"L4",	"L5",	"R6"};
        String[] columnasEN = {"Transaction",	"Transaction name",	"Transaction module",	"L1",	"L2",	"L3",	"L4",	"L5",	"R6"};
        String[] columnasFR = {"Transaction",	"Nom de la transaction","Module de transaction","L1",	"L2",	"L3",	"L4",	"L5",	"R6"};
        String[] columnasPT = {"Transação",	"Nome da transação",	"Módulo de transação",	"L1",	"L2",	"L3",	"L4",	"L5",	"R6"};
        String[] columnasRU = {"Транзакция",	"Название транзакции",	"Модуль транзакции",	"L1",	"L2",	"L3",	"L4",	"L5",	"R6"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);

    }
}
