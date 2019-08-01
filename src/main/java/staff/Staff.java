package staff;

import pub.vScan;

import java.util.Scanner;
//import java.util.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;

public class Staff {

    private String staffID;
    private String password;
    private String sName;
    private char gender;
    private String designation;
    private LocalDate dateOfEmployed;
    private Duration totalDurationWorked;
    private int salesReceived;
    //Date dateOfEmployed = new Date();
        //public Staff(){

   // }

    public static ArrayList<Staff> employeeList = new ArrayList<Staff>(Arrays.asList(
            new Staff("0001","123","Long Shun",'M',"Holy 1",2001,1,1,100,10),
            new Staff("0002","456","Jun Rong",'M',"Holy 2",2000,2,2,200,20),
            new Staff("0003","789","Shannen",'F',"Holy 3",2000,3,3,300,30),
            new Staff("0004","000","Kim Chun",'M',"Holy 4",2000,4,4,400,40)
    ));

    public Staff(String staffID, String password, String sName, char gender, String designation,
                 int year, int month, int dayOfMonth, int totalDurationWorked, int salesReceived){
        this.setStaffID(staffID);
        this.setPassword(password);
        this.setName(sName);
        this.setGender(gender);
        this.setDesignation(designation);
        this.dateOfEmployed = LocalDate.of(year, month, dayOfMonth);
        this.setTotalDurationWorked(Duration.ofHours(totalDurationWorked));
        this.setSalesReceived(salesReceived);
    }
    
    public Staff(String staffID, String password, String sName, char gender,String designation){
        this(staffID, password, sName, gender, designation,  2019, 1, 1, 0, 0);
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
                    displayStaff(employeeList);
                    break;

                case 3:
                    System.out.println("Modify Staff");
                    ArrayList<Staff> result_Modify = searchStaff();
                    modifyStaff(result_Modify);
                    break;

                case 4:
                    System.out.println("Search Staff");
                    ArrayList<Staff> result_Search = searchStaff();
                    displayStaff(result_Search);
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
    public String getName() { return sName; }
    public void setName(String sName) { this.sName = sName; }
    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public LocalDate getDateOfEmployed() { return dateOfEmployed; }
    public void setDateOfEmployed(LocalDate dateOfEmployed) { this.dateOfEmployed = dateOfEmployed; }
    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password = password; }
    public Duration getTotalDurationWorked() { return totalDurationWorked; }
    public void setTotalDurationWorked(Duration totalDurationWorked) { this.totalDurationWorked = totalDurationWorked; }
    public int getSalesReceived() { return salesReceived; }
    public void setSalesReceived(int salesReceived) { this.salesReceived = salesReceived; }

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
                Integer.parseInt(dateOfEmployed[0]),0,0)
        );


    }

    private static void displayStaff(ArrayList<Staff> data) {
        for(int i = 0; i< data.size(); i++){
            Staff temp = data.get(i);
            System.out.printf("%-8s%-16s%-16s%2c%15s\n",
                    temp.getStaffID(), temp.getPassword(),temp.getName(),
                    temp.getGender(),
                    temp.getDateOfEmployed().toString());
        }
    }

    private static void modifyStaff(ArrayList<Staff> data){
        Staff staffToBeModified;

        if(data.size() == 0) return; //Staff does not exists
        else if(data.size() > 1){    //If more than one staff, user have to select which one to modify
            displayStaff(data);
            System.out.print("Enter the index : ");
            int target = vScan.getInt();
            staffToBeModified = data.get(target - 1);
        }
        else
            staffToBeModified = data.get(0);

        System.out.println("1. Staff ID");
        System.out.println("2. Staff Name");
        System.out.println("3. Staff Gender");
        System.out.println("4. Staff Designation");
        System.out.print("Select which one the modify : ");
        int choice = vScan.getInt();
        String inputString;
        char inputChar;

        switch(choice){
            case 1:
                System.out.print("Enter the staff Id : ");
                inputString = vScan.getString();
                staffToBeModified.setStaffID(inputString);
                break;

            case 2:
                System.out.print("Enter the staff name : ");
                inputString = vScan.getString();
                staffToBeModified.setName(inputString);
                break;
            case 3:
                System.out.print("Enter the staff gender : ");
                inputChar = vScan.getChar();
                staffToBeModified.setGender(inputChar);
            case 4:
                System.out.print("Enter the staff designation : ");
                inputString = vScan.getString();
                staffToBeModified.setDesignation(inputString);
                break;
            default:
                System.out.println("Invalid value!");
        }
    }

    private static ArrayList<Staff> searchStaff(){
        String searchInput;
        ArrayList<Staff> result = new ArrayList<>();

        System.out.println("1. Staff ID");
        System.out.println("2. Staff Name");
        System.out.println("3. Staff Gender");
        System.out.println("4. Staff Designation");
        System.out.println("Select the search criteria : ");
        int choice = vScan.getInt();

        switch(choice) {
            case 1:
                System.out.print("Enter the staff Id : ");
                searchInput = vScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getStaffID()))
                        result.add(employeeList.get(i));
                }
                break;
            case 2:
                System.out.print("Enter the staff name : ");
                searchInput = vScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getName()))
                        result.add(employeeList.get(i));
                }
                break;
            case 3:
                System.out.print("Enter the staff gender : ");
                searchInput = vScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getGender()))
                        result.add(employeeList.get(i));
                }
                break;
            case 4:
                System.out.print("Enter the staff designation : ");
                searchInput = vScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getDesignation()))
                        result.add(employeeList.get(i));
                }
                break;
            default:
                System.out.println("Invalid value!");
        }
        return result;
    }
}







