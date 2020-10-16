package com.mqz.seal.v1.ps.rule;

import com.mqz.seal.v1.ps.model.SealDTO;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
public abstract class PrivateSealBuilder {

    public static final String base64Prefix="data:image/png;base64,";
    public static final String SEAL_SAVE_PATH = "/Applications/mqz/seal-builder/temp/";
    public static final String Font_HeiTi = "SimHei";//"Heiti";
    public static final String Font_FangSong = "SimSun";//STFangsong
    public static final String Font_KaiTi = "KaiTi";//"STKaiti";
    public final static int INIT_BEGIN = 10;

    /**
     * 处理并返回base64
     * @param dto
     * @return
     * @throws Exception
     */
    public abstract String handleToBase64(SealDTO dto) throws Exception;


    /**
     * 处理并返回图片本地地址
     * @param dto
     * @return
     * @throws Exception
     */
    public abstract String handleToLocal(SealDTO dto) throws Exception;

}
