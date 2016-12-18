import java.io.*;
import net.ucanaccess.jdbc.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

public class DeleteUser extends HttpServlet{ 
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException,IOException{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			System.out.println("MS Access Database");
			Connection conn = null;
			String url = "jdbc:ucanaccess://C:/Softwares/userregister_DB.accdb";
			String driver ="net.ucanaccess.jdbc.UcanaccessDriver";
			String str[] = request.getRequestURI().toString().split("/");

			int id = Integer.parseInt(str[3]);
					
			Statement stmt;
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url);
				System.out.println("Connected to the database");
				
				ArrayList al=null;
				ArrayList userList =new ArrayList();
				String query = "delete from userregister  where id="+id;
				stmt = conn.createStatement();
            
			    int i = stmt.executeUpdate(query);
				System.out.println("query" + query);
				if(i>0)
				{
					response.sendRedirect("../index.jsp");
				}
				conn.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
			e.printStackTrace();
			}
		
	  }
}