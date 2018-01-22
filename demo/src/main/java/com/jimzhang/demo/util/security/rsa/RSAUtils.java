package com.jimzhang.demo.util.security.rsa;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA公钥/私钥/签名工具包
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 */
public class RSAUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /** 
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /** 
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /** 
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /** 
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** 
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public RSAUtils() throws Exception {
    }

    /** 
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /** 
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(String data, String privateKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initSign(privateK);
        signature.update(data.getBytes());

        return Base64.encodeBase64String(signature.sign());
    }

    /** 
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(String data, String publicKey, String sign)
            throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        PublicKey publicK = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initVerify(publicK);
        signature.update(data.getBytes("utf-8"));

        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, RSAPublicKey publicKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 模长
        int key_len = publicKey.getModulus().bitLength() / 8;
        // 加密数据长度 <= 模长-11
        String[] datas = splitString(data, key_len - 11);
        String mi = "";
        //如果明文长度大于模长-11则要分组加密
        for (String s : datas) {
            mi += bcd2Str(cipher.doFinal(s.getBytes()));
        }
        return mi;
    }

    /**
     * 私钥解密
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //模长
        int key_len = privateKey.getModulus().bitLength() / 8;
        byte[] bytes = data.getBytes();
        byte[] bcd = ASCII_To_BCD(bytes, bytes.length);
        System.err.println(bcd.length);
        //如果密文长度大于模长则要分组解密
        String ming = "";
        byte[][] arrays = splitArray(bcd, key_len);
        for(byte[] arr : arrays){
            ming += new String(cipher.doFinal(arr));
        }
        return ming;
    }

    /**
     * ASCII码转BCD码
     *
     */
    public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++) {
            bcd[i] = asc_to_bcd(ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }
    public static byte asc_to_bcd(byte asc) {
        byte bcd;

        if ((asc >= '0') && (asc <= '9'))
            bcd = (byte) (asc - '0');
        else if ((asc >= 'A') && (asc <= 'F'))
            bcd = (byte) (asc - 'A' + 10);
        else if ((asc >= 'a') && (asc <= 'f'))
            bcd = (byte) (asc - 'a' + 10);
        else
            bcd = (byte) (asc - 48);
        return bcd;
    }
    /**
     * BCD转字符串
     */
    public static String bcd2Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }
    /**
     * 拆分字符串
     */
    public static String[] splitString(String string, int len) {
        int x = string.length() / len;
        int y = string.length() % len;
        int z = 0;
        if (y != 0) {
            z = 1;
        }
        String[] strings = new String[x + z];
        String str = "";
        for (int i=0; i<x+z; i++) {
            if (i==x+z-1 && y!=0) {
                str = string.substring(i*len, i*len+y);
            }else{
                str = string.substring(i*len, i*len+len);
            }
            strings[i] = str;
        }
        return strings;
    }
    /**
     *拆分数组
     */
    public static byte[][] splitArray(byte[] data,int len){
        int x = data.length / len;
        int y = data.length % len;
        int z = 0;
        if(y!=0){
            z = 1;
        }
        byte[][] arrays = new byte[x+z][];
        byte[] arr;
        for(int i=0; i<x+z; i++){
            arr = new byte[len];
            if(i==x+z-1 && y!=0){
                System.arraycopy(data, i*len, arr, 0, y);
            }else{
                System.arraycopy(data, i*len, arr, 0, len);
            }
            arrays[i] = arr;
        }
        return arrays;
    }


    /** 
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /** 
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }


    public static void main(String args[]) throws Exception {

        //生成rsa公私钥
//        Map<String, Object> rsaMap = RSAUtils.genKeyPair();
//        String privatekey = RSAUtils.getPrivateKey(rsaMap);
//        String publickey = RSAUtils.getPublicKey(rsaMap);
//        System.out.println(privatekey);
//        System.out.println("============");
//        System.out.println(publickey);


        String rsaPrikeyA = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCzeYS5j1dXDStgxJ1Xg38mE+3TyvKvnSYH730BhDSjHLQR3kne8U5zj3edBQp2Mdes1J1gWfgxzAWZZGjASskUAUrdqviNYy7gT856TZlzEhIVeuAKgO4EEvRwKFm1Rmxq4kjST9MPlEcTlI/Z76Zz1MmxIATV9xDPz8LN792Md+UAfOyIj5O2ukPTZ8OXE7ZysGacvhs6sJvvGzamL+63D6qGSWbDa68TRQcNDN0pr3+Nfn5Xg71jnB1xSBGkS7XDyQ5DcorPzrIJ8nZcL/0cOcN0OhcXLQWDncDlu87RhP8Nt2u8cxT3DuxbhK/lnutRm9+922+Hx1VCPlv/QkFVAgMBAAECggEAfTX4LOUeD/N2IerJwrZQzKX2tF3QdkhysfKZCafMeUebMSQd8VX0X5bl4jwUEmWvjDpBGWlrSIKIHX35vr3uDvDf5EpVYX6iw4eqC5V7ytX+m9G/4FQWuezncCAWhet91AvOtjtOin3uIHDy8x7Ix+LaW6J0/TmDFxhhVf9UV5xWNq0sg3+qwjyLJgz6vb9qGJIeTlVukk767QizpT2RQkq6D2KcZAbYSFEhvWLFT/Fm2tC8SmigtEAaYWRqfjZVbgDzJE+6Qp9tULiOsQxDPPHc0WGjqSRXrt6aIS0ErPIwokP+5rZP9uPN1DGJZmyFRkex/ZTQ7kk3vPaJSdghQQKBgQD+m5pVNybvhLR7+KC5G1HPSTSu0iGYrh72VLBxX2F5aJxeR3RJP0EwsFTKvyotibGz9x9/4V6ehYZJc2PjXuAmgjq+kBMAwT4ZoAwPEB5UiokUJDEqHMK2pp7H3HUtWAjED9tv+QZfFeNOSQ0I2SoOcdYIJrNqTuvStdvAYoYNOQKBgQC0dL69QOEb9bDp5L48HABjqafYQ3DA/cAwxfI6+BO0XSGVT/7UWv6se4tALQcCQHeupncNcwm5SNfxnRRG+1zHtDBXcVVEdWvVErCZkVLuP4jgegC5DFUMpzt1mvIY2LtHNMsw4hyj44A58yk5Jo9KwbYviIQQ5UNCo8bI5TCw/QKBgEKWh4KJ7hDucBWMLNy8nGTODI08P7UitM5Pz+WWwHN0PJEbtvoBJReKxmJQJMPMI0Gh/AcB9jEXUsua9iDdC1qG7L4yCAZGg+tHxF+cI5KQuNEftgwBgZsrcJNswOifAYvlacqbImNz4gLzcAlEYo9sjkGLfZjsExkEnvhtzAlhAoGAAilX04qAZ8ZUnSiZQr0PICJ7GTMNmbIwaCUm1p/8FG/9I/22TrLcVBKSw20GpXdCN7xUtruHBUojFAjOol8GaHn3DkUUQWKqrP2xUdWUybfHGvE7KvxD1If4RVtaxQNrktnGCUrcDvZ9pbQM7VtR4hJZ7U3UTyN/3794dRWRtL0CgYAyQHIESEivRlkV2Q663ZqskqGQgPX2ZIqB4AbJif4oPrTZ1Fy1IcAUS5fHLuCgMhdisJ5Jv8C6gUrH0IA6KAAZ7p/KuRccAR9mGYecrhmuA9WA8psOhSxEg7yMTMCp5NR3PqgiINvcteFMdcBqAYmtsTLOz8QEuUe+69VNAVyeVg==";
        String rsaPubkeyA = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs3mEuY9XVw0rYMSdV4N/JhPt08ryr50mB+99AYQ0oxy0Ed5J3vFOc493nQUKdjHXrNSdYFn4McwFmWRowErJFAFK3ar4jWMu4E/Oek2ZcxISFXrgCoDuBBL0cChZtUZsauJI0k/TD5RHE5SP2e+mc9TJsSAE1fcQz8/Cze/djHflAHzsiI+TtrpD02fDlxO2crBmnL4bOrCb7xs2pi/utw+qhklmw2uvE0UHDQzdKa9/jX5+V4O9Y5wdcUgRpEu1w8kOQ3KKz86yCfJ2XC/9HDnDdDoXFy0Fg53A5bvO0YT/DbdrvHMU9w7sW4Sv5Z7rUZvfvdtvh8dVQj5b/0JBVQIDAQAB";
        String signEncode = RSAUtils.sign("000",rsaPrikeyA);
        System.out.println("签名sign："+signEncode);
        String rsaPrikeyB = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOS+JMuKCktEydXe6Y/iwAltU1n/BkXhWFB220oi65Q6vgnXYv7pVCvkicKJS45xnS9gSTkiYyPyWkxTaG6ZxCNI7ZaVlpu+3ODfts/OAo1ehU4ilnQZLLtZAkREdFXyIZ92cXf0rDI9+O6dLlHskCpXLRpTmAbvhyxk7uVJfqR5JgpGWb81skZde8pDSWx+OXNKjMDUqKAHvYepyzznpQlK3PLTxwbmm3UcCTvHi1ocw6Dr6InZY1IQZTDN8WnNwVTj2doNfduT7M3Mr0KlHR+NTDwHK+xoXzkt6cUjCgq5Gl5ZPwACojWQ19BuMFq2ORibmyL7ILW7wwAJWZznA7AgMBAAECggEAVzHazwaHscWl+PxZO7jsf8PtjHmgVfBtIWJG8Fw9Y1s7VF1I0eBR6F8voTkgwRkj8ui8Ly1saHfXels6qXs3PD52lnT+zFLTSuO4gLWIKp3BS1p24oYFDP0KAZ8xSSodfGnk6zoeNuuhjzJq/+lYZV26uQqU7MWTSsmIDnUZzf35O/yHfcDrKIGRQasHdoeQeoJU6nEC5T7HIZuoRo7WwZJ+x3CjbA1B2M8W2aTkG0Ky79+CRF67C89vVLsjtZQsDLPZAmiIbUpRGhnHr/mhfcZ3M+198fqBcbwrtd6R+eWcYVadMn7DxNEqGFCTQCkBKO3xAeKd9g//eStNhs7NEQKBgQDKdumpxR7aSCLWgdkRcDhxNaJBxGgExVYekXLKxOiGlQ3OrlBGTd4btklPr1g/kM+GKO2Cxrw25KfI/Z0pLrBn+Frqt5XOlBuMjFCGIjQusnR3395Ep+5rOisZ7MgaNwGej3a0fkgSuBSLMIqVRV0u3E0RO86x2WT+ZwIgZ2uuuQKBgQCz7B10f6DlCRVvEeeUiHzaE8h52w4ot55fCM8PkYPemrIb0mn1A9pFQfCvTuNi9ErWHitmbhrGA1fv/SnXa1nM7PgUxqG3FmJttysZ6fQjkrheEscH+n/h/PxOzIEK+uGa3nhREMLBLcwUVtVv3ryK+GSynKriUbW8StqGmVP8kwKBgBnAplgByrrZEhVWztV5ALTlrY3P/asdrqaUuZUxFqKHswnnAi7vGKFNvmV0WDU9pifabNuFi8svqmfWWncyiR2waWARmex8ykRGVLe7AgguisH0p8Dz20+oRnM1uv4aoQoaFW8/4MsAvxw1QGtcN04Ol37prJDSphX15D9a6EeBAoGAbXAmLnFfNVEEJ44VdWRbxO4Zl/0lLKEbhKTABbE2K9WelouzBWR1hIDqXEe4ybzcNEcfkOtR/7WS7Jfptm1Grv5Im6KCRPIppqCuZ9HFPUoGOLplM7YryRLviSKdL0eQqHm/Z1exjn1Dz+9NXgF2X3VdEsN2sbiPuVsydoKW8l8CgYEAxzS922+Pi4yp9vbT/PibH1jKND7w5u1XkC4TW1zkVZ8pkvjsyiQg789LHLOl6zg4zMioaO0nQtEhtK6PAhcgfqW/45iSZUBNs+jGbvoFrMEjOlZ4ch8eLNcZsLQyf4cywE+n9YaQ2yNQu+2vztx7Mff4z2H8QaQW/uxu7ii7nKY=";
        String rsaPubkeyB = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjkviTLigpLRMnV3umP4sAJbVNZ/wZF4VhQdttKIuuUOr4J12L+6VQr5InCiUuOcZ0vYEk5ImMj8lpMU2humcQjSO2WlZabvtzg37bPzgKNXoVOIpZ0GSy7WQJERHRV8iGfdnF39KwyPfjunS5R7JAqVy0aU5gG74csZO7lSX6keSYKRlm/NbJGXXvKQ0lsfjlzSozA1KigB72Hqcs856UJStzy08cG5pt1HAk7x4taHMOg6+iJ2WNSEGUwzfFpzcFU49naDX3bk+zNzK9CpR0fjUw8ByvsaF85LenFIwoKuRpeWT8AAqI1kNfQbjBatjkYm5si+yC1u8MACVmc5wOwIDAQAB";
        boolean verify = RSAUtils.verify("000", rsaPubkeyA, signEncode);
        System.out.println(verify);
    }

}
