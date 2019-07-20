package staff;

import java.util.Scanner;
//import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Staff {

    private String staffID;
    private String password;
    private String sName;
    private char gender;
    private static int day;
    private int month;
    private int year;
    private String designation;
    private LocalDate dateOfEmployed;
    //Date dateOfEmployed = new Date();
        //public Staff(){

   // }

    public static ArrayList<Staff> employeeList = new ArrayList<Staff>();

    public Staff(String staffID, String password, String sName, char gender,String designation,
                int year,int month, int dayOfMonth){
        this.setStaffID(staffID);
        this.setPassword(password);
        this.setsName(sName);
        this.setGender(gender);
        this.setDesignation(designation);
        this.dateOfEmployed = LocalDate.of(year, month, dayOfMonth);
    }
    
    public Staff(String staffID, String password, String sName, char gender,String designation){
        this(staffID, password, sName, gender, designation,  0, 0, 0);
    }


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static int mainMenu() //To allow the user to perform any of the transaction
    {
        //Local Variables declaration
        int selection;
        Scanner scan = new Scanner(System.in);
        //Display homescreen
       // homescreen();

        //Display menu
        //header("Staff Information Module");
        System.out.printf("%20s%s\n", " ", "Staff Information Module");
        System.out.printf("%20s%s\n", " ", "1. Add Staff Record");
        System.out.printf("%20s%s\n", " ", "2. Display Staff Record");
        System.out.printf("%20s%s\n", " ", "3. Modify Staff Record");
        System.out.printf("%20s%s\n", " ", "4. Search Staff Record");
        System.out.printf("%20s%s\n", " ", "5. Exit");


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
                    //System.out.println("Add Staff Record");
                    addStaff();
                    break;

                case 2:
                    System.out.println("Display Staff Record");
                    for(int i = 0; i< employeeList.size(); i++){
                        Staff temp = employeeList.get(i);
                        System.out.printf("%-8s%-16s%-16s%2c%4d%15s",
                                temp.getStaffID(), temp.getPassword(),temp.getsName(),
                                temp.getGender(),
                                temp.getDateOfEmployed().toString());
                    }
                    break;

                case 3:
                    System.out.println("");
                    break;

                case 4:
                    System.out.println("");
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

    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }
    public String getsName() { return sName; }
    public void setsName(String sName) { this.sName = sName; }
    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public LocalDate getDateOfEmployed() { return dateOfEmployed; }
    public void setDateOfEmployed(LocalDate dateOfEmployed) { this.dateOfEmployed = dateOfEmployed; }
    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password = password; }

    public static void addStaff(){

        String staffID, password, sName, designation;
        char gender;
        String[] dateOfEmployed;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter staff Id : ");
        staffID = scan.nextLine();

        System.out.println("Enter staff password : ");
        password = scan.nextLine();

        System.out.println("Enter staff name : ");
        sName = scan.nextLine();

        System.out.println("Enter staff gender : ");
        gender = scan.nextLine().charAt(0);

        System.out.println("Enter staff designation : ");
        designation = scan.nextLine();

        scan.nextLine();
        System.out.println("Enter staff date of employed (dd-mm-yyyy): ");
        dateOfEmployed = scan.nextLine().split("[-/]");

        employeeList.add(new Staff(staffID, password, sName, gender,
                designation,
                Integer.parseInt(dateOfEmployed[2]),
                Integer.parseInt(dateOfEmployed[1]),
                Integer.parseInt(dateOfEmployed[0]))
        );


    }

}







