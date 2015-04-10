package forms;

import entities.Test;
import entities.Vopros;
import java.awt.Graphics;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import main.Formula;

/**
 *
 * @author Алексей
 */
public class PassageTestForm extends javax.swing.JDialog {

    private Test testForPassage;
    private Vopros currentQuestion;
    private int currentQuestionIndex;
    private int questionsAmount;
    private final Graphics graphics;
    private Formula currentFormula;
    private final Stack<Formula> stackFormula;
    private String[] answers;
    /*
    * НУЖНЫЙ КОД ДЛЯ КОРРЕКТНЫХ БУКВ В tableSymbols
    *
    private final TableModel TABLE_SYBOLS_MODEL = new AbstractTableModel() {

        @Override
        public int getRowCount() {
            if (tests != null) {
                return tests.size();
            } else {
                return 0;
            }
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return tests.get(rowIndex).getStatusTestaIdStatusTesta().
                        getNaimenovanie().equalsIgnoreCase("Открыт");
            } else {
                return tests.get(rowIndex).getNazvanie();
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex); 
        }
    };
*/
    public PassageTestForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        stackFormula = new Stack<>();
        makeDefaultFormula();
        addFormulaCopyToStack();
        graphics = paneForFormulaConstruct.getGraphics();
        currentQuestionIndex = 0;
        bPreviusQuestion.setEnabled(false);
    }

    private void updateLabel() {
        labelQuestionNumber.setText("Вопрос "
                + (currentQuestionIndex + 1) + "/" + questionsAmount);
    }

    private void setButtonNextAndPreviusProperties() {
        /**********НУЖНОЕ***************
        if (currentQuestionIndex == 0) {
            bPreviusQuestion.setEnabled(false);
        } else {
            bPreviusQuestion.setEnabled(true);
        }
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
        bPreviusQuestion = new javax.swing.JButton();
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

        bPreviusQuestion.setText("Предыдущий вопрос");
        bPreviusQuestion.setEnabled(false);
        bPreviusQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPreviusQuestionActionPerformed(evt);
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
                            .addComponent(bPreviusQuestion)
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
                            .addComponent(bPreviusQuestion)
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
            }
        }
        setButtonNextAndPreviusProperties();
        updateLabel();
    }//GEN-LAST:event_bNextQuestionActionPerformed

    private void bPreviusQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPreviusQuestionActionPerformed
        setButtonNextAndPreviusProperties();
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            updateQuestion();
            drawFormula();
        }
        updateLabel();
    }//GEN-LAST:event_bPreviusQuestionActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClearAll;
    private javax.swing.JButton bClearElement;
    private javax.swing.JButton bCompleteTest;
    private javax.swing.JButton bNextQuestion;
    private javax.swing.JButton bPreviusQuestion;
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
