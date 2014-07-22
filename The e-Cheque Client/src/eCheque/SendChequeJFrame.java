/**
 * SendChequeJFrame.java
 *
 * Created: May 4, 2007, 4:29 AM by Saad
 * Last edit: July 21, 2014, 3:28 PM by Paul Moon
 */
package eCheque;

import javax.crypto.Cipher;
import javax.swing.*;
import java.io.*;
import java.security.Key;

/**
 * A Swing interface for sending eCheque to a receiver via PTP protocol or email.
 *
 * @author Saad
 * @version 0 JULY 2014
 */
public class SendChequeJFrame extends javax.swing.JFrame {
    private static int CLIENT_PORT = 8189;

    private String chequePath;
    private EChequeRegisteration user;

    //GEN-BEGIN:variables
    private javax.swing.JPanel emailTransferJPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel ptpTransferJPanel;
    private javax.swing.JLabel receiverEmailAddressLabel;
    private javax.swing.JTextField receiverEmailAddressTextField;
    private javax.swing.JLabel receiverURLAddressLabel;
    private javax.swing.JTextField receiverURLAddressTextField;
    private javax.swing.JButton selectChequeEmailButton;
    private javax.swing.JButton selectChequePTPButton;
    private javax.swing.JButton sendChequePTPButton;
    private javax.swing.JScrollPane statusScrollPane;
    private javax.swing.JTextArea statusTextArea;
    private javax.swing.JCheckBox timeoutEnabledCheckBox;
    private javax.swing.JButton zipSendChequeEmailButton;
    //GEN-END:variables

    public SendChequeJFrame(EChequeRegisteration user) {
        this.user = user;
        initComponents();
    }

    //GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ptpTransferJPanel = new javax.swing.JPanel();
        receiverURLAddressLabel = new javax.swing.JLabel();
        receiverURLAddressTextField = new javax.swing.JTextField();
        timeoutEnabledCheckBox = new javax.swing.JCheckBox();
        selectChequePTPButton = new javax.swing.JButton();
        sendChequePTPButton = new javax.swing.JButton();
        emailTransferJPanel = new javax.swing.JPanel();
        receiverEmailAddressLabel = new javax.swing.JLabel();
        receiverEmailAddressTextField = new javax.swing.JTextField();
        zipSendChequeEmailButton = new javax.swing.JButton();
        selectChequeEmailButton = new javax.swing.JButton();
        statusScrollPane = new javax.swing.JScrollPane();
        statusTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Send e-Cheque");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)));

        ptpTransferJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PTP Transfer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));

        receiverURLAddressLabel.setText("Receiver URL / IP:");

        timeoutEnabledCheckBox.setText("Enable timeout");
        timeoutEnabledCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        timeoutEnabledCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        selectChequePTPButton.setText("Select eCheque");
        selectChequePTPButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectChequePTPButtonMouseClicked(evt);
            }
        });

        sendChequePTPButton.setText("Send");
        sendChequePTPButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendChequePTPButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout ptpTransferJPanelLayout = new org.jdesktop.layout.GroupLayout(ptpTransferJPanel);
        ptpTransferJPanel.setLayout(ptpTransferJPanelLayout);
        ptpTransferJPanelLayout.setHorizontalGroup(
                ptpTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(ptpTransferJPanelLayout.createSequentialGroup()
                                .add(ptpTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, timeoutEnabledCheckBox, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, receiverURLAddressLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(ptpTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(ptpTransferJPanelLayout.createSequentialGroup()
                                                .add(0, 0, Short.MAX_VALUE)
                                                .add(selectChequePTPButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(sendChequePTPButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(receiverURLAddressTextField))
                                .addContainerGap())
        );
        ptpTransferJPanelLayout.setVerticalGroup(
                ptpTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(ptpTransferJPanelLayout.createSequentialGroup()
                                .add(ptpTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(receiverURLAddressTextField)
                                        .add(receiverURLAddressLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(ptpTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(selectChequePTPButton)
                                        .add(timeoutEnabledCheckBox)
                                        .add(sendChequePTPButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        emailTransferJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "E-mail Transfer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));

        receiverEmailAddressLabel.setText("Receiver e-mail address:");

        zipSendChequeEmailButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eCheque/resources/icon-zip.gif"))); // NOI18N
        zipSendChequeEmailButton.setText("ZIP & Send eCheque");

        selectChequeEmailButton.setText("Select eCheque");

        org.jdesktop.layout.GroupLayout emailTransferJPanelLayout = new org.jdesktop.layout.GroupLayout(emailTransferJPanel);
        emailTransferJPanel.setLayout(emailTransferJPanelLayout);
        emailTransferJPanelLayout.setHorizontalGroup(
                emailTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(emailTransferJPanelLayout.createSequentialGroup()
                                .add(emailTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(emailTransferJPanelLayout.createSequentialGroup()
                                                .add(5, 5, 5)
                                                .add(receiverEmailAddressLabel)
                                                .add(18, 18, 18)
                                                .add(receiverEmailAddressTextField))
                                        .add(emailTransferJPanelLayout.createSequentialGroup()
                                                .add(51, 51, 51)
                                                .add(selectChequeEmailButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(18, 18, 18)
                                                .add(zipSendChequeEmailButton)))
                                .addContainerGap())
        );
        emailTransferJPanelLayout.setVerticalGroup(
                emailTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(emailTransferJPanelLayout.createSequentialGroup()
                                .add(emailTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(receiverEmailAddressTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(receiverEmailAddressLabel))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(emailTransferJPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(zipSendChequeEmailButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(selectChequeEmailButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        statusTextArea.setColumns(20);
        statusTextArea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        statusTextArea.setRows(5);
        statusTextArea.setText(">>Status: disconnected");
        statusScrollPane.setViewportView(statusTextArea);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(ptpTransferJPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jPanel1Layout.createSequentialGroup()
                                                .add(emailTransferJPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(0, 0, Short.MAX_VALUE))
                                        .add(statusScrollPane))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .add(ptpTransferJPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(emailTransferJPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(statusScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(415, 379));
        setLocationRelativeTo(null);
    }//GEN-END:initComponents

    /**
     * Sends an eCheque via the PTP protocol.
     *
     * @param evt MouseEvent which triggered the method to be called.
     */
    private void sendChequePTPButtonMouseClicked(java.awt.event.MouseEvent evt) {
        AESCrypt aesKey128;
        Cipher aesCipher;
        Key sessionKey;
        String hostName, walletLocation, chequeName;
        InputStream in;
        OutputStream out;
        DigitalCertificate clientDC;
        Runnable threadingClient;
        Thread client;

        if (chequePath.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select an eCheque " +
                    "first.");
            return;
        }

        hostName = receiverURLAddressTextField.getText();
        if (hostName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please specify the URL/IP" +
                    "of the receiver.");
            return;
        }

        try {
            // Generate a session key
            aesKey128 = new AESCrypt();
            sessionKey = aesKey128.GenerateRandomAESKey();
            aesCipher = aesKey128.initializeCipher(sessionKey, 0);

            walletLocation = user.getEWalletLoaction();
            chequeName = new File(chequePath).getName();

            // Save the eCheque in the Out going folder
            in = new FileInputStream(chequePath);
            out = new FileOutputStream(walletLocation + "/Out going/" + chequeName);
            aesKey128.crypt(in, out, aesCipher);
            in.close();
            out.close();

            // Get the server's digital certificate
            clientDC = DigitalCertificateIO.readDigitalCertificate(
                    walletLocation + "/Security Tools/" + user.getClientName()
                            + "DigCert.edc");

            JOptionPane.showMessageDialog(null, "Starting client...");

            threadingClient = new EchequeClient(statusTextArea, clientDC,
                    sessionKey, walletLocation, chequePath, hostName,
                    CLIENT_PORT);

            client = new Thread(threadingClient);
            client.start();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "The selected file is not a valid eCheque.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "The eCheque was not found at this path: " + chequePath);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error while reading in the eCheque file.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "An error occurred while creating a session key. " +
                            "Please try again.");
        }
    }

    /**
     * Validates the selected eCheque file and sets the chequePath accordingly.
     *
     * @param evt MouseEvent which triggered the method to be called.
     */
    private void selectChequePTPButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String path = getFileLocation();
        File file = new File(path);

        if (!path.isEmpty()) {
            try {
                EChequeIO.readECheque(path);
                chequePath = path;
                JOptionPane.showMessageDialog(null, "eCheque " + file.getName()
                                + " successfully selected.", "Cheque Selected",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error while loading the " +
                        "cheque.", "System Error", JOptionPane.ERROR_MESSAGE);
                chequePath = "";
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Invalid eCheque file.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                chequePath = "";
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
}
