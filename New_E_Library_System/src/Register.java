

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String query = "insert into login values(?,?,?)";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/servlet","root","root");
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.setString(3, fullname);
			stmt.executeUpdate();
			out.print("<script>"
					+ "	alert('Registered Successfully!!');"
					+ "</script>");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
		}
		catch(Exception e){
			out.print("<script>"
					+ "	alert('Username already in use....Enter different Username');"
					+ "</script>");
			RequestDispatcher rd = request.getRequestDispatcher("Register.html");
			rd.include(request, response);
			e.printStackTrace();
		}		
	}
}
