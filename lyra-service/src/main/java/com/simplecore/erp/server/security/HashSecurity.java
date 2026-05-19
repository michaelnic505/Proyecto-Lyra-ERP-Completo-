package com.simplecore.erp.server.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author USER
 */
public class HashSecurity {

    public static String miFuncionHash(String passwordPlano) {
        try {
            // 1. Instanciamos el algoritmo matemático SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 2. Convertimos la contraseña en un arreglo de bytes y aplicamos el hash
            byte[] encodedhash = digest.digest(passwordPlano.getBytes(StandardCharsets.UTF_8));

            // 3. Convertimos los bytes resultantes a formato Hexadecimal (legible)
            return bytesToHex(encodedhash);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: Algoritmo no encontrado", e);
        }
    }

// Función auxiliar para convertir bytes a String Hexadecimal
    private static String bytesToHex(byte[] hash) {
       
        StringBuilder hexString = new StringBuilder(2 * hash.length);
       
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
      
        String pass = "mike123";
        System.out.println("Password: " + pass);

        String hash = miFuncionHash(pass);

        System.out.println("Hash en Java: " + hash);

        if ("541bf3ce2c00becc8012af585a3fa01210c046c10039dab2f7990b5ea84c2312".equals(hash)) {
            System.out.println("Si son iguales");
        } else {
            System.out.println("No son iguales");
        }
    }
}
