package ru.vsu.cs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListUtils {
    public static List<List<String>> readList2FromFile(String fileName) {
        try {
            return toList2(readLinesFromFile(fileName));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static List<String> readLinesFromFile(String fileName) throws FileNotFoundException {
        List<String> lines;
        try (Scanner scanner = new Scanner(new File(fileName))) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }

    public static String[][] toStringArray2(List<List<String>> lists) {
        String[][] array = new String[lists.size()][lists.get(0).size()];
        for (int r = 0; r < lists.size(); r++) {
            for (int c = 0; c < lists.get(r).size(); c++) {
                array[r][c] = lists.get(r).get(c);
            }
        }
        return array;
    }

    public static List<List<String>> toList2(List<String> lines) {
        if (lines.get(0).equals("")) {
            return null;
        }
        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            List<String> list = new ArrayList<>();
            String[] str = lines.get(i).split(",");
            for (int j = 0; j < str.length; j++) {
                list.add(str[j].trim());
            }
            lists.add(list);
        }
        return lists;
    }

    public static List<List<String>> toList2(String[][] array) {
        List<List<String>> lists = new ArrayList<>();
        for (int r = 0; r < array.length; r++) {
            List<String> list = new ArrayList<>();
            for (int c = 0; c < array[r].length; c++) {
                list.add(array[r][c].trim());
            }
            lists.add(list);
        }
        return lists;
    }

    public static void writeList2ToConsole(List<List<String>> lists) {
        for (int i = 0; i < lists.size(); i++) {
            System.out.print("Курс " + lists.get(i).get(0) + ": ");
            System.out.println(lists.get(i).get(1));
        }
    }

    public static void writeList2ToFile(List<List<String>> lists, String fileName) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(fileName);
        for (int i = 0; i < lists.size(); i++) {
            out.print("Курс " + lists.get(i).get(0) + ": ");
            out.println(lists.get(i).get(1));
        }
        out.close();
    }
}