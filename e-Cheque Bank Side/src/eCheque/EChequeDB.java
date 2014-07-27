/*
 * EChequeDB.java
 *
 * Created on June 6, 2007, 6:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package eCheque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 * The EChequeDB class is the Data Abstraction Layer of the ECheque system. It
 * carries out operations against the local database throw the static helper
 * method it provides.
 *
 * @author Paul
 */
public class EChequeDB {

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String databaseUrl;
    private String userName;
    private String password;
    private Connection connection = null;
    private Statement sqlStatement = null;
    private int databaseMode;
    private ResultSet resultSet;

    private static EChequeDB instance;

    private static EChequeDB getInstance() {
        if (instance == null) {
            instance = new EChequeDB();
        }
        return instance;
    }

    /**
     * Creates a new instance of EChequeDB
     */
    public EChequeDB() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("dbConfig.txt"));

            databaseUrl = reader.readLine();
            userName = reader.readLine();
            password = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error: No configuration file!");
            System.exit(0);
        }

    }

    private boolean connectToDataBase() throws ClassNotFoundException, SQLException {
        // Initialize Connection to DB:
        Class.forName(JDBC_DRIVER); // load database driver class
        // establish connection to database                              
        connection = DriverManager.getConnection(databaseUrl, userName, password);
        return true;
    }

    private boolean closeDataBaseConnection() {
        try {
            // close the database connection channel
            connection.close();
            sqlStatement.close();
            //JOptionPane.showMessageDialog(null,"You are disconnected to e-Cheque Bank DB","DB State",JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException exp) {
            //JOptionPane.showMessageDialog(null,exp.getMessage(),"DB Error",JOptionPane.ERROR_MESSAGE);
            exp.printStackTrace();
            return false;
        }
    }

    private boolean createStatment() throws SQLException {

        sqlStatement = connection.createStatement();
        return true;
    }

    private void executeSQLStatment(String statment, int statType) throws SQLException {

        // Initialize sql statment and excute it.
        if (statType == 0) {
            resultSet = sqlStatement.executeQuery(statment);

        }
        if (statType == 1) {
            sqlStatement.executeUpdate(statment);
        }

    }

    public boolean runDB(int mode, String databaseStat) {
        databaseMode = mode;
        boolean flag = false;
        try {
            connectToDataBase();
            //JOptionPane.showMessageDialog(null,"You are connected to e-Cheque Bank DB","DB State",JOptionPane.INFORMATION_MESSAGE);
            createStatment();
            //JOptionPane.showMessageDialog(null,"You have created statment","DB State",JOptionPane.INFORMATION_MESSAGE);

            // run the specific sql statment
            executeSQLStatment(databaseStat, databaseMode);
            flag = true;
        } catch (ClassNotFoundException exp) {
            //JOptionPane.showMessageDialog(null,exp.getMessage(),"DB Error",JOptionPane.ERROR_MESSAGE);
            exp.printStackTrace();

        } catch (SQLException exp) {
            //JOptionPane.showMessageDialog(null,exp.getMessage(),"DB Error",JOptionPane.ERROR_MESSAGE);
            exp.printStackTrace();

        } finally {

            closeDataBaseConnection();
            if (flag) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean runDB(int mode, String databaseStat, double[] balance) {
        databaseMode = mode;
        boolean flag = false;

        try {
            connectToDataBase();
            System.out.println("DB State: You are connected to the e-Cheque Bank DB");
            createStatment();
            System.out.println("DB State: You have created a statement");

            // run the specific sql statment
            executeSQLStatment(databaseStat, databaseMode);
            if (resultSet.next()) {
                balance[0] = resultSet.getDouble(1);
                flag = true;
            } else {
                balance[0] = 0.0;
                flag = false;
            }

        } catch (ClassNotFoundException exp) {
            System.out.println("DB Error: " + exp.getMessage());
        } catch (SQLException exp) {
            System.out.println("DB Error: " + exp.getMessage());
        } finally {

            closeDataBaseConnection();
            if (flag) {
                return true;
            } else {
                return false;
            }
        }
    }

    //Used by echeque server to check if things exist
    public boolean runDB(String databaseStat, int mode) {
        databaseMode = mode;
        boolean flag = false;

        try {
            connectToDataBase();
            System.out.println("DB State: You are connected to e-Cheque Bank DB");
            createStatment();
            System.out.println("DB State: You have created a statement");

            // run the specific sql statment
            executeSQLStatment(databaseStat, databaseMode);
            if (resultSet.next()) {
                flag = true;
            } else {
                flag = false;
            }

        } catch (ClassNotFoundException exp) {
            System.out.println("DB Error: " + exp.getMessage());
        } catch (SQLException exp) {
            System.out.println("DB Error: " + exp.getMessage());
        } finally {

            closeDataBaseConnection();
            if (flag) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Check if an account associated with a payer of a cheque has an account
     * with this institution. If it does the balance of the account is assigned
     * the parameter balance.
     *
     * @param cheque Cheque of instute.
     * @param balance Balance of account.
     * @return true if account existed, false otherwise.
     */
    public static boolean GetAccountFromCheque(ECheque cheque, double[] balance) {
        String withdrawStat = "Select balance from accounts where accountID =" + cheque.getaccountNumber();
        return getInstance().runDB(0, withdrawStat, balance);
    }

    /**
     * Check if a cheque has been cancelled by the payer. It is assumed that the
     * account does exist with this institute.
     *
     * @param cheque Cheque to check.
     * @return True if it was cancelled, false otherwise.
     */
    public static boolean IsChequeCancelled(ECheque cheque) {
        String withdrawStat = "Select * from cancelledCheque where accountID ='" + cheque.getaccountNumber() + "'and chequeID ='" + cheque.getchequeNumber() + "'";
        return getInstance().runDB(withdrawStat, 0);
    }

    //Checks if the cheque has already been deposited before. 
    //The assumption is made that the account does exist.
    /**
     * Check is a cheque has already been cashed. The assemption is made that
     * the account does exist with this institute.
     *
     * @param cheque Cheque to be checked.
     * @return True if the check has been cashed, false otherwise.
     */
    public static boolean HasChequeBeenCashed(ECheque cheque) {
        String withdrawStat = "Select * from eChequeOut where chequeID='" + cheque.getchequeNumber() + "'and accountID='" + cheque.getaccountNumber() + "'";
        return getInstance().runDB(withdrawStat, 0);
    }

    /**
     * Flag a cheque as cancelled so that it cannot be cashed. The is assumed
     * that the account exists with the institute.
     *
     * @param cheque cheque to be cancelled.
     * @return True if the operation was successful, false otherwise.
     */
    public static boolean CancelCheque(ECheque cheque) {
        String cancelChequeStat = "insert into cancelledCheque (accountID,chequeID) values('"
                + cheque.getaccountNumber() + "','" + cheque.getchequeNumber() + "')";
        return getInstance().runDB(1, cancelChequeStat);
    }

    /**
     * Deposit a cheque.
     *
     * @param cheque Cheque to be cashed.
     * @param account Account of the payee of the cheque
     * @return True if the operation was successful, false otherwise.
     */
    public static boolean DepositCheque(ECheque cheque, String account) {
        try {
            String withdrawStat = "Update accounts set balance = balance -" + cheque.getMoney() + "where accountID =" + cheque.getaccountNumber();
            getInstance().runDB(1, withdrawStat);
            withdrawStat = "Update accounts set balance = balance +" + cheque.getMoney() + "where accountID =" + account;
            getInstance().runDB(1, withdrawStat);

            // update cheque out and in table
            String cheqUpdate = "Insert into eChequeOut(chequeID, accountID, balance) values(" + "'" + cheque.getchequeNumber()
                    + "','" + cheque.getaccountNumber() + "'," + cheque.getMoney() + ")";
            getInstance().runDB(1, cheqUpdate);

            cheqUpdate = "Insert into eChequeIN(chequeID, accountID, balance) values(" + "'" + cheque.getchequeNumber()
                    + "','" + cheque.getaccountNumber() + "'," + cheque.getMoney() + ")";
            getInstance().runDB(1, cheqUpdate);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Register a client to this institute.
     *
     * @param client registration paperwork of client to be created in system.
     */
    public static void RegisterClient(EChequeRegisteration client) {
        String accountID = "'" + client.getAccountNumber() + "',";
        String cerit = "'" + client.getClientName() + "DC.edc" + "',";
        String clientName = "'" + client.getClientName() + "',";

        String registerStat = "insert into accounts(accountID,clientName,dcPath,balance) values("
                + accountID + clientName + cerit + 100000 + ")";

        // starting database
        getInstance().runDB(1, registerStat);
    }

}
