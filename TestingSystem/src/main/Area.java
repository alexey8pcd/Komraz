package main;

import entities.Kartinka;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Алексей
 */
public class Area {

    public int x;
    public int y;
    public int size;
    public boolean showNumber;
    public boolean selected;
    public BufferedImage image;
    public int number;
    public Kartinka kartinka;
    public static final int DEFAULT_SIZE = 90;
    public static final Font FONT = new Font("Arial", Font.BOLD, 36);

    public Area(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        showNumber = true;
    }

    public void setSelected(int x, int y) {
        selected = containPoint(x, y);
    }

    public boolean containPoint(int x, int y) {
        return x >= this.x && x < this.x + size
                && y >= this.y && y < this.y + size;
    }

    public void draw(Graphics g, Color areaColor, Color numberColor) {
        if (selected) {
            g.setColor(Color.BLUE);
            g.fillRect(x - 2, y - 2, size + 4, size + 4);
        }
        g.setColor(areaColor);
        g.fillRect(x, y, size, size);
        if (image != null) {
            g.drawImage(image, x, y, size, size, null);
        }
        if (showNumber) {
            g.setColor(numberColor);
            g.setFont(FONT);
            g.drawString(String.valueOf(number), x + 10, y + size / 2);
        }

    }
}
