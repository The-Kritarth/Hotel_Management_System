package Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.xdevapi.PreparableStatement;
import services.crud;


public class allQuery implements crud {
    
	public boolean login(Connection con,int user_id,String password,int choice) throws SQLException {
		
		String query;
		System.out.println(choice);
		
		if(choice!=5) {
		    query="Select * from employee_data where emp_id=? AND passwd=? AND emp_role=?";}
		else {
			query="select * from hotel_customer where room_id=? AND passwd=?";
		}
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setInt(1, user_id);
		psmt.setString(2, password);
		if(choice!=5)
		    psmt.setInt(3, choice);
		
		ResultSet resultSet=psmt.executeQuery();
		int count=0;
		while(resultSet.next()) {
			count++;
		}
		
		
		return count==1;
		
	}
	
	
	
	public void insertEmpData(Connection con,String emp_name,String password,String gender,int age,String aadhar_no,String phone_no,int emp_role) throws SQLException {
		String query="insert into employee_data(passwd,emp_name,age,aadhar_no,phone_no,gender,emp_role) values (?,?,?,?,?,?,?)";
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setString(1, password);
		psmt.setString(2, emp_name);
		psmt.setInt(3, age);
		psmt.setString(4, aadhar_no);
		psmt.setString(5, phone_no);
        psmt.setString(6, gender);
        psmt.setInt(7, emp_role);
        
        psmt.execute();
        
        System.out.println("Data Added Successfully");
        
		
	
	}
	

	public void deleteEmpData(Connection con,int id) throws SQLException {
		// TODO Auto-generated method stub
		String query="delete from employee_data where emp_id=?";
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setInt(1, id);
		
		psmt.execute();
		
		System.out.println("Admin data Deleted Successfully");
		
	}


    public ResultSet fetchUserdata(Connection con,int emp_role) throws SQLException {
    	String qeury="select * from employee_data where emp_role=?";
    	
    	PreparedStatement psmt=con.prepareStatement(qeury);
    	psmt.setInt(1, emp_role);
    	
    	ResultSet resultSet=psmt.executeQuery();
    	
    	return resultSet;
    	
    }
    
    public  ResultSet checkRoomAvailability(Connection con,int room_size,String room_type) throws SQLException {
    	
    	String query="select * from roomavailability where room_size=? AND room_type=? AND available=1";
    	PreparedStatement psmt=con.prepareStatement(query);
    	psmt.setInt(1, room_size);
    	psmt.setString(2, room_type);
    	
    	ResultSet rs=psmt.executeQuery();
    	
		return rs;
    	
    }
    
    public Map<String, Float> getMenu(Connection con) throws SQLException {
    	Map<String,Float> mp=new HashMap<>();
    	String query="select * from order_food";
    	PreparedStatement psmt=con.prepareStatement(query);
    	ResultSet rs=psmt.executeQuery();
    	while(rs.next()) {
    		mp.put(rs.getString("food_name"),(float) rs.getInt("price"));
    	}
		return mp;
    	
    }
    
//    public static ResultSet getCustomerAmountData(Connection con,int roomId) throws SQLException {
//    	
//    	String query="select * from hotel_customer where room_id=?";
//    	PreparedStatement psmt=con.prepareStatement(query);
//    	psmt.setInt(1, roomId);
//    	ResultSet rs=psmt.executeQuery();
//    	//System.out.println(rs.getInt("user_id"));
//    	//System.out.println(rs.getInt("amount"));
//    	
//		//return rs.getInt("amount");
//    	return rs;
//    	
//    }


    public ResultSet roomPriceData(Connection con,int roomNo) throws SQLException {
    	String query="select * from roomavailability where room_id=?";
    	PreparedStatement psmt = con.prepareStatement(query);
    	psmt.setInt(1,roomNo);
    	
    	ResultSet rs=psmt.executeQuery();
    	
    	return rs;
    }
	public void addCustomerData(Connection con,String name, String password, String gender,int age, String aadharNo, String phoneNo,
			int i,int roomId,String check_in_date) throws SQLException {
		// TODO Auto-generated method stub
		
		String query="insert into hotel_customer(passwd,user_name,age,aadhar_no,phone_no,gender,amount,room_id,check_in_date) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setString(1, password);
		psmt.setString(2, name);
		psmt.setInt(3, age);
		psmt.setString(4, aadharNo);
		psmt.setString(5, phoneNo);
		psmt.setString(6, gender);
		psmt.setInt(7, i);
		psmt.setInt(8, roomId);
		psmt.setString(9, check_in_date);
		
		psmt.execute();
		changeAvailabilityData(DBHome.getCon(),roomId,0);
		System.out.println("Data Added Successfully");
		
	}



	public void changeAvailabilityData(Connection con,int roomId,int i) throws SQLException {
		// TODO Auto-generated method stub
			String query="update roomavailability set available=? where room_id=?";
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setInt(1,i);
			psmt.setInt(2, roomId);
			
			psmt.execute();
	}



	public ResultSet fetchCustomerData(Connection con, int roomNo) throws SQLException {
		// TODO Auto-generated method stub
		String query="select * from hotel_customer where room_id=?";
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setInt(1, roomNo);
		ResultSet rs=psmt.executeQuery();
		
		return rs;
	}



	public void deleteCustomerData(Connection con, int roomNo) throws SQLException {
		// TODO Auto-generated method stub
		String query="delete from hotel_customer where room_id=?";
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setInt(1, roomNo);
		
		psmt.execute();
		
		System.out.println("Customer data Deleted Successfully");
		
	}



	public void changePassData(Connection con, int roomNo, String pass) throws SQLException {
		// TODO Auto-generated method stub
		String query="update hotel_customer set passwd=? where room_id=?";
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setString(1, pass);
		psmt.setInt(2, roomNo);
		
		psmt.execute();
		
	}



	public void updateCustomerAmountData(Connection con, int roomNo, float amount) throws SQLException {
		// TODO Auto-generated method stub
		String query="update hotel_customer set amount=amount+? where room_id=?";
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setInt(1, (int) amount);
		psmt.setInt(2, roomNo);
		
		psmt.execute();
		
		System.out.println("Order Successful");
		
	}



	
	
	
	
	
	
	
	
	
	
}
