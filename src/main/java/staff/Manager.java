package staff;

import pub.vScan;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Worker {

    public Manager(){
        super("","","",' ',"",2000,0,0,0,0);
    }

    public Manager(String staffID, String password, String sName, char gender, String designation,
                 int year, int month, int dayOfMonth, int totalDurationWorked, int salesReceived){
        super(staffID, password, sName, gender, designation, year, month, dayOfMonth, totalDurationWorked, salesReceived) ;
    }

    public Manager(String staffID, String password, String sName, char gender,String designation){
        super(staffID, password, sName, gender, designation,  2019, 1, 1, 0, 0);
    }

    public void addStaff(){

        String staffID, password, sName, designation;
        char gender;
        String[] dateOfEmployed;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter worker Id : ");
        staffID = scan.nextLine();

        System.out.println("Enter worker password : ");
        password = scan.nextLine();

        System.out.println("Enter worker name : ");
        sName = scan.nextLine();

        System.out.println("Enter worker gender : ");
        gender = scan.nextLine().charAt(0);

        System.out.println("Enter worker designation : ");
        designation = scan.nextLine();

        scan.nextLine();
        System.out.println("Enter worker date of employed (dd-mm-yyyy): ");
        dateOfEmployed = scan.nextLine().split("[-/]");

        Staff.getEmployeeList().add(new Worker(staffID, password, sName, gender,
                designation,
                Integer.parseInt(dateOfEmployed[2]),
                Integer.parseInt(dateOfEmployed[1]),
                Integer.parseInt(dateOfEmployed[0]),0,0)
        );
    }

    public void displayStaff(ArrayList<Worker> data) {
        for(int i = 0; i< data.size(); i++){
            Worker temp = data.get(i);
            System.out.printf("%-8s%-16s%-16s%2c%15s\n",
                    temp.getStaffID(), temp.getPassword(),temp.getName(),
                    temp.getGender(),
                    temp.getDateOfEmployed().toString());
        }
    }

    public void modifyStaff(){
        ArrayList<Worker> data = searchStaff();

        Worker workerToBeModified;

        if(data.size() == 0) return; //Worker does not exists
        else if(data.size() > 1){    //If more than one worker, user have to select which one to modify
            displayStaff(data);
            System.out.print("Enter the index : ");
            int target = vScan.getInt();
            workerToBeModified = data.get(target - 1);
        }
        else
            workerToBeModified = data.get(0);

        System.out.println("1. Worker ID");
        System.out.println("2. Worker Name");
        System.out.println("3. Worker Gender");
        System.out.println("4. Worker Designation");
        System.out.print("Select which one the modify : ");
        int choice = vScan.getInt();
        String inputString;
        char inputChar;

        switch(choice){
            case 1:
                System.out.print("Enter the worker Id : ");
                inputString = vScan.getString();
                workerToBeModified.setStaffID(inputString);
                break;

            case 2:
                System.out.print("Enter the worker name : ");
                inputString = vScan.getString();
                workerToBeModified.setName(inputString);
                break;
            case 3:
                System.out.print("Enter the worker gender : ");
                inputChar = vScan.getChar();
                workerToBeModified.setGender(inputChar);
            case 4:
                System.out.print("Enter the worker designation : ");
                inputString = vScan.getString();
                workerToBeModified.setDesignation(inputString);
                break;
            default:
                System.out.println("Invalid value!");
        }
    }

    public ArrayList<Worker> searchStaff(){
        String searchInput;
        ArrayList<Worker> employeeList = Staff.getEmployeeList();
        ArrayList<Worker> result = new ArrayList<>();

        System.out.println("1. Worker ID");
        System.out.println("2. Worker Name");
        System.out.println("3. Worker Gender");
        System.out.println("4. Worker Designation");
        System.out.println("Select the search criteria : ");
        int choice = vScan.getInt();

        switch(choice) {
            case 1:
                System.out.print("Enter the worker Id : ");
                searchInput = vScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getStaffID()))
                        result.add(employeeList.get(i));
                }
                break;
            case 2:
                System.out.print("Enter the worker name : ");
                searchInput = vScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getName()))
                        result.add(employeeList.get(i));
                }
                break;
            case 3:
                System.out.print("Enter the worker gender : ");
                searchInput = vScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getGender()))
                        result.add(employeeList.get(i));
                }
                break;
            case 4:
                System.out.print("Enter the worker designation : ");
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
