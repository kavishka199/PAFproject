package com;

import model.items;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Items")
public class itemService {
	
	items itemObj = new items();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		
		return itemObj.readItems();
		
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertItems(@FormParam("itemCode") String itemCode,
							@FormParam("itemName") String itemName,
							@FormParam("itemPrice") String itemPrice,
							@FormParam("itemDesc") String itemDesc)
	{
		
		String output = itemObj.insertItems(itemCode, itemName, itemPrice, itemDesc);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItems(String itemData)
	{
		//Convert input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read values from JSON object
		String itemID = itemObject.get("itemID").getAsString();
		String itemCode = itemObject.get("itemCode").getAsString();
		String itemName = itemObject.get("itemName").getAsString();
		String itemPrice = itemObject.get("itemPrice").getAsString();
		String itemDesc = itemObject.get("itemDesc").getAsString();
		
		String output = itemObj.updateItem(itemID, itemCode, itemName, itemPrice, itemDesc);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItems(String itemData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String itemID = doc.select("itemID").text();
		
		String output = itemObj.deleteItem(itemID);
		return output;
		
	}

}
