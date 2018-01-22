package com.jimzhang.core.io.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 文件拷贝
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2018-01-10 15:11
 */
public class FileUtil {

    private FileUtil() {
        throw new AssertionError();
    }

    public static void fileCopy(String source, String target) throws IOException {
        try (InputStream in = new FileInputStream(source)) {
            try (OutputStream out = new FileOutputStream(target)) {
                byte[] buffer = new byte[4096];
                int bytesToRead;
                while ((bytesToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
            }
        }
    }

    public static void fileCopyNIO(String source, String target) throws IOException {
        try (FileInputStream in = new FileInputStream(source)) {
            try (FileOutputStream out = new FileOutputStream(target)) {
                FileChannel inChannel = in.getChannel();
                FileChannel outChannel = out.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                while (inChannel.read(buffer) != -1) {
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        fileCopy("D:\\md/test.docx", "D:\\md/a.docx");
        fileCopyNIO("D:\\md/test.docx", "D:\\md/b.docx");
    }
}
