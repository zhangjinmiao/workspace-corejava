package com.jimzhang.demo.util.security.cipher;

import java.util.Random;

/**
 * 生成16位的随机字符串
 * Created by admin on 2016/11/22.
 */
public class CharUtil {

    private static char ch[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '0', '1' };

    private static Random random = new Random();

    /**
     * 生成指定长度的随机字符串
     * @param length
     * @return
     */
    public static String createRandomStr(int length){
        if (length > 0) {
            int index = 0;
            char[] temp = new char[length];
            int num = random.nextInt();
            for (int i = 0; i < length % 5; i++) {
                temp[index++] = ch[num & 63];
                num >>= 6;
            }
            for (int i = 0; i < length / 5; i++) {
                num = random.nextInt();
                for (int j = 0; j < 5; j++) {
                    temp[index++] = ch[num & 63];
                    num >>= 6;
                }
            }
            return new String(temp, 0, length);
        }
        else if (length == 0) {
            return "";
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 生成指定长度的随机数字格式字符串
     */
    private static char ch2[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String createRandomNumber(int length){
        if(length > 0) {
            int index = 0;
            char[] temp = new char[length];
            for (int i = 0; i<length;i++){
                int num = random.nextInt(10);
                temp[index++] = ch2[num];
            }
            return new String(temp,0,length);
        }else if (length == 0){
            return "";
        }else{
            throw new IllegalArgumentException();
        }
    }


}
