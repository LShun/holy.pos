package reporting;

import java.util.Scanner;

public class Reporting {
    public static void reporting() {
       /* System.out.println("Daily Sales Report");
        System.out.println(" ___________________________________________________________________________");
        System.out.println("|___________________________________________________________________________|");*/

       Scanner scanner=new Scanner(System.in);

       int choice=0;

        while (true){
       System.out.println("List of reports: ");
       System.out.println("1. Daily Sales Report");
       System.out.println("2. Monthly Sales Report");
       System.out.println("3. Product Performance Report");
       System.out.println("4. Staff Performance Report");
       System.out.println("5. Tax Report");

       System.out.print("Please select report: ");
       choice = scanner.nextInt();

       if(choice==1)
           new DailySalesReport();
       else if(choice==2)
           new MonthlySalesReport();
       else if(choice==3)
           new ProductPerformanceReport();
       else if(choice==4)
           new StaffPerformanceReport();
       else if(choice==5)
           new TaxReport();
       else
           return;
        }














       // System.out.println("Monthly Sales Report");

        //System.out.println("Product Performance Report");

        //System.out.println("Daily Sales Report");


    }
}
