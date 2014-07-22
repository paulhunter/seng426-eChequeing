/**
 * EBankingJFrame.java
 *
 * Created: June 11, 2007, 6:52 PM by Saad
 * Last edit: July 21, 2014, 3:28 PM by Paul Moon
 */
package eCheque;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * A Swing interface for sending deposit/cancel requests to a bank server.
 *
 * @author Saad
 * @version 0 JULY 2014
 */
public class EBankingJFrame extends javax.swing.JFrame {
    private static int CLIENT_PORT = 8189;

    private EChequeRegisteration user;
    private ECheque cheque;

    //GEN-BEGIN:variables
    private javax.swing.JLabel bankAddressLabel;
    private javax.swing.JTextField bankAddressTextField;
    private javax.swing.JCheckBox cancelCheckBox;
    private javax.swing.JPanel chequeJPanel;
    private javax.swing.JCheckBox depositCheckBox;
    private javax.swing.JButton loadChequeButton;
    private javax.swing.JPanel mainJPanel;
    private javax.swing.JLabel operationTypeLabel;
    private javax.swing.JLabel selectChequeLabel;
    private javax.swing.JButton submitButton;
    //GEN-END:variables

    public EBankingJFrame(EChequeRegisteration user) {
        initComponents();
        this.user = user;
    }

    /**
     * Initializes Swing components.
     */
    //GEN-BEGIN:initComponents
    private void initComponents() {
        mainJPanel = new javax.swing.JPanel();
        chequeJPanel = new javax.swing.JPanel();
        bankAddressLabel = new javax.swing.JLabel();
        bankAddressTextField = new javax.swing.JTextField();
        selectChequeLabel = new javax.swing.JLabel();
        loadChequeButton = new javax.swing.JButton();
        operationTypeLabel = new javax.swing.JLabel();
        depositCheckBox = new javax.swing.JCheckBox();
        cancelCheckBox = new javax.swing.JCheckBox();
        submitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("e-Banking");
        setResizable(false);

        mainJPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        chequeJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Submit a banking request."));

        bankAddressLabel.setText("Bank URL/IP:");

        selectChequeLabel.setText("Select e-Cheque:");

        loadChequeButton.setText("Load");
        loadChequeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadChequeButtonActionPerformed(evt);
            }
        });

        operationTypeLabel.setText("Operation Type:");

        depositCheckBox.setText("Deposit");
        depositCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        depositCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        depositCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositCheckBoxActionPerformed(evt);
            }
        });

        cancelCheckBox.setText("Cancel");
        cancelCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cancelCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cancelCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelCheckBoxActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout chequeJPanelLayout = new org.jdesktop.layout.GroupLayout(chequeJPanel);
        chequeJPanel.setLayout(chequeJPanelLayout);
        chequeJPanelLayout.setHorizontalGroup(
                chequeJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(chequeJPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .add(chequeJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(chequeJPanelLayout.createSequentialGroup()
                                                .add(selectChequeLabel)
                                                .add(47, 47, 47)
                                                .add(loadChequeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(0, 0, Short.MAX_VALUE))
                                        .add(chequeJPanelLayout.createSequentialGroup()
                                                .add(chequeJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                        .add(bankAddressLabel)
                                                        .add(operationTypeLabel))
                                                .add(8, 8, 8)
                                                .add(chequeJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                        .add(bankAddressTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                                        .add(chequeJPanelLayout.createSequentialGroup()
                                                                .add(depositCheckBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                                .add(cancelCheckBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())
        );
        chequeJPanelLayout.setVerticalGroup(
                chequeJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(chequeJPanelLayout.createSequentialGroup()
                                .add(chequeJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(bankAddressLabel)
                                        .add(bankAddressTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(16, 16, 16)
                                .add(chequeJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(selectChequeLabel)
                                        .add(loadChequeButton))
                                .add(15, 15, 15)
                                .add(chequeJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(operationTypeLabel)
                                        .add(depositCheckBox)
                                        .add(cancelCheckBox))
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        submitButton.setText("Submit Request");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout mainJPanelLayout = new org.jdesktop.layout.GroupLayout(mainJPanel);
        mainJPanel.setLayout(mainJPanelLayout);
        mainJPanelLayout.setHorizontalGroup(
                mainJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(mainJPanelLayout.createSequentialGroup()
                                .add(18, 18, 18)
                                .add(chequeJPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(13, Short.MAX_VALUE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, mainJPanelLayout.createSequentialGroup()
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(submitButton)
                                .add(96, 96, 96))
        );
        mainJPanelLayout.setVerticalGroup(
                mainJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(mainJPanelLayout.createSequentialGroup()
                                .add(26, 26, 26)
                                .add(chequeJPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(submitButton)
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        chequeJPanel.getAccessibleContext().setAccessibleName("Enter information to submit banking request");
        chequeJPanel.getAccessibleContext().setAccessibleDescription("");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(mainJPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(mainJPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(372, 317));
        setLocationRelativeTo(null);
    }//GEN-END:initComponents


    private void cancelCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        depositCheckBox.setSelected(false);
    }

    private void depositCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        cancelCheckBox.setSelected(false);
    }

    /** 
     * Read an eCheque from the file system. 
     * 
     * @param evt ActionEvent which triggered the submitButton
     */
    private void loadChequeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String chequePath = getFileLocation();
        File file = new File(chequePath);

        if (chequePath.length() != 0) {
            try {
                cheque = EChequeIO.readECheque(chequePath);
                JOptionPane.showMessageDialog(null, "eCheque " + file.getName()
                        + " successfully selected.", "Cheque Selected",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error while loading the " +
                        "cheque.", "System Error", JOptionPane.ERROR_MESSAGE);
                cheque = null;
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Invalid eCheque file.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                cheque = null;
            }
        }
    }

    /**
     * Opens a file chooser window and returns the path of a selected eCheque.
     *
     * @return file path to the eCheque
     */
    private String getFileLocation() {
        JFileChooser fileChooser = new JFileChooser();
        File file;
        int result;

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return "";
        }

        file = fileChooser.getSelectedFile();
        if (file == null || file.getName().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid File Name",
                    "Invalid File Name", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        return file.getPath();
    }

    /** 
     * Submit the deposit/cancel eCheque request to the bank server. 
     * 
     * @param evt ActionEvent which triggered the submitButton.
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String bankAddress;
        Runnable client;
        Thread clientThread;

        bankAddress = bankAddressTextField.getText();
        if (bankAddress.length() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Please enter the bank URL or IP.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cheque == null) {
            JOptionPane.showMessageDialog(null,
                    "Please load an e-Cheque first.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (depositCheckBox.isSelected()) {
            client = new EchequeClient(CLIENT_PORT, 1, bankAddress, user, cheque);
            clientThread = new Thread(client);
            clientThread.start();
        } else if (cancelCheckBox.isSelected()) {
            client = new EchequeClient(CLIENT_PORT, 2, bankAddress, user, cheque);
            clientThread = new Thread(client);
            clientThread.start();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Please select an operation type.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
