package forms;

import entities.PolozhenieKartinki;
import entities.TestVopros;
import entities.Vopros;
import entities.VoprosLatex;
import entities.VoprosPeretaskivanieKartinok;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author ScanNorOne
 */
public class QuestionsForm extends javax.swing.JDialog {

    private List<Vopros> questions;
    private final String[] TABLE_HEADERS = {
        "Название вопроса",
        "Дисциплина",
        "Тип вопроса",
        "Сложность",
        "Балл"
    };
    private final TableModel QUESTIONS_TABLE_MODEL = new AbstractTableModel() {

        @Override
        public int getRowCount() {
            return questions.size();
        }

        @Override
        public int getColumnCount() {
            return TABLE_HEADERS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return questions.get(rowIndex).getNazvanie();
                case 1:
                    return questions.get(rowIndex).getDisciplinaIdDisciplina().
                            getNazvanie();
                case 2:
                    return questions.get(rowIndex).
                            getTipVoprosaIdTipVoprosa().getNazvanie();
                case 3:
                    return questions.get(rowIndex).
                            getKategoriyaSlozhnostiIdKategoriyaSlozhnosti().
                            getNazvanie();
                case 4:
                    return questions.get(rowIndex).getBall();
            }
            return null;
        }
    };

    public QuestionsForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        tableQuestions.setModel(QUESTIONS_TABLE_MODEL);
        refresh();
        JTableHeader header = tableQuestions.getTableHeader();
        for (int i = 0; i < TABLE_HEADERS.length; i++) {
            header.getColumnModel().getColumn(i).setHeaderValue(TABLE_HEADERS[i]);
        }
        header.getColumnModel().getColumn(0).setWidth(200);
        header.getColumnModel().getColumn(1).setWidth(50);
        header.getColumnModel().getColumn(2).setWidth(100);
        header.getColumnModel().getColumn(3).setWidth(20);
        header.getColumnModel().getColumn(4).setWidth(10);

        tableQuestions.setTableHeader(header);
        tableQuestions.getColumnModel().getColumn(0).setPreferredWidth(200);
        tableQuestions.getColumnModel().getColumn(1).setPreferredWidth(50);
        tableQuestions.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableQuestions.getColumnModel().getColumn(3).setPreferredWidth(20);
        tableQuestions.getColumnModel().getColumn(4).setPreferredWidth(10);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lQuestionsList = new javax.swing.JLabel();
        textSearchQuestion = new javax.swing.JTextField();
        bSearchQuestion = new javax.swing.JButton();
        bCreateQuestion = new javax.swing.JButton();
        bDeleteQuestion = new javax.swing.JButton();
        bEditQuestion = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableQuestions = new javax.swing.JTable();
        bCancelSearchingQuestion = new javax.swing.JButton();
        lSearchingName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Вопросы");
        setResizable(false);

        lQuestionsList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestionsList.setText("Список вопросов:");

        textSearchQuestion.setFocusCycleRoot(true);

        bSearchQuestion.setText("Найти");
        bSearchQuestion.setPreferredSize(new java.awt.Dimension(100, 23));
        bSearchQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchQuestionActionPerformed(evt);
            }
        });

        bCreateQuestion.setText("Создать");
        bCreateQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateQuestionActionPerformed(evt);
            }
        });

        bDeleteQuestion.setText("Удалить");
        bDeleteQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteQuestionActionPerformed(evt);
            }
        });

        bEditQuestion.setText("Редактировать");
        bEditQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditQuestionActionPerformed(evt);
            }
        });

        bClose.setText("Закрыть");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        tableQuestions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Название", "Дисциплина", "Тип вопроса", "Сложность", "Балл"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        jScrollPane1.setViewportView(tableQuestions);

        bCancelSearchingQuestion.setText("Сбросить");
        bCancelSearchingQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelSearchingQuestionActionPerformed(evt);
            }
        });

        lSearchingName.setText("Поиск по названию вопроса:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bCreateQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bDeleteQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bEditQuestion))
                            .addComponent(lQuestionsList)
                            .addComponent(lSearchingName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bClose, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(textSearchQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bSearchQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bCancelSearchingQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lQuestionsList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSearchQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSearchQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancelSearchingQuestion)
                    .addComponent(lSearchingName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCreateQuestion)
                    .addComponent(bDeleteQuestion)
                    .addComponent(bEditQuestion))
                .addGap(5, 5, 5)
                .addComponent(bClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refresh() {
        try {
            questions = entityManager.createNamedQuery("Vopros.findAll",
                    Vopros.class).getResultList();
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
        }
        if (questions != null) {
            tableQuestions.updateUI();
        }
    }

    private void bCreateQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateQuestionActionPerformed
        QuestionEditorForm questionEditor = new QuestionEditorForm(null, true);
        questionEditor.setVisible(true);
        refresh();
    }//GEN-LAST:event_bCreateQuestionActionPerformed

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bDeleteQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteQuestionActionPerformed
        int selectedIndex = tableQuestions.getSelectedRow();

        if (selectedIndex != -1 && selectedIndex < questions.size()) {
            Vopros vopros = entityManager.find(Vopros.class,
                    questions.get(selectedIndex).getIdVopros());
            if (vopros != null) {
                TypedQuery<TestVopros> queryForTestVopros = entityManager.createQuery(
                        "SELECT tv FROM TestVopros tv "
                        + "WHERE tv.voprosIdVopros.idVopros=:id",
                        TestVopros.class);
                queryForTestVopros.setParameter("id", vopros.getIdVopros());
                List<TestVopros> relativeTestVoproses = queryForTestVopros.getResultList();

                boolean allowDelete;
                if (!relativeTestVoproses.isEmpty()) {
                    allowDelete = DialogManager.confirmDeleting("Данный вопрос содержится в тестах. "
                            + "Вы действительно хотите удалить его? "
                            + "Он также будет удален из всех тестов, где содержится.");
                } else {
                    allowDelete = DialogManager.confirmDeleting("Вы действительно хотите удалить данный вопрос?");
                }
                if (allowDelete) {
                    boolean deleted = false;
                    switch (questions.get(selectedIndex).
                            getTipVoprosaIdTipVoprosa().getIdTipVoprosa()) {
                        case 1:
                            //вопрос Latex
                            TypedQuery<VoprosLatex> queryForVoprosLates = entityManager.
                                    createQuery("Select v FROM VoprosLatex v "
                                            + "WHERE v.voprosIdVopros.idVopros=:id",
                                            VoprosLatex.class);
                            queryForVoprosLates.setParameter("id", vopros.getIdVopros());
                            VoprosLatex voprosLatex = queryForVoprosLates.
                                    getSingleResult();
                            if (voprosLatex != null) {
                                try {
                                    entityManager.getTransaction().begin();
                                    entityManager.remove(voprosLatex);
                                    entityManager.remove(vopros);
                                    entityManager.getTransaction().commit();
                                    deleted = true;
                                } catch (Exception ex) {
                                    DialogManager.errorMessage(ex);
                                }
                            }//endif
                            break;
                        case 2:
                            //вопрос с перетаскиванием картинок
                            TypedQuery<VoprosPeretaskivanieKartinok> queryForVoprosPeretaskivanieKartinok = entityManager.
                                    createQuery("Select v FROM VoprosPeretaskivanieKartinok v "
                                            + "WHERE v.voprosIdVopros.idVopros=:id",
                                            VoprosPeretaskivanieKartinok.class);
                            queryForVoprosPeretaskivanieKartinok.setParameter("id", vopros.getIdVopros());
                            VoprosPeretaskivanieKartinok voprosPeretaskivanieKartinok = queryForVoprosPeretaskivanieKartinok.
                                    getSingleResult();
                            if (voprosPeretaskivanieKartinok != null) {
                                try {
                                    entityManager.getTransaction().begin();
                                    PolozhenieKartinki polozhenieKartinki;
                                    for (int i = 0; i < voprosPeretaskivanieKartinok.getPolozhenieKartinkiList().size(); i++) {
                                        polozhenieKartinki = voprosPeretaskivanieKartinok.getPolozhenieKartinkiList().get(i);
                                        entityManager.remove(polozhenieKartinki);
                                    }
                                    entityManager.remove(voprosPeretaskivanieKartinok);
                                    entityManager.remove(vopros);
                                    entityManager.getTransaction().commit();
                                    deleted = true;
                                } catch (Exception ex) {
                                    DialogManager.errorMessage(ex);
                                }
                            }//endif
                            break;
                    }//end switch
                    entityManager.getTransaction().begin();
                    //удалить тест-вопросы
                    int linkedTestVoprosAmount = relativeTestVoproses.size();
                    for (int i = 0; i < linkedTestVoprosAmount; i++) {
                        entityManager.remove(relativeTestVoproses.get(i));
                    }
                    entityManager.getTransaction().commit();
                    if (deleted) {
                        refresh();
                    }
                }//end if(allowDelete)
            }//end if(vopros ? null)
        }//end if(selected index ? -1 || selected index ? question.size)
    }//GEN-LAST:event_bDeleteQuestionActionPerformed

    private void bEditQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditQuestionActionPerformed
        int selectedIndex = tableQuestions.getSelectedRow();
        if (selectedIndex != -1 && selectedIndex < questions.size()) {
            Vopros vopros = entityManager.find(Vopros.class,
                    questions.get(selectedIndex).getIdVopros());
            QuestionEditorForm questionEditorForm = new QuestionEditorForm(null, true);
            questionEditorForm.setQuestion(vopros);
            questionEditorForm.setVisible(true);
            refresh();
        }
    }//GEN-LAST:event_bEditQuestionActionPerformed

    private void bSearchQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchQuestionActionPerformed
        String inputQuestionName = textSearchQuestion.getText();

        List<Vopros> tempQuestionList = null;
        try {
            tempQuestionList = entityManager.createNamedQuery("Vopros.findAll",
                    Vopros.class).getResultList();
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
        }
        questions.clear();
        for (Vopros singleQuestion : tempQuestionList) {
            if (inputQuestionName.length() <= singleQuestion.getNazvanie().length()) {
                String iteratedQuestion = singleQuestion.
                        getNazvanie().substring(0, inputQuestionName.length());
                if (iteratedQuestion.equalsIgnoreCase(inputQuestionName)) {
                    questions.add(singleQuestion);
                }
            }
        }

        if (questions != null) {
            tableQuestions.updateUI();
        }
    }//GEN-LAST:event_bSearchQuestionActionPerformed

    private void bCancelSearchingQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelSearchingQuestionActionPerformed
        textSearchQuestion.setText(null);
        refresh();
    }//GEN-LAST:event_bCancelSearchingQuestionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelSearchingQuestion;
    private javax.swing.JButton bClose;
    private javax.swing.JButton bCreateQuestion;
    private javax.swing.JButton bDeleteQuestion;
    private javax.swing.JButton bEditQuestion;
    private javax.swing.JButton bSearchQuestion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lQuestionsList;
    private javax.swing.JLabel lSearchingName;
    private javax.swing.JTable tableQuestions;
    private javax.swing.JTextField textSearchQuestion;
    // End of variables declaration//GEN-END:variables
}
