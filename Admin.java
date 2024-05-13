package Tracker;

import java.awt.Desktop;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.aspose.pdf.BorderInfo;
import com.aspose.pdf.BorderSide;
import com.aspose.pdf.Color;
import com.aspose.pdf.ColumnAdjustment;
import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.Row;
import com.aspose.pdf.Table;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.File; 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.FileOutputStream; 
import java.util.Map; 
import java.util.Set; 
import java.util.TreeMap; 


@WebServlet("/Admin")
@MultipartConfig
public class Admin extends HttpServlet {
	private List<String> Identifiers = new ArrayList<String>();
	private static String firstName;
	public static HttpSession session;
	private DataSource datasource = null;
	private String html = "";
	private final String url = "jdbc:postgresql://localhost:5432/Dev Tracker";
    private final String nom_utilisateur = "postgres";
    private final String code = "1234";
    private sql query = new sql();
    static int page = 0;
    private String pagination = "";
    static int page_room = 0;
    static int page_action;
    static int page_reports =0;
    static int page_stats=0;
    static int assigned_page = 0;
	
	public Admin(String fname) {
		firstName = fname;
	}
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 String role = (String) session.getAttribute("name");
		 String name = (String) session.getAttribute("name");
		 System.out.println("ADMIN Role: "+role+" Name: "+name);
		}
	 
	 public Admin() {
		 super();
	 }
	 
	 public void init() throws ServletException {
			try {
				InitialContext ctx = new InitialContext();
				datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/LiveDataSource");
			} catch (Exception e) {
				throw new ServletException(e.toString());
			}

		}
	 
	 public void generate_agents(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = page*12;
			 PreparedStatement stmt = conn.prepareStatement(query.generate_agents(offset));
			 ResultSet rs = stmt.executeQuery();
			 PreparedStatement stmt_2 = conn.prepareStatement(query.count_agent());
			 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String account_role = rs.getString("account_role");
						if(account_role==null)
							account_role="";
						String username = rs.getString("username");
						if(username==null)
							username="";
						String nom = rs.getString("nom");
						if(nom==null )
							nom = "";
						String surnom = rs.getString("surnom");
						if(surnom==null)
							surnom="";
						Identifiers.add(username);
						String row = "<tr><td>" + account_role.toString() + "</td><td>" + username.toString() + "</td><td>" + nom.toString() + "</td><td>" + surnom.toString() + "</td><td><form method=\"post\" action=\"Admin\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"open\">\r\n"
					            + "<input type=\"hidden\" name=\"username\" value=\"" + username + "\">\r\n"
					            + "<button class=\"btn\" type=\"submit\">Open Profile</button>"
					            + "</td></form>";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 int num_results = rs_2.getInt(1);
					 if(page==0) {
						 pagination = "<form method=\"post\" action=\"Aadmin\">" + "<input type=\"hidden\" name=\"method\" value=\"next\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>" + " </form>";
					 }
					 else if(num_results - offset <= 10) {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous\">" + "<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>" + " </form>";
						 
					 }
					 else {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous\">" +"<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>"+"</form>"+ "<form method=\"post\" action=\"Agent\">" + "<input type=\"hidden\" name=\"method\" value=\"next\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>"+ "</form>";
					 }
				 }
			 }
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("modifyAgent.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void open(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				String username = request.getParameter("username");
				PreparedStatement stmt = conn.prepareStatement(query.openProfile(username));
				ResultSet rs = stmt.executeQuery();
				if(rs.isBeforeFirst()) {
					while(rs.next()) {
						request.setAttribute("account_role", rs.getString("account_role"));
						String role = rs.getString("account_role");
						if(role.equals("Agent")) {
							request.setAttribute("account_role2", "Administrator");
							request.setAttribute("account_role3", "Client");
						}
						else if(role.equals("Administrator")) {
							request.setAttribute("account_role2", "Agent");
							request.setAttribute("account_role3", "Client");
						}
						else {
							request.setAttribute("account_role2", "Agent");
							request.setAttribute("account_role3", "Administrator");
						}
						request.setAttribute("username", rs.getString("username"));
						request.setAttribute("nom", rs.getString("nom"));
						request.setAttribute("email", rs.getString("email"));
						request.setAttribute("surnom", rs.getString("surnom"));
						request.setAttribute("mot_de_passe", rs.getString("mot_de_passe"));
					}
				}
			request.getRequestDispatcher("updateUserProfile.jsp").forward(request, response);
			}catch(SQLException | ClassNotFoundException | IOException | ServletException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void delete(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				String username = request.getParameter("username");
				PreparedStatement stmt = conn.prepareStatement(query.delete_user(username));
				stmt.executeUpdate();
				generate_agents(request, response);
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void update(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Enumeration<String> Values;
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				String id = request.getParameter("username");
				String[] headers = {"account_role", "username", "mot_de_passe", "nom", "email", "surnom"};
				List<Integer> positions = new ArrayList<Integer>();
				PreparedStatement original = conn.prepareStatement(query.openProfile(id));
				ResultSet rs = original.executeQuery();
				String[] originale = new String[6];
				if(rs.isBeforeFirst()) {
					while(rs.next()) {
						originale[0] = rs.getString("account_role");
						originale[1] = rs.getString("username");
						originale[2] = rs.getString("mot_de_passe");
						originale[3] = rs.getString("nom");
						originale[4] = rs.getString("email");
						originale[5] = rs.getString("surnom");
					}
				}
				String[] changements = new String[7];
				int j = 0;
				for(int i=0; i<6; i++) {
					String temp = request.getParameter(headers[i]);
					if(temp.equals("")!=true) {
						positions.add(i);
						temp = "'"+temp+"'";
						PreparedStatement stmt = conn.prepareStatement(query.updateAgent(headers[i], id, temp));
						changements[j] = headers[i];
						changements[j+1] = temp;
						j=j+1;
						stmt.executeUpdate();
					}
					}
				conn.close();
				rs.close();
				generate_agents(request, response);
			} catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void createAgent(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				String account_role = request.getParameter("account_role");
				String username = request.getParameter("username");
				String nom = request.getParameter("nom");
				String surname = request.getParameter("surnom");
				String mot_de_passe = request.getParameter("mot_de_passe");
				String email = request.getParameter("email");
				PreparedStatement stmt = conn.prepareStatement(query.createUser(account_role, username, mot_de_passe, nom, surname, email));
				stmt.executeUpdate();
				generate_agents(request, response);
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void searchAgent(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = page*12;
			 String field = request.getParameter("searchField");
			 String value = request.getParameter("search");
			 PreparedStatement stmt = conn.prepareStatement(query.searchAgent(field, value, offset));
			 ResultSet rs = stmt.executeQuery();
			 PreparedStatement stmt_2 = conn.prepareStatement(query.count_agent(field, value));
			 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String account_role = rs.getString("account_role");
						if(account_role==null)
							account_role="";
						String username = rs.getString("username");
						if(username==null)
							username="";
						String nom = rs.getString("nom");
						if(nom==null )
							nom = "";
						String surnom = rs.getString("surnom");
						if(surnom==null)
							surnom="";
						Identifiers.add(username);
						String row = "<tr><td>" + account_role.toString() + "</td><td>" + username.toString() + "</td><td>" + nom.toString() + "</td><td>" + surnom.toString() + "</td><td><form method=\"post\" action=\"Admin\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"open\">\r\n"
					            + "<input type=\"hidden\" name=\"username\" value=\"" + username + "\">\r\n"
					            + "<button class=\"btn\" type=\"submit\">Open Profile</button>"
					            + "</td></form>";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 int num_results = rs_2.getInt(1);
					 if(num_results <= 12) {
						 pagination = "";
					 }
					 else if(num_results > 12 && num_results - offset > 12 && page == 0) {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"next\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>" + " </form>";
					 }
					 else if(num_results - offset <= 12 && num_results > 12) {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous\">" + "<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>" + " </form>";
						 
					 }
					 else {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous\">" +"<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>"+"</form>"+ "<form method=\"post\" action=\"Agent\">" + "<input type=\"hidden\" name=\"method\" value=\"next\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>"+ "</form>";
					 }
				 }
			 }
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("modifyAgent.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void openRoom(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				String room_num = request.getParameter("room_num");
				PreparedStatement stmt = conn.prepareStatement(query.openRoom(room_num));
				ResultSet rs = stmt.executeQuery();
				if(rs.isBeforeFirst()) {
					while(rs.next()) {
						request.setAttribute("room_type", rs.getString("room_type"));
						String type = rs.getString("room_type");
						if(type.equals("ACCQ")) {
							request.setAttribute("room_type2", "BUNK");
							request.setAttribute("room_type3", "DOUBLEBUNK");
							request.setAttribute("room_type4", "DX2D");
							request.setAttribute("room_type5", "DX2Q");
							request.setAttribute("room_type6", "DXSQ");
							request.setAttribute("room_type7", "EXSQ");
							request.setAttribute("room_type8", "SK");
							request.setAttribute("room_type9", "SQ");
							request.setAttribute("room_type10", "TRADD");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("BUNK")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "DOUBLEBUNK");
							request.setAttribute("room_type4", "DX2D");
							request.setAttribute("room_type5", "DX2Q");
							request.setAttribute("room_type6", "DXSQ");
							request.setAttribute("room_type7", "EXSQ");
							request.setAttribute("room_type8", "SK");
							request.setAttribute("room_type9", "SQ");
							request.setAttribute("room_type10", "TRADD");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("DOUBLEBUNK")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DX2D");
							request.setAttribute("room_type5", "DX2Q");
							request.setAttribute("room_type6", "DXSQ");
							request.setAttribute("room_type7", "EXSQ");
							request.setAttribute("room_type8", "SK");
							request.setAttribute("room_type9", "SQ");
							request.setAttribute("room_type10", "TRADD");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("DX2D")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DOUBLEBUNK");
							request.setAttribute("room_type5", "DX2Q");
							request.setAttribute("room_type6", "DXSQ");
							request.setAttribute("room_type7", "EXSQ");
							request.setAttribute("room_type8", "SK");
							request.setAttribute("room_type9", "SQ");
							request.setAttribute("room_type10", "TRADD");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("DX2Q")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DOUBLEBUNK");
							request.setAttribute("room_type5", "DX2D");
							request.setAttribute("room_type6", "DXSQ");
							request.setAttribute("room_type7", "EXSQ");
							request.setAttribute("room_type8", "SK");
							request.setAttribute("room_type9", "SQ");
							request.setAttribute("room_type10", "TRADD");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("DXSQ")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DOUBLEBUNK");
							request.setAttribute("room_type5", "DX2D");
							request.setAttribute("room_type6", "DX2Q");
							request.setAttribute("room_type7", "EXSQ");
							request.setAttribute("room_type8", "SK");
							request.setAttribute("room_type9", "SQ");
							request.setAttribute("room_type10", "TRADD");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("EXSQ")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DOUBLEBUNK");
							request.setAttribute("room_type5", "DX2D");
							request.setAttribute("room_type6", "DX2Q");
							request.setAttribute("room_type7", "DXSQ");
							request.setAttribute("room_type8", "SK");
							request.setAttribute("room_type9", "SQ");
							request.setAttribute("room_type10", "TRADD");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("SK")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DOUBLEBUNK");
							request.setAttribute("room_type5", "DX2D");
							request.setAttribute("room_type6", "DX2Q");
							request.setAttribute("room_type7", "DXSQ");
							request.setAttribute("room_type8", "EXSQ");
							request.setAttribute("room_type9", "SQ");
							request.setAttribute("room_type10", "TRADD");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("SQ")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DOUBLEBUNK");
							request.setAttribute("room_type5", "DX2D");
							request.setAttribute("room_type6", "DX2Q");
							request.setAttribute("room_type7", "DXSQ");
							request.setAttribute("room_type8", "EXSQ");
							request.setAttribute("room_type9", "SK");
							request.setAttribute("room_type10", "TRADD");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("TRADD")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DOUBLEBUNK");
							request.setAttribute("room_type5", "DX2D");
							request.setAttribute("room_type6", "DX2Q");
							request.setAttribute("room_type7", "DXSQ");
							request.setAttribute("room_type8", "EXSQ");
							request.setAttribute("room_type9", "SK");
							request.setAttribute("room_type10", "SQ");
							request.setAttribute("room_type11", "TRADK");
							request.setAttribute("room_type12", "TRADQ");
						}
						else if(type.equals("TRADK")) {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DOUBLEBUNK");
							request.setAttribute("room_type5", "DX2D");
							request.setAttribute("room_type6", "DX2Q");
							request.setAttribute("room_type7", "DXSQ");
							request.setAttribute("room_type8", "EXSQ");
							request.setAttribute("room_type9", "SK");
							request.setAttribute("room_type10", "SQ");
							request.setAttribute("room_type11", "TRADD");
							request.setAttribute("room_type12", "TRADQ");
						}
						else {
							request.setAttribute("room_type2", "ACCQ");
							request.setAttribute("room_type3", "BUNK");
							request.setAttribute("room_type4", "DOUBLEBUNK");
							request.setAttribute("room_type5", "DX2D");
							request.setAttribute("room_type6", "DX2Q");
							request.setAttribute("room_type7", "DXSQ");
							request.setAttribute("room_type8", "EXSQ");
							request.setAttribute("room_type9", "SK");
							request.setAttribute("room_type10", "SQ");
							request.setAttribute("room_type11", "TRADD");
							request.setAttribute("room_type12", "TRADK");
						}
						request.setAttribute("room_num", rs.getString("room_num"));
						request.setAttribute("max_occupancy", rs.getString("max_occupancy"));
						request.setAttribute("number_of_beds", rs.getString("number_of_beds"));
					}
				}
			request.getRequestDispatcher("updateRoom.jsp").forward(request, response);
			}catch(SQLException | ClassNotFoundException | IOException | ServletException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void generate_room(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = page_room*12;
			 PreparedStatement stmt = conn.prepareStatement(query.generate_rooms(offset));
			 ResultSet rs = stmt.executeQuery();
			 PreparedStatement stmt_2 = conn.prepareStatement(query.count_room());
			 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num="";
						String max_occupancy = rs.getString("max_occupancy");
						if(max_occupancy==null)
							max_occupancy="";
						String number_of_beds = rs.getString("number_of_beds");
						if(number_of_beds==null )
							number_of_beds = "";
						String beds_occupied = rs.getString("beds_occupied");
						if(beds_occupied==null)
							beds_occupied="";
						String room_type = rs.getString("room_type");
						if(room_type==null)
							room_type="";
						String occupied = rs.getString("occupied");
						if(occupied==null)
							occupied="";
						String row = "<tr><td>" + room_num.toString() + "</td><td>" + max_occupancy.toString() + "</td><td>" + number_of_beds.toString() + "</td><td>" + beds_occupied.toString() + "</td><td>" + room_type.toString() + "</td><td>" + occupied.toString() + "</td><td><form method=\"post\" action=\"Admin\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"openRoom\">\r\n"
					            + "<input type=\"hidden\" name=\"room_num\" value=\"" + room_num + "\">\r\n"
					            + "<button class=\"btn\" type=\"submit\">Open Room</button>"
					            + "</td></form>";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 int num_results = rs_2.getInt(1);
					 if(page_room==0) {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"next_room\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>" + " </form>";
					 }
					 else if(num_results - offset <= 10) {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous_room\">" + "<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>" + " </form>";
						 
					 }
					 else {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous_room\">" +"<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>"+"</form>"+ "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"next_room\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>"+ "</form>";
					 }
				 }
			 }
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("modifyRoom.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void search_room(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = page_room*12;
			 String field = request.getParameter("searchField");
			 String value = request.getParameter("search");
			 if(field.equals("room_type") | field.equals("occupied")) {
				 value = " iLIKE '%"+value+"%'";
			 }
			 else {
				 value = " = "+value;
			 }
			 PreparedStatement stmt = conn.prepareStatement(query.search_rooms(field, value, offset));
			 ResultSet rs = stmt.executeQuery();
			 PreparedStatement stmt_2 = conn.prepareStatement(query.count_room_search(field, value));
			 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num="";
						String max_occupancy = rs.getString("max_occupancy");
						if(max_occupancy==null)
							max_occupancy="";
						String number_of_beds = rs.getString("number_of_beds");
						if(number_of_beds==null )
							number_of_beds = "";
						String beds_occupied = rs.getString("beds_occupied");
						if(beds_occupied==null)
							beds_occupied="";
						String room_type = rs.getString("room_type");
						if(room_type==null)
							room_type="";
						String occupied = rs.getString("occupied");
						if(occupied==null)
							occupied="";
						String row = "<tr><td>" + room_num.toString() + "</td><td>" + max_occupancy.toString() + "</td><td>" + number_of_beds.toString() + "</td><td>" + beds_occupied.toString() + "</td><td>" + room_type.toString() + "</td><td>" + occupied.toString() + "</td><td><form method=\"post\" action=\"Admin\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"openRoom\">\r\n"
					            + "<input type=\"hidden\" name=\"room_num\" value=\"" + room_num + "\">\r\n"
					            + "<button class=\"btn\" type=\"submit\">Open Room</button>"
					            + "</td></form>";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 int num_results = rs_2.getInt(1);
					 if(page_room==0) {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"next_room\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>" + " </form>";
					 }
					 else if(num_results - offset <= 10) {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous_room\">" + "<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>" + " </form>"; 
					 }
					 else if(num_results <=12) {
						 pagination = "";
					 }
					 else {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous_room\">" +"<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>"+"</form>"+ "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"next_room\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>"+ "</form>";
					 }
				 }
			 }
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("modifyRoom.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void createRoom(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				String room_num = request.getParameter("room_num");
				String max_occupancy = request.getParameter("max_occupancy");
				String number_of_beds = request.getParameter("number_of_beds");
				String room_type = request.getParameter("room_type");
				PreparedStatement stmt = conn.prepareStatement(query.createRoom(room_num, max_occupancy, number_of_beds, room_type));
				stmt.executeUpdate();
				generate_room(request, response);
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void updateRoom(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Enumeration<String> Values;
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				String room_num = request.getParameter("room_num");
				String[] headers = {"room_num", "room_type", "max_occupancy", "number_of_beds"};
				List<Integer> positions = new ArrayList<Integer>();
				PreparedStatement original = conn.prepareStatement(query.openRoom(room_num));
				ResultSet rs = original.executeQuery();
				String[] originale = new String[4];
				if(rs.isBeforeFirst()) {
					while(rs.next()) {
						originale[0] = rs.getString("room_num");
						originale[1] = rs.getString("room_type");
						originale[2] = rs.getString("max_occupancy");
						originale[3] = rs.getString("number_of_beds");
					}
				}
				String[] changements = new String[6];
				int j = 0;
				for(int i=0; i<4; i++) {
					String temp = request.getParameter(headers[i]);
					if(temp.equals("")!=true) {
						if(headers[i].equals("room_type"))
							temp = "'"+temp+"'";
						positions.add(i);
						PreparedStatement stmt = conn.prepareStatement(query.updateRoom(headers[i], room_num, temp));
						changements[j] = headers[i];
						changements[j+1] = temp;
						j=j+1;
						stmt.executeUpdate();
					}
					}
				conn.close();
				rs.close();
				generate_room(request, response);
			} catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void deleteRoom(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				String room_num = request.getParameter("room_num");
				PreparedStatement stmt = conn.prepareStatement(query.deleteRoom(room_num));
				stmt.executeUpdate();
				generate_room(request, response);
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void generate_action(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 PreparedStatement stmt = conn.prepareStatement(query.generate_actions(0));
			 ResultSet rs = stmt.executeQuery();
			 String sqlQuery = query.generate_actionsNoOffset();
			 PreparedStatement stmt_2 = conn.prepareStatement(query.count_actions());
			 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String agent_name = rs.getString("agent");
						if(agent_name==null)
							agent_name="";
						String actions = rs.getString("actions");
						if(actions==null)
							actions="";
						String date = rs.getString("date_of_modification");
						if(date==null )
							date = "";
						String time = rs.getString("time_of_modification");
						if(time==null)
							time="";
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num="";
						String nom = rs.getString("nom");
						if(nom==null)
							nom="";
						String surname = rs.getString("surname");
						if(surname==null)
							surname="";
						String original = rs.getString("original");
						if(original==null)
							original="";
						String modified = rs.getString("modified");
						if(modified==null)
							modified="";
						String row = "<tr height=\"45px\" style=\"overflow: hidden;\"><td>" + agent_name.toString() + "</td><td>" + actions.toString() + "</td><td>" + date.toString() + "</td><td>" + time.toString() + "</td><td>" + room_num.toString() + "</td><td>" + nom.toString() + "</td><td>" + surname.toString() +"</td><td>" + original.toString() + "</td><td>" + modified.toString() +"</td><td>\r\n";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 request.setAttribute("count", (int) Math.ceil((double)rs_2.getInt(1)/12));
				 }
			 }
			 request.setAttribute("query", sqlQuery);
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
			 request.getRequestDispatcher("adminPortal.jsp").forward(request, response);
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void search_action(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = page_room*12;
			 String field = request.getParameter("searchField");
			 request.setAttribute("field", field);
			 String value = request.getParameter("search");
			 request.setAttribute("value", value);
			 if(field.equals("agent") || field.equals("date_of_modification") || field.equals("actions")) {
				 value = "'"+value+"'";
			 }
				 PreparedStatement stmt = conn.prepareStatement(query.search_action(field, value, 0));
				 ResultSet rs = stmt.executeQuery();
				 String sqlQuery = query.search_actionNoOffset(field, value);
				 PreparedStatement stmt_2 = conn.prepareStatement(query.count_action_search(field, value));
				 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String agent_name = rs.getString("agent");
						if(agent_name==null)
							agent_name="";
						String actions = rs.getString("actions");
						if(actions==null)
							actions="";
						String date = rs.getString("date_of_modification");
						if(date==null )
							date = "";
						String time = rs.getString("time_of_modification");
						if(time==null)
							time="";
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num="";
						String nom = rs.getString("nom");
						if(nom==null)
							nom="";
						String surname = rs.getString("surname");
						if(surname==null)
							surname="";
						String original = rs.getString("original");
						if(original==null)
							original="";
						String modified = rs.getString("modified");
						if(modified==null)
							modified="";
						String row = "<tr><td>" + agent_name.toString() + "</td><td>" + actions.toString() + "</td><td>" + date.toString() + "</td><td>" + time.toString() + "</td><td>" + room_num.toString() + "</td><td>" + nom.toString() + "</td><td>" + surname.toString() +"</td><td>" + original.toString() + "</td><td>" + modified.toString() +"</td><td>\r\n";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 request.setAttribute("count", (int) Math.ceil((double)rs_2.getInt(1)/12));
				 }
			 }
			 request.setAttribute("query", sqlQuery);
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("adminPortal.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void search_action_Move(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = page_action*12;
			 String field = request.getParameter("field");
			 String value = request.getParameter("value");
			 if(field.equals("agent") || field.equals("date_of_modification") || field.equals("actions")) {
				 value = "'"+value+"'";
			 }
				 PreparedStatement stmt = conn.prepareStatement(query.search_action(field, value, offset));
				 ResultSet rs = stmt.executeQuery();
				 PreparedStatement stmt_2 = conn.prepareStatement(query.count_action_search(field, value));
				 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String agent_name = rs.getString("agent");
						if(agent_name==null)
							agent_name="";
						String actions = rs.getString("actions");
						if(actions==null)
							actions="";
						String date = rs.getString("date_of_modification");
						if(date==null )
							date = "";
						String time = rs.getString("time_of_modification");
						if(time==null)
							time="";
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num="";
						String nom = rs.getString("nom");
						if(nom==null)
							nom="";
						String surname = rs.getString("surname");
						if(surname==null)
							surname="";
						String original = rs.getString("orginal");
						if(original==null)
							original="";
						String modified = rs.getString("modified");
						if(modified==null)
							modified="";
						String row = "<tr><td>" + agent_name.toString() + "</td><td>" + actions.toString() + "</td><td>" + date.toString() + "</td><td>" + time.toString() + "</td><td>" + room_num.toString() + "</td><td>" + nom.toString() + "</td><td>" + surname.toString() +"</td><td>" + original.toString() + "</td><td>" + modified.toString() +"</td><td>\r\n";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 int num_results = rs_2.getInt(1);
					 if(page_action==0) {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"next_actions_search\">" + "<input type=\"hidden\" name=\"field\" value="+field+">"
				            + "<input type=\"hidden\" name=\"value\" value="+value+"> <button class=\"button\"><i class=\"fa fa-caret-right\"></i></button>" + " </form>";
					 }
					 else if(num_results - offset <= 10) {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous_actions_search\">" + "<input type=\"hidden\" name=\"field\" value="+field+">"
						            + "<input type=\"hidden\" name=\"value\" value="+value+"> <button class=\"button\"><i class=\"fa fa-caret-right\"></i></button>" + " </form>";
					 }
					 else {
						 pagination = "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"previous_actions_search\">"+ "<input type=\"hidden\" name=\"field\" value="+field+">"
						            + "<input type=\"hidden\" name=\"value\" value="+value+">" +"<button class=\"button\"><i class=\"fa fa-caret-left\"></i></button>"+"</form>"+ "<form method=\"post\" action=\"Admin\">" + "<input type=\"hidden\" name=\"method\" value=\"next_actions_search\">" + "<input type=\"hidden\" name=\"field\" value="+field+">"
						            + "<input type=\"hidden\" name=\"value\" value="+value+">" + "<button class=\"button\"><i class=\"fa fa-caret-right\"></i></button>"+ "</form>";
					 }
				 }
			 }
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("adminPortal.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void generateReports(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = page_reports*12;
			 PreparedStatement stmt = conn.prepareStatement(query.generate_reports(0));
			 String sqlQuery = query.generate_reportsNoOffset();
			 ResultSet rs = stmt.executeQuery();
			 PreparedStatement stmt_2 = conn.prepareStatement(query.count_reports());
			 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String agent_name = rs.getString("agent");
						if(agent_name==null)
							agent_name="";
						String title = rs.getString("title");
						if(title==null)
							title="";
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num = "";
						String responded = rs.getString("responded");
						if(responded==null )
							responded = "";
						String report_identifier = rs.getString("report_identifier");
						if(report_identifier ==null)
							report_identifier="";
						String res_identifiers = rs.getString("res_identifiers");
						if(res_identifiers==null)
							res_identifiers="";
						String date = rs.getString("date_of_writing");
						if(date==null) {
							date="";
						}
						if(responded.equals("No")) {
							title = "<b>"+title+"</b>";
							agent_name = "<b>"+agent_name+"</b>";
							room_num = "<b>"+room_num+"</b>";
							date = "<b>"+date+"</b>";
						}
						res_identifiers = res_identifiers.substring(1, res_identifiers.length() -1);
						String rooms ="";
						String names="";
						ArrayList<String> room_tests = new ArrayList<String>();
						Boolean copy= false;
						String[] identifiers = res_identifiers.split("\\s*,\\s*");
						for(int i=0; i<identifiers.length; i++) {
							PreparedStatement stmt3 = conn.prepareStatement(query.open(identifiers[i]));
							ResultSet rs3 = stmt3.executeQuery();
							if(rs3.isBeforeFirst()) {
								while(rs3.next()) {
									room_tests.add(rs3.getString("room_num"));
									names = names + rs3.getString("nom")+" "+rs3.getString("surname")+", ";
									for(int j=0; j<room_tests.size(); j++) {
										if(room_tests.get(j).equals(rs3.getString("room_num"))) {
											copy=true;
											break;
										}
									}
							if(copy==false) {
								rooms = rooms + rs3.getString("room_num") +", ";
							}
						}
					}
				}
						if(responded.equals("No")) {
							names = "<b>"+names+"</b>";
						}
						String row = "<form method=\"post\" action=\"Admin\"> <tr><td>" + agent_name.toString() + "</td><td>" + title + "</td><td>" + date.toString() + "</td><td>" + room_num.toString() + "</td><td>" + names.toString() + "</td><td>\r\n"
								+ "<input type=\"hidden\" name=\"method\" value=\"openReport\">\r\n"
					            + "<input type=\"hidden\" name=\"id\" value=\"" + report_identifier + "\">\r\n"
					            + "<button class=\"buttonss\" type=\"submit\">Open Report</button>"
					            + "</td></form>";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 request.setAttribute("count", (int) Math.ceil((double)rs_2.getInt(1)/12)); 
				 }
			 }
			 request.setAttribute("query", sqlQuery);
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
			 request.getRequestDispatcher("reportPortal.jsp").forward(request, response);
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void openReports(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String identifier = request.getParameter("id");
			 PreparedStatement stmt = conn.prepareStatement(query.openReport(identifier));
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 request.setAttribute("report", rs.getString("report"));
					 request.setAttribute("report_type", rs.getString("report_type"));
					 request.setAttribute("names", rs.getString("nom"));
					 request.setAttribute("date_of_incident", rs.getString("date_of_incident"));
					 request.setAttribute("room_num", rs.getString("room_num"));
					 request.setAttribute("agent", rs.getString("agent"));
					 request.setAttribute("identifier", rs.getString("report_identifier"));
					 String answer = rs.getString("response");
					 if(answer==null) {
						 answer = "";
					 }
					 request.setAttribute("response", answer);
				 }
			}
			 request.getRequestDispatcher("openReport.jsp").forward(request, response);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void submitResponse(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String identifier = request.getParameter("identifier");
			 String answer = request.getParameter("response");
			 PreparedStatement stmt = conn.prepareStatement(query.openReport(identifier));
			 ResultSet rs = stmt.executeQuery();
			 PreparedStatement responded = conn.prepareStatement(query.opened(identifier, answer));
			 responded.executeUpdate();
			 String path = "";
			 String title = "";
			 String room_num = "";
			 String names = "";
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 title = rs.getString("title");
					 room_num = rs.getString("room_num");
				 }
			 }
			 String[] rooms = room_num.split("\\s*,\\s*");
			 for(int i=0; i<rooms.length; i++) {
				Document document = new Document("Reports/"+rooms[i]+"/" + title);
			 
				Page page = document.getPages().add();
			 
			 	Table table = new Table();
			 	table.setColumnWidths("100 100");
			 	table.setColumnAdjustment(ColumnAdjustment.AutoFitToWindow);
			 
			 	table.setBorder(new BorderInfo(BorderSide.All, .5f, Color.getBlack()));
			 	table.setDefaultCellBorder(new BorderInfo(BorderSide.All, .5f, Color.getBlack()));
			 	Row row = table.getRows().add();
			 	row.setFixedRowHeight(20);
			 	row.getCells().add(request.getParameter("response"));
			 
			 	page.getParagraphs().add(table);
			 	document.save(path + title);
			 	document.close();
			 
			 	String name = request.getParameter("agent_name");
			 	String mail = "";
			 
			 	PreparedStatement email = conn.prepareStatement(query.getEmail(name));
			 	ResultSet rs2 = email.executeQuery();
			 	if(rs2.isBeforeFirst()) {
			 		while(rs2.next()) {
					 	mail = rs2.getString(1);
				 	}
			 	}
			 	String to = mail;
			 	String from = "noreplyglobalys@gmail.com";

		      	Properties properties = System.getProperties();
		      	properties.put("mail.smtp.starttls.enable", "true");
		      	properties.put("mail.smtp.host", "smtp.gmail.com");
		      	properties.put("mail.smtp.port", "587");

		      	properties.put("mail.smtp.auth", "true");
		      
		      	Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
		      		protected PasswordAuthentication getPasswordAuthentication() {
		      			return new PasswordAuthentication("noreplyglobalys@gmail.com", "qdvw jlhq gtiv appn");
		          	}
		      	});
		      	session.setDebug(true);
		      	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		      	LocalDateTime now = LocalDateTime.now();  
		      	MimeMessage message = new MimeMessage(session);
		        message.setFrom(new InternetAddress(from));
		        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		        message.setSubject("Your Report Now Has A Response : "+dtf.format(now));
		        message.setText("Your report concerning "+room_num+" now has a response!" );
		        
		        Transport.send(message);
		        generateReports(request, response);
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void searchReports(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = page_action*12;
			 String field = request.getParameter("searchField");
			 String value = request.getParameter("search");
			 if(field.equals("agent") || field.equals("report_type") || field.equals("room_num")) {
				 value = "iLIKE '%"+value+"%'";
			 }
			 else {
				 value = "= "+value;
			 }
				 PreparedStatement stmt = conn.prepareStatement(query.searchReports(field, value, 0));
				 ResultSet rs = stmt.executeQuery();
				 String sqlQuery = query.searchReportsNoOffset(field, value);
				 System.out.println(sqlQuery);
				 PreparedStatement stmt_2 = conn.prepareStatement(query.countReports(field, value));
				 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String agent_name = rs.getString("agent");
						if(agent_name==null)
							agent_name="";
						String title = rs.getString("title");
						if(title==null)
							title="";
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num = "";
						String responded = rs.getString("responded");
						if(responded==null )
							responded = "";
						String report_identifier = rs.getString("report_identifier");
						if(report_identifier ==null)
							report_identifier="";
						String res_identifiers = rs.getString("res_identifiers");
						if(res_identifiers==null)
							res_identifiers="";
						String date = rs.getString("date_of_writing");
						if(date==null) {
							date="";
						}
						if(responded.equals("No")) {
							title = "<b>"+title+"</b>";
							agent_name = "<b>"+agent_name+"</b>";
							room_num = "<b>"+room_num+"</b>";
							date = "<b>"+date+"</b>";
						}
						res_identifiers = res_identifiers.substring(1, res_identifiers.length() -1);
						String rooms ="";
						String names="";
						ArrayList<String> room_tests = new ArrayList<String>();
						Boolean copy= false;
						String[] identifiers = res_identifiers.split("\\s*,\\s*");
						for(int i=0; i<identifiers.length; i++) {
							PreparedStatement stmt3 = conn.prepareStatement(query.open(identifiers[i]));
							ResultSet rs3 = stmt3.executeQuery();
							if(rs3.isBeforeFirst()) {
								while(rs3.next()) {
									room_tests.add(rs3.getString("room_num"));
									names = names + rs3.getString("nom")+" "+rs3.getString("surname")+", ";
									for(int j=0; j<room_tests.size(); j++) {
										if(room_tests.get(j).equals(rs3.getString("room_num"))) {
											copy=true;
											break;
										}
									}
							if(copy==false) {
								rooms = rooms + rs3.getString("room_num") +", ";
							}
						}
					}
				}
						if(responded.equals("No")) {
							names = "<b>"+names+"</b>";
						}
						String row = "<form method=\"post\" action=\"Admin\"> <tr><td>" + agent_name.toString() + "</td><td>" + title + "</td><td>" + date.toString() + "</td><td>" + room_num.toString() + "</td><td>" + names.toString() + "</td><td>\r\n"
								+ "<input type=\"hidden\" name=\"method\" value=\"openReport\">\r\n"
					            + "<input type=\"hidden\" name=\"id\" value=\"" + report_identifier + "\">\r\n"
					            + "<button class=\"buttonss\" type=\"submit\">Open Report</button>"
					            + "</td></form>";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 request.setAttribute("count", (int) Math.ceil((double)rs_2.getInt(1)/12));
				 }
			 }
			 request.setAttribute("query", sqlQuery);
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
			 request.getRequestDispatcher("reportPortal.jsp").forward(request, response);
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }

	 public void stats(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String id = request.getParameter("id");
			 if(id==null) {
				 id = "";
			 }
			 if(id.equals("language")) {
				 String[] primary_languages = {"ARABIC", "BAMBARA", "BANGLA", "BANGOLI", "CREOLE", "DARI", "ENGLISH", "FARSI", "FRENCH", "GEORGIAN", "GORAM", "IGBO", "KALENJIN", "KIKUYU", "KINYARWANDA", "KIRUNDI", "KURDISH", "LINGALA", "LUGANDA", "MANDINKA", "NDEBELE", "PASHTO", "PERSIAN", "PORTUGUESE", "PUNJABI", "RUNYANKORE", "SHONA", "SOMALI", "SPANSIH", "SWAHILI", "TAGLOG", "TIGRINYA", "TURKISH", "TWI", "URDU", "YORUBA", "OTHER"};
				 String temp = "";
				 for(int j=0; j<primary_languages.length; j++) {
					 temp = "primary_language iLike '%"+primary_languages[j]+"%'";
					 PreparedStatement count = conn.prepareStatement(query.countstats(temp));
					 ResultSet rs = count.executeQuery();
					 if(rs.isBeforeFirst()) {
						 while(rs.next()) {
							 request.setAttribute(primary_languages[j], rs.getInt(1));
						}
					 }
				 }
				 String[] other_language = {"iLike '%N/A%'", "iLike '%English%'", "iLike '%French%'", "NOT iLike '%N/A%' AND other_language NOT iLIKE '%English%' AND other_language NOT iLIKE '%French%'"};
				 temp = "";
				 for(int s=0; s<other_language.length; s++) {
					 temp = "other_language "+other_language[s];
					 PreparedStatement count = conn.prepareStatement(query.countstats(temp));
					 ResultSet rs = count.executeQuery();
					 if(rs.isBeforeFirst()) {
						 while(rs.next()) {
							 request.setAttribute(other_language[s], rs.getString(1));
						 }
					 }
				 }
				 request.getRequestDispatcher("stats2.jsp").forward(request, response);
			 }
			 else if(id.equals("ageAndGender")) {
				 String[] age_gender = {"age<18 AND gender = 'Female'", "age>17 AND age<35 AND gender = 'Female'", "age>35 AND age<55 AND gender ='Female'", "age>=55 AND gender='Female'", "age<18 AND gender = 'Male'",  "age>17 AND age<35 AND gender = 'Male'", "age>35 AND age<55 AND gender = 'Male'", "age>=55 AND gender='Male'"};
				 String[] age = {"age<5", "age>17 AND age<50", "age>4 AND age<18","age>50 AND age<65", "age>64"};
				 String temp = "";
				 PreparedStatement count = conn.prepareStatement(query.ageAndGender());
				 ResultSet rs = count.executeQuery();
				 int value = 0;
				 if(rs.isBeforeFirst()) {
					 while(rs.next() && value < 8) {
							 request.setAttribute(age_gender[value], rs.getInt(3));
							 value = value + 1;
						 }
					 }
				 PreparedStatement count2 = conn.prepareStatement(query.ageTrial());
				 ResultSet rs2 = count2.executeQuery();
				 value = 0;
				 if(rs2.isBeforeFirst()) {
					 while(rs2.next() && value < 5) {
							 request.setAttribute(age[value], rs2.getInt(2));
							 value = value + 1;
						 }
					 }
				 request.getRequestDispatcher("stats3.jsp").forward(request, response);
			 }
			 else if(id.equals("family_comp")) {
				 String[] family_comp = {"Solo", "Couple", "Family of 2", "Family of 3", "Family of 4", "Family of 5", "Family of 6", "Family of 7", "Family of 8", "Family of 9", "Family of 10", "Family of 11"};
				 String temp = "";
				 for(int k=0; k<family_comp.length; k++) {
					 temp = "family_composition = '"+family_comp[k]+"'";
					 PreparedStatement count = conn.prepareStatement(query.countstats(temp));
					 ResultSet rs = count.executeQuery();
					 int val = 1;
					 if(k==2 || k==1) {
						 val = 2;
					 }
					 else if(k==3) {
						 val = 3;
					 }
					 else if(k==4) {
						 val = 4;
					 }
					 else if(k==5){
						 val = 5;
					 }
					 else if(k==6) {
						 val = 6;
					 }
					 else if(k==7) {
						 val = 7;
					 }
					 else if(k==8) {
						 val = 8;
					 }
					 else if(k==9) {
						 val = 9;
					 }
					 else if(k==10) {
						 val = 10;
					 }
					 else if(k==11) {
						 val = 11;
					 }
					 if(rs.isBeforeFirst()) {
						 while(rs.next()) {
							 request.setAttribute(family_comp[k], rs.getInt(1)/val);
						 }
					 }
				 }
				 request.getRequestDispatcher("stats4.jsp").forward(request, response);
				 
			 }
			 else if(id.equals("Education")) {
				 String[] education = {"None", "Elementary", "High School", "College", "University", "Masters", "PhD"};
				 String temp = "";
				 for(int l=0; l<education.length; l++) {
					 temp = "education = '"+education[l]+"'";
					 PreparedStatement count = conn.prepareStatement(query.countstats(temp));
					 ResultSet rs = count.executeQuery();
					 if(rs.isBeforeFirst()) {
						 while(rs.next()) {
							 request.setAttribute(education[l], rs.getInt(1));
						 }
					 }
				 }
				 request.getRequestDispatcher("stats5.jsp").forward(request, response);
			 }
			 else if(id.equals("challenges")){
				 String[] challenges = {"Transportation", "Childcare", "Language", "Layoff", "Medical", "Still in School", "Pregnant", "ODSP", "Recertification", "Other"};
				 String temp = "";
				 for(int m=0; m<challenges.length; m++) {
					 temp = "employment_challenges iLIKE '%"+challenges[m]+"%'";
					 PreparedStatement count = conn.prepareStatement(query.countstats(temp));
					 ResultSet rs = count.executeQuery();
					 if(rs.isBeforeFirst()) {
						 while(rs.next()) {
							 request.setAttribute(challenges[m], rs.getInt(1));
						 }
					 }
				 }
				 request.getRequestDispatcher("stats6.jsp").forward(request, response);
			 }
			 else if(id.equals("employment")) {
				 String[] employed = {"Employed", "Unemployed"};
				 String[] format = {"Full Time", "Part Time", "Seasonal"};
				 String temp = "";
				 for(int x=0; x<employed.length; x++) {
					 temp = "employment = '"+employed[x]+"'";
					 PreparedStatement count = conn.prepareStatement(query.countstats(temp));
					 ResultSet rs = count.executeQuery();
					 if(rs.isBeforeFirst()) {
						 while(rs.next()) {
							 request.setAttribute(employed[x], rs.getInt(1));
						 }
					 }
				 }
				 temp = "";
				 for(int y=0; y<format.length; y++) {
					 temp = "employment = 'Employed' AND part_full_time = '"+format[y]+"'";
					 PreparedStatement count = conn.prepareStatement(query.countstats(temp));
					 ResultSet rs = count.executeQuery();
					 if(rs.isBeforeFirst()) {
						 while(rs.next()) {
							 request.setAttribute(format[y], rs.getInt(1));
						 }
					 }
				 }
				 request.getRequestDispatcher("stats7.jsp").forward(request, response);
				}
			 else {
				 String[] countries = {"AFGHANISTAN", "ANGOLA", "BANGLADESH", "BARBADOS", "BURKINA FASO", "BURUNDI", "CAMEROON", "CANADA", "CHILE", "CHINA", "COLOMBIA", "CONGO", "ERITREA", "FRANCE", "GAMBIA", "GUINEA", "GUINEA-BISSAU", "HAITI", "KENYA", "MALI", "MAURITANIA", "MEXICO", "NIGERIA", "PAKISTAN", "PALESTINE", "PANAMA", "PERU", "PHILIPPINES", "CONGO", "RWANDA", "SAUDI ARABIA", "SENEGAL", "SOMALIA", "SOUTH SUDAN", "SUDAN", "SYRIA", "TANZANIA", "TCHAD", "TOGO", "TURKEY", "UGANDA", "UNITED ARAB EMIRATES", "UNITED STATES", "VENEZUELA", "YEMEN", "ZIMBABWE"};
				 String temp = "";
				 for(int i=0; i<countries.length; i++) {
					 temp = "citizenship iLIKE '%"+countries[i]+"%'";
					 PreparedStatement count = conn.prepareStatement(query.countstats(temp));
					 ResultSet rs = count.executeQuery();
					 if(rs.isBeforeFirst()) {
						 while(rs.next()) {
							 request.setAttribute(countries[i], rs.getInt(1));
						 }
					 }
				 }
				 request.getRequestDispatcher("stats.jsp").forward(request, response);
			 }
			 conn.close();
			 }catch(ClassNotFoundException | SQLException |IOException | ServletException e) {
				 e.printStackTrace();
			 }
		 }
	 
	 public void inventory(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				PreparedStatement stmt = conn.prepareStatement(query.getInventory());
				ResultSet rs = stmt.executeQuery();
				if(rs.isBeforeFirst()) {
					while(rs.next()) {
						request.setAttribute("cereal_bag", rs.getString("cereal_bag"));
						request.setAttribute("cereal_box", rs.getString("cereal_box"));
						request.setAttribute("similac_aimentum", rs.getString("similac_aimentum"));
						request.setAttribute("similac_advanced", rs.getString("similac_advanced"));
						request.setAttribute("baby_pacifier", rs.getString("baby_pacifier"));
						request.setAttribute("enfamily_12months", rs.getString("enfamily_12months"));
						request.setAttribute("enfagrow_toddler", rs.getString("enfagrow_toddler"));
						request.setAttribute("nestle_baby_formula", rs.getString("nestle_baby_formula"));
						request.setAttribute("good_start_formula", rs.getString("good_start_formula"));
						request.setAttribute("tide_pods", rs.getString("tide_pods"));
						request.setAttribute("dishwashing", rs.getString("dishwashing"));
						request.setAttribute("mini_shampoo", rs.getString("mini_shampoo"));
						request.setAttribute("shampoo", rs.getString("shampoo"));
						request.setAttribute("mini_conditioner", rs.getString("mini_conditioner"));
						request.setAttribute("conditioner", rs.getString("conditioner"));
						request.setAttribute("diapers_newborn", rs.getString("diapers_newborn"));
						request.setAttribute("diapers_1", rs.getString("diapers_1"));
						request.setAttribute("diapers_2", rs.getString("diapers_2"));
						request.setAttribute("diapers_3", rs.getString(("diapers_3")));
						request.setAttribute("diapers_4", rs.getString("diapers_4"));
						request.setAttribute("diapers_5", rs.getString("diapers_5"));
						request.setAttribute("diapers_6", rs.getString("diapers_6"));
						request.setAttribute("pull_ups", rs.getString("pull_ups"));
						request.setAttribute("sanitary_pad", rs.getString("sanitary_pad"));
						request.setAttribute("defense_underwearsm", rs.getString("defense_underwearsm"));
						request.setAttribute("defense_underwearl", rs.getString("defense_underwearl"));
						request.setAttribute("baby_wipes", rs.getString("baby_wipes"));
						request.setAttribute("bags", rs.getString("bags"));
						request.setAttribute("lotion", rs.getString("lotion"));
						request.setAttribute("baby_wash", rs.getString("baby_wash"));
						request.setAttribute("bottle9oz", rs.getString("bottle9oz"));
						request.setAttribute("bottle3oz", rs.getString("bottle3oz"));
						request.setAttribute("fruit_snack", rs.getString("fruit_snack"));
						request.setAttribute("soap", rs.getString("soap"));
						request.setAttribute("enfamil_nipple", rs.getString("enfamil_nipple"));
						request.setAttribute("enfamil_vitamin", rs.getString("enfamil_vitamin"));
					}
				}
				request.getRequestDispatcher("inventory.jsp").forward(request, response);
			}catch(SQLException | ClassNotFoundException | IOException | ServletException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void submitInventory(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String[] headers = {"cereal_bag", "cereal_box", "similac_aimentum", "similac_advanced", "baby_pacifier", "enfamily_12months", "enfagrow_toddler", "nestle_baby_formula", "good_start_formula", "tide_pods", "dishwashing", "mini_shampoo", "shampoo", "mini_conditioner", "conditioner", "diapers_newborn", "diapers_1", "diapers_2", "diapers_3", "diapers_4", "diapers_5", "diapers_6", "pull_ups", "sanitary_pad", "defense_underwearsm", "defense_underwearl", "baby_wipes", "bags", "lotion", "baby_wash", "bottle9oz", "bottle3oz", "fruit_snack", "soap", "enfamil_nipple", "enfamil_vitamin"};
			 for(int i=0; i<headers.length; i++) {
				 String temp = headers[i]+" = "+request.getParameter(headers[i]);
				 PreparedStatement stmt = conn.prepareStatement(query.setInventory(temp));
				 stmt.executeUpdate();
			 }
			 generate_action(request, response);
		 }catch(SQLException | ClassNotFoundException e) {
			 
		 }
	 }
	 
	 public void generate_assigned(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = assigned_page*12;
			 String color="";
			 String html = "";
			 PreparedStatement stmt = conn.prepareStatement(query.generateMeeting(offset));
			 ResultSet rs = stmt.executeQuery();
			 String sqlQuery = query.generateMeetingNoOffset();
			 PreparedStatement stmt3 = conn.prepareStatement(query.countMeeting());
			 ResultSet rs3 = stmt3.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 String room_num = rs.getString("room_num");
					 if(room_num == null) {
						 room_num="";
					 }
					 String nom = rs.getString("nom");
					 if(nom == null) {
						 nom="";
					 }
					 String surname = rs.getString("surname");
					 nom = nom +" "+surname;
					 String adultORminor = rs.getString("adultorminor");
					 if(adultORminor == null) {
						 adultORminor = "";
					 }
					 String agent = rs.getString("agent");
					 if(agent == null) {
						 agent="";
					 }
					 String family_id = rs.getString("family_id");
					 String identifier = rs.getString("identifier");
							 String temp = rs.getString("meeting_date");
							 String meeting_agent = rs.getString("meeting_agent");
							 LocalDate meeting;
							 LocalDate curDate = LocalDate.now();
							 if(temp != null) {
								 meeting = LocalDate.parse(temp);
							 }
							 else {
								 meeting = LocalDate.now().minusDays(20);
							 }
							 long days = ChronoUnit.DAYS.between(meeting,  curDate); 
							 if(days < 13) {
								 color = "#ccffb3";
							 }
							 else if(12 < days && 16 > days) {
								 color = "#ffff99";
							 }
							 else {
								 color = "#ffc2b3";
							 }
							 if(meeting_agent == null) {
								 meeting_agent = "";
							 }
							 String row = "<tr bgcolor=\""+color+"\"><td>"+room_num+"</td><td>"+nom+"</td><td>"+agent+"</td><td>"+meeting+"</td><td>"+meeting_agent+"</td><td><form method=\"post\" action=\"Admin\"> <input type=\"hidden\" name=\"method\" value=\"openMeeting\"><input type=\"hidden\" name=\"identifier\" value=\""+identifier+"\"><button class=\"button\" type=\"submit\">View Meeting</button></form></td>";
							 html += row;
						 }
					 }
	 
			 if(rs3.isBeforeFirst()) {
				 while(rs3.next()) {
					 request.setAttribute("count", (int) Math.ceil((double)rs3.getInt(1)/12));
				 }
			 }
			 request.setAttribute("query", sqlQuery);
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("meetingPortal.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs3.close();
			 conn.close();
		 }catch(SQLException | ClassNotFoundException | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void search_assigned(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = assigned_page*12;
			 String color="";
			 String html = "";
			 String field = request.getParameter("searchField");
			 String value = request.getParameter("search");
			 if(field.equals("agent")) {
				 value = "iLike $$"+value+"$$";
			 }
			 else if(field.equals("room_num")) {
				 value = "= "+value;
			 }
			 else if(field.equals("resident_name")) {
				 field = "resident.nom iLIKE $$%"+value+"%$$ ";
				 value = "OR resident.surname ILIKE $$%"+value+"%$$";
			 }
			 PreparedStatement stmt = conn.prepareStatement(query.searchMeeting(field, value));
			 ResultSet rs = stmt.executeQuery();
			 String sqlQuery = query.searchMeetingNoOffset(field, value);
			 PreparedStatement stmt3 = conn.prepareStatement(query.countMeetingSearch(field, value));
			 ResultSet rs3 = stmt3.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 String room_num = rs.getString("room_num");
					 if(room_num == null) {
						 room_num="";
					 }
					 String nom = rs.getString("nom");
					 if(nom == null) {
						 nom="";
					 }
					 String surname = rs.getString("surname");
					 nom = nom +" "+surname;
					 String adultORminor = rs.getString("adultorminor");
					 if(adultORminor == null) {
						 adultORminor = "";
					 }
					 String agent = rs.getString("agent");
					 if(agent == null) {
						 agent="";
					 }
					 String family_id = rs.getString("family_id");
					 String identifier = rs.getString("identifier");
							 String temp = rs.getString("meeting_date");
							 String meeting_agent = rs.getString("meeting_agent");
							 LocalDate meeting;
							 LocalDate curDate = LocalDate.now();
							 if(temp != null) {
								 meeting = LocalDate.parse(temp);
							 }
							 else {
								 meeting = LocalDate.now().minusDays(20);
							 }
							 long days = ChronoUnit.DAYS.between(meeting,  curDate); 
							 if(days < 13) {
								 color = "#ccffb3";
							 }
							 else if(12 < days && 16 > days) {
								 color = "#ffff99";
							 }
							 else {
								 color = "#ffc2b3";
							 }
							 if(meeting_agent == null) {
								 meeting_agent = "";
							 }
							 String row = "<tr bgcolor=\""+color+"\"><td>"+room_num+"</td><td>"+nom+"</td><td>"+agent+"</td><td>"+meeting+"</td><td>"+meeting_agent+"</td><td><form method=\"post\" action=\"Admin\"> <input type=\"hidden\" name=\"method\" value=\"openMeeting\"><input type=\"hidden\" name=\"identifier\" value=\""+identifier+"\"><button class=\"button\" type=\"submit\">View Meeting</button></form></td>";
								html += row;
						 }
					 }
	 
			 if(rs3.isBeforeFirst()) {
				 while(rs3.next()) {
					 request.setAttribute("count", (int) Math.ceil((double)rs3.getInt(1)/12));
				 }
			 }
			 request.setAttribute("query", sqlQuery);
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("meetingPortal.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs3.close();
			 conn.close();
		 }catch(SQLException | ClassNotFoundException | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void openMeeting(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String identifier = request.getParameter("identifier");
			 PreparedStatement stmt = conn.prepareStatement(query.openMeeting(identifier));
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 request.setAttribute("agent", rs.getString("meeting_agent"));
					 request.setAttribute("meeting_date", rs.getString("meeting_date"));
					 request.setAttribute("meeting_type", rs.getString("meeting_type"));
					 request.setAttribute("meeting_notes", rs.getString("meeting_notes"));
					 request.setAttribute("employment_status", rs.getString("employment_status"));
					 request.setAttribute("employment_type", rs.getString("employment_type"));
					 request.setAttribute("looking_job", rs.getString("looking_job"));
					 request.setAttribute("resume", rs.getString("resume"));
					 request.setAttribute("actions", rs.getString("actions"));
					 request.setAttribute("obstacles", rs.getString("obstacles"));
					 System.out.println("Obstacles: "+rs.getString("obstacles"));
					 request.setAttribute("experience", rs.getString("experience"));
					 request.setAttribute("income", rs.getString("income"));
					 request.setAttribute("transportation", rs.getString("transportation"));
					 request.setAttribute("lodging", rs.getString("lodging"));
					 request.setAttribute("moving_date", rs.getString("moving_date"));
					 request.setAttribute("housing_search", rs.getString("housing_search"));
					 request.setAttribute("housing_outside", rs.getString("housing_outside"));
					 request.setAttribute("colocation", rs.getString("colocation"));
					 request.setAttribute("housing_obstacles", rs.getString("housing_obstacles"));
					 request.setAttribute("housing_support", rs.getString("housing_support"));
				 }
			 }
			 PreparedStatement stmt2 = conn.prepareStatement(query.getAgent(identifier));
			 ResultSet rs2 = stmt2.executeQuery();
			 if(rs2.isBeforeFirst()) {
				 while(rs2.next()) {
					 request.setAttribute("assigned_agent", rs2.getString(1));
					 request.setAttribute("family_id", rs2.getString(2));
				 }
			 }
			 request.getRequestDispatcher("meetingViewer.jsp").forward(request, response); 
		 }catch(SQLException | IOException | ClassNotFoundException | ServletException e) {
			 e.printStackTrace();
		 }
	 }

	 public void exportMeeting(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 XSSFWorkbook workbook = new XSSFWorkbook(); 
		     XSSFSheet spreadsheet = workbook.createSheet(" Meeting Data "); 
		     XSSFRow row;
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String first_date = request.getParameter("first_date");
			 String second_date = request.getParameter("second_date");
			 String agent_name = request.getParameter("agent_name");
			 String assigned_agent = request.getParameter("assigned_agent");
			 String uci = request.getParameter("uci");
			 String irb = request.getParameter("irb_decision");
			 String employment = request.getParameter("employment_status");
			 String looking_housing = request.getParameter("looking_housing");
			 String move_out = request.getParameter("move_out");
			 String where = "WHERE ";
			 String temp="";
			 if(first_date.equals("")==false) {
				 temp = "meeting_date > '"+first_date+"'";
			 }
			 if(second_date.equals("")==false) {
				 if(temp.equals("")) {
					 temp = "meeting_date < '"+second_date+"'";
				 }
				 else {
					 temp = temp +" AND meeting_date < '"+second_date+"'";
				 }
			 }
			 if(agent_name.equals("")==false) {
				 if(temp.equals("")) {
					 temp = "meeting_agent = '"+agent_name+"'";
				 }
				 else {
					 temp = temp +" AND meeting_agent = '"+agent_name+"'";
				 }
			 }
			 if(assigned_agent.equals("")==false) {
				 if(temp.equals("")) {
					 temp = "assigned_agent = '"+assigned_agent+"'";
				 }
				 else {
					 temp = temp + " AND assigned_agent = '"+assigned_agent+"'";
				 }
			 }
			 if(uci.equals("")==false) {
				 if(temp.equals("")) {
					 temp = "uci = '"+uci+"'";
				 }
				 else {
					 temp = temp +" AND uci = '"+uci+"'";
				 }
			 }
			 if((irb==null)==false) {
				 if(temp.equals("")) {
					 temp = "irb_decision = '"+irb+"'";
				 }
				 else {
					 temp = temp + " AND irb_decision = '"+irb+"'";
				 }
			 }
			 if((employment.equals("N/A"))==false) {
				 if(temp.equals("")) {
					 temp = "employement_status = '"+employment+"'";
				 }
				 else {
					 temp = temp +" AND employment_status = '"+employment+"'";
				 }
			 }
			 if((looking_housing.equals("N/A"))==false) {
				 if(temp.equals("")) {
					 temp = "lodging = '"+looking_housing+"'";
				 }
				 else {
					 temp = temp + "lodging = '"+looking_housing+"'";
				 }
			 }
			 if((move_out==null)==false) {
				 if(temp.equals("")) {
					 temp = "moving_date = '"+move_out+"'";
				 }
				 else {
					 temp = temp + " AND moving_date = '"+move_out+"'";
				 }
			 }
			 String setter = "";
			 if(temp.equals("")==false) {
				 where = where + temp;
				 setter = where;
			 }
			 PreparedStatement stmt = conn.prepareStatement(query.findMeetings(setter));
			 ResultSet rs = stmt.executeQuery();
			 int count = 2;
			 Map<String, Object[]> residentData = new TreeMap<String, Object[]>();
			 residentData.put("1", new Object[] {"Province", "City", "Hotel", "Priority List", "DIR", "UCI", "Bracelet No", "Room No", "Name", "Fam. Comp.", "Arrival at hotel (Y/M/D)", "Time in Hotel (months)", "Adult or minor", "Last meeting recorded in this tracker", "Meeting date (Y/M/D)", "Staff Name", "Meeting Notes", "Type of Meeting", "Presently Employed", "Employment Type", "Currently Looking for Employment", "Resume is Prepared", "Job Cold Calls", "In Person Visit", "Applied for Jobs Online", "Followed Up On Previous Applications", "Went to Job Fair", "Consulted Employment Service", "Used JobBank.gc.ca", "Education Level", "Education Field", "Main Industry", "Job Search Obstacle 1", "Job Search Obstacle 2", "Job Search Obstacle 3", "Current Income", "Means of Transportation", "Next Lodging Found", "Moving Date", "Way of Finding Lodging", "Presently Looking for Housing", "Housing Search Outside Urban Area", "Colocation", "Community Help", "Housing Search Obstacle 1", "Housing Search Obstacle 2", "Housing Search Obstacle 3"});
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 String uci_value = rs.getString("uci");
					 String bracelet = rs.getString("bracelet");
					 String room_num = rs.getString("room_num");
					 String name = rs.getString("nom");
					 String family_comp = rs.getString("family_composition");
					 String hotel_arrival = rs.getString("arrival_date");
					 LocalDate arrival;
					 LocalDate curDate = LocalDate.now();
					 if(hotel_arrival != null) {
						 arrival = LocalDate.parse(hotel_arrival);
						 System.out.println(arrival);					 }
					 else {
						 arrival = LocalDate.now();
					 }
					 int months = Period.between(arrival, curDate).getYears() * 12 + Period.between(arrival, curDate).getMonths();
					 System.out.println(months);
					 String adultORminor = rs.getString("adultorminor");
					 String most_recent_meeting = "";
					 PreparedStatement stmt2 = conn.prepareStatement(query.kidsMostRecentMeeting(rs.getString("family_id")));
					 ResultSet rs2 = stmt2.executeQuery();
					 if(rs2.isBeforeFirst()) {
						 while(rs2.next()) {
							 most_recent_meeting = rs2.getString(1);
						 }
					 }
					 String meeting_date = rs.getString("meeting_date");
					 String agent = rs.getString("meeting_agent");
					 String meeting_notes = rs.getString("meeting_notes");
					 String meeting_type = rs.getString("meeting_type");
					 String employment_status = rs.getString("employment_status");
					 String employment_type = rs.getString("employment_type");
					 String looking_employment = rs.getString("looking_job");
					 String resume = rs.getString("resume");
					 String cold_calls = "No";
					 if((rs.getString("actions")).contains("Cold calls to potential employers")) {
						 cold_calls = "Yes";
					 }
					 String visits = "No";
					 if((rs.getString("actions")).contains("In person visits to potential workplaces")) {
						 visits = "Yes";
					 }
					 String online = "No";
					 if(rs.getString("actions").contains("Applied for jobs online")) {
						 online = "Yes";
					 }
					 String previous = "No";
					 if(rs.getString("actions").contains("Followed up on previous applications")) {
						 previous = "Yes";
					 }
					 String fair = "No";
					 if(rs.getString("actions").contains("Went to job fairs")) {
						 fair = "Yes";
					 }
					 String service = "No";
					 if(rs.getString("actions").contains("Consulted employment services")) {
						 service = "Yes";
					 }
					 String education = "";
					 PreparedStatement stmt3 = conn.prepareStatement(query.getEducation(uci_value));
					 ResultSet rs3 = stmt3.executeQuery();
					 if(rs3.isBeforeFirst()) {
						 while(rs3.next()) {
							 education = rs3.getString(1);
						 }
					 }
					 String experience = rs.getString("experience");
					 String job_obstacles = rs.getString("obstacles");
					 if(job_obstacles.length()!=0) {
						 job_obstacles = job_obstacles.substring(1, job_obstacles.length()-1);
					 }
					 String[] obstacle = job_obstacles.split("\\s*,\\s*");
					 String[] obstacles = {"", "", ""};
					 int temp_count = 0;
					 while(temp_count<3 && temp_count<obstacle.length) {
						 obstacles[temp_count] = obstacle[temp_count];
						 temp_count = temp_count + 1;
					 }
					 String income = rs.getString("income");
					 String transportation = rs.getString("transportation");
					 String lodging = rs.getString("lodging");
					 String moving_date = rs.getString("moving_date");
					 if(moving_date == null) {
						 moving_date = "";
					 }
					 String housing_search = rs.getString("housing_search");
					 String housing_outside = rs.getString("housing_outside");
					 String colocation = rs.getString("colocation");
					 String housing_support = rs.getString("housing_support");
					 String housing_obstacles = rs.getString("housing_obstacles");
					 if(housing_obstacles.length()!=0) {
						 housing_obstacles = housing_obstacles.substring(1, housing_obstacles.length() -1);
						}
					 String[] housing_obs = housing_obstacles.split("\\s*,\\s*");
					 String[] housing_ob = {"", "", ""};
					 String jobBank = rs.getString("jobBank");
					 String diplomaIndustry = rs.getString("diplomaIndustry");
					 String wayOfFindingLodging = rs.getString("wayOfFindingLodging");
					 int tmp_count = 0;
					 while(tmp_count<3 && tmp_count<housing_obs.length) {
						 housing_ob[tmp_count] = housing_obs[tmp_count];
						 tmp_count = tmp_count + 1;
					 }
					 residentData.put(""+count, new Object[] {"Ontario", "Cornwall", "Dev Hotel", "", "", uci_value, bracelet, room_num, name, family_comp, arrival, months, adultORminor, most_recent_meeting, meeting_date, agent, meeting_notes, meeting_type, employment_status, employment_type, looking_employment, resume, cold_calls, visits, online, previous, fair, service, jobBank, education, diplomaIndustry, experience, obstacles[0], obstacles[1], obstacles[2], income, transportation, lodging, moving_date, wayOfFindingLodging, housing_search, housing_outside, colocation, housing_support, housing_ob[0], housing_ob[1], housing_ob[2]});
					 count += 1;
				 }
			 }
			 Set<String> keyid = residentData.keySet();
			 int rowid = 0;
			 for(String key: keyid) {
				 row = spreadsheet.createRow(rowid++);
				 Object[] objectArr = residentData.get(key);
				 int cellid = 0;
				 
				 for(Object obj : objectArr) {
					 Cell cell = row.createCell(cellid++);
					 cell.setCellValue(""+obj+"");
				 }
			 }
			 LocalDate curDate = LocalDate.now();
			 response.setContentType("application/vnd.ms-excel");
			    response.setHeader("Content-Disposition", "attachment; filename=Meeting Tracker-"+curDate+".xlsx");

			    ServletOutputStream outputStream = response.getOutputStream();
			    workbook.write(outputStream);
			    workbook.close();

			    outputStream.close();
			 generate_assigned(request, response);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void changePage(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 String val ="";
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 BufferedReader reader = request.getReader();
			 StringBuilder requestBody = new StringBuilder();
			 String line;
			 while((line = reader.readLine()) != null) {
				 requestBody.append(line);
			 }
			 String jsonData = requestBody.toString();
			 JSONObject jsonObject = new JSONObject(jsonData);
			 int num = jsonObject.getInt("page");
			 num = num - 1;
			 int offset = num*12;
			 String sqlQuery = jsonObject.getString("query");
			 System.out.println(sqlQuery+" "+offset);
			 PreparedStatement stmt = conn.prepareStatement(sqlQuery+" "+offset);
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						if(sqlQuery.contains("FROM actions")) {
						String agent_name = rs.getString("agent");
						if(agent_name==null)
							agent_name="";
						String actions = rs.getString("actions");
						if(actions==null)
							actions="";
						String date = rs.getString("date_of_modification");
						if(date==null )
							date = "";
						String time = rs.getString("time_of_modification");
						if(time==null)
							time="";
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num="";
						String nom = rs.getString("nom");
						if(nom==null)
							nom="";
						String surname = rs.getString("surname");
						if(surname==null)
							surname="";
						String original = rs.getString("original");
						if(original==null)
							original="";
						String modified = rs.getString("modified");
						if(modified==null)
							modified="";
						String row = "<tr height=\"45px\" style=\"overflow: hidden;\"><td>" + agent_name.toString() + "</td><td>" + actions.toString() + "</td><td>" + date.toString() + "</td><td>" + time.toString() + "</td><td>" + room_num.toString() + "</td><td>" + nom.toString() + "</td><td>" + surname.toString() +"</td><td>" + original.toString() + "</td><td>" + modified.toString() +"</td><td>\r\n";
						val += row.toString();
						}
						else if(sqlQuery.contains("LEFT JOIN meeting")) {
							System.out.println("HERE");
							String color = "";
							String room_num = rs.getString("room_num");
							 if(room_num == null) {
								 room_num="";
							 }
							 String nom = rs.getString("nom");
							 if(nom == null) {
								 nom="";
							 }
							 String surname = rs.getString("surname");
							 nom = nom +" "+surname;
							 String adultORminor = rs.getString("adultorminor");
							 if(adultORminor == null) {
								 adultORminor = "";
							 }
							 String agent = rs.getString("agent");
							 if(agent == null) {
								 agent="";
							 }
							 String family_id = rs.getString("family_id");
							 String identifier = rs.getString("identifier");
									 String temp = rs.getString("meeting_date");
									 String meeting_agent = rs.getString("meeting_agent");
									 LocalDate meeting;
									 LocalDate curDate = LocalDate.now();
									 if(temp != null) {
										 meeting = LocalDate.parse(temp);
									 }
									 else {
										 meeting = LocalDate.now().minusDays(20);
									 }
									 long days = ChronoUnit.DAYS.between(meeting,  curDate); 
									 if(days < 13) {
										 color = "#ccffb3";
									 }
									 else if(12 < days && 16 > days) {
										 color = "#ffff99";
									 }
									 else {
										 color = "#ffc2b3";
									 }
									 if(meeting_agent == null) {
										 meeting_agent = "";
									 }
									 String row = "<tr bgcolor=\""+color+"\"><td>"+room_num+"</td><td>"+nom+"</td><td>"+agent+"</td><td>"+meeting+"</td><td>"+meeting_agent+"</td><td><form method=\"post\" action=\"Admin\"> <input type=\"hidden\" name=\"method\" value=\"openMeeting\"><input type=\"hidden\" name=\"identifier\" value=\""+identifier+"\"><button class=\"button\" type=\"submit\">View Meeting</button></form></td>";
									 val += row;
								 }
						else if(sqlQuery.contains("FROM reports")) {
							String agent_name = rs.getString("agent");
							if(agent_name==null)
								agent_name="";
							String title = rs.getString("title");
							if(title==null)
								title="";
							String room_num = rs.getString("room_num");
							if(room_num==null)
								room_num = "";
							String responded = rs.getString("responded");
							if(responded==null )
								responded = "";
							String report_identifier = rs.getString("report_identifier");
							if(report_identifier ==null)
								report_identifier="";
							String res_identifiers = rs.getString("res_identifiers");
							if(res_identifiers==null)
								res_identifiers="";
							String date = rs.getString("date_of_writing");
							if(date==null) {
								date="";
							}
							if(responded.equals("No")) {
								title = "<b>"+title+"</b>";
								agent_name = "<b>"+agent_name+"</b>";
								room_num = "<b>"+room_num+"</b>";
								date = "<b>"+date+"</b>";
							}
							res_identifiers = res_identifiers.substring(1, res_identifiers.length() -1);
							String rooms ="";
							String names="";
							ArrayList<String> room_tests = new ArrayList<String>();
							Boolean copy= false;
							String[] identifiers = res_identifiers.split("\\s*,\\s*");
							for(int i=0; i<identifiers.length; i++) {
								PreparedStatement stmt3 = conn.prepareStatement(query.open(identifiers[i]));
								ResultSet rs3 = stmt3.executeQuery();
								if(rs3.isBeforeFirst()) {
									while(rs3.next()) {
										room_tests.add(rs3.getString("room_num"));
										names = names + rs3.getString("nom")+" "+rs3.getString("surname")+", ";
										for(int j=0; j<room_tests.size(); j++) {
											if(room_tests.get(j).equals(rs3.getString("room_num"))) {
												copy=true;
												break;
											}
										}
								if(copy==false) {
									rooms = rooms + rs3.getString("room_num") +", ";
								}
							}
						}
					}
							if(responded.equals("No")) {
								names = "<b>"+names+"</b>";
							}
							String row = "<form method=\"post\" action=\"Admin\"> <tr><td>" + agent_name.toString() + "</td><td>" + title + "</td><td>" + date.toString() + "</td><td>" + room_num.toString() + "</td><td>" + names.toString() + "</td><td>\r\n"
									+ "<input type=\"hidden\" name=\"method\" value=\"openReport\">\r\n"
						            + "<input type=\"hidden\" name=\"id\" value=\"" + report_identifier + "\">\r\n"
						            + "<button class=\"buttonss\" type=\"submit\">Open Report</button>"
						            + "</td></form>";
							val += row.toString();
						}
							 }
						}
			 request.setAttribute("page", num+1);
			 request.setAttribute("val", val);
			 request.setAttribute("query", sqlQuery);
			 
			 response.setContentType("application/json");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write(val);
			 response.getWriter().close();
			 html = "";
			 pagination = "";
			 rs.close();
		 }catch(IOException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void changeAssigned(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 System.out.println("HERE");
			 String agent = request.getParameter("assigned_agent");
			 String identifier = request.getParameter("family_id");
			 PreparedStatement stmt = conn.prepareStatement(query.reassignAgent(agent, identifier));
			stmt.executeUpdate();
			conn.close();
			generate_assigned(request, response);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || !request.isRequestedSessionIdValid()) {
		    response.sendRedirect("index.jsp");       
		}
		else {
		doGet(request,response);
		String method = request.getParameter("method");
		if(method == null) {
			 method = "changePage";
		 }
		if (method.equals("login")) {
			Agent agent = new Agent();
			agent.page = 0;
			request.setAttribute("fname", firstName);
			request.getRequestDispatcher("adminWelcomePage.jsp").forward(request, response);
		}
		else if(method.equals("administrator")) {
			page_action = 0;
			request.setAttribute("html", html);
			generate_action(request, response);
			}
		else if(method.equals("agent")) {
			request.getRequestDispatcher("agentPortal.jsp").forward(request, response);
		}
		else if(method.equals("modifyAgent")) {
			generate_agents(request, response);
		}
		else if(method.equals("open")) {
			open(request, response);
		}
		else if(method.equals("delete")) {
			delete(request, response);
		}
		else if(method.equals("updateAgent")) {
			update(request, response);
		}
		else if(method.equals("addAgent")) {
			response.sendRedirect("addAgent.jsp");
		}
		else if(method.equals("createAgent")) {
			createAgent(request, response);
		}
		else if(method.equals("searchAgent")) {
			searchAgent(request, response);
		}
		else if(method.equals("search")) {
			if(page_action == 0) {
				search_action(request, response);
			}
			else {
				search_action_Move(request, response);
			}
		}
		else if(method.equals("modifyRoom")) {
			page_room = 0;
			generate_room(request, response);
		}
		else if(method.equals("previous_room")) {
			page_room = page_room - 1;
			generate_room(request, response);
		}
		else if(method.equals("next_room")) {
			page_room = page_room + 1;
			generate_room(request, response);
		}
		else if(method.equals("search_room")) {
			page_room = 0;
			search_room(request, response);
		}
		else if(method.equals("addRoom")) {
			response.sendRedirect("addRoom.jsp");
		}
		else if(method.equals("createRoom")) {
			createRoom(request, response);
		}
		else if(method.equals("openRoom")) {
			openRoom(request, response);
		}
		else if(method.equals("updateRoom")) {
			updateRoom(request, response);
		}
		else if(method.equals("deleteRoom")) {
			deleteRoom(request, response);
		}
		else if(method.equals("next_actions")) {
			page_action = page_action + 1;
			generate_action(request, response);
		}
		else if(method.equals("previous_actions")) {
			page_action = page_action - 1;
			generate_action(request, response);
		}
		else if(method.equals("next_actions_search")) {
			page_action = page_action + 1;
			search_action_Move(request, response);
		}
		else if(method.equals("previous_actions_search")) {
			page_action = page_action - 1;
			search_action_Move(request, response);
		}
		else if(method.equals("next_reports")) {
			page_action = page_action + 1;
			generateReports(request, response);
		}
		else if(method.equals("previous_reports")) {
			page_action = page_action - 1;
			generateReports(request, response);
		}
		else if(method.equals("next_search_reports")) {
			page_action = page_action + 1;
			searchReports(request, response);
		}
		else if(method.equals("previous_search_reports")) {
			page_action = page_action - 1;
			searchReports(request, response);
		}
		else if(method.equals("reports")) {
			generateReports(request, response);
		}
		else if(method.equals("openReport")) {
			openReports(request, response);
		}
		else if(method.equals("submitResponse")) {
			submitResponse(request, response);
		}
		else if(method.equals("searchReports")) {
			searchReports(request, response);
		}
		else if(method.equals("stats")) {
			stats(request, response);
		}
		else if(method.equals("home")) {
			generate_action(request, response);
		}
		else if(method.equals("inventory")) {
			inventory(request, response);
		}
		else if(method.equals("submitInventory")) {
			submitInventory(request, response);
		}
		else if(method.equals("assigned")) {
			assigned_page = 0;
			generate_assigned(request, response);
		}
		else if(method.equals("previous_meeting")) {
			assigned_page = assigned_page - 1;
			generate_assigned(request, response);
		}
		else if(method.equals("next_meeting")) {
			assigned_page = assigned_page + 1;
			generate_assigned(request, response);
		}
		else if(method.equals("openMeeting")) {
			openMeeting(request, response);
		}
		else if(method.equals("exportMeeting")) {
			response.sendRedirect("exportPage.jsp");
		}
		else if(method.equals("exporting")) {
			exportMeeting(request, response);
		}
		else if(method.equals("changePage")) {
			changePage(request, response);
		}
		else if(method.equals("searchAssigned")) {
			search_assigned(request, response);
		}
		else if(method.equals("changeAssigned")) {
			changeAssigned(request, response);
		}
		}
	}
}