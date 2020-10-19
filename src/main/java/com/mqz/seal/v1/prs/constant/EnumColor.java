package com.mqz.seal.v1.prs.constant;

import java.awt.*;

/**
 * @author mqz
 * @description 字体枚举
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
public enum EnumColor {

    COLOR_Red(1,Color.RED),
    COLOR_Blue(2,Color.BLUE),
    COLOR_Black(3,Color.BLACK),
    ;


    private int tag;
    private Color color;

    EnumColor(int tag, Color color){
        this.tag = tag;
        this.color = color;
    }

    public int getTag() { return tag; }

    public void setTag(int tag) { this.tag = tag; }

    public Color getColor() { return color; }

    public void setFont(Color color) { this.color = color; }
}
