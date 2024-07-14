package Frontend;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class Home {
	 
	
	 
     public static void UI() throws SQLException, ParseException {
    	 System.out.println("_______________ Welcome to U-ROKA INN __________________\n\n");
    	 
    	 System.out.println("                            LOGIN                             \n\n");
    	 
    	 Scanner sc=new Scanner(System.in);
    	 
    	 showOptions();
    	 
    	 System.out.print("Enter your choice...     ");
    	 int choice=sc.nextInt();
    	 
    	 System.out.println("Enter your user_ID");
    	 int user_id=sc.nextInt();
    	 sc.nextLine();
    	 System.out.println("Enter your password");
    	 String passwd=sc.nextLine();
    	 
    	 Login lg=new Login(user_id,passwd,choice);
    	 lg.checkLogin();
     }

	private static void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("0. SuperAdmin Login");
		System.out.println("1. Admin Login");
		System.out.println("2. Receptionist Login");
		System.out.println("3. Hotel Manager Login");
		System.out.println("4. Restaurant Manager Login");
		System.out.println("5. Customer Login");
		System.out.println("6. Exit");
		
	}
}
