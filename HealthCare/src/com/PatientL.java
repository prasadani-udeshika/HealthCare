package com;

import model.LogP;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/LogPD")

public class PatientL {

	LogP LogObj = new LogP();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readLogPD()
	{
		return LogObj.readLogPD();
	}
	
	@POST 
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertLogP(@FormParam("userN") String userN,       
			                @FormParam("passW") String passW,    
			                @FormParam("email") String email ) 
	{  
		String output = LogObj.insertLogP(userN, passW,email);  
	    return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateLogP(String LogPData) 
	{
		//Convert the input string to a JSON object 
		JsonObject LogPObject = new JsonParser().parse(LogPData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String userId = LogPObject.get("userId").getAsString(); 
		String userN = LogPObject.get("userN").getAsString(); 
		String passW = LogPObject.get("passW").getAsString();  
		String email = LogPObject.get("email").getAsString();   
		 
		String output = LogObj.updateLogP(userId,userN,passW,email); 
		 
		return output; 
	}
	
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteLogP(String LogPData) 
	{
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(LogPData, "", Parser.xmlParser());     
		
		//Read the value from the element <userId>  
		String userId = doc.select("userId").text(); 
		 
	    String output = LogObj.deleteLogP(userId); 
		 
		 return output;
	} 
	
	
	
}
