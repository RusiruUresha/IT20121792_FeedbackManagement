package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Feedback {
	 
	
	
	  
	//A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.cj.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb","root","Rusiru#99"); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertFeedback(String type, String desc, String rate) {
			Connection con = connect(); 
			String output;
			if (con == null) 
			{ 
			return "Error while connecting to the database"; 
			}
			
			else {
				String query="insert into feedback(feedbackID,fbType,fbDesc,fbRate) values(?,?,?,?)";
			   try {
				PreparedStatement ps=con.prepareStatement(query);
	            
				
				ps.setInt(1, 0);
				ps.setString(2, type);
				ps.setString(3, desc);
				ps.setDouble(4, Double.parseDouble(rate));
				 
				
				ps.execute();
				output="inserted";
				con.close();
				String newFeedback = readFeedbacks();
				 output = "{\"status\":\"success\", \"data\": \"" + 
						 newFeedback+ "\"}"; 
			 } 
			catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
				 System.err.println(e.getMessage()); 
		     } 
			   return output;
			     
			
			}
			
		}
		
		
		
		public String readFeedbacks() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\" class=\"table\"><tr><th>Feedback Type</th>"
		 		+ "<th>Feddback Description</th>"
		 		+ "<th>Feedback Rate</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from feedback"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String feedbackID = Integer.toString(rs.getInt("feedbackID")); 
		 String fbType = rs.getString("fbType"); 
		 String fbDesc = rs.getString("fbDesc"); 
		 String fbRate = Double.toString(rs.getDouble("fbRate")); 
		   
		 // Add into the html table
		 output += "<tr><td><input id='hidFeedbackIDUpdate' name='hidFeedbackIDUpdate' type='hidden' value='"+feedbackID+"'>"+fbType+"</td>"; 
		 output += "<td>" + fbDesc + "</td>"; 
		 output += "<td>" + fbRate+ "</td>"; 
		   
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-feedbackid='" + feedbackID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-feedbackid='" + feedbackID + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the feedbacks."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateFeedback(String ID, String type, String desc, String rate){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE feedback SET fbType=?,fbDesc=?,fbRate=? WHERE feedbackID=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, type); 
							preparedStmt.setString(2, desc); 
							preparedStmt.setDouble(3, Double.parseDouble(rate));  
							preparedStmt.setInt(4, Integer.parseInt(ID)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newFeedbacks = readFeedbacks(); 
							output = "{\"status\":\"success\",\"data\":\""+newFeedbacks+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the feedback.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteFeedback(String feedbackID){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from feedback where feedbackID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(feedbackID)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newFeedbacks = readFeedbacks(); 
						 output = "{\"status\":\"success\",\"data\":\""+newFeedbacks+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the feedback.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
