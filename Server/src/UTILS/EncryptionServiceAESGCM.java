package utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionServiceAESGCM {
    private SecretKey secretKey;
    private Cipher cipher;

    public EncryptionServiceAESGCM(String key) throws GeneralSecurityException {
        byte[] keyBytes = "SecurityKey12345".getBytes(StandardCharsets.UTF_8);
        secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher = Cipher.getInstance("AES/GCM/NoPadding");
    }

    public String encrypt(String original) throws GeneralSecurityException {
        byte[] iv = new byte[16]; // Initialization Vector
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv); // Generate a random IV
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(128, iv));
        byte[] encryptedData = cipher.doFinal(original.getBytes(StandardCharsets.UTF_8));
        Base64.Encoder encoder = Base64.getEncoder();
        String encrypt64 = encoder.encodeToString(encryptedData);
        String iv64 = encoder.encodeToString(iv);
        return encrypt64 + "#" + iv64;
    }

    public String decrypt(String cipherText) throws GeneralSecurityException {
        String[] split = cipherText.split("#");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] cypherTextBytes = decoder.decode(split[0]);
        byte[] iv = decoder.decode(split[1]);
        GCMParameterSpec paraSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, paraSpec);
        byte[] decryptedData = cipher.doFinal(cypherTextBytes);
        return new String(decryptedData, StandardCharsets.UTF_8);
    }
}