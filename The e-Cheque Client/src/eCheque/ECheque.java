/*
 * cheque.java
 *
 * Created on March 27, 2007, 10:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eCheque;

import java.io.Serializable;

/**
 * This class is a holder class for all ECheque information
 * @author Basel
 */
public class ECheque implements Serializable
{
    private String accountHolder;
    private String accountNumber;
    private String bankName;
    private String payToOrderOf ;
    private String amountOfMoney;
    private String currencyType;
    private String chequeNumber;
    private boolean guaranteed;
    private String earnDay;
    private byte[] bankSignature;
    private byte[] drawerSignature;

    public ECheque() {
        
    }
    
    /*
     * SET FUNCTIONS
    */
    
    public void setAccountHolder(String x) {
        accountHolder = x;     
    }
    
    public void setaccountNumber(String y) {
        accountNumber = y;
    }
    
    public void setBankName(String z) {
        bankName = z;
    }
  
    public void setPayToOrderOf(String m) {
        payToOrderOf = m;
    }
    
    public void setAmountOfMoney(String s) {
        amountOfMoney = s;
    }
    
    public void setCurrencyType(String n) {
        currencyType = n;
    }
    
    public void setChequeNumber(String c) {
        chequeNumber = c;
    }
    
    public void setGuaranteed(boolean s) {
        guaranteed = s;    
    }
    
    public void setEarnDay(String u) {
        earnDay = u; 
    }
    
    public void setBankSignature(byte[] y) {
        bankSignature = y;
    } 
   
    public void setDrawerSignature(byte[] y) {
        drawerSignature = y;
    } 
    
    /*
     * GET FUNCTIONS
    */
    
    public String getAmountOfMoney() {
        return amountOfMoney;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public String getAccountNumber() {
       return accountNumber;
    }
    
    public String getBankName() {
         return bankName;  
    }
        
    public String getPayToOrderOf() {
       return  payToOrderOf;   
    }
    
    public String getCurrencyType() {
        return currencyType;   
    }
    
    public String getChequeNumber() {
        return chequeNumber;
    }

    public boolean getGuaranteed() {
        return guaranteed;
    }
    
    public String getEarnDay() {
        return earnDay;
    }
    
    public byte[] getBankSignature() {
        return bankSignature;
    }
    
    public byte[] getDrawerSignature() {
        return  drawerSignature;
    }
}