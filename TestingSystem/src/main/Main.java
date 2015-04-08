package main;

import forms.MainForm;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        new MainForm().setVisible(true);
    }

}
