package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy.utils;


import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class Modelo_Lista_Equipos {

    public static void setLanguage(JTable tabla) {
        
        LyraTableModel model = new LyraTableModel();
        
        String[] ES = {"Equipo",
                        "Denominación",
                        "Estatus",
                        "Descripción estatus",
                        "Tipo",
                        "Descripción tipo",
                        "Prioridad",
                        "Descripción prioridad",
                        "Marca",
                        "Modelo",
                        "Serie",
                        
                        "Sociedad",
                        "Descripción sociedad",
                        
                        "Activo fijo",
                        "Descripcion de activo",
                        
                        "Centro coste",
                        "Descripción centro coste",
                        
                        "Emplazamiento",
                        "Denominación emplazamiento",
                        
                        "Área",
                        "Descripción área",
                        
                        "Equipo superior",
                        "Denominación equipo superior",
                        
                        "Ubicación",
                        "Denominación ubicación",
                        
                        "Creado por",
                        "Fecha creación",
                        "Modificado por",
                        
                        "Fecha modificación",
                        "Gp.Planif.",
                        "Descripcion Gp.Planif"
                
                        };
        
        
        String[] EN = {"Equipment",
                        "Name",
                        "Status",
                        "Status description",
                        "Type",
                        "Type description",
                        "Priority",
                        "Priority description",
                        "Brand",
                        "Model",
                        "Series",
                        
                        "Company",
                        "Company name",
                        
                        "Fixed asset",
                        "Fixed asset description",
                        
                        "Cost center",
                        "Cost center description",
                        
                        "Emplazament",
                        "Emplazament description",
                        
                        "Area",
                        "Area description",
                        
                        "Superior equipment",
                        "Superior equipment name",
                        
                        "Location",
                        "Location name",
                        "Created by",
                        "Creation date",
                        "Modified by",
                        "Modification date",
                        
                        "Plann.Group",
                        "Description Plann.G."};


        String[] FR = {"Équipe",
                        "Dénomination",
                        "Statut",
                        "Statut de la description",
                        "Type",
                        "Type de description",
                        "Priorité",
                        "Description priorité",
                        "Marca",
                        "Modèle",
                        "Série",

                        "Société",
                        "Description de la société",

                        "Activer fijo",
                        "Description de l'activité",

                        "Centre Coste",
                        "Description du centre de coût",

                        "Emplacement",
                        "Dénomination emplazamiento",

                        "Zone",
                        "Zone de description",

                        "Équipe supérieure",
                        "Dénomination équipe supérieure",

                        "Ubicación",
                        "Dénomination ubicación",

                        "Créé pour",
                        "Fécha création",
                        "Modifié par",
                        
                        "Modification Fecha",
                        
                        "Plann.Group",
                        "Description Plann.G."};
        
        
        String[] PT = {"Equipamento",
                        "Denominação",
                        "Estado",
                        "Status da descrição",
                        "Tipo",
                        "Tipo de descrição",
                        "Prioridade",
                        "Descrição prioritária",
                        "Marca",
                        "Modelo",
                        "Série",

                        "Sociedade",
                        "Descrição da sociedade",

                        "Ativo fijo",
                        "Descrição da atividade",

                        "Centro Costa",
                        "Descrição do centro de custo",

                        "Emplacamento",
                        "Denominação de colocação",

                        "Área",
                        "Área de descrição",

                        "Equipamento superior",
                        "Denominação de equipamento superior",

                        "Ubicação",
                        "Denominação de localização",

                        "Criado por",
                        "Criação Fecha",
                        "Modificado por",
                        
                        "Modificação Fecha",
                        "Plann.Grupo",
                        "Descrição Plann.G."};

        
        String[] RU = {"Экипо",
                        "Деноминация",
                        "Эстатус",
                        "Статус описания",
                        "Типо",
                        "Описание типо",
                        "Приоридад",
                        "Приоритет описания",
                        "Марка",
                        "Модель",
                        "Ряд",

                        "Сосьедад",
                        "Описание общества",

                        "Активо фихо",
                        "Описание деятельности",

                        "Центро Косте",
                        "Описание центра затрат",

                        "Эмплазамиенто",
                        "Деноминационная установка",

                        "Область",
                        "Область описания",

                        "Экипо Супериор",
                        "Деноминация высшего оборудования",

                        "Убикасьон",
                        "Убикасион номинала",

                        "Креадо пор",
                        "Изменено",
                        "Творение Фечи",
                        "Модификация Феча"
                       };


        String[] DE = {"Ausrüstung",
                        "Konfession",
                        "Estatus",
                        "Descripción estatus",
                        "Tipo",
                        "Beschreibung Tipo",
                        "Prioridad",
                        "Priorität der Beschreibung",
                        "Marca",
                        "Modell",
                        "Serie",

                        "Gesellschaft",
                        "Descripción sociedad",

                        "Activo Fijo",
                        "Beschreibung der Tätigkeit",

                        "Centro coste",
                        "Beschreibung Centro Coste",

                        "Emplazamiento",
                        "Denomination emplazamiento",

                        "Bereich",
                        "Descripción área",

                        "Ausrüstung überlegen",
                        "Denominación equipo superior",

                        "Ubicación",
                        "Denominación ubicación",

                        "Creado por",
                        "Modificado por",
                        "Fecha creación",
                        "Fecha-Modifikation",
                       };

            model.setColumnIdentifiers(EN);
     
        tabla.setModel(model);
        
    }
}
