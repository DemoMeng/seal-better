package com.mqz.seal.v1.ps.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
public class ByteUtils {

    /**
     * 生成印章图片的byte数组
     * @param image BufferedImage对象
     * @return byte数组
     * @throws IOException 异常
     */
    public static byte[] buildBytes(BufferedImage image) throws Exception {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            //bufferedImage转为byte数组
            ImageIO.write(image, "png", outStream);
            return outStream.toByteArray();
        }
    }

    /**
     * 将byte数组保存为本地文件
     * @param buf      byte数组
     * @param fullPath 文件全路径
     * @throws IOException 异常
     */
    public static String storeBytes(byte[] buf, String fullPath) throws IOException {
        Random random = new Random();
        String fileName = "personalSeal" + System.currentTimeMillis() + "" + random.nextInt(1000) + ".png";
        File file = new File(fullPath + fileName);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            //1.如果父目录不存在，则创建
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //2.写byte数组到文件
            bos.write(buf);
        }
        return fullPath + fileName;
    }

}
