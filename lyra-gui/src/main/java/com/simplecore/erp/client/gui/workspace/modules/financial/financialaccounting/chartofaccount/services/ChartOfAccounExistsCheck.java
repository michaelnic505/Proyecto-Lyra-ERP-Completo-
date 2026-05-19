
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.chartofaccount.services;

import com.simplecore.erp.client.config.server.ServerController;
import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import com.simplecore.erp.shared.models.sessions.ActiveSession;
import com.simplecore.erp.shared.requests.types.ChartOfAccountExistsRequest;
import com.simplecore.erp.shared.responses.types.ChartOfAccountExistsResponse;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JTextField;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class ChartOfAccounExistsCheck {

    private JTextField chartCodeTextField;
    private ActiveSession session;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerController serverController;
    private final SystemMessages notificator;

    public ChartOfAccounExistsCheck(Builder builder) {
        this.chartCodeTextField = builder.chartCodeTextField;
        this.session = builder.session;
        this.output = builder.output;
        this.input = builder.input;
        this.serverController = new ServerController(output, input);
        this.notificator = new SystemMessages();
    }
    
    public boolean isChartOfAccountCodeExists(){
        return checkIfExists();
    }
    
    private boolean checkIfExists() {
        String code = chartCodeTextField.getText().trim();
        
        if (!code.isEmpty()) {
            var checkCode = new ChartOfAccountExistsRequest(session.getSessionId(), session.getUserId(), code);
            Object response = serverController.sendData(checkCode);
        
            if (response instanceof ChartOfAccountExistsResponse existsResponse) {
                if (existsResponse.isSqlError()) {
                    notificator.showWarningMsg(AppMessages.msg(AppMessages.Key.SQL_ERROR));
                    return true;
                }
                if (existsResponse.wasFound()) {
                    notificator.showErrorMsg(AppMessages.msg(AppMessages.Key.FOUND) + " " + code);
                    return true;
                } else {
                    return false;
                }

            }
        }
        return true;
    }

   
    public static class Builder {

        private JTextField chartCodeTextField;
        private ActiveSession session;
        private ObjectOutputStream output;
        private ObjectInputStream input;

        public Builder withChartCodeTextField(JTextField chartCodeTextField){
            this.chartCodeTextField = chartCodeTextField;
            return this;
        }
        public Builder withSession(ActiveSession session){
            this.session = session;
            return this;
        }
        public Builder withOutput(ObjectOutputStream output){
            this.output = output;
            return this;
        }
        public Builder withInput(ObjectInputStream input){
            this.input = input;
            return this;
        }
        public ChartOfAccounExistsCheck build(){
            return new ChartOfAccounExistsCheck(this);
        }
    }
}
