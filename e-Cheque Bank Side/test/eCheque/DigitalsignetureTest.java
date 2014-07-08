package eCheque;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author pmoon
 */

public class DigitalsignetureTest {
    Digitalsigneture digSignature;
    PublicKey validPubKey;
    PrivateKey validPrivKey;
    private static String SIGNATURE_ALGORITHM = "SHA1WITHRSA";
    private static String KEYPAIR_ALGORITHM = "RSA";
    
    @Before
    public void setUp() throws NoSuchAlgorithmException {
        KeyPair keyPair = generateKeyPair();
        
        this.digSignature = new Digitalsigneture();
        this.validPubKey = keyPair.getPublic();
        this.validPrivKey = keyPair.getPrivate();
    }

    @After
    public void tearDown() {
        this.digSignature = null;
        this.validPubKey = null;
        this.validPrivKey = null;
    }

    /**
     * Test of signeture method, of class Digitalsigneture. 
     * Tests whether the method throws a NullPointerException given a null 
     * string.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = NullPointerException.class)
    public void testSignetureNullStringInput() throws Exception {
        this.digSignature.signeture(null, this.validPrivKey);
    }

    /**
     * Test of signeture method, of class Digitalsigneture. 
     * Tests whether the method throws an InvalidKeyException given a null 
     * private key.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = InvalidKeyException.class)
    public void testSignetureNullPrivKeyInput() throws Exception {
        this.digSignature.signeture("", null);
    }

    /**
     * Test of verifySignature method, of class Digitalsigneture. 
     * Tests whether the method throws a NullPointerException given a null for
     * the signed data.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = NullPointerException.class)
    public void testverifySignatureNullSignatureInput() throws Exception {
        this.digSignature.verifySignature(null, "", this.validPubKey);
    }

    /**
     * Test of verifySignature method, of class Digitalsigneture. 
     * Tests whether the method throws an NullPointerException given a null 
     * string for the message.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = NullPointerException.class)
    public void testverifySignatureNullMessageInput() throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(this.validPrivKey);
        this.digSignature.verifySignature(signature.sign(), null, this.validPubKey);
    }

    /**
     * Test of verifySignature method, of class Digitalsigneture. 
     * Tests whether the method throws an InvalidKeyException given a null 
     * private key.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = InvalidKeyException.class)
    public void testverifySignatureNullPublicKeyInput() throws Exception {
        Signature signature = Signature.getInstance("SHA1WITHRSA");
        signature.initSign(this.validPrivKey);
        this.digSignature.verifySignature(signature.sign(), "", null);
    }

    /**
     * Test of signeture and verifySignature methods, of class Digitalsigneture.
     * Tests whether the verifySignature, given a public key, returns the 
     * correct result after a string has been encrypted with the corresponding
     * private key.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSignetureAndVerifySignature() throws Exception {
        String testString = generateRandomAlphanumericString(50);
        byte[] results = this.digSignature.signeture(testString, validPrivKey);
        KeyPair invalidKP = generateKeyPair();
        
        while (invalidKP.getPrivate().equals(this.validPrivKey)) {
            invalidKP = generateKeyPair();
        }
        
        assertTrue(this.digSignature.verifySignature(results, testString, validPubKey));
        assertFalse(this.digSignature.verifySignature(results, "", validPubKey));
        assertFalse(this.digSignature.verifySignature(results, testString, invalidKP.getPublic()));
    }

    private String generateRandomAlphanumericString(int length) {
        Random random = new Random();
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] text = new char[length];

        for (int i = 0; i < text.length; i++) {
            text[i] = alphanumeric.charAt(random.nextInt(alphanumeric.length()));
        }

        return new String(text);
    }
    
    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(KEYPAIR_ALGORITHM);
        keyGenerator.initialize(2048, secureRandom);
        return keyGenerator.generateKeyPair();
    }
}
