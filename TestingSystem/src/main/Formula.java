package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey
 */
public class Formula {

    public static final int START_X_DEFAULT = 20;
    public static final int START_Y_DEFAULT = 150;
    public static final int SIZE_DEFAULT = 40;
    private int startX;
    private int startY;
    private int size;
    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);

    private class Atom {

        public String text;
        public int x;
        public int y;
        public int size;
        public boolean replaceble;

        public Atom(String text, int y, int size) {
            this.text = text;
            this.y = y;
            this.size = size;
            replaceble = true;
        }

        public Atom(Atom toCopy) {
            this.text = toCopy.text;
            this.x = toCopy.x;
            this.y = toCopy.y;
            this.replaceble = toCopy.replaceble;
            this.size = toCopy.size;
        }

        public void show(Graphics g, boolean selected) {
            g.setColor(Color.red);
            if (replaceble) {
                g.drawRect(x + 1, y + 1, size - 2, size - 2);
            } else {
                g.setColor(Color.gray);
                g.fillRect(x + 1, y + 1, size - 2, size - 2);
            }

            if (selected) {
                g.setColor(Color.blue);
                g.drawRect(x, y, size, size);
            }
            g.setColor(Color.black);
            g.setFont(DEFAULT_FONT);
            if (text != null) {
                g.drawString(text, x + size / 3, y + size / 2);
            }
        }

        public boolean containPoint(int x, int y) {
            return x >= this.x
                    && x < this.x + size
                    && y >= this.y
                    && y < this.y + size;
        }
    }
    private final List<Atom> elements;
    private int selectedIndex;

    public Formula() {
        elements = new ArrayList<>();
        startX = START_X_DEFAULT;
        startY = START_Y_DEFAULT;
        size = SIZE_DEFAULT;
        selectedIndex = -1;
    }

    public Formula(Formula toCopy) {
        elements = new ArrayList<>();
        for (Atom atom : toCopy.elements) {
            elements.add(new Atom(atom));
        }
        startX = toCopy.startX;
        startY = toCopy.startY;
        size = toCopy.size;
        selectedIndex = toCopy.selectedIndex;
    }

    public int getStartX() {
        return startX;
    }

    public void setSelectedAtom(int x, int y) {
        selectedIndex = -1;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).containPoint(x, y)) {
                selectedIndex = i;
                return;
            }
        }
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void replaceAtomText(String text, int index) {
        if (index >= 0 && index < elements.size()) {
            Atom toReplace = elements.get(index);
            if (toReplace.replaceble) {
                toReplace.text = text;
            }

        }
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Formula(int startX, int startY, int size) {
        elements = new ArrayList<>();
        this.startX = startX;
        this.startY = startY;
        this.size = size;
    }

    private void countXPositions() {
        for (int i = 0; i < elements.size(); i++) {
            Atom atom = elements.get(i);
            atom.x = startX + i * atom.size;
        }
    }

    public boolean currentIsEmpty() {
        if (selectedIndex == -1) {
            return false;
        } else {
            return elements.get(selectedIndex).text == null;
        }
    }

    public void addEmpty() {
        add(null, true);
    }

    public void addEmptyIn(int index) {
        insertIn(null, index, true);
    }

    public void add(String text, boolean replaceble) {
        Atom atom = new Atom(text, startY, size);
        atom.replaceble = replaceble;
        elements.add(atom);
        countXPositions();
    }

    /**
     * Помещает элемент в формулу по заданному индексу
     *
     * @param text текст, который содержит элемент формулы
     * @param index индекс от 0 до количества элементов в формуле, Элемент не
     * вставляется, если индекс превышает количество элементов в формуле
     * @param replaceble можно ли заменить символ в формуле(true можно, false
     * нельзя)
     */
    public void insertIn(String text, int index, boolean replaceble) {
        if (index >= 0 || index < elements.size()) {
            Atom atom = new Atom(text, startY, size);
            atom.replaceble = replaceble;
            elements.add(index, atom);
            countXPositions();
        }
    }

    public void show(Graphics g) {
        for (int i = 0; i < elements.size(); i++) {
            if (selectedIndex == i) {
                elements.get(i).show(g, true);
            } else {
                elements.get(i).show(g, false);
            }
        }
    }
}
