package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.dao;

import com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.model.Characteristic;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @Michael F. Sanchez
 */
public interface CharacteristicDAO {

    void insert(Characteristic characteristic);
    void update(String characteristicCode, Characteristic characteristic);
    void delete(String characteristic);
    Characteristic getCharacteristicData(String characteristicCode);
    boolean exists(String characteristicCode);
    void loadDataInTable(DefaultTableModel table);
    
}

