package Frontend;
import java.util.*;

import Backend.DBHome;
import Backend.allQuery;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import services.customerInterface;

public class Customer implements customerInterface{
	static allQuery al=new allQuery();
	private static Map<String, Integer> Orders = new HashMap<String, Integer>();
	private int roomNo;
	
	public Customer(int roomNo){
		this.roomNo=roomNo;
	}
	
	public void changePassword() throws SQLException {
		String pass="",confirmPass="";
		Scanner sc=new Scanner(System.in);
		int rechoose=0;
		
		do {
			System.out.println("Enter the New Password:\t");
			pass=sc.nextLine();
			System.out.println("Re-enter the New Password:\t");
		    confirmPass =sc.nextLine();
		    
		    
		    if(pass==confirmPass) {
		    	System.out.println("Press 1 to confirm the changes, press 5 to retry or press anyother key to exit:");
		    	rechoose=sc.nextInt();sc.nextLine();
		    	al.changePassData(DBHome.getCon(),roomNo,pass);
		    }
		    else{
		    	System.out.println("Entered Passwords don't Match\n"
		    			+ "Press 5 to retry or press any other key");
		    	rechoose=sc.nextInt();sc.nextLine();
		    	
		    }
		}while(rechoose==5);
		
	}
	public void orderFood() throws SQLException {
		String foodDetails = "";
		int foodId=0,foodQty=0,amount=0;
		Scanner sc = new Scanner(System.in);
		
		Map<String, Float> Menu = al.getMenu(DBHome.getCon());
		Menu.forEach((k,v) -> System.out.println(k + "\t\t\t\t\t\t" + v));
		boolean flag=true;
		while(flag) {
			System.out.println();
			System.out.println("Press Add/Remove/Exit/Order");
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();
			switch (choice) {
			case "Add": {
				takingOrder();
				break;
				}
			case "Remove": {
				removingOrder();
				break;
				}
			case "Exit": {
				//go back
				flag=false;
				break;
				}
			case "Order": {
				//provide bill
				flag=false;
				System.out.println("Ya hoo.. Order Placed");
				System.out.println("-----------------------------------------------------------------");
				provideBill(Menu);
				break;
				}
			}
		}
//		foodDetails=foodData();
//		System.out.println("Food Id\tFood Name\tPrice");
//		
//		while(foodDetails.next()) {
//			Display details of the food
//		}
//		
//		System.out.println("Enter the Food Id:");
//		foodId=sc.nextInt();sc.nextLine();
//		System.out.println("Enter the Food Quantity:");
//		foodQty=sc.nextInt();sc.nextLine();
//		
//		amount=getFoodAmountData(foodId)*foodQty;
//		updateCustomerAmountData(roomNo,amount);
	}
	void provideBill(Map<String, Float> Menu) throws SQLException {
		float billAmount = 0,amount=0;
		System.out.println("Item\t\t\tCount\t\t\tTotal");
		for (String key : Orders.keySet()) {
            System.out.println(key + "\t\t\t" + Orders.get(key) + "\t\t\t"  + Menu.get(key));
            billAmount += Orders.get(key)* Menu.get(key);
        }
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Grand Total\t\t\t\t\t\t" + billAmount);
		
		//ResultSet data =allQuery.getCustomerAmountData(DBHome.getCon(),this.roomNo);
		
		
		al.updateCustomerAmountData(DBHome.getCon(),roomNo,billAmount);
		
		
	}
	
	void takingOrder() {
		//asking order from user by taking item no. and count
		System.out.println("Please Enter Item Name and Count");
		String FoodName;
		int Count;
		Scanner scanner = new Scanner(System.in);
		FoodName = scanner.nextLine();scanner.nextLine();
		Count = scanner.nextInt();
		if (Orders.containsKey(FoodName)) {
			Orders.put(FoodName, Orders.get(FoodName) + Count);
        } else {
        	Orders.put(FoodName, Count);
        }
	}
	 void removingOrder() {
		//asking order from user by taking item no. and count
		System.out.println("Please Enter Item Name and Count");
		String FoodName;
		int Count;
		Scanner scanner = new Scanner(System.in);
		FoodName = scanner.nextLine();
		Count = scanner.nextInt();
		if (Orders.get(FoodName) - Count > 0) {
			Orders.put(FoodName, Orders.get(FoodName) - Count);
        } else {
        	Orders.remove(FoodName);
        }
	}
	 public void feedback() {
		String roomFeedback;
        String serviceFeedback;
        String foodFeedback;
        String userName="Kritarth";
        String filePath="feedback.txt";
        
//        userName=getUserNameData(roomNo);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Feedback...");
        System.out.print("1. Room : ");
        roomFeedback = sc.nextLine();
        System.out.print("2. Service : ");
        serviceFeedback = sc.nextLine();
        System.out.print("3. Food : ");
        foodFeedback = sc.nextLine();

        try (FileWriter writer = new FileWriter(filePath, true)) { // 'true' for appending to the file
            writer.write(userName + "--------------------\n");
            writer.write("Room : " + roomFeedback + "\n");
            writer.write("Service : " + serviceFeedback + "\n");
            writer.write("Food : " + foodFeedback + "\n");
            writer.write("\n");
            System.out.println("\n Thank you for your feedback!\n");
        }catch (IOException e) {
            System.out.println("An error occurred while writing feedback.");
            e.printStackTrace();
        }
	}
	public static void main(int roomNo) throws SQLException {
		System.out.println("________*** Customer Interface ***_______");
		int choice=0,rechoose=0;
		Scanner sc=new Scanner(System.in);
		
		do {
			rechoose=0;
			System.out.println("Enter Your Choice:\n"
					+ "1. Change Password\n"
					+ "2. Order Food\n"
					+ "3. Feedback");
			choice = sc.nextInt();
			Customer ct=new Customer(roomNo);
			
			switch(choice) {
			case 1: ct.changePassword();break;
			case 2: ct.orderFood();break;
			case 3: ct.feedback();break;
			default: System.out.println("Wrong choice, press 1 to rechoose or press any other key to exit");
					rechoose=sc.nextInt();sc.nextLine();
			}
			
			choice=0;
		}while(rechoose==1);
		sc.close();
		
	}

}
