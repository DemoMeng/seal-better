package com.mqz.seal.v1.ps.utils;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */

public enum FileEnum {

    DOCX(".docx"),

    DOC(".DOC"),

    PDF(".pdf"),

    PNG(".png"),

    PFX(".pfx"),

    OFD(".ofd"),
    ;
    private String prefix;

    FileEnum(String preFix){
        this.prefix = preFix;
    }

    public String getPrefix() { return prefix; }

    public void setPrefix(String prefix) { this.prefix = prefix; }
}
