package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * @author Alexey
 */
public class Atom {

    public Atom top;
    public Atom prev;
    public Atom next;
    public Atom down;
    public Atom parent;
    private int startX;
    private int startY;
    private int width;
    private int height;
    public Character symbol;
    public TypeOfAtom typeOfAtom;
    public boolean selected;
    public boolean index;

    public Atom(char symbol, TypeOfAtom typeOfAtom) {
        this.symbol = symbol;
        this.typeOfAtom = typeOfAtom;
    }

    public Atom() {
        this.typeOfAtom = TypeOfAtom.EMPTY;
    }

    public void draw(Graphics g) {
        if (selected) {
            g.setColor(Color.BLUE);
            g.drawRect(startX, startY, width, height);
        }
        switch (typeOfAtom) {
            case EMPTY:
                break;
            case IMMUTABLE:
                g.setColor(Color.GRAY);
                g.fillRect(startX + 1, startY + 1, width - 2, height - 2);
            case NORMAL:
                g.setColor(Color.BLACK);
                g.setFont(new Font("Times New Roman", Font.BOLD, height));
                g.drawString(symbol.toString(),
                        startX + width / 5, startY + 3 * height / 4);
                break;
            case FRAC_LINE:
                g.setColor(Color.BLACK);
                g.fillRect(startX, startY + height / 2 - 1,
                        width, 2);
        }
        if (typeOfAtom == TypeOfAtom.EMPTY) {
            g.setColor(Color.BLACK);
            g.drawRect(startX + 1, startY + 1, width - 2, height - 2);
        }
        if (top != null) {
            top.draw(g);
        }
        if (next != null) {
            next.draw(g);
        }
        if (down != null) {
            down.draw(g);
        }
    }

    public void computeWidth() {
        if (typeOfAtom == TypeOfAtom.FRAC_LINE) {
            int topWidth = computeWidth(top);
            int topHeight = computeWidth(down);
            this.width = Math.max(topWidth, topHeight);
        }
    }

    public static int computeWidth(Atom atom) {
        int w = 0;
        Atom current = atom;
        do {
            current.computeWidth();
            w += current.width + 1;
            current = current.next;
        } while (current != null);
        return w;
    }

    public void setSelected(int x, int y) {
        if (x >= startX && x < startX + width
                && y >= startY && y < startY + height) {
            if (typeOfAtom != TypeOfAtom.FRAC_LINE) {
                selected = true;
                return;
            }
        }
        if (top != null) {
            top.setSelected(x, y);
        }
        if (next != null) {
            next.setSelected(x, y);
        }
        if (down != null) {
            down.setSelected(x, y);
        }
    }

    public void setLocation(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return startX;
    }

    public int getY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void clearSelection() {
        selected = false;
        if (top != null) {
            top.clearSelection();
        }
        if (next != null) {
            next.clearSelection();
        }
        if (down != null) {
            down.clearSelection();
        }
    }

    public Atom getSelectedAtom() {
        if (selected) {
            return this;
        }
        if (top != null) {
            Atom atom = top.getSelectedAtom();
            if (atom != null) {
                return atom;
            }
        }
        if (next != null) {
            Atom atom = next.getSelectedAtom();
            if (atom != null) {
                return atom;
            }
        }
        if (down != null) {
            Atom atom = down.getSelectedAtom();
            if (atom != null) {
                return atom;
            }
        }
        return null;
    }

    public void setParent(Atom parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.typeOfAtom == TypeOfAtom.FRAC_LINE) {
            builder.append('/');
        } else {
            builder.append(this.symbol);
        }
        builder.append(':');
        if (top != null) {
            if (top.index) {
                builder.append('[');
                builder.append(top.toString());
                builder.append(']');
            } else {
                builder.append('{');
                builder.append(top.toString());
                builder.append('}');
            }
        }
        builder.append(',');
        if (down != null) {
            if (down.index) {
                builder.append('[');
                builder.append(down.toString());
                builder.append(']');
            } else {
                builder.append('{');
                builder.append(down.toString());
                builder.append('}');
            }
        }
        builder.append(';');
        if (next != null) {
            builder.append(next.toString());
        }
        return builder.toString();
    }
}