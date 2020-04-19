package com;

import model.AppointP;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/PatientAppo")
public class PAppointment {

	AppointP AppObj = new AppointP();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return AppObj.readAppoint();
	}
	// start to insert method 
	@POST 
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertAppointD(@FormParam("NoAppoint") String NoAppoint,       
			                @FormParam("DoctorN") String DoctorN,    
			                @FormParam("HospitalN") String HospitalN,     
			                @FormParam("userPhon") String userPhon,
			                @FormParam("Date") String Date,
			                @FormParam("time") String time) 
	{  
		String output = AppObj.insertAppointD(NoAppoint, DoctorN, HospitalN, userPhon,Date,time);  
	    return output; 
	}
	// End insert method
	
	// update method start
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateADetails(String AppoDetails) 
	{
		//Convert the input string to a JSON object 
		JsonObject AppointObject = new JsonParser().parse(AppoDetails).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String NoAppoint = AppointObject.get("NoAppoint").getAsString(); 
		String DoctorN = AppointObject.get("DoctorN").getAsString(); 
		String HospitalN = AppointObject.get("HospitalN").getAsString();  
		String userPhon = AppointObject.get("userPhon").getAsString();  
		String Date = AppointObject.get("Date").getAsString();
		String time = AppointObject.get("time").getAsString();
		 
		String output = AppObj.updateADetails(NoAppoint, DoctorN, HospitalN, userPhon,Date,time); 
		 
		return output; 
	}
	// end update method
	
	//start the delete method
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteADetails(String AppoDetails) 
	{
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(AppoDetails, "", Parser.xmlParser());     
		
		//Read the value from the element <NoAppint>  
		String NoAppoint = doc.select("NoAppoint").text(); 
		 
	    String output = AppObj.deleteADetails(NoAppoint); 
		 
		 return output;
	} 
	
	// End delete method
	
	
	//End main class
}
