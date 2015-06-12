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
    private List<Kartinka> picturesFromDB;
    private List<Area> imagesWithData;
    private Area selectedArea;
    

    public PictureManagerForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
                    int x = (i % 5) * (PICTURE_SIZE + 2) + START_X;
                    int y = (i / 5) * (PICTURE_SIZE + 2) + START_Y;
                    Area area = new Area(x, y, PICTURE_SIZE, 
                            picture.getIdKartinka(),picture,
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
            for (int i = 0; i < imagesWithData.size(); ++i) {
                Area area = imagesWithData.get(i);
                area.draw(G_PICTURES, Color.white, true, Color.BLUE);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Менеджер фрагментов");
        setPreferredSize(new java.awt.Dimension(900, 600));
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

        bCutImages.setText("<html><center>Разрезание картинок");
        bCutImages.setPreferredSize(new java.awt.Dimension(100, 30));
        bCutImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCutImagesActionPerformed(evt);
            }
        });

        paneForPictures.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
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
                    .addComponent(bDeletePicture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneForPictures, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bDeletePicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bCutImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bCutImages;
    private javax.swing.JButton bDeletePicture;
    private javax.swing.JPanel paneForPictures;
    // End of variables declaration//GEN-END:variables

}
