package forms;

import entities.Disciplina;
import java.util.List;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import sql.DBManager;
import static sql.DBManager.entityManager;

/**
 *
 * @author Артем
 */
public class EditSubjectForm extends javax.swing.JDialog {

    public static final int MIN_SUBJECT_NAME_LENGTH = 3;
    
    private Disciplina subject;

    public EditSubjectForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
    }
    
    public void setDisciplina(Disciplina subject) {
        this.subject = subject;
        textDisciplinaName.setText(subject.getNazvanie());
        this.setTitle("Редактирование дисциплины");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bSave = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        lDisciplinaName = new javax.swing.JLabel();
        textDisciplinaName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Новая дисциплина");

        bSave.setText("Сохранить");
        bSave.setPreferredSize(new java.awt.Dimension(87, 23));
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        bClose.setText("Закрыть");
        bClose.setPreferredSize(new java.awt.Dimension(87, 23));
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        lDisciplinaName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lDisciplinaName.setText("Наименование дисциплины:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(lDisciplinaName, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textDisciplinaName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textDisciplinaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDisciplinaName))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCloseActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        String disciplinaName = textDisciplinaName.getText();

        boolean correct = true;
        if (disciplinaName.isEmpty()
                || disciplinaName.length() < MIN_SUBJECT_NAME_LENGTH) {
            correct = false;
        }
        if (correct) {
            boolean existed = false;
            List<Disciplina> subjects = entityManager.createNamedQuery(
                    "Disciplina.findAll", Disciplina.class).getResultList();
            for (Disciplina subject1 : subjects) {
                if (subject1.getNazvanie().equals(disciplinaName)) {
                    existed = true;
                }
            }
            if (!existed) {

                if (subject == null) {
                    subject = new Disciplina();
                    subject.setNazvanie(disciplinaName);
                    try {
                        DBManager.writeObjectMerge(subject);
                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }
                } else {
                    subject.setNazvanie(disciplinaName);
                    try {
                        DBManager.writeObjectMerge(subject);
                    } catch (Exception ex) {
                        DialogManager.errorMessage(ex);
                    }
                }
                this.dispose();
            } else {
                DialogManager.notify("Предупреждение",
                        "Такая дисциплина уже существует!",
                        DialogManager.TypeOfMessage.WARNING);

            }
        } else {
            DialogManager.notify("Ошибка",
                    "Название не может быть пустым "
                    + "и длина имени не короче "
                    + MIN_SUBJECT_NAME_LENGTH + " символов",
                    DialogManager.TypeOfMessage.ERROR);
        }

    }//GEN-LAST:event_bSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bSave;
    private javax.swing.JLabel lDisciplinaName;
    private javax.swing.JTextField textDisciplinaName;
    // End of variables declaration//GEN-END:variables

}
