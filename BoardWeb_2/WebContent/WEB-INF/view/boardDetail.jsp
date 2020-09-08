<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판</title>
		<style>
			table { width: 500px; border-collapse: collapse; text-align: center;}
			tr, td { width: 250px; height: 80px; border : 1px solid black;}
			th { width: 100px; border : 1px solid black;}
		</style>
	</head>
	<body>
		<div>
			<button onclick="doDel(${data.i_board})">삭제</button>
			<button onclick="doMod(${data.i_board})">수정</button>
		</div>
		<table>
			<tr>
				<th>No</th>
				<td>${data.i_board}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${data.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${data.cont}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${data.i_student}</td>
			</tr>
		</table>
		<script>
			function doDel(i_board)
			{
				if(confirm('삭제 하시겠습니까?'))
				{
					location.href='/boardDel?i_board=' + i_board; 
				}
			}
			function doMod(i_board)
			{
				location.href='/boardMod?i_board=' + i_board;
			}
		</script>
	</body>
</html>