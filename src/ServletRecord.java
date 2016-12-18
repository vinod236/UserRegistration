import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
/*import net.ucanaccess.jdbc.*;*/
public class ServletRecord extends HttpServlet{ 
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException,IOException{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			System.out.println("MySQL Connect Example.");
			Connection conn = null;
			String url = "jdbc:ucanaccess://C:/Softwares/userregister_DB.accdb";
			String driver ="net.ucanaccess.jdbc.UcanaccessDriver";
			Statement st;
			try {
				Class.forName( "net.ucanaccess.jdbc.UcanaccessDriver" );
				conn = DriverManager.getConnection(url);
				System.out.println("Connected to the database");
				
				ArrayList al=null;
				ArrayList userList =new ArrayList();
				String query = "select * from userregister order by id ";
				System.out.println("query " + query);
				st = conn.createStatement();
				ResultSet  rs = st.executeQuery(query);


				while(rs.next())
				{
					al  = new ArrayList();
				
				  al.add(rs.getInt("id"));
				  al.add(rs.getString("username"));
				  al.add(rs.getString("first_name"));
				  al.add(rs.getString("last_name"));
				  al.add(rs.getString("city"));
				  al.add(rs.getString("state"));
				  al.add(rs.getString("country"));
				 System.out.println("al :: "+al);
				  userList.add(al);
				}

				request.setAttribute("userList",userList);
				
                String nextJSP = "/home.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
				conn.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
			e.printStackTrace();
			}
  }
}