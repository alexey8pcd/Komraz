package main;

import forms.MainForm;
import forms.PassageTestForm;
import javax.swing.UIManager;

public class Main {
    
    public static void main(String[] args) {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
//        new QuestionEditor(null, true).setVisible(true);
//        new MainForm().setVisible(true);
//        new PassageTestForm(null, true).setVisible(true);
        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
    }

}
