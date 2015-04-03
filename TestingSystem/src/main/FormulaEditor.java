package main;

import java.awt.Graphics;

/**
 *
 * @author Алексей
 */
public class FormulaEditor extends javax.swing.JDialog {

    private Formula formula;
    private Graphics graphics;

    public FormulaEditor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        graphics = paneFormulaEditor.getGraphics();
        formula = new Formula();
        formula.add("[ ]");
        formula.add("=");
        formula.add("[ ]");
    }

    public void draw(){
        graphics.clearRect(0, 0, paneFormulaEditor.getWidth(), 
                paneFormulaEditor.getHeight());
        formula.show(graphics);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneFormulaEditor = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        paneFormulaEditor.setBackground(new java.awt.Color(153, 153, 153));
        paneFormulaEditor.setPreferredSize(new java.awt.Dimension(500, 300));
        paneFormulaEditor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneFormulaEditorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneFormulaEditorLayout = new javax.swing.GroupLayout(paneFormulaEditor);
        paneFormulaEditor.setLayout(paneFormulaEditorLayout);
        paneFormulaEditorLayout.setHorizontalGroup(
            paneFormulaEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        paneFormulaEditorLayout.setVerticalGroup(
            paneFormulaEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        paneNumberButtons.setBackground(new java.awt.Color(153, 153, 153));

        bPutNumber2.setText("2");
        bPutNumber2.setPreferredSize(new java.awt.Dimension(50, 50));

        bPutNumber1.setText("1");
        bPutNumber1.setPreferredSize(new java.awt.Dimension(50, 50));

        bPutNumber3.setText("3");
        bPutNumber3.setPreferredSize(new java.awt.Dimension(50, 50));

        bPutNumber4.setText("4");
        bPutNumber4.setPreferredSize(new java.awt.Dimension(50, 50));

        bPutNumber5.setText("5");
        bPutNumber5.setPreferredSize(new java.awt.Dimension(50, 50));

        bPutNumber6.setText("6");
        bPutNumber6.setPreferredSize(new java.awt.Dimension(50, 50));

        bPutNumber7.setText("7");
        bPutNumber7.setPreferredSize(new java.awt.Dimension(50, 50));

        bPutNumber8.setText("8");
        bPutNumber8.setPreferredSize(new java.awt.Dimension(50, 50));

        bPutNumber9.setText("9");
        bPutNumber9.setPreferredSize(new java.awt.Dimension(50, 50));

        bPutNumber0.setText("0");
        bPutNumber0.setPreferredSize(new java.awt.Dimension(50, 50));

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
                {"π", "ρ", "ς", "σ", "τ"},
                {"υ", "φ", "χ", "ψ", "ω"}
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
        scrollPaneForGreekAlphabet.setViewportView(tableGreekAlphabet);

        bChangeRegisterGreekAlphabet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bChangeRegisterGreekAlphabet.setText("<html>δ\n<br>|\n<br>|\n<br>v\n<br>Δ");

        bChangeRegisterLatinAlphabet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bChangeRegisterLatinAlphabet.setText("<html>a\n<br>|\n<br>|\n<br>v\n<br>A");

        bPutSignMinus.setText("[ ] - [ ]");

        bPutSignPlus.setText("[ ] + [ ]");
        bPutSignPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPutSignPlusActionPerformed(evt);
            }
        });

        bPutSignMulti.setText("[ ] x [ ]");

        bCloseForm.setText("Закрыть");

        bSaveFormula.setText("Сохранить");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                    .addComponent(paneFormulaEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bPutSignMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutSignPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPutSignMulti, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(128, Short.MAX_VALUE))
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
                    .addComponent(paneFormulaEditor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bPutSignPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bPutSignMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bPutSignMulti, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        draw();
    }//GEN-LAST:event_bPutSignPlusActionPerformed

    private void paneFormulaEditorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneFormulaEditorMouseClicked
        formula.setSelectedAtom(evt.getX(), evt.getY());
        System.out.println(evt.getX());
        draw();
    }//GEN-LAST:event_paneFormulaEditorMouseClicked


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
    private javax.swing.JPanel paneFormulaEditor;
    private javax.swing.JPanel paneNumberButtons;
    private javax.swing.JScrollPane scrollPaneForGreekAlphabet;
    private javax.swing.JScrollPane scrollPaneForLatinAlphabet;
    private javax.swing.JTable tableGreekAlphabet;
    private javax.swing.JTable tableLatinAlphabet;
    // End of variables declaration//GEN-END:variables

}
