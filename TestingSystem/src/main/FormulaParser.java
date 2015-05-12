package main;

import java.text.ParseException;

/**
 * @author Alexey
 */
public class FormulaParser {

    private String transcription;
    private int currentPP;
    private Atom root;
    private Atom currentAtom;
    /**
     *
     * @param transcription
     * @throws ParseException
     */
    public void parseFormula(String transcription) throws ParseException {
        this.transcription = transcription;
        formula();        
    }

    private void formula() throws ParseException {
        if (preScan() != null) {
            if (currentAtom == null) {
                currentAtom = elementOfFormula(null, null, false);
                root = currentAtom;
            } else {
                currentAtom.next = elementOfFormula(null, currentAtom, false);
                currentAtom = currentAtom.next;
            }
            formula();
        }
    }

    private boolean isArifmeticSymbol(char c) {
        switch (c) {
            case '+':
                return true;
            case '-':
                return true;
            case '/':
                return true;
            case '•':
                return true;
            case '→':
                return true;
            case '=':
                return true;
        }
        return false;
    }

    private Atom elementOfFormula(Atom parent, Atom prev, boolean index) throws ParseException {
        char c = scan();
        Atom atom;
        if (Character.isLetterOrDigit(c) || isArifmeticSymbol(c)) {
            //создать элемент дерева
            TypeOfAtom typeOfAtom = TypeOfAtom.NORMAL;
            if (c == '/') {
                typeOfAtom = TypeOfAtom.FRAC_LINE;
            }
            atom = new Atom(c, typeOfAtom);
            atom.index = index;
            atom.parent = parent;
            atom.prev = prev;
        } else {
            throw new ParseException(String.valueOf(c)
                    + " не является буквой или цифрой", currentPP);
        }
        c = scan();
        if (c != ':') {
            throw new ParseException("ожидалось : найдено " + c, currentPP);
        }
        c = preScan();
        //создать верхнего потомка
        if (c == '[' || c == '{') {
            atom.top = createTopChild(atom);
        }
        c = scan();
        if (c != ',') {
            throw new ParseException("ожидалось , найдено " + c, currentPP);
        }
        //создать нижнего потомка
        c = preScan();
        if (c == '[' || c == '{') {
            atom.down = createDownChild(atom);
        }
        c = scan();
        if (c != ';') {
            throw new ParseException("ожидалось ; найдено " + c, currentPP);
        }
        return atom;
    }

    private Atom createDownChild(Atom parent) throws ParseException {
        //создать верхнего потомка
        Atom atom = null;
        char c = scan();
        if (c == '[') {
            atom = elementOfFormula(parent, null, true);
            c = scan();
            if (c != ']') {
                throw new ParseException("ожидалось ] найдено " + c, currentPP);
            }
        } else if (c == '{') {
            atom = elementOfFormula(parent, null, false);
            c = scan();
            if (c != '}') {
                throw new ParseException("ожидалось } найдено " + c, currentPP);
            }
        }
        return atom;
    }

    private Atom createTopChild(Atom parent) throws ParseException {
        //создать верхнего потомка
        Atom atom = null;
        char c = scan();
        if (c == '[') {
            atom = elementOfFormula(parent, null, true);
            c = scan();
            if (c != ']') {
                throw new ParseException("ожидалось ] найдено " + c, currentPP);
            }
        } else if (c == '{') {
            atom = elementOfFormula(parent, null, false);
            c = scan();
            if (c != '}') {
                throw new ParseException("ожидалось } найдено " + c, currentPP);
            }
        }
        return atom;
    }

    private Character preScan() {
        return (currentPP < transcription.length())
                ? transcription.charAt(currentPP) : null;
    }

    private Character scan() {
        return (currentPP < transcription.length())
                ? transcription.charAt(currentPP++) : null;
    }

}
