package staff;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import pub.VScan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Worker {

    public Manager(){

        super("","","",' ',"",0.0,"",2000,0,0,0,0);

    }

    public Manager(String staffID, String password, String sName, char gender, String phoneNumber, double salary, String designation,
                 int year, int month, int dayOfMonth, int totalDurationWorked, int salesReceived){
        super(staffID, password, sName, gender, phoneNumber, salary, designation, year, month, dayOfMonth, totalDurationWorked, salesReceived) ;
    }

//    public Manager(String staffID, String password, String sName, char gender,String designation){
//        super(staffID, password, sName, gender, designation,  2019, 1, 1, 0, 0);
//    }

    //Add Staff Record
    public void addStaff(){

        String staffID, password, sName, designation, phoneNumber;
        double salary;
        char gender;
        String inputDateOfEmployed;
        boolean repeat = true;
        LocalDate dateOfEmployed;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter worker Id (XXX to stop): ");
        staffID = scan.nextLine();

        while(staffID.equals("XXX")==false) {
            System.out.println("Enter worker password : ");
            password = scan.nextLine();

            System.out.println("Enter worker name : ");
            sName = scan.nextLine();

            System.out.println("Enter worker gender (M or F) : ");
            gender = scan.nextLine().charAt(0);

//            while(gender!='M'&&gender!='F'){
//                System.out.println("Please enter a valid gender (M or F)!");
//
//                System.out.println("Enter worker gender (M or F): ");
//                gender = scan.nextLine().charAt(0);
//            }

            System.out.println("Enter the worker phone number : ");
            phoneNumber = scan.nextLine();

            System.out.println("Enter the worker salary : ");
            salary = scan.nextDouble();

            System.out.println("Enter worker designation : ");
            designation = scan.nextLine();

            do {
                dateOfEmployed = LocalDate.parse("1970-01-01"); //To get rid of compiler error
                System.out.println("Enter date of employed (dd-mm-yyyy): ");
                inputDateOfEmployed = scan.nextLine();
                try {
                    dateOfEmployed = LocalDate.parse(inputDateOfEmployed, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    repeat = false;
                } catch (DateTimeParseException e) {
                    System.out.println(inputDateOfEmployed + " is not following the pattern dd-mm-yyyy\nPlease try to enter again");
                }
            }while(repeat);

            Staff.getEmployeeList().add(new Worker(staffID, password, sName, gender, phoneNumber, salary,
                    designation, dateOfEmployed, 0, 0));

            System.out.println("Staff Record Has Been Added Successfully!");

            System.out.println("Enter worker Id (XXX to stop): ");
            staffID = scan.nextLine();
        }
    }

    //Display Staff Record
    public void displayStaff(ArrayList<Worker> data) {
        AsciiTable at = new AsciiTable();
        CWC_LongestLine width = new CWC_LongestLine();
        at.getRenderer().setCWC(width);
        //width.add(3,0).add(8,0).add(16,0).add(3,0).add(12,0).add(10,0).add(20,0); //Specify the width of each column

        at.addRule();

        //Display the heading
        AT_Row heading = at.addRow("NO.","STAFF ID", "STAFF NAME", "SEX", "PHONE NUMBER", "SALARY", "DATE OF EMPLOYED");
        heading.setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        for(int i = 0; i< data.size(); i++){
            Worker temp = data.get(i);
            AT_Row row = at.addRow(i+1, temp.getStaffID(), temp.getName(), temp.getGender(),
                    temp.getPhoneNumber(), temp.getSalary(), temp.getDateOfEmployed().toString());
            row.getCells().get(1).getContext().setTextAlignment(TextAlignment.RIGHT);
            row.getCells().get(3).getContext().setTextAlignment(TextAlignment.CENTER);
            row.getCells().get(5).getContext().setTextAlignment(TextAlignment.RIGHT);

            at.addRule();
        }

        System.out.println(at.render());
    }

    //Modify Staff Record
    public void modifyStaff(){
        ArrayList<Worker> data = searchStaff(); //Call search() to find the list of staff match the criteria

        Worker workerToBeModified;

        if(data.size() == 0) return; //Worker does not exists
        else if(data.size() > 1){    //If more than one worker, user have to select which one to modify
            displayStaff(data);
            System.out.print("Enter the index : ");
            int target = VScan.getInt();
            workerToBeModified = data.get(target - 1);
        }
        else
            workerToBeModified = data.get(0);

        System.out.println("1. Worker ID");
        System.out.println("2. Worker Name");
        System.out.println("3. Worker Gender");
        System.out.println("4. Worker Phone Number");
        System.out.println("5. Worker Salary");
        System.out.println("6. Worker Designation");
        System.out.print("Select which one the modify : ");

        int choice = VScan.getInt();

        String inputString;
        char inputChar;
        double inputDouble;

        switch(choice){
            case 1:
                System.out.print("Enter the worker Id : ");
                inputString = VScan.getString();
                workerToBeModified.setStaffID(inputString);
                break;
            case 2:
                System.out.print("Enter the worker name : ");
                inputString = VScan.getString();
                workerToBeModified.setName(inputString);
                break;
            case 3:
                System.out.print("Enter the worker gender : ");
                inputChar = VScan.getChar();
                workerToBeModified.setGender(inputChar);
                break;
            case 4:
                System.out.print("Enter the worker phone number : ");
                inputString = VScan.getString();
                workerToBeModified.setPhoneNumber(inputString);
                break;
            case 5:
                System.out.print("Enter the worker salary : ");
                inputDouble = VScan.getDouble();
                workerToBeModified.setSalary(inputDouble);
                break;
            case 6:
                System.out.print("Enter the worker designation : ");
                inputString = VScan.getString();
                workerToBeModified.setDesignation(inputString);
                break;
            default:
                System.out.println("Invalid value!");
        }
    }

    //Search Staff Record
    public ArrayList<Worker> searchStaff(){
        String searchInput;
        ArrayList<Worker> employeeList = Staff.getEmployeeList();
        ArrayList<Worker> result = new ArrayList<>();

        System.out.println("1. Worker ID");
        System.out.println("2. Worker Name");
        System.out.println("3. Worker Gender");
        System.out.println("4. Worker Designation");
        System.out.println("Select the search criteria : ");
        int choice = VScan.getInt();

        switch(choice) {
            case 1:
                System.out.print("Enter the worker Id : ");
                searchInput = VScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (employeeList.get(i).getStaffID().contains(searchInput))
                        result.add(employeeList.get(i));
                }
                break;
            case 2:
                System.out.print("Enter the name : ");
                searchInput = VScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (employeeList.get(i).getName().contains(searchInput))
                        result.add(employeeList.get(i));
                }
                break;
            case 3:
                System.out.print("Enter the gender : ");
                searchInput = VScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getGender()))
                        result.add(employeeList.get(i));
                }
                break;
            case 4:
                System.out.print("Enter the designation : ");
                searchInput = VScan.getString();

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
