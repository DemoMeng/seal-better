package com.mqz.seal.v1.pus.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/19
 */
@Data
@Accessors(chain = true)
public class SealFont {
    private String fontText;
    private Boolean isBold = true;
    private String fontFamily = "KaiTi";//具体的服务器安装的字体名称
    private Integer fontSize;
    private Double fontSpace;
    private Integer marginSize;
}
