/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yedpay;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author kobeleung
 */
public class Helper {
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
    
    /**
     * 
     * @param message
     * @param key
     * @return String
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException 
     */
    public static String hmacSha256(String message, String key) throws InvalidKeyException, NoSuchAlgorithmException, Exception {
        if (key.length() != 32) {
            throw new Exception("Invalid key length");
        }
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return bytesToHex(sha256_HMAC.doFinal(message.getBytes()));
    }
    
    /**
     * 
     * @param bytes
     * @return String
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
