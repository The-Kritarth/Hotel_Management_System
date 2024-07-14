package businessLogic;
import java.util.*;

public class Validation {
	static boolean checkNumber(String val) {
		int i;
		for(i=0;i<val.length();i++) {
			if(val.charAt(i)<48 && val.charAt(i)>57) {
				return true;
			}
		}
		return false;
	}
	public static int aadharValid(String val) {
		Scanner sc = new Scanner(System.in);
		int choice=0;
			
		if(val.length()!=12 || checkNumber(val)) {
			System.out.println("Wrong Aadhar No., press 1 to Re-enter or press 5 to exit");
			choice=sc.nextInt();sc.nextLine();	
		}
		return choice;
	}
	public static int phoneValid(String val) {
		Scanner sc = new Scanner(System.in);
		int choice=0;
		
		if(val.length()!=10 || checkNumber(val)) {
			System.out.println("Wrong Phone No., press 1 to Re-enter or press 5 to exit");
			choice=sc.nextInt();sc.nextLine();	
		}
		return choice;
	}
}
