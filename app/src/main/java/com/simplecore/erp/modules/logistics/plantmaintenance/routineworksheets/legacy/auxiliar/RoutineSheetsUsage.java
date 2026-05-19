package com.simplecore.erp.modules.logistics.plantmaintenance.routineworksheets.legacy.auxiliar;

import com.simplecore.erp.gui.workspace.LyraWorkspace;

public enum RoutineSheetsUsage {

    MAINTENANCE(1, LyraWorkspace.getTableTranslator().getTranslation("usage.maintenance")),
    DESIGN(2, LyraWorkspace.getTableTranslator().getTranslation("usage.design")),
    MANUFACTURING(3, LyraWorkspace.getTableTranslator().getTranslation("usage.manufacturing")),
    CIVIL_WORKS(4, LyraWorkspace.getTableTranslator().getTranslation("usage.civilWorks"));

    private int id;
    private String description;

    private RoutineSheetsUsage(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Método para recuperar la descripción por el ID
    public static String getDescriptionById(int id) {
        for (RoutineSheetsUsage usage : values()) {
            if (usage.getId() == id) {
                return usage.getDescription();
            }
        }
        return null; // Si no se encuentra el ID, se retorna null o puedes lanzar una excepción si prefieres.
    }
}
