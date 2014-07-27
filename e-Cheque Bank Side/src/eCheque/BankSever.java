/*
 * BankSever.java
 *
 * Created on June 10, 2007, 1:06 AM
 *
 */

package eCheque;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;


/**
 * The BankServer class implements the container application which the Banking
 * institute would run for their portion of the e-Cheque system.
 * 
 * @author Saad
 * Last edit: Paul
 */
public class BankSever implements Runnable{
    private ServerSocket serverSocket;
    
    
    /**
     * Instantiate a Bank Server. 
     * @throws IOException if the network connection could not be established. 
     * Check that your network is connected within the OS and that you can visit
     * intra or internet pages.
     */
    public BankSever() throws IOException{
        
        serverSocket = new ServerSocket(8189);
    }
    
    /**
     * Runnable interface method.
     */
    public void run(){
        try{
            while(true){
            
                Socket incoming = serverSocket.accept();
                Runnable chequeServer = new BankServerProcessWorker(incoming);
                Thread bankThreading = new Thread(chequeServer);
                bankThreading.start();
            }
        }
        catch(IOException exp){
            System.out.println("Network Error: " + exp.getMessage());
        }
    }    
}
