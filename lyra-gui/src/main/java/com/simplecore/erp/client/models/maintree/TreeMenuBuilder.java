package com.simplecore.erp.client.models.maintree;

import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.i18n.TranslationHelper;
import com.simplecore.erp.client.i18n.TranslatorType;
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
    private Map<BusinessMenu, DefaultMutableTreeNode> nodes;
    private static TranslationHelper treeTranslator;

    /**
     * Constructor de la clase. Inicializa el mapa de nodos.
     */
    public TreeMenuBuilder() {
        nodes = new HashMap<>();
        treeTranslator = Workspace.translators(TranslatorType.TREE);
    }

    /**
     * Construye un {@link DefaultMutableTreeNode} a partir de un
     * {@link BusinessModulesEnum}.
     *
     * @param node El nodo de negocio que se convertirá en un nodo del árbol.
     * @return Un {@link DefaultMutableTreeNode} con la información del nodo.
     */
    private static DefaultMutableTreeNode buildNode(BusinessMenu node) {
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
        String description = treeTranslator.getTranslation(key);
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
    private void addChildrens(DefaultMutableTreeNode parent, BusinessMenu[] childNodes) {
        for (BusinessMenu node : childNodes) {
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
    private void buildIndividualNode(BusinessMenu node) {
        nodes.put(node, buildNode(node));
    }

    /**
     * Construye todos los nodos del árbol a partir de los valores del enum
     * {@link BusinessModulesEnum}.
     */
    private void buildNodes() {
        for (BusinessMenu node : BusinessMenu.values()) {
            buildIndividualNode(node);
        }
    }
    
    /**
     * Construye y devuelve la jerarquía completa del árbol de menús. La
     * estructura del árbol está definida de manera estática en este método.
     *
     * @return El nodo raíz del árbol, que contiene toda la jerarquía de menús.
     */
    public DefaultMutableTreeNode createHierarchy(){
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("CORE Menu");
        buildNodes();
        
        addChildrens(root, new BusinessMenu[]{BusinessMenu.FM,BusinessMenu.LO,BusinessMenu.HR,BusinessMenu.QC,BusinessMenu.BI,BusinessMenu.SYS});
        addChildrens(nodes.get(BusinessMenu.FM), new BusinessMenu[]{BusinessMenu.FI,BusinessMenu.CO});
        addChildrens(nodes.get(BusinessMenu.FI), new BusinessMenu[]{BusinessMenu.FI_ENT,BusinessMenu.FI_COA,BusinessMenu.FI_ACC,BusinessMenu.FI_RUL,BusinessMenu.FI_BOK,BusinessMenu.FI_FRT,BusinessMenu.FI_SMG,BusinessMenu.FI_CMG,BusinessMenu.FI_FAM,BusinessMenu.FI_TAX,BusinessMenu.FI_TBM});
        
        addChildrens(nodes.get(BusinessMenu.FI_ENT), new BusinessMenu[]{BusinessMenu.EN01,BusinessMenu.EN02,BusinessMenu.EN03,BusinessMenu.EN04});
        addChildrens(nodes.get(BusinessMenu.FI_COA), new BusinessMenu[]{BusinessMenu.CO01,BusinessMenu.CO02,BusinessMenu.CO03,BusinessMenu.CO04,BusinessMenu.CO05});
        addChildrens(nodes.get(BusinessMenu.FI_ACC), new BusinessMenu[]{BusinessMenu.AC11,BusinessMenu.AC12,BusinessMenu.AC13,BusinessMenu.AC14,BusinessMenu.AC15,BusinessMenu.AC16,BusinessMenu.AC17,BusinessMenu.AC18});
        addChildrens(nodes.get(BusinessMenu.FI_RUL), new BusinessMenu[]{BusinessMenu.RU01,BusinessMenu.RU02,BusinessMenu.RU03,BusinessMenu.RU04});
        
        addChildrens(nodes.get(BusinessMenu.FI_BOK), new BusinessMenu[]{BusinessMenu.FI_BOK_JRN,BusinessMenu.FI_BOK_GL});
        
        addChildrens(nodes.get(BusinessMenu.FI_BOK_JRN), new BusinessMenu[]{BusinessMenu.JR01,BusinessMenu.JR02,BusinessMenu.JR03,BusinessMenu.JR04,BusinessMenu.JR05});
        addChildrens(nodes.get(BusinessMenu.FI_BOK_GL), new BusinessMenu[]{BusinessMenu.GL01,BusinessMenu.GL02,BusinessMenu.GL03,BusinessMenu.GL04,BusinessMenu.GL05});
        
        addChildrens(nodes.get(BusinessMenu.FI_FRT), new BusinessMenu[]{BusinessMenu.FI_FRT_IST,BusinessMenu.FI_FRT_BSH,BusinessMenu.FI_FRT_CFS});
        addChildrens(nodes.get(BusinessMenu.FI_FRT_IST), new BusinessMenu[]{BusinessMenu.ST01,BusinessMenu.ST02,BusinessMenu.ST03,BusinessMenu.ST04});
        addChildrens(nodes.get(BusinessMenu.FI_FRT_BSH), new BusinessMenu[]{BusinessMenu.BS01,BusinessMenu.BS02,BusinessMenu.BS03,BusinessMenu.BS04,BusinessMenu.BS05});
        addChildrens(nodes.get(BusinessMenu.FI_FRT_CFS), new BusinessMenu[]{BusinessMenu.CF01,BusinessMenu.CF02,BusinessMenu.CF03,BusinessMenu.CF04,BusinessMenu.CF05});
        addChildrens(nodes.get(BusinessMenu.FI_SMG), new BusinessMenu[]{BusinessMenu.SM01,BusinessMenu.SM02,BusinessMenu.SM03,BusinessMenu.SM04,BusinessMenu.SM05});
        addChildrens(nodes.get(BusinessMenu.FI_CMG), new BusinessMenu[]{BusinessMenu.CM01,BusinessMenu.CM02,BusinessMenu.CM03,BusinessMenu.CM04,BusinessMenu.CM05,BusinessMenu.CM06,BusinessMenu.CM07,BusinessMenu.CM08,BusinessMenu.CM09});
        addChildrens(nodes.get(BusinessMenu.FI_FAM), new BusinessMenu[]{BusinessMenu.FA01,BusinessMenu.FA02,BusinessMenu.FA03,BusinessMenu.FA04,BusinessMenu.FA05});
        addChildrens(nodes.get(BusinessMenu.FI_TAX), new BusinessMenu[]{BusinessMenu.TX01,BusinessMenu.TX02,BusinessMenu.TX03,BusinessMenu.TX04,BusinessMenu.TX05,BusinessMenu.TX06,BusinessMenu.TX07});
        addChildrens(nodes.get(BusinessMenu.FI_TBM), new BusinessMenu[]{BusinessMenu.BK01,BusinessMenu.BK02,BusinessMenu.BK03,BusinessMenu.BK04});
        
        addChildrens(nodes.get(BusinessMenu.CO), new BusinessMenu[]{BusinessMenu.CO_ENT,BusinessMenu.CO_CCT,BusinessMenu.CO_CDT,BusinessMenu.CO_COR,BusinessMenu.CO_PAC});
        addChildrens(nodes.get(BusinessMenu.CO_ENT), new BusinessMenu[]{BusinessMenu.CE01,BusinessMenu.CE02,BusinessMenu.CE03,BusinessMenu.CE04});
        addChildrens(nodes.get(BusinessMenu.CO_CCT), new BusinessMenu[]{BusinessMenu.CC01,BusinessMenu.CC02,BusinessMenu.CC03,BusinessMenu.CC04});
        addChildrens(nodes.get(BusinessMenu.CO_CDT), new BusinessMenu[]{BusinessMenu.AC01,BusinessMenu.AC02});
        addChildrens(nodes.get(BusinessMenu.CO_COR), new BusinessMenu[]{BusinessMenu.OR01,BusinessMenu.OR02,BusinessMenu.OR03,BusinessMenu.OR04});
        addChildrens(nodes.get(BusinessMenu.CO_PAC), new BusinessMenu[]{BusinessMenu.PC01,BusinessMenu.PC02,BusinessMenu.PC03,BusinessMenu.PC04,BusinessMenu.PC05,BusinessMenu.PC06,BusinessMenu.PC07});
        
        addChildrens(nodes.get(BusinessMenu.LO), new BusinessMenu[]{BusinessMenu.SD,BusinessMenu.PM,BusinessMenu.WM,BusinessMenu.LMG,BusinessMenu.PPM});
        
        addChildrens(nodes.get(BusinessMenu.SD), new BusinessMenu[]{BusinessMenu.SD_CMG,BusinessMenu.SD_COR,BusinessMenu.SD_PDS});
        addChildrens(nodes.get(BusinessMenu.SD_CMG), new BusinessMenu[]{BusinessMenu.CS01,BusinessMenu.CS02,BusinessMenu.CS03,BusinessMenu.CS04});
        addChildrens(nodes.get(BusinessMenu.SD_COR), new BusinessMenu[]{BusinessMenu.COR1,BusinessMenu.COR2,BusinessMenu.COR3,BusinessMenu.COR4,BusinessMenu.COR5,BusinessMenu.COR6,BusinessMenu.COR7});
        addChildrens(nodes.get(BusinessMenu.SD_PDS), new BusinessMenu[]{BusinessMenu.DP01,BusinessMenu.DP02});
        
        addChildrens(nodes.get(BusinessMenu.PM), new BusinessMenu[]{BusinessMenu.PM_PRQ,BusinessMenu.PM_POR,BusinessMenu.PM_SUP});
        addChildrens(nodes.get(BusinessMenu.PM_PRQ), new BusinessMenu[]{BusinessMenu.PR01,BusinessMenu.PR02,BusinessMenu.PR03,BusinessMenu.PR04});
        addChildrens(nodes.get(BusinessMenu.PM_POR), new BusinessMenu[]{BusinessMenu.PO01,BusinessMenu.PO02,BusinessMenu.PO03,BusinessMenu.PO04});
        addChildrens(nodes.get(BusinessMenu.PM_SUP), new BusinessMenu[]{BusinessMenu.SUP1,BusinessMenu.SUP2,BusinessMenu.SUP3,BusinessMenu.SUP4});
        
        addChildrens(nodes.get(BusinessMenu.WM), new BusinessMenu[]{BusinessMenu.WM_WMT,BusinessMenu.WM_WHS,BusinessMenu.WM_INV});
        addChildrens(nodes.get(BusinessMenu.WM_WMT), new BusinessMenu[]{BusinessMenu.WM01,BusinessMenu.WM02,BusinessMenu.WM03,BusinessMenu.WM04});
        addChildrens(nodes.get(BusinessMenu.WM_WHS), new BusinessMenu[]{BusinessMenu.WH01,BusinessMenu.WH02,BusinessMenu.WH03,BusinessMenu.WH04});
        addChildrens(nodes.get(BusinessMenu.WM_INV), new BusinessMenu[]{BusinessMenu.MI01,BusinessMenu.MI02,BusinessMenu.MI03,BusinessMenu.MI04});
        
        addChildrens(nodes.get(BusinessMenu.LMG), new BusinessMenu[]{BusinessMenu.LMG_LCT,BusinessMenu.LMG_LSC,BusinessMenu.LMG_LPC,BusinessMenu.LMG_LAR,BusinessMenu.LMG_LST,BusinessMenu.LMG_LPG});
        addChildrens(nodes.get(BusinessMenu.LMG_LCT), new BusinessMenu[]{BusinessMenu.LC01,BusinessMenu.LC02,BusinessMenu.LC03,BusinessMenu.LC04});
        addChildrens(nodes.get(BusinessMenu.LMG_LSC), new BusinessMenu[]{BusinessMenu.SC01,BusinessMenu.SC02,BusinessMenu.SC03,BusinessMenu.SC04});
        addChildrens(nodes.get(BusinessMenu.LMG_LPC), new BusinessMenu[]{BusinessMenu.PC11,BusinessMenu.PC12,BusinessMenu.PC13,BusinessMenu.PC14});
        addChildrens(nodes.get(BusinessMenu.LMG_LAR), new BusinessMenu[]{BusinessMenu.CA01,BusinessMenu.CA02,BusinessMenu.CA03,BusinessMenu.CA04});
        addChildrens(nodes.get(BusinessMenu.LMG_LST), new BusinessMenu[]{BusinessMenu.ST11,BusinessMenu.ST12,BusinessMenu.ST13,BusinessMenu.ST14});
        addChildrens(nodes.get(BusinessMenu.LMG_LPG), new BusinessMenu[]{BusinessMenu.PG01,BusinessMenu.PG02,BusinessMenu.PG03,BusinessMenu.PG04});
        
        addChildrens(nodes.get(BusinessMenu.PPM), new BusinessMenu[]{BusinessMenu.PPM_PP,BusinessMenu.PPM_OS,BusinessMenu.PPM_MM});
        addChildrens(nodes.get(BusinessMenu.PPM_PP), new BusinessMenu[]{BusinessMenu.PP01,BusinessMenu.PP02,BusinessMenu.PP03});
        addChildrens(nodes.get(BusinessMenu.PPM_OS), new BusinessMenu[]{BusinessMenu.PP04,BusinessMenu.PP05,BusinessMenu.PP06});
        
        addChildrens(nodes.get(BusinessMenu.PPM_MM), new BusinessMenu[]{BusinessMenu.PPM_MM_ASM,BusinessMenu.PPM_MM_TLC,BusinessMenu.PPM_MM_MNT,BusinessMenu.PPM_MM_MWO,BusinessMenu.PPM_MM_TSK,BusinessMenu.PPM_MM_MPL,BusinessMenu.PPM_MM_MMT});
        addChildrens(nodes.get(BusinessMenu.PPM_MM_ASM), new BusinessMenu[]{BusinessMenu.ME01,BusinessMenu.ME02,BusinessMenu.ME03,BusinessMenu.ME04});
        addChildrens(nodes.get(BusinessMenu.PPM_MM_TLC), new BusinessMenu[]{BusinessMenu.ML01,BusinessMenu.ML02,BusinessMenu.ML03});
        addChildrens(nodes.get(BusinessMenu.PPM_MM_MNT), new BusinessMenu[]{BusinessMenu.MN01,BusinessMenu.MN02,BusinessMenu.MN03,BusinessMenu.MN04});
        addChildrens(nodes.get(BusinessMenu.PPM_MM_MWO), new BusinessMenu[]{BusinessMenu.MO01,BusinessMenu.MO02,BusinessMenu.MO03,BusinessMenu.MO04,BusinessMenu.MO05});
        addChildrens(nodes.get(BusinessMenu.PPM_MM_TSK), new BusinessMenu[]{BusinessMenu.MT01,BusinessMenu.MT02,BusinessMenu.MT03});
        addChildrens(nodes.get(BusinessMenu.PPM_MM_MPL), new BusinessMenu[]{BusinessMenu.MP01,BusinessMenu.MP02,BusinessMenu.MP03,BusinessMenu.MP04});
        addChildrens(nodes.get(BusinessMenu.PPM_MM_MMT), new BusinessMenu[]{BusinessMenu.MM01,BusinessMenu.MM02,BusinessMenu.MM03,BusinessMenu.MM04,BusinessMenu.MM05});
        
        addChildrens(nodes.get(BusinessMenu.QC), new BusinessMenu[]{BusinessMenu.QC_MNG});
        addChildrens(nodes.get(BusinessMenu.QC_MNG), new BusinessMenu[]{BusinessMenu.QC01,BusinessMenu.QC02,BusinessMenu.QC03});
        
        
        addChildrens(nodes.get(BusinessMenu.HR), new BusinessMenu[]{BusinessMenu.HR_PMG,BusinessMenu.HR_PBF});
        addChildrens(nodes.get(BusinessMenu.HR_PMG), new BusinessMenu[]{BusinessMenu.HR01,BusinessMenu.HR02,BusinessMenu.HR03});
        addChildrens(nodes.get(BusinessMenu.HR_PBF), new BusinessMenu[]{BusinessMenu.HR04,BusinessMenu.HR05,BusinessMenu.HR06});
        
        addChildrens(nodes.get(BusinessMenu.BI), new BusinessMenu[]{BusinessMenu.BI_RAN});
        addChildrens(nodes.get(BusinessMenu.BI_RAN), new BusinessMenu[]{BusinessMenu.BI01,BusinessMenu.BI02,BusinessMenu.BI03,BusinessMenu.BI04});

        addChildrens(nodes.get(BusinessMenu.SYS), new BusinessMenu[]{BusinessMenu.SYS_USP,BusinessMenu.SYS_CFG});
        addChildrens(nodes.get(BusinessMenu.SYS_USP), new BusinessMenu[]{BusinessMenu.SYS_1,BusinessMenu.SYS_2,BusinessMenu.SYS_3});
        addChildrens(nodes.get(BusinessMenu.SYS_CFG), new BusinessMenu[]{BusinessMenu.SYS_4,BusinessMenu.SYS_5,BusinessMenu.SYS_6});
        
        return root;
    }
    

    
    
}
