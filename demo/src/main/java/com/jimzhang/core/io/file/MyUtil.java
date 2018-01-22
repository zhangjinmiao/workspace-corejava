package com.jimzhang.core.io.file;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 输入一个文件名和一个字符串，统计这个字符串在这个文件中出现的次数
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 15:29
 */
public class MyUtil {

    // 工具类中的方法都是静态方式访问的因此将构造器私有不允许创建对象(绝对好习惯)
    private MyUtil() {
        throw new AssertionError();
    }

    /**
     * 统计给定文件中给定字符串的出现次数
     *
     * @param filename 文件名
     * @param word     字符串
     * @return 字符串在文件中出现的次数
     */
    public static int countWordInFile(String filename, String word) {
        int counter = 0;
        try (FileReader fr = new FileReader(filename)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    int index = -1;
                    while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return counter;
    }

    public static void main(String[] args) {
        int i = countWordInFile("D:\\country.txt", "B");
        System.out.println(i);
    }

}
