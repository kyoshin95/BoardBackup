<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.koreait.board.db.BoardDAO" %>
<%@ page import="com.koreait.board.vo.BoardVO" %>
<%@ page import="java.util.*" %>
<%
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("data"); // object형이라서 List<BoardVO>형으로 캐스팅
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판</title>
		<style>
			table { width: 1000px; border-collapse: collapse; text-align: center;}
			th, tr, td { width: 300px; height: 30px; border : 1px solid black;}
			.itemRow:hover { background-color: #dfdfdf; cursor:pointer;}
		</style>
	</head>
	<body>
		<div>
			<a href="/boardWrite"><button>글쓰기</button></a>
		</div>
		<table>
			<tr>
				<th>No</th>
				<th>이름</th>
				<th>작성자번호</th>
			</tr>
			<% for(BoardVO vo : list) {%> <%-- List반복 --%>
			<tr class="itemRow" onclick="moveToDetail(<%=vo.getI_board() %>)">
				<td><%=vo.getI_board()%></td>
				<td><%=vo.getTitle()%></td>
				<td><%=vo.getI_student()%></td>
			</tr>
			<% } %>
		</table>
		<script>
			function moveToDetail(i_board)
			{
				console.log('moveToDetail - i_board : '+ i_board);
				location.href = 'boardDetail?i_board=' + i_board; 
			}
		</script>
	</body>
</html>