<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String err = request.getParameter("err");
	String msg = "";
	
	if(err != null)
	{
		switch(err)
		{
			case "10" : msg = "등록할 수 없습니다."; break;
			case "20" : msg = "DB 에러 발생"; break;
		}
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>글쓰기</title>
		<style> /* css는 밑에 적지 않는다. 동작은 하지만 번쩍임 현상이 일어남. */
			#msg { color : red; }
		</style>
	</head>
	<body>
		<div id="msg"><%=msg %></div>
		<div>
			<form id="frm" action="/jsp/boardWriteProc.jsp" method="post" onsubmit="return chk()"> <!-- post : url에 아무것도 나타나지 않지만, get : 각 요소들과 값이 나온다. -->
				<div><label>제목: <input type="text" name="title"></label></div> <!-- name은 key값 -->
				<div><label>내용: <textarea name="cont"></textarea></label></div>
				<div><label>작성자: <input type="text" name="i_student"></label></div>
				<div><input type="submit" value="글등록"></div>
			</form>
		</div>
		<script> // script를 body 밑에 적는 이유 : 위의 div, form등 elements 들을 사용하기 때문이다. 하지만 선언은 head안에.
			function eleValid(ele, nm)
			{
				if(ele.value.length == 0)
				{
					alert(nm + "을(를) 입력해 주세요.");
					ele.focus()
					return true;
				}
			}
			
			function chk()
			{
				console.log(`title : \${frm.title.value}`); // console 내용 표시
			
				if(eleValid(frm.title, '제목')) // frm의 자식 title이라는 name을 가진 값이 없다면.
				{
					return false;
				}
				else if(eleValid(frm.cont, '내용'))
				{
					return false;
				}
				else if(eleValid(frm.i_student, '작성자'))
				{
					return false;
				}
			}
		</script>
	</body>
</html>