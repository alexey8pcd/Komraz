package forms;

/**
 *
 * @author ScanNorOne
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bGroups = new javax.swing.JButton();
        bTests = new javax.swing.JButton();
        bQuestions = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        bPassageTest = new javax.swing.JButton();
        panePicture = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        bGroups.setText("Группы");

        bTests.setText("Тесты");
        bTests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTestsActionPerformed(evt);
            }
        });

        bQuestions.setText("Вопросы");
        bQuestions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bQuestionsActionPerformed(evt);
            }
        });

        bClose.setText("Закрыть");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        bPassageTest.setText("Пройти тест");
        bPassageTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPassageTestActionPerformed(evt);
            }
        });

        panePicture.setName("qqq"); // NOI18N

        javax.swing.GroupLayout panePictureLayout = new javax.swing.GroupLayout(panePicture);
        panePicture.setLayout(panePictureLayout);
        panePictureLayout.setHorizontalGroup(
            panePictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );
        panePictureLayout.setVerticalGroup(
            panePictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(bQuestions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bPassageTest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bTests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bGroups, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bClose)
                    .addComponent(panePicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bGroups)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bTests)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bQuestions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(bPassageTest))
                    .addComponent(panePicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bClose)
                .addContainerGap())
        );

        bGroups.getAccessibleContext().setAccessibleName("jButtonGroup");
        panePicture.getAccessibleContext().setAccessibleName("");
        panePicture.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bTestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTestsActionPerformed
        TestForm tests = new TestForm(this, true);
        tests.setVisible(true);
    }//GEN-LAST:event_bTestsActionPerformed

    private void bQuestionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bQuestionsActionPerformed
        QuestionsForm questionsForm = new QuestionsForm(this, true);
        questionsForm.setVisible(true);
    }//GEN-LAST:event_bQuestionsActionPerformed

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bPassageTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPassageTestActionPerformed
        ChooseTestForm chooseTestForm = new ChooseTestForm(this, true);
        chooseTestForm.setVisible(true);
    }//GEN-LAST:event_bPassageTestActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bGroups;
    private javax.swing.JButton bPassageTest;
    private javax.swing.JButton bQuestions;
    private javax.swing.JButton bTests;
    private javax.swing.JPanel panePicture;
    // End of variables declaration//GEN-END:variables
}
