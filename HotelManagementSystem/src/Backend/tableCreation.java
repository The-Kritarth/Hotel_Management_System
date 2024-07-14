package Backend;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class roomAvailability{
	private int roomId;
	private int roomSize;
	private String roomType;
	private int availability;
	private int price;
	
	
	
	public roomAvailability(int roomId,int roomSize, String roomType, int availability,int price) {
		super();
		this.roomId=roomId;
		this.roomSize = roomSize;
		this.roomType = roomType;
		this.availability = availability;
		this.price=price;
	}
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public int getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public int getPrice() {
		
		// TODO Auto-generated method stub
		return  price;
	}
	
}

class foodItem{
	private int foodId;
	private String foodName;
	private int price;
	public foodItem(int foodId, String foodName, int price) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}

class roleCategory{
	private int role_id;
	private String role_name;
	public roleCategory(int role_id, String role_name) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
}

public class tableCreation {
	
	
	private static boolean tableExistsSQL(Connection con ,String tableName) throws SQLException {
		DatabaseMetaData meta=con.getMetaData();
		ResultSet resultSet=meta.getTables(null, null, tableName, new String[] {"TABLE"});
		return resultSet.next();
	}
	
   
    public static void createTable(Connection con) throws SQLException {
		
		//user table 
		if(!tableExistsSQL(con,"hotel_customer")) {
			String query="create table hotel_customer(user_id int primary key auto_increment,passwd varchar(200) not null,user_name varchar(200) not null,age int not null,aadhar_no varchar(16) not null,phone_no varchar(10) not null,Gender varchar(10) not null,amount int default 0,room_id int not null,check_in_date varchar(15),foreign key(room_id) references roomAvailability(room_id))";
			Statement st=con.createStatement();
		    st.execute(query);
		    
        }
		if(!tableExistsSQL(con,"role_category")) {
			String query="create table role_category(role_id int primary key auto_increment,role_name varchar(30) not null)";
			Statement st=con.createStatement();
		    st.execute(query);
		    insertRoleData(con);
		    
		    
		}
		if(!tableExistsSQL(con,"order_food")) {
			String query="create table order_food(food_id int primary key auto_increment,food_name varchar(30) not null,price int not null)";
			Statement st=con.createStatement();
		    st.execute(query);
		}
		if(!tableExistsSQL(con,"Employee_Data")) {
			String query="create table Employee_Data(Emp_id int primary key auto_increment,passwd varchar(200),Emp_name varchar(200) not null,age int not null,aadhar_no varchar(16) not null,phone_no varchar(10) not null,Gender varchar(10) not null,Emp_role int not null,foreign key(Emp_role) references role_category(role_id))";
			Statement st=con.createStatement();
		    st.execute(query);
		}
		
	}
    
    
    public static void insertStaticData(Connection con) throws SQLException{
    	
    	if(!tableExistsSQL(con,"roomavailability")) {
    	String query="create table roomAvailability(room_id int primary key auto_increment,room_size int not null,room_type varchar(30) not null,available int not null,price int not null)";
		Statement st=con.createStatement();
		st.execute(query);
    	query="insert into roomavailability(room_id,room_size,room_type,available,price) values(?,?,?,?,?)";
    	List<roomAvailability> li=new ArrayList<>();
    	li.add(new roomAvailability(101,1,"AC",1,3000));
    	li.add(new roomAvailability(102,1,"AC",1,3000));
    	li.add(new roomAvailability(103,1,"AC",1,3000));
    	li.add(new roomAvailability(104,1,"NonAC",1,2500));
    	li.add(new roomAvailability(105,1,"NonAC",1,2500));
    	li.add(new roomAvailability(106,1,"NonAC",1,2500));
    	li.add(new roomAvailability(201,2,"AC",1,4500));
    	li.add(new roomAvailability(202,2,"NonAC",1,4000));
    	li.add(new roomAvailability(203,2,"AC",1,4500));
    	li.add(new roomAvailability(204,2,"NonAC",1,4000));
    	li.add(new roomAvailability(205,2,"AC",1,4500));
    	li.add(new roomAvailability(206,2,"NonAC",1,4000));
    	li.add(new roomAvailability(301,3,"AC",1,6000));
    	li.add(new roomAvailability(302,3,"NonAC",1,5500));
    	li.add(new roomAvailability(303,3,"AC",1,6000));
    	li.add(new roomAvailability(304,3,"NonAC",1,5500));
    	li.add(new roomAvailability(305,3,"AC",1,6000));
    	li.add(new roomAvailability(306,3,"NonAC",1,5500));
    	li.add(new roomAvailability(401,4,"AC",1,7000));
    	li.add(new roomAvailability(402,4,"NonAC",1,6500));
    	li.add(new roomAvailability(403,4,"AC",1,7000));
    	li.add(new roomAvailability(404,4,"NonAC",1,6500));
    	li.add(new roomAvailability(405,4,"AC",1,7000));
    	li.add(new roomAvailability(406,4,"NonAC",1,6500));
    	
    	
    	PreparedStatement psmt=con.prepareStatement(query);
    	for(int i=0;i<li.size();i++) {
    		psmt.setInt(1, li.get(i).getRoomId());
    		psmt.setInt(2, li.get(i).getRoomSize());
    		psmt.setString(3, li.get(i).getRoomType());
    		psmt.setInt(4, li.get(i).getAvailability());
    		psmt.setInt(5, li.get(i).getPrice());
    		psmt.execute();
    	}
    }
    	
    	
    }
    
 public static void insertFoodData(Connection con) throws SQLException{
    	
    	if(tableExistsSQL(con,"order_food")) {
			String localQuery="truncate table order_food";
			Statement st=con.createStatement();
			st.execute(localQuery);
			
		}
    	
    	String query="insert into order_food(food_id,food_name,price) values(?,?,?)";
    	List<foodItem> li=new ArrayList<>();
    	li.add(new foodItem(101,"Samosa",20));
    	li.add(new foodItem(102,"Chole Bhatura",70));
    	li.add(new foodItem(103,"Mini Thali",120));
    	li.add(new foodItem(104,"Full Meal",220));
    	li.add(new foodItem(105,"Non Veg Thali",320));
    	li.add(new foodItem(106,"Desert",120));
    	li.add(new foodItem(107,"Icecream",20));
    	
    	
    	
    	
    	PreparedStatement psmt=con.prepareStatement(query);
    	for(int i=0;i<li.size();i++) {
    		psmt.setInt(1, li.get(i).getFoodId());
    		psmt.setString(2, li.get(i).getFoodName());
    		psmt.setInt(3, li.get(i).getPrice());
    		psmt.execute();
    	}
    	
    	
    }
    
 
 public static void insertRoleData(Connection con) throws SQLException{
 	
 	
 	String query="insert into role_category(role_id,role_name) values(?,?)";
 	List<roleCategory> li=new ArrayList<>();
 	li.add(new roleCategory(1,"Admin"));
 	li.add(new roleCategory(2,"Receptionist"));
 	li.add(new roleCategory(3,"Hotel Manager"));
 	li.add(new roleCategory(4,"Restaurent Manager"));
 	li.add(new roleCategory(5,"Housekeeping Staff"));
 	li.add(new roleCategory(6,"Technical Staff"));
 	li.add(new roleCategory(7,"Chefs"));
 	li.add(new roleCategory(8,"Waiters"));
 	
 	
 	 	
 	
 	
 	
 	PreparedStatement psmt=con.prepareStatement(query);
 	for(int i=0;i<li.size();i++) {
 		psmt.setInt(1, li.get(i).getRole_id());
 		psmt.setString(2, li.get(i).getRole_name());
 		//psmt.setInt(3, li.get(i).getPrice());
 		psmt.execute();
 	}
 	
 	
 }
     
    
    
    
    
}
