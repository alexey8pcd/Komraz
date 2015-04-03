package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

/**
 * @author Alexey
 */
public class Formula {

    private int startX = 20;
    private int startY = 150;
    private int sizeDefault = 40;

    private class Atom {

        public String text;
        public int x;
        public int y;
        public int size;
        public Font font = new Font("Tahoma", Font.BOLD, size / 20);

        public Atom(String text, int y, int size) {
            this.text = text;
            this.y = y;
            this.size = size;
        }

        public void show(Graphics g, int x) {
            g.setColor(Color.red);
            g.drawRect(x, y, size, size);
            g.setColor(Color.black);
            g.setFont(font);
            g.drawString(text, x + size / 3, y + size / 3);
        }
    }
    private List<Atom> elements;

    public void add(int index, String text) {
        elements.add(index, new Atom(text, startY, sizeDefault));
    }

    public void show(Graphics g) {
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).show(g, startX + i * sizeDefault);
        }
    }
}
