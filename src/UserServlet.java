import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import net.ucanaccess.jdbc.*;
public class UserServlet extends HttpServlet{ 
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException,IOException{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String str[] = request.getRequestURI().toString().split("/");

         int id = Integer.parseInt(str[3]);
			System.out.println("MS Access Database");
			Connection conn = null;
			String url = "jdbc:ucanaccess://C:/Softwares/userregister_DB.accdb";
			String driver ="net.ucanaccess.jdbc.UcanaccessDriver";
		//	out.println(request.getRequestURI());
			Statement st;
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url);
				System.out.println("Connected to the database");
				
				
				ArrayList userList=null;
				String query = "select * from userregister where id="+id;
				System.out.println("query " + query);
				st = conn.createStatement();
				ResultSet  rs = st.executeQuery(query);


				while(rs.next())
				{
				  
				  userList =new ArrayList();
				  userList.add(rs.getInt("id"));
				  userList.add(rs.getString("first_name"));
				  userList.add(rs.getString("last_name"));
				  userList.add(rs.getString("username"));
				  userList.add(rs.getString("city"));
				  userList.add(rs.getString("state"));
				  userList.add(rs.getString("country"));
				
				}

				request.setAttribute("userList",userList);
				
                String nextJSP = "/editUser.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
				conn.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
			e.printStackTrace();
			}
  }
}