/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eCheque;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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
public class EChequeTests {
    
    public ECheque eCheque;
    
    public EChequeTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        eCheque = new ECheque();
    }
    
    @After
    public void tearDown() {
        eCheque = null;
    }
    
//         private String accountholder;
//     private String accountNumber;
//     private String bankname;
//     private String payToOrderOf ;
//     private String amountofMony;
//     private String currencytype;
//     private String chequeNumber;
//     private boolean guaranteed;
//     private String earnday;
//     private byte[] banksignature;
//     private byte[] drawersiganure;
//     
//     

    /*
     * Test Methods
     */
    @Test
    public void testGetSetAccountHolder() {
        String accountHolder = "Mike Smith";
        eCheque.setaccountholder(accountHolder);
        assertThat(accountHolder, is(equalTo(eCheque.getaccountholder())));
    }
    
    @Test
    public void testGetSetAccountNumber() {
        String accountNumber = "1234 5678 9101";
        eCheque.setaccountNumber(accountNumber);
        assertThat(accountNumber, is(equalTo(eCheque.getaccountNumber())));
    }
    
    @Test
    public void testGetSetBankName() {
        String bankname = "Royal Bank of Anything";
        eCheque.setbankname(bankname);
        assertThat(bankname, is(equalTo(eCheque.getbankname())));
    }
    
    @Test
    public void testGetSetPayToOrderOf() {
        String payTo = "Jon Smith";
        eCheque.setpayToOrderOf(payTo);
        assertThat(payTo, is(equalTo(eCheque.getpayToOrderOf())));
    }
    
    @Test
    public void testGetSetAmountOfMoney() {
        String amountOfMoney = "1234";
        eCheque.setamountofMony(amountOfMoney);
        assertThat(amountOfMoney, is(equalTo(eCheque.getMoney())));
    }
    
    @Test
    public void testGetSetCurrencyType() {
        String currencyType = "USD";
        eCheque.setcurrencytype(currencyType);
        assertThat(currencyType, is(equalTo(eCheque.getcurrencytype())));
    }
    
    @Test
    public void testGetSetChequeNumber() {
        String chequeNumber = "12";
        eCheque.setchequeNumber(chequeNumber);
        assertThat(chequeNumber, is(equalTo(eCheque.getchequeNumber())));
    }
    
    @Test
    public void testGetSetGuaranteed() {
        boolean guaranteed = true;
        eCheque.setguaranteed(guaranteed);
        assertThat(guaranteed, is(equalTo(eCheque.getguaranteed())));
    }
    
    @Test
    public void testGetSetEarnDay() {
        String earnDay = "Monday";
        eCheque.setearnday(earnDay);
        assertThat(earnDay, is(equalTo(eCheque.getearnday())));
    }
    
    @Test
    public void testGetSetBankSignature() {
        byte[] signature = new byte[100];
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                signature[i] = 1;
            }
        }
        eCheque.setbanksignature(signature);
        assertThat(signature, is(equalTo(eCheque.getbanksignature())));
    }
    
    @Test
    public void testGetSetDrawerSignature() {
        byte[] signature = new byte[100];
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                signature[i] = 1;
            }
        }
        eCheque.setdrawersiganure(signature);
        assertThat(signature, is(equalTo(eCheque.getdrawersiganure())));       
    }
}
