package ru.vsu.cs.novichikhin;

import ru.vsu.cs.util.ListUtils;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public class ConsoleMain {
    public static class CmdParams {
        String inputFile;
        String outputFile;
        boolean error;
        boolean help;
        boolean window;

    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();

        if (args.length == 1 || args.length == 2 || args.length == 4) {

            if (args.length == 1) {
                if (args[0].equals("--help")) {
                    params.help = true;
                } else {
                    if (args[0].equals("--window")) {
                        params.window = true;
                    } else {
                        returnError(params);
                    }
                }
            } else {

                if (args.length == 2 && args[0].equals("-i")) {
                    params.inputFile = args[1];
                    params.outputFile = null;
                } else {

                    if (args[0].equals("-i") && args[2].equals("-o")) {
                        params.inputFile = args[1];
                        params.outputFile = args[3];
                    } else {
                        returnError(params);
                    }
                }
            }
        } else {
            returnError(params);
        }
        return params;
    }

    private static void returnError(ConsoleMain.CmdParams params) {
        params.help = true;
        params.error = true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        CmdParams params = parseArgs(args);

        if (params.help) {
            printUsage(params);
        } else {
            if (params.window) {
                GuiMain.winMain();
            } else {
                makePairs(params);
            }
        }
    }

    private static void printUsage(ConsoleMain.CmdParams params) {
        PrintStream out = params.error ? System.err : System.out;
        out.println("Usage:");
        out.println("  <cmd> -i <input-file> -o <output-file>");
        out.println("  <cmd> -i <input-file>");
        out.println("  <cmd> --help");
        out.println(" <cmd> --window");
        System.exit(params.error ? 1 : 0);
    }

    private static void makePairs(ConsoleMain.CmdParams params) {
        try {
            List<List<String>> students = ListUtils.readList2FromFile(params.inputFile);

            if (students == null) {
                printError();
            }

            MakingPairs makingPair = new MakingPairs();
            List<List<String>> pairs = makingPair.createPairs(students);

            printResult(params, pairs);
        } catch (Exception e) {
            printError();
        }
    }

    private static void printResult(ConsoleMain.CmdParams params, List<List<String>> list) throws FileNotFoundException {
        if (params.outputFile != null) {
            ListUtils.writeList2ToFile(list, params.outputFile);
        } else {
            ListUtils.writeList2ToConsole(list);
        }
    }

    private static void printError() {
        System.err.print("Данные в файле некорректны (в каждой строке через запятую должны быть указаны ФИО, " +
                "пол(мужской, женский), номер курса(1 - 4) и средний балл студента)");
        System.exit(1);
    }
}
