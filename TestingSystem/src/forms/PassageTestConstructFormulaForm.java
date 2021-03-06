package forms;

import entities.Student;
import entities.StudentTest;
import entities.Test;
import entities.TestVopros;
import entities.Vopros;
import entities.VoprosLatex;
import java.awt.Graphics;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Stack;
import javax.persistence.TypedQuery;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import main.Atom;
import main.DialogManager;
import main.Formula;
import main.FormulaParser;
import main.TypeOfAtom;
import main.TypeOfFunction;
import static resources.Parameters.*;
import static sql.DBManager.entityManager;

/**
 *
 * @author Алексей
 */
public class PassageTestConstructFormulaForm extends javax.swing.JDialog {

    private Student student;
    private Test testForPassage;
    private Vopros currentQuestion;
    private List<Vopros> questions;
    private int currentQuestionIndex;
    private int questionsAmount;
    private List<String> wordsToInsert; //Буквы, которые собираемся вставить
    private final int MAX_AMOUNT_OF_WORDS = 20;
    private List<Object[]> insertValues;
    private boolean mouseInTableClicked;
    private final Graphics GRAPHICS;
    private Formula currentFormula;
    private final Stack<Formula> STACK_FORMULA;
    private int sqrtNumber;
//    private String[] answers;
    private Formula[] answers;
    private final Random RANDOM = new Random();
    private final TableModel TABLE_SYBOLS_MODEL = new AbstractTableModel() {

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

    public PassageTestConstructFormulaForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mouseInTableClicked = false;
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        STACK_FORMULA = new Stack<>();
        makeDefaultFormula();
        addFormulaCopyToStack();
        GRAPHICS = paneForFormulaConstruct.getGraphics();
        wordsToInsert = new ArrayList<>();
        tableSymbols.setFont(new java.awt.Font("Times New Roman", 1, 18));
    }

    private void updateLabel() {
        labelQuestionNumber.setText("Вопрос "
                + (currentQuestionIndex + 1) + " из " + questionsAmount);
    }

    private String getLatexTranscription(Vopros question) {
        TypedQuery<VoprosLatex> queryForVoprosLatex = entityManager.createQuery(
                "SELECT vl FROM VoprosLatex vl "
                + "WHERE vl.voprosIdVopros.idVopros=:id",
                VoprosLatex.class);
        queryForVoprosLatex.setParameter("id", question.getIdVopros());
        return queryForVoprosLatex.getSingleResult().getLatexZapis();
    }

    /**
     * Обновление таблицы алфавита
     */
    private void updateAlphabet() {
        wordsToInsert.clear(); //Подчистим массив
        String currentLatexString = getLatexTranscription(currentQuestion);
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
        tableSymbols.updateUI();
    }

    /**
     * На основе записанных в массив букв дополняет массив рандомными
     *
     * @param inputArray входной массив
     * @return готовый
     */
    private List<String> fillTheArrayByRandomWords(List<String> inputArray) {
        List<String> resultArray = new ArrayList<>();
        for (String element : inputArray) {
            resultArray.add(element);
        }
        for (int index = resultArray.size(); index < MAX_AMOUNT_OF_WORDS; index++) {

            switch (RANDOM.nextInt(4)) {
                case 0:
                    //Берем значения из латинского списка нижнего регистра
                    checkAndInsertWord(LOWER_CASE_LATIN_ALPHABET, resultArray);
                    break;
                case 1:
                    //Берем значения из латинского списка верхнего регистра
                    checkAndInsertWord(UPPER_CASE_LATIN_ALPHABET, resultArray);
                    break;
                case 2:
                    //Берем значения из греческого списка нижнего регистра
                    checkAndInsertWord(LOWER_CASE_GREEK_ALPHABET, resultArray);
                    break;
                case 3:
                    //Берем значения из греческого списка нижнего регистра
                    checkAndInsertWord(UPPER_CASE_GREEK_ALPHABET, resultArray);
                    break;
            }
        }
        Collections.shuffle(resultArray, RANDOM);
        return resultArray;
    }

    private void checkAndInsertWord(Object[][] dictionary, List<String> listOfWords) {
        boolean isComplete = false;

        while (!isComplete) {
            int rowIndex = RANDOM.nextInt(dictionary.length);
            int columnIndex = RANDOM.nextInt(dictionary[0].length);
            boolean allowToInsert = true;

            String temp = (String) dictionary[rowIndex][columnIndex];

            for (String elementInResult : listOfWords) {
                if (temp == null) {
                    allowToInsert = false;
                } else if (temp.equals(elementInResult)) {
                    allowToInsert = false;
                }
            }
            if (allowToInsert) {
                isComplete = true;
                listOfWords.add(temp);
            }
        }
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
        for (Object[] lowerCaseGreekAlphabet2 : LOWER_CASE_GREEK_ALPHABET) {
            for (Object lowerCaseGreekAlphabet1 : lowerCaseGreekAlphabet2) {
                if (lowerCaseGreekAlphabet1 != null) {
                    if (lowerCaseGreekAlphabet1.toString().equalsIgnoreCase(String.valueOf(t))) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < UPPER_CASE_GREEK_ALPHABET.length; i++) {
            for (int j = 0; j < UPPER_CASE_GREEK_ALPHABET[i].length; j++) {
                if (LOWER_CASE_GREEK_ALPHABET[i][j] != null) {
                    if (UPPER_CASE_GREEK_ALPHABET[i][j].toString().equalsIgnoreCase(String.valueOf(t))) {
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
        currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion != null) {
            lQuestionFormulation.setText(currentQuestion.getFormulirovka());
        }
        makeDefaultFormula();
    }

    private void makeDefaultFormula() {
        currentFormula = new Formula(20,
                paneForFormulaConstruct.getHeight() / 3);
        currentFormula.addEmptyElement(Formula.BASE_ATOM_WIDTH);
        currentFormula.addNextElement('=', true, Formula.BASE_ATOM_WIDTH);
        currentFormula.addEmptyElement(Formula.BASE_ATOM_WIDTH);
        currentFormula.update();
        sqrtNumber = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawFormula();
    }

    public void drawFormula() {
        GRAPHICS.clearRect(0, 0, this.getWidth(), this.getHeight());
        currentFormula.draw(GRAPHICS);
    }

    private void addFormulaCopyToStack() {
        STACK_FORMULA.push(new Formula(currentFormula));
    }

    /**
     * Устанавливает тест для прохождения
     *
     * @param testForPassage
     */
    public void setTestForPassage(Test testForPassage) {
        List<TestVopros> testVoproses = null;
        try {
            this.testForPassage = entityManager.find(Test.class, testForPassage.getIdTest());
            TypedQuery<TestVopros> queryForTestVopros = entityManager.createQuery(
                    "SELECT tv FROM TestVopros tv WHERE tv.testIdTest.idTest=:id",
                    TestVopros.class);
            queryForTestVopros.setParameter("id", this.testForPassage.getIdTest());
            testVoproses = queryForTestVopros.getResultList();
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
            dispose();
        }
        questions = new ArrayList<>();
        for (TestVopros testVopros : testVoproses) {
            questions.add(testVopros.getVoprosIdVopros());
        }
        Collections.shuffle(questions);
        currentQuestion = questions.get(currentQuestionIndex);
        questionsAmount = questions.size();
//        answers = new String[questionsAmount];
        answers = new Formula[questionsAmount];
        tableSymbols.setModel(TABLE_SYBOLS_MODEL);
        //Установим заголовки таблицы букв пустыми
        JTableHeader header = tableSymbols.getTableHeader();
        for (int i = 0; i < 10; i++) {
            header.getColumnModel().getColumn(i).setHeaderValue("");
        }
        tableSymbols.setTableHeader(header);
        tableSymbols.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSymbols.getColumnModel().getSelectionModel().setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        updateLabel();
        updateQuestion();
        updateAlphabet();
        setButtonNextAndPreviousProperties();
    }

    private void insertSplitterAndEmptyElements(char splitter) {
        if (currentFormula.isSelectedEmpty()) {
            addFormulaCopyToStack();
            currentFormula.addEmptyAfterSelected(Formula.BASE_ATOM_WIDTH);
            currentFormula.addAfterSelected(splitter, true, Formula.BASE_ATOM_WIDTH);
            currentFormula.update();
            drawFormula();
        }
    }

    public void setStudent(Student student) {
        this.student = student;
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
        bNextQuestion = new javax.swing.JButton();
        paneForFormulaConstruct = new javax.swing.JPanel();
        lQuestionFormulation = new javax.swing.JLabel();
        labelQuestionNumber = new javax.swing.JLabel();
        bPutSignFrac = new javax.swing.JButton();
        cbTrigonometry = new javax.swing.JComboBox();
        bPutSignPlusMinus = new javax.swing.JButton();
        bPutDelta = new javax.swing.JButton();
        bPutSignUnaryMinus = new javax.swing.JButton();
        bPutBrackets = new javax.swing.JButton();
        bPutModule = new javax.swing.JButton();

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
        tableSymbols.setAutoscrolls(false);
        tableSymbols.setRowHeight(40);
        tableSymbols.setRowSelectionAllowed(false);
        tableSymbols.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableSymbols.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tableSymbolsMouseDragged(evt);
            }
        });
        tableSymbols.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSymbolsMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableSymbolsMouseReleased(evt);
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
        bUndo.setText("<html>Шаг назад");
        bUndo.setToolTipText("<html>Возвращает предыдущую конструкцию формулы");
        bUndo.setPreferredSize(new java.awt.Dimension(80, 80));
        bUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUndoActionPerformed(evt);
            }
        });

        bRestartConstruction.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        bRestartConstruction.setText("<html>Сбросить");
        bRestartConstruction.setToolTipText("<html>Устанавливает формулу в  состояние [ ] = [ ]");
        bRestartConstruction.setPreferredSize(new java.awt.Dimension(80, 80));
        bRestartConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRestartConstructionActionPerformed(evt);
            }
        });

        bClearAll.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        bClearAll.setText("<html><center>Очистить все ячейки");
        bClearAll.setToolTipText("<html>Очищает содержимое все ячеек,<br> которые не содержат знаков операций");
        bClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearAllActionPerformed(evt);
            }
        });

        bClearElement.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        bClearElement.setText("<html><center>Очистить ячейку");
        bClearElement.setToolTipText("<html>Очищает содержимое выбранной ячейки,<br> если она не содержит знаков операций");
        bClearElement.setPreferredSize(new java.awt.Dimension(80, 80));
        bClearElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearElementActionPerformed(evt);
            }
        });

        bPutSignPlus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bPutSignPlus.setText("<html><strong>[ ] + [ ]");
        bPutSignPlus.setToolTipText("");
        bPutSignPlus.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignPlusActionPerformed(evt);
            }
        });

        bPutSignSqrt.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignSqrt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sqrt.PNG"))); // NOI18N
        bPutSignSqrt.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignSqrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignSqrtActionPerformed(evt);
            }
        });

        bPutSignPower.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignPower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/x2.PNG"))); // NOI18N
        bPutSignPower.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignPowerActionPerformed(evt);
            }
        });

        bPutSignMinus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bPutSignMinus.setText("<html><strong>[ ] - [ ]");
        bPutSignMinus.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignMinusActionPerformed(evt);
            }
        });

        bPutSignMulti.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bPutSignMulti.setText("<html><strong>[ ] ● [ ]");
        bPutSignMulti.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignMulti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignMultiActionPerformed(evt);
            }
        });

        bPutSignIndex.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/x0.PNG"))); // NOI18N
        bPutSignIndex.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignIndexActionPerformed(evt);
            }
        });

        bPutSignVector.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignVector.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/vec.PNG"))); // NOI18N
        bPutSignVector.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignVector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignVectorActionPerformed(evt);
            }
        });

        bNextQuestion.setText("Следующий вопрос");
        bNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextQuestionActionPerformed(evt);
            }
        });

        paneForFormulaConstruct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneForFormulaConstructMousePressed(evt);
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
            .addGap(0, 345, Short.MAX_VALUE)
        );

        lQuestionFormulation.setText("Формулировка вопроса");

        labelQuestionNumber.setText("Вопрос N/M");

        bPutSignFrac.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignFrac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/frac.PNG"))); // NOI18N
        bPutSignFrac.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignFrac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignFracActionPerformed(evt);
            }
        });

        cbTrigonometry.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbTrigonometry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "sin [ ]", "cos [ ]", "tg [  ]", "ctg [ ]", "arcsin [ ]", "arccos [ ]", "arctg [ ]", "arcctg [ ]", "ln [ ]" }));
        cbTrigonometry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTrigonometryActionPerformed(evt);
            }
        });

        bPutSignPlusMinus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bPutSignPlusMinus.setText("<html><strong>± [ ]");
        bPutSignPlusMinus.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignPlusMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignPlusMinusActionPerformed(evt);
            }
        });

        bPutDelta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bPutDelta.setText("<html><strong>Δ [ ]");
        bPutDelta.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutDelta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutDeltaActionPerformed(evt);
            }
        });

        bPutSignUnaryMinus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bPutSignUnaryMinus.setText("<html><strong>- [ ]");
        bPutSignUnaryMinus.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignUnaryMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignUnaryMinusActionPerformed(evt);
            }
        });

        bPutBrackets.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bPutBrackets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/brackets.PNG"))); // NOI18N
        bPutBrackets.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutBrackets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutBracketsActionPerformed(evt);
            }
        });

        bPutModule.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bPutModule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mod.PNG"))); // NOI18N
        bPutModule.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutModuleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(187, 187, 187)
                                .addComponent(bNextQuestion))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(paneForFormulaConstruct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sPaneForSymbolsTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 234, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(bCompleteTest))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(bPutSignFrac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(bPutSignMulti, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(bPutSignMinus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(bPutSignPlus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(bClearElement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(bUndo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(bPutDelta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(bRestartConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(bPutSignSqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(bPutSignPower, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(bPutSignIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(bPutSignVector, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(bPutSignPlusMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(bPutModule, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(bPutBrackets, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bPutSignUnaryMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbTrigonometry, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(4, 4, 4)))
                        .addGap(0, 12, Short.MAX_VALUE))
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
                        .addComponent(bNextQuestion))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lQuestionFormulation, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneForFormulaConstruct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sPaneForSymbolsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bRestartConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bPutSignSqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bPutSignPower, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bPutSignIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bPutSignVector, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bClearElement, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bPutSignPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bPutSignMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bPutSignMulti, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bPutSignFrac, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bPutSignPlusMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bPutDelta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bPutBrackets, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bPutModule, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTrigonometry, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bPutSignUnaryMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addComponent(bCompleteTest)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUndoActionPerformed
        if (STACK_FORMULA.size() > 1) {
            currentFormula = new Formula(STACK_FORMULA.pop());
        } else {
            currentFormula = new Formula(STACK_FORMULA.peek());
        }
        drawFormula();
    }//GEN-LAST:event_bUndoActionPerformed

    private void bRestartConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRestartConstructionActionPerformed
        makeDefaultFormula();
        drawFormula();
    }//GEN-LAST:event_bRestartConstructionActionPerformed

    private void bClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearAllActionPerformed
        for (Atom atom : currentFormula) {
            atom.clear();
        }
        drawFormula();
    }//GEN-LAST:event_bClearAllActionPerformed

    private void bClearElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearElementActionPerformed
        currentFormula.makeSelectedEmpty();
        drawFormula();
    }//GEN-LAST:event_bClearElementActionPerformed

    private void bPutSignPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignPlusActionPerformed
        insertSplitterAndEmptyElements('+');
    }//GEN-LAST:event_bPutSignPlusActionPerformed

    private void bPutSignMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMinusActionPerformed
        insertSplitterAndEmptyElements('-');
    }//GEN-LAST:event_bPutSignMinusActionPerformed

    private void bPutSignMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMultiActionPerformed
        insertSplitterAndEmptyElements('•');
    }//GEN-LAST:event_bPutSignMultiActionPerformed

    private void bNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextQuestionActionPerformed
        if (currentQuestionIndex < questionsAmount - 1) {
            boolean hasEmpty = false;
            for (Atom atom : currentFormula) {
                if (atom.isEmpty()) {
                    hasEmpty = true;
                }
            }
            if (hasEmpty) {
                DialogManager.notify("Предупреждение",
                        "В формуле не должно быть пустых элементов",
                        DialogManager.TypeOfMessage.CLOSED);
            } else {
                Formula answer = new Formula(currentFormula);
                answers[currentQuestionIndex] = answer;
                currentQuestionIndex++;
                updateQuestion();
                drawFormula();
                updateAlphabet();
            }
        }
        setButtonNextAndPreviousProperties();
        updateLabel();
    }//GEN-LAST:event_bNextQuestionActionPerformed

    private void insertSymbolBeforeSelected(char symbol) {
        if (currentFormula.isSelectedEmpty()
                || currentFormula.getTypeOfSelectedAtom() == TypeOfAtom.NORMAL) {
            addFormulaCopyToStack();
            currentFormula.addBeforeSelected(symbol, false, Formula.BASE_ATOM_WIDTH);
            currentFormula.update();
            drawFormula();
        }
    }

    private void insertFunctionBeforeSelected(TypeOfFunction typeOfFunction) {
        if (currentFormula.isSelectedEmpty()
                || currentFormula.getTypeOfSelectedAtom() == TypeOfAtom.NORMAL) {
            addFormulaCopyToStack();
            currentFormula.addEmptyAfterSelected(Formula.BASE_ATOM_WIDTH);
            currentFormula.placeInSelected((char) (typeOfFunction.ordinal()),
                    true);
            currentFormula.update();
            drawFormula();
        }
    }

    private void bCompleteTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompleteTestActionPerformed
        if (currentQuestionIndex < questionsAmount - 1) {
            if (!DialogManager.confirmDeleting("Вы ответили не на все вопросы! "
                    + "Вы действительно хотите завершить тест?")) {
                return;
            }
        }
        boolean hasEmpty = false;
        for (Atom atom : currentFormula) {
            if (atom.isEmpty()) {
                hasEmpty = true;
            }
        }
        if (hasEmpty) {
            DialogManager.notify("Предупреждение",
                    "В формуле не должно быть пустых элементов",
                    DialogManager.TypeOfMessage.CLOSED);
        } else {
            answers[currentQuestionIndex] = new Formula(currentFormula);
        }
        int scored = 0;
        int maximalScore = 0;
        FormulaParser parser = new FormulaParser();
        for (int i = 0; i < questionsAmount; i++) {
            Vopros vopros = questions.get(i);
            Formula answer = answers[i];
            if (answer != null) {
                try {
                    Formula fromQuestion = parser.parseFormula(
                            getLatexTranscription(vopros));
                    if (fromQuestion.toString().equals(answer.toString())) {
                        scored += vopros.getBall();
                    } else if (fromQuestion.equalFormula(answer)) {
                        scored += vopros.getBall();
                    }
                } catch (ParseException ex) {
                    DialogManager.errorMessage(ex);
                }
            }
            maximalScore += vopros.getBall();
        }
        DialogManager.notify("Результат", "Вы набрали " + scored + " баллов  из "
                + maximalScore, DialogManager.TypeOfMessage.INFORMATION);

        try {
            if (student == null) {
                student = entityManager.createNamedQuery(
                        "Student.findAll", Student.class).getResultList().get(0);
            }
            StudentTest studentTest = new StudentTest();
            studentTest.setStudentIdStudent(student);
            studentTest.setTestIdTest(testForPassage);
            studentTest.setProcentBallov(scored * 100 / maximalScore);
            studentTest.setDataProhozhdeniya(GregorianCalendar.
                    getInstance().getTime());
            entityManager.getTransaction().begin();
            entityManager.persist(studentTest);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
        }
        dispose();
    }//GEN-LAST:event_bCompleteTestActionPerformed

    private void paneForFormulaConstructMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForFormulaConstructMousePressed
        currentFormula.setSelectedAtom(evt.getX(), evt.getY());
        drawFormula();
    }//GEN-LAST:event_paneForFormulaConstructMousePressed

    private void tableSymbolsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSymbolsMouseClicked
        Object value = tableSymbols.getValueAt(
                tableSymbols.getSelectedRow(),
                tableSymbols.getSelectedColumn());
        if (value != null) {
            if (!mouseInTableClicked) {
                addFormulaCopyToStack();
            }
            mouseInTableClicked = true;
            if (!currentFormula.replaceSelected(value.toString().charAt(0))) {
                STACK_FORMULA.pop();
            }
            drawFormula();
        }
    }//GEN-LAST:event_tableSymbolsMouseClicked

    private void tableSymbolsMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSymbolsMouseDragged
        Object value = tableSymbols.getValueAt(
                tableSymbols.getSelectedRow(),
                tableSymbols.getSelectedColumn());
        if (value != null) {
            if (!mouseInTableClicked) {
                addFormulaCopyToStack();
            }
            if (!currentFormula.replaceSelected(value.toString().charAt(0))) {
                if (!mouseInTableClicked) {
                    STACK_FORMULA.pop();
                }
            }
            mouseInTableClicked = true;
            drawFormula();
        }
    }//GEN-LAST:event_tableSymbolsMouseDragged

    private void tableSymbolsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSymbolsMouseReleased
        mouseInTableClicked = false;
    }//GEN-LAST:event_tableSymbolsMouseReleased

    private void bPutSignPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignPowerActionPerformed
        if (currentFormula.isSelectedEmpty()
                || currentFormula.getTypeOfSelectedAtom() == TypeOfAtom.NORMAL) {
            addFormulaCopyToStack();
            currentFormula.addPowerInSelected();
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignPowerActionPerformed

    private void bPutSignIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignIndexActionPerformed
        if (currentFormula.isSelectedEmpty()
                || currentFormula.getTypeOfSelectedAtom() == TypeOfAtom.NORMAL) {
            addFormulaCopyToStack();
            currentFormula.addLowerIndexInSelected();
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignIndexActionPerformed

    private void bPutSignVectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignVectorActionPerformed
        if (currentFormula.isSelectedEmpty()
                || currentFormula.getTypeOfSelectedAtom() == TypeOfAtom.NORMAL) {
            addFormulaCopyToStack();
            currentFormula.addVectorInSelected();
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignVectorActionPerformed

    private void bPutSignFracActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignFracActionPerformed
        if (currentFormula.isSelectedEmpty()) {
            addFormulaCopyToStack();
            currentFormula.addFractionInSelected();
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignFracActionPerformed

    private void bPutSignPlusMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignPlusMinusActionPerformed
        insertSymbolBeforeSelected('±');
    }//GEN-LAST:event_bPutSignPlusMinusActionPerformed

    private void bPutDeltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutDeltaActionPerformed
        insertSymbolBeforeSelected('Δ');
    }//GEN-LAST:event_bPutDeltaActionPerformed

    private void bPutSignUnaryMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignUnaryMinusActionPerformed
        insertSymbolBeforeSelected('-');
    }//GEN-LAST:event_bPutSignUnaryMinusActionPerformed

    private void bPutBracketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutBracketsActionPerformed
        if (currentFormula.isSelectedEmpty()
                || currentFormula.getTypeOfSelectedAtom() == TypeOfAtom.NORMAL) {
            addFormulaCopyToStack();
            currentFormula.addBeforeSelected('(', true, Formula.BASE_ATOM_WIDTH);
            currentFormula.addAfterSelected(')', true, Formula.BASE_ATOM_WIDTH);
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutBracketsActionPerformed

    private void bPutModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutModuleActionPerformed
        if (currentFormula.isSelectedEmpty()
                || currentFormula.getTypeOfSelectedAtom() == TypeOfAtom.NORMAL) {
            addFormulaCopyToStack();
            currentFormula.addBeforeSelected('|', true, Formula.BASE_ATOM_WIDTH);
            currentFormula.addAfterSelected('|', true, Formula.BASE_ATOM_WIDTH);
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutModuleActionPerformed

    private void cbTrigonometryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTrigonometryActionPerformed
        insertFunctionBeforeSelected(
                TypeOfFunction.getType(cbTrigonometry.getSelectedIndex()));
    }//GEN-LAST:event_cbTrigonometryActionPerformed

    private void bPutSignSqrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignSqrtActionPerformed
        if (currentFormula.isSelectedEmpty()
                || currentFormula.getTypeOfSelectedAtom() == TypeOfAtom.NORMAL) {
            addFormulaCopyToStack();

            currentFormula.addSqrtStartBeforeSelected(sqrtNumber);
            currentFormula.addSqrtEndAfterSelected(sqrtNumber++);
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignSqrtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClearAll;
    private javax.swing.JButton bClearElement;
    private javax.swing.JButton bCompleteTest;
    private javax.swing.JButton bNextQuestion;
    private javax.swing.JButton bPutBrackets;
    private javax.swing.JButton bPutDelta;
    private javax.swing.JButton bPutModule;
    private javax.swing.JButton bPutSignFrac;
    private javax.swing.JButton bPutSignIndex;
    private javax.swing.JButton bPutSignMinus;
    private javax.swing.JButton bPutSignMulti;
    private javax.swing.JButton bPutSignPlus;
    private javax.swing.JButton bPutSignPlusMinus;
    private javax.swing.JButton bPutSignPower;
    private javax.swing.JButton bPutSignSqrt;
    private javax.swing.JButton bPutSignUnaryMinus;
    private javax.swing.JButton bPutSignVector;
    private javax.swing.JButton bRestartConstruction;
    private javax.swing.JButton bUndo;
    private javax.swing.JComboBox cbTrigonometry;
    private javax.swing.JLabel lQuestionFormulation;
    private javax.swing.JLabel labelQuestionNumber;
    private javax.swing.JPanel paneForFormulaConstruct;
    private javax.swing.JScrollPane sPaneForSymbolsTable;
    private javax.swing.JTable tableSymbols;
    // End of variables declaration//GEN-END:variables

}
