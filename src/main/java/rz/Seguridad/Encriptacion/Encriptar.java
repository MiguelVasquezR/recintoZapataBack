package rz.Seguridad.Encriptacion;

import com.google.gson.JsonObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Encriptar {


    public static JsonObject encryptPassword(String password) {
        JsonObject jsonObject = new JsonObject();
        try {
            byte[] salt = generateSalt();

            // Concatenar el salt con la contraseña
            String passwordWithSalt = password + Base64.getEncoder().encodeToString(salt);

            // Crear un MessageDigest para SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Aplicar el hash a la contraseña concatenada con el salt
            byte[] hashedPassword = digest.digest(passwordWithSalt.getBytes());

            // Convertir el hash a una representación en formato base64
            String encryptedPassword = Base64.getEncoder().encodeToString(hashedPassword);

            jsonObject.addProperty("contraseña", encryptedPassword);
            jsonObject.addProperty("salt", Base64.getEncoder().encodeToString(salt));

            return jsonObject;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String validarPassword(String password, byte[] salt) {
        try {
            String passwordWithSalt = password + Base64.getEncoder().encodeToString(salt);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = digest.digest(passwordWithSalt.getBytes());
            return  Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }


}
