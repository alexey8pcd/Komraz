package main;

import entities.questions.Question;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Алексей
 */
public class QuestionEditor extends javax.swing.JDialog {

    private Question question;
    private String formulaTranscription;
    private final Graphics graphics;
    private Formula formula;
    private static final int LEFT_X_OFFSET = 5;
    private static final int TOP_Y_OFFSET = 20;
    private static final int RIGHT_X_OFFSET = 10;
    private static final int BOTTOM_Y_OFFSET = 23;

    public QuestionEditor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        graphics = panePreviewFormula.getGraphics();
    }

    public void draw() {
        graphics.setColor(panePreviewFormula.getBackground());
        graphics.clearRect(LEFT_X_OFFSET, TOP_Y_OFFSET, 
                panePreviewFormula.getWidth() - RIGHT_X_OFFSET,
                panePreviewFormula.getHeight() - BOTTOM_Y_OFFSET);
        if (formula != null) {
            formula.show(graphics);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lQuestionTitle = new javax.swing.JLabel();
        tQuestionTitle = new javax.swing.JTextField();
        lQuestionFormulation = new javax.swing.JLabel();
        sPaneForQuestionFormulation = new javax.swing.JScrollPane();
        textAreaForQuestionFormulation = new javax.swing.JTextArea();
        lQuestionType = new javax.swing.JLabel();
        rbConstructFormula = new javax.swing.JRadioButton();
        rbAssembledFromulaFromPieces = new javax.swing.JRadioButton();
        lFormula = new javax.swing.JLabel();
        bCreateFormula = new javax.swing.JButton();
        bDeleteFormula = new javax.swing.JButton();
        bEditFormula = new javax.swing.JButton();
        bCloseForm = new javax.swing.JButton();
        bSaveQuestion = new javax.swing.JButton();
        panePreviewFormula = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Новый вопрос");
        setResizable(false);

        lQuestionTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestionTitle.setText("Название вопроса:");

        lQuestionFormulation.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestionFormulation.setText("Формулировка вопроса:");

        textAreaForQuestionFormulation.setColumns(20);
        textAreaForQuestionFormulation.setRows(5);
        sPaneForQuestionFormulation.setViewportView(textAreaForQuestionFormulation);

        lQuestionType.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestionType.setText("Тип вопроса:");

        buttonGroup1.add(rbConstructFormula);
        rbConstructFormula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbConstructFormula.setSelected(true);
        rbConstructFormula.setText("Конструирование формулы");

        buttonGroup1.add(rbAssembledFromulaFromPieces);
        rbAssembledFromulaFromPieces.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbAssembledFromulaFromPieces.setText("Сборка формулы из кусочков");
        rbAssembledFromulaFromPieces.setEnabled(false);

        lFormula.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lFormula.setText("Формула:");

        bCreateFormula.setText("Создать");
        bCreateFormula.setPreferredSize(new java.awt.Dimension(100, 30));
        bCreateFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateFormulaActionPerformed(evt);
            }
        });

        bDeleteFormula.setText("Удалить");
        bDeleteFormula.setPreferredSize(new java.awt.Dimension(100, 30));
        bDeleteFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteFormulaActionPerformed(evt);
            }
        });

        bEditFormula.setText("Редактировать");
        bEditFormula.setPreferredSize(new java.awt.Dimension(100, 30));
        bEditFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditFormulaActionPerformed(evt);
            }
        });

        bCloseForm.setText("Закрыть");
        bCloseForm.setPreferredSize(new java.awt.Dimension(100, 30));
        bCloseForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseFormActionPerformed(evt);
            }
        });

        bSaveQuestion.setText("Сохранить");
        bSaveQuestion.setPreferredSize(new java.awt.Dimension(100, 30));
        bSaveQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveQuestionActionPerformed(evt);
            }
        });

        panePreviewFormula.setBorder(javax.swing.BorderFactory.createTitledBorder("Предварительный просмотр"));
        panePreviewFormula.setPreferredSize(new java.awt.Dimension(650, 200));

        javax.swing.GroupLayout panePreviewFormulaLayout = new javax.swing.GroupLayout(panePreviewFormula);
        panePreviewFormula.setLayout(panePreviewFormulaLayout);
        panePreviewFormulaLayout.setHorizontalGroup(
            panePreviewFormulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        panePreviewFormulaLayout.setVerticalGroup(
            panePreviewFormulaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sPaneForQuestionFormulation)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lQuestionTitle)
                        .addGap(18, 18, 18)
                        .addComponent(tQuestionTitle))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bSaveQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbAssembledFromulaFromPieces)
                            .addComponent(lQuestionFormulation)
                            .addComponent(lQuestionType)
                            .addComponent(rbConstructFormula)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lFormula)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(bDeleteFormula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                        .addComponent(bCreateFormula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                    .addComponent(bEditFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addComponent(panePreviewFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 92, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQuestionTitle)
                    .addComponent(tQuestionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(lQuestionFormulation)
                .addGap(18, 18, 18)
                .addComponent(sPaneForQuestionFormulation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lQuestionType)
                .addGap(18, 18, 18)
                .addComponent(rbConstructFormula)
                .addGap(18, 18, 18)
                .addComponent(rbAssembledFromulaFromPieces)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lFormula)
                        .addGap(18, 18, 18)
                        .addComponent(bCreateFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDeleteFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bEditFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panePreviewFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSaveQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseFormActionPerformed

    }//GEN-LAST:event_bCloseFormActionPerformed

    private void bSaveQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveQuestionActionPerformed

    }//GEN-LAST:event_bSaveQuestionActionPerformed

    private void bCreateFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateFormulaActionPerformed
        FormulaEditor formulaEditor = new FormulaEditor(null, true);
        formulaEditor.setVisible(true);
        formulaTranscription = formulaEditor.getFormulaTranscription();
        if (formulaTranscription != null) {
            formula = new Formula(formulaTranscription);
        }
        draw();
    }//GEN-LAST:event_bCreateFormulaActionPerformed

    private void bDeleteFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteFormulaActionPerformed
        formula = null;
        draw();
    }//GEN-LAST:event_bDeleteFormulaActionPerformed

    private void bEditFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditFormulaActionPerformed
        if (formulaTranscription != null) {
            FormulaEditor formulaEditor = new FormulaEditor(null, true);
            formulaEditor.setFormula(formulaTranscription);
            formulaEditor.setVisible(true);
            formulaTranscription = formulaEditor.getFormulaTranscription();
            if (formulaTranscription != null) {
                formula = new Formula(formulaTranscription);
            }
            draw();
        }
    }//GEN-LAST:event_bEditFormulaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCloseForm;
    private javax.swing.JButton bCreateFormula;
    private javax.swing.JButton bDeleteFormula;
    private javax.swing.JButton bEditFormula;
    private javax.swing.JButton bSaveQuestion;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lFormula;
    private javax.swing.JLabel lQuestionFormulation;
    private javax.swing.JLabel lQuestionTitle;
    private javax.swing.JLabel lQuestionType;
    private javax.swing.JPanel panePreviewFormula;
    private javax.swing.JRadioButton rbAssembledFromulaFromPieces;
    private javax.swing.JRadioButton rbConstructFormula;
    private javax.swing.JScrollPane sPaneForQuestionFormulation;
    private javax.swing.JTextField tQuestionTitle;
    private javax.swing.JTextArea textAreaForQuestionFormulation;
    // End of variables declaration//GEN-END:variables
}
