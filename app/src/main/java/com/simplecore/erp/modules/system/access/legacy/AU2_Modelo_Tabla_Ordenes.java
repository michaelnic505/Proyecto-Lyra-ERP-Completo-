package com.simplecore.erp.modules.system.access.legacy;

import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel2;

public class AU2_Modelo_Tabla_Ordenes {    
    
    public static void set(JTable tabla) {

        LyraTableModel2 modelo = new LyraTableModel2();

        String[] columnasES = {"Usuario",
"Transaccion",
"OCRTD",
"OIPNN",
"OUAPP",
"OAPPV",
"OSCHD",
"OIEXN",
"OEXTD",
"OCLSD",
"ORJTD",
"OCCLD"
};
        String[] columnasEN = {"User",
"Transaction",
"OCRTD",
"OIPNN",
"OUAPP",
"OAPPV",
"OSCHD",
"OIEXN",
"OEXTD",
"OCLSD",
"ORJTD",
"OCCLD"
};
        String[] columnasFR = {"User",
"Transaction",
"OCRTD",
"OIPNN",
"OUAPP",
"OAPPV",
"OSCHD",
"OIEXN",
"OEXTD",
"OCLSD",
"ORJTD",
"OCCLD"
};
        String[] columnasPT = {"Usuário",
"Transação",
"OCRTD",
"OIPNN",
"OUAPP",
"OAPPV",
"OSCHD",
"OIEXN",
"OEXTD",
"OCLSD",
"ORJTD",
"OCCLD",
};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
    }
}
