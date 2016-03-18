/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.secure;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Владислав
 */
public class Encryption {
    public static String md5Custom(String st) {
    MessageDigest messageDigest = null;
    byte[] digest = new byte[0];
 
    try {
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(st.getBytes());
        digest = messageDigest.digest();
    } catch (NoSuchAlgorithmException e) {
        // тут можно обработать ошибку
        // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
        e.printStackTrace();
    }
 
    BigInteger bigInt = new BigInteger(1, digest);
    String md5Hex = bigInt.toString(16);
 
    while( md5Hex.length() < 32 ){
        md5Hex = "0" + md5Hex;
    }
 
    return md5Hex;
}
}
