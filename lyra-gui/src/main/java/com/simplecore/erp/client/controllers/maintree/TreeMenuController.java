package com.simplecore.erp.client.controllers.maintree;

import com.simplecore.erp.client.controllers.transaction.TransactionRouter;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.models.maintree.TreeNodeData;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.client.utils.sound.Sound;
import com.simplecore.erp.client.utils.sound.SoundManager;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class TreeMenuController {

    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final  ActiveSession activeSession;
    private TreeTransactionService transactionService;
    
    public TreeMenuController(ObjectOutputStream output, ObjectInputStream input, ActiveSession activeSession) {
        this.output = output;
        this.input = input;
        this.activeSession = activeSession;
        transactionService = new TreeTransactionService(output, input, activeSession);
    }

    public void setController(JPanel treePanel,JTree tree) {
        expandOrCollapseListener(tree);
        doubleClickListener(tree);
    }
   
    private void doubleClickListener(JTree tree) {
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Verificar si fue un doble clic
                if (e.getClickCount() == 2) {
                    // Obtener el nodo seleccionado
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (selectedNode == null || !selectedNode.isLeaf()) {
                        return; // Si no hay nodo seleccionado o tiene hijos, salir.
                    }
                    // Recuperar el objeto TreeNodeData asociado al nodo
                    Object userObject = selectedNode.getUserObject();
                    if (userObject instanceof TreeNodeData nodeData) { // Uso de pattern matching en Java 16+
                        if (transactionService.checkTransactionAccess(nodeData.getCode())) {

                            TransactionRouter.routeTransaction(nodeData.getCode(),activeSession, output, input);
                            SoundManager.playSound(Sound.OPEN.dir());

                        }else{
                            new SystemMessages().showErrorMsg(AppMessages.msg(AppMessages.Key.ACCESS_DENIED));
                        }
                    }

                }
            }

        });
    }

    private static boolean isMassExpanding = false;

    private static void expandOrCollapseListener(JTree tree) {
        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) {
                if (!isMassExpanding) {
                    // Get the expanding node
                    DefaultMutableTreeNode expandingNode = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                    // Check if the node has children before playing sound
                    if (expandingNode.getChildCount() > 0) {
                        SoundManager.playSound(Sound.EXPAND_NODE.dir()); // Play expand sound
                    }
                }
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) {
                if(!isMassExpanding){
                SoundManager.playSound(Sound.COLLAPSE_NODE.dir());
                }
            }
        });
    }

    public static void showNodeCodeInTree(JTree tree) {
        toggleCodeVisibility(tree);
        tree.updateUI(); // Actualizar la interfaz de usuario del árbol
        tree.setSize(tree.getPreferredSize()); // Ajustar manualmente el ancho de las celdas
        tree.revalidate(); // Forzar la actualización del layout
    }

    private static void toggleCodeVisibility(JTree tree) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        toggleCodeVisibilityRecursive(root);
    }

    private static void toggleCodeVisibilityRecursive(DefaultMutableTreeNode node) {
        // Obtener el objeto TreeNodeData asociado al nodo
        Object userObject = node.getUserObject();
        if (userObject instanceof TreeNodeData) {
            TreeNodeData nodeData = (TreeNodeData) userObject;
            // Alternar la visibilidad del código
            nodeData.setShowCode(!nodeData.isShowCode());
        }

        // Recorrer los hijos del nodo
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(i);
            toggleCodeVisibilityRecursive(childNode);
        }
    }

    public static void setTreeExpandedState(JTree tree, boolean expanded) {
        isMassExpanding = true;
        TreeModel model = tree.getModel();
        Object root = model.getRoot();

        if (root != null) {
            TreePath rootPath = new TreePath(root);
            expandOrCollapseAll(tree, rootPath, expanded);
            
            // Asegurar que la raíz siempre quede expandida
            tree.expandPath(rootPath);
        }
        isMassExpanding = false;
    }

    private static void expandOrCollapseAll(JTree tree, TreePath parent, boolean expand) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (int i = 0; i < node.getChildCount(); i++) {
                TreeNode child = node.getChildAt(i);
                TreePath path = parent.pathByAddingChild(child);
                expandOrCollapseAll(tree, path, expand);
            }
        }
        if (expand) {
            tree.expandPath(parent);
        } else if (parent.getParentPath() != null) { 
            tree.collapsePath(parent);
        }
    }

}
