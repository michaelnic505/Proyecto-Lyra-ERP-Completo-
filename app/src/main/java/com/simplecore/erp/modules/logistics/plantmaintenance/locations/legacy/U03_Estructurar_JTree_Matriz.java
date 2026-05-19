package com.simplecore.erp.modules.logistics.plantmaintenance.locations.legacy;

import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class U03_Estructurar_JTree_Matriz {

    private static ArrayList<String[]> matriz = new ArrayList<>();
    private ArrayList<DefaultMutableTreeNode> nodos2 = new ArrayList<>();
    private ArrayList<DefaultMutableTreeNode> nodos3 = new ArrayList<>();
    private ArrayList<DefaultMutableTreeNode> nodos4 = new ArrayList<>();
    private ArrayList<DefaultMutableTreeNode> nodos5 = new ArrayList<>();
    private ArrayList<DefaultMutableTreeNode> nodos6 = new ArrayList<>();
    private ArrayList<DefaultMutableTreeNode> nodos7 = new ArrayList<>();
    
    private DefaultMutableTreeNode nivel1 = null;
    private  DefaultTreeModel modeloTree;

    public void construirArbol(JTree tree) {
        
        extraerDataSQL();
        estableceNodoRoot();
        estableceNivel_2();
        estableceNivel_3();
        estableceNivel_4();
        estableceNivel_5();
        estableceNivel_6();
        estableceEquipoSuperiores();
        estableceEquipoInferiores();
        
        tree.setCellRenderer(new JTree_Cell_Renderer());        
        modeloTree = new DefaultTreeModel(nivel1);
        tree.setModel(modeloTree);
        
    }

    
    
    private void extraerDataSQL() {

        U03_Extracicon_Matriz_Ubicaciones nuevaExtraccion = new U03_Extracicon_Matriz_Ubicaciones();
        nuevaExtraccion.extraerMatrizDatos();
        matriz = nuevaExtraccion.getMatriz();
        
    }

    private void estableceNodoRoot() {

        if (!matriz.isEmpty()) {
            for (int i = 0; i < matriz.size(); i++) {
                if (matriz.get(i)[5].equals("1")) {
                    nivel1 = new DefaultMutableTreeNode(matriz.get(i)[0]);
                }
            }
        }

    }

    private void estableceNivel_2() {
        if (!matriz.isEmpty()) {
            for (int i = 0; i < matriz.size(); i++) {
                if (matriz.get(i)[5].equals("2")) {
                    nivel1.add(new DefaultMutableTreeNode(matriz.get(i)[0]));
                }
            }
        }

    }
    
        
    
    private void estableceNivel_3() {

        if (nivel1.getChildCount() > 0) {
            for (int i = 0; i < nivel1.getChildCount(); i++) {
                nodos2.add((DefaultMutableTreeNode) nivel1.getChildAt(i));
            }
        }

        if (!nodos2.isEmpty()) {
            for (int i = 0; i < nodos2.size(); i++) {
                for (int n = 0; n < matriz.size(); n++) {
                    if (matriz.get(n)[2].equals(nodos2.get(i).toString())) {
                        nodos2.get(i).add(new DefaultMutableTreeNode(matriz.get(n)[0]));
                    }
                }
            }
        }
    }
    
    
    
    private void estableceNivel_4() {

        if (!nodos2.isEmpty()) {
            for (int i = 0; i < nodos2.size(); i++) {
                if (nodos2.get(i).getChildCount() > 0) {
                    for (int n = 0; n < nodos2.get(i).getChildCount(); n++) {
                        nodos3.add((DefaultMutableTreeNode) nodos2.get(i).getChildAt(n));
                    }
                }
            }

        }
        if (!nodos3.isEmpty()) {
            for (int i = 0; i < nodos3.size(); i++) {
                for (int n = 0; n < matriz.size(); n++) {
                    if (matriz.get(n)[2].equals(nodos3.get(i).toString())) {
                        nodos3.get(i).add(new DefaultMutableTreeNode(matriz.get(n)[0]));
                    }
                }
            }
        }
    }
    
    

    private void estableceNivel_5() {

        if (!nodos3.isEmpty()) {
            for (int i = 0; i < nodos3.size(); i++) {
                if (nodos3.get(i).getChildCount() > 0) {
                    for (int n = 0; n < nodos3.get(i).getChildCount(); n++) {
                        nodos4.add((DefaultMutableTreeNode) nodos3.get(i).getChildAt(n));
                    }
                }
            }
        }

        if (!nodos4.isEmpty()) {
            for (int i = 0; i < nodos4.size(); i++) {
                for (int n = 0; n < matriz.size(); n++) {
                    if (matriz.get(n)[2].equals(nodos4.get(i).toString())) {
                        nodos4.get(i).add(new DefaultMutableTreeNode(matriz.get(n)[0]));
                    }
                }
            }
        }
    }

    
    
    private void estableceNivel_6() {

        if (!nodos4.isEmpty()) {
            for (int i = 0; i < nodos4.size(); i++) {
                if (nodos4.get(i).getChildCount() > 0) {
                    for (int n = 0; n < nodos4.get(i).getChildCount(); n++) {
                        nodos5.add((DefaultMutableTreeNode) nodos4.get(i).getChildAt(n));
                    }
                }
            }
        }

        if (!nodos5.isEmpty()) {
            for (int i = 0; i < nodos5.size(); i++) {
                for (int n = 0; n < matriz.size(); n++) {
                    if (matriz.get(n)[2].equals(nodos5.get(i).toString())) {
                        nodos5.get(i).add(new DefaultMutableTreeNode(matriz.get(n)[0]));
                    }
                }
            }
        }
    }

    private void estableceEquipoSuperiores() {

        if (!nodos5.isEmpty()) {
            for (int i = 0; i < nodos5.size(); i++) {
                if (nodos5.get(i).getChildCount() > 0) {
                    for (int n = 0; n < nodos5.get(i).getChildCount(); n++) {
                        nodos6.add((DefaultMutableTreeNode) nodos5.get(i).getChildAt(n));
                    }
                }
            }
        }

        if (!nodos6.isEmpty()) {
            for (int i = 0; i < nodos6.size(); i++) {
                for (int n = 0; n < matriz.size(); n++) {
                    if (matriz.get(n)[2].equals(nodos6.get(i).toString())) {
                        nodos6.get(i).add(new DefaultMutableTreeNode(matriz.get(n)[0]));
                    }
                }
            }
        }
    }

    private void estableceEquipoInferiores() {

        if (!nodos6.isEmpty()) {
            for (int i = 0; i < nodos6.size(); i++) {
                if (nodos6.get(i).getChildCount() > 0) {
                    for (int n = 0; n < nodos6.get(i).getChildCount(); n++) {
                        nodos7.add((DefaultMutableTreeNode) nodos6.get(i).getChildAt(n));
                    }
                }
            }
        }

        if (!nodos7.isEmpty()) {
            for (int i = 0; i < nodos7.size(); i++) {
                for (int n = 0; n < matriz.size(); n++) {
                    if (matriz.get(n)[2].equals(nodos7.get(i).toString())) {
                        nodos7.get(i).add(new DefaultMutableTreeNode(matriz.get(n)[0]));
                    }
                }
            }
        }
    }

}
