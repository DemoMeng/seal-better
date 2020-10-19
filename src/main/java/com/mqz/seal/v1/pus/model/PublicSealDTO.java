package com.mqz.seal.v1.pus.model;

import com.mqz.seal.v1.prs.constant.EnumColor;
import com.mqz.seal.v1.prs.constant.EnumFontFamily;
import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.*;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/19
 */
@Data
@Accessors(chain = true)
public class PublicSealDTO {
    private String companyName;
    private Integer color;
    private Integer font;
    private String no;//下弦文字内容
    private String title;//标题名称

    private Color colorX;
    /** 具体的字体 */
    private String fontFamily;

    public static PublicSealDTO initDefault(PublicSealDTO dto){
        dto.setColor(dto.getColor()==null?0:dto.getColor());
        dto.setFont(dto.getFont()==null?0:dto.getFont());
        dto.setFontFamily(
                dto.getFont().intValue() == EnumFontFamily.FONT_SongTi.getTag()?
                EnumFontFamily.FONT_SongTi.getFont():
                (dto.getFont().intValue() == EnumFontFamily.FONT_KaiTi_Public.getTag()?
                        EnumFontFamily.FONT_KaiTi_Public.getFont():
                        EnumFontFamily.FONT_FangZhengHeiTi.getFont()));
        dto.setColorX(
                dto.getColor().intValue() == EnumColor.COLOR_Red.getTag() ?
                EnumColor.COLOR_Red.getColor():
                (dto.getColor().intValue() == EnumColor.COLOR_Black.getTag()?
                        EnumColor.COLOR_Black.getColor():
                        EnumColor.COLOR_Blue.getColor())
        );
        return dto;
    }

}
