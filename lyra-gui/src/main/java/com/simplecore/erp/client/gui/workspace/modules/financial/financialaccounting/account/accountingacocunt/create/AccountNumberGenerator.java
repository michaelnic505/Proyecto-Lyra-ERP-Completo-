
package com.simplecore.erp.client.gui.workspace.modules.financial.financialaccounting.account.accountingacocunt.create;

import com.simplecore.erp.shared.exceptions.AccountNumberLimitExceededException;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua   
 * 
 */
public class AccountNumberGenerator {

    public static String getNextAccountNumber(String lastAccountNumber, int baseNumber) throws AccountNumberLimitExceededException {
        //Verificamos que no haya aun una primera cuenta de la subclase
        if (lastAccountNumber == null || lastAccountNumber.isEmpty()) {
            return String.valueOf(baseNumber + 1);
        }
        int lastNumber = Integer.parseInt(lastAccountNumber);
        int maxLimit = getMaxLimit(baseNumber); // El límite máximo basado en el patrón
        int nextNumber = lastNumber + 1; // El siguiente número

        // Verificar que el siguiente número no se salga del límite
        if (nextNumber > maxLimit) {
            throw new AccountNumberLimitExceededException("No more available numbers in the range for base: " + baseNumber);
        }

        return String.valueOf(nextNumber);
    }

    // Método para obtener el límite máximo (reemplaza los ceros por 9)
    private static int getMaxLimit(int baseNumber) {
        String numberStr = String.valueOf(baseNumber);
        char[] chars = numberStr.toCharArray();

        // Reemplazar los ceros por 9
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                chars[i] = '9';
            } else {
                break;
            }
        }

        return Integer.parseInt(new String(chars));
    }
    
    
    public static String getNextAccountNumberByParent(String lastAccountNumber, String baseNumber) throws AccountNumberLimitExceededException {
        if (lastAccountNumber == null || lastAccountNumber.isEmpty()) {
            return baseNumber + ".001";
        }

        // Extract the number after the dot and add 1
        String[] parts = lastAccountNumber.split("\\.");
        int lastNumber = Integer.parseInt(parts[1]);

        // Check if the number after the dot has reached the limit of 999
        if (lastNumber >= 999) {
            // Throw an exception if the number exceeds the limit
            throw new AccountNumberLimitExceededException("Cannot create more accounts. The numbering limit has been reached.");
        }

        return baseNumber + "." + String.format("%03d", lastNumber + 1);
    }

}
