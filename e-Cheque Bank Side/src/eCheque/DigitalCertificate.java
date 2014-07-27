/*
 * DigitalCertificate.java
 *
 * Created on March 28, 2007, 3:26 PM
 * Last edit on 2014-JULY-20
 * 
 */
package eCheque;

import java.io.Serializable;
import java.security.*;

/**
 * The DigitalCertificate is a data structure used to verify a client or bank
 * connection. It contains important information for encryption and validation
 * of the certificates between the two agents.
 *
 * @author Abu^S3ooD 
 * Last Edit: Paul Hunter
 */
public class DigitalCertificate implements Serializable {

    /**
     * Holder's Name
     */
    private String holderName;

    /**
     * Subject of Certificate
     */
    private String subject;

    /**
     * Issueing Authority
     */
    private String issuer;

    /**
     * Unique Serial Number
     */
    private String serialNumber;

    /**
     * Start of Valid period
     */
    private String validFrom;

    /**
     * End of valid period
     */
    private String validTo;

    /**
     * Public Key of Holder
     */
    private PublicKey publicKey;

    /**
     * Issueing Authority's Signature
     */
    private byte[] issuerSignature;

    /**
     * Creates a new instance of certificate
     */
    public DigitalCertificate() {
    }

    /**
     * Set the Holder Name field of the certificate.
     *
     * @param x Desired value.
     */
    public void setHolderName(String x) {
        holderName = x;
    }

    /**
     * Retrieve the current Holder name from the certificate.
     *
     * @return current value.
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Set the Subject field of the certificate.
     *
     * @param x Desired value.
     */
    public void setSubject(String x) {
        subject = x;
    }

    /**
     * Retrieve the current Subject of the certificate.
     *
     * @return current value.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set the Issuer field of the certificate.
     *
     * @param x Desired value.
     */
    public void setIssuer(String x) {
        issuer = x;
    }

    /**
     * Retrieve the current Issuer of the certificate.
     *
     * @return current value.
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Set the Serial Number of the certificate.
     *
     * @param x Desired value.
     */
    public void setSerialNumber(String x) {
        serialNumber = x;
    }

    /**
     * Retrieve the current Serial Number of the certificate.
     *
     * @return current value.
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Set the Valid From date of the certificate.
     *
     * @param x desired value.
     */
    public void setValidFrom(String x) {
        validFrom = x;
    }

    /**
     * Retrieve the current Valid From date of the certificate.
     *
     * @return current value.
     */
    public String getValidFrom() {
        return validFrom;
    }

    /**
     * Set the Valid To date of the certificate.
     *
     * @param x desired value.
     */
    public void setValidTo(String x) {
        validTo = x;
    }

    /**
     * Retrieve the current Valid To date of the certificate.
     *
     * @return current value.
     */
    public String getValidTo() {
        return validTo;
    }

    /**
     * Set the PublicK Key of the certificate.
     *
     * @param x desired value.
     */
    public void setPublicKey(PublicKey x) {
        publicKey = x;
    }

    /**
     * Retrieve the current Public Key of the certificate.
     *
     * @return current value.
     */
    public PublicKey getpublicKey() {
        return publicKey;
    }

    /**
     * Set the Issuer's Signature on the Certificate.
     *
     * @param x desired value.
     */
    public void setIssuerSignature(byte[] x) {
        issuerSignature = x;
    }

    /**
     * Retrieve the current Issuer's Signature of the certificate.
     *
     * @return current value.
     */
    public byte[] getIssuerSignature() {
        return issuerSignature;
    }
}
