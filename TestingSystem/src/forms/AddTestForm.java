package forms;

import entities.Disciplina;
import entities.StatusTesta;
import entities.Test;
import entities.TestVopros;
import entities.Vopros;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import sql.DBManager;
import static sql.DBManager.entityManager;

/**
 *
 * @author ScanNorOne
 */
public class AddTestForm extends javax.swing.JDialog {

    private Disciplina subject;
    private List<Vopros> allQuestions;
    private final List<Vopros> TEST_QUESTIONS;
    private final ListModel ALL_QUESTION_LIST_MODEL = new AbstractListModel() {

        @Override
        public int getSize() {
            return allQuestions.size();
        }

        @Override
        public Object getElementAt(int index) {
            return allQuestions.get(index).getNazvanie();
        }
    };
    private final ListModel TEST_QUESTION_LIST_MODEL = new AbstractListModel() {

        @Override
        public int getSize() {
            return TEST_QUESTIONS.size();
        }

        @Override
        public Object getElementAt(int index) {
            return TEST_QUESTIONS.get(index).getNazvanie();
        }
    };

    public AddTestForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        TEST_QUESTIONS = new ArrayList<>();
        listTestQuestions.setModel(TEST_QUESTION_LIST_MODEL);
    }

    public void setSubject(Disciplina subject) {
        this.subject = subject;
        refresh();
        if (allQuestions != null) {
            listAllQuestions.setModel(ALL_QUESTION_LIST_MODEL);
            listAllQuestions.updateUI();
        }
    }

    private void refresh() {
        try {
            Query query = entityManager.createQuery(
                    "SELECT v FROM Vopros v WHERE "
                    + "v.disciplinaIdDisciplina.idDisciplina=:id",
                    Vopros.class);
            query.setParameter("id", subject.getIdDisciplina());
            allQuestions = query.getResultList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lTestName = new javax.swing.JLabel();
        textTestName = new javax.swing.JTextField();
        lQuestions = new javax.swing.JLabel();
        bPlaceHigh = new javax.swing.JButton();
        bPlaceLow = new javax.swing.JButton();
        scrollPaneForAllQuestions = new javax.swing.JScrollPane();
        listAllQuestions = new javax.swing.JList();
        bAddQuestionToTest = new javax.swing.JButton();
        bRemoveQuestionFromTest = new javax.swing.JButton();
        scrollPaneForTestQuestions = new javax.swing.JScrollPane();
        listTestQuestions = new javax.swing.JList();
        bSearch = new javax.swing.JButton();
        textSearch = new javax.swing.JTextField();
        bSaveTest = new javax.swing.JButton();
        bClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Новый тест");
        setResizable(false);

        lTestName.setText("Название теста:");

        lQuestions.setText("Вопросы теста");

        bPlaceHigh.setText("Переместить выше");
        bPlaceHigh.setEnabled(false);

        bPlaceLow.setText("Переместить ниже");
        bPlaceLow.setEnabled(false);

        scrollPaneForAllQuestions.setViewportView(listAllQuestions);

        bAddQuestionToTest.setText("<");
        bAddQuestionToTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddQuestionToTestActionPerformed(evt);
            }
        });

        bRemoveQuestionFromTest.setText(">");
        bRemoveQuestionFromTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemoveQuestionFromTestActionPerformed(evt);
            }
        });

        scrollPaneForTestQuestions.setViewportView(listTestQuestions);

        bSearch.setText("Поиск");
        bSearch.setEnabled(false);

        textSearch.setEnabled(false);

        bSaveTest.setText("Сохранить");
        bSaveTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveTestActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lTestName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textTestName))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lQuestions)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bPlaceHigh)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bPlaceLow))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(scrollPaneForTestQuestions)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bAddQuestionToTest, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bRemoveQuestionFromTest, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(textSearch)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bSearch))
                                            .addComponent(scrollPaneForAllQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(3, 3, 3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(bClose)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bSaveTest)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTestName)
                    .addComponent(textTestName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lQuestions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bPlaceHigh)
                            .addComponent(bPlaceLow))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPaneForTestQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bSearch)
                                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPaneForAllQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bAddQuestionToTest, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bRemoveQuestionFromTest, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(134, 134, 134)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSaveTest)
                            .addComponent(bClose))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bAddQuestionToTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddQuestionToTestActionPerformed
        int selectedIndex = listAllQuestions.getSelectedIndex();
        if (selectedIndex != -1 && selectedIndex < allQuestions.size()) {
            TEST_QUESTIONS.add(allQuestions.get(selectedIndex));
            allQuestions.remove(selectedIndex);
            listAllQuestions.updateUI();
            listTestQuestions.updateUI();
        }
    }//GEN-LAST:event_bAddQuestionToTestActionPerformed

    private void bRemoveQuestionFromTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemoveQuestionFromTestActionPerformed
        int selectedIndex = listTestQuestions.getSelectedIndex();
        if (selectedIndex != -1 && selectedIndex < TEST_QUESTIONS.size()) {
            allQuestions.add(TEST_QUESTIONS.get(selectedIndex));
            TEST_QUESTIONS.remove(selectedIndex);
            listAllQuestions.updateUI();
            listTestQuestions.updateUI();
        }
    }//GEN-LAST:event_bRemoveQuestionFromTestActionPerformed

    private void bSaveTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveTestActionPerformed
        boolean correct = true;
        if (textTestName.getText().isEmpty()) {
            correct = false;
            JOptionPane.showMessageDialog(this,
                    "Название теста не может быть пустым",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
        } else if (TEST_QUESTIONS.isEmpty()) {
            correct = false;
            JOptionPane.showMessageDialog(this,
                    "Не выбрано ни одного вопроса",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
        }
        if (correct) {
            Test test = new Test();
            test.setNazvanie(new String(textTestName.getText().getBytes(), UTF_8));
            StatusTesta statusTesta;
            TypedQuery<StatusTesta> query = entityManager.createNamedQuery(
                    "StatusTesta.findByNaimenovanie", StatusTesta.class);
            query.setParameter("naimenovanie", "Закрыт");
            statusTesta = query.getSingleResult();
            test.setStatusTestaIdStatusTesta(statusTesta);
            try {
                DBManager.writeObject(test);
                //добавить данные о соответствии тестов и вопросов
                List<TestVopros> testVoproses = new ArrayList<>();
                for (Vopros v : TEST_QUESTIONS) {
                    TestVopros testVopros = new TestVopros();
                    testVopros.setTestIdTest(test);
                    testVopros.setVoprosIdVopros(v);
                    testVoproses.add(testVopros);//                    
                }
                test.setTestVoprosList(testVoproses);
                entityManager.getTransaction().begin();
                entityManager.merge(test);
                entityManager.getTransaction().commit();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString(),
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        }

    }//GEN-LAST:event_bSaveTestActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddQuestionToTest;
    private javax.swing.JButton bClose;
    private javax.swing.JButton bPlaceHigh;
    private javax.swing.JButton bPlaceLow;
    private javax.swing.JButton bRemoveQuestionFromTest;
    private javax.swing.JButton bSaveTest;
    private javax.swing.JButton bSearch;
    private javax.swing.JLabel lQuestions;
    private javax.swing.JLabel lTestName;
    private javax.swing.JList listAllQuestions;
    private javax.swing.JList listTestQuestions;
    private javax.swing.JScrollPane scrollPaneForAllQuestions;
    private javax.swing.JScrollPane scrollPaneForTestQuestions;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextField textTestName;
    // End of variables declaration//GEN-END:variables
}
