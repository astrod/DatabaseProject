<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <html>
     <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 페이지</title>
</head>
<div style="border-style:solid; margin-left:100px; height: 900px; width: 700px">



<%

	
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
		pstmt = conn.prepareStatement("select name, Page, originPrice, Author, s.SellingCost, s.SellingNum from Book b inner join SellingList s on b.ISBN = s.Book_ISBN Limit 0, 20;");
		
		pstmt.execute();

		rs = pstmt.getResultSet();
		
		while(rs.next()) {
			String Title = rs.getString("name");
			String Author = rs.getString("Author");
			int Page = rs.getInt("Page");
			int Cost = rs.getInt("SellingCost");
			int SellingNum = rs.getInt("SellingNum");
			%> 
			<br>
			<hr />
			
			<%
			out.println(Title);
			%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%
			out.println(Author);
			%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<% 
			out.println(Page+"P");  
			%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%
			out.println(Cost+"원");  
			%>
			
			<input type=hidden name = "SellingNum" value="<%=SellingNum%>">
			<button type="button" onclick="location.href='buy.jsp'">구매하기</button>
			
			
			<%
			
		}
		pstmt.close();
	}  catch (Exception e) {
			System.err.println("Driver Error" + e.getMessage());
		}
		
	finally {
		if(rs != null) rs.close();
		if(stmt != null)stmt.close();
		if(conn != null) conn.close();
	}
	%>
	</div>
	</body>
</html> 
	