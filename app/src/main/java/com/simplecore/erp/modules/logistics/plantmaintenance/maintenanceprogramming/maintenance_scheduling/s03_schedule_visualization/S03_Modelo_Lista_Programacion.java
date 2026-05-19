package com.simplecore.erp.modules.logistics.plantmaintenance.maintenanceprogramming.maintenance_scheduling.s03_schedule_visualization;

import javax.swing.JTable;
import com.simplecore.erp.gui.components.tables.lastversion.LyraTableModel;

public class S03_Modelo_Lista_Programacion {
    
    
    public static void setModelo(JTable tabla) {

        LyraTableModel modelo = new LyraTableModel();

        String[] columnasES = {"Núm. Programa","Núm. Plan","Descripción del plan","Equipo","Descripción equipo","Núm. Ejecución","Fecha programada","Fecha de Orden","Fecha de Cierre","Paquete","Descripción de programa","Valor contador","Und.Medida","Hoja ruta","Contador","Orden"};
        String[] columnasEN = {"Program No.","Plan No.","Plan Description","Team","Equipment description","No. Execution","Scheduled date","Order Date","Closing Date",	"Package","Program Description","Counter Value","Measurement unit","Task list",	"Counter","Order"};
        String[] columnasFR = {"No. de programme","No du plan",	"Description du plan","Équipe","Description de l'équipement","No. Exécution","Date prévue","Date de la commande","Date de clôture","Paquet","Description du programme",	"Valeur du compteur","Unité de mesure",	"Liste des tâches","Counter","Commande"};
        String[] columnasPT = {"No. do programa","Número do plano","Descrição do plano","Equipe","Descrição do equipamento","No. de execução","Data programada","Data do pedido","Data de fechamento","Pacote",	"Descrição do programa","Valor do contador","Unidade de medição","Lista de tarefas","Contador","Pedido"};
        String[] columnasRU = {"Программа No.",	"План No.","Описание плана","Команда","Описание оборудования","Исполнение No.",	"Планируемая дата","Дата заказа","Дата закрытия","Пакет","Описание программы","Значение счетчика","Единица измерения","Список задач","Счетчик","Заказать"};

            modelo.setColumnIdentifiers(columnasEN);
            tabla.setModel(modelo);

    }
}
