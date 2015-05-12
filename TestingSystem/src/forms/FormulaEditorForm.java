package forms;

import java.awt.Graphics;
import java.util.Stack;
import javax.swing.ImageIcon;
import main.Atom;
import main.DialogManager;
import main.Formula;
import static resources.Parameters.*;

/**
 *
 * @author Алексей
 */
public class FormulaEditorForm extends javax.swing.JDialog {

    private Formula currentFormula;
    private final Graphics graphics;
    private boolean smallLatinLetters;
    private boolean smallGreekLetters;
    private final Stack<Formula> STACK_FORMULA;
    private final ImageIcon GREEK_UPPER_TO_LOWER_CASE;
    private final ImageIcon GREEK_LOWER_TO_UPPER_CASE;
    private final ImageIcon LATIN_UPPER_TO_LOWER_CASE;
    private final ImageIcon LATIN_LOWER_TO_UPPER_CASE;

    public FormulaEditorForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        graphics = paneEditFormula.getGraphics();
        STACK_FORMULA = new Stack<>();
        makeDefaultFormula();
        addFormulaCopyToStack();
        smallLatinLetters = true;
        smallGreekLetters = true;
        GREEK_UPPER_TO_LOWER_CASE = new ImageIcon(getClass().getResource(
                "/resources/greek_up_to_low.PNG"));
        GREEK_LOWER_TO_UPPER_CASE = new ImageIcon(getClass().getResource(
                "/resources/greek_low_to_up.PNG"));
        LATIN_UPPER_TO_LOWER_CASE = new ImageIcon(getClass().getResource(
                "/resources/latin_up_to_low.PNG"));
        LATIN_LOWER_TO_UPPER_CASE = new ImageIcon(getClass().getResource(
                "/resources/latin_low_to_up.PNG"));
        bChangeRegisterGreekAlphabet.setIcon(GREEK_LOWER_TO_UPPER_CASE);
        bChangeRegisterLatinAlphabet.setIcon(LATIN_LOWER_TO_UPPER_CASE);
    }

    private void makeDefaultFormula() {
        currentFormula = new Formula(20, paneEditFormula.getHeight() / 3);
        currentFormula.addEmptyElement();
        currentFormula.addNextElement('=', true);
        currentFormula.addEmptyElement();
        currentFormula.update();
    }

    public void setFormula(String transcription) {
//        STACK_FORMULA.clear();
//        currentFormula = new Formula(transcription);
//        addFormulaCopyToStack();
    }

    public void drawFormula() {
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
        currentFormula.draw(graphics);
    }

    private void addFormulaCopyToStack() {
        STACK_FORMULA.push(new Formula(currentFormula));
    }

    private void insertSplitterAndEmptyElements(char splitter) {
        if (currentFormula.isSelectedEmpty()) {
//            if (currentFormula.getElementsCount() < Formula.MAX_ITEM_AMOUNT - 2) {
            addFormulaCopyToStack();
            currentFormula.addEmptyAfterSelected();
            currentFormula.addAfterSelected(splitter, true);
            currentFormula.update();
            drawFormula();
//            } else {
//                JOptionPane.showMessageDialog(null, "Максимальная длина формулы",
//                        "Предупреждение", JOptionPane.OK_OPTION);
//            }
        }
    }

    public Formula getFormula() {
        return currentFormula;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawFormula();
    }

    private void replaceAtom(char symbol) {
        addFormulaCopyToStack();
        if (!currentFormula.replaceSelected(symbol)) {
            STACK_FORMULA.pop();
        }
        drawFormula();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneNumberButtons = new javax.swing.JPanel();
        bPutNumber2 = new javax.swing.JButton();
        bPutNumber1 = new javax.swing.JButton();
        bPutNumber3 = new javax.swing.JButton();
        bPutNumber4 = new javax.swing.JButton();
        bPutNumber5 = new javax.swing.JButton();
        bPutNumber6 = new javax.swing.JButton();
        bPutNumber7 = new javax.swing.JButton();
        bPutNumber8 = new javax.swing.JButton();
        bPutNumber9 = new javax.swing.JButton();
        bPutNumber0 = new javax.swing.JButton();
        scrollPaneForLatinAlphabet = new javax.swing.JScrollPane();
        tableLatinAlphabet = new javax.swing.JTable();
        scrollPaneForGreekAlphabet = new javax.swing.JScrollPane();
        tableGreekAlphabet = new javax.swing.JTable();
        bChangeRegisterGreekAlphabet = new javax.swing.JButton();
        bChangeRegisterLatinAlphabet = new javax.swing.JButton();
        bPutSignMinus = new javax.swing.JButton();
        bPutSignPlus = new javax.swing.JButton();
        bPutSignMulti = new javax.swing.JButton();
        bCloseForm = new javax.swing.JButton();
        bSaveFormula = new javax.swing.JButton();
        paneBorder = new javax.swing.JPanel();
        paneEditFormula = new javax.swing.JPanel();
        bClearAll = new javax.swing.JButton();
        bPutSignFrac = new javax.swing.JButton();
        bUndo = new javax.swing.JButton();
        bClearElement = new javax.swing.JButton();
        bPutSignSqrt = new javax.swing.JButton();
        bPutSignPower = new javax.swing.JButton();
        bPutSignIndex = new javax.swing.JButton();
        bPutSignVector = new javax.swing.JButton();
        bRestartConstruction = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Редактор формулы");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        paneNumberButtons.setBackground(new java.awt.Color(153, 153, 153));

        bPutNumber2.setText("2");
        bPutNumber2.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber2ActionPerformed(evt);
            }
        });

        bPutNumber1.setText("1");
        bPutNumber1.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber1ActionPerformed(evt);
            }
        });

        bPutNumber3.setText("3");
        bPutNumber3.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber3ActionPerformed(evt);
            }
        });

        bPutNumber4.setText("4");
        bPutNumber4.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber4ActionPerformed(evt);
            }
        });

        bPutNumber5.setText("5");
        bPutNumber5.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber5ActionPerformed(evt);
            }
        });

        bPutNumber6.setText("6");
        bPutNumber6.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber6ActionPerformed(evt);
            }
        });

        bPutNumber7.setText("7");
        bPutNumber7.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber7ActionPerformed(evt);
            }
        });

        bPutNumber8.setText("8");
        bPutNumber8.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber8ActionPerformed(evt);
            }
        });

        bPutNumber9.setText("9");
        bPutNumber9.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber9ActionPerformed(evt);
            }
        });

        bPutNumber0.setText("0");
        bPutNumber0.setPreferredSize(new java.awt.Dimension(50, 50));
        bPutNumber0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutNumber0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneNumberButtonsLayout = new javax.swing.GroupLayout(paneNumberButtons);
        paneNumberButtons.setLayout(paneNumberButtonsLayout);
        paneNumberButtonsLayout.setHorizontalGroup(
            paneNumberButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneNumberButtonsLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(paneNumberButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneNumberButtonsLayout.createSequentialGroup()
                        .addComponent(bPutNumber4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(bPutNumber5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(bPutNumber6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneNumberButtonsLayout.createSequentialGroup()
                        .addComponent(bPutNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(bPutNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(bPutNumber3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneNumberButtonsLayout.createSequentialGroup()
                        .addComponent(bPutNumber7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(paneNumberButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bPutNumber0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(paneNumberButtonsLayout.createSequentialGroup()
                                .addComponent(bPutNumber8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(bPutNumber9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        paneNumberButtonsLayout.setVerticalGroup(
            paneNumberButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneNumberButtonsLayout.createSequentialGroup()
                .addGroup(paneNumberButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPutNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutNumber3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(paneNumberButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPutNumber4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutNumber5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutNumber6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(paneNumberButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPutNumber7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutNumber8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutNumber9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bPutNumber0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        scrollPaneForLatinAlphabet.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneForLatinAlphabet.setPreferredSize(new java.awt.Dimension(300, 250));

        tableLatinAlphabet.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tableLatinAlphabet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", "b", "c", "d", "e", "f"},
                {"g", "h", "i", "j", "k", "l"},
                {"m", "n", "o", "p", "q", "r"},
                {"s", "t", "u", "v", "w", "x"},
                {"y", "z", null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLatinAlphabet.setAutoscrolls(false);
        tableLatinAlphabet.setPreferredSize(new java.awt.Dimension(240, 200));
        tableLatinAlphabet.setRowHeight(37);
        tableLatinAlphabet.setRowSelectionAllowed(false);
        tableLatinAlphabet.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableLatinAlphabet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableLatinAlphabetMousePressed(evt);
            }
        });
        scrollPaneForLatinAlphabet.setViewportView(tableLatinAlphabet);
        tableLatinAlphabet.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableLatinAlphabet.getColumnModel().getColumnCount() > 0) {
            tableLatinAlphabet.getColumnModel().getColumn(0).setResizable(false);
            tableLatinAlphabet.getColumnModel().getColumn(1).setResizable(false);
            tableLatinAlphabet.getColumnModel().getColumn(2).setResizable(false);
            tableLatinAlphabet.getColumnModel().getColumn(3).setResizable(false);
            tableLatinAlphabet.getColumnModel().getColumn(4).setResizable(false);
            tableLatinAlphabet.getColumnModel().getColumn(5).setResizable(false);
        }
        tableLatinAlphabet.getAccessibleContext().setAccessibleParent(tableLatinAlphabet);

        scrollPaneForGreekAlphabet.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableGreekAlphabet.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tableGreekAlphabet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"α", "β", "γ", "δ", "ε"},
                {"ζ", "η", "θ", "ι", "κ"},
                {"λ", "μ", "ν", "ξ", "π"},
                {"ρ", "σ", "τ", "υ", "φ"},
                {"χ", "ψ", "ω", "", ""}
            },
            new String [] {
                "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableGreekAlphabet.setPreferredSize(new java.awt.Dimension(250, 200));
        tableGreekAlphabet.setRowHeight(37);
        tableGreekAlphabet.setRowSelectionAllowed(false);
        tableGreekAlphabet.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableGreekAlphabet.getTableHeader().setResizingAllowed(false);
        tableGreekAlphabet.getTableHeader().setReorderingAllowed(false);
        tableGreekAlphabet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableGreekAlphabetMousePressed(evt);
            }
        });
        scrollPaneForGreekAlphabet.setViewportView(tableGreekAlphabet);

        bChangeRegisterGreekAlphabet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bChangeRegisterGreekAlphabet.setToolTipText("Сменить регистр греческих букв");
        bChangeRegisterGreekAlphabet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bChangeRegisterGreekAlphabetMouseClicked(evt);
            }
        });

        bChangeRegisterLatinAlphabet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bChangeRegisterLatinAlphabet.setToolTipText("Сменить регистр латинских букв");
        bChangeRegisterLatinAlphabet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChangeRegisterLatinAlphabetActionPerformed(evt);
            }
        });

        bPutSignMinus.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignMinus.setText("[ ] - [ ]");
        bPutSignMinus.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignMinusActionPerformed(evt);
            }
        });

        bPutSignPlus.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignPlus.setText("[ ] + [ ]");
        bPutSignPlus.setToolTipText("");
        bPutSignPlus.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignPlusActionPerformed(evt);
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

        bCloseForm.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bCloseForm.setText("Закрыть");
        bCloseForm.setPreferredSize(new java.awt.Dimension(100, 30));
        bCloseForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseFormActionPerformed(evt);
            }
        });

        bSaveFormula.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bSaveFormula.setText("<html>Продолжить");
        bSaveFormula.setPreferredSize(new java.awt.Dimension(100, 30));
        bSaveFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveFormulaActionPerformed(evt);
            }
        });

        paneBorder.setBackground(new java.awt.Color(204, 204, 204));
        paneBorder.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Область редактирования"));
        paneBorder.setToolTipText("Кликните для создания формулы");
        paneBorder.setPreferredSize(new java.awt.Dimension(650, 350));

        paneEditFormula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneEditFormulaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout paneEditFormulaLayout = new javax.swing.GroupLayout(paneEditFormula);
        paneEditFormula.setLayout(paneEditFormulaLayout);
        paneEditFormulaLayout.setHorizontalGroup(
            paneEditFormulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        paneEditFormulaLayout.setVerticalGroup(
            paneEditFormulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout paneBorderLayout = new javax.swing.GroupLayout(paneBorder);
        paneBorder.setLayout(paneBorderLayout);
        paneBorderLayout.setHorizontalGroup(
            paneBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneEditFormula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        paneBorderLayout.setVerticalGroup(
            paneBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneBorderLayout.createSequentialGroup()
                .addComponent(paneEditFormula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bClearAll.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bClearAll.setText("<html><center>Очистить все ячейки");
        bClearAll.setToolTipText("<html>Очищает содержимое все ячеек,<br> которые не содержат знаков операций");
        bClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearAllActionPerformed(evt);
            }
        });

        bPutSignFrac.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignFrac.setText("<html>[ ]\n<br>---\n<br>[ ]");
        bPutSignFrac.setToolTipText("Дробь");
        bPutSignFrac.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignFrac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignFracActionPerformed(evt);
            }
        });

        bUndo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bUndo.setText("<html>Шаг назад");
        bUndo.setToolTipText("<html>Возвращает предыдущую конструкцию формулы");
        bUndo.setPreferredSize(new java.awt.Dimension(80, 80));
        bUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUndoActionPerformed(evt);
            }
        });

        bClearElement.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bClearElement.setText("<html><center>Очистить ячейку");
        bClearElement.setToolTipText("<html>Очищает содержимое выбранной ячейки,<br> если она не содержит знаков операций");
        bClearElement.setPreferredSize(new java.awt.Dimension(80, 80));
        bClearElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearElementActionPerformed(evt);
            }
        });

        bPutSignSqrt.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignSqrt.setText("\\/[ ]");
        bPutSignSqrt.setEnabled(false);
        bPutSignSqrt.setPreferredSize(new java.awt.Dimension(80, 80));

        bPutSignPower.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignPower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/x2.PNG"))); // NOI18N
        bPutSignPower.setToolTipText("Верхний индекс");
        bPutSignPower.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignPowerActionPerformed(evt);
            }
        });

        bPutSignIndex.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/x0.PNG"))); // NOI18N
        bPutSignIndex.setToolTipText("Нижний индекс");
        bPutSignIndex.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignIndexActionPerformed(evt);
            }
        });

        bPutSignVector.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignVector.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/vec.PNG"))); // NOI18N
        bPutSignVector.setToolTipText("Вектор");
        bPutSignVector.setPreferredSize(new java.awt.Dimension(80, 80));
        bPutSignVector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignVectorActionPerformed(evt);
            }
        });

        bRestartConstruction.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bRestartConstruction.setText("<html><Сбросить");
        bRestartConstruction.setToolTipText("<html>Устанавливает формулу в  состояние [ ] = [ ]");
        bRestartConstruction.setPreferredSize(new java.awt.Dimension(80, 80));
        bRestartConstruction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRestartConstructionActionPerformed(evt);
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
                        .addComponent(paneNumberButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollPaneForLatinAlphabet, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bChangeRegisterLatinAlphabet, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPaneForGreekAlphabet, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bChangeRegisterGreekAlphabet, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paneBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bSaveFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bPutSignFrac, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bPutSignVector, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bRestartConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bClearElement, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bRestartConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bClearElement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
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
                            .addComponent(bPutSignVector, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bSaveFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bChangeRegisterLatinAlphabet, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(bChangeRegisterGreekAlphabet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(paneNumberButtons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPaneForLatinAlphabet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(scrollPaneForGreekAlphabet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bPutSignPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignPlusActionPerformed
        insertSplitterAndEmptyElements('+');
    }//GEN-LAST:event_bPutSignPlusActionPerformed

    private void bPutNumber1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber1ActionPerformed
        replaceAtom('1');
    }//GEN-LAST:event_bPutNumber1ActionPerformed

    private void bPutNumber2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber2ActionPerformed
        replaceAtom('2');
    }//GEN-LAST:event_bPutNumber2ActionPerformed

    private void bPutNumber3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber3ActionPerformed
        replaceAtom('3');
    }//GEN-LAST:event_bPutNumber3ActionPerformed

    private void bPutNumber4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber4ActionPerformed
        replaceAtom('4');
    }//GEN-LAST:event_bPutNumber4ActionPerformed

    private void bPutNumber5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber5ActionPerformed
        replaceAtom('5');
    }//GEN-LAST:event_bPutNumber5ActionPerformed

    private void bPutNumber6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber6ActionPerformed
        replaceAtom('6');
    }//GEN-LAST:event_bPutNumber6ActionPerformed

    private void bPutNumber7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber7ActionPerformed
        replaceAtom('7');
    }//GEN-LAST:event_bPutNumber7ActionPerformed

    private void bPutNumber8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber8ActionPerformed
        replaceAtom('8');
    }//GEN-LAST:event_bPutNumber8ActionPerformed

    private void bPutNumber9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber9ActionPerformed
        replaceAtom('9');
    }//GEN-LAST:event_bPutNumber9ActionPerformed

    private void bPutNumber0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber0ActionPerformed
        replaceAtom('0');
    }//GEN-LAST:event_bPutNumber0ActionPerformed

    private void bChangeRegisterLatinAlphabetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChangeRegisterLatinAlphabetActionPerformed
        for (int i = 0; i < tableLatinAlphabet.getRowCount(); i++) {
            for (int j = 0; j < tableLatinAlphabet.getColumnCount(); j++) {
                Object value = tableLatinAlphabet.getValueAt(i, j);
                if (value != null) {
                    String letter = value.toString();
                    if (smallLatinLetters) {
                        tableLatinAlphabet.setValueAt(letter.toUpperCase(), i, j);
                    } else {
                        tableLatinAlphabet.setValueAt(letter.toLowerCase(), i, j);
                    }
                }
            }
        }
        if (!smallLatinLetters) {
            bChangeRegisterLatinAlphabet.setIcon(LATIN_LOWER_TO_UPPER_CASE);
        } else {
            bChangeRegisterLatinAlphabet.setIcon(LATIN_UPPER_TO_LOWER_CASE);
        }
        smallLatinLetters = !smallLatinLetters;
    }//GEN-LAST:event_bChangeRegisterLatinAlphabetActionPerformed

    private void bChangeRegisterGreekAlphabetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bChangeRegisterGreekAlphabetMouseClicked
        for (int i = 0; i < tableGreekAlphabet.getRowCount(); i++) {
            for (int j = 0; j < tableGreekAlphabet.getColumnCount(); j++) {
                Object value = tableGreekAlphabet.getValueAt(i, j);
                if (value != null) {
                    String letter = value.toString();
                    if (smallGreekLetters) {
                        tableGreekAlphabet.setValueAt(letter.toUpperCase(), i, j);
                    } else {
                        tableGreekAlphabet.setValueAt(letter.toLowerCase(), i, j);
                    }
                }
            }
        }
        if (!smallGreekLetters) {
            bChangeRegisterGreekAlphabet.setIcon(GREEK_LOWER_TO_UPPER_CASE);
        } else {
            bChangeRegisterGreekAlphabet.setIcon(GREEK_UPPER_TO_LOWER_CASE);
        }
        smallGreekLetters = !smallGreekLetters;
    }//GEN-LAST:event_bChangeRegisterGreekAlphabetMouseClicked

    private void bPutSignMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMinusActionPerformed
        insertSplitterAndEmptyElements('-');
    }//GEN-LAST:event_bPutSignMinusActionPerformed

    private void bPutSignMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMultiActionPerformed
        insertSplitterAndEmptyElements('•');
    }//GEN-LAST:event_bPutSignMultiActionPerformed

    private void bClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearAllActionPerformed
        for (Atom atom : currentFormula) {
            atom.clear();
        }
        drawFormula();
    }//GEN-LAST:event_bClearAllActionPerformed

    private void bCloseFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseFormActionPerformed
        if (DialogManager.confirmClosingForm("формулы")) {
            currentFormula = null;
            dispose();
        }
    }//GEN-LAST:event_bCloseFormActionPerformed

    private void bSaveFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveFormulaActionPerformed
        boolean hasEmpty = false;
        for (Atom atom : currentFormula) {
            if (atom.isEmpty()) {
                hasEmpty = true;
            }
        }
        if (hasEmpty) {
            DialogManager.notify("Предупреждение",
                    "В формуле не должно быть пустых элементов",
                    DialogManager.TypeOfMessage.WARNING);
        } else {
            for (Atom atom : currentFormula) {
                atom.selected = false;
            }
            dispose();
        }
    }//GEN-LAST:event_bSaveFormulaActionPerformed

    private void bUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUndoActionPerformed
        if (STACK_FORMULA.size() > 1) {
            currentFormula = new Formula(STACK_FORMULA.pop());
        } else {
            currentFormula = new Formula(STACK_FORMULA.peek());
        }
        drawFormula();
    }//GEN-LAST:event_bUndoActionPerformed

    private void bClearElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearElementActionPerformed
        currentFormula.makeSelectedEmpty();
        drawFormula();
    }//GEN-LAST:event_bClearElementActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (DialogManager.confirmClosingForm("формулы")) {
            currentFormula = null;
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void bRestartConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRestartConstructionActionPerformed
        makeDefaultFormula();
        drawFormula();
    }//GEN-LAST:event_bRestartConstructionActionPerformed

    private void tableLatinAlphabetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLatinAlphabetMousePressed
        Object value = tableLatinAlphabet.getValueAt(
                tableLatinAlphabet.getSelectedRow(),
                tableLatinAlphabet.getSelectedColumn());
        if (value != null) {
            replaceAtom(value.toString().charAt(0));
        }
    }//GEN-LAST:event_tableLatinAlphabetMousePressed

    private void tableGreekAlphabetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGreekAlphabetMousePressed
        Object value = tableGreekAlphabet.getValueAt(
                tableGreekAlphabet.getSelectedRow(),
                tableGreekAlphabet.getSelectedColumn());
        if (value != null) {
            replaceAtom(value.toString().charAt(0));
        }
    }//GEN-LAST:event_tableGreekAlphabetMousePressed

    private void paneEditFormulaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneEditFormulaMousePressed
        currentFormula.setSelectedAtom(evt.getX(), evt.getY());
        drawFormula();
    }//GEN-LAST:event_paneEditFormulaMousePressed

    private void bPutSignPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignPowerActionPerformed
        if (currentFormula.isSelectedEmpty()
                || currentFormula.isSelectedNormal()) {
            addFormulaCopyToStack();
            currentFormula.addPowerInSelected();
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignPowerActionPerformed

    private void bPutSignFracActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignFracActionPerformed
        if (currentFormula.isSelectedEmpty()) {
            addFormulaCopyToStack();
            currentFormula.addFractionInSelected();
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignFracActionPerformed

    private void bPutSignIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignIndexActionPerformed
        if (currentFormula.isSelectedEmpty()
                || currentFormula.isSelectedNormal()) {
            addFormulaCopyToStack();
            currentFormula.addLowerIndexInSelected();
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignIndexActionPerformed

    private void bPutSignVectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignVectorActionPerformed
        if (currentFormula.isSelectedEmpty()
                || currentFormula.isSelectedNormal()) {
            addFormulaCopyToStack();
            currentFormula.addVectorInSelected();
            currentFormula.update();
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignVectorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChangeRegisterGreekAlphabet;
    private javax.swing.JButton bChangeRegisterLatinAlphabet;
    private javax.swing.JButton bClearAll;
    private javax.swing.JButton bClearElement;
    private javax.swing.JButton bCloseForm;
    private javax.swing.JButton bPutNumber0;
    private javax.swing.JButton bPutNumber1;
    private javax.swing.JButton bPutNumber2;
    private javax.swing.JButton bPutNumber3;
    private javax.swing.JButton bPutNumber4;
    private javax.swing.JButton bPutNumber5;
    private javax.swing.JButton bPutNumber6;
    private javax.swing.JButton bPutNumber7;
    private javax.swing.JButton bPutNumber8;
    private javax.swing.JButton bPutNumber9;
    private javax.swing.JButton bPutSignFrac;
    private javax.swing.JButton bPutSignIndex;
    private javax.swing.JButton bPutSignMinus;
    private javax.swing.JButton bPutSignMulti;
    private javax.swing.JButton bPutSignPlus;
    private javax.swing.JButton bPutSignPower;
    private javax.swing.JButton bPutSignSqrt;
    private javax.swing.JButton bPutSignVector;
    private javax.swing.JButton bRestartConstruction;
    private javax.swing.JButton bSaveFormula;
    private javax.swing.JButton bUndo;
    private javax.swing.JPanel paneBorder;
    private javax.swing.JPanel paneEditFormula;
    private javax.swing.JPanel paneNumberButtons;
    private javax.swing.JScrollPane scrollPaneForGreekAlphabet;
    private javax.swing.JScrollPane scrollPaneForLatinAlphabet;
    private javax.swing.JTable tableGreekAlphabet;
    private javax.swing.JTable tableLatinAlphabet;
    // End of variables declaration//GEN-END:variables

}
