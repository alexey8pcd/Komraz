package forms;

import entities.Disciplina;
import entities.Kartinka;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.TypedQuery;
import javax.swing.AbstractListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import sql.DBManager;
import static sql.DBManager.entityManager;

/**
 *
 * @author Алексей
 */
public class CutImageForm extends javax.swing.JDialog {

    private BufferedImage image;
    private final Graphics GRAPHICS;
    private final List<VerticalLine> LINES;
    private Color lineColor = Color.BLACK;
    private boolean pressed;
    private List<Disciplina> subjects;
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

    private class VerticalLine {

        public int x;
        public boolean selected;

        public VerticalLine(int x) {
            this.x = x;
        }

        public void setSelected(int x) {
            selected = x >= this.x - 5 && x <= this.x + 5;
        }

        public void draw(Graphics g) {
            if (selected) {
                g.setColor(Color.BLUE);
                g.fillRect(x - 1, 0, 3, paneForDisplayImage.getHeight());
            }
            g.setColor(lineColor);
            g.drawLine(x, 0, x, paneForDisplayImage.getHeight());
        }
    }

    public CutImageForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        GRAPHICS = paneForDisplayImage.getGraphics();
        LINES = new ArrayList<>();
        lLinesCount.setText("Линий: 0 из 5");
        try {
            TypedQuery<Disciplina> typedQuery
                    = entityManager.createNamedQuery("Disciplina.findAll",
                            Disciplina.class);
            subjects = typedQuery.getResultList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        listSubjects.setModel(SUBJECTS_LIST_MODEL);
        listSubjects.setSelectedIndex(0);
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
            double scaleWidth = paneForDisplayImage.getWidth();
            scaleWidth /= image.getWidth();
            double scaleHeight = paneForDisplayImage.getHeight();
            scaleHeight /= image.getHeight();
            double scale = Math.min(scaleWidth, scaleHeight);

            GRAPHICS.drawImage(image, 0, 0, (int) (image.getWidth() * scale),
                    (int) (image.getHeight() * scale), null);
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
        bDeleteSelectedLine = new javax.swing.JButton();
        bChooseColor = new javax.swing.JButton();
        lSubject = new javax.swing.JLabel();
        sPaneForListSubjects = new javax.swing.JScrollPane();
        listSubjects = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Разрезание картинок");

        paneForDisplayImage.setBackground(new java.awt.Color(204, 204, 204));
        paneForDisplayImage.setToolTipText("");
        paneForDisplayImage.setFocusCycleRoot(true);
        paneForDisplayImage.setPreferredSize(new java.awt.Dimension(880, 510));
        paneForDisplayImage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                paneForDisplayImageMouseDragged(evt);
            }
        });
        paneForDisplayImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneForDisplayImageMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneForDisplayImageMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                paneForDisplayImageMouseReleased(evt);
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
            .addGap(0, 427, Short.MAX_VALUE)
        );

        bSave.setText("Сохранить");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        bLoadImage.setText("Загрузить картинку");
        bLoadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadImageActionPerformed(evt);
            }
        });

        bCreateImage.setText("Создать картинку с формулой");
        bCreateImage.setEnabled(false);

        bDeleteSelectedLine.setText("Удалить выбранную линию");
        bDeleteSelectedLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteSelectedLineActionPerformed(evt);
            }
        });

        bChooseColor.setText("Изменить цвет линии");
        bChooseColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChooseColorActionPerformed(evt);
            }
        });

        lSubject.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lSubject.setText("Дисциплина:");

        listSubjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sPaneForListSubjects.setViewportView(listSubjects);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(paneForDisplayImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lSubject)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGap(142, 142, 142)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lLinesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bCreateImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bLoadImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bChooseColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bDeleteSelectedLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneForDisplayImage, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lSubject)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bLoadImage)
                            .addComponent(bDeleteSelectedLine))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bChooseColor)
                            .addComponent(bCreateImage))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(bSave))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lLinesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))))
                    .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLoadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadImageActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser("C:");
            fileChooser.setFileFilter(
                    new FileNameExtensionFilter(
                            "Изображения (*.jpg, *.jpeg, *.png,*.bmp,*gif)",
                            "jpg", "jpeg", "png", "bmp", "gif"));
            fileChooser.showOpenDialog(this);
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                image = ImageIO.read(file);
                draw();
            }
        } catch (HeadlessException | IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Не удалось загрузить файл выбранный файл", "Ошибка",
                    JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_bLoadImageActionPerformed

    private void paneForDisplayImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForDisplayImageMouseClicked
        if (evt.getClickCount() == 2) {
            if (LINES.size() < 5) {
                LINES.add(new VerticalLine(evt.getX()));
                lLinesCount.setText("Линии: " + LINES.size() + " из 5");
            }
        }
        draw();
    }//GEN-LAST:event_paneForDisplayImageMouseClicked

    private void paneForDisplayImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForDisplayImageMousePressed
        paneForDisplayImage.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        for (VerticalLine verticalLine : LINES) {
            verticalLine.setSelected(evt.getX());
        }
        pressed = true;
        draw();
    }//GEN-LAST:event_paneForDisplayImageMousePressed

    private void paneForDisplayImageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForDisplayImageMouseReleased
        paneForDisplayImage.setCursor(Cursor.getDefaultCursor());
        for (VerticalLine verticalLine : LINES) {
            if (verticalLine.selected) {
                verticalLine.x = evt.getX();
            }
        }
        pressed = false;
        draw();
    }//GEN-LAST:event_paneForDisplayImageMouseReleased

    private void bDeleteSelectedLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteSelectedLineActionPerformed
        for (VerticalLine verticalLine : LINES) {
            if (verticalLine.selected) {
                LINES.remove(verticalLine);
                lLinesCount.setText("Линии: " + LINES.size() + " из 5");
                break;
            }
        }
        draw();
    }//GEN-LAST:event_bDeleteSelectedLineActionPerformed

    private void bChooseColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChooseColorActionPerformed
        lineColor = JColorChooser.showDialog(this,
                "Выбрать цвет линии", lineColor);
        draw();
    }//GEN-LAST:event_bChooseColorActionPerformed

    private void savePieceOfImage(double startX, double finishX) {
        int imgWidth = image.getWidth();
        int paneWidth = paneForDisplayImage.getWidth();
        startX /= paneWidth;
        finishX /= paneWidth;
        int ip1 = (int) (startX * imgWidth);
        int ip2 = (int) (finishX * imgWidth);
        BufferedImage piece = image.getSubimage(
                ip1, 0, ip2 - ip1, image.getHeight());
        try {
            File dir = new File("./images");
            if (!dir.exists()) {
                dir.mkdir();
            }
            String pictureName = "./images/image"
                    + System.currentTimeMillis() + ".png";
            File out = new File(pictureName);
            ImageIO.write(piece, "png", out);
            Kartinka kartinka = new Kartinka();
            kartinka.setImgLink(pictureName);
            kartinka.setDisciplinaIdDisciplina(
                    subjects.get(listSubjects.getSelectedIndex()));
            DBManager.writeObjectPersist(kartinka);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString(),
                    "Ошибка", JOptionPane.OK_OPTION);
        }
    }

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        if (image != null) {
            Collections.sort(LINES, (VerticalLine o1, VerticalLine o2)
                    -> Integer.compare(o1.x, o2.x));
            int startXPosition = 0;
            int finishXPosition = paneForDisplayImage.getWidth();
            double current = startXPosition;
            for (VerticalLine line : LINES) {
                double p1 = current;
                double p2 = line.x;
                savePieceOfImage(p1, p2);
                current = p2;
            }
            savePieceOfImage(current, finishXPosition);
            JOptionPane.showMessageDialog(this, "Сохранение завершено",
                    "Информация", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }

    }//GEN-LAST:event_bSaveActionPerformed

    private void paneForDisplayImageMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForDisplayImageMouseDragged
        if (pressed) {
            for (VerticalLine verticalLine : LINES) {
                if (verticalLine.selected) {
                    verticalLine.x = evt.getX();
                }
            }
            draw();
        }
    }//GEN-LAST:event_paneForDisplayImageMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChooseColor;
    private javax.swing.JButton bCreateImage;
    private javax.swing.JButton bDeleteSelectedLine;
    private javax.swing.JButton bLoadImage;
    private javax.swing.JButton bSave;
    private javax.swing.JLabel lLinesCount;
    private javax.swing.JLabel lSubject;
    private javax.swing.JList listSubjects;
    private javax.swing.JPanel paneForDisplayImage;
    private javax.swing.JScrollPane sPaneForListSubjects;
    // End of variables declaration//GEN-END:variables

}
