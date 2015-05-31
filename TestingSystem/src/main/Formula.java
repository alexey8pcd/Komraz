package main;

import java.awt.Graphics;
import java.util.Iterator;

/**
 * @author Alexey
 */
public class Formula implements Iterable<Atom> {

    private Atom root;
    private Atom last;
    private int startX, startY;
    private final int BASE_ATOM_WIDTH = 60;
    private final int BASE_ATOM_HEIGHT = 60;

    public Formula(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public Formula(Formula formula) {
        this.startX = formula.startX;
        this.startY = formula.startY;
        root = new Atom(formula.root);
        last = root;
        while (last != null) {
            last = last.next;
        }
    }

    public Formula(Atom root, int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        this.root = root;
        last = root;
        while (last != null) {
            last = last.next;
        }
    }

    public void moveOnVertical(int offset) {
        for (Atom atom : this) {
            atom.setLocation(atom.getX(), atom.getY() + offset);
        }
    }

    public void moveOnHorizontal(int offset) {
        for (Atom atom : this) {
            atom.setLocation(atom.getX() + offset, atom.getY());
        }
    }

    public void addNextElement(char symbol, boolean mutable) {
        TypeOfAtom typeOfAtom
                = mutable == true ? TypeOfAtom.IMMUTABLE : TypeOfAtom.NORMAL;
        if (root == null) {
            root = new Atom(symbol, typeOfAtom);
            root.setLocation(startX, startY);
            root.setSize(BASE_ATOM_WIDTH, BASE_ATOM_HEIGHT);
            last = root;
        } else {
            last.next = new Atom(symbol, typeOfAtom);
            last.next.setLocation(last.getX() + last.getWidth() + 1, last.getY());
            last.next.setSize(last.getWidth(), last.getHeight());
            last.next.prev = last;
            last = last.next;
        }
    }

    public void addEmptyElement() {
        if (root == null) {
            root = new Atom();
            root.setLocation(startX, startY);
            root.setSize(BASE_ATOM_WIDTH, BASE_ATOM_HEIGHT);
            last = root;
        } else {
            last.next = new Atom();
            last.next.setLocation(last.getX() + last.getWidth() + 1, last.getY());
            last.next.setSize(last.getWidth(), last.getHeight());
            last.next.prev = last;
            last = last.next;
        }

    }

    public void draw(Graphics g) {
        root.draw(g);
    }

    public boolean isSelectedEmpty() {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            return selected.typeOfAtom == TypeOfAtom.EMPTY;
        }
        return false;
    }

    public boolean replaceSelected(char symbol) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            if (selected.typeOfAtom != TypeOfAtom.IMMUTABLE) {
                if (selected.symbol == null || selected.symbol != symbol) {
                    selected.symbol = symbol;
                    selected.typeOfAtom = TypeOfAtom.NORMAL;
                    return true;
                }
            }
        }
        return false;
    }

    public void placeInSelected(char symbol, boolean mutable) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            if (selected.typeOfAtom != TypeOfAtom.IMMUTABLE) {
                selected.symbol = symbol;
                selected.typeOfAtom = mutable == true ? TypeOfAtom.IMMUTABLE
                        : TypeOfAtom.NORMAL;
            }
        }
    }

    public void makeSelectedEmpty() {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            if (selected.typeOfAtom == TypeOfAtom.NORMAL) {
                selected.setAsEmpty();
            }
        }
    }

    /**
     * Создает новый элемент формулы и помещает его сразу после выделенного
     * элемента.
     *
     * @param symbol
     * @param mutable
     */
    public void addAfterSelected(char symbol, boolean mutable) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.setLocation(selected.getX() + selected.getWidth() + 1, selected.getY());
            atom.setSize(selected.getWidth(), selected.getHeight());
            atom.symbol = symbol;
            atom.typeOfAtom = mutable == true ? TypeOfAtom.IMMUTABLE : TypeOfAtom.NORMAL;
            updateLinksAfterSelected(selected, atom);
        }
    }

    private void updateLinksAfterSelected(Atom selected, Atom atom) {
        if (selected.next == null) {
            selected.next = atom;
            atom.prev = selected;
        } else {
            //установить связи
            atom.next = selected.next;
            selected.next.prev = atom;
            selected.next = atom;
            atom.prev = selected;
        }
        recalculatePositions(selected);
    }

    public void addEmptyAfterSelected() {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.setLocation(selected.getX() + selected.getWidth() + 1, selected.getY());
            atom.setSize(selected.getWidth(), selected.getHeight());
            updateLinksAfterSelected(selected, atom);
        }
    }

    public void addBeforeSelected(char symbol, boolean mutable) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.setLocation(selected.getX(), selected.getY());
            atom.setSize(selected.getWidth(), selected.getHeight());
            atom.symbol = symbol;
            atom.typeOfAtom = mutable == true
                    ? TypeOfAtom.IMMUTABLE : TypeOfAtom.NORMAL;
            updateLinksBeforeSelected(selected, atom);
        }
    }

    public void addEmptyBeforeSelected() {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.setLocation(selected.getX(), selected.getY());
            atom.setSize(selected.getWidth(), selected.getHeight());
            updateLinksBeforeSelected(selected, atom);
        }
    }

    private void updateLinksBeforeSelected(Atom selected, Atom atom) {
        if (selected.prev == null) {
            selected.prev = atom;
            atom.next = selected;
            atom.parent = selected.parent;
            if (selected.parent != null) {
                //если выбранный является верхним 
                if (selected.parent.top == selected) {
                    selected.parent.top = atom;
                }
                if (selected.parent.down == selected) {
                    selected.parent.down = atom;
                }
                selected.parent = null;
            }
            if (selected == root) {
                root = atom;
            }
        } else {
            //установить связи
            selected.prev.next = atom;
            atom.prev = selected.prev;
            atom.next = selected;
            selected.prev = atom;
        }
        recalculatePositions(atom);
    }

    private void computeWidth(Atom atom) {
        Atom current = atom;
        do {
            current.computeWidth();
            current = current.next;
        } while (current != null);
    }

    private void recalculatePositions(Atom atom) {
        Atom current = atom;
        recalculatePositionChildren(current);
        while (current.next != null) {
            Atom next = current.next;
            next.setLocation(current.getX() + current.getWidth() + 1,
                    current.getY());
            current = next;
            recalculatePositionChildren(current);
        }
    }

    private void recalculatePositionChildren(Atom current) {
        int xPosition;
        int yPosition;
        if (current.top != null) {
            if (current.top.index) {
                xPosition = current.getX() + current.getWidth() / 4 * 3;
            } else {
                xPosition = current.getX() + (current.getWidth()
                        - Atom.computeWidth(current.top)) / 2;
            }

            if (current.top.symbol != null && current.top.symbol == '→') {
                yPosition = current.getY() - current.getHeight() / 5;
            } else {
                yPosition = current.getY() - 2;
            }
            current.top.setLocation(xPosition, yPosition);
            recalculatePositions(current.top);
        }
        if (current.down != null) {
            if (current.down.index) {
                xPosition = current.getX() + current.getWidth() / 4 * 3;
            } else {
                xPosition = current.getX() + (current.getWidth()
                        - Atom.computeWidth(current.down)) / 2;
            }
            yPosition = current.getY() + current.getHeight() - current.down.getHeight() + 2;
            current.down.setLocation(xPosition, yPosition);
            recalculatePositions(current.down);
        }
    }

    public boolean isSelectedNormal() {
        return root.getSelectedAtom().typeOfAtom
                == TypeOfAtom.NORMAL;
    }

    public void update() {
        root.setLocation(startX, startY);
        computeWidth(root);
        recalculatePositions(root);
    }

    public void setSelectedAtom(int x, int y) {
        root.clearSelection();
        root.setSelected(x, y);
    }

    public void addPowerInSelected() {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            selected.top = new Atom();
            selected.top.setSize(selected.getWidth() / 2,
                    selected.getHeight() / 2);
            selected.top.index = true;
            selected.top.parent = selected;
        }
        root.clearSelection();
    }

    public void addLowerIndexInSelected() {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            selected.down = new Atom();
            selected.down.setSize(selected.getWidth() / 2,
                    selected.getHeight() / 2);
            selected.down.index = true;
            selected.down.parent = selected;
        }
        root.clearSelection();
    }

    public void addFractionInSelected() {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            int childHeight = selected.getHeight() / 2;
            int childWidth = selected.getWidth() / 2;
            selected.top = new Atom();
            selected.top.setSize(childWidth, childHeight);
            selected.top.parent = selected;
            selected.down = new Atom();
            selected.down.setSize(childWidth, childHeight);
            selected.down.parent = selected;
            selected.typeOfAtom = TypeOfAtom.FRAC_LINE;
        }
        root.clearSelection();
    }

    public void addVectorInSelected() {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            selected.top = new Atom('→', TypeOfAtom.IMMUTABLE);
            selected.top.setSize(selected.getWidth() / 2,
                    selected.getHeight() / 2);
            selected.top.parent = selected;
        }
        root.clearSelection();
    }

    @Override
    public String toString() {
        return root.toString();
    }

    @Override
    public Iterator<Atom> iterator() {
        return new FormulaIterator(this, root);
    }
}
