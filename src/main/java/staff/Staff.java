package staff;

import java.awt.*;
import java.util.Scanner;

public class Staff {

    private String staffID;
    private String sName;
    private char gender;
    private int day;
    private int month;
    private int year;
    private String designation;
    private int workingExperience;

    //public Staff(){

   // }

    public Staff(){

        this.staffID=staffID;
        this.sName=sName;
        this.gender=gender;
        this.designation=designation;
        this.workingExperience=workingExperience;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int getInt() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter an integer: ");
        return in.nextInt();
    }

    public static int mainMenu() //To allow the user to perform any of the transaction
    {
        //Local Variables declaration
        int selection;
        Scanner scan=new Scanner(System.in);
        //Display homescreen
       // homescreen();

        //Display menu
        //header("Staff Information Module");
        System.out.printf("%20s%s\n", " ", "Staff Information Module");
        System.out.printf("%20s%s\n", " ", "1. Display Existing Staff Record");
        System.out.printf("%20s%s\n", " ", "2. Add Staff Record");
        System.out.printf("%20s%s\n", " ", "3. Search Staff Record");
        System.out.printf("%20s%s\n", " ", "4. Modify Staff Record");
        System.out.printf("%20s%s\n", " ", "5. Remove Staff Record");
        System.out.printf("%20s%s\n", " ", "6. Display Removed Staff Record");
        System.out.printf("%20s%s\n\n", " ", "7. Exit");

        //Input
        System.out.printf("%20s%s", " ", "Enter your selection : ");

        return scan.nextInt();
    }
    public static void staff(){
        //Variables declaration
        int choice;

        //Function call-mainMenu()
        choice = getInt();

        do
        {
            clearScreen();

            if (choice == 1)
            {
                //displayExistingRecord();
                System.out.println("Hello");


            }

            else if (choice == 2)
            {
                //addRecord();
                System.out.println("Hello1");


            }

            else if (choice == 3)
            {
                //searchRecord();
                System.out.println("Hello2");


            }

            else if (choice == 4)
            {
                //modifyRecord();
                System.out.println("Hello3");


            }

            else if (choice == 5)
            {
                //removeRecord();
                System.out.println("Hello4");



            }

            else if (choice == 6)
            {
               // displayRemoveRecord();
                System.out.println("Hello5");

            }

            else if (choice != 7)
            {
                System.out.println("Error! Please enter again\n");


            }

            System.out.println("\n");

        } while (getInt() != 7);




    }



}







