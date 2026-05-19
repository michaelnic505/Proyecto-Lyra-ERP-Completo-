
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.financialaccountsetup.ui.form;

import com.simplecore.erp.client.gui.utils.svg.CustomSVGIcon;
import com.simplecore.erp.shared.models.dto.AccountCardData;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class AccountSetupCard extends javax.swing.JPanel {

    private AccountCardData data;
    
    public AccountSetupCard() {
        initComponents();
    }
    
    public void setAccountData(AccountCardData data){this.data = data;};
    public AccountCardData getAccountData(){return data;};
    
    public JTextField getAccountNameTF(){return accountingAccountNameTF;};
    public JTextField getAccountNumberTF(){return acocuntingAccountNumberTF;};
    public JLabel getAccountClassNameLB(){return classNameValueLB;};
    public JLabel getSubClassCodeValueLB(){return subclassCodeValueLB;};
    public JLabel getSubClassNameValueLB(){return subclassNameValueLB;};
    public JLabel getChartOfAccountCodeValueLB(){return chartOfAccountCodeValueLB;};
    public JLabel getChartOfAccountNameValueLB(){return chartOfAccountNameValueLB;};
    public JButton getSetupButton(){return setupButton;};
    public JButton getViewButton(){return viewButton;};
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        companyPane = new corex.suite.JPanelRoundedGradient();
        accountingAccountNameTF = new javax.swing.JTextField();
        acocuntingAccountNumberTF = new javax.swing.JTextField();
        accountingIconLB = new javax.swing.JLabel();
        companyPane1 = new corex.suite.JPanelRoundedGradient();
        classNameLB = new javax.swing.JLabel();
        subclassCodeLB = new javax.swing.JLabel();
        chartOfAccountCodeLB = new javax.swing.JLabel();
        classNameValueLB = new javax.swing.JLabel();
        subclassCodeValueLB = new javax.swing.JLabel();
        chartOfAccountCodeValueLB = new javax.swing.JLabel();
        chartOfAccountNameValueLB = new javax.swing.JLabel();
        chartOfAccountNameLB = new javax.swing.JLabel();
        subclassNameValueLB = new javax.swing.JLabel();
        subclassNameLB = new javax.swing.JLabel();
        setupButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();

        companyPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        companyPane.setColor1(new java.awt.Color(247, 247, 255));
        companyPane.setColor2(new java.awt.Color(247, 247, 255));
        companyPane.setPreferredSize(new java.awt.Dimension(800, 163));

        accountingAccountNameTF.setFont(new java.awt.Font("IBM Plex Sans", 1, 13)); // NOI18N
        accountingAccountNameTF.setText("Banks");

        acocuntingAccountNumberTF.setFont(new java.awt.Font("IBM Plex Sans", 1, 12)); // NOI18N
        acocuntingAccountNumberTF.setForeground(new java.awt.Color(51, 51, 51));
        acocuntingAccountNumberTF.setText("1000001");

        accountingIconLB.setIcon(new CustomSVGIcon("/icons/svg/accounting_icon.svg",new Dimension(22,22)));

        javax.swing.GroupLayout companyPaneLayout = new javax.swing.GroupLayout(companyPane);
        companyPane.setLayout(companyPaneLayout);
        companyPaneLayout.setHorizontalGroup(
            companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accountingIconLB, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accountingAccountNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acocuntingAccountNumberTF)
                .addContainerGap())
        );
        companyPaneLayout.setVerticalGroup(
            companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountingAccountNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acocuntingAccountNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountingIconLB, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        companyPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 144, 144)));
        companyPane1.setColor1(new java.awt.Color(247, 247, 255));
        companyPane1.setColor2(new java.awt.Color(247, 247, 255));
        companyPane1.setPreferredSize(new java.awt.Dimension(800, 163));

        classNameLB.setText("Class Name :");

        subclassCodeLB.setText("Subclass Code :");

        chartOfAccountCodeLB.setText("Chart Code :");

        classNameValueLB.setFont(new java.awt.Font("IBM Plex Sans", 1, 13)); // NOI18N
        classNameValueLB.setText("Assets");

        subclassCodeValueLB.setFont(new java.awt.Font("IBM Plex Sans", 1, 13)); // NOI18N
        subclassCodeValueLB.setText("Sub Class Code");

        chartOfAccountCodeValueLB.setFont(new java.awt.Font("IBM Plex Sans", 1, 13)); // NOI18N
        chartOfAccountCodeValueLB.setText("Chart Of Account Code");

        chartOfAccountNameValueLB.setFont(new java.awt.Font("IBM Plex Sans", 1, 13)); // NOI18N
        chartOfAccountNameValueLB.setText("Chart Of Account Name");

        chartOfAccountNameLB.setText("Plan :");

        subclassNameValueLB.setFont(new java.awt.Font("IBM Plex Sans", 1, 13)); // NOI18N
        subclassNameValueLB.setText("Sub Class Name");

        subclassNameLB.setText("Sub Class :");

        javax.swing.GroupLayout companyPane1Layout = new javax.swing.GroupLayout(companyPane1);
        companyPane1.setLayout(companyPane1Layout);
        companyPane1Layout.setHorizontalGroup(
            companyPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companyPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classNameLB)
                    .addComponent(subclassCodeLB)
                    .addComponent(chartOfAccountCodeLB))
                .addGap(18, 18, 18)
                .addGroup(companyPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chartOfAccountCodeValueLB, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(subclassCodeValueLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(classNameValueLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(companyPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartOfAccountNameLB)
                    .addComponent(subclassNameLB))
                .addGap(18, 18, 18)
                .addGroup(companyPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subclassNameValueLB, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(chartOfAccountNameValueLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        companyPane1Layout.setVerticalGroup(
            companyPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companyPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classNameLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classNameValueLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subclassNameLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subclassNameValueLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(companyPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subclassCodeLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subclassCodeValueLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartOfAccountNameLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartOfAccountNameValueLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(companyPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chartOfAccountCodeLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartOfAccountCodeValueLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setupButton.setBackground(new java.awt.Color(226, 210, 144));
        setupButton.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N
        setupButton.setText("Setup");

        viewButton.setBackground(new java.awt.Color(226, 210, 144));
        viewButton.setFont(new java.awt.Font("IBM Plex Sans", 0, 12)); // NOI18N
        viewButton.setText("View");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(companyPane, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
            .addComponent(companyPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(setupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(companyPane, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(companyPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setupButton)
                    .addComponent(viewButton))
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountingAccountNameTF;
    private javax.swing.JLabel accountingIconLB;
    private javax.swing.JTextField acocuntingAccountNumberTF;
    private javax.swing.JLabel chartOfAccountCodeLB;
    private javax.swing.JLabel chartOfAccountCodeValueLB;
    private javax.swing.JLabel chartOfAccountNameLB;
    private javax.swing.JLabel chartOfAccountNameValueLB;
    private javax.swing.JLabel classNameLB;
    private javax.swing.JLabel classNameValueLB;
    private corex.suite.JPanelRoundedGradient companyPane;
    private corex.suite.JPanelRoundedGradient companyPane1;
    private javax.swing.JButton setupButton;
    private javax.swing.JLabel subclassCodeLB;
    private javax.swing.JLabel subclassCodeValueLB;
    private javax.swing.JLabel subclassNameLB;
    private javax.swing.JLabel subclassNameValueLB;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
