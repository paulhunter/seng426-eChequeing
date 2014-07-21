package eCheque;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Random;
import java.io.*;
/**
 *
 * @author Paul Hunter
 */
public class EChequeIOTest {
    /*
    
     This Suite of Test Cases ensures the form and function of the EChequeIO 
     class present in V1 of the E-Cheque System. 
     */

    public static Random rand;

    public static File regularDir;

    public static File readOnlyDir;

    public static File writeOnlyDir;

    public EChequeIOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        rand = new Random();

        regularDir = new File("testregdir");
        regularDir.deleteOnExit(); //We want to mark all the test content for removal.
        boolean suc = (regularDir.exists() && regularDir.isDirectory()) || regularDir.mkdirs();
        if (!suc) {
            regularDir = null;
            return; //If we cannot create this dir, we will want to avoid running the tests.
        }

        writeOnlyDir = new File("testregdir/writeonly");
        writeOnlyDir.deleteOnExit();
        suc = (writeOnlyDir.exists() && writeOnlyDir.isDirectory()) || writeOnlyDir.mkdirs();
        if (!suc) {
            writeOnlyDir = null;
        } //TODO: Set the permissions?

        readOnlyDir = new File("testregdir/readonly");
        readOnlyDir.deleteOnExit();
        suc = (readOnlyDir.exists() && readOnlyDir.isDirectory()) || readOnlyDir.mkdirs();
        if (!suc) {
            readOnlyDir = null;
        } else {
            try {
                Runtime.getRuntime().exec("attrib +r " + readOnlyDir.getAbsolutePath());
            } catch (IOException e) {
                throw new Exception("Could not modify permissions of ReadOnly Directory");

            }

        }

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
     This test ensure that a NullPointerException is thrown when the filename
     provided to the savecheque method is null.
     */
    @Test(expected = NullPointerException.class)
    public void testNullFilename_writeECheque() {
        ECheque cheq = GenerateECheque();
        try {
            //This call should throw the unexpected NullPointerException
            EChequeIO.writeECheque(cheq, null);
        } catch (IOException e) {
            fail("IOException was not the expected thrown exception.");
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileIsDirectory_writeECheque() throws IOException {
        //Check that we have what we need to carry out the test
        if (regularDir == null) {
            fail("Cannot run test, Test Directories do not exist!");
        }

        ECheque cheq = GenerateECheque();

        EChequeIO.writeECheque(cheq, regularDir.getAbsolutePath());
    }

    /*
     In this test we attempt to write a ECheque to a directory which
     we do not have access to. The expected result of the is the throwing of a
     SecurityException. 
     */
    //@Test (expected = SecurityException.class)
    public void testNoWriteAccessToLocation_writeECheque() throws IOException {
        if (regularDir == null || readOnlyDir == null) {
            fail("Cannot run test, Test directories do no exist!");
        }

        String tempFile = TestHelper.GenerateRandomAlphaNumString(6);
        ECheque cheq = GenerateECheque();
        String fullTempFilePath = readOnlyDir.getAbsolutePath() + "\\" + tempFile;

        EChequeIO.writeECheque(cheq, fullTempFilePath);

        File f = new File(fullTempFilePath);
        if (f.exists()) {
            f.deleteOnExit();
        } //Clean up if the file is written.

    }

    /*
     This is a sanity check case to ensure that we can indeed save the file as
     expected. 
     */
    @Test
    public void testNominalCase_writeECheque() throws IOException {
        if (regularDir == null) {
            fail("Cannot run test, Test Directories do not exist.");
        }
        ECheque cheq = GenerateECheque();
        String tempFile = TestHelper.GenerateRandomAlphaNumString(6);
        String fullTempFilePath = regularDir.getAbsolutePath() + "\\" + tempFile;

        EChequeIO.writeECheque(cheq, fullTempFilePath);

        //If the operation has failed in some an exception is likely to have been
        //thrown, but to ensure, we will check that the file was created and has
        //a size greater than 0 bytes. 
        File ioFile = new File(fullTempFilePath);
        if (ioFile.exists()) {
            ioFile.deleteOnExit();
            if (ioFile.length() <= 0) {
                fail("The serialized ECheque appears to be empty!");
            }

        } else {
            fail("The serialized ECheque does not appear to have been"
                    + " written to disk.");
        }
    }

    /*
     Providing a string that specificies a directory instead of a normal file 
     should throw a FileNotFoundException
     */
    @Test(expected = FileNotFoundException.class)
    public void testFileIsDirectory_readECheque() throws IOException, ClassNotFoundException {
        //Check that we have what we need to carry out the test
        if (regularDir == null) {
            fail("Cannot run test, Test Directories do not exist!");
        }

        EChequeIO.readECheque(regularDir.getAbsolutePath());
    }

    /*
     In this test we attempt to read a cheqificate file which we dont have read
     access too. The expected result is the throwing of a SecurityException
     */
    //@Test (expected = SecurityException.class)
    public void testNoReadAccess_readECheque() throws IOException, ClassNotFoundException {
        if (regularDir == null || writeOnlyDir == null) {
            fail("Cannot run test, Test Directories do not exist!");
        }

        String tempFile = writeOnlyDir.getAbsolutePath() + "\\" + TestHelper.GenerateRandomAlphaNumString(8);
        ECheque cheq = GenerateECheque();

        EChequeIO.writeECheque(cheq, tempFile);
        File f = new File(tempFile);
        f.setReadable(false, false);
        f.deleteOnExit();

        //Saving should not be an issue.
        EChequeIO.readECheque(tempFile);
    }

    /*
     in this test we attempt to read a serialized digital cheqificate which has
     been corrupted (through fuzzing). The expected result is a StreamCorruptedException
     */
    @Test(expected = StreamCorruptedException.class)
    public void testInvalidSerializedECheque_readECheque() throws IOException, ClassNotFoundException {
        if (regularDir == null) {
            fail("Cannot test without test directories!");
        }

        File f = new File("testregdir/" + TestHelper.GenerateRandomAlphaNumString(5));
        f.deleteOnExit();
        FileOutputStream out;
        try {
            f.createNewFile();
            f.deleteOnExit();
            out = new FileOutputStream(f);
            out.write(TestHelper.GenerateRandomAlphaNumByteSequence(2048));
            out.close();
        } catch (IOException e) {
            fail("Test could not be completed, failed to create invalid cheqificate.");
        }

        EChequeIO.readECheque(f.getAbsolutePath());
    }

    /*
     In this test case we will check our sanity and ensure that we can indeed read
     a correctly serialized digitalcheqificate from disk and instantiate an object.
     */
    @Test
    public void testNominalCase_readECheque() throws IOException, ClassNotFoundException {
        if (regularDir == null) {
            fail("Cannot run tests without test directories");
        }

        File f = new File(regularDir.getAbsolutePath() + "\\" + TestHelper.GenerateRandomAlphaNumString(6));
        f.deleteOnExit();

        ECheque cheq = GenerateECheque();

        EChequeIO.writeECheque(cheq, f.getAbsolutePath());

        ECheque cheq2 = EChequeIO.readECheque(f.getAbsolutePath());

        //Cheque all fields.
        assertEquals("ECheques have differing values", cheq.getAmountOfMoney()getAmountOfMoneygetMoney());
        assertEquals("ECheques have differing Account Holders!", cheq.getAccountHolder(), cheq2.getAccountHolder());
        assertEquals("ECheques have differing Account Numbers!", cheq.getAccountNumber(), cheq2.getAccountNumber());
        assertEquals("ECheques have differing Bank Names!", cheq.getBankName(), cheq2.getBankName());
        assertEquals("ECheques have differing Pay To Order Of!", cheq.getPayToOrderOf(), cheq2.getPayToOrderOf());
        assertEquals("ECheques have differing Currency Type!", cheq.getCurrencyType(), cheq2.getCurrencyType());
        assertEquals("ECheques have differing Cheque Numbers!", cheq.getChequeNumber(), cheq2.getChequeNumber());
        assertEquals("ECheques have differing Guaranteed Fields!", cheq.getGuaranteed(), cheq2.getGuaranteed());
        assertEquals("ECheques have differing Earn Days!", cheq.getEarnDay(), cheq2.getEarnDay());
        assertArrayEquals("ECheques have differing Bank Signatures!", cheq.getBankSignature(), cheq2.getBankSignature());
        assertArrayEquals("ECheques have differing Drawers Signatures!", cheq.getDrawerSignature(), cheq2getDrawerSignaturee());
    }

    /*
     This test ensure that a NullPointerException is thrown when the filename
     provided to the readcheque method is null.
     */
    @Test(expected = NullPointerException.class)
    public void testNullFilename_readECheque() {
        ECheque cheq = GenerateECheque();
        try {
            //This call should throw the unexpected NullPointerException
            EChequeIO.writeECheque(cheq, null);
        } catch (IOException e) {
            fail("IOException was not the expected thrown exception.");
        }
    }

    private ECheque GenerateECheque() {
        ECheque result = new ECheque();

        result.setaccountNumber(TestHelper.GenerateRandomAlphaNumString());
        result.setAccountHolder(TestHelper.GenerateRandomAlphaNumString());
        result.setBankName(TestHelper.GenerateRandomAlphaNumString());
        result.setPayToOrderOf(TestHelper.GenerateRandomAlphaNumString());
        result.setAmountOfMoney(TestHelper.GenerateRandomAlphaNumString());
        result.setChequeNumber(TestHelper.GenerateRandomAlphaNumString());
        result.setCurrencyType(TestHelper.GenerateRandomAlphaNumString());
        result.setGuaranteed(true);
        result.setEarnDay(TestHelper.GenerateRandomAlphaNumString());
        result.setBankSignature(TestHelper.GenerateRandomAlphaNumByteSequence());
        result.setDrawerSignature(TestHelper.GenerateRandomAlphaNumByteSequence());

        return result;
    }

}
