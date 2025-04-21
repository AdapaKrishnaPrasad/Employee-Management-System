package employeemanagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int age = Integer.parseInt(req.getParameter("age"));
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html><body>");
		pw.println("<form action='updatesuccess' method='post'>");
		pw.println("<h1>Update your details.....</h1>");
		pw.println("<label>Id : </label>");
        pw.println("<input type='text' name='id' value='" + id + "' readonly><br><br><br>");
        pw.println("<label>First Name : </label>");
        pw.println("<input type='text' name='fname' value='" + fname + "'><br><br><br>");
        pw.println("<label>Last Name : </label>");
        pw.println("<input type='text' name='lname' value='" + lname + "'><br><br><br>");
        pw.println("<label>Email : </label>");
        pw.println("<input type='email' name='email' value='" + email + "'><br><br><br>");
        pw.println("<label>Password : </label>");
        pw.println("<input type='text' name='password' value='" + password + "'><br><br><br>");
        pw.println("<label>Age : </label>");
        pw.println("<input type='number' name='age' value='" + age + "'><br><br><br>");
        pw.println("<label>Gender : </label>");
        pw.println("<input type='text' name='gender' value='" + gender + "'><br><br><br>");
        pw.println("<label>Address : </label>");
        pw.println("<input type='text' name='address' value='" + address + "'><br><br><br>");
        pw.println("<button>Update</button>");
        pw.println("</form>");
        pw.println("</body></html>");
	}
}
