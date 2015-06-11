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
    private final int PICTURES_MAX_AMOUNT = 16;
    private int beginIndex;

    private List<Kartinka> picturesFromDB;
    private List<Area> imagesWithData;

    public PictureManagerForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        G_PICTURES = paneForPictures.getGraphics();
        beginIndex = 0;
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
                for (Kartinka picture : picturesFromDB) {
                    Area area = new Area(0, 0, PICTURE_SIZE);
                    area.image = ImageIO.read(new File(picture.getImgLink()));
                    area.kartinka = picture;
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
                int x = (i % 4) * PICTURE_SIZE;
                int y = (i / 4) * PICTURE_SIZE;
                G_PICTURES.drawImage(imagesWithData.get(i).image, x, y,
                        PICTURE_SIZE, PICTURE_SIZE, null);

                G_PICTURES.drawRect(x, y, PICTURE_SIZE, PICTURE_SIZE);
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

        paneForPictures = new javax.swing.JPanel();
        bClose = new javax.swing.JButton();
        bPreviousPicture = new javax.swing.JButton();
        bNextPicture = new javax.swing.JButton();
        bDeletePicture = new javax.swing.JButton();
        bCutImages = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Менеджер фрагментов");

        paneForPictures.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout paneForPicturesLayout = new javax.swing.GroupLayout(paneForPictures);
        paneForPictures.setLayout(paneForPicturesLayout);
        paneForPicturesLayout.setHorizontalGroup(
            paneForPicturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );
        paneForPicturesLayout.setVerticalGroup(
            paneForPicturesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        bClose.setText("Закрыть");
        bClose.setPreferredSize(new java.awt.Dimension(100, 30));

        bPreviousPicture.setText("<html><center>Предыдущая");
        bPreviousPicture.setPreferredSize(new java.awt.Dimension(100, 30));

        bNextPicture.setText("<html><center>Следующая");
        bNextPicture.setPreferredSize(new java.awt.Dimension(100, 30));

        bDeletePicture.setText("<html><center>Удалить");
        bDeletePicture.setPreferredSize(new java.awt.Dimension(100, 30));

        bCutImages.setText("<html><center>Разрезание картинок");
        bCutImages.setPreferredSize(new java.awt.Dimension(100, 30));
        bCutImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCutImagesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneForPictures, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bPreviousPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bNextPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bDeletePicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bCutImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneForPictures, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bPreviousPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bNextPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bDeletePicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bCutImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 356, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCutImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCutImagesActionPerformed
        CutImageForm cutImageForm = new CutImageForm(null, true);
        cutImageForm.setVisible(true);
        loadPictures();
        draw();
    }//GEN-LAST:event_bCutImagesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bCutImages;
    private javax.swing.JButton bDeletePicture;
    private javax.swing.JButton bNextPicture;
    private javax.swing.JButton bPreviousPicture;
    private javax.swing.JPanel paneForPictures;
    // End of variables declaration//GEN-END:variables

}
