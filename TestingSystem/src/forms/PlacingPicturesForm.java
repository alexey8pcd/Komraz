package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import main.Area;

/**
 *
 * @author Алексей
 */
public class PlacingPicturesForm extends javax.swing.JDialog {

    private Area[][] rightAreas;
    private Area[] wrongAreas;
    private final Graphics G_RIGHT_AREAS;
    private final Graphics G_WRONG_AREAS;
    private final Graphics G_PICTURES;
    private List<BufferedImage> images;
    private int beginIndex;
    private final int PICTURES_SIZE;
    private final int PICTURES_MAX_AMOUNT;
    private final int AREA_SIZE = 100;
    private Color areaRightColor;
    private Color areaWrongColor;
    private BufferedImage selectedImage;
    private int startX = 20;
    private int startY = 20;
    private int AREA_SPAN = 10;

    public PlacingPicturesForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        areaRightColor = Color.GRAY;
        areaWrongColor = Color.ORANGE;
        G_PICTURES = spaneForPictures.getGraphics();
        G_RIGHT_AREAS = paneForRightAreas.getGraphics();
        G_WRONG_AREAS = paneForWrongAreas.getGraphics();
        PICTURES_SIZE = spaneForPictures.getHeight();
        PICTURES_MAX_AMOUNT = spaneForPictures.getWidth() / PICTURES_SIZE;
        beginIndex = 0;
        wrongAreas = new Area[PICTURES_MAX_AMOUNT];

        for (int i = 0; i < wrongAreas.length; i++) {
            wrongAreas[i] = new Area(startX
                    + i * (AREA_SIZE + AREA_SPAN),
                    startY, AREA_SIZE);
        }
        loadImages();
        draw();

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
        for (Area column : wrongAreas) {
            if (column != null) {
                column.selected = false;
            }
        }
    }

    private void loadImages() {
        try {
            File dir = new File("./images");
            if (dir.exists() && dir.isDirectory()) {
                if (images == null) {
                    images = new ArrayList<>();
                }
                images.clear();
                File[] filesInDirectory = dir.listFiles();
                for (int i = 0; i < filesInDirectory.length; i++) {
                    images.add(ImageIO.read(filesInDirectory[i]));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    private void draw() {
        if (images != null) {
            G_PICTURES.clearRect(0, 0, spaneForPictures.getWidth(),
                    spaneForPictures.getHeight());
            for (int i = beginIndex, j = 0;
                    i < images.size() && j < PICTURES_SIZE; i++, j++) {
                G_PICTURES.drawImage(images.get(i), 10 + j * (PICTURES_SIZE + 2),
                        10, PICTURES_SIZE, PICTURES_SIZE, null);
            }
        }
        if (rightAreas != null) {
            G_RIGHT_AREAS.clearRect(0, 0, paneForRightAreas.getWidth(),
                    paneForRightAreas.getHeight());
            for (Area[] row : rightAreas) {
                for (Area column : row) {
                    if (column != null) {
                        column.draw(G_RIGHT_AREAS, areaRightColor);
                    }
                }
            }
        }
        G_WRONG_AREAS.clearRect(0, 0, paneForWrongAreas.getWidth(),
                paneForWrongAreas.getHeight());
        for (Area area : wrongAreas) {
            area.draw(G_WRONG_AREAS, areaWrongColor);
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
        for (Area area : wrongAreas) {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        bSave.setText("Сохранить");

        bClearAll.setText("Очистить все области");
        bClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearAllActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(paneForWrongAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bScrollRight, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(spaneForPictures)
                    .addComponent(bScrollLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bScrollRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bScrollRightActionPerformed
        if (beginIndex < images.size() - PICTURES_MAX_AMOUNT) {
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
        selectedImage = null;
        for (int j = 0; j < PICTURES_MAX_AMOUNT; j++) {
            int left = 10 + j * (PICTURES_SIZE + 2);
            int right = left + PICTURES_SIZE;
            if (x >= left && x < right && y >= top && y <= down) {
                int index = beginIndex + j;
                if (index >= 0 && index < images.size()) {
                    selectedImage = images.get(index);
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
            for (Area column : row) {
                if (column != null) {
                    if (column.containPoint(positionOnRightPanelX,
                            positionOnRightPanelY)) {
                        if (column.image != null && selectedImage != null) {
                            images.add(column.image);
                        }
                        if (selectedImage != null) {
                            column.image = selectedImage;
                            inserted = true;
                        }
                        break;
                    }
                }
            }
        }
        for (Area column : wrongAreas) {
            if (column != null) {
                if (column.containPoint(positionOnWrongPanelX,
                        positionOnWrongPanelY)) {
                    if (column.image != null && selectedImage != null) {
                        images.add(column.image);
                    }
                    if (selectedImage != null) {
                        column.image = selectedImage;
                        inserted = true;
                    }
                    break;
                }
            }
        }
        if (inserted) {
            images.remove(selectedImage);
            if (beginIndex > 0) {
                --beginIndex;
            }
        }
        selectedImage = null;
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
        for (Area area : wrongAreas) {
            area.setSelected(evt.getX(), evt.getY());
        }
        draw();
    }//GEN-LAST:event_paneForWrongAreasMousePressed

    private void bDeletePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeletePictureActionPerformed
        Area area = getSelectedAreaFromRightAreas();
        if (area != null) {
            if (area.selected) {
                if (area.image != null) {
                    images.add(area.image);
                    area.image = null;
                }
            }
        }
        area = getSelectedAreaFromWrongAreas();
        if (area != null) {
            if (area.selected) {
                if (area.image != null) {
                    images.add(area.image);
                    area.image = null;
                }
            }
        }
        draw();
    }//GEN-LAST:event_bDeletePictureActionPerformed

    private void bClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearAllActionPerformed
        for (Area area : wrongAreas) {
            if (area.image != null) {
                images.add(area.image);
                area.image = null;
            }
        }
        for (Area[] row : rightAreas) {
            for (Area area : row) {
                if (area.image != null) {
                    images.add(area.image);
                    area.image = null;
                }
            }
        }
        draw();
    }//GEN-LAST:event_bClearAllActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
