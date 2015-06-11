package forms;

import entities.Disciplina;
import entities.Kartinka;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.TypedQuery;
import javax.swing.JColorChooser;
import main.Area;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author Алексей
 */
public class PlacingPicturesForm extends javax.swing.JDialog {

    private class ImageWithData {

        public BufferedImage image;
        public Kartinka data;

        public ImageWithData(BufferedImage image, Kartinka data) {
            this.image = image;
            this.data = data;
        }
    }

    private Area[] RIGHT_AREAS;
    private final Area[] WRONG_AREAS;
    private final Graphics G_RIGHT_AREAS;
    private final Graphics G_WRONG_AREAS;
    private final Graphics G_PICTURES;
    private List<ImageWithData> imagesWithData;
    private int beginIndex;
    private int rightAreasAmount;
    private final int PICTURES_SIZE;
    private final int PICTURES_MAX_AMOUNT;
    private final Color AREA_RIGHT_COLOR;
    private final Color AREA_WRONG_COLOR;
    private ImageWithData selectedIWD;
    private final int START_X = 40;
    private final int START_Y = 40;
    private final int SPAN = 10;
    private Color numberColor;
    private Disciplina subject;
    private List<Kartinka> picturesFromDB;

    public PlacingPicturesForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        AREA_RIGHT_COLOR = Color.GREEN;
        AREA_WRONG_COLOR = Color.RED;
        numberColor = Color.BLACK;
        G_PICTURES = spaneForPictures.getGraphics();
        G_RIGHT_AREAS = paneForRightAreas.getGraphics();
        G_WRONG_AREAS = paneForWrongAreas.getGraphics();
        PICTURES_SIZE = spaneForPictures.getHeight();
        PICTURES_MAX_AMOUNT = spaneForPictures.getWidth() / PICTURES_SIZE;
        beginIndex = 0;
        RIGHT_AREAS = new Area[]{
            null, null, null, null, null, null
        };
        RIGHT_AREAS[0] = new Area(START_X, START_Y, Area.DEFAULT_SIZE);
        RIGHT_AREAS[0].number = 1;
        RIGHT_AREAS[1] = new Area(START_X + Area.DEFAULT_SIZE + SPAN,
                START_Y, Area.DEFAULT_SIZE);
        RIGHT_AREAS[1].number = 2;
        rightAreasAmount = 2;
        WRONG_AREAS = new Area[PICTURES_MAX_AMOUNT];
        for (int i = 0; i < WRONG_AREAS.length; i++) {
            WRONG_AREAS[i] = new Area(START_X
                    + i * (Area.DEFAULT_SIZE + SPAN),
                    START_Y, Area.DEFAULT_SIZE);
            WRONG_AREAS[i].number = 7 + i;
        }
        draw();

    }

    public void setSubject(Disciplina subject) {
        this.subject = subject;
        loadImages();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw();
    }

    private void clearSelection() {
        for (Area area : RIGHT_AREAS) {
            if (area != null) {
                area.selected = false;
            }
        }
        for (Area column : WRONG_AREAS) {
            if (column != null) {
                column.selected = false;
            }
        }
    }

    public List<List<Area>> getAreas() {
        List<List<Area>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if (RIGHT_AREAS == null) {
            return null;
        }
        for (Area area : RIGHT_AREAS) {
            if (area != null) {
                result.get(0).add(area);
            }
        }
        result.add(new ArrayList<>());
        for (Area area : WRONG_AREAS) {
            result.get(1).add(area);
        }
        return result;
    }

    private void loadImages() {
        if (subject != null) {
            try {
                TypedQuery<Kartinka> typedQuery = entityManager.createQuery(
                        "Select k FROM Kartinka k WHERE "
                        + "k.disciplinaIdDisciplina.idDisciplina=:id",
                        Kartinka.class);
                typedQuery.setParameter("id", subject.getIdDisciplina());
                picturesFromDB = typedQuery.getResultList();
                File dir = new File("./images");
                if (dir.exists() && dir.isDirectory()) {
                    if (imagesWithData == null) {
                        imagesWithData = new ArrayList<>();
                    }
                    imagesWithData.clear();
                    for (Kartinka picture : picturesFromDB) {
                        BufferedImage loadedImage
                                = ImageIO.read(new File(picture.getImgLink()));
                        imagesWithData.add(new ImageWithData(loadedImage, picture));
                    }
                }
            } catch (Exception e) {
                DialogManager.errorMessage(e);
            }
        }

    }

    private void draw() {
        if (imagesWithData != null) {
            G_PICTURES.clearRect(0, 0, spaneForPictures.getWidth(),
                    spaneForPictures.getHeight());
            for (int i = beginIndex, j = 0;
                    i < imagesWithData.size() && j < PICTURES_SIZE; i++, j++) {
                G_PICTURES.drawImage(imagesWithData.get(i).image, 10 + j * (PICTURES_SIZE + 2),
                        10, PICTURES_SIZE, PICTURES_SIZE, null);
            }
        }
        if (RIGHT_AREAS != null) {
            G_RIGHT_AREAS.clearRect(0, 0, paneForRightAreas.getWidth(),
                    paneForRightAreas.getHeight());
            for (Area area : RIGHT_AREAS) {
                if (area != null) {
                    area.draw(G_RIGHT_AREAS, AREA_RIGHT_COLOR, numberColor);
                }
            }
        }
        G_WRONG_AREAS.clearRect(0, 0, paneForWrongAreas.getWidth(),
                paneForWrongAreas.getHeight());
        for (Area area : WRONG_AREAS) {
            area.draw(G_WRONG_AREAS, AREA_WRONG_COLOR, numberColor);
        }
    }

    private Area getSelectedAreaFromRightAreas() {
        for (Area area : RIGHT_AREAS) {
            if (area != null && area.selected) {
                return area;
            }
        }
        return null;
    }

    private Area getSelectedAreaFromWrongAreas() {
        for (Area area : WRONG_AREAS) {
            if (area != null && area.selected) {
                return area;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneForRightAreas = new javax.swing.JPanel();
        bDeletePicture = new javax.swing.JButton();
        paneForWrongAreas = new javax.swing.JPanel();
        spaneForPictures = new javax.swing.JScrollPane();
        bScrollRight = new javax.swing.JButton();
        bScrollLeft = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        bSave = new javax.swing.JButton();
        bClearAll = new javax.swing.JButton();
        bChooseColor = new javax.swing.JButton();
        bAddArea = new javax.swing.JButton();
        bDeleteArea = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Размещение изображений в областях");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        paneForRightAreas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneForRightAreasMousePressed(evt);
            }
        });

        javax.swing.GroupLayout paneForRightAreasLayout = new javax.swing.GroupLayout(paneForRightAreas);
        paneForRightAreas.setLayout(paneForRightAreasLayout);
        paneForRightAreasLayout.setHorizontalGroup(
            paneForRightAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        paneForRightAreasLayout.setVerticalGroup(
            paneForRightAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        bDeletePicture.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bDeletePicture.setText("<html><center>Очистить область");
        bDeletePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeletePictureActionPerformed(evt);
            }
        });

        paneForWrongAreas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneForWrongAreasMousePressed(evt);
            }
        });

        javax.swing.GroupLayout paneForWrongAreasLayout = new javax.swing.GroupLayout(paneForWrongAreas);
        paneForWrongAreas.setLayout(paneForWrongAreasLayout);
        paneForWrongAreasLayout.setHorizontalGroup(
            paneForWrongAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        paneForWrongAreasLayout.setVerticalGroup(
            paneForWrongAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );

        spaneForPictures.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                spaneForPicturesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                spaneForPicturesMouseReleased(evt);
            }
        });

        bScrollRight.setText(">>");
        bScrollRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bScrollRightActionPerformed(evt);
            }
        });

        bScrollLeft.setText("<<");
        bScrollLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bScrollLeftActionPerformed(evt);
            }
        });

        bClose.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bClose.setText("Закрыть");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        bSave.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bSave.setText("Продолжить");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        bClearAll.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bClearAll.setText("<html><center>Очистить все области");
        bClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearAllActionPerformed(evt);
            }
        });

        bChooseColor.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bChooseColor.setText("<html><center>Изменить цвет цифр");
        bChooseColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChooseColorActionPerformed(evt);
            }
        });

        bAddArea.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bAddArea.setText("<html><center>Добавить область");
        bAddArea.setToolTipText("");
        bAddArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddAreaActionPerformed(evt);
            }
        });

        bDeleteArea.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bDeleteArea.setText("<html><center>Удалить область");
        bDeleteArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteAreaActionPerformed(evt);
            }
        });

        jLabel1.setText("Области с правильными ответами");

        jLabel2.setText("Области с неправильными ответами");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bScrollLeft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spaneForPictures)
                        .addGap(10, 10, 10)
                        .addComponent(bScrollRight))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(paneForWrongAreas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(paneForRightAreas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bDeletePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(bDeleteArea)
                                .addComponent(bAddArea, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bChooseColor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bAddArea, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bDeleteArea, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bDeletePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bChooseColor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(paneForRightAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paneForWrongAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bScrollLeft, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bScrollRight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spaneForPictures, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bScrollRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bScrollRightActionPerformed
        if (beginIndex < imagesWithData.size() - PICTURES_MAX_AMOUNT) {
            ++beginIndex;
            draw();
        }
    }//GEN-LAST:event_bScrollRightActionPerformed

    private void bScrollLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bScrollLeftActionPerformed
        if (beginIndex > 0) {
            --beginIndex;
            draw();
        }
    }//GEN-LAST:event_bScrollLeftActionPerformed

    private void spaneForPicturesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spaneForPicturesMousePressed
        int top = 10;
        int down = 10 + PICTURES_SIZE;
        int x = evt.getX();
        int y = evt.getY();
        selectedIWD = null;
        for (int j = 0; j < PICTURES_MAX_AMOUNT; j++) {
            int left = 10 + j * (PICTURES_SIZE + 2);
            int right = left + PICTURES_SIZE;
            if (x >= left && x < right && y >= top && y <= down) {
                int index = beginIndex + j;
                if (index >= 0 && index < imagesWithData.size()) {
                    selectedIWD = imagesWithData.get(index);
                    break;
                }
            }
        }
    }//GEN-LAST:event_spaneForPicturesMousePressed

    private void spaneForPicturesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spaneForPicturesMouseReleased
        int releaseX = evt.getXOnScreen();
        int releaseY = evt.getYOnScreen();
        int positionOnRightPanelX = releaseX
                - paneForRightAreas.getLocationOnScreen().x;
        int positionOnRightPanelY = releaseY
                - paneForRightAreas.getLocationOnScreen().y;
        int positionOnWrongPanelX = releaseX
                - paneForWrongAreas.getLocationOnScreen().x;
        int positionOnWrongPanelY = releaseY
                - paneForWrongAreas.getLocationOnScreen().y;
        boolean inserted = false;
        for (Area area : RIGHT_AREAS) {
            if (area != null) {
                if (area.containPoint(positionOnRightPanelX,
                        positionOnRightPanelY)) {
                    if (area.image != null && selectedIWD != null) {
                        imagesWithData.add(
                                new ImageWithData(area.image, area.kartinka));
                    }
                    if (selectedIWD != null) {
                        area.image = selectedIWD.image;
                        area.kartinka = selectedIWD.data;
                        inserted = true;
                    }
                    break;
                }
            }
        }
        for (Area area : WRONG_AREAS) {
            if (area != null) {
                if (area.containPoint(positionOnWrongPanelX,
                        positionOnWrongPanelY)) {
                    if (area.image != null && selectedIWD != null) {
                        imagesWithData.add(
                                new ImageWithData(area.image, area.kartinka));
                    }
                    if (selectedIWD != null) {
                        area.image = selectedIWD.image;
                        area.kartinka = selectedIWD.data;
                        inserted = true;
                    }
                    break;
                }
            }
        }
        if (inserted) {
            imagesWithData.remove(selectedIWD);
            if (beginIndex > 0) {
                --beginIndex;
            }
        }
        selectedIWD = null;

        draw();
    }//GEN-LAST:event_spaneForPicturesMouseReleased

    private void paneForRightAreasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForRightAreasMousePressed
        clearSelection();
        for (Area area : RIGHT_AREAS) {
            if (area != null) {
                area.setSelected(evt.getX(), evt.getY());
            }
        }
        draw();
    }//GEN-LAST:event_paneForRightAreasMousePressed

    private void paneForWrongAreasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForWrongAreasMousePressed
        clearSelection();
        for (Area area : WRONG_AREAS) {
            area.setSelected(evt.getX(), evt.getY());
        }
        draw();
    }//GEN-LAST:event_paneForWrongAreasMousePressed

    private void bDeletePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeletePictureActionPerformed
        Area area = getSelectedAreaFromRightAreas();
        if (area != null) {
            if (area.selected) {
                if (area.image != null) {
                    imagesWithData.add(
                            new ImageWithData(area.image, area.kartinka));
                    area.image = null;
                }
            }
        }
        area = getSelectedAreaFromWrongAreas();
        if (area != null) {
            if (area.selected) {
                if (area.image != null) {
                    imagesWithData.add(
                            new ImageWithData(area.image, area.kartinka));
                    area.image = null;
                }
            }
        }
        draw();
    }//GEN-LAST:event_bDeletePictureActionPerformed

    private void bClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearAllActionPerformed
        for (Area area : WRONG_AREAS) {
            if (area != null) {
                if (area.image != null) {
                    imagesWithData.add(
                            new ImageWithData(area.image, area.kartinka));
                    area.image = null;
                }
            }
        }
        for (Area area : RIGHT_AREAS) {
            if (area != null) {
                if (area.image != null) {
                    imagesWithData.add(
                            new ImageWithData(area.image, area.kartinka));
                    area.image = null;
                }
            }
        }
        draw();
    }//GEN-LAST:event_bClearAllActionPerformed

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        if (DialogManager.confirmClosingForm("вопроса")) {
            RIGHT_AREAS = null;
            dispose();
        }
    }//GEN-LAST:event_bCloseActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        boolean wrongAreasFilled = false;
        for (Area area : WRONG_AREAS) {
            if (area.image == null || area.kartinka == null) {
                wrongAreasFilled = true;
            }
        }
        if (wrongAreasFilled) {
            DialogManager.notify("Предупреждение",
                    "Не допускается пустых областей",
                    DialogManager.TypeOfMessage.OK);
        } else {
            dispose();
        }
    }//GEN-LAST:event_bSaveActionPerformed

    private void bChooseColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChooseColorActionPerformed
        numberColor = JColorChooser.showDialog(this,
                "Выбрать цвет цифр", numberColor);
        draw();
    }//GEN-LAST:event_bChooseColorActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (DialogManager.confirmClosingForm("вопроса")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void bAddAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddAreaActionPerformed
        if (rightAreasAmount < 6) {
            for (int i = 0; i < RIGHT_AREAS.length; i++) {
                if (RIGHT_AREAS[i] == null) {
                    RIGHT_AREAS[i] = new Area(
                            START_X + i * (Area.DEFAULT_SIZE + SPAN),
                            START_Y, Area.DEFAULT_SIZE);
                    RIGHT_AREAS[i].number = i + 1;
                    rightAreasAmount++;
                    break;
                }
            }
            draw();
        }
    }//GEN-LAST:event_bAddAreaActionPerformed

    private void bDeleteAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteAreaActionPerformed
        if (rightAreasAmount > 2) {
            for (int i = 0; i < RIGHT_AREAS.length; i++) {
                Area area = RIGHT_AREAS[i];
                if (area != null) {
                    if (area.selected) {
                        imagesWithData.add(
                                new ImageWithData(area.image, area.kartinka));
                        RIGHT_AREAS[i] = null;
                        rightAreasAmount--;
                        break;
                    }
                }
            }
        }
        draw();
    }//GEN-LAST:event_bDeleteAreaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddArea;
    private javax.swing.JButton bChooseColor;
    private javax.swing.JButton bClearAll;
    private javax.swing.JButton bClose;
    private javax.swing.JButton bDeleteArea;
    private javax.swing.JButton bDeletePicture;
    private javax.swing.JButton bSave;
    private javax.swing.JButton bScrollLeft;
    private javax.swing.JButton bScrollRight;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel paneForRightAreas;
    private javax.swing.JPanel paneForWrongAreas;
    private javax.swing.JScrollPane spaneForPictures;
    // End of variables declaration//GEN-END:variables

}
