package main;

import java.awt.Graphics;
import java.util.Stack;

/**
 *
 * @author Алексей
 */
public class FormulaEditor extends javax.swing.JDialog {

    private Formula currentFormula;
    private final Graphics graphics;
    private boolean smallLatinLetters;
    private boolean smallGreekLetters;
    private Stack<Formula> stackFormula;

    public FormulaEditor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        graphics = paneEditFormula.getGraphics();
        stackFormula = new Stack<>();
        currentFormula = new Formula();
        currentFormula.addEmpty();
        currentFormula.add("=", false);
        currentFormula.addEmpty();
        stackFormula.add(new Formula(currentFormula));
        smallLatinLetters = true;
        smallGreekLetters = true;
    }

    public void drawFormula() {
        graphics.clearRect(0, 0, this.getWidth(),
                this.getHeight());
        currentFormula.show(graphics);
    }

    private void addFormulaCopyToStack() {
        stackFormula.add(new Formula(currentFormula));
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
        paneEditFormula = new javax.swing.JPanel();
        bUndo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

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

        tableLatinAlphabet.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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

        tableGreekAlphabet.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tableGreekAlphabet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"α", "β", "γ", "δ", "ε"},
                {"ζ", "η", "θ", "ι", "κ"},
                {"λ", "μ", "ν", "ξ", "ο"},
                {"π", "ρ", "σ", "τ", "υ"},
                {"φ", "χ", "ψ", "ω", ""}
            },
            new String [] {
                "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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

        bPutSignMinus.setText("[ ] - [ ]");
        bPutSignMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignMinusActionPerformed(evt);
            }
        });

        bPutSignPlus.setText("[ ] + [ ]");
        bPutSignPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignPlusActionPerformed(evt);
            }
        });

        bPutSignMulti.setText("[ ] ● [ ]");
        bPutSignMulti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignMultiActionPerformed(evt);
            }
        });

        bCloseForm.setText("Закрыть");

        bSaveFormula.setText("Сохранить");

        paneEditFormula.setToolTipText("Кликните для создания формулы");
        paneEditFormula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneEditFormulaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneEditFormulaLayout = new javax.swing.GroupLayout(paneEditFormula);
        paneEditFormula.setLayout(paneEditFormulaLayout);
        paneEditFormulaLayout.setHorizontalGroup(
            paneEditFormulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        paneEditFormulaLayout.setVerticalGroup(
            paneEditFormulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        bUndo.setText("Отменить действие");
        bUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUndoActionPerformed(evt);
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
                    .addComponent(paneEditFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bPutSignMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutSignPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutSignMulti, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(bSaveFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bPutSignPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bPutSignMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bPutSignMulti, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paneEditFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(paneNumberButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPaneForLatinAlphabet, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(scrollPaneForGreekAlphabet, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(bChangeRegisterLatinAlphabet, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bChangeRegisterGreekAlphabet, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSaveFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bPutSignPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignPlusActionPerformed
        if (currentFormula.currentIsEmpty()) {
            addFormulaCopyToStack();
            currentFormula.addEmptyIn(currentFormula.getSelectedIndex());
            currentFormula.insertIn("+", currentFormula.getSelectedIndex() + 1, false);
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignPlusActionPerformed

    private void paneEditFormulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneEditFormulaMouseClicked
        currentFormula.setSelectedAtom(evt.getX(), evt.getY());
        drawFormula();
    }//GEN-LAST:event_paneEditFormulaMouseClicked

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
        if (currentFormula.currentIsEmpty()) {
            addFormulaCopyToStack();
            currentFormula.addEmptyIn(currentFormula.getSelectedIndex());
            currentFormula.insertIn("-", currentFormula.getSelectedIndex() + 1, false);
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignMinusActionPerformed

    private void bPutSignMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMultiActionPerformed
        if (currentFormula.currentIsEmpty()) {
            addFormulaCopyToStack();
            currentFormula.addEmptyIn(currentFormula.getSelectedIndex());
            currentFormula.insertIn("●", currentFormula.getSelectedIndex() + 1, false);
            drawFormula();
        }
    }//GEN-LAST:event_bPutSignMultiActionPerformed

    private void bUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUndoActionPerformed
        if (stackFormula.size() > 1) {
            currentFormula = stackFormula.pop();
        } else {
            currentFormula = stackFormula.peek();
        }
        drawFormula();
    }//GEN-LAST:event_bUndoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChangeRegisterGreekAlphabet;
    private javax.swing.JButton bChangeRegisterLatinAlphabet;
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
    private javax.swing.JButton bPutSignMinus;
    private javax.swing.JButton bPutSignMulti;
    private javax.swing.JButton bPutSignPlus;
    private javax.swing.JButton bSaveFormula;
    private javax.swing.JButton bUndo;
    private javax.swing.JPanel paneEditFormula;
    private javax.swing.JPanel paneNumberButtons;
    private javax.swing.JScrollPane scrollPaneForGreekAlphabet;
    private javax.swing.JScrollPane scrollPaneForLatinAlphabet;
    private javax.swing.JTable tableGreekAlphabet;
    private javax.swing.JTable tableLatinAlphabet;
    // End of variables declaration//GEN-END:variables

}
