/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eCheque;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import static org.hamcrest.CoreMatchers.equalTo;
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
public class RSAGeneratorTest {
    
    
    public RSAGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /*
     * Test the RSAGenerator. Ensure that the generator creates
     * different key pairs every time, and that they are never null.
     */
    @Test
    public void testRSAGenerator() throws NoSuchAlgorithmException {
        KeyPair rsaKeyPair1 = RSAGenerator.GenerateRSAKeys();
        KeyPair rsaKeyPair2 = RSAGenerator.GenerateRSAKeys();
        assertThat(rsaKeyPair1, not(nullValue()));
        assertThat(rsaKeyPair2, not(nullValue()));
        
        assertThat(rsaKeyPair1.getPublic().getEncoded(), not(equalTo(rsaKeyPair2.getPublic().getEncoded())));
        assertThat(rsaKeyPair1.getPrivate().getEncoded(), not(equalTo(rsaKeyPair2.getPrivate().getEncoded())));        
    }
}
