package forms;

import java.sql.*;
import entities.Prepodavatel;
import entities.Student;
import java.awt.event.KeyEvent;
import javax.persistence.TypedQuery;
import main.DialogManager;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author Solovenko
 *
 */
public class LoginForm extends javax.swing.JFrame {

    private Student student;
    private Prepodavatel prepodavatel;

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
    }

    /**
     * Проверка на подключение к БД
     *
     * @return true - если подключение корректно false - если подключиться к БД
     * не удалось
     */
    private boolean checkAccessToDB() {
        boolean result = true;
        try {
            Class.forName("java.sql.Driver");
            //Создаём соединение
            Connection con = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb", "root", "ytrewq");
        } catch (ClassNotFoundException | SQLException ex) {
            DialogManager.notify("Ошибка", "Не удалось подключиться к базе данных! "
                    + "\nПроверьте, запущен ли сервер базы данных MySQL "
                    + "\nили обратитесь к администратору программы",
                    DialogManager.TypeOfMessage.ERROR);
            result = false;
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lUsername = new javax.swing.JLabel();
        lPassword = new javax.swing.JLabel();
        bEnter = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        textUsername = new javax.swing.JTextField();
        passwordText = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Вход в систему");
        setResizable(false);

        lUsername.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lUsername.setText("Имя пользователя:");

        lPassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lPassword.setText("Пароль:");

        bEnter.setText("Войти");
        bEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEnterActionPerformed(evt);
            }
        });

        bExit.setText("Закрыть");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        textUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textUsernameKeyPressed(evt);
            }
        });

        passwordText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordTextKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textUsername)
                    .addComponent(passwordText)
                    .addComponent(bExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lUsername)
                    .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPassword)
                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bExit)
                    .addComponent(bEnter))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEnterActionPerformed
        String username = textUsername.getText();
        String password = String.valueOf(passwordText.getPassword());

        if (checkAccessToDB()) {
            try {
                //Ищем в преподавателях
                TypedQuery<Prepodavatel> queryForPrepodavatel = entityManager.
                        createQuery("SELECT p FROM Prepodavatel p "
                                + "WHERE p.login = :login",
                                Prepodavatel.class);
                queryForPrepodavatel.setParameter("login", username);
                prepodavatel = queryForPrepodavatel.getSingleResult();

                if (prepodavatel != null) {
                    if (password.equals(prepodavatel.getPassword())) {
                        //Преподавателя выводим на главную форму
                        MainForm mainForm = new MainForm();
                        mainForm.setVisible(true);
                        this.dispose();
                    } else {
                        DialogManager.notify("Ошибка",
                                "Неверно введён пароль!",
                                DialogManager.TypeOfMessage.ERROR);
                    }
                }
            } catch (Exception ex) {
                try {
                    //Ищем в студентах
                    TypedQuery<Student> queryForStudent = entityManager.
                            createQuery("SELECT s FROM Student s "
                                    + "WHERE s.login = :login",
                                    Student.class);
                    queryForStudent.setParameter("login", username);
                    student = queryForStudent.getSingleResult();

                    if (student != null) {
                        if (password.equals(student.getPassword())) {
                            //Отображаем форму прохождения тестов для студента
                            ChooseTestForm chooseTestForm = new ChooseTestForm(this, true);
                            chooseTestForm.setStudent(student);
                            chooseTestForm.setVisible(true);
                            this.dispose();
                        } else {
                            DialogManager.notify("Ошибка",
                                    "Неверно введён пароль!",
                                    DialogManager.TypeOfMessage.ERROR);
                        }
                    }
                } catch (Exception e) {
                    DialogManager.notify("Ошибка",
                            "Такого пользователя не существует!",
                            DialogManager.TypeOfMessage.ERROR);
                }
            }
        }

    }//GEN-LAST:event_bEnterActionPerformed

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed

        this.dispose();

    }//GEN-LAST:event_bExitActionPerformed

    private void textUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            bEnterActionPerformed(null);
        }
    }//GEN-LAST:event_textUsernameKeyPressed

    private void passwordTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            bEnterActionPerformed(null);
        }
    }//GEN-LAST:event_passwordTextKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEnter;
    private javax.swing.JButton bExit;
    private javax.swing.JLabel lPassword;
    private javax.swing.JLabel lUsername;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables

}
