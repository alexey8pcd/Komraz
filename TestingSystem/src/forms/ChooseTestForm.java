package forms;

import entities.Test;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import static sql.DBManager.entityManager;

/**
 *
 * @author ScanNorOne
 */
public class ChooseTestForm extends javax.swing.JDialog {

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
        try {
            TypedQuery<Test> query = entityManager.createQuery(
                    "SELECT t FROM Test t where "
                    + "t.statusTestaIdStatusTesta.idStatusTesta=1",
                    Test.class);
            availableTests = query.getResultList();            
            listTests.setModel(LISTS_TEST_MODEL);
            listTests.updateUI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

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
                    .addComponent(scrollPaneForListTests)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lAvailableTests)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bStartTest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                        .addComponent(bClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lAvailableTests)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneForListTests, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bStartTest)
                    .addComponent(bClose))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            passageTestForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Тест не выбран",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
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
