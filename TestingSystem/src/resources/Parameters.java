package resources;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author Alexey
 */
public class Parameters {

    public static final String BUTTON_LABEL_LATIN_LOWER_TO_UPPER_CASE
            = "<html>A <br>↑ <br>a";
    public static final String BUTTON_LABEL_LATIN_UPPER_TO_LOWER_CASE
            = "<html>A <br>↓ <br>a";
    public static final String BUTTON_LABEL_GREEK_LOWER_TO_UPPER_CASE
            = "<html>Δ <br>↑ <br>δ";
    public static final String BUTTON_LABEL_GREEK_UPPER_TO_LOWER_CASE
            = "<html>Δ <br>↓ <br>δ";
    public static final Dimension SCREEN_SIZE = Toolkit.
            getDefaultToolkit().getScreenSize();
    /**
     * 4 статичных объекта - алфавитов верхнего и нижнего регистра для
     * латинского и греческого
     *
     * В случае дублирования букв верхнего регистра, у греческого алфавита стоят
     * латинские по кодировке буквы, что облегчает сравнение.
     */
    public static final Object[][] LOWER_CASE_LATIN_ALPHABET
            = new Object[][]{
                {"a", "b", "c", "d", "e", "f"},
                {"g", "h", "i", "j", "k", "l"},
                {"m", "n", "o", "p", "q", "r"},
                {"s", "t", "u", "v", "w", "x"},
                {"y", "z", null, null, null, null}
            };
    public static final Object[][] UPPER_CASE_LATIN_ALPHABET
            = new Object[][]{
                {"A", "B", "C", "D", "E", "F"},
                {"G", "H", "I", "J", "K", "L"},
                {"M", "N", "O", "P", "Q", "R"},
                {"S", "T", "U", "V", "W", "X"},
                {"Y", "Z", null, null, null, null}
            };
    public static final Object[][] LOWER_CASE_GREEK_ALPHABET
            = new Object[][]{
                {"α", "β", "γ", "δ", "ε"},
                {"ζ", "η", "θ", "ι", "κ"},
                {"λ", "μ", "ν", "ξ", "π"},
                {"ρ", "σ", "τ", "υ", "φ"},
                {"χ", "ψ", "ω", null, null}
            };
    public static final Object[][] UPPER_CASE_GREEK_ALPHABET
            = new Object[][]{
                {"A", "B", "Γ", "Δ", "E"},
                {"Z", "H", "Θ", "I", "K"},
                {"Λ", "M", "N", "Ξ", "Π"},
                {"P", "Σ", "T", "Y", "Φ"},
                {"X", "Ψ", "Ω", null, null}
            };
}
