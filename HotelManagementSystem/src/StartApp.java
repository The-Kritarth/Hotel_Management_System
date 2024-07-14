import java.util.Scanner;

import Backend.DBHome;
import Frontend.Home;


public class StartApp {
	public static void main(String[] args)
	{
		try
		{
			DBHome.dbConnect();
			Home.UI();
		} 
		catch (Exception e)
		{

			System.out.println(e.getMessage());
		} 
		finally
		{
			try
			{
				DBHome.dbDisconnect();
			} 
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}


