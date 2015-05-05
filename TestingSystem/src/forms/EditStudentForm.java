package forms;

import entities.Gruppa;
import entities.Prepodavatel;
import entities.Student;
import java.util.List;
import javax.swing.JOptionPane;
import static resources.Parameters.SCREEN_SIZE;
import sql.DBManager;
import static sql.DBManager.entityManager;

/**
 *
 * @author Артем
 */
public class EditStudentForm extends javax.swing.JDialog {

    public static final int MIN_USER_FIO_LENGTH = 5;
    public static final int MIN_USER_NAME_LENGTH = 5;
    public static final int MIN_USER_PASSWORD_LENGTH = 3;

    private Student student;
    private Gruppa group;

    public EditStudentForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
    }

    public void setStudent(Student student) {
        this.student = student;
        textFio.setText(student.getFio());
        textUsername.setText(student.getLogin());
        textPassword.setText(student.getPassword());
        textPasswordConfirm.setText(student.getPassword());
    }

    public void setGroup(Gruppa group) {
        this.group = group;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lUsername = new javax.swing.JLabel();
        lPassword = new javax.swing.JLabel();
        lPasswordConfirm = new javax.swing.JLabel();
        textUsername = new javax.swing.JTextField();
        textPassword = new javax.swing.JTextField();
        textPasswordConfirm = new javax.swing.JTextField();
        bSave = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        lFio = new javax.swing.JLabel();
        textFio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Новый студент");

        lUsername.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lUsername.setText("Имя пользователя:");

        lPassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lPassword.setText("Пароль:");

        lPasswordConfirm.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lPasswordConfirm.setText("Подтверждение пароля:");

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

        lFio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lFio.setText("ФИО студента:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lPasswordConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lFio, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)))
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lFio)
                    .addComponent(textFio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lUsername)
                    .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPassword)
                    .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPasswordConfirm)
                    .addComponent(textPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave)
                    .addComponent(bClose))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed

        this.dispose();

    }//GEN-LAST:event_bCloseActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        String fio = textFio.getText();
        String username = textUsername.getText();
        String password = textPassword.getText();

        boolean correctFio = true;
        if (fio.isEmpty() || username.length() < MIN_USER_FIO_LENGTH) {
            correctFio = false;
        }
        if (correctFio) {
            boolean correctLogin = true;
            if (username.isEmpty() || username.length() < MIN_USER_NAME_LENGTH) {
                correctLogin = false;
            }
            if (correctLogin) {

                boolean confirmPass = true;
                if (password.isEmpty()
                        || password.length() < MIN_USER_PASSWORD_LENGTH) {
                    confirmPass = false;
                }
                if (confirmPass) {
                    if (password.equals(textPasswordConfirm.getText())) {

                        boolean existed = false;

                        List<Prepodavatel> prepodavatels = entityManager.createNamedQuery(
                                "Prepodavatel.findAll", Prepodavatel.class).getResultList();
                        for (Prepodavatel prepodavatel1 : prepodavatels) {
                            if (prepodavatel1.getLogin().equals(username)) {
                                existed = true;
                            }
                        }
                        if (!existed) {
                            List<Student> students = entityManager.createNamedQuery(
                                    "Student.findAll", Student.class).getResultList();
                            for (Student student1 : students) {
                                if (student1.getLogin().equals(username)) {
                                    existed = true;
                                }
                            }
                        }

                        if (!existed) {

                            if (student == null) {
                                student = new Student();
                                student.setLogin(username);
                                student.setFio(fio);
                                student.setPassword(password);
                                student.setGruppaIdGruppa(group);

                                try {
                                    DBManager.writeObject(student);
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(this,
                                            ex.toString(),
                                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                //Редактируем существующего
                                student.setLogin(username);
                                student.setFio(fio);
                                student.setPassword(password);
                                try {
                                    DBManager.writeObject(student);
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(this,
                                            ex.toString(),
                                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "Такой пользователь уже существует!",
                                    "Предупреждение", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        //Неправильное подтверждение пароля
                        JOptionPane.showMessageDialog(this,
                                "Введённые пароли не соответствуют друг другу! Повторите",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Пароль не может быть пустым "
                            + "и длина пароля не короче "
                            + MIN_USER_PASSWORD_LENGTH + " символов",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Имя пользователя не может быть пустым "
                        + "и длина имени не короче "
                        + MIN_USER_NAME_LENGTH + " символов",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "ФИО пользователя не может быть пустым "
                    + "и длина ФИО не короче "
                    + MIN_USER_FIO_LENGTH + " символов",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bSave;
    private javax.swing.JLabel lFio;
    private javax.swing.JLabel lPassword;
    private javax.swing.JLabel lPasswordConfirm;
    private javax.swing.JLabel lUsername;
    private javax.swing.JTextField textFio;
    private javax.swing.JTextField textPassword;
    private javax.swing.JTextField textPasswordConfirm;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
