package auth;

import staff.Staff;
import staff.Worker;
import pub.*;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class AuthV2 {
    private Worker session = null;
    private static LocalDateTime clockInTime= LocalDateTime.now();

    public void startSession(Worker w) {
        this.session = w;
    }

    public void endSession() {
        this.session = null;
    }

    public void authenticate() {
        final int TRIES = 3;
        String id, password;
        int error = 0;
        Worker attempt;

        FormatPrint.printHeader("Authentication");

        do {
            System.out.print("Enter your STAFF ID: ");
            id = VScan.getString();
            System.out.print("Enter your PASSWORD: ");
            password = VScan.getString();
            attempt = login(id, password);
        } while (error++ < TRIES && attempt == null);

        if (error >= TRIES) {
            System.out.println("Wrong information entered for 3 times, sending back to Main Menu.");
        }
        else {
            startSession(attempt);
        }
    }

    public static Worker login(String id, String password) {
        ArrayList<Worker> employeeList = Staff.getEmployeeList();
        for(int i = 0; i < employeeList.size(); i++){
            Worker matchAttempt = employeeList.get(i);
            if(matchAttempt.getStaffID().equals(id) && matchAttempt.getPassword().equals(password)){
                FormatPrint.printHeader("Authentication Success!");
                return matchAttempt;
            }
        }
        FormatPrint.printHeader("Authentication Failed. Please Try Again.");
        return null;
    }
}