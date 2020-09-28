

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Admin")
public class Admin extends HttpServlet {
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
				+ "			margin-top : 20px;"
				+ "		}"
				+ "		body{"
				+ "			background-color :  #e6f2ff;"
				+ "		}"
				+ "		a{"
				+ "			text-decoration : none;"
				+ "		}"
				+ "		table tr td{"
				+ "			border : 2px solid;"
				+ "		}"
				+ "		table{"
				+ "			font-size : 20px;"
				+ "		}"
				+ "		table tr a:hover{"
				+ "			color : red;"
				+ "		}"
				+ "		table tr a{"
				+ "			color : #000066;;"
				+ "		}"
				+ "	</style>"
				+ "</head>"
				+ "<div class=logout>"
				+ "	Welcome "+session.getAttribute("username")+""
				+ "	<a href='./Logout'>Logout</a>"
				+ "</div>"
				+ "<br/><hr>"
				+ "<div class=heading>"
				+ "	<p>Select Your Action</p>"
				+ "</div>"
				+ "<br/>"
				+ "<center>"
				+ "<table>"
				+ "	<tr>"
				+ "		<td><a href=./ShowUsers>Show Registered Users</a></td>"
				+ "		<td><a href=./RemoveUsers>Remove Registered Users</a></td>"
				+ "		<td><a href=./UserActivity>Check User Activity</a></td>"
				+ "	</tr>"
				+ "</table>"
				+ "</center>"
				+ "<div class=image>"
				+ "	<img src=Admin_Image.png width=1256px height=450px;>"
				+ "</div>"
		);		
	}
}
