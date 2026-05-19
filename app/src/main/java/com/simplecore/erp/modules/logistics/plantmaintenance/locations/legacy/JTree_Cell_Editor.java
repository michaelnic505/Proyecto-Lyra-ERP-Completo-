
package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.awt.Component;
import java.awt.Font;
import java.util.EventObject;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;


public class JTree_Cell_Editor implements TreeCellEditor{
    
    private JTree_Event event;
   
    

    public JTree_Cell_Editor(JTree_Event event) {
        this.event = event;
}

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        
        Nodo_JTree nodos = new Nodo_JTree();
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
        String nombreNodo = nodo.getUserObject().toString();
        
        nodos.eventoBotonDesplegar(event,row);
        
        
        if(leaf){
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/icons/locations/tree/location.png")));
            nodos.btnDesplegar.setIcon(new ImageIcon(getClass().getResource("/icons/locations/tree/deploy.png")));
        }else if (expanded){
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/icons/locations/tree/enterprise.png")));
            nodos.btnDesplegar.setIcon(new ImageIcon(getClass().getResource("/icons/locations/tree/deploy.png")));
        }else{
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/icons/locations/tree/shared_file.png")));
            nodos.btnDesplegar.setIcon(new ImageIcon(getClass().getResource("/icons/locations/tree/deploy.png")));
        }
        
        
        if (nodo.isRoot()) {
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/icons/locations/tree/shared_file.png")));
            nodos.textoNodo.setFont(new Font("Segoe UI", Font.BOLD, 15));
            nodos.descripcion_nodo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        }
        if (!nodo.isRoot()&& nodo.getChildCount()>0){
            nodos.textoNodo.setIcon(new ImageIcon(getClass().getResource("/icons/locations/tree/enterprise.png")));
        }                 
        
        
                
        nodos.textoNodo.setText(nombreNodo);        
        nodos.descripcion_nodo.setText(U03_Descripcion_Ubicacion.extraerDescripcion(nombreNodo));
        

                
        return nodos;
    }


    @Override
    public Object getCellEditorValue() {        
        return null;        
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
       return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
       return true;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {
        
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {

    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {

    }

}
