package auth;

import staff.Staff;
import staff.Worker;
import pub.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Auth {
    private static Worker session = Staff.getEmployeeList().get(0);
    private static LocalDateTime clockInTime = LocalDateTime.now();
    private static LocalDateTime clockOutTime;

    private static void startSession(Worker w) {
        clockInTime = LocalDateTime.now();
        session = w;
        FormatPrint.printHeader("Clock-in time: " + clockInTime.toLocalTime().truncatedTo(ChronoUnit.SECONDS));
    }

    public static void endSession() {
        clockOutTime = LocalDateTime.now();

        LocalDateTime tempDateTime = LocalDateTime.from(clockInTime);

        long hrs = tempDateTime.until(clockOutTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours( hrs );

        long min = tempDateTime.until(clockOutTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes( min );

        long secs = tempDateTime.until(clockOutTime, ChronoUnit.SECONDS);


        FormatPrint.printHeader("Clock-out time: " + clockOutTime.toLocalTime().truncatedTo(ChronoUnit.SECONDS));
        FormatPrint.printHeader("You worked for " + hrs + " Hours, " + min + " Minutes, " + secs + " Seconds");
        // write back into Worker
        session.setTotalDurationWorked(session.getTotalDurationWorked().plusHours(hrs).plusMinutes(min).plusSeconds(secs));
        session = null;
    }

    public static void auth() {
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
        for (Worker matchAttempt : employeeList) {
            if (matchAttempt.getStaffID().equals(id) && matchAttempt.getPassword().equals(password)) {
                FormatPrint.printHeader("Authentication Success!");
                return matchAttempt;
            }
        }
        FormatPrint.printHeader("Authentication Failed. Please Try Again.");
        return null;
    }

    public static Worker getSession() {
        return session;
    }

    public static boolean isManager() {
        if (session.getDesignation().equals("Manager") || session.getDesignation().equals("Holy Manager")) {
            return true;
        }
        else {
            return false;
        }
    }
}