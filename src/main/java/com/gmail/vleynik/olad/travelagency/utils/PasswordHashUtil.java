package com.gmail.vleynik.olad.travelagency.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHashUtil {
    public static final String HASH_ALGORITHM = "SHA-512";

    private PasswordHashUtil() {
    }

    public static String getHash(String password, byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            messageDigest.update(salt);
            byte[] hash = messageDigest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("can`t hash password!", e);
        }
    }

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
