package pub;

public class FormatPrint {
    // constants
    private final static int LINE_LENGTH = 85;

    public static void printHeader(String s) {
        int counts = (LINE_LENGTH - s.length()) / 2;
        StringBuilder padding = new StringBuilder();

        for (int i = 0; i < counts - 1; i++) {
            padding.append("=");
        }

        System.out.println("\n" + padding + " " + s.toUpperCase() + " " + padding + "\n");
    }


}
