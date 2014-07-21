/*
 * RSAGenerator.java
 *
 * Created on March 28, 2007, 4:44 PM
 *
 */

package eCheque;

import java.security.*;

/**
 * A class that generates RSA keypairs with a size of 1024
 * @author SaaD
 */
public final class RSAGenerator {
    private static int RSAKEYSIZE = 1024;
    private static String RSASTRING = "RSA";

    // Ensure the class is treated as a static class
    private RSAGenerator(){}
    
    public static KeyPair GenerateRSAKeys() throws NoSuchAlgorithmException {
        SecureRandom SecRandom = new SecureRandom();
        KeyPairGenerator KeyGenerator = KeyPairGenerator.getInstance(RSASTRING);
        KeyGenerator.initialize(RSAKEYSIZE,SecRandom);
        KeyPair RSAKeys = KeyGenerator.generateKeyPair();
        return RSAKeys;
    }
}
