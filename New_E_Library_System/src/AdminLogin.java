

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int f = 0;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username.equals("Admin05@gmail.com") && password.equals("123456*")){
			f=1;
		}
		if(f==0){
			out.print("<script>"
					+ "	alert('Invalid Username OR Password');"
					+ "</script>");
			RequestDispatcher rd = request.getRequestDispatcher("Admin.html");
			rd.include(request, response);
		}
		if(f==1){
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(1000);
			session.setAttribute("username", username);
			response.sendRedirect("Admin");
		}
	}
}