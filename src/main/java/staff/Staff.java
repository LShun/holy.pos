package staff;

import auth.Auth;
import pub.FormatPrint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Staff {
    private static ArrayList<Worker> employeeList = new ArrayList<Worker>(Arrays.asList(
            new Manager("1000","123456","Mr. Manager",'M',"011-3456789",10000,"Manager",1999,9,9,1000,1000),
            new Worker("0001","123","Long Shun",'M',"011-27313088",3000,"Cashier",2001,1,1,100,10),
            new Worker("0002","456","Jun Rong",'M',"011-23177422",4000,"Cashier",2000,2,2,200,20),
            new Worker("0003","789","Shannen",'F',"011-39873922",5000,"Cashier",2000,3,3,300,30),
            new Worker("0004","000","Kim Chun",'M',"016-2949819",6000,"Cashier",2000,4,4,400,40)
    )
    );

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int mainMenu() //To allow the user to perform any of the task
    {
        //Local Variables declaration
        int selection;
        Scanner scan = new Scanner(System.in);

        FormatPrint.printHeader("STAFF");

//        System.out.printf("%s\n","Worker Information Module");
        System.out.printf("%s\n", "1. Add Worker Record");
        System.out.printf("%s\n", "2. Display Worker Record");
        System.out.printf("%s\n", "3. Modify Worker Record");
        System.out.printf("%s\n", "4. Search Worker Record");
        System.out.printf("%s\n", "5. Exit");


        //Input
        System.out.printf("%s", "Enter your selection : ");

        return scan.nextInt();
    }

    public static void staff() {
        //Variables declaration
        int choice;

        //Function call-mainMenu()
        choice = mainMenu();

        //Find which worker or manager is performing action
        Worker s = Auth.getSession();
        if (s == null) return;

        do {
            clearScreen();

            switch (choice) {
                case 1:
                    //System.out.println("Add Worker Record");
                    FormatPrint.printHeader("ADDING STAFF RECORD");
                    s.addStaff();
                    break;

                case 2:
                    FormatPrint.printHeader("DISPLAYING STAFF RECORD");
                    s.displayStaff(getEmployeeList());
                    break;

                case 3:
                    FormatPrint.printHeader("MODIFYING STAFF RECORD");
                    s.modifyStaff();
                    break;

                case 4:
                    FormatPrint.printHeader("SEARCHING STAFF RECORD");
                    ArrayList<Worker> result_Search = s.searchStaff();
                    s.displayStaff(result_Search);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Please enter a valid number");
            }

            System.out.println("\n");

            choice = mainMenu();
        } while (choice != 7);
    }

    public static ArrayList<Worker> getEmployeeList() {
        return employeeList;
    }
}
