package staff;

import pub.VScan;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class Worker {
    private String staffID;
    private String password;
    private String sName;
    private char gender;
    private String phoneNumber;
    private double salary;
    private String designation;
    private LocalDate dateOfEmployed;
    private Duration totalDurationWorked;
    private int salesReceived;

    public Worker(){

        this("","","",' ',"",0.0,"",2000,0,0,0,0);
    }

    public Worker(String staffID, String password, String sName, char gender, String phoneNumber, double salary,
                  String designation, int year, int month, int dayOfMonth, int totalDurationWorked, int salesReceived){
        this.setStaffID(staffID);
        this.setPassword(password);
        this.setName(sName);
        this.setGender(gender);
        this.setPhoneNumber(phoneNumber);
        this.setSalary(salary);
        this.setDesignation(designation);
        this.dateOfEmployed = LocalDate.of(year, month, dayOfMonth);
        this.setTotalDurationWorked(Duration.ofHours(totalDurationWorked));
        this.setSalesReceived(salesReceived);
    }
    
//    public Worker(String staffID, String password, String sName, char gender, String designation){
//        this(staffID, password, sName, gender, designation,  2019, 1, 1, 0, 0);
//    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return sName;
    }

    public void setName(String sName) {
        this.sName = sName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDate getDateOfEmployed() {
        return dateOfEmployed;
    }

    public void setDateOfEmployed(LocalDate dateOfEmployed) {
        this.dateOfEmployed = dateOfEmployed;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public Duration getTotalDurationWorked() {
        return totalDurationWorked;
    }

    public void setTotalDurationWorked(Duration totalDurationWorked) {
        this.totalDurationWorked = totalDurationWorked;
    }

    public int getSalesReceived() {
        return salesReceived;
    }

    public void setSalesReceived(int salesReceived) {
        this.salesReceived = salesReceived;
    }

    public void addStaff(){
        System.out.println("You have no permission to perform this action.");
        return;
    }

    public void displayStaff(ArrayList<Worker> data) {
        System.out.println("You have no permission to perform this action.");
        return;
    }

    public void modifyStaff(){
        System.out.println("1. Worker Name");
        System.out.println("2. Worker Gender");
        System.out.print("Select which one the modify : ");
        int choice = VScan.getInt();
        String inputString;
        char inputChar;

        switch(choice){
            case 1:
                System.out.print("Enter the worker name : ");
                inputString = VScan.getString();
                this.setName(inputString);
                break;
            case 2:
                System.out.print("Enter the worker gender : ");
                inputChar = VScan.getChar();
                this.setGender(inputChar);
                break;
            default:
                System.out.println("Invalid value!");
        }
    }

    public ArrayList<Worker> searchStaff(){
        System.out.println("You have no permission to perform this action.");
        return null;
    }
}







