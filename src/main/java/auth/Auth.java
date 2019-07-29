package auth;

import java.util.Scanner;
import staff.*;

public class Auth {

    //If it is null means no login
    //Else means a user has login
    //public static Staff s = null;
    public static Staff s = new Staff("0001","123","Chun",'M',"Cashier",2019,2,24);

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
            System.out.print("Do you want to log out ? > ");
            if(in.nextLine().equals("y"))
                s = null;
        }

        System.out.println("Press any key to continue :)");
        in.nextLine();
        return;
    }

    public static boolean login(String id, String password) {
        for(int i = 0; i < Staff.employeeList.size(); i++){
            Staff tempStaff = Staff.employeeList.get(i);
            if(tempStaff.getStaffID().equals(id) && tempStaff.getPassword().equals(password)){
                System.out.println("Authenticated User!");
                s = tempStaff;
                return true;
            }
        }
        System.out.println("Authentication Failed. Please Try Again.");
        return false;
    }
}
