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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListModel;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import sql.DBManager;
import static sql.DBManager.entityManager;

/**
 *
 * @author Alexey Ovcharov & Artem Solovenko
 */
public class EditTestForm extends javax.swing.JDialog {

    private Disciplina subject;
    private List<Vopros> allQuestions;
    private Test test;

    private EditMode editMode;

    private enum EditMode {

        CREATE,
        EDIT
    }

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
        allQuestions = new ArrayList<>();
        listTestQuestions.setModel(TEST_QUESTION_LIST_MODEL);
        //Режим работы
        editMode = EditMode.CREATE;
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
        this.editMode = EditMode.EDIT;
        if (!test.getTestVoprosList().get(0).getVoprosIdVopros().
                getVoprosPeretaskivanieKartinokList().isEmpty()) {
            //Тест состоит из вопросов на перетаскивание картинок
            Object o = comboChooseTypeOfTest.getItemAt(1);
            comboChooseTypeOfTest.setModel(
                    new DefaultComboBoxModel(new Object[]{o})
            );
        }
        comboChooseTypeOfTest.setEnabled(false);

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
        TEST_QUESTIONS.clear();
        allQuestions.clear();

        //Выбираем режим работы
        switch (editMode) {
            case CREATE:
                switch (comboChooseTypeOfTest.getSelectedIndex()) {
                    case 0:
                        //Выбираем только вопросы Latex при загрузке формы
                        try {
                            Query query = entityManager.createQuery(
                                    "SELECT v FROM Vopros v WHERE "
                                    + "v.disciplinaIdDisciplina.idDisciplina=:id",
                                    Vopros.class);
                            query.setParameter("id", subject.getIdDisciplina());
                            List<Vopros> tempResult = query.getResultList();
                            for (Vopros singleResult : tempResult) {
                                if (!singleResult.getVoprosLatexList().isEmpty()) {
                                    allQuestions.add(singleResult);
                                }
                            }

                        } catch (Exception ex) {
                            DialogManager.errorMessage(ex);
                        }
                        break;
                    case 1:
                        //Выбираем только вопросы Puzzle при загрузке формы
                        try {
                            Query query = entityManager.createQuery(
                                    "SELECT v FROM Vopros v WHERE "
                                    + "v.disciplinaIdDisciplina.idDisciplina=:id",
                                    Vopros.class);
                            query.setParameter("id", subject.getIdDisciplina());
                            List<Vopros> tempResult = query.getResultList();
                            for (Vopros singleResult : tempResult) {
                                if (!singleResult.getVoprosPeretaskivanieKartinokList().isEmpty()) {
                                    allQuestions.add(singleResult);
                                }
                            }
                        } catch (Exception ex) {
                            DialogManager.errorMessage(ex);
                        }
                        break;
                }
                break;
            case EDIT:
                String neededType = comboChooseTypeOfTest.getItemAt(0).toString();
                if (neededType.equalsIgnoreCase("Конструирование формулы")) {
                    //Выбираем только вопросы Latex при загрузке формы
                    try {
                        Query query = entityManager.createQuery(
                                "SELECT v FROM Vopros v WHERE "
                                + "v.disciplinaIdDisciplina.idDisciplina=:id",
                                Vopros.class);
                        query.setParameter("id", subject.getIdDisciplina());
                        List<Vopros> tempResult = query.getResultList();
                        for (Vopros singleResult : tempResult) {
                            if (!singleResult.getVoprosLatexList().isEmpty()) {
                                allQuestions.add(singleResult);
                            }
                        }

                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }
                } else {
                    //Выбираем только вопросы Puzzle при загрузке формы
                    try {
                        Query query = entityManager.createQuery(
                                "SELECT v FROM Vopros v WHERE "
                                + "v.disciplinaIdDisciplina.idDisciplina=:id",
                                Vopros.class);
                        query.setParameter("id", subject.getIdDisciplina());
                        List<Vopros> tempResult = query.getResultList();
                        for (Vopros singleResult : tempResult) {
                            if (!singleResult.getVoprosPeretaskivanieKartinokList().isEmpty()) {
                                allQuestions.add(singleResult);
                            }
                        }
                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }
                }
                break;
        }

        listAllQuestions.updateUI();
        listTestQuestions.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lTestName = new javax.swing.JLabel();
        textTestName = new javax.swing.JTextField();
        lQuestions = new javax.swing.JLabel();
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
        bCancelSearch = new javax.swing.JButton();
        bTypeTest = new javax.swing.JLabel();
        comboChooseTypeOfTest = new javax.swing.JComboBox();

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

        lQuestions.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lQuestions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lQuestions.setText("Вопросы теста");

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
        bSearch.setPreferredSize(new java.awt.Dimension(90, 23));
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });

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

        lQuestionsInSubject.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lQuestionsInSubject.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lQuestionsInSubject.setText("Все вопросы по дисциплине");

        bCancelSearch.setText("Сбросить");
        bCancelSearch.setPreferredSize(new java.awt.Dimension(90, 23));
        bCancelSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelSearchActionPerformed(evt);
            }
        });

        bTypeTest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bTypeTest.setText("Выберите тип теста:");

        comboChooseTypeOfTest.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Конструирование формулы", "Сборка из фрагментов" }));
        comboChooseTypeOfTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboChooseTypeOfTestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bSaveTest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bClose))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lTestName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textTestName))
                            .addComponent(lQuestions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(scrollPaneForTestQuestions, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bRemoveQuestionFromTest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bAddQuestionToTest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(textSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPaneForAllQuestions, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                            .addComponent(lQuestionsInSubject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bTypeTest, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboChooseTypeOfTest, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTestName)
                    .addComponent(textTestName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bTypeTest)
                    .addComponent(comboChooseTypeOfTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQuestionsInSubject)
                    .addComponent(lQuestions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(scrollPaneForTestQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
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
            DialogManager.notify("Предупреждение",
                    "Название теста не может быть пустым",
                    DialogManager.TypeOfMessage.WARNING);
        } else if (TEST_QUESTIONS.isEmpty()) {
            correct = false;
            DialogManager.notify("Предупреждение",
                    "Не выбрано ни одного вопроса",
                    DialogManager.TypeOfMessage.WARNING);
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
        String typedQuestionName = textSearch.getText();

        for (int i = 0; i < allQuestions.size();) {
            if (typedQuestionName.length() <= allQuestions.get(i).
                    getNazvanie().length()) {
                String iteratedQuestionName = allQuestions.get(i).
                        getNazvanie().substring(0, typedQuestionName.length());
                if (!iteratedQuestionName.equalsIgnoreCase(typedQuestionName)) {
                    allQuestions.remove(i);
                } else {
                    ++i;
                }
            }
        }
        listAllQuestions.updateUI();
    }//GEN-LAST:event_bSearchActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        if (DialogManager.confirmClosingForm("теста")) {
            dispose();
        }

    }//GEN-LAST:event_formWindowClosing

    private void comboChooseTypeOfTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboChooseTypeOfTestActionPerformed
        allQuestions.clear();
        TEST_QUESTIONS.clear();
        switch (comboChooseTypeOfTest.getSelectedIndex()) {
            case 0:
                //Выбираем только вопросы Latex при загрузке формы
                try {
                    Query query = entityManager.createQuery(
                            "SELECT v FROM Vopros v WHERE "
                            + "v.disciplinaIdDisciplina.idDisciplina=:id",
                            Vopros.class);
                    query.setParameter("id", subject.getIdDisciplina());
                    List<Vopros> tempResult = query.getResultList();
                    for (Vopros singleResult : tempResult) {
                        if (!singleResult.getVoprosLatexList().isEmpty()) {
                            allQuestions.add(singleResult);
                        }
                    }

                } catch (Exception ex) {
                    DialogManager.errorMessage(ex);
                }
                break;
            case 1:
                //Выбираем только вопросы Puzzle при загрузке формы
                try {
                    Query query = entityManager.createQuery(
                            "SELECT v FROM Vopros v WHERE "
                            + "v.disciplinaIdDisciplina.idDisciplina=:id",
                            Vopros.class);
                    query.setParameter("id", subject.getIdDisciplina());
                    List<Vopros> tempResult = query.getResultList();
                    for (Vopros singleResult : tempResult) {
                        if (!singleResult.getVoprosPeretaskivanieKartinokList().isEmpty()) {
                            allQuestions.add(singleResult);
                        }
                    }
                } catch (Exception ex) {
                    DialogManager.errorMessage(ex);
                }
                break;
        }
        listAllQuestions.updateUI();
        listTestQuestions.updateUI();
    }//GEN-LAST:event_comboChooseTypeOfTestActionPerformed

    private void bCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelSearchActionPerformed
        textSearch.setText(null);
        allQuestions.clear();
        switch (editMode) {
            case CREATE:
                switch (comboChooseTypeOfTest.getSelectedIndex()) {
                    case 0:
                        //Выбираем только вопросы Latex при загрузке формы
                        try {
                            Query query = entityManager.createQuery(
                                    "SELECT v FROM Vopros v WHERE "
                                    + "v.disciplinaIdDisciplina.idDisciplina=:id",
                                    Vopros.class);
                            query.setParameter("id", subject.getIdDisciplina());
                            List<Vopros> tempResult = query.getResultList();
                            for (Vopros singleResult : tempResult) {
                                if (!singleResult.getVoprosLatexList().isEmpty()) {
                                    if (!TEST_QUESTIONS.contains(singleResult)) {
                                        allQuestions.add(singleResult);
                                    }
                                }
                            }

                        } catch (Exception ex) {
                            DialogManager.errorMessage(ex);
                        }
                        break;
                    case 1:
                        //Выбираем только вопросы Puzzle при загрузке формы
                        try {
                            Query query = entityManager.createQuery(
                                    "SELECT v FROM Vopros v WHERE "
                                    + "v.disciplinaIdDisciplina.idDisciplina=:id",
                                    Vopros.class);
                            query.setParameter("id", subject.getIdDisciplina());
                            List<Vopros> tempResult = query.getResultList();
                            for (Vopros singleResult : tempResult) {
                                if (!singleResult.getVoprosPeretaskivanieKartinokList().isEmpty()) {
                                    if (!TEST_QUESTIONS.contains(singleResult)) {
                                        allQuestions.add(singleResult);
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            DialogManager.errorMessage(ex);
                        }
                        break;
                }
                break;
            case EDIT:
                String neededType = comboChooseTypeOfTest.getItemAt(0).toString();
                if (neededType.equalsIgnoreCase("Конструирование формулы")) {
                    //Выбираем только вопросы Latex при загрузке формы
                    try {
                        Query query = entityManager.createQuery(
                                "SELECT v FROM Vopros v WHERE "
                                + "v.disciplinaIdDisciplina.idDisciplina=:id",
                                Vopros.class);
                        query.setParameter("id", subject.getIdDisciplina());
                        List<Vopros> tempResult = query.getResultList();
                        for (Vopros singleResult : tempResult) {
                            if (!singleResult.getVoprosLatexList().isEmpty()) {
                                if (!TEST_QUESTIONS.contains(singleResult)) {
                                    allQuestions.add(singleResult);
                                }
                            }
                        }

                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }
                } else {
                    //Выбираем только вопросы Puzzle при загрузке формы
                    try {
                        Query query = entityManager.createQuery(
                                "SELECT v FROM Vopros v WHERE "
                                + "v.disciplinaIdDisciplina.idDisciplina=:id",
                                Vopros.class);
                        query.setParameter("id", subject.getIdDisciplina());
                        List<Vopros> tempResult = query.getResultList();
                        for (Vopros singleResult : tempResult) {
                            if (!singleResult.getVoprosPeretaskivanieKartinokList().isEmpty()) {
                                if (!TEST_QUESTIONS.contains(singleResult)) {
                                    allQuestions.add(singleResult);
                                }
                            }
                        }
                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }
                }
                break;
        }

        listAllQuestions.updateUI();
    }//GEN-LAST:event_bCancelSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddQuestionToTest;
    private javax.swing.JButton bCancelSearch;
    private javax.swing.JButton bClose;
    private javax.swing.JButton bRemoveQuestionFromTest;
    private javax.swing.JButton bSaveTest;
    private javax.swing.JButton bSearch;
    private javax.swing.JLabel bTypeTest;
    private javax.swing.JComboBox comboChooseTypeOfTest;
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
