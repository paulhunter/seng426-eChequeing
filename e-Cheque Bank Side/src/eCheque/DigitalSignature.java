/**
 * DigitalSignature.java
 *
 * Created: March 28, 2007, 4:03 PM by Basel
 * Last edit: July 21, 2014, 12:16 PM by Paul Moon
 */
package eCheque;

import java.security.*;

/**
 * DigitalSignature provides helper methods for signing and verifying digital
 * signatures.
 *
 * @version 0 JULY 2014
 * @author Basel
 */
public class DigitalSignature {
    private static String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * Signs data using a SHA1withRSA algorithm.
     * @param data data to be signed
     * @param privateKey private key for signing
     * @throws InvalidKeyException if the private key is invalid
     * @throws NoSuchAlgorithmException if no Signature implementation supports
     *     the specified algorithm
     * @throws SignatureException if the signature object was not initialized
     *     properly, or the algorithm cannot process the data
     * @return byte array of digitally signed data
     */
    public static byte[] signDS(String data, PrivateKey privateKey)
            throws InvalidKeyException,
                   NoSuchAlgorithmException,
                   SignatureException {

        Signature signer = Signature.getInstance(SIGNATURE_ALGORITHM);

        if (data == null || privateKey == null) {
            return null;
        }

        signer.initSign(privateKey);
        signer.update(data.getBytes());
        return signer.sign();
    }

    /**
     * Verifies signed data against a given signature.
     * @param signature digitally signed data
     * @param data data before it was signed
     * @param publicKey public key for verifying
     * @throws InvalidKeyException if the public key is invalid
     * @throws NoSuchAlgorithmException if no Signature implementation supports
     *     the specified algorithm
     * @throws SignatureException if the signature object was not initialized
     *     properly, or the algorithm cannot process the data
     * @return true only if the signature is valid
     */
    public static boolean verifyDS(byte[] signature, String data,
                                   PublicKey publicKey)
            throws InvalidKeyException,
                   NoSuchAlgorithmException,
                   SignatureException {

        Signature verifier = Signature.getInstance(SIGNATURE_ALGORITHM);

        if (data == null || publicKey == null) {
            return false;
        }

        verifier.initVerify(publicKey);
        verifier.update(data.getBytes());
        return verifier.verify(signature);
    }
}
