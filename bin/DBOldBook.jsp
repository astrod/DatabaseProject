<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mysql.jdbc.Driver" %>
<%@ page improt = "java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인하세요!</title>
</head>
<body>
	Connection conn;
	Statement stmt;
	ResultSet rs;		
	String sql;
	PreparedStatement pstmt;
		
	String addr = "jdbc:mysql://10.73.45.60/nextdb";
	String user = "jongun";
	String pw = "next123!@#";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");			
		} catch(ClassNotFoundException e) {
			System.err.println("Driver Error" + e.getMessage());
			return false;
		}
		System.out.println("Driver Loading Success");
		
		try {
			conn = DriverManager.getConnection(addr, user, pw);
			System.out.println("Connect Success");
			conn.setAutoCommit(false);
			pstmt = conn.preparedStatement
		}

</body>
</html>