package forms;

import entities.Kartinka;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.TypedQuery;
import main.Area;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author Артем
 */
public class PictureManagerForm extends javax.swing.JDialog {

    private final Graphics G_PICTURES;
    private final int PICTURE_SIZE = 140;
    private final int START_X = 10;
    private final int START_Y = 10;
    private final int PICTURES_LIMIT = 20;
    private List<Kartinka> picturesFromDB;
    private List<Area> imagesWithData;
    private Area selectedArea;
    private int beginIndex;

    public PictureManagerForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        beginIndex = 0;
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        G_PICTURES = paneForPictures.getGraphics();
        G_PICTURES.setColor(Color.BLACK);
        loadPictures();
        draw();
    }

    private void loadPictures() {
        try {
            TypedQuery<Kartinka> typedQuery = entityManager.createQuery(
                    "Select k FROM Kartinka k",
                    Kartinka.class);
            picturesFromDB = typedQuery.getResultList();
            File dir = new File("./images");
            if (dir.exists() && dir.isDirectory()) {
                if (imagesWithData == null) {
                    imagesWithData = new ArrayList<>();
                }
                imagesWithData.clear();
                for (int i = 0; i < picturesFromDB.size(); i++) {
                    Kartinka picture = picturesFromDB.get(i);
                    Area area = new Area(0, 0, PICTURE_SIZE,
                            picture.getIdKartinka(), picture,
                            ImageIO.read(new File(picture.getImgLink())));
                    imagesWithData.add(area);
                }
            }
        } catch (Exception e) {
            DialogManager.errorMessage(e);
        }
    }

    private void draw() {
        if (imagesWithData != null) {
            G_PICTURES.clearRect(0, 0, paneForPictures.getWidth(),
                    paneForPictures.getHeight());
//            for (int i = 0; i < imagesWithData.size(); ++i) {
//                Area area = imagesWithData.get(i);
//                area.draw(G_PICTURES, Color.white, true, Color.BLUE);
//            }
            int bound = imagesWithData.size() - beginIndex;
            if (bound > PICTURES_LIMIT) {
                bound = PICTURES_LIMIT;
            }
            for (int i = 0; i < bound; ++i) {
                Area area = imagesWithData.get(beginIndex + i);
                int x = (i % 5) * (PICTURE_SIZE + 2) + START_X;
                int y = (i / 5) * (PICTURE_SIZE + 2) + START_Y;
                area.setX(x);
                area.setY(y);
                area.draw(G_PICTURES, Color.WHITE, true, Color.BLUE);
            }
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

        bClose = new javax.swing.JButton();
        bDeletePicture = new javax.swing.JButton();
        bCutImages = new javax.swing.JButton();
        paneForPictures = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Менеджер фрагментов");
        setResizable(false);

        bClose.setText("Закрыть");
        bClose.setPreferredSize(new java.awt.Dimension(100, 30));
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        bDeletePicture.setText("<html><center>Удалить");
        bDeletePicture.setPreferredSize(new java.awt.Dimension(100, 30));
        bDeletePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeletePictureActionPerformed(evt);
            }
        });

        bCutImages.setText("<html><center>Разрезание изображений");
        bCutImages.setPreferredSize(new java.awt.Dimension(100, 30));
        bCutImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCutImagesActionPerformed(evt);
            }
        });

        paneForPictures.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        paneForPictures.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                paneForPicturesMouseWheelMoved(evt);
            }
        });
        paneForPictures.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneForPicturesMousePressed(evt);
            }
        });

        javax.swing.GroupLayout paneForPicturesLayout = new javax.swing.GroupLayout(paneForPictures);
        paneForPictures.setLayout(paneForPicturesLayout);
        paneForPicturesLayout.setHorizontalGroup(
            paneForPicturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 758, Short.MAX_VALUE)
        );
        paneForPicturesLayout.setVerticalGroup(
            paneForPicturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );

        jButton1.setText("<<");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(">>");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneForPictures, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(bCutImages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bDeletePicture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneForPictures, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bDeletePicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bCutImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(61, 61, 61))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCutImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCutImagesActionPerformed
        CutImageForm cutImageForm = new CutImageForm(null, true);
        cutImageForm.setVisible(true);
        loadPictures();
        draw();
    }//GEN-LAST:event_bCutImagesActionPerformed

    private void paneForPicturesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForPicturesMousePressed
        selectedArea = null;
        for (Area area : imagesWithData) {
            if (area.containPoint(evt.getX(), evt.getY())) {
                selectedArea = area;
            }
            area.setSelected(evt.getX(), evt.getY());
        }
        draw();
    }//GEN-LAST:event_paneForPicturesMousePressed

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bDeletePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeletePictureActionPerformed
        if (selectedArea != null) {
            try {
                File file = new File(selectedArea.getKartinka().getImgLink());
                if (file.exists()) {
                    file.delete();
                }
                entityManager.getTransaction().begin();
                entityManager.remove(selectedArea.getKartinka());
                entityManager.getTransaction().commit();
                imagesWithData.remove(selectedArea);
            } catch (Exception e) {
                DialogManager.errorMessage(e);
            }
            loadPictures();
            draw();
        }
    }//GEN-LAST:event_bDeletePictureActionPerformed

    private void paneForPicturesMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_paneForPicturesMouseWheelMoved
        if (evt.getWheelRotation() < 0) {
            beginIndex = beginIndex > 1 ? beginIndex - 1 : 0;
        } else {
            beginIndex = beginIndex < imagesWithData.size() - PICTURES_LIMIT
                    ? beginIndex + 1
                    : beginIndex;
        }
        draw();
    }//GEN-LAST:event_paneForPicturesMouseWheelMoved

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        beginIndex = beginIndex > 1 ? beginIndex - 1 : 0;
        draw();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        beginIndex = beginIndex < imagesWithData.size() - PICTURES_LIMIT
                ? beginIndex + 1
                : beginIndex;
        draw();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bCutImages;
    private javax.swing.JButton bDeletePicture;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel paneForPictures;
    // End of variables declaration//GEN-END:variables

}
