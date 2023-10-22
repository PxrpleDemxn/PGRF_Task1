package Services;

import Services.Interface.IRBIService;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RBIService implements IRBIService {
    private final BufferedImage img;
    private int color;

    public BufferedImage getImg() {
        return img;
    }

    public RBIService(int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void repaint(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
    }

    public Graphics getGraphics() {
        return img.getGraphics();
    }

    @Override
    public void clear() {
        Graphics graphics = getGraphics();

        graphics.setColor(new Color(color));
        graphics.fillRect(0, 0, img.getWidth() - 1, img.getHeight() - 1);
    }

    @Override
    public void setClearColor(int color) {
        this.color = color;
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

    @Override
    public int getPixel(int x, int y) {
        return img.getRGB(x, y);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        img.setRGB(x, y, color);
    }
}
