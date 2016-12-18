<%@ page language="java" import="java.util.*"%>
<html>
<head>
</head>
<body>
<%!
int id;
String first_name="";
String last_name="";
String username="";
String city="";
String state="";
String country="";
List  userList=null;
%>

<%

if(request.getAttribute("userList")!=null && request.getAttribute("userList")!="")
{
		userList = (ArrayList)request.getAttribute("userList");
		id=Integer.parseInt(userList.get(0).toString());
		username=userList.get(1).toString();
		first_name=userList.get(2).toString();
		last_name=userList.get(3).toString();
		city=userList.get(4).toString();
		state=userList.get(5).toString();
		country=userList.get(6).toString();
		//out.println(id);
}
%>

<form name="userform" method="post" action="../editUser">
<br><br><br>
<table align="center" width="300px" style="background-color:#EDF6EA;border:1px solid #000000;">
<input type="hidden" name="id" value="<%=id%>">
<tr><td colspan=2 style="font-weight:bold;" align="center">Edit User</td></tr>
<tr><td colspan=2 align="center" height="10px"></td></tr>
	<tr>
		<td>First Name</td>
		<td><input type="text" name="first_name" value="<%=first_name%>"></td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td><input type="text" name="last_name" value="<%=last_name%>"></td>
	</tr>
	<tr>
		<td>UserName</td>
		<td><input type="text" name="username" value="<%=username%>"></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type="password" name="password" value=""></td>
	</tr>
	
	<tr>
		<td>City</td>
		<td><input type="text"  name="city" value="<%=city%>"></td>
	</tr>
	<tr>
		<td>State</td>
		<td><input type="text" name="state" value="<%=state%>"></td>
	</tr>
	<tr>
		<td>Country</td>
		<td><input type="text" name="country" value="<%=country%>"></td>
	</tr>
	
	<tr>
		<td></td>
		<td><input type="submit" name="Submit" value="Edit" style="background-color:#49743D;font-weight:bold;color:#ffffff;"></td>
	</tr>
	<tr><td colspan=2 align="center" height="10px"></td></tr>
</table>
</form>
</body>
</html>


