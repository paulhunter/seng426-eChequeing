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
 * @author Justin Guze
 */
public class EChequeTest {
    
    public ECheque eCheque;
    
    public EChequeTest() {
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
    
    /*
     * Test Methods
     */
    @Test
    public void testGetSetAccountHolder() {
        String accountHolder = "Mike Smith";
        eCheque.setAccountHolder(accountHolder);
        assertThat(accountHolder, is(equalTo(eCheque.getAccountHolder())));
    }
    
    @Test
    public void testGetSetAccountNumber() {
        String accountNumber = "1234 5678 9101";
        eCheque.setaccountNumber(accountNumber);
        assertThat(accountNumber, is(equalTo(eCheque.getAccountNumber())));
    }
    
    @Test
    public void testGetSetBankName() {
        String bankname = "Royal Bank of Anything";
        eCheque.setBankName(bankname);
        assertThat(bankname, is(equalTo(eCheque.getBankName())));
    }
    
    @Test
    public void testGetSetPayToOrderOf() {
        String payTo = "Jon Smith";
        eCheque.setPayToOrderOf(payTo);
        assertThat(payTo, is(equalTo(eCheque.getPayToOrderOf())));
    }
    
    @Test
    public void testGetSetAmountOfMoney() {
        String amountOfMoney = "1234";
        eCheque.setAmountOfMoney(amountOfMoney);
        assertThat(amountOfMoney, is(equalTo(eCheque.getAmountOfMoney())));
    }
    
    @Test
    public void testGetSetCurrencyType() {
        String currencyType = "USD";
        eCheque.setCurrencyType(currencyType);
        assertThat(currencyType, is(equalTo(eCheque.getCurrencyType())));
    }
    
    @Test
    public void testGetSetChequeNumber() {
        String chequeNumber = "12";
        eCheque.setChequeNumber(chequeNumber);
        assertThat(chequeNumber, is(equalTo(eCheque.getChequeNumber())));
    }
    
    @Test
    public void testGetSetGuaranteed() {
        boolean guaranteed = true;
        eCheque.setGuaranteed(guaranteed);
        assertThat(guaranteed, is(equalTo(eCheque.getGuaranteed())));
    }
    
    @Test
    public void testGetSetEarnDay() {
        String earnDay = "Monday";
        eCheque.setEarnDay(earnDay);
        assertThat(earnDay, is(equalTo(eCheque.getEarnDay())));
    }
    
    @Test
    public void testGetSetBankSignature() {
        byte[] signature = new byte[100];
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                signature[i] = 1;
            }
        }
        eCheque.setBankSignature(signature);
        assertThat(signature, is(equalTo(eCheque.getBankSignature())));
    }
    
    @Test
    public void testGetSetDrawerSignature() {
        byte[] signature = new byte[100];
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                signature[i] = 1;
            }
        }
        eCheque.setDrawerSignature(signature);
        assertThat(signature, is(equalTo(eCheque.getDrawerSignature())));       
    }
}
