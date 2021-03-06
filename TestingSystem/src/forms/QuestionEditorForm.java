package forms;

import entities.Disciplina;
import entities.KategoriyaSlozhnosti;
import entities.KolvoOblastey;
import entities.PolozhenieKartinki;
import entities.PoryadkoviyNomer;
import entities.TipVoprosa;
import entities.Vopros;
import entities.VoprosLatex;
import entities.VoprosPeretaskivanieKartinok;
import java.awt.Graphics;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import main.Area;
import main.DialogManager;
import main.Formula;
import main.FormulaParser;
import static resources.Parameters.SCREEN_SIZE;
import sql.DBManager;
import static sql.DBManager.entityManager;

/**
 *
 * @author Alexey Ovcharov & Artem Solovenko
 */
public class QuestionEditorForm extends javax.swing.JDialog {

    public enum TypeOfQuestion {

        LATEX,
        PUZZLE,
    }
    private final Graphics GRAPHICS;
    private Formula formula;
    private Vopros question;
    private VoprosLatex latexQuestion;
    private VoprosPeretaskivanieKartinok peretaskivanieQuestion;
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
    private TypeOfQuestion currentTypeOfQuestion;
    private List<List<Area>> placingPicturesInAreas;
    private boolean editMode;

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
            DialogManager.errorMessage(ex);
        }
        if (subjects != null) {
            listSubjects.updateUI();
        }
        if (difficulty != null) {
            listDifficulty.updateUI();
        }
        currentTypeOfQuestion = TypeOfQuestion.LATEX;
        lInfo1.setVisible(false);
        listSubjects.setSelectedIndex(0);
        listDifficulty.setSelectedIndex(0);
    }

    public void draw() {
        GRAPHICS.setColor(panePreview.getBackground());
        GRAPHICS.clearRect(0, 0, panePreview.getWidth(),
                panePreview.getHeight());
        if (formula != null) {
            formula.draw(GRAPHICS);
        }
    }

    private void exitConfirm() {
        if (DialogManager.confirmClosingForm("вопроса")) {
            dispose();
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
        lDescription = new javax.swing.JLabel();
        bCreateFormulaOrAddAreas = new javax.swing.JButton();
        bDeleteFormula = new javax.swing.JButton();
        bCloseForm = new javax.swing.JButton();
        bSaveQuestion = new javax.swing.JButton();
        paneBorder = new javax.swing.JPanel();
        panePreview = new javax.swing.JPanel();
        sPaneForListDifficulty = new javax.swing.JScrollPane();
        listDifficulty = new javax.swing.JList();
        lSubject = new javax.swing.JLabel();
        textSearch = new javax.swing.JTextField();
        bSearch = new javax.swing.JButton();
        lDifficulty = new javax.swing.JLabel();
        sPaneForListSubjects = new javax.swing.JScrollPane();
        listSubjects = new javax.swing.JList();
        lPoints = new javax.swing.JLabel();
        spinnerPoints = new javax.swing.JSpinner();
        lInfo1 = new javax.swing.JLabel();
        bCancelSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Новый вопрос");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
        rbConstructFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbConstructFormulaActionPerformed(evt);
            }
        });

        buttonGroupQuestionType.add(rbAssembledFromulaFromPieces);
        rbAssembledFromulaFromPieces.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbAssembledFromulaFromPieces.setText("Сборка из фрагментов");
        rbAssembledFromulaFromPieces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAssembledFromulaFromPiecesActionPerformed(evt);
            }
        });

        lDescription.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lDescription.setText("Формула:");

        bCreateFormulaOrAddAreas.setText("Создать");
        bCreateFormulaOrAddAreas.setPreferredSize(new java.awt.Dimension(100, 30));
        bCreateFormulaOrAddAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateFormulaOrAddAreasActionPerformed(evt);
            }
        });

        bDeleteFormula.setText("Удалить");
        bDeleteFormula.setPreferredSize(new java.awt.Dimension(100, 30));
        bDeleteFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteFormulaActionPerformed(evt);
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
        listDifficulty.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listDifficultyValueChanged(evt);
            }
        });
        sPaneForListDifficulty.setViewportView(listDifficulty);

        lSubject.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lSubject.setText("Дисциплина:");

        bSearch.setText("Найти");
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });

        lDifficulty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lDifficulty.setText("Сложность:");

        listSubjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sPaneForListSubjects.setViewportView(listSubjects);

        lPoints.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lPoints.setText("Балл:");

        spinnerPoints.setValue(1);
        spinnerPoints.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerPointsStateChanged(evt);
            }
        });

        bCancelSearch.setText("Сбросить");
        bCancelSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelSearchActionPerformed(evt);
            }
        });

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
                            .addComponent(lDescription)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(bDeleteFormula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(bCreateFormulaOrAddAreas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                        .addGap(32, 32, 32)
                        .addComponent(paneBorder, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lQuestionTitle)
                        .addGap(18, 18, 18)
                        .addComponent(tQuestionTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbAssembledFromulaFromPieces)
                            .addComponent(lQuestionType)
                            .addComponent(rbConstructFormula))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lSubject)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bCancelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sPaneForListDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lDifficulty))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPoints)
                            .addComponent(spinnerPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lQuestionFormulation)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bSaveQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                                .addComponent(rbAssembledFromulaFromPieces))
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
                                    .addComponent(bSearch)
                                    .addComponent(bCancelSearch))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lDescription)
                                .addGap(18, 18, 18)
                                .addComponent(bCreateFormulaOrAddAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bDeleteFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(paneBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(lInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSaveQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCloseForm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setQuestion(Vopros question) {
        try {
            TypedQuery<Vopros> queryForVopros = entityManager.createNamedQuery(
                    "Vopros.findByIdVopros", Vopros.class);
            queryForVopros.setParameter("idVopros", question.getIdVopros());
            this.question = queryForVopros.getSingleResult();
            spinnerPoints.setValue(question.getBall());
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
                    rbConstructFormula.setSelected(true);
                    TypedQuery<VoprosLatex> queryForVoprosLatex
                            = entityManager.createQuery("select vl from "
                                    + "VoprosLatex vl WHERE "
                                    + "vl.voprosIdVopros.idVopros=:id",
                                    VoprosLatex.class);
                    queryForVoprosLatex.setParameter("id",
                            this.question.getIdVopros());
                    latexQuestion = queryForVoprosLatex.getSingleResult();
                    formula = new FormulaParser().
                            parseFormula(latexQuestion.getLatexZapis());
                    formula.update();
                    formula.moveOnVertical(30);
                    rbAssembledFromulaFromPieces.setEnabled(false);
                    currentTypeOfQuestion = TypeOfQuestion.LATEX;
                    break;
                case 2:
                    rbAssembledFromulaFromPieces.setSelected(true);
                    TypedQuery<VoprosPeretaskivanieKartinok> queryForVoprosP
                            = entityManager.createQuery("select vl from "
                                    + "VoprosPeretaskivanieKartinok vl WHERE "
                                    + "vl.voprosIdVopros.idVopros=:id",
                                    VoprosPeretaskivanieKartinok.class);
                    queryForVoprosP.setParameter("id",
                            this.question.getIdVopros());
                    peretaskivanieQuestion = queryForVoprosP.getSingleResult();
                    lDescription.setText("Размещение изображений в областях");
                    bCreateFormulaOrAddAreas.setText("<html><center>Разместить изображения");
                    bDeleteFormula.setVisible(false);
                    paneBorder.setVisible(false);
                    panePreview.setVisible(false);
                    bCreateFormulaOrAddAreas.setEnabled(false);
                    lInfo1.setVisible(true);
                    rbAssembledFromulaFromPieces.setSelected(true);
                    rbConstructFormula.setEnabled(false);
                    currentTypeOfQuestion = TypeOfQuestion.PUZZLE;
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
            editMode = true;
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
            dispose();
        }
    }

    private void bCloseFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseFormActionPerformed
        exitConfirm();
    }//GEN-LAST:event_bCloseFormActionPerformed

    private void bSaveQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveQuestionActionPerformed
        boolean correct = true;
        if (tQuestionTitle.getText().isEmpty()) {
            DialogManager.notify("Предупреждение",
                    "Название вопроса не может быть пустым",
                    DialogManager.TypeOfMessage.WARNING);
            correct = false;
        } else if (textAreaForQuestionFormulation.getText().isEmpty()) {
            DialogManager.notify("Предупреждение",
                    "Формулировка вопроса не может быть пустой",
                    DialogManager.TypeOfMessage.WARNING);
            correct = false;
        } else if (subjects.isEmpty() || listSubjects.getSelectedIndex() < 0) {
            //Проверка на корректность дисциплины
            DialogManager.notify("Предупреждение",
                    "Дисциплина не выбрана", DialogManager.TypeOfMessage.WARNING);
            correct = false;
        } else if (listDifficulty.getSelectedIndex() == -1) {
            //Проверка на корректность выбора сложности
            DialogManager.notify("Предупреждение",
                    "Сложность не выбрана", DialogManager.TypeOfMessage.WARNING);
            correct = false;
        }

        if (correct) {
            switch (currentTypeOfQuestion) {
                case LATEX:
                    if (formula == null) {
                        DialogManager.notify("Предупреждение",
                                "Формула отсутствует", DialogManager.TypeOfMessage.WARNING);
                        correct = false;
                    }
                    break;
                case PUZZLE:
                    if (placingPicturesInAreas == null && !editMode) {
                        DialogManager.notify("Предупреждение",
                                "Картинки не размещены", DialogManager.TypeOfMessage.WARNING);
                        correct = false;
                    }
                    break;
            }
        }
        if (correct) {
            if (question == null) {
                question = new Vopros();
                switch (currentTypeOfQuestion) {
                    case LATEX:
                        latexQuestion = new VoprosLatex();
                        break;
                    case PUZZLE:
                        peretaskivanieQuestion
                                = new VoprosPeretaskivanieKartinok();
                        break;
                }
            }
            question.setBall((Integer) spinnerPoints.getValue());
            //старое
//            question.setNazvanie(new String(
//                    tQuestionTitle.getText().getBytes(), UTF_8));
//            question.setFormulirovka(new String(
//                    textAreaForQuestionFormulation.getText().getBytes(),
//                    UTF_8));
            //-------------

            //*****новое*******************************************************
            question.setNazvanie(tQuestionTitle.getText());
            question.setFormulirovka(textAreaForQuestionFormulation.getText());
            //*****************************************************************

            question.setDisciplinaIdDisciplina(subjects.get(
                    listSubjects.getSelectedIndex()));
            question.setKategoriyaSlozhnostiIdKategoriyaSlozhnosti(
                    difficulty.get(listDifficulty.getSelectedIndex()));
            if (typesOfQuestion.size() == 3) {
                question.setTipVoprosaIdTipVoprosa(
                        typesOfQuestion.get(currentTypeOfQuestion.ordinal()));
            } else {
                DialogManager.notify("Ошибка",
                        "База данных содержит некорректную информацию о типах вопросов. "
                        + "\nОбратитесь к администратору",
                        DialogManager.TypeOfMessage.ERROR);
                return;
            }
            switch (currentTypeOfQuestion) {
                //записать вопрос Latex
                case LATEX:
                    latexQuestion.setLatexZapis(
                            new String(formula.toString().getBytes(), UTF_8));
                    latexQuestion.setVoprosIdVopros(question);
                    try {
                        DBManager.writeObjectMerge(latexQuestion);
                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }
                    dispose();
                    break;
                //записать вопрос на перетаскивание картинок
                case PUZZLE:
                    if (editMode) {
                        try {
                            DBManager.writeObjectMerge(question);
                        } catch (Exception ex) {
                            DialogManager.errorMessage(ex);
                        }
                    } else {
                        try {
                            TypedQuery<KolvoOblastey> typedQuery
                                    = entityManager.createQuery("SELECT k FROM "
                                            + "KolvoOblastey k WHERE k.kolvo=:id",
                                            KolvoOblastey.class);
                            int bound = placingPicturesInAreas.get(0).size();
                            typedQuery.setParameter("id", bound);
                            peretaskivanieQuestion.setKolvoOblasteyIdKolvoOblastey(
                                    typedQuery.getSingleResult());
                            peretaskivanieQuestion.setVoprosIdVopros(question);
                            DBManager.writeObjectPersist(question);
                            DBManager.writeObjectPersist(peretaskivanieQuestion);
                            TypedQuery<PoryadkoviyNomer> typedQuery1
                                    = entityManager.createNamedQuery("PoryadkoviyNomer.findAll",
                                            PoryadkoviyNomer.class);
                            List<PoryadkoviyNomer> numbers = typedQuery1.getResultList();
                            PoryadkoviyNomer illegalNumber = null;
                            for (int j = 0; j < bound; j++) {
                                for (PoryadkoviyNomer nomer : numbers) {
                                    Area area = placingPicturesInAreas.get(0).get(j);
                                    if (area.getNumber() == nomer.getNomer()) {
                                        PolozhenieKartinki polKart
                                                = new PolozhenieKartinki();
                                        polKart.setKartinkaIdKartinka(
                                                area.getKartinka());
                                        polKart.setPoryadkoviyNomerIdPoryadkoviyNomer(nomer);
                                        polKart.
                                                setVoprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok(
                                                        peretaskivanieQuestion);
                                        DBManager.writeObjectPersist(polKart);
                                    }
                                    if (nomer.getNomer() == -1) {
                                        illegalNumber = nomer;
                                    }
                                }
                            }
                            for (int j = 0; j < placingPicturesInAreas.get(1).size(); j++) {
                                Area area = placingPicturesInAreas.get(1).get(j);
                                PolozhenieKartinki polKart = new PolozhenieKartinki();
                                polKart.setKartinkaIdKartinka(area.getKartinka());
                                polKart.setPoryadkoviyNomerIdPoryadkoviyNomer(illegalNumber);
                                polKart.setVoprosPeretaskivanieKartinokIdVoprosPeretaskivanieKartinok(
                                        peretaskivanieQuestion);
                                DBManager.writeObjectPersist(polKart);
                            }
                        } catch (Exception ex) {
                            DialogManager.errorMessage(ex);
                        }
                    }
                    dispose();
                    break;

            }

        }
    }//GEN-LAST:event_bSaveQuestionActionPerformed

    private void bCreateFormulaOrAddAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateFormulaOrAddAreasActionPerformed
        switch (currentTypeOfQuestion) {
            case LATEX:
                FormulaEditorForm formulaEditor = new FormulaEditorForm(null, true);
                formulaEditor.setVisible(true);
                formula = formulaEditor.getFormula();
                if (formula != null) {
                    formula.moveOnVertical(-70);
                    draw();
                }
                break;
            case PUZZLE:
                PlacingPicturesForm placingPicturesForm
                        = new PlacingPicturesForm(null, true);
//                placingPicturesForm.setAreas(areas);
                placingPicturesForm.setSubject(
                        subjects.get(listSubjects.getSelectedIndex()));
                placingPicturesForm.setVisible(true);
                placingPicturesInAreas = placingPicturesForm.getAreas();
                if (placingPicturesInAreas != null) {
                    lInfo1.setText("Картинки размещены");
                }
        }

    }//GEN-LAST:event_bCreateFormulaOrAddAreasActionPerformed

    private void bDeleteFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteFormulaActionPerformed
        switch (currentTypeOfQuestion) {
            case LATEX:
                formula = null;
                draw();
                break;
        }

    }//GEN-LAST:event_bDeleteFormulaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exitConfirm();
    }//GEN-LAST:event_formWindowClosing


    private void spinnerPointsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerPointsStateChanged
        int spinnerValue = (int) spinnerPoints.getValue();
        spinnerValue = spinnerValue < 1 ? 1 : spinnerValue;
        spinnerValue = spinnerValue > 100 ? 100 : spinnerValue;
        spinnerPoints.setValue(spinnerValue);
    }//GEN-LAST:event_spinnerPointsStateChanged

    private void rbConstructFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbConstructFormulaActionPerformed
        lDescription.setText("Формула");
        bCreateFormulaOrAddAreas.setText("Создать");
        bDeleteFormula.setVisible(true);
        paneBorder.setVisible(true);
        panePreview.setVisible(true);
        lInfo1.setVisible(false);
        currentTypeOfQuestion = TypeOfQuestion.LATEX;
    }//GEN-LAST:event_rbConstructFormulaActionPerformed

    private void rbAssembledFromulaFromPiecesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAssembledFromulaFromPiecesActionPerformed
        lDescription.setText("Размещение изображений в областях");
        bCreateFormulaOrAddAreas.setText("<html><center>Разместить изображения");
        bDeleteFormula.setVisible(false);
        paneBorder.setVisible(false);
        panePreview.setVisible(false);
        if (!lInfo1.isVisible()) {
            lInfo1.setText("");
            lInfo1.setVisible(true);
        }
        currentTypeOfQuestion = TypeOfQuestion.PUZZLE;
    }//GEN-LAST:event_rbAssembledFromulaFromPiecesActionPerformed

    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchActionPerformed

        if (!textSearch.getText().isEmpty()) {
            String typedDisciplinaName = textSearch.getText();
            List<Disciplina> tempResults = entityManager.createNamedQuery(
                    "Disciplina.findAll", Disciplina.class).getResultList();

            subjects.clear();
            for (Disciplina singleResult : tempResults) {
                if (typedDisciplinaName.length() <= singleResult.
                        getNazvanie().length()) {
                    String iteratedDisciplinaName = singleResult.
                            getNazvanie().substring(0, typedDisciplinaName.length());
                    if (iteratedDisciplinaName.equalsIgnoreCase(typedDisciplinaName)) {
                        subjects.add(singleResult);
                    }
                }
            }
            if (!subjects.isEmpty()) {
                listSubjects.setSelectedIndex(0);
            } else {
                listSubjects.setSelectedIndex(-1);
            }
            listSubjects.updateUI();
        } else {
            subjects = entityManager.createNamedQuery(
                    "Disciplina.findAll", Disciplina.class).getResultList();
            listSubjects.updateUI();
        }
    }//GEN-LAST:event_bSearchActionPerformed

    private void bCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelSearchActionPerformed
        textSearch.setText(null);
        subjects = entityManager.createNamedQuery(
                "Disciplina.findAll", Disciplina.class).getResultList();
        listSubjects.updateUI();
    }//GEN-LAST:event_bCancelSearchActionPerformed

    private void listDifficultyValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listDifficultyValueChanged
        switch (listDifficulty.getSelectedIndex()) {
            case 0:
                spinnerPoints.setValue(1);
                break;
            case 1:
                spinnerPoints.setValue(10);
                break;
            case 2:
                spinnerPoints.setValue(20);
        }
    }//GEN-LAST:event_listDifficultyValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelSearch;
    private javax.swing.JButton bCloseForm;
    private javax.swing.JButton bCreateFormulaOrAddAreas;
    private javax.swing.JButton bDeleteFormula;
    private javax.swing.JButton bSaveQuestion;
    private javax.swing.JButton bSearch;
    private javax.swing.ButtonGroup buttonGroupQuestionType;
    private javax.swing.JLabel lDescription;
    private javax.swing.JLabel lDifficulty;
    private javax.swing.JLabel lInfo1;
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
    private javax.swing.JScrollPane sPaneForListDifficulty;
    private javax.swing.JScrollPane sPaneForListSubjects;
    private javax.swing.JScrollPane sPaneForQuestionFormulation;
    private javax.swing.JSpinner spinnerPoints;
    private javax.swing.JTextField tQuestionTitle;
    private javax.swing.JTextArea textAreaForQuestionFormulation;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables

}
