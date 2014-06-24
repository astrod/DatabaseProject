<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
<form action='joinProcess.jsp' method='post'>
이름  : <input type='text' size='10' maxlength='15' name='name'> <br>
성별  : <input type='text' size='10' maxlength='15' name='sex'> <br>
주소  : <input type='text' size='10' maxlength='50' name='address'> <br>
아이디 : <input type='text' size='10' maxlength='15' name='id'> <br>
패스워드   : <input type='password' size='10' maxlength='15' name='pwd'> <br>
휴대전화   : <input type='text' size='10' maxlength='15' name='phone'> <br>
닉네임    : <input type='text' size='10' maxlength='15' name='nick'> <br>
<input type='submit' value='Join'/>


</body>
</html>