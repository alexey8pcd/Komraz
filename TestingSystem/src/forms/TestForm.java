package forms;

/**
 *
 * @author ScanNorOne
 */
public class TestForm extends javax.swing.JDialog {

    /**
     * Creates new form TestForm
     */
    public TestForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sPaneForListSubjects = new javax.swing.JScrollPane();
        listSubjects = new javax.swing.JList();
        lSubjects = new javax.swing.JLabel();
        sPaneForTests = new javax.swing.JScrollPane();
        tableListOfTests = new javax.swing.JTable();
        lTests = new javax.swing.JLabel();
        bOpenAccess = new javax.swing.JButton();
        bCloseAccess = new javax.swing.JButton();
        bViewResult = new javax.swing.JButton();
        bEditTest = new javax.swing.JButton();
        bDeleteTest = new javax.swing.JButton();
        bCreateTest = new javax.swing.JButton();
        bClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Тесты");
        setResizable(false);

        sPaneForListSubjects.setViewportView(listSubjects);

        lSubjects.setText("Дисциплины:");

        tableListOfTests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Открыт", "Название теста"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sPaneForTests.setViewportView(tableListOfTests);
        if (tableListOfTests.getColumnModel().getColumnCount() > 0) {
            tableListOfTests.getColumnModel().getColumn(0).setMinWidth(40);
            tableListOfTests.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableListOfTests.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        lTests.setText("Тесты:");

        bOpenAccess.setText("<html>\n<center>\nОткрыть <br>\nдоступ к тесту\n</html>");
        bOpenAccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOpenAccessActionPerformed(evt);
            }
        });

        bCloseAccess.setText("<html>\n<center>\nЗакрыть <br>\nдоступ к тесту\n</html>");

        bViewResult.setText("<html>\n<center>\nПросмотреть <br>\nрезультаты\n</html>");
        bViewResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bViewResultActionPerformed(evt);
            }
        });

        bEditTest.setText("Редактировать");

        bDeleteTest.setText("Удалить");

        bCreateTest.setText("Создать");
        bCreateTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateTestActionPerformed(evt);
            }
        });

        bClose.setText("Закрыть");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bClose)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lSubjects)
                            .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lTests)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bCreateTest, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bDeleteTest, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bEditTest))
                            .addComponent(sPaneForTests, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bOpenAccess)
                            .addComponent(bCloseAccess)
                            .addComponent(bViewResult))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lSubjects)
                    .addComponent(lTests))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sPaneForTests, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bOpenAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bCloseAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bViewResult, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 119, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCreateTest)
                            .addComponent(bDeleteTest)
                            .addComponent(bEditTest)))
                    .addComponent(sPaneForListSubjects))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bCreateTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateTestActionPerformed
        AddTestForm addTest = new AddTestForm(null, true);
        addTest.setVisible(true);
    }//GEN-LAST:event_bCreateTestActionPerformed

    private void bOpenAccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOpenAccessActionPerformed
        OpenTestForm openTest = new OpenTestForm(null, true);
        openTest.setVisible(true);
    }//GEN-LAST:event_bOpenAccessActionPerformed

    private void bViewResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bViewResultActionPerformed
        TestResultForm testResult = new TestResultForm(null, true);
        testResult.setVisible(true);
    }//GEN-LAST:event_bViewResultActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bCloseAccess;
    private javax.swing.JButton bCreateTest;
    private javax.swing.JButton bDeleteTest;
    private javax.swing.JButton bEditTest;
    private javax.swing.JButton bOpenAccess;
    private javax.swing.JButton bViewResult;
    private javax.swing.JLabel lSubjects;
    private javax.swing.JLabel lTests;
    private javax.swing.JList listSubjects;
    private javax.swing.JScrollPane sPaneForListSubjects;
    private javax.swing.JScrollPane sPaneForTests;
    private javax.swing.JTable tableListOfTests;
    // End of variables declaration//GEN-END:variables
}
