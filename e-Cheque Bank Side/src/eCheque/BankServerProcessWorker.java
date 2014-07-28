/*
 * BankServerProcessWorker.java
 *
 * Created on April 27, 2007, 8:17 PM
 *
 */
package eCheque;

import java.net.*;
import java.io.*;

/**
 * The BankServerProcessWorker is a asynchronous worker thread for the Banking
 * institute's server. The process will handle all network IO and terminate
 * after the operation has completed or failed.
 *
 * @author Paul
 */
public class BankServerProcessWorker implements Runnable {

    /**
     * Variables for socket connection, reading, and writing.
     */
    private Socket serverConnection;
    private ObjectInputStream clientInputObjectStream;
    private ObjectOutputStream clientOutputObjectStream;
    private InputStream clientInputStream;
    private OutputStream clientOutputStream;

    /**
     * Instantiate a new worker providing the socket of the incoming client
     * connection.
     *
     * @param socket Client Socket connection.
     */
    public BankServerProcessWorker(Socket socket) {
        serverConnection = socket;
    }

    /**
     * Attempt to retrieve the socket stream and object stream objects for input
     * and output from the client socket.
     *
     * @throws Exception if any of the operations needed to complete the
     * transaction fail.
     */
    private void getSocketSreams() throws Exception {
        clientOutputStream = serverConnection.getOutputStream();
        clientOutputStream.flush();

        clientInputStream = serverConnection.getInputStream();

        clientOutputObjectStream = new ObjectOutputStream(serverConnection.getOutputStream());
        clientOutputObjectStream.flush();

        clientInputObjectStream = new ObjectInputStream(serverConnection.getInputStream());
    }

    /**
     * Given the socket streams have been opened, process the client request.
     *
     * @throws IOException Networking failure.
     * @throws ClassNotFoundException Information from client is not of expected
     * type.
     */
    private void processClientRequest() throws IOException, ClassNotFoundException {
        String line;
        int code;
        line = (String) clientInputObjectStream.readObject();
        code = clientInputObjectStream.readInt();
        if (code == 0) {
            registerClientInfo();
        }
        if (code == 1) {
            depositCheque();
        }
        if (code == 2) {
            cancelCheque();
        }
    }

    /**
     * Carry out the registration of a client.
     *
     * @throws IOException Networking failure.
     * @throws ClassNotFoundException Information from client did not match the
     * expected pattern.
     */
    private void registerClientInfo() throws IOException, ClassNotFoundException {
        EChequeRegisteration registerClient;
        registerClient = (EChequeRegisteration) clientInputObjectStream.readObject();
        // get user account ID

        EChequeDB.RegisterClient(registerClient);

        DigitalCertificate registDC = (DigitalCertificate) clientInputObjectStream.readObject();
        //store client digital certificate
        DigitalCertificateIO.writeDigitalCertificate(registDC, "Bank\\" + registerClient.getClientName() + "DC.edc");

        clientOutputObjectStream.writeObject("registeration complete");
        clientOutputObjectStream.flush();
        //JOptionPane.showMessageDialog(null,"Register Done");
    }

    /**
     * Carry out the deposit of a check including the appropriate checks before
     * deposit.
     *
     * @throws IOException Networking Failure.
     * @throws ClassNotFoundException the information received from the client
     * did not match the expected format.
     */
    private void depositCheque() throws IOException, ClassNotFoundException {

        String depositResult = "";
        //read cheque from socket
        ECheque recivedCehq = (ECheque) clientInputObjectStream.readObject();
        //read deposit Account number.
        String depositAccount = (String) clientInputObjectStream.readObject();

        //check the withdraw account. 
        double[] balanceValue = new double[1];
        double chequeMoney = Double.parseDouble(recivedCehq.getAmountOfMoney());
        if (!EChequeDB.GetAccountFromCheque(recivedCehq, balanceValue)) {
            depositResult = "This eCheque does not appear to belong to one of our customers\nYou will need to conenct with their bank to deposit the cheque.";
        } else if (chequeMoney > balanceValue[0]) {
            depositResult = "Apologies, it does not appear that the Drawer has the appriopriate funds.\n";
        } else if (EChequeDB.IsChequeCancelled(recivedCehq)) {
            depositResult = "Unfortunately, this eCheque has been canceled by the Drawer\nWe can not deposit it at this time.";
        } else if (EChequeDB.HasChequeBeenCashed(recivedCehq)) {
            depositResult = "This eCheque has already been deposited. Sorry, we can not deposit it twice.";
        } else {
            EChequeDB.DepositCheque(recivedCehq, depositAccount);
            depositResult = "Your acoount recieves the deposit.\nYour balance is incremented by" + recivedCehq.getAmountOfMoney();
        }

        clientOutputObjectStream.writeObject(depositResult);
        clientOutputObjectStream.flush();
    }

    /**
     * Cancel a cheque that was previously created. Making it impossible to cash
     * by the individual it was created for.
     *
     * @throws IOException Networking Failure.
     * @throws ClassNotFoundException Information received from the client was
     * not of the expected format.
     */
    private void cancelCheque() throws IOException, ClassNotFoundException {
        ECheque recivedCheq = (ECheque) clientInputObjectStream.readObject();
        if (EChequeDB.CancelCheque(recivedCheq)) {
            clientOutputObjectStream.writeObject("cheque canceld done");
            clientOutputObjectStream.flush();

        } else {
            clientOutputObjectStream.writeObject("sorry cheque not canceled");
            clientOutputObjectStream.flush();
        }

    }

    /**
     * After the communication is complete for the operation that the client
     * wants to carry out this method should be called to close all the
     * appropriate socket objects for input and output. No exceptions will be
     * thrown from this method, it will handle the exceptions as needed.
     */
    private void closeClientConnection() {
        try {
            clientInputStream.close();
        } catch (Exception e) {
        }

        try {
            clientOutputStream.close();
        } catch (Exception e) {
        }

        try {
            clientInputObjectStream.close();
        } catch (Exception e) {
        }

        try {
            clientOutputObjectStream.close();
        } catch (Exception e) {
        }

        try {
            serverConnection.close();
        } catch (Exception e) {
        }

    }

    /**
     * Entry method for the worker process. This is the method that should be
     * used to star a working thread from within the BankServer
     * process/application.
     */
    public void RunServer() {
        try {
            getSocketSreams();
            processClientRequest();
        } catch (Exception e) {
            //TODO: provide an error to the user.
            //Unable to complete operation.
        } finally {
             closeClientConnection();
        }
    }

    /**
     * Runnable Interface method's concrete implementation.
     */
    public void run() {

        RunServer();
    }

}
