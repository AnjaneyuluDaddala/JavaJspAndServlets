package devloperRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out=response.getWriter();
		//out.print("hello");
		
		String Uname=request.getParameter("name");
		String Password=request.getParameter("pass");
		String Email=request.getParameter("email");
		String Mobile=request.getParameter("contact");
		
		//out.print(Uname+" "+Password+" "+Email+" "+ Mobile);
	    RequestDispatcher dispatcher=null;
	    Connection con=null;
	    try {
		
		//Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Form","root","Anji@123");
	    PreparedStatement ps=con.prepareStatement("insert into Form.Register(uname,upwd,uemail,umobile) values(?,?,?,?)");
	    ps.setString(1, Uname);
	    ps.setString(2, Password);
	    ps.setString(3, Email);
	    ps.setString(4, Mobile);
	    
	   
	   int rowcount=ps.executeUpdate();
	   dispatcher=request.getRequestDispatcher("registration.jsp");
	   if(rowcount>0) {
		  request.setAttribute("status", "success"); 
	   }else {
		   request.setAttribute("status", "failed"); 
	   }
	  dispatcher.forward(request, response);
	}catch(Exception e) {
		e.printStackTrace();
		
	}finally {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	

}
