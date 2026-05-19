package com.simplecore.erp.client.controllers.workspace;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
import com.simplecore.erp.client.abstractions.FormState;
import com.simplecore.erp.client.controllers.gui.PanelLoader;
import com.simplecore.erp.client.controllers.servicebuttons.ButtonServices;
import com.simplecore.erp.client.gui.utils.pdfvisor.PDFVisorListener;
import com.simplecore.erp.client.gui.workspace.frontend.Workspace;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.client.utils.sound.Sound;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Stack;

public class PanelManager {

    private static final Stack<JPanel> panelStack = new Stack<>(); // Stack to manage panel history | Pila para gestionar el historial de paneles
    private static JPanel workspacePanel; // The main workspace panel | Panel principal de trabajo
    private static JPanel currentPanel; // The currently active panel | Panel actualmente activo
    private static JLabel transactionLb = Workspace.getTransactionLb();
    private static final ButtonServices backButton = Workspace.getBackButton(); // Reference to the back button | Referencia al botón de regreso
    private static final ButtonServices closeButton = Workspace.getCloseButton();
    private static final ButtonServices cancelButton = Workspace.getCancelButton();
    private static final ButtonServices saveButton = Workspace.getSaveButton(); // Reference to the save button | Referencia al botón de guardado
    private static ActionListener backAction = e -> goBack(); // Default back action | Acción predeterminada para regresar
    private static ActionListener closeAction = e -> goToFirstPanel();
    private static ActionListener cancelAction = e -> clearStackAndGoToWorkspace();
    private static String backTitle = AppMessages.msg(AppMessages.Key.UNSAVE_CHANGES); // Default confirmation title | Título predeterminado de confirmación
    private static String backMessage = AppMessages.msg(AppMessages.Key.ARE_YOU_SURE_YOU_WANT_TO_PROCEED); // Default confirmation message | Mensaje predeterminado de confirmación

    public static void setWorkspace(JPanel workspace) {
        workspacePanel = workspace;
        currentPanel = workspace; // Set initial panel as workspace | Establece el panel inicial como el espacio de trabajo
        backButton.addTaskAction(backAction);
        closeButton.addTaskAction(closeAction);
        cancelButton.addTaskAction(cancelAction);
    }

    public static void goToPanel(JPanel newPanel) {
        if (newPanel == null || newPanel == currentPanel) {
            return; // Prevent null or redundant transitions | Evita transiciones nulas o redundantes
        }
        if (currentPanel != null) {
            panelStack.push(currentPanel); // Save the current panel before moving forward | Guarda el panel actual antes de avanzar
        }

        if (currentPanel instanceof TaskPanel) {
            saveButton.setEnabled(false);

        }
        currentPanel = newPanel;
        // Si el nuevo panel es PanelTask, ajustamos el botón según isTasking()
        if (currentPanel instanceof TaskPanel taskPanel) {
            saveButton.setEnabled(taskPanel.isTaskRunning());
            saveButton.clearTaskActions();
            transactionLb.setText("Transaction | " + taskPanel.getTransactionCode() + " |");
            ActionListener action = taskPanel.getOnTaskComplete();
            if (action != null) { // Solo agrega la acción si no es nula
                saveButton.setSound(Sound.DONE);
                saveButton.addTaskAction(action);
            } else {
               saveButton.setSound(null);
            }
        } else {
            transactionLb.setText("Workspace |");
            saveButton.setEnabled(false); // Si el nuevo panel no es una tarea, el botón siempre se deshabilita
        }
        updateUI(currentPanel); // Update the UI to display the new panel | Actualiza la UI para mostrar el nuevo panel
    }

    public static void goBack() {
        if (currentPanel instanceof TaskPanel task) {
            FormState state = task.getFormState();
            if (state != null) {
                int percent = state.getCompletionPercent();
                if (state.isFormInProgress()) {
                    int choice = JOptionPane.showConfirmDialog(
                            null,
                            backMessage + " " + percent + " % " + AppMessages.msg(AppMessages.Key.FIELDS_COMPLETED),
                            backTitle,
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (choice == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }
        }
        if(currentPanel instanceof PDFVisorListener visor){
            visor.onPanelClose();
        }

        if (!panelStack.isEmpty()) {
            currentPanel = panelStack.pop();
        } else if (currentPanel != workspacePanel) {
            currentPanel = workspacePanel;
            panelStack.clear();
            transactionLb.setText("Workspace |");
        } else {
            return;
        }
        // Si el panel actual es una tarea, ajustamos el botón
        if (currentPanel instanceof TaskPanel taskPanel) {
            saveButton.setEnabled(taskPanel.isTaskRunning());
            saveButton.clearTaskActions();
            transactionLb.setText("Transaction | " + taskPanel.getTransactionCode() + " |");

            ActionListener action = taskPanel.getOnTaskComplete();
            if (action != null) { // Solo agrega la acción si no es nula
                saveButton.addTaskAction(action);
            }
        } else {
            transactionLb.setText("Workspace |");
            saveButton.setEnabled(false);
        }

        updateUI(currentPanel);
    }

    private static void goToFirstPanel() {
        if (currentPanel instanceof TaskPanel task) {
            FormState state = task.getFormState();
            if (state != null) {
                int percent = state.getCompletionPercent();
                if (state.isFormInProgress()) {
                    int choice = JOptionPane.showConfirmDialog(
                            null,
                            backMessage + " " + percent + " % " + AppMessages.msg(AppMessages.Key.FIELDS_COMPLETED),
                            backTitle,
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (choice == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }
        }

        // Verificamos si hay al menos dos elementos en la pila
        if (panelStack.size() > 1) {
            currentPanel = panelStack.get(1); // El segundo panel está en el índice 1
            while (panelStack.size() > 1) { // Mientras haya más de dos elementos (panel 0 y panel 1)
                panelStack.pop(); // Eliminar los paneles adicionales
            }
             if (currentPanel instanceof TaskPanel taskPanel) {
                 transactionLb.setText("Transaction | "+taskPanel.getTransactionCode()+" |");
             }
            // Si hay al menos dos paneles, vamos al segundo
            updateUI(currentPanel); // Actualizamos la interfaz
        } else {
            // Si no hay segundo panel, vamos al primero y limpiamos la pila
            if (!panelStack.isEmpty()) {
                JPanel firstPanel = panelStack.firstElement();
                if (firstPanel != null) {
                    panelStack.clear(); // Limpiar la pila después de obtener el primer panel
                    currentPanel = firstPanel; // Asignar el primer panel como el actual
                    transactionLb.setText("Workspace |");
                    updateUI(currentPanel); // Actualizar la interfaz
                }
            }
        }
    }

    private static void clearStackAndGoToWorkspace() {
        if (currentPanel instanceof TaskPanel task) {
            FormState state = task.getFormState();
            if (state != null) {
                int percent = state.getCompletionPercent();
                if (state.isFormInProgress()) {
                    int choice = JOptionPane.showConfirmDialog(
                            null,
                            backMessage + " " + percent + " % " + AppMessages.msg(AppMessages.Key.FIELDS_COMPLETED),
                            backTitle,
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (choice == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }
        }

        if (currentPanel != workspacePanel) {
            panelStack.clear(); // Clear navigation history | Limpia el historial de navegación
            currentPanel = workspacePanel;
            transactionLb.setText("Workspace |");
            updateUI(currentPanel);
        }
    }

    private static void updateUI(JPanel panel) {
        if (panel != null) {
            PanelLoader.loadPanel(panel, Workspace.getMainContainer()); // Load the specified panel into the main container | Carga el panel especificado en el contenedor principal
        }
    }

    public static boolean canGoBack() {
        return !panelStack.isEmpty(); // Returns true if there is a panel to go back to | Devuelve true si hay un panel al cual regresar
    }

    public static void clearStack() {
        panelStack.clear(); // Clears the panel stack | Limpia la pila de paneles
    }

}
