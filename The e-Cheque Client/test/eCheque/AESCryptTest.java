package eCheque;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import junit.framework.TestCase;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Justin Guze
 */
public class AESCryptTest {
    
    public AESCrypt aesCrypt;
    
    public AESCryptTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        aesCrypt = new AESCrypt();
        
    }
    
    @After
    public void tearDown() {
        aesCrypt = null;
    }

    /*
     *  Test Methods
     */
    
    /**
     * This test generates two random AES keys, and tests to make sure
     * they are both not null and they are not the same key.
     * @throws Exception 
     */
    @Test
    public void testGenerateRandomAESKey() throws Exception  {
        SecretKey secretKey1 = aesCrypt.GenerateRandomAESKey();
        SecretKey secretKey2 = aesCrypt.GenerateRandomAESKey();
        assertThat(secretKey1, not(nullValue()));
        assertThat(secretKey2, not(nullValue()));
        assertThat("Generated keys are not the same", secretKey1.getEncoded(), is(not(secretKey2.getEncoded())));
    }
    
    /**
     * Test that the test produces the appropriate AES key given
     * an appropriate password
     */
    @Test
    public void testAESKeyUsingPassword() {
        String password = "password1234!";
        testAESKey(password);
    }

    /**
     * Test that a zero length password will throw an illegal argument
     * exception
     */
    @Test(expected = IllegalArgumentException.class) 
    public void testAESKeyUsingZeroLengthPassword() {
        String password = "";
        testAESKey(password);        
    }
    
    /**
     * Test that a large password is acceptable by the cryptography
     */
    @Test
    public void testAESKeyUsingLongPassword() {
        String password = "ThisPasswordIs57CharactersLongAndWillStayThatWayForAWhile";
        testAESKey(password);
    }
    
    /**
     * Test that a one length password is acceptable
     */
    @Test
    public void testAESKeyUSingOneLengthPassword() {
        String password = "1";
        testAESKey(password);
    }
    
    /**
     * Test that a cipher can be initialized in encrypt mode given the correct
     * mode
     * @throws Exception 
     */
    @Test
    public void testInitializeCipherEncryptMode() throws Exception {
        int mode = 0;
        testInitializeCipher(mode);
    }
    
    /**
     * Test that a cipher can be initialized in decrypt mode given the correct
     * mode
     * @throws Exception 
     */
    @Test
    public void testInitializeCipherDecryptMode() throws Exception {
        int mode = 1;
        testInitializeCipher(mode);
    }

    /**
     * Test that a cipher can be initialized in wrap mode given the correct
     * mode
     * @throws Exception 
     */
    @Test
    public void testInitializeCipherWrapMode() throws Exception {
        int mode = 2;
        testInitializeCipher(mode);
    }
    
    /**
     * Test that a cipher can be initialized in unwrap mode given the correct
     * mode
     * @throws Exception 
     */    
    @Test
    public void testInitializeCipherUnwrapMode() throws Exception {
        int mode = 3;
        testInitializeCipher(mode);
    }
    
    /**
     * Test that the method handles invalid modes by throwing an
     * exception
     * @throws Exception 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testInvalidModeCipher() throws Exception {
        int mode = 4;
        testInitializeCipher(mode);
    }
    
    @Test
    public void testEncrypt() throws Exception {
        int mode = 0;
        String inputString = "This is a test!!";
        OutputStream out = new ByteArrayOutputStream();
        testCrypt(inputString, out, mode);
    }
    
    @Test
    public void testDecrypt() throws Exception {
        int mode = 1;
        String inputString = "This is a test!!";
        OutputStream out = new ByteArrayOutputStream();
        testCrypt(inputString, out, mode);
    }
    
    @Test
    public void testEncryptZeroLength() throws Exception {
        int mode = 0;
        String inputString = "";
        OutputStream out = new ByteArrayOutputStream();
        testCrypt(inputString, out, mode);
    }
    
    @Test (expected = NullPointerException.class)
    public void testEncryptNullStream() throws Exception {
        int mode = 0;
        Key key = KeyGenerator.getInstance("AES").generateKey();
        Cipher cipher = aesCrypt.initializeCipher(key, mode);
        aesCrypt.crypt(null, null, cipher);
    }
    
    private void testCrypt(String inputString, OutputStream out, int mode) throws Exception {
        InputStream in = new ByteArrayInputStream(inputString.getBytes());
        Key key = KeyGenerator.getInstance("AES").generateKey();
        Cipher cipher = aesCrypt.initializeCipher(key, mode);
        aesCrypt.crypt(in, out, cipher);
        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;
        String output = new String(baos.toByteArray(), Charset.defaultCharset());
        assertThat(output, not(nullValue()));
        
        if (inputString.length() != 0)
            assertThat(inputString, not(equalTo(output)));
    }
    
    private void testInitializeCipher(int mode) throws Exception {
        String algoType = "AES";
        Key key = KeyGenerator.getInstance(algoType).generateKey();
        Cipher cipher = aesCrypt.initializeCipher(key, mode);

        assertThat(cipher, not(nullValue()));
        assertThat(cipher.getAlgorithm(), is(equalTo(algoType)));
    }
    
    
    private void testAESKey(String password) {
        SecretKey secretKey = aesCrypt.inilizeAESKeyByPassword(password);
        assertThat(secretKey, not(nullValue()));         
    }
}
