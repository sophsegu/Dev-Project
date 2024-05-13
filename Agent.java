package Tracker;

import java.io.IOException;

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

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport; 

import jakarta.servlet.*;  
import jakarta.servlet.http.*;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;

import com.aspose.pdf.*;
import com.aspose.pdf.operators.ConcatenateMatrix;
import com.aspose.pdf.operators.Do;
import com.aspose.pdf.operators.GRestore;
import com.aspose.pdf.operators.GSave;

import Tracker.sql;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;

import java.security.MessageDigest;

@WebServlet("/Agent")
@MultipartConfig
public class Agent extends HttpServlet{
	private List<String> Identifiers = new ArrayList<String>();
	private DataSource datasource = null;
	public String firstName;
	private String html = "";
	private final String url = "jdbc:postgresql://localhost:5432/Dev Tracker";
    private final String nom_utilisateur = "postgres";
    private final String code = "1234";
    private sql query = new sql();
    static int page = 0;
    private String pagination = "";
    private int resident_page = 0;
    private int assigned_page = 0;
    private int id;
  
    public Agent(String name) {
		firstName = name;
	}
    
	public Agent() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		 String role = (String) session.getAttribute("name");
		 String name = (String) session.getAttribute("name");
		 System.out.println("AGENT Role: "+role+" Name: "+name);
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
			 PreparedStatement stmt = conn.prepareStatement(query.generate(0));
			 String sqlQuery = query.generateNOOFFSET();
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
						String row = "<tr><td>" + status.toString() + "</td><td>" + uci.toString() + "</td><td>" + room_num.toString() + "</td><td>" + f_name.toString() + "</td><td>" + s_name.toString() + "</td><td>" + dob.toString() + "</td><td>" + gender.toString() + "</td><td>" + COO.toString() + "</td><td><form method=\"post\" action=\"Agent\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"open\">\r\n"
					            + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">\r\n"
					            + "<button class=\"buttonss\" type=\"submit\">Open Profile</button>"
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
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("agentPortal.jsp").forward(request, response);
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
			 String id = request.getParameter("id");
			 PreparedStatement stmt = conn.prepareStatement(query.open(id));
			 ResultSet rs = stmt.executeQuery();
			 String row = "";
			 String row_2 = "";
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
					 if(resident_page == 0) {
						 request.setAttribute("arrival_canada", rs.getString("arrival_canada"));
						 LocalDate arrival_canada;
						 if(rs.getString("arrival_canada") != null) {
							 arrival_canada = LocalDate.parse(rs.getString("arrival_canada"));
						 }
						 else {
							 arrival_canada = LocalDate.now();
						 }
						 long canada_days = ChronoUnit.DAYS.between(arrival_canada, curDate);
						 request.setAttribute("length_canada", canada_days);
						 request.setAttribute("arrival_dev", rs.getString("arrival_dev"));
						 LocalDate arrival_dev;
						 if(rs.getString("arrival_dev") != null) {
							 arrival_dev = LocalDate.parse(rs.getString("arrival_dev"));
						 }
						 else {
							 arrival_dev = LocalDate.now();
						 }
						 long dev_days = ChronoUnit.DAYS.between(arrival_dev, curDate);
						 request.setAttribute("lengt_dev", dev_days);
						 request.setAttribute("mode_of_arrival", rs.getString("mode_of_arrival"));
						 request.setAttribute("reunification", rs.getString("reunification"));
				 }
					 else if(resident_page == 1) {
						 request.setAttribute("biometrics", rs.getString("biometrics"));
						 request.setAttribute("aoc", rs.getString("aoc"));
						 request.setAttribute("ime", rs.getString("ime"));
						 request.setAttribute("eapp", rs.getString("eapp"));
						 request.setAttribute("rpcd", rs.getString("rpcd"));
						 request.setAttribute("boc", rs.getString("boc"));
						 request.setAttribute("prra", rs.getString("prra"));
						 request.setAttribute("work_permit", rs.getString("work_permit"));
						 request.setAttribute("wp_date", rs.getString("wp_date"));
						 LocalDate wp_days;
						 if(rs.getString("wp_date")==null) {
							 wp_days = curDate;
						 }else {
							 wp_days = LocalDate.parse(rs.getString("wp_date"));
						 }
						 long wp_num = ChronoUnit.DAYS.between(wp_days, curDate);
						 request.setAttribute("wp_length", wp_num);
						 request.setAttribute("irb_decision", rs.getString("irb_decision"));
						 request.setAttribute("length_irb", rs.getString("length_irb"));
						 request.setAttribute("healthcare", rs.getString("healthcare"));
					 }
					 else if(resident_page == 2) {
						 request.setAttribute("bank", rs.getString("bank"));
						 request.setAttribute("legal", rs.getString("legal"));
						 request.setAttribute("ontario_works", rs.getString("ontario_works"));
						 request.setAttribute("length_ow", rs.getString("length_ow"));
						 request.setAttribute("service_canada", rs.getString("service_canada"));
						 request.setAttribute("gen_assistance", rs.getString("gen_assistance"));
						 request.setAttribute("initial_settelment", rs.getString("initial_settelment"));
						 request.setAttribute("destination_preference", rs.getString("destination_preference"));
						 request.setAttribute("settelment_updates", rs.getString("settelment_updates"));
						 request.setAttribute("education", rs.getString("education"));
						 request.setAttribute("certifications", rs.getString("certifications"));
						 request.setAttribute("adult_learning", rs.getString("adult_learning"));
						 request.setAttribute("language_class", rs.getString("language_class"));
						 request.setAttribute("initial_proficiency", rs.getString("initial_proficiency"));
						 request.setAttribute("current_proficiency", rs.getString("current_proficiency"));
						 request.setAttribute("civil_ws", rs.getString("civil_ws"));
						 request.setAttribute("tenant_ws", rs.getString("tenant_ws"));
						 request.setAttribute("resume_ws", rs.getString("resume_ws"));
						 request.setAttribute("legal_ws", rs.getString("legal_ws"));
						 request.setAttribute("domestic_ws", rs.getString("domestic_ws"));
						 request.setAttribute("traffic_ws", rs.getString("traffic_ws"));
						 request.setAttribute("jobzone_resume", rs.getString("jobzone_resume"));
						 request.setAttribute("jobzone_search", rs.getString("jobzone_search"));
						 request.setAttribute("jobzone_interview", rs.getString("jobzone_interview"));
					 }
					 else if(resident_page == 3) {
						 request.setAttribute("employment", rs.getString("employment"));
						 request.setAttribute("resume", rs.getString("resume"));
						 request.setAttribute("part_full_time", rs.getString("part_full_time"));
						 request.setAttribute("employment_challenges", rs.getString("employment_challenges"));
						 request.setAttribute("employer", rs.getString("employer"));
						 request.setAttribute("start_employment", rs.getString("start_employment"));
						 LocalDate start_employment;
						 if(rs.getString("start_employment") != null) {
							 start_employment = LocalDate.parse(rs.getString("start_employment"));
						 }
						 else {
							 start_employment = LocalDate.now();
						 }
						 long length_employment = ChronoUnit.DAYS.between(start_employment, curDate);
						 request.setAttribute("length_employment", length_employment);
						 request.setAttribute("refferal_jobzone", rs.getString("refferal_jobzone"));
						 request.setAttribute("date_jobzone", rs.getString("date_jobzone"));
						 request.setAttribute("refferal_drake", rs.getString("refferal_drake"));
						 request.setAttribute("date_drake", rs.getString("date_drake"));
						 request.setAttribute("refferal_dev", rs.getString("refferal_dev"));
						 request.setAttribute("dev_date", rs.getString("dev_date"));
						 request.setAttribute("refferal_news", rs.getString("refferal_news"));
						 request.setAttribute("date_news", rs.getString("date_news"));
						 request.setAttribute("ow_notified", rs.getString("ow_notified"));
						 request.setAttribute("employment_assessment", rs.getString("employment_assessment"));
						 request.setAttribute("initial_mployment_assessment", rs.getString("initial_mployment_assessment"));
						 request.setAttribute("employment_updates", rs.getString("employment_updates"));
						 request.setAttribute("housing_assessment", rs.getString("housing_assessment"));
						 request.setAttribute("housing_updates", rs.getString("housing_updates"));
						 request.setAttribute("housing_application", rs.getString("housing_application"));
						 request.setAttribute("date_housing_support", rs.getString("date_housing_support"));
						 request.setAttribute("projected_move_out", rs.getString("projected_move_out"));
						 request.setAttribute("first_last_month", rs.getString("first_last_month"));
						 request.setAttribute("networth", rs.getString("networth"));
						 request.setAttribute("signed_lease", rs.getString("signed_lease"));
					 }
					 else if(resident_page == 4) {
						 request.setAttribute("occurances", rs.getString("occurances"));
						 request.setAttribute("ircc_refferal", rs.getString("ircc_refferal"));
						 request.setAttribute("date_ircc", rs.getString("date_ircc"));
						 request.setAttribute("medical_condition", rs.getString("medical_condition"));
						 request.setAttribute("mental_health", rs.getString("mental_health"));
						 request.setAttribute("clothing", rs.getString("clothing"));
						 request.setAttribute("childcare", rs.getString("childcare"));
						 request.setAttribute("cra", rs.getString("cra"));
						 request.setAttribute("volunteering", rs.getString("volunteering"));
						 request.setAttribute("furniture", rs.getString("furniture"));
						 request.setAttribute("speciality_food", rs.getString("speciality_food"));
						 request.setAttribute("food_bank", rs.getString("food_bank"));
						 request.setAttribute("vehicle", rs.getString("vehicle"));
						 request.setAttribute("insurance", rs.getString("insurance"));
						 request.setAttribute("parking_pass", rs.getString("parking_pass"));
						 request.setAttribute("registration", rs.getString("registration"));
						 request.setAttribute("vaccinations", rs.getString("vaccinations"));
						 request.setAttribute("tricounty_refferal", rs.getString("tricounty_refferal"));
						 request.setAttribute("school", rs.getString("school"));
						 request.setAttribute("service_ontario", rs.getString("service_ontario"));
						 request.setAttribute("victim_services", rs.getString("victim_services"));
						 request.setAttribute("house_faith", rs.getString("house_faith"));
						 request.setAttribute("lgbt", rs.getString("lgbt"));
						 request.setAttribute("family_id", rs.getString("family_id"));					 }
					 else if(resident_page == 5) {
						 String report_identifiers = rs.getString("report_ids");
						 if(report_identifiers!=null) {
						 report_identifiers = report_identifiers.substring(4);
						 String[] reports = report_identifiers.split("\\s*,\\s*");
						 String rapports = "<form method=\"post\" action=\"Agent\">";
						 for(int i=0; i<reports.length; i++) {
							 PreparedStatement reportTitles = conn.prepareStatement(query.openReport(reports[i]));
							 ResultSet resultSet = reportTitles.executeQuery();
							 if(resultSet.isBeforeFirst()) {
								 while(resultSet.next()) {
									 rapports = rapports + "<form method=\"post\" action=\"Agent\"> <tr><td>"+resultSet.getString("title")+"</td><td> \r\n"
											 + "<input type=\"hidden\" name=\"method\" value=\"openReport\">\r\n"
											 + "<input type=\"hidden\" name=\"id\" value=\""+reports[i]+"\">\r\n"
											 + "<button class=\"buttonss\" type=\"submit\">Open Report</button>"
											 + "</td></form>";
								 }
									 
							 }}
							 request.setAttribute("rapports", rapports);
						 }
						 else {
							 String reportss = "";
							 request.setAttribute("rapports", reportss);
						 }
					 }
			 }
			 }
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			  LocalTime localTime = LocalTime.now();
			 PreparedStatement stmt2 = conn.prepareStatement(query.updateAction(firstName, "Opened Chart", java.time.LocalDate.now(), dtf.format(localTime), room_num, nom, surname, number, "ARRAY ['']", "ARRAY ['']"));
			 stmt2.executeUpdate();
			 if(resident_page == 0) {
				 rs.close();
				 conn.close();
				 request.getRequestDispatcher("updateResidentPage1.jsp").forward(request, response);
			 }
			 else if(resident_page == 1) {
				 rs.close();
				 conn.close();
				 request.getRequestDispatcher("UpdateResidentPage2.jsp").forward(request, response);
			 }
			 else if(resident_page == 2){
				 rs.close();
				 conn.close();
				 request.getRequestDispatcher("updateResidentPage3.jsp").forward(request, response);
			 }
			 else if(resident_page == 3){
				 rs.close();
				 conn.close();
				 request.getRequestDispatcher("updateResidentPage4.jsp").forward(request, response);
			 }
			 else if(resident_page == 4){
				 rs.close();
				 conn.close();
				 request.getRequestDispatcher("updateResidentPage5.jsp").forward(request, response);
			 }
			 else if(resident_page == 6) {
				 rs.close();
				 conn.close();
				 request.getRequestDispatcher("Meeting.jsp").forward(request, response); 
			 }
			 else {
				 rs.close();
				 conn.close();
				 request.getRequestDispatcher("updateResidentPage6.jsp").forward(request, response);
			 }
			
		 }catch(ClassNotFoundException | SQLException | IOException | ServletException e) {
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
			 if(Field.equals("nom") | Field.equals("surname") | Field.equals("uci") || Field.equals("bracelet") || Field.equals("agent"))
					values = " iLIKE $$%"+values+"%$$";
			 else {
				 values = "="+values;
			 }
			 PreparedStatement stmt = conn.prepareStatement(query.search(Field, values, offset));
			 String sqlQuery = query.searchResidentNoOffset(Field, values);
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
						String row = "<tr><td>" + status.toString() + "</td><td>" + uci.toString() + "</td><td>" + room_num.toString() + "</td><td>" + f_name.toString() + "</td><td>" + s_name.toString() + "</td><td>" + dob.toString() + "</td><td>" + gender.toString() + "</td><td>" + COO.toString() + "</td><td><form method=\"post\" action=\"Agent\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"open\">\r\n"
					            + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">\r\n"
					            + "<button class=\"buttonss\" type=\"submit\">Open Profile</button>"
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
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("agentPortal.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(ClassNotFoundException | SQLException | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void createResident(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 PreparedStatement max = conn.prepareStatement("SELECT MAX(identifier) FROM resident");
			 ResultSet rs = max.executeQuery();
			 int maximum = 0;
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 maximum = rs.getInt(1);
				 }
			 }
			 maximum = maximum+1;
			 String room_num = request.getParameter("room_num");
			 if(room_num == null)
				 room_num="";
			 String arrival_canada = request.getParameter("arrival_canada");
			 if(arrival_canada == null || arrival_canada.equals(""))
				 arrival_canada="NULL";
			 else {
				 arrival_canada = "'"+arrival_canada+"'";
			 }
			 String arrival_dev = request.getParameter("arrival_dev");
			 if(arrival_dev == null || arrival_dev.equals(""))
				 arrival_dev="NULL";
			 else {
				 arrival_dev = "'"+arrival_dev+"'";
			 }
			 String mode_of_arrival = request.getParameter("mode_of_arrival");
			 if(mode_of_arrival == null)
				 mode_of_arrival="";
			 String reunification = request.getParameter("reunification");
			 if(reunification == null || reunification.equals(""))
				 reunification="NULL";
			 else {
				 reunification = "'"+reunification+"'";
			 }
			 String bracelet = request.getParameter("bracelet");
			 if(bracelet == null)
				 bracelet="";
			 String uci = request.getParameter("uci");
			 if(uci == null)
				 uci="";
			 String dob = request.getParameter("dob");
			 if(dob == null || dob.equals(""))
				 dob="NULL";
			 else {
				 dob = "'"+dob+"'";
			 }
			 String citizenship = request.getParameter("citizenship");
			 if(citizenship == null)
				 citizenship="";
			 String surname = request.getParameter("surname");
			 if(surname == null)
				 surname="";
			 String name = request.getParameter("name");
			 if(name == null)
				 name="";
			 String phone = request.getParameter("phone");
			 if(phone == null)
				 phone="";
			 String email = request.getParameter("email");
			 if(email == null)
				 email="";
			 String gender = request.getParameter("gender");
			 if(gender == null)
				 gender="";
			 String family_composition = request.getParameter("family_composition");
			 if(family_composition == null)
				 family_composition="";
			 String primary_language = request.getParameter("primary_language");
			 if(primary_language == null)
				 primary_language="";
			 String other_language = request.getParameter("other_language");
			 if(other_language == null)
				 other_language="";
			 PreparedStatement stmt = conn.prepareStatement(query.createResident(maximum, room_num, arrival_canada, arrival_dev, mode_of_arrival, reunification, bracelet, uci, dob, citizenship, surname, name, phone, email, gender, family_composition, primary_language, other_language));
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			  LocalTime localTime = LocalTime.now();
			  HttpSession session = request.getSession();
			 PreparedStatement stmt2 = conn.prepareStatement(query.updateAction(session.getAttribute("name"), "Created Resident", java.time.LocalDate.now(), dtf.format(localTime), room_num, name, surname, maximum, "ARRAY ['']", "ARRAY ['']"));
			 stmt2.executeUpdate();
			 stmt.executeUpdate();
			 conn.close();
			 request.setAttribute("identifier", maximum);
			 request.getRequestDispatcher("addResident2.jsp").forward(request, response);
		 }catch(ClassNotFoundException | SQLException | IOException | ServletException e) {
			 e.printStackTrace();
		 }
		        
	 }
	 
	 public void saveResident1(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String identifier = request.getParameter("id");
			 PreparedStatement openRes = conn.prepareStatement(query.open(identifier));
			 ResultSet resident = openRes.executeQuery();
			 String [] headers = {"bracelet", "room_num", "uci", "nom", "surname", "arrival_canada", "arrival_dev", "mode_of_arrival", "reunification", "dob", "primary_language", "other_language", "phone", "email", "gender", "citizenship", "family_composition"};
			 ArrayList<String> setters = new ArrayList<String>();
			 ArrayList<String> values = new ArrayList<String>();
			 ArrayList<String> value = new ArrayList<String>();
			 for(int i=0; i<headers.length; i++) {
				 String temp_value = request.getParameter(headers[i]);
				 if(temp_value == null) {
					 temp_value = "null";
				 }
				 if(temp_value.equals("null")==false && temp_value.equals("")==false) {
					 if(headers[i].equals("mode_of_arrival") || headers[i].equals("other_language") || headers[i].equals("service_canada") || headers[i].equals("initial_settelment") || headers[i].equals("adult_learning") || headers[i].equals("employment_assessment") || headers[i].equals("housing_assessment") || headers[i].equals("housing_updates") || headers[i].equals("housing_application") || headers[i].equals("occurances") || headers[i].equals("ircc_refferal") || headers[i].equals("mental_health") || headers[i].equals("furniture") || headers[i].equals("school") || headers[i].equals("service_ontario") ) {
						 String[] temp = request.getParameterValues(headers[i]);
						 if(temp==null) {
							 String[] val = {""};
							 temp = val;
						 }
						 if(temp.length>1) {
						 for(int j=0; j<temp.length; j++) {
							 if(j == temp.length-1) {
								 temp_value = temp_value + temp[j];
							 }
							 else {
								 temp_value = temp[j] +", ";
							 }
						 }
					 }
					 }
					 else {
						 temp_value = temp_value;
					 }
					 value.add(temp_value);
					 if(headers[i].equals("bracelet") || headers[i].equals("uci") || headers[i].equals("nom") || headers[i].equals("surname") || headers[i].equals("primary_language") || headers[i].equals("other_language") || headers[i].equals("citizenship") || headers[i].equals("family_composition") || headers[i].equals("mode_of_arrival") || headers[i].equals("gender") ||headers[i].equals("phone") || headers[i].equals("email")) {
						 temp_value = "$$"+temp_value+"$$";
					 }
					 if(headers[i].equals("arrival_canada") || headers[i].equals("arrival_dev") || headers[i].equals("reunification") || headers[i].equals("dob") || headers[i].equals("wp_date") || headers[i].equals("length_irb") || headers[i].equals("length_ow") || headers[i].equals("civil_ws") || headers[i].equals("tenant_ws") || headers[i].equals("resume_ws") || headers[i].equals("legal_ws") || headers[i].equals("domestic_ws") || headers[i].equals("traffic_ws") || headers[i].equals("start_employment") || headers[i].equals("date_jobzone") || headers[i].equals("date_drake") || headers[i].equals("dev_date") || headers[i].equals("date_news") || headers[i].equals("date_housing_support") || headers[i].equals("projected_move_out") || headers[i].equals("first_last_month") || headers[i].equals("date_ircc") || headers[i].equals("jobzone_resume") || headers[i].equals("jobzone_search") || headers[i].equals("jobzone_interview")) {
						 if(temp_value.equals("''") || temp_value.equals("'null'")){
							 temp_value = "NULL";
						 }
						 else {
							 temp_value = "'"+temp_value+"'";
						 }
					 }
					 setters.add(headers[i]);
					 values.add(temp_value);
				 }
			 }
			 String setting = "";
			 String modified = "Array [";
			 for(int j=0; j<setters.size()-1; j++) {
				 String temp = setters.get(j)+" = "+values.get(j)+", ";
				 setting = setting + temp;
			 }
			 setting = setting +""+ setters.get(setters.size()-1)+" = "+values.get(setters.size()-1)+" ";
			 int count = 0;
			 String nom = "";
			 String surname = "";
			 String room_num = "";
			 String original = "Array [";
			 if(resident.isBeforeFirst()) {
				 while(resident.next()) {
					 for(int k=0; k<setters.size()-1; k++) {
						 if(count == 0) {
							 original = original +"$$"+setters.get(k)+" = "+resident.getString(setters.get(k))+"$$";
							 modified = modified +"$$"+setters.get(k)+" = "+value.get(k)+"$$";
							 count = count +1;
						 }
						 else {
							 original = original +", $$"+setters.get(k)+" = "+resident.getString(setters.get(k))+"$$";
							 modified = modified +", $$"+setters.get(k)+" = "+value.get(k)+"$$";
							 count = count + 1;
						 }
					 }
					 nom = resident.getString("nom");
					 surname = resident.getString("surname");
					 room_num = resident.getString("room_num");
					 
				 }
			 }
			 modified = modified +"]";
			 original = original +"]";
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			 LocalTime localTime = LocalTime.now();
			 PreparedStatement stmt = conn.prepareStatement(query.updateResident(identifier, setting));
			 stmt.executeUpdate();
			 int id = Integer.parseInt(identifier);
			 HttpSession session = request.getSession();
			 PreparedStatement stmt3 = conn.prepareStatement(query.updateAction(session.getAttribute("name"), "Updated Resident", java.time.LocalDate.now(), dtf.format(localTime), room_num, nom, surname, id, original, modified));stmt3.executeUpdate();
			 stmt3.executeUpdate();
			 stmt = conn.prepareStatement(query.open(identifier));
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 request.setAttribute("id", request.getParameter("id"));
					 request.setAttribute("nom", rs.getString("nom"));
					 nom = rs.getString("nom");
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
					 request.setAttribute("arrival_canada", rs.getString("arrival_canada"));
					 LocalDate arrival_canada;
					 if(rs.getString("arrival_canada") != null) {
						 arrival_canada = LocalDate.parse(rs.getString("arrival_canada"));
					 }
					 else {
						 arrival_canada = LocalDate.now();
					 }
					 long canada_days = ChronoUnit.DAYS.between(arrival_canada, curDate);
					 request.setAttribute("length_canada", canada_days);
					 request.setAttribute("arrival_dev", rs.getString("arrival_dev"));
					 LocalDate arrival_dev;
					 if(rs.getString("arrival_dev") != null) {
						 arrival_dev = LocalDate.parse(rs.getString("arrival_dev"));
					 }
					 else {
						 arrival_dev = LocalDate.now();
					 }
					 long dev_days = ChronoUnit.DAYS.between(arrival_dev, curDate);
					 request.setAttribute("lengt_dev", dev_days);
					 request.setAttribute("mode_of_arrival", rs.getString("mode_of_arrival"));
					 request.setAttribute("reunification", rs.getString("reunification"));
				 }
			 }
			 rs.close();
			 conn.close();
			 request.getRequestDispatcher("updateResidentPage1.jsp").forward(request, response);
		 }catch(ClassNotFoundException | SQLException  | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void saveResident2(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String identifier = request.getParameter("id");
			 PreparedStatement openRes = conn.prepareStatement(query.open(identifier));
			 ResultSet resident = openRes.executeQuery();
			 String [] headers = {"biometrics", "ime", "aoc", "rpcd", "eapp", "boc", "prra", "work_permit", "wp_date", "irb_decision", "length_irb", "healthcare"};
			 ArrayList<String> setters = new ArrayList<String>();
			 ArrayList<String> values = new ArrayList<String>();
			 ArrayList<String> value = new ArrayList<String>();
			 for(int i=0; i<headers.length; i++) {
				 String temp_value = request.getParameter(headers[i]);
				 if(temp_value == null) {
					 temp_value = "null";
				 }
				 if(temp_value.equals("null")==false && temp_value.equals("")==false) {
					 if(headers[i].equals("mode_of_arrival") || headers[i].equals("other_language") || headers[i].equals("service_canada") || headers[i].equals("initial_settelment") || headers[i].equals("adult_learning") || headers[i].equals("employment_assessment") || headers[i].equals("housing_assessment") || headers[i].equals("housing_updates") || headers[i].equals("housing_application") || headers[i].equals("occurances") || headers[i].equals("ircc_refferal") || headers[i].equals("mental_health") || headers[i].equals("furniture") || headers[i].equals("school") || headers[i].equals("service_ontario") ) {
						 String[] temp = request.getParameterValues(headers[i]);
						 if(temp==null) {
							 String[] val = {""};
							 temp = val;
						 }
						 for(int j=0; j<temp.length; j++) {
							 if(j == temp.length-1) {
								 temp_value = temp_value + temp[j];
							 }
							 else {
								 temp_value = temp[j] +", ";
							 }
						 }
					 }
					 value.add(temp_value);
					 if(headers[i].equals("gen_assistance") == false && headers[i].equals("room_num") == false) {
						 temp_value = "'"+temp_value+"'";
					 }
					 if(headers[i].equals("arrival_canada") || headers[i].equals("arrival_dev") || headers[i].equals("reunification") || headers[i].equals("dob") || headers[i].equals("wp_date") || headers[i].equals("length_irb") || headers[i].equals("length_ow") || headers[i].equals("civil_ws") || headers[i].equals("tenant_ws") || headers[i].equals("resume_ws") || headers[i].equals("legal_ws") || headers[i].equals("domestic_ws") || headers[i].equals("traffic_ws") || headers[i].equals("start_employment") || headers[i].equals("date_jobzone") || headers[i].equals("date_drake") || headers[i].equals("dev_date") || headers[i].equals("date_news") || headers[i].equals("date_housing_support") || headers[i].equals("projected_move_out") || headers[i].equals("first_last_month") || headers[i].equals("date_ircc") || headers[i].equals("jobzone_resume") || headers[i].equals("jobzone_search") || headers[i].equals("jobzone_interview")) {
						 if(temp_value.equals("''") || temp_value.equals("'null'")){
							 temp_value = "NULL";
						 }
					 }
					 setters.add(headers[i]);
					 values.add(temp_value);
				 }
			 }
			 String setting = "";
			 String modified = "Array [";
			 for(int j=0; j<setters.size()-1; j++) {
				 String temp = setters.get(j)+" = "+values.get(j)+", ";
				 setting = setting + temp;
			 }
			 setting = setting +""+ setters.get(setters.size()-1)+" = "+values.get(setters.size()-1)+" ";
			 int count = 0;
			 String nom = "";
			 String surname = "";
			 String room_num = "";
			 String original = "Array [";
			 if(resident.isBeforeFirst()) {
				 while(resident.next()) {
					 for(int k=0; k<setters.size()-1; k++) {
						 if(count == 0) {
							 original = original +"'"+setters.get(k)+" = "+resident.getString(setters.get(k))+"'";
							 modified = modified +"'"+setters.get(k)+" = "+value.get(k)+"'";
							 count = count +1;
						 }
						 else {
							 original = original +", '"+setters.get(k)+" = "+resident.getString(setters.get(k))+"'";
							 modified = modified +", '"+setters.get(k)+" = "+value.get(k)+"'";
							 count = count + 1;
						 }
					 }
					 nom = resident.getString("nom");
					 surname = resident.getString("surname");
					 room_num = resident.getString("room_num");
					 
				 }
			 }
			 modified = modified +"]";
			 original = original +"]";
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			 LocalTime localTime = LocalTime.now();
			 PreparedStatement stmt = conn.prepareStatement(query.updateResident(identifier, setting));
			 stmt.executeUpdate();
			 int id = Integer.parseInt(identifier);
			 HttpSession session = request.getSession();
			 PreparedStatement stmt3 = conn.prepareStatement(query.updateAction(session.getAttribute("name"), "Updated Resident", java.time.LocalDate.now(), dtf.format(localTime), room_num, nom, surname, id, original, modified));stmt3.executeUpdate();
			 stmt3.executeUpdate();
			 stmt = conn.prepareStatement(query.open(identifier));
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 request.setAttribute("id", request.getParameter("id"));
					 request.setAttribute("nom", rs.getString("nom"));
					 nom = rs.getString("nom");
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
					 request.setAttribute("biometrics", rs.getString("biometrics"));
					 request.setAttribute("aoc", rs.getString("aoc"));
					 request.setAttribute("ime", rs.getString("ime"));
					 request.setAttribute("eapp", rs.getString("eapp"));
					 request.setAttribute("rpcd", rs.getString("rpcd"));
					 request.setAttribute("boc", rs.getString("boc"));
					 request.setAttribute("prra", rs.getString("prra"));
					 request.setAttribute("work_permit", rs.getString("work_permit"));
					 request.setAttribute("wp_date", rs.getString("wp_date"));
					 LocalDate wp_days;
					 if(rs.getString("wp_date") != null) {
						 wp_days = LocalDate.parse(rs.getString("wp_date"));
					 }
					 else {
						 wp_days = LocalDate.now();
					 }
					 long wp_num = ChronoUnit.DAYS.between(wp_days, curDate);
					 request.setAttribute("wp_length", wp_num);
					 request.setAttribute("irb_decision", rs.getString("irb_decision"));
					 request.setAttribute("length_irb", rs.getString("length_irb"));
					 request.setAttribute("healthcare", rs.getString("healthcare"));
				 }
			 }
			 rs.close();
			 conn.close();
			 request.getRequestDispatcher("UpdateResidentPage2.jsp").forward(request, response);
		 }catch(ClassNotFoundException | SQLException  | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void saveResident3(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String identifier = request.getParameter("id");
			 PreparedStatement openRes = conn.prepareStatement(query.open(identifier));
			 ResultSet resident = openRes.executeQuery();
			 String [] headers = {"bank", "legal", "ontario_works", "length_ow", "service_canada", "gen_assistance", "initial_settelment", "destination_preference", "settelment_updates", "education", "certifications", "adult_learning", "initial_proficiency", "current_proficiency", "language_class", "civil_ws", "tenant_ws", "resume_ws", "legal_ws", "domestic_ws", "traffic_ws", "jobzone_resume", "jobzone_search", "jobzone_interview"};
			 ArrayList<String> setters = new ArrayList<String>();
			 ArrayList<String> values = new ArrayList<String>();
			 ArrayList<String> value = new ArrayList<String>();
			 for(int i=0; i<headers.length; i++) {
				 String temp_value = request.getParameter(headers[i]);
				 if(temp_value == null) {
					 temp_value = "null";
				 }
				 if(temp_value.equals("null")==false && temp_value.equals("")==false) {
					 if(headers[i].equals("mode_of_arrival") || headers[i].equals("other_language") || headers[i].equals("service_canada") || headers[i].equals("initial_settelment") || headers[i].equals("adult_learning") || headers[i].equals("employment_assessment") || headers[i].equals("housing_assessment") || headers[i].equals("housing_updates") || headers[i].equals("housing_application") || headers[i].equals("occurances") || headers[i].equals("ircc_refferal") || headers[i].equals("mental_health") || headers[i].equals("furniture") || headers[i].equals("school") || headers[i].equals("service_ontario") ) {
						 String[] temp = request.getParameterValues(headers[i]);
						 if(temp==null) {
							 String[] val = {""};
							 temp = val;
						 }
						 for(int j=0; j<temp.length; j++) {
							 if(j == temp.length-1) {
								 temp_value = temp_value + temp[j];
							 }
							 else {
								 temp_value = temp[j] +", ";
							 }
						 }
					 }
					 value.add(temp_value);
					 if(headers[i].equals("gen_assistance") == false && headers[i].equals("room_num") == false && headers[i].equals("initial_settelment") == false && headers[i].equals("certifications") == false) {
						 temp_value = "'"+temp_value+"'";
					 }
					 if(headers[i].equals("initial_settelment") || headers[i].equals("certifications")) {
						 temp_value = "$$"+temp_value+"$$";
					 }
					 if(headers[i].equals("arrival_canada") || headers[i].equals("arrival_dev") || headers[i].equals("reunification") || headers[i].equals("dob") || headers[i].equals("wp_date") || headers[i].equals("length_irb") || headers[i].equals("length_ow") || headers[i].equals("civil_ws") || headers[i].equals("tenant_ws") || headers[i].equals("resume_ws") || headers[i].equals("legal_ws") || headers[i].equals("domestic_ws") || headers[i].equals("traffic_ws") || headers[i].equals("start_employment") || headers[i].equals("date_jobzone") || headers[i].equals("date_drake") || headers[i].equals("dev_date") || headers[i].equals("date_news") || headers[i].equals("date_housing_support") || headers[i].equals("projected_move_out") || headers[i].equals("first_last_month") || headers[i].equals("date_ircc") || headers[i].equals("jobzone_resume") || headers[i].equals("jobzone_search") || headers[i].equals("jobzone_interview")) {
						 if(temp_value.equals("''") || temp_value.equals("'null'")){
							 temp_value = "NULL";
						 }
					 }
					 setters.add(headers[i]);
					 values.add(temp_value);
				 }
			 }
			 String setting = "";
			 String modified = "Array [";
			 for(int j=0; j<setters.size()-1; j++) {
				 String temp = setters.get(j)+" = "+values.get(j)+", ";
				 setting = setting + temp;
			 }
			 setting = setting +""+ setters.get(setters.size()-1)+" = "+values.get(setters.size()-1)+" ";
			 int count = 0;
			 String nom = "";
			 String surname = "";
			 String room_num = "";
			 String original = "Array [";
			 if(resident.isBeforeFirst()) {
				 while(resident.next()) {
					 for(int k=0; k<setters.size()-1; k++) {
						 if(count == 0) {
							 original = original +"'"+setters.get(k)+" = "+resident.getString(setters.get(k))+"'";
							 modified = modified +"'"+setters.get(k)+" = "+value.get(k)+"'";
							 count = count +1;
						 }
						 else {
							 original = original +", '"+setters.get(k)+" = "+resident.getString(setters.get(k))+"'";
							 modified = modified +", '"+setters.get(k)+" = "+value.get(k)+"'";
							 count = count + 1;
						 }
					 }
					 nom = resident.getString("nom");
					 surname = resident.getString("surname");
					 room_num = resident.getString("room_num");
					 
				 }
			 }
			 modified = modified +"]";
			 original = original +"]";
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			 LocalTime localTime = LocalTime.now();
			 PreparedStatement stmt = conn.prepareStatement(query.updateResident(identifier, setting));
			 stmt.executeUpdate();
			 int id = Integer.parseInt(identifier);
			 HttpSession session = request.getSession();
			 PreparedStatement stmt3 = conn.prepareStatement(query.updateAction(session.getAttribute("name"), "Updated Resident", java.time.LocalDate.now(), dtf.format(localTime), room_num, nom, surname, id, original, modified));stmt3.executeUpdate();
			 stmt3.executeUpdate();
			 stmt = conn.prepareStatement(query.open(identifier));
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 request.setAttribute("id", request.getParameter("id"));
					 request.setAttribute("nom", rs.getString("nom"));
					 nom = rs.getString("nom");
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
					 request.setAttribute("bank", rs.getString("bank"));
					 request.setAttribute("legal", rs.getString("legal"));
					 request.setAttribute("ontario_works", rs.getString("ontario_works"));
					 request.setAttribute("length_ow", rs.getString("length_ow"));
					 request.setAttribute("service_canada", rs.getString("service_canada"));
					 request.setAttribute("gen_assistance", rs.getString("gen_assistance"));
					 request.setAttribute("initial_settelment", rs.getString("initial_settelment"));
					 request.setAttribute("destination_preference", rs.getString("destination_preference"));
					 request.setAttribute("settelment_updates", rs.getString("settelment_updates"));
					 request.setAttribute("education", rs.getString("education"));
					 request.setAttribute("certifications", rs.getString("certifications"));
					 request.setAttribute("adult_learning", rs.getString("adult_learning"));
					 request.setAttribute("language_class", rs.getString("language_class"));
					 request.setAttribute("initial_proficiency", rs.getString("initial_proficiency"));
					 request.setAttribute("current_proficiency", rs.getString("current_proficiency"));
					 request.setAttribute("civil_ws", rs.getString("civil_ws"));
					 request.setAttribute("tenant_ws", rs.getString("tenant_ws"));
					 request.setAttribute("resume_ws", rs.getString("resume_ws"));
					 request.setAttribute("legal_ws", rs.getString("legal_ws"));
					 request.setAttribute("domestic_ws", rs.getString("domestic_ws"));
					 request.setAttribute("traffic_ws", rs.getString("traffic_ws"));
					 request.setAttribute("jobzone_resume", rs.getString("jobzone_resume"));
					 request.setAttribute("jobzone_search", rs.getString("jobzone_search"));
					 request.setAttribute("jobzone_interview", rs.getString("jobzone_interview"));
				 }
			 }
			 rs.close();
			 conn.close();
			 request.getRequestDispatcher("updateResidentPage3.jsp").forward(request, response);
		 }catch(ClassNotFoundException | SQLException  | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void saveResident4(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String identifier = request.getParameter("id");
			 PreparedStatement openRes = conn.prepareStatement(query.open(identifier));
			 ResultSet resident = openRes.executeQuery();
			 String [] headers = {"employment", "part_full_time", "resume", "employment_challenges", "employer", "start_employment", "ow_notified", "refferal_jobzone", "date_jobzone", "refferal_drake", "date_drake", "refferal_dev", "dev_date", "refferal_news", "date_news", "employment_assessment", "initial_mployment_assessment", "employment_updates", "housing_assessment", "housing_updates", "housing_application", "date_housing_support", "projected_move_out", "first_last_month"};
			 ArrayList<String> setters = new ArrayList<String>();
			 ArrayList<String> values = new ArrayList<String>();
			 ArrayList<String> value = new ArrayList<String>();
			 for(int i=0; i<headers.length; i++) {
				 String temp_value = request.getParameter(headers[i]);
				 if(temp_value == null) {
					 temp_value = "null";
				 }
				 if(temp_value.equals("null")==false && temp_value.equals("")==false) {
					 if(headers[i].equals("mode_of_arrival") || headers[i].equals("other_language") || headers[i].equals("service_canada") || headers[i].equals("initial_settelment") || headers[i].equals("adult_learning") || headers[i].equals("employment_assessment") || headers[i].equals("housing_assessment") || headers[i].equals("housing_updates") || headers[i].equals("housing_application") || headers[i].equals("occurances") || headers[i].equals("ircc_refferal") || headers[i].equals("mental_health") || headers[i].equals("furniture") || headers[i].equals("school") || headers[i].equals("service_ontario") || headers[i].equals("employment_challenges")) {
						 String[] temp = request.getParameterValues(headers[i]);
						 if(temp==null) {
							 String[] val = {""};
							 temp = val;
						 }
						 for(int j=0; j<temp.length; j++) {
							 if(j == temp.length-1) {
								 temp_value = temp_value + temp[j];
							 }
							 else {
								 temp_value = temp[j] +", ";
							 }
						 }
					 }
					 value.add(temp_value);
					 if(headers[i].equals("gen_assistance") == false && headers[i].equals("room_num") == false && headers[i].equals("employer") == false && headers[i].equals("initial_mployment_assessment") == false && headers[i].equals("employment_updates") == false) {
						 temp_value = "'"+temp_value+"'";
					 }
					 if(headers[i].equals("employer") || headers[i].equals("initial_mployment_assessment") || headers[i].equals("employment_updates")) {
						 temp_value = "$$"+temp_value+"$$";
					 }
					 if(headers[i].equals("arrival_canada") || headers[i].equals("arrival_dev") || headers[i].equals("reunification") || headers[i].equals("dob") || headers[i].equals("wp_date") || headers[i].equals("length_irb") || headers[i].equals("length_ow") || headers[i].equals("civil_ws") || headers[i].equals("tenant_ws") || headers[i].equals("resume_ws") || headers[i].equals("legal_ws") || headers[i].equals("domestic_ws") || headers[i].equals("traffic_ws") || headers[i].equals("start_employment") || headers[i].equals("date_jobzone") || headers[i].equals("date_drake") || headers[i].equals("dev_date") || headers[i].equals("date_news") || headers[i].equals("date_housing_support") || headers[i].equals("projected_move_out") || headers[i].equals("first_last_month") || headers[i].equals("date_ircc") || headers[i].equals("jobzone_resume") || headers[i].equals("jobzone_search") || headers[i].equals("jobzone_interview")) {
						 if(temp_value.equals("''") || temp_value.equals("'null'")){
							 temp_value = "NULL";
						 }
					 }
					 setters.add(headers[i]);
					 values.add(temp_value);
				 }
			 }
			 String setting = "";
			 String modified = "Array [";
			 for(int j=0; j<setters.size()-1; j++) {
				 String temp = setters.get(j)+" = "+values.get(j)+", ";
				 setting = setting + temp;
			 }
			 setting = setting +""+ setters.get(setters.size()-1)+" = "+values.get(setters.size()-1)+" ";
			 int count = 0;
			 String nom = "";
			 String surname = "";
			 String room_num = "";
			 String original = "Array [";
			 if(resident.isBeforeFirst()) {
				 while(resident.next()) {
					 for(int k=0; k<setters.size()-1; k++) {
						 if(count == 0) {
							 original = original +"'"+setters.get(k)+" = "+resident.getString(setters.get(k))+"'";
							 modified = modified +"'"+setters.get(k)+" = "+value.get(k)+"'";
							 count = count +1;
						 }
						 else {
							 original = original +", '"+setters.get(k)+" = "+resident.getString(setters.get(k))+"'";
							 modified = modified +", '"+setters.get(k)+" = "+value.get(k)+"'";
							 count = count + 1;
						 }
					 }
					 nom = resident.getString("nom");
					 surname = resident.getString("surname");
					 room_num = resident.getString("room_num");
					 
				 }
			 }
			 modified = modified +"]";
			 original = original +"]";
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			 LocalTime localTime = LocalTime.now();
			 PreparedStatement stmt = conn.prepareStatement(query.updateResident(identifier, setting));
			 stmt.executeUpdate();
			 int id = Integer.parseInt(identifier);
			 HttpSession session = request.getSession();
			 PreparedStatement stmt3 = conn.prepareStatement(query.updateAction(session.getAttribute("name"), "Updated Resident", java.time.LocalDate.now(), dtf.format(localTime), room_num, nom, surname, id, original, modified));stmt3.executeUpdate();
			 stmt = conn.prepareStatement(query.open(identifier));
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 request.setAttribute("id", request.getParameter("id"));
					 request.setAttribute("nom", rs.getString("nom"));
					 nom = rs.getString("nom");
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
					 request.setAttribute("employment", rs.getString("employment"));
					 request.setAttribute("resume", rs.getString("resume"));
					 request.setAttribute("part_full_time", rs.getString("part_full_time"));
					 request.setAttribute("employment_challenges", rs.getString("employment_challenges"));
					 request.setAttribute("employer", rs.getString("employer"));
					 request.setAttribute("start_employment", rs.getString("start_employment"));
					 LocalDate start_employment;
					 if(rs.getString("start_employment") != null) {
						 start_employment = LocalDate.parse(rs.getString("start_employment"));
					 }
					 else {
						 start_employment = LocalDate.now();
					 }
					 long length_employment = ChronoUnit.DAYS.between(start_employment, curDate);
					 request.setAttribute("length_employment", length_employment);
					 request.setAttribute("refferal_jobzone", rs.getString("refferal_jobzone"));
					 request.setAttribute("date_jobzone", rs.getString("date_jobzone"));
					 request.setAttribute("refferal_drake", rs.getString("refferal_drake"));
					 request.setAttribute("date_drake", rs.getString("date_drake"));
					 request.setAttribute("refferal_dev", rs.getString("refferal_dev"));
					 request.setAttribute("dev_date", rs.getString("dev_date"));
					 request.setAttribute("refferal_news", rs.getString("refferal_news"));
					 request.setAttribute("date_news", rs.getString("date_news"));
					 request.setAttribute("ow_notified", rs.getString("ow_notified"));
					 request.setAttribute("employment_assessment", rs.getString("employment_assessment"));
					 request.setAttribute("initial_mployment_assessment", rs.getString("initial_mployment_assessment"));
					 request.setAttribute("employment_updates", rs.getString("employment_updates"));
					 request.setAttribute("housing_assessment", rs.getString("housing_assessment"));
					 request.setAttribute("housing_updates", rs.getString("housing_updates"));
					 request.setAttribute("housing_application", rs.getString("housing_application"));
					 request.setAttribute("date_housing_support", rs.getString("date_housing_support"));
					 request.setAttribute("projected_move_out", rs.getString("projected_move_out"));
					 request.setAttribute("first_last_month", rs.getString("first_last_month"));
					 request.setAttribute("networth", rs.getString("networth"));
					 request.setAttribute("signed_lease", rs.getString("signed_lease"));
				 }
			 }
			 rs.close();
			 conn.close();
			 request.getRequestDispatcher("updateResidentPage4.jsp").forward(request, response);
		 }catch(ClassNotFoundException | SQLException  | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void saveResident5(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String identifier = request.getParameter("id");
			 PreparedStatement openRes = conn.prepareStatement(query.open(identifier));
			 ResultSet resident = openRes.executeQuery();
			 String [] headers = {"occurances", "ircc_refferal", "date_ircc", "medical_condition", "mental_health", "clothing", "childcare", "cra", "volunteering", "furniture", "speciality_food", "food_bank", "vehicle", "insurance", "parking_pass", "registration", "vaccinations", "tricounty_refferal", "school", "service_ontario", "victim_services", "house_faith", "lgbt"};
			 ArrayList<String> setters = new ArrayList<String>();
			 ArrayList<String> values = new ArrayList<String>();
			 ArrayList<String> value = new ArrayList<String>();
			 for(int i=0; i<headers.length; i++) {
				 String temp_value = request.getParameter(headers[i]);
				 if(temp_value == null) {
					 temp_value = "null";
				 }
				 if(temp_value.equals("null")==false && temp_value.equals("")==false) {
					 if(headers[i].equals("mode_of_arrival") || headers[i].equals("other_language") || headers[i].equals("service_canada") || headers[i].equals("initial_settelment") || headers[i].equals("adult_learning") || headers[i].equals("employment_assessment") || headers[i].equals("housing_assessment") || headers[i].equals("housing_updates") || headers[i].equals("housing_application") || headers[i].equals("occurances") || headers[i].equals("ircc_refferal") || headers[i].equals("mental_health") || headers[i].equals("furniture") || headers[i].equals("school") || headers[i].equals("service_ontario") ) {
						 String[] temp = request.getParameterValues(headers[i]);
						 if(temp==null) {
							 String[] val = {""};
							 temp = val;
						 }
						 for(int j=0; j<temp.length; j++) {
							 if(j == temp.length-1) {
								 temp_value = temp_value + temp[j];
							 }
							 else {
								 temp_value = temp[j] +", ";
							 }
						 }
					 }
					 value.add(temp_value);
					 if(headers[i].equals("gen_assistance") == false && headers[i].equals("room_num") == false && headers[i].equals("medical_condition") == false && headers[i].equals("vehicle") == false) {
						 temp_value = "'"+temp_value+"'";
					 }
					 if(headers[i].equals("medical_condition") || headers[i].equals("vehicle")) {
						 temp_value = "$$"+temp_value+"$$";
					 }
					 if(headers[i].equals("arrival_canada") || headers[i].equals("arrival_dev") || headers[i].equals("reunification") || headers[i].equals("dob") || headers[i].equals("wp_date") || headers[i].equals("length_irb") || headers[i].equals("length_ow") || headers[i].equals("civil_ws") || headers[i].equals("tenant_ws") || headers[i].equals("resume_ws") || headers[i].equals("legal_ws") || headers[i].equals("domestic_ws") || headers[i].equals("traffic_ws") || headers[i].equals("start_employment") || headers[i].equals("date_jobzone") || headers[i].equals("date_drake") || headers[i].equals("dev_date") || headers[i].equals("date_news") || headers[i].equals("date_housing_support") || headers[i].equals("projected_move_out") || headers[i].equals("first_last_month") || headers[i].equals("date_ircc") || headers[i].equals("jobzone_resume") || headers[i].equals("jobzone_search") || headers[i].equals("jobzone_interview")) {
						 if(temp_value.equals("''") || temp_value.equals("'null'")){
							 temp_value = "NULL";
						 }
					 }
					 setters.add(headers[i]);
					 values.add(temp_value);
				 }
			 }
			 String setting = "";
			 String modified = "Array [";
			 for(int j=0; j<setters.size()-1; j++) {
				 String temp = setters.get(j)+" = "+values.get(j)+", ";
				 setting = setting + temp;
			 }
			 setting = setting +""+ setters.get(setters.size()-1)+" = "+values.get(setters.size()-1)+" ";
			 int count = 0;
			 String nom = "";
			 String surname = "";
			 String room_num = "";
			 String original = "Array [";
			 if(resident.isBeforeFirst()) {
				 while(resident.next()) {
					 for(int k=0; k<setters.size()-1; k++) {
						 if(count == 0) {
							 original = original +"'"+setters.get(k)+" = "+resident.getString(setters.get(k))+"'";
							 modified = modified +"'"+setters.get(k)+" = "+value.get(k)+"'";
							 count = count +1;
						 }
						 else {
							 original = original +", '"+setters.get(k)+" = "+resident.getString(setters.get(k))+"'";
							 modified = modified +", '"+setters.get(k)+" = "+value.get(k)+"'";
							 count = count + 1;
						 }
					 }
					 nom = resident.getString("nom");
					 surname = resident.getString("surname");
					 room_num = resident.getString("room_num");
					 
				 }
			 }
			 modified = modified +"]";
			 original = original +"]";
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			 LocalTime localTime = LocalTime.now();
			 PreparedStatement stmt = conn.prepareStatement(query.updateResident(identifier, setting));
			 stmt.executeUpdate();
			 int id = Integer.parseInt(identifier);
			 HttpSession session = request.getSession();
			 PreparedStatement stmt3 = conn.prepareStatement(query.updateAction(session.getAttribute("name"), "Updated Resident", java.time.LocalDate.now(), dtf.format(localTime), room_num, nom, surname, id, original, modified));
			 stmt3.executeUpdate();
			 stmt = conn.prepareStatement(query.open(identifier));
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 request.setAttribute("id", request.getParameter("id"));
					 request.setAttribute("nom", rs.getString("nom"));
					 nom = rs.getString("nom");
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
					 request.setAttribute("occurances", rs.getString("occurances"));
					 request.setAttribute("ircc_refferal", rs.getString("ircc_refferal"));
					 request.setAttribute("date_ircc", rs.getString("date_ircc"));
					 request.setAttribute("medical_condition", rs.getString("medical_condition"));
					 request.setAttribute("mental_health", rs.getString("mental_health"));
					 request.setAttribute("clothing", rs.getString("clothing"));
					 request.setAttribute("childcare", rs.getString("childcare"));
					 request.setAttribute("cra", rs.getString("cra"));
					 request.setAttribute("volunteering", rs.getString("volunteering"));
					 request.setAttribute("furniture", rs.getString("furniture"));
					 request.setAttribute("speciality_food", rs.getString("speciality_food"));
					 request.setAttribute("food_bank", rs.getString("food_bank"));
					 request.setAttribute("vehicle", rs.getString("vehicle"));
					 request.setAttribute("insurance", rs.getString("insurance"));
					 request.setAttribute("parking_pass", rs.getString("parking_pass"));
					 request.setAttribute("registration", rs.getString("registration"));
					 request.setAttribute("vaccinations", rs.getString("vaccinations"));
					 request.setAttribute("tricounty_refferal", rs.getString("tricounty_refferal"));
					 request.setAttribute("school", rs.getString("school"));
					 request.setAttribute("service_ontario", rs.getString("service_ontario"));
					 request.setAttribute("victim_services", rs.getString("victim_services"));
					 request.setAttribute("house_faith", rs.getString("house_faith"));
					 request.setAttribute("lgbt", rs.getString("lgbt"));
				 }
			 }
			 rs.close();
			 conn.close();
			 request.getRequestDispatcher("updateResidentPage5.jsp").forward(request, response);
		 }catch(ClassNotFoundException | SQLException  | IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void addResident(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String max = request.getParameter("id");
			 int id = Integer.parseInt(max);
			 String employed = request.getParameter("employed");
			 if(employed == null)
				 employed="";
			 String employer = request.getParameter("employer");
			 if(employer == null)
				 employer="";
			 String start_employment = request.getParameter("start_employment");
			 if(start_employment == null || start_employment.equals(""))
				 start_employment="NULL";
			 else {
				 start_employment="'"+start_employment+"'";
			 }
			 String vehicle = request.getParameter("vehicle");
			 if(vehicle == null)
				 vehicle="";
			 String insurance = request.getParameter("insurance");
			 if(insurance == null)
				 insurance="";
			 String registration = request.getParameter("registration");
			 if(registration == null)
				 registration="";
			 String education = request.getParameter("education");
			 if(education == null)
				 education="";
			 String certifications = request.getParameter("certifications");
			 if(certifications == null)
				 certifications="";
			 String work_experience = request.getParameter("work_experience");
			 if(work_experience == null)
				 work_experience="";
			 String initial_assessment = request.getParameter("initial_assessment");
			 if(initial_assessment == null)
				 initial_assessment="";
			 String destination = request.getParameter("destination");
			 if(destination == null)
				 destination="";
			 String medical = request.getParameter("medical");
			 if(medical == null)
				 medical="";
			 String ontario_works = request.getParameter("ontario_works");
			 if(ontario_works == null)
				 ontario_works="";
			 String date_ow = request.getParameter("date_ow");
			 if(date_ow == null || date_ow.equals(""))
				 date_ow="NULL";
			 else {
				 date_ow = "'"+date_ow+"'";
			 }
			 String ime = request.getParameter("ime");
			 if(ime == null)
				 ime="";
			 String rpcd = request.getParameter("rpcd");
			 if(rpcd == null)
				 rpcd="";
			 String eapp = request.getParameter("eapp");
			 if(eapp == null)
				 eapp="";
			 String boc = request.getParameter("boc");
			 if(boc == null)
				 boc="";
			 String healthcare = request.getParameter("healthcare");
			 if(healthcare == null)
				 healthcare="";
			 String work_permit = request.getParameter("work_permit");
			 if(work_permit == null)
				 work_permit="";
			 String date_wp = request.getParameter("date_wp");
			 if(date_wp == null || date_wp.equals(""))
				 date_wp="NULL";
			 else {
				 date_wp = "'"+date_wp+"'";
			 }
			 String legal = request.getParameter("legal");
			 if(legal == null)
				 legal="";
			 String bank = request.getParameter("bank");
			 if(bank == null)
				 bank="";
			 String irb_decision = request.getParameter("irb_decision");
			 if(irb_decision == null)
				 irb_decision="";
			 String date_irb = request.getParameter("date_irb");
			 if(date_irb == null || date_irb.equals(""))
				 date_irb="NULL";
			 else {
				 date_irb = "'"+date_irb+"'";
			 }
			 PreparedStatement stmt = conn.prepareStatement(query.addResident(id, employed, employer, start_employment, vehicle, insurance, registration, education, certifications, work_experience, initial_assessment, destination, medical, ontario_works, date_ow, ime, rpcd, eapp, boc, healthcare, work_permit, date_wp, legal, bank, irb_decision, date_irb));
			 stmt.executeUpdate();
			 conn.close();
			 generate(request, response);
		 }catch(ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void submitReport(HttpServletRequest request, HttpServletResponse response, String room_num) {
		 Connection conn;
		 try {
			 int lowerLeftX = 50;
		     int lowerLeftY = 750;
		      int upperRightX = 150;
		     int upperRightY = 800;
			 String agent_name = request.getParameter("agent_name");
			 String other_agents = request.getParameter("other_agents");
			 if(other_agents.equals("null")==false || other_agents.equals("")||other_agents == null) {
				 agent_name = agent_name +", "+other_agents;
			 }
			 String date_of_incident = request.getParameter("date_of_incident");
			 String rooms = request.getParameter("room_num");
			 String time_of_incident = request.getParameter("time_of_incident");
			 String individuals = request.getParameter("individuals");
			 String report_type = request.getParameter("report_type");
			 String report = request.getParameter("report");
			 
			 Document document = new Document();
			 
			 Page page = document.getPages().add();
			 java.io.FileInputStream imageStream = new java.io.FileInputStream(new java.io.File("Reports/Settings/globalys_logo.png"));
			 page.getResources().getImages().add(imageStream);
			 page.getContents().add(new GSave());
			 Rectangle rectangle = new Rectangle(lowerLeftX, lowerLeftY, upperRightX, upperRightY);
		        Matrix matrix = new Matrix(new double[] { rectangle.getURX() - rectangle.getLLX(), 0, 0,
		                rectangle.getURY() - rectangle.getLLY(), rectangle.getLLX(), rectangle.getLLY() });
		     page.getContents().add(new ConcatenateMatrix(matrix));
		     XImage ximage = page.getResources().getImages().get_Item(page.getResources().getImages().size());
			 page.getContents().add(new Do(ximage.getName()));
			 page.getContents().add(new GRestore());
			 page.getParagraphs().add(new TextFragment(" "));
			 page.getParagraphs().add(new TextFragment(" "));
			 
			 Table table = new Table();
			 table.setColumnWidths("100 100");
			 table.setColumnAdjustment(ColumnAdjustment.AutoFitToWindow);
			 
			 table.setBorder(new BorderInfo(BorderSide.All, .5f, Color.getBlack()));
			 table.setDefaultCellBorder(new BorderInfo(BorderSide.All, .5f, Color.getBlack()));
			 
			 Row row = table.getRows().add();
			 row.setFixedRowHeight(20);
			 row.getCells().add(" Agent: "+agent_name);
			 row.getCells().add(" Report Type: "+report_type);
			 
			 Row row2 = table.getRows().add();
			 row2.setFixedRowHeight(20);
			 row2.getCells().add(" Date: "+date_of_incident);
			 row2.getCells().add(" Time: "+time_of_incident);
			 
			 Row row3 = table.getRows().add();
			 row3.setFixedRowHeight(20);
			 row3.getCells().add(" Room: "+rooms);
			 row3.getCells().add(" Individuals: "+individuals);
			 
			 page.getParagraphs().add(table);
			 page.getParagraphs().add(new TextFragment(" "));
			 
			 Table table2 = new Table();
			 table2.setColumnWidths("100 100");
			 table2.setColumnAdjustment(ColumnAdjustment.AutoFitToWindow);
			 
			 table2.setBorder(new BorderInfo(BorderSide.All, .5f, Color.getBlack()));
			 table2.setDefaultCellBorder(new BorderInfo(BorderSide.All, .5f, Color.getBlack()));
			 
			 Row row4 = table2.getRows().add();
			 row4.setFixedRowHeight(20);
			 row4.getCells().add("Report Information");
			 row4.getDefaultCellTextState().setHorizontalAlignment(HorizontalAlignment.Center);
			 
			 Row row5 = table2.getRows().add();
			 row5.getCells().add(report);
			 page.getParagraphs().add(table2);
			 document.save("Reports/"+room_num+"/"+report_type+"-"+""+individuals+"-"+date_of_incident+"(Agent"+agent_name+").pdf");
			 imageStream.close();
			 document.close();
			 
			 String to = "malemoine@globalysservices.com";
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
		        message.setSubject("A New Report Has Been Written : "+dtf.format(now));
		        message.setText("A new report was written concerning "+room_num+" by "+agent_name+". The new report is titled : "+report_type+"-"+"individuals"+date_of_incident+"(Agent"+agent_name+").pdf");
		        
		        Transport.send(message);
		      generate(request, response);
		 }catch(Exception e) {
			 e.printStackTrace();
			 
		 }
	 }
	 
	 public void depart(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, nom_utilisateur, code);
				String identifier = request.getParameter("id");
				int id = Integer.parseInt(identifier);
				PreparedStatement stmt = conn.prepareStatement(query.deleteResident(identifier));
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
				  LocalTime localTime = LocalTime.now();
				  PreparedStatement openRes = conn.prepareStatement(query.open(identifier));
				  ResultSet rs = openRes.executeQuery();
				  String nom = "";
				  String room_num = "";
				  String surname = "";
				  String date_arrival = "";
				  String original = "ARRAY ['']";
				  String modified = "ARRAY ['']";
				  if(rs.isBeforeFirst()) {
					  while(rs.next()) {
						  nom = rs.getString("nom");
						  room_num = rs.getString("room_num");
						  surname = rs.getString("surname");
						  date_arrival = rs.getString("arrival_dev");
					  }
				  }
				PreparedStatement stmt2 = conn.prepareStatement(query.updateAction(firstName, "Departed Resident", java.time.LocalDate.now(), dtf.format(localTime), room_num, nom, surname, id, original, modified));
				PreparedStatement stmt3 = conn.prepareStatement(query.departFromStore("identifier"));
				stmt3.executeUpdate();
				stmt.executeUpdate();
				stmt2.executeUpdate();
				generate(request, response);
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	 }
	 
	 public void setAttributes(HttpServletRequest request, HttpServletResponse response) {
		 try {
			 request.setAttribute("id", request.getParameter("id"));
			 request.setAttribute("status", request.getParameter("status"));
			 request.setAttribute("uci", request.getParameter("uci"));
			 request.setAttribute("bracelet", request.getParameter("bracelet"));
			 request.setAttribute("room_num", request.getParameter("room_num"));
			 request.setAttribute("nom", request.getParameter("nom"));
			 request.setAttribute("surname", request.getParameter("surname"));
			 request.setAttribute("dob", request.getParameter("dob"));
			 request.setAttribute("age", request.getParameter("age"));
			 request.setAttribute("gender", request.getParameter("gender"));
			 request.setAttribute("primary_language", request.getParameter("primary_language"));
			 request.setAttribute("other_language", request.getParameter("other_language"));
			 request.setAttribute("citizenship", request.getParameter("citizenship"));
			 request.setAttribute("phone", request.getParameter("phone"));
			 request.setAttribute("email", request.getParameter("email"));
			 request.setAttribute("family_composition", request.getParameter("family_composition"));
			 request.getRequestDispatcher("departResident.jsp").forward(request, response);
		 }catch(IOException | ServletException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void checkResident(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String room_num = request.getParameter("room_num");
			 String[] room_numbers = room_num.split("\\s*,\\s*");
			 String nom = request.getParameter("individuals");
			 String[] names = nom.split("\\s*,\\s*");
			 ArrayList<String> vals = new ArrayList<String>();
			 for(int j=0; j<names.length; j++) {
				 String[] temp = names[j].split("\\s+");
				 for(int i=0; i<temp.length; i++) {
					 vals.add(temp[i]);
				 }
			 }
			 String agent_name = request.getParameter("agent_name");
			 String other_agents = request.getParameter("other_agents");
			 if(other_agents.equals("null")==false || other_agents.equals("")||other_agents == null) {
				 agent_name = agent_name +", "+other_agents;
			 }
			 String identifier = "ARRAY [";
			 String found ="false";
			 ArrayList<String> rooms = new ArrayList<String>();
			 ArrayList<String> identifiers = new ArrayList<String>();
			 for(int i=0; i<room_numbers.length; i++) {
				 rooms.add(room_numbers[i]);
				 for(int j=0; j<vals.size(); j++) {
					 PreparedStatement stmt = conn.prepareStatement(query.findResident(room_numbers[i], vals.get(j)));
					 ResultSet rs = stmt.executeQuery();
					 if(rs.isBeforeFirst()) {
						 while(rs.next()) {
						 if(rs.getString(1).equals("null") == false) {
							 if(identifier.equals("ARRAY [")) {
								 identifier = identifier + "'"+rs.getString(1)+"'";
								 identifiers.add(rs.getString(1));
							 }
							 else {
								 identifier = identifier + ", '" + rs.getString(1) +"'";
								 identifiers.add(rs.getString(1));
							 }
							 found = "true";
							 }
						 else {
							 found ="false";
							 rooms.remove(rooms.size()-1);
						 }
					 }
					 }
					 else {
						 HttpSession session = request.getSession();
						 request.setAttribute("agent", session.getAttribute("name"));
						 request.setAttribute("date_of_incident", request.getParameter("date_of_incident"));
						 request.setAttribute("time_of_incident", request.getParameter("time_of_incident"));
						 request.setAttribute("room_num", request.getParameter("room_num"));
						 request.setAttribute("individuals", request.getParameter("individuals"));
						 }
				 }
			 }
			 identifier = identifier +"]";
			 if(found.equals("true")) {
				 String report_type = request.getParameter("report_type");
				 String date_of_incident = request.getParameter("date_of_incident");
				 String title = report_type+"-"+""+request.getParameter("individuals")+"-"+date_of_incident+"(Agent"+agent_name+").pdf";
				 String report = request.getParameter("report");
				 report = report.replace("'", "''");
				 HttpSession session = request.getSession();
				 PreparedStatement stmt2 = conn.prepareStatement(query.reportCreated(identifier, title, "Reports/"+rooms.get(0)+"/",session.getAttribute("name"), java.time.LocalDate.now(), "No", report, request.getParameter("date_of_incident"), request.getParameter("report_type"), request.getParameter("room_num"), request.getParameter("individuals")));
				 stmt2.executeUpdate();
				 PreparedStatement findReport =  conn.prepareStatement(query.findReport(title, request.getParameter("room_num") , session.getAttribute("name"), request.getParameter("individuals")));
				 ResultSet report_id = findReport.executeQuery();
				 String report_identifier = "";
				 if(report_id.isBeforeFirst()) {
					 while(report_id.next()) {
						 report_identifier = report_id.getString(1);
					 }
				 }
				 for(int m=0; m<identifiers.size(); m++) {
					 PreparedStatement getReports = conn.prepareStatement(query.getReports(identifiers.get(m)));
					 ResultSet gotReports = getReports.executeQuery();
					 String temp = "";
					 if(gotReports.isBeforeFirst()) {
						 while(gotReports.next()) {
							 temp = gotReports.getString(1);
						 }
					 }
					 temp = temp + report_identifier +",";
					 PreparedStatement updateReports = conn.prepareStatement(query.updateReports(identifiers.get(m), temp));
					 updateReports.executeUpdate();
				 }
				for(int k=0; k<rooms.size(); k++) {
					 submitReport(request, response, rooms.get(k));
				 }
			 }
			 else if(found.equals("false")){
				 request.getRequestDispatcher("writeReportError.jsp").forward(request, response);
				 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void openReport(HttpServletRequest request, HttpServletResponse response) {
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
			 request.getRequestDispatcher("openReport2.jsp").forward(request, response);
		 }catch(Exception e) {
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
			 String card = "";
			 ArrayList<String> identifiers = new ArrayList<String>();
			 ArrayList<String> image_path = new ArrayList<String>();
			 ArrayList<String> first_name = new ArrayList<String>();
			 ArrayList<String> last_name = new ArrayList<String>();
			 ArrayList<String> status = new ArrayList<String>();
			 ArrayList<String> uci = new ArrayList<String>();
			 ArrayList<String> bracelet = new ArrayList<String>();
			 ArrayList<String> room_num = new ArrayList<String>();
			 ArrayList<String> dob = new ArrayList<String>();
			 ArrayList<String> gender = new ArrayList<String>();
			 ArrayList<String> primary_language = new ArrayList<String>();
			 ArrayList<String> other_language = new ArrayList<String>();
			 ArrayList<String> citizenship = new ArrayList<String>();
			 ArrayList<String> phone = new ArrayList<String>();
			 ArrayList<String> email = new ArrayList<String>();
			 ArrayList<String> family_composition = new ArrayList<String>();
			 
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					identifiers.add(rs.getString("identifier"));
					image_path.add(rs.getString("image_path"));
					first_name.add(rs.getString("nom"));
					last_name.add(rs.getString("surname"));
					status.add(rs.getString("status"));
					uci.add(rs.getString("uci"));
					bracelet.add(rs.getString("bracelet"));
					room_num.add(rs.getString("room_num"));
					dob.add(rs.getString("dob"));
					gender.add(rs.getString("gender"));
					primary_language.add(rs.getString("primary_language"));
					other_language.add(rs.getString("other_language"));
					citizenship.add(rs.getString("citizenship"));
					phone.add(rs.getString("phone"));
					email.add(rs.getString("email"));
					family_composition.add(rs.getString("family_composition"));
				}
			}
			int count = 0;
			for(int i=0;i<status.size();i++) {
				String s = "";
					s = "<div class=\"container\"> <div class=\"card shadow-sm\"><img class=\"logo\" src=\"img/globalys_logo.png\" alt=\"logo\">"
							+ "<div class=\"card-header bg-transparent text-center\"> <img class=\"profile_img\" src=\""+image_path.get(i)+"\" alt=\"profile pic\">"
							+ "<h3>"+first_name.get(i)+"</h3> <h3>"+last_name.get(i)+"</h3> </div>"
							+ "<div class=\"card-body\">"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Status: </strong>"+status.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">UCI: </strong>"+uci.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Bracelet: </strong>"+bracelet.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Room Number : </strong>"+room_num.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Name: </strong>"+first_name.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Surname: </strong>"+last_name.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Date of Birth: </strong>"+dob.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Gender: </strong>"+gender.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Primary Language: </strong>"+primary_language.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Other Language: </strong>"+other_language.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Citizenship: </strong>"+citizenship.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Phone Number: </strong>"+phone.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Email: </strong>"+email.get(i)+"</p>"
							+ "<p class=\"mb-0\"><strong class=\"pr-1\">Family Composition: </strong>"+family_composition.get(i)+"</p>"
							+ "<p class=\"mb-0\"><form method=\"post\" action=\"Agent\"><input type=\"hidden\" name = \"method\" value = \"open\"> <input type=\"hidden\" name=\"id\" value="+identifiers.get(i)+"> <button>Open Profile</button></form></p>"
							+"</div></div>";
				card = card + s;
			}
			request.setAttribute("card", card);
			request.getRequestDispatcher("openFamily.jsp").forward(request, response);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void meeting(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 String id = request.getParameter("id");
			 String uci = "";
			 String bracelet = "";
			 String room_num = "";
			 String nom = "";
			 String family_composition = "";
			 String arrival_date = "";
			 String adultORminor = "";
			 String assigned_agent = "";
			 String family_id = "";
			 String irb_decision = "";
			 PreparedStatement stmt = conn.prepareStatement(query.getInfo(id));
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 uci = rs.getString(1);
					 bracelet = rs.getString(2);
					 room_num = rs.getString(3);
					 nom = rs.getString(5) + ", " + rs.getString(4);
					 family_composition = rs.getString(6);
					 arrival_date = rs.getString(7);
					 if(rs.getInt(8)<18) {
						 adultORminor = "Minor";
					 }
					 else {
						 adultORminor = "Adult";
					 }
					 assigned_agent = rs.getString(9);
					 family_id = rs.getString(10);
					 irb_decision = rs.getString(11);
				 }
			 }
			 String agent = request.getParameter("agent");
			 String meeting_date = request.getParameter("meeting_date");
			 String meeting_type = request.getParameter("meeting_type");
			 String meeting_notes = request.getParameter("meeting_notes");
			 String employment_status = request.getParameter("employment_status");
			 String employment_type = request.getParameter("employment_type");
			 String looking_job = request.getParameter("looking_job");
			 String resume = request.getParameter("resume");
			 String[] actions = request.getParameterValues("actions");
			 String[] obstacles = request.getParameterValues("obstacles");
			 String experience = request.getParameter("experience");
			 String income = request.getParameter("income");
			 String transportation = request.getParameter("transportation");
			 String lodging = request.getParameter("lodging");
			 String moving_date = "'"+request.getParameter("moving_date")+"'";
			 String housing_search = request.getParameter("housing_search");
			 String housing_outside = request.getParameter("housing_outside");
			 String colocation = request.getParameter("colocation");
			 String[] housing_obstacles = request.getParameterValues("housing_obstacles");
			 String housing_support = request.getParameter("housing_support");
			 String actions_values = "";
			 String obstacles_values = "";
			 String housing_ob = "";
			 String jobBank = request.getParameter("jobBank");
			 String certificateIndustry = request.getParameter("certificateIndustry");
			 String wayOfFindingLodging = request.getParameter("wayOfFindingLodging");
			 if(employment_status.equals("Yes")){
				 looking_job = "No (Have One)";
				 actions_values = "[]";
				 obstacles_values = "[]";
			 }
			 else if(employment_status.equals("No")) {
				 employment_type = "";
			 }
			 if(lodging.equals("Yes")) {
				 housing_search = "";
				 housing_outside = "";
				 colocation = "";
				 housing_ob = "[]";
				 housing_support = "";
			 }
			 else if(lodging.equals("No")) {
				 moving_date = "NULL";
			 }
			 if(actions != null) {
				 actions_values = "[";
			 for(int i=0; i<actions.length; i++) {
				 if(i == 0) {
					 actions_values = actions_values + ""+actions[i]+"";
				 }
				 else {
					 actions_values = actions_values +", "+actions[i]+"";
				 }
			 }
			 actions_values = actions_values + "]";
			 }
			 if(obstacles != null) {
				 obstacles_values = "[";
			 for(int j=0; j<obstacles.length; j++) {
				 if(j == 0) {
					 obstacles_values = obstacles_values + ""+obstacles[j]+"";
				 }
				 else {
					 obstacles_values = obstacles_values +", "+obstacles[j]+"";
				 }
			 }
			 obstacles_values = obstacles_values +"]";
			 }
			 if(housing_obstacles != null) {
			 housing_ob = "[";
			 for(int k=0; k<housing_obstacles.length; k++) {
				 if(k == 0) {
					 housing_ob = housing_ob + ""+housing_obstacles[k]+"";
				 }
				 else {
					 housing_ob = housing_ob +", "+housing_obstacles[k]+"";
				 }
			 }
			 housing_ob = housing_ob + "]";
			 }
			 if(moving_date.equals("''")) {
				 moving_date = "NULL";
			 }
			 PreparedStatement stmt2 = conn.prepareStatement(query.insertMeeting(uci, bracelet, room_num, nom, family_composition, arrival_date, adultORminor, agent, meeting_date, meeting_type, meeting_notes, employment_status, employment_type, looking_job, resume, actions_values, obstacles_values, experience, income, transportation, lodging, moving_date, housing_search, housing_outside, colocation, housing_ob, housing_support, assigned_agent, family_id, irb_decision, jobBank, certificateIndustry, wayOfFindingLodging));
			 stmt2.executeUpdate();
			 conn.close();
			 generate(request, response);
		 }catch(ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 
	 public void openMeetingList(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 HttpSession session = request.getSession();
			 String name = (String) session.getAttribute("name");
			 String sqlQuery = query.openAssignedNoOffset(name);
			 PreparedStatement stmt = conn.prepareStatement(query.openAssignedResident(name, 0));
			 ResultSet rs = stmt.executeQuery();
			 PreparedStatement stmt_2 = conn.prepareStatement(query.countAssigned(name));
			 ResultSet rs_2 = stmt_2.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String id = rs.getString("identifier");
						if(id==null)
							id="";
						String status = rs.getString("Status");
						if(status==null)
							status="";
						String agent = rs.getString("agent");
						if(agent==null)
							agent="";
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
						String color = "";
						String date = "";
						LocalDate today = LocalDate.now();
						LocalDate Birth = LocalDate.parse(dob);
						int age = Period.between(Birth, today).getYears();
						String sql = "";
						if(age < 18 ) {
							sql = query.kidsMostRecentMeeting(rs.getString("family_id"));
						}
						else {
							sql = query.mostRecentMeeting(rs.getString("uci"));
						}
						PreparedStatement mostRecentMeeting = conn.prepareStatement(sql);
						ResultSet mostRecent = mostRecentMeeting.executeQuery();
						if(mostRecent.isBeforeFirst()) {
							while(mostRecent.next()) {
								date = mostRecent.getString(1);
							}
						}
						LocalDate recent;
						 LocalDate curDate = LocalDate.now();
						 if(date != null && date.equals("")==false) {
							 recent = LocalDate.parse(date);
							 int days = Period.between(recent, curDate).getDays();
							 if(days<13) {
								 color = "#ccffb3";
							 }
							 else if(days>12 && days<17) {
								 color = "#ffff99";
							 }
							 else {
								 color = "#ffc2b3";
							 }
						 }
						 else {
							 color = "#ffc2b3";
						 }
						Identifiers.add(id);
						String row = "<tr bgcolor=\""+color+"\"><td>" + status.toString() + "</td><td>" + agent.toString() + "</td><td>" + uci.toString() + "</td><td>" + room_num.toString() + "</td><td>" + f_name.toString() + "</td><td>" + s_name.toString() + "</td><td>" + dob.toString() + "</td><td>" + gender.toString() + "</td><td>" + COO.toString() + "</td><td><form method=\"post\" action=\"Agent\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"open\">\r\n"
					            + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">\r\n"
					            + "<button class=\"buttonss\" type=\"submit\">Open Profile</button>"
					            + "</td></form></tr>";
						html += row.toString();
					}
			 }
			 if(rs_2.isBeforeFirst()) {
				 while(rs_2.next()) {
					 request.setAttribute("count", (int) Math.ceil((double)rs_2.getInt(1)/12));
				 }
			 }
			 request.setAttribute("pagination", pagination);
			 request.setAttribute("query", sqlQuery);
			 request.setAttribute("html", html);
			 request.getRequestDispatcher("assignedAgentPortal.jsp").forward(request, response);
			 html = "";
			 pagination = "";
			 rs.close();
			 rs_2.close();
		 }catch(IOException | ServletException | ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
		}
	 
	 public void export(HttpServletRequest request, HttpServletResponse response) {
		 Connection conn;
		 try {
			 XSSFWorkbook workbook = new XSSFWorkbook(); 
		     XSSFSheet spreadsheet = workbook.createSheet(" Meeting Data "); 
		     XSSFRow row;
			 Class.forName("org.postgresql.Driver");
			 conn = DriverManager.getConnection(url, nom_utilisateur, code);
			 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM resident");
			 ResultSet rs = stmt.executeQuery();
			 int count = 2;
			 Map<String, Object[]> residentData = new TreeMap<String, Object[]>();
			 residentData.put("1", new Object[] {"Status", "Room Number", "UCI", "Bracelet", "Arrival Date to Canada", "Mode of Arrival", "Reunification Date", "Arrival Date at DEV", "Surname", "Name", "Date of Birth", "Gender", "Primary Language", "Other Languages", "Family Composition", "Citizenship", "Phone Number", "Email", "Biometrics", "AoC", "IME", "eApp", "RPCD", "BoC", "PRRA", "Work Permit", "Work Permit Date", "IRB Decision", "Date of IRB Decision", "Healthcare Coverage", "Bank Account", "Legal Aid", "Ontario Works", "Date of Ontario Works", "Service Canada", "General Assistance Refferals", "Initial Settelment Assessment", "Destination Preference", "Settelment Updates", "Education", "Certifications", "Adult Learning", "Language Classes", "Initial Proficiency", "Current Proficiency", "Employment", "Resume", "Employment Type", "Employment Challenges", "Employer", "Start Employment", "Refferal JobZone", "Date of Jobzone Refferal", "Refferal to Drake", "Date of Drake Refferal", "Refferal to Dev", "Date of Refferal to Dev", "Refferal to NEWS", "Date of Refferal to NEWS", "Ontario Works Notified", "Employment Assessment", "Initial Employment Assessment", "Employment Updates", "Housing Assessment", "Housing Updates", "Housing Application", "Date Housing Support", "Projected Move Out Date", "First and Last Month", "Networth", "Signed Lease", "Civil Workshop", "Tenant Workshop", "Resume Workshop", "Legal Workshop", "Domestic Workshop", "Traffic Workshop", "Jobzone Resume Workshop", "Jobzone Job Search Workshop", "JobZone Job Interview Workshop", "Occurances", "IRCC Refferal", "Date of Last IRCC Appointment", "Medical Conditions", "Mental Health", "Clothing", "Childcare", "CRA", "Volunteering", "Furniture", "Speciality Food", "Food Bank", "Vehicle", "Insurance", "Parking Pass", "Registration", "Vaccinations", "TriCounty Refferal", "School", "Service Ontario", "Victim Services", "House of Faith", "LGBTQ+", "Follow Up Transit Center Appointment", "Assigned Agent"});
			 if(rs.isBeforeFirst()) {
				 while(rs.next()) {
					 residentData.put(""+count, new Object[] {rs.getString("status"), rs.getString("room_num"), rs.getString("uci"), rs.getString("bracelet"), rs.getString("arrival_canada"), rs.getString("mode_of_arrival"), rs.getString("reunification"), rs.getString("arrival_dev"), rs.getString("surname"), rs.getString("nom"), rs.getString("dob"), rs.getString("gender"), rs.getString("primary_language"), rs.getString("other_language"), rs.getString("family_composition"), rs.getString("citizenship"), rs.getString("phone"), rs.getString("email"), rs.getString("biometrics"), rs.getString("aoc"), rs.getString("ime"), rs.getString("eapp"), rs.getString("rpcd"), rs.getString("boc"), rs.getString("prra"), rs.getString("work_permit"), rs.getString("wp_date"), rs.getString("irb_decision"), rs.getString("length_irb"), rs.getString("healthcare"), rs.getString("bank"), rs.getString("legal"), rs.getString("ontario_works"), rs.getString("length_ow"), rs.getString("service_canada"), rs.getString("gen_assistance"), rs.getString("initial_settelment"), rs.getString("destination_preference"), rs.getString("settelment_updates"), rs.getString("education"), rs.getString("certifications"), rs.getString("adult_learning"), rs.getString("language_class"), rs.getString("initial_proficiency"), rs.getString("current_proficiency"), rs.getString("employment"), rs.getString("resume"), rs.getString("part_full_time"), rs.getString("employment_challenges"), rs.getString("employer"), rs.getString("start_employment"), rs.getString("refferal_jobzone"), rs.getString("date_jobzone"), rs.getString("refferal_drake"), rs.getString("date_drake"), rs.getString("refferal_dev"), rs.getString("dev_date"), rs.getString("refferal_news"), rs.getString("date_news"), rs.getString("ow_notified"), rs.getString("employment_assessment"), rs.getString("initial_mployment_assessment"), rs.getString("employment_updates"), rs.getString("housing_assessment"), rs.getString("housing_updates"), rs.getString("housing_application"), rs.getString("date_housing_support"), rs.getString("projected_move_out"), rs.getString("first_last_month"), rs.getString("networth"), rs.getString("signed_lease"), rs.getString("civil_ws"), rs.getString("tenant_ws"), rs.getString("resume_ws"), rs.getString("legal_ws"), rs.getString("domestic_ws"), rs.getString("traffic_ws"), rs.getString("jobzone_resume"), rs.getString("jobzone_search"), rs.getString("jobzone_interview"), rs.getString("occurances"), rs.getString("ircc_refferal"), rs.getString("date_ircc"), rs.getString("medical_condition"), rs.getString("mental_health"), rs.getString("clothing"), rs.getString("childcare"), rs.getString("cra"), rs.getString("volunteering"), rs.getString("furniture"), rs.getString("speciality_food"), rs.getString("food_bank"), rs.getString("vehicle"), rs.getString("insurance"), rs.getString("parking_pass"), rs.getString("registration"), rs.getString("vaccinations"), rs.getString("tricounty_refferal"), rs.getString("school"), rs.getString("service_ontario"), rs.getString("victim_services"), rs.getString("house_faith"), rs.getString("lgbt"), rs.getString("follow_up_tc")});
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
			    response.setHeader("Content-Disposition", "attachment; filename=Resident Database-"+curDate+".xlsx");

			    ServletOutputStream outputStream = response.getOutputStream();
			    workbook.write(outputStream);
			    workbook.close();

			    outputStream.close();
		 }catch(ClassNotFoundException | SQLException | IOException e) {
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
			 String agent = "";
			 num = num - 1;
			 int offset = num*12;
			 String sqlQuery = jsonObject.getString("query");
			 PreparedStatement stmt = conn.prepareStatement(sqlQuery+" "+offset);
			 ResultSet rs = stmt.executeQuery();
			 if(rs.isBeforeFirst()) {
					while(rs.next()) {
						String id = rs.getString("identifier");
						if(id==null)
							id="";
						String status = rs.getString("Status");
						if(status==null)
							status="";
						if(sqlQuery.contains("SELECT * FROM resident WHERE agent iLike")) {
							agent = rs.getString("agent");
							if(agent==null)
								agent = "";
						}
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
						if(sqlQuery.contains("SELECT * FROM resident WHERE agent iLike")) {
							String color = "";
							String date = "";
							LocalDate today = LocalDate.now();
							LocalDate Birth = LocalDate.parse(dob);
							int age = Period.between(Birth, today).getYears();
							String sql = "";
							if(age < 18 ) {
								sql = query.kidsMostRecentMeeting(rs.getString("family_id"));
							}
							else {
								sql = query.mostRecentMeeting(rs.getString("uci"));
							}
							PreparedStatement mostRecentMeeting = conn.prepareStatement(sql);
							ResultSet mostRecent = mostRecentMeeting.executeQuery();
							if(mostRecent.isBeforeFirst()) {
								while(mostRecent.next()) {
									date = mostRecent.getString(1);
								}
							}
							LocalDate recent;
							 LocalDate curDate = LocalDate.now();
							 if(date != null && date.equals("")==false) {
								 recent = LocalDate.parse(date);
								 int days = Period.between(recent, curDate).getDays();
								 if(days<13) {
									 color = "#ccffb3";
								 }
								 else if(days>12 && days<17) {
									 color = "#ffff99";
								 }
								 else {
									 color = "#ffc2b3";
								 }
							 }
							 else {
								 color = "#ffc2b3";
							 }
							Identifiers.add(id);
							String row = "<tr bgcolor=\""+color+"\"><td>" + status.toString() + "</td><td>" + agent.toString() + "</td><td>" + uci.toString() + "</td><td>" + room_num.toString() + "</td><td>" + f_name.toString() + "</td><td>" + s_name.toString() + "</td><td>" + dob.toString() + "</td><td>" + gender.toString() + "</td><td>" + COO.toString() + "</td><td><form method=\"post\" action=\"Agent\">\r\n"
						            + "<input type=\"hidden\" name=\"method\" value=\"open\">\r\n"
						            + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">\r\n"
						            + "<button class=\"buttonss\" type=\"submit\">Open Profile</button>"
						            + "</td></form></tr>";
							val += row.toString();
						}
						else {
						Identifiers.add(id);
						String row = "<tr><td>" + status.toString() + "</td><td>" + uci.toString() + "</td><td>" + room_num.toString() + "</td><td>" + f_name.toString() + "</td><td>" + s_name.toString() + "</td><td>" + dob.toString() + "</td><td>" + gender.toString() + "</td><td>" + COO.toString() + "</td><td><form method=\"post\" action=\"Agent\">\r\n"
					            + "<input type=\"hidden\" name=\"method\" value=\"open\">\r\n"
					            + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">\r\n"
					            + "<button class=\"buttonss\" type=\"submit\">Open Profile</button>"
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
				page=0;
				generate(request, response);
				request.setAttribute("html", html);
				generate(request, response);
			}
			else if(method.equals("home")) {
				generate(request, response);
				if (!response.isCommitted()) {
		            request.getRequestDispatcher("agentPortal.jsp").forward(request, response);
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
			else if(method.equals("agent")) {
				generate(request, response);
				if (!response.isCommitted()) {
		            request.getRequestDispatcher("agentPortal.jsp").forward(request, response);
		        }
			}
			else if(method.equals("open")) {
				resident_page = 0;
				open(request, response);
			}
			else if(method.equals("next_page")) {
				resident_page = resident_page + 1;
				open(request, response);
			}
			else if(method.equals("previous_page")) {
				resident_page = resident_page - 1;
				open(request, response);
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
			else if(method.equals("SectionA")) {
				resident_page = 0;
				open(request, response);
			}
			else if(method.equals("SectionB")) {
				resident_page = 1;
				open(request, response);
			}
			else if(method.equals("SectionC")) {
				resident_page = 2;
				open(request, response);
			}
			else if(method.equals("SectionD")) {
				resident_page = 3;
				open(request, response);
			}
			else if(method.equals("SectionE")) {
				resident_page = 4;
				open(request, response);
			}
			else if(method.equals("SectionF")) {
				resident_page = 5;
				open(request, response);
			}
			else if(method.equals("addNewResident")) {
				response.sendRedirect("addResident.jsp");
			}
			else if(method.equals("NextIntake")) {
				createResident(request, response);
			}
			else if(method.equals("SubmitNewResident")) {
				addResident(request, response);
			}
			else if(method.equals("saveChanges1")) {
				saveResident1(request, response);
			}
			else if(method.equals("saveChanges2")) {
				saveResident2(request, response);
			}
			else if(method.equals("saveChanges3")) {
				saveResident3(request, response);
			}
			else if(method.equals("saveChanges4")) {
				saveResident4(request, response);
			}
			else if(method.equals("saveChanges5")) {
				saveResident5(request, response);
			}
			else if(method.equals("writeReport")) {
				response.sendRedirect("writeReport.jsp");
			}
			else if(method.equals("submitReport")) {
				checkResident(request, response);
			}
			else if(method.equals("depart")) {
				setAttributes(request, response);
				}
			else if(method.equals("departResident")) {
				depart(request, response);
			}
			else if(method.equals("openReport")){
				openReport(request, response);
			}
			else if(method.equals("openFamily")) {
				openFamily(request, response);
			}
			else if(method.equals("meeting")) {
				resident_page = 6;
				open(request, response);
			}
			else if(method.equals("saveMeeting")) {
				meeting(request, response);
			}
			else if(method.equals("openAssigned")) {
				openMeetingList(request, response);
			}
			else if(method.equals("next_assigned")) {
				assigned_page = assigned_page + 1;
				openMeetingList(request, response);
			}
			else if(method.equals("previous_assigned")) {
				assigned_page = assigned_page - 1;
				openMeetingList(request, response);
			}
			else if(method.equals("export")) {
				export(request, response);
			}
			else if(method.equals("changePage")) {
				changePage(request, response);
			}
			}
	 }
}
