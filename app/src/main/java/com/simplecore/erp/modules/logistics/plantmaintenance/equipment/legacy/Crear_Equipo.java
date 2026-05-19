package com.simplecore.erp.modules.logistics.plantmaintenance.equipment.legacy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simplecore.erp.config.database.PooledConnectionService;
import com.simplecore.erp.config.database.utils.SQLKeywords;
import com.simplecore.erp.config.database.utils.SentenceValues;
import com.simplecore.erp.config.database.DatabaseTables;

public class Crear_Equipo {

    public String getGrupoPlanificacion() {
        return grupoPlanificacion;
    }

    public String getDescripcionGruopoPlanif() {
        return descripcionGruopoPlanif;
    }

    public void setGrupoPlanificacion(String grupoPlanificacion) {
        this.grupoPlanificacion = grupoPlanificacion;
    }

    public void setDescripcionGruopoPlanif(String descripcionGruopoPlanif) {
        this.descripcionGruopoPlanif = descripcionGruopoPlanif;
    }

    public String getFixed_assetsName() {
        return fixed_assetsName;
    }

    public void setFixed_assetsName(String fixed_assetsName) {
        this.fixed_assetsName = fixed_assetsName;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public int getYear_construction() {
        return year_construction;
    }

    public int getMonth_construction() {
        return month_construction;
    }

    public void setYear_construction(int year_construction) {
        this.year_construction = year_construction;
    }

    public void setMonth_construction(int month_construction) {
        this.month_construction = month_construction;
    }

    public String getManufacture_date() {
        return manufacture_date;
    }

    public void setManufacture_date(String manufacture_date) {
        this.manufacture_date = manufacture_date;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public String getModification_date() {
        return modification_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public void setModification_date(String modification_date) {
        this.modification_date = modification_date;
    }



    private int equipment_id;
    private String name;
    private String status_id;
    private String status_name;

    private String type_equipment_id;
    private String type_name;
    private String criticality_id;
    private String criticality_name;
    private String brand;
    private String model;
    private String serial;
    private String manufacture_date;
    private String dimensions;
    private String commissioning_date;

    private String acquisition_value;
    private String currency;
    private String acquisition_date;

    private String manufacturer;
    private String manufacturer_type;
    private String part_number;
    private String manu_serial_number;
    private String producing_country;
    private int year_construction;
    private int month_construction;

    private String society;
    private String societyName;
    
    private String fixed_assets;
    private String fixed_assetsName;
    
    private String cost_center;
    private String cost_center_name;

    private String emplazement_center;
    private String emplazement_center_name;
    private String area_id;
    private String area_name;

    private String top_equipment;
    private String top_equipment_name;
    private String location;
    private String location_name;

    private String mec_power;
    private String rpm;
    private String torque;
    private String mec_max_cap;
    private String mec_min_cap;
    private String mec_energy_type;
    private String flow;
    private String mec_ratio;
    private String mec_frecuency;
    private String lubricant;
    private String bearing;

    private String voltage;
    private String elec_frecuency;
    private String elec_power;
    private String elec_max_cap;
    private String elec_min_cap;
    private String elec_energy_type;
    private String nominal_eff;
    private String min_eff;
    private String power_factor;
    private String service_factor;
    private String elec_ratio;
    private String phases;
    private String rise_temp;

    private String volume;
    private String frame;
    private String cat_num;
    private String espec_num;
    private String serial_num;
    private String weight;
    private String classes;
    private String style;
    private String design;
    private String vibration;

    private File file1;
    private File file2;
    private File file3;

    private String created_by;
    private String modified_by;
    
    private String creation_date;
    private String modification_date;
    
    private String grupoPlanificacion;
    private String descripcionGruopoPlanif;

    public void create() {

        try {
                        
            Connection conexion = PooledConnectionService.getConnection();            
            PreparedStatement statement = null;
            
            String query = SQLKeywords.INSERT.toSQL()
                    + DatabaseTables.EQUIPMENTS.tableName()
                    + SentenceValues.setValues(81);
            

                    statement = conexion.prepareStatement(query,java.sql.Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, "0");
                    statement.setString(2, getName());
                    statement.setString(3, getStatus_id());
                    statement.setString(4, getStatus_name());

                    statement.setString(5, getType_equipment_id());
                    statement.setString(6, getType_name());
                    statement.setString(7, getCriticality_id());
                    statement.setString(8, getCriticality_name());
                    statement.setString(9, getBrand());
                    statement.setString(10, getModel());
                    statement.setString(11, getSerial());
                    statement.setString(12, getManufacture_date());
                    statement.setString(13, getDimensions());
                    statement.setString(14, getCommissioning_date());

                    statement.setString(15, getAcquisition_value());
                    statement.setString(16, getCurrency());
                    statement.setString(17, getAcquisition_date());

                    statement.setString(18, getManufacturer());
                    statement.setString(19, getManufacturer_type());
                    statement.setString(20, getPart_number());
                    statement.setString(21, getManu_serial_number());
                    statement.setString(22, getProducing_country());
                    statement.setInt(23, getYear_construction());
                    statement.setInt(24, getMonth_construction());

                    statement.setString(25, getSociety());
                    statement.setString(26, getSocietyName());
                    
                    statement.setString(27, getFixed_assets());
                    statement.setString(28, getFixed_assetsName());
                    
                    statement.setString(29, getCost_center());
                    statement.setString(30, getCost_center_name());

                    statement.setString(31, getEmplazement_center());
                    statement.setString(32, getEmplazement_center_name());
                    statement.setString(33, getArea_id());
                    statement.setString(34, getArea_name());

                    statement.setString(35, getTop_equipment());
                    statement.setString(36, getTop_equipment_name());
                    statement.setString(37, getLocation());
                    statement.setString(38, getLocation_name());

                    statement.setString(39, getMec_power());
                    statement.setString(40, getRpm());
                    statement.setString(41, getTorque());
                    statement.setString(42, getMec_max_cap());
                    statement.setString(43, getMec_min_cap());
                    statement.setString(44, getMec_energy_type());
                    statement.setString(45, getFlow());
                    statement.setString(46, getMec_ratio());
                    statement.setString(47, getMec_frecuency());
                    statement.setString(48, getLubricant());
                    statement.setString(49, getBearing());

                    statement.setString(50, getVoltage());
                    statement.setString(51, getElec_frecuency());
                    statement.setString(52, getElec_power());
                    statement.setString(53, getElec_max_cap());
                    statement.setString(54, getElec_min_cap());
                    statement.setString(55, getElec_energy_type());
                    statement.setString(56, getNominal_eff());
                    statement.setString(57, getMin_eff());
                    statement.setString(58, getPower_factor());
                    statement.setString(59, getService_factor());
                    statement.setString(60, getElec_ratio());
                    statement.setString(61, getPhases());
                    statement.setString(62, getRise_temp());

                    statement.setString(63, getVolume());
                    statement.setString(64, getFrame());
                    statement.setString(65, getCat_num());
                    statement.setString(66, getEspec_num());
                    statement.setString(67, getSerial_num());
                    statement.setString(68, getWeight());
                    statement.setString(69, getClasses());
                    statement.setString(70, getStyle());
                    statement.setString(71, getDesign());
                    statement.setString(72, getVibration());

                    statement.setBytes(73, getFile1Bytes());
                    statement.setBytes(74, getFile2Bytes());
                    statement.setBytes(75, getFile3Bytes());

                    statement.setString(76, getCreated_by());                    
                    statement.setString(77, getModified_by());
                    
                    statement.setString(78,getCreation_date());
                    statement.setString(79,getModification_date());
                    
                    statement.setString(80, getGrupoPlanificacion());
                    statement.setString(81, getDescripcionGruopoPlanif());
                    

                    statement.executeUpdate();

                    ResultSet rs = statement.getGeneratedKeys();
                    if (rs.next()) {
                        setEquipment_id(rs.getInt(1));
                    }

                    statement.close();

        } catch (SQLException | IOException ex) {
            Logger.getLogger(Crear_Equipo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public String getName() {
        return name;
    }

    public String getStatus_id() {
        return status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public String getType_equipment_id() {
        return type_equipment_id;
    }

    public String getType_name() {
        return type_name;
    }

    public String getCriticality_id() {
        return criticality_id;
    }

    public String getCriticality_name() {
        return criticality_name;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerial() {
        return serial;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getAcquisition_value() {
        return acquisition_value;
    }

    public String getCurrency() {
        return currency;
    }


    public String getManufacturer() {
        return manufacturer;
    }

    public String getManufacturer_type() {
        return manufacturer_type;
    }

    public String getPart_number() {
        return part_number;
    }

    public String getManu_serial_number() {
        return manu_serial_number;
    }

    public String getProducing_country() {
        return producing_country;
    }


    public String getSociety() {
        return society;
    }

    public String getFixed_assets() {
        return fixed_assets;
    }

    public String getCost_center() {
        return cost_center;
    }

    public String getCost_center_name() {
        return cost_center_name;
    }

    public String getEmplazement_center() {
        return emplazement_center;
    }

    public String getEmplazement_center_name() {
        return emplazement_center_name;
    }

    public String getArea_id() {
        return area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public String getTop_equipment() {
        return top_equipment;
    }

    public String getTop_equipment_name() {
        return top_equipment_name;
    }

    public String getLocation() {
        return location;
    }

    public String getLocation_name() {
        return location_name;
    }

    public String getMec_power() {
        return mec_power;
    }

    public String getRpm() {
        return rpm;
    }

    public String getTorque() {
        return torque;
    }

    public String getMec_max_cap() {
        return mec_max_cap;
    }

    public String getMec_min_cap() {
        return mec_min_cap;
    }

    public String getMec_energy_type() {
        return mec_energy_type;
    }

    public String getFlow() {
        return flow;
    }

    public String getMec_ratio() {
        return mec_ratio;
    }

    public String getMec_frecuency() {
        return mec_frecuency;
    }

    public String getLubricant() {
        return lubricant;
    }

    public String getBearing() {
        return bearing;
    }

    public String getVoltage() {
        return voltage;
    }

    public String getElec_frecuency() {
        return elec_frecuency;
    }

    public String getElec_power() {
        return elec_power;
    }

    public String getElec_max_cap() {
        return elec_max_cap;
    }

    public String getElec_min_cap() {
        return elec_min_cap;
    }

    public String getElec_energy_type() {
        return elec_energy_type;
    }

    public String getNominal_eff() {
        return nominal_eff;
    }

    public String getMin_eff() {
        return min_eff;
    }

    public String getPower_factor() {
        return power_factor;
    }

    public String getService_factor() {
        return service_factor;
    }

    public String getElec_ratio() {
        return elec_ratio;
    }

    public String getPhases() {
        return phases;
    }

    public String getRise_temp() {
        return rise_temp;
    }

    public String getVolume() {
        return volume;
    }

    public String getFrame() {
        return frame;
    }

    public String getCat_num() {
        return cat_num;
    }

    public String getEspec_num() {
        return espec_num;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public String getWeight() {
        return weight;
    }

    public String getClasses() {
        return classes;
    }

    public String getStyle() {
        return style;
    }

    public String getDesign() {
        return design;
    }

    public String getVibration() {
        return vibration;
    }


    public String getCreated_by() {
        return created_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public void setType_equipment_id(String type_equipment_id) {
        this.type_equipment_id = type_equipment_id;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public void setCriticality_id(String criticality_id) {
        this.criticality_id = criticality_id;
    }

    public void setCriticality_name(String criticality_name) {
        this.criticality_name = criticality_name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public void setAcquisition_value(String acquisition_value) {
        this.acquisition_value = acquisition_value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setManufacturer_type(String manufacturer_type) {
        this.manufacturer_type = manufacturer_type;
    }

    public void setPart_number(String part_number) {
        this.part_number = part_number;
    }

    public void setManu_serial_number(String manu_serial_number) {
        this.manu_serial_number = manu_serial_number;
    }

    public void setProducing_country(String producing_country) {
        this.producing_country = producing_country;
    }


    public void setSociety(String society) {
        this.society = society;
    }

    public void setFixed_assets(String fixed_assets) {
        this.fixed_assets = fixed_assets;
    }

    public void setCost_center(String cost_center) {
        this.cost_center = cost_center;
    }

    public void setCost_center_name(String cost_center_name) {
        this.cost_center_name = cost_center_name;
    }

    public void setEmplazement_center(String emplazement_center) {
        this.emplazement_center = emplazement_center;
    }

    public void setEmplazement_center_name(String emplazement_center_name) {
        this.emplazement_center_name = emplazement_center_name;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public void setTop_equipment(String top_equipment) {
        this.top_equipment = top_equipment;
    }

    public void setTop_equipment_name(String top_equipment_name) {
        this.top_equipment_name = top_equipment_name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setMec_power(String mec_power) {
        this.mec_power = mec_power;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }

    public void setTorque(String torque) {
        this.torque = torque;
    }

    public void setMec_max_cap(String mec_max_cap) {
        this.mec_max_cap = mec_max_cap;
    }

    public void setMec_min_cap(String mec_min_cap) {
        this.mec_min_cap = mec_min_cap;
    }

    public void setMec_energy_type(String mec_energy_type) {
        this.mec_energy_type = mec_energy_type;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public void setMec_ratio(String mec_ratio) {
        this.mec_ratio = mec_ratio;
    }

    public void setMec_frecuency(String mec_frecuency) {
        this.mec_frecuency = mec_frecuency;
    }

    public void setLubricant(String lubricant) {
        this.lubricant = lubricant;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public void setElec_frecuency(String elec_frecuency) {
        this.elec_frecuency = elec_frecuency;
    }

    public void setElec_power(String elec_power) {
        this.elec_power = elec_power;
    }

    public void setElec_max_cap(String elec_max_cap) {
        this.elec_max_cap = elec_max_cap;
    }

    public void setElec_min_cap(String elec_min_cap) {
        this.elec_min_cap = elec_min_cap;
    }

    public void setElec_energy_type(String elec_energy_type) {
        this.elec_energy_type = elec_energy_type;
    }

    public void setNominal_eff(String nominal_eff) {
        this.nominal_eff = nominal_eff;
    }

    public void setMin_eff(String min_eff) {
        this.min_eff = min_eff;
    }

    public void setPower_factor(String power_factor) {
        this.power_factor = power_factor;
    }

    public void setService_factor(String service_factor) {
        this.service_factor = service_factor;
    }

    public void setElec_ratio(String elec_ratio) {
        this.elec_ratio = elec_ratio;
    }

    public void setPhases(String phases) {
        this.phases = phases;
    }

    public void setRise_temp(String rise_temp) {
        this.rise_temp = rise_temp;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setCat_num(String cat_num) {
        this.cat_num = cat_num;
    }

    public void setEspec_num(String espec_num) {
        this.espec_num = espec_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public void setVibration(String vibration) {
        this.vibration = vibration;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public File getFile1() {        
        return file1;
    }

    public File getFile2() {
        return file2;
    }

    public File getFile3() {
        return file3;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
    }

    public void setFile3(File file3) {
        this.file3 = file3;
    }

    private byte[] getFile1Bytes() throws FileNotFoundException, IOException {

        if (getFile1() != null) {

            File img1 = getFile1();
            FileInputStream fis = new FileInputStream(img1);
            byte[] image = new byte[(int) img1.length()];
            fis.read(image);

            return image;
        } else {
            return null;
        }

    }

    private byte[] getFile2Bytes() throws FileNotFoundException, IOException {

        if (getFile2() != null) {
            File img2 = getFile1();
            FileInputStream fis = new FileInputStream(img2);
            byte[] image = new byte[(int) img2.length()];
            fis.read(image);

            return image;
        }else{
            return null;
        }

    }

    private byte[] getFile3Bytes() throws FileNotFoundException, IOException {

        if (getFile3() != null) {
            File img3 = getFile1();
            FileInputStream fis = new FileInputStream(img3);
            byte[] image = new byte[(int) img3.length()];
            fis.read(image);

            return image;
        }else{
            return null;
        }

    }

    public String getCommissioning_date() {
        return commissioning_date;
    }

    public void setCommissioning_date(String commissioning_date) {
        this.commissioning_date = commissioning_date;
    }

    public String getAcquisition_date() {
        return acquisition_date;
    }

    public void setAcquisition_date(String acquisition_date) {
        this.acquisition_date = acquisition_date;
    }

}
