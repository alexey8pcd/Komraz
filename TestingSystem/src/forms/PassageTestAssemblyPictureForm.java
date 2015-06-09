package forms;

import entities.Kartinka;
import entities.PolozhenieKartinki;
import entities.Student;
import entities.Test;
import entities.TestVopros;
import entities.VoprosPeretaskivanieKartinok;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.TypedQuery;
import main.Area;
import main.DialogManager;
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
    private Kartinka picture;
    private BufferedImage image;

    public PassageTestAssemblyPictureForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
                area.draw(GRAPHICS, Color.RED, Color.BLACK);
            }
        }
        if (areasContainingPictures != null) {
            for (Area area : areasContainingPictures) {
                area.draw(GRAPHICS, Color.RED, Color.BLACK);
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
        bPreviousQuestion = new javax.swing.JButton();
        bNextQuestion = new javax.swing.JButton();
        bCompleteTest = new javax.swing.JButton();
        paneWork = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelQuestionNumber.setText("Вопрос N/M");

        lQuestionFormulation.setText("Формулировка вопроса");

        bPreviousQuestion.setText("Предыдущий вопрос");
        bPreviousQuestion.setEnabled(false);
        bPreviousQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPreviousQuestionActionPerformed(evt);
            }
        });

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
                                .addGap(42, 42, 42)
                                .addComponent(bPreviousQuestion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bPreviousQuestion)
                            .addComponent(bNextQuestion)))
                    .addComponent(labelQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lQuestionFormulation, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneWork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 457, Short.MAX_VALUE)
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
            questions.add(testVopros.getVoprosIdVopros().
                    getVoprosPeretaskivanieKartinokList().get(0));
        }
        Collections.shuffle(questions);
        currentQuestionIndex = 0;
        currentQuestion = questions.get(currentQuestionIndex);
        questionsAmount = questions.size();
        makeNextQuestion();
        draw();
    }

    private void makeNextQuestion() {
        int amountOfAreas = currentQuestion.getKolvoOblasteyIdKolvoOblastey().
                getKolvo();
        areasForPlacingPictures = new Area[amountOfAreas];
        for (int i = 0; i < areasForPlacingPictures.length; ++i) {
            Area area = new Area(LEFT_X + i * (Area.DEFAULT_SIZE + SPAN),
                    TOP_AREA_Y, Area.DEFAULT_SIZE);
            area.number = i + 1;
            areasForPlacingPictures[i] = area;

        }
        areasContainingPictures = new ArrayList<>();
        List<PolozhenieKartinki> linkList
                = currentQuestion.getPolozhenieKartinkiList();
        try {
            int rowSize = 5;
            for (int i = 0; i < linkList.size(); ++i) {
                int top = i > rowSize
                        ? TOP_PICTURE_Y + Area.DEFAULT_SIZE + SPAN
                        : TOP_PICTURE_Y;
                int left = i > rowSize
                        ? LEFT_X + (i - rowSize - 1) * (Area.DEFAULT_SIZE + SPAN)
                        : LEFT_X + i * (Area.DEFAULT_SIZE + SPAN);
                Area area = new Area(left, top, Area.DEFAULT_SIZE);
                area.image = ImageIO.read(new File(linkList.get(i).
                        getKartinkaIdKartinka().getImgLink()));
                area.kartinka = linkList.get(i).getKartinkaIdKartinka();
                area.showNumber = false;
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

    private void bPreviousQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPreviousQuestionActionPerformed
    }//GEN-LAST:event_bPreviousQuestionActionPerformed

    private void bNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextQuestionActionPerformed

    }//GEN-LAST:event_bNextQuestionActionPerformed

    private void bCompleteTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompleteTestActionPerformed

    }//GEN-LAST:event_bCompleteTestActionPerformed

    private void paneWorkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneWorkMousePressed
        for (Area area : areasContainingPictures) {
            if (area.containPoint(evt.getX(), evt.getY())) {
                picture = area.kartinka;
                image = area.image;
                break;
            }
        }
    }//GEN-LAST:event_paneWorkMousePressed

    private void paneWorkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneWorkMouseReleased
        if (picture != null && image != null) {
            for (Area area : areasForPlacingPictures) {
                if (area.containPoint(evt.getX(), evt.getY())) {
                    area.kartinka = picture;
                    area.image = image;
                    draw();
                    break;
                }
            }
        }
    }//GEN-LAST:event_paneWorkMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCompleteTest;
    private javax.swing.JButton bNextQuestion;
    private javax.swing.JButton bPreviousQuestion;
    private javax.swing.JLabel lQuestionFormulation;
    private javax.swing.JLabel labelQuestionNumber;
    private javax.swing.JPanel paneWork;
    // End of variables declaration//GEN-END:variables

}
