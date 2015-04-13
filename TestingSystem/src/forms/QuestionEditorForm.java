package forms;

import entities.Disciplina;
import entities.KategoriyaSlozhnosti;
import entities.TipVoprosa;
import entities.Vopros;
import entities.VoprosLatex;
import java.awt.Graphics;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import main.Formula;
import static resources.Parameters.SCREEN_SIZE;
import sql.DBManager;
import static sql.DBManager.entityManager;

/**
 *
 * @author Алексей
 */
public class QuestionEditorForm extends javax.swing.JDialog {

    private final Graphics GRAPHICS;
    private Formula formula;
    private Vopros question;
    private VoprosLatex latexQuestion;
    private List<Disciplina> subjects;
    private List<KategoriyaSlozhnosti> difficulty;
    private List<TipVoprosa> typesOfQuestion;
    private final ListModel SUBJECTS_LIST_MODEL = new AbstractListModel() {

        @Override
        public int getSize() {
            return subjects.size();
        }

        @Override
        public Object getElementAt(int index) {
            return subjects.get(index).getNazvanie();
        }
    };
    private final ListModel DIFFICULTY_LIST_MODEL = new AbstractListModel() {

        @Override
        public int getSize() {
            return difficulty.size();
        }

        @Override
        public Object getElementAt(int index) {
            return difficulty.get(index).getNazvanie();
        }
    };

    public QuestionEditorForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2, 
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        GRAPHICS = panePreview.getGraphics();
        listSubjects.setModel(SUBJECTS_LIST_MODEL);
        listDifficulty.setModel(DIFFICULTY_LIST_MODEL);
        try {
            subjects = entityManager.createNamedQuery(
                    "Disciplina.findAll", Disciplina.class).getResultList();
            difficulty = entityManager.createNamedQuery(
                    "KategoriyaSlozhnosti.findAll",
                    KategoriyaSlozhnosti.class).getResultList();
            typesOfQuestion = entityManager.createNamedQuery(
                    "TipVoprosa.findAll",
                    TipVoprosa.class).getResultList();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        if (subjects != null) {
            listSubjects.updateUI();
        }
        if (difficulty != null) {
            listDifficulty.updateUI();
        }
    }

    public void draw() {
        GRAPHICS.setColor(panePreview.getBackground());
        GRAPHICS.clearRect(0, 0, panePreview.getWidth(),
                panePreview.getHeight());
        if (formula != null) {
            formula.preview(GRAPHICS);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupQuestionType = new javax.swing.ButtonGroup();
        lQuestionTitle = new javax.swing.JLabel();
        tQuestionTitle = new javax.swing.JTextField();
        lQuestionFormulation = new javax.swing.JLabel();
        sPaneForQuestionFormulation = new javax.swing.JScrollPane();
        textAreaForQuestionFormulation = new javax.swing.JTextArea();
        lQuestionType = new javax.swing.JLabel();
        rbConstructFormula = new javax.swing.JRadioButton();
        rbAssembledFromulaFromPieces = new javax.swing.JRadioButton();
        lFormula = new javax.swing.JLabel();
        bCreateFormula = new javax.swing.JButton();
        bDeleteFormula = new javax.swing.JButton();
        bEditFormula = new javax.swing.JButton();
        bCloseForm = new javax.swing.JButton();
        bSaveQuestion = new javax.swing.JButton();
        paneBorder = new javax.swing.JPanel();
        panePreview = new javax.swing.JPanel();
        sPaneForListDifficulty = new javax.swing.JScrollPane();
        listDifficulty = new javax.swing.JList();
        lSubject = new javax.swing.JLabel();
        rbLinkingPictures = new javax.swing.JRadioButton();
        textSearch = new javax.swing.JTextField();
        bSearch = new javax.swing.JButton();
        lDifficulty = new javax.swing.JLabel();
        sPaneForListSubjects = new javax.swing.JScrollPane();
        listSubjects = new javax.swing.JList();
        lPoints = new javax.swing.JLabel();
        spinnerPoints = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Новый вопрос");
        setResizable(false);

        lQuestionTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestionTitle.setText("Название вопроса:");

        lQuestionFormulation.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestionFormulation.setText("Формулировка вопроса:");

        textAreaForQuestionFormulation.setColumns(20);
        textAreaForQuestionFormulation.setRows(5);
        sPaneForQuestionFormulation.setViewportView(textAreaForQuestionFormulation);

        lQuestionType.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lQuestionType.setText("Тип вопроса:");

        buttonGroupQuestionType.add(rbConstructFormula);
        rbConstructFormula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbConstructFormula.setSelected(true);
        rbConstructFormula.setText("Конструирование формулы");

        buttonGroupQuestionType.add(rbAssembledFromulaFromPieces);
        rbAssembledFromulaFromPieces.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbAssembledFromulaFromPieces.setText("Сборка формулы из кусочков");
        rbAssembledFromulaFromPieces.setEnabled(false);

        lFormula.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lFormula.setText("Формула:");

        bCreateFormula.setText("Создать");
        bCreateFormula.setPreferredSize(new java.awt.Dimension(100, 30));
        bCreateFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateFormulaActionPerformed(evt);
            }
        });

        bDeleteFormula.setText("Удалить");
        bDeleteFormula.setPreferredSize(new java.awt.Dimension(100, 30));
        bDeleteFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteFormulaActionPerformed(evt);
            }
        });

        bEditFormula.setText("Редактировать");
        bEditFormula.setPreferredSize(new java.awt.Dimension(100, 30));
        bEditFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditFormulaActionPerformed(evt);
            }
        });

        bCloseForm.setText("Закрыть");
        bCloseForm.setPreferredSize(new java.awt.Dimension(100, 30));
        bCloseForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseFormActionPerformed(evt);
            }
        });

        bSaveQuestion.setText("Сохранить");
        bSaveQuestion.setPreferredSize(new java.awt.Dimension(100, 30));
        bSaveQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveQuestionActionPerformed(evt);
            }
        });

        paneBorder.setBackground(new java.awt.Color(204, 204, 204));
        paneBorder.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Предварительный просмотр"));
        paneBorder.setPreferredSize(new java.awt.Dimension(650, 150));

        javax.swing.GroupLayout panePreviewLayout = new javax.swing.GroupLayout(panePreview);
        panePreview.setLayout(panePreviewLayout);
        panePreviewLayout.setHorizontalGroup(
            panePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panePreviewLayout.setVerticalGroup(
            panePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout paneBorderLayout = new javax.swing.GroupLayout(paneBorder);
        paneBorder.setLayout(paneBorderLayout);
        paneBorderLayout.setHorizontalGroup(
            paneBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panePreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        paneBorderLayout.setVerticalGroup(
            paneBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBorderLayout.createSequentialGroup()
                .addComponent(panePreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        listDifficulty.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sPaneForListDifficulty.setViewportView(listDifficulty);

        lSubject.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lSubject.setText("Дисциплина:");

        buttonGroupQuestionType.add(rbLinkingPictures);
        rbLinkingPictures.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbLinkingPictures.setText("Соединение картинок линиями");
        rbLinkingPictures.setEnabled(false);

        textSearch.setEnabled(false);

        bSearch.setText("Поиск");
        bSearch.setEnabled(false);

        lDifficulty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lDifficulty.setText("Сложность:");

        listSubjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sPaneForListSubjects.setViewportView(listSubjects);

        lPoints.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lPoints.setText("Балл:");

        spinnerPoints.setEnabled(false);
        spinnerPoints.setValue(1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sPaneForQuestionFormulation)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lFormula)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(bDeleteFormula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(bCreateFormula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                            .addComponent(bEditFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(paneBorder, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bSaveQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lQuestionTitle)
                        .addGap(18, 18, 18)
                        .addComponent(tQuestionTitle))
                    .addComponent(lQuestionFormulation)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbAssembledFromulaFromPieces)
                            .addComponent(lQuestionType)
                            .addComponent(rbConstructFormula)
                            .addComponent(rbLinkingPictures))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textSearch)
                                .addGap(18, 18, 18)
                                .addComponent(bSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lSubject)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sPaneForListDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lDifficulty))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPoints)
                            .addComponent(spinnerPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQuestionTitle)
                    .addComponent(tQuestionTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lQuestionFormulation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sPaneForQuestionFormulation, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lQuestionType)
                            .addComponent(lSubject))
                        .addGap(18, 18, 18)
                        .addComponent(rbConstructFormula)
                        .addGap(18, 18, 18)
                        .addComponent(rbAssembledFromulaFromPieces)
                        .addGap(18, 18, 18)
                        .addComponent(rbLinkingPictures))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lDifficulty)
                                    .addComponent(lPoints))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinnerPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sPaneForListDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bSearch))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lFormula)
                        .addGap(18, 18, 18)
                        .addComponent(bCreateFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDeleteFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bEditFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paneBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSaveQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setQuestion(Vopros question) {
        try {
            TypedQuery<Vopros> queryForVopros = entityManager.createNamedQuery(
                    "Vopros.findByIdVopros", Vopros.class);
            queryForVopros.setParameter("idVopros", question.getIdVopros());
            this.question = queryForVopros.getSingleResult();
            tQuestionTitle.setText(this.question.getNazvanie());
            textAreaForQuestionFormulation.setText(
                    this.question.getFormulirovka());
            for (int i = 0; i < subjects.size(); i++) {
                if (subjects.get(i).getIdDisciplina().intValue()
                        == this.question.getDisciplinaIdDisciplina().
                        getIdDisciplina()) {
                    listSubjects.setSelectedIndex(i);
                    break;
                }
            }
            switch (this.question.getTipVoprosaIdTipVoprosa().getIdTipVoprosa()) {
                case 1:
                    this.rbConstructFormula.setSelected(true);
                    //исправить если не работает
                    TypedQuery<VoprosLatex> queryForVoprosLatex
                            = entityManager.createQuery("select vl from "
                                    + "VoprosLatex vl WHERE "
                                    + "vl.voprosIdVopros.idVopros=:id",
                                    VoprosLatex.class);

                    queryForVoprosLatex.setParameter("id",
                            this.question.getIdVopros());
                    latexQuestion = queryForVoprosLatex.getSingleResult();
                    formula = new Formula(latexQuestion.getLatexZapis());
                    break;
                //
                case 2:
                    this.rbAssembledFromulaFromPieces.setSelected(true);
                    break;
                case 3:
                    this.rbLinkingPictures.setSelected(true);
            }
            for (int i = 0; i < difficulty.size(); i++) {
                if (difficulty.get(i).getIdKategoriyaSlozhnosti().intValue()
                        == this.question.
                        getKategoriyaSlozhnostiIdKategoriyaSlozhnosti().
                        getIdKategoriyaSlozhnosti()) {
                    listDifficulty.setSelectedIndex(i);
                    break;
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private void bCloseFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseFormActionPerformed
        dispose();
    }//GEN-LAST:event_bCloseFormActionPerformed

    private void bSaveQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveQuestionActionPerformed
        boolean correct = true;
        if (tQuestionTitle.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Название вопроса не может быть "
                    + "пустым", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            correct = false;
        } else if (textAreaForQuestionFormulation.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Формулировка вопроса не может "
                    + "быть пустой", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            correct = false;
        } else if (formula == null) {
            JOptionPane.showMessageDialog(this, "Формула отсутствует",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
            correct = false;
        } else if (listSubjects.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Дисциплина не выбрана",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
            correct = false;
        } else if (listDifficulty.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Сложность не выбрана",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
            correct = false;
        }
        int type;
        if (rbConstructFormula.isSelected()) {
            type = 0;
        } else if (rbAssembledFromulaFromPieces.isSelected()) {
            type = 1;
        } else if (rbLinkingPictures.isSelected()) {
            type = 2;
        } else {
            type = 3;
        }
        if (type == 3) {
            JOptionPane.showMessageDialog(this, "Тип вопроса не выбран",
                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
            correct = false;
        }
        if (correct) {
            if (question == null) {
                question = new Vopros();
                latexQuestion = new VoprosLatex();
            }
            question.setBall(1);
            question.setNazvanie(new String(
                    tQuestionTitle.getText().getBytes(), UTF_8));
            question.setFormulirovka(new String(
                    textAreaForQuestionFormulation.getText().getBytes(),
                    UTF_8));
            question.setDisciplinaIdDisciplina(subjects.get(
                    listSubjects.getSelectedIndex()));
            question.setKategoriyaSlozhnostiIdKategoriyaSlozhnosti(
                    difficulty.get(listDifficulty.getSelectedIndex()));
            question.setTipVoprosaIdTipVoprosa(typesOfQuestion.get(type));
            latexQuestion.setLatexZapis(new String(
                    formula.getTranscription().getBytes(), UTF_8));
            latexQuestion.setVoprosIdVopros(question);
            try {
                DBManager.writeObject(latexQuestion);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.toString(),
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        }
    }//GEN-LAST:event_bSaveQuestionActionPerformed

    private void bCreateFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateFormulaActionPerformed
        FormulaEditorForm formulaEditor = new FormulaEditorForm(null, true);
        formulaEditor.setVisible(true);
        String formulaTranscription = formulaEditor.getFormulaTranscription();
        if (formulaTranscription != null) {
            formula = new Formula(formulaTranscription);
        }
        draw();
    }//GEN-LAST:event_bCreateFormulaActionPerformed

    private void bDeleteFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteFormulaActionPerformed
        formula = null;
        draw();
    }//GEN-LAST:event_bDeleteFormulaActionPerformed

    private void bEditFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditFormulaActionPerformed
        if (formula != null) {
            FormulaEditorForm formulaEditor = new FormulaEditorForm(null, true);
            formulaEditor.setFormula(formula.getTranscription());
            formulaEditor.setVisible(true);
            String formulaTranscription = formulaEditor.getFormulaTranscription();
            if (formulaTranscription != null) {
                formula = new Formula(formulaTranscription);
            }
            draw();
        }
    }//GEN-LAST:event_bEditFormulaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCloseForm;
    private javax.swing.JButton bCreateFormula;
    private javax.swing.JButton bDeleteFormula;
    private javax.swing.JButton bEditFormula;
    private javax.swing.JButton bSaveQuestion;
    private javax.swing.JButton bSearch;
    private javax.swing.ButtonGroup buttonGroupQuestionType;
    private javax.swing.JLabel lDifficulty;
    private javax.swing.JLabel lFormula;
    private javax.swing.JLabel lPoints;
    private javax.swing.JLabel lQuestionFormulation;
    private javax.swing.JLabel lQuestionTitle;
    private javax.swing.JLabel lQuestionType;
    private javax.swing.JLabel lSubject;
    private javax.swing.JList listDifficulty;
    private javax.swing.JList listSubjects;
    private javax.swing.JPanel paneBorder;
    private javax.swing.JPanel panePreview;
    private javax.swing.JRadioButton rbAssembledFromulaFromPieces;
    private javax.swing.JRadioButton rbConstructFormula;
    private javax.swing.JRadioButton rbLinkingPictures;
    private javax.swing.JScrollPane sPaneForListDifficulty;
    private javax.swing.JScrollPane sPaneForListSubjects;
    private javax.swing.JScrollPane sPaneForQuestionFormulation;
    private javax.swing.JSpinner spinnerPoints;
    private javax.swing.JTextField tQuestionTitle;
    private javax.swing.JTextArea textAreaForQuestionFormulation;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables

}
