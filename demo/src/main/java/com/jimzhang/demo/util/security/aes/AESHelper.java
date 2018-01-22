package com.jimzhang.demo.util.security.aes;

import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by liyz on 2017/9/23.
 */
public class AESHelper {

    public static String UTF_8 = "UTF-8";

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";


    /**
     * AES解密
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptData(String content,String key){
        //SecretKeySpec类是KeySpec接口的实现类,用于构建秘密密钥规范
        try {
            SecretKeySpec speckey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
            cipher.init(Cipher.DECRYPT_MODE, speckey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(content)), UTF_8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES 加密
     * @param content   明文数据
     * @param key       16位随机数
     * @return  base64编码的数据
     */
    public static String encryptData(String content, String key) {

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
            // 指定加密的算法、工作模式和填充方式
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(content.getBytes(UTF_8));
            return new BASE64Encoder().encode(encryptedBytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String key = "g@q35425ERfgdfbh";

        String encodeStr = AESHelper.encryptData("000", key);
        System.out.println("加密后:" + encodeStr);

        String encode =  AESHelper.decryptData(encodeStr,key);
        System.out.println("解密后：" + encode);

    }
}
