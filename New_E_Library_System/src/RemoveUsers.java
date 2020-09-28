

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/RemoveUsers")
public class RemoveUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
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
					+ "		body{"
					+ "			background-color :  #e6f2ff;"
					+ "		}"
					+ "		a{"
					+ "			text-decoration : none;"
					+ "		}"
					+ "		table, th, td{"
					+ "			font-size : 16px;"
					+ "			border : 2px solid black;"
					+ "			border-collapse : collapse;"
					+ "			padding : 5px;"
					+ "		}"
					+ "	</style>"
					+ "<div class=logout>"
					+ "	Welcome "+session.getAttribute("username")+""
					+ "	<a href='./Logout'>Logout</a>"
					+ "</div>"
					+ "<br/><hr>"
					+ "<div class=heading>"
					+ "	<p>Remove Users</p>"
					+ "</div>"
					+ "<center>"
					+ "	<pre>"
					+ "	<form action='Remove' method='post' autocomplete=off>"
					+ "	Enter Username of User : <input type='text' name='getUsername' placeholder='Enter the Username' size=55px; required=''/>     <input type='Submit' value='Delete'>"
					+ "	</form>"
					+ "	</pre>"
					+ "	<h1>*NOTE* : Once user has been deleted it cannot be undone....</h1>"
					+ "	<div class = image>"
					+"		<img src='Remove_User_Image.png' width=350px height=350px >"
					+"	</div>"
					+ "</center>");
	}
}
