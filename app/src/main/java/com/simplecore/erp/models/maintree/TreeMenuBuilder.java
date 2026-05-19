package com.simplecore.erp.models.maintree;

import com.simplecore.erp.gui.workspace.LyraWorkspace;
import com.simplecore.erp.i18n.TranslationHelper;
import java.util.HashMap;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
/**
 * Clase responsable de construir un árbol de menús jerárquico basado en un enum
 * {@link BusinessModulesEnum}.La estructura del menú es inmutable y está definida en
 * el método {@link #createHierarchy()}.Esta clase es adecuada para sistemas en
 los que el menú es una parte estable y central del software, y los cambios en
 su estructura son infrecuentes.
 */
public class TreeMenuBuilder {

    // Mapa que almacena los nodos del árbol, donde la clave es el identificador del nodo (ej. "L1", "L1.1").
    private Map<BusinessModulesEnum, DefaultMutableTreeNode> nodes;

    /**
     * Constructor de la clase. Inicializa el mapa de nodos.
     */
    public TreeMenuBuilder() {
        nodes = new HashMap<>();
    }

    /**
     * Construye un {@link DefaultMutableTreeNode} a partir de un
     * {@link BusinessModulesEnum}.
     *
     * @param node El nodo de negocio que se convertirá en un nodo del árbol.
     * @return Un {@link DefaultMutableTreeNode} con la información del nodo.
     */
    private static DefaultMutableTreeNode buildNode(BusinessModulesEnum node) {
        return new DefaultMutableTreeNode(createNode(node.getKey()));
    }

    /**
     * Crea un objeto {@link TreeNodeData} que representa un nodo con un código
     * y una descripción.
     *
     * @param keyCode La clave única del nodo.
     * @return Un objeto {@link TreeNodeData} con el código y la descripción del
     * nodo.
     * @throws IllegalArgumentException Si la clave es nula o vacía.
     */
    private static TreeNodeData createNode(String keyCode) {
        if (keyCode == null || keyCode.isEmpty()) {
            throw new IllegalArgumentException("La clave del nodo no puede ser nula o vacía.");
        }
        return new TreeNodeData(keyCode, createDescription(keyCode));
    }

    /**
     * Obtiene la descripción traducida de un nodo utilizando un
     * {@link TranslationHelper}. Si no se encuentra la traducción, se devuelve
     * un valor por defecto.
     *
     * @param key La clave del nodo para la cual se busca la descripción.
     * @return La descripción traducida o "Descripción no disponible" si no se
     * encuentra.
     */
    private static String createDescription(String key) {
        TranslationHelper mainMenuTree = LyraWorkspace.getMenuTreeTranslator();
        String description = mainMenuTree.getTranslation(key);
        if (description == null) {
            return "Descripción no disponible";  // Valor por defecto si no se encuentra la traducción
        }
        return description;
    }

    /**
     * Agrega nodos hijos a un nodo padre.
     *
     * @param parent El nodo padre al que se agregarán los hijos.
     * @param childNodes Un arreglo de nodos hijos.
     */
    private void addChildrens(DefaultMutableTreeNode parent, BusinessModulesEnum[] childNodes) {
        for (BusinessModulesEnum node : childNodes) {
            DefaultMutableTreeNode childNode = nodes.get(node);
            if (childNode != null) {
                parent.add(childNode);
            }
        }
    }

    /**
     * Construye un nodo individual y lo almacena en el mapa de nodos.
     *
     * @param node El nodo de negocio que se convertirá en un nodo del árbol.
     */
    private void buildIndividualNode(BusinessModulesEnum node) {
        nodes.put(node, buildNode(node));
    }

    /**
     * Construye todos los nodos del árbol a partir de los valores del enum
     * {@link BusinessModulesEnum}.
     */
    private void buildNodes() {
        for (BusinessModulesEnum node : BusinessModulesEnum.values()) {
            buildIndividualNode(node);
        }
    }

    /**
     * Construye y devuelve la jerarquía completa del árbol de menús. La
     * estructura del árbol está definida de manera estática en este método.
     *
     * @return El nodo raíz del árbol, que contiene toda la jerarquía de menús.
     */
    public DefaultMutableTreeNode createHierarchy() {
        // Crear la raíz del árbol
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Lyra Core+");

        // Construir todos los nodos y almacenarlos en el mapa
        buildNodes();

        // Agregar los nodos principales (nivel 1) al nodo raíz
        // L1-Logistics | L2-Controlling | L3-Human Resources | L4-Business Intelligence | L5-System
        addChildrens(root, new BusinessModulesEnum[]{BusinessModulesEnum.L1, BusinessModulesEnum.L2, BusinessModulesEnum.L3, BusinessModulesEnum.L4, BusinessModulesEnum.L5});
        // L1 - LOGISTICS 
        // L1.1 - Commercial Sales | L1.2 - Material Management | L1.3 - Plant Maintenance | L1.4 - Production | L1.5 - Projects Management 
        addChildrens(nodes.get(BusinessModulesEnum.L1), new BusinessModulesEnum[]{BusinessModulesEnum.L1_1, BusinessModulesEnum.L1_2, BusinessModulesEnum.L1_3, BusinessModulesEnum.L1_4, BusinessModulesEnum.L1_5});
        // L1.1 - COMMERCIAL SALES
        // L1.1.1 - Customer Management | L1.1.2 - Master data | L1.1.3 - Sales And Billing
        addChildrens(nodes.get(BusinessModulesEnum.L1_1), new BusinessModulesEnum[]{BusinessModulesEnum.L1_1_1, BusinessModulesEnum.L1_1_2, BusinessModulesEnum.L1_1_3});
        // L1.1.1 - Customer Management
        // L1.1.1.1 - Clients | L1.1.1.2 - Customer Analysis
        addChildrens(nodes.get(BusinessModulesEnum.L1_1_1), new BusinessModulesEnum[]{BusinessModulesEnum.L1_1_1_1, BusinessModulesEnum.L1_1_1_2});
        //L1.1.2 - Master data 
        addChildrens(nodes.get(BusinessModulesEnum.L1_1_2), new BusinessModulesEnum[]{BusinessModulesEnum.MD1});
        //L1.1.1.1 - Clients
        addChildrens(nodes.get(BusinessModulesEnum.L1_1_1_1), new BusinessModulesEnum[]{BusinessModulesEnum.C01, BusinessModulesEnum.C02, BusinessModulesEnum.C03, BusinessModulesEnum.C04});
        //L1.1.1.2 - Customer Analysis
        addChildrens(nodes.get(BusinessModulesEnum.L1_1_1_2), new BusinessModulesEnum[]{BusinessModulesEnum.CA1});
        // L1.1.3 - Sales And Billing
        // L1.1.3.1 - Billing | L1.1.3.2 - Sales Orders
        addChildrens(nodes.get(BusinessModulesEnum.L1_1_3), new BusinessModulesEnum[]{BusinessModulesEnum.L1_1_3_1, BusinessModulesEnum.L1_1_3_2});
        //L1.1.3.1 - Billing 
        addChildrens(nodes.get(BusinessModulesEnum.L1_1_3_1), new BusinessModulesEnum[]{BusinessModulesEnum.B01, BusinessModulesEnum.B02, BusinessModulesEnum.B03, BusinessModulesEnum.B04, BusinessModulesEnum.B05});
        //L1.1.3.2 - Sales Orders
        addChildrens(nodes.get(BusinessModulesEnum.L1_1_3_2), new BusinessModulesEnum[]{BusinessModulesEnum.S01, BusinessModulesEnum.S02, BusinessModulesEnum.S03, BusinessModulesEnum.S04});
        // L1.2 - Material Management
        // L1.2.1 - Inventory | L1.2.2 - Purchasing and vendor
        addChildrens(nodes.get(BusinessModulesEnum.L1_2), new BusinessModulesEnum[]{BusinessModulesEnum.L1_2_1, BusinessModulesEnum.L1_2_2});
        // L1.2.1 - Inventory
        // L1.2.1.1 - Materials | L1.2.1.2 - Warehouses
        addChildrens(nodes.get(BusinessModulesEnum.L1_2_1), new BusinessModulesEnum[]{BusinessModulesEnum.L1_2_1_1, BusinessModulesEnum.L1_2_1_2});
        //L1.2.1.1 - Materials 
        addChildrens(nodes.get(BusinessModulesEnum.L1_2_1_1), new BusinessModulesEnum[]{BusinessModulesEnum.M01, BusinessModulesEnum.M02, BusinessModulesEnum.M03, BusinessModulesEnum.M04});
        //L.1.2.1.2 L1.2.1.2 - Warehouses
        addChildrens(nodes.get(BusinessModulesEnum.L1_2_1_2), new BusinessModulesEnum[]{BusinessModulesEnum.W01, BusinessModulesEnum.W02, BusinessModulesEnum.W03, BusinessModulesEnum.W04});
        // L1.2.2 - Purchasing and vendor
        // L1.2.2.1 - Purchase Orders | L1.2.2.2 - Supplier Requests | L1.2.2.3 - Suppliers
        addChildrens(nodes.get(BusinessModulesEnum.L1_2_2), new BusinessModulesEnum[]{BusinessModulesEnum.L1_2_2_1, BusinessModulesEnum.L1_2_2_2, BusinessModulesEnum.L1_2_2_3});
        //L1.2.2.1 - Purchase Orders 
        addChildrens(nodes.get(BusinessModulesEnum.L1_2_2_1), new BusinessModulesEnum[]{BusinessModulesEnum.P01, BusinessModulesEnum.P02, BusinessModulesEnum.P03, BusinessModulesEnum.P04, BusinessModulesEnum.P05});
        //L1.2.2.2 - Supplier Requests
        addChildrens(nodes.get(BusinessModulesEnum.L1_2_2_2), new BusinessModulesEnum[]{BusinessModulesEnum.SR1, BusinessModulesEnum.SR2, BusinessModulesEnum.SR3});
        //L1.2.2.3 - Suppliers
        addChildrens(nodes.get(BusinessModulesEnum.L1_2_2_3), new BusinessModulesEnum[]{BusinessModulesEnum.S11, BusinessModulesEnum.S12, BusinessModulesEnum.S13});
        // L1.3 - Plant Maintenance
        // L1.3.1 - Counters | L1.3.2 - Equipments | L1.3.3 - Failures Management | L1.3.4 - Locations | L1.3.5 - Routine Worksheets | L1.3.6 - Strategies | L1.3.7 - Work Orders | L1.3.8 - Work Requets
        addChildrens(nodes.get(BusinessModulesEnum.L1_3), new BusinessModulesEnum[]{BusinessModulesEnum.L1_3_1, BusinessModulesEnum.L1_3_2, BusinessModulesEnum.L1_3_3, BusinessModulesEnum.L1_3_4, BusinessModulesEnum.L1_3_5, BusinessModulesEnum.L1_3_6, BusinessModulesEnum.L1_3_7, BusinessModulesEnum.L1_3_8});
        //L1.3.1 - Counters
        addChildrens(nodes.get(BusinessModulesEnum.L1_3_1), new BusinessModulesEnum[]{BusinessModulesEnum.C11, BusinessModulesEnum.C12, BusinessModulesEnum.C13, BusinessModulesEnum.C14, BusinessModulesEnum.C15});
        //L1.3.2 - Equipments
        addChildrens(nodes.get(BusinessModulesEnum.L1_3_2), new BusinessModulesEnum[]{BusinessModulesEnum.E01, BusinessModulesEnum.E02, BusinessModulesEnum.E03, BusinessModulesEnum.E04});
        //L1.3.3 - Failures Management
        addChildrens(nodes.get(BusinessModulesEnum.L1_3_3), new BusinessModulesEnum[]{BusinessModulesEnum.F01, BusinessModulesEnum.F02, BusinessModulesEnum.F03, BusinessModulesEnum.F04});
        //L1.3.4 - Locations
        addChildrens(nodes.get(BusinessModulesEnum.L1_3_4), new BusinessModulesEnum[]{BusinessModulesEnum.L01, BusinessModulesEnum.L02, BusinessModulesEnum.L03, BusinessModulesEnum.L04});
        //L1.3.5 - Routine Worksheets
        addChildrens(nodes.get(BusinessModulesEnum.L1_3_5), new BusinessModulesEnum[]{BusinessModulesEnum.R01, BusinessModulesEnum.R02, BusinessModulesEnum.R03, BusinessModulesEnum.R04});
        //L1.3.6 - Strategies
        addChildrens(nodes.get(BusinessModulesEnum.L1_3_6), new BusinessModulesEnum[]{BusinessModulesEnum.E21, BusinessModulesEnum.E22, BusinessModulesEnum.E23, BusinessModulesEnum.E24});
        //L1.3.7 - Work Orders
        addChildrens(nodes.get(BusinessModulesEnum.L1_3_7), new BusinessModulesEnum[]{BusinessModulesEnum.W11, BusinessModulesEnum.W12, BusinessModulesEnum.W13, BusinessModulesEnum.W14, BusinessModulesEnum.W15, BusinessModulesEnum.W16});
        //L1.3.8 - Work Requets
        addChildrens(nodes.get(BusinessModulesEnum.L1_3_8), new BusinessModulesEnum[]{BusinessModulesEnum.WR1, BusinessModulesEnum.WR2, BusinessModulesEnum.WR3});
        // L1.4 - Production
        // L1.4.1 - Planning | L1.4.2 - Quality | L1.4.3 - Master Data
        addChildrens(nodes.get(BusinessModulesEnum.L1_4), new BusinessModulesEnum[]{BusinessModulesEnum.L1_4_1, BusinessModulesEnum.L1_4_2, BusinessModulesEnum.L1_4_3});
        // L1.4.1 - Planning
        // L1.4.1.1 - Production Orders | L1.4.1.2 - Production Tracking | L1.4.1.3 - Resources Planning
        addChildrens(nodes.get(BusinessModulesEnum.L1_4_1), new BusinessModulesEnum[]{BusinessModulesEnum.L1_4_1_1, BusinessModulesEnum.L1_4_1_2, BusinessModulesEnum.L1_4_1_3});
        //L1.4.1.1 - Production Orders
        addChildrens(nodes.get(BusinessModulesEnum.L1_4_1_1), new BusinessModulesEnum[]{BusinessModulesEnum.P11, BusinessModulesEnum.P12, BusinessModulesEnum.P13, BusinessModulesEnum.P14, BusinessModulesEnum.P15});
        //L1.4.1.2 - Production Tracking 
        addChildrens(nodes.get(BusinessModulesEnum.L1_4_1_2), new BusinessModulesEnum[]{BusinessModulesEnum.PT1, BusinessModulesEnum.PT2, BusinessModulesEnum.PT3, BusinessModulesEnum.PT4, BusinessModulesEnum.PT5});
        //L1.4.1.3 - Resources Planning
        addChildrens(nodes.get(BusinessModulesEnum.L1_4_1_3), new BusinessModulesEnum[]{BusinessModulesEnum.RP1, BusinessModulesEnum.RP2, BusinessModulesEnum.RP3, BusinessModulesEnum.RP4, BusinessModulesEnum.RP5, BusinessModulesEnum.RP6});
        // L1.4.2 - Quality
        // L1.4.2.1 - Inspection | L1.4.2.2 - Non Conformities
        addChildrens(nodes.get(BusinessModulesEnum.L1_4_2), new BusinessModulesEnum[]{BusinessModulesEnum.L1_4_2_1, BusinessModulesEnum.L1_4_2_2});
        //L1.4.2.1 - Inspection 
        addChildrens(nodes.get(BusinessModulesEnum.L1_4_2_1), new BusinessModulesEnum[]{BusinessModulesEnum.I01, BusinessModulesEnum.I02, BusinessModulesEnum.I03, BusinessModulesEnum.I04});
        //L1.4.2.2 - Non Conformities
        addChildrens(nodes.get(BusinessModulesEnum.L1_4_2_2), new BusinessModulesEnum[]{BusinessModulesEnum.NC1, BusinessModulesEnum.NC2});
        //L1.4.3 - Master Data
        addChildrens(nodes.get(BusinessModulesEnum.L1_4_3), new BusinessModulesEnum[]{BusinessModulesEnum.POBJ});
        // L1.5 - Projects Management
        // L1.5.1 - Management | L1.5.2 - Analysis | L1.5.3 - Master Data
        addChildrens(nodes.get(BusinessModulesEnum.L1_5), new BusinessModulesEnum[]{BusinessModulesEnum.L1_5_1, BusinessModulesEnum.L1_5_2, BusinessModulesEnum.L1_5_3});

        
        
        // L2 - Controlling
        // L2.1 - Areas | L2.2 - Cost Management | L2.3 - Financial Acounting | L2.4 - Master data | L2.5 - Societies | L2.6 - Treasury Management
        addChildrens(nodes.get(BusinessModulesEnum.L2), new BusinessModulesEnum[]{BusinessModulesEnum.L2_1, BusinessModulesEnum.L2_2, BusinessModulesEnum.L2_3, BusinessModulesEnum.L2_4, BusinessModulesEnum.L2_5, BusinessModulesEnum.L2_6});
        //L2.1 - Areas 
        addChildrens(nodes.get(BusinessModulesEnum.L2_1), new BusinessModulesEnum[]{BusinessModulesEnum.A01, BusinessModulesEnum.A02, BusinessModulesEnum.A03, BusinessModulesEnum.A04});
        // L2.2 - Cost Management
        // L2.2.1 - Cost Analysis | L2.2.2 - Cost Centers | L2.2.3 - Cost Orders
        addChildrens(nodes.get(BusinessModulesEnum.L2_2), new BusinessModulesEnum[]{BusinessModulesEnum.L2_2_1, BusinessModulesEnum.L2_2_2, BusinessModulesEnum.L2_2_3});
        //L2.2.1 - Cost Analysis 
        addChildrens(nodes.get(BusinessModulesEnum.L2_2_1), new BusinessModulesEnum[]{BusinessModulesEnum.C21, BusinessModulesEnum.C22, BusinessModulesEnum.C23, BusinessModulesEnum.C24, BusinessModulesEnum.C25});
        //L2.2.2- Cost Centers
        addChildrens(nodes.get(BusinessModulesEnum.L2_2_2), new BusinessModulesEnum[]{BusinessModulesEnum.C31, BusinessModulesEnum.C32, BusinessModulesEnum.C33});
        //L2.2.3 - Cost Orders
        addChildrens(nodes.get(BusinessModulesEnum.L2_2_3), new BusinessModulesEnum[]{BusinessModulesEnum.CO1, BusinessModulesEnum.CO2, BusinessModulesEnum.CO3, BusinessModulesEnum.CO4, BusinessModulesEnum.CO5});
        // L2.3 - Financial Acounting
        // L2.3.1 - Financial Statements | L2.3.2 - General Accounts | L2.3.3 - Journal Entries
        addChildrens(nodes.get(BusinessModulesEnum.L2_3), new BusinessModulesEnum[]{BusinessModulesEnum.L2_3_1, BusinessModulesEnum.L2_3_2, BusinessModulesEnum.L2_3_3});
        //L2.3.1 - Financial Statements
        addChildrens(nodes.get(BusinessModulesEnum.L2_3_1), new BusinessModulesEnum[]{BusinessModulesEnum.F11, BusinessModulesEnum.F12, BusinessModulesEnum.F13, BusinessModulesEnum.F14, BusinessModulesEnum.F15});
        //L2.3.2 - General Accounts 
        addChildrens(nodes.get(BusinessModulesEnum.L2_3_2), new BusinessModulesEnum[]{BusinessModulesEnum.G01, BusinessModulesEnum.G02, BusinessModulesEnum.G03, BusinessModulesEnum.G04, BusinessModulesEnum.G05});
        //L2.3.3 - Journal Entries
        addChildrens(nodes.get(BusinessModulesEnum.L2_3_3), new BusinessModulesEnum[]{BusinessModulesEnum.J01, BusinessModulesEnum.J02, BusinessModulesEnum.J03, BusinessModulesEnum.J04});
        //L2.4 - Master data (controlling)
        addChildrens(nodes.get(BusinessModulesEnum.L2_4), new BusinessModulesEnum[]{BusinessModulesEnum.COBJ});
        //L2.5 - Societies (controlling)
        addChildrens(nodes.get(BusinessModulesEnum.L2_5), new BusinessModulesEnum[]{BusinessModulesEnum.S21, BusinessModulesEnum.S22, BusinessModulesEnum.S23});
        // L2.6 - Treasury Management
        // L2.6.1 - Bank Accounts | L2.6.2 - Bank Reconciliation | L2.6.3 - Cash Flow | L2.6.4 - Liquidity Report
        addChildrens(nodes.get(BusinessModulesEnum.L2_6), new BusinessModulesEnum[]{BusinessModulesEnum.L2_6_1, BusinessModulesEnum.L2_6_2, BusinessModulesEnum.L2_6_3, BusinessModulesEnum.L2_6_4});
        //L2.6.1 - Bank Accounts
        addChildrens(nodes.get(BusinessModulesEnum.L2_6_1), new BusinessModulesEnum[]{BusinessModulesEnum.B11, BusinessModulesEnum.B12, BusinessModulesEnum.B13, BusinessModulesEnum.B14});


        
        // L3 - Human Resources
        // L3.1 - Certifications | L3.2 - Employee Management | L3.3 - Master data | L3.4 - Payroll Analysis | L3.5 - Payroll Management | L3.6 - Performance Evaluations | L3.7 - Time Management | L3.8 - Training Plans  
        addChildrens(nodes.get(BusinessModulesEnum.L3), new BusinessModulesEnum[]{BusinessModulesEnum.L3_1, BusinessModulesEnum.L3_2, BusinessModulesEnum.L3_3, BusinessModulesEnum.L3_4, BusinessModulesEnum.L3_5, BusinessModulesEnum.L3_6, BusinessModulesEnum.L3_7, BusinessModulesEnum.L3_8});
        //L3.1 - Certifications 
        addChildrens(nodes.get(BusinessModulesEnum.L3_1), new BusinessModulesEnum[]{BusinessModulesEnum.CR31, BusinessModulesEnum.CR32, BusinessModulesEnum.CR33});
        //L3.2 - Employee Management
        addChildrens(nodes.get(BusinessModulesEnum.L3_2), new BusinessModulesEnum[]{BusinessModulesEnum.EM1, BusinessModulesEnum.EM2, BusinessModulesEnum.EM3, BusinessModulesEnum.EM4, BusinessModulesEnum.EM5});
        //L3.3 - Master data
        addChildrens(nodes.get(BusinessModulesEnum.L3_3), new BusinessModulesEnum[]{BusinessModulesEnum.HROJ});
        // L3.4 - Payroll Analysis 
        addChildrens(nodes.get(BusinessModulesEnum.L3_4), new BusinessModulesEnum[]{BusinessModulesEnum.PA1, BusinessModulesEnum.PA2, BusinessModulesEnum.PA3, BusinessModulesEnum.PA4, BusinessModulesEnum.PA5});
        //L3.5 - Payroll Management 
        addChildrens(nodes.get(BusinessModulesEnum.L3_5), new BusinessModulesEnum[]{BusinessModulesEnum.PM1, BusinessModulesEnum.PM2, BusinessModulesEnum.PM3, BusinessModulesEnum.PM4, BusinessModulesEnum.PM5, BusinessModulesEnum.PM6});
        //L3.6 - Performance Evaluations 
        addChildrens(nodes.get(BusinessModulesEnum.L3_6), new BusinessModulesEnum[]{BusinessModulesEnum.PE1, BusinessModulesEnum.PE2, BusinessModulesEnum.PE3, BusinessModulesEnum.PE4, BusinessModulesEnum.PE5, BusinessModulesEnum.PE6});
        //L3.7 - Time Management 
        addChildrens(nodes.get(BusinessModulesEnum.L3_7), new BusinessModulesEnum[]{BusinessModulesEnum.T01, BusinessModulesEnum.T02, BusinessModulesEnum.T03, BusinessModulesEnum.T04, BusinessModulesEnum.T05, BusinessModulesEnum.T06});
        //L3.8 - Training Plans  
        addChildrens(nodes.get(BusinessModulesEnum.L3_8), new BusinessModulesEnum[]{BusinessModulesEnum.TP1, BusinessModulesEnum.TP2, BusinessModulesEnum.TP3, BusinessModulesEnum.TP4, BusinessModulesEnum.TP5, BusinessModulesEnum.TP6});

        
        
        // L4 - Business Intelligence
        // L4.1 - Analysis | L4.2 - Reports
        addChildrens(nodes.get(BusinessModulesEnum.L4), new BusinessModulesEnum[]{BusinessModulesEnum.L4_1, BusinessModulesEnum.L4_2});



        // L5 - System
        // L5.1 - Access | L5.2 - Users | L5.3 - Master Data
        addChildrens(nodes.get(BusinessModulesEnum.L5), new BusinessModulesEnum[]{BusinessModulesEnum.L5_1, BusinessModulesEnum.L5_2, BusinessModulesEnum.L5_3});
        //L5.1 - Access 
        addChildrens(nodes.get(BusinessModulesEnum.L5_1), new BusinessModulesEnum[]{BusinessModulesEnum.AC01, BusinessModulesEnum.AC02, BusinessModulesEnum.AC03, BusinessModulesEnum.AC04, BusinessModulesEnum.A05, BusinessModulesEnum.A06});
        //L5.2 - Users 
        addChildrens(nodes.get(BusinessModulesEnum.L5_2), new BusinessModulesEnum[]{BusinessModulesEnum.U01, BusinessModulesEnum.U02, BusinessModulesEnum.U03, BusinessModulesEnum.U04, BusinessModulesEnum.U05});
        //L5.3 - Master Data
        addChildrens(nodes.get(BusinessModulesEnum.L5_3), new BusinessModulesEnum[]{BusinessModulesEnum.SYSOBJ});
        
        return root;
    }
}
