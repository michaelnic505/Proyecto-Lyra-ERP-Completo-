
package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class JTree_Cell_Renderer implements TreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        Nodo_JTree nodos = new Nodo_JTree();
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
        String nombreNodo = nodo.getUserObject().toString();



        if(leaf){
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/tree_location/ubicacion.png")));
            nodos.btnDesplegar.setIcon(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/tree_location/desplegable.png")));
        }else if (expanded){
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/tree_location/administracion.png")));
            nodos.btnDesplegar.setIcon(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/tree_location/desplegable.png")));
        }else{
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/main_tree/carpeta_cerrada.png")));
            nodos.btnDesplegar.setIcon(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/tree_location/desplegar.png")));
        }
        
        
        if (nodo.isRoot()) {
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/tree_location/empresa_root.png")));
            nodos.textoNodo.setFont(new Font("Segoe UI", Font.BOLD, 15));
            nodos.descripcion_nodo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        }
        if (!nodo.isRoot()&& nodo.getChildCount()>0){
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/lyra/access/modules/iconography/tree_location/administracion.png")));
        }              
        
        
                
        nodos.textoNodo.setText(nombreNodo);        
        nodos.descripcion_nodo.setText(U03_Descripcion_Ubicacion.extraerDescripcion(nombreNodo));
        
        
        
        if(selected){
            nodos.textoNodo.setForeground(Color.WHITE);
            nodos.descripcion_nodo.setForeground(Color.WHITE);
        }else{
            nodos.textoNodo.setForeground(Color.BLACK);
            nodos.descripcion_nodo.setForeground(Color.BLACK);
        }
    
                
        return nodos;
    }
    
}
