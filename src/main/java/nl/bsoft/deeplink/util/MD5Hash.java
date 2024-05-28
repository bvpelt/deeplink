package nl.bsoft.deeplink.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5Hash {

    public static String getMD5Hex(String input) {
        try {
            // Create a MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Digest the input string to get the hash byte array
            byte[] digest = md.digest(input.getBytes());

            // Convert the byte array to a hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }

            // Return the hex string
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No MD5 algorithm found", e);
        }
    }
}
