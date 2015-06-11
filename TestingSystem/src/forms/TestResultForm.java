package forms;

import entities.StudentTest;
import entities.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.TypedQuery;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author ScanNorOne
 */
public class TestResultForm extends javax.swing.JDialog {

    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private final String[] TABLE_HEADERS = {
        "ФИО студента",
        "Группа",
        "Дата прохождения",
        "% набранных баллов",
        "Оценка"
    };

    private enum FilterType {

        BY_NAME,
        BY_GROUP,
        BY_DATE
    }

    private FilterType filterType;
    private List<StudentTest> results;
    private Test test;

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
                    return results.get(rowIndex).getStudentIdStudent().
                            getGruppaIdGruppa().getNazvanie();
                case 2:
                    return DATE_FORMAT.format(results.get(rowIndex).
                            getDataProhozhdeniya());
                case 3:
                    return results.get(rowIndex).getProcentBallov();
                case 4:
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
        filterType = FilterType.BY_NAME;
        refreshFilterPane();
    }

    private final DefaultFormatterFactory DATE_FORMATTER
            = new DefaultFormatterFactory(new DateFormatter());

    private void refreshFilterPane() {
        switch (filterType) {
            case BY_DATE:
                ftfInputNameOrStartDate.setText(null);
                lEndDate.setVisible(true);
                ftfInputEndDate.setVisible(true);
                lNameOrStartDate.setText("С");
                lEndDate.setText("По");
                ftfInputNameOrStartDate.setFormatterFactory(DATE_FORMATTER);
                ftfInputNameOrStartDate.setValue(GregorianCalendar.
                        getInstance().getTime());
                ftfInputEndDate.setFormatterFactory(DATE_FORMATTER);
                ftfInputEndDate.setValue(GregorianCalendar.
                        getInstance().getTime());
                break;
            case BY_NAME:
                ftfInputNameOrStartDate.setText(null);
                lNameOrStartDate.setText("ФИО студента");
                lEndDate.setVisible(false);
                ftfInputNameOrStartDate.setFormatterFactory(null);
                ftfInputEndDate.setVisible(false);
                break;
            case BY_GROUP:
                ftfInputNameOrStartDate.setText(null);
                lNameOrStartDate.setText("Название группы");
                lEndDate.setVisible(false);
                ftfInputNameOrStartDate.setFormatterFactory(null);
                ftfInputEndDate.setVisible(false);
                break;
        }
    }

    public void setTest(Test test) {
        lTestName.setText(test.getNazvanie());
        this.test = test;
        TypedQuery<StudentTest> query = entityManager.createQuery(
                "SELECT s FROM StudentTest s WHERE s.testIdTest.idTest=:idTest",
                StudentTest.class);
        query.setParameter("idTest", test.getIdTest());
        try {
            results = loadAllResultsFromStudentTest();
            tableForTestResult.updateUI();
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
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

    /**
     * Метод загрузки всех сущностей StudentTest из БД
     *
     * @return все записи StudentTest из базы данных
     */
    private List<StudentTest> loadAllResultsFromStudentTest() {
        TypedQuery<StudentTest> query = entityManager.createQuery(
                "SELECT s FROM StudentTest s WHERE s.testIdTest.idTest=:idTest",
                StudentTest.class);
        query.setParameter("idTest", test.getIdTest());
        try {
            return query.getResultList();
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
        }
        return null;
    }

    private Date parseDate(String inputStringDate) {
        String day = null, month = null, year = null;
        try {
            String[] parts = inputStringDate.split(Pattern.quote("."));
            day = parts[0];
            month = parts[1];
            year = parts[2];
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.clear();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
        Date date = calendar.getTime();
        return date;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lTestName = new javax.swing.JLabel();
        sPaneForTestResult = new javax.swing.JScrollPane();
        tableForTestResult = new javax.swing.JTable();
        bClose = new javax.swing.JButton();
        bExportToFile = new javax.swing.JButton();
        paneFilter = new javax.swing.JPanel();
        rbStudentName = new javax.swing.JRadioButton();
        rbGroupName = new javax.swing.JRadioButton();
        rbDate = new javax.swing.JRadioButton();
        bApplyFilter = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        lNameOrStartDate = new javax.swing.JLabel();
        lEndDate = new javax.swing.JLabel();
        ftfInputNameOrStartDate = new javax.swing.JFormattedTextField();
        ftfInputEndDate = new javax.swing.JFormattedTextField();
        bChangeCriteria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Результаты теста");

        lTestName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lTestName.setText("Название теста");

        tableForTestResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ФИО студента", "Группа", "Дата прохождения", "% набранных баллов", "Оценка"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        paneFilter.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Отфильтровать результаты"));

        buttonGroup1.add(rbStudentName);
        rbStudentName.setSelected(true);
        rbStudentName.setText("По ФИО студента");
        rbStudentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbStudentNameActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbGroupName);
        rbGroupName.setText("По группе");
        rbGroupName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbGroupNameActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbDate);
        rbDate.setText("По дате");
        rbDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDateActionPerformed(evt);
            }
        });

        bApplyFilter.setText("Отфильтровать");
        bApplyFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApplyFilterActionPerformed(evt);
            }
        });

        bClear.setText("Сбросить");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        lNameOrStartDate.setText("Название");

        lEndDate.setText("Название 2");

        ftfInputNameOrStartDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        javax.swing.GroupLayout paneFilterLayout = new javax.swing.GroupLayout(paneFilter);
        paneFilter.setLayout(paneFilterLayout);
        paneFilterLayout.setHorizontalGroup(
            paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbDate)
                    .addComponent(rbGroupName)
                    .addComponent(rbStudentName))
                .addGap(102, 102, 102)
                .addGroup(paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lNameOrStartDate)
                    .addComponent(ftfInputNameOrStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneFilterLayout.createSequentialGroup()
                        .addComponent(bApplyFilter)
                        .addGap(18, 18, 18)
                        .addComponent(bClear))
                    .addComponent(lEndDate)
                    .addComponent(ftfInputEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        paneFilterLayout.setVerticalGroup(
            paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneFilterLayout.createSequentialGroup()
                .addGroup(paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneFilterLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bApplyFilter)
                            .addComponent(bClear)))
                    .addGroup(paneFilterLayout.createSequentialGroup()
                        .addGroup(paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paneFilterLayout.createSequentialGroup()
                                .addComponent(rbStudentName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbGroupName))
                            .addGroup(paneFilterLayout.createSequentialGroup()
                                .addGroup(paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNameOrStartDate)
                                    .addComponent(lEndDate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(paneFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ftfInputNameOrStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftfInputEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbDate)
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );

        bChangeCriteria.setText("Изменить критерий оценки");
        bChangeCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChangeCriteriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sPaneForTestResult)
                    .addComponent(lTestName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(paneFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bExportToFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bClose))
                            .addComponent(bChangeCriteria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lTestName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sPaneForTestResult, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(bChangeCriteria)
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bClose)
                            .addComponent(bExportToFile)))
                    .addComponent(paneFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void rbDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDateActionPerformed
        filterType = FilterType.BY_DATE;
        refreshFilterPane();
    }//GEN-LAST:event_rbDateActionPerformed

    private void rbGroupNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGroupNameActionPerformed
        filterType = FilterType.BY_GROUP;
        refreshFilterPane();
    }//GEN-LAST:event_rbGroupNameActionPerformed

    private void rbStudentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbStudentNameActionPerformed
        filterType = FilterType.BY_NAME;
        refreshFilterPane();
    }//GEN-LAST:event_rbStudentNameActionPerformed

    private void bChangeCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChangeCriteriaActionPerformed
        CriteriaForm criteriaForm = new CriteriaForm(null, true);
        criteriaForm.setCriteria(50, 70, 90);
        criteriaForm.setVisible(true);
    }//GEN-LAST:event_bChangeCriteriaActionPerformed

    private void bApplyFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApplyFilterActionPerformed
        switch (filterType) {
            case BY_NAME:
                if (!ftfInputNameOrStartDate.getText().isEmpty()) {
                    String typedFio = ftfInputNameOrStartDate.getText();
                    List<StudentTest> tempResults = loadAllResultsFromStudentTest();

                    results.clear();
                    for (StudentTest singleResult : tempResults) {
                        if (typedFio.length() <= singleResult.
                                getStudentIdStudent().getFio().length()) {
                            String iteratedFio = singleResult.
                                    getStudentIdStudent().
                                    getFio().substring(0, typedFio.length());
                            if (iteratedFio.equalsIgnoreCase(typedFio)) {
                                results.add(singleResult);
                            }
                        }
                    }
                    tableForTestResult.updateUI();
                } else {
                    results = loadAllResultsFromStudentTest();
                    tableForTestResult.updateUI();
                }
                break;
            case BY_GROUP:
                if (!ftfInputNameOrStartDate.getText().isEmpty()) {
                    String typedGroupName = ftfInputNameOrStartDate.getText();
                    List<StudentTest> tempResults = loadAllResultsFromStudentTest();

                    results.clear();
                    for (StudentTest singleResult : tempResults) {
                        if (typedGroupName.length() <= singleResult.
                                getStudentIdStudent().getGruppaIdGruppa().
                                getNazvanie().length()) {
                            String iteratedGroupName = singleResult.
                                    getStudentIdStudent().
                                    getGruppaIdGruppa().
                                    getNazvanie().substring(0, typedGroupName.length());
                            if (iteratedGroupName.equalsIgnoreCase(typedGroupName)) {
                                results.add(singleResult);
                            }
                        }
                    }
                    tableForTestResult.updateUI();
                } else {
                    results = loadAllResultsFromStudentTest();
                    tableForTestResult.updateUI();
                }
                break;
            case BY_DATE:
                if (!ftfInputNameOrStartDate.getText().isEmpty()
                        && !ftfInputEndDate.getText().isEmpty()) {
                    String parseableStartDate = ftfInputNameOrStartDate.getText();
                    String parseableEndDate = ftfInputEndDate.getText();
                    Date typedStartDate = null;
                    Date typedEndDate = null;
                    try {
                        typedStartDate = parseDate(parseableStartDate);
                        typedEndDate = parseDate(parseableEndDate);
                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }

                    List<StudentTest> tempResults = loadAllResultsFromStudentTest();

                    results.clear();
                    for (StudentTest singleResult : tempResults) {
                        Date iteratedDate = singleResult.getDataProhozhdeniya();
                        if (iteratedDate.after(typedStartDate)
                                && iteratedDate.before(typedEndDate)) {
                            results.add(singleResult);
                        }
                    }
                    tableForTestResult.updateUI();
                } else {
                    results = loadAllResultsFromStudentTest();
                    tableForTestResult.updateUI();
                }
                break;
        }
    }//GEN-LAST:event_bApplyFilterActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        ftfInputEndDate.setText(null);
        ftfInputNameOrStartDate.setText(null);
        if (filterType == FilterType.BY_DATE) {
            ftfInputNameOrStartDate.setValue(GregorianCalendar.
                    getInstance().getTime());
            ftfInputEndDate.setValue(GregorianCalendar.
                    getInstance().getTime());
        }
        results = loadAllResultsFromStudentTest();
        tableForTestResult.updateUI();
    }//GEN-LAST:event_bClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bApplyFilter;
    private javax.swing.JButton bChangeCriteria;
    private javax.swing.JButton bClear;
    private javax.swing.JButton bClose;
    private javax.swing.JButton bExportToFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField ftfInputEndDate;
    private javax.swing.JFormattedTextField ftfInputNameOrStartDate;
    private javax.swing.JLabel lEndDate;
    private javax.swing.JLabel lNameOrStartDate;
    private javax.swing.JLabel lTestName;
    private javax.swing.JPanel paneFilter;
    private javax.swing.JRadioButton rbDate;
    private javax.swing.JRadioButton rbGroupName;
    private javax.swing.JRadioButton rbStudentName;
    private javax.swing.JScrollPane sPaneForTestResult;
    private javax.swing.JTable tableForTestResult;
    // End of variables declaration//GEN-END:variables
}
