package forms;

import entities.Gruppa;
import java.util.List;
import javax.swing.JOptionPane;
import static resources.Parameters.SCREEN_SIZE;
import sql.DBManager;
import static sql.DBManager.entityManager;

/**
 *
 * @author Артем
 */
public class EditGroupForm extends javax.swing.JDialog {

    public static final int MIN_GROUP_NAME_LENGTH = 3;

    private Gruppa group;

    public EditGroupForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
    }

    public void setGroup(Gruppa group) {
        this.group = group;
        textGroupName.setText(group.getNazvanie());
        this.setTitle("Редактирование группы");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lGroupName = new javax.swing.JLabel();
        textGroupName = new javax.swing.JTextField();
        bSave = new javax.swing.JButton();
        bClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Новая группа");

        lGroupName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lGroupName.setText("Название группы:");

        bSave.setText("Сохранить");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        bClose.setText("Закрыть");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave)
                    .addComponent(bClose))
                .addGap(75, 75, 75))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed

        this.dispose();

    }//GEN-LAST:event_bCloseActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed

        String groupName = textGroupName.getText();

        boolean correct = true;
        if (groupName.isEmpty()
                || groupName.length() < MIN_GROUP_NAME_LENGTH) {
            correct = false;
        }
        if (correct) {
            boolean existed = false;
            List<Gruppa> groups = entityManager.createNamedQuery(
                    "Gruppa.findAll", Gruppa.class).getResultList();
            for (Gruppa group1 : groups) {
                if (group1.getNazvanie().equals(groupName)) {
                    existed = true;
                }
            }
            if (!existed) {

                if (group == null) {
                    group = new Gruppa();
                    group.setNazvanie(groupName);
                    try {
                        DBManager.writeObjectMerge(group);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                                ex.toString(),
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    group.setNazvanie(groupName);
                    try {
                        DBManager.writeObjectMerge(group);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                                ex.toString(),
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Такая группа уже существует!",
                        "Предупреждение", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Название не может быть пустым "
                    + "и длина имени не короче "
                    + MIN_GROUP_NAME_LENGTH + " символов",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bSave;
    private javax.swing.JLabel lGroupName;
    private javax.swing.JTextField textGroupName;
    // End of variables declaration//GEN-END:variables
}
