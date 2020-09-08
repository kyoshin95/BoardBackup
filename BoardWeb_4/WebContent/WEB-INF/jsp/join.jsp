<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	* { margin: 0px; padding: 0px;}
	body { font-family: 'Noto Sans KR', sans-serif;}
	#container { width: 100%; height: 100vh; margin: 0 auto; text-align: center; background: #2B3032} 
	#container h1{padding: 70px; color: #dedede;}
	#container form {text-align: center; margin-top: 100px;}
	#container form input { border: none; width: 480px; height: 45px; margin-bottom: 20px; border-radius : 10px;}
	#button { background : #8e8e8e;  width: 500px; margin-top : 30px; font-family:나눔고딕;}
	#button:hover{ background : #cecece; color : 2e2e2e; border: 1px solid white; font-weight: bold;
	transition: all 0.5s;
    	transition-timing-function:ease; border-radius: 0px;}
	.input { padding-left : 20px; font-family:나눔고딕;}
	span { color: red; font-weight: bold; font-family:나눔고딕;}
	hr { border : 1px solid #4e4e4e}
	a { text-decoration:none; font-size : 12px; margin-top : -10px;font-weight : bold; color : #dedede; font-family: 'Noto Sans KR', sans-serif;}
</style>
</head>
<body>
	<div id="container">
		<h1>회원가입</h1>
		<hr>
		<form id="frm"action="/join" method="post" onsubmit="return chk()">
			<div><label><input type="text" class="input" name="user_id" placeholder="아이디" required value="${data.user_id}"></label></div>
			<div><label><input type="password" class="input" name="user_pw" placeholder="비밀번호"></label></div>
			<div><label><input type="password" class="input" name="user_pwre" placeholder="확인 비밀번호"></label></div>
			<div><label><input type="text" class="input"name="nm" placeholder="이름" value="${data.nm}"></label></div>
			<div><input type="email"class="input" name="email" placeholder="이메일" value="${data.email}"></div>
			<div><input id="button" type="submit" value="회원가입"></div>
		</form>
		<div><h3><span>${msg}</span></h3></div>
		<a href="/login">뒤로가기</a>
	</div>
	<script>
		function chk()
		{	
			if(frm.user_id.value.length < 8) // id 검증(글자수)
			{
				alert('아이디는 8글자 이상이 되어야 합니다.')
				frm.user_id.focus()
				return false
			}
			
			else if(frm.user_pw.value.length < 8) // pw 검증(글자수)
			{

				alert('비밀번호는 8글자 이상이 되어야 합니다.')
				frm.user_pw.focus()
				return false	
			}
			
			else if(frm.user_pw.value != frm.user_pwre.value) // pw 검증(확인)
			{
				alert('비밀번호를 확인해 주세요.')
				frm.user_pw.focus()
				return false
			}
			
			if(frm.nm.value.length > 0) // 이름 검증
			{
				const korean = /[^가-힣]/
				
				if(korean.test(frm.nm.value))
				{
					alert('이름은 한글만 입력해 주세요.')
					frm.nm.focus()
					return false
				}
			}
			
			if(frm.email.value.length > 0) // 이메일 검증
			{
				const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
				
				if(!email.test(frm.email.value))
				{
					alert('이메일을 확인해 주세요.')
					frm.email.focus()
					return false
				}
			}
		}
	</script>
</body>
</html>