package forms;

import javax.swing.JButton;

/**
 *
 * @author Алексей
 */
public class PassageTestForm extends javax.swing.JDialog {
    public PassageTestForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        jPanel1 = new javax.swing.JPanel();
        lQuestionFormulation = new javax.swing.JLabel();
        toolBarQuestionNumbers = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        bNextQuestion.setText("Следующий вопрос");
        bNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextQuestionActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lQuestionFormulation.setText("Формулировка вопроса");

        toolBarQuestionNumbers.setFloatable(false);
        toolBarQuestionNumbers.setRollover(true);

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
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(toolBarQuestionNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bPreviusQuestion)
                        .addComponent(bNextQuestion))
                    .addComponent(toolBarQuestionNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    }//GEN-LAST:event_bUndoActionPerformed

    private void bRestartConstructionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRestartConstructionActionPerformed

    }//GEN-LAST:event_bRestartConstructionActionPerformed

    private void bClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearAllActionPerformed

    }//GEN-LAST:event_bClearAllActionPerformed

    private void bClearElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearElementActionPerformed

    }//GEN-LAST:event_bClearElementActionPerformed

    private void bPutSignPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignPlusActionPerformed

    }//GEN-LAST:event_bPutSignPlusActionPerformed

    private void bPutSignMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMinusActionPerformed

    }//GEN-LAST:event_bPutSignMinusActionPerformed

    private void bPutSignMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPutSignMultiActionPerformed

    }//GEN-LAST:event_bPutSignMultiActionPerformed

    private void bNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextQuestionActionPerformed
        toolBarQuestionNumbers.add(new JButton("1"));
        toolBarQuestionNumbers.updateUI();
    }//GEN-LAST:event_bNextQuestionActionPerformed


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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lQuestionFormulation;
    private javax.swing.JScrollPane sPaneForSymbolsTable;
    private javax.swing.JTable tableSymbols;
    private javax.swing.JToolBar toolBarQuestionNumbers;
    // End of variables declaration//GEN-END:variables

}
