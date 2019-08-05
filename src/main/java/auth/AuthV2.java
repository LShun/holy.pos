package auth;

import staff.Staff;
import staff.Worker;
import pub.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class AuthV2 {
    private Worker session = null;
    private static LocalDateTime clockInTime;
    private static LocalDateTime clockOutTime;

    public void startSession(Worker w) {
        clockInTime = LocalDateTime.now();
        this.session = w;
    }

    public void endSession() {
        LocalDateTime clockOutTime = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(clockInTime);

        long hrs = tempDateTime.until(clockOutTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours( hrs );

        long min = tempDateTime.until(clockOutTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes( min );

        long secs = tempDateTime.until(clockOutTime, ChronoUnit.SECONDS);


        System.out.println("You worked for Hours: " + hrs + "Minutes: " + min + "Seconds: " + secs);
        // write back into Worker
        this.session.setTotalDurationWorked(this.session.getTotalDurationWorked().plusHours(hrs).plusMinutes(min).plusSeconds(secs));
        this.session = null;
    }

    public void auth() {
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
            if (attempt == null) {
                error++;
            }
        } while (error < TRIES && attempt == null);

        if (error >= TRIES) {
            System.out.println("Wrong information entered for 3 times, sending back to Main Menu.\n");
        }
        else {
            startSession(attempt);
        }
    }

    private static Worker login(String id, String password) {
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

    public static LocalDateTime getClockOutTime() {
        return clockOutTime;
    }

    public static void setClockOutTime(LocalDateTime clockOutTime) {
        AuthV2.clockOutTime = clockOutTime;
    }
}