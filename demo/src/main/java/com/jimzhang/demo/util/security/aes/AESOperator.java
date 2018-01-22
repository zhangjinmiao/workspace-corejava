package com.jimzhang.demo.util.security.aes;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2017-12-13 18:15
 */
public class AESOperator {
    /*
    * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
    */
    private String sKey = "g@q35425ERfgdfbh";
    private String ivParameter = "1234567887654321";

    public static String UTF_8 = "UTF-8";
    public static String ASCII = "ASCII";
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式 ECB/填充方式 PKCS5Padding
     */
    private static final String ALGORITHM_ECB = "AES/ECB/PKCS5Padding";
    /**
     * 加解密算法/工作模式 CBC/填充方式 PKCS5Padding
     */
    private static final String ALGORITHM_CBC = "AES/CBC/PKCS5Padding";

    private static AESOperator instance = null;
    private AESOperator() { }

    public static AESOperator getInstance() {
        if (instance == null)
            instance = new AESOperator();
        return instance;
    }

    /**
     * CBC 加密
     * @param encData       明文数据
     * @param secretKey     加密key
     * @param vector        使用CBC模式，需要一个向量iv,最少：16字节长度
     * @return
     * @throws Exception
     */
    public static String Encrypt(String encData, String secretKey, String vector) throws Exception {
        if (secretKey == null) {
            return null;
        }
        if (secretKey.length() != 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance(ALGORITHM_CBC);
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes(UTF_8));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }


    /**
     *  ECB 加密
     * @param sSrc  明文数据
     * @return
     * @throws Exception
     */
    public String encrypt(String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_ECB);
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(UTF_8));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }

    /**
     * ECB 解密
     * @param sSrc
     * @return
     * @throws Exception
     */
    public String decrypt(String sSrc) throws Exception {
        try {
            byte[] raw = sKey.getBytes(ASCII);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM_ECB);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, UTF_8);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * CBC 解密
     * @param sSrc  密文数据
     * @param key   加密时用的key
     * @param ivs   使用CBC模式，需要一个向量iv,最少：16字节长度
     * @return
     * @throws Exception
     */
    public String decrypt(String sSrc, String key, String ivs) throws Exception {
        try {
            byte[] raw = key.getBytes(ASCII);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM_CBC);
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, UTF_8);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }
        return strBuf.toString();
    }

    public static void main(String[] args) throws Exception {
        // 需要加密的字串
        String cSrc = "[{\"request_no\":\"1001\",\"service_code\":\"FS0001\",\"contract_id\":\"100002\",\"order_id\":\"0\",\"phone_id\":\"13913996922\",\"plat_offer_id\":\"100094\",\"channel_id\":\"1\",\"activity_id\":\"100045\"}]";

        // 加密
        long lStart = System.currentTimeMillis();
        String enString = AESOperator.getInstance().encrypt(cSrc);
        System.out.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();
        String DeString = AESOperator.getInstance().decrypt(enString);
        System.out.println("解密后的字串是：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
    }
}
