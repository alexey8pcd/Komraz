package forms;

import entities.Disciplina;
import entities.Kartinka;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseEvent;
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
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
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
    private Color objectColor = Color.BLACK;
    private boolean pressed;
    private List<Disciplina> subjects;
    private final int OBJECTS_MAX_AMOUNT = 10;
    private final List<PickArea> AREAS;
    private final ListModel SUBJECTS_LIST_MODEL
            = new AbstractListModel() {

                @Override
                public int getSize() {
                    return subjects.size();
                }

                @Override
                public Object getElementAt(int index) {
                    return subjects.get(index).getNazvanie();
                }
            };
    private WorkMode mode;
    private Point startMousePoint;
    private PickArea selectedArea;
    private double scale;

    private enum WorkMode {

        AREAS,
        LINES
    }

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
            g.setColor(objectColor);
            g.drawLine(x, 0, x, paneForDisplayImage.getHeight());
        }
    }

    private class PickArea {

        public int x;
        public int y;
        public int width;
        public int height;
        private boolean selected;
        public static final int MIN_SIZE = 5;

        public boolean isContainPoint(Point point) {
            return point.x >= x
                    && point.x < x + width
                    && point.y >= y
                    && point.y < y + height;
        }

        public void translate(int deltaX, int deltaY) {
            this.x += deltaX;
            this.y += deltaY;
        }

        public void draw(Graphics g) {
            if (selected) {
                g.setColor(Color.BLUE);
                g.drawRect(x - 1, y - 1, width + 1, height + 1);
            }
            Color color = new Color(objectColor.getRed(), objectColor.getGreen(),
                    objectColor.getBlue(), 128);
            g.setColor(color);
            g.fillRect(x, y, width, height);

        }

        public PickArea(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        private void setSelected(boolean selected) {
            this.selected = selected;
        }

        private void enlarge() {
            int valueWidth = width / 5;
            int valueHeight = height / 5;
            this.x -= valueWidth / 2;
            this.width += valueWidth;
            this.y -= valueHeight / 2;
            this.height += valueHeight;
        }

        private void reduceSize() {
            if (width > MIN_SIZE && height > MIN_SIZE) {
                int valueWidth = width / 5;
                int valueHeight = height / 5;
                this.x += valueWidth / 2;
                this.width -= valueWidth;
                this.y += valueHeight / 2;
                this.height -= valueHeight;
            }
        }
    }

    public CutImageForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        GRAPHICS = paneForDisplayImage.getGraphics();
        LINES = new ArrayList<>();
        lObjectsCount.setText("Линий: " + LINES.size()
                + " из " + OBJECTS_MAX_AMOUNT);
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
        AREAS = new ArrayList<>();
        mode = WorkMode.LINES;
        bEnlargeOfArea.setVisible(false);
        bReduceSizeOfArea.setVisible(false);
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
            GRAPHICS.drawImage(image, 0, 0, (int) (image.getWidth() * scale),
                    (int) (image.getHeight() * scale), null);
        }
        if (mode == WorkMode.LINES) {
            for (VerticalLine verticalLine : LINES) {
                verticalLine.draw(GRAPHICS);
            }
        } else {
            for (PickArea area : AREAS) {
                area.draw(GRAPHICS);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        paneForDisplayImage = new javax.swing.JPanel();
        bSave = new javax.swing.JButton();
        lObjectsCount = new javax.swing.JLabel();
        bLoadImage = new javax.swing.JButton();
        bDeleteLineOrAreas = new javax.swing.JButton();
        bChooseColor = new javax.swing.JButton();
        lSubject = new javax.swing.JLabel();
        sPaneForListSubjects = new javax.swing.JScrollPane();
        listSubjects = new javax.swing.JList();
        lCutType = new javax.swing.JLabel();
        rbSplitLines = new javax.swing.JRadioButton();
        rbPickOutAreas = new javax.swing.JRadioButton();
        bReduceSizeOfArea = new javax.swing.JButton();
        bEnlargeOfArea = new javax.swing.JButton();
        bHelp = new javax.swing.JButton();
        bSave1 = new javax.swing.JButton();

        setTitle("Разрезание картинок");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        paneForDisplayImage.setBackground(new java.awt.Color(204, 204, 204));
        paneForDisplayImage.setToolTipText("");
        paneForDisplayImage.setFocusCycleRoot(true);
        paneForDisplayImage.setPreferredSize(new java.awt.Dimension(880, 510));
        paneForDisplayImage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                paneForDisplayImageMouseDragged(evt);
            }
        });
        paneForDisplayImage.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                paneForDisplayImageMouseWheelMoved(evt);
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
            .addGap(0, 430, Short.MAX_VALUE)
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

        bDeleteLineOrAreas.setText("Удалить выбранную линию");
        bDeleteLineOrAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteLineOrAreasActionPerformed(evt);
            }
        });

        bChooseColor.setText("Изменить цвет линии/области");
        bChooseColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChooseColorActionPerformed(evt);
            }
        });

        lSubject.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lSubject.setText("Дисциплина:");

        listSubjects.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sPaneForListSubjects.setViewportView(listSubjects);

        lCutType.setText("Режим разрезания:");

        buttonGroup1.add(rbSplitLines);
        rbSplitLines.setSelected(true);
        rbSplitLines.setText("Разделяющие линии");
        rbSplitLines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSplitLinesActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbPickOutAreas);
        rbPickOutAreas.setText("Области выделения");
        rbPickOutAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPickOutAreasActionPerformed(evt);
            }
        });

        bReduceSizeOfArea.setText("-");
        bReduceSizeOfArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReduceSizeOfAreaActionPerformed(evt);
            }
        });

        bEnlargeOfArea.setText("+");
        bEnlargeOfArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEnlargeOfAreaActionPerformed(evt);
            }
        });

        bHelp.setText("Помощь");
        bHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHelpActionPerformed(evt);
            }
        });

        bSave1.setText("Закрыть");
        bSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSave1ActionPerformed(evt);
            }
        });

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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bLoadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lCutType)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbPickOutAreas)
                                    .addComponent(rbSplitLines)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bReduceSizeOfArea)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bEnlargeOfArea)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(lObjectsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bChooseColor, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bDeleteLineOrAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bSave1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneForDisplayImage, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lSubject)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bLoadImage)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbSplitLines)
                            .addComponent(lCutType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbPickOutAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(lObjectsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bReduceSizeOfArea, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bEnlargeOfArea, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sPaneForListSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bDeleteLineOrAreas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bChooseColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSave)
                            .addComponent(bSave1))))
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
                scale = Math.min(
                        (double) paneForDisplayImage.getWidth() / image.getWidth(),
                        (double) paneForDisplayImage.getHeight() / image.getHeight());
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
            if (mode == WorkMode.LINES) {
                if (LINES.size() < OBJECTS_MAX_AMOUNT) {
                    LINES.add(new VerticalLine(evt.getX()));
                    lObjectsCount.setText("Линии: " + LINES.size()
                            + " из " + OBJECTS_MAX_AMOUNT);
                }
            }
        }
        draw();
    }//GEN-LAST:event_paneForDisplayImageMouseClicked

    private void paneForDisplayImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForDisplayImageMousePressed
        if (mode == WorkMode.LINES) {
            paneForDisplayImage.setCursor(Cursor.getPredefinedCursor(
                    Cursor.W_RESIZE_CURSOR));
            for (VerticalLine verticalLine : LINES) {
                verticalLine.setSelected(evt.getX());
            }
        } else {
            if (evt.getButton() == MouseEvent.BUTTON1) {
                paneForDisplayImage.setCursor(Cursor.getPredefinedCursor(
                        Cursor.MOVE_CURSOR));
                for (PickArea area : AREAS) {
                    area.setSelected(false);
                    selectedArea = null;
                }
                for (PickArea area : AREAS) {
                    if (area.isContainPoint(evt.getPoint())) {
                        selectedArea = area;
                        area.setSelected(true);
                        break;
                    }
                }
            }
            startMousePoint = evt.getPoint();
        }
        pressed = true;
        draw();
    }//GEN-LAST:event_paneForDisplayImageMousePressed

    private void paneForDisplayImageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForDisplayImageMouseReleased
        paneForDisplayImage.setCursor(Cursor.getDefaultCursor());
        if (mode == WorkMode.LINES) {
            for (VerticalLine verticalLine : LINES) {
                if (verticalLine.selected) {
                    verticalLine.x = evt.getX();
                }
            }
            lObjectsCount.setText("Линий: " + LINES.size() + " из "
                    + OBJECTS_MAX_AMOUNT);
        } else {
            int deltaX = evt.getX() - startMousePoint.x;
            int deltaY = evt.getY() - startMousePoint.y;
            if (evt.getButton() == MouseEvent.BUTTON1) {
                for (PickArea area : AREAS) {
                    if (area.isContainPoint(startMousePoint)) {
                        area.translate(deltaX, deltaY);
                        break;
                    }
                }
            } else if (evt.getButton() == MouseEvent.BUTTON3) {
                if (AREAS.size() < OBJECTS_MAX_AMOUNT
                        && Math.abs(deltaX) >= PickArea.MIN_SIZE
                        && Math.abs(deltaY) >= PickArea.MIN_SIZE) {
                    int startX, startY;
                    if (deltaX > 0) {
                        startX = startMousePoint.x;
                    } else {
                        startX = evt.getX();
                    }
                    if (deltaY > 0) {
                        startY = startMousePoint.y;
                    } else {
                        startY = evt.getY();
                    }
                    AREAS.add(new PickArea(startX, startY,
                            Math.abs(deltaX), Math.abs(deltaY)));
                }
            }
            lObjectsCount.setText("Областей: " + AREAS.size()
                    + " из " + OBJECTS_MAX_AMOUNT);
        }
        pressed = false;
        draw();
    }//GEN-LAST:event_paneForDisplayImageMouseReleased

    private void bDeleteLineOrAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteLineOrAreasActionPerformed
        if (mode == WorkMode.LINES) {
            for (VerticalLine verticalLine : LINES) {
                if (verticalLine.selected) {
                    LINES.remove(verticalLine);
                    lObjectsCount.setText("Линии: " + LINES.size()
                            + " из " + OBJECTS_MAX_AMOUNT);
                    break;
                }
            }
        } else {
            for (PickArea area : AREAS) {
                if (area.selected) {
                    AREAS.remove(area);
                    lObjectsCount.setText("Областей: " + AREAS.size()
                            + " из " + OBJECTS_MAX_AMOUNT);
                    break;
                }
            }
        }
        draw();
    }//GEN-LAST:event_bDeleteLineOrAreasActionPerformed

    private void bChooseColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChooseColorActionPerformed
        objectColor = JColorChooser.showDialog(this,
                "Выбрать цвет линии или области", objectColor);
        draw();
    }//GEN-LAST:event_bChooseColorActionPerformed

    private void savePieceOfImage(double startX, double finishX) {
        int ip1 = (int) (startX / scale);
        int ip2 = (int) (finishX / scale);
        saveImage(image.getSubimage(ip1, 0, ip2 - ip1, image.getHeight()));
    }

    private void saveImage(BufferedImage image) {
        try {
            File dir = new File("./images");
            if (!dir.exists()) {
                dir.mkdir();
            }
            String pictureName = "./images/image"
                    + System.currentTimeMillis() + ".png";
            File out = new File(pictureName);
            ImageIO.write(image, "png", out);
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
            if (mode == WorkMode.LINES) {
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
            } else {
                for (PickArea area : AREAS) {
                    int imgX = Math.max((int) (area.x / scale), 0);
                    int imgWidth = (int) (area.width / scale);
                    int imgY = Math.max((int) (area.y / scale), 0);
                    int imgHeight = (int) (area.height / scale);
                    if (imgX + imgWidth > image.getWidth()) {
                        imgWidth = image.getWidth() - imgX - 1;
                    }
                    if (imgY + imgHeight > image.getHeight()) {
                        imgHeight = image.getHeight() - imgY - 1;
                    }
                    saveImage(image.getSubimage(imgX, imgY,
                            imgWidth, imgHeight));
                }
            }
            JOptionPane.showMessageDialog(this, "Сохранение завершено",
                    "Информация", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }

    }//GEN-LAST:event_bSaveActionPerformed


    private void paneForDisplayImageMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForDisplayImageMouseDragged
        if (pressed) {
            if (mode == WorkMode.LINES) {
                for (VerticalLine verticalLine : LINES) {
                    if (verticalLine.selected) {
                        verticalLine.x = evt.getX();
                    }
                }
                draw();
            }
        }
    }//GEN-LAST:event_paneForDisplayImageMouseDragged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (DialogManager.confirmDeleting("Разрезание картинки не сохранено. "
                + "\nВы действительно хотите выйти из редактора?")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void rbPickOutAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPickOutAreasActionPerformed
        mode = WorkMode.AREAS;
        bDeleteLineOrAreas.setText("Удалить выбранную область");
        lObjectsCount.setText("Областей: " + AREAS.size()
                + " из " + OBJECTS_MAX_AMOUNT);
        bReduceSizeOfArea.setVisible(true);
        bEnlargeOfArea.setVisible(true);
        draw();
    }//GEN-LAST:event_rbPickOutAreasActionPerformed

    private void rbSplitLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSplitLinesActionPerformed
        mode = WorkMode.LINES;
        bDeleteLineOrAreas.setText("Удалить выбранную линию");
        lObjectsCount.setText("Линий: " + LINES.size() + " из "
                + OBJECTS_MAX_AMOUNT);
        bReduceSizeOfArea.setVisible(false);
        bEnlargeOfArea.setVisible(false);
        draw();
    }//GEN-LAST:event_rbSplitLinesActionPerformed

    private void paneForDisplayImageMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_paneForDisplayImageMouseWheelMoved
        if (mode == WorkMode.AREAS) {
            if (selectedArea != null) {
                if (evt.getWheelRotation() < 0) {
                    selectedArea.enlarge();
                } else {
                    selectedArea.reduceSize();
                }
                draw();
            }
        }
    }//GEN-LAST:event_paneForDisplayImageMouseWheelMoved

    private void bEnlargeOfAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEnlargeOfAreaActionPerformed
        if (selectedArea != null) {
            selectedArea.enlarge();
            draw();
        }
    }//GEN-LAST:event_bEnlargeOfAreaActionPerformed

    private void bReduceSizeOfAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReduceSizeOfAreaActionPerformed
        if (selectedArea != null) {
            selectedArea.reduceSize();
            draw();
        }
    }//GEN-LAST:event_bReduceSizeOfAreaActionPerformed

    private void bHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHelpActionPerformed
        DialogManager.notify("Помощь",
                "<html>Для добавление линии дважды щелкните левой на рабочей области."
                + "<br>Для добавления области выделения нажмите и"
                + "<br>перетаскивайте правую кнопку мыши. Для выделения области или"
                + "<br>линии нажмите на ней левой кнопкой мыши. Для перемещения области"
                + "<br>или линии выделите ее и перемещайте мышь при зажатой левой кнопке."
                + "<br>Для увеличения или уменьшения размера выделенной области "
                + "<br>воспользуйтесь колесиком мыши или нажимайте кнопки - и +",
                DialogManager.TypeOfMessage.INFORMATION);
    }//GEN-LAST:event_bHelpActionPerformed

    private void bSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSave1ActionPerformed
        if (DialogManager.confirmDeleting("Разрезание картинки не сохранено. "
                + "\nВы действительно хотите выйти из редактора?")) {
            dispose();
        }
    }//GEN-LAST:event_bSave1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChooseColor;
    private javax.swing.JButton bDeleteLineOrAreas;
    private javax.swing.JButton bEnlargeOfArea;
    private javax.swing.JButton bHelp;
    private javax.swing.JButton bLoadImage;
    private javax.swing.JButton bReduceSizeOfArea;
    private javax.swing.JButton bSave;
    private javax.swing.JButton bSave1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lCutType;
    private javax.swing.JLabel lObjectsCount;
    private javax.swing.JLabel lSubject;
    private javax.swing.JList listSubjects;
    private javax.swing.JPanel paneForDisplayImage;
    private javax.swing.JRadioButton rbPickOutAreas;
    private javax.swing.JRadioButton rbSplitLines;
    private javax.swing.JScrollPane sPaneForListSubjects;
    // End of variables declaration//GEN-END:variables

}
