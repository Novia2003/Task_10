package ru.vsu.cs.novichikhin;

import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.MyUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;

public class FrameMain extends JFrame {
    private JButton buttonLoadFromInputFile;
    private JButton buttonSaveToInputFile;
    private JTable tableOutput;
    private JButton buttonSaveToOutputFile;
    private JButton buttonMakePairs;
    private JPanel panelMain;
    private JTable tableInput;
    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("Составление пар на новый год");
        this.setContentPane(panelMain);
        this.setBounds(300, 50, 1000, 10);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 190, false, true, true, false, true);
        JTableUtils.initJTableForArray(tableOutput, 190, false, true, true, false, false);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("C:\\Users\\ВЯЧЕСЛАВ\\ВГУ\\ВВП\\Ten\\Ten"));
        fileChooserSave.setCurrentDirectory(new File("C:\\Users\\ВЯЧЕСЛАВ\\ВГУ\\ВВП\\Ten\\Ten"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        buttonLoadFromInputFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    List<List<String>> students = MyUtils.readList2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    assert students != null;
                    String[][] array = MyUtils.toStringArray2(students);
                    JTableUtils.writeArrayToJTable(tableInput, array);
                }
            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("Данные в файле некоректны (в каждой строке через запятую" +
                        " должны быть указаны ФИО, пол(мужской, женский), номер курса(1 - 4) и средний " +
                        "балл студента)", "Ошибка");
            }
        });


        buttonSaveToInputFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String[][] array = JTableUtils.readStringMatrixFromJTable(tableInput);
                    assert array != null;
                    List<List<String>> students = MyUtils.toList2(array);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    MyUtils.writeList2ToFile(students, file);
                }
            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("Не уадётся сохранить в файл", "Ошибка");
            }
        });


        buttonMakePairs.addActionListener(actionEvent -> {
            try {
                String[][] array = JTableUtils.readStringMatrixFromJTable(tableInput);
                assert array != null;

                List<List<String>> students = MyUtils.toList2(array);
                MakingPairs makingPair = new MakingPairs();
                List<List<String>> pairs = makingPair.createPairs(students);

                String[][] matrix = MyUtils.toStringArray2(pairs);
                JTableUtils.writeArrayToJTable(tableOutput, matrix);
            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("В каждой строке через запятую должны быть указаны ФИО," +
                        " пол(мужской, женский), номер курса(1 - 4) и средний балл студента", "Ошибка");
            }
        });

        buttonSaveToOutputFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String[][] array = JTableUtils.readStringMatrixFromJTable(tableOutput);
                    assert array != null;
                    List<List<String>> pairs = MyUtils.toList2(array);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    MyUtils.writeList2ToFile(pairs, file);
                }
            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("Не уадётся сохранить в файл", "Ошибка");
            }
        });
    }
}
