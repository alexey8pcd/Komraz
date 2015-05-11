package main;

import javax.swing.JOptionPane;

/**
 *
 * @author Solovenko
 *
 * Методы на:
 * <br>1) Подтверждение выхода
 * <br>2) Предупреждения от исключительных событий
 *
 */
public class DialogManager {

    public enum TypeOfMessage {

        WARNING,
        ERROR
    }

    /**
     * Метод на подтверждение закрытия формы
     *
     * @param subject
     * @return <br>true - если закрытие формы возможно
     * <br>false - если в закрытии формы отказано
     */
    public static boolean confirmClosingForm(String subject) {
        int result = JOptionPane.showConfirmDialog(null,
                "Редактирование " + subject + " не завершено. "
                + "Вы действительно хотите выйти из редактора?",
                "Подтверждение выхода", JOptionPane.YES_NO_OPTION);

        return result == JOptionPane.YES_OPTION;
    }

    public static boolean confirmDeleting(String text) {
        int result = JOptionPane.showConfirmDialog(null, text,
                "Подтверждение", JOptionPane.YES_NO_OPTION);

        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Метод на вызов предупреждения
     *
     * @param headerOfMessage заголовок диалогового окна
     * @param textOfMessage текст диалогового окна
     * @param type тип предупреждения (WARNING or ERROR)
     */
    public static void warningMessage(String headerOfMessage, String textOfMessage, TypeOfMessage type) {
        switch (type) {
            case WARNING:
                JOptionPane.showMessageDialog(null, textOfMessage, headerOfMessage, JOptionPane.WARNING_MESSAGE);
            case ERROR:
                JOptionPane.showMessageDialog(null, textOfMessage, headerOfMessage, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Вывод сообщения об ошибке при отлове исключительных событий
     *
     * @param ex
     */
    public static void errorMessage(Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString(),
                "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
