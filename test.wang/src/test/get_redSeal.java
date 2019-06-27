package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class get_redSeal {
    public static void main(String[] args) throws IOException {
        Image img = ImageIO.read(new File("/Users/xmly/Downloads/red_seal.png"));
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
        g2D.drawImage(img, 0, 0, null);
        g2D.dispose();
        int alpha = 0;
        for (int y = bufferedImage.getMinY(); y < bufferedImage.getHeight(); y++) {
            for (int x = bufferedImage.getMinX(); x < bufferedImage.getWidth(); x++) {
                int pixel = bufferedImage.getRGB(x, y);
                int r = ((pixel & 0x00ff0000) >>> 16);
                int g = ((pixel & 0x0000ff00) >>> 8);
                int b = ((pixel & 0x000000ff));
                if (r < 245 && g < 245 && b < 245) {
                    //非白色像素改为红色
                    int red = Color.RED.getRGB();
                    bufferedImage.setRGB(x, y, red);
                } else {
                    //白色像素改为透明
                    pixel = (alpha << 24) | (pixel & 0x00ffffff);
                    bufferedImage.setRGB(x, y, pixel);
                }
            }
        }
        ImageIO.write(bufferedImage, "png", new File("/Users/xmly/Downloads/test2.png"));
    }
}
