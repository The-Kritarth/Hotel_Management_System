package Frontend;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.sql.Date;
import Backend.DBHome;
import Backend.allQuery;
import businessLogic.Validation;

public class Receptionist {
	static allQuery al=new allQuery();
	static int checkRoomAvailability() throws SQLException, ParseException {
		System.out.println("_______*** Check the Room Availability ***_______");
		
		int roomSize=0,roomType=0,rechoose=0,roomAvailability=0;
		String roomString;
		Scanner sc=new Scanner(System.in);
		
		do {
			rechoose=0;
			System.out.println("Select the room size:\n"
					+ "1. Single Room\n"
					+ "2. Double Room\n"
					+ "3. Triple Room\n"
					+ "4. Quad Room");
			roomSize=sc.nextInt();
			
			if(roomSize<1 || roomSize>4) {
				System.out.println("Wrong Choice, press 1 to rechoose or press any other key to exit");
				rechoose = sc.nextInt();
				
				if(rechoose !=1)
					return 0;
			}
		}while(rechoose == 1);
		
		do {
			rechoose=0;
			System.out.println("Select the room type:\n"
					+ "1. AC Room\n"
					+ "2. Non-AC Room");
			roomType=sc.nextInt();
			
			if(roomType==1) {
				roomString = "AC";
			}
			else
			{
				roomString="NonAC";
			}
			if(roomType<1 || roomType>2) {
				System.out.println("Wrong Choice, press 1 to rechoose or press any other key to exit");
				rechoose = sc.nextInt();
				
				if(rechoose != 1)
					return 0;
			}
		}while(rechoose == 1);

		ResultSet room=al.checkRoomAvailability(DBHome.getCon(),roomSize,roomString);
		
		while(room.next()) {
			System.out.println("|\tRoom ID\t|");
			System.out.println("|\t"+room.getInt("room_id")+"\t|");
		}
		System.out.println("Enter Room ID for booking");
		int roomId=sc.nextInt();sc.nextLine();
		bookRoom(roomId);
		
		return 1;
	}
	
	static int bookRoom(int roomId) throws SQLException, ParseException {		
		
		String name="",password="",gender="",aadharNo="",phoneNo="",date="";
		int age=0,valid;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the Name of the Customer:\t");
		name=sc.nextLine();
		System.out.print("Enter the Password of the Customer:\t");
		password=sc.nextLine();
		System.out.print("Enter the Gender of the Customer(Male/Female/Other):\t");
		gender=sc.nextLine();
		System.out.print("Enter the Age of the Customer:\t");
		age=sc.nextInt();
		sc.nextLine();	
		do {
			System.out.print("Enter the Aadhar Card No. of the Customer:\t");
			aadharNo=sc.nextLine();
			valid=Validation.aadharValid(aadharNo);
			
			if(valid==5)
				return 0;
		}while(valid==1);
		do {
			System.out.print("Enter the Phone No. of the Customer:\t");
			phoneNo=sc.nextLine();
			valid=Validation.phoneValid(phoneNo);
			
			if(valid==5)
				return 0;
		}while(valid==1);
		
		System.out.print("Enter the Check-in Date:\t");
		String checkInDate=sc.nextLine();
		al.addCustomerData(DBHome.getCon(),name,password,gender,age,aadharNo,phoneNo,0,roomId,checkInDate);
		return 0;
	}
	
	
	
	static int checkout() throws SQLException {
		System.out.println("\t\t_______*** Checkout the Customer ***_______\n\n");
		
		int roomNo=0,rechoose=0,daysOfStay=0,totAmount=0,amount = 0,price=0;
		String customerData="";
		Scanner sc=new Scanner(System.in);
		
		do {
			System.out.println("Enter the room number to be checked out:");
			roomNo=sc.nextInt();sc.nextLine();
//			System.out.println("Enter the Check-out Date:");
//			checkOutDate=sc.nextLine();
			ResultSet data=al.fetchCustomerData(DBHome.getCon(),roomNo);
			while(data.next()) {
				amount=data.getInt("amount");
			}
			System.out.println(data);
			
			
//			String checkInDate=to_Stringdata.getDate("check_in_date");
			//System.out.println( data.getString("check_in_date"));
			System.out.println("Enter the Number of Days of Stay:");
			daysOfStay=sc.nextInt();sc.nextLine();
			
			ResultSet d=al.roomPriceData(DBHome.getCon(),roomNo);
			while(d.next()) {
				price=d.getInt("price");}
			totAmount=amount+price*daysOfStay;
			
			System.out.println("Total Amount Payable "+totAmount);
			while(data.next()) {
				System.out.println("|\tUser ID\t|\tEmp Name\t\t|\tAge\t|\tAadhar Number\t|\tPhone Number\t|\\tTotal Amount\\t");
				System.out.println("|\t"+data.getInt("user_id")+"\t|\t"+data.getString("user_name")+"\t\t|\t"+data.getInt("age")+"\t|\t"+data.getString("aadhar_no")+"\t|\t"+data.getString("phone_no")+"\t|\t"+data.getInt("amount")+"\t\t"+data.getString("check_in_date")+"\t");
			}

			
			
			System.out.println("Press 1 to checkout the customer, press 2 to re-enter the room number or press anyother key");
			rechoose=sc.nextInt();
			
			if(rechoose==1) {
				al.changeAvailabilityData(DBHome.getCon(), roomNo, 1);
				al.deleteCustomerData(DBHome.getCon(),roomNo);
				return 1;
			}
		}while(rechoose==2);
		
			
		return 0;

		
	}
	public static void main() throws SQLException, ParseException {
		Scanner sc = new Scanner(System.in);
		int choice=0,rechoose=0;
		
		System.out.println("_______*** Welcome To Receptionist Login ***_______");
		
		do {
			System.out.println("Opt the task you want to do:\n"
					+"1. Check Room Availability\n"
					+"2. Checkout the Customer");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1: checkRoomAvailability();break;
			case 2: checkout();break;
			default: System.out.println("Wrong Choice");
			}
			
			System.out.println("#####\nEnter 1 to rechoose or press any other key");
			rechoose = sc.nextInt();
		}while(rechoose==1);
		
		
	}

}
