package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.tratamiento_de_listas.aprobacion_ordenes;

import com.simplecore.erp.gui.components.tables.lastversion.LyraTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel2;

public class Order_List_Model_List {

    public static void set(LyraTable tabla) {

        LyraTableModel2 modelo = new LyraTableModel2();

        String[] columnasES = {"P","Orden",
            "Código estatus",
            "Descripción del estatus",
            "Título",
            "Fecha inicio prog.",
            "Fecha fin prog.",
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
        String[] columnasEN = {"","Order",
            "Status Code",
            "Status Description",
            "Title",
            "Scheduled Start Date",
            "Scheduled End Date",
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
        String[] columnasFR = {"","Ordre",
            "Code d'état",
            "Description de l'état",
            "Titre",
            "Date de début prévue",
            "Date de fin prévue",
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
        String[] columnasPT = {"","Ordem",
            "Código de status",
            "Descrição do status",
            "Título do pedido",
            "Data de início agendada",
            "Data final programada",
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
