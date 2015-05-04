package main;

import forms.LoginForm;
import forms.MainForm;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        /*
         * UIManager исправления для JOptionPane
         */
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        /*
        * Для создания пользователя временно расскоментируйте главную форму и 
        * там создавайте юзеров :)
        * 
        * По умолчанию, в fetchScript'e уже есть 3 пользователя, смотрите там.
        */
        new LoginForm().setVisible(true);
//        new MainForm().setVisible(true); 
    }

}
