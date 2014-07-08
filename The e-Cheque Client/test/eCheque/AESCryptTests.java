package eCheque;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jaguz_000
 */
public class AESCryptTests {
    
    public AESCrypt aesCrypt;
    
    public AESCryptTests() {
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
    
    @Test
    public void testAESKeyUsingPassword() {
        String password = "password1234!";
        testAESKey(password);
    }

    
    @Test(expected = IllegalArgumentException.class) 
    public void testAESKeyUsingZeroLengthPassword() {
        String password = "";
        testAESKey(password);        
    }
    
    @Test
    public void testAESKeyUsingLongPassword() {
        String password = "ThisPasswordIs57CharactersLongAndWillStayThatWayForAWhile";
        testAESKey(password);
    }
    
    @Test
    public void testAESKeyUSingOneLengthPassword() {
        String password = "1";
        testAESKey(password);
    }
    
    @Test
    public void testInitializeCipherEncryptMode() throws Exception {
        int mode = 0;
        testInitializeCipher(mode);
    }
    
    @Test
    public void testInitializeCipherDecryptMode() throws Exception {
        int mode = 1;
        testInitializeCipher(mode);
    }
    
    @Test
    public void testInitializeCipherWrapMode() throws Exception {
        int mode = 2;
        testInitializeCipher(mode);
    }
    
    @Test
    public void testInitializeCipherUnwrapMode() throws Exception {
        int mode = 3;
        testInitializeCipher(mode);
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
