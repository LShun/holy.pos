package staff;

import java.awt.*;
import java.util.Scanner;
import java.util.Date;

public class Staff {

    private String staffID;
    private String sName;
    private char gender;
    private static int day;
    private int month;
    private int year;
    private String designation;
    private int workingExperience;

    Date dateOfEmployed = new Date();
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
        choice = mainMenu();

        do
        {
            clearScreen();

            switch(choice)
            {
                case 1:
                    System.out.println("Add Staff Record");
                    break;

                case 2:
                    System.out.println("Display Staff Record");
                    break;

                case 3:
                    System.out.println("Modify Staff Record");
                    break;

                case 4:
                    System.out.println("Search Staff Record");
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



}







