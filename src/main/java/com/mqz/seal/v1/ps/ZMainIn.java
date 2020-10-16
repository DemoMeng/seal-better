package com.mqz.seal.v1.ps;

import com.mqz.seal.v1.ps.model.SealDTO;
import com.mqz.seal.v1.ps.rule.PrivateSealBuilder;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
public class ZMainIn {

    public static void main(String[] args) throws Exception {

//        /** 构建请求参数 */
//        SealDTO dto = new SealDTO()
//                .setColorTag(1)
//                .setFontContent("萌章章")
//                .setFontFamilyTag(1);
//
//        PrivateSealBuilder ps = new ZFXSeal();
//        String path = ps.handleToLocal(dto);
//        System.out.println(path);
//
//
//        PrivateSealBuilder ps = new ZFXSeal();
//        SealDTO dto1 = new SealDTO()
//                .setColorTag(1)
//                .setFontContent("萌章章")
//                .setFontFamilyTag(1);
//        String base64 = ps.handleToBase64(dto1);
//        System.out.println(base64);

//        SealDTO dto = new SealDTO()
//                .setColorTag(1)
//                .setFontContent("蒙大拿")
//                .setFontFamilyTag(1)
//                .setFontSize(80);
//        PrivateSealBuilder ps = new CFXSeal();
//        String path = ps.handleToLocal(dto);
//        System.out.println(path);

        SealDTO dto = new SealDTO()
                .setColorTag(1)
                .setFontContent("蒙大拿")
                .setFontFamilyTag(1)
                .setFontSize(80);
        PrivateSealBuilder ps = new ZFXSeal();
        String path = ps.handleToBase64(dto);
        System.out.println(path);
    }

}
