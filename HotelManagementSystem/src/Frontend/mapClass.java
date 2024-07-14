package Frontend;

import java.util.HashMap;

public class mapClass {
      public static HashMap<String,String> getResult(){
    	  HashMap<String,String> map=new HashMap<>();
    	  
    	map.put("2", "Admin");
    	map.put("3","Hotel Manager");
  		map.put("4","Restaurant Manager");
  		map.put("2","Receptionists");
  		map.put("5","Housekeeping Staff");
  		map.put("6","Technical Staff");
  		map.put("7","Chefs");
  		map.put("8", "Waiters");
  		
  		
  		return map;
    	  
      }
}
