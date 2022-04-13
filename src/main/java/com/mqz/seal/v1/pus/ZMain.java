package com.mqz.seal.v1.pus;

import com.mqz.seal.v1.pus.model.PublicSealDTO;
import com.mqz.seal.v1.pus.rule.PublicSealBuilder;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/19
 */
public class ZMain {

    public static void main(String[] args) throws Exception {

        /** 椭圆企业电子印章 */
//        PublicSealBuilder psb = new TYSeal();
//        PublicSealDTO dto = new PublicSealDTO()
//                .setColor(1)
//                .setCompanyName("蒙大拿传媒科技有限公司")
//                .setFont(1)
//                .setNo("92369866312X")
//                .setTitle("测试专用章");
//        String base64 = psb.handleToBase64(dto);
//        System.out.println(base64);


        /** 圆形电子印章 */
        PublicSealBuilder psb = new YXSeal();
        PublicSealDTO dto = new PublicSealDTO()
                .setColor(1)
                .setCompanyName("XXXX有限公司")
                .setFont(1)
                .setNo("4201181206232");
                //.setTitle("");
        String base64 = psb.handleToBase64(dto);
        System.out.println(base64);
    }


}
