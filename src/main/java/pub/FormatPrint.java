package pub;

import java.util.ArrayList;

public class FormatPrint {
    // constants
    final static int LINE_LENGTH = 85;

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
