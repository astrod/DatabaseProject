<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.Random" %>
    
   <html>
     <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 과정</title>
</head>

<%
	String SellingNum = request.getParameter("SellingNum");
	out.println(SellingNum);
     	
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
		Random random = new Random();
		int CliNum = random.nextInt(99) + 1;
		conn = DriverManager.getConnection(addr, user, pwd);
		System.out.println("Connect Success");
		pstmt = conn.prepareStatement("CALL Buying(?, ?, ?);");
		pstmt.setString(1, SellingNum);
		pstmt.setInt(2, CliNum);
		pstmt.setString(3, "신용카드");
		pstmt.execute();
		pstmt.close();
 		
	}  catch (Exception e) {
			out.println("Driver Error" + e.getMessage());
	} finally {
		if(rs != null) rs.close();
		if(stmt != null)stmt.close();
		if(conn != null) conn.close();
		
		//response.sendRedirect("/oldBook/mainpage.jsp");
	}
	%>
	</body>
</html> 