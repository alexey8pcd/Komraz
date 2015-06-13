package forms;

import entities.Kartinka;
import entities.PolozhenieKartinki;
import entities.Student;
import entities.StudentTest;
import entities.Test;
import entities.TestVopros;
import entities.VoprosPeretaskivanieKartinok;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.TypedQuery;
import main.Area;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author Алексей
 */
public class PassageTestAssemblyPictureForm extends javax.swing.JDialog {

    private Student student;
    private Test testForPassage;
    private VoprosPeretaskivanieKartinok currentQuestion;
    private List<VoprosPeretaskivanieKartinok> questions;
    private int currentQuestionIndex;
    private int questionsAmount;
    private final Graphics GRAPHICS;
    private Area[] areasForPlacingPictures;
    private List<Area> areasContainingPictures;
    private final int SPAN = 10;
    private final int LEFT_X = 40;
    private final int TOP_AREA_Y = 40;
    private final int TOP_PICTURE_Y = 200;
    private int[][] results;
    private int amountOfAreasInCurrentQuestion;
    private Area placing;

    public PassageTestAssemblyPictureForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        GRAPHICS = paneWork.getGraphics();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw();
    }

    public void draw() {
        GRAPHICS.clearRect(0, 0, paneWork.getWidth(), paneWork.getHeight());
        if (areasForPlacingPictures != null) {
            for (Area area : areasForPlacingPictures) {
                area.draw(GRAPHICS, Color.GRAY, false, null);
            }
        }
        if (areasContainingPictures != null) {
            for (Area area : areasContainingPictures) {
                area.draw(GRAPHICS, Color.WHITE, false, null);
            }
        }
    }

    private void updateLabel() {
        labelQuestionNumber.setText("Вопрос "
                + (currentQuestionIndex + 1) + " из " + questionsAmount);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelQuestionNumber = new javax.swing.JLabel();
        lQuestionFormulation = new javax.swing.JLabel();
        bNextQuestion = new javax.swing.JButton();
        bCompleteTest = new javax.swing.JButton();
        paneWork = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Прохождение теста");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelQuestionNumber.setText("Вопрос N/M");

        lQuestionFormulation.setText("Формулировка вопроса");

        bNextQuestion.setText("Следующий вопрос");
        bNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextQuestionActionPerformed(evt);
            }
        });

        bCompleteTest.setText("Завершить тест");
        bCompleteTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompleteTestActionPerformed(evt);
            }
        });

        paneWork.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneWorkMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                paneWorkMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout paneWorkLayout = new javax.swing.GroupLayout(paneWork);
        paneWork.setLayout(paneWorkLayout);
        paneWorkLayout.setHorizontalGroup(
            paneWorkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        paneWorkLayout.setVerticalGroup(
            paneWorkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneWork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bCompleteTest)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(187, 187, 187)
                                .addComponent(bNextQuestion))
                            .addComponent(lQuestionFormulation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(bNextQuestion))
                    .addComponent(labelQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lQuestionFormulation, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneWork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 445, Short.MAX_VALUE)
                        .addComponent(bCompleteTest)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setTestForPassage(Test testForPassage) {
        List<TestVopros> testVoproses = null;
        try {
            this.testForPassage = entityManager.find(Test.class,
                    testForPassage.getIdTest());
            TypedQuery<TestVopros> queryForTestVopros = entityManager.createQuery(
                    "SELECT tv FROM TestVopros tv WHERE tv.testIdTest.idTest=:id",
                    TestVopros.class);
            queryForTestVopros.setParameter("id", this.testForPassage.getIdTest());
            testVoproses = queryForTestVopros.getResultList();
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
            dispose();
        }
        questions = new ArrayList<>();
        for (TestVopros testVopros : testVoproses) {
            TypedQuery<VoprosPeretaskivanieKartinok> typedQuery
                    = entityManager.createQuery("select v FROM "
                            + "VoprosPeretaskivanieKartinok v WHERE "
                            + "v.voprosIdVopros.idVopros=:id",
                            VoprosPeretaskivanieKartinok.class);
            typedQuery.setParameter("id", testVopros.getVoprosIdVopros().
                    getIdVopros());
            VoprosPeretaskivanieKartinok voprosPeretaskivanieKartinok
                    = typedQuery.getSingleResult();
            questions.add(voprosPeretaskivanieKartinok);
        }
        Collections.shuffle(questions);
        currentQuestionIndex = 0;
        currentQuestion = questions.get(currentQuestionIndex);
        questionsAmount = questions.size();
        results = new int[questionsAmount][6];
        makeNextQuestion();
        if (questionsAmount == 1) {
            bNextQuestion.setEnabled(false);
        }
        draw();
    }

    private void makeNextQuestion() {
        amountOfAreasInCurrentQuestion = currentQuestion.getKolvoOblasteyIdKolvoOblastey().
                getKolvo();
        areasForPlacingPictures = new Area[amountOfAreasInCurrentQuestion];
        for (int i = 0; i < areasForPlacingPictures.length; ++i) {
            Area area = new Area(LEFT_X + i * (Area.DEFAULT_SIZE + SPAN),
                    TOP_AREA_Y, Area.DEFAULT_SIZE);
            area.setNumber(i + 1);
            areasForPlacingPictures[i] = area;
        }
        areasContainingPictures = new ArrayList<>();
        try {
            TypedQuery<Kartinka> typedQuery = entityManager.createQuery(
                    "SELECT k FROM Kartinka k, VoprosPeretaskivanieKartinok v, "
                    + "PolozhenieKartinki pk WHERE "
                    + "v.idVoprosPeretaskivanieKartinok=:id AND "
                    + "pk.voprosPeretaskivanieKartinokId"
                    + "VoprosPeretaskivanieKartinok.idVoprosPeretaskivanie"
                    + "Kartinok=v.idVoprosPeretaskivanieKartinok AND "
                    + "k.idKartinka=pk.kartinkaIdKartinka.idKartinka",
                    Kartinka.class);
            typedQuery.setParameter("id", currentQuestion.getIdVoprosPeretaskivanieKartinok());
            List<Kartinka> list = typedQuery.getResultList();
            int rowSize = 5;
            Collections.shuffle(list);
            for (int i = 0; i < list.size(); ++i) {
                int top = i > rowSize
                        ? TOP_PICTURE_Y + Area.DEFAULT_SIZE + SPAN
                        : TOP_PICTURE_Y;
                int left = i > rowSize
                        ? LEFT_X + (i - rowSize - 1) * (Area.DEFAULT_SIZE + SPAN)
                        : LEFT_X + i * (Area.DEFAULT_SIZE + SPAN);
                Area area = new Area(left, top);
//                Kartinka kartinka = listToQuestion.get(i);
                Kartinka kartinka = list.get(i);
                area.setData(kartinka, ImageIO.read(new File(kartinka.getImgLink())));
                areasContainingPictures.add(area);
            }
        } catch (IOException ex) {
            DialogManager.errorMessage(ex);
        }
        if (currentQuestion != null) {
            lQuestionFormulation.setText(currentQuestion.
                    getVoprosIdVopros().getFormulirovka());
        }
        updateLabel();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    private void bNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextQuestionActionPerformed
        if (currentQuestionIndex < questionsAmount - 1) {
            //записать результаты
            for (int i = 0; i < amountOfAreasInCurrentQuestion; ++i) {
                results[currentQuestionIndex][i]
                        = areasForPlacingPictures[i].getKartinka().getIdKartinka();
            }
            currentQuestion = questions.get(++currentQuestionIndex);
            makeNextQuestion();
            if (currentQuestionIndex == questionsAmount - 1) {
                bNextQuestion.setEnabled(false);
            }
            draw();
        }
    }//GEN-LAST:event_bNextQuestionActionPerformed

    private void bCompleteTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompleteTestActionPerformed
        if (currentQuestionIndex < questionsAmount - 1) {
            if (!DialogManager.confirmDeleting("Вы ответили не на все вопросы! "
                    + "Вы действительно хотите завершить тест?")) {
                return;
            }
        }
        //проверить тест
        for (int i = 0; i < amountOfAreasInCurrentQuestion; ++i) {
            if (areasForPlacingPictures[i].getKartinka() != null) {
                results[currentQuestionIndex][i]
                        = areasForPlacingPictures[i].getKartinka().getIdKartinka();
            } else {
                results[currentQuestionIndex][i] = -1;
            }
        }
        int scored = 0;
        int maximalScore = 0;
        for (int i = 0; i < results.length; ++i) {
            boolean correct = true;
            VoprosPeretaskivanieKartinok vpk = questions.get(i);
            List<PolozhenieKartinki> listOfPositions
                    = vpk.getPolozhenieKartinkiList();
            for (int j = 0; j < listOfPositions.size(); ++j) {
                int idPicture = listOfPositions.get(j).
                        getKartinkaIdKartinka().getIdKartinka();
                int idArea = listOfPositions.get(j).
                        getPoryadkoviyNomerIdPoryadkoviyNomer().getNomer();
                if (idArea != -1) {
                    if (results[i][idArea - 1] != idPicture) {
                        correct = false;
                        break;
                    }
                }
            }
            if (correct) {
                scored += vpk.getVoprosIdVopros().getBall();
            }
            maximalScore += vpk.getVoprosIdVopros().getBall();
        }
        DialogManager.notify("Результат", "Вы набрали " + scored + " баллов  из "
                + maximalScore, DialogManager.TypeOfMessage.INFORMATION);
        try {
            if (student == null) {
                student = entityManager.createNamedQuery(
                        "Student.findAll", Student.class).getResultList().get(0);
            }
            StudentTest studentTest = new StudentTest();
            studentTest.setStudentIdStudent(student);
            studentTest.setTestIdTest(testForPassage);
            studentTest.setProcentBallov(scored * 100 / maximalScore);
            studentTest.setDataProhozhdeniya(GregorianCalendar.
                    getInstance().getTime());
            entityManager.getTransaction().begin();
            entityManager.persist(studentTest);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            DialogManager.errorMessage(ex);
        }
        dispose();
    }//GEN-LAST:event_bCompleteTestActionPerformed

    private void paneWorkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneWorkMousePressed
        placing = null;
        for (Area area : areasContainingPictures) {
            if (area.containPoint(evt.getX(), evt.getY())) {
                placing = area;
                break;
            }
        }
    }//GEN-LAST:event_paneWorkMousePressed

    private void paneWorkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneWorkMouseReleased
        if (placing != null) {
            for (Area area : areasForPlacingPictures) {
                if (area.containPoint(evt.getX(), evt.getY())) {
                    if (area.getKartinka() != null && area.getImage() != null) {
                        Area area1 = new Area(placing);
                        area1.setData(area.getKartinka(), area.getImage());
                        areasContainingPictures.add(area1);
                    }
                    area.setData(placing.getKartinka(), placing.getImage());
                    areasContainingPictures.remove(placing);
                    placing = null;
                    draw();
                    break;
                }
            }
        }
    }//GEN-LAST:event_paneWorkMouseReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (DialogManager.confirmClosingForm("Выйти")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCompleteTest;
    private javax.swing.JButton bNextQuestion;
    private javax.swing.JLabel lQuestionFormulation;
    private javax.swing.JLabel labelQuestionNumber;
    private javax.swing.JPanel paneWork;
    // End of variables declaration//GEN-END:variables

}
