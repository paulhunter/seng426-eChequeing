package eCheque;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Random;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
 /*
 * @author Paul Hunter
 */
public class DigitalCertificateIOTests {
    
    public static Random rand;
    
    public static File regularDir;
    
    public static File readOnlyDir; 
    
    public static File writeOnlyDir;
    
    public DigitalCertificateIOTests() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        rand = new Random();

        regularDir = new File("testregdir");
        regularDir.deleteOnExit(); //We want to mark all the test content for removal.
        boolean suc = (regularDir.exists() && regularDir.isDirectory()) || regularDir.mkdirs();
        if(!suc){
            regularDir = null;
            return; //If we cannot create this dir, we will want to avoid running the tests.
        }

        writeOnlyDir = new File("testregdir/writeonly");
        writeOnlyDir.deleteOnExit();
        suc = (writeOnlyDir.exists() && writeOnlyDir.isDirectory()) || writeOnlyDir.mkdirs();
        if(!suc) {
            writeOnlyDir = null;
        } //TODO: Set the permissions?
        
        readOnlyDir = new File("testregdir/readonly");
        readOnlyDir.deleteOnExit();
        suc = (readOnlyDir.exists() && readOnlyDir.isDirectory()) || readOnlyDir.mkdirs();
        if(!suc)
        {
            readOnlyDir = null;
        } else {
            try
            {
                Runtime.getRuntime().exec("attrib +r " + readOnlyDir.getAbsolutePath());
            } catch (IOException e) {
                throw new Exception("Could not modify permissions of ReadOnly Directory");
                        
            }            
            
        }
        
    }

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() {}

    @After
    public void tearDown() {}
    
    /*
    This test ensure that a NullPointerException is thrown when the filename
    provided to the SaveDC method is null.
    */
    @Test (expected = NullPointerException.class) 
    public void testNullFilenameSaveDC() {
        DigitalCertificate cert = GenerateCertificate();
        DigitalCertificateIO io = new DigitalCertificateIO();
        try
        {
            //This call should throw the unexpected NullPointerException
            io.SaveDC(cert, null);
        } catch (IOException e)
        {
            fail("IOException was not the expected thrown exception.");
        }
    }
    
    @Test (expected = FileNotFoundException.class)
    public void testFileIsDirectorySaveDC() throws IOException {
        //Check that we have what we need to carry out the test
        if(regularDir == null)
        {
            fail("Cannot run test, Test Directories do not exist!");
        }
        
        DigitalCertificate cert = GenerateCertificate();
        DigitalCertificateIO io = new DigitalCertificateIO();
        
        io.SaveDC(cert, regularDir.getAbsolutePath());
    }
    
    /*
    In this test we attempt to write a DigitalCertificate to a directory which
    we do not have access to. The expected result of the is the throwing of a
    SecurityException. 
    */
    //@Test (expected = SecurityException.class)
    public void testNoWriteAccessToLocation_SaveDC() throws IOException
    {
        if(regularDir == null || readOnlyDir == null)
        {   
            fail("Cannot run test, Test directories do no exist!");
        }
        
        String tempFile = TestHelper.GenerateRandomAlphaNumString(6);
        DigitalCertificate cert = GenerateCertificate();
        DigitalCertificateIO io = new DigitalCertificateIO();
        String fullTempFilePath = readOnlyDir.getAbsolutePath() + "\\" + tempFile;
        
        io.SaveDC(cert, fullTempFilePath);
        
        File f = new File(fullTempFilePath);
        if(f.exists())
        {
            f.deleteOnExit();
        } //Clean up if the file is written.
        
    }
    
    /*
    This is a sanity check case to ensure that we can indeed save the file as
    expected. 
    */
    @Test
    public void testNominalCase_SaveDC() throws IOException
    {
        if(regularDir == null)
        {
            fail("Cannot run test, Test Directories do not exist.");
        }
        DigitalCertificate cert = GenerateCertificate();
        String tempFile = TestHelper.GenerateRandomAlphaNumString(6);
        DigitalCertificateIO io = new DigitalCertificateIO();
        String fullTempFilePath = regularDir.getAbsolutePath() + "\\" + tempFile;
        
        io.SaveDC(cert, fullTempFilePath);
        
        //If the operation has failed in some an exception is likely to have been
        //thrown, but to ensure, we will check that the file was created and has
        //a size greater than 0 bytes. 
        
        File ioFile = new File(fullTempFilePath);
        if(ioFile.exists())
        {
            ioFile.deleteOnExit();
            if(ioFile.length() <= 0)
            {
                fail("The serialized Certificate appears to be empty!");
            }
            
        } else {
            fail("The serialized Certificate does not appear to have been"
                    + " written to disk.");
        }
    }
    
    /*
    Providing a string that specificies a directory instead of a normal file 
    should throw a FileNotFoundException
    */
    @Test (expected = FileNotFoundException.class)
    public void testFileIsDirectory_readDigitalCertificate() throws IOException, ClassNotFoundException
    {
        //Check that we have what we need to carry out the test
        if(regularDir == null)
        {
            fail("Cannot run test, Test Directories do not exist!");
        }
        
        DigitalCertificateIO io = new DigitalCertificateIO();
        io.readDigitalCertificate(regularDir.getAbsolutePath());
    }
    
    /*
    In this test we attempt to read a certificate file which we dont have read
    access too. The expected result is the throwing of a SecurityException
    */
    //@Test (expected = SecurityException.class)
    public void testNoReadAccess_readDigitalCertificate() throws IOException, ClassNotFoundException
    {
        if(regularDir == null || writeOnlyDir == null)
        {
            fail("Cannot run test, Test Directories do not exist!");
        }
        
        String tempFile = writeOnlyDir.getAbsolutePath() + "\\" + TestHelper.GenerateRandomAlphaNumString(8);
        DigitalCertificate cert = GenerateCertificate();
        DigitalCertificateIO io = new DigitalCertificateIO();
        
        io.SaveDC(cert, tempFile);
        File f = new File(tempFile);
        f.setReadable(false, false);
        f.deleteOnExit();
        
        //Saving should not be an issue.
        io.readDigitalCertificate(tempFile);
    }
    
    /*
    in this test we attempt to read a serialized digital certificate which has
    been corrupted (through fuzzing). The expected result is a StreamCorruptedException
    */
    @Test (expected = StreamCorruptedException.class)
    public void testInvalidSerializedCertificate_readDigitalCertificate() throws IOException, ClassNotFoundException
    {
        if(regularDir == null)
        {
            fail("Cannot test without test directories!");
        }
        
        File f = new File("testregdir/" + TestHelper.GenerateRandomAlphaNumString(5));
        f.deleteOnExit();
        FileOutputStream out;
        try
        {
            f.createNewFile();
            f.deleteOnExit();
            out = new FileOutputStream(f);
            out.write(TestHelper.GenerateRandomAlphaNumByteSequence(2048));
            out.close();
        } catch (IOException e) {
            fail("Test could not be completed, failed to create invalid certificate.");
        } 
        
        DigitalCertificateIO io = new DigitalCertificateIO();
        io.readDigitalCertificate(f.getAbsolutePath());        
    }    
        
    /*
    In this test case we will check our sanity and ensure that we can indeed read
    a correctly serialized digitalcertificate from disk and instantiate an object.
    */
    @Test
    public void testNominalCase_readDigitalCertificate() throws IOException, ClassNotFoundException
    {
        if(regularDir == null)
        {
            fail("Cannot run tests without test directories");
        }
        
        File f = new File(regularDir.getAbsolutePath() + "\\" + TestHelper.GenerateRandomAlphaNumString(6));
        f.deleteOnExit();
        
        DigitalCertificate cert = GenerateCertificate();
        DigitalCertificateIO io = new DigitalCertificateIO();
        
        io.SaveDC(cert, f.getAbsolutePath());
        
        DigitalCertificate cert2 = io.readDigitalCertificate(f.getAbsolutePath());  
        
        assertEquals("Certificates have differing Holder Names!", cert.getHolderName(), cert2.getHolderName());
        assertEquals("Certificates have differing Issuers!", cert.getIssuer(), cert2.getIssuer());
        assertArrayEquals("Certificates have differing Issuer Signatures!", cert.getIssuerSignature(), cert2.getIssuerSignature());
        assertEquals("Certificates have differing Serial Numbers!", cert.getSerialNumber(), cert2.getSerialNumber());
        assertEquals("Certificates have differing Subjects!", cert.getSubject(), cert2.getSubject());
        assertEquals("Certificates have differing ValidFrom!", cert.getValidFrom(), cert2.getValidFrom());
        assertEquals("Certificates have differing ValidTo!", cert.getValidTo(), cert2.getValidTo());
        assertEquals("Certificates have differing Public Keys!", cert.getpublicKey(), cert2.getpublicKey());
    }
    
    
    private DigitalCertificate GenerateCertificate() {
        DigitalCertificate result = new DigitalCertificate();
        String n = TestHelper.GenerateRandomAlphaNumString();
        result.setHolderName(n);
        result.setIssuer(TestHelper.GenerateRandomAlphaNumString());
        result.setIssuerSignature(TestHelper.GenerateRandomAlphaNumByteSequence(24));
        try
        {
            result.setPublicKey(new RSAGenerator().GenerateRSAKeys().getPublic());
        }
        catch (java.security.NoSuchAlgorithmException e)
        {
            //TODO: Log?
            //We don't 
        }
        result.setSerialNumber(TestHelper.GenerateRandomAlphaNumString());
        result.setSubject(TestHelper.GenerateRandomAlphaNumString());
        result.setValidFrom(TestHelper.GenerateRandomAlphaNumString());
        result.setValidTo(TestHelper.GenerateRandomAlphaNumString());
        return result;
    }
}
