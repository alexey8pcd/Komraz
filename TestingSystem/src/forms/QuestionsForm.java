package forms;

import entities.Disciplina;
import entities.Vopros;
import entities.VoprosLatex;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import static sql.DBManager.entityManager;

/**
 *
 * @author ScanNorOne
 */
public class QuestionsForm extends javax.swing.JDialog {

    private List<Vopros> questions;
    private final String[] tableHeaderValues = {
        "Название вопроса",
        "Дисциплина",
        "Тип вопроса",
        "Сложность",
        "Балл"
    };
    private final TableModel tableModel = new AbstractTableModel() {

        @Override
        public int getRowCount() {
            return questions.size();
        }

        @Override
        public int getColumnCount() {
            return tableHeaderValues.length;
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

    private void loadQuestionsFromDB() {
        try {
            questions = entityManager.createNamedQuery("Vopros.findAll",
                    Vopros.class).getResultList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        if (questions != null) {
            tableQuestions.updateUI();
        }
    }

    public QuestionsForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tableQuestions.setModel(tableModel);
        loadQuestionsFromDB();
        JTableHeader header = tableQuestions.getTableHeader();
        for (int i = 0; i < tableHeaderValues.length; i++) {
            header.getColumnModel().getColumn(i).setHeaderValue(tableHeaderValues[i]);
        }
        tableQuestions.setTableHeader(header);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Вопросы");
        setResizable(false);

        lQuestionsList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestionsList.setText("Список вопросов:");

        textSearchQuestion.setEnabled(false);

        bSearchQuestion.setText("Поиск");
        bSearchQuestion.setEnabled(false);

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
                "Название", "Дисциплина", "Тип вопроса", "Сложность", "Балл за правильный ответ"
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bClose))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textSearchQuestion)
                        .addGap(18, 18, 18)
                        .addComponent(bSearchQuestion))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bCreateQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bDeleteQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bEditQuestion))
                            .addComponent(lQuestionsList))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(bSearchQuestion))
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

    private void bCreateQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateQuestionActionPerformed
        QuestionEditorForm questionEditor = new QuestionEditorForm(null, true);
        questionEditor.setVisible(true);
        loadQuestionsFromDB();
    }//GEN-LAST:event_bCreateQuestionActionPerformed

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bDeleteQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteQuestionActionPerformed
        int index = tableQuestions.getSelectedRow();
        if (index != -1 && index < questions.size()) {
            switch (questions.get(index).
                    getTipVoprosaIdTipVoprosa().getIdTipVoprosa()) {
                case 1:
                    //вопрос Latex
                    Vopros vopros = entityManager.find(
                            Vopros.class, questions.get(index).getIdVopros());
                    VoprosLatex voprosLatex = null;
                    if (vopros != null) {
                        if (!vopros.getVoprosLatexList().isEmpty()) {
                            voprosLatex = vopros.getVoprosLatexList().get(0);
                        }
                        try {
                            if (voprosLatex != null) {
                                entityManager.getTransaction().begin();
                                entityManager.remove(voprosLatex);
                                entityManager.getTransaction().commit();
                            }
                            entityManager.getTransaction().begin();
                            Query query = entityManager.createQuery(
                                    "DELETE FROM Vopros v WHERE v.idVopros=:id");
                            query.setParameter("id", vopros.getIdVopros());
                            query.executeUpdate();
                            entityManager.getTransaction().commit();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.toString(),
                                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }

                    }
            }
        }
        loadQuestionsFromDB();
    }//GEN-LAST:event_bDeleteQuestionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bCreateQuestion;
    private javax.swing.JButton bDeleteQuestion;
    private javax.swing.JButton bEditQuestion;
    private javax.swing.JButton bSearchQuestion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lQuestionsList;
    private javax.swing.JTable tableQuestions;
    private javax.swing.JTextField textSearchQuestion;
    // End of variables declaration//GEN-END:variables
}
