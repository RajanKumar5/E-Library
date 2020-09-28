

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(session.getAttribute("username")==null){
			response.sendRedirect("index.html");
		}
		String Book_Name = request.getParameter("getBook");
		String fileName = Book_Name+".pdf";
		String filePath = "D:\\E-library\\";
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
		FileInputStream fs = new FileInputStream(filePath+fileName);
		int i;
		while((i=fs.read()) != -1){
			out.write(i);
		}
		String query = "insert into user_activity(username, Book_Name, Date) values(?,?,?)";
		String username = (String) session.getAttribute("username");
		Date date = new Date();
		String Date = date.toString();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/servlet","root","root");
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, Book_Name);
			stmt.setString(3, Date);
			stmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		fs.close();
		out.close();
	}
}
