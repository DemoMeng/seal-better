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
public class TYSeal extends PublicSealBuilder {


    /***
     * 构建签章配置类
     * @param dto
     * @return
     */
    private SealConfiguration initConfiguration(PublicSealDTO dto){
        /** 初始化配置值，添加默认参数什么的 */
        dto = PublicSealDTO.initDefault(dto);

        /** 构建配置类 */
        SealConfiguration configuration = new SealConfiguration();

        /** 签章主字体 */
        SealFont mainFont = new SealFont()
                    .setIsBold(true)
                    .setFontFamily(dto.getFontFamily())
                    .setMarginSize(10)
                    .setFontText(dto.getCompanyName())
                    .setFontSize(25)
                    .setFontSpace(12.0D);
        if (dto.getCompanyName().length() > 14) {
            mainFont.setFontSize(20);
            mainFont.setFontSpace(8.0D);
        }
        configuration.setMainFont(mainFont);

        /** 标题字体 */
        SealFont titleFont;
        if (!StringUtils.isEmpty(dto.getNo())) {
            titleFont = new SealFont()
                        .setIsBold(true)
                        .setFontFamily(dto.getFontFamily())
                        .setMarginSize(5)
                        .setFontText(dto.getNo())
                        .setFontSize(13)
                        .setFontSpace(12.0D);
            configuration.setViceFont(titleFont);
        }

        if (!StringUtils.isEmpty(dto.getTitle())) {
            titleFont = new SealFont()
                .setIsBold(true)
                .setFontFamily(dto.getFontFamily())
                .setFontSize(22);
            if (dto.getCompanyName().length() > 14) {
                titleFont.setFontSize(20);
            }
            titleFont.setFontText(dto.getTitle());
            titleFont.setMarginSize(68);
            titleFont.setMarginSize(27);
            configuration.setTitleFont(titleFont);
        }
        /** 其他配置 */
        configuration.setImageSize(300);
        configuration.setBackgroundColor(dto.getColorX());
        configuration.setBorderCircle(new SealCircle(4, 140, 90));
        return configuration;
    }


    @Override
    public String handleToBase64(PublicSealDTO dto) throws Exception {
        SealConfiguration configuration = initConfiguration(dto);
        String base64 = SealRepository.dealWith(configuration);
        return base64;
    }
}
