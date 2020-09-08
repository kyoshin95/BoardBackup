<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<form id="frm" class="frm" action="/user/joinProc" method="post" onsubmit="return joinCheck()">
		<div id="idCheckResult" class="msg"></div>
		<div><input type="text" name="user_id" placeholder="아이디"><button  type="button" onclick="idCheck()">중복확인</button></div>
		<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
		<div><input type="password" name="user_pwre" placeholder="비밀번호 확인"></div>
		<div><input type="text" name="nm" placeholder="이름"></div>
		<div><input type="submit" value="회원가입"></div>
	</form>
	<div>
		<a href="/user/login">로그인</a>
	</div>
</div>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <%-- AXIOS import --%>
<script>
function idCheck() 
{
	const user_id = frm.user_id.value
	axios.get('/user/ajaxIdCheck', {
		params: {
			user_id
		} 
	}).then(function(res) {
		console.log(res)
		if(res.data.result == 2) { //아이디 없음
			idCheckResult.innerText = '사용할 수 있는 아이디입니다.'
		} else if(res.data.result == 3) { //아이디 중복됨
			idCheckResult.innerText = '이미 사용중입니다.'
		}
	})
}
	
	function joinCheck()
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
	}
</script>