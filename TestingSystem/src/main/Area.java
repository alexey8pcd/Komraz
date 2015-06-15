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

    private int x;
    private int y;
    private int size;
    private boolean selected;
    private BufferedImage image;
    private int number;
    private Kartinka kartinka;
    public static final int DEFAULT_SIZE = 90;
    public static final Font FONT = new Font("Arial", Font.BOLD, 36);

    public Area(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public Area(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = DEFAULT_SIZE;
    }

    public Area(Area areaToCopy) {
        this.x = areaToCopy.x;
        this.y = areaToCopy.y;
        this.image = areaToCopy.image;
        this.kartinka = areaToCopy.kartinka;
        this.number = areaToCopy.number;
        this.size = areaToCopy.size;
    }

    public Area(int x, int y, int size, int number,
            Kartinka kartinka, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.kartinka = kartinka;
        this.size = size;
        this.number = number;
    }

    public void setSelected(int x, int y) {
        selected = containPoint(x, y);
    }

    public boolean containPoint(int x, int y) {
        return x >= this.x && x < this.x + size
                && y >= this.y && y < this.y + size;
    }

    public void draw(Graphics g, Color areaColor,
            boolean showNumber, Color numberColor) {
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
            g.drawString(String.valueOf(number), x + 10, y + size / 4);
        }
    }

    public void setData(Kartinka kartinka, BufferedImage image) {
        this.kartinka = kartinka;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Kartinka getKartinka() {
        return kartinka;
    }

    public void setKartinka(Kartinka kartinka) {
        this.kartinka = kartinka;
    }
}
