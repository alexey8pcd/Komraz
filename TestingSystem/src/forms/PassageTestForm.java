package forms;

import entities.Test;
import entities.Vopros;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import main.Formula;
import static resources.Parameters.*;

/**
 *
 * @author Алексей
 */
public class PassageTestForm extends javax.swing.JDialog {

    private Test testForPassage;
    private Vopros currentQuestion;
    private int currentQuestionIndex;
    private int questionsAmount;

    private ArrayList<String> wordsToInsert; //Буквы, которые собираемся вставить
    private final int MAX_AMOUNT_OF_WORDS = 20;
    private ArrayList<Object[]> insertValues;

    private final Graphics graphics;
    private Formula currentFormula;
    private final Stack<Formula> stackFormula;
    private String[] answers;

    private TableModel TABLE_SYBOLS_MODEL = new AbstractTableModel() {

        @Override
        public int getRowCount() {
            return 3;
        }

        @Override
        public int getColumnCount() {
            return 10;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return insertValues.get(rowIndex)[columnIndex];
        }
    };

    public PassageTestForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        stackFormula = new Stack<>();
        makeDefaultFormula();
        addFormulaCopyToStack();
        graphics = paneForFormulaConstruct.getGraphics();
        currentQuestionIndex = 0;
        bPreviousQuestion.setEnabled(false);

        wordsToInsert = new ArrayList<>();
        tableSymbols.setFont(new java.awt.Font("Times New Roman", 1, 18));
    }

    private void updateLabel() {
        labelQuestionNumber.setText("Вопрос "
                + (currentQuestionIndex + 1) + "/" + questionsAmount);
    }

    /**
     * Обновление таблицы алфавита
     */
    private void updateAlphabet() {
        wordsToInsert.clear(); //Подчистим массив

        Vopros vopros = testForPassage.
                getTestVoprosList().get(currentQuestionIndex).getVoprosIdVopros();

        String currentLatexString = "";
        try {
            currentLatexString = vopros.getVoprosLatexList().
                    get(0).getLatexZapis();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }

        for (int i = 0; i < currentLatexString.length(); i++) {
            if (isCharacter(currentLatexString.charAt(i))) {
                wordsToInsert.add(String.valueOf(currentLatexString.charAt(i)));
            }
        }

        wordsToInsert = fillTheArrayByRandomWords(wordsToInsert);

        //Выделяем память
        insertValues = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            insertValues.add(i, new Object[10]);
        }

        //Вставка значений в модель
        int countOfResultArray = 0;
        for (int i = 0; i < TABLE_SYBOLS_MODEL.getRowCount(); i++) {
            for (int j = 0; j < TABLE_SYBOLS_MODEL.getColumnCount(); j++) {
                if (i == 0) {
                    //В первую строку вставляем цифры от 0 до 9
                    insertValues.get(i)[j] = j;
                } else {
                    //В остальные строки вставляем буквы
                    if (countOfResultArray < wordsToInsert.size()) {
                        insertValues.get(i)[j] = wordsToInsert.get(countOfResultArray);
                        countOfResultArray++;
                    }
                }
            }
        }
        tableSymbols.setModel(TABLE_SYBOLS_MODEL);
        //Установим заголовки таблицы букв пустыми
        JTableHeader header = tableSymbols.getTableHeader();
        for (int i = 0; i < 10; i++) {
            header.getColumnModel().getColumn(i).setHeaderValue("");
        }
        tableSymbols.setTableHeader(header);
        tableSymbols.updateUI();
    }

    /**
     * На основе записанных в массив букв дополняет массив рандомными
     *
     * @param inputArray входной массив
     * @return готовый
     */
    private ArrayList<String> fillTheArrayByRandomWords(ArrayList<String> inputArray) {
        ArrayList<String> resultArray = new ArrayList<>();
        for (String element : inputArray) {
            resultArray.add(element);
        }

        Random r1 = new Random();

        for (int index = resultArray.size(); index < MAX_AMOUNT_OF_WORDS; index++) {
            boolean isComplete = false;
            switch (r1.nextInt(4)) {
                case 0:
                    //Берем значения из латинского списка нижнего регистра                    
                    while (!isComplete) {
                        int m = r1.nextInt(lowerCaseLatinAlphabet.length);
                        int n = r1.nextInt(lowerCaseLatinAlphabet[0].length);
                        boolean allowToInsert = true;

                        for (String elementInResult : resultArray) {
                            if (lowerCaseLatinAlphabet[m][n] != null) {
                                if (lowerCaseLatinAlphabet[m][n].toString().
                                        equalsIgnoreCase(elementInResult)) {
                                    allowToInsert = false;
                                }
                            } else {
                                allowToInsert = false;
                            }
                        }
                        if (allowToInsert) {
                            isComplete = true;
                            resultArray.add(lowerCaseLatinAlphabet[m][n].toString());
                        }
                    }
                    break;
                case 1:
                    //Берем значения из латинского списка верхнего регистра
                    while (!isComplete) {
                        int m = r1.nextInt(upperCaseLatinAlphabet.length);
                        int n = r1.nextInt(upperCaseLatinAlphabet[0].length);
                        boolean allowToInsert = true;

                        for (String elementInResult : resultArray) {
                            if (upperCaseLatinAlphabet[m][n] != null) {
                                if (upperCaseLatinAlphabet[m][n].toString().
                                        equalsIgnoreCase(elementInResult)) {
                                    allowToInsert = false;
                                }
                            } else {
                                allowToInsert = false;
                            }
                        }
                        if (allowToInsert) {
                            isComplete = true;
                            resultArray.add(upperCaseLatinAlphabet[m][n].toString());
                        }
                    }
                    break;
                case 2:
                    //Берем значения из греческого списка нижнего регистра                    
                    while (!isComplete) {
                        int m = r1.nextInt(lowerCaseGreekAlphabet.length);
                        int n = r1.nextInt(lowerCaseGreekAlphabet[0].length);
                        boolean allowToInsert = true;

                        for (String elementInResult : resultArray) {
                            if (lowerCaseGreekAlphabet[m][n] != null) {
                                if (lowerCaseGreekAlphabet[m][n].toString().
                                        equalsIgnoreCase(elementInResult)) {
                                    allowToInsert = false;
                                }
                            } else {
                                allowToInsert = false;
                            }
                        }
                        if (allowToInsert) {
                            isComplete = true;
                            resultArray.add(lowerCaseGreekAlphabet[m][n].toString());
                        }
                    }
                    break;
                case 3:
                    //Берем значения из греческого списка нижнего регистра                    
                    while (!isComplete) {
                        int m = r1.nextInt(upperCaseGreekAlphabet.length);
                        int n = r1.nextInt(upperCaseGreekAlphabet[0].length);
                        boolean allowToInsert = true;

                        for (String elementInResult : resultArray) {
                            if (upperCaseGreekAlphabet[m][n] != null) {
                                if (upperCaseGreekAlphabet[m][n].toString().
                                        equalsIgnoreCase(elementInResult)) {
                                    allowToInsert = false;
                                }
                            } else {
                                allowToInsert = false;
                            }
                        }
                        if (allowToInsert) {
                            isComplete = true;
                            resultArray.add(upperCaseGreekAlphabet[m][n].toString());
                        }
                    }
                    break;
            }
        }

        return resultArray;
    }

    /**
     * Проверка, является ли символ буквой
     *
     * @param t
     * @return true - да; <br>false - нет
     */
    private boolean isCharacter(char t) {

        if (t >= 'a' && t <= 'z') {
            return true;
        }
        if (t >= 'A' && t <= 'Z') {
            return true;
        }
        for (int i = 0; i < lowerCaseGreekAlphabet.length; i++) {
            for (int j = 0; j < lowerCaseGreekAlphabet[i].length; j++) {
                if (lowerCaseGreekAlphabet[i][j] != null) {
                    if (lowerCaseGreekAlphabet[i][j].toString().equalsIgnoreCase(String.valueOf(t))) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < upperCaseGreekAlphabet.length; i++) {
            for (int j = 0; j < upperCaseGreekAlphabet[i].length; j++) {
                if (lowerCaseGreekAlphabet[i][j] != null) {
                    if (upperCaseGreekAlphabet[i][j].toString().equalsIgnoreCase(String.valueOf(t))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void setButtonNextAndPreviousProperties() {
        /**
         * ********НУЖНОЕ*************** if (currentQuestionIndex == 0) {
         * bPreviusQuestion.setEnabled(false); } else {
         * bPreviusQuestion.setEnabled(true); }
         */
        if (currentQuestionIndex == questionsAmount - 1) {
            bNextQuestion.setEnabled(false);
        } else {
            bNextQuestion.setEnabled(true);
        }
    }

    private void updateQuestion() {
        currentQuestion = testForPassage.getTestVoprosList().
                get(currentQuestionIndex).getVoprosIdVopros();
        if (currentQuestion != null) {
            lQuestionFormulation.setText(currentQuestion.getFormulirovka());
        }
        makeDefaultFormula();
    }

    private void makeDefaultFormula() {
        currentFormula = new Formula();
        currentFormula.addEmpty();
        currentFormula.add("=", false);
        currentFormula.addEmpty();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawFormula();
    }

    public void drawFormula() {
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
        currentFormula.displayForEditing(graphics);
    }

    private void addFormulaCopyToStack() {
        stackFormula.push(new Formula(currentFormula));
    }

    /**
     * Устанавливает тест для прохождения
     *
     * @param testForPassage
     */
    public void setTestForPassage(Test testForPassage) {
        this.testForPassage = testForPassage;
        currentQuestion = testForPassage.getTestVoprosList().
                get(currentQuestionIndex).getVoprosIdVopros();

        questionsAmount = testForPassage.getTestVoprosList().size();
        answers = new String[questionsAmount];
        updateLabel();
        updateQuestion();
        updateAlphabet();
        setButtonNextAndPreviousProperties();

    }

    private void insertSplitterAndEmptyElements(String splitter) {
        if (currentFormula.isEmptySelectedElement()) {
            if (currentFormula.getElementsCount() < Formula.MAX_ITEM_AMOUNT - 2) {
                addFormulaCopyToStack();
                currentFormula.addEmptyIn(currentFormula.getSelectedIndex());
                currentFormula.insertIn(splitter,
                        currentFormula.getSelectedIndex() + 1, false);
                drawFormula();
            } else {
                JOptionPane.showMessageDialog(null, "Максимальная длина формулы",
                        "Предупреждение", JOptionPane.OK_OPTION);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sPaneForSymbolsTable = new javax.swing.JScrollPane();
        tableSymbols = new javax.swing.JTable();
        bCompleteTest = new javax.swing.JButton();
        bUndo = new javax.swing.JButton();
        bRestartConstruction = new javax.swing.JButton();
        bClearAll = new javax.swing.JButton();
        bClearElement = new javax.swing.JButton();
        bPutSignPlus = new javax.swing.JButton();
        bPutSignSqrt = new javax.swing.JButton();
        bPutSignPower = new javax.swing.JButton();
        bPutSignMinus = new javax.swing.JButton();
        bPutSignMulti = new javax.swing.JButton();
        bPutSignIndex = new javax.swing.JButton();
        bPutSignVector = new javax.swing.JButton();
        bPutSignFrac = new javax.swing.JButton();
        bPreviousQuestion = new javax.swing.JButton();
        bNextQuestion = new javax.swing.JButton();
        paneForFormulaConstruct = new javax.swing.JPanel();
        lQuestionFormulation = new javax.swing.JLabel();
        labelQuestionNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Прохождение теста");
        setResizable(false);

        tableSymbols.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
                {"a", "s", "v", "t", "i", "j", "k", "n", "m", "p"},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSymbols.setRowHeight(40);
        tableSymbols.setRowSelectionAllowed(false);
        tableSymbols.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableSymbols.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSymbolsMouseClicked(evt);
            }
        });
        sPaneForSymbolsTable.setViewportView(tableSymbols);
        if (tableSymbols.getColumnModel().getColumnCount() > 0) {
            tableSymbols.getColumnModel().getColumn(0).setResizable(false);
            tableSymbols.getColumnModel().getColumn(1).setResizable(false);
            tableSymbols.getColumnModel().getColumn(2).setResizable(false);
            tableSymbols.getColumnModel().getColumn(3).setResizable(false);
            tableSymbols.getColumnModel().getColumn(4).setResizable(false);
            tableSymbols.getColumnModel().getColumn(5).setResizable(false);
            tableSymbols.getColumnModel().getColumn(6).setResizable(false);
            tableSymbols.getColumnModel().getColumn(7).setResizable(false);
            tableSymbols.getColumnModel().getColumn(8).setResizable(false);
            tableSymbols.getColumnModel().getColumn(9).setResizable(false);
        }

        bCompleteTest.setText("Завершить тест");
        bCompleteTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompleteTestActionPerformed(evt);
            }
        });

        bUndo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        bUndo.setText("Шаг назад");
        bUndo.setPreferredSize(new java.awt.Dimension(80, 80));
        bUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUndoActionPerformed(evt);
            }
        });

        bRestartConstruction.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        bRestartConstruction.setText("Сбросить");
        bRestartConstruction.setPreferredSize(new java.awt.Dimension(80, 80));
        bRestartConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRestartConstructionActionPerformed(evt);
            }
        });

        bClearAll.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        bClearAll.setLabel("Очистить все");
        bClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearAllActionPerformed(evt);
            }
        });

        bClearElement.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        bClearElement.setText("Очистить");
        bClearElement.setPreferredSize(new java.awt.Dimension(80, 80));
        bClearElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearElementActionPerformed(evt);
            }
        });

        bPutSignPlus.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignPlus.setText("[ ] + [ ]");
        bPutSignPlus.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignPlusActionPerformed(evt);
            }
        });

        bPutSignSqrt.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignSqrt.setText("\\/[ ]");
        bPutSignSqrt.setEnabled(false);
        bPutSignSqrt.setPreferredSize(new java.awt.Dimension(80, 80));

        bPutSignPower.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignPower.setText("<html>\n.  [ ]\n<br>\n[ ]");
        bPutSignPower.setEnabled(false);
        bPutSignPower.setPreferredSize(new java.awt.Dimension(80, 80));

        bPutSignMinus.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignMinus.setText("[ ] - [ ]");
        bPutSignMinus.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignMinusActionPerformed(evt);
            }
        });

        bPutSignMulti.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignMulti.setText("[ ] ● [ ]");
        bPutSignMulti.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignMulti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignMultiActionPerformed(evt);
            }
        });

        bPutSignIndex.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignIndex.setText("<html>\n[ ]\n<br>\n.[ ]");
        bPutSignIndex.setEnabled(false);
        bPutSignIndex.setPreferredSize(new java.awt.Dimension(80, 80));

        bPutSignVector.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignVector.setText("<html>\n-->\n<br>\n[ ]");
        bPutSignVector.setEnabled(false);
        bPutSignVector.setPreferredSize(new java.awt.Dimension(80, 80));

        bPutSignFrac.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignFrac.setText("<html>[ ]\n<br>---\n<br>[ ]");
        bPutSignFrac.setEnabled(false);
        bPutSignFrac.setPreferredSize(new java.awt.Dimension(80, 80));

        bPreviousQuestion.setText("Предыдущий вопрос");
        bPreviousQuestion.setEnabled(false);
        bPreviousQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPreviousQuestionActionPerformed(evt);
            }
        });

        bNextQuestion.setText("Следующий вопрос");
        bNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextQuestionActionPerformed(evt);
            }
        });

        paneForFormulaConstruct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneForFormulaConstructMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneForFormulaConstructLayout = new javax.swing.GroupLayout(paneForFormulaConstruct);
        paneForFormulaConstruct.setLayout(paneForFormulaConstructLayout);
        paneForFormulaConstructLayout.setHorizontalGroup(
            paneForFormulaConstructLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        paneForFormulaConstructLayout.setVerticalGroup(
            paneForFormulaConstructLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lQuestionFormulation.setText("Формулировка вопроса");

        labelQuestionNumber.setText("Вопрос N/M");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sPaneForSymbolsTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCompleteTest)
                        .addGap(56, 56, 56))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(paneForFormulaConstruct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bPutSignMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bPutSignPower, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bPutSignMulti, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bPutSignIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bPutSignPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bPutSignSqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bPutSignFrac, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bPutSignVector, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bRestartConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bClearElement, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(12, 12, 12))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(bPreviousQuestion)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bNextQuestion)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lQuestionFormulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bPreviousQuestion)
                            .addComponent(bNextQuestion)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lQuestionFormulation, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bRestartConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bClearElement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bPutSignPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bPutSignSqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bPutSignMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bPutSignPower, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bPutSignMulti, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bPutSignIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bPutSignFrac, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bPutSignVector, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(paneForFormulaConstruct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(sPaneForSymbolsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bCompleteTest)
                        .addGap(29, 29, 29))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUndoActionPerformed
        if (stackFormula.size() > 1) {
            currentFormula = new Formula(stackFormula.pop());
        } else {
            currentFormula = new Formula(stackFormula.peek());
        }
        drawFormula();
    }//GEN-LAST:event_bUndoActionPerformed

    private void bRestartConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRestartConstructionActionPerformed
        makeDefaultFormula();
        drawFormula();
    }//GEN-LAST:event_bRestartConstructionActionPerformed

    private void bClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearAllActionPerformed
        for (int i = 0; i < currentFormula.getElementsCount(); i++) {
            currentFormula.replaceAtomText(null, i);
        }
        drawFormula();
    }//GEN-LAST:event_bClearAllActionPerformed

    private void bClearElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearElementActionPerformed
        currentFormula.replaceAtomText(null, currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bClearElementActionPerformed

    private void bPutSignPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignPlusActionPerformed
        insertSplitterAndEmptyElements("+");
    }//GEN-LAST:event_bPutSignPlusActionPerformed

    private void bPutSignMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMinusActionPerformed
        insertSplitterAndEmptyElements("-");
    }//GEN-LAST:event_bPutSignMinusActionPerformed

    private void bPutSignMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMultiActionPerformed
        insertSplitterAndEmptyElements("●");
    }//GEN-LAST:event_bPutSignMultiActionPerformed

    private void bNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextQuestionActionPerformed
        if (currentQuestionIndex < questionsAmount - 1) {
            if (currentFormula.hasEmptyElements()) {
                JOptionPane.showMessageDialog(null,
                        "В формуле не должно быть пустых элементов",
                        "Предупреждение", JOptionPane.CLOSED_OPTION);
            } else {
                answers[currentQuestionIndex] = currentFormula.getTranscription();
                currentQuestionIndex++;
                updateQuestion();
                drawFormula();
                updateAlphabet();
            }
        }
        setButtonNextAndPreviousProperties();
        updateLabel();
    }//GEN-LAST:event_bNextQuestionActionPerformed

    private void bPreviousQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPreviousQuestionActionPerformed
        setButtonNextAndPreviousProperties();
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            updateQuestion();
            drawFormula();
            updateAlphabet();
        }
        updateLabel();
    }//GEN-LAST:event_bPreviousQuestionActionPerformed

    private void paneForFormulaConstructMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForFormulaConstructMouseClicked
        currentFormula.setSelectedAtom(evt.getX(), evt.getY());
        drawFormula();
    }//GEN-LAST:event_paneForFormulaConstructMouseClicked

    private void tableSymbolsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSymbolsMouseClicked
        Object value = tableSymbols.getValueAt(
                tableSymbols.getSelectedRow(),
                tableSymbols.getSelectedColumn());
        if (value != null) {
            currentFormula.replaceAtomText(value.toString(), currentFormula.getSelectedIndex());
        }
        drawFormula();
    }//GEN-LAST:event_tableSymbolsMouseClicked

    private void bCompleteTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompleteTestActionPerformed
        int scored = 0;
        int maximalScore = 0;
        answers[currentQuestionIndex] = currentFormula.getTranscription();
        for (int i = 0; i < questionsAmount; i++) {
            Vopros vopros = this.testForPassage.
                    getTestVoprosList().get(i).getVoprosIdVopros();
            String answer = answers[i];
            if (answer != null) {
                if (answer.equals(vopros.getVoprosLatexList().
                        get(0).getLatexZapis())) {
                    scored += vopros.getBall();
                }
            }
            maximalScore += vopros.getBall();
        }
        JOptionPane.showMessageDialog(null, "Вы набрали баллов "
                + scored + "/" + maximalScore,
                "Результат", JOptionPane.OK_OPTION);
        dispose();
    }//GEN-LAST:event_bCompleteTestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClearAll;
    private javax.swing.JButton bClearElement;
    private javax.swing.JButton bCompleteTest;
    private javax.swing.JButton bNextQuestion;
    private javax.swing.JButton bPreviousQuestion;
    private javax.swing.JButton bPutSignFrac;
    private javax.swing.JButton bPutSignIndex;
    private javax.swing.JButton bPutSignMinus;
    private javax.swing.JButton bPutSignMulti;
    private javax.swing.JButton bPutSignPlus;
    private javax.swing.JButton bPutSignPower;
    private javax.swing.JButton bPutSignSqrt;
    private javax.swing.JButton bPutSignVector;
    private javax.swing.JButton bRestartConstruction;
    private javax.swing.JButton bUndo;
    private javax.swing.JLabel lQuestionFormulation;
    private javax.swing.JLabel labelQuestionNumber;
    private javax.swing.JPanel paneForFormulaConstruct;
    private javax.swing.JScrollPane sPaneForSymbolsTable;
    private javax.swing.JTable tableSymbols;
    // End of variables declaration//GEN-END:variables

}
