package resources;

/**
 * @author Alexey
 */
public class Parameters {
    
    /**
     * 4 статичных объекта - алфавитов верхнего и нижнего регистра
     * для латинского и греческого
     * 
     * В случае дублирования букв верхнего регистра, у греческого
     * алфавита стоят латинские по кодировке буквы, что облегчает сравнение.
     */
    public static final Object[][] lowerCaseLatinAlphabet
            = new Object[][]{
                {"a", "b", "c", "d", "e", "f"},
                {"g", "h", "i", "j", "k", "l"},
                {"m", "n", "o", "p", "q", "r"},
                {"s", "t", "u", "v", "w", "x"},
                {"y", "z", null, null, null, null}
            };
    public static final Object[][] upperCaseLatinAlphabet
            = new Object[][]{
                {"A", "B", "C", "D", "E", "F"},
                {"G", "H", "I", "J", "K", "L"},
                {"M", "N", "O", "P", "Q", "R"},
                {"S", "T", "U", "V", "W", "X"},
                {"Y", "Z", null, null, null, null}
            };
    public static final Object[][] lowerCaseGreekAlphabet
            = new Object[][]{
                {"α", "β", "γ", "δ", "ε"},
                {"ζ", "η", "θ", "ι", "κ"},
                {"λ", "μ", "ν", "ξ", "π"},
                {"ρ", "σ", "τ", "υ", "φ"},
                {"χ", "ψ", "ω", "", ""}
            };
    public static final Object[][] upperCaseGreekAlphabet
            = new Object[][]{
                {"A", "B", "Γ", "Δ", "E"},
                {"Z", "H", "Θ", "I", "K"},
                {"Λ", "M", "N", "Ξ", "Π"},
                {"P", "Σ", "T", "Y", "Φ"},
                {"X", "Ψ", "Ω", "", ""}
            };
}
