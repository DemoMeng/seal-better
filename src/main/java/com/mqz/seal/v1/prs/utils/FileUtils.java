package com.mqz.seal.v1.prs.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Set;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
public class FileUtils {


    /**操作的文件目录，可自定选其他目录*/
    private static final String path = "/Applications/mqz/seal-builder/";

    public static final String base64Prefix="data:image/png;base64,";


    /**
     * 创建文件夹路径
     * @param fileEnum
     * @return
     */
    public static String makeDir(FileEnum fileEnum) {
        String filePath = path + TimeUtils.getYearToHou() + UUIDUtils.getUUID() + fileEnum.getPrefix();
        return filePath;
    }

    /**
     * base64转文件
     * @param base64
     * @param fileEnum
     * @return
     * @throws IOException
     */
    public static String base64ToFile(String base64, FileEnum fileEnum) throws IOException {
        String filePath = makeDir(fileEnum);
        Files.write(Paths.get(filePath), Base64.getDecoder().decode(base64), StandardOpenOption.CREATE);
        return filePath;
    }

    /**
     * 文件路径转base64
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String pathToBase64(String filePath) throws IOException {
        return Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * 文件转base64
     * @param file
     * @return
     * @throws IOException
     */
    public static String fileToBase64(File file) throws IOException {
        return base64Prefix + pathToBase64(file.getAbsolutePath());
    }


    /**
     * 文件下载到本地
     * @param contractUrl
     * @param fileEnum
     * @return
     * @throws Exception
     */
    public static String downLoad(String contractUrl, FileEnum fileEnum) throws Exception {
        String filePath = makeDir(fileEnum);
        URL imgUrl = new URL(contractUrl);
        org.apache.commons.io.FileUtils.copyURLToFile(imgUrl, new File(filePath));
        return filePath;
    }

    /**
     * 文件删除
     * @param paths
     */
    public static void delete(Set<String> paths) {
        try {
            for (String path : paths) {
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (Exception e) {

        }
    }







}
