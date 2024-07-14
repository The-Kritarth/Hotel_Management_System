package Frontend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import Backend.DBHome;
import Backend.allQuery;
import businessLogic.Validation;

public class HotelManager {
	
	static HashMap<String,String> map=mapClass.getResult();
	static allQuery al=new allQuery();
	
	static void fetchUser(String str) throws NumberFormatException, SQLException {
		System.out.println("\t\t_______*** List of "+map.get(str)+" ***_______\n\n");
		ResultSet data;
		data=al.fetchUserdata(DBHome.getCon(),Integer.parseInt(str));
		while(data.next()) {
			System.out.println("|\tEmp ID\t|\tEmp Name\t\t|\tAge\t|\tAadhar Number\t|\tPhone Number\t|");
			System.out.println("|\t"+data.getInt("emp_id")+"\t|\t"+data.getString("emp_name")+"\t\t|\t"+data.getInt("age")+"\t|\t"+data.getString("aadhar_no")+"\t|\t"+data.getString("phone_no")+"\t|");
		}

	}
	static int addEmployee(String str) throws NumberFormatException, SQLException {
		System.out.println("_______*** Enter the Data of the Employee ***_______");
		
		String name="",password="",gender="",aadharNo="",phoneNo="";
		int age=0,valid=0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the Name of the Employee:\t");
		name=sc.nextLine();
		System.out.print("Enter the Password of the Employee:\t");
		password=sc.nextLine();
		System.out.print("Enter the Gender of the Employee(Male/Female/Other):\t");
		gender=sc.nextLine();
		System.out.print("Enter the Age of the Employee:\t");
		age=sc.nextInt();
		sc.nextLine();		
		
		do {
			System.out.print("Enter the Aadhar Card No. of the Employee:\t");
			aadharNo=sc.nextLine();
			valid=Validation.aadharValid(aadharNo);
			
			if(valid==5)
				return 0;
		}while(valid==1);
		do {
			System.out.print("Enter the Phone No. of the Employee:\t");
			phoneNo=sc.nextLine();
			valid=Validation.phoneValid(phoneNo);
			
			if(valid==5)
				return 0;
		}while(valid==1);
		
		al.insertEmpData(DBHome.getCon(),name,password,gender,age,aadharNo,phoneNo,Integer.parseInt(str));

		return 1;
//		addEmployeeData(firstName,lastName,gender,age,aadharNo,phoneNo,str);
		
		
	}
	static int deleteEmployee(String str) throws SQLException {
		System.out.println("_______*** Employee Data Deletion ***_______");
		int empId=0,rechoose=0;
		Scanner sc=new Scanner(System.in);
		
		do {
			System.out.println("Enter the Employee Id");
			empId=sc.nextInt();sc.nextLine();
			
			System.out.println("Press 1 to confirm, press 2 to re-enter the Employee Id or press any other key to exit:");
			rechoose=sc.nextInt();sc.nextLine();
			
			if(rechoose == 1) {
				al.deleteEmpData(DBHome.getCon(),empId);
//				deleteEmployeeData(empId);
				return 1;
			}
				
		}while(rechoose == 2);
		
		return 1;	
	}
	static void review(String str) throws NumberFormatException, SQLException {
		int choice=0,rechoose=0;
		Scanner sc=new Scanner(System.in);
		
		do {
			System.out.println("Enter Your Choice:\n"
					+ "1. Get the List of "+map.get(str)+"\n"
					+ "2. Add "+map.get(str)+"\n"
					+ "3. Delete "+map.get(str));
			choice=sc.nextInt();
			
			switch(choice) {
			case 1: fetchUser(str);break;
			case 2: addEmployee(str);break;
			case 3: deleteEmployee(str);break;
			default:System.out.println("Wrong Choice, press 1 to rechoose or press anyother key");
					rechoose=sc.nextInt();
			}
		}while(rechoose==1);
		
	}
	public static void main() throws NumberFormatException, SQLException {
		Scanner sc= new Scanner(System.in);
		int choice=0, rechoose=0;
		
		
		do {
			rechoose=0;
			System.out.println("Enter the choice:\n"
					+ "1. Receptionists\n"
					+ "2. Housekeeping Staff\n"
					+ "3. Technical Staff\n"
					+ "4. Feedbacks\n"
					);
			choice=sc.nextInt();
			switch(choice) {
			case 1: review("2");break;
			case 2: review("5");break;
			case 3: review("6");break;
			case 4: review("Feedbacks");break;
			default:  System.out.println("Wrong Choice\n");		
			}
			
			System.out.println("######\nEnter 1 to rechoose or press any other key to exit:");
			rechoose = sc.nextInt();
			choice=0;
		}while(rechoose==1);
		
		sc.close();
	}
}
