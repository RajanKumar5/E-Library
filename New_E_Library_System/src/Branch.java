

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Branch")
public class Branch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(session.getAttribute("username")==null){
			response.sendRedirect("index.html");
		}
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
				+ "	<p>Select Your Branch</p>"
				+ "</div>"
				+ "<center>"
				+ "	<pre>"
				+ "	<form action='Subject' method='post' autocomplete=off>"
				+ "	Branch : <input type='text' name='getBranch' list='branch' placeholder='Select your Branch' size=55px; required=''/>     <input type='Submit' value='Next >>'>"
				+ "	</form>"
				+ "	</pre>"
				+ "	<datalist id='branch'>"
				+ "		<option value='Civil Engineering'>Civil Engineering</option>"
				+ "		<option value='Electrical Engineering'>Electrical Engineering</option>"
				+ "		<option value='Mechanical Engineering'>Mechanical Engineering</option>"
				+ "		<option value='Computer Science & Engineering'>Computer Science & Engineering</option>"
				+ "		<option value='Information Technology'>Information Technology</option>"
				+ "		<option value='Textile Technology'>Textile Technology</option>"
				+ "		<option value='Architecture'>Architecture</option>"
				+ "		<option value='Computer Application'>Computer Application</option>"
				+ "	</datalist>"
				+ "</center>"
				+ "<div class=image>"
				+ "	<img src=Banner_Image.jpg width=1256px height=450px;>"
				+ "</div>");
	}
}