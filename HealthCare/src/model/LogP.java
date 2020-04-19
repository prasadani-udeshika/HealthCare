package model;

import java.sql.*;

public class LogP {
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

	public String insertLogP(String userN, String passW, String email)  
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
	   String query = " insert into login(`userId`,`userN`,`passW`,`email`)"+ " values (?, ?, ?, ?)"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setInt(1, 0);    
	   preparedStmt.setString(2, userN);    
	   preparedStmt.setString(3, passW);       
	   preparedStmt.setString(4, email);
	   
	   // execute the statement   
	   preparedStmt.execute();   
	   con.close(); 
	   
	   output = "Inserted successfully";   
	  }  
	   catch (Exception e)   
	  {
		   output = "Error while inserting the item.";  
		   System.err.println(e.getMessage());  
		   
	  }
	 
	  return output;  
	  } 
	
	public String readLogPD() 
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
	        + "<tr><th>User ID </th><th>User Name</th><th>User PassWord</th>   "
	   		+ "<th>User          "
	   		+ " Email</th><th>Update</th><th>Remove</th></tr>"; 
	 
	        String query = "select * from login";    
	        Statement stmt = con.createStatement();   
	        ResultSet rs = stmt.executeQuery(query);
	   
	  // iterate through the rows in the result set  
	       while (rs.next())    
	       {
		       String userId = Integer.toString(rs.getInt("userId"));  
		       String userN = rs.getString("userN");   
		       String passW = rs.getString("passW");       
		       String email = rs.getString("email"); 
	   
	   
	    // Add into the html table  
		       output += "<tr><td>" + userId + "</td>";     
		       output += "<td>" + userN + "</td>";     
		       output += "<td>" + passW + "</td>";    
		       output += "<td>" + email + "</td>"; 
	 
	    // buttons     
		       output += "<td><input name=\"btnUpdate\" type=\"button\"        "
		   		+"value=\"Update\" class=\"btn btn-secondary\"></td>"     
				   + "<td><form method=\"post\" action=\"PAppointmentX.jsp\">"      
		   		+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"     "
		   		+ " class=\"btn btn-danger\">"     
		   		+ "<input name=\"userId\" type=\"hidden\" value=\"" + userId     
		   		+ "\">" + "</form></td></tr>";    
		    }
	       
	        con.close(); 
	   
	   // Complete the html table    
	         output += "</table>";  
          }
          catch(Exception e)  
          {
    	     output = "Error while reading the items.";    
    	     System.err.println(e.getMessage());   
          } 
	 
	      return output;
	 
      }
	
	 public String updateLogP(String userId, String userN, String passW, String email)  
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
	       String query = "UPDATE login SET userN=?,passW=?,email=?WHERE userId=?"; 
	 
	       PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	       preparedStmt.setString(1, userN);    
	       preparedStmt.setString(2, passW);       
	       preparedStmt.setString(3, email);    
	       preparedStmt.setInt(4, Integer.parseInt(userId)); 
	 
	   // execute the statement    
	      preparedStmt.execute();    
	      con.close(); 
	 
	      output = "Updated successfully";   
	      }   
	      catch (Exception e)   
	      {   
		     output = "Error while updating the item.";    
	         System.err.println(e.getMessage());   
	      } 
	 
	      return output;  
	  }
	
	 public String deleteLogP(String userId)  
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
	        String query = "delete from login where userId=?"; 
	 
	        PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	        preparedStmt.setInt(1, Integer.parseInt(userId)); 
	 
	   // execute the statement    
	        preparedStmt.execute();   
	        con.close(); 
	 
	        output = "Deleted successfully";   
	        }   
	        catch (Exception e)  
	        {    output = "Error while deleting the item.";    
	            System.err.println(e.getMessage());   
	        } 
	 
	  return output;  
	  } 
	  
	 
	

}
