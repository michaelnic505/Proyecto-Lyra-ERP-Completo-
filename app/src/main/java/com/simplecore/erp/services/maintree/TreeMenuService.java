package com.simplecore.erp.services.maintree;

import com.simplecore.erp.controllers.maintree.ModulesController;
import com.simplecore.erp.models.maintree.BusinessTransactionsEnum;
import com.simplecore.erp.models.maintree.TreeMenuBuilder;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
public class TreeMenuService {

    public static void configureTree(JTree tree) {
        DefaultMutableTreeNode root = new TreeMenuBuilder().createHierarchy();
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree.setModel(model);
        tree.setCellRenderer(createRenderer());
    }

    private static DefaultTreeCellRenderer createRenderer() {
        ImageIcon closedFolderIcon = new ImageIcon(TreeMenuService.class.getResource("/icons/maintree/close_folder.png"));
        ImageIcon openFolderIcon = new ImageIcon(TreeMenuService.class.getResource("/icons/maintree/open_folder.png"));
        ImageIcon fileIcon = new ImageIcon(TreeMenuService.class.getResource("/icons/maintree/core.png"));

        return new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                setIcon(leaf ? fileIcon : (expanded ? openFolderIcon : closedFolderIcon));
                return this;
            }
        };
    }




}
