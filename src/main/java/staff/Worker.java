package staff;

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

    public Worker() {
        this("", "", "", ' ', "", 0.0, "", 2000, 0, 0, 0, 0);
    }

    public Worker(String staffID, String password, String sName, char gender, String phoneNumber, double salary,
                  String designation, LocalDate dateOfEmployed, int totalDurationWorked, int salesReceived) {
        this.setStaffID(staffID);
        this.setPassword(password);
        this.setName(sName);
        this.setGender(gender);
        this.setPhoneNumber(phoneNumber);
        this.setSalary(salary);
        this.setDesignation(designation);
        this.dateOfEmployed = dateOfEmployed;
        this.setTotalDurationWorked(Duration.ofHours(totalDurationWorked));
        this.setSalesReceived(salesReceived);
    }

    public Worker(String staffID, String password, String sName, char gender, String phoneNumber, double salary,
                  String designation, int year, int month, int dayOfMonth, int totalDurationWorked, int salesReceived) {
        this(staffID, password, sName, gender, phoneNumber, salary, designation, LocalDate.of(year, month, dayOfMonth), totalDurationWorked, salesReceived);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public void addStaff() {
        System.out.println("You have no permission to perform this action.");
        return;
    }

    public void displayStaff(ArrayList<Worker> data) {
        System.out.println("You have no permission to perform this action.");
        return;
    }

    public void modifyStaff() {
        System.out.println("You have no permission to perform this action.");
        return;
    }

    public ArrayList<Worker> searchStaff() {
        System.out.println("You have no permission to perform this action.");
        return null;
    }
}







