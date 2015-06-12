package forms;

import static resources.Parameters.SCREEN_SIZE;

/**
 *
 * @author ScanNorOne
 */
public class MainForm extends javax.swing.JFrame {

    public MainForm() {
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bTests = new javax.swing.JButton();
        bQuestions = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        panePicture = new javax.swing.JPanel();
        bPictureManager = new javax.swing.JButton();
        bPassageTest = new javax.swing.JButton();
        bGroups = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Тестирующая система");
        setResizable(false);

        bTests.setText("Тесты");
        bTests.setPreferredSize(new java.awt.Dimension(160, 160));
        bTests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTestsActionPerformed(evt);
            }
        });

        bQuestions.setText("Вопросы");
        bQuestions.setPreferredSize(new java.awt.Dimension(160, 160));
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

        panePicture.setBackground(new java.awt.Color(204, 204, 204));
        panePicture.setToolTipText("");
        panePicture.setName("qqq"); // NOI18N

        javax.swing.GroupLayout panePictureLayout = new javax.swing.GroupLayout(panePicture);
        panePicture.setLayout(panePictureLayout);
        panePictureLayout.setHorizontalGroup(
            panePictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panePictureLayout.setVerticalGroup(
            panePictureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );

        bPictureManager.setText("<html><center>Менеджер <br>изображений");
        bPictureManager.setPreferredSize(new java.awt.Dimension(160, 160));
        bPictureManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPictureManagerActionPerformed(evt);
            }
        });

        bPassageTest.setText("Пройти тест");
        bPassageTest.setPreferredSize(new java.awt.Dimension(160, 160));
        bPassageTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPassageTestActionPerformed(evt);
            }
        });

        bGroups.setText("Группы и Студенты");
        bGroups.setPreferredSize(new java.awt.Dimension(160, 160));
        bGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGroupsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panePicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bClose, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bPictureManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bGroups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bTests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bPassageTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(bTests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bQuestions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bPassageTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bGroups, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(bPictureManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panePicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bClose)
                .addContainerGap())
        );

        panePicture.getAccessibleContext().setAccessibleName("");
        bGroups.getAccessibleContext().setAccessibleName("jButtonGroup");

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

    private void bGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGroupsActionPerformed
        GroupForm groupForm = new GroupForm(this, true);
        groupForm.setVisible(true);
    }//GEN-LAST:event_bGroupsActionPerformed

    private void bPictureManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPictureManagerActionPerformed
        PictureManagerForm pictureManagerForm = new PictureManagerForm(this, true);
        pictureManagerForm.setVisible(true);
    }//GEN-LAST:event_bPictureManagerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bGroups;
    private javax.swing.JButton bPassageTest;
    private javax.swing.JButton bPictureManager;
    private javax.swing.JButton bQuestions;
    private javax.swing.JButton bTests;
    private javax.swing.JPanel panePicture;
    // End of variables declaration//GEN-END:variables
}
