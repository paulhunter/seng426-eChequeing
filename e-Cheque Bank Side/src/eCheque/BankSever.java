/*
 * BankSever.java
 *
 * Created on June 10, 2007, 1:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eCheque;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
/**
 *
 * @author Saad
 */
public class BankSever implements Runnable{
    private static final int SERVER_PORT = 8190;
    private ServerSocket serverSocket;
    /** Creates a new instance of BankSever */
    public BankSever() throws IOException{
        
        serverSocket = new ServerSocket(SERVER_PORT);
    }
    
    
    public void run(){
        try{
            while(true){
            
                Socket incoming = serverSocket.accept();
                Runnable chequeServer = new Echqueserver(incoming);
                Thread bankThreading = new Thread(chequeServer);
                bankThreading.start();
            }
        }
        catch(IOException exp){
            System.out.println("Network Error: " + exp.getMessage());
        }
    }    
}
