/*
 * AESCrypt.java
 *
 * Created on May 6, 2007, 2:29 PM
 *
 */

package eCheque;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;

/**
 * A class with multiple functionality for creating AES keys and
 * encrypting streams.
 * @author SAAD
 */
public class AESCrypt {
    
    /**
     * Creates a random secret AES key.
     * @return The secret key that was randomly generated
     * @throws Exception 
     */
    public SecretKey GenerateRandomAESKey() throws Exception {
        KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
        SecureRandom random =new SecureRandom();
        KeyGen.init(random);
        SecretKey key =KeyGen.generateKey();
        return key;
    }
  
    /**
     * Creates a cipher using the appropriate key, and mode. The mode
     * must fall between 0 and 1 for encrypt and decrypt mode.
     * @param key
     * @param mode
     * @return A cipher in the correct mode that corresponds to an AES cipher
     * @throws Exception, IllegalArgumentException if the mode is not in the
     * appropriate range
     */
    public Cipher initializeCipher(Key key, int mode) throws Exception {
        int CipherMode;
        Cipher cipherObj;    
        
        switch (mode) {
            case 0:
                CipherMode = Cipher.ENCRYPT_MODE;
                break;
            case 1:
                CipherMode = Cipher.DECRYPT_MODE;
                break;
            default:
                throw new IllegalArgumentException();
        }
        
        cipherObj = Cipher.getInstance("AES");
        cipherObj.init(CipherMode,key);
        return cipherObj;
    }  
    
    /**
     * Encrypts the input stream and writes it to the output stream given
     * the appropriate cipher.
     * @param in
     * @param out
     * @param cipherObj
     * @throws Exception 
     */
    public void crypt(InputStream in,OutputStream out,Cipher cipherObj) throws Exception {
        int blockSize = cipherObj.getBlockSize();
        int outputSize = cipherObj.getOutputSize(blockSize);
        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];
        int inLength = 0;
        boolean more = true;

        while (more)
        {
            inLength = in.read(inBytes);
            if(inLength == blockSize)
            {
                int outLength = cipherObj.update(inBytes,0,blockSize,outBytes);
                out.write(outBytes,0,outLength);
            }
            else { 
                more = false;
            }
        }
        if (inLength > 0) {
            outBytes = cipherObj.doFinal(inBytes,0,inLength);
        } 
        else {
            outBytes = cipherObj.doFinal();
        }
        out.write(outBytes);
    }

    /**
     * Creates an AES key given a password as the seed.
     * @param pass
     * @return a secret key specification object in AES form.
     */
    public SecretKeySpec initializeAESKeyByPassword(String pass) {
        SecretKeySpec aesKey;
        byte[] KeyData = pass.getBytes();
        aesKey = new SecretKeySpec(KeyData,"AES");
        return aesKey;
    }

}