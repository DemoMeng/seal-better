package com.mqz.seal.v1.prs.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
@Data
@Accessors(chain = true)
public class SealDTO {
    /**主体内容*/
    private String fontContent;
    /**文字大小*/
    private Integer fontSize;
    /**文字间距*/
    private Double fontSpace;
    /**文字距外边距*/
    private Integer marginSize;
    /**字体类别 1:方正黑体,2:仿宋 3:楷体*/
    private Integer fontFamilyTag;
    /**颜色类别 1:红色, 2黑色, 3:蓝色*/
    private Integer colorTag;

    /** 具体字体名称 */
    private String fontFamily;

    public SealDTO ifNullInitDefault(SealDTO dto){
        dto.setColorTag(dto.getColorTag()==null?1:dto.getColorTag());
        dto.setFontSize(dto.getFontSize()==null?120:dto.getFontSize());
        dto.setFontFamilyTag(dto.getFontFamilyTag()==null?1:dto.getFontFamilyTag());
        return dto;
    }

}
