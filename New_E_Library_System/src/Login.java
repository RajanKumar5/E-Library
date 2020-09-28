

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int f=0;
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("uname");
		String pass = request.getParameter("password");
		LoginDao dao = new LoginDao(); 
		if(dao.check(uname, pass)){
			f=1;
		}
		if(f==1){
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(1000);
			session.setAttribute("username", uname);
			response.sendRedirect("Branch");
		}
		if(f==0){
			out.print("<script>"
					+ "	alert('Invalid Username OR Password');"
					+ "</script>");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
		}
	}
}
