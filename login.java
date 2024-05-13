package Tracker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import jakarta.servlet.*;  
import jakarta.servlet.http.*;  
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import Tracker.sql;


import javax.naming.InitialContext;

import java.security.MessageDigest;

@WebServlet("/Login")
public class login extends HttpServlet{
	private String html = "";
	private sql query = new sql();
	private DataSource datasource = null;
	private String page = "index";
	private final String url = "jdbc:postgresql://localhost:5432/Dev Tracker";
    private final String nom_utilisateur = "postgres";
    private final String code = "1234";
    
    public static HttpSession session;
    
    public void login(HttpServletRequest request, HttpServletResponse response) {
    	Connection conn;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, nom_utilisateur, code);
			String uname = request.getParameter("uname");
			String password = request.getParameter("password");
			PreparedStatement stmt = conn.prepareStatement(query.userLogin(uname, password));
			ResultSet rs = stmt.executeQuery();
			
			if (rs.isBeforeFirst()) {
				while(rs.next()) {
					String r_fname = rs.getString("nom");
					HttpSession session = request.getSession(true);
					session.setAttribute("name", r_fname);
					session.setAttribute("role", rs.getString("account_role"));
					if((rs.getString("account_role")).equals("Agent")) {
						Agent agent = new Agent(r_fname);
						request.setAttribute("html", html);
						agent.doPost(request, response);
					}
					else if ((rs.getString("account_role")).equals("Administrator")) {
						Admin admin = new Admin(r_fname);
						request.setAttribute("html", html);
						admin.doPost(request, response);
					} 
					else {
						Client client = new Client();
						request.setAttribute("html", html);
						client.generate(request, response);
					}
				}
			}
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request,response);
				page = "login";
			}
			conn.close();
			rs.close();
			return;
		} catch (SQLException | IOException | ServletException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
    public void init() throws ServletException {
		try {
			InitialContext ctx = new InitialContext();
			datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch (Exception e) {
			throw new ServletException(e.toString());
		}

	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("login"))
			login(request,response);
		else if (method.equals("logout")){
			session.invalidate();
			response.sendRedirect("index.jsp");
		}

	}
}