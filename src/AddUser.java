import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import net.ucanaccess.jdbc.*;

public class AddUser extends HttpServlet{ 
 
   public void doPost(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException,IOException{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			System.out.println("MS Access Database");
			Connection conn = null;
			String url = "jdbc:ucanaccess://C:/Softwares/userregister_DB.accdb";
			String driver ="net.ucanaccess.jdbc.UcanaccessDriver";

			String username = request.getParameter("username").toString();
			String first_name = request.getParameter("first_name").toString();
			String last_name = request.getParameter("last_name").toString();
			String city = request.getParameter("city").toString();
			String state = request.getParameter("state").toString();
			String country = request.getParameter("country").toString();
		
			
			Statement stmt;
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url);
				System.out.println("Connected to the database");
				
				ArrayList al=null;
				ArrayList userList =new ArrayList();
				String query = "insert into userregister (username,first_name,last_name,city,state,country) values ('"+username+"','"+first_name+"','"+last_name+"','"+city+"','"+state+"','"+country+"')";
				stmt = conn.createStatement();
            
			    int i = stmt.executeUpdate(query);
				System.out.println("query" + query);
				if(i>0)
				{
					response.sendRedirect("servletRecord");
				}
				conn.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
			e.printStackTrace();
			}
	  }
}