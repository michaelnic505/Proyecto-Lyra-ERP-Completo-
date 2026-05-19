package com.simplecore.erp.server.config.database;

public enum DatabaseTables {
    
    FI_ACCOUNT_CLASSES("account_classes"),
    FI_ACCOUNT_MODELS("account_models"),
    FI_ACCOUNT_RANGES("account_ranges"),
    FI_ACCOUNT_SUBCLASSES("account_subclasses"),
    FI_ACCOUNTING_ACCOUNTS("accounting_accounts"),
    COUNTRIES("countries"),
    CURRENCIES("currencies"),
    FI_CHART_OF_ACCOUNTS("chart_of_accounts"),
    FI_TAX_SCHEMAS("tax_schemas"),
    FI_COMPANY("fi_company"),
    FI_COMPANY_DOCUMENTS("fi_company_documents"),
    CO_COMPANY("co_company"),
    FICO_COMPANY_RELATION("fico_company_relation"),
    TIMEZONES("timezones"),
    CO_COST_VARIANT("cost_variant"),
    CO_VALUATION_VARIANT("valuation_variant"),
    CO_COSTING_VERSION("costing_version"),
    CO_COST_CENTER("cost_center"),
    CO_COST_CENTER_MODEL("cost_center_model"),
    CO_COST_CENTER_MODEL_DETAIL("cost_center_model_detail"),
    CO_COST_CENTER_PLAN("cost_center_plan"),
    CO_COST_CENTER_PLAN_MODEL("cost_center_plan_model"),
    CO_COMPANY_COST_CENTER_PLAN("company_cost_center_plan"),
        

    SOCIETY_CLASSES("society_classes"),
    USERS("users"),
    USERS_ROLES("users_roles"),
    ACTIVE_SESSIONS("active_sessions"),
    WORK_ORDERS("work_orders"),
    OPERATIONS_WORK_ORDERS("operations_work_orders"),
    MATERIALS_WORK_ORDERS("materials_work_order"),
    PERMISSIONS_MODIFICATION_ORDER("modification_permissions_orders"),
    USERS_SYSTEM("users_system"),
    EQUIPMENTS("equipments"),
    LOCATIONS("locations"),
    ORDER_CLASS("order_class"),//no se modifica
    ORDER_CRITICALITY("order_criticality"),//no se modifica
    BUSINESS_TRANSACTIONS("business_transactions"),//no se modifica
    PLANNING_GROUPS("planning_groups"),
    POINT_MEASUREMENT_OBJECTS("point_measurement_objects"),//no se modifica
    MATERIAL_WAREHOUSE_REGISTRATION("material_warehouse_registration"),
    CHARACTERISTICS("characteristics"),
    CHARACTERISTICS_DESCRIPTIONS("characteristics_description"),
    CHARACTERISTICS_VALUES("characteristics_values"),
    CHARACTERISTICS_RESTRICTIONS("characteristics_restrictions"),
    MEASURING_POINTS("measuring_points"),
    MEASUREMENT_DOCUMENTS("measurement_documents"),
    MAINTENANCE_STRATEGIES("maintenance_strategies"),
    MAINTENANCE_STRATEGIES_PACKAGES("maintenance_strategies_packages"),
    MAINTENANCE_STRATEGIES_PERMISSIONS("maintenance_strategy_permissions"),
    TIME_ZONES_BY_USERS("time_zones_by_users"),
    OPERATION_TYPE_MAINTENANCE_ORDER("operation_type_maintenance_order"),
    ROUTINE_SHEET_USAGES("routine_sheets_usages"),
    ROUTINE_SHEET_HEADERS("routine_sheets_header"),
    ROUTINE_SHEET_OPERATIONS("routine_sheets_operations"),
    ROUTINE_SHEET_MATERIALS("routine_sheet_materials"),
    ROUTINE_SHEET_PACKAGES("routine_sheet_packages"),
    ROLES("roles"),
    ROLE_TRANSACTIONS("role_transactions"),
    MAINTENANCE_PROCEDURES("maintenance_procedures"),
    MAINTENANCE_PROCEDURES_AUTHORIZATIONS("maintenance_procedures_authorizations"),//IMPLEMENTAR MODULO
    TRANSACTIONS("transactions"),
    
    Tipo_Equipo("tipos_de_equipos"),
    Estatus_Equipo("estatus_de_equipo"),
    Tipo_Orden("tipo_de_orden_de_mantenimiento"),
    Puntos_de_medida("puntos_medida"),
    Tipo_Puntos_Medida("tipos_punto_medida"),
    Estatus_Puntos_Medida("estatus_puntos_medida"),
    Notificacion("notificaciones_para_ordenes"),
    Crear_Docs_Medicion("documentos_de_medicion"),
    Estatus_Orden("estatus_de_ordenes_mantenimiento"),
    Tipos_de_usuarios("tipos_usuario"),
    Equipos("equipos"),
    Materiales("materiales"),
    Niveles_Areas("niveles_areas"),
    Areas("areas"),
    Paises("paises"),
    Centro_de_costos("centro_de_costos"),
    Arbol("arbol"),
    Idiomas("idiomas"),
    Empresas("empresas"),
    Estatus_Centro_Costos("estatus_centros_costos"),
    Tipo_Material("tipos_material"),
    Clase_Material("clase_materiales"),
    Estatus_Material("estatus_material"),
    Almacenes("almacenes"),
    Tipos_Almacen("tipo_almacen"),
    Clase_Almacen("clase_almacen"),
    Estatus_Almacen("estatus_almacen"),
    Area_Almacen("areas"),
    Hosts("lista_hosts"),
    
    Movimientos_material("movimientos_material_almacen"),
    Estrategias_mantenimiento("estrategias_mantenimiento"),
    Paquetes_mantenimiento("paquetes_mantenimiento"),
    Unidades_Medida("lista_unidades_medida"),
    Tipo_Plan_Mantenimiento("tipo_plan_mantenimiento"),
    Caracteristicas("caracteristicas"),
    Planes_Mantenimiento("planes_mantenimiento"),
    Estatus_Planes_Mantenimiento("estatus_planes_mantenimiento"),
    Programacion_mantenimiento("programacion_mantenimiento"),
    Divisas("divisas"),
    emplazement("emplazement"),
    cost_centers("cost_centers"),
    sistemas("sistemas"),
    componentes("componentes"),
    sintomas("sintomas"),
    personal("personal");

    private String tabla;

    private DatabaseTables(String tabla) {
        this.tabla = tabla;
    }

    public String tableName() {
        return tabla;
    }

    public enum PermissionsModificationOrder {
        USER, // The user who performs the modification
        TRANSACTION, // The transaction type
        OCRTD, // Created status
        OIPNN, // In progress status
        OUAPP, // Under approval status
        OAPPV, // Approved status
        OSCHD, // Scheduled status
        OIEXN, // Execution in progress status
        OEXTD, // Execution completed status
        OCLSD, // Closed status
        ORJTD, // Rejected status
        OCCLD  // Cancelled status
    }

    public enum UsersSystem {
        NOMBRE_USUARIO, // Username
        PRIMER_NOMBRE, // First name
        SEGUNDO_NOMBRE, // Middle name
        PRIMER_APELLIDO, // Last name
        SEGUNDO_APELLIDO, // Second last name
        CORREO, // Email address
        CARGO, // Position
        ORGANIZACIÓN, // Organization
        TIPO_USUARIO, // User type
        CONTRASENA, // Password
        DIRECCION_HOST // Host address
    }

    public enum Equipments {
        EQUIPMENT_ID, // Unique identifier for the equipment
        NAME, // Name of the equipment
        STATUS_ID, // Status ID
        STATUS_NAME, // Name of the status
        TYPE_EQUIPMENT_ID, // Equipment type ID
        TYPE_NAME, // Name of the equipment type
        CRITICALITY_ID, // Criticality level ID
        CRITICALITY_NAME, // Name of the criticality level
        BRAND, // Equipment brand
        MODEL, // Equipment model
        SERIAL, // Serial number
        MANUFACTURE_DATE, // Manufacture date
        DIMENSIONS, // Physical dimensions
        COMMISSIONING_DATE, // Commissioning date
        ACQUISITION_VALUE, // Acquisition value
        CURRENCY, // Currency type
        ACQUISITION_DATE, // Acquisition date
        MANUFACTURER, // Manufacturer name
        MANUFACTURER_TYPE, // Manufacturer type
        PART_NUMBER, // Part number
        MANU_SERIAL_NUMBER, // Manufacturer serial number
        PRODUCING_COUNTRY, // Country of origin
        YEAR_CONSTRUCTION, // Year of construction
        MONTH_CONSTRUCTION, // Month of construction
        SOCIETY, // Society ID
        SOCIETY_NAME, // Name of the society
        FIXED_ASSETS, // Fixed assets ID
        FIXED_ASSETS_NAME, // Name of the fixed assets
        COST_CENTER, // Cost center ID
        COST_CENTER_NAME, // Name of the cost center
        EMPLAZEMENT_CENTER, // Emplacement center ID
        EMPLAZEMENT_CENTER_NAME, // Name of the emplacement center
        AREA_ID, // Area ID
        AREA_NAME, // Name of the area
        TOP_EQUIPMENT, // Parent equipment ID
        TOP_EQUIPMENT_NAME, // Name of the parent equipment
        LOCATION, // Location ID
        LOCATION_NAME, // Name of the location
        MEC_POWER, // Mechanical power
        RPM, // Rotations per minute
        TORQUE, // Torque value
        MEC_MAX_CAP, // Maximum mechanical capacity
        MEC_MIN_CAP, // Minimum mechanical capacity
        MEC_ENERGY_TYPE, // Mechanical energy type
        FLOW, // Flow rate
        MEC_RATIO, // Mechanical ratio
        MEC_FRECUENCY, // Mechanical frequency
        LUBRICANT, // Lubricant type
        BEARING, // Bearing type
        VOLTAGE, // Voltage rating
        ELEC_FRECUENCY, // Electrical frequency
        ELEC_POWER, // Electrical power
        ELEC_MAX_CAP, // Maximum electrical capacity
        ELEC_MIN_CAP, // Minimum electrical capacity
        ELEC_ENERGY_TYPE, // Electrical energy type
        NOMINAL_EFF, // Nominal efficiency
        MIN_EFF, // Minimum efficiency
        POWER_FACTOR, // Power factor
        SERVICE_FACTOR, // Service factor
        ELEC_RATIO, // Electrical ratio
        PHASES, // Electrical phases
        RISE_TEMP, // Temperature rise
        VOLUME, // Volume
        FRAME, // Frame type
        CAT_NUM, // Catalog number
        ESPEC_NUM, // Specification number
        SERIAL_NUM, // Serial number
        WEIGHT, // Weight
        CLASS, // Equipment class
        STYLE, // Equipment style
        DESIGN, // Equipment design
        VIBRATION, // Vibration level
        FILE1, // Reference file 1
        FILE2, // Reference file 2
        FILE3, // Reference file 3
        CREATED_BY, // User who created the record
        MODIFIED_BY, // User who modified the record
        CREATION_DATE, // Record creation date
        MODIFICATION_DATE, // Record modification date
        G_PLANIFICACION, // Planning group ID
        DESCRIPCION_G_PLANIF // Description of the planning group
    }

    public enum Locations {
        ID_UBICACION, // Location ID
        DENOMINACION_UBICACION, // Location Name
        ID_UBICACION_SUPERIOR, // Superior Location ID
        DENOMINACION_UBICACION_SUP, // Superior Location Name
        MONTAJE_PERMITIDO, // Allowed Assembly
        ESTATUS, // Status
        NIVEL, // Level
        TIPO_REGISTRO, // Record Type
        CENTRO_COSTOS, // Cost Center
        DESCRIPCION_CC, // Cost Center Description
        EMPLAZAMIENTO, // Site
        DESCRIPCION_EMP, // Site Description
        AREA, // Area
        DESCRIPCION_AREA, // Area Description
        SOCIEDAD, // Company
        DESCRIPCION_SOCIEDAD, // Company Description
        GRUPO_PLANIF, // Planning Group
        DESCRIPCION_G_PLANIF         // Planning Group Description
    }

    public enum OrderClass {
        ID_CLASE, // Class ID
        DESCRIPCION_CLASE    // Class Description
    }

    public enum OrderCriticality {
        ID_CRITICIDAD, // Criticality ID
        DESCRIPCION_CRITICIDAD, // Criticality Description
        DIAS                 // Days
    }

    public enum PlanningGroupSql {
        PLANNINGROUPCODE, // Planning Group Code
        PGROUPDENOMINATION, // Planning Group Denomination
        STATUS               // Status
    }

    public enum WorkOrders {
        ORDER_NUM, // Order Number
        STATUS_CODE, // Status Code
        STATUS_DESCRIPTION, // Status Description
        STOP, // Stop
        ORDER_TITLE, // Order Title
        EXTENDED_DESCRIPTION, // Extended Description
        TYPE_ORDER_CODE, // Order Type Code
        TYPE_ORDER_DESCRIPTION, // Order Type Description
        CLASS_ORDER_CODE, // Order Class Code
        CLASS_ORDER_DESCRIPTION, // Order Class Description
        CRITICALITY_CODE, // Criticality Code
        CRITICALITY_DESCRIPTION, // Criticality Description
        SYSTEM_CODE, // System Code
        SYSTEM_DESCRIPTION, // System Description
        COMPONENT_CODE, // Component Code
        COMPONENT_DESCRIPTION, // Component Description
        SYMPTOM_CODE, // Symptom Code
        SYMPTOM_DESCRIPTION, // Symptom Description
        EQUIPMENT_CODE, // Equipment Code
        EQUIPMENT_DESCRIPTION, // Equipment Description
        UBICATION_CODE, // Location Code
        UBICATION_DESCRIPTION, // Location Description
        ACTUAL_START_DATE, // Actual Start Date
        ACTUAL_END_DATE, // Actual End Date
        ACTUAL_START_TIME, // Actual Start Time
        ACTUAL_END_TIME, // Actual End Time
        TOTAL_REAL_TIME, // Total Real Time
        SCHEDULED_START_DATE, // Scheduled Start Date
        SCHEDULED_END_DATE, // Scheduled End Date
        SCHEDULED_START_TIME, // Scheduled Start Time
        SCHEDULED_END_TIME, // Scheduled End Time
        SCHEDULED_TOTAL_TIME, // Scheduled Total Time
        CREATION_DATE_ORDER, // Order Creation Date
        APPROVAL_DATE_ORDER, // Order Approval Date
        CREATION_TIME_ORDER, // Order Creation Time
        AUTHORIZATION_TIME_ORDER, // Order Authorization Time
        TOTAL_CREATION_HOURS, // Total Creation Hours
        GROUP_PLANNING_CODE, // Planning Group Code
        PLANNING_GROUP_DESCRIPTION, // Planning Group Description
        APPLICANT_CODE, // Applicant Code
        APPLICANT_DESCRIPTION, // Applicant Description
        RESPONSIBLE_CODE, // Responsible Code
        DESCRIPTION_OF_RESPONSIBLE, // Responsible Description
        COMPANY_CODE, // Company Code
        COMPANY_DESCRIPTION, // Company Description
        AREA_CODE, // Area Code
        AREA_DESCRIPTION, // Area Description
        EMPLAZEMENT_CODE, // Site Code
        EMPLAZEMENT_DESCRIPTION, // Site Description
        COST_CENTER_CODE, // Cost Center Code
        COST_CENTER_DESCRIPTION, // Cost Center Description
        ORDER_CREATED_BY, // Order Created By
        ORDER_PLANNED_BY, // Order Planned By
        ORDER_APPROVED_BY, // Order Approved By
        ORDER_EXECUTED_BY, // Order Executed By
        ESTIMATED_TIME, // Estimated Time
        REAL_TIME, // Real Time
        ESTIMATED_COST, // Estimated Cost
        REAL_COST, // Real Cost
        COUNTER, // Counter
        COUNTER_VALUE, // Counter Value
        ROAD_SHEET, // Road Sheet
        PLAN                          // Plan
    }

    public enum PointMeasurementObjects {
        TYPE, // Type
        LANGUAGE, // Language
        TEXT             // Text
    }

    public enum Characteristics {
        CHARACTERISTIC, // Characteristic
        VALIDFROM, // Valid From
        STATUS, // Status
        DESCRIPTION, // Description
        CHARGROUP, // Charge Group
        SINGLEVALUE, // Single Value
        MULTIPLESVALUES, // Multiple Values
        INTERVALSALLOWED, // Intervals Allowed
        NEGATIVEVALSALLOWED, // Negative Values Allowed
        RESTRICTABLE, // Restrictable
        ENTRYREQUIRED, // Entry Required
        DATATYPE, // Data Type
        NUMBERCHARACTERS, // Number of Characters
        NUMBERDECIMALS, // Number of Decimals
        UNITOFMEASURE, // Unit of Measure
        CURRENCYSIMBOLS, // Currency Symbols
        TEMPLATE              // Template
    }

    public enum CharacteristicsDescriptions {
        ID, // ID
        LANGUAGE, // Language
        DESCRIPTION, // Description
        CHARACTERISTIC        // Characteristic
    }

    public enum CharacteristicsValues {
        ID, // ID
        CHARACTERISTIC, // Characteristic
        CHARVALUE, // Characteristic Value
        DESCRIPTION           // Description
    }

    public enum CharacteristicsRestrictions {
        ID, // ID
        TYPE, // Type
        CHARACTERISTIC        // Characteristic
    }

    public enum MeasuringPoints {
        MEASURE_POINT, // Measure Point
        OBJECT_MEAS_POINT, // Object Measure Point
        MEAS_POSITION, // Measurement Position
        MEAS_TYPE, // Measurement Type
        DESCRIPTION, // Description
        EQUIPMENT, // Equipment
        EQUIPMENT_DESCRIPTION, // Equipment Description
        CHARACTERISTIC, // Characteristic
        CHARACT_UNIT, // Characteristic Unit
        DECIMAL_PLACES, // Decimal Places
        IS_COUNTER, // Is Counter
        CODE_GROUP, // Code Group
        ASSEMBLY, // Assembly
        AUTHORIZ_GROUP, // Authorization Group
        TARGET_VALUE, // Target Value
        TEXT, // Text
        STATUS                // Status
    }

    public enum MeasurementDocuments {
        MEASNUMDOC, // Measurement Document Number
        MEASPOINT, // Measurement Point
        MEASPOSITION, // Measurement Position
        MEASPOSDESC, // Measurement Position Description
        MEASTYPECODE, // Measurement Type Code
        EQUIPMENT, // Equipment
        EQUIPDESC, // Equipment Description
        CHARACTERISTIC, // Characteristic
        READINGDATE, // Reading Date
        READINGTIME, // Reading Time
        MUNIT, // Measurement Unit
        IRD, // Instrument Reading Device
        MEASUREDVALUE, // Measured Value
        THEORICALVALUE, // Theoretical Value
        TEXT, // Text
        CREATEDBY, // Created By
        CREATEDON, // Created On
        IRDPREV, // Previous Instrument Reading Device
        DAYSLASTREG, // Days Since Last Registration
        PREVIOUSVALUE, // Previous Value
        STATUS                 // Status
    }

    public enum MaintenanceStrategiesHeaders {
        STRATEGYCODE, // Strategy Code
        STRATEGYDESCRIPTION, // Strategy Description
        STRATEGYUNIT, // Strategy Unit
        APERTUREHORIZON, // Aperture Horizon
        DELAYFACTORDELAYEDCONCLUSION,// Delay Factor for Delayed Conclusion
        TOLERANCEONLATECOMPLETION, // Tolerance on Late Completion
        DELAYFACTOREARLYCONCLUSION, // Delay Factor for Early Conclusion
        TOLERANCEONEARLYCOMPLETION, // Tolerance on Early Completion
        STATUS                       // Status
    }

    public enum MaintenanceStrategiesPackages {
        PACKAGENUMBER, // Package Number
        CYCLEDURATION, // Cycle Duration
        MEASUNIT, // Measurement Unit
        MEASUNITDESCRIPTION, // Measurement Unit Description
        MAINTENANCECYCLETEXT, // Maintenance Cycle Text
        SHORTCYCLETEXT, // Short Cycle Text
        HIERARCHY, // Hierarchy
        SHORTHIERARCHYTEXT, // Short Hierarchy Text
        OFFSET, // Offset
        STRATEGYCODE, // Strategy Code
        ID                        // ID
    }

    public enum MaintenanceStrategiesPermissions {
        ID, // ID
        USERS, // Users
        TRANSACTION, // Transaction
        CREATES, // Creates
        CHANGES, // Changes
        DELETES, // Deletes
        VIEWS                     // Views
    }


    public enum OperationTypeMaintenanceOrder {
        ACTIVITYMAINTENANCEID, // Activity Maintenance ID
        ACTIVITYMAINTENANCEDESCRIPTION, // Activity Maintenance Description
        UNITCOST, // Unit Cost
        UNITOFMEASURE, // Unit of Measure
        CURRENCY, // Currency
        STATUS                    // Status
    }

    public enum RoutineSheetsHeader {
        ROUTINESHEET, // Routine Sheet
        COUNTER, // Counter
        POSITIONNAME, // Position Name
        PLANNINGGROUP, // Planning Group
        OPERATIONTYPE, // Operation Type
        STATUS, // Status
        OPERATINGCONTEXT, // Operating Context
        MAINTENANCESTRATEGY, // Maintenance Strategy
        USAGE, // Usage
        ID, // ID
        SCHEDULEDDATE // Scheduled Date
    }
    public enum RoutineSheetOperations {
        ROUTINESHEET,
        COUNTER,
        OPERATION,
        PROCEDURES,
        OPERATIONDESCRIPTION,
        WORK,
        QUANTITY,
        DURATION,
        OPERATIONTYPECODE,
        OPERATIONTYPE,
        UNITCOST,
        UNITOFMEASURE,
        TOTALAMOUNT,
        ORDERREQUEST,
        CURRENCY
    }
    public enum RoutineSheetMaterials {
        ROUTINESHEET,
        COUNTER,
        OPERATION,
        POSITION,
        MATERIAL,
        COMPONENT_DENOMINATION,
        QUANTITY,
        UNITOFMEASURE,
        UNITCOST,
        WAREHOUSE,
        TOTALAMOUNT,
        CLASSIFICATION
    }
    public enum RoutineSheetPackages {
        ROUTINESHEET,
        COUNTER,
        OPERATION,
        PACKAGE,
        MAINTENANCESTRATEGY
    }

    public enum MaintenanceProcedures1 {
        PROCEDUREKEY, // Procedure Key
        PROCEDURETEXT,             // Procedure Text
        SHORDESCRIPTION
    }
    public enum MaintenanceProceduresAurotizations {
        USER,
        CREATING,
        UPDATING,
        DELETING,
        VIEWING,
        ID
    }
    public enum MaterialWareHouseRegistration{
        MATERIALCODE,
        MATERIALDESCRIPTION,
        WAREHOUSECODE,
        WAREHOUSEDESCRIPTION,
        UNITPRICE,
        UNITMEASUREID,
        UNITMEASUREDESCRIPTION,
        SERIES,
        MODEL,
        BRAND,
        STATUS,
        REGISTRATIONNUMBER
    }


}
