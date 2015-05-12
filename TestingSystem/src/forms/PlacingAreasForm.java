package forms;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import main.Area;
import main.DialogManager;

/**
 *
 * @author Алексей
 */
public class PlacingAreasForm extends javax.swing.JDialog {

    private final Graphics GRAPHICS;
    private int prefAreaSize = 100;
    private Area[][] areas;
    private Color areaColor = Color.GRAY;
    private int startX = 20;
    private int startY = 20;
    private int rowSpan = 20;
    private int columnSpan = 20;
    private Layout layout;

    public Area[][] getAreas() {
        return areas;
    }

    private enum Layout {

        MATRIX,
        LINE
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw();
    }

    public void draw() {
        GRAPHICS.clearRect(0, 0, paneForDisplayAreas.getWidth(),
                paneForDisplayAreas.getHeight());
        if (areas != null) {
            for (Area[] row : areas) {
                for (Area area : row) {
                    if (area != null) {
                        area.draw(GRAPHICS, areaColor, Color.WHITE);
                    }
                }
            }
        }
    }

    public PlacingAreasForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        GRAPHICS = paneForDisplayAreas.getGraphics();
        areas = new Area[2][3];
        layout = Layout.MATRIX;
        areas[0][0] = new Area(startX, startY, prefAreaSize);
        areas[0][0].number = 1;
        areas[0][1] = new Area(startX + prefAreaSize + rowSpan,
                startY, prefAreaSize);
        areas[0][1].number = 2;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupLayout = new javax.swing.ButtonGroup();
        paneForDisplayAreas = new javax.swing.JPanel();
        bClose = new javax.swing.JButton();
        bSave = new javax.swing.JButton();
        bAddArea = new javax.swing.JButton();
        bDeleteArea = new javax.swing.JButton();
        tbMatrixMode = new javax.swing.JToggleButton();
        tbLineMode = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Размещение областей");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        paneForDisplayAreas.setBackground(new java.awt.Color(204, 204, 204));
        paneForDisplayAreas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneForDisplayAreasMousePressed(evt);
            }
        });

        javax.swing.GroupLayout paneForDisplayAreasLayout = new javax.swing.GroupLayout(paneForDisplayAreas);
        paneForDisplayAreas.setLayout(paneForDisplayAreasLayout);
        paneForDisplayAreasLayout.setHorizontalGroup(
            paneForDisplayAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        paneForDisplayAreasLayout.setVerticalGroup(
            paneForDisplayAreasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

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

        bAddArea.setText("Добавить область");
        bAddArea.setToolTipText("");
        bAddArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddAreaActionPerformed(evt);
            }
        });

        bDeleteArea.setText("<html><center>Удалить выбранную область");
        bDeleteArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteAreaActionPerformed(evt);
            }
        });

        groupLayout.add(tbMatrixMode);
        tbMatrixMode.setSelected(true);
        tbMatrixMode.setText("<html><center>[-] [-] [-]<br> [-] [-] [-]");
        tbMatrixMode.setToolTipText("Матричное размещение");
        tbMatrixMode.setPreferredSize(new java.awt.Dimension(80, 60));
        tbMatrixMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbMatrixModeActionPerformed(evt);
            }
        });

        groupLayout.add(tbLineMode);
        tbLineMode.setText("<html><center>[-] ... [-]");
        tbLineMode.setToolTipText("Горизонтальное размещение");
        tbLineMode.setPreferredSize(new java.awt.Dimension(80, 60));
        tbLineMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbLineModeActionPerformed(evt);
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
                        .addComponent(paneForDisplayAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 20, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bDeleteArea, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(bAddArea, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tbLineMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tbMatrixMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bAddArea, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDeleteArea, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161)
                        .addComponent(tbMatrixMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tbLineMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paneForDisplayAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bClose)
                    .addComponent(bSave))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAddAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddAreaActionPerformed
        boolean placed = false;
        for (int i = 0; i < areas.length && !placed; i++) {
            for (int j = 0; j < areas[i].length && !placed; j++) {
                if (areas[i][j] == null) {
                    areas[i][j] = new Area(startX + j * (prefAreaSize + rowSpan),
                            startY + i * (prefAreaSize + columnSpan), prefAreaSize);
                    areas[i][j].number = i * areas[i].length + j + 1;
                    placed = true;
                }
            }
        }
        draw();
    }//GEN-LAST:event_bAddAreaActionPerformed

    private void paneForDisplayAreasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneForDisplayAreasMousePressed
        for (Area[] row : areas) {
            for (Area column : row) {
                if (column != null) {
                    column.setSelected(evt.getX(), evt.getY());
                }
            }
        }
        draw();
    }//GEN-LAST:event_paneForDisplayAreasMousePressed

    private void bDeleteAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteAreaActionPerformed
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                if (areas[i][j] != null) {
                    if (areas[i][j].selected) {
                        areas[i][j] = null;
                    }
                }
            }
        }
        draw();
    }//GEN-LAST:event_bDeleteAreaActionPerformed

    private void tbMatrixModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbMatrixModeActionPerformed
        if (layout == Layout.LINE) {
            Area[][] matrix = new Area[2][3];
            for (int i = 0; i < areas.length; i++) {
                for (int j = 0; j < areas[i].length; j++) {
                    matrix[j / 3][j % 3] = areas[i][j];
                }
            }
            layout = Layout.MATRIX;
            areas = matrix;
            prefAreaSize /= 3;
            prefAreaSize *= 4;
            for (int i = 0; i < areas.length; i++) {
                for (int j = 0; j < areas[i].length; j++) {
                    if (areas[i][j] != null) {
                        areas[i][j].x = startX + j * (prefAreaSize + rowSpan);
                        areas[i][j].y = startY + i * (prefAreaSize + columnSpan);
                        areas[i][j].size = prefAreaSize;
                    }
                }
            }
            draw();
        }
    }//GEN-LAST:event_tbMatrixModeActionPerformed

    private void tbLineModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbLineModeActionPerformed
        if (layout == Layout.MATRIX) {
            Area[][] line = new Area[1][6];
            for (int i = 0; i < areas.length; i++) {
                for (int j = 0; j < areas[i].length; j++) {
                    line[0][i * 3 + j] = areas[i][j];
                }
            }
            layout = Layout.LINE;
            areas = line;
            prefAreaSize /= 4;
            prefAreaSize *= 3;
            for (int i = 0; i < areas[0].length; i++) {
                if (areas[0][i] != null) {
                    areas[0][i].x = startX + i * (prefAreaSize + rowSpan);
                    areas[0][i].y = startY;
                    areas[0][i].size = prefAreaSize;
                }
            }
            draw();
        }
    }//GEN-LAST:event_tbLineModeActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        dispose();
    }//GEN-LAST:event_bSaveActionPerformed

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        if (DialogManager.confirmClosingForm("вопроса")) {
            dispose();
        }
    }//GEN-LAST:event_bCloseActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (DialogManager.confirmClosingForm("вопроса")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddArea;
    private javax.swing.JButton bClose;
    private javax.swing.JButton bDeleteArea;
    private javax.swing.JButton bSave;
    private javax.swing.ButtonGroup groupLayout;
    private javax.swing.JPanel paneForDisplayAreas;
    private javax.swing.JToggleButton tbLineMode;
    private javax.swing.JToggleButton tbMatrixMode;
    // End of variables declaration//GEN-END:variables

}
