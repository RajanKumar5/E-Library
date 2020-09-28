

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Books")
public class Books extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(session.getAttribute("username")==null){
			response.sendRedirect("index.html");
		}
		//String branch = request.getParameter("getBranch");
		String subject = request.getParameter("getSubject");
		out.print("<head>"
				+ "	<style>"
				+ "		.logout a{"
				+ "			position : absolute;"
				+ "			top : 10px;"
				+ "			right : 10px;"
				+ "			color : #000066;"
				+ "		}"
				+ "		.logout a:hover{"
				+ "			color : red;"
				+ "		}"
				+ "		.logout{"
				+ "			font-size : 20px;"	
				+ "		}"
				+ "		.heading{"
				+ "			background-color : black;"
				+ "			width : 1254px;"
				+ "			height : 40px;"
				+ "			border : 1px solid gray;"
				+ "			text-align : center;"
				+ "			margin-left : 41px;"
				+ "			color : white;"
				+ "			position : relative;"
				+ "			line-height : 10px;"
				+ "			font-family : cursive;"
				+ "		}"
				+ "		.image{"
				+ "			margin-left : 41px;"
				+ "			margin-top : -10px;"
				+ "		}"
				+ "		body{"
				+ "			background-color :  #e6f2ff;"
				+ "		}"
				+ "		a{"
				+ "			text-decoration : none;"
				+ "		}"
				+ "		input [type=text]{"
				+ "			width : 70px;"
				+ "			font-size : 20px;"
				+ "			font-family : Arial;"
				+ "		}"
				+ "	</style>"
				+ "<div class=logout>"
				+ "	Welcome "+session.getAttribute("username")+""
				+ "	<a href='./Logout'>Logout</a>"
				+ "</div>"
				+ "<br/><hr>"
				+ "<div class=heading>"
				+ "	<p>Download Your Book Here</p>"
				+ "</div>"
				+ "<center>"
				+ "	<pre>"
				+ "	<form action='Download' method='post' autocomplete=off>"
				+ "	Branch : "+session.getAttribute("Branch")+"		Subject : "+subject+"		Books: <input type='text' name='getBook' list='Book' placeholder='Select your Book' size=55px; required='' />     <input type='Submit' value='Download'>"
				+ "	</form>"
				+ "	</pre>"
				+ "	<datalist id='Book'>");
		session.setAttribute("Subject", subject);
		try{
			String query = "select Book_Name from books where Subject=?";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/servlet","root","root");
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, subject);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String Book_Name =(String) rs.getString("Book_Name");
				out.println("<option value='"+Book_Name+"'>"+Book_Name+"</option>");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		out.println(""
				+ "</datalist>"
				+ "</center>"
				+ "<div class=image>"
				+ "	<img src=Banner_Image.jpg width=1256px height=450px;>"
				+ "</div>");
	}
}
