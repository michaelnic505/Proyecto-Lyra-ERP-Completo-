/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.simplecore.erp.models.maintree;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeVisibilityExample {

    public static void main(String[] args) {
        // Crear el JFrame
        JFrame frame = new JFrame("Visibilidad del código en el árbol");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Crear el árbol
        JTree tree = createTree();
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Ajustar el tamaño del JScrollPane
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botón para alternar la visibilidad del código
        JButton toggleButton = new JButton("Mostrar/Ocultar código");
        frame.add(toggleButton, BorderLayout.SOUTH);

        // Acción del botón
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCodeVisibility(tree);
                tree.updateUI(); // Actualizar la interfaz de usuario del árbol
                tree.setSize(tree.getPreferredSize()); // Ajustar manualmente el ancho de las celdas
                tree.revalidate(); // Forzar la actualización del layout
            }
        });

        // Mostrar el JFrame
        frame.setVisible(true);
    }

    /**
     * Alterna la visibilidad del código en todos los nodos del árbol.
     */
    private static void toggleCodeVisibility(JTree tree) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        toggleCodeVisibilityRecursive(root);
    }

    /**
     * Recorre recursivamente los nodos del árbol y alterna la visibilidad del código.
     */
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

    /**
     * Crea un JTree de ejemplo con nodos que contienen TreeNodeData.
     */
    private static JTree createTree() {
        // Crear la raíz del árbol
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Raíz");

        // Crear nodos con TreeNodeData
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(new TreeNodeData("L1", "Logística"));
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(new TreeNodeData("L2", "Controlling"));
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode(new TreeNodeData("L3", "Recursos Humanos"));

        // Agregar nodos a la raíz
        root.add(node1);
        root.add(node2);
        root.add(node3);

        // Crear el JTree
        return new JTree(root);
    }
}