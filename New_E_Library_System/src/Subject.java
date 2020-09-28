

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Subject")
public class Subject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(session.getAttribute("username")==null){
			response.sendRedirect("index.html");
		}
		String branch = request.getParameter("getBranch");
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
				+ "</head>"
				+ "<div class=logout>"
				+ "	Welcome "+session.getAttribute("username")+""
				+ "	<a href='./Logout'>Logout</a>"
				+ "</div>"
				+ "<br/><hr>"
				+ "<div class=heading>"
				+ "	<p>Select Your Subject</p>"
				+ "</div>"
				+ "<center>"
				+ "	<pre>"
				+ "	<form action='Books' method='post' autocomplete=off>"
				+ "	Branch : "+branch+"		Subject: <input type='text' name='getSubject' list='subject' placeholder='Select your Subject' size=55px; required=''/>     <input type='Submit' value='Next >>'>"
				+ "	</form>"
				+ "	</pre>"
				+ "	<datalist id='subject'>");
		session.setAttribute("Branch", branch);
		try{
			String query = "Select subject from branch_subject where branch=?";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/servlet","root","root");
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, branch);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String subject =(String) rs.getString("subject");
				out.println("<option value='"+subject+"'>"+subject+"</option>");
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
