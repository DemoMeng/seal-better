package com.mqz.seal.v1.pus.model;

import lombok.Data;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/19
 */
@Data
public class SealCircle {

    private Integer lineSize;
    private Integer width;
    private Integer height;

    public SealCircle(Integer lineSize, Integer width, Integer height) {
        this.lineSize = lineSize;
        this.width = width;
        this.height = height;
    }
}
