package com.mqz.seal.v1.ps.utils;

import java.util.UUID;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
