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

/**
 * Servlet implementation class Showevery
 */
@WebServlet("/Showevery")
public class Showevery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String q = "Select * from records";
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Done");
			con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/sqlite-tools-win32-x86-3170000/new.db");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(q);
			pw.println("<h1>The table has :</h1>");
			while(rs.next()) {
				
				pw.println(rs.getString("name") + "	    -:-   	" + rs.getString("uname") + "	 -:- 	" + rs.getString("password") + "	 -:- 	" + rs.getString("gender") + "	 -:- 	" + rs.getString("location") + "	 -:- 	" + rs.getInt("age") + "<br>");
				
			}
			pw.print("<br> <form action = \"Options.jsp\" > <input type=\"submit\" value=\"Options !!\" </form> ");
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
