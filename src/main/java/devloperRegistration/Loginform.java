package devloperRegistration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Loginform
 */
@WebServlet("/login")
public class Loginform extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("username");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher =null;
		
		try {
			
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Form?useSSL=false","root","Anji@123");
			
			
			String query="select * from Form.Register where uemail=? and upwd=?;";
			
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setString(1,email);
			ps.setString(2,password);
			
			// java.sql.SQLException: Parameter index out of range (3 > number of parameters, which is 2).
		   ResultSet rs = ps.executeQuery();
		   
		   if(rs.next()) {
			   session.setAttribute("name", rs.getString("uname"));
			   dispatcher=request.getRequestDispatcher("index.jsp");
		   }else {
			   request.setAttribute("status", "failed");
			   dispatcher=request.getRequestDispatcher("login.jsp");
		   }
		   dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	}

}
