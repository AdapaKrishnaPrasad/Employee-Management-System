package employeemanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateSuccess extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String iid = req.getParameter("id");
		int id = Integer.parseInt(iid);
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String sage = req.getParameter("age");
		int age = Integer.parseInt(sage);
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		
		String query = "update employeemanagement.customer SET firstname=?, lastname=?, password=?, age=?, gender=?, address=? where email=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&&password=root");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, password);
			ps.setInt(4, age);
			ps.setString(5, gender);
			ps.setString(6, address);
			ps.setString(7, email);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("email", email);
		RequestDispatcher rd = req.getRequestDispatcher("Home");
		rd.forward(req, resp);
	}
}
