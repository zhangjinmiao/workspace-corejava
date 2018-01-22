package com.jimzhang.demo.util.security.rsa;

import com.jimzhang.demo.util.security.beas64.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description ☆ RSA签名 ☆
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2017-12-20 18:15
 */
public class RSASignature {
    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * RSA签名
     *
     * @param content    待签名数据
     * @param privateKey 商户私钥
     * @param encode     字符集编码
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String encode) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));

            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(encode));

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes());
            byte[] signed = signature.sign();
            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA验签名检查
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 分配给开发商公钥
     * @param encode    字符集编码
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign, String publicKey, String encode) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(encode));

            boolean bverify = signature.verify(Base64.decode(sign));
            return bverify;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean doCheck(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes());

            boolean bverify = signature.verify(Base64.decode(sign));
            return bverify;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void main(String[] args) {
//        String signData = "CKq28WySiDU4kjIPwWVfrXPqToHeDMFw2UBxx1IaDR1Y+Rlbu/gMKf4ByMgK8C3+rRFnBZ1SC0Tof5hDYLr7xen4AVq2zbZiQwQSz/IoP84l0R+24EBDejIXoy9IeAZkfSK3+XHX47KR/aBTap0RnBIGIvcWwfSlCxTUoCfAMsa1x6Fq+Q3HwvI2pn+z6qhax8wpD7VmpBb5kaBYeuIgM1qBIup10uIBCmMbRX5SqDURr5g+uEkApynlmJlmKQvzalCdvDhHhCNZ07mDeo0O/LWxk2b5weM5yUL0UcOkWyRiiQXr+O81xZ1KwnAyVT6h1l/yqd9bA+210z+mdrs6/g==";
//        String content = "000";
//        String rsaPubkeyA = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs3mEuY9XVw0rYMSdV4N/JhPt08ryr50mB+99AYQ0oxy0Ed5J3vFOc493nQUKdjHXrNSdYFn4McwFmWRowErJFAFK3ar4jWMu4E/Oek2ZcxISFXrgCoDuBBL0cChZtUZsauJI0k/TD5RHE5SP2e+mc9TJsSAE1fcQz8/Cze/djHflAHzsiI+TtrpD02fDlxO2crBmnL4bOrCb7xs2pi/utw+qhklmw2uvE0UHDQzdKa9/jX5+V4O9Y5wdcUgRpEu1w8kOQ3KKz86yCfJ2XC/9HDnDdDoXFy0Fg53A5bvO0YT/DbdrvHMU9w7sW4Sv5Z7rUZvfvdtvh8dVQj5b/0JBVQIDAQAB";
//        boolean b = RSASignature.doCheck(content, signData, rsaPubkeyA);
//        System.out.println(b);

        String s = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYubCpwjcRbPaD\n" +
                "5t2lIei5OPJvDoYkqNv5Y7RSNOSFNLCHicxWl+yy6QSJ7dkR2HpYwf26WLzv9Yji\n" +
                "OfzywDLPVphOOa4UcTVq4Z0W6ezVTFWwkB48hw5RBXMdLj2r5JMRQkGo4S073Tit\n" +
                "onlhWhaU1poVlWkhPmwJX/b/2Wv2SB+OyM5XscpcoUXItKB4e52db5hLmPArj7Rp\n" +
                "gCVAsUO6OZ+X7TYUxYqMVDYMo1qeAjtj/Zj0pMi2QDD7hYdCNSSsZq6qA2pfHWrI\n" +
                "3Yv3/FvH2O19rKjLqQR9RqBFzyt8VfogkGSWL5hg7y7DOVb3a+YttKncDbvAYojz\n" +
                "qI4A5LtBAgMBAAECggEAB1rJ2yLzK++nX2q6ub0MS/8mDZsRcqp/DV9zy5Gof5Gt\n" +
                "/pjwMitWxrbZ9RpzHZzjJ2sQIO0tDBEy6lIIkFgZGkr9QqccbveUrvT2b6gMM6ld\n" +
                "ZwdJrXiDg/tTM9CKP8DeJDKgntVZLEzW8eWlGr8P31MGUIvFaEhtQ3ZPmrqJrG5x\n" +
                "qz4UxjK8gjO/q3S0yxRlZcLc5OqUwFXjuIBLTORpSp/heKV/Ae4EeculMiSjbFdX\n" +
                "P45vudfB0s9Z64TxCRqh58/wlwDNBHcpVSOf7fcK2pmU183/v+GSmVB7D6zMFvJr\n" +
                "8vLa6DkmeNHOcgg4UUn7lryP9B0G89y2LcjsDbCAAQKBgQDGm1nXwDG3/dGpVCb+\n" +
                "pKt+XO/PJ54rf4NaDePT0Vm3iF89Tx6FC4CjhiFf/YHv2E8kW2i28qXzGZvKl/3s\n" +
                "GYvFFwU5DzFyLXQM+Jjdif2lbhTYn0sxWFANPd32j0CloNZfmFG2I7DbUvofGh8p\n" +
                "c9BDoL6b+aYn7IgwkNQ/mm6kwQKBgQDE3BeuiemgaIYhjIhrqOM6Adtkk0/3YDwL\n" +
                "UeAliVKA6rNw16d1tNN4ZeekmI2o/UhgIHR+szF6OuJQF7K25YZpcYpPFGFhN3zE\n" +
                "98ZhFDPbh83cTMqg1EEUNwwpNrr+QNnkuQLUe+ZY9eyvTwKgQEIDLofX+BBfhGMa\n" +
                "NiBn0iM2gQKBgQCT7bZXevINZ2K4ytNYq/KIZlTAaXPoVkvR0bK2XKKEj3DQy5Oh\n" +
                "6VVhd1B4nOwhZ25bWDKAwe9VSOe/WMJrDwQKQwFC5MwhjTV5S9kged2RUJL6Xnu1\n" +
                "P2Aw3LntIfo247uI7H1AOwlJ814VuHEpgPfIx3vx1VsrNYSjF9zfmvl6gQKBgGKH\n" +
                "zoqbmIWjC0+LaVSJaYHFICxM1z93uVXpGwlunA7OahXWuohdg2LFjpWBjZXBgBIs\n" +
                "7GHmCqOlCVURJ3AHnaj1HUbLapogDaEAaxh35iTA6jaTcO/ijNgzXTDbpehQST8D\n" +
                "ovj7MqXgISD1qNjQkLnK9k8QaBGGz5t07+9G7D0BAoGAQmKPyz6GZC7/nPJHY6zO\n" +
                "7Z6WC/FI5byj41xFSejr9f1IEcze+JA31G7MxIvQ+gC7vWOwKk6yirN0h7fZIOiR\n" +
                "7AJ1ThECPewubYKPQbap+539th/7TAdLCzOu5lnO5BgSVC3G2GvB8BEwhokn9AQw\n" +
                "MbSE6SmBXofok7X2sjazSXU=";

        String replace = s.replace("\n", "");
        System.out.println(replace);
    }
}
