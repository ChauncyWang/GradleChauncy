package cc.chauncy.foc.JLA;

import java.io.File;
import java.io.FileInputStream;

/**
 * 一些小工具
 * Created by 13969 on 2017/5/17.
 */
public class Tools {
    /**
     * 根据文件名字读取所有内容
     * @param fileName 文件名字
     * @return 字符数组
     */
    public static char[] readAll(String fileName) {
        return readAll(new File(fileName));
    }

    /**
     * 读取文件所有内容
     * @param file 文件
     * @return 字符数组
     */
    public static char[] readAll(File file) {
        byte[] bytes = new byte[(int) file.length()];
        char[] chars = new char[bytes.length];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes,0,bytes.length);
            for(int i = 0;i < chars.length;++i) {
                chars[i] = (char)bytes[i];
            }
        } catch (Exception e) {
            return null;
        }
        return chars;
    }
}
