package com.jimzhang.demo.util.security.cipher;

import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by michaelli on 2017/7/6.
 */
public class FuluAESUtils {


    public static byte[] encryptAES(String content, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        byte[] byteContent = content.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

        // 指定加密的算法、工作模式和填充方式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = cipher.doFinal(byteContent);
//        return new BASE64Encoder().encode(encryptedBytes);
        return encryptedBytes;
    }


    public static byte[] decryptAES(String content, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        // base64 解码
        byte[] encryptedBytes = Base64.getDecoder().decode(content);

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] result = cipher.doFinal(encryptedBytes);
//        return new String(result, "UTF-8");
        return result;
    }


    public static void main(String[] args) throws Exception {
        String key = "g@q35425ERfgdfbh";

        /*String encodeData = "nRf2Ofnr8iztxemjXuXLceuhC05ZzLuYDAs83gVYn+4H3rECjGuzAdLFHoXHicy9ENgkjfSkGIjdre5I3BbYJs304WfDv6RAywZJDNbcQF8ngAmeieI49xDO5jGUyQ4XEBy4LBqZQa7n0Kbf2cgEmG6kzlaZohk/tWkT87c5blhc6M9iiMkSaQJaESKSib8kkv2nM79f6ClPY9b8VgkkatiN9xYJYowmx/2vi4Ww+wKTpERVRYhYSIk+3H80ZsngXfVcoYoK/VjpkBTdHzNa9sfovi2oPFkzsKwqGS5aVLtzlXCK0wipA2z0WCiSiRwtGmdk7pT0I6OZQMS21jweNUVPjNp3avq2/MOrnQ49M6y4CJNeKq2vlnazx7j+XRjW51wXKUVUgB3k+LQVd/VtTLv8OhS5nvCWQYtBqDUTMIRXxo1OcUbDIE3WTjCk6RmNRqX9sVMhc4gFZOB0aRtPMqsawWkbFxudE6WZerpSpoEXLJQe0AyWquEM/MpkzcP3Bx6Pq3lisTqME7EK8ivSoK9c2y7iYBxecw4IMYfXbVXW3r08S60kb43NJW21YL9i";
        String content = new String(FuluAESUtils.decryptAES(encodeData, key), "utf-8");
        System.out.println("content:"+content);*/

        String encodeStr = new BASE64Encoder().encode(FuluAESUtils.encryptAES("000", key));
        System.out.println("加密后:"+ encodeStr);

        String encode = new String(FuluAESUtils.decryptAES(encodeStr, key), "utf-8");
        System.out.println("解密后：" + encode);

    }
}
