package com.jimzhang.demo.util.security.cipher;/**
 * Created by zhangjm on 2017/7/6.
 */

import com.alibaba.fastjson.JSONObject;
import com.jimzhang.demo.util.security.rsa.RsaUtil;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 福禄专用加密解密工具类
 *
 * @author zhangjm
 * @create 2017-07-06 15:43
 **/
public class FuluCipherUtil {

    /**
     * 封装aeskey,验签,参数加密 (用于httpclient方式调用) 均是1次base64
     * @param data          业务参数明文
     * @param privateKey    自己私钥
     * @param publicKey     对方公钥
     * @return
     * @throws Exception
     */
    public static Map cipherData(Map data, String privateKey, String publicKey) throws Exception {
        Map urlParam = new HashMap();
        // 将待加密数据转为json
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(data);
        // aes加密的key，16位随机数
        String aesPassword = CharUtil.createRandomStr(16);
        // 先aes加密，再base64转码（1次）
        String paramEncode = Base64.getEncoder().encodeToString(FuluAESUtils.encryptAES(jsonObject.toJSONString(), aesPassword));
        // 先rsa私钥签名，再base64转码（1次）
        String signEncode = Base64.getEncoder().encodeToString(RsaUtil.sign(jsonObject.toJSONString(), privateKey, "utf-8"));
        // 先rsa对方公钥加密，再base64转码（1次）
        String keyPassEncode = Base64.getEncoder().encodeToString(RsaUtil.encrypt(publicKey, aesPassword.getBytes()));
        urlParam.put("data", paramEncode);
        urlParam.put("sign", signEncode);
        urlParam.put("keyPass", keyPassEncode);
        return urlParam;
    }


    /**
     * 解密password 解密data
     *
     * @param data       Aes加密后入参
     * @param keypass    rsa加密后password
     * @param privateKey Rsa私钥
     * @return
     * @throws Exception
     */
    public static Map unCipherPassAndDate(String data, String keypass, String privateKey) throws Exception {
        Map dataMap = null;
        //解密password，先base64解码，再rsa解密
        keypass = new String(RsaUtil.decrypt(privateKey, Base64.getDecoder().decode(keypass)));
        //aes解密参数
        String param = new String(FuluAESUtils.decryptAES(data, keypass), "utf-8");
        System.out.println(param);
        dataMap = (Map) JSONObject.parse(param);
        dataMap.put("param", param);
        return dataMap;
    }


    /**
     * 验签
     * @param data          明文jsonData
     * @param sign          base64
     * @param publicKey     公钥
     * @return
     */
    public static boolean verify(String data, String sign, String publicKey) {
        //验证来源
        boolean verify = RsaUtil.verify(data, sign, publicKey, "utf-8");
        return verify;
    }
}
