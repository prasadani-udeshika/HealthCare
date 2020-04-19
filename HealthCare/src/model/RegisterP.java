package model;

import java.sql.*;

public class RegisterP {

	private Connection connect()  
	{
		Connection con = null; 
	
	 
	  try   
	  {   
		  Class.forName("com.mysql.jdbc.Driver"); 
		  //Provide the correct details: DBServer/DBName, username, password    
		  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");   
	 }
	  catch (Exception e)  
	  {
		  e.printStackTrace();
	  }
	 
	  return con; 
	}
	
	public String insertRDetails(String UName, String NIC, String userPhon, String email, String Address, String creditNo, String passW)  
	 {
		 String output = ""; 
	 
	 try  
	 {    
		 Connection con = connect(); 
	 
	 
	   if (con == null)   
	   {
		   return "Error while connecting to the database for inserting.";
		    
	   }
	 
	   // create a prepared statement    
	   String query = " insert into registerpd(`UName`,`NIC`,`userPhon`,`email`,`Address`,`creditNo`,`passW`)"+ " values (?, ?, ?, ?, ?,?,?)"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setString(1,UName );
	   preparedStmt.setString(2,NIC );
	   preparedStmt.setString(3,userPhon );
	   preparedStmt.setString(4,email );
	   preparedStmt.setString(5,Address );    
	   preparedStmt.setString(6, creditNo);       
	   preparedStmt.setString(7, passW);
	   
	   // execute the statement   
	   preparedStmt.execute();   
	   con.close(); 
	   
	   output = "Registration  successfully";   
	  }  
	   catch (Exception e)   
	  {
		   output = "Try again Entered details not valid.";  
		   System.err.println(e.getMessage());  
		   
	  }
	 
	  return output;  
	  } 
	// Read Registered Details 
	public String readRDetails() 
	{
		 String output = ""; 
	 
	 
	    try
	    {
	    	Connection con=connect();
	 
	       
		if (con == null) 
	       {
		      return "Error while connecting to the database for reading";
		   } 
	 
	   // Prepare the html table to be displayed
	        output = "<table border=\"1\">"
	        + "<tr><th>User Name</th><th>User NIC</th><th>User Phone</th><th>Email</th><th>Address   "
	   		+ "</th><th>Credit No          "
	   		+ " passW</th><th>Update</th><th>Remove</th></tr>"; 
	 
	        String query = "select * from registerpd";    
	        Statement stmt = con.createStatement();   
	        ResultSet rs = stmt.executeQuery(query);
	   
	  // iterate through the rows in the result set  
	       while (rs.next())    
	       {
	    	   String UName = rs.getString("UName");
	    	   String NIC = rs.getString("NIC");
	    	   String userPhon = rs.getString("userPhon");
		       String email = rs.getString("email");   
		       String Address = rs.getString("Address");       
		       String creditNo = rs.getString("creditNo");
		       String passW = rs.getString("passW");
	   
	   
	    // Add into the html table  
		       output += "<tr><td>" + UName + "</td>";     
		       output += "<td>" + NIC + "</td>";     
		       output += "<td>" + userPhon + "</td>"; 
		       output += "<td>" + email + "</td>"; 
		       output += "<td>" + Address + "</td>"; 
		       output += "<td>" + creditNo + "</td>"; 
		       output += "<td>" + passW + "</td>"; 
	 
	    // buttons     
		       output += "<td><input name=\"btnUpdate\" type=\"button\"        "
		   		+"value=\"Update\" class=\"btn btn-secondary\"></td>"     
				   + "<td><form method=\"post\" action=\"PAppointmentX.jsp\">"      
		   		+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"     "
		   		+ " class=\"btn btn-danger\">"     
		   		+ "<input name=\"NIC\" type=\"hidden\" value=\"" + NIC     
		   		+ "\">" + "</form></td></tr>";    
		    }
	       
	        con.close(); 
	   
	   // Complete the html table    
	         output += "</table>";  
          }
          catch(Exception e)  
          {
    	     output = "Error while reading the Registered Details.";    
    	     System.err.println(e.getMessage());   
          } 
	 
	      return output;
	 
      }
	
	public String updateRDetails(String UName, String NIC, String userPhon, String email, String Address, String creditNo , String passW)  
	  {
		  String output = ""; 
	  
	       try   
	       {
		      Connection con = connect(); 
	 
	          if (con == null)    
	          {
		        return "Error while connecting to the database for updating."; 
		      } 
	 
	   // create a prepared statement    
	       String query = "UPDATE registerpd SET UName=?,userPhon=?,email=?,Address=?,creditNo=?,passW=?WHERE NIC=?"; 
	 
	       PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	       preparedStmt.setString(1, UName);     
	       preparedStmt.setString(2, userPhon);
	       preparedStmt.setString(3, email);
	       preparedStmt.setString(4, Address);
	       preparedStmt.setString(5, creditNo);    
	       preparedStmt.setString(6, passW); 
	       preparedStmt.setString(7, NIC);
	 
	   // execute the statement    
	      preparedStmt.execute();    
	      con.close(); 
	 
	      output = "Registration Details Update successfully";   
	      }   
	      catch (Exception e)   
	      {   
		     output = "Registration Details Not Update.Try again.";    
	         System.err.println(e.getMessage());   
	      } 
	 
	      return output;  
	  }
	
	 // Delete Method
	public String deleteRDetails(String NIC)  
	  {
		  String output = ""; 
	  
	  try  
	  {
		    Connection con = connect(); 
	 
	        if (con == null)    
	        {
	        	return "Error while connecting to the database for deleting."; 
	        } 
	 
	   // create a prepared statement    
	        String query = "delete from registerpd where NIC=?"; 
	 
	        PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	        preparedStmt.setString(1,NIC); 
	 
	   // execute the statement    
	        preparedStmt.execute();    
	        con.close(); 
	 
	        output = " Registration Details Delete  successfully";   
	        }   
	        catch (Exception e)  
	        {    output = "Not Delete. Try again.";    
	            System.err.println(e.getMessage());   
	        } 
	 
	  return output;  
	  } 
	
	//End model class
	
}
