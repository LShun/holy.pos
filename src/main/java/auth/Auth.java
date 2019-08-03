package auth;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDateTime;

import staff.*;

public class Auth {

    //If it is null means no login
    //Else means a user has login
    //public static Worker s = null;
    public static Worker s = Staff.getEmployeeList().get(1);
    public static LocalDateTime clockInTime= LocalDateTime.now();

    public static void auth() {
        Scanner in = new Scanner(System.in);

        if(s == null) {
            //Declaration
            String id, password;
            int error = 0;

            System.out.print("Enter your staff ID > ");
            id       = in.nextLine();
            System.out.print("Enter your password > ");
            password = in.nextLine();

            while(login(id, password) == false) {
                error++;
                if(error == 3){
                    System.out.println("Error more than 3 time!");
                    return;
                }
                System.out.print("Enter your staff ID > ");
                id       = in.nextLine();
                System.out.print("Enter your password > ");
                password = in.nextLine();
            }
        }else{
            Duration durationWorked = Duration.between(clockInTime, LocalDateTime.now());
            long second     = durationWorked.toMillis();
            String duration = String.format("%02d:%02d:%02d", second/3600000, second%36000000/60000, second%36000000%60000/1000);

            System.out.println("You have been working for " + duration);
            System.out.print("Do you want to log out ? > ");
            if(in.nextLine().equalsIgnoreCase("y")) {
                s.setTotalDurationWorked(s.getTotalDurationWorked().plus(durationWorked));
                s = null;
            }
        }

        System.out.println("Press enter to continue :)");
        in.nextLine();
        return;
    }

    public static boolean login(String id, String password) {
        ArrayList<Worker> employeeList = Staff.getEmployeeList();
        for(int i = 0; i < employeeList.size(); i++){
            Worker tempWorker = employeeList.get(i);
            if(tempWorker.getStaffID().equals(id) && tempWorker.getPassword().equals(password)){
                System.out.println("Authenticated User!");
                s = tempWorker;
                return true;
            }
        }
        System.out.println("Authentication Failed. Please Try Again.");
        return false;
    }
}
