/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eCheque;

import junit.framework.TestCase;

/**
 *
 * @author scottlow
 */
public class EChequeRegisterationTest extends TestCase
{
    
    public EChequeRegisterationTest(String testName)
    {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test of setBankName and getBankName methods, of class EChequeRegisteration.
     */
    public void testGetSetBankName()
    {
        System.out.println("getSetBankName");
        String bankName = "SampleBankName";
        EChequeRegisteration instance = new EChequeRegisteration();
        instance.setBankName(bankName);
        assertEquals(bankName, instance.getBankName());
    }

    /**
     * Test of setBankAddress and getBankAddress methods, of class EChequeRegisteration.
     */
    public void testGetSetBankAddress()
    {
        System.out.println("getSetBankAddress");
        String URL = "127.0.0.1";
        EChequeRegisteration instance = new EChequeRegisteration();
        instance.setBankAddress(URL);
        assertEquals(URL, instance.getBankAddress());
    }

    /**
     * Test of setAccountNumber and getAccountNumber methods, of class EChequeRegisteration.
     */
    public void testGetSetAccountNumber()
    {
        System.out.println("getSetAccountNumber");
        String accountNumber = "4579372";
        EChequeRegisteration instance = new EChequeRegisteration();
        instance.setAccountNumber(accountNumber);
        assertEquals(accountNumber, instance.getAccountNumber());
    }

    /**
     * Test of setClientName and getClientName methods, of class EChequeRegisteration.
     */
    public void testGetSetClientName()
    {
        System.out.println("getSetClientName");
        String clientName = "Test Client Name";
        EChequeRegisteration instance = new EChequeRegisteration();
        instance.setClientName(clientName);
        assertEquals(clientName, instance.getClientName());
    }

    /**
     * Test of setEWalletLoaction and getEWalletLoaction methods, of class EChequeRegisteration.
     */
    public void testGetSetEWalletLoaction()
    {
        System.out.println("getSetEWalletLoaction");
        String path = "/SamplePath/";
        EChequeRegisteration instance = new EChequeRegisteration();
        instance.setEWalletLoaction(path);
        assertEquals(path, instance.getEWalletLoaction());
    }

    /**
     * Test of setUsername and getUsername methods, of class EChequeRegisteration.
     */
    public void testGetSetUsername()
    {
        System.out.println("getSetUsername");
        int usernameHashValue = 32490235;
        EChequeRegisteration instance = new EChequeRegisteration();
        instance.setUsername(usernameHashValue);
        assertEquals(usernameHashValue, instance.getUsername());
    }

    /**
     * Test of setPasword and getPasword methods, of class EChequeRegisteration.
     */
    public void testGetSetPassword()
    {
        System.out.println("getSetPasword");
        int passwordHashValue = 46282;
        EChequeRegisteration instance = new EChequeRegisteration();
        instance.setPasword(passwordHashValue);
        assertEquals(passwordHashValue, instance.getPasword());
    }
}
