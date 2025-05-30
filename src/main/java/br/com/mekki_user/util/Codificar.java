package br.com.mekki_user.util;

import java.security.MessageDigest;

public class Codificar {
    public static String generateHash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            System.out.println(hexString.toString());
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException("falha ao gerar senha");

        }
    }
}
