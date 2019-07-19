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
    private int workingExperience;
    private LocalDate dateOfEmployed;
    //Date dateOfEmployed = new Date();
        //public Staff(){

   // }

    public static ArrayList<Staff> employeeList = new ArrayList<Staff>();

    public Staff(String staffID, String password, String sName, char gender,String designation, int workingExperience,
                int year,int month, int dayOfMonth){
        this.setStaffID(staffID);
        this.setPassword(password);
        this.setsName(sName);
        this.setGender(gender);
        this.setDesignation(designation);
        this.setWorkingExperience(workingExperience);
        this.dateOfEmployed = LocalDate.of(year, month, dayOfMonth);
    }
    
    public Staff(String staffID, String password, String sName, char gender,String designation, int workingExperience){
        this(staffID, password, sName, gender, designation, workingExperience, 0, 0, 0);
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

<<<<<<< HEAD

=======
>>>>>>> d90617ff7a2c793a793625c567f1189f00680bd3
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
<<<<<<< HEAD
                    System.out.println("1. Add Staff Record");
                    break;

                case 2:
                    System.out.println("2. Display Staff Record");
=======
                    System.out.println("Add Staff Record");
                    {
                        String staffID, password, sName, designation;
                        char gender;
                        int workingExperience;
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
                        System.out.println("Enter staff working experience : ");
                        workingExperience = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter staff date of employed (yy-mm-dd): ");
                        dateOfEmployed = scan.nextLine().split("[-/]");

                        employeeList.add(new Staff(staffID, password, sName, gender,
                                designation, workingExperience,
                                Integer.parseInt(dateOfEmployed[0]),
                                Integer.parseInt(dateOfEmployed[1]),
                                Integer.parseInt(dateOfEmployed[2]))
                        );
                    }
                    break;

                case 2:
                    System.out.println("Display Staff Record");
                    for(int i = 0; i< employeeList.size(); i++){
                        Staff temp = employeeList.get(i);
                        System.out.printf("%-8s%-16s%-16s%2c%4d%15s",
                                temp.getStaffID(), temp.getPassword(),temp.getsName(),
                                temp.getGender(), temp.getWorkingExperience(),
                                temp.getDateOfEmployed().toString());
                    }
>>>>>>> d90617ff7a2c793a793625c567f1189f00680bd3
                    break;

                case 3:
                    System.out.println("3. Modify Staff Record");
                    break;

                case 4:
                    System.out.println("4. Search Staff Record");
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

<<<<<<< HEAD
    public void addStaff(){







    }



=======
    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }
    public String getsName() { return sName; }
    public void setsName(String sName) { this.sName = sName; }
    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public int getWorkingExperience() { return workingExperience; }
    public void setWorkingExperience(int workingExperience) { this.workingExperience = workingExperience; }
    public LocalDate getDateOfEmployed() { return dateOfEmployed; }
    public void setDateOfEmployed(LocalDate dateOfEmployed) { this.dateOfEmployed = dateOfEmployed; }
    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password = password; }
>>>>>>> d90617ff7a2c793a793625c567f1189f00680bd3
}







