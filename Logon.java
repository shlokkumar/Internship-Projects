package servdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String unm = request.getParameter("unm");
		String pwd = request.getParameter("pwd");
		int f=0;
		String q = "Select * from records";
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Done");
			con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/sqlite-tools-win32-x86-3170000/new.db");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(q);
			while(rs.next()) {
				if(unm.equals(rs.getString("uname")) && pwd.equals(rs.getString("password"))) {
					f=1;
					break;
				}
			}
			System.out.println(f);
			if(f==1) {
				String q1 = "Select * from records where uname=" + "\"" + unm + "\"";
				rs = statement.executeQuery(q1);
				while(rs.next()) {
					HttpSession session = request.getSession();
					session.setAttribute("unm", unm);
					request.getRequestDispatcher("Options.jsp").forward(request, response);
				}
			}
			else {
				pw.println("<h1>Incorrect Credentials!!</h1>");
				request.getRequestDispatcher("Logon.html").include(request, response);
				pw.print(" <form action = \"register.html\" > <input type=\"submit\" value=\"Register\" </form> ");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
