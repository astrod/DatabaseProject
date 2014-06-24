<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <html>
     <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 과정</title>
</head>
<%
	String inputName = new String(request.getParameter("name").getBytes("8859_1"), "UTF-8");
	String inputSex = new String(request.getParameter("sex").getBytes("8859_1"), "UTF-8");
	String inputAddress = new String(request.getParameter("address").getBytes("8859_1"), "UTF-8");
	String inputID = new String(request.getParameter("id").getBytes("8859_1"), "UTF-8");
	String inputPwd = new String(request.getParameter("pwd").getBytes("8859_1"), "UTF-8");
	String inputPhone = new String(request.getParameter("phone").getBytes("8859_1"), "UTF-8");
	String inputNick = new String(request.getParameter("nick").getBytes("8859_1"), "UTF-8");
	
     	
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;
	String sql;
	PreparedStatement pstmt;

	String addr = "jdbc:mysql://10.73.45.60/nextdb";
	String user = "jongun";
	String pwd = "next123!@#";

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.err.println("Driver Error" + e.getMessage());
	}
	System.out.println("Driver Loading Success");
	
	try {
		conn = DriverManager.getConnection(addr, user, pwd);
		System.out.println("Connect Success");
		pstmt = conn.prepareStatement("insert into client values (null, ?, ?, ?, ?, md5(?), ?, ?)" );
		out.println(inputName + "," + inputNick);
		pstmt.setString(1, inputName);
		pstmt.setString(2, inputSex);
		pstmt.setString(3, inputAddress);
		pstmt.setString(4, inputID);
		pstmt.setString(5, inputPwd);
		pstmt.setString(6, inputPhone);
		pstmt.setString(7, inputNick);
		pstmt.execute();
		pstmt.close();

		
	}  catch (Exception e) {
			out.println("Driver Error" + e.getMessage());
	} finally {
		if(rs != null) rs.close();
		if(stmt != null)stmt.close();
		if(conn != null) conn.close();
		
		response.sendRedirect("/oldBook/index.jsp");
	}
	%>
	</body>
</html> 
	