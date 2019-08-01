package pub;

import java.util.ArrayList;

public class formatPrint {
    // constants
    final static int LINE_LENGTH = 85;

//    // prints a string appropriately
//    public static void fPrint(String s) {
//        ArrayList<String> lines = new ArrayList<>();
//
//        // break string into lines
//        lines = addLines(s);
//
//        // print headers
//        printHeader();
//
//        // print body
//        printBody(lines);
//
//        // print footers
//        printFooter();
//    }
//
//    private static ArrayList<String> addLines(String s) {
//        ArrayList<String> lines = new ArrayList<>();
//        // split s into multiple lines
//        while (s.length() > LINE_LENGTH) {
//            lines.add(s.substring(0, LINE_LENGTH));
//            s = s.substring(LINE_LENGTH, s.length());
//        }
//        lines.add(s);
//        return lines;
//    }
//
//    private static void printHeader() {
//        String header = "";
//
//        for (int i = 0; i < LINE_LENGTH; i++) {
//            header += "=";
//        }
//
//        System.out.println(header + "\n");
//    }
//
//    private static void printBody(ArrayList<String> lines) {
//        for (String s : lines) {
//            System.out.println(s);
//        }
//
//    }
//
//    private static void printFooter() {
//        String header = "";
//
//        for (int i = 0; i < LINE_LENGTH; i++) {
//            header += "=";
//        }
//
//        System.out.println(header + "\n");
//    }

    public static void printHeader(String s) {
        int counts = (LINE_LENGTH - s.length()) / 2;
        int remainder = (LINE_LENGTH - s.length()) % 2;
        String padding = "";

        for (int i = 0; i < counts - 1; i++) {
            padding += "=";
        }

        System.out.println("\n" + padding + " " + s.toUpperCase() + " " + padding + "\n");
    }




}
