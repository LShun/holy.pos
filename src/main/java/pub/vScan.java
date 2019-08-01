package pub;

import java.util.Scanner;

public class vScan {
    private static Scanner in = new Scanner(System.in);

    public static int getInt() {
        int i;

        while(!in.hasNextInt()) {
            System.out.print("Enter a number: ");
            in.next();
        }
        i = in.nextInt();
        in.nextLine();
        return i;
    }

    public static double getDouble() {
        double d;

        while(!in.hasNextDouble()) {
            System.out.print("Enter a number: ");
            in.next();
        }
        d = in.nextDouble();
        in.nextLine();
        return d;
    }

    public static String getString() {
        String s;

        s = in.nextLine();

        while(s.length() <= 0) {
            System.out.print("Enter a string: ");
            s = in.nextLine();
        }
        return s;
    }

    public static char getChar() {

        char c = in.nextLine().charAt(0);

        while(c == ' ' || c == '\n') {
            System.out.print("Enter a character: ");
            c = in.nextLine().charAt(0);
        }
        return c;
    }
}
