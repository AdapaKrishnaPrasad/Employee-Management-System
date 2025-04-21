package employeemanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAccount extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int age = Integer.parseInt(req.getParameter("age"));
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		
		try {
			if (connectionn("select * from employeemanagement.customer where email = ?", email).next()) {
				PrintWriter pw = resp.getWriter();
				pw.println("<html><body>");
				pw.println("<h1 style='color:red'>Email already exist</h1>");
				pw.println("</body></html>");
				RequestDispatcher rd = req.getRequestDispatcher("register.html");
				rd.include(req, resp);
			}else if (connectionn("select * from employeemanagement.customer where id = ?", id).next()) {
				PrintWriter pw = resp.getWriter();
				pw.println("<html><body>");
				pw.println("<h1 style='color:red'>Id already exist</h1>");
				pw.println("</body></html>");
				RequestDispatcher rd = req.getRequestDispatcher("register.html");
				rd.include(req, resp);
			} else {
				try {
					
					String query = "insert into employeemanagement.customer values(?,?,?,?,?,?,?,?)";
//				    String query = "create table employeemanagement.customer (id int, firstname varchar(15), lastname varchar(15), email varchar(20), password varchar(15) , age int, gender varchar(5), address varchar(30))";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&&password=root");
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, id);
					ps.setString(2, fname);
					ps.setString(3, lname);
					ps.setString(4, email);
					ps.setString(5, password);
					ps.setInt(6, age);
					ps.setString(7, gender);
					ps.setString(8, address);
					ps.executeUpdate();
					
					req.setAttribute("email", email);
					RequestDispatcher rd = req.getRequestDispatcher("Home");
					rd.forward(req, resp);
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet connectionn(String query, String rudra) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&&password=root");
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, rudra);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
}
