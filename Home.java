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

public class Home extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailed = (String) req.getAttribute("email");
		int id = 0;
		String fname = "";
		String lname = "";
		String email = "";
		String password = "";
		int age = 0;
		String gender = "";
		String address = "";
		
		String query = "select * from employeemanagement.customer where email = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&&password=root");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, emailed);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				fname = rs.getString(2);
				lname = rs.getString(3);
				email = rs.getString(4);
				password = rs.getString(5);
				age = rs.getInt(6);
				gender = rs.getString(7);
				address = rs.getString(8);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		req.setAttribute("id", id);
//		req.setAttribute("fname", fname);
//		req.setAttribute("lname", lname);
//		req.setAttribute("email", email);
//		req.setAttribute("password", password);
//		req.setAttribute("age", age);
//		req.setAttribute("gender", gender);
//		req.setAttribute("address", address);
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html><body>");
//		pw.println("<h1>Id : "+ id + "<h1>");
//		pw.println("<h1>First Name :" + fname + "</h1>");
//		pw.println("<h1>Last Name : "+ lname + "</h1>");
//		pw.println("<h1>Email : "+ email + "</h1>");
//		pw.println("<h1>Password : "+ password + "</h1>");
//		pw.println("<h1>Age : "+ age + "</h1>");
//		pw.println("<h1>Gender : "+ gender + "</h1>");
//		pw.println("<h1>Address : "+ address + "</h1>");
		pw.println("<table style='border:1; padding:10px'>");
		pw.println("<tr>");
		pw.println("<th style='border:2px solid black; padding:10px'>Id</th>");
		pw.println("<th style='border:2px solid black; padding:10px'>FirstName</th>");
		pw.println("<th style='border:2px solid black; padding:10px'>LastName</th>");
		pw.println("<th style='border:2px solid black; padding:10px'>Email</th>");
		pw.println("<th style='border:2px solid black; padding:10px'>Password</th>");
		pw.println("<th style='border:2px solid black; padding:10px'>Age</th>");
		pw.println("<th style='border:2px solid black; padding:10px'>Gender</th>");
		pw.println("<th style='border:2px solid black; padding:10px'>Address</th>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td style='border:2px solid black; padding:10px'>"+ id +"</td>");
		pw.println("<td style='border:2px solid black; padding:10px'>"+ fname +"</td>");
		pw.println("<td style='border:2px solid black; padding:10px'>"+ lname +"</td>");
		pw.println("<td style='border:2px solid black; padding:10px'>"+ email +"</td>");
		pw.println("<td style='border:2px solid black; padding:10px'>"+ password +"</td>");
		pw.println("<td style='border:2px solid black; padding:10px'>"+ age +"</td>");
		pw.println("<td style='border:2px solid black; padding:10px'>"+ gender +"</td>");
		pw.println("<td style='border:2px solid black; padding:10px'>"+ address +"</td>");
		pw.println("</tr><br><br>");
		
//		update form which is hidden
		pw.println("<form action='update' method='post'>");
        pw.println("<input type='hidden' name='id' value='" + id + "'>");
        pw.println("<input type='hidden' name='fname' value='" + fname + "'>");
        pw.println("<input type='hidden' name='lname' value='" + lname + "'>");
        pw.println("<input type='hidden' name='email' value='" + email + "'>");
        pw.println("<input type='hidden' name='password' value='" + password + "'>");
        pw.println("<input type='hidden' name='age' value='" + age + "'>");
        pw.println("<input type='hidden' name='gender' value='" + gender + "'>");
        pw.println("<input type='hidden' name='address' value='" + address + "'>");
        pw.println("<button type='submit'>Update</button>");
        pw.println("</form><br><br>");
        
//        delete form which is hidden
        pw.println("<form action='delete' method='post'>");
        pw.println("<input type='hidden' name='email' value='" + email + "'>");
        pw.println("<button type='submit'>Delete</button>");
        pw.println("</form><br><br>");
        
        pw.println("</body></html>");
	}
}
