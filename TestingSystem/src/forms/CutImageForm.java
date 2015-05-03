package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Алексей
 */
public class CutImageForm extends javax.swing.JDialog {

    private BufferedImage image;
    private final Graphics GRAPHICS;
    private final List<VerticalLine> LINES;

    private class VerticalLine {

        public int x;
        public boolean selected;

        public VerticalLine(int x) {
            this.x = x;
        }

        public void setSelected(int x) {
            if (x >= this.x - 1 && x <= this.x + 1) {
                selected = true;
            } else {
                selected = false;
            }
        }

        public void draw(Graphics g) {
            if (selected) {
                g.setColor(Color.BLUE);
                g.fillRect(x - 1, 0, 2, paneForDisplayImage.getHeight());
            }
            g.setColor(Color.BLACK);
            g.drawLine(x, 0, x, paneForDisplayImage.getHeight());
        }
    }

    private void clearSelection() {
        for (VerticalLine verticalLine : LINES) {
            verticalLine.selected = false;
        }
    }

    public CutImageForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        GRAPHICS = paneForDisplayImage.getGraphics();
        LINES = new ArrayList<>();
        LINES.add(new VerticalLine(20));
        LINES.add(new VerticalLine(120));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw();
    }

    private void draw() {
        GRAPHICS.clearRect(0, 0, paneForDisplayImage.getWidth(),
                paneForDisplayImage.getHeight());
        if (image != null) {
            GRAPHICS.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        }
        for (VerticalLine verticalLine : LINES) {
            verticalLine.draw(GRAPHICS);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneForDisplayImage = new javax.swing.JPanel();
        bSave = new javax.swing.JButton();
        lLinesCount = new javax.swing.JLabel();
        bLoadImage = new javax.swing.JButton();
        bCreateImage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Разрезание картинок");

        paneForDisplayImage.setBackground(new java.awt.Color(204, 204, 204));
        paneForDisplayImage.setToolTipText("");
        paneForDisplayImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneForDisplayImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneForDisplayImageLayout = new javax.swing.GroupLayout(paneForDisplayImage);
        paneForDisplayImage.setLayout(paneForDisplayImageLayout);
        paneForDisplayImageLayout.setHorizontalGroup(
            paneForDisplayImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        paneForDisplayImageLayout.setVerticalGroup(
            paneForDisplayImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        bSave.setText("Сохранить");

        lLinesCount.setText("Линии: 1/5");

        bLoadImage.setText("Загрузить картинку");
        bLoadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadImageActionPerformed(evt);
            }
        });

        bCreateImage.setText("Создать картинку с формулой");
        bCreateImage.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneForDisplayImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bLoadImage)
                        .addGap(18, 18, 18)
                        .addComponent(bCreateImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                        .addComponent(lLinesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneForDisplayImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave)
                    .addComponent(lLinesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bLoadImage)
                    .addComponent(bCreateImage))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLoadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadImageActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser("C:");
            fileChooser.showOpenDialog(this);
            File file = fileChooser.getSelectedFile();
            image = ImageIO.read(file);
            draw();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Ошибка", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_bLoadImageActionPerformed

    private void paneForDisplayImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForDisplayImageMouseClicked
        if (evt.getClickCount() == 2) {
            LINES.add(new VerticalLine(evt.getX()));

        } else if (evt.getClickCount() == 1) {
//            clearSelection();
            for (VerticalLine verticalLine : LINES) {
                verticalLine.setSelected(evt.getX());
            }
        }
        draw();
    }//GEN-LAST:event_paneForDisplayImageMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCreateImage;
    private javax.swing.JButton bLoadImage;
    private javax.swing.JButton bSave;
    private javax.swing.JLabel lLinesCount;
    private javax.swing.JPanel paneForDisplayImage;
    // End of variables declaration//GEN-END:variables

}
