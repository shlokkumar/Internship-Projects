package servdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String nm = request.getParameter("nm");
		String unm = request.getParameter("unm");
		String pwd = request.getParameter("pwd");
		String age = request.getParameter("ag");
		String gender = request.getParameter("sex");
		String location = request.getParameter("country");
		
		int a = Integer.valueOf(age);
		int f=0;

		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Done");
			con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/sqlite-tools-win32-x86-3170000/new.db");
			String q1 = "Select * from records";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(q1);
			while (rs.next()) {
				if (unm.equals(rs.getString("uname"))) {
					f=1;
					break;
				}
			}
			if(f==0) {
				String q2 = "Insert into records values(?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(q2);
				ps.setString(1, nm);
				ps.setString(2, unm);
				ps.setString(3, pwd);
				ps.setString(4, gender);
				ps.setString(5, location);
				ps.setInt(6, a);
				int i = ps.executeUpdate();
				if(i>0) {
					request.getRequestDispatcher("Logon.html").include(request, response);
					pw.println("<h1>Data Inserted</h1><br>");
				}
			}
			else {
				pw.print("<h1>Username Exists</h1><br>");
				request.getRequestDispatcher("register.html").include(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
