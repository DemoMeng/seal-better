package com.mqz.seal.v1.pus.rule;

import com.mqz.seal.v1.pus.model.PublicSealDTO;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/19
 */
public abstract class PublicSealBuilder {

    /**
     * 返回base64
     * @param dto
     * @return
     * @throws Exception
     */
    public abstract String handleToBase64(PublicSealDTO dto) throws Exception;




}
