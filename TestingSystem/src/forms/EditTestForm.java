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
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import sql.DBManager;
import static sql.DBManager.entityManager;

/**
 *
 * @author ScanNorOne
 */
public class EditTestForm extends javax.swing.JDialog {

    private Disciplina subject;
    private List<Vopros> allQuestions;
    private Test test;
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

    public EditTestForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        TEST_QUESTIONS = new ArrayList<>();
        listTestQuestions.setModel(TEST_QUESTION_LIST_MODEL);
    }

    public void setSubject(Disciplina subject) {
        this.subject = subject;
        lQuestionsInSubject.setText("Все вопросы по дисциплине: "
                + subject.getNazvanie());
        refresh();
        if (allQuestions != null) {
            listAllQuestions.setModel(ALL_QUESTION_LIST_MODEL);
            listAllQuestions.updateUI();
        }
    }

    public void setTestForEdit(Test test, Disciplina subject) {
        setSubject(subject);
        this.test = test;
        this.textTestName.setText(test.getNazvanie());
        TEST_QUESTIONS.clear();
//        for (int i = 0; i < test.getTestVoprosList().size(); i++) {
        TypedQuery<TestVopros> queryForVopros = entityManager.createNamedQuery(
                "TestVopros.findAll", TestVopros.class);
        List<TestVopros> allTestVoproses = queryForVopros.getResultList();
        for (int i = 0; i < allTestVoproses.size(); i++) {
            if (allTestVoproses.get(i).getTestIdTest().getIdTest().intValue()
                    == test.getIdTest()) {
                TEST_QUESTIONS.add(allTestVoproses.get(i).getVoprosIdVopros());
            }
        }
//        queryForVopros.setParameter("id", test.getIdTest());
//        TEST_QUESTIONS.add(queryForVopros.getSingleResult());
//            TEST_QUESTIONS.add(test.getTestVoprosList().get(i).
//                    getVoprosIdVopros());
//        }
        for (int i = 0; i < allQuestions.size();) {
            Vopros v = allQuestions.get(i);
            boolean removed = false;
            for (Vopros questionFromTest : TEST_QUESTIONS) {
                if (questionFromTest.getIdVopros().intValue() == v.getIdVopros()) {
                    allQuestions.remove(i);
                    removed = true;
                    break;
                }
            }
            if (!removed) {
                i++;
            }
        }
        listTestQuestions.updateUI();
        listAllQuestions.updateUI();
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
        lQuestionsInSubject = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Новый тест");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lTestName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lTestName.setText("Название теста:");

        lQuestions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });

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

        lQuestionsInSubject.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestionsInSubject.setText("Все вопросы по дисциплине");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lTestName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(textTestName, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bSaveTest))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPaneForTestQuestions)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bPlaceHigh)
                                .addGap(18, 18, 18)
                                .addComponent(bPlaceLow)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lQuestions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bRemoveQuestionFromTest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bAddQuestionToTest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bSearch))
                            .addComponent(scrollPaneForAllQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lQuestionsInSubject))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTestName)
                    .addComponent(textTestName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lQuestions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lQuestionsInSubject))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bPlaceLow)
                            .addComponent(bPlaceHigh))
                        .addGap(18, 18, 18)
                        .addComponent(scrollPaneForTestQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bSearch))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(scrollPaneForAllQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bSaveTest)
                                    .addComponent(bClose))
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bAddQuestionToTest, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bRemoveQuestionFromTest, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(206, 206, 206))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed

        if (DialogManager.confirmClosingForm("теста")) {
            dispose();
        }

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
            List<TestVopros> testVoproses;
            if (test == null) {
                //создаем новый
                test = new Test();
            } else {
                //удалить связные тест-вопросы
                DBManager.requestWithoutAnswerSQL(
                        "delete from test_vopros "
                        + "where test_vopros.TEST_ID_TEST="
                        + test.getIdTest());
            }
            testVoproses = test.getTestVoprosList();
            test.setNazvanie(new String(textTestName.getText().getBytes(), UTF_8));
            StatusTesta statusTesta;
            TypedQuery<StatusTesta> query = entityManager.createNamedQuery(
                    "StatusTesta.findByNaimenovanie", StatusTesta.class);
            query.setParameter("naimenovanie", "Закрыт");
            statusTesta = query.getSingleResult();
            test.setStatusTestaIdStatusTesta(statusTesta);
            try {
                if (testVoproses == null) {
                    testVoproses = new ArrayList<>();
                }
                testVoproses.clear();
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
                DialogManager.errorMessage(ex);
            }
            dispose();
        }

    }//GEN-LAST:event_bSaveTestActionPerformed

    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bSearchActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
        if (DialogManager.confirmClosingForm("теста")) {
            dispose();
        }
        
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddQuestionToTest;
    private javax.swing.JButton bClose;
    private javax.swing.JButton bPlaceHigh;
    private javax.swing.JButton bPlaceLow;
    private javax.swing.JButton bRemoveQuestionFromTest;
    private javax.swing.JButton bSaveTest;
    private javax.swing.JButton bSearch;
    private javax.swing.JLabel lQuestions;
    private javax.swing.JLabel lQuestionsInSubject;
    private javax.swing.JLabel lTestName;
    private javax.swing.JList listAllQuestions;
    private javax.swing.JList listTestQuestions;
    private javax.swing.JScrollPane scrollPaneForAllQuestions;
    private javax.swing.JScrollPane scrollPaneForTestQuestions;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextField textTestName;
    // End of variables declaration//GEN-END:variables
}
