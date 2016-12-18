import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
public class UserRegistrationTest extends Mockito{
	private String url = "jdbc:ucanaccess://C:/Softwares/userregister_DB.accdb";
	private Statement st;
	private Connection conn = null;
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	RequestDispatcher rd;
	@Before
	public void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testAddUser() throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getParameter("username")).thenReturn("vinod");
		when(request.getParameter("first_name")).thenReturn("Mysore");
		when(request.getParameter("last_name")).thenReturn("Puttaraju");
		when(request.getParameter("city")).thenReturn("Bangalore");
		when(request.getParameter("state")).thenReturn("Karnataka");
		when(request.getParameter("country")).thenReturn("india");
		AddUser objAddUser = new AddUser();
		objAddUser.doPost(request, response);
		Class.forName( "net.ucanaccess.jdbc.UcanaccessDriver" );
		conn = DriverManager.getConnection(url);
		System.out.println("Connected to the database");
		String query = "select username from userregister where username ='vinod'";
		System.out.println("query " + query);
		st = conn.createStatement();
		ResultSet  rs = st.executeQuery(query);
		if(rs.next()){
		Assert.assertTrue(rs.getString("username").equals("vinod"));
		}else{
			Assert.fail("Inserted Record 'vinod' not found");
			}
	}
	
	@Test
	public void testEditUser() throws ServletException, IOException, ClassNotFoundException, SQLException {
		Class.forName( "net.ucanaccess.jdbc.UcanaccessDriver" );
		conn = DriverManager.getConnection(url);
		System.out.println("Connected to the database");
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getParameter("username")).thenReturn("vinod");
		when(request.getParameter("first_name")).thenReturn("Mandya");
		when(request.getParameter("password")).thenReturn("vinod");
		when(request.getParameter("last_name")).thenReturn("Puttaraju");
		when(request.getParameter("city")).thenReturn("Bangalore");
		when(request.getParameter("state")).thenReturn("Karnataka");
		when(request.getParameter("country")).thenReturn("India");
		String query = "select id from userregister where username ='vinod'";
		st = conn.createStatement();
		ResultSet  rs = st.executeQuery(query);
		if(rs.next()){
			String id = rs.getString("id");
			when(request.getParameter("id")).thenReturn(id);
		}
		EditUser objEditUser = new EditUser();
		objEditUser.doPost(request, response);
		String query1 = "select username from userregister where username ='vinod' and first_name='Mandya'";
		System.out.println("query " + query1);
		st = conn.createStatement();
		ResultSet  rs1 = st.executeQuery(query1);
		if(rs1.next()){
		Assert.assertTrue(rs1.getString("username").equals("vinod"));
		}else{
			Assert.fail("Updated Record 'vinod' not found");
			}
	}
	@Test
	public void testDeleteUser() throws ServletException, IOException, ClassNotFoundException, SQLException {
		String id =null;
		Class.forName( "net.ucanaccess.jdbc.UcanaccessDriver" );
		conn = DriverManager.getConnection(url);
		System.out.println("Connected to the database");
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		String query = "select id from userregister where username ='vinod'";
		st = conn.createStatement();
		ResultSet  rs = st.executeQuery(query);
		if(rs.next()){
			id = rs.getString("id");
			when(request.getParameter("id")).thenReturn(id);
		}
		Mockito.when(request.getRequestURI()).thenReturn("/UserRegistration/deleteUser/" + id);
		DeleteUser objDeleteUser = new DeleteUser();
		objDeleteUser.doGet(request, response);
		String query1 = "select username from userregister where id="+Integer.parseInt(id);
		System.out.println("query " + query1);
		st = conn.createStatement();
		ResultSet  rs1 = st.executeQuery(query1);
		Assert.assertFalse(rs1.next());
	}
}
