package main;

import java.text.ParseException;

/**
 * @author Alexey
 */
public class FormulaParser {

    private String transcription;
    private int currentPP;
    private final int DEFAULT_WIDTH = 60;
    private final int DEFAULT_HEIGHT = 60;

    /**
     *
     * @param transcription
     * @return
     * @throws ParseException
     */
    public Formula parseFormula(String transcription) throws ParseException {
        this.transcription = transcription;
        return new Formula(formula(null, false), 0, 0);
    }

    private Atom formula(Atom parent, boolean index) throws ParseException {
        Atom atom = null;
        if (preScan() != null) {
            atom = elementOfFormula(parent, index);
            Character c = preScan();
            if (c != null && c != ']' && c != '}') {
                atom.next = formula(parent, index);
            }
        }
        return atom;
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

    private Atom elementOfFormula(Atom parent, boolean index) throws ParseException {
        char c = scan();
        Atom atom;
        int width = parent == null ? DEFAULT_WIDTH : parent.getWidth() / 2;
        int height = parent == null ? DEFAULT_HEIGHT : parent.getHeight() / 2;
        if (Character.isLetterOrDigit(c) || isArifmeticSymbol(c)) {
            //создать элемент дерева
            TypeOfAtom typeOfAtom = TypeOfAtom.NORMAL;
            if (c == '/') {
                typeOfAtom = TypeOfAtom.FRAC_LINE;
            }
            atom = new Atom(c, typeOfAtom);
            atom.index = index;
            atom.parent = parent;
        } else {
            throw new ParseException(String.valueOf(c)
                    + " не является буквой или цифрой", currentPP);
        }
        atom.setSize(width, height);
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
            atom = formula(parent, true);
            c = scan();
            if (c != ']') {
                throw new ParseException("ожидалось ] найдено " + c, currentPP);
            }
        } else if (c == '{') {
            atom = formula(parent, false);
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
            atom = formula(parent, true);
            c = scan();
            if (c != ']') {
                throw new ParseException("ожидалось ] найдено " + c, currentPP);
            }
        } else if (c == '{') {
            atom = formula(parent, false);
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
