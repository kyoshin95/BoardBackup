<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	* { margin: 0 px; padding: 0 px; color: white;}
	body { background: #2B3032; font-family: "나눔고딕"; }
	#container { padding-top: 50px;width: 1000px; height: 800px; margin: 0 auto; text-align: center;}
	#tbl { padding-top: 20px; width: 350px; height: 250px; margin: 0 auto; color: white;}
	.pro_img { width: 400px; height : 500px; }
</style>
</head>
<body>
	<div id="container">
		<h2>프로필</h2>
		<c:choose>
			<c:when test="${data.profile_img != null}">
				<img class="pro_img" src="img/user/${loginUser.i_user}/${data.profile_img}">
			</c:when>
			<c:otherwise>
				<img class="pro_img"  src="img/default_profile.jpg">
			</c:otherwise>
		</c:choose>
		<table id="tbl">
			<tr>
				<td colspan="2">
					<form action="/profile" method="post" enctype="multipart/form-data">
						<label><input type="file" name="profile_img" accept="image/*"></label>
						<%-- 이미지만 넣고싶다면 accept="image/*"을 넣어줘야한다 --%>
						<input type="submit" value="업로드">
					</form>
				</td>
			</tr>
			<tr>
				<th>ID</th>
				<td>${data.user_id}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${data.nm}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${data.email}</td>
			</tr>
			<tr>
				<th>가입일시</th>
				<td>${data.r_dt}</td>
			</tr>
		</table>
	</div>
</body>
</html>