package servdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String name = request.getParameter("nm");
		String password = request.getParameter("pwd");
		int age = Integer.parseInt(request.getParameter("ag"));
		String gender = request.getParameter("sex");
		String location = request.getParameter("country");
		
		HttpSession session = request.getSession(false);
		String unm = (String) session.getAttribute("unm");
		
		String q = "Update records Set name=" + "\"" + name + "\""  + "," + "password=" + "\"" + password + "\"" + "," + "gender=" + "\"" + gender + "\"" + "," + "location=" + "\"" + location + "\"" + "," + "age=" + "\"" + age + "\"" + " where uname=" + "\"" + unm + "\"";
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Done");
			con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/sqlite-tools-win32-x86-3170000/new.db");
			Statement statement = con.createStatement();
			statement.execute(q);
			pw.println("<h1>Record Updated</h1><br>");
			pw.print(" <form action = \"Options.jsp\" > <input type=\"submit\" value=\"Options!!\" </form> ");
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
