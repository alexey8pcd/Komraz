package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey 
 * 
 * Класс для хранения, отображения и работы с формулами
 */
public class Formula {

    /**
     * Класс для хранения и отображения элемента формулы
     */
    private class Atom {

        public String text;//содержимое элемента
        public int x;//позиция левого верхнего угла на экране по горизонтали
        public int y;//позиция левого верхнего угла на экране по вертикали
        public int size;//размер элемента
        public boolean mutable;//можно ли заменить содержимое элемента

        /**
         * Создает новый экземпляр класса Atom
         *
         * @param text текст для отображения
         * @param y позиция левого верхнего угла по вертикали
         * @param size размер элемента
         */
        public Atom(String text, int y, int size) {
            this.text = text;
            this.y = y;
            this.size = size;
            mutable = true;
        }

        /**
         * Создает новый экземпляр класса Atom на основе существующего
         *
         * @param toCopy объект, свойства которого копируются
         */
        public Atom(Atom toCopy) {
            this.text = toCopy.text;
            this.x = toCopy.x;
            this.y = toCopy.y;
            this.mutable = toCopy.mutable;
            this.size = toCopy.size;
        }

        /**
         * Отображает на экране элемент формулы
         *
         * @param g экземпляр класса java.awt.Graphics;
         * @param selected атрибут выделения. Выделенный элемент отображается в
         * синей рамке
         * @param editing атрибут редактирования. Редактируемый элемент
         * отображается в красной рамке.
         */
        public void display(Graphics g, boolean selected, boolean editing) {
            if (editing) {
                g.setColor(Color.red);
                if (mutable) {
                    g.drawRect(x + 1, y + 1, size - 2, size - 2);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x + 1, y + 1, size - 2, size - 2);
                }

                if (selected) {
                    g.setColor(Color.blue);
                    g.drawRect(x, y, size, size);
                }
            }
            g.setColor(Color.black);
            g.setFont(DEFAULT_FONT);
            if (text != null) {
                g.drawString(text, x + size / 3, y + size / 2);
            }
        }

        /**
         * Проверяет, содержит ли элемент точку на экране
         *
         * @param x позиция точки по горизонтали
         * @param y позиция точки по вертикали
         * @return true, если элемент содержит точку, false иначе
         */
        public boolean containPoint(int x, int y) {
            return x >= this.x
                    && x < this.x + size
                    && y >= this.y
                    && y < this.y + size;
        }
    }

    public static final int START_X_DEFAULT = 10;
    public static final int START_Y_DEFAULT = 50;
    public static final int SIZE_DEFAULT = 40;
    public static final int MAX_ITEM_AMOUNT = 16;
    public char[] immutableSymbols = {
        '=',
        '+',
        '-',
        '●'
    };
    private int startX;
    private int startY;
    private int size;
    public static final Font DEFAULT_FONT = new Font("Times New Roman", Font.BOLD, 22);
    private final List<Atom> elements;
    private int selectedIndex;

    /**
     * Cоздает новый экземпляр класса Formula Устанавливает все свойства по
     * умолчанию
     */
    public Formula() {
        elements = new ArrayList<>();
        startX = START_X_DEFAULT;
        startY = START_Y_DEFAULT;
        size = SIZE_DEFAULT;
        selectedIndex = -1;
    }

    /**
     * Создает экземпляр класса Formula на основе существующего
     *
     * @param toCopy экземпляр класса, с которого будут скопированы свойства
     */
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

    /**
     * Создает экземпляр класса Formula на основе строковой записи
     *
     * @param transcription
     */
    public Formula(String transcription) {
        elements = new ArrayList<>();
        startX = START_X_DEFAULT;
        startY = START_Y_DEFAULT;
        size = SIZE_DEFAULT;
        selectedIndex = -1;
        if (transcription != null && !transcription.isEmpty()) {
            for (int i = 0; i < transcription.length(); i++) {
                char brick = transcription.charAt(i);
                Atom atom = new Atom(String.valueOf(brick),
                        startY, size);
                for (char immutable : immutableSymbols) {
                    if (brick == immutable) {
                        atom.mutable = false;
                    }
                }
                if (i < MAX_ITEM_AMOUNT) {
                    elements.add(atom);
                }
            }
            countXPositions();
        }
    }

    public Formula(int startX, int startY, int size) {
        elements = new ArrayList<>();
        this.startX = startX;
        this.startY = startY;
        this.size = size;
    }

    /**
     * Проверяет, присутствуют ли в формуле пустые элементы
     *
     * @return true, если присутствуют, false, если нет таких
     */
    public boolean hasEmptyElements() {
        for (Atom atom : elements) {
            if (atom.text == null) {
                return true;
            }
        }
        return false;
    }

    public int getStartX() {
        return startX;
    }

    /**
     * Выделяет элемент формулы, если он содержит координаты x и y
     *
     * @param x координаты точки по горизонтали
     * @param y координаты точки по вертикали
     */
    public void setSelectedAtom(int x, int y) {
        selectedIndex = -1;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).containPoint(x, y)) {
                selectedIndex = i;
                return;
            }
        }
    }

    public int getElementsCount() {
        return elements.size();
    }

    /**
     * Возвращает индекс выделенного элемента
     *
     * @return индекс выделенного элемента в списке, если он выделен или -1,
     * если нет выделенных элементов
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Заменяет текст элемента формулы на заданный по индексу. Замена не
     * происходит, если индекс указан вне границ списка элементов или элемент
     * не допускает замену
     *
     * @param text текст, который будет записан в элемент формулы
     * @param index номер элемента в списке элементов >= 0
     */
    public void replaceAtomText(String text, int index) {
        if (index >= 0 && index < elements.size()) {
            Atom toReplace = elements.get(index);
            if (toReplace.mutable) {
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

    private void countXPositions() {
        for (int i = 0; i < elements.size(); i++) {
            Atom atom = elements.get(i);
            atom.x = startX + i * atom.size;
        }
    }

    /**
     * Проверяет выделенный элемент формулы, является ли он пустым
     *
     * @return true, если элемент пустой, false, если не пустой
     */
    public boolean isEmptySelectedElement() {
        if (selectedIndex == -1) {
            return false;
        } else {
            return elements.get(selectedIndex).text == null;
        }
    }

    /**
     * Добавляет пустой элемент в конец формулы
     */
    public void addEmpty() {
        add(null, true);
    }

    /**
     * Возвращает строковое представление формулы
     *
     * @return строка с записью формулы
     */
    public String getTranscription() {
        StringBuilder builder = new StringBuilder();
        for (Atom atom : elements) {
            builder.append(atom.text);
        }
        return builder.toString();
    }

    /**
     * Добавляет пустой элемент в выбранную позицию в формуле, если индекс не
     * превышает размер списка
     *
     * @param index позиция элемента в списке >= 0
     */
    public void addEmptyIn(int index) {
        insertIn(null, index, true);
    }

    /**
     * Добавляет элемент в конец формулы
     *
     * @param text текст элемента формулы
     * @param mutable можно ли заменить содержимое элемента(true - можно, false
     * - нельзя)
     */
    public void add(String text, boolean mutable) {
        Atom atom = new Atom(text, startY, size);
        atom.mutable = mutable;
        elements.add(atom);
        countXPositions();
    }

    /**
     * Помещает элемент в формулу по заданному индексу
     *
     * @param text текст, который содержит элемент формулы
     * @param index индекс от 0 до количества элементов в формуле, Элемент не
     * вставляется, если индекс превышает количество элементов в формуле
     * @param mutable можно ли заменить содержимое элемента(true - можно, false
     * - нельзя)
     */
    public void insertIn(String text, int index, boolean mutable) {
        if (index >= 0 || index < elements.size()) {
            Atom atom = new Atom(text, startY, size);
            atom.mutable = mutable;
            elements.add(index, atom);
            countXPositions();
        }
    }

    /**
     * Отображает формулу на экране
     *
     * @param graphics экземпляр класса java.awt.Graphics;
     */
    public void displayForEditing(Graphics graphics) {
        for (int i = 0; i < elements.size(); i++) {
            if (selectedIndex == i) {
                elements.get(i).display(graphics, true, true);
            } else {
                elements.get(i).display(graphics, false, true);
            }
        }
    }

    public void preview(Graphics graphics) {
        for (Atom atom : elements) {
            atom.display(graphics, false, false);
        }
    }
}
