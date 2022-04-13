package com.mqz.seal.v1.pus.factory;

import com.mqz.seal.v1.prs.utils.ByteUtils;
import com.mqz.seal.v1.prs.utils.FileUtils;
import com.mqz.seal.v1.pus.model.SealCircle;
import com.mqz.seal.v1.pus.model.SealConfiguration;
import com.mqz.seal.v1.pus.model.SealFont;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2020/10/19
 */
public class SealRepository {

    public static final String SEAL_SAVE_PATH = "/Users/mengqizhang/Documents/mqz/seal-better/temp/public/";

    private static void drawArcFont4Circle(Graphics2D g2d, int circleRadius, SealFont font, boolean isTop) {
        if (font != null) {
            int fontTextLen = font.getFontText().length();
            int fontSize = font.getFontSize() == null ? 55 - fontTextLen * 2 : font.getFontSize();
            int fontStyle = font.getIsBold() ? 1 : 0;
            Font f = new Font(font.getFontFamily(), fontStyle, fontSize);
            FontRenderContext context = g2d.getFontRenderContext();
            Rectangle2D rectangle = f.getStringBounds(font.getFontText(), context);
            double fontSpace;
            if (font.getFontSpace() != null) {
                fontSpace = font.getFontSpace();
            } else if (fontTextLen == 1) {
                fontSpace = 0.0D;
            } else {
                fontSpace = rectangle.getWidth() / (double)(fontTextLen - 1) * 0.9D;
            }

            int marginSize = font.getMarginSize() == null ? 10 : font.getMarginSize();
            double newRadius = (double)circleRadius + rectangle.getY() - (double)marginSize;
            double radianPerInterval = 2.0D * Math.asin(fontSpace / (2.0D * newRadius));
            double fix = 0.04D;
            if (isTop) {
                fix = 0.18D;
            }

            double firstAngle;
            if (!isTop) {
                if (fontTextLen % 2 == 1) {
                    firstAngle = 4.71238898038469D - (double)(fontTextLen - 1) * radianPerInterval / 2.0D - fix;
                } else {
                    firstAngle = 4.71238898038469D - ((double)fontTextLen / 2.0D - 0.5D) * radianPerInterval - fix;
                }
            } else if (fontTextLen % 2 == 1) {
                firstAngle = (double)(fontTextLen - 1) * radianPerInterval / 2.0D + 1.5707963267948966D + fix;
            } else {
                firstAngle = ((double)fontTextLen / 2.0D - 0.5D) * radianPerInterval + 1.5707963267948966D + fix;
            }

            for(int i = 0; i < fontTextLen; ++i) {
                double theta;
                double thetaX;
                double thetaY;
                if (!isTop) {
                    theta = firstAngle + (double)i * radianPerInterval;
                    thetaX = newRadius * Math.sin(1.5707963267948966D - theta);
                    thetaY = newRadius * Math.cos(theta - 1.5707963267948966D);
                } else {
                    theta = firstAngle - (double)i * radianPerInterval;
                    thetaX = newRadius * Math.sin(1.5707963267948966D - theta);
                    thetaY = newRadius * Math.cos(theta - 1.5707963267948966D);
                }

                AffineTransform transform;
                if (!isTop) {
                    transform = AffineTransform.getRotateInstance(4.71238898038469D - theta);
                } else {
                    transform = AffineTransform.getRotateInstance(1.5707963267948966D - theta + Math.toRadians(8.0D));
                }

                Font f2 = f.deriveFont(transform);
                g2d.setFont(f2);
                g2d.drawString(font.getFontText().substring(i, i + 1), (float)((double)circleRadius + thetaX + 10.0D), (float)((double)circleRadius - thetaY + 10.0D));
            }

        }
    }

    private static void drawArcFont4Oval(Graphics2D g2d, SealCircle circle, SealFont font, boolean isTop) {
        if (font != null) {
            float radiusX = (float)circle.getWidth();
            float radiusY = (float)circle.getHeight();
            float radiusWidth = radiusX + (float)circle.getLineSize();
            float radiusHeight = radiusY + (float)circle.getLineSize();
            int fontTextLen = font.getFontText().length();
            int fontSize = font.getFontSize() == null ? 25 + (10 - fontTextLen) / 2 : font.getFontSize();
            int fontStyle = font.getIsBold() ? 1 : 0;
            Font f = new Font(font.getFontFamily(), fontStyle, fontSize);
            float totalArcAng = 180.0F;
            if (!isTop) {
                totalArcAng = 120.0F;
            }

            float minRat = 0.9F;
            double startAngle = isTop ? (double)(-90.0F - totalArcAng / 2.0F) : (double)(90.0F - totalArcAng / 2.0F);
            double step = 0.5D;
            int alCount = (int)Math.ceil((double)totalArcAng / step) + 1;
            double[] angleArr = new double[alCount];
            double[] arcLenArr = new double[alCount];
            int num = 0;
            double accArcLen = 0.0D;
            angleArr[num] = startAngle;
            arcLenArr[num] = accArcLen;
            num = num + 1;
            double angR = startAngle * 3.141592653589793D / 180.0D;
            double lastX = (double)radiusX * Math.cos(angR) + (double)radiusWidth;
            double lastY = (double)radiusY * Math.sin(angR) + (double)radiusHeight;

            double arcPer;
            for(arcPer = startAngle + step; num < alCount; arcPer += step) {
                angR = arcPer * 3.141592653589793D / 180.0D;
                double x = (double)radiusX * Math.cos(angR) + (double)radiusWidth;
                double y = (double)radiusY * Math.sin(angR) + (double)radiusHeight;
                accArcLen += Math.sqrt((lastX - x) * (lastX - x) + (lastY - y) * (lastY - y));
                angleArr[num] = arcPer;
                arcLenArr[num] = accArcLen;
                lastX = x;
                lastY = y;
                ++num;
            }

            arcPer = accArcLen / (double)fontTextLen;

            for(int i = 0; i < fontTextLen; ++i) {
                double arcL = (double)i * arcPer + arcPer / 2.0D;
                double ang = 0.0D;

                for(int p = 0; p < arcLenArr.length - 1; ++p) {
                    if (arcLenArr[p] <= arcL && arcL <= arcLenArr[p + 1]) {
                        ang = arcL >= (arcLenArr[p] + arcLenArr[p + 1]) / 2.0D ? angleArr[p + 1] : angleArr[p];
                        break;
                    }
                }

                angR = ang * 3.141592653589793D / 180.0D;
                Float x = radiusX * (float)Math.cos(angR) + radiusWidth;
                Float y = radiusY * (float)Math.sin(angR) + radiusHeight;
                double qxang = Math.atan2((double)radiusY * Math.cos(angR), (double)(-radiusX) * Math.sin(angR));
                double fxang = qxang + 1.5707963267948966D;
                int subIndex = isTop ? i : fontTextLen - 1 - i;
                String c = font.getFontText().substring(subIndex, subIndex + 1);
                FontMetrics fm = FontDesignMetrics.getMetrics(f);
                int w = fm.stringWidth(c);
                int h = fm.getHeight();
                if (isTop) {
                    x = x + (float)h * minRat * (float)Math.cos(fxang);
                    y = y + (float)h * minRat * (float)Math.sin(fxang);
                    x = x + (float)(-w) / 2.0F * (float)Math.cos(qxang);
                    y = y + (float)(-w) / 2.0F * (float)Math.sin(qxang);
                } else {
                    x = x + (float)h * minRat * (float)Math.cos(fxang);
                    y = y + (float)h * minRat * (float)Math.sin(fxang);
                    x = x + (float)w / 2.0F * (float)Math.cos(qxang);
                    y = y + (float)w / 2.0F * (float)Math.sin(qxang);
                }

                AffineTransform affineTransform = new AffineTransform();
                affineTransform.scale(0.8D, 1.0D);
                if (isTop) {
                    affineTransform.rotate(Math.toRadians(fxang * 180.0D / 3.141592653589793D - 90.0D), 0.0D, 0.0D);
                } else {
                    affineTransform.rotate(Math.toRadians(fxang * 180.0D / 3.141592653589793D + 180.0D - 90.0D), 0.0D, 0.0D);
                }

                Font f2 = f.deriveFont(affineTransform);
                g2d.setFont(f2);
                g2d.drawString(c, x.intValue() + 10, y.intValue() + 10);
            }

        }
    }

    private static BufferedImage buildSeal(SealConfiguration conf) throws Exception {
        BufferedImage bi = new BufferedImage(conf.getImageSize(), conf.getImageSize(), 6);
        Graphics2D g2d = bi.createGraphics();
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);
        g2d.setComposite(AlphaComposite.getInstance(2, 0.0F));
        g2d.fillRect(0, 0, conf.getImageSize(), conf.getImageSize());
        g2d.setComposite(AlphaComposite.getInstance(2, 1.0F));
        g2d.setPaint(conf.getBackgroundColor());
        if (conf.getBorderCircle() != null) {
            drawCircle(g2d, conf.getBorderCircle(), 10, 10);
            int borderCircleWidth = conf.getBorderCircle().getWidth();
            int borderCircleHeight = conf.getBorderCircle().getHeight();
            if (borderCircleHeight != borderCircleWidth) {
                drawArcFont4Oval(g2d, conf.getBorderCircle(), conf.getMainFont(), true);
            } else {
                drawArcFont4Circle(g2d, borderCircleHeight, conf.getMainFont(), true);
            }

            if (borderCircleHeight != borderCircleWidth) {
                drawArcFont4Oval(g2d, conf.getBorderCircle(), conf.getViceFont(), false);
            } else {
                drawArcFont4Circle(g2d, borderCircleHeight, conf.getViceFont(), false);
            }

            drawFont(g2d, (borderCircleWidth + 10) * 2, (borderCircleHeight + 10) * 2, conf.getCenterFont());
            drawFont(g2d, (borderCircleWidth + 10) * 2, (borderCircleHeight + 10) * 2, conf.getTitleFont());
            g2d.dispose();
            return bi;
        } else {
            throw new Exception("BorderCircle can not null！");
        }
    }

    private static void drawFont(Graphics2D g2d, int circleWidth, int circleHeight, SealFont font) {
        if (font != null) {
            int fontTextLen = font.getFontText().length();
            int fontSize = font.getFontSize() == null ? 55 - fontTextLen * 2 : font.getFontSize();
            int fontStyle = font.getIsBold() ? 1 : 0;
            Font f = new Font(font.getFontFamily(), fontStyle, fontSize);
            g2d.setFont(f);
            FontRenderContext context = g2d.getFontRenderContext();
            String[] fontTexts = font.getFontText().split("\n");
            float marginSize;
            if (fontTexts.length > 1) {
                int y = 0;
                String[] var11 = fontTexts;
                int var12 = fontTexts.length;

                int var13;
                for(var13 = 0; var13 < var12; ++var13) {
                    String fontText = var11[var13];
                    y = (int)((double)y + Math.abs(f.getStringBounds(fontText, context).getHeight()));
                }

                marginSize = 10.0F + (float)(circleHeight / 2 - y / 2);
                String[] var19 = fontTexts;
                var13 = fontTexts.length;

                for(int var20 = 0; var20 < var13; ++var20) {
                    String fontText = var19[var20];
                    Rectangle2D rectangle2D = f.getStringBounds(fontText, context);
                    g2d.drawString(fontText, (float)((double)(circleWidth / 2) - rectangle2D.getCenterX() + 1.0D), marginSize);
                    marginSize = (float)((double)marginSize + Math.abs(rectangle2D.getHeight()));
                }
            } else {
                Rectangle2D rectangle2D = f.getStringBounds(font.getFontText(), context);
                marginSize = font.getMarginSize() == null ? (float)((double)(circleHeight / 2) - rectangle2D.getCenterY()) : (float)((double)(circleHeight / 2) - rectangle2D.getCenterY()) + (float)font.getMarginSize();
                g2d.drawString(font.getFontText(), (float)((double)(circleWidth / 2.2) - rectangle2D.getCenterX() + 1.0D), marginSize);
            }

        }
    }

    private static void drawCircle(Graphics2D g2d, SealCircle circle, int x, int y) {
        if (circle != null) {
            int lineSize = circle.getLineSize() == null ? circle.getHeight() * 2 / 35 : circle.getLineSize();
            g2d.setStroke(new BasicStroke((float)lineSize));
            g2d.drawOval(x, y, circle.getWidth() * 2, circle.getHeight() * 2);
        }
    }


    /**
     * 具体处理方法
     * @param configuration
     * @return
     * @throws Exception
     */
    public static String dealWith(SealConfiguration configuration) throws Exception {
        BufferedImage bi = buildSeal(configuration);
        byte[] bs = ByteUtils.buildBytes(bi);
        String fullPath = ByteUtils.storeBytes(bs,SEAL_SAVE_PATH);
        String base64 = FileUtils.fileToBase64(new File(fullPath));
        return base64;

    }

}
