

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		int f = 0;
                String username = request.getParameter("getUsername");
                String check_query = "select * from login where username=?";
		String query = "delete from login where username=?";
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/servlet","root","root");
                    PreparedStatement stmt = con.prepareStatement(check_query);
                    stmt.setString(1, username);
                    ResultSet rs = stmt.executeQuery();
                    if(rs.next()){
                        f=1;
                    }
                }
                catch(Exception e){
                    out.println("<script>"
				+ "alert('Something Went Wrong! Try Again..');"
				+ "</script>");
                    RequestDispatcher rd = request.getRequestDispatcher("RemoveUsers");
                    rd.include(request, response);
                    e.printStackTrace();
                }
                if(f == 0){
                    out.println("<script>"
					+ "		alert('No Such User Exists!!');"
					+ "	</script>");
			RequestDispatcher rd = request.getRequestDispatcher("RemoveUsers");
			rd.include(request, response);
                }
                if(f == 1){
                    try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/servlet","root","root");
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			stmt.executeUpdate();
			out.println("<script>"
					+ "		alert('User Deleted Successfully!!');"
					+ "	</script>");
			RequestDispatcher rd = request.getRequestDispatcher("RemoveUsers");
			rd.include(request, response);
                    }
                    catch(Exception e){
			out.println("<script>"
					+ "		alert('Something Went Wrong! Try Again..');"
					+ "	</script>");
			RequestDispatcher rd = request.getRequestDispatcher("RemoveUsers");
			rd.include(request, response);
			e.printStackTrace();
                    }
                }
	}

}
