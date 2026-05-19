package com.simplecore.erp.modules.logistics.plantmaintenance.workorders.legacy.orders.auxiliares;

import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Lista_Ordenes_Modelo {

    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Orden",
            "Estatus",
            "Descripción estatus",
            "Titulo",
            "Equipo",
            "Denominación del equipo",
            "Ubicación",
            "Denominación de ubicación",
            };
        String[] columnasEN = {"Order",
            "Status",
            "Status description",
            "Title",
            "Equipment",
            "Equipment name",
            "Location",
            "Location designation"};
        String[] columnasFR = {"Commande",
            "Statut",
            "Description de l'état",
            "Titre",
            "Équipement",
            "Nom de l'équipe",
            "Emplacement",
            "Désignation du lieu",};
        String[] columnasPT = {"Ordem",
            "Status",
            "Descrição do estado",
            "Título",
            "Equipamento",
            "Nome da equipe",
            "Localização",
            "Designação de localização"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
