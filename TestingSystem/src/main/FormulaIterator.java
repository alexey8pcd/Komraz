package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alexey
 */
public class FormulaIterator implements Iterator {

    private Formula formula;
    private List<Atom> list;
    private int index;
    private int size;

    FormulaIterator(Formula formula, Atom root) {
        this.formula = formula;
        index = 0;        
        list = new ArrayList<>();
        createList(root);
        size = list.size();
    }

    private void createList(Atom atom) {
        list.add(atom);
        if (atom.top != null) {
            createList(atom.top);
        }
        if (atom.down != null) {
            createList(atom.down);
        }
        if (atom.next != null) {
            createList(atom.next);
        }
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public Atom next() {
        return list.get(index++);
    }

}
