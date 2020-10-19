package com.mqz.seal.v1.prs.constant;

/**
 * @author mqz
 * @description 字体枚举
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
public enum EnumFontFamily {

    FONT_SimHei(1,"SimHei"),
    FONT_SimSun(2,"SimSun"),
    FONT_KaiTi(3,"KaiTi"),

    FONT_SongTi(1,"宋体"),
    FONT_FangZhengHeiTi(2,"方正黑体"),
    FONT_KaiTi_Public(3,"楷体")
    ;


    private int tag;
    private String font;

    EnumFontFamily(int tag,String font){
        this.tag = tag;
        this.font = font;
    }

    public int getTag() { return tag; }

    public void setTag(int tag) { this.tag = tag; }

    public String getFont() { return font; }

    public void setFont(String font) { this.font = font; }
}
