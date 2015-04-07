package forms;

import java.awt.Graphics;
import java.util.Stack;
import javax.swing.JOptionPane;
import main.Formula;

/**
 *
 * @author Алексей
 */
public class FormulaEditorForm extends javax.swing.JDialog {

    private Formula currentFormula;
    private final Graphics graphics;
    private boolean smallLatinLetters;
    private boolean smallGreekLetters;
    private final Stack<Formula> stackFormula;
    private String formulaTranscription;

    public FormulaEditorForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        graphics = paneEditFormula.getGraphics();
        stackFormula = new Stack<>();
        makeDefaultFormula();
        addFormulaCopyToStack();
        smallLatinLetters = true;
        smallGreekLetters = true;
    }

    private void makeDefaultFormula() {
        currentFormula = new Formula();
        currentFormula.addEmpty();
        currentFormula.add("=", false);
        currentFormula.addEmpty();
    }

    public void setFormula(String transcription) {
        stackFormula.clear();
        currentFormula = new Formula(transcription);
        addFormulaCopyToStack();
    }

    public void drawFormula() {
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
        currentFormula.displayForEditing(graphics);
    }

    private void addFormulaCopyToStack() {
        stackFormula.push(new Formula(currentFormula));
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

    public String getFormulaTranscription() {
        return formulaTranscription;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawFormula();
    }

    /**
     * Подтверждение выхода из редактора
     */
    private void confirmExit() {
        int result = JOptionPane.showConfirmDialog(null, "Закрыть окно редактора?",
                "Подтверждение выхода", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            dispose();
        }
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
        setSize(new java.awt.Dimension(800, 600));
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLatinAlphabetMouseClicked(evt);
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGreekAlphabetMouseClicked(evt);
            }
        });
        scrollPaneForGreekAlphabet.setViewportView(tableGreekAlphabet);

        bChangeRegisterGreekAlphabet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bChangeRegisterGreekAlphabet.setText("<html>δ\n<br>|\n<br>|\n<br>v\n<br>Δ");
        bChangeRegisterGreekAlphabet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bChangeRegisterGreekAlphabetMouseClicked(evt);
            }
        });

        bChangeRegisterLatinAlphabet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bChangeRegisterLatinAlphabet.setText("<html>a\n<br>|\n<br>|\n<br>v\n<br>A");
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
        bSaveFormula.setText("Сохранить");
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneEditFormulaMouseClicked(evt);
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

        bClearAll.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        bClearAll.setLabel("Очистить все");
        bClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearAllActionPerformed(evt);
            }
        });

        bPutSignFrac.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignFrac.setText("<html>[ ]\n<br>---\n<br>[ ]");
        bPutSignFrac.setEnabled(false);
        bPutSignFrac.setPreferredSize(new java.awt.Dimension(80, 80));

        bUndo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        bUndo.setText("Шаг назад");
        bUndo.setPreferredSize(new java.awt.Dimension(80, 80));
        bUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUndoActionPerformed(evt);
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

        bPutSignSqrt.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignSqrt.setText("\\/[ ]");
        bPutSignSqrt.setEnabled(false);
        bPutSignSqrt.setPreferredSize(new java.awt.Dimension(80, 80));

        bPutSignPower.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignPower.setText("<html>\n.  [ ]\n<br>\n[ ]");
        bPutSignPower.setEnabled(false);
        bPutSignPower.setPreferredSize(new java.awt.Dimension(80, 80));

        bPutSignIndex.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignIndex.setText("<html>\n[ ]\n<br>\n.[ ]");
        bPutSignIndex.setEnabled(false);
        bPutSignIndex.setPreferredSize(new java.awt.Dimension(80, 80));

        bPutSignVector.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bPutSignVector.setText("<html>\n-->\n<br>\n[ ]");
        bPutSignVector.setEnabled(false);
        bPutSignVector.setPreferredSize(new java.awt.Dimension(80, 80));

        bRestartConstruction.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        bRestartConstruction.setText("Сбросить");
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
                            .addComponent(bPutSignVector, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
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
        insertSplitterAndEmptyElements("+");
    }//GEN-LAST:event_bPutSignPlusActionPerformed

    private void bPutNumber1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber1ActionPerformed
        currentFormula.replaceAtomText("1", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber1ActionPerformed

    private void bPutNumber2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber2ActionPerformed
        currentFormula.replaceAtomText("2", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber2ActionPerformed

    private void bPutNumber3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber3ActionPerformed
        currentFormula.replaceAtomText("3", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber3ActionPerformed

    private void bPutNumber4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber4ActionPerformed
        currentFormula.replaceAtomText("4", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber4ActionPerformed

    private void bPutNumber5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber5ActionPerformed
        currentFormula.replaceAtomText("5", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber5ActionPerformed

    private void bPutNumber6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber6ActionPerformed
        currentFormula.replaceAtomText("6", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber6ActionPerformed

    private void bPutNumber7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber7ActionPerformed
        currentFormula.replaceAtomText("7", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber7ActionPerformed

    private void bPutNumber8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber8ActionPerformed
        currentFormula.replaceAtomText("8", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber8ActionPerformed

    private void bPutNumber9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber9ActionPerformed
        currentFormula.replaceAtomText("9", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber9ActionPerformed

    private void bPutNumber0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutNumber0ActionPerformed
        currentFormula.replaceAtomText("0", currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bPutNumber0ActionPerformed

    private void tableLatinAlphabetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLatinAlphabetMouseClicked
        Object value = tableLatinAlphabet.getValueAt(
                tableLatinAlphabet.getSelectedRow(),
                tableLatinAlphabet.getSelectedColumn());
        if (value != null) {
            currentFormula.replaceAtomText(value.toString(), currentFormula.getSelectedIndex());
        }
        drawFormula();
    }//GEN-LAST:event_tableLatinAlphabetMouseClicked

    private void tableGreekAlphabetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGreekAlphabetMouseClicked
        Object value = tableGreekAlphabet.getValueAt(
                tableGreekAlphabet.getSelectedRow(),
                tableGreekAlphabet.getSelectedColumn());
        if (value != null) {
            currentFormula.replaceAtomText(value.toString(), currentFormula.getSelectedIndex());
        }
        drawFormula();
    }//GEN-LAST:event_tableGreekAlphabetMouseClicked

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
        smallGreekLetters = !smallGreekLetters;
    }//GEN-LAST:event_bChangeRegisterGreekAlphabetMouseClicked

    private void bPutSignMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMinusActionPerformed
        insertSplitterAndEmptyElements("-");
    }//GEN-LAST:event_bPutSignMinusActionPerformed

    private void bPutSignMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMultiActionPerformed
        insertSplitterAndEmptyElements("●");
    }//GEN-LAST:event_bPutSignMultiActionPerformed

    private void bClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearAllActionPerformed
        for (int i = 0; i < currentFormula.getElementsCount(); i++) {
            currentFormula.replaceAtomText(null, i);
        }
        drawFormula();
    }//GEN-LAST:event_bClearAllActionPerformed

    private void bCloseFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseFormActionPerformed
        confirmExit();
    }//GEN-LAST:event_bCloseFormActionPerformed

    private void bSaveFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveFormulaActionPerformed
        if (currentFormula.hasEmptyElements()) {
            JOptionPane.showMessageDialog(null,
                    "В формуле не должно быть пустых элементов",
                    "Предупреждение", JOptionPane.CLOSED_OPTION);
        } else {
            formulaTranscription = currentFormula.getTranscription();
            dispose();
        }
    }//GEN-LAST:event_bSaveFormulaActionPerformed

    private void bUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUndoActionPerformed
        if (stackFormula.size() > 1) {
            currentFormula = new Formula(stackFormula.pop());
        } else {
            currentFormula = new Formula(stackFormula.peek());
        }
        drawFormula();
    }//GEN-LAST:event_bUndoActionPerformed

    private void bClearElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearElementActionPerformed
        currentFormula.replaceAtomText(null, currentFormula.getSelectedIndex());
        drawFormula();
    }//GEN-LAST:event_bClearElementActionPerformed

    private void paneEditFormulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneEditFormulaMouseClicked
        currentFormula.setSelectedAtom(evt.getX(), evt.getY());
        drawFormula();
    }//GEN-LAST:event_paneEditFormulaMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        confirmExit();
    }//GEN-LAST:event_formWindowClosing

    private void bRestartConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRestartConstructionActionPerformed
        makeDefaultFormula();
        drawFormula();
    }//GEN-LAST:event_bRestartConstructionActionPerformed


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
