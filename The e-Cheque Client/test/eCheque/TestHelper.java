/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eCheque;

import java.util.Random;

/**
 *
 * @author Paul
 */
public class TestHelper {
    
    public static Random rand = new Random();
    public static final int MAX_TEST_STRING_LENGTH = 100;
    
    private static final String CharSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    public static String GenerateRandomAlphaNumString()
    {
        return GenerateRandomAlphaNumString(MAX_TEST_STRING_LENGTH);
    }
    
    public static String GenerateRandomAlphaNumString(int length) {
        return new String(GenerateRandomAlphaNumSequence(length));
    }

    public static byte[] GenerateRandomAlphaNumByteSequence()
    {
        return GenerateRandomAlphaNumByteSequence(MAX_TEST_STRING_LENGTH);
    }
    
    public static byte[] GenerateRandomAlphaNumByteSequence(int length) {
        char[] seq = GenerateRandomAlphaNumSequence(length);
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = (byte) seq[i];
        }
        return result;
    }

    public static char[] GenerateRandomAlphaNumSequence()
    {
        return GenerateRandomAlphaNumSequence(MAX_TEST_STRING_LENGTH);
    }
    
    public static char[] GenerateRandomAlphaNumSequence(int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = CharSet.charAt(rand.nextInt(CharSet.length()));
        }
        return text;
    }
    
    
}
