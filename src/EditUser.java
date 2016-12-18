import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import net.ucanaccess.jdbc.*;
public class EditUser extends HttpServlet{ 
 
   public void doPost(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException,IOException{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			System.out.println("MS Access Database");
			Connection conn = null;
			String url = "jdbc:ucanaccess://C:/Softwares/userregister_DB.accdb";
			String driver ="net.ucanaccess.jdbc.UcanaccessDriver";
			/*out.println(request.getRequestURI());*/

			if(request.getParameter("id")!=null && request.getParameter("id")!="")
			{

            int id = Integer.parseInt(request.getParameter("id").toString());
			String username = request.getParameter("username").toString();
			String first_name = request.getParameter("first_name").toString();
			String userpass = request.getParameter("password").toString();
			String last_name = request.getParameter("last_name").toString();
			String city = request.getParameter("city").toString();
			String state = request.getParameter("state").toString();
			String country = request.getParameter("country").toString();
		
			
			Statement stmt;
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url);
				System.out.println("Connected to the database");
				
				String query = "update userregister set username='"+username+"',password='"+userpass+"',first_name='"+first_name+"',last_name='"+last_name+"',city='"+city+"',state='"+state+"',country='"+country+"' where id="+id;
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
}