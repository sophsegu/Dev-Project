package Tracker;

import java.io.IOException;
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

@WebServlet("/Client")
public class Client extends HttpServlet{
	private List<String> Identifiers = new ArrayList<String>();
	private static String firstName;
	private String html = "";
	public static HttpSession session;
	private DataSource datasource = null;
	private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String nom_utilisateur = "postgres";
    private final String code = "12345";
    static int page = 0;
    private String pagination = "";
    private sql query = new sql();
    
	public Client(String name) {
		firstName = name;
	}
	
	public Client() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		 String role = (String) session.getAttribute("name");
		 String name = (String) session.getAttribute("name");
		 System.out.println(" CLIENT Role: "+role+" Name: "+name);
	}
	
	 public void init() throws ServletException {
			try {
				InitialContext ctx = new InitialContext();
				datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/LiveDataSource");
			} catch (Exception e) {
				throw new ServletException(e.toString());
			}

		}
	 
	 public void generate(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 int offset = page*12;
			 PreparedStatement stmt = conn.prepareStatement(query.generate(offset));
			 ResultSet rs = stmt.executeQuery();
			 PreparedStatement stmt_2 = conn.prepareStatement(query.count());
			 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String id = rs.getString("identifier");
						if(id==null)
							id="";
						String status = rs.getString("Status");
						if(status==null)
							status="";
						String uci = rs.getString("UCI");
						if(uci==null )
							uci = "";
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num="";
						String f_name = rs.getString("nom");
						if(f_name==null)
							f_name="";
						String s_name = rs.getString("surname");
						if(s_name==null)
							s_name="";
						String dob = rs.getString("dob");
						if(dob==null)
							dob="";
						String gender = rs.getString("gender");
						if(gender==null)
							gender="";
						String COO = rs.getString("citizenship");
						if(COO==null)
							COO="";
						Identifiers.add(id);
						String row = "<tr><td>" + status.toString() + "</td><td>" + uci.toString() + "</td><td>" + room_num.toString() + "</td><td>" + f_name.toString() + "</td><td>" + s_name.toString() + "</td><td>" + dob.toString() + "</td><td>" + gender.toString() + "</td><td>" + COO.toString() + "</td><td><form method=\"post\" action=\"Client\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"open\">\r\n"
					            + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">\r\n"
					            + "<button class=\"buttonss\" type=\"submit\">Open Passport</button>"
					            + "</td></form>";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 int num_results = rs_2.getInt(1);
					 if(page==0) {
						 pagination = "<form method=\"post\" action=\"Client\">" + "<input type=\"hidden\" name=\"method\" value=\"next\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>" + " </form>";
					 }
					 else if(num_results - offset <= 10) {
						 pagination = "<form method=\"post\" action=\"Client\">" + "<input type=\"hidden\" name=\"method\" value=\"previous\">" + "<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>" + " </form>";
						 
					 }
					 else {
						 pagination = "<form method=\"post\" action=\"Client\">" + "<input type=\"hidden\" name=\"method\" value=\"previous\">" +"<button class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>"+"</form>"+ "<form method=\"post\" action=\"Client\">" + "<input type=\"hidden\" name=\"method\" value=\"next\">" + "<button class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>"+ "</form>";
					 }
				 }
			 }
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			request.getRequestDispatcher("clientPortal.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void search(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String values = request.getParameter("search");
			 String original_values = values;
			 String Field = request.getParameter("searchField");
			 String original_field = Field;
			 int offset = page*12;
			 if(Field.equals("room_num")) {
				 try {
					 int num = Integer.valueOf(values);
				 }catch(NumberFormatException e) {
					 if(e != null) {
						 values = " 99999999999999999";
					 }
				 }
			 }
			 if(Field.equals("nom") | Field.equals("surname") | Field.equals("uci"))
					values = " iLIKE '%"+values+"%'";
			 else {
				 values = "="+values;
			 }
			 PreparedStatement stmt = conn.prepareStatement(query.search(Field, values, offset));
			 ResultSet rs = stmt.executeQuery();
			 PreparedStatement stmt_2 = conn.prepareStatement(query.count_search(Field, values));
			 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String id = rs.getString("identifier");
						if(id==null)
							id="";
						String status = rs.getString("Status");
						if(status==null)
							status="";
						String uci = rs.getString("UCI");
						if(uci==null )
							uci = "";
						String room_num = rs.getString("room_num");
						if(room_num==null)
							room_num="";
						String f_name = rs.getString("nom");
						if(f_name==null)
							f_name="";
						String s_name = rs.getString("surname");
						if(s_name==null)
							s_name="";
						String dob = rs.getString("dob");
						if(dob==null)
							dob="";
						String gender = rs.getString("gender");
						if(gender==null)
							gender="";
						String COO = rs.getString("citizenship");
						if(COO==null)
							COO="";
						Identifiers.add(id);
						String row = "<tr><td>" + status.toString() + "</td><td>" + uci.toString() + "</td><td>" + room_num.toString() + "</td><td>" + f_name.toString() + "</td><td>" + s_name.toString() + "</td><td>" + dob.toString() + "</td><td>" + gender.toString() + "</td><td>" + COO.toString() + "</td><td><form method=\"post\" action=\"Client\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"open\">\r\n"
					            + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">\r\n"
					            + "<button class=\"buttonss\" type=\"submit\">Open Passport</button>"
					            + "</td></form>";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 int num_results = rs_2.getInt(1);
					 if(num_results - offset <= 12 && num_results <= 12) {
						 pagination = "";
					 }
					 else if(num_results > 12 && num_results - offset <= 12) {
						 pagination = "<form method=\"post\" action=\"Client\">"
								 		+ "<input type=\"hidden\" name=\"search\" value=\""+original_values+"\">\r\n"
								 		+ "<input type=\"hidden\" name=\"searchField\" value=\""+original_field+"\">\r\n"
								 		+ "<input type=\"hidden\" name=\"method\" value=\"previousSearch\">"
								 		+ "<button type=\"submit\" class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>" + "</form>";
					 }
					 else if(num_results > 12 && num_results - offset > 12 && page == 0) {
						 pagination = "<form method=\"post\" action=\"Client\">"
							 		+ "<input type=\"hidden\" name=\"search\" value=\""+original_values+"\">\r\n"
							 		+ "<input type=\"hidden\" name=\"searchField\" value=\""+original_field+"\">\r\n"
							 		+ "<input type=\"hidden\" name=\"method\" value=\"nextSearch\">"
							 		+ "<button type=\"submit\" class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>" + "</form>";
						 }
					 else if(num_results > 12 && num_results - offset > 12 && page > 0) {
						 pagination = "<form method=\"post\" action=\"Client\">" 
							 		+ "<input type=\"hidden\" name=\"search\" value=\""+original_values+"\">\r\n"
							 		+ "<input type=\"hidden\" name=\"searchField\" value=\""+original_field+"\">\r\n"	 		
							 		+ "<input type=\"hidden\" name=\"method\" value=\"previousSearch\">" 
							 		+ "<button type=\"submit\" class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>"+"</form>"
							 		+ "<form method=\"post\" action=\"Client\">" 
							 		+ "<input type=\"hidden\" name=\"method\" value=\"nextSearch\">" 
							 		+ "<input type=\"hidden\" name=\"search\" value=\""+original_values+"\">\r\n"
							 		+ "<input type=\"hidden\" name=\"searchField\" value=\""+original_field+"\">\r\n"
							 		+ "<input type=\"hidden\" name=\"method\" value=\"nextSearch\">"
							 		+ "<button type=\"submit\" class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>"+ "</form>";
					 }
					 else {
						 pagination = "<form method=\"post\" action=\"Client\">" 
								 		+ "<input type=\"hidden\" name=\"search\" value=\""+original_values+"\">\r\n"
								 		+ "<input type=\"hidden\" name=\"searchField\" value=\""+original_field+"\">\r\n"	 		
								 		+ "<input type=\"hidden\" name=\"method\" value=\"previousSearch\">" 
								 		+ "<button type=\"submit\" class=\"btn\"><i class=\"fa fa-caret-left\"></i></button>"+"</form>"
								 		+ "<form method=\"post\" action=\"Client\">" 
								 		+ "<input type=\"hidden\" name=\"method\" value=\"nextSearch\">" 
								 		+ "<input type=\"hidden\" name=\"search\" value=\""+original_values+"\">\r\n"
								 		+ "<input type=\"hidden\" name=\"searchField\" value=\""+original_field+"\">\r\n"
								 		+ "<button type=\"submit\" class=\"btn\"><i class=\"fa fa-caret-right\"></i></button>"+ "</form>";
					 }
				 }
			 }
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("clientPortal.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(ClassNotFoundException | SQLException | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void open(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String id = request.getParameter("id");
			 PreparedStatement stmt = conn.prepareStatement(query.open(id));
			 ResultSet rs = stmt.executeQuery();
			 String family_id = "";
			 String row = "<tr><th>Name: </th><th>Room: </th><th>Date: </th><th>Items: </th></tr>";
			 int number = 0;
			 String nom = "";
			 String room_num = "";
			 String surname ="";
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 number = Integer.parseInt(rs.getString("identifier"));
					 request.setAttribute("id", request.getParameter("id"));
					 request.setAttribute("nom", rs.getString("nom"));
					 nom = rs.getString("nom");
					 family_id = rs.getString("family_id");
					 request.setAttribute("family_id", rs.getString("family_id"));
					 request.setAttribute("surname", rs.getString("surname"));
					 request.setAttribute("image", rs.getString("image_path"));
					 surname = rs.getString("surname");
					 request.setAttribute("status", rs.getString("status"));
					 request.setAttribute("uci", rs.getString("uci"));
					 request.setAttribute("bracelet", rs.getString("bracelet"));
					 request.setAttribute("room_num", rs.getString("room_num"));
					 room_num = rs.getString("room_num");
					 request.setAttribute("nom", rs.getString("nom"));
					 request.setAttribute("surname", rs.getString("surname"));
					 request.setAttribute("dob", rs.getString("dob"));
					 LocalDate dob;
					 LocalDate curDate = LocalDate.now();
					 if(rs.getString("dob") != null) {
						 dob = LocalDate.parse(rs.getString("dob"));
					 }
					 else {
						 dob = LocalDate.now();
					 }
					 int age = Period.between(dob, curDate).getYears();
					 request.setAttribute("age", age);
					 request.setAttribute("gender", rs.getString("gender"));
					 request.setAttribute("primary_language", rs.getString("primary_language"));
					 request.setAttribute("other_language", rs.getString("other_language"));
					 request.setAttribute("citizenship", rs.getString("citizenship"));
					 request.setAttribute("phone", rs.getString("phone"));
					 request.setAttribute("email", rs.getString("email"));
					 request.setAttribute("family_composition", rs.getString("family_composition"));
					 
			 }
			 String[] store_headers = {"cereal_bag", "cereal_box", "similac_aimentum", "similac_advanced", "baby_pacifier", "enfamily_12months", "enfagrow_toddler", "nestle_baby_formula", "good_start_formula", "tide_pods", "dishwashing", "mini_shampoo", "shampoo", "mini_conditioner", "conditioner", "diapers_newborn", "diapers_1", "diapers_2", "diapers_3", "diapers_4", "diapers_5", "diapers_6", "pull_ups", "sanitary_pad", "defense_underwearSM", "defense_underwearL", "baby_wipes", "bags", "lotion", "baby_wash", "bottle9oz", "bottle3oz", "fruit_snack", "enfamil_nipple", "enfamil_vitamin", "soap"};
			 String[] headers = {"Heinz Cereal Bag", "Gerber Cereal Box", "Similac Aimentum for Allergies & Colic", "Similac Advanced (6-24 months)", "Baby Pacifier", "Enfamil A+ Baby Formula (0-12 Months)", "Enfamil A+ Toddler Nutritional Drink (1-5 Years)", "NESTLE GOOD START PLUS 2 Powder Baby Forumla For Babies 6+ Months", "GOOD START PLUS 1 Powder Baby Forumla For Babies 0+", "Tide Pods", "Dishwashing Liquid", "Mini Shampoo", "Shampoo", "Mini Conditioner", "Conditioner", "Diapers (Newborn)", "Diapers (Size 1)", "Diapers (Size 2)", "Diapers (Size 3)", "Diapers (Size 4)", "Diapers (Size 5)", "Diapers (Size 6)", "Pull Ups (Size 5T - 6T)", "Sanitary Pads", "Defense Underwear (S/M)", "Defense Underwear (L)", "Baby Wipes", "Bags", "Body Lotion", "Baby Body Wash", "9oz Bottles", "5oz Bottles", "Fruit Snack", "Enfamil Soft Nipple", "Enfamil Liquid Vitamin Supplement", "Soap Bar"};
			 PreparedStatement store = conn.prepareStatement(query.passport(family_id));
			 ResultSet passport = store.executeQuery();
			 if(passport.isBeforeFirst()) {
				 while(passport.next()) {
					 String name = passport.getString("nom");
					 String room = passport.getString("room_num");
					 String date_of_visit = passport.getString("date_of_visit");
					 row = row + "<tr><td>"+name+"</td><td>"+room+"</td><td>"+date_of_visit+"</td><td>";
					 String values = "";
					 for(int i=0; i<store_headers.length; i++) {
						 int temp = passport.getInt(store_headers[i]);
						 if(temp>0) {
							 values = values+" "+headers[i]+" X "+temp+"";
							 
						 }
					 }
					 row = row + values+"</td>";
				 }
			 }
			 }
			 request.setAttribute("row", row);
			 request.getRequestDispatcher("clientPage.jsp").forward(request, response);
		 }catch(ClassNotFoundException | SQLException | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void storeVisit(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String id = request.getParameter("id");
			 PreparedStatement stmt = conn.prepareStatement(query.nameAndFamilyID(id));
			 ResultSet rs = stmt.executeQuery();
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 long millis=System.currentTimeMillis();  
		      
			 java.sql.Date date = new java.sql.Date(millis); 
			 String name = "";
			 String family_id = "";
			 String room_num = "";
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 name = rs.getString(1);
					 family_id = rs.getString(2);
					 room_num = rs.getString(3);
				 }
			 }
			 PreparedStatement stmt2 = conn.prepareStatement(query.createTransaction(name, id, family_id, room_num, date));
			 stmt2.executeUpdate();
			 request.getRequestDispatcher("passport.jsp").forward(request, response);
		 }catch(SQLException | ClassNotFoundException | ServletException | IOException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void openFamily(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, nom_utilisateur, code); 
			String identifier = request.getParameter("family_id");
			PreparedStatement stmt = conn.prepareStatement(query.openFamily(identifier));
			ResultSet rs = stmt.executeQuery();
			PreparedStatement stmt2 = conn.prepareStatement(query.countfamily(identifier));
			ResultSet rs2 = stmt2.executeQuery();
			int number = 0;
			 String nom = "";
			 String room_num = "";
			 String surname ="";
			 String card = "";
			 String card2 = "";
			 String card3 = "";
			 if(rs2.isBeforeFirst()) {
				 while(rs2.next()) {
					 String temp = rs2.getString(1);
					 number = Integer.parseInt(temp);
				 }
			 }
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					if(number%2==0) {
					card = card + "<div class=\"fiche\"> <div class=\"card-header bg-transparent text-center\">"
							+ "<img class=\"profile_img\" src="+rs.getString("image_path")+" alt=\"profile pic\">"
							+ "<h3>"+rs.getString("nom")+"</h3>"
							+ "<h3>"+rs.getString("surname")+"</h3>"
							+ "</div>"
							+ "<div class=\"card-body\">"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Status:</strong>"+rs.getString("status")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">UCI:</strong>"+rs.getString("uci")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Bracelet:</strong>"+rs.getString("bracelet")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Room Number:</strong>"+rs.getString("room_num")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Name:</strong>"+rs.getString("nom")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Surname:</strong>"+rs.getString("surname")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Date of Birth:</strong>"+rs.getString("dob")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Gender:</strong>"+rs.getString("gender")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Primary Language:</strong>"+rs.getString("primary_language")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Other Languages:</strong>"+rs.getString("other_language")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Citizenship:</strong>"+rs.getString("citizenship")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Phone Number:</strong>"+rs.getString("phone")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Email:</strong>"+rs.getString("email")+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Family Composition:</strong>"+rs.getString("family_composition")+"</p>"
							+ "<p class=\"mb-0\"><form method=\"post\" action=\"Agent\"><input type=\"hidden\" name = \"method\" value = \"open\"> <input type=\"hidden\" name=\"id\" value="+rs.getString("identifier")+"> <button>Open Profile</button></form></p>"
							+ "</div> </div>";
					}
					else if(number%3==0) {
						card2 = card2 + "<div class=\"fiche\"> <div class=\"card-header bg-transparent text-center\">"
								+ "<img class=\"profile_img\" src="+rs.getString("image_path")+" alt=\"profile pic\">"
								+ "<h3>"+rs.getString("nom")+"</h3>"
								+ "<h3>"+rs.getString("surname")+"</h3>"
								+ "</div>"
								+ "<div class=\"card-body\">"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Status:</strong>"+rs.getString("status")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">UCI:</strong>"+rs.getString("uci")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Bracelet:</strong>"+rs.getString("bracelet")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Room Number:</strong>"+rs.getString("room_num")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Name:</strong>"+rs.getString("nom")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Surname:</strong>"+rs.getString("surname")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Date of Birth:</strong>"+rs.getString("dob")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Gender:</strong>"+rs.getString("gender")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Primary Language:</strong>"+rs.getString("primary_language")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Other Languages:</strong>"+rs.getString("other_language")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Citizenship:</strong>"+rs.getString("citizenship")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Phone Number:</strong>"+rs.getString("phone")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Email:</strong>"+rs.getString("email")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Family Composition:</strong>"+rs.getString("family_composition")+"</p>"
								+ "<p class=\"mb-0\"><form method=\"post\" action=\"Agent\"><input type=\"hidden\" name = \"method\" value = \"open\"> <input type=\"hidden\" name=\"id\" value="+rs.getString("identifier")+"> <button>Open Profile</button></form></p>"
								+ "</div> </div>";
					}
					else {
						card3 = card3 + "<div class=\"fiche\"> <div class=\"card-header bg-transparent text-center\">"
								+ "<img class=\"profile_img\" src="+rs.getString("image_path")+" alt=\"profile pic\">"
								+ "<h3>"+rs.getString("nom")+"</h3>"
								+ "<h3>"+rs.getString("surname")+"</h3>"
								+ "</div>"
								+ "<div class=\"card-body\">"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Status:</strong>"+rs.getString("status")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">UCI:</strong>"+rs.getString("uci")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Bracelet:</strong>"+rs.getString("bracelet")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Room Number:</strong>"+rs.getString("room_num")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Name:</strong>"+rs.getString("nom")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Surname:</strong>"+rs.getString("surname")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Date of Birth:</strong>"+rs.getString("dob")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Gender:</strong>"+rs.getString("gender")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Primary Language:</strong>"+rs.getString("primary_language")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Other Languages:</strong>"+rs.getString("other_language")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Citizenship:</strong>"+rs.getString("citizenship")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Phone Number:</strong>"+rs.getString("phone")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Email:</strong>"+rs.getString("email")+"</p>"
								+ "<p class=\"mb-0\"><strong class=\"pr-1\">Family Composition:</strong>"+rs.getString("family_composition")+"</p>"
								+ "<p class=\"mb-0\"><form method=\"post\" action=\"Agent\"><input type=\"hidden\" name = \"method\" value = \"open\"> <input type=\"hidden\" name=\"id\" value="+rs.getString("identifier")+"> <button>Open Profile</button></form></p>"
								+ "</div> </div>";
					}
					number = number -1;
				}
			}
			request.setAttribute("card", card);
			request.setAttribute("card2", card2);
			request.setAttribute("card3", card3);
			 request.getRequestDispatcher("openFamily.jsp").forward(request, response);
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
		 if(method==null) {
			 method = "checkout";
		 }
		 if (method.equals("login")) {
				page=0;
				session = request.getSession();
				session.setAttribute("fname", firstName);
				request.setAttribute("html", html);
				generate(request, response);
			}
		 else if(method.equals("home")) {
				generate(request, response);
				if (!response.isCommitted()) {
		            request.getRequestDispatcher("clientPortal.jsp").forward(request, response);
		        }
			}
		 else if(method.equals("next")) {
				page = page + 1;
				generate(request, response);
			}
			else if(method.equals("previous")) {
				page = page - 1;
				generate(request, response);
			}
			else if(method.equals("search")) {
				page = 0;
				search(request, response);
			}
			else if(method.equals("nextSearch")) {
				page = page + 1;
				search(request, response);
			}
			else if(method.equals("previousSearch")) {
				page = page - 1;
				search(request, response);
			}
			else if(method.equals("open")) {
				open(request, response);
			}
			else if(method.equals("passport")) {
				storeVisit(request, response);
			}
			else if(method.equals("openFamily")) {
				openFamily(request, response);
			}
			}
	 }
}

