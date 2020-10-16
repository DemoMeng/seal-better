package com.mqz.seal.v1.ps;

import com.mqz.seal.v1.ps.model.SealDTO;
import com.mqz.seal.v1.ps.reponsitory.ZFXRepository;
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
public class ZFXSeal extends PrivateSealBuilder {

    public final static int fixH = 18;
    public final static int fixW = 2;
    public static final int lineSize = 14;
    private static final int imageSize = 230;



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

    private BufferedImage toBufferedImage(SealDTO font, int lineSize, int imageSize, int fixH, int fixW) throws Exception {
        font.setFontContent(font.getFontContent()+(font.getFontContent().length() == 2 ? "之印" : "印"));
        BufferedImage bi = null;
        if (font != null && font.getFontContent().length() >= 2 && font.getFontContent().length() <= 4) {
             bi = new BufferedImage(imageSize, imageSize / 2, 6);
        } else {
            throw new Exception("FontText.length illegal!");
        }
        Graphics2D g2d = bi.createGraphics();
        Color color = font.getColorTag() == 1 ? Color.RED : (font.getColorTag() == 2 ? Color.BLACK : Color.BLUE);
        g2d.setPaint(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (font.getFontContent().length() == 2) {
            bi = ZFXRepository.drawThreeFont(bi, g2d, font.setFontContent(font.getFontContent() ), lineSize, imageSize, fixH, fixW, color);
        } else if (font.getFontContent().length() == 3) {
            bi = ZFXRepository.drawFourFont(bi, font.setFontContent(font.getFontContent()), lineSize, imageSize, fixH, fixW, color);
        } else {
            bi = ZFXRepository.drawFourFont(bi, font, lineSize, imageSize, fixH, fixW, color);
        }
        return bi;
    }


}
