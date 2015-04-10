package forms;

import entities.Disciplina;
import entities.Test;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import static sql.DBManager.entityManager;

/**
 *
 * @author ScanNorOne
 */
public class TestForm extends javax.swing.JDialog {

    private List<Disciplina> subjects;
    private List<Test> tests;
    private final ListModel SUBJECT_LIST_MODEL = new AbstractListModel() {

        @Override
        public int getSize() {
            return subjects.size();
        }

        @Override
        public Object getElementAt(int index) {
            return subjects.get(index).getNazvanie();
        }
    };
    private final TableModel TEST_TABLE_MODEL = new AbstractTableModel() {

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
            if (columnIndex == 0)
                return Boolean.class;
            return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public TestForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        listSubjects.setModel(SUBJECT_LIST_MODEL);
        tableListOfTests.setModel(TEST_TABLE_MODEL);
        refresh();
//        tableListOfTests.getColumnModel().getColumn(0).setWidth(5);
        JTableHeader header = tableListOfTests.getTableHeader();
        header.getColumnModel().getColumn(1).setWidth(50);
        header.getColumnModel().getColumn(0).setHeaderValue("Открыт");
        header.getColumnModel().getColumn(1).setHeaderValue("Название теста");
        tableListOfTests.setTableHeader(header);
        tableListOfTests.getColumnModel().getColumn(0);
        if (subjects != null) {
            listSubjects.updateUI();
        }

    }

    private void refresh() {
        try {
            subjects = entityManager.createNamedQuery(
                    "Disciplina.findAll",
                    Disciplina.class).getResultList();
            int selectedIndex = listSubjects.getSelectedIndex();
            if (selectedIndex == -1) {
                //отобразить все тесты
                tests = entityManager.createNamedQuery("Test.findAll",
                        Test.class).getResultList();
            } else if (selectedIndex < subjects.size()) {
                //отобразить тесты, которые содержат только вопросы по
                //заданной дисциплине
                TypedQuery<Test> query = entityManager.createQuery(
                        "SELECT tv.testIdTest from TestVopros tv WHERE "
                        + "tv.voprosIdVopros.disciplinaIdDisciplina."
                        + "idDisciplina=:id GROUP BY tv.testIdTest.idTest", 
                        Test.class);
                query.setParameter("id",
                        subjects.get(selectedIndex).getIdDisciplina());
                tests = query.getResultList();
            }
            tableListOfTests.updateUI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
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

        listSubjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listSubjectsMouseClicked(evt);
            }
        });
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lSubjects)
                    .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lTests)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bCreateTest, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(bDeleteTest, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bEditTest))
                    .addComponent(sPaneForTests, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bClose, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bOpenAccess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCloseAccess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bViewResult, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lSubjects)
                            .addComponent(lTests))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bClose))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bOpenAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bCloseAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bViewResult, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sPaneForTests, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bCreateTest)
                                    .addComponent(bDeleteTest)
                                    .addComponent(bEditTest))))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bCreateTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateTestActionPerformed
        int selectedIndex = listSubjects.getSelectedIndex();
        if (selectedIndex != -1) {
            AddTestForm addTest = new AddTestForm(null, true);
            addTest.setSubject(subjects.get(selectedIndex));
            addTest.setVisible(true);
            refresh();
        } else {
            JOptionPane.showMessageDialog(null, "Дисциплина не выбрана",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bCreateTestActionPerformed

    private void bOpenAccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOpenAccessActionPerformed
        
        boolean correct = true; 
        
        if (tableListOfTests.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Тест не выбран",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
            correct = false;
        }
        
        
        if (correct) {
            
            Test test = tests.get(tableListOfTests.getSelectedRow());
            
            OpenTestForm openTest = new OpenTestForm(null, true);
            openTest.setTest(test);
            openTest.setVisible(true);
        }
    }//GEN-LAST:event_bOpenAccessActionPerformed

    private void bViewResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bViewResultActionPerformed
        TestResultForm testResult = new TestResultForm(null, true);
        testResult.setVisible(true);
    }//GEN-LAST:event_bViewResultActionPerformed

    private void listSubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSubjectsMouseClicked
        refresh();
    }//GEN-LAST:event_listSubjectsMouseClicked


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
