package com.mqz.seal.v1.ps;

import com.mqz.seal.v1.ps.model.SealDTO;
import com.mqz.seal.v1.ps.reponsitory.CFXRepository;
import com.mqz.seal.v1.ps.rule.PrivateSealBuilder;
import com.mqz.seal.v1.ps.utils.ByteUtils;
import com.mqz.seal.v1.ps.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */
public class CFXSeal extends PrivateSealBuilder {

    public final static int fixH = 18;
    public final static int fixW = 2;
    public static final int lineSize = 16;
    private static final int imageSize = 300;

    @Override
    public String handleToBase64(SealDTO dto) throws Exception {
        BufferedImage bi = toBufferedImage(dto, lineSize, imageSize, fixH, fixW);
        byte[] bs = ByteUtils.buildBytes(bi);
        String fullPath = ByteUtils.storeBytes(bs,SEAL_SAVE_PATH);
        String base64 = FileUtils.fileToBase64(new File(fullPath));
        return base64Prefix+base64;
    }

    @Override
    public String handleToLocal(SealDTO dto) throws Exception {
        BufferedImage bi = toBufferedImage(dto, lineSize, imageSize, fixH, fixW);
        byte[] bs = ByteUtils.buildBytes(bi);
        String fullPath = ByteUtils.storeBytes(bs,SEAL_SAVE_PATH);
        return fullPath;
    }


    private BufferedImage toBufferedImage(SealDTO dto,int lineSize, int imageSize, int fixH, int fixW) throws Exception {
        //1.生成画布
        if (dto == null || dto.getFontContent().length() < 2 || dto.getFontContent().length() >= 16) {
            throw new Exception("FontText.length illegal!");
        }
        BufferedImage bi = new BufferedImage(imageSize, imageSize / 2, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bi.createGraphics();
        Color color = dto.getColorTag() == 1 ? Color.RED : (dto.getColorTag() == 2 ? Color.BLACK : Color.BLUE);
        //2.1设置画笔颜色
        g2d.setPaint(color);
        //2.2抗锯齿设置`
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (dto.getFontContent().length() == 2) {
            bi = CFXRepository.drawTwoFont(bi, g2d, dto, lineSize, imageSize, fixH, fixW, color);
        } else if (dto.getFontContent().length() == 3) {
            bi = CFXRepository.drawThreeFont(bi, g2d, dto, lineSize, imageSize, fixH, fixW, color);
        } else {
            bi = CFXRepository.drawFourFont(bi, g2d, dto, lineSize, imageSize, fixH, fixW, color);
        }
        return bi;
    }


}
