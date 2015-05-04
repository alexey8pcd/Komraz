package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Алексей
 */
public class Area {

    public int x;
    public int y;
    public int size;
    public boolean selected;
    public BufferedImage image;
    public int number;

    public Area(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void setSelected(int x, int y) {
        selected = containPoint(x, y);
    }

    public boolean containPoint(int x, int y) {
        return x >= this.x && x < this.x + size
                && y >= this.y && y < this.y + size;
    }

    public void draw(Graphics g, Color areaColor) {
        if (selected) {
            g.setColor(Color.BLUE);
            g.fillRect(x - 2, y - 2, size + 4, size + 4);
        }
        g.setColor(areaColor);
        g.fillRect(x, y, size, size);
        if (image != null) {
            g.drawImage(image, x, y, size, size, null);
        }
    }
}
