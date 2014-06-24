<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="utf-8">
  <title>메인 페이지</title>
</head>
<body>
<form action='LoginProcess.jsp' method='post'>
ID : <input type='text' size='10' maxlength='15' name='id'> <br>
Password : <input type='password' size='10' maxlength='15' name='pwd'>
<input type='submit' value='Login'/> <br>
<a href="join.jsp">회원가입</a>

</form>

</body>
</html>

