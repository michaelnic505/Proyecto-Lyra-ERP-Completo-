package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Modelo_Lista_Ubicaciones {
    

    public static void set(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Código ubic.","Descripción de Ubicación","Código ubic. sup.",
                                "Descripción de Ubicación superior","Centro Costo","Descripción CC",
                                "Emplazamiento","Descripción emplazamiento","Área",
                                "Descripción área","Sociedad","Descripción sociedad","Gr.Planif","Descripción g.Planif",
                                "Nivel","Montaje","Estatus"};
        
        String[] columnasEN = {"Location Code","Location Description","Upper Location Code",
                                "Upper Location Description","Cost Center","CC Description",
                                "Location","Location description","Area",
                                "Area description","Company","Company description","Plann.Group","Planning group description",
                                "Level","Assembly","Status"};
        
        String[] columnasFR = {"Code de localisation", "Description de la localisation", "Code de localisation supérieur",
                                "Description de l'emplacement supérieur", "Centre de coûts", "Description CC",
                                "Emplacement", "Description de l'emplacement", "Zone",
                                "Description de la zone","Entreprise","Description de l'entreprise","Gr.planif.","Description G.Planif",
                                "Niveau", "Assemblage", "Statut"};
        
        
        String[] columnasPT = {"Código de localização","Descrição do local","Código de localização superior",
                                "Descrição da Localização Superior","Centro de Custo","Descrição CC",
                                "Localização","Descrição da localização","Área",
                                "Descrição da área","Empresa","Descrição da empresa","Gr.Planej.","Descrição G.Planif",
                                "Nível","Montagem","Status"};
        
        String[] columnasAL = {"Standortcode", "Standortbeschreibung", "Oberer Standortcode",
                                "Obere Standortbeschreibung", "Kostenstelle", "CC-Beschreibung",
                                "Standort", "Standortbeschreibung", "Gebiet",
                                "Bereichsbeschreibung", "Firma", "Firmenbeschreibung",
                                "Ebene", "Montage", "Status","Grupo de Planejamento",
"Descrição G.Planif"};
        
            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);
        
    }
}
