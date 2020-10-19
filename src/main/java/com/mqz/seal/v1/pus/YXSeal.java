package com.mqz.seal.v1.pus;

import com.mqz.seal.v1.pus.factory.SealRepository;
import com.mqz.seal.v1.pus.model.PublicSealDTO;
import com.mqz.seal.v1.pus.model.SealCircle;
import com.mqz.seal.v1.pus.model.SealConfiguration;
import com.mqz.seal.v1.pus.model.SealFont;
import com.mqz.seal.v1.pus.rule.PublicSealBuilder;
import org.springframework.util.StringUtils;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/19
 */
public class YXSeal extends PublicSealBuilder {


    /***
     * 构建签章配置类
     * @param dto
     * @return
     */
    private SealConfiguration initConfiguration(PublicSealDTO dto){
        dto = PublicSealDTO.initDefault(dto);
        /** 签章配置初始化 */
        SealConfiguration configuration = new SealConfiguration();
        /** 企业签章设置主字体 */
        SealFont mainFont = new SealFont()
                .setIsBold(true)
                .setFontFamily(dto.getFontFamily())
                .setMarginSize(5)
                .setFontText(dto.getCompanyName())
                .setFontSize(30)
                .setFontSpace(30.0D);
        if (dto.getCompanyName().length() > 14) {
            mainFont.setFontSize(23);
            mainFont.setFontSpace(21.0D);
        }
        configuration.setMainFont(mainFont);

        /** 设置中间字体 */
        SealFont centerFont;
        if (!StringUtils.isEmpty(dto.getNo())) {
            centerFont = new SealFont()
                    .setFontFamily(dto.getFontFamily())
                    .setMarginSize(-5)
                    .setFontText(dto.getNo())
                    .setIsBold(false)
                    .setFontSize(13)
                    .setFontSpace(10.0D);
            configuration.setViceFont(centerFont);
        }
        centerFont = new SealFont()
                .setIsBold(false)
                .setFontFamily(dto.getFontFamily())
                .setFontText("★")
                .setFontSize(70);
        configuration.setCenterFont(centerFont);

        /** 设置标题 */
        if (!StringUtils.isEmpty(dto.getTitle())) {
            SealFont titleFont = new SealFont()
                    .setIsBold(true)
                    .setFontFamily(dto.getFontFamily())
                    .setFontSize(20)
                    .setFontText(dto.getTitle())
                    .setMarginSize(70);
            configuration.setTitleFont(titleFont);
        }
        /** 其他设置 */
        configuration.setImageSize(250);
        configuration.setBackgroundColor(dto.getColorX());
        configuration.setBorderCircle(new SealCircle(4, 115, 115));
        return configuration;
    }


    @Override
    public String handleToBase64(PublicSealDTO dto) throws Exception {
        SealConfiguration configuration = initConfiguration(dto);
        String base64 = SealRepository.dealWith(configuration);
        return base64;
    }
}
