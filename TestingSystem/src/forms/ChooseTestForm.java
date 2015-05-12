package forms;

import entities.Student;
import entities.Test;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author ScanNorOne
 */
public class ChooseTestForm extends javax.swing.JDialog {

    private Student student;

    private List<Test> availableTests;
    private final ListModel LISTS_TEST_MODEL = new AbstractListModel() {

        @Override
        public int getSize() {
            return availableTests.size();
        }

        @Override
        public Object getElementAt(int index) {
            return availableTests.get(index).getNazvanie();
        }
    };

    public ChooseTestForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        try {
            TypedQuery<Test> query = entityManager.createQuery(
                    "SELECT t FROM Test t where "
                    + "t.statusTestaIdStatusTesta.idStatusTesta=1",
                    Test.class);
            availableTests = query.getResultList();
            listTests.setModel(LISTS_TEST_MODEL);
            listTests.updateUI();
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
        }

    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lAvailableTests = new javax.swing.JLabel();
        scrollPaneForListTests = new javax.swing.JScrollPane();
        listTests = new javax.swing.JList();
        bStartTest = new javax.swing.JButton();
        bClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Выберите тест");
        setResizable(false);

        lAvailableTests.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lAvailableTests.setText("Доступные тесты:");

        scrollPaneForListTests.setViewportView(listTests);

        bStartTest.setText("Начать тест");
        bStartTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartTestActionPerformed(evt);
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
                    .addComponent(scrollPaneForListTests, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lAvailableTests)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bStartTest)
                        .addGap(18, 18, 18)
                        .addComponent(bClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lAvailableTests)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneForListTests, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bClose)
                    .addComponent(bStartTest))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bStartTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartTestActionPerformed
        int selectedIndex = listTests.getSelectedIndex();
        if (selectedIndex != -1 && selectedIndex < availableTests.size()) {
            PassageTestForm passageTestForm = new PassageTestForm(null, true);
            passageTestForm.setTestForPassage(availableTests.get(selectedIndex));
            passageTestForm.setStudent(student);
            passageTestForm.setVisible(true);
        } else {
            DialogManager.notify("Предупреждение", "Тест не выбран", DialogManager.TypeOfMessage.WARNING);
        }
    }//GEN-LAST:event_bStartTestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bStartTest;
    private javax.swing.JLabel lAvailableTests;
    private javax.swing.JList listTests;
    private javax.swing.JScrollPane scrollPaneForListTests;
    // End of variables declaration//GEN-END:variables
}
