package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;

import com.simplecore.erp.config.database.PooledConnectionService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;
import com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy.UbicationType;

public class E01_Create_Equipment_Locations {

    public String getGrupoPlasnif() {
        return grupoPlasnif;
    }

    public String getDescripcionGrupoPlanif() {
        return descripcionGrupoPlanif;
    }

    public void setGrupoPlasnif(String grupoPlasnif) {
        this.grupoPlasnif = grupoPlasnif;
    }

    public void setDescripcionGrupoPlanif(String descripcionGrupoPlanif) {
        this.descripcionGrupoPlanif = descripcionGrupoPlanif;
    }

    public String getID_UBICACION() {
        return ID_UBICACION;
    }

    public String getDENOMINACION_UBICACION() {
        return DENOMINACION_UBICACION;
    }

    public String getID_UBICACION_SUP() {
        return ID_UBICACION_SUP;
    }

    public String getDENOMINACION_UBICACION_SUP() {
        return DENOMINACION_UBICACION_SUP;
    }

    public int getNIVEL() {
        return NIVEL;
    }

    public boolean isMONTAJE() {
        return MONTAJE;
    }

    public boolean isESTATUS() {
        return ESTATUS;
    }

    public void setID_UBICACION(String ID_UBICACION) {
        this.ID_UBICACION = ID_UBICACION;
    }

    public void setDENOMINACION_UBICACION(String DENOMINACION_UBICACION) {
        this.DENOMINACION_UBICACION = DENOMINACION_UBICACION;
    }

    public void setID_UBICACION_SUP(String ID_UBICACION_SUP) {
        this.ID_UBICACION_SUP = ID_UBICACION_SUP;
    }

    public void setDENOMINACION_UBICACION_SUP(String DENOMINACION_UBICACION_SUP) {
        this.DENOMINACION_UBICACION_SUP = DENOMINACION_UBICACION_SUP;
    }

    public void setNIVEL(int NIVEL) {
        this.NIVEL = NIVEL;
    }

    public void setMONTAJE(boolean MONTAJE) {
        this.MONTAJE = MONTAJE;
    }

    public void setESTATUS(boolean ESTATUS) {
        this.ESTATUS = ESTATUS;
    }

    private String ID_UBICACION;
    private String DENOMINACION_UBICACION;
    private String ID_UBICACION_SUP;
    private String DENOMINACION_UBICACION_SUP;
    private int NIVEL;
    private boolean MONTAJE;
    private boolean ESTATUS;
    private String grupoPlasnif;
    private String descripcionGrupoPlanif;

    public void crearUbicacion() {

        try {
            
            Connection conexion = PooledConnectionService.getConnection();
            PreparedStatement pSt = null;
            
            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.LOCATIONS.tableName()
                    + SentenceValues.setValues(18);

            pSt = conexion.prepareStatement(query);

            pSt.setString(1, getID_UBICACION());
            pSt.setString(2, getDENOMINACION_UBICACION());
            pSt.setString(3, getID_UBICACION_SUP());
            pSt.setString(4, getDENOMINACION_UBICACION_SUP());
            pSt.setBoolean(5, isMONTAJE());
            pSt.setInt(6, getNIVEL());
            pSt.setBoolean(7, isESTATUS());
            pSt.setString(8, UbicationType.E.toString());
            pSt.setString(9, null);
            pSt.setString(10, null);
            pSt.setString(11, null);
            pSt.setString(12, null);
            pSt.setString(13, null);
            pSt.setString(14, null);
            pSt.setString(15, null);
            pSt.setString(16, null);
            pSt.setString(17,getGrupoPlasnif());
            pSt.setString(18, getDescripcionGrupoPlanif());


            pSt.executeUpdate();
            pSt.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(E01_Create_Equipment_Locations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
