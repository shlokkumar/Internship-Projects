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
 * Servlet implementation class Deleteall
 */
@WebServlet("/Deleteall")
public class Deleteall extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String unm = (String) session.getAttribute("unm");
		String q = "DELETE FROM records Where uname=" + "\"" + unm + "\"";
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Done");
			con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/sqlite-tools-win32-x86-3170000/new.db");
			Statement statement = con.createStatement();
			statement.execute(q);
			pw.println("Your Record is deleted");
			request.getRequestDispatcher("First.jsp").forward(request, response);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
