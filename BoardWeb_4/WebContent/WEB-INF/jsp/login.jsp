<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<style>
		* { margin: 0px; padding: 0px;}
		body{ background: #2B3032;  margin-top : 150px; font-family: 'Noto Sans KR', sans-serif;}
		#container {  width: 600px; height: 600px; margin: 0 auto; text-align: center; background-color : #dfdfdf} 
		#container h3{ padding: 70px; color: #3e3e3e; font-size: 24px;}
		#container form {width: 100%; height: 40%; text-align: center; margin-top: 50px; display:flex; flex-direction: column; justify-content: center; align-items: center;}
		#container form input { width: 360px; border:none; height: 45px; margin-bottom: 10px; border-radius : 10px; font-family:나눔고딕; }
		.input { padding-left : 20px;}
		#button { background : #8e8e8e;  width: 500px; margin-top : 30px; font-family:나눔고딕;}
		#button:hover{background : #9e9e9e; color : 2e2e2e; font-weight: bold;
	transition: all 0.5s;
    	transition-timing-function:ease; border-radius: 0px;}
		a { text-decoration:none; font-size : 12px; margin-top : -10px;font-weight : bold; color : #2e2e2e; font-family:나눔고딕;}
		h4 { color: #ff2222;}
	</style>
</head>
<body>
	<div id="container">
		<h3>로그인</h3>
		<hr>
		<form id="frm"action="/login" method="post" onsubmit="return login()">
			<div><label><input class="input" type="text" name="user_id" placeholder="아이디" value="${data.user_id}"></label></div>
			<div><label><input class="input"type="password" name="user_pw" placeholder="비밀번호"></label></div>
			<h4><span>${msg}</span></h4>
			<div><input id="button" type="submit" value="로그인"></div>
		</form>
		<a href="/join">회원가입</a>
	</div>
	<script>
		function login()
		{	
			if(frm.user_id.value.length == 0) // id 검증(글자수)
			{
				alert('아이디를 입력해주세요.')
				frm.user_id.focus()
				return false
			}
			
			else if(frm.user_pw.value.length == 0) // pw 검증(글자수)
			{
	
				alert('비밀번호를 입력해주세요.')
				frm.user_pw.focus()
				return false	
			}
			return true
		}
	</script>
</body>
</html>