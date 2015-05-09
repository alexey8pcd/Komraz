package forms;

import entities.Student;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import static resources.Parameters.SCREEN_SIZE;
import static sql.DBManager.entityManager;

/**
 *
 * @author Артем
 * 
 * Пасхалка для девелоперов:
 * Тестовые логин: "admin" и пароль: "1"
 * 
 */
public class LoginForm extends javax.swing.JFrame {

    private Student student;

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        this.setLocation(SCREEN_SIZE.width / 2 - this.getWidth() / 2,
                SCREEN_SIZE.height / 2 - this.getHeight() / 2);
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
        setTitle("Вход");

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

        textUsername.setText("solovenko");

        passwordText.setText("12345");

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

        if (username.equals("admin") && password.equals("1")) {
            MainForm mainForm = new MainForm();
            mainForm.setVisible(true);
            this.dispose();
        } else {
            try {
                TypedQuery<Student> queryForStudent = entityManager.
                        createQuery("SELECT s FROM Student s "
                                + "WHERE s.login = :login",
                                Student.class);
                queryForStudent.setParameter("login", username);
                student = queryForStudent.getSingleResult();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Такого пользователя не существует!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

            if (student != null) {
                if (password.equals(student.getPassword())) {
                    MainForm mainForm = new MainForm();
                    mainForm.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Неверно введён пароль!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }//GEN-LAST:event_bEnterActionPerformed

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed

        this.dispose();

    }//GEN-LAST:event_bExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEnter;
    private javax.swing.JButton bExit;
    private javax.swing.JLabel lPassword;
    private javax.swing.JLabel lUsername;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables

}
