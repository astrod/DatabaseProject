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

	String inputId = new String(request.getParameter("id").getBytes("8859_1"), "UTF-8");
	String inputPwd = new String(request.getParameter("pwd").getBytes("8859_1"), "UTF-8");

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
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement("CALL Login(?, ?, @var_result);");
		pstmt.setString(1, inputId);
		pstmt.setString(2, inputPwd);
		pstmt.execute();
		pstmt.close();

		pstmt = conn.prepareStatement("select @var_result;");
		pstmt.execute();
		rs = pstmt.getResultSet();
		rs.next();
				
		String returnStr = rs.getString("@var_result");
		pstmt.close();
				
		if(returnStr.equals("incorrect ID")) {
			out.println("아이디를 잘못 입력하셨습니다. 다시 입력해 주십시오.");
		}
		else if(returnStr.equals("incorrect Password")) {
			out.println("패스워드를 잘못 입력하셨습니다. 다시 입력해 주십시오.");
		}
		else { 
			response.sendRedirect("/oldBook/mainpage.jsp?name=" + inputId);
		}
		 
		
		
	}  catch (Exception e) {
			System.err.println("Driver Error" + e.getMessage());
		}
		
	finally {
		if(rs != null) rs.close();
		if(stmt != null)stmt.close();
		if(conn != null) conn.close();
	}
	%>
	
	
	</body>
</html> 
	
