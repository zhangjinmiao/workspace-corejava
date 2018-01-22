package com.jimzhang.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 文本处理类
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2017-11-15 14:31
 */
public class TextHelper {
    /**
     * 判断对象为空字符串或者为null，如果满足其中一个条件，则返回true
     *
     * @param object
     * @return
     */
    public static boolean isNullOrEmpty(Object object) {
        return object == null || "".equals(object);
    }

    /**
     * 获取富文本内容中的图片链接地址
     *
     * @return
     */
    public static List<String> getTextImageSrc(String text) {
        if (TextHelper.isNullOrEmpty(text))
            return null;
        String regex = "<\\s*[I|i][m|M][g|G]\\s+([^>]*)\\s*>";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(text);
        List<String> list = new ArrayList<String>();
        while (ma.find()) {// 首先判断话题内容中是否有图片
            list.add(ma.group());
        }
        if (list.size() != 0) {// 有图片文件
            List<String> imgSrcList = null;
            String a = null;
            for (String s : list) {
                ma = Pattern.compile("[s|S][R|r][c|C]=[\"|'](.*?)[\"|']").matcher(s);
                if (ma.find()) {
                    a = ma.group();
                    if (imgSrcList == null)
                        imgSrcList = new ArrayList<String>();
                } else {
                    a = null;
                }
                if (a != null) {
                    a = a.replaceAll("[s|S][R|r][c|C]=[\"|']", "").replaceAll("[\"|']", "");
                    if (!"".equals(a)) { // <img class="media-object" src="" style="width: 56px;" /> 避免src="" 的这种情况
                        imgSrcList.add(a);
                    }
                }
            }
            if (imgSrcList != null && imgSrcList.size() != 0)
                return imgSrcList;
            else
                return null;
        } else {
            return null;
        }
    }
}
