package Tracker;


import java.io.BufferedReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	private sql query = new sql();
	private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String nom_utilisateur = "postgres";
    private final String code = "12345";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Read the JSON data from the request body
		Connection conn;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, nom_utilisateur, code);
			String identifier = "";
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			String setting = "";
			String stock = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonData = sb.toString();
			PreparedStatement stmt = conn.prepareStatement(query.getLastTransaction());
			ResultSet rs = stmt.executeQuery();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					identifier = rs.getString(1);
				}
			}
	    // Parse the JSON string to extract the data
			JSONObject json = new JSONObject(jsonData);
			JSONArray cartItemsArray = json.getJSONArray("cartItems");

	    // Now cartItemsArray should contain the JSON objects sent from the frontend
			for (int i = 0; i < cartItemsArray.length(); i++) {
				JSONObject item = cartItemsArray.getJSONObject(i);
				// Extract individual fields from the JSON object
				String itemName = item.getString("name");
				int itemQuantity = item.getInt("quantity");
				String itemLabel = item.getString("label");
	        
				if(i==0) {
					stock = itemLabel+" = "+itemLabel+" - "+itemQuantity;
					setting = itemLabel+" = "+itemQuantity;
				}
				else {
					stock = stock+", "+itemLabel+" = "+itemLabel+" - "+itemQuantity;
					setting = setting +", "+itemLabel+" = "+itemQuantity;
				}	
			}
			PreparedStatement stmt2 = conn.prepareStatement(query.addItems(setting, identifier));
			stmt2.executeUpdate();
			PreparedStatement stmt3 = conn.prepareStatement(query.updateStock(stock));
			stmt3.executeUpdate();
			PreparedStatement stmt4 = conn.prepareStatement(query.removeEmptyRows());
			stmt4.executeUpdate();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	    // Your remaining servlet logic goes here
	}
}