package com.mqz.seal.v1.pus.model;

import lombok.Data;

import java.awt.*;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/19
 */
@Data
public class SealConfiguration {

    private SealFont mainFont;
    private SealFont viceFont;
    private SealFont titleFont;
    private SealFont centerFont;
    private SealCircle borderCircle;
    private SealCircle borderInnerCircle;
    private SealCircle innerCircle;
    private Color backgroundColor;
    private Integer imageSize = 300;


}
