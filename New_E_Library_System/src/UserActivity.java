

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


@WebServlet("/UserActivity")
public class UserActivity extends HttpServlet {
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
					+ "	<p>User Activity</p>"
					+ "</div>"
					+ "<br/>"
					+ "<center>"
					+ "<table>"
					+ "	<tr>"
					+ "		<th>User Name</th>"
					+ "		<th>Book Name</th>"
					+ "		<th>Data & Time</th>"
					+ "	</tr>");
			
			String query = "select username, Book_Name, Date from user_activity order by serial_no desc";
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/servlet","root","root");
				PreparedStatement stmt = con.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					String username = rs.getString("username");
					String Book_Name = rs.getString("Book_Name");
					String Date = rs.getString("Date");
					out.println(""
							+ "<tr>"
							+ "	<td>"+username+"</td>"
							+ "	<td>"+Book_Name+"</td>"
							+ "	<td>"+Date+"</td>"
							+ "</tr>");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			out.println("</table>"
					+ "	</center>");		
	}
}	
