package staff;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;

import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import pub.VScan;
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
        AsciiTable at = new AsciiTable();
        at.addRule();
        CWC_FixedWidth width = new CWC_FixedWidth();
        at.getRenderer().setCWC(width);
        width.add(8).add(16).add(3).add(25); //Specify the width of each column

        //Display the heading
        AT_Row heading = at.addRow("STAFF ID", "STAFF NAME", "SEX", "DATE OF EMPLOYED");
        heading.setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        for(int i = 0; i< data.size(); i++){
            Worker temp = data.get(i);
            AT_Row row = at.addRow(temp.getStaffID(), temp.getName(), temp.getGender(), temp.getDateOfEmployed().toString());
            row.getCells().get(0).getContext().setTextAlignment(TextAlignment.RIGHT);
            row.getCells().get(2).getContext().setTextAlignment(TextAlignment.CENTER);
            at.addRule();
        }

        System.out.println(at.render());
    }

    public void modifyStaff(){
        ArrayList<Worker> data = searchStaff();

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
        System.out.println("4. Worker Designation");
        System.out.print("Select which one the modify : ");
        int choice = VScan.getInt();
        String inputString;
        char inputChar;

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
            case 4:
                System.out.print("Enter the worker designation : ");
                inputString = VScan.getString();
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
        int choice = VScan.getInt();

        switch(choice) {
            case 1:
                System.out.print("Enter the worker Id : ");
                searchInput = VScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getStaffID()))
                        result.add(employeeList.get(i));
                }
                break;
            case 2:
                System.out.print("Enter the worker name : ");
                searchInput = VScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getName()))
                        result.add(employeeList.get(i));
                }
                break;
            case 3:
                System.out.print("Enter the worker gender : ");
                searchInput = VScan.getString();

                for (int i = 0; i < employeeList.size(); i++) {
                    if (searchInput.equals(employeeList.get(i).getGender()))
                        result.add(employeeList.get(i));
                }
                break;
            case 4:
                System.out.print("Enter the worker designation : ");
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
