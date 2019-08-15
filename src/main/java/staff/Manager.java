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

    //Add Staff Record
    public void addStaff(){

        String staffID, password, sName, designation, phoneNumber, inputDateOfEmployed;
        double salary;
        char gender;
        boolean repeat;
        LocalDate dateOfEmployed;
        Scanner scan = new Scanner(System.in);

        do {
            repeat = false;
            System.out.print("Enter worker Id (XXX to stop): ");
            staffID = VScan.getString();
            ArrayList<Worker> employeeList = Staff.getEmployeeList();
            for (Worker worker : employeeList) {
                if (worker.getStaffID().equals(staffID)) {
                    System.out.println("The Staff ID is already used. Please enter a new one.");
                    repeat = true;
                    break;
                }
            }
        }while(repeat);

        while(!staffID.equals("XXX")) {
            System.out.print("Enter worker password : ");
            password = VScan.getString();

            System.out.print("Enter worker name : ");
            sName = VScan.getString();

            System.out.print("Enter worker gender (M or F) : ");
            gender = VScan.getChar();

            while(gender!='M' && gender!='F'){
                System.out.println("Please enter a valid gender!");
                gender = VScan.getChar();
             
            }

            do {
                System.out.print("Enter the worker phone number (XXX-XXXXXXX): ");
                phoneNumber = VScan.getString();

            }while(!phoneNumber.matches("\\d{3}-\\d{7,}"));

            System.out.print("Enter the worker salary : ");
            salary = VScan.getDouble();

            System.out.print("Enter worker designation : ");
            designation = VScan.getString();

            do {
                repeat = true;
                dateOfEmployed = LocalDate.parse("1970-01-01"); //To get rid of compiler error
                System.out.print("Enter date of employed (dd-mm-yyyy): ");
                inputDateOfEmployed = VScan.getString();
                try {
                    dateOfEmployed = LocalDate.parse(inputDateOfEmployed, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    repeat = false;
                } catch (DateTimeParseException e) {
                    System.out.println(inputDateOfEmployed + " is invalid\nPlease enter a valid date");
                }
            }while(repeat || dateOfEmployed.isAfter(LocalDate.now()));

            Staff.getEmployeeList().add(new Worker(staffID, password, sName, gender, phoneNumber, salary,
                    designation, dateOfEmployed, 0, 0));

            System.out.println("Staff Record Has Been Added Successfully!\n");

            System.out.print("Enter worker Id (XXX to stop): ");
            staffID = VScan.getString();
        }
    }

    //Display Staff Record
    public void displayStaff(ArrayList<Worker> data) {
        AsciiTable at = new AsciiTable();
        CWC_LongestLine width = new CWC_LongestLine();
        at.getRenderer().setCWC(width);

        at.addRule();

        //Display the heading
        AT_Row heading = at.addRow("NO.","STAFF ID", "STAFF NAME", "SEX", "PHONE NUMBER", "SALARY", "DESIGNATION", "DATE OF EMPLOYED");
        heading.setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        for(int i = 0; i< data.size(); i++){
            Worker temp = data.get(i);
            AT_Row row = at.addRow(i+1, temp.getStaffID(), temp.getName(), temp.getGender(),
                    temp.getPhoneNumber(), String.format("%.2f",temp.getSalary()), temp.getDesignation(), temp.getDateOfEmployed().toString());
            row.setPaddingLeftRight(1);
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

        if(data.size() == 0) {
           System.out.println("No Record Found!!!");
            return; //Worker does not exists

        }
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
        System.out.println("3. Worker Phone Number");
        System.out.println("4. Worker Salary");
        System.out.println("5. Worker Designation");
        System.out.print("Select which one to modify : ");

        int choice = VScan.getInt();

        String inputString;
        char inputChar;
        double inputDouble;

        switch(choice){
            case 1:
                System.out.print("Enter the new worker Id : ");
                inputString = VScan.getString();
                boolean repeated = false;
                ArrayList<Worker> employeeList = Staff.getEmployeeList();
                for (Worker worker : employeeList) {
                    if (worker.getStaffID().equals(inputString)) {
                        System.out.println("The Staff ID is already used. Please enter a new one.");
                        repeated = true;
                        break;
                    }
                }
                if(!repeated)
                    workerToBeModified.setStaffID(inputString);
                break;
            case 2:
                System.out.print("Enter the new worker name : ");
                inputString = VScan.getString();
                workerToBeModified.setName(inputString);
                break;
            case 3:
                System.out.print("Enter the new worker phone number : ");
                inputString = VScan.getString();
                if(!inputString.matches("\\d{3}-\\d{7,}"))
                    System.out.println("Invalid phone number format!(xxx-xxxxxxx)");
                else
                    workerToBeModified.setPhoneNumber(inputString);
                break;
            case 4:
                System.out.print("Enter the new worker salary : ");
                inputDouble = VScan.getDouble();
                if(inputDouble < 0)
                    System.out.println("Invalid salary!");
                else
                    workerToBeModified.setSalary(inputDouble);
                break;
            case 5:
                System.out.print("Enter the new worker designation : ");
                inputString = VScan.getString();
                workerToBeModified.setDesignation(inputString);
                break;
            default:
                System.out.println("Invalid value!");
        }

        System.out.printf("\nDone Modifying!!!");
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
        System.out.print("Select the search criteria : ");
        int choice = VScan.getInt();

        switch(choice) {
            case 1:
                System.out.print("Enter the worker Id : ");
                searchInput = VScan.getString();

                for (Worker worker : employeeList) {
                    if (worker.getStaffID().contains(searchInput))
                        result.add(worker);
                }
                break;
            case 2:
                System.out.print("Enter the name : ");
                searchInput = VScan.getString();

                for (Worker worker : employeeList) {
                    if (worker.getName().contains(searchInput))
                        result.add(worker);
                }
                break;
            case 3:
                System.out.print("Enter the gender (M or F): ");
                searchInput = VScan.getString();

                for (Worker worker : employeeList) {
                    if (searchInput.equals(String.valueOf(worker.getGender())))
                        result.add(worker);
                }
                break;
            case 4:
                System.out.print("Enter the designation : ");
                searchInput = VScan.getString();

                for (Worker worker : employeeList) {
                    if (worker.getDesignation().contains(searchInput))
                        result.add(worker);
                }
                break;
            default:
                System.out.println("Invalid value!");
        }
        return result;
    }
}
