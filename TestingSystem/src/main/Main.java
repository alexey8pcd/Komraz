package main;

import javax.swing.UIManager;

public class Main {
    
    public static void main(String[] args) {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
    }

}
