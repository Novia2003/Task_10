package ru.vsu.cs.util;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;


/**
 * Набор функций для работы с оконными приложениями с ипользованием Swing
 *
 * @author Дмитрий Соломатин (кафедра ПиИТ ФКН ВГУ)
 */
public class SwingUtils {
    /**
     * Показать диалоговое окно с информационным сообщением
     *
     * @param message Сообщение
     * @param title   Заголовок окна
     */
    public static void showInfoMessageBox(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Установка шрифта по умолчанию для элементов управления
     *
     * @param fontName Название шрифта (null - не менять)
     * @param size     Размер шрифта (меньше или равно 0 - не менять)
     */
    public static void setDefaultFont(String fontName, int size) {
        UIManager.getDefaults().forEach((key, value1) -> {
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource fr) {
                fr = new FontUIResource(
                        (fontName != null) ? fontName : fr.getFontName(),
                        fr.getStyle(),
                        (size > 0) ? size : fr.getSize()
                );
                UIManager.put(key, fr);
            }
        });
    }
}
