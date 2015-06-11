package forms;

import entities.Gruppa;
import entities.Prepodavatel;
import entities.Student;
import entities.Vopros;
import java.util.List;
import main.DialogManager;
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

    private List<Gruppa> groupList;

    private Student student;
    private Gruppa group;

    public EditStudentForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
        groupList = entityManager.createNamedQuery("Gruppa.findAll",
                Gruppa.class).getResultList();
        for (Gruppa iteratedGroup : groupList) {
            comboGroupChoose.addItem(iteratedGroup.getNazvanie());
        }
    }

    public void setStudent(Student student) {
        this.student = student;
        textFio.setText(student.getFio());
        textUsername.setText(student.getLogin());
        textPassword.setText(student.getPassword());
        textPasswordConfirm.setText(student.getPassword());

        this.setTitle("Редактирование студента");
    }

    public void setGroup(Gruppa group) {
        this.group = group;
        comboGroupChoose.setSelectedItem(group.getNazvanie());
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
        lGroupChoose = new javax.swing.JLabel();
        comboGroupChoose = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Новый студент");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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

        lGroupChoose.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lGroupChoose.setText("Группа:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(bClose, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lPasswordConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lFio, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lGroupChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(textUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(textFio, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(textPasswordConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(comboGroupChoose, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(53, 53, 53))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lFio)
                    .addComponent(textFio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lUsername)
                    .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPasswordConfirm)
                    .addComponent(textPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lGroupChoose)
                    .addComponent(comboGroupChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSave)
                    .addComponent(bClose))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed

        if (DialogManager.confirmClosingForm("студента")) {
            dispose();
        }

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
                        int idStudent = -1; //Проверка на редактируемого студента
                        if (student != null) {
                            idStudent = student.getIdStudent();
                        }

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
                                    if (student1.getIdStudent() != idStudent) {
                                        existed = true;
                                    }
                                }
                            }
                        }

                        if (!existed) {

                            if (student == null) {
                                student = new Student();
                                student.setLogin(username);
                                student.setFio(fio);
                                student.setPassword(password);
                                //Добавление группы
//                                student.setGruppaIdGruppa(group); 
                                Gruppa tempGroup = groupList.get(comboGroupChoose.getSelectedIndex());
                                student.setGruppaIdGruppa(tempGroup);

                                try {
                                    DBManager.writeObjectPersist(student);
                                } catch (Exception ex) {
                                    DialogManager.errorMessage(ex);
                                }
                            } else {
                                //Редактируем существующего
                                student.setLogin(username);
                                student.setFio(fio);
                                student.setPassword(password);
                                //Добавить добавление в группу
                                Gruppa tempGroup = groupList.get(comboGroupChoose.getSelectedIndex());
                                student.setGruppaIdGruppa(tempGroup);
                                try {
                                    DBManager.writeObjectMerge(student);
                                } catch (Exception ex) {
                                    DialogManager.errorMessage(ex);
                                }
                            }
                            this.dispose();
                        } else {
                            DialogManager.notify("Предупреждение",
                                    "Такой пользователь уже существует!",
                                    DialogManager.TypeOfMessage.WARNING);
                        }
                    } else {
                        //Неправильное подтверждение пароля
                        DialogManager.notify("Ошибка",
                                "Введённые пароли не соответствуют друг другу! Повторите ввод",
                                DialogManager.TypeOfMessage.ERROR);
                    }
                } else {
                    DialogManager.notify("Ошибка",
                            "Пароль не может быть пустым и длина пароля не короче "
                            + MIN_USER_PASSWORD_LENGTH + " символов",
                            DialogManager.TypeOfMessage.ERROR);
                }
            } else {
                DialogManager.notify("Ошибка",
                        "Имя пользователя не может быть пустым "
                        + "и длина имени не короче " + MIN_USER_NAME_LENGTH + " символов",
                        DialogManager.TypeOfMessage.ERROR);
            }
        } else {
            DialogManager.notify("Ошибка",
                    "ФИО пользователя не может быть пустым и длина ФИО не короче "
                    + MIN_USER_FIO_LENGTH + " символов",
                    DialogManager.TypeOfMessage.ERROR);
        }

    }//GEN-LAST:event_bSaveActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        if (DialogManager.confirmClosingForm("студента")) {
            dispose();
        }

    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox comboGroupChoose;
    private javax.swing.JLabel lFio;
    private javax.swing.JLabel lGroupChoose;
    private javax.swing.JLabel lPassword;
    private javax.swing.JLabel lPasswordConfirm;
    private javax.swing.JLabel lUsername;
    private javax.swing.JTextField textFio;
    private javax.swing.JTextField textPassword;
    private javax.swing.JTextField textPasswordConfirm;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
