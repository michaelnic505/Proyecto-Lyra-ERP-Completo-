package com.simplecore.erp.modules.logistics.plantmaintenance.masterdata.characteristics.utils;


public class Class_Type_List {

    public Class_Type_List() {
    }

    public static String getClassTypeCode(int index) {
        return classTypeCodeList[index];
    }
    
    
    public static String getClassTypeDescriptionByIndex(int index) {
            classTypeDescription = classTypeDescripcionList_EN[index];
        return classTypeDescription;
    }
    
    public static String getClassTypeDescriptionByCode(String code) {

        int index = 0;
        String description = "";

        for (int i = 0; i < classTypeCodeList.length; i++) {
          
            if (classTypeCodeList[i].equals(code)) {
                index = i;
                break;
            }
        }
        
            description = classTypeDescripcionList_EN[index];

        return description;
    }
    
    
    
    public static int getClassTypeIndex(String code) {

        int index = -1;
        for (int i = 0; i < classTypeCodeList.length; i++) {
            if(code.equals(classTypeCodeList[i])){
                index = i;
                break;
            }
        }
      
        return index;
    }
    public static String[] getClassTypeCodesList(){
        return classTypeCodeList;
    }
    
    private static String classTypeDescription;   

    private static final String[] classTypeCodeList = {
    
        "001",
        "002",
        "003",
        "004",
        "005",
        "006",
        "007",
        "008",
        "009",
        "010",
        "011",
        "012",
        "013",
        "014",
        "015",
        "016",
        "017",
        "018",
        "019",
        "020",
        "021",
        "022",
        "023",
        "024"

    };
    
    private static final String[] classTypeDescripcionList_EN = {
        
        "Material class",
        "Equipment class",
        "Functional location",
        "Ref. functional location",
        "Inspection characteristics",
        "Inspection methods",
        "Code groups",
        "Selected set",
        "Prod. Resource/tools",
        "Vendor class",
        "Customer class",
        "Characteristics class",
        "Controlling: orders",
        "Controlling: projects",
        "Error codes",
        "Object link",
        "Document management",
        "Task list class",
        "Work Center Class",
        "Standard network class",
        "Network",
        "Batch",
        "Dependency",
        "Business process"

    };
    
    private static final String[] classTypeDescripcionList_ES = {
        
        "Clase de material",
        "Clase de equipo",
        "Ubicación funcional",
        "Ubicación funcional de referencia",
        "Características de inspección",
        "Métodos de inspección",
        "Grupos de códigos",
        "Conjunto seleccionado",
        "Recurso/herramientas de producción",
        "Clase de proveedor",
        "Clase de cliente",
        "Clase de características",
        "Control: pedidos",
        "Control: proyectos",
        "Códigos de error",
        "Enlace de objeto",
        "Gestión de documentos",
        "Clase de lista de tareas",
        "Clase de centro de trabajo",
        "Clase de red estándar",
        "Red",
        "Lote",
        "Dependencia",
        "Proceso empresarial"
    
    };

       
    
}
