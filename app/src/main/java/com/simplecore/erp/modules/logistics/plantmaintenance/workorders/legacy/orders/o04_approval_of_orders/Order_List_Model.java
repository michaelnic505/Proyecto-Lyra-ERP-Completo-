package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.o04_approval_of_orders;

import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Order_List_Model {

    public static void set(LyraTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Orden",
            "Código estatus",
            "Descripción del estatus",
            "Parada",
            "Título",
            "Equipo",
            "Denominación del equipo",
            "Ubicación",
            "Denominación de ubicación",
            "Centro de costes",
            "Denominación del centro de costes",
            "Costo estimado $",
            "Tiempo estimado Hrs",
            "Tipo",
            "Clase",
            "Prioridad",
            "Sistema",
            "Componente",
            "Síntoma",
            "Planeado por",};
        String[] columnasEN = {"Order",
            "Status Code",
            "Status Description",
            "Stop",
            "Title",
            "Equipment",
            "Equipment Name",
            "Location",
            "Location Name",
            "Cost Center",
            "Cost Center Name",
            "Estimated Cost $",
            "Estimated Time Hrs",
            "Type",
            "Class",
            "Priority",
            "System",
            "Component",
            "Symptom",
            "Planned By",};
        String[] columnasFR = {"Ordre",
            "Code d'état",
            "Description de l'état",
            "Arrêt",
            "Titre",
            "Équipement",
            "Nom de l'équipement",
            "Emplacement",
            "Désignation de l'emplacement",
            "Centre de coûts",
            "Nom du centre de coûts",
            "Coût estimé $",
            "Temps estimé heures",
            "Gars",
            "Classe",
            "Priorité",
            "Système",
            "Composant",
            "Symptôme",
            "Planifié par",};
        String[] columnasPT = {"Ordem",
            "Código de status",
            "Descrição do status",
            "Parar",
            "Título do pedido",
            "Equipamento",
            "Nome do equipamento",
            "Localização",
            "Designação de localização",
            "Centro de custo",
            "Nome do centro de custo",
            "Custo estimado $",
            "Tempo estimado Horas",
            "Tipo",
            "Classe",
            "Prioridade",
            "Sistema",
            "Componente",
            "Sintoma",
            "Planejado por",};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
