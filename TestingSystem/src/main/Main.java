package main;

import entities.Student;
import forms.MainForm;
import javax.swing.UIManager;
import static sql.DBManager.entityManager;

public class Main {

    public static void main(String[] args) {
        /*
         * UIManager исправления для JOptionPane
         */
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        
        new MainForm().setVisible(true);
    }

}
