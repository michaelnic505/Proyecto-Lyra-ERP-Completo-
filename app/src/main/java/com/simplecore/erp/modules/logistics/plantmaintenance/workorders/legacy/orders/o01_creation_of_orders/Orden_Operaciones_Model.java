package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o01_creation_of_orders;

import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Orden_Operaciones_Model {

    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] activitySpanish = {
            "Oper.",
            "Procedimiento",
            "TE",
            "Descripción operación",
            "Trabajo",
            "Cantidad",
            "Duración",
            "Cod.Tip.Op.",
            "Tipo operación",
            "Costo Unit.",
            "Und Med.",
            "Monto total",
            "Paquete",
            "Sol.Ped.",
            "Moneda"
        };

        String[] activityEnglish = {
            "Oper.",
            "Procedure",
            "ET",
            "Operation Description",
            "Work",
            "Quantity",
            "Duration",
            "Op. Type Code",
            "Operation Type",
            "Unit Cost",
            "Unit of Measure",
            "Total Amount",
            "Package",
            "Order Request",
            "Currency"
        };

        String[] activityFrench = {
            "Op.",
            "Procédure",
            "TE",
            "Description de l'opération",
            "Travail",
            "Quantité",
            "Durée",
            "Code type d'op.",
            "Type d'opération",
            "Coût Unitaire",
            "Unité de mesure",
            "Montant total",
            "Paquet",
            "Demande de commande",
            "Monnaie"
        };

        String[] activityPortuguese = {
            "Op.",
            "Procedimento",
            "TE",
            "Descrição da operação",
            "Trabalho",
            "Quantidade",
            "Duração",
            "Código Tipo Op.",
            "Tipo de operação",
            "Custo Unit.",
            "Und Med.",
            "Montante total",
            "Pacote",
            "Solicitação de Pedido",
            "Moeda"
        };

            modelo.setColumnIdentifiers(activityEnglish);
            tabla.setModel(modelo);

    }
}
