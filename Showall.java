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

/**
 * Servlet implementation class Showall
 */
@WebServlet("/Showall")
public class Showall extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String unm = (String) session.getAttribute("unm");
		//System.out.println(unm);
		String q = "Select * from records Where uname=" + "\"" + unm + "\"";
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Done");
			con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/sqlite-tools-win32-x86-3170000/new.db");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(q);
			while(rs.next()) {
				pw.println("<h1>Your details are as follows :</h1>");
				pw.println(rs.getString("name") + "	    -:-   	" + rs.getString("uname") + "	 -:- 	" + rs.getString("password") + "	 -:- 	" + rs.getString("gender") + "	 -:- 	" + rs.getString("location") + "	 -:- 	" + rs.getInt("age") + "<br>");
				pw.print("<br> <form action = \"Options.jsp\" > <input type=\"submit\" value=\"Options !!\" </form> ");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
