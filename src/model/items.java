package model;

import java.sql.*;

public class items {
	
	public String insertItems(String code, String name, String price, String desc) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for inserting";
				
			}
			
			String query = "insert into items values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,code);
			preparedStmt.setString(3,name);
			preparedStmt.setDouble(4,Double.parseDouble(price));
			preparedStmt.setString(5,desc);
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Inserted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while inserting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String readItems() {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return "Error while connecting to the database for reading";
				
			}
			
			//HTML table
			output = "<table border=\"1\">\r\n"
					+ "		<tr>\r\n"
					+ "			<th>Item Code</th><th>Item Name</th><th>Item Price</th><th>Item Description</th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from items";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String itemID = Integer.toString(rs.getInt("itemId"));
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				String itemDesc = rs.getString("itemDesc");
				
				//add a row into the html table
				output += "<tr>"
						+ "			<td>" +itemCode+ "</td><td>" +itemName+ "</td><td>" +itemPrice+ "</td><td>" +itemDesc+ "</td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='itemID' type='hidden' value='"+itemID+"'><input name='itemCode' type='hidden' value='"+itemCode+"'><input name='itemName' type='hidden' value='"+itemName+"'><input name='itemPrice' type='hidden' value='"+itemPrice+"'><input name='itemDesc' type='hidden' value='"+itemDesc+"'></form></td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='itemID' type='hidden' value='"+itemID+"'><input name='itemCode' type='hidden' value='"+itemCode+"'></form></td>"
						+ "		</tr>";
				
			}
			
			con1.close();
			
			//complete the html table
			output += "</table>";
			
			
			
		}
		catch(Exception e) {
			
			output = "Error while reading the items";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String updateItem(String id, String code, String name, String price, String desc) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update items set itemCode=?, itemName=?, itemPrice=?, itemDesc=? where itemID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,code);
			preparedStmt.setString(2,name);
			preparedStmt.setDouble(3,Double.parseDouble(price));
			preparedStmt.setString(4,desc);
			preparedStmt.setInt(5, Integer.parseInt(id));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Updated Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while updating";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String deleteItem(String itemID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from items where itemID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(itemID));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Deleted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while deleting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}

}
