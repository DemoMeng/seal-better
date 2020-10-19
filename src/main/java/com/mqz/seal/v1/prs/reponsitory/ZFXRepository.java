package com.mqz.seal.v1.prs.reponsitory;

import com.mqz.seal.v1.prs.model.SealDTO;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/16
 */

public class ZFXRepository {

    public static final int INIT_BEGIN = 10;


    public static BufferedImage drawThreeFont(BufferedImage bi, Graphics2D g2d, SealDTO font, int lineSize, int imageSize, int fixH, int fixW, Color color) {
        fixH += 10;
        int marginW = fixW + lineSize;
        Font f = new Font(font.getFontFamily(), 1, 120);
        g2d.setFont(f);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle = f.getStringBounds(font.getFontContent().substring(0, 1), context);
        float marginH = (float)(Math.abs(rectangle.getCenterY()) * 2.0D + (double)marginW) + (float)fixH;
        int oldW = marginW;
        BufferedImage nbi = new BufferedImage(imageSize, imageSize, bi.getType());
        Graphics2D ng2d = nbi.createGraphics();
        ng2d.setPaint(color);
        ng2d.drawImage(bi, 0, 0, imageSize, imageSize, (ImageObserver)null);
        ng2d.setStroke(new BasicStroke((float)lineSize));
        ng2d.drawRect(0, 0, imageSize, imageSize);
        ng2d.dispose();
        g2d = nbi.createGraphics();
        g2d.setPaint(color);
        g2d.setFont(f);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(font.getFontContent().substring(2, 3), (float)marginW, marginH);
        marginW = (int)((double)marginW + Math.abs(rectangle.getCenterX()) * 2.0D + (font.getFontSpace() == null ? 10.0D : font.getFontSpace()));
        g2d.drawString(font.getFontContent().substring(0, 1), (float)marginW, marginH);
        marginH = (float)((double)marginH + Math.abs(rectangle.getHeight()));
        g2d.drawString(font.getFontContent().substring(3, 4), (float)oldW, marginH);
        g2d.drawString(font.getFontContent().substring(1, 2), (float)marginW, marginH);
        return nbi;
    }

    public static BufferedImage drawFourFont(BufferedImage bi, SealDTO font, int lineSize, int imageSize, int fixH, int fixW, Color color) {
        fixH += 10;
        int marginW = fixW + lineSize;
        BufferedImage nbi = new BufferedImage(imageSize, imageSize, bi.getType());
        Graphics2D ng2d = nbi.createGraphics();
        ng2d.setPaint(color);
        ng2d.drawImage(bi, 0, 0, imageSize, imageSize, (ImageObserver)null);
        ng2d.setStroke(new BasicStroke((float)lineSize));
        ng2d.drawRect(0, 0, imageSize, imageSize);
        ng2d.dispose();
        Graphics2D g2d = nbi.createGraphics();
        g2d.setPaint(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontRenderContext context = g2d.getFontRenderContext();
        Font f = new Font(font.getFontFamily(), 1, font.getFontSize());
        System.out.println("字体......" + f.getName() + "....." + f.getFamily() + "....." + f.getFontName());
        g2d.setFont(f);
        Rectangle2D rectangle = f.getStringBounds(font.getFontContent().substring(0, 1), context);
        float marginH = (float)(Math.abs(rectangle.getCenterY()) * 2.0D + (double)marginW) + (float)fixH;
        g2d.drawString(font.getFontContent().substring(2, 3), (float)marginW, marginH);
        int oldW = marginW;
        marginW = (int)((double)marginW + Math.abs(rectangle.getCenterX()) * 2.0D + (font.getFontSpace() == null ? 10.0D : font.getFontSpace()));
        g2d.drawString(font.getFontContent().substring(0, 1), (float)marginW, marginH);
        marginH = (float)((double)marginH + Math.abs(rectangle.getHeight()));
        g2d.drawString(font.getFontContent().substring(3, 4), (float)oldW, marginH);
        g2d.drawString(font.getFontContent().substring(1, 2), (float)marginW, marginH);
        return nbi;
    }
}
