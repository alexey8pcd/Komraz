package forms;

import entities.StudentTest;
import entities.Test;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.TypedQuery;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author Solovenko
 */
public class ClearTestResultsForm extends javax.swing.JDialog {

    private final DefaultFormatterFactory DATE_FORMATTER
            = new DefaultFormatterFactory(new DateFormatter());

    private Test test;
    private List<StudentTest> results;

    public ClearTestResultsForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);

        lStatusClearing.setForeground(Color.BLACK);
        
        //Устанавливаем textFields на формат Date
        ftfStartDate.setFormatterFactory(DATE_FORMATTER);
        ftfStartDate.setValue(GregorianCalendar.getInstance().getTime());
        ftfEndDate.setFormatterFactory(DATE_FORMATTER);
        ftfEndDate.setValue(GregorianCalendar.getInstance().getTime());
    }

    public void setTest(Test test) {
        this.test = test;
        results = new ArrayList<>();
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

    /**
     * Метод для распознавания даты по строке
     *
     * @param inputStringDate дата в строковом виде
     * @return дата в формате <code>Date
     */
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

    private boolean deleteTestResults() {
        boolean isDeleted = false;

        if (!ftfStartDate.getText().isEmpty()
                && !ftfEndDate.getText().isEmpty()) {
            String parseableStartDate = ftfStartDate.getText();
            String parseableEndDate = ftfEndDate.getText();
            Date typedStartDate = null;
            Date typedEndDate = null;
            try {
                typedStartDate = parseDate(parseableStartDate);
                typedEndDate = parseDate(parseableEndDate);
            } catch (Exception ex) {
                DialogManager.errorMessage(ex);
            }

            List<StudentTest> tempResults = loadAllResultsFromStudentTest();

            for (StudentTest singleResult : tempResults) {
                Date iteratedDate = singleResult.getDataProhozhdeniya();
                if (iteratedDate.after(typedStartDate)
                        && iteratedDate.before(typedEndDate)) {
                    //Удаляем
                    try {
                        entityManager.getTransaction().begin();
                        entityManager.remove(singleResult);
                        entityManager.getTransaction().commit();
                        isDeleted = true;
                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }
                }
            }
        }
        return isDeleted;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lStartDate = new javax.swing.JLabel();
        lEndDate = new javax.swing.JLabel();
        bClear = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        lStatusClearing = new javax.swing.JLabel();
        ftfStartDate = new javax.swing.JFormattedTextField();
        ftfEndDate = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Очистка результатов теста за период");

        lStartDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lStartDate.setText("C:");

        lEndDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lEndDate.setText("По:");

        bClear.setText("Очистить");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
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
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(lEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(ftfEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(lStatusClearing, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lStartDate)
                    .addComponent(lEndDate))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(lStatusClearing, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bClear)
                    .addComponent(bClose))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed

        dispose();

    }//GEN-LAST:event_bCloseActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        if (DialogManager.confirmDeleting("Вы действительно хотите удалить "
                + "результаты теста за выбранный период?")) {
            //Удаляем
            if (deleteTestResults()) {
                lStatusClearing.setForeground(Color.GREEN);
                lStatusClearing.setText("Результаты за выбранный период успешно удалены");
            } else {
                lStatusClearing.setForeground(Color.RED);
                lStatusClearing.setText("Результатов за выбранный период не обнаружено");
            }
        }
    }//GEN-LAST:event_bClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bClose;
    private javax.swing.JFormattedTextField ftfEndDate;
    private javax.swing.JFormattedTextField ftfStartDate;
    private javax.swing.JLabel lEndDate;
    private javax.swing.JLabel lStartDate;
    private javax.swing.JLabel lStatusClearing;
    // End of variables declaration//GEN-END:variables
}
