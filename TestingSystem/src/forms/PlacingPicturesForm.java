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
import javax.swing.JOptionPane;
import main.Area;
import main.DialogManager;
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

    private Area[][] rightAreas;
    private final Area[] WRONG_AREAS;
    private final Graphics G_RIGHT_AREAS;
    private final Graphics G_WRONG_AREAS;
    private final Graphics G_PICTURES;
    private List<ImageWithData> imagesWithData;
    private int beginIndex;
    private final int PICTURES_SIZE;
    private final int PICTURES_MAX_AMOUNT;
    private final int AREA_SIZE = 100;
    private Color areaRightColor;
    private Color areaWrongColor;
    private ImageWithData selectedIWD;
    private int startX = 20;
    private int startY = 20;
    private int AREA_SPAN = 10;
    private Color numberColor;
    private Disciplina subject;
    private List<Kartinka> picturesFromDB;

    public PlacingPicturesForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        areaRightColor = Color.GRAY;
        areaWrongColor = Color.ORANGE;
        numberColor = Color.RED;
        G_PICTURES = spaneForPictures.getGraphics();
        G_RIGHT_AREAS = paneForRightAreas.getGraphics();
        G_WRONG_AREAS = paneForWrongAreas.getGraphics();
        PICTURES_SIZE = spaneForPictures.getHeight();
        PICTURES_MAX_AMOUNT = spaneForPictures.getWidth() / PICTURES_SIZE;
        beginIndex = 0;
        WRONG_AREAS = new Area[PICTURES_MAX_AMOUNT];
        for (int i = 0; i < WRONG_AREAS.length; i++) {
            WRONG_AREAS[i] = new Area(startX
                    + i * (AREA_SIZE + AREA_SPAN),
                    startY, AREA_SIZE);
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
        for (Area[] row : rightAreas) {
            for (Area column : row) {
                if (column != null) {
                    column.selected = false;
                }
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
        if (rightAreas == null) {
            return null;
        }
        for (Area[] row : rightAreas) {
            for (Area area : row) {
                if (area != null) {
                    result.get(0).add(area);
                }
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
//                File[] filesInDirectory = dir.listFiles();
//                for (File file : filesInDirectory) {
                    for (Kartinka picture : picturesFromDB) {
                        BufferedImage loadedImage
                                = ImageIO.read(new File(picture.getImgLink()));
                        imagesWithData.add(new ImageWithData(loadedImage, picture));
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.toString());
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
        if (rightAreas != null) {
            G_RIGHT_AREAS.clearRect(0, 0, paneForRightAreas.getWidth(),
                    paneForRightAreas.getHeight());
            for (Area[] row : rightAreas) {
                for (Area column : row) {
                    if (column != null) {
                        column.draw(G_RIGHT_AREAS, areaRightColor, numberColor);
                    }
                }
            }
        }
        G_WRONG_AREAS.clearRect(0, 0, paneForWrongAreas.getWidth(),
                paneForWrongAreas.getHeight());
        for (Area area : WRONG_AREAS) {
            area.draw(G_WRONG_AREAS, areaWrongColor, numberColor);
        }
    }

    public void setAreas(Area[][] areas) {
        this.rightAreas = areas;
    }

    private Area getSelectedAreaFromRightAreas() {
        for (Area[] row : rightAreas) {
            for (Area column : row) {
                if (column != null && column.selected) {
                    return column;
                }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        paneForRightAreas.setBackground(new java.awt.Color(204, 204, 204));
        paneForRightAreas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneForRightAreasMousePressed(evt);
            }
        });

        javax.swing.GroupLayout paneForRightAreasLayout = new javax.swing.GroupLayout(paneForRightAreas);
        paneForRightAreas.setLayout(paneForRightAreasLayout);
        paneForRightAreasLayout.setHorizontalGroup(
            paneForRightAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        paneForRightAreasLayout.setVerticalGroup(
            paneForRightAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        bDeletePicture.setText("<html><center>Удалить картинку из области");
        bDeletePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeletePictureActionPerformed(evt);
            }
        });

        paneForWrongAreas.setBackground(new java.awt.Color(204, 204, 204));
        paneForWrongAreas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneForWrongAreasMousePressed(evt);
            }
        });

        javax.swing.GroupLayout paneForWrongAreasLayout = new javax.swing.GroupLayout(paneForWrongAreas);
        paneForWrongAreas.setLayout(paneForWrongAreasLayout);
        paneForWrongAreasLayout.setHorizontalGroup(
            paneForWrongAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        paneForWrongAreasLayout.setVerticalGroup(
            paneForWrongAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
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

        bClose.setText("Закрыть");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        bSave.setText("Продолжить");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        bClearAll.setText("Очистить все области");
        bClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearAllActionPerformed(evt);
            }
        });

        bChooseColor.setText("Цвет цифр");
        bChooseColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChooseColorActionPerformed(evt);
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
                        .addComponent(paneForRightAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bDeletePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(bClearAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bScrollLeft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spaneForPictures)
                        .addGap(10, 10, 10)
                        .addComponent(bScrollRight))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneForWrongAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bClose, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(bSave, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(bChooseColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bDeletePicture, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(bClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paneForRightAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(paneForWrongAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(bChooseColor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bScrollRight, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(spaneForPictures)
                    .addComponent(bScrollLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
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
        for (Area[] row : rightAreas) {
            for (Area area : row) {
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
        for (Area[] row : rightAreas) {
            for (Area area : row) {
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
            if (area.image != null) {
                imagesWithData.add(
                        new ImageWithData(area.image, area.kartinka));
                area.image = null;
            }
        }
        for (Area[] row : rightAreas) {
            for (Area area : row) {
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
            dispose();
        }
    }//GEN-LAST:event_bCloseActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        dispose();
    }//GEN-LAST:event_bSaveActionPerformed

    private void bChooseColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChooseColorActionPerformed
        numberColor = JColorChooser.showDialog(this,
                "Выбрать цвет цифр", numberColor);
    }//GEN-LAST:event_bChooseColorActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (DialogManager.confirmClosingForm("вопроса")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChooseColor;
    private javax.swing.JButton bClearAll;
    private javax.swing.JButton bClose;
    private javax.swing.JButton bDeletePicture;
    private javax.swing.JButton bSave;
    private javax.swing.JButton bScrollLeft;
    private javax.swing.JButton bScrollRight;
    private javax.swing.JPanel paneForRightAreas;
    private javax.swing.JPanel paneForWrongAreas;
    private javax.swing.JScrollPane spaneForPictures;
    // End of variables declaration//GEN-END:variables

}
