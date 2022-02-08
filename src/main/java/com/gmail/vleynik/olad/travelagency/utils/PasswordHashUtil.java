package com.gmail.vleynik.olad.travelagency.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Used to hash password using SHA-512 and salt
 *
 * @author Vladyslav Oliinyk.
 * @version 1.0
 */
public class PasswordHashUtil {
    public static final String HASH_ALGORITHM = "SHA-512";

    /**
     * Private constructor excludes creation object of utility class
     *
     * @throws IllegalStateException on calling
     */
    private PasswordHashUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Provides hashed password
     *
     * @param password - password to hash
     * @param salt - salt for hashing
     * @return hashed password
     */
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

    /**
     * Provides random generated salt
     *
     * @return random generated salt
     */
    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
