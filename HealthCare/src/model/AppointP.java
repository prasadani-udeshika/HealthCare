package model;

import java.sql.*;

public class AppointP {
	// Start model class
	// Database connection 
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
	
	// End Database connection
	
	 public String insertAppointD(String NoAppoint, String DoctorN, String HospitalN, String userPhon, String Date, String time)  
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
	   String query = " insert into pappointment(`NoAppoint`,`DoctorN`,`HospitalN`,`userPhon`,`Date`,`time`)"+ " values (?,?, ?, ?, ?, ?)"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setString(1, NoAppoint);    
	   preparedStmt.setString(2, DoctorN);    
	   preparedStmt.setString(3, HospitalN);    
	   preparedStmt.setString(4, userPhon);   
	   preparedStmt.setString(5, Date);
	   preparedStmt.setString(6, time);
	   
	   // execute the statement   
	   preparedStmt.execute();   
	   con.close(); 
	   
	   output = "Appointment Booked  successfully";   
	  }  
	   catch (Exception e)   
	  {
		   output = "Try again ..Error.";  
		   System.err.println(e.getMessage());  
		   
	  }
	 
	  return output;  
	  }
	// 
	 
	 public String readAppoint() 
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
		        + "<tr><th>Appointment No </th><th>Doctor Name</th><th>Hospital   "
		   		+ "Name</th><th>User Phon </th><th>Date</th><th>          "
		   		+ " Time</th><th>Update</th><th>Remove</th></tr>"; 
		 
		        String query = "select * from pappointment";    
		        Statement stmt = con.createStatement();   
		        ResultSet rs = stmt.executeQuery(query);
		   
		  // iterate through the rows in the result set  
		       while (rs.next())    
		       {
			       String NoAppoint = rs.getString("NoAppoint");
			       String DoctorN = rs.getString("DoctorN");
			       String HospitalN = rs.getString("HospitalN");   
			       String userPhon = rs.getString("userPhon");       
			       String Date = rs.getString("Date");
			       String time = rs.getString("time");
		   
		   
		    // Add into the html table  
			       output += "<tr><td>" + NoAppoint + "</td>";     
			       output += "<td>" + DoctorN + "</td>";     
			       output += "<td>" + HospitalN + "</td>";    
			       output += "<td>" + userPhon + "</td>";
			       output += "<td>" + Date + "</td>";
			       output += "<td>" + time + "</td>";
		 
		    // buttons     
			       output += "<td><input name=\"btnUpdate\" type=\"button\"        "
			   		+"value=\"Update\" class=\"btn btn-secondary\"></td>"     
					   + "<td><form method=\"post\" action=\"PAppointmentX.jsp\">"      
			   		+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"     "
			   		+ " class=\"btn btn-danger\">"     
			   		+ "<input name=\"NoAppoint\" type=\"hidden\" value=\"" + NoAppoint     
			   		+ "\">" + "</form></td></tr>";    
			    }
		       
		        con.close(); 
		   
		   // Complete the html table    
		         output += "</table>";  
	          }
	          catch(Exception e)  
	          {
	    	     output = "Error while reading the Appointment Details .";    
	    	     System.err.println(e.getMessage());   
	          } 
		 
		      return output;
		 
	      }
	 //  End Reading method 
	 // Start Update method
	 
	 
	 public String updateADetails(String NoAppoint, String DoctorN, String HospitalN, String userPhon, String Date, String time)  
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
	       String query = "UPDATE pappointment SET DoctorN=?,HospitalN=?,userphon=?,Date=?,time=?WHERE NoAppoint=?"; 
	 
	       PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values  
	       preparedStmt.setString(1, DoctorN);    
	       preparedStmt.setString(2, HospitalN); 
	       preparedStmt.setString(3, userPhon); 
	       preparedStmt.setString(4, Date); 
	       preparedStmt.setString(5, time);
	       preparedStmt.setString(6, NoAppoint);
	 
	   // execute the statement    
	      preparedStmt.execute();    
	      con.close(); 
	 
	      output = "Appointment Details  successfully";   
	      }   
	      catch (Exception e)   
	      {   
		     output = "Appointment Details not Correctly updated.Try again";    
	         System.err.println(e.getMessage());   
	      } 
	 
	      return output;  
	  }
	 // Start Delete method
	 
	 public String deleteADetails(String NoAppoint)  
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
	        String query = "delete from pappointment where NoAppoint=?"; 
	 
	        PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	        preparedStmt.setString(1,NoAppoint); 
	 
	   // execute the statement    
	        preparedStmt.execute();   
	        con.close(); 
	 
	        output = "Appointment Details Deleteed successfully";   
	        }   
	        catch (Exception e)  
	        {    output = "Appointment Details Not Deleted .Try again.";    
	            System.err.println(e.getMessage());   
	        } 
	 
	  return output;  
	  } 
	  
	 
	
// End model class 
}
