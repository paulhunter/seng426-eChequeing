package eCheque;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.*;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author pmoon
 */

public class DigitalSignatureTest {
    PublicKey validPubKey;
    PrivateKey validPrivKey;
    private static String SIGNATURE_ALGORITHM = "SHA1WITHRSA";
    private static String KEYPAIR_ALGORITHM = "RSA";
    
    @Before
    public void setUp() throws NoSuchAlgorithmException {
        KeyPair keyPair = generateKeyPair();
        
        this.validPubKey = keyPair.getPublic();
        this.validPrivKey = keyPair.getPrivate();
    }

    @After
    public void tearDown() {
        this.validPubKey = null;
        this.validPrivKey = null;
    }

    /**
     * Test of signDS method, of class Digitalsigneture.
     * Tests whether the method throws a NullPointerException given a null 
     * string.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = NullPointerException.class)
    public void testSignetureNullStringInput() throws Exception {
        DigitalSignature.signDS(null, this.validPrivKey);
    }

    /**
     * Test of signDS method, of class Digitalsigneture.
     * Tests whether the method throws an InvalidKeyException given a null 
     * private key.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = InvalidKeyException.class)
    public void testSignetureNullPrivKeyInput() throws Exception {
        DigitalSignature.signDS("", null);
    }

    /**
     * Test of verifyDS method, of class Digitalsigneture.
     * Tests whether the method throws a NullPointerException given a null for
     * the signed data.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = NullPointerException.class)
    public void testverifySignatureNullSignatureInput() throws Exception {
        DigitalSignature.verifyDS(null, "", this.validPubKey);
    }

    /**
     * Test of verifyDS method, of class Digitalsigneture.
     * Tests whether the method throws an NullPointerException given a null 
     * string for the message.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = NullPointerException.class)
    public void testverifySignatureNullMessageInput() throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(this.validPrivKey);
        DigitalSignature.verifyDS(signature.sign(), null, this.validPubKey);
    }

    /**
     * Test of verifyDS method, of class Digitalsigneture.
     * Tests whether the method throws an InvalidKeyException given a null 
     * private key.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = InvalidKeyException.class)
    public void testverifySignatureNullPublicKeyInput() throws Exception {
        Signature signature = Signature.getInstance("SHA1WITHRSA");
        signature.initSign(this.validPrivKey);
        DigitalSignature.verifyDS(signature.sign(), "", null);
    }

    /**
     * Test of signDS and verifyDS methods, of class Digitalsigneture.
     * Tests whether the verifyDS, given a public key, returns the
     * correct result after a string has been encrypted with the corresponding
     * private key.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSignetureAndVerifySignature() throws Exception {
        String testString = generateRandomAlphanumericString(50);
        byte[] results = DigitalSignature.signDS(testString, validPrivKey);
        KeyPair invalidKP = generateKeyPair();
        
        while (invalidKP.getPrivate().equals(this.validPrivKey)) {
            invalidKP = generateKeyPair();
        }
        
        assertTrue(DigitalSignature.verifyDS(results, testString, validPubKey));
        assertFalse(DigitalSignature.verifyDS(results, "", validPubKey));
        assertFalse(DigitalSignature.verifyDS(results, testString, invalidKP.getPublic()));
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
