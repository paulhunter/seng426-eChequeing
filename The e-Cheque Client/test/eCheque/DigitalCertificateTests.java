package eCheque;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Random;
import java.security.*;

/*
 * @author Paul Hunter
 */
public class DigitalCertificateTests {

    public static Random rand;
    public static final int maxStringLength = 100;

    public DigitalCertificateTests() {
    }

    @BeforeClass
    public static void setUpClass() {
        rand = new Random();
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
     *  Test Methods - Fuzzed String get and set Test Methods
     */
    
    @Test
    public void testGetSetHolderName() {
        DigitalCertificate subject = new DigitalCertificate();
        String originalValue = subject.getHolderName();
        //We want to generate a name up to 100 characters that is atleast 1 character long.
        String newValue = GenerateRandomAlphaNumString(rand.nextInt(maxStringLength) + 1);
        assertThat("Current value and Generate value are not equal.", originalValue, is(not(newValue)));

        subject.setHolderName(newValue);
        assertThat("New value is returned after assignment", newValue, is(subject.getHolderName()));
    }

    //Test that the setter for HolderName functions. 
    @Test
    public void testGetSetSubject() {
        DigitalCertificate subject = new DigitalCertificate();
        String originalValue = subject.getSubject();
        //We want to generate a name up to 100 characters that is atleast 1 character long.
        String newValue = GenerateRandomAlphaNumString(rand.nextInt(maxStringLength) + 1);
        assertThat("Current value and Generate value are not equal.", originalValue, is(not(newValue)));

        subject.setSubject(newValue);
        assertThat("New value is returned after assignment", newValue, is(subject.getSubject()));
    }

    //Test that the setter for HolderName functions. 
    @Test
    public void testGetSetIssuer() {
        DigitalCertificate subject = new DigitalCertificate();
        String originalValue = subject.getIssuer();
        //We want to generate a name up to 100 characters that is atleast 1 character long.
        String newValue = GenerateRandomAlphaNumString(rand.nextInt(maxStringLength) + 1);
        assertThat("Current value and Generate value are not equal.", originalValue, is(not(newValue)));

        subject.setIssuer(newValue);
        assertThat("New value is returned after assignment", newValue, is(subject.getIssuer()));
    }

    //Test that the setter for HolderName functions. 
    @Test
    public void testGetSetSerialNumber() {
        DigitalCertificate subject = new DigitalCertificate();
        String originalValue = subject.getSerialNumber();
        //We want to generate a name up to 100 characters that is atleast 1 character long.
        String newValue = GenerateRandomAlphaNumString(rand.nextInt(maxStringLength) + 1);
        assertThat("Current value and Generate value are not equal.", originalValue, is(not(newValue)));

        subject.setSerialNumber(newValue);
        assertThat("New value is returned after assignment", newValue, is(subject.getSerialNumber()));
    }

    //Test that the setter for HolderName functions. 
    @Test
    public void testGetSetValidFrom() {
        DigitalCertificate subject = new DigitalCertificate();
        String originalValue = subject.getValidFrom();
        //We want to generate a name up to 100 characters that is atleast 1 character long.
        String newValue = GenerateRandomAlphaNumString(rand.nextInt(maxStringLength) + 1);
        assertThat("Current value and Generate value are not equal.", originalValue, is(not(newValue)));

        subject.setValidFrom(newValue);
        assertThat("New value is returned after assignment", newValue, is(subject.getValidFrom()));
    }

    //Test that the setter for HolderName functions. 
    @Test
    public void testGetSetValidTo() {
        DigitalCertificate subject = new DigitalCertificate();
        String originalValue = subject.getValidTo();
        //We want to generate a name up to 100 characters that is atleast 1 character long.
        String newValue = GenerateRandomAlphaNumString(rand.nextInt(maxStringLength) + 1);
        assertThat("Current value and Generate value are not equal.", originalValue, is(not(newValue)));

        subject.setValidTo(newValue);
        assertThat("New value is returned after assignment", newValue, is(subject.getValidTo()));
    }

    /*
     * Test Methods - Getter and Setter Testing for PublicKey and IssuerSignature
     */
    @Test
    public void testGetSetPublicKey() {
        DigitalCertificate subject = new DigitalCertificate();
        RSAGenerator keyGen = new RSAGenerator();
        PublicKey newValue = null;
        try {
            KeyPair keys = keyGen.GenerateRSAKeys();
            newValue = keys.getPublic();
        } catch (java.security.NoSuchAlgorithmException e) {
            //if this happens, we dont have a value new pair to use to test with, so we fail.
            fail("Unable to Generate RSA Keys with RSAGeneretor. Test Generator class before attempting to debug.");
        }

        assertThat("Current publicKey value is equal to generated value.", newValue, is(not(subject.getpublicKey())));
        subject.setPublicKey(newValue);
        assertThat("New value was indeed assigned to member.", newValue, is(subject.getpublicKey()));
    }

    @Test
    public void testGetSetIssuerSignatur() {
        DigitalCertificate subject = new DigitalCertificate();
        byte[] newValue = GenerateRandomAlphaNumByteSequence(maxStringLength);
        assertThat("Current signature is not equal to generated value.", newValue, is(not(subject.getIssuerSignature())));

        subject.setIssuerSignature(newValue);
        assertThat("New value was indeed assigned to member variable.", newValue, is(subject.getIssuerSignature()));
    }

    /*
     * Test Methods - Default Value check for empty constructor.
     */
    @Test
    public void testDefaultInstanceValues() {
        DigitalCertificate subject = new DigitalCertificate();

        //All strings are exected to be null as they are reference types.
        assertThat("<String>HolderName is null", null, is(subject.getHolderName()));
        assertThat("<String>Subject is null", null, is(subject.getSubject()));
        assertThat("<String>Issuer is null", null, is(subject.getIssuer()));
        assertThat("<String>SerialNumber is null", null, is(subject.getSerialNumber()));
        assertThat("<String>ValidFrom is null", null, is(subject.getValidFrom()));
        assertThat("<String>ValidTo is null", null, is(subject.getValidTo()));

        //We will also expected the PublicKey and IssuerSignature to be null as they too are reference types.
        assertThat("<PublicKey>publicKey is null", null, is(subject.getpublicKey()));
        assertThat("<byte[]>IssuerSignature is null", null, is(subject.getIssuerSignature()));
    }

    private static final String CharSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private String GenerateRandomAlphaNumString(int length) {
        return new String(GenerateRandomAlphaNumSequence(length));
    }

    private byte[] GenerateRandomAlphaNumByteSequence(int length) {
        char[] seq = GenerateRandomAlphaNumSequence(length);
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = (byte) seq[i];
        }
        return result;
    }

    private char[] GenerateRandomAlphaNumSequence(int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = CharSet.charAt(rand.nextInt(CharSet.length()));
        }
        return text;
    }
}
