package eCheque;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.crypto.SecretKey;
import junit.framework.TestCase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.not;
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
        assertNotNull(secretKey1);
        assertNotNull(secretKey2);
        assertThat("Generated keys are not the same", secretKey1.getEncoded(), is(not(secretKey2.getEncoded())));
    }
    
    @Test
    public void testAESKeyUsingPassword() {
        String password = "password1234!";
        SecretKey secretKey = aesCrypt.inilizeAESKeyByPassword(password);
        System.out.println(secretKey.getEncoded());
    }

    
    @Test(expected = IllegalArgumentException.class) 
    public void testAESKeyUsingZeroLengthPassword() {
        String password = "";
        SecretKey secretKey = aesCrypt.inilizeAESKeyByPassword(password);
        
    }
    
    @Test
    public void testAESKeyUsingLongPassword() {
        String password = "ThisPasswordIs57CharactersLongAndWillStayThatWayForAWhile";
        SecretKey secretKey = aesCrypt.inilizeAESKeyByPassword(password);
    }
    
    @Test
    public void testAESKeyUSingOneLengthPassword() {
        String password = "1";
        SecretKey secretKey = aesCrypt.inilizeAESKeyByPassword(password);
    }
}
