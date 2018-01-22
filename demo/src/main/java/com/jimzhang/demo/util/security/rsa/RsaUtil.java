
package com.jimzhang.demo.util.security.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtil {
	
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
	
	/**
	* RSA签名
	* @param content 待签名数据明文
	* @param privateKey 商户私钥
	* @param input_charset 编码格式
	* @return 签名值
	*/
	public static byte[] sign(String content, String privateKey, String input_charset)
	{
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.getDecoder().decode(privateKey) );
        	KeyFactory keyf 				= KeyFactory.getInstance("Rsa");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            Signature signature = Signature
                .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );

            byte[] signed = signature.sign();

            return signed;
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }

        return null;
    }

	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param public_key 公钥
	* @param input_charset 编码格式
	* @return 布尔值
	*/
	public static boolean verify(String content, String sign, String public_key, String input_charset)
	{
		try
		{
			KeyFactory keyFactory = KeyFactory.getInstance("Rsa");
	        byte[] encodedKey = Base64.getDecoder().decode(public_key);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			Signature signature = Signature
			.getInstance(SIGN_ALGORITHMS);
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes(input_charset) );
		
			return signature.verify(Base64.getDecoder().decode(sign));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 公钥加密过程
	 *
	 * @param publicKey
	 *            公钥
	 * @param plainTextData
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static byte[] encrypt(String publicKey, byte[] plainTextData)
			throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		RSAPublicKey rsaPublicKey = loadPublicKeyByStr(publicKey);
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("Rsa");
			// cipher= Cipher.getInstance("RsaUtil", new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}
	
	/**
	* 解密
	* @param content 密文
	* @param private_key 商户私钥
	* @param input_charset 编码格式
	* @return 解密后的字符串
	*/
	public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("Rsa");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.getDecoder().decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }


	public static byte[] decrypt(String privateKey, byte[] cipherData)
			throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		PrivateKey prikey = getPrivateKey(privateKey);
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("Rsa");
//			 cipher= Cipher.getInstance("RsaUtil", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, prikey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}
	
	/**
	* 得到私钥
	* @param key 密钥字符串（经过base64编码）
	* @throws Exception
	*/
	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;
		
		keyBytes = Base64.getDecoder().decode(key);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("Rsa");
		
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		return privateKey;
	}

	/**
	 * 从字符串中加载公钥
	 *
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
			throws Exception {
		try {
			byte[] buffer = Base64.getDecoder().decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("Rsa");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	public static void main(String[] args) throws Exception {
		String rsaPrikeyA = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCzeYS5j1dXDStgxJ1Xg38mE+3TyvKvnSYH730BhDSjHLQR3kne8U5zj3edBQp2Mdes1J1gWfgxzAWZZGjASskUAUrdqviNYy7gT856TZlzEhIVeuAKgO4EEvRwKFm1Rmxq4kjST9MPlEcTlI/Z76Zz1MmxIATV9xDPz8LN792Md+UAfOyIj5O2ukPTZ8OXE7ZysGacvhs6sJvvGzamL+63D6qGSWbDa68TRQcNDN0pr3+Nfn5Xg71jnB1xSBGkS7XDyQ5DcorPzrIJ8nZcL/0cOcN0OhcXLQWDncDlu87RhP8Nt2u8cxT3DuxbhK/lnutRm9+922+Hx1VCPlv/QkFVAgMBAAECggEAfTX4LOUeD/N2IerJwrZQzKX2tF3QdkhysfKZCafMeUebMSQd8VX0X5bl4jwUEmWvjDpBGWlrSIKIHX35vr3uDvDf5EpVYX6iw4eqC5V7ytX+m9G/4FQWuezncCAWhet91AvOtjtOin3uIHDy8x7Ix+LaW6J0/TmDFxhhVf9UV5xWNq0sg3+qwjyLJgz6vb9qGJIeTlVukk767QizpT2RQkq6D2KcZAbYSFEhvWLFT/Fm2tC8SmigtEAaYWRqfjZVbgDzJE+6Qp9tULiOsQxDPPHc0WGjqSRXrt6aIS0ErPIwokP+5rZP9uPN1DGJZmyFRkex/ZTQ7kk3vPaJSdghQQKBgQD+m5pVNybvhLR7+KC5G1HPSTSu0iGYrh72VLBxX2F5aJxeR3RJP0EwsFTKvyotibGz9x9/4V6ehYZJc2PjXuAmgjq+kBMAwT4ZoAwPEB5UiokUJDEqHMK2pp7H3HUtWAjED9tv+QZfFeNOSQ0I2SoOcdYIJrNqTuvStdvAYoYNOQKBgQC0dL69QOEb9bDp5L48HABjqafYQ3DA/cAwxfI6+BO0XSGVT/7UWv6se4tALQcCQHeupncNcwm5SNfxnRRG+1zHtDBXcVVEdWvVErCZkVLuP4jgegC5DFUMpzt1mvIY2LtHNMsw4hyj44A58yk5Jo9KwbYviIQQ5UNCo8bI5TCw/QKBgEKWh4KJ7hDucBWMLNy8nGTODI08P7UitM5Pz+WWwHN0PJEbtvoBJReKxmJQJMPMI0Gh/AcB9jEXUsua9iDdC1qG7L4yCAZGg+tHxF+cI5KQuNEftgwBgZsrcJNswOifAYvlacqbImNz4gLzcAlEYo9sjkGLfZjsExkEnvhtzAlhAoGAAilX04qAZ8ZUnSiZQr0PICJ7GTMNmbIwaCUm1p/8FG/9I/22TrLcVBKSw20GpXdCN7xUtruHBUojFAjOol8GaHn3DkUUQWKqrP2xUdWUybfHGvE7KvxD1If4RVtaxQNrktnGCUrcDvZ9pbQM7VtR4hJZ7U3UTyN/3794dRWRtL0CgYAyQHIESEivRlkV2Q663ZqskqGQgPX2ZIqB4AbJif4oPrTZ1Fy1IcAUS5fHLuCgMhdisJ5Jv8C6gUrH0IA6KAAZ7p/KuRccAR9mGYecrhmuA9WA8psOhSxEg7yMTMCp5NR3PqgiINvcteFMdcBqAYmtsTLOz8QEuUe+69VNAVyeVg==";
		String rsaPubkeyA = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs3mEuY9XVw0rYMSdV4N/JhPt08ryr50mB+99AYQ0oxy0Ed5J3vFOc493nQUKdjHXrNSdYFn4McwFmWRowErJFAFK3ar4jWMu4E/Oek2ZcxISFXrgCoDuBBL0cChZtUZsauJI0k/TD5RHE5SP2e+mc9TJsSAE1fcQz8/Cze/djHflAHzsiI+TtrpD02fDlxO2crBmnL4bOrCb7xs2pi/utw+qhklmw2uvE0UHDQzdKa9/jX5+V4O9Y5wdcUgRpEu1w8kOQ3KKz86yCfJ2XC/9HDnDdDoXFy0Fg53A5bvO0YT/DbdrvHMU9w7sW4Sv5Z7rUZvfvdtvh8dVQj5b/0JBVQIDAQAB";
		String signEncode = Base64.getEncoder().encodeToString(RsaUtil.sign("000", rsaPrikeyA, "utf-8"));
		System.out.println("签名sign：" + signEncode);
		String rsaPrikeyB = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOS+JMuKCktEydXe6Y/iwAltU1n/BkXhWFB220oi65Q6vgnXYv7pVCvkicKJS45xnS9gSTkiYyPyWkxTaG6ZxCNI7ZaVlpu+3ODfts/OAo1ehU4ilnQZLLtZAkREdFXyIZ92cXf0rDI9+O6dLlHskCpXLRpTmAbvhyxk7uVJfqR5JgpGWb81skZde8pDSWx+OXNKjMDUqKAHvYepyzznpQlK3PLTxwbmm3UcCTvHi1ocw6Dr6InZY1IQZTDN8WnNwVTj2doNfduT7M3Mr0KlHR+NTDwHK+xoXzkt6cUjCgq5Gl5ZPwACojWQ19BuMFq2ORibmyL7ILW7wwAJWZznA7AgMBAAECggEAVzHazwaHscWl+PxZO7jsf8PtjHmgVfBtIWJG8Fw9Y1s7VF1I0eBR6F8voTkgwRkj8ui8Ly1saHfXels6qXs3PD52lnT+zFLTSuO4gLWIKp3BS1p24oYFDP0KAZ8xSSodfGnk6zoeNuuhjzJq/+lYZV26uQqU7MWTSsmIDnUZzf35O/yHfcDrKIGRQasHdoeQeoJU6nEC5T7HIZuoRo7WwZJ+x3CjbA1B2M8W2aTkG0Ky79+CRF67C89vVLsjtZQsDLPZAmiIbUpRGhnHr/mhfcZ3M+198fqBcbwrtd6R+eWcYVadMn7DxNEqGFCTQCkBKO3xAeKd9g//eStNhs7NEQKBgQDKdumpxR7aSCLWgdkRcDhxNaJBxGgExVYekXLKxOiGlQ3OrlBGTd4btklPr1g/kM+GKO2Cxrw25KfI/Z0pLrBn+Frqt5XOlBuMjFCGIjQusnR3395Ep+5rOisZ7MgaNwGej3a0fkgSuBSLMIqVRV0u3E0RO86x2WT+ZwIgZ2uuuQKBgQCz7B10f6DlCRVvEeeUiHzaE8h52w4ot55fCM8PkYPemrIb0mn1A9pFQfCvTuNi9ErWHitmbhrGA1fv/SnXa1nM7PgUxqG3FmJttysZ6fQjkrheEscH+n/h/PxOzIEK+uGa3nhREMLBLcwUVtVv3ryK+GSynKriUbW8StqGmVP8kwKBgBnAplgByrrZEhVWztV5ALTlrY3P/asdrqaUuZUxFqKHswnnAi7vGKFNvmV0WDU9pifabNuFi8svqmfWWncyiR2waWARmex8ykRGVLe7AgguisH0p8Dz20+oRnM1uv4aoQoaFW8/4MsAvxw1QGtcN04Ol37prJDSphX15D9a6EeBAoGAbXAmLnFfNVEEJ44VdWRbxO4Zl/0lLKEbhKTABbE2K9WelouzBWR1hIDqXEe4ybzcNEcfkOtR/7WS7Jfptm1Grv5Im6KCRPIppqCuZ9HFPUoGOLplM7YryRLviSKdL0eQqHm/Z1exjn1Dz+9NXgF2X3VdEsN2sbiPuVsydoKW8l8CgYEAxzS922+Pi4yp9vbT/PibH1jKND7w5u1XkC4TW1zkVZ8pkvjsyiQg789LHLOl6zg4zMioaO0nQtEhtK6PAhcgfqW/45iSZUBNs+jGbvoFrMEjOlZ4ch8eLNcZsLQyf4cywE+n9YaQ2yNQu+2vztx7Mff4z2H8QaQW/uxu7ii7nKY=";
		String rsaPubkeyB = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjkviTLigpLRMnV3umP4sAJbVNZ/wZF4VhQdttKIuuUOr4J12L+6VQr5InCiUuOcZ0vYEk5ImMj8lpMU2humcQjSO2WlZabvtzg37bPzgKNXoVOIpZ0GSy7WQJERHRV8iGfdnF39KwyPfjunS5R7JAqVy0aU5gG74csZO7lSX6keSYKRlm/NbJGXXvKQ0lsfjlzSozA1KigB72Hqcs856UJStzy08cG5pt1HAk7x4taHMOg6+iJ2WNSEGUwzfFpzcFU49naDX3bk+zNzK9CpR0fjUw8ByvsaF85LenFIwoKuRpeWT8AAqI1kNfQbjBatjkYm5si+yC1u8MACVmc5wOwIDAQAB";
		boolean verify = RsaUtil.verify("000", signEncode, rsaPubkeyA, "utf-8");
		System.out.println(verify);
	}
}
