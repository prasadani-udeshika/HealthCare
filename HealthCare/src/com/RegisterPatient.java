package com;

import model.RegisterP;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/ReDetails")

public class RegisterPatient {
	
	RegisterP RDetails = new RegisterP();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readRDetails()
	{
		return RDetails.readRDetails();
	}
// Start insert method
	
	@POST 
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertRDetails(@FormParam("UName") String UName,       
			                @FormParam("NIC") String NIC,    
			                @FormParam("userPhon") String userPhon,   
			                @FormParam("email") String email,
			                @FormParam("Address") String Address,
			                @FormParam("creditNo") String creditNo,
			                @FormParam("passW") String passW) 
	{  
		String output = RDetails.insertRDetails(UName, NIC, userPhon, email,Address,creditNo,passW);
	    return output; 
	}
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateRDetails(String RegisterData) 
	{
		//Convert the input string to a JSON object 
		JsonObject ReObject = new JsonParser().parse(RegisterData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String UName = ReObject.get("UName").getAsString(); 
		String NIC = ReObject.get("NIC").getAsString(); 
		String userPhon = ReObject.get("userPhon").getAsString();  
		String email = ReObject.get("email").getAsString();  
		String Address = ReObject.get("Address").getAsString();
		String creditNo = ReObject.get("creditNo").getAsString(); 
		String passW = ReObject.get("passW").getAsString(); 
		 
		String output = RDetails.updateRDetails(UName, NIC, userPhon, email, Address,creditNo,passW); 
		 
		return output; 
	}
	// start delete method
	
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteRDetails(String RegisterData) 
	{
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(RegisterData, "", Parser.xmlParser());     
		
		//Read the value from the element <NIC>  
		String NIC = doc.select("NIC").text(); 
		 
	    String output = RDetails.deleteRDetails(NIC); 
		 
		 return output;
	} 
	
	
	// Main class End
}
