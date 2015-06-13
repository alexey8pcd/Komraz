package main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alexey
 */
public class Formula implements Iterable<Atom> {

    private Atom root;
    private Atom last;
    private final int START_X, START_Y;
    public static final int BASE_ATOM_WIDTH = 50;
    public static final int BASE_ATOM_HEIGHT = 50;

    public Formula(int startX, int startY) {
        this.START_X = startX;
        this.START_Y = startY;
    }

    public Formula(Formula formula) {
        this.START_X = formula.START_X;
        this.START_Y = formula.START_Y;
        root = new Atom(formula.root);
        last = root;
        while (last.next != null) {
            last = last.next;
        }
    }

    public Formula(Atom root, int startX, int startY) {
        this.START_X = startX;
        this.START_Y = startY;
        this.root = root;
        last = root;
        while (last.next != null) {
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

    public void addNextElement(char symbol, boolean mutable, int width) {
        TypeOfAtom typeOfAtom
                = mutable == true ? TypeOfAtom.IMMUTABLE : TypeOfAtom.NORMAL;
        if (root == null) {
            root = new Atom(symbol, typeOfAtom);
            root.setLocation(START_X, START_Y);
            root.setSize(width, BASE_ATOM_HEIGHT);
            last = root;
        } else {
            last.next = new Atom(symbol, typeOfAtom);
            last.next.setLocation(last.getX() + last.getWidth() + 1, last.getY());
            last.next.setSize(width, last.getHeight());
            last.next.prev = last;
            last = last.next;
        }
    }

    public void addEmptyElement(int width) {
        if (root == null) {
            root = new Atom();
            root.setLocation(START_X, START_Y);
            root.setSize(width, BASE_ATOM_HEIGHT);
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

    public void placeInSelected(char symbol, boolean immutable) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            if (selected.typeOfAtom != TypeOfAtom.IMMUTABLE) {
                selected.symbol = symbol;
                selected.typeOfAtom = immutable == true ? TypeOfAtom.IMMUTABLE
                        : TypeOfAtom.NORMAL;
            }
        }
    }

    public void placeInSelected(char symbol, boolean immutable, int width) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            if (selected.typeOfAtom != TypeOfAtom.IMMUTABLE) {
                selected.symbol = symbol;
                selected.setSize(width, selected.getHeight());
                selected.typeOfAtom = immutable == true ? TypeOfAtom.IMMUTABLE
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
     * @param immutable
     * @param width
     */
    public void addAfterSelected(char symbol, boolean immutable, int width) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.setLocation(selected.getX() + selected.getWidth() + 1, selected.getY());
            atom.setSize(selected.getWidth(), selected.getHeight());
            atom.symbol = symbol;
            atom.typeOfAtom = immutable == true ? TypeOfAtom.IMMUTABLE : TypeOfAtom.NORMAL;
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

    public void addEmptyAfterSelected(int width) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.setLocation(selected.getX() + selected.getWidth() + 1, selected.getY());
            atom.setSize(selected.getWidth(), selected.getHeight());
            updateLinksAfterSelected(selected, atom);
        }
    }

    public void addBeforeSelected(char symbol, boolean immutable, int width) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.setLocation(selected.getX(), selected.getY());
            atom.setSize(selected.getWidth(), selected.getHeight());
            atom.symbol = symbol;
            atom.typeOfAtom = immutable == true
                    ? TypeOfAtom.IMMUTABLE : TypeOfAtom.NORMAL;
            updateLinksBeforeSelected(selected, atom);
        }
    }

    public void addEmptyBeforeSelected(int width) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.setLocation(selected.getX(), selected.getY());
            atom.setSize(selected.getWidth(), selected.getHeight());
            updateLinksBeforeSelected(selected, atom);
        }
    }

    /**
     * Добавляет элемент начала корня перед выбранным
     *
     * @param sqrtNumber
     */
    public void addSqrtStartBeforeSelected(int sqrtNumber) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.typeOfAtom = TypeOfAtom.SQRT_START;
            atom.sqrtNumber = sqrtNumber;
            atom.setLocation(selected.getX(), selected.getY());
            atom.setSize(0, selected.getHeight());
            updateLinksBeforeSelected(selected, atom);
        }
    }

    /**
     * Добавляет невидимый элемент окончания корня после выбранного
     *
     * @param sqrtNumber
     */
    public void addSqrtEndAfterSelected(int sqrtNumber) {
        Atom selected = root.getSelectedAtom();
        if (selected != null) {
            Atom atom = new Atom();
            atom.typeOfAtom = TypeOfAtom.SQRT_END;
            atom.sqrtNumber = sqrtNumber;
            atom.setLocation(selected.getX(), selected.getY());
            atom.setSize(0, selected.getHeight());
            updateLinksAfterSelected(selected, atom);
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
            if (current.top.asIndex) {
                xPosition = current.getX() + current.getWidth() / 4 * 3;
            } else {
                xPosition = current.getX() + (current.getWidth()
                        - Atom.computeWidth(current.top)) / 2;
            }

            if (current.top.symbol != null && current.top.symbol == '→') {
                yPosition = current.getY() - current.getHeight() / 4;
            } else {
                yPosition = current.getY() - 3;
            }
            current.top.setLocation(xPosition, yPosition);
            recalculatePositions(current.top);
        }
        if (current.down != null) {
            if (current.down.asIndex) {
                xPosition = current.getX() + current.getWidth() / 4 * 3;
            } else {
                xPosition = current.getX() + (current.getWidth()
                        - Atom.computeWidth(current.down)) / 2;
            }
            yPosition = current.getY() + current.getHeight() - 
                    current.down.getHeight() + 3;
            current.down.setLocation(xPosition, yPosition);
            recalculatePositions(current.down);
        }
    }

    public TypeOfAtom getTypeOfSelectedAtom() {
        if (root.getSelectedAtom() != null) {
            return root.getSelectedAtom().typeOfAtom;
        }
        return null;
    }

    public void update() {
        root.setLocation(START_X, START_Y);
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
            selected.top.asIndex = true;
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
            selected.down.asIndex = true;
            selected.down.parent = selected;
        }
        root.clearSelection();
    }

    public void clearSelection() {
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
            selected.symbol = '/';
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

    public boolean equalFormula(Formula formula) {
        if (formula == null) {
            return false;
        }
        List<Formula> splittedFirst = new Formula(this).split('=');
        List<Formula> splittedSecond = new Formula(formula).split('=');
        boolean i1 = splittedFirst.get(0).equal2(splittedSecond.get(0));
        boolean i2 = splittedFirst.get(1).equal2(splittedSecond.get(1));
        boolean i3 = splittedFirst.get(1).equal2(splittedSecond.get(0));
        boolean i4 = splittedFirst.get(0).equal2(splittedSecond.get(1));
        return i1 && i2 || i3 && i4;
    }

    public void addNextAtom(Atom atom) {
        if (last == null) {
            root = last = atom;
        } else {
            last.next = atom;
            if (atom != null) {
                last = last.next;
            }
        }
    }

    private List<Formula> split(Character regex) {
        List<Formula> result = new ArrayList<>();
        //слева от regex для первой формулы
        Formula current = new Formula(START_X, START_Y);
        Atom pointer = root;
        while (pointer != null) {
            if (!regex.equals(pointer.symbol)) {
                current.addNextAtom(pointer);
            } else {
                current.addNextAtom(null);
                result.add(current);
                current = new Formula(START_X, START_Y);
            }
            pointer = pointer.next;
        }
        result.add(current);
        return result;
    }

    public boolean equal2(Formula formula) {
        if (formula == null) {
            return false;
        }
        //составить список слагаемых
        List<Formula> summands1 = new Formula(this).split('+');
        List<Formula> summands2 = new Formula(formula).split('+');
        for (Formula summand1 : summands1) {
            boolean contains = false;
            for (Formula summand2 : summands2) {
                if (summand1.equal3(summand2)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                return false;
            }
        }
        return true;
    }

    private boolean equal3(Formula formula) {
        if (formula == null) {
            return false;
        }
        //составить список множителей
        List<Formula> multipliers1 = new Formula(this).split('•');
        List<Formula> multipliers2 = new Formula(formula).split('•');
        for (Formula multiplier1 : multipliers1) {
            boolean contains = false;
            for (Formula multiplier2 : multipliers2) {
                if (multiplier1.equal4(multiplier2)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                return false;
            }
        }
        return true;
    }

    private boolean equal4(Formula formula) {
        if (formula == null) {
            return false;
        }
        Atom head1 = root;
        Atom head2 = formula.root;
        while (head1 != null) {
            if (!head1.equalAtom(head2)) {
                return false;
            }
            if (head1.next != null && head2.next == null
                    || head1.next == null && head2.next != null) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

}
