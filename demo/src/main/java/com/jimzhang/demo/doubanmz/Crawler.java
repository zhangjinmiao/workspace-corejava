package com.jimzhang.demo.doubanmz;

import com.jimzhang.demo.util.FileHelper;
import com.jimzhang.demo.util.TextHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 抓取豆瓣图片
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2017-11-15 14:30
 */
public class Crawler {

    public static void main(String[] args) {
        for (int i = 1; i <= 1; i++) {
            List<String> list = get(i);
            if (list == null || list.size() == 0) {
                break;
            } else {
                for (String s : list) {
                    String type = s.substring(s.lastIndexOf("."));
                    if (s.indexOf("http://") == -1 && s.indexOf("https://") == -1) {
//                        s = "http://www.dbmeizi.com" + s;
                        s = "https://www.dbmeinv.com" + s;
                    }
                    //保存的目录名称，可以修改
                    FileHelper.downloadWebFile(s, UUID.randomUUID() + type, "E:/网络抓取/");
                }
            }
            System.out.println(i);
        }
    }

    private static List<String> get(int i) {
        InputStreamReader reader = null;
        BufferedReader in = null;
        String reqUrl = "https://www.dbmeinv.com/?pager_offset=" + i;
        System.out.println("请求网址：" + reqUrl);
        try {
            URL url = new URL("https://www.dbmeinv.com/?pager_offset=" + i);
//            URL url = new URL("http://www.dbmeizi.com/?p=" + i);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "MSIE");
            connection.setConnectTimeout(10000);
            reader = new InputStreamReader(connection.getInputStream(), "utf-8");
            in = new BufferedReader(reader);
            String line = null; // 每行内容
            int lineFlag = 0; // 标记: 判断有没有数据
            StringBuilder content = new StringBuilder();
            while ((line = in.readLine()) != null) {
                content.append(line);
                lineFlag++;
            }
            if (lineFlag >= 1) {
                return TextHelper.getTextImageSrc(content.toString());
            }
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
