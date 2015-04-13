package forms;

import entities.StudentTest;
import entities.Test;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author ScanNorOne
 */
public class TestResultForm extends javax.swing.JDialog {

    private final String[] TABLE_HEADERS = {
        "ФИО студента",
        "Дата прохождения",
        "% набранных баллов",
        "Оценка"
    };

    private List<StudentTest> results;
    private final TableModel RESULT_TABLE_MODEL = new AbstractTableModel() {

        @Override
        public int getRowCount() {
            return results.size();
        }

        @Override
        public int getColumnCount() {
            return TABLE_HEADERS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return results.get(rowIndex).getStudentIdStudent().getFio();
                case 1:
                    return results.get(rowIndex).
                            getDataProhozhdeniya().toString();
                case 2:
                    return results.get(rowIndex).getProcentBallov();
                case 3:
                    return percentToMark(results.get(rowIndex).
                            getProcentBallov());
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return TABLE_HEADERS[column];
        }
    };

    /**
     * Creates new form TestResult
     *
     * @param parent
     * @param modal
     */
    public TestResultForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2, 
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
    }

    public void setTest(Test test) {
        lTestName.setText(test.getNazvanie());
        TypedQuery<StudentTest> query = entityManager.createQuery(
                "SELECT s FROM StudentTest s WHERE s.testIdTest.idTest=:idTest",
                StudentTest.class);
        query.setParameter("idTest", test.getIdTest());
        try {
            results = query.getResultList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        tableForTestResult.setModel(RESULT_TABLE_MODEL);
    }

    private int percentToMark(int percent) {
        if (percent < 50) {
            return 2;
        }
        if (percent < 70) {
            return 3;
        }
        if (percent < 90) {
            return 4;
        }
        return 5;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lTestName = new javax.swing.JLabel();
        sPaneForTestResult = new javax.swing.JScrollPane();
        tableForTestResult = new javax.swing.JTable();
        bClose = new javax.swing.JButton();
        bExportToFile = new javax.swing.JButton();
        bPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Результаты теста");

        lTestName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lTestName.setText("Название теста");

        tableForTestResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ФИО студента", "Дата прохождения", "% набранных баллов", "Оценка"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sPaneForTestResult.setViewportView(tableForTestResult);

        bClose.setText("Закрыть");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        bExportToFile.setText("Экспорт в файл");
        bExportToFile.setEnabled(false);

        bPrint.setText("Печать");
        bPrint.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bExportToFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bClose))
                    .addComponent(sPaneForTestResult, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addComponent(lTestName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lTestName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sPaneForTestResult, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bClose)
                    .addComponent(bExportToFile)
                    .addComponent(bPrint))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bExportToFile;
    private javax.swing.JButton bPrint;
    private javax.swing.JLabel lTestName;
    private javax.swing.JScrollPane sPaneForTestResult;
    private javax.swing.JTable tableForTestResult;
    // End of variables declaration//GEN-END:variables
}
