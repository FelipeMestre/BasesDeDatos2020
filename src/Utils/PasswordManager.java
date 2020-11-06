package Utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordManager {
    private final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    private final int iterations = 20;
    private final int keyLength = 115;

    private static PasswordManager passwordMang;

    //Passwords

    public boolean validatePassword(String databasePassword, String inputPassword, int salt){
        char[] passwordChars = inputPassword.toCharArray();
        byte[] saltBytes = String.valueOf(salt).getBytes();
        String hashedString = hashPassword(passwordChars, saltBytes, iterations, keyLength);
        return hashedString.compareTo(databasePassword) == 0;
    }

    public String generatePassword(String inputPassword, int salt){
        char[] passwordChars = inputPassword.toCharArray();
        byte[] saltBytes = String.valueOf(salt).getBytes();
        String hashedString = hashPassword(passwordChars, saltBytes, iterations, keyLength);
        return hashedString;
    }

    public static boolean validatePassword2(String databasePassword, String inputPassword){
        // Para utilizar contraseña sin hash.
        return databasePassword.compareTo(inputPassword) == 0;
    }

    public static boolean isPasswordValid(String password, Container parent)
    {
        // for checking if password length
        // is between 8 and 15
        if (!((password.length() >= 8)
                && (password.length() <= 15))) {
            JOptionPane.showMessageDialog(parent, "El Password debe tener entre 8 y 15 caracteres");
            return false;
        }

        // to check space
        if (password.contains(" ")) {
            JOptionPane.showMessageDialog(parent, "El password no puede contener espacios");
            return false;
        }
        if (true) {
            int count = 0;

            // check digits from 0 to 9
            for (int i = 0; i <= 9; i++) {

                // to convert int to string
                String str1 = Integer.toString(i);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(parent, "El password debe contener al menos un numero");
                return false;
            }
        }
        if (true) {
            int count = 0;

            // checking capital letters
            for (int i = 65; i <= 90; i++) {

                // type casting
                char c = (char)i;

                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(parent, "El password debe contener una mayuscula minimo");
                return false;
            }
        }

        if (true) {
            int count = 0;

            // checking small letters
            for (int i = 90; i <= 122; i++) {

                // type casting
                char c = (char)i;
                String str1 = Character.toString(c);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(parent, "El password debe contener una minuscula minimo");
                return false;
            }
        }

        // if all conditions fails
        return true;
    }

    //Usernames
    public boolean isUsernameValid(String username, Container parent){
        if (!((username.length() >= 8)
                && (username.length() <= 20))) {
            JOptionPane.showMessageDialog(parent, "El Nombre de Usuario debe tener entre 8 y 20 caracteres");
            return false;
        }
        return true;
    }


    //Utilities
    private String hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return  bytesToHex(res);
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    //Funcion sacada de internesss
    private String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    public static PasswordManager getInstance(){
        if (passwordMang == null){
            return new PasswordManager();
        }
        return passwordMang;
    }

}
